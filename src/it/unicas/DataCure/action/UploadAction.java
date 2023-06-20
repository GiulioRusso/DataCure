package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import it.unicas.DataCure.dao.ImageDAO;
import it.unicas.DataCure.dbutil.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * The UploadAction class is an action class that handles uploading an image to the server and adding its details to the database.
 * It interacts with the ImageDAO class to perform the upload and database operations.
 */
public class UploadAction extends ActionSupport {

    private File imageFile; // Represents the uploaded image file.
    private String imageFileFileName;   // Stores the filename of the uploaded image file.
    private String operatorDescription; // Stores the operator's description of the image.
    private String uploadMessage;   // Stores the message indicating the result of the upload.

    /**
     * Executes the upload action and performs the image upload and database operations.
     *
     * @return The status code indicating the success or failure of the upload.
     * @throws IOException If an I/O error occurs during the file copy process.
     */
    public String execute() throws IOException {

        String statusCode = "";

        // Specify the path to save the uploaded image
        String databasePath = Configuration.getPathVariable("database_path");
        String tomcatResourcePath = Configuration.getPathVariable("resource_path_tomcat");

        // If no file is uploaded
        if(imageFile == null) {
            // Print the error message
            uploadMessage = "ERROR: No file selected.";
            addActionError(uploadMessage);
            statusCode = "error";
            return statusCode;
        }

        // Create a File object representing the target file
        String targetFileName = imageFileFileName;
        File targetFile = new File(databasePath, targetFileName);
        File targetFileTomcat = new File(tomcatResourcePath, targetFileName);

        // Call the addImage method to save the image details in the database
        int addImageResult = ImageDAO.addImage(targetFileName, getOperatorDescription());

        if (addImageResult == 0) {
            // Copy the uploaded file to the target location only if the addImage function return 0
            FileUtils.copyFile(imageFile, targetFile);
            FileUtils.copyFile(imageFile, targetFileTomcat);
            uploadMessage = "MESSAGE: Image added successfully!";
            // Write on log.txt
            try (FileWriter writer = new FileWriter(Configuration.getPathVariable("log_path"), true)) {
                writer.write(LocalDateTime.now() + " " +
                        ServletActionContext.getRequest().getSession().getAttribute("loggedinUser") +
                        " ----- " + imageFileFileName + " has been uploaded.\n\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
            statusCode = "success"; // Return a success result
        } else if (addImageResult == 1) {
            uploadMessage = "ERROR: Image named '" + targetFileName + "' already exists.";
            statusCode = "error";
        } else if (addImageResult == 2) {
            uploadMessage = "ERROR: Invalid extension. Image accepted in .jpg/.png/.tiff. Image cannot be added.";
            statusCode = "error";
        } else if (addImageResult == 3) {
            uploadMessage = "ERROR: Invalid description. Description can't be null. Image cannot be added.";
            statusCode = "error";
        } else if (addImageResult == 4) {
            uploadMessage = "ERROR: Invalid image name. Image name cannot contain spaces. Image cannot be added.";
            statusCode = "error";
        } else if (addImageResult == 5) {
            uploadMessage = "ERROR: Failed to add the Image. Exception occurred in ImageDAO.addImage";
            statusCode = "error";
        }

        // Print message on the welcomeOperator.jsp
        addActionError(uploadMessage);

        return statusCode;
    }

    /**
     * Gets the uploaded image file.
     *
     * @return The uploaded image file.
     */
    public File getImageFile() {
        return imageFile;
    }

    /**
     * Sets the uploaded image file.
     *
     * @param imageFile The uploaded image file to be set.
     */
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * Gets the filename of the uploaded image file.
     *
     * @return The filename of the uploaded image file.
     */
    public String getImageFileFileName() {
        return imageFileFileName;
    }

    /**
     * Sets the filename of the uploaded image file.
     *
     * @param imageFileFileName The filename of the uploaded image file to be set.
     */
    public void setImageFileFileName(String imageFileFileName) {
        this.imageFileFileName = imageFileFileName;
    }

    /**
     * Gets the operator's description of the image.
     *
     * @return The operator's description of the image.
     */
    public String getOperatorDescription() {
        return operatorDescription;
    }

    /**
     * Sets the operator's description of the image.
     *
     * @param operatorDescription The operator's description of the image to be set.
     */
    public void setOperatorDescription(String operatorDescription) {
        this.operatorDescription = operatorDescription;
    }
}
