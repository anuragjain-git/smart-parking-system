package day6;

import java.sql.*;
import java.util.Scanner;

public class UpdateUser {
	public static void main(String args[]) {
		String url = "jdbc:mysql://localhost:3306/parking_management_db";
		String Username = "root";
		String Password = "zsaxdwecq";
		
		try (Connection conn = DriverManager.getConnection(url, Username, Password)) {
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Enter your registered username: ");
			String updateUsername = scanner.nextLine();
			
			System.out.print("Update User Is VIP (true/false):");
			boolean updateIsVip = scanner.nextBoolean();
			
			String updateVipQuery = "UPDATE Users SET isVip = ? where username = ?";
			PreparedStatement userPstmt = conn.prepareStatement(updateVipQuery);
			
			userPstmt.setBoolean(1, updateIsVip);
			userPstmt.setString(2, updateUsername);
			
			int rows = userPstmt.executeUpdate();
			System.out.println(rows + " user(s) updated successfully.");
			
		}
		catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
	}
}