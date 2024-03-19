package org.hoann.prj301.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    private static final String DB_NAME = "PRJ301";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";
    private static DBContext instance;

    // Single-ton Design Pattern
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
        } catch (SQLException | ClassNotFoundException e) {
            log("Exception on getConnection: ", e);
        }
        return conn;
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            log("Exception on closeConnection", e);
        }
    }

    public void closePreparedStatement(PreparedStatement stm) {
        try {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
        } catch (SQLException e) {
            log("Exception on closeConnection", e);
        }
    }

    public void closeResultSet(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            log("Exception on closeConnection", e);
        }
    }
}
