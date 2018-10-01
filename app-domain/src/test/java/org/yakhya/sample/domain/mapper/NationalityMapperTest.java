package org.yakhya.sample.domain.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.domain.config.MybatisTestConfig;
import org.yakhya.sample.domain.model.Nationality;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MybatisTestConfig.class)
public class NationalityMapperTest {

  @Autowired
  private NationalityMapper nationalityMapper;

  private static Nationality SEN = new Nationality("SEN","Senegal");

  @Test
  public void should_find_nationality_by_using_country_code(){

    nationalityMapper.insert(SEN);
    Nationality nationality = nationalityMapper.selectByCode(SEN.getCode());

    assertThat(nationality).isEqualToComparingFieldByField(SEN);
  }

  @Test
  public void should_find_all_nationalities(){

    List<Nationality> nationalities = nationalityMapper.selectAll();

    assertThat(nationalities).hasSize(2);
  }
}