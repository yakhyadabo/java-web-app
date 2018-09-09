package org.yakhya.sample.api.config;

/*import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;*/

public class AppMockMvc {/*implements BeforeAllCallback, AfterAllCallback {

  private static final String TEMPLATE_PREFIX = "/templates/";
  private static final String TEMPLATE_SUFFIX = ".html";
  private final MockMvc mockMvc;

  public AppMockMvc(Object controller) {
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
        //.setViewResolvers(viewResolver())
        .setCustomReturnValueHandlers(new AppResponseResolver())
        .build();
  }

  public ResultActions perform(RequestBuilder requestBuilder) throws Exception {
    return mockMvc.perform(requestBuilder);
  }


  public ResultMatcher view(final AppView view) {
    //checkNotNull(view);
    return result -> {
      ModelAndView mav = result.getModelAndView();
      assertTrue("No ModelAndView found", mav != null);
      assertEquals("View name", view.getLogicalViewName(), mav.getViewName());
    };
  }


  public ResultMatcher redirect(final String someUrl) {
    //checkNotNull(someUrl);
    return result -> {
      ModelAndView mav = result.getModelAndView();
      assertTrue("No ModelAndView found", mav != null);
      View view = mav.getView();
      assertTrue("View must be an instance of RedirectView", view instanceof RedirectView);
      RedirectView redirectView = (RedirectView) view;
      assertEquals("Redirect URL", redirectView.getUrl(), someUrl);
    };
  }

  @Override
  public void afterAll(ExtensionContext extensionContext) throws Exception {

  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {

  }*/
}

