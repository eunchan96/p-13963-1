package com.back;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    int version() {
        return 2;
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println("AppConfig.applicationRunner() 작동");

            work1();
            work2();
        };
    }

    private void work1() {
        System.out.println("work1() 작동");
    }

    private void work2() {
        System.out.println("work2() 작동");
    }
}