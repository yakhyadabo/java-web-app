package org.yakhya.sample.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.model.Student;

import java.util.List;

@Component
public interface StudentMapper {
  String GET_NATIONALITY ="SELECT * FROM nationality WHERE id=#{nationality_id}";
  String SELECT_ALL = "SELECT * FROM student";
  String SELECT_BY_ID = "SELECT * FROM student where id=#{id}";
  String SELECT_BY_PERSONAL_NUMBER = "SELECT * FROM student where personal_number=#{personalNumber}";
  String INSERT_USER = "Insert into student (personal_number, first_name, last_name, date_of_birth, nationality_id, education, scholarship_holder) " +
      "values (#{personalNumber},#{firstName}, #{lastName}, #{dateOfBirth}, #{nationality.id}, #{education}, #{scholarshipHolder})";

  @Select(SELECT_ALL)
  @Results(value = {
      @Result(property = "id",column = "id"),
      @Result(property = "nationality",column = "nationality_id", javaType = Nationality.class,one=@One(select = "getNationality")),
  })
  List<Student> selectAll();

  @Select(SELECT_BY_ID)
  Student selectById(Long id);

  @Select(SELECT_BY_PERSONAL_NUMBER)
  @Results(value = {
      @Result(property = "id",column = "id"),
      @Result(property = "nationality",column = "nationality_id", javaType = Nationality.class,one=@One(select = "getNationality")),
  })
  Student selectByPersonalNumber(String personalNumber);

  @Insert(INSERT_USER)
  @Options(useGeneratedKeys = true, keyProperty="id")
  boolean insert(Student user);

  @Select(GET_NATIONALITY)
  Nationality getNationality(String id);
}