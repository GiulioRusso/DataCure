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
 * The DeleteAction class is responsible for deleting a user from the database.
 */
public class DeleteAction extends ActionSupport {

	private String userID;	// Represents the user ID to be deleted.
	private String deleteMessage;	// Stores the message indicating the result of deleting the user.
	private List<Login> userList;	// Stores the login records.

	/**
	 * Executes the logic for deleting a user from the database.
	 *
	 * @return The result of the action execution. Returns "success" if the user is deleted successfully,
	 *         or "error" if the user deletion fails.
	 */
	public String execute() {

		String statusCode = "";

		// Delete the user
		boolean userDeleted = LoginDAO.deleteUser(userID);

		if (userDeleted) {
			deleteMessage = "MESSAGE: User deleted successfully!";
			// Write on log.txt
			try (FileWriter writer = new FileWriter(Configuration.getPathVariable("log_path"), true)) {
				writer.write(LocalDateTime.now() + " " +
						ServletActionContext.getRequest().getSession().getAttribute("loggedinUser") +
						" ----- " + userID + " has been deleted.\n\n");
			} catch (IOException e) {
				System.out.println("An error occurred while writing to the file: " + e.getMessage());
			}
			statusCode = "success";
		} else {
			if (userID.equals("admin")) {
				deleteMessage = "ERROR: The 'admin' user cannot be deleted.";
				statusCode = "error";
			} else {
				deleteMessage = "ERROR: User with ID '" + userID + "' does not exist!";
				statusCode = "error";
			}
		}

		// Set the userList attribute with login records
		userList = LoginDAO.getAllUsers();

		// Print message on the welcomeAdmin.jsp
		addActionError(deleteMessage);

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
	 * Gets the delete message.
	 *
	 * @return The delete message.
	 */
	public String getDeleteMessage() {
		return deleteMessage;
	}

	/**
	 * Sets the delete message.
	 *
	 * @param deleteMessage The delete message to set.
	 */
	public void setDeleteMessage(String deleteMessage) {
		this.deleteMessage = deleteMessage;
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
