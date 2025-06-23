package dao;

import entity.Vehicle;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class VehicleDao {

    public void insertVehicle(Vehicle v) {
        String sql = "INSERT INTO Vehicles (userId, typeId, licensePlate, make, model, color) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, v.getUserId());
            stmt.setInt(2, v.getTypeId());
            stmt.setString(3, v.getLicensePlate());
            stmt.setString(4, v.getMake());
            stmt.setString(5, v.getModel());
            stmt.setString(6, v.getColor());

            stmt.executeUpdate();
            System.out.println("Vehicle inserted.");
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public Vehicle getVehicleById(int id) {
        String sql = "SELECT * FROM Vehicles WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vehicle(
                        rs.getInt("vehicleId"),
                        rs.getInt("userId"),
                        rs.getInt("typeId"),
                        rs.getString("licensePlate"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("color")
                );
            }
        } catch (SQLException e) {
            System.out.println("Fetch error: " + e.getMessage());
        }
        return null;
    }

    public void updateVehicle(Vehicle v) {
        String sql = "UPDATE Vehicles SET userId = ?, typeId = ?, licensePlate = ?, make = ?, model = ?, color = ? WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, v.getUserId());
            stmt.setInt(2, v.getTypeId());
            stmt.setString(3, v.getLicensePlate());
            stmt.setString(4, v.getMake());
            stmt.setString(5, v.getModel());
            stmt.setString(6, v.getColor());
            stmt.setInt(7, v.getVehicleId());

            stmt.executeUpdate();
            System.out.println("Vehicle updated.");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    public void deleteVehicleById(int id) {
        String sql = "DELETE FROM Vehicles WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Vehicle deleted.");
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }
}
