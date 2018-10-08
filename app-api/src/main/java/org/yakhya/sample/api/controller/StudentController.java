package org.yakhya.sample.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yakhya.sample.api.service.StudentService;
import org.yakhya.sample.domain.model.Student;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/students")
  ResponseEntity<List<Student>> allStudent() {
    List<Student> students = studentService.getStudents();
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @PostMapping("/students")
  ResponseEntity<Student> newStudent(@RequestBody Student student) {
    Student newStudent = studentService.addStudent(student);
    return new ResponseEntity<>(newStudent, HttpStatus.OK);
  }

}
