package net.javaguides.springbootrestapi.Controller;


import net.javaguides.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {


    // http://localhost:8080/students/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                 1,
                 "kishan",
                 "yarlagadda"
    );
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header","ramesh").body(student);
    }


    //http://localhost:8080/students
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"kishan","yarlagadda"));
        students.add(new Student(2,"naga","dantu"));
        students.add(new Student(3,"satya","yarlagadda"));
        return ResponseEntity.ok(students);
    }

    //SpringBoot REST API with PathVariable
    //{id} -URI template variable
    //http://localhost:8080/students/id/first-name/last-name

    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        Student student = new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring Boot REST API with RequestParam
    //http://localhost:8080/students/query?id=1&firstName=kishan&lastName=Yarlagadda
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        Student student=  new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    @PostMapping("create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }


    //Spring Boot REST API that handles http PUT request - updating existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id ){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    //Spring Boot REST API that handles http DELETE request - deleting the existing  resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        System.out.println(id);
        return ResponseEntity.ok("Student deleted succesfully");
    }


}
