package org.yakhya.sample.domain.enums;

public enum Education implements EnumType<String> {

  BACHELOR ("B", "BACHELOR"),
  MASTER ("M", "MASTER"),
  PHD ("P", "PHD");

  private String name;
  private String id;

  Education(String id, String education) {
    this.name = education;
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
