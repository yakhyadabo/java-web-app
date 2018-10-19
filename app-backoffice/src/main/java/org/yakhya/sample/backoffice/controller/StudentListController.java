package org.yakhya.sample.backoffice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.model.NationalityList;
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
public class StudentListController {

  private  static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

  @Autowired
  private StudentService studentService;

  @Autowired
  private NationalityService nationalityService;

  @Autowired
  private Function<Student, StudentView> studentToStudentViewMapper;

  @Autowired
  private Function<Student, StudentForm> studentToStudentFormMapper;

  @Autowired
  private Function<Nationality, NationalityRow> nationalityToNationalityRowMapper;


  @RequestMapping(value = "/students/{personalNumber}", method = RequestMethod.GET)
  public AppApiView view(@PathVariable String personalNumber, Model model) {
    StudentView studentView = studentService.getStudent(personalNumber)
        .map(studentToStudentViewMapper)
        .get();
    
    model.addAttribute("studentView", studentView);
    return AppApiView.STUDENT_VIEW;
  }

  @RequestMapping(value = "/students/new", method = RequestMethod.GET)
  public AppApiView create(Model model) {
    List<NationalityRow> nationalityRows = nationalityService.findAll()
        .stream()
        .map(nationalityToNationalityRowMapper)
        .collect(Collectors.toList());

    NationalityList nationalityList = new NationalityList(nationalityRows);

    model.addAttribute("studentForm", new StudentForm());
    model.addAttribute("nationalityList", nationalityList);
    LOGGER.info("Displaying student creation form /students/new");
    return AppApiView.STUDENT_EDIT_CREATE;
  }

  @RequestMapping(value = "/students/edit/{personalNumber}", method = RequestMethod.GET)
  public AppApiView edit(@PathVariable Optional<String> personalNumber, Model model) {

    StudentForm studentForm = studentService.getStudent(personalNumber.get())
        .map(studentToStudentFormMapper)
        .get();


    List<NationalityRow> nationalityRows = nationalityService.findAll()
        .stream()
        .map(nationalityToNationalityRowMapper)
        .collect(Collectors.toList());

    NationalityList nationalityList = new NationalityList(nationalityRows);

    model.addAttribute("studentForm", studentForm);
    model.addAttribute("nationalityList", nationalityList);
    LOGGER.info("Editing student {}", personalNumber);

    return AppApiView.STUDENT_EDIT_CREATE;
  }

  @RequestMapping(value = "/students/delete/{personalNumber}", method = RequestMethod.GET)
  public AppApiView delete(@PathVariable String personalNumber, Model model) {
    StudentView studentView = studentService.getStudent(personalNumber)
        .map(studentToStudentViewMapper)
        .get();

    model.addAttribute("studentView", studentView);
    return AppApiView.STUDENT_LIST;
  }
}
