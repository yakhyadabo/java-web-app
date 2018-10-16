package org.yakhya.sample.backoffice.service;

import org.yakhya.sample.domain.model.Nationality;

import java.util.List;

public interface NationalityService {
  List<Nationality> findAll();

  Nationality getByCountryCode(String countryCode);
}
