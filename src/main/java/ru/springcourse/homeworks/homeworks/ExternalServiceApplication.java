package ru.springcourse.homeworks.homeworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.springcourse.homeworks.homeworks.firstHomeWrok.ExternalService;

@SpringBootApplication
public class ExternalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalService.class, args);
    }
}