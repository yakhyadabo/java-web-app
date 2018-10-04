package org.yakhya.sample.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.yakhya.sample.api.service.StudentService;
import org.yakhya.sample.domain.model.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentControllerTest {

  @InjectMocks
  private StudentController controller;

  @Mock
  private StudentService studentService;

  private MockMvc mockMvc;

  private static Student YAKHYA = Student.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student MAXIME = Student.builder().personalNumber("mm00222").firstName("Maxime").lastName("Wilson").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student DAVID =  Student.builder().personalNumber("dd00333").firstName("David").lastName("Shepherd").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static Student MICHEL = Student.builder().personalNumber("mm00444").firstName("Michel").lastName("Martin").dateOfBirth(LocalDate.of(2000,01,15)).build();

  @BeforeAll
  public void setup() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(controller)
        .build();
  }

  @Test
  @DisplayName("/api/students GET should return the list of students")
  public void should__the_list_of_students() throws Exception {
    when(studentService.getStudents()).thenReturn(listOfStudents());

    mockMvc.perform(get("/api/students"))

        //.content(json(book))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", hasSize(listOfStudents().size())))
        .andExpect(jsonPath("$[0].personalNumber").value(YAKHYA.getPersonalNumber()))
        .andExpect(jsonPath("$[0].firstName").value(YAKHYA.getFirstName()))
        .andExpect(jsonPath("$[0].lastName").value(YAKHYA.getLastName()))


        //   .andExpect(header().string("Location", "http://localhost/api/books/123-1234567890"))
      //  .andExpect(content().string(""))
        .andDo(print());
  }

  private List<Student> listOfStudents() {
    return Arrays.asList(YAKHYA, MAXIME, DAVID, MICHEL);
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}