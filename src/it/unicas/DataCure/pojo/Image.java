package it.unicas.DataCure.pojo;

import java.io.File;
import java.util.Date;

public class Image {

    private int idimage;
    private Date uploadDate;
    private boolean labeled;
    private String operatorDescription;
    private String doctorDescription;
    private String imagePath;

    private File imageFile;

    public File getImageFile() {
        return imageFile;
    }


    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    // Getters and setters

    public int getIdimage() {
        return idimage;
    }

    public void setIdimage(int idimage) {
        this.idimage = idimage;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean isLabeled() {
        return labeled;
    }

    public void setLabeled(boolean labeled) {
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