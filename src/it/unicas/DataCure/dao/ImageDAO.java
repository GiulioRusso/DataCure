package it.unicas.DataCure.dao;

import it.unicas.DataCure.action.LoginAction;
import it.unicas.DataCure.dbutil.DBUtil;

import java.sql.*;
import java.util.Date;
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

}

