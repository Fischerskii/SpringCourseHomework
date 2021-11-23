package ru.springcourse.homeworks.externalServiceApplication.impl;

import org.springframework.stereotype.Component;
import ru.springcourse.homeworks.externalServiceApplication.ExternalInfo;
import ru.springcourse.homeworks.externalServiceApplication.Process;

@Component
public class ProcessImpl implements Process {
    @Override
    public boolean run(ExternalInfo externalInfo) {
        return false;
    }
}
