package org.yakhya.sample.domain.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.yakhya.sample.domain.config.MybatisTestConfig;
import org.yakhya.sample.domain.model.Nationality;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MybatisTestConfig.class)
@Transactional
public class NationalityMapperTest {

  @Autowired
  private NationalityMapper nationalityMapper;

  private static Nationality SEN = new Nationality("SEN","Senegal");
  private static Nationality GMB = new Nationality("GMB","Gambia");
  private static Nationality MAU = new Nationality("MAU","Mauritania");

  @BeforeEach
  public void setUp(){

  }

  @AfterEach
  public void tearDown(){

  }

  @Test
  public void should_find_nationality_by_using_country_code(){

    nationalityMapper.insert(SEN);
    Nationality nationality = nationalityMapper.selectByCode(SEN.getCode());

    assertThat(nationality).isEqualToComparingFieldByField(SEN);
  }

  @Test
  public void should_find_all_nationalities(){
    nationalityMapper.insert(SEN);
    nationalityMapper.insert(GMB);
    nationalityMapper.insert(MAU);

    List<Nationality> nationalities = nationalityMapper.selectAll();

    assertThat(nationalities).contains(GMB,MAU);
  }
}