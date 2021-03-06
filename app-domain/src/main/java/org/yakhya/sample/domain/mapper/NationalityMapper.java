package org.yakhya.sample.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.yakhya.sample.domain.model.Nationality;

import java.util.List;

@Component
public interface NationalityMapper {

  String INSERT_NATIONALITY = "INSERT INTO nationality (code,name) VALUES (#{code}, #{name})";

  String SELECT_BY_CODE = "SELECT * FROM nationality where code=#{code}";

  String SELECT_ALL = "SELECT * FROM nationality";

  @Insert(INSERT_NATIONALITY)
  @Options(useGeneratedKeys = true, keyProperty="id")
  boolean insert(Nationality nationality);

  @Select(SELECT_BY_CODE)
  Nationality selectByCode(String code);

  @Select(SELECT_ALL)
  List<Nationality> selectAll();
}

