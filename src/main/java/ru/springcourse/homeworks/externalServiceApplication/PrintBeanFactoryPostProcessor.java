package ru.springcourse.homeworks.externalServiceApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrintBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintBeanFactoryPostProcessor.class);
    ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, Object> beansWithAnnotation = configurableListableBeanFactory.getBeansWithAnnotation(CacheResult.class);
        if (!beansWithAnnotation.isEmpty()) {
            LOGGER.info("Have beans containing annotation @CacheResult");
            for (Map.Entry<String, Object> map : beansWithAnnotation.entrySet()) {
                System.out.println("Вывод мапы " + map);
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
