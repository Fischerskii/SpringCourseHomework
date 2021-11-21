package ru.springcourse.homeworks.firstHomeWrok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Flow {

    ExternalInfoProcess externalInfoProcess;
    ExternalService externalService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Flow.class);

    public Flow(@Lazy ExternalInfoProcess externalInfoProcess, ExternalService externalService) {
        this.externalInfoProcess = externalInfoProcess;
        this.externalService = externalService;
    }

    void run(Integer id) {
        ExternalInfo externalInfo = externalService.getExternalInfo(id);

        if (externalInfo != null) {
            externalInfoProcess.run(externalInfo);
        } else {
            LOGGER.info(String.valueOf(externalInfoProcess.getClass()));
        }
    }
}