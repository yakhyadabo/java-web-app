package org.yakhya.sample.domain.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.yakhya.sample.domain.model.ProfileType;
import org.yakhya.sample.domain.model.User;

import java.util.List;
import java.util.Set;

@Component
public interface UserMapper {
  String SELECT_ALL = "SELECT * FROM sample_user";
  String SELECT_BY_ID = "SELECT * FROM sample_user where id=#{id}";
  String SELECT_BY_LOGIN = "SELECT * FROM sample_user where login=#{login}";
  String INSERT_USER = "Insert into sample_user (login, password, first_name, last_name) values (#{login}, #{password}, #{firstName}, #{lastName})";

  String SELECT_USER_PROFILE_TYPES = "SELECT user_profile_type_id FROM user_profile_type p WHERE p.sample_user_id = #{userId}";

  @Select(SELECT_ALL)
  List<User> selectAll();

  @Select(SELECT_BY_ID)
  User selectById(Long id);

  @Select(SELECT_BY_LOGIN)
  @Results(value = {
      @Result(property = "id",column = "id"),
      @Result(property = "profiles", column = "user_profile_type_id", javaType = Set.class, many = @Many(select = "selectProfilesByUserId"))
  })
  User selectByLogin(String login);

  @Insert(INSERT_USER)
  @Options(useGeneratedKeys = true, keyProperty="id")
  boolean insert(User user);

  @Select(SELECT_USER_PROFILE_TYPES)
  Set<ProfileType> selectProfilesByUserId(@Param("userId") Long userId);
}
