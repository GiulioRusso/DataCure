package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.ImageDAO;
import it.unicas.DataCure.dbutil.Configuration;
import org.apache.struts2.ServletActionContext;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * The UpdateImageAction class is an action class that handles updating an image in the database.
 * It interacts with the ImageDAO class to update the image details and manages the update process.
 */
public class UpdateImageAction extends ActionSupport {

    private String ID;  // Represents the image ID.
    private boolean label;  // Indicates whether the image is labeled.
    private String dDesc;   // Stores the doctor description of the image.
    private String updateMessage;   // Stores the message indicating the result of update the image.
    private static final Logger logger = Logger.getLogger(LoginAction.class.getName()); // logger instance for logging messages.

    /**
     * Executes the update image action and performs the image update in the database.
     *
     * @return The status code indicating the success or failure of the image update.
     */
    public String execute() {

        String statusCode = "";

        // Update image
        int check= ImageDAO.updateImage(ID, label, dDesc);

        if(check == 0) {
            updateMessage = "MESSAGE: Image updated successfully!";
            // Write on log.txt
            try (FileWriter writer = new FileWriter(Configuration.getPathVariable("log_path"), true)) {
                writer.write(LocalDateTime.now() + " " +
                        ServletActionContext.getRequest().getSession().getAttribute("loggedinUser") +
                        " ----- " + "Image " + ID + " has been updated.\n\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
            logger.severe(updateMessage);
            addActionError(updateMessage);
            statusCode = "success";
        }
        else if(check == 1) {
            updateMessage = "ERROR: Invalid description. Description can't be empty. Image cannot be updated.";
            logger.severe(updateMessage);
            addActionError(updateMessage);
            statusCode = "error";
        }
        else if(check == 2) {
            updateMessage = "ERROR: Failed to update the Image. Exception occurred in ImageDAO.updateImage";
            logger.severe(updateMessage);
            addActionError(updateMessage);
            statusCode = "error";
        }

        return statusCode;
    }

    /**
     * Returns the ID of the image.
     *
     * @return The ID of the image
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns the label status of the image.
     *
     * @return The label status of the image
     */
    public boolean isLabel() {
        return label;
    }

    /**
     * Returns the doctor description of the image.
     *
     * @return The doctor description of the image
     */
    public String getdDesc() {
        return dDesc;
    }

    /**
     * Sets the ID of the image.
     *
     * @param ID The ID of the image to be set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Sets the label status of the image.
     *
     * @param label The label status of the image to be set
     */
    public void setLabel(boolean label) {
        this.label = label;
    }

    /**
     * Sets the doctor description of the image.
     *
     * @param dDesc The doctor description of the image to be set
     */
    public void setdDesc(String dDesc) {
        this.dDesc = dDesc;
    }
}
