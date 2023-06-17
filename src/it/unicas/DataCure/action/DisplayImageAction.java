package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;

public class DisplayImageAction extends ActionSupport {

    private String ID;

    public String execute() {
        System.out.println(ID);
        return "success";
    }

    public String getID() {return ID;}

    public void setID(String id) {ID = id;}
}
