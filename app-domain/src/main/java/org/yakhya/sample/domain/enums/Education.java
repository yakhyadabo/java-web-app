package org.yakhya.sample.domain.enums;

public enum Education implements EnumType<String> {

  BACHELOR ("B", "BACHELOR"),
  MASTER ("M", "MASTER"),
  PHD ("P", "PHD");

  private String id;
  private String name;

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

  public static Education getById(String id) {
    for(Education education : values()) {
      if(education.id.equals(id)) return education;
    }
    throw new IllegalArgumentException("Id doesn't match any Education");
  }
}
