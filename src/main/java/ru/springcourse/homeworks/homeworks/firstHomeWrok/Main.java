package ru.springcourse.homeworks.homeworks.firstHomeWrok;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.springcourse.homeworks.homeworks.firstHomeWrok.impl.ExternalServiceImpl;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        ExternalServiceImpl externalService = applicationContext.getBean(ExternalServiceImpl.class);

        Flow flow = applicationContext.getBean(Flow.class);
        flow.run(1);
        flow.run(2);
        flow.run(3);
        flow.run(4);

        externalService.cleaner();
        applicationContext.close();
    }
}