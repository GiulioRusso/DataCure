package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import it.unicas.DataCure.dao.ImageDAO;
import org.apache.commons.io.FileUtils;

public class UploadAction extends ActionSupport {

    private File imageFile;
    private String imageFileFileName;
    private String operatorDescription;
    private String uploadMessage;

    public String execute() throws IOException {

        // Specify the path to save the uploaded image
        String databasePath = "C:\\Users\\gianf\\IdeaProjects\\DataCure\\web\\resources\\database-images";

        // Create a File object representing the target file
        String targetFileName = imageFileFileName;
        File targetFile = new File(databasePath, targetFileName);

        // Call the addImage method to save the image details in the database
        int addImageResult = ImageDAO.addImage(targetFileName, getOperatorDescription());
        String statusCode = "";

        if (addImageResult == 0) {
            // Copy the uploaded file to the target location only if the addImage function return 0
            FileUtils.copyFile(imageFile, targetFile);
            uploadMessage = "MESSAGE: Image added successfully!";
            statusCode = "success"; // Return a success result
        } else if (addImageResult == 1) {
            uploadMessage = "ERROR: Image named '" + targetFileName + "' already exists.";
            statusCode = "error";
        } else if (addImageResult == 2) {
            uploadMessage = "ERROR: Invalid extension. Image accepted in .jpg/.png/.tiff. Image cannot be added.";
            statusCode = "error";
        } else if (addImageResult == 3) {
            uploadMessage = "ERROR: Invalid description. Description can't be empty. Image cannot be added.";
            statusCode = "error";
        } else if (addImageResult == 4) {
            uploadMessage = "ERROR: Failed to add the Image. Exception occurred in ImageDAO.addImage";
            statusCode = "error";
        }

        // Print message on the welcomeOperator.jsp
        addActionError(uploadMessage);

        return statusCode;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileFileName() {
        return imageFileFileName;
    }

    public void setImageFileFileName(String imageFileFileName) {
        this.imageFileFileName = imageFileFileName;
    }

    public String getOperatorDescription() {
        return operatorDescription;
    }

    public void setOperatorDescription(String operatorDescription) {
        this.operatorDescription = operatorDescription;
    }
}
