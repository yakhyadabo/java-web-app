package org.yakhya.sample.domain.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.yakhya.sample.domain.mapper")

@ComponentScan(basePackages = "org.yakhya.sample.domain.repository")
//@PropertySource("classpath:application.properties")
public class MyBatisConfiguration {

  @Value("${datasource.driver.class.name}")
  private String dbDriverClassName;

  @Value("${datasource.url}")
  private String dbUrl;

  @Value("${datasource.username}")
  private String dbUsername;

  @Value("${datasource.password}")
  private String dbPassword;

  @Bean
  public DataSource getDataSource() {
    return DataSourceBuilder
        .create()
        .url(dbUrl)
        .driverClassName(dbDriverClassName)
        .username(dbUsername)
        .password(dbPassword)
        .build();
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    //sessionFactory.setConfigLocation(new ClassPathResource("META-INF/spring/mybatis-config.xml"));
    sessionFactory.setDataSource(getDataSource());
    return sessionFactory.getObject();
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(getDataSource());
  }
}
