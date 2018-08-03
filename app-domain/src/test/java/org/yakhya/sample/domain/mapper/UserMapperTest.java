package org.yakhya.sample.domain.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.domain.mapper.config.MybatisTestConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest (classes = MybatisTestConfig.class)
public class UserMapperTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  void justAnExample() {
    System.out.println("This test method should be run");
  }
}