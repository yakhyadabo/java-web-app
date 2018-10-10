package org.yakhya.sample.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.yakhya.sample.backoffice.config.AppResponseResolver;

import java.util.List;

// import org.springframework.boot.web.servlet.ErrorPage;

//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
    "org.yakhya.sample.backoffice.controller",
    "org.yakhya.sample.backoffice.service",
    "org.yakhya.sample.domain.config"})
public class AppBackoffice implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(AppBackoffice.class,args);
  }

  @Override
  public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers){
    handlers.add(new AppResponseResolver());
  }

}
/*

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
