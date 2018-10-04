package org.yakhya.sample.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yakhya.sample.api.service.StudentService;
import org.yakhya.sample.domain.model.Student;
import org.yakhya.sample.domain.repository.StudentRepository;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  @Override
  public Student getStudent(String personalNumber) {
    return studentRepository.findByPersonalNumber(personalNumber);
  }

  @Override
  public Student addStudent(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public void deleteStudent(String personalNumber) {

  }
}
