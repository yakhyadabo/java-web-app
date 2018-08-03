package org.yakhya.sample.test.db;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseTestConfig {

  @Bean
  public DataSource getDataSource() {
    return DataSourceBuilder
        .create()
        .url("jdbc:h2:mem:play;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=FALSE")
        .driverClassName("org.h2.Driver")
        //.username(dbUsername)
        //.password(dbPassword)
        .build();
  }

/*  @Bean
  public DataSource getDataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setName("jdbc:h2:mem:play;MODE=PostgreSQL")
        .build();
  }*/

  @Bean
  public SpringLiquibase liquibase() {
    SpringLiquibase liquibase = new SpringLiquibase();

    liquibase.setDataSource(getDataSource());
    liquibase.setChangeLog("classpath:liquibase/db.changelog-master.xml");
    liquibase.setContexts("integrationtest");

    return liquibase;
  }
}