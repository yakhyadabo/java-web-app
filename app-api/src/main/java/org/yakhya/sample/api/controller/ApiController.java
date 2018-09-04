package org.yakhya.sample.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yakhya.sample.api.service.UserService;
import org.yakhya.sample.domain.model.User;

import java.util.List;

@Controller
public class ApiController {

  @Autowired
  private UserService userService;

  @RequestMapping("/")
  public void handleRequest() {
    throw new RuntimeException("test exception");
  }

  @RequestMapping("/users")
  public List<User> allUsers() {
    return userService.getUsers();
  }
}
