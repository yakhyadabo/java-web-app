package org.yakhya.sample.backoffice.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.backoffice.model.NationalityView;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.domain.model.Nationality;
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
        .education(student.getEducation())
        .scholarshipHolder(student.getScholarshipHolder())
        .nationality(of(student.getNationality()))
        .build();
  }

  private static NationalityView of(Nationality nationality){
    return NationalityView.builder()
        .code(nationality.getCode())
        .name(nationality.getName())
        .build();
  }
}
