package org.yakhya.sample.domain.model;

import lombok.Data;
import org.yakhya.sample.domain.model.Role;

@Data
public class User {
  private Long id;
  private String name;
  private String login;
  private String password;
  private Role role;
}
