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
public class ApiListController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  public AppApiView view(@PathVariable Long id, Model model) {
    model.addAttribute("userView", userService.getUser(id));
    return AppApiView.USER_VIEW;
  }

  @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
  public AppApiView edit(@PathVariable Long id, Model model) {
    model.addAttribute("userForm", userService.getUser(id));
    return AppApiView.USER_EDIT_CREATE;
  }

  @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
  public AppApiView delete(@PathVariable Long id, Model model) {
    model.addAttribute("userView", userService.getUser(id));
    return AppApiView.USER_LIST;
  }
}
