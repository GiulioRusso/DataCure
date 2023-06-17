package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.Timestamp;
import java.util.Date;

public class DisplayImageAction extends ActionSupport {

    private String ID;

    private String imgPath;

    private Timestamp date;

    private boolean label;
    private String oDesc;
    private String dDesc;
    public String execute() {

        // Identify image path
        imgPath = "resources\\database-images\\" + ID;

        return "success";
    }

    public String getID() {return ID;}

    public void setID(String id) {ID = id;}

    public String getImgPath(){return imgPath;}

    public void setImgPath(String path){imgPath = path;}

    public Timestamp getDate() {
        return date;
    }

    public boolean isLabel() {
        return label;
    }

    public String getoDesc() {
        return oDesc;
    }

    public String getdDesc() {
        return dDesc;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setLabel(boolean label) {
        this.label = label;
    }

    public void setoDesc(String oDesc) {
        this.oDesc = oDesc;
    }

    public void setdDesc(String dDesc) {
        this.dDesc = dDesc;
    }
}
