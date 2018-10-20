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
import org.yakhya.sample.backoffice.config.AppMockMvc;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.model.NationalityRow;
import org.yakhya.sample.backoffice.model.StudentForm;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.backoffice.service.NationalityService;
import org.yakhya.sample.backoffice.service.StudentService;
import org.yakhya.sample.backoffice.util.DateUtils;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentListControllerTest {
  private static final String STUDENT_VIEW = "studentView";
  private static final String STUDENT_FORM = "studentForm";
  private static final String NATIONALITY_LIST = "nationalityList";

  private static final Student YAKHYA = Student.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();
  private static final StudentView YAKHYA_VIEW = StudentView.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(of(2000,01,15)).build();
  private static final StudentForm YAKHYA_FORM = StudentForm.builder().personalNumber("yyy0011").firstName("Yakhya").lastName("Dabo").dateOfBirth(LocalDate.of(2000,01,15)).build();

  private static final Nationality SN = Nationality.builder().code("SN").name("Senegal").build();
  private static final Nationality EN = Nationality.builder().code("EN").name("England").build();
  private static final Nationality FR = Nationality.builder().code("FR").name("France").build();

  private static final NationalityRow SN_ROW = NationalityRow.builder().code("SN").name("Senegal").build();
  private static final NationalityRow EN_ROW = NationalityRow.builder().code("EN").name("England").build();
  private static final NationalityRow FR_ROW = NationalityRow.builder().code("FR").name("France").build();

  @InjectMocks
  private StudentListController apiListController;

  @Mock
  private StudentService studentService;

  @Mock
  private NationalityService nationalityService;

  @Mock
  private Function<Student, StudentView> studentToStudentViewMapper;

  @Mock
  private Function<Student, StudentForm> studentToStudentFormMapper;

  @Mock
  private Function<Nationality, NationalityRow> nationalityToNationalityRowMapper;

  @Mock
  private Function<StudentView, Student> studentViewToStudentMapper;

  private AppMockMvc mockMvc;


  @BeforeAll
  public void setup() {
    mockMvc = new AppMockMvc(apiListController);
  }

  @BeforeEach
  public void setUpEach(){
    when(nationalityToNationalityRowMapper.apply(SN)).thenReturn(SN_ROW);
    when(nationalityToNationalityRowMapper.apply(EN)).thenReturn(EN_ROW);
    when(nationalityToNationalityRowMapper.apply(FR)).thenReturn(FR_ROW);
  }

  @Test
  @DisplayName("/students/{personalNumber} should return a student")
  public void should_return_a_user() throws Exception {
    when(studentToStudentViewMapper.apply(YAKHYA)).thenReturn(YAKHYA_VIEW);
    when(studentService.getStudent(YAKHYA.getPersonalNumber())).thenReturn(Optional.of(YAKHYA));

    mockMvc.perform(get("/students/"+YAKHYA.getPersonalNumber()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(STUDENT_VIEW))
        .andExpect(model().attribute(STUDENT_VIEW, hasProperty("personalNumber", equalTo(YAKHYA_VIEW.getPersonalNumber()))))
        .andExpect(model().attribute(STUDENT_VIEW, hasProperty("firstName", equalTo(YAKHYA_VIEW.getFirstName()))))
        .andExpect(model().attribute(STUDENT_VIEW, hasProperty("lastName", equalTo(YAKHYA_VIEW.getLastName()))))
        .andExpect(mockMvc.view(AppApiView.STUDENT_VIEW));
  }

  @Test
  @DisplayName("/students/new should return empty form")
  public void should_display_creation_form() throws Exception {
    when(nationalityService.findAll()).thenReturn(listOfNationalities());

    mockMvc.perform(get("/students/new"))
        .andExpect(status().isOk())
        .andExpect(model().hasNoErrors())
        .andExpect(model().attributeExists(STUDENT_FORM))
        .andExpect(model().attributeExists(NATIONALITY_LIST))
        .andExpect(mockMvc.view(AppApiView.STUDENT_EDIT_CREATE));
  }

  @Test
  @DisplayName("/students/new should return form with student details")
  public void should_display_creation_edition_form() throws Exception {
    when(studentService.getStudent(YAKHYA.getPersonalNumber())).thenReturn(Optional.of(YAKHYA));
    when(studentToStudentFormMapper.apply(YAKHYA)).thenReturn(YAKHYA_FORM);
    when(nationalityService.findAll()).thenReturn(listOfNationalities());

    mockMvc.perform(get("/students/edit/" + YAKHYA.getPersonalNumber()))
        .andExpect(status().isOk())
        .andExpect(model().hasNoErrors())
        .andExpect(model().attributeExists(STUDENT_FORM))
        .andExpect(model().attribute(STUDENT_FORM, hasProperty("personalNumber", equalTo(YAKHYA_FORM.getPersonalNumber()))))
        .andExpect(model().attribute(STUDENT_FORM, hasProperty("firstName", equalTo(YAKHYA_FORM.getFirstName()))))
        .andExpect(model().attribute(STUDENT_FORM, hasProperty("lastName", equalTo(YAKHYA_FORM.getLastName()))))
        .andExpect(model().attributeExists(NATIONALITY_LIST))
        .andExpect(mockMvc.view(AppApiView.STUDENT_EDIT_CREATE));
  }

  private static List<Nationality> listOfNationalities() {
    return Arrays.asList(SN,EN,FR);
  }

  private  static Date of(int year, int month, int day){
    return DateUtils.asNullableDate(LocalDate.of(year,month,day));
  }
}