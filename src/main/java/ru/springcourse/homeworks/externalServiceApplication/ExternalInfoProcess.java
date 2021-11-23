package ru.springcourse.homeworks.externalServiceApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class ExternalInfoProcess implements Process {
    @Value("${id-not-process}")
    private Integer idNotProcess;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalInfoProcess.class);

    @Override
    public boolean run(ExternalInfo externalInfo) {

        if (idNotProcess.equals(externalInfo.getId())) {
            LOGGER.info("ExternalInfoProcess equals idNotProcess");
            return false;
        } else {
            LOGGER.info("ExternalInfoProcess not equals idNotProcess");
            return true;
        }
    }
}