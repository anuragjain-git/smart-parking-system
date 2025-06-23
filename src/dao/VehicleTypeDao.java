package dao;

import entity.VehicleType;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class VehicleTypeDao {

    public void insertVehicleType(VehicleType vt) {
        String sql = "INSERT INTO VehicleTypes (vehicleType, baseRatePerHour) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vt.getVehicleType());
            stmt.setDouble(2, vt.getBaseRatePerHour());
            stmt.executeUpdate();
            System.out.println("Vehicle type inserted.");
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public VehicleType getVehicleTypeById(int id) {
        String sql = "SELECT * FROM VehicleTypes WHERE typeId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new VehicleType(
                        rs.getInt("typeId"),
                        rs.getString("vehicleType"),
                        rs.getDouble("baseRatePerHour")
                );
            }
        } catch (SQLException e) {
            System.out.println("Fetch error: " + e.getMessage());
        }
        return null;
    }

    public void updateVehicleType(VehicleType vt) {
        String sql = "UPDATE VehicleTypes SET vehicleType = ?, baseRatePerHour = ? WHERE typeId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vt.getVehicleType());
            stmt.setDouble(2, vt.getBaseRatePerHour());
            stmt.setInt(3, vt.getTypeId());
            stmt.executeUpdate();
            System.out.println("Vehicle type updated.");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    public void deleteVehicleTypeById(int id) {
        String sql = "DELETE FROM VehicleTypes WHERE typeId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Vehicle type deleted.");
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }
}
