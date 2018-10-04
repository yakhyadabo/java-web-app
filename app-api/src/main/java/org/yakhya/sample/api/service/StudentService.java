package org.yakhya.sample.api.service;

import org.yakhya.sample.domain.model.Student;

import java.util.List;

public interface StudentService {
  List<Student> getStudents();
  Student getStudent(String personalNumber);
  Student addStudent(Student user);
  void deleteStudent(String personalNumber);
}
