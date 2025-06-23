package dao;

import entity.ParkingTransaction;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class ParkingTransactionDao {

    public void insertTransaction(ParkingTransaction tx) {
        String sql = """
            INSERT INTO ParkingTransactions (
                vehicleId, entryTime, exitTime,
                calculatedExtraCharges, calculatedDiscount, calculatedFee,
                peakHourChargeApplied, durationHours, status
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tx.getVehicleId());
            stmt.setTimestamp(2, Timestamp.valueOf(tx.getEntryTime()));
            stmt.setTimestamp(3, Timestamp.valueOf(tx.getExitTime()));
            stmt.setDouble(4, tx.getCalculatedExtraCharges());
            stmt.setDouble(5, tx.getCalculatedDiscount());
            stmt.setDouble(6, tx.getCalculatedFee());
            stmt.setBoolean(7, tx.isPeakHourChargeApplied());
            stmt.setDouble(8, tx.getDurationHours());
            stmt.setString(9, tx.getStatus());

            stmt.executeUpdate();
            System.out.println("Transaction inserted.");
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public ParkingTransaction getTransactionById(int id) {
        String sql = "SELECT * FROM ParkingTransactions WHERE transactionId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ParkingTransaction(
                        rs.getInt("transactionId"),
                        rs.getInt("vehicleId"),
                        rs.getTimestamp("entryTime").toLocalDateTime(),
                        rs.getTimestamp("exitTime").toLocalDateTime(),
                        rs.getDouble("calculatedExtraCharges"),
                        rs.getDouble("calculatedDiscount"),
                        rs.getDouble("calculatedFee"),
                        rs.getBoolean("peakHourChargeApplied"),
                        rs.getDouble("durationHours"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Fetch error: " + e.getMessage());
        }
        return null;
    }

    public void updateTransaction(ParkingTransaction tx) {
        String sql = """
            UPDATE ParkingTransactions SET 
                vehicleId = ?, entryTime = ?, exitTime = ?, 
                calculatedExtraCharges = ?, calculatedDiscount = ?, 
                calculatedFee = ?, peakHourChargeApplied = ?, 
                durationHours = ?, status = ? 
            WHERE transactionId = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tx.getVehicleId());
            stmt.setTimestamp(2, Timestamp.valueOf(tx.getEntryTime()));
            stmt.setTimestamp(3, Timestamp.valueOf(tx.getExitTime()));
            stmt.setDouble(4, tx.getCalculatedExtraCharges());
            stmt.setDouble(5, tx.getCalculatedDiscount());
            stmt.setDouble(6, tx.getCalculatedFee());
            stmt.setBoolean(7, tx.isPeakHourChargeApplied());
            stmt.setDouble(8, tx.getDurationHours());
            stmt.setString(9, tx.getStatus());
            stmt.setInt(10, tx.getTransactionId());

            stmt.executeUpdate();
            System.out.println("Transaction updated.");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    public void deleteTransactionById(int id) {
        String sql = "DELETE FROM ParkingTransactions WHERE transactionId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Transaction deleted.");
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }
}
