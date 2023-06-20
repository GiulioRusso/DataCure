package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dbutil.Configuration;
import java.sql.Timestamp;

/**
 * The DisplayImageAction class is responsible for displaying image selected to edit by the Doctor
 */
public class DisplayImageAction extends ActionSupport {

    private String ID;  // Represents the image ID.
    private String imgPath; // Stores the image path.
    private Timestamp date; // Represents the timestamp of the image.
    private boolean label;  // Indicates whether the image is labeled.
    private String oDesc;   // Stores the operator description of the image.
    private String dDesc;   // Stores the doctor description of the image.

    /**
     * Executes the logic for displaying an image.
     *
     * @return The result of the action execution. Always returns "success".
     */
    public String execute() {

        // Identify image path
        imgPath = Configuration.getPathVariable("relative_database_path") + ID;

        return "success";
    }

    /**
     * Gets the image ID.
     *
     * @return The image ID.
     */
    public String getID() {return ID;}

    /**
     * Sets the image ID.
     *
     * @param id The image ID to set.
     */
    public void setID(String id) {ID = id;}

    /**
     * Gets the image path.
     *
     * @return The image path.
     */
    public String getImgPath(){return imgPath;}

    /**
     * Sets the image path.
     *
     * @param path The image path to set.
     */
    public void setImgPath(String path){imgPath = path;}

    /**
     * Gets the timestamp of the image.
     *
     * @return The timestamp of the image.
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Gets the label indicating whether the image is labeled.
     *
     * @return {@code true} if the image is labeled, {@code false} otherwise.
     */
    public boolean isLabel() {
        return label;
    }

    /**
     * Gets the original description of the image.
     *
     * @return The original description of the image.
     */
    public String getoDesc() {
        return oDesc;
    }

    /**
     * Gets the modified description of the image.
     *
     * @return The modified description of the image.
     */
    public String getdDesc() {
        return dDesc;
    }

    /**
     * Sets the timestamp of the image.
     *
     * @param date The timestamp to set.
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Sets the label indicating whether the image is labeled.
     *
     * @param label The label to set.
     */
    public void setLabel(boolean label) {
        this.label = label;
    }

    /**
     * Sets the original description of the image.
     *
     * @param oDesc The original description to set.
     */
    public void setoDesc(String oDesc) {
        this.oDesc = oDesc;
    }

    /**
     * Sets the modified description of the image.
     *
     * @param dDesc The modified description to set.
     */
    public void setdDesc(String dDesc) {
        this.dDesc = dDesc;
    }
}
