package org.yakhya.sample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends UIDBaseEntity{
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private Set<ProfileType> profiles = new HashSet<>();

  public void addProfile(ProfileType profileType){
    this.profiles.add(profileType);
  }
}
