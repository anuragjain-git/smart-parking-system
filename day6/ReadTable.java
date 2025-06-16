package day6;

import java.sql.DriverManager;
import java.sql.*;

public class ReadTable {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/parking_management_db";
		String username = "root";
		String password = "zsaxdwecq";
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String queryUsers = "SELECT * from Users";
			
			PreparedStatement pst = conn.prepareStatement(queryUsers);
			ResultSet result = pst.executeQuery(queryUsers);
			
			while (result.next()) {
		         int userId = result.getInt("userId");
		         

		         System.out.println("-------------------------");
		         System.out.println("User ID: " + userId);
		         
		     }
			
		}
		catch (SQLException e) {
			System.out.println();
		}
	}

}
