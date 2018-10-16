package org.yakhya.sample.backoffice.mapper;

import org.springframework.stereotype.Component;
import org.yakhya.sample.backoffice.model.NationalityRow;
import org.yakhya.sample.domain.model.Nationality;

import java.util.function.Function;

@Component
public class NationalityToNationalityRowMapper implements Function<Nationality, NationalityRow>{


  @Override
  public NationalityRow apply(Nationality nationality) {
    return NationalityRow.builder()
        .id(nationality.getId())
        .code(nationality.getCode())
        .name(nationality.getName())
        .build();
  }
}
