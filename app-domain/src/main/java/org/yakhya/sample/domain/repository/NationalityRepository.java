package org.yakhya.sample.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yakhya.sample.domain.mapper.NationalityMapper;
import org.yakhya.sample.domain.model.Nationality;

import java.util.List;

@Repository
public class NationalityRepository {
  @Autowired
  private NationalityMapper nationalityMapper;

  public List<Nationality> findAll(){
    return nationalityMapper.selectAll();
  }

  public Nationality getByCountryCode(String countryCode) {
    return nationalityMapper.selectByCode(countryCode);
  }
}
