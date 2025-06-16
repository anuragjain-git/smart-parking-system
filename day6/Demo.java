package day6;

import java.sql.*;
import java.util.Scanner;

public class Demo {

 public static void main(String[] args) throws ClassNotFoundException, SQLException {
     Scanner sc = new Scanner(System.in);
 	
 	 String url = "jdbc:mysql://localhost:3306/bank";
     String user = "root";
     String pass = "zsaxdwecq";
     String query = "SELECT * FROM accounts";

     Class.forName("com.mysql.cj.jdbc.Driver");
     
     Connection con = DriverManager.getConnection(url, user, pass);
     Statement st = con.createStatement();

     // 1: create
     int newId = 3;
     String newHolder = "Rahul Jain";
     double newBalance = 7500.50;

     int x = st.executeUpdate(
         "INSERT INTO accounts VALUES (" + newId + ", '" + newHolder + "', " + newBalance + ")"
     );

     if (x > 0) System.out.println("Successfully Inserted");
     else System.out.println("Insert Failed");
     
     // 2: Update 
     System.out.print("Enter Account ID to update: ");
     int updateAccId = sc.nextInt();
     System.out.print("Enter new balance: ");
     double updateBalance = sc.nextDouble();
//     int y = st.executeUpdate("UPDATE accounts SET balance = " + updateBalance + " WHERE account_id = " + updateAccId);
//     System.out.println(y > 0 ? "Updated successfully" : "No record updated");
     PreparedStatement pst = con.prepareStatement("UPDATE accounts SET balance = ? WHERE account_id = ?");
     pst.setDouble(1, updateBalance);
     pst.setInt(2, updateAccId);
     int y = pst.executeUpdate();
     System.out.println(y > 0 ? "Updated successfully" : "No record updated");

     // 3: Read
     ResultSet result = st.executeQuery(query);
     while (result.next()) {
         int accountId = result.getInt("account_id");
         String accountHolder = result.getString("account_holder");
         double balance = result.getDouble("balance");

         System.out.println("-------------------------");
         System.out.println("Account ID: " + accountId);
         System.out.println("Account Holder: " + accountHolder);
         System.out.println("Balance: " + balance);
     }
     
     // 4: Delete
     System.out.println("Enter account_id to delete a record: ");
     int accId = sc.nextInt();
     
     int z = st.executeUpdate("DELETE FROM accounts WHERE account_id = "+accId);
     System.out.println(z > 0 ? "Deleted successfully" : "No record deleted");
     
     // Close resources
     result.close();
     st.close();
     con.close();
 }
}
