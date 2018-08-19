package org.yakhya.sample.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.api.AppApi;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppApi.class)
public class ApiControllerTest {

  @Autowired
  private ApiController apiController;

  @Test
  void justAnExample() {
    System.out.println("This test method should be run");
  }

}