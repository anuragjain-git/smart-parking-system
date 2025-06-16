package day6;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InsertParkingTransaction {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/parking_management_db";
        String Username = "root";
        String Password = "zsaxdwecq";

        try (Connection conn = DriverManager.getConnection(url, Username, Password);
             Scanner scanner = new Scanner(System.in)) {

            // Step 1: Get license plate input
            System.out.print("Enter vehicle license plate: ");
            String licensePlate = scanner.nextLine();

            // Step 2: Find vehicleId, isVip, baseRatePerHour
            // e.g. to get baseRateHour
            // select vt.baseRateHour from vehicleTypes vt join Vehicels v 
            // on v.typeId = vt.typeId where v.licensePlate = "ABC123";
            String vehicleQuery = """
                SELECT v.vehicleId, u.isVip, vt.baseRatePerHour
                FROM Vehicles v
                JOIN Users u ON v.userId = u.userId
                JOIN VehicleTypes vt ON v.typeId = vt.typeId
                WHERE v.licensePlate = ?
            """;

            PreparedStatement vehicleStmt = conn.prepareStatement(vehicleQuery);
            vehicleStmt.setString(1, licensePlate);
            ResultSet rs = vehicleStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Vehicle not found.");
                return;
            }

            int vehicleId = rs.getInt("vehicleId");
            boolean isVip = rs.getBoolean("isVip");
            double baseRate = rs.getDouble("baseRatePerHour");

            // Step 3: Get entryTime and exitTime
            System.out.print("Enter entry time (yyyy-MM-dd HH:mm): ");
            String entryStr = scanner.nextLine();
            System.out.print("Enter exit time (yyyy-MM-dd HH:mm): ");
            String exitStr = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime entryTime = LocalDateTime.parse(entryStr, formatter);
            LocalDateTime exitTime = LocalDateTime.parse(exitStr, formatter);

            // Step 4: Calculate duration in hours (as double)
            double durationHours = Duration.between(entryTime, exitTime).toMinutes() / 60.0;

            // Step 5: Calculate fees
            double baseFee = durationHours * baseRate;
            boolean peakHour = entryTime.getHour() >= 8 && entryTime.getHour() < 20;
            double extraCharge = peakHour ? baseFee * 0.10 : 0.0;
            double discount = isVip ? (baseFee + extraCharge) * 0.15 : 0.0;
            double finalFee = baseFee + extraCharge - discount;

            // Step 6: Insert into ParkingTransactions
            String insertQuery = """
                INSERT INTO ParkingTransactions (
                    vehicleId, entryTime, exitTime,
                    calculatedBaseFee, calculatedExtraCharges, calculatedDiscount, calculatedFee,
                    peakHourChargeApplied, durationHours, status
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, vehicleId);
            insertStmt.setTimestamp(2, Timestamp.valueOf(entryTime));
            insertStmt.setTimestamp(3, Timestamp.valueOf(exitTime));
            insertStmt.setDouble(4, baseFee);
            insertStmt.setDouble(5, extraCharge);
            insertStmt.setDouble(6, discount);
            insertStmt.setDouble(7, finalFee);
            insertStmt.setBoolean(8, peakHour);
            insertStmt.setDouble(9, durationHours);
            insertStmt.setString(10, "Completed");

            int rows = insertStmt.executeUpdate();
            System.out.println(rows + " transaction(s) inserted successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


