package day9;

import java.io.*;

public class CustomerFeedbackApp {
    public static void main(String[] args) {
        String feedbackFile = "feedback.txt";
 
        // Step 1: Write feedback to file using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFile, true))) {
            writer.write("Customer: John Doe\n");
            writer.write("Feedback: Excellent service and prompt response.\n");
            writer.write("------\n");
            System.out.println("Feedback written successfully.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        // Step 2: Read feedback from file using BufferedReader
        System.out.println("=== All Feedback Entries ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(feedbackFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}