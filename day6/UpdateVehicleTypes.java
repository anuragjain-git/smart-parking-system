package day6;

import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.*;

public class UpdateVehicleTypes {

	public static void main(String[] args) {
		String url =  "jdbc:mysql://localhost:3306/parking_management_db";
		String username = "root";
		String password = "zsaxdwecq";
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Enter vehicle type: ");
			String updateVehicleType = scanner.nextLine();
			
			System.out.print("Enter baserateperhour: ");
			double updatebaserate = scanner.nextDouble();
			
			String updateBasePriceQuery = "UPDATE vehicletypes SET baseRatePerHour = ? WHERE vehicleType = ?";
			
			PreparedStatement vehicleTypesPstmt = conn.prepareStatement(updateBasePriceQuery);
			vehicleTypesPstmt.setDouble(1, updatebaserate);
			vehicleTypesPstmt.setString(2, updateVehicleType);
			
			int rows = vehicleTypesPstmt.executeUpdate();
			System.out.println(rows + " vehicle type updated successfully.");
		}
		catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
	}

}
