package net.geekhour.springreview.controller;

import jakarta.validation.Valid;
import net.geekhour.springreview.common.Result;
import net.geekhour.springreview.dto.StudentCreateDTO;
import net.geekhour.springreview.entity.Student;
import net.geekhour.springreview.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/hello")
    public String helloStudent(){
        return studentService.sayHello();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Result<Student>> getStudentById(@PathVariable Integer id){
        Student stu=studentService.findById(id);
        return ResponseEntity.ok(Result.success(stu));
    }

    @PostMapping("/students")
    public ResponseEntity<Result<Integer>> addStudent(@Valid @RequestBody StudentCreateDTO dto){
        Student stu=new Student();
        stu.setId(dto.getId());
        stu.setName(dto.getName());
        stu.setAge(dto.getAge());
        stu.setScore(dto.getScore());
        return ResponseEntity.ok(Result.success(studentService.addStudent(stu)));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Result<Integer>> updateStudent(@PathVariable Integer id , @RequestBody Student student){
        return ResponseEntity.ok(Result.success(studentService.updateStudent(id, student)));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Result<Integer>> deleteById(@PathVariable Integer id){
        return ResponseEntity.ok(Result.success(studentService.deleteById(id)));
    }

    @GetMapping("/students/search")
    public ResponseEntity<Result<List<Student>>> searchByName(@RequestParam String name){
        return ResponseEntity.ok(Result.success(studentService.searchByName(name)));

    }

    @GetMapping("/students/page")
    public ResponseEntity<Result<List<Student>>> findPage(@RequestParam Integer page,@RequestParam Integer pageSize){
        return ResponseEntity.ok(Result.success(studentService.findPage(page, pageSize)));
    }

}
