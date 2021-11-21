package ru.springcourse.homeworks.homeworks.firstHomeWrok.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.springcourse.homeworks.homeworks.firstHomeWrok.CacheResult;
import ru.springcourse.homeworks.homeworks.firstHomeWrok.ExternalInfo;
import ru.springcourse.homeworks.homeworks.firstHomeWrok.ExternalService;

import java.util.Map;

@Component("externalServiceImpl")
public class ExternalServiceImpl implements ExternalService {
    Map<Integer, ExternalInfo> externalInfoMap;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);

    @CacheResult
    @Override
    public ExternalInfo getExternalInfo(Integer id) {
        LOGGER.info(String.valueOf(externalInfoMap));
        return externalInfoMap.get(id);
    }
}