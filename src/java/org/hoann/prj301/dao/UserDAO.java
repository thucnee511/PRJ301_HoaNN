package org.hoann.prj301.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hoann.prj301.core.DBContext;
import org.hoann.prj301.repositories.user.UserDTO;

public class UserDAO {

    private static UserDAO instance;
    private final DBContext CONTEXT = DBContext.getInstance();
    private static final String GET
            = "SELECT [userId],[name],[password],[phone],[roleId],[status]"
            + " FROM [dbo].[Users]";
    private static final String POST
            = "INSERT INTO [dbo].[Users]"
            + "([userId],[name],[password],[phone],[roleId],[status])"
            + " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String PUT
            = "UPDATE [dbo].[Users] SET "
            + "[name] = ?,"
            + "[password] = ?,"
            + "[phone] = ?,"
            + "[status] = ?"
            + " WHERE [userId] = ?";
    private static final String DELETE
            = "DELETE FROM [dbo].[Users]"
            + " WHERE [userId] = ?";

    private void log(String message, Exception ex) {
        Logger.getLogger(UserDAO.class.getName())
                .log(Level.SEVERE, message, ex);
    }

    private UserDAO() {
    }

    //Single-ton Design Pattern
    public static UserDAO getInstance() {
        return instance = instance == null ? new UserDAO() : instance;
    }

    public List<UserDTO> get() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<UserDTO> users = new ArrayList<>();
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(GET);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                String name = rs.getString("name");
                String password = "********";
                String phone = rs.getString("phone");
                String roleId = rs.getString("roleId");
                boolean status = rs.getBoolean("status");
                users.add(new UserDTO(userId, name, password, phone, roleId, status));
            }
        } catch (SQLException e) {
            log("Exception on get", e);
        } finally {
            CONTEXT.closeResultSet(rs);
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return users;
    }

    public UserDTO get(String userId) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(GET + " WHERE [userId] = ?");
            stm.setString(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String password = "********";
                String phone = rs.getString("phone");
                String roleId = rs.getString("roleId");
                boolean status = rs.getBoolean("status");
                user = new UserDTO(userId, name, password, phone, roleId, status);
            }
        } catch (SQLException e) {
            log("Exception on get:userid", e);
        } finally {
            CONTEXT.closeResultSet(rs);
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return user;
    }

    public boolean post(UserDTO userInfo) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(POST);
            stm.setString(1, userInfo.getUserId());
            stm.setString(2, userInfo.getName());
            stm.setString(3, userInfo.getPassword());
            stm.setString(4, userInfo.getPhone());
            stm.setString(5, userInfo.getRoleId());
            stm.setBoolean(6, userInfo.isStatus());
            res = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            log("Exception on post:userinfo", e);
            res = false;
        } finally {
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return res;
    }

    public boolean put(UserDTO userInfo) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(PUT);
            stm.setString(1, userInfo.getName());
            stm.setString(2, userInfo.getPassword());
            stm.setString(3, userInfo.getPhone());
            stm.setBoolean(4, userInfo.isStatus());
            stm.setString(5, userInfo.getUserId());
            res = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            log("Exception on put:userinfo", e);
            res = false;
        } finally {
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return res;
    }

    public boolean delete(String userId) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(DELETE);
            stm.setString(1, userId);
            res = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            log("Exception on delete:userid", e);
            res = false;
        } finally {
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return res;
    }
}
