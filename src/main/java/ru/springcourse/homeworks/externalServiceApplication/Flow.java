package ru.springcourse.homeworks.externalServiceApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Flow {

    Process process;
    ExternalService externalService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Flow.class);

    public Flow(@Lazy Process process, ExternalService externalService) {
        this.process = process;
        this.externalService = externalService;
    }

    void run(Integer id) {
        ExternalInfo externalInfo = externalService.getExternalInfo(id);

        if (externalInfo != null) {
            process.run(externalInfo);
        } else {
            LOGGER.info("Not run process: {}", externalInfo);
        }
    }
}