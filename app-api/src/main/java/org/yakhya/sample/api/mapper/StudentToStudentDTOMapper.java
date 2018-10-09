package org.yakhya.sample.api.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.api.model.StudentDTO;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentToStudentDTOMapper implements Function<Student, StudentDTO> {

  @Override
  public StudentDTO apply(Student student) {
    return StudentDTO.builder()
        .firstName(student.getFirstName())
        .lastName(student.getLastName())
        .personalNumber(student.getPersonalNumber())
        .dateOfBirth(student.getDateOfBirth())
        .education(student.getEducation().getName())
        .scholarshipHolder(student.getScholarshipHolder())
        .nationality(student.getNationality())
        .build();
  }
}
