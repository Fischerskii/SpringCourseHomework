package ru.springcourse.homeworks.homeworks.firstHomeWrok;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TestData {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Map<Integer, ExternalInfo> addDataToMap() {
        Map<Integer, ExternalInfo> externalInfoMap = new HashMap<>() {{
            put(1, new ExternalInfo(1, null));
            put(2, new ExternalInfo(2, "hasInfo"));
            put(3, new ExternalInfo(3, "info"));
            put(4, new ExternalInfo(4, "information"));
        }};
        return externalInfoMap;
    }
}
