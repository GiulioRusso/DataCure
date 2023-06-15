package it.unicas.DataCure.dbutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DataLoader class provides methods for loading data from a text file into a database.
 */
public class DataLoader {
    private static final String LOGIN_TABLE = "login";
    private static final String IMAGES_TABLE = "images";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Loads data from a text file and populates the specified database table if the data does not already exist in the table.
     * @param filePath The file path of the data file
     * @param tableName The name of the target database table (either "login" or "images")
     * @implNote The data in the text file should be separated by semicolons (;).
     * @implNote This method checks if the data already exists in the database table before performing the insertion.
     * If the data already exists, it will be skipped.
     * If the data does not exist, it will be inserted into the database table.
     * @implNote The method utilizes the checkLoginDataExists and checkImagesDataExists methods to perform the data existence check.
     * @implNote The insertLoginData and insertImagesData methods are used for inserting data into the respective tables.
     */
    public static void loadDataFromFile(String filePath, String tableName) {
        System.out.println("MESSAGE: Start loading data");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             Connection connection = DBUtil.getConnection()) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");

                // Check if the data already exists in the database
                boolean dataExists = false;
                if (tableName.equals("login")) {
                    // Check if the login ID already exists in the login table
                    String idLogin = data[0].trim();
                    dataExists = checkLoginDataExists(connection, idLogin);
                } else if (tableName.equals("images")) {
                    // Check if the image ID already exists in the images table
                    int idImages = Integer.parseInt(data[0].trim());
                    dataExists = checkImagesDataExists(connection, idImages);
                }

                // If data does not exist, perform the insertion
                if (!dataExists) {
                    // Insert the data into the database
                    if (tableName.equals("login")) {
                        // Insert login data
                        String idLogin = data[0].trim();
                        String password = data[1].trim();
                        insertLoginData(connection, idLogin, password);
                    } else if (tableName.equals("images")) {

                        // Insert images data
                        int idImages = Integer.parseInt(data[0].trim());
                        LocalDateTime uploadDate = LocalDateTime.parse(data[1].trim(), DATE_FORMATTER);
                        boolean labeled = Boolean.parseBoolean(data[2].trim());
                        String operatorDescription = data[3].trim();
                        String doctorDescription = data[4].trim();
                        insertImagesData(connection, idImages, uploadDate, labeled, operatorDescription, doctorDescription);
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if login data already exists in the database.
     * @param connection The database connection
     * @param idLogin The login ID to check
     * @return {@code true} if login data exists, {@code false} otherwise
     * @throws SQLException If a database access error occurs
     */
    private static boolean checkLoginDataExists(Connection connection, String idLogin) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + LOGIN_TABLE + " WHERE idlogin = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idLogin);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    /**
     * Checks if images data already exists in the database.
     * @param connection The database connection
     * @param idImages The image ID to check
     * @return {@code true} if images data exists, {@code false} otherwise
     * @throws SQLException If a database access error occurs
     */
    private static boolean checkImagesDataExists(Connection connection, int idImages) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + IMAGES_TABLE + " WHERE idimages = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idImages);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }


    /**
     * Inserts login data into the database.
     *
     * @param connection  The database connection
     * @param idLogin     The login ID
     * @param password    The login password
     * @throws SQLException If a database access error occurs
     */
    private static void insertLoginData(Connection connection, String idLogin, String password) throws SQLException {
        String sql = "INSERT INTO " + LOGIN_TABLE + " (idlogin, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idLogin);
            statement.setString(2, password);
            statement.executeUpdate();
        }
        System.out.println("MESSAGE: login table data uploaded");
    }

    /**
     * Inserts images data into the database.
     *
     * @param connection           The database connection
     * @param idImages             The image ID
     * @param uploadDate           The upload date and time
     * @param labeled              The labeled status of the image
     * @param operatorDescription  The operator's description of the image
     * @param doctorDescription    The doctor's description of the image
     * @throws SQLException If a database access error occurs
     */
    private static void insertImagesData(Connection connection, int idImages, LocalDateTime uploadDate,
                                         boolean labeled, String operatorDescription, String doctorDescription) throws SQLException {
        String sql = "INSERT INTO " + IMAGES_TABLE + " (idimages, upload_date, labeled, operator_description, doctor_description) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idImages);
            statement.setString(2, uploadDate.format(DATE_FORMATTER));
            statement.setBoolean(3, labeled);
            statement.setString(4, operatorDescription);
            statement.setString(5, doctorDescription);
            statement.executeUpdate();
        }
        System.out.println("MESSAGE: images table data uploaded");
    }
}

