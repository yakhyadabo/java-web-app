package org.yakhya.sample.domain.mapper;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.domain.config.MybatisTestConfig;
import org.yakhya.sample.domain.model.User;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MybatisTestConfig.class)
public class UserMapperTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void justAnExample() {
    System.out.println("This test method should be run");
  }

  @Test
  public void should_insert() {
    User newUser = User.builder()
        .login("yakhyadabo")
        .password("myPassword")
        .firstName("Yakhya")
        .lastName("Dabo")
        .build();

    userMapper.insert(newUser);
    User user =  userMapper.selectByLogin("yakhyadabo");
    assertThat(user).isEqualToComparingFieldByField(newUser);
  }
}