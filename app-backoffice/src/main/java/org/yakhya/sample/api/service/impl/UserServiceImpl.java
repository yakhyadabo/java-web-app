package org.yakhya.sample.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yakhya.sample.api.service.UserService;
import org.yakhya.sample.domain.model.User;
import org.yakhya.sample.domain.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> getUsers() {
    return userRepository.findAllUser();
  }

  @Override
  public User getUser(Long id) {
    return userRepository.findUser(id);
  }

  @Override
  public User addUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public void deleteUser(String id) {

  }
}
