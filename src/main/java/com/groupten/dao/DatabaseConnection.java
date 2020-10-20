package com.groupten.dao;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        JsonParser parser = new JsonParser();
        JsonObject configData = null;
        try {
            configData = (JsonObject) parser.parse(new FileReader(CONFIG_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            DB_HOST = configData.get("DB_HOST").getAsString();
            DB_PORT = configData.get("DB_PORT").getAsString();
            DB_NAME = configData.get("DB_NAME").getAsString();
            DB_URL = "jdbc:" + "mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
            DB_USER = configData.get("DB_USER").getAsString();
            DB_PASS = configData.get("DB_PASS").getAsString();
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
