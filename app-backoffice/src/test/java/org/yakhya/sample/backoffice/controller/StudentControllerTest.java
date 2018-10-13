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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.yakhya.sample.backoffice.config.AppMockMvc;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.backoffice.service.StudentService;
import org.yakhya.sample.domain.model.Student;
import org.yakhya.sample.domain.model.User;
import org.yaml.snakeyaml.error.YAMLException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentControllerTest {
  private static Student YAKHYA = Student.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student MAXIME = Student.builder().personalNumber("mm00222").firstName("Maxime").lastName("Wilson").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student DAVID =  Student.builder().personalNumber("dd00333").firstName("David").lastName("Shepherd").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student MICHEL = Student.builder().personalNumber("mm00444").firstName("Michel").lastName("Martin").dateOfBirth(LocalDate.of(2000,01,15)).build();

  private static StudentView YAKHYA_VIEW = StudentView.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static StudentView MAXIME_VIEW = StudentView.builder().personalNumber("mm00222").firstName("Maxime").lastName("Wilson").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static StudentView DAVID_VIEW =  StudentView.builder().personalNumber("dd00333").firstName("David").lastName("Shepherd").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static StudentView MICHEL_VIEW = StudentView.builder().personalNumber("mm00444").firstName("Michel").lastName("Martin").dateOfBirth(LocalDate.of(2000,01,15)).build();

  @InjectMocks
  private StudentController apiController;

  @Mock
  private StudentService userService;

  @Mock
  private Function<Student, StudentView> studentToStudentViewMapper;

  @Mock
  private Function<StudentView, Student> studentViewToStudentMapper;

  private AppMockMvc mockMvc;

  @BeforeAll
  public void setup() {
    mockMvc = new AppMockMvc(apiController);
  }

  @BeforeEach
  public void setUpEach(){
    when(studentToStudentViewMapper.apply(YAKHYA)).thenReturn(YAKHYA_VIEW);
    when(studentToStudentViewMapper.apply(MAXIME)).thenReturn(MAXIME_VIEW);
    when(studentToStudentViewMapper.apply(DAVID)).thenReturn(DAVID_VIEW);
    when(studentToStudentViewMapper.apply(MICHEL)).thenReturn(MICHEL_VIEW);

    when(studentViewToStudentMapper.apply(YAKHYA_VIEW)).thenReturn(YAKHYA);
  }

  @Test
  @DisplayName("/students/add should create a student")
  public void should_create_a_user() throws Exception {
    when(userService.addStudent(YAKHYA)).thenReturn(YAKHYA);

    mockMvc.perform(postValidUser("/students/add"))
        .andExpect(status().isFound())
        .andExpect(mockMvc.redirect("/students/" + YAKHYA_VIEW.getPersonalNumber()));
  }

  @Test
  @DisplayName("/students/list should return the list of users")
  public void should_display_view_with_the_list_of_users() throws Exception {
    when(userService.getStudents()).thenReturn(listOfStudents());

    mockMvc.perform(get("/students/list"))
        .andExpect(status().isOk())
        .andExpect(model().hasNoErrors())
        .andExpect(model().attributeExists("studentList"))
        .andExpect(mockMvc.view(AppApiView.STUDENT_LIST));
  }

  private static MockHttpServletRequestBuilder postValidUser(String url) {
    return post(url)
        .param("personalNumber", YAKHYA.getPersonalNumber())
        .param("firstName", "Yakhya")
        .param("lastName", "Dabo");
  }

  private List<Student> listOfStudents() {
    return Arrays.asList(YAKHYA, MAXIME, DAVID, MICHEL);
  }

}