package org.yakhya.sample.api.config;

public interface AppRedirect extends AppResponse {
  public String getUrl();

  public boolean isContextRelative();
}

