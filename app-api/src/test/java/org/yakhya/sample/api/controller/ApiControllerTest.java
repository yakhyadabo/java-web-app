package org.yakhya.sample.api.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.yakhya.sample.api.AppApi;
import org.yakhya.sample.api.service.UserService;
import org.yakhya.sample.domain.mapper.UserMapper;
import org.yakhya.sample.domain.model.User;
import org.yakhya.sample.domain.repository.UserRepository;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
/*
@SpringBootTest(classes = AppApi.class)
@WebAppConfiguration*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiControllerTest {
  private static final String TEMPLATE_PREFIX = "templates/";
  private static final String TEMPLATE_SUFFIX = ".html";

  @InjectMocks
  private ApiController apiController;

  @Mock
  private UserService userService;

  private MockMvc mockMvc;

  @BeforeAll
  public void setup() {

    this.mockMvc = MockMvcBuilders.standaloneSetup(this.apiController)
        .setViewResolvers(viewResolver())
        .build();
  }

  @Test
  @DisplayName("Just an example")
  void justAnExample() {
    System.out.println("This test method should be run");
  }

  @Test
  public void shouldReturnDefaultMessage() throws Exception {
    when(userService.getUsers()).thenReturn(Arrays.asList(new User()));

   mockMvc.perform(get("/user/lists"))//.andDo(print())

       .andExpect(status().is2xxSuccessful())
       .andExpect(content().string(containsString("Hello World")));
  }

  private InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix(TEMPLATE_PREFIX);
    viewResolver.setSuffix(TEMPLATE_SUFFIX);
    return viewResolver;
  }

}