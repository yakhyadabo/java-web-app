package org.yakhya.sample.backoffice.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.view.RedirectView;

public class AppResponseResolver implements HandlerMethodReturnValueHandler {

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return AppResponse.class.isAssignableFrom(returnType.getParameterType());
  }

  @Override
  public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
    if (returnValue == null) {
      return;
    }

    if (returnValue instanceof AppView) {
      String viewName = ((AppView) returnValue).getLogicalViewName();
      mavContainer.setViewName(viewName);
    } else if (returnValue instanceof AppRedirect) {
      AppRedirect projectRedirect = (AppRedirect) returnValue;
      mavContainer.setView(new RedirectView(projectRedirect.getUrl(), projectRedirect.isContextRelative()));
      mavContainer.setRedirectModelScenario(true);
    } else {
      throw new UnsupportedOperationException("Unexpected return type: " +
          returnType.getParameterType().getName() + " in method: " + returnType.getMethod());
    }
  }
}
