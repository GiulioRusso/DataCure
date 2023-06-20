package it.unicas.DataCure.action;

import it.unicas.DataCure.dao.LoginDAO;
import it.unicas.DataCure.dbutil.Configuration;
import it.unicas.DataCure.pojo.Login;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * The LoginAction class is an action class that handles the login functionality.
 * It interacts with the LoginDAO class to validate user credentials and manages the login process.
 */
public class LoginAction extends ActionSupport {

	private static final Logger logger = Logger.getLogger(LoginAction.class.getName());	// logger instance for logging messages.
	private String userID; // The ID entered by the user.
	private String userPassword;	// The password entered by the user.

	/**
	 * Executes the login action and determines the status code based on the user's credentials.
	 * The status code indicates the role of the user or prompts for further input.
	 *
	 * @return The status code indicating the role of the user (doctor, operator, or admin) or prompting for further input.
	 */
	public String execute() {

		String statusCode;

		boolean isUserValid = LoginDAO.isUserValid(new Login(userID, userPassword));
		ServletActionContext.getRequest().getSession().setAttribute("loggedinUser", userID);

		if (isUserValid) {
			if (userID.startsWith("D")) {
				statusCode = "doctor";
				// Write on log.txt
				try (FileWriter writer = new FileWriter(Configuration.getPathVariable("log_path"), true)) {
					writer.write(LocalDateTime.now() + " " + userID + " (Doctor) has logged in DataCure.\n\n");
				} catch (IOException e) {
					System.out.println("An error occurred while writing to the file: " + e.getMessage());
				}
			} else if (userID.startsWith("O")) {
				statusCode = "operator";
				// Write on log.txt
				try (FileWriter writer = new FileWriter(Configuration.getPathVariable("log_path"), true)) {
					writer.write(LocalDateTime.now() + " " + userID + " (Operator) has logged in DataCure.\n\n");
				} catch (IOException e) {
					System.out.println("An error occurred while writing to the file: " + e.getMessage());
				}
			} else if (userID.equals("admin")) {
				statusCode = "admin";
				// Write on log.txt
				try (FileWriter writer = new FileWriter(Configuration.getPathVariable("log_path"), true)) {
					writer.write(LocalDateTime.now() + " " + userID + " (Admin) has logged in DataCure.\n\n");
				} catch (IOException e) {
					System.out.println("An error occurred while writing to the file: " + e.getMessage());
				}
			} else {
				statusCode = "input";
				logger.severe("ERROR: The user is registered into the database but does not respect the ID rules. Login failed. Call support to solve the problem");
				addActionError("ERROR: The user is registered into the database but does not respect the ID rules. Login failed. Call support to solve the problem");
			}
		} else {
			statusCode = "input";
			logger.severe("ERROR: Invalid user credentials. Login failed. Call support in case you lost your credentials");
			addActionError("ERROR: Invalid user credentials. Login failed. Call support in case you lost your credentials");
		}

		return statusCode;
	}


	/**
	 * Returns the ID or username entered by the user.
	 *
	 * @return The ID or username entered by the user
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Sets the ID or username entered by the user.
	 *
	 * @param userID The ID or username to be set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * Returns the password entered by the user.
	 *
	 * @return The password entered by the user
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Sets the password entered by the user.
	 *
	 * @param userPassword The password to be set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}

