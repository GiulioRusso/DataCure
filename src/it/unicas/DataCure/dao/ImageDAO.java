package it.unicas.DataCure.dao;

import it.unicas.DataCure.action.LoginAction;
import it.unicas.DataCure.dbutil.DBUtil;
import it.unicas.DataCure.pojo.Image;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * The ImageDAO class is a data access object that handles the database operations for the Image entity.
 */
public class ImageDAO {

    private static final Logger logger = Logger.getLogger(LoginAction.class.getName()); // logger instance for logging messages.

    /**
     * Adds an image to the database.
     *
     * @param imageName             The name of the image to add.
     * @param operatorDescription   The operator description of the image.
     * @return The result code indicating the success or failure of the image addition.
     */
    public static int addImage(String imageName, String operatorDescription) {
        // Check if the imageName ends with a valid extension
        if (!(imageName.endsWith(".jpg") || imageName.endsWith(".png") || imageName.endsWith(".tiff"))) {
            logger.severe("ERROR: Invalid extension. Image accepted in .jpg/.png/.tiff. Image cannot be added.");
            return 2;
        }

        // Check if the operatorDescription is null
        if (operatorDescription.isEmpty()) {
            logger.severe("ERROR: Invalid description. Description can't be empty. Image cannot be added.");
            return 3;
        }

        // Check if the imageName contains spaces
        if (imageName.contains(" ")) {
            logger.severe("ERROR: Invalid image name. Image name cannot contain spaces. Image cannot be added.");
            return 4;
        }

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO images (idimages, upload_date, labeled, operator_description, doctor_description) " +
                     "VALUES (?, ?, ?, ?, ?)");
            PreparedStatement checkStmt = conn.prepareStatement("SELECT idimages FROM images WHERE idimages = ?")) {

            checkStmt.setString(1, imageName);
            ResultSet resultSet = checkStmt.executeQuery();

            // Check if the imageName is unique
            if (resultSet.next()) {
                // Image name already exists in the database
                logger.severe("ERROR: Image named '" + imageName + "' already exists.");
                return 1;
            } else {
                insertStmt.setString(1, imageName);
                insertStmt.setTimestamp(2, new Timestamp(new Date().getTime()));
                insertStmt.setInt(3, 0);
                insertStmt.setString(4, operatorDescription);
                insertStmt.setNull(5, java.sql.Types.VARCHAR);

                insertStmt.executeUpdate();
                System.out.println("MESSAGE: Image added successfully!");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe("ERROR: Failed to add the Image. Exeption occured in ImageDAO.addImage");
            return 5;
        }
    }

    /**
     * Retrieves all images from the database.
     *
     * @return The list of all images in the database.
     */
    public static List<Image> getAllImages() {

        // Retrieve every image in the database
        List<Image> imageList = new ArrayList<>();

        try {
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM images");

                while (rs.next()) {
                    String imageID = rs.getString("idimages");
                    Timestamp imageDate = rs.getTimestamp("upload_date");
                    boolean label = rs.getBoolean("labeled");
                    String o_descr = rs.getString("operator_description");
                    String d_descr = rs.getString("doctor_description");

                    Image image = new Image(imageID, imageDate, label, o_descr, d_descr);
                    imageList.add(image);
                }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe("ERROR: Failed to get images. Exeption occured in ImageDAO.getAllImages");
            return null;
        }

        return imageList;
    }

    /**
     * Updates the image with the given ID in the database.
     *
     * @param id    The ID of the image to update.
     * @param label The labeled status of the image.
     * @param dDesc The doctor description of the image.
     * @return The result code indicating the success or failure of the image update.
     */
    public static int updateImage(String id, boolean label, String dDesc) {

        // Check if doctor description is empty
        if (dDesc.isEmpty()) {
            logger.severe("ERROR: Invalid description. Description can't be empty. Image cannot be updated.");
            return 1;
        }

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement("UPDATE images SET labeled = ?, " +
                     "doctor_description = ? " +
                     "WHERE idimages = ?")) {

            // Update image properties
            updateStmt.setBoolean(1, label);
            updateStmt.setString(2, dDesc);
            updateStmt.setString(3, id);
            updateStmt.executeUpdate();
            System.out.println(updateStmt);
            logger.severe("MESSAGE: Image updated successfully!");

            return 0;

        } catch (SQLException e) {
            logger.severe("ERROR: Failed to add the Image. Exception occurred in ImageDAO.addImage");
            e.printStackTrace();
            return 2;
        }
    }
}

