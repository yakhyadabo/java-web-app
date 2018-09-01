package org.yakhya.sample.domain.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.yakhya.sample.test.db.LiquibaseTestConfig;

import javax.sql.DataSource;

@Configuration
@MapperScan({"org.yakhya.sample.domain.mapper","org.yakhya.sample.domain.repository"})
@Import(LiquibaseTestConfig.class)
public class MybatisTestConfig {
  @Bean
  public DataSource getDataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .build();
  }


  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    //sessionFactory.setConfigLocation(new ClassPathResource("META-INF/spring/mybatis-db.xml"));
    sessionFactory.setDataSource(getDataSource());
    return sessionFactory.getObject();
  }

}