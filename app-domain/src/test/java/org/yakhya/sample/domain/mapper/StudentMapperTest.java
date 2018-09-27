package org.yakhya.sample.domain.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.domain.config.MybatisTestConfig;
import org.yakhya.sample.domain.enums.Education;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MybatisTestConfig.class)
public class StudentMapperTest {

  @Autowired
  private StudentMapper studentMapper;

  @Autowired
  private NationalityMapper nationalityMapper;

  private static Nationality SEN = new Nationality("SEN","Senegal");

  @Test
  public void should_find_student_by_using_personal_number(){
    Student newStudent = Student.builder()
        .personalNumber("000111")
        .firstName("Yakhya")
        .lastName("Dabo")
        .dateOfBirth(LocalDate.of(2000,01,15))
        .education(Education.BACHELOR)
        .nationality(SEN)
        .scholarshipHolder(Boolean.FALSE)
        .build();

    nationalityMapper.insert(SEN);
    studentMapper.insert(newStudent);

    Student student =  studentMapper.selectByPersonalNumber("000111");
    assertThat(student).isEqualToComparingFieldByField(newStudent);
  }
}