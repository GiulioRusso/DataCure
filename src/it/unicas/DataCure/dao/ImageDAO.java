package it.unicas.DataCure.dao;

import it.unicas.DataCure.dbutil.DBUtil;
import it.unicas.DataCure.pojo.Image;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageDAO {

    public static void addImage(Image image) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO images (upload_date, labeled, operator_description, doctor_description, path) " +
                    "VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter values
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(image.getUploadDate().getTime()));
            preparedStatement.setBoolean(2, image.isLabeled());
            preparedStatement.setString(3, image.getOperatorDescription());
            preparedStatement.setString(4, image.getDoctorDescription());
            preparedStatement.setString(5, image.getImagePath());

            // Execute the SQL statement
            preparedStatement.executeUpdate();

            // Save the uploaded file to the specified path
            File uploadedFile = image.getImageFile();
            String uploadPath = "/web/resources/database-images";
            File destination = new File(uploadPath, uploadedFile.getName());
            FileUtils.copyFile(uploadedFile, destination);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
