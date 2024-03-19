package org.hoann.prj301.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hoann.prj301.core.DBContext;
import org.hoann.prj301.repositories.booking.BookingDTO;

public class BookingDAO {

    private static BookingDAO instance;
    private static final DBContext CONTEXT = DBContext.getInstance();
    private static final String GET = "SELECT [bookingId],[userId],[time],[total],[status]"
            + "FROM [dbo].[Bookings]";
    private static final String POST = "INSERT INTO [dbo].[Bookings]"
            + "([bookingId],[userId],[time],[total],[status])"
            + "VALUES (?,?,?,?,?)";
    private static final String PUT = "UPDATE [dbo].[Bookings] SET "
            + "[status] = ?"
            + " WHERE [bookingId] = ?";
    private static final String DELETE = "DELETE FROM [dbo].[Bookings]"
            + "WHERE [bookingId] = ?";

    private BookingDAO() {
    }

    private void log(String message, Exception e) {
        Logger.getLogger(BookingDAO.class.getName())
                .log(Level.SEVERE, message, e);
    }

    public static BookingDAO getInstance() {
        return instance = instance == null ? new BookingDAO() : instance;
    }

    public List<BookingDTO> get() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<BookingDTO> bookings = new ArrayList<>();
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(GET);
            rs = stm.executeQuery();
            while (rs.next()) {
                String bookingId = rs.getString("bookingId");
                String userId = rs.getString("userId");
                Timestamp time = rs.getTimestamp("time");
                double total = rs.getDouble("total");
                boolean status = rs.getBoolean("status");
                bookings.add(new BookingDTO(bookingId, userId, time, total, status));
            }
        } catch (SQLException e) {
            log("Exception on get", e);
        } finally {
            CONTEXT.closeResultSet(rs);
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return bookings;
    }

    public BookingDTO get(String bookingId) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookingDTO booking = null;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(GET + " WHERE [bookingId] = ?");
            stm.setString(1, bookingId);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                Timestamp time = rs.getTimestamp("time");
                double total = rs.getDouble("total");
                boolean status = rs.getBoolean("status");
                booking = new BookingDTO(bookingId, userId, time, total, status);
            }
        } catch (SQLException e) {
            log("Exception on get:bookingId", e);
        } finally {
            CONTEXT.closeResultSet(rs);
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return booking;
    }

    public boolean post(BookingDTO booking) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(POST);
            stm.setString(1, booking.getBookingId());
            stm.setString(2, booking.getUserId());
            stm.setTimestamp(3, booking.getTime());
            stm.setDouble(4, booking.getTotal());
            stm.setBoolean(5, booking.isStatus());
            res = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            log("Exception on post:booking", e);
            res = false;
        } finally {
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return res;
    }

    public boolean put(BookingDTO booking) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(PUT);
            stm.setBoolean(1, booking.isStatus());
            stm.setString(2, booking.getBookingId());
            res = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            log("Exception on put:booking", e);
            res = false;
        } finally {
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return res;
    }

    public boolean delete(String bookingId) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(DELETE);
            stm.setString(1, bookingId);
            res = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            log("Exception on delete:bookingId", e);
            res = false;
        } finally {
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return res;
    }
}
