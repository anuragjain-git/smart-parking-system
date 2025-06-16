package day10;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
 
public class DeserializeDemo {
    public static void main(String[] args) {
        Student s1 = null;
 
        try {
            FileInputStream fileIn = new FileInputStream("student.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
 
            s1 = (Student) in.readObject(); // Deserialization
            in.close();
            fileIn.close();
 
            System.out.println("Object has been deserialized:");
            s1.display(); // 'marks' will be 0 because it was transient
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}