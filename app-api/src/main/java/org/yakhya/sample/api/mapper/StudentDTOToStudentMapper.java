package org.yakhya.sample.api.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.api.model.StudentDTO;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentDTOToStudentMapper implements Function<StudentDTO, Student> {
  @Override
  public Student apply(StudentDTO studentDTO) {
    return Student.builder()
        .firstName(studentDTO.getFirstName())
        .lastName(studentDTO.getLastName())
        .personalNumber(studentDTO.getPersonalNumber())
        .build();

  }
}
