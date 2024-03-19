package org.hoann.prj301.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    private static final String DB_NAME = "";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";
    private static DBContext instance;

    //Single-ton Design Pattern
    public static DBContext getInstance() {
        if (instance == null) {
            instance = new DBContext();
        }
        return instance;
    }
    
    private static void log(String message, Exception ex) {
        Logger.getLogger(DBContext.class.getName())
                .log(Level.SEVERE, message, ex);
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + DB_NAME
                    + ";encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
        }catch(SQLException | ClassNotFoundException e){
            log("Exception on getConnection: ", e);
        }
        return conn;
    }
}
