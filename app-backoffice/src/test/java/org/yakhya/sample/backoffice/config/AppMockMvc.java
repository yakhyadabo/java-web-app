package org.yakhya.sample.backoffice.config;

import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import org.yakhya.sample.backoffice.config.AppResponseResolver;
import org.yakhya.sample.backoffice.config.AppView;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class AppMockMvc {

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
      AssertionErrors.assertEquals("View name", view.getLogicalViewName(), mav.getViewName());
      AssertionErrors.assertEquals("Forwarded URL",  view.getLogicalViewName(), result.getResponse().getForwardedUrl());
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
      AssertionErrors.assertEquals("Redirect URL", redirectView.getUrl(), someUrl);
    };
  }

}
