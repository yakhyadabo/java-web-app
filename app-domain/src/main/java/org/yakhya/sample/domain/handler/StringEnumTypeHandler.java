package org.yakhya.sample.domain.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.yakhya.sample.domain.enums.Education;
import org.yakhya.sample.domain.enums.EnumType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@MappedTypes({Education.class})
public class StringEnumTypeHandler<E extends Enum<E> & EnumType<String>> implements TypeHandler<E> {
  private Class<E> type;

  public StringEnumTypeHandler(Class<E> type) {
    this.type = type;
  }

  @Override
  public void setParameter(PreparedStatement preparedStatement, int paramInt, E enumType, JdbcType jdbcType) throws SQLException {
    if (enumType != null) {
      preparedStatement.setString(paramInt, enumType.getId());
    } else {
      preparedStatement.setObject(paramInt, null);
    }
  }

  @Override
  public E getResult(ResultSet resultSet, String param) throws SQLException {
    return from(resultSet.getString(param));
  }

  @Override
  public E getResult(ResultSet resultSet, int param) throws SQLException {
    return from(resultSet.getString(param));
  }

  @Override
  public E getResult(CallableStatement callableStatement, int col) throws SQLException {
    return from(callableStatement.getString(col));
  }

  private E from(String typeId) {
    return Arrays.asList(type.getEnumConstants())
        .stream()
        .filter(enm -> enm.getId().equals(typeId))
        .findAny()
        .orElse(null);
    // Define a default value, UNKNOWN, NOT_DEFINED, ... or accept NULL
    //throw new IllegalArgumentException("Unknown value : " + typeId);
  }
}