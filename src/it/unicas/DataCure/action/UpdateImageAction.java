package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.ImageDAO;
import it.unicas.DataCure.dao.LoginDAO;
import it.unicas.DataCure.pojo.Login;
import org.apache.struts2.ServletActionContext;

import java.util.List;
import java.util.logging.Logger;

/**
 * The UpdateAction class is responsible for updating a user's password in the database.
 */
public class UpdateImageAction extends ActionSupport {

    private String ID;
    private boolean label;
    private String dDesc;
    private String updateMessage;
    private static final Logger logger = Logger.getLogger(LoginAction.class.getName());

    public String execute() {

        String statusCode = "";
        ServletActionContext.getRequest().getSession().setAttribute("updateImage", ID);
        System.out.println(ID);
        System.out.println(label);
        System.out.println(dDesc);
        int check= ImageDAO.updateImage(ID, label, dDesc);
        if(check == 0) {
            updateMessage = "MESSAGE: Image updated successfully!";
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

    public String getID() {
        return ID;
    }

    public boolean isLabel() {
        return label;
    }

    public String getdDesc() {
        return dDesc;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setLabel(boolean label) {
        this.label = label;
    }

    public void setdDesc(String dDesc) {
        this.dDesc = dDesc;
    }
}
