package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.ImageDAO;
import it.unicas.DataCure.pojo.Image;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Date;

public class UploadAction extends ActionSupport {

    private File imageFile;
    private String imageFileFileName;
    private String operatorDescription;

    public String execute() {
        try {
            // Get the uploaded file
            File uploadedFile = getImageFile();

            // Set the path for saving the uploaded file
            String uploadPath = "/path/to/project"; // Modify this path as per your project's requirements

            // Save the uploaded file to the specified path
            File destination = new File(uploadPath, getImageFileFileName());
            FileUtils.copyFile(uploadedFile, destination);

            // Create an Image object with the necessary details
            Image image = new Image();
            image.setUploadDate(new Date());
            image.setLabeled(false);
            image.setOperatorDescription(getOperatorDescription());
            image.setImagePath(destination.getPath());

            // Insert the image details into the database
            ImageDAO.addImage(image);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
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
