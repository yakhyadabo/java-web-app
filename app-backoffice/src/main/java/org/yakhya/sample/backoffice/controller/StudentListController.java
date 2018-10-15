package org.yakhya.sample.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.model.StudentView;
import org.yakhya.sample.backoffice.service.StudentService;
import org.yakhya.sample.domain.model.Student;

import java.util.function.Function;

@Controller
public class StudentListController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private Function<Student, StudentView> studentToStudentViewMapper;
  
  @RequestMapping(value = "/students/{personalNumber}", method = RequestMethod.GET)
  public AppApiView view(@PathVariable String personalNumber, Model model) {
    StudentView studentView = studentService.getStudent(personalNumber)
        .map(studentToStudentViewMapper)
        .get();
    
    model.addAttribute("studentView", studentView);
    return AppApiView.STUDENT_VIEW;
  }

  @RequestMapping(value = "/students/edit/{personalNumber}", method = RequestMethod.GET)
  public AppApiView edit(@PathVariable String personalNumber, Model model) {
    StudentView studentView = studentService.getStudent(personalNumber)
        .map(studentToStudentViewMapper)
        .get();
    
    model.addAttribute("studentView", studentView);
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
