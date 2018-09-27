package org.yakhya.sample.domain.model;

import lombok.Data;

@Data
public abstract class UIDBaseEntity {
  protected Long id;

  public boolean exists(){
    return id != null;
  }

}

