package org.yakhya.sample.api.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.yakhya.sample.api.config.AppMockMvc;
import org.yakhya.sample.api.enums.AppApiView;
import org.yakhya.sample.api.service.UserService;
import org.yakhya.sample.domain.model.User;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiControllerTest {
  @InjectMocks
  private ApiController apiController;

  @Mock
  private UserService userService;

  private AppMockMvc mockMvc;

  @BeforeAll
  public void setup() {
    mockMvc = new AppMockMvc(apiController);
  }

  @Test
  @DisplayName("/user/save should create a user")
  public void should_create_a_user() throws Exception {
    when(userService.addUser(any(User.class)))
        .thenReturn(User.builder().id(1L).build());

    mockMvc.perform(postValidUser("/user/save"))
        .andExpect(status().isFound())
        .andExpect(mockMvc.redirect("/user/1"));
  }

  @Test
  @DisplayName("/user/list should return the list of users")
  public void should_return_list_of_users() throws Exception {
    when(userService.getUsers()).thenReturn(listOfUsers());

    mockMvc.perform(get("/user/list"))
        .andExpect(status().isOk())
        .andExpect(model().hasNoErrors())
        .andExpect(model().attributeExists("userList"))
        .andExpect(mockMvc.view(AppApiView.USER_LIST));
  }

  private static MockHttpServletRequestBuilder postValidUser(String url) {
    return post(url)
        .param("login", "yakhya")
        .param("firstName", "Yakhya")
        .param("lastName", "Dabo");
  }

  private List<User> listOfUsers() {
    return Arrays.asList(
        User.builder().id(000L).login("yakhya").firstName("Yakhya").lastName("Dabo").build(),
        User.builder().id(111L).login("max").firstName("Max").lastName("Wilson").build(),
        User.builder().id(222L).login("dave").firstName("Dave").lastName("Shepherd").build(),
        User.builder().id(333L).login("michel").firstName("Michel").lastName("Martin").build()
    );
  }

}