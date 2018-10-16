package org.yakhya.sample.domain.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yakhya.sample.domain.config.MybatisTestConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MybatisTestConfig.class)
@SpringBootApplication
public class NationalityRepositoryTest {

  @Autowired
  private NationalityRepository nationalityRepository;
}