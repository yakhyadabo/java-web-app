package org.yakhya.sample.domain.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

@MappedTypes(LocalDate.class)
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int paramIndex, LocalDate localDate, JdbcType jdbcType) throws SQLException {
    if (localDate != null) {
      ps.setTimestamp(paramIndex, Timestamp.valueOf(localDate.atStartOfDay()));
    } else {
      ps.setTimestamp(paramIndex, null);
    }
  }

  @Override
  public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return asLocalDateIfNotNull(rs.getTimestamp(columnName));
  }

  @Override
  public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return asLocalDateIfNotNull(rs.getTimestamp(columnIndex));
  }

  @Override
  public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return asLocalDateIfNotNull(cs.getTimestamp(columnIndex));
  }

  private LocalDate asLocalDateIfNotNull(Timestamp timestamp) {
    if (timestamp != null) {
      return timestamp.toLocalDateTime().toLocalDate();
    } else {
      return null;
    }
  }

}

