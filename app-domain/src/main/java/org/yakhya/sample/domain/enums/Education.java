package org.yakhya.sample.domain.enums;

public enum Education implements EnumType<String> {

  BACHELOR ("B", "BACHELOR"),
  MASTER ("M", "MASTER"),
  PHD ("P", "PHD");

  private String education;
  private String id;

  Education(String id, String education) {
    this.education = education;
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  public String getEducation() {
    return education;
  }
}
