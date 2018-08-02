package org.yakhya.sample.domain.mapper;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.yakhya.sample.domain.model.User;

import java.util.List;

@Component
public interface UserMapper {
  String SELECT_ALL = "SELECT * FROM sample_user";

  @Select(SELECT_ALL)
  List<User> selectAll();
}
