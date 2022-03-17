package com.example.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

@SpringBootApplication(exclude = {
        SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class,SecurityAutoConfiguration.class}
)
public class TechnicaltestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicaltestApplication.class, args);
    }

}
