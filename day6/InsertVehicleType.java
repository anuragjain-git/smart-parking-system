package day6;

import java.sql.*;
import java.util.Scanner;

public class InsertVehicleType {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/parking_management_db";
        String username = "root";
        String password = "zsaxdwecq";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter vehicle type (e.g., Car, Bike, Truck): ");
            String vehicleType = scanner.nextLine();

            System.out.print("Enter base rate per hour: ");
            double baseRate = scanner.nextDouble();

            String sql = "INSERT INTO VehicleTypes (vehicleType, baseRatePerHour) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehicleType);
            stmt.setDouble(2, baseRate);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " vehicle type(s) inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
