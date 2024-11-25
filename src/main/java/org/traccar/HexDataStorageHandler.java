package org.traccar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HexDataStorageHandler {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/gpswox_traccar";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "uu91ClhY03qZOO6m5RREcaYpq91MeOmN";
    private static final Logger LOGGER = Logger.getLogger(HexDataStorageHandler.class.getName());

    /**
     * Inserts the received hex string and decoded data into the database.
     *
     * @param hexData     The received hex string.
     * @param decodedData The decoded data as a string.
     */
    public void storeHexAndDecodedData(String hexData, String decodedData) {
        String insertQuery = "INSERT INTO HexDecodedData (hexData, decodedData) VALUES (?, ?)";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, hexData);
            preparedStatement.setString(2, decodedData);

            // Execute the query
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                LOGGER.info("Data successfully inserted into the HexDecodedData table.");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to insert data into the HexDecodedData table.", e);
        }
    }
}
