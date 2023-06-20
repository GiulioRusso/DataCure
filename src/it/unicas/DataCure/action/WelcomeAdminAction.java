package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;
import it.unicas.DataCure.dao.LoginDAO;
import it.unicas.DataCure.pojo.Login;
import java.util.List;

/**
 * The WelcomeAdminAction class is an action class that handles the welcome page for the admin user.
 * It retrieves the list of users from the database and initializes them for display.
 */
public class WelcomeAdminAction extends ActionSupport{

	private List<Login> userList;	// Stores the list of users.
	private String userID;	// Represents the user ID form to Add/Update/Delete
	private String userPassword;	// Represents the user password form to Add/Update/Delete

	/**
	 * Initializes the list of users by retrieving them from the database.
	 */
	public void initializeUsers() {
		userList = LoginDAO.getAllUsers();
		System.out.println("MESSAGE: Login instances");
		System.out.println(userList);
	}

	/**
	 * Executes the welcome admin action and initializes the list of users.
	 *
	 * @return The status code indicating the success of the action.
	 */
	public String execute() {
		initializeUsers();

		return "success";
	}

	/**
	 * Gets the list of users.
	 *
	 * @return The list of users.
	 */
	public List<Login> getUserList() {
		return userList;
	}

	/**
	 * Sets the list of users.
	 *
	 * @param userList The list of users to be set.
	 */
	public void setUserList(List<Login> userList) {
		this.userList = userList;
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
	 * @param userID The user ID to be set.
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
	 * @param userPassword The user password to be set.
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
