package org.yakhya.sample.api.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.api.model.StudentResource;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentResourceToStudentMapper implements Function<StudentResource, Student> {
  @Override
  public Student apply(StudentResource studentResource) {
    return Student.builder()
        .firstName(studentResource.getFirstName())
        .lastName(studentResource.getLastName())
        .personalNumber(studentResource.getPersonalNumber())
        .build();

  }
}
