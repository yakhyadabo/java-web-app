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
import org.yakhya.sample.backoffice.controller.ApiController;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.service.UserService;
import org.yakhya.sample.domain.model.User;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
  @DisplayName("/user/add should create a user")
  public void should_create_a_user() throws Exception {
    when(userService.addUser(any(User.class)))
        .thenReturn(User.builder().build());

    mockMvc.perform(postValidUser("/user/add"))
        .andExpect(status().isFound())
        .andExpect(mockMvc.redirect("/user/1"));
  }

  @Test
  @DisplayName("/user/list should return the list of users")
  public void should_display_view_with_the_list_of_users() throws Exception {
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
        User.builder().login("yakhya").firstName("Yakhya").lastName("Dabo").build(),
        User.builder().login("max").firstName("Max").lastName("Wilson").build(),
        User.builder().login("dave").firstName("Dave").lastName("Shepherd").build(),
        User.builder().login("michel").firstName("Michel").lastName("Martin").build()
    );
  }

}