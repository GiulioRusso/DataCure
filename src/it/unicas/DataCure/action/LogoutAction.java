package it.unicas.DataCure.action;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * The LogoutAction class is an action class that handles the logout functionality.
 * It invalidates the user session and redirects to the login page.
 */
public class LogoutAction extends ActionSupport{

	/**
	 * Executes the logout action by invalidating the user session.
	 *
	 * @return The result code indicating the redirection to the login page.
	 */
	public String execute( ) {
		ServletActionContext.getRequest().getSession().invalidate();
		return INPUT;
	}
}
