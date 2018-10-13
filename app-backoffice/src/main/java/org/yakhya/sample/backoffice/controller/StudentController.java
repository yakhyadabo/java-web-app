package org.yakhya.sample.backoffice.controller;

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
import org.yakhya.sample.backoffice.model.StudentForm;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.backoffice.service.StudentService;
import org.yakhya.sample.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class StudentController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private Function<Student, StudentView> studentToStudentViewMapper;

  @Autowired
  private Function<StudentView, Student> studentViewToStudentMapper;

  @Autowired
  private Function<StudentForm, Student> studentFormToStudentMapper;

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
    return AppApiView.STUDENT_LIST;
  }

  @RequestMapping(value = "/students/new", method = RequestMethod.GET)
  public AppApiView create(Model model) {
    model.addAttribute("studentForm", new StudentForm());
    return AppApiView.STUDENT_EDIT_CREATE;
  }

  @RequestMapping(value = "/students/save", method = RequestMethod.POST)
  public AppRedirect save(@ModelAttribute("studentForm") StudentForm studentForm,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

    StudentView studentView = Optional.of(studentForm)
        .map(studentFormToStudentMapper)
        .map(student -> studentService.addStudent(student))
        .map(studentToStudentViewMapper)
        .get();

    return new AppRedirect("/students/" + studentView.getPersonalNumber());
  }
}
