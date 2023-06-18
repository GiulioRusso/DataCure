package it.unicas.DataCure.dao;

import it.unicas.DataCure.action.LoginAction;
import it.unicas.DataCure.dbutil.DBUtil;
import it.unicas.DataCure.pojo.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The LoginDAO class provides data access methods for performing operations related to login information in the database.
 */
public class LoginDAO {

	/**
	 * The logger instance for logging messages.
	 */
	private static final Logger logger = Logger.getLogger(LoginAction.class.getName());

	/**
	 * Checks if the user details provided are valid by querying the login table in the database.
	 *
	 * @param userDetails The login details of the user.
	 * @return true if the user is valid, false otherwise.
	 */
	public static boolean isUserValid(Login userDetails) {
		boolean validStatus = false;
		try {
			Connection conn = DBUtil.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM login WHERE idlogin = '" + userDetails.getID() + "' AND password = '" + userDetails.getPassword() + "'");
			while (rs.next()) {
				validStatus = true;
			}

			DBUtil.closeConnection(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return validStatus;
	}

	/**
	 * Retrieves all users from the database.
	 *
	 * @return The list of all users.
	 */
	public static List<Login> getAllUsers() {
		List<Login> userList = new ArrayList<>();

		try {
				Connection conn = DBUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT idlogin, password FROM login");

				while (rs.next()) {
					String ID = rs.getString("idlogin");
					String password = rs.getString("password");

					Login login = new Login(ID, password);
					userList.add(login);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	/**
	 * Adds a new user to the database.
	 *
	 * DA RIFARE
	 */
	public static int addUser(Login userDetails) {
		// Check if the userID meets the required conditions
		if (!(userDetails.getID().startsWith("D") || userDetails.getID().startsWith("O"))) {
			logger.severe("ERROR: Invalid ID. It must start by 'D' or 'O'. User cannot be added.");
			return 1;
		}

		// Check if the userPassword is empty or null
		if (userDetails.getPassword() == null || userDetails.getPassword().isEmpty()) {
			logger.severe("ERROR: Invalid Password. Password can't be null. User cannot be added.");
			return 2;
		}

		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement checkStmt = conn.prepareStatement("SELECT idlogin FROM login WHERE idlogin = ?");
			 PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO login (idlogin, password) VALUES (?, ?)")) {

			// Set the userID parameter for the checkStmt
			checkStmt.setString(1, userDetails.getID());

			// Execute the query to check if the user already exists
			ResultSet resultSet = checkStmt.executeQuery();

			if (resultSet.next()) {
				// User already exists in the database
				logger.severe("ERROR: User with ID '" + userDetails.getID() + "' already exists! Update or Delete the existing one before.");
				return 3;
			} else {
				// User does not exist, insert the new user
				insertStmt.setString(1, userDetails.getID());
				insertStmt.setString(2, userDetails.getPassword());
				insertStmt.executeUpdate();
				System.out.println("MESSAGE: User added successfully!");
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 4; // Return error code 4 if user addition failed due to an exception
	}

	/**
	 * Deletes a user from the database.
	 *
	 * @param userID The ID of the user to delete.
	 * @return true if the user is deleted successfully, false otherwise.
	 */
	public static boolean deleteUser(String userID) {
		// admin cannot be deleted
		if (userID.equals("admin")) {
			logger.severe("ERROR: The 'admin' user cannot be deleted.");
			return false;
		}

		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement checkStmt = conn.prepareStatement("SELECT idlogin FROM login WHERE idlogin = ?");
			 PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM login WHERE idlogin = ?")) {

			// Set the userID parameter for the checkStmt
			checkStmt.setString(1, userID);

			// Execute the query to check if the user exists
			ResultSet resultSet = checkStmt.executeQuery();

			if (resultSet.next()) {
				// User exists, delete the user
				deleteStmt.setString(1, userID);
				int rowsAffected = deleteStmt.executeUpdate();
				System.out.println("MESSAGE: User deleted successfully!");

				return rowsAffected > 0;  // Return true if at least one row was deleted
			} else {
				// User does not exist
				logger.severe("ERROR: User with ID '" + userID + "' does not exist!");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;  // Return false if deletion failed
	}

	/**
	 * Updates the password of a user in the database.
	 *
	 * @param userID      The ID of the user.
	 * @param newPassword The new password for the user.
	 * @return 0 if the user password is updated successfully, 1 if the password is empty or null,
	 *         2 if the user does not exist, or 3 if an exception occurs.
	 */
	public static int updateUser(Login userDetails) {
		// Check if the newPassword is empty or null
		if (userDetails.getPassword() == null || userDetails.getPassword().isEmpty()) {
			logger.severe("ERROR: Invalid Password. Password can't be null. User cannot be updated.");
			return 1;
		}

		// Check if the userID is "admin"
		if (userDetails.getID().equals("admin")) {
			logger.severe("ERROR: Password change not allowed for user 'admin'. User cannot be updated.");
			return 3;
		}

		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement checkStmt = conn.prepareStatement("SELECT idlogin FROM login WHERE idlogin = ?");
			 PreparedStatement updateStmt = conn.prepareStatement("UPDATE login SET password = ? WHERE idlogin = ?")) {

			// Set the userID parameter for the checkStmt
			checkStmt.setString(1, userDetails.getID());

			// Execute the query to check if the user exists
			ResultSet resultSet = checkStmt.executeQuery();

			if (resultSet.next()) {
				// User exists, update the password
				updateStmt.setString(1, userDetails.getPassword());
				updateStmt.setString(2, userDetails.getID());
				updateStmt.executeUpdate();
				System.out.println("MESSAGE: User password updated successfully!");
				return 0;
			} else {
				// User does not exist
				logger.severe("ERROR: User with ID '" + userDetails.getID() + "' does not exist!");
				return 2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 4; // Return error code 3 if user update failed due to an exception
	}


}

