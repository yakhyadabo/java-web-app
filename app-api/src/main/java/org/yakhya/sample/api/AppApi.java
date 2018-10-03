package org.yakhya.sample.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableAutoConfiguration
@ComponentScan(basePackages = {
    "org.yakhya.sample.api.controller",
    "org.yakhya.sample.api.service",
    "org.yakhya.sample.domain.config"})
public class AppApi  {

  public static void main(String[] args) {
    SpringApplication.run(AppApi.class,args);
  }

}
