package day10;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
 
public class SerializeDemo {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Preeti", 90);
 
        try {
            FileOutputStream fileOut = new FileOutputStream("student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
 
            out.writeObject(s1); // Serialization
            out.close();
            fileOut.close();
 
            System.out.println("Object has been serialized to student.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}