package org.yakhya.sample.backoffice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yakhya.sample.backoffice.service.StudentService;
import org.yakhya.sample.domain.model.Student;
import org.yakhya.sample.domain.model.User;
import org.yakhya.sample.domain.repository.StudentRepository;
import org.yakhya.sample.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
  public Optional<Student> getStudent(String personalNumber) {
    Student student = studentRepository.findByPersonalNumber(personalNumber);
    return Optional.ofNullable(student);
  }

  @Override
  public Student addStudent(Student student) {
    return studentRepository.add(student);
  }

  @Override
  public Student updateStudent(String personalNumber, Student student) {
    return studentRepository.update(personalNumber, student);
  }

  @Override
  public void deleteStudent(String personalNumber) {

  }
}
