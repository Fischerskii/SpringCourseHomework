package ru.springcourse.homeworks.homeworks.firstHomeWrok.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.springcourse.homeworks.homeworks.firstHomeWrok.CacheResult;
import ru.springcourse.homeworks.homeworks.firstHomeWrok.ExternalInfo;
import ru.springcourse.homeworks.homeworks.firstHomeWrok.ExternalService;

import java.util.HashMap;
import java.util.Map;

@Component("externalServiceImpl")
public class ExternalServiceImpl implements ExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);

    Map<Integer, ExternalInfo> externalInfoMap = new HashMap<>();

    public ExternalServiceImpl() {
        this.externalInfoMap.put(1, new ExternalInfo(1, null));
        this.externalInfoMap.put(2, new ExternalInfo(2, "hashInfo"));
        this.externalInfoMap.put(3, new ExternalInfo(3, "info"));
        this.externalInfoMap.put(4, new ExternalInfo(4, "information"));
    }

    @CacheResult
    @Override
    public ExternalInfo getExternalInfo(Integer id) {
        LOGGER.info("Method getExternalInfo called");
        return externalInfoMap.get(id);
    }

    public void cleaner() {
        this.externalInfoMap.clear();

        LOGGER.info("HashMap cleared");
    }
}