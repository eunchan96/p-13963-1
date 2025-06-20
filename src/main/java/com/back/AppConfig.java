package com.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {
    @Autowired
    @Lazy
    private AppConfig self;

    @Bean
    int version() {
        return 2;
    }

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            System.out.println("AppConfig.applicationRunner() 작동");

            self.work1();
            self.work2();
        };
    }

    private void work1() {
        System.out.println("work1() 작동");
    }

    private void work2() {
        System.out.println("work2() 작동");
    }
}