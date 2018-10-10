package org.yakhya.sample.backoffice.enums;

import org.yakhya.sample.backoffice.config.AppView;

public enum AppApiView implements AppView{
  USER_LIST("user/list"),
  USER_EDIT_CREATE("user/edit_create"),
  USER_VIEW("user/view");

  private final String logicalViewName;

  AppApiView(String logicalViewName) {
    this.logicalViewName = logicalViewName;
  }

  @Override
  public String getLogicalViewName() {
    return logicalViewName;
  }
}
