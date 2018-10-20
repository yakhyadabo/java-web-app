package org.yakhya.sample.backoffice.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.domain.repository.StudentRepository;

@ExtendWith({SpringExtension.class})
public class StudentServiceTest {

  @InjectMocks
  private StudentService studentService;

  @Mock
  private StudentRepository studentRepository;




}