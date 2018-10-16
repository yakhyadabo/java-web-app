package org.yakhya.sample.backoffice.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yakhya.sample.backoffice.model.NationalityRow;
import org.yakhya.sample.backoffice.model.StudentForm;
import org.yakhya.sample.backoffice.service.NationalityService;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentFormToStudentMapper implements Function<StudentForm, Student> {

  @Autowired
  private NationalityService nationalityService;

  @Override
  public Student apply(StudentForm studentForm) {

    return Student.builder()
        .firstName(studentForm.getFirstName())
        .lastName(studentForm.getLastName())
        .personalNumber(studentForm.getPersonalNumber())
        .education(studentForm.getEducation())
        .dateOfBirth(studentForm.getDateOfBirth())
        .nationality(of(studentForm.getCountryCode()))
        .build();
  }

  private Nationality of(String countryCode){
    return nationalityService.getByCountryCode(countryCode);
  }
}
