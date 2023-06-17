package it.unicas.DataCure.dao;

import it.unicas.DataCure.action.LoginAction;
import it.unicas.DataCure.dbutil.DBUtil;
import it.unicas.DataCure.pojo.Image;
import it.unicas.DataCure.pojo.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ImageDAO {

    private static final Logger logger = Logger.getLogger(LoginAction.class.getName());

    public static int addImage(String imageName, String operatorDescription) {
        // Check if the imageName ends with a valid extension
        if (!(imageName.endsWith(".jpg") || imageName.endsWith(".png") || imageName.endsWith(".tiff"))) {
            logger.severe("ERROR: Invalid extension. Image accepted in .jpg/.png/.tiff. Image cannot be added.");
            return 2;
        }

        // Check if the operatorDescription is null
        if (operatorDescription == null) {
            logger.severe("ERROR: Invalid description. Description can't be null. Image cannot be added.");
            return 3;
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
            return 4;
        }
    }

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

    public static int updateImage(String id, boolean label, String dDesc) {

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
            logger.severe("ERROR: An error has occurred during image update");
            e.printStackTrace();
        }
            return 1;
    }
}

