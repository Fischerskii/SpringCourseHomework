package ru.springcourse.homeworks.firstHomeWrok;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import ru.springcourse.homeworks.firstHomeWrok.impl.ExternalServiceImpl;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class CacheResultBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheResultBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("postProcessAfterInitialization: beanName: {}", beanName);
        if (bean instanceof ExternalServiceImpl) {
            LOGGER.info("Bean is ExternalServiceImpl class");
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvice(new MethodInterceptor() {
                @Override
                public Object invoke(MethodInvocation invocation) throws Throwable {
                    LOGGER.info("Before call method in CacheResult");
                    Object proceed = invocation.proceed();
                    if (invocation.getMethod().isAnnotationPresent(CacheResult.class)) {
                        LOGGER.warn("Call method, result of which need to be encrypted");
                        return encrypt(proceed);
                    }
                    return proceed;
                }
            });
            return proxyFactory.getProxy();
        }
        return bean;
    }
    private Object encrypt(Object proceed) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        cipher.init(Cipher.ENCRYPT_MODE, keyGenerator.generateKey());
        byte[] bytes = cipher.doFinal(SerializationUtils.serialize(proceed));
        return Base64.getEncoder().encodeToString(bytes);
    }
}

