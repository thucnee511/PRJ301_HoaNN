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
import org.hoann.prj301.repositories.service.ServiceDTO;

public class ServiceDAO {

    private static ServiceDAO instance;
    private static final DBContext CONTEXT = DBContext.getInstance();
    private static final String GET
            = "SELECT [serviceId],[name],[price],[status]"
            + "FROM [dbo].[Services]";
    private static final String POST
            = "INSERT INTO [dbo].[Services]"
            + "([serviceId],[name],[price],[status])"
            + "VALUES (?,?,?,?)";
    private static final String PUT
            = "UPDATE [dbo].[Services] SET "
            + "[name] = ?,"
            + "[price] = ?,"
            + "[status] = ?"
            + " WHERE [serviceId] = ?";
    private static final String DELETE
            = "DELETE FROM [dbo].[Services]"
            + "WHERE [serviceId] = ?";

    private ServiceDAO() {
    }

    private void log(String message, Exception e) {
        Logger.getLogger(ServiceDAO.class.getName())
                .log(Level.SEVERE, message, e);
    }

    public static ServiceDAO getInstance() {
        return instance = instance == null ? new ServiceDAO() : instance;
    }

    public List<ServiceDTO> get() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ServiceDTO> services = new ArrayList<>();
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(GET);
            rs = stm.executeQuery();
            while (rs.next()) {
                String serviceId = rs.getString("serviceId");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                boolean status = rs.getBoolean("status");
                services.add(new ServiceDTO(serviceId, name, price, status));
            }
        } catch (SQLException e) {
            log("Exception on get", e);
        } finally {
            CONTEXT.closeResultSet(rs);
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return services;
    }

    public ServiceDTO get(String serviceId) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ServiceDTO service = null;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(GET + " WHERE [serviceId] = ?");
            stm.setString(1, serviceId);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                boolean status = rs.getBoolean("status");
                service = new ServiceDTO(serviceId, name, price, status);
            }
        } catch (SQLException e) {
            log("Exception on get", e);
        } finally {
            CONTEXT.closeResultSet(rs);
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return service;
    }

    public boolean post(ServiceDTO service) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(POST);
            stm.setString(1, service.getServiceId());
            stm.setString(2, service.getName());
            stm.setDouble(3, service.getPrice());
            stm.setBoolean(4, service.isStatus());
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

    public boolean put(ServiceDTO service) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(PUT);
            stm.setString(1, service.getName());
            stm.setDouble(2, service.getPrice());
            stm.setBoolean(3, service.isStatus());
            stm.setString(4, service.getServiceId());
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

    public boolean delete(String serviceId) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(DELETE);
            stm.setString(1, serviceId);
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
}
