package day6;

import java.sql.*;
import java.util.Scanner;

public class InsertVehicle {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/parking_management_db";
        String dbUsername = "root";
        String dbPassword = "zsaxdwecq";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter already registered username: ");
            String usernameInput = scanner.nextLine();

            String userQuery = "SELECT userId FROM Users WHERE username = ?";
            PreparedStatement userStmt = conn.prepareStatement(userQuery);
            userStmt.setString(1, usernameInput);
            ResultSet userRs = userStmt.executeQuery();

            if (!userRs.next()) {
                System.out.println("User not found!");
                return;
            }
            int userId = userRs.getInt("userId");

            System.out.print("Enter vehicle type (e.g., Car, Bike, Truck): ");
            String vehicleTypeInput = scanner.nextLine();

            String typeQuery = "SELECT typeId FROM VehicleTypes WHERE vehicleType = ?";
            PreparedStatement typeStmt = conn.prepareStatement(typeQuery);
            typeStmt.setString(1, vehicleTypeInput);
            ResultSet typeRs = typeStmt.executeQuery();

            if (!typeRs.next()) {
                System.out.println("Vehicle type not found!");
                return;
            }
            int typeId = typeRs.getInt("typeId");

            System.out.print("Enter license plate: ");
            String licensePlate = scanner.nextLine();

            System.out.print("Enter vehicle make: ");
            String make = scanner.nextLine();

            System.out.print("Enter vehicle model: ");
            String model = scanner.nextLine();

            // Insert new vehicle record
            String insertSql = "INSERT INTO Vehicles (userId, typeId, licensePlate, make, model) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);

            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, typeId);
            insertStmt.setString(3, licensePlate);
            insertStmt.setString(4, make);
            insertStmt.setString(5, model);

            int rows = insertStmt.executeUpdate();
            System.out.println(rows + " vehicle(s) inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
