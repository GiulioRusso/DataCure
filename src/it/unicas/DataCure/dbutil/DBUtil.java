package it.unicas.DataCure.dbutil;

import java.sql.*;

/**
 * The DBUtil class provides utility methods for establishing and closing a connection to a MySQL database.
 */
public class DBUtil {
	/**
	 * The host of the MySQL database.
	 */
	private static final String HOST = "localhost";

	/**
	 * The schema (database name) of the MySQL database.
	 */
	private static final String SCHEMA = "DataCure";

	/**
	 * The username for connecting to the MySQL database.
	 */
	private static final String USER = "root";

	/**
	 * The password for connecting to the MySQL database.
	 */
	private static final String PASSWORD = "password";

	/**
	 * Establishes a connection to the MySQL database based on the parameters specified in the DBUtil class.
	 *
	 * @return The connection object
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://" + HOST + "/" + SCHEMA + "?useSSL=false&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * Closes the given database connection.
	 *
	 * @param conn The connection to be closed
	 */
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

