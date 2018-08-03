package org.yakhya.sample.db.migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.yakhya.sample.db.migration.config.MigrationDataSourceConfiguration;

@SpringBootApplication
@Import(value = {MigrationDataSourceConfiguration.class})
@EnableConfigurationProperties
public class DatabaseMigrationStartup {
  public static void main(String[] args) {
    SpringApplication.run(DatabaseMigrationStartup.class, args);
  }

}
