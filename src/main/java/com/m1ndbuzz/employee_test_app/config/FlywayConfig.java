package com.m1ndbuzz.employee_test_app.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FlywayConfig {
    private final Environment env;

    public FlywayConfig(final Environment env) {
        this.env = env;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .baselineOnMigrate(true)
                .locations("classpath:db/migration")
                .dataSource(
                        env.getRequiredProperty("spring.datasource.url"),
                        env.getRequiredProperty("spring.datasource.username"),
                        env.getRequiredProperty("spring.datasource.password"))
                .load();
    }
}
