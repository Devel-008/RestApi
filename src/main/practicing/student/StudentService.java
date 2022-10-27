package demo.rest.api.student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<Student>();
        Student s1 = new Student(1, 17, "Amit", "Chauhan", "9876543210");
        Student s2 = new Student(2, 17, "Nikhil", "Chauhan", "9876543210");
        students.add(s1);
        students.add(s2);
        return students;
    }
    public List<Student> getStudentWithRollNo(int rollNo){
        List<Student> students = new ArrayList<Student>();
        Student s1 = new Student(1, 17, "Amit", "Chauhan", "9876543210");
        Student s2 = new Student(2, 17, "Nikhil", "Chauhan", "9876543210");
        students.add(s1);
        students.add(s2);
        return students;
    }
}
