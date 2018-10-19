package org.yakhya.sample.backoffice.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.backoffice.model.StudentForm;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentToStudentFormMapper implements Function<Student, StudentForm> {

  @Override
  public StudentForm apply(Student studentForm) {

    return StudentForm.builder()
        .firstName(studentForm.getFirstName())
        .lastName(studentForm.getLastName())
        .personalNumber(studentForm.getPersonalNumber())
        .education(studentForm.getEducation())
        .dateOfBirth(studentForm.getDateOfBirth())
        .countryCode (of(studentForm.getNationality()))
        .build();
  }

  private String of(Nationality nationality){
    return nationality.getCode();
  }
}
