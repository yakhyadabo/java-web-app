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
import org.yakhya.sample.api.config.AppMockMvc;
import org.yakhya.sample.backoffice.controller.ApiListController;
import org.yakhya.sample.backoffice.enums.AppApiView;
import org.yakhya.sample.backoffice.service.UserService;
import org.yakhya.sample.domain.model.User;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiListControllerTest {
  private static final String USER_VIEW = "userView";
  private static final Long USER_ID = 1L;

  @InjectMocks
  private ApiListController apiListController;

  @Mock
  private UserService userService;

  private AppMockMvc mockMvc;

  @BeforeAll
  public void setup() {
    mockMvc = new AppMockMvc(apiListController);
  }

  @Test
  @DisplayName("/user/1 should return a user")
  public void should_return_a_user() throws Exception {
    when(userService.getUser(USER_ID))
        .thenReturn(User.builder().login("yakhya").firstName("Yakhya").lastName("Dabo").build());

    mockMvc.perform(get("/user/"+USER_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(USER_VIEW))
        .andExpect(model().attribute(USER_VIEW, hasProperty("id", equalTo(1L))))
        .andExpect(model().attribute(USER_VIEW, hasProperty("login", equalTo("yakhya"))))
        .andExpect(mockMvc.view(AppApiView.USER_VIEW));
  }
}