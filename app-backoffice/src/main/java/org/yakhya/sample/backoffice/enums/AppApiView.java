package org.yakhya.sample.backoffice.enums;

import org.yakhya.sample.backoffice.config.AppView;

public enum AppApiView implements AppView{
  STUDENT_LIST("student/list"),
  STUDENT_EDIT_CREATE("student/edit_create"),
  STUDENT_VIEW("student/view");

  private final String logicalViewName;

  AppApiView(String logicalViewName) {
    this.logicalViewName = logicalViewName;
  }

  @Override
  public String getLogicalViewName() {
    return logicalViewName;
  }
}
