package org.yakhya.sample.backoffice.config;

public class AppRedirect implements AppResponse {
  private final String url;
  private final boolean contextRelative;

  /**
   * Creates a context-relative redirect.
   */
  public AppRedirect(String url) {
    this(url, true);
  }

  /**
   * Creates a redirect, context-relative or not.
   */
  public AppRedirect(String url, boolean contextRelative) {
    this.url = url;
    this.contextRelative = contextRelative;
  }

  public String getUrl() {
    return url;
  }

  public boolean isContextRelative() {
    return contextRelative;
  }
}

