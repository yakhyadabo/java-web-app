package org.yakhya.sample.backoffice.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.backoffice.config.AppMockMvc;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.backoffice.service.StudentService;
import org.yakhya.sample.domain.model.Student;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentListControllerTest {
  private static final String STUDENT_VIEW = "studentView";
  private static final Student YAKHYA = Student.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static final StudentView YAKHYA_VIEW = StudentView.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();

  @InjectMocks
  private StudentListController apiListController;

  @Mock
  private StudentService studentService;

  @Mock
  private Function<Student, StudentView> studentToStudentViewMapper;

  @Mock
  private Function<StudentView, Student> studentViewToStudentMapper;

  private AppMockMvc mockMvc;


  @BeforeAll
  public void setup() {
    mockMvc = new AppMockMvc(apiListController);
  }

  @BeforeEach
  public void setUpEach(){
    when(studentToStudentViewMapper.apply(YAKHYA)).thenReturn(YAKHYA_VIEW);
  }

  @Test
  @DisplayName("/students/{personalNumber} should return a student")
  public void should_return_a_user() throws Exception {
    when(studentService.getStudent(YAKHYA.getPersonalNumber())).thenReturn(Optional.of(YAKHYA));

    mockMvc.perform(get("/students/"+YAKHYA.getPersonalNumber()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(STUDENT_VIEW))
        .andExpect(model().attribute(STUDENT_VIEW, hasProperty("personalNumber", equalTo(YAKHYA_VIEW.getPersonalNumber()))))
        .andExpect(model().attribute(STUDENT_VIEW, hasProperty("firstName", equalTo(YAKHYA_VIEW.getFirstName()))))
        .andExpect(model().attribute(STUDENT_VIEW, hasProperty("lastName", equalTo(YAKHYA_VIEW.getLastName()))))
        .andExpect(mockMvc.view(AppApiView.STUDENT_VIEW));
  }
}