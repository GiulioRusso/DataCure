package it.unicas.DataCure.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The Image class represents an image entity in the system.
 */
public class Image {

    private String idImage;
    private Timestamp uploadDate;
    private boolean labeled;
    private String operatorDescription;
    private String doctorDescription;
    private String imagePath;

    public Image() {

    }

    public Image(String imageID, Timestamp imageDate, boolean label, String o_description, String d_description) {

        setIdImage(imageID);
        setUploadDate(imageDate);
        setLabel(label);
        setOperatorDescription(o_description);
        setDoctorDescription(d_description);
    }
    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean isLabeled() {
        return labeled;
    }

    public void setLabel(boolean labeled) {
        this.labeled = labeled;
    }

    public String getOperatorDescription() {
        return operatorDescription;
    }

    public void setOperatorDescription(String operatorDescription) {
        this.operatorDescription = operatorDescription;
    }

    public String getDoctorDescription() {
        return doctorDescription;
    }

    public void setDoctorDescription(String doctorDescription) {
        this.doctorDescription = doctorDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}