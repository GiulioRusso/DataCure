package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The WelcomeOperatorAction class is an action class that handles the welcome page for the operator user.
 */
public class WelcomeOperatorAction extends ActionSupport{

	/**
	 * Executes the welcome operator action.
	 *
	 * @return The status code indicating the success of the action.
	 */
	public String execute() {
		return "success";
	}
	
}
