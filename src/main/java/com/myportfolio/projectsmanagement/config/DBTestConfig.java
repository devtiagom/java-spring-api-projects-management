package com.myportfolio.projectsmanagement.config;

import com.myportfolio.projectsmanagement.services.DBServiceH2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class DBTestConfig {

    private final DBServiceH2 dbService;

    public DBTestConfig(DBServiceH2 dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase() {
        this.dbService.instantiateTestDatabase();
        return true;
    }
}
