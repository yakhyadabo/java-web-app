package org.yakhya.sample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yakhya.sample.domain.enums.Role;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends UIDBaseEntity{
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private Role role;
}
