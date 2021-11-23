package ru.springcourse.homeworks.externalServiceApplication.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.springcourse.homeworks.externalServiceApplication.CacheResult;
import ru.springcourse.homeworks.externalServiceApplication.ExternalInfo;
import ru.springcourse.homeworks.externalServiceApplication.ExternalService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExternalServiceImpl implements ExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);

    private final Map<Integer, ExternalInfo> externalInfoMap = new HashMap<>();

    @Value("${id-not-process}")
    private Integer id;

    public ExternalServiceImpl() {
        externalInfoMap.put(id, new ExternalInfo(id, "from properties"));
    }

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
        LOGGER.info("Map before: {}", externalInfoMap);
        this.externalInfoMap.clear();
        LOGGER.info("Map after: {}", externalInfoMap);
    }
}