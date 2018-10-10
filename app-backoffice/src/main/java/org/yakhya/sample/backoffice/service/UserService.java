package org.yakhya.sample.backoffice.service;

import org.yakhya.sample.domain.model.User;

import java.util.List;

public interface UserService {
  List<User> getUsers();
  User getUser(Long id);
  User addUser(User user);
  void deleteUser(String id);
}
