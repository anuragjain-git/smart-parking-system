package dao;

import entity.User;
import util.DBConnection;

import java.sql.*;

public class UserDao {

    public void insertUser(User user) {
        String sql = "INSERT INTO Users (username, email, isVip) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setBoolean(3, user.isVip());
            stmt.executeUpdate();
            System.out.println("User inserted.");
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getBoolean("isVip")
                );
            }
        } catch (SQLException e) {
            System.out.println("Get error: " + e.getMessage());
        }
        return null;
    }

    public void updateUser(User user) {
        String sql = "UPDATE Users SET username = ?, email = ?, isVip = ? WHERE userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setBoolean(3, user.isVip());
            stmt.setInt(4, user.getUserId());
            int rows = stmt.executeUpdate();
            System.out.println(rows + " user(s) updated.");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    public void deleteUserById(int userId) {
        String sql = "DELETE FROM Users WHERE userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " user(s) deleted.");
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }
}
