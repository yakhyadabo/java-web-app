package org.yakhya.sample.api.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.api.model.NationalityResource;
import org.yakhya.sample.api.model.StudentResource;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentToStudentResourceMapper implements Function<Student, StudentResource> {

  @Override
  public StudentResource apply(Student student) {
    return StudentResource.builder()
        .firstName(student.getFirstName())
        .lastName(student.getLastName())
        .personalNumber(student.getPersonalNumber())
        .dateOfBirth(student.getDateOfBirth())
        .education(student.getEducation().getName())
        .scholarshipHolder(student.getScholarshipHolder())
        .nationality(of(student.getNationality()))
        .build();
  }

  private static NationalityResource of(Nationality nationality){
    return NationalityResource.builder()
        .code(nationality.getCode())
        .name(nationality.getName())
        .build();
  }
}
