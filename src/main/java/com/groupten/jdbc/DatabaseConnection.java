package com.groupten.jdbc;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection dbConnectionObj = null;

    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_HOST, DB_PORT, DB_NAME, DB_URL, DB_USER, DB_PASS;

    public DatabaseConnection(){
        String CONFIG_PATH = "configuration.json";
        JSONParser parser = new JSONParser();
        JSONObject configData = null;
        try {
            configData = (JSONObject) parser.parse(new FileReader(CONFIG_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            DB_HOST = (String) configData.get("DB_HOST");
            DB_PORT = (String) configData.get("DB_PORT");
            DB_NAME = (String) configData.get("DB_NAME");
            DB_URL = "jdbc:" + "mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
            DB_USER = (String) configData.get("DB_USER");
            DB_PASS = (String) configData.get("DB_PASS");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection connect(){
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static DatabaseConnection getDatabaseConnectionObject() {
        if(dbConnectionObj == null){
            dbConnectionObj = new DatabaseConnection();
        }
        return dbConnectionObj;
    }
}
