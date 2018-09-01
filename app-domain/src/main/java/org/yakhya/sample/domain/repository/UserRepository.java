package org.yakhya.sample.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yakhya.sample.domain.mapper.UserMapper;
import org.yakhya.sample.domain.model.User;

import java.util.List;

@Repository
public class UserRepository {

  @Autowired
  private UserMapper userMapper;

  public List<User> findAllUser(){
    return userMapper.selectAll();
  }

}
