package com.techforge.reservation.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

@Configuration
public class RunnerConfiguration {

    @Bean
    public ApplicationListener<ContextRefreshedEvent> runApplicationRunners(
            List<ApplicationRunner> runners) {

        return event -> {
            ApplicationArguments args =
                    new DefaultApplicationArguments(new String[]{});

            for (ApplicationRunner runner : runners) {
                try {
                    runner.run(args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

}
