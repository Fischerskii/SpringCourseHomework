package ru.springcourse.homeworks.externalServiceApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrintBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, Object> beansWithAnnotation = configurableListableBeanFactory.getBeansWithAnnotation(CacheResult.class);
        if (!beansWithAnnotation.isEmpty()) {
            LOGGER.info("Have beans containing annotation @CacheResult");
            for (Map.Entry<String, Object> map : beansWithAnnotation.entrySet()) {
                LOGGER.warn("Bean " + map + " contains CacheResult annotation");
            }
        }

        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (configurableListableBeanFactory.isPrototype(beanDefinitionName)) {
                LOGGER.warn(beanDefinitionName + " - isPrototype");
            }
            LOGGER.info("BeanDefinitionName " + beanDefinitionName);
        }
    }
}