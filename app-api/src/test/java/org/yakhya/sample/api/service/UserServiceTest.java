package org.yakhya.sample.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void should_return_all_users(){
    System.out.println("This test method should be run");
  }
}