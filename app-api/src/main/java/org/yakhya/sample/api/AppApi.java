package org.yakhya.sample.api;

import java.util.List;

//import com.peopleinput.iris.core.web.config.IrisResponseResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.WebServer;
// import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AppApi extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(AppApi.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(AppApi.class);
  }
}
/*
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.yakhya.sample.api"})
public class AppApi {

  public static void main(String[] args) {
    SpringApplication.run(AppApi.class, args);
  }

  @Bean
  public WebMvcConfigurerAdapter observableMVCConfiguration() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new IrisResponseResolver());
      }
    };
  }

  @Bean
  public WebServer containerCustomizer() {

    return (container -> {
      ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html");
      ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
      ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");

      container.addErrorPages(error401Page, error404Page, error500Page);
    });
  }
}*/
