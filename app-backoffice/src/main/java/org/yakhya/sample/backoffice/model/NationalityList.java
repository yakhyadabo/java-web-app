package org.yakhya.sample.backoffice.model;

import lombok.Getter;

import java.util.List;

@Getter
public class NationalityList {
  private final List<NationalityRow> rows;

  public NationalityList(List<NationalityRow> rows){
    this.rows = rows;
  }
}
