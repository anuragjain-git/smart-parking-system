package day6;

import java.sql.*;
import java.util.Scanner;

public class InsertUser {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/parking_management_db";
        String username = "root";
        String password = "zsaxdwecq";

        try (Connection conn = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter username: ");
            String userNameInput = scanner.nextLine();

            System.out.print("Enter email: ");
            String emailInput = scanner.nextLine();

            System.out.print("Is VIP (true/false): ");
            boolean isVip = scanner.nextBoolean();

            String sql = "INSERT INTO Users (username, email, isVip) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userNameInput);
            stmt.setString(2, emailInput);
            stmt.setBoolean(3, isVip);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " user(s) inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
