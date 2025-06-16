package day8;

//import java.io.*;
//import java.util.*;
//
//public class StudentFileManager {
//
//    static final String filename = "students.txt";
//
//    static void createFile() throws IOException {
//        File file = new File(filename);
//        if (!file.exists()) {
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//                writer.write("Name,Roll,Marks");
//                writer.newLine();
//            }
//            System.out.println("File created with header.");
//        } else {
//            System.out.println("File already exists.");
//        }
//    }
//
//    static void addStudent() throws IOException {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Enter student name: ");
//        String name = sc.nextLine();
//
//        System.out.print("Enter roll number: ");
//        String roll = sc.nextLine();
//
//        System.out.print("Enter marks: ");
//        String marks = sc.nextLine();
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
//			writer.write(name + "," + roll + "," + marks);
//			writer.newLine();
//		}
//        System.out.println("Student added.");
//    }
//
//    static void display() throws IOException {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
//    }
//
//    static void countTotalStudents() throws IOException {
//        int count = 0;
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            reader.readLine(); // skip first line as it is a header
//            while (reader.readLine() != null) {
//                count++;
//            }
//        }
//        System.out.println("Total students: " + count);
//    }
//
//    static void searchByRollNumber() throws IOException {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter roll number to search: ");
//        String searchRoll = sc.nextLine();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            reader.readLine(); // skip first line as it is a header
//            boolean found = false;
//
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts[1].equals(searchRoll)) {
//                    System.out.println("Student Found: " + line);
//                    found = true;
//                    break;
//                }
//            }
//
//            if (!found) {
//                System.out.println("Student with roll number " + searchRoll + " not found.");
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        
//    	createFile();
//        addStudent();
//        display();
//        countTotalStudents();
//        searchByRollNumber();
//    }
//}


import java.io.*;
import java.util.*;

public class StudentFileManager {

    static final String filename = "students.txt";

    static void createFile() throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            FileWriter writer = new FileWriter(file);
            writer.write("Name,Roll,Marks\n");
            writer.close();
            System.out.println("File created with header.");
        } else {
            System.out.println("File already exists.");
        }
    }

    static void addStudent() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter roll number: ");
        String roll = sc.nextLine();

        System.out.print("Enter marks: ");
        String marks = sc.nextLine();

        FileWriter writer = new FileWriter(filename, true);
        writer.write(name + "," + roll + "," + marks + "\n");
        writer.close();

        System.out.println("Student added.");
    }

    static void display() throws IOException {
        FileReader fr = new FileReader(filename);
        int ch;
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }
        fr.close();
    }

    static void countTotalStudents() throws IOException {
        FileReader fr = new FileReader(filename);
        int ch;
        int count = 0;
        boolean isNewLine = true;

        // Skip first line as it is a header
        while ((ch = fr.read()) != -1) {
            if (ch == '\n') {
                break;
            }
        }

        while ((ch = fr.read()) != -1) {
            if (ch == '\n') {
                count++;
            }
        }
        fr.close();
        System.out.println("Total students: " + count);
    }

    static void searchByRollNumber() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter roll number to search: ");
        String searchRollNumber = sc.nextLine();

        FileReader fr = new FileReader(filename);
        StringBuilder sb = new StringBuilder();
        int ch;
        boolean found = false;

        // Skip first line - header
        while ((ch = fr.read()) != -1) {
            if (ch == '\n') break;
        }

        while ((ch = fr.read()) != -1) {
            if (ch != '\n') {
                sb.append((char) ch);
            } else {
                String line = sb.toString();
                String[] arr = line.split(",");
                if (arr[1].equals(searchRollNumber)) {
                    System.out.println("Student Found: " + line);
                    found = true;
                    break;
                }
                sb.setLength(0); //reset
            }
        }

        if (!found) {
            System.out.println("Student with roll number " + searchRollNumber + " not found.");
        }

        fr.close();
    }

    public static void main(String[] args) throws IOException {
        createFile();
        addStudent();
        display();
        countTotalStudents();
        searchByRollNumber();
    }
}

