package org.yakhya.sample.api.service;

import org.yakhya.sample.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
  List<Student> getStudents();
  Optional<Student> getStudent(String personalNumber);
  Student addStudent(Student user);
  Student updateStudent(String personalNumber, Student student);
  void deleteStudent(String personalNumber);
}
