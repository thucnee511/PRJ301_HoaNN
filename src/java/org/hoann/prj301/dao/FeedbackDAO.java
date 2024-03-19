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
import org.hoann.prj301.repositories.feedback.FeedbackDTO;

public class FeedbackDAO {

    private static FeedbackDAO instance;
    private static final DBContext CONTEXT = DBContext.getInstance();
    private static final String GET
            = "SELECT [feedbackId][bookingId],[description],[time],[reply],[status]"
            + "FROM [dbo].[Feedbacks]";
    private static final String POST
            = "INSERT INTO [dbo].[Feedbacks]"
            + "([feedbackId][bookingId],[description],[time],[reply],[status])"
            + "VALUES (?,?,?,?,?,?)";
    private static final String PUT
            = "UPDATE [dbo].[Feedbacks] SET "
            + "[reply] = ?,"
            + "[status] = ?"
            + " WHERE [feedbackId] = ?";
    private static final String DELETE
            = "DELETE FROM [dbo].[Feedbacks]"
            + "WHERE [feedbackId] = ?";

    private FeedbackDAO() {
    }

    private void log(String message, Exception e) {
        Logger.getLogger(FeedbackDAO.class.getName())
                .log(Level.SEVERE, message, e);
    }

    public static FeedbackDAO getInstance() {
        return instance = instance == null ? new FeedbackDAO() : instance;
    }

    public List<FeedbackDTO> get() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<FeedbackDTO> feedbacks = new ArrayList<>();
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(GET);
            rs = stm.executeQuery();
            while (rs.next()) {
                String feedbackId = rs.getString("feedbackId");
                String bookingId = rs.getString("bookingId");
                String description = rs.getString("description");
                Timestamp time = rs.getTimestamp("time");
                String reply = rs.getString("reply");
                boolean status = rs.getBoolean("status");
                feedbacks.add(new FeedbackDTO(feedbackId, bookingId, description, time, reply, status));
            }
        } catch (SQLException e) {
            log("Exception on get", e);
        } finally {
            CONTEXT.closeResultSet(rs);
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return feedbacks;
    }

    public FeedbackDTO get(String feedbackId) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        FeedbackDTO feedback = null;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(GET + " WHERE [feedbackId] = ?");
            stm.setString(1, feedbackId);
            rs = stm.executeQuery();
            while (rs.next()) {
                String bookingId = rs.getString("bookingId");
                String description = rs.getString("description");
                Timestamp time = rs.getTimestamp("time");
                String reply = rs.getString("reply");
                boolean status = rs.getBoolean("status");
                feedback = new FeedbackDTO(feedbackId, bookingId, description, time, reply, status);
            }
        } catch (SQLException e) {
            log("Exception on get:feedback", e);
        } finally {
            CONTEXT.closeResultSet(rs);
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return feedback;
    }

    public boolean post(FeedbackDTO feedback) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(POST);

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

    public boolean put(FeedbackDTO feedback) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(PUT);
            stm.setString(1, feedback.getReply());
            stm.setBoolean(2, feedback.isStatus());
            stm.setString(3, feedback.getFeedbackId());

            res = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            log("Exception on put:feedback", e);
            res = false;
        } finally {
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return res;
    }

    public boolean delete(String feedbackId) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = CONTEXT.getConnection();
            stm = conn.prepareStatement(DELETE);
            stm.setString(1, feedbackId);
            res = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            log("Exception on delete:feedbackId", e);
            res = false;
        } finally {
            CONTEXT.closePreparedStatement(stm);
            CONTEXT.closeConnection(conn);
        }
        return res;
    }
}
