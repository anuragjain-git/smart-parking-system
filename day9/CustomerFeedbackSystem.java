package day9;

import java.io.*;
import java.util.Scanner;
 
public class CustomerFeedbackSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String feedbackFile = "feedbackUserInput.txt";
 
        // Step 1: Take feedback input from user
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
 
        System.out.print("Enter your feedback: ");
        String feedback = scanner.nextLine();
        
        // -----------------------------------------------------------------------------------------------------
 
        // Step 2: Write the feedback to file using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFile, true))) {
            writer.write("Customer: " + name + "\n");
            writer.write("Feedback: " + feedback + "\n");
            writer.write("------\n");
            System.out.println("\nFeedback submitted successfully!");
        } catch (IOException e) {
            System.out.println(" Error writing feedback.");
            e.printStackTrace();
        }
 
        // Step 3: Read and display all feedback using BufferedReader
        System.out.println("\n=== All Feedback Entries ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(feedbackFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(" Error reading feedback.");
            e.printStackTrace();
        }
 
        scanner.close();
        
        //-------------------------------------------------------------------------------------------------------
        
        // Write the feedback using BufferedOutputStream
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(feedbackFile, true))) {
            String content = "Customer: " + name + "\n" +
                             "Feedback: " + feedback + "\n" +
                             "------\n";
            bos.write(content.getBytes()); // Convert string to bytes
            bos.flush(); // Ensure all data is written
            System.out.println("\nFeedback submitted successfully!");
        } catch (IOException e) {
            System.out.println("Error writing feedback.");
            e.printStackTrace();
        }

        // Read and display feedback using BufferedInputStream
        System.out.println("\n=== All Feedback Entries ===");
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(feedbackFile))) {
            int byteData;
            while ((byteData = bis.read()) != -1) {
                System.out.print((char) byteData); // Convert byte to char
            }
        } catch (IOException e) {
            System.out.println("Error reading feedback.");
            e.printStackTrace();
        }

        scanner.close();
        
        //---------------------------------------------------------------------------------------------------------
        
        // Write feedback using FileOutputStream
        try (FileOutputStream fos = new FileOutputStream(feedbackFile, true)) {
            String content = "Customer: " + name + "\n" +
                             "Feedback: " + feedback + "\n" +
                             "------\n";
            fos.write(content.getBytes());  // Convert string to bytes
            System.out.println("\nFeedback submitted successfully!");
        } catch (IOException e) {
            System.out.println("Error writing feedback.");
            e.printStackTrace();
        }

        // Read and display feedback using FileInputStream
        System.out.println("\n=== All Feedback Entries ===");
        try (FileInputStream fis = new FileInputStream(feedbackFile)) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData);  // Convert each byte to character
            }
        } catch (IOException e) {
            System.out.println("Error reading feedback.");
            e.printStackTrace();
        }
    
    }
}