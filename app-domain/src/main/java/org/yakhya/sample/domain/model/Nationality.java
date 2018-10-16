package org.yakhya.sample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder(toBuilder = true)
@ToString(of = {"code", "name"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nationality extends UIDBaseEntity{
  private String code;
  private String name;
}
