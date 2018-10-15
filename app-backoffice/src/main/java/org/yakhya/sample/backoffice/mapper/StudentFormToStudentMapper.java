package org.yakhya.sample.backoffice.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.backoffice.model.NationalityForm;
import org.yakhya.sample.backoffice.model.StudentForm;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentFormToStudentMapper implements Function<StudentForm, Student> {

  @Override
  public Student apply(StudentForm studentForm) {
    return Student.builder()
        .firstName(studentForm.getFirstName())
        .lastName(studentForm.getLastName())
        .personalNumber(studentForm.getPersonalNumber())
        .education(studentForm.getEducation())
        .nationality(of(studentForm.getNationality()))
        .build();
  }

  private static Nationality of(NationalityForm nationality){
    return Nationality.builder()
        .code(nationality.getCode())
        .name(nationality.getName())
        .build();
  }
}
