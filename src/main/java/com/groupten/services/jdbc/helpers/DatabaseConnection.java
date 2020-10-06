package com.groupten.services.jdbc.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    static
    {
        //Load Configurations
        String CONFIG_PATH = "configuration.json";
        JSONParser parser = new JSONParser();
        JSONObject configData = null;
        try {
            configData = (JSONObject) parser.parse(new FileReader(CONFIG_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            //Set Driver
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

            //Set DB Configs
            String DB_HOST = (String) configData.get("DB_HOST");
            String DB_PORT = (String) configData.get("DB_PORT");
            String DB_NAME = (String) configData.get("DB_NAME");
            String DB_URL = "jdbc:" + "mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";

            //Set DB Credentials
            String DB_USER = (String) configData.get("DB_USER");
            String DB_PASSWORD = (String) configData.get("DB_PASS");

            //Establish Connection
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
