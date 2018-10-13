package org.yakhya.sample.backoffice.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentViewToStudentMapper implements Function<StudentView, Student> {
  @Override
  public Student apply(StudentView studentView) {
    return Student.builder()
        .firstName(studentView.getFirstName())
        .lastName(studentView.getLastName())
        .personalNumber(studentView.getPersonalNumber())
        .build();

  }
}
