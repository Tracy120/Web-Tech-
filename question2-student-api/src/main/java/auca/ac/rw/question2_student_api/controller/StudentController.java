package auca.ac.rw.question2_student_api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.question2_student_api.model.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    
    private static List<Student> students = new ArrayList<>();

    
    static {
        students.add(new Student(1L, "John", "Doe", "john.doe@university.edu", "Computer Science", 3.85));
        students.add(new Student(2L, "Jane", "Smith", "jane.smith@university.edu", "Computer Science", 3.92));
        students.add(new Student(3L, "Michael", "Johnson", "michael.johnson@university.edu", "Engineering", 3.65));
        students.add(new Student(4L, "Emily", "Brown", "emily.brown@university.edu", "Business", 3.45));
        students.add(new Student(5L, "David", "Wilson", "david.wilson@university.edu", "Engineering", 3.78));
        students.add(new Student(6L, "Sarah", "Martinez", "sarah.martinez@university.edu", "Computer Science", 3.88));
    }

    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

    
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> majorStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                majorStudents.add(student);
            }
        }
        if (majorStudents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(majorStudents);
        }
        return ResponseEntity.ok(majorStudents);
    }

   
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterByGpa(@RequestParam Double gpa) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getGpa() >= gpa) {
                filteredStudents.add(student);
            }
        }
        if (filteredStudents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(filteredStudents);
        }
        return ResponseEntity.ok(filteredStudents);
    }

    
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        
        Long newId = students.size() > 0 ? students.get(students.size() - 1).getStudentId() + 1 : 1L;
        student.setStudentId(newId);
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                student.setEmail(updatedStudent.getEmail());
                student.setMajor(updatedStudent.getMajor());
                student.setGpa(updatedStudent.getGpa());
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
