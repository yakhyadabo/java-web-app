package org.yakhya.sample.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yakhya.sample.domain.mapper.StudentMapper;
import org.yakhya.sample.domain.model.Student;

import java.util.List;

@Repository
public class StudentRepository {
  @Autowired
  private StudentMapper studentMapper;

  public List<Student> findAll(){
    return studentMapper.selectAll();
  }

  public Student findByPersonalNumber(String personalNumber){
    return studentMapper.selectByPersonalNumber(personalNumber);
  }

  public Student add(Student student) {
    studentMapper.insert(student);
    return student;
  }

  public Student update(String personalNumber, Student student) {
    studentMapper.update(personalNumber, student);
    return student;
  }

  public void delete(String personalNumber) {
    studentMapper.delete(personalNumber);
  }
}
