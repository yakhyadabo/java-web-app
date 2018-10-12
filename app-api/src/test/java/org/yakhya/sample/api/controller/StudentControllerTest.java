package org.yakhya.sample.api.controller;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.yakhya.sample.api.model.StudentResource;
import org.yakhya.sample.api.service.StudentService;
import org.yakhya.sample.domain.model.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static util.TestUtil.asJsonString;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StudentControllerTest {

  @InjectMocks
  private StudentController controller;

  @Mock
  private StudentService studentService;

  @Mock
  private Function<Student,StudentResource> studentToStudentResourceMapper;

  @Mock
  private Function<StudentResource, Student> studentResourceToStudentMapper;

  private MockMvc mockMvc;

  private static Student YAKHYA = Student.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student MAXIME = Student.builder().personalNumber("mm00222").firstName("Maxime").lastName("Wilson").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student DAVID =  Student.builder().personalNumber("dd00333").firstName("David").lastName("Shepherd").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student MICHEL = Student.builder().personalNumber("mm00444").firstName("Michel").lastName("Martin").dateOfBirth(LocalDate.of(2000,01,15)).build();

  private static StudentResource Resource_YAKHYA = StudentResource.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static StudentResource Resource_MAXIME = StudentResource.builder().personalNumber("mm00222").firstName("Maxime").lastName("Wilson").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static StudentResource Resource_DAVID =  StudentResource.builder().personalNumber("dd00333").firstName("David").lastName("Shepherd").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static StudentResource Resource_MICHEL = StudentResource.builder().personalNumber("mm00444").firstName("Michel").lastName("Martin").dateOfBirth(LocalDate.of(2000,01,15)).build();


  @BeforeAll
  public void setUpAll() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(controller)
        .build();
  }

  @BeforeEach
  public void setUpEach(){
    when(studentToStudentResourceMapper.apply(YAKHYA)).thenReturn(Resource_YAKHYA);
    when(studentToStudentResourceMapper.apply(MAXIME)).thenReturn(Resource_MAXIME);
    when(studentToStudentResourceMapper.apply(DAVID)).thenReturn(Resource_DAVID);
    when(studentToStudentResourceMapper.apply(MICHEL)).thenReturn(Resource_MICHEL);

    when(studentResourceToStudentMapper.apply(Resource_YAKHYA)).thenReturn(YAKHYA);
    when(studentResourceToStudentMapper.apply(Resource_MAXIME)).thenReturn(MAXIME);
    when(studentResourceToStudentMapper.apply(Resource_DAVID)).thenReturn(DAVID);
    when(studentResourceToStudentMapper.apply(Resource_MICHEL)).thenReturn(MICHEL);
  }

  @Test
  @DisplayName("/api/students GET should return the list of students")
  public void should_return_the_list_of_students() throws Exception {
    when(studentService.getStudents()).thenReturn(listOfStudents());

    mockMvc.perform(get("/api/students"))

        //.content(json(book))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", hasSize(listOfStudents().size())))
        .andExpect(jsonPath("$[0].personalNumber").value(YAKHYA.getPersonalNumber()))
        .andExpect(jsonPath("$[0].firstName").value(YAKHYA.getFirstName()))
        .andExpect(jsonPath("$[0].lastName").value(YAKHYA.getLastName()))
        //.andExpect(jsonPath("$[0].dateOfBirth").value(YAKHYA.getDateOfBirth()))


        //   .andExpect(header().string("Location", "http://localhost/api/books/123-1234567890"))
      //  .andExpect(content().string(""))
        .andDo(print());
  }

  @Test
  @DisplayName("/api/students POST should create a student")
  public void should_create_a_student() throws Exception {
    when(studentService.addStudent(YAKHYA)).thenReturn(YAKHYA);

    mockMvc.perform(post("/api/students")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .content(asJsonString(YAKHYA))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.personalNumber").value(YAKHYA.getPersonalNumber()))
        .andExpect(jsonPath("$.firstName").value(YAKHYA.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(YAKHYA.getLastName()))

        .andDo(print());

  }

  @Test
  @DisplayName("/api/students/{personalNumber} GET should return a student")
  public void should_return_a_student() throws Exception {
    when(studentService.getStudent("yyy0011")).thenReturn(Optional.of(YAKHYA));

    mockMvc.perform(get("/api/students/yyy0011")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.personalNumber").value(YAKHYA.getPersonalNumber()))
        .andExpect(jsonPath("$.firstName").value(YAKHYA.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(YAKHYA.getLastName()));

  }

  @Test
  @DisplayName("/api/students/{personalNumber} GET should return a student")
  public void should_return_404_when_student_is_not_found() throws Exception {
    when(studentService.getStudent(any(String.class))).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/students/zzz0011")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isNotFound());

  }

  @Test
  @DisplayName("/api/students/{personalNumber} PUT should update student")
  public void should_update_student() throws Exception {
    when(studentService.getStudent(YAKHYA.getPersonalNumber())).thenReturn(Optional.of(YAKHYA));
    when(studentService.updateStudent(YAKHYA.getPersonalNumber(), MAXIME)).thenReturn(MAXIME);

    mockMvc.perform(put("/api/students/"+ YAKHYA.getPersonalNumber())
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .content(asJsonString(MAXIME))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.personalNumber").value(MAXIME.getPersonalNumber()))
        .andExpect(jsonPath("$.firstName").value(MAXIME.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(MAXIME.getLastName()));

    // verify which method has been called (add or update)
  }

  private List<Student> listOfStudents() {
    return Arrays.asList(YAKHYA, MAXIME, DAVID, MICHEL);
  }

}