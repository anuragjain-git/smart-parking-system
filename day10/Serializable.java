package day10;

import java.io.Serializable;

class Student implements Serializable {
    private static final long serialVersionUID = 1L; // Good practice
    int id;
    String name;
    transient int marks; // 'transient' will not be serialized
 
    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
 
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Marks: " + marks);
    }
}