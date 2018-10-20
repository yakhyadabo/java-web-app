package org.yakhya.sample.backoffice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yakhya.sample.backoffice.config.AppRedirect;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.model.NationalityRow;
import org.yakhya.sample.backoffice.model.StudentForm;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.backoffice.service.NationalityService;
import org.yakhya.sample.backoffice.service.StudentService;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class StudentController {

  private  static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

  @Autowired
  private StudentService studentService;

  @Autowired
  private NationalityService nationalityService;

  @Autowired
  private Function<Student, StudentView> studentToStudentViewMapper;

  @Autowired
  private Function<StudentForm, Student> studentFormToStudentMapper;

  @Autowired
  private Function<Nationality, NationalityRow> nationalityToNationalityFormMapper;

  @RequestMapping("/")
  public void handleRequest() {
    throw new RuntimeException("test exception");
  }

  @RequestMapping(value = "/students/list", method = RequestMethod.GET)
  public AppApiView list(Model model) {
    List<StudentView>  studentList = studentService.getStudents()
        .stream()
        .map(studentToStudentViewMapper)
        .collect(Collectors.toList());

    model.addAttribute("studentList", studentList);
    LOGGER.info("Displaying list of students from /students/list");
    return AppApiView.STUDENT_LIST;
  }

  @RequestMapping(value = "/students/save", method = RequestMethod.POST)
  public AppRedirect save(@ModelAttribute("studentForm") StudentForm studentForm,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

    LOGGER.info("Saving student /students/new");

    StudentView studentView = Optional.of(studentForm)
        .map(studentFormToStudentMapper)
        .map(student -> studentService.addStudent(student))
        .map(studentToStudentViewMapper)
        .get();

    return new AppRedirect("/students/" + studentView.getPersonalNumber());
  }
}
