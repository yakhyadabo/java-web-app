package org.yakhya.sample.api.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.api.model.NationalityResource;
import org.yakhya.sample.api.model.StudentResource;
import org.yakhya.sample.domain.model.Nationality;
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
        .nationality(of(studentResource.getNationality()))
        .build();

  }

  private static Nationality of(NationalityResource nationality){
    return Nationality.builder()
        .code(nationality.getCode())
        .name(nationality.getName())
        .build();
  }
}
