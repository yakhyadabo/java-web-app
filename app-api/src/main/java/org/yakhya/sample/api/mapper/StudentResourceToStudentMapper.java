package org.yakhya.sample.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yakhya.sample.api.model.StudentResource;
import org.yakhya.sample.api.service.NationalityService;
import org.yakhya.sample.domain.enums.Education;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Component
public class StudentResourceToStudentMapper implements Function<StudentResource, Student> {

  @Autowired
  private NationalityService nationalityService;

  @Override
  public Student apply(StudentResource studentResource) {
    return Student.builder()
        .personalNumber(studentResource.getPersonalNumber())
        .firstName(studentResource.getFirstName())
        .lastName(studentResource.getLastName())
        .dateOfBirth(studentResource.getDateOfBirth())
        .education(Education.getById(studentResource.getEducation()))
        .nationality(of(studentResource.getNationality().getCode()))
        .build();
  }

  private Nationality of(String countryCode){
    return nationalityService.getByCountryCode(countryCode);
  }
}
