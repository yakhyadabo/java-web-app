package org.yakhya.sample.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yakhya.sample.backoffice.config.AppRedirect;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.service.UserService;
import org.yakhya.sample.domain.model.User;

@Controller
public class ApiController {

  @Autowired
  private UserService userService;

  @RequestMapping("/")
  public void handleRequest() {
    throw new RuntimeException("test exception");
  }

  @RequestMapping(value = "/user/list", method = RequestMethod.GET)
  public AppApiView list(Model model) {
    model.addAttribute("userList",userService.getUsers());
    return AppApiView.USER_LIST;
  }

  @RequestMapping(value = "/user/new", method = RequestMethod.GET)
  public AppApiView create(Model model) {
    model.addAttribute("userForm", new User());
    return AppApiView.USER_EDIT_CREATE;
  }

  @RequestMapping(value = "/user/add", method = RequestMethod.POST)
  public AppRedirect save(@ModelAttribute("userForm") User userForm,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

    User newUser = userService.addUser(userForm);
    return new AppRedirect("/user/" + newUser.getId());
  }
}
