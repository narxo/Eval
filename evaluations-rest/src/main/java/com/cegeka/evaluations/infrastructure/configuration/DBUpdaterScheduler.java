package com.cegeka.evaluations.infrastructure.configuration;

import com.cegeka.evaluations.domain.populateDB.DBUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@Configuration
@Profile("!test")
@EnableScheduling
public class DBUpdaterScheduler {

    @Autowired
    private DBUpdater employeeUpdater;
    // 1 day fixed delay
    @Scheduled(initialDelay = 3000, fixedDelay = 86_400_000)
    public void update() {
        System.out.println("Begin loading users from LDAP");

        employeeUpdater.updateAll();

        System.out.println("Ended loading users from LDAP");
    }
}
