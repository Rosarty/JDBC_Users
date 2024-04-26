package org.example.app.database;

import org.example.app.view.UserView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnector {

    public static Connection connect() {
        Properties props = new Properties();
        Connection conn = null;

        try {
            props.load(DatabaseConnector.class.getResourceAsStream("/db/jdbc.properties"));
            conn = DriverManager.getConnection(props.getProperty("dbDriver") +
                            props.getProperty("dbName"),
                    props.getProperty("username"), props.getProperty("password"));
        } catch (SQLException | IOException e) {
            new UserView().getOutput(e.getMessage());
        }
        return conn;
    }
}