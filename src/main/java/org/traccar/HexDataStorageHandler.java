package org.traccar;

import org.traccar.config.Config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HexDataStorageHandler {

    private static Config config;
    private static String DB_URL = null;
    private static String DB_USER = null;
    private static String DB_PASSWORD = null;
    private static String DB_DRIVER = null;
    private static Logger LOGGER = Logger.getLogger(HexDataStorageHandler.class.getName());

    public HexDataStorageHandler() {
        try {
            config = new Config("/opt/traccar/conf/traccar.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DB_URL = config.getString("database.url");
        DB_USER = config.getString("database.user");
        DB_PASSWORD = config.getString("database.password");
        DB_DRIVER = config.getString("database.driver");
        LOGGER = Logger.getLogger(HexDataStorageHandler.class.getName());
//        System.out.println("private static final String DB_URL =" + config.getString("database.url") +
//                "    \nprivate static final String DB_USER =" + config.getString("database.user") +
//                "    \nprivate static final String DB_PASSWORD =" + config.getString("database.password") +
//                "    \nDB_DRIVER =" + config.getString("database.driver"));
    }

    /**
     * Inserts the received hex string and decoded data into the database.
     *
     * @param hexData     The received hex string.
     * @param decodedData The decoded data as a string.
     */
    public void storeHexAndDecodedData(String hexData, String decodedData) {
//        config
        String insertQuery = "INSERT INTO HexDecodedData (hexData, decodedData) VALUES (?, ?)";
        try {
            Class.forName(DB_DRIVER);
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
