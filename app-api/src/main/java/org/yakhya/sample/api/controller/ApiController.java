package org.yakhya.sample.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiController {

  @RequestMapping("/")
  public void handleRequest() {
    throw new RuntimeException("test exception");
  }
}
