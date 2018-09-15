package org.yakhya.sample.api.service;

import org.yakhya.sample.domain.model.User;

import java.util.List;

public interface UserService {
  List<User> getUsers();
  User getUser(Long id);
  void addUser(User user);
  void deleteUser(String id);
}
