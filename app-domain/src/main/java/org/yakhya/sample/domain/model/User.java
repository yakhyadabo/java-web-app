package org.yakhya.sample.domain.model;

import lombok.Data;
import org.yakhya.sample.domain.model.Role;

@Data
public class User {
  private Long id;
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private Role role;
}
