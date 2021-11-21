package ru.springcourse.homeworks.firstHomeWrok.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.springcourse.homeworks.firstHomeWrok.CacheResult;
import ru.springcourse.homeworks.firstHomeWrok.ExternalInfo;
import ru.springcourse.homeworks.firstHomeWrok.ExternalService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Component("externalServiceImpl")
public class ExternalServiceImpl implements ExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);

    Map<Integer, ExternalInfo> externalInfoMap = new HashMap<>();

    @CacheResult
    @Override
    public ExternalInfo getExternalInfo(Integer id) {
        LOGGER.info("Method getExternalInfo called");
        return externalInfoMap.get(id);
    }

    @PostConstruct
    public void init() {
        externalInfoMap.put(1, new ExternalInfo(1, null));
        externalInfoMap.put(2, new ExternalInfo(2, "hashInfo"));
        externalInfoMap.put(3, new ExternalInfo(3, "info"));
        externalInfoMap.put(4, new ExternalInfo(4, "information"));
    }

    @PreDestroy
    public void destroy() {
        this.externalInfoMap.clear();

        LOGGER.info("HashMap cleared");
    }
}