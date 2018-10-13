package org.yakhya.sample.backoffice.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentToStudentViewMapper implements Function<Student, StudentView> {

  @Override
  public StudentView apply(Student student) {
    return StudentView.builder()
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
