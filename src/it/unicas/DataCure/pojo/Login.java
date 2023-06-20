package it.unicas.DataCure.pojo;

/**
 * The Login class represents the login information of a user.
 */
public class Login {

	String ID;	// The ID or username of the user.

	String password;	// The password of the user.

	/**
	 * Constructs an empty Login object.
	 */
	public Login() {
	}

	/**
	 * Constructs a Login object with the specified ID and password.
	 *
	 * @param ID The ID or username of the user
	 * @param password The password of the user
	 */
	public Login(String ID, String password) {
		this.ID = ID;
		this.password = password;
	}

	/**
	 * Returns the ID or username of the user.
	 *
	 * @return The ID or username of the user
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Sets the ID or username of the user.
	 *
	 * @param ID The ID or username to be set
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * Returns the password of the user.
	 *
	 * @return The password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password The password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns a string representation of the Login object.
	 *
	 * @return A string representation of the Login object
	 */
	@Override
	public String toString() {
		return "[ID=" + ID + ", password=" + password + "]";
	}
}

