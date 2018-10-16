package org.yakhya.sample.backoffice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yakhya.sample.backoffice.service.NationalityService;
import org.yakhya.sample.domain.model.Nationality;
import org.yakhya.sample.domain.repository.NationalityRepository;

import java.util.List;

@Service
@Transactional
public class NationalityServiceImpl implements NationalityService {

  @Autowired
  private NationalityRepository nationalityRepository;

  @Override
  public List<Nationality> findAll() {
    return nationalityRepository.findAll();
  }

  @Override
  public Nationality getByCountryCode(String countryCode){
    return nationalityRepository.getByCountryCode(countryCode);
  }

}
