package org.yakhya.sample.domain.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.domain.config.MybatisTestConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MybatisTestConfig.class)
@SpringBootApplication
@ComponentScan(basePackages = {"org.yakhya.sample.domain.repository"})
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void should_return_all_users(){
    System.out.println("This test method should be run");
  }
}