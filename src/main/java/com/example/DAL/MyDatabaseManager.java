package com.example.DAL;

import java.sql.*;

public class MyDatabaseManager {
    private String host;
    private String port;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    protected MyDatabaseManager() {
        this.host = "localhost";
        this.port = "3306";
        this.dbName = "qlthanhvien";
        this.dbUser = "root";
        this.dbPassword = "";
        connect();
    }

    protected Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName,
                    this.dbUser, this.dbPassword);
        } catch (SQLException e) {
            System.out.print(e);
            return null;
        }
    }
}
