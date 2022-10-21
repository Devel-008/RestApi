package demo.rest.api.student;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/students")
public class Students {
    StudentService studentService = new StudentService();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Student> getAllStudents(@Context HttpHeaders headers){
        List<Student> students = studentService.getAllStudents();
        return students;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{rollno}")
    public Student getStudentWithRollNo(@PathParam("rollno") int rollNo){
        Student student = (Student) studentService.getStudentWithRollNo(rollNo);
        return student;
    }

}
