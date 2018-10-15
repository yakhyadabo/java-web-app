package org.yakhya.sample.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yakhya.sample.api.model.StudentResource;
import org.yakhya.sample.api.service.StudentService;
import org.yakhya.sample.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudentRestController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private Function<Student, StudentResource> studentToStudentResourceMapper;

  @Autowired
  private Function<StudentResource, Student> studentResourceToStudentMapper;

  @GetMapping("/students")
  ResponseEntity<List<StudentResource>> allStudent() {
    return new ResponseEntity<>(studentService.getStudents()
        .parallelStream()
        .map(studentToStudentResourceMapper)
        .collect(toList()), HttpStatus.OK);
  }

  @PostMapping("/students")
  ResponseEntity<StudentResource> newStudent(@RequestBody StudentResource studentResource) {

    return Optional.of(studentResource)
        .map(studentResourceToStudentMapper)
        .map(student -> studentService.addStudent(student))
        .map(studentToStudentResourceMapper)
        .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
        .get();

  }

  @GetMapping("/students/{personalNumber}")
  ResponseEntity<StudentResource> getStudent(@PathVariable String personalNumber) {

    return studentService.getStudent(personalNumber)
        .map(studentToStudentResourceMapper)
        .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/students/{personalNumber}")
  void deleteStudent(@PathVariable String personalNumber) {
    studentService.deleteStudent(personalNumber);
  }


  @PutMapping("/students/{personalNumber}")
  ResponseEntity<StudentResource> replaceStudent(@RequestBody StudentResource newStudentResource, @PathVariable String personalNumber) {

    return studentService.getStudent(personalNumber)
        .map(student -> Student.builder()
              .personalNumber(newStudentResource.getPersonalNumber())
              .firstName(newStudentResource.getFirstName())
              .lastName(newStudentResource.getLastName())
              .dateOfBirth(newStudentResource.getDateOfBirth())
              /*...*/
              .build())
        .map(student -> studentService.updateStudent(personalNumber, student))
        .map(studentToStudentResourceMapper)
        .map(studentResource -> new ResponseEntity<>(studentResource, HttpStatus.OK))
        .get();
  }

}
