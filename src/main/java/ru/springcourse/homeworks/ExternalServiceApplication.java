package ru.springcourse.homeworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.springcourse.homeworks.externalServiceApplication.ExternalService;

@SpringBootApplication
public class ExternalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalService.class, args);
    }
}