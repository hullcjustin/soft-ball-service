package com.softball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class SoftBallMain {
    public static void main(String[] args) {
        SpringApplication.run(SoftBallMain.class, args);
    }
}
