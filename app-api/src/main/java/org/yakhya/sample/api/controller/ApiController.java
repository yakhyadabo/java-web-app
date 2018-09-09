package org.yakhya.sample.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yakhya.sample.api.enums.AppApiView;
import org.yakhya.sample.api.service.UserService;

@Controller
public class ApiController {

  @Autowired
  private UserService userService;

  @RequestMapping("/")
  public void handleRequest() {
    throw new RuntimeException("test exception");
  }

  @RequestMapping(value = "/user/list", method = RequestMethod.GET)
  public AppApiView getAllUsers(Model model) {
    model.addAttribute("userList",userService.getUsers());
    return AppApiView.USER_LIST;
  }

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  public AppApiView getUser(@PathVariable String id, Model model) {
    model.addAttribute("userView", userService.getUser(id));
    return AppApiView.USER_VIEW;
  }
}
