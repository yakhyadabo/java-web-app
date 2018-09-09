package org.yakhya.sample.api.enums;

import org.yakhya.sample.api.config.AppView;

public enum AppApiView implements AppView{
  USER_LIST("user/list"),
  USER_EDIT_CREATE("user/edit_create"),
  USER_VIEW("user/view"),
  USER_SETTINGS("user/settings");

  private final String logicalViewName;

  AppApiView(String logicalViewName) {
    this.logicalViewName = logicalViewName;
  }

  @Override
  public String getLogicalViewName() {
    return logicalViewName;
  }
}
