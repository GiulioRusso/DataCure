package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.LoginDAO;
import it.unicas.DataCure.pojo.Login;

import java.util.List;

/**
 * The AddAction class is responsible for adding a new user to the database.
 */
public class AddAction extends ActionSupport {

	private String userID;
	private String userPassword;

	private String addMessage;
	private List<Login> userList; // Add userList attribute

	/**
	 * Executes the logic for adding a new user to the database.
	 *
	 * @return The result of the action execution. Returns "success" if the user is added successfully, or "error" if the user addition fails.
	 */
	public String execute() {

		String statusCode = "";
		// Add the new user to the database
		int userAdded = LoginDAO.addUser(new Login(userID, userPassword));

		if (userAdded == 0) {
			addMessage = "MESSAGE: User added successfully!";
			statusCode = "success";
		} else if (userAdded == 1){
			addMessage = "ERROR: Invalid ID. It must start by 'D' or 'O'. User cannot be added.";
			statusCode = "error";
		} else if (userAdded == 2){
			addMessage = "ERROR: Invalid Password. Password can't be null. User cannot be added.";
			statusCode = "error";
		} else if (userAdded == 3){
			addMessage = "ERROR: User with ID '" + userID + "' already exists! Update or Delete the existing one before.";
			statusCode = "error";
		} else if (userAdded == 4) {
			addMessage = "ERROR: Failed to add the user. Exeption occured in LoginDAO.addUser";
			statusCode = "error";
		}

		// Set the userList attribute with login records
		userList = LoginDAO.getAllUsers();

		// Print message on the welcomeAdmin.jsp
		addActionError(addMessage);

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
	 * Gets the message indicating the result of adding the user.
	 *
	 * @return The message indicating the result of adding the user.
	 */
	public String getAddMessage() {
		return addMessage;
	}

	/**
	 * Sets the message indicating the result of adding the user.
	 *
	 * @param addMessage The message to set.
	 */
	public void setAddMessage(String addMessage) {
		this.addMessage = addMessage;
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
