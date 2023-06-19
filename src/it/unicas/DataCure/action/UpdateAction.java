package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.LoginDAO;
import it.unicas.DataCure.dbutil.Configuration;
import it.unicas.DataCure.pojo.Login;
import org.apache.struts2.ServletActionContext;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The UpdateAction class is responsible for updating a user's password in the database.
 */
public class UpdateAction extends ActionSupport {

	private String userID;
	private String userPassword;
	private String updateMessage;

	private List<Login> userList; // Add userList attribute

	/**
	 * Executes the logic for updating a user's password in the database.
	 *
	 * @return The result of the action execution. Returns "success" if the user's password is updated successfully,
	 *         or "error" if the password update fails.
	 */
	public String execute() {

		String statusCode = "";
		// Update the user's password
		int userUpdated = LoginDAO.updateUser(new Login (userID, userPassword));

		if (userUpdated == 0) {
			updateMessage = "MESSAGE: User password updated successfully!";
			try (FileWriter writer = new FileWriter(Configuration.getPathVariable("log_path"), true)) {
				writer.write(LocalDateTime.now() + " " +
						ServletActionContext.getRequest().getSession().getAttribute("loggedinUser") +
						" ----- " + userID + " has been updated.\n\n");
			} catch (IOException e) {
				System.out.println("An error occurred while writing to the file: " + e.getMessage());
			}
			statusCode = "success";
		} else if (userUpdated == 1) {
			updateMessage = "ERROR: Invalid Password. Password can't be null. User cannot be updated.";
			statusCode = "error";
		} else if (userUpdated == 2) {
			updateMessage = "ERROR: User with ID '" + userID + "' does not exist!";
			statusCode = "error";
		} else if (userUpdated == 3) {
			updateMessage = "ERROR: Password change not allowed for user 'admin'. User cannot be updated.";
			statusCode = "error";
		} else if (userUpdated == 4) {
			updateMessage = "ERROR: Failed to update the user. Exception occurred in LoginDAO.updateUser";
			statusCode = "error";
		}

		// Set the userList attribute with login records
		userList = LoginDAO.getAllUsers();

		// Print message on the welcomeAdmin.jsp
		addActionError(updateMessage);

		return statusCode;
	}

	/**
	 * Gets the user ID.
	 *
	 * @return The user ID.
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Sets the user ID.
	 *
	 * @param userID The user ID to set.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * Gets the user password.
	 *
	 * @return The user password.
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Sets the user password.
	 *
	 * @param userPassword The user password to set.
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * Gets the update message.
	 *
	 * @return The update message.
	 */
	public String getUpdateMessage() {
		return updateMessage;
	}

	/**
	 * Sets the update message.
	 *
	 * @param updateMessage The update message to set.
	 */
	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	/**
	 * Gets the login records (userList).
	 *
	 * @return The login records.
	 */
	public List<Login> getUserList() {
		return userList;
	}

	/**
	 * Sets the login records (userList).
	 *
	 * @param userList The login records to set.
	 */
	public void setUserList(List<Login> userList) {
		this.userList = userList;
	}
}
