package ru.springcourse.homeworks.firstHomeWrok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ExternalInfoProcess implements Process {
    @Value("${id-not-process}")
    private Integer idNotProcess;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalInfoProcess.class);

    @Override
    public boolean run(ExternalInfo externalInfo) {

        if (externalInfo.id.equals(idNotProcess)) {
            LOGGER.info("ExternalInfoProcess equals idNotProcess");
            return false;
        } else {
            LOGGER.info("ExternalInfoProcess not equals idNotProcess");
            return true;
        }
    }
}