package ru.springcourse.homeworks.externalServiceApplication;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheResultMethodInterceptor implements MethodInterceptor, Advice {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheResultMethodInterceptor.class);

    private static final Map<String, Map<MethodArgs, Object>> CACHE = new ConcurrentHashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        final Method method = invocation.getMethod();
        boolean isAnnotationMethod = method.isAnnotationPresent(CacheResult.class);
        if (!isAnnotationMethod) {
            for (Method declaredMethod : invocation.getThis().getClass().getDeclaredMethods()) {
                if (method.getName().equals(declaredMethod.getName()) && AnnotationUtils.findAnnotation(declaredMethod, CacheResult.class) != null) {
                    isAnnotationMethod = true;
                    break;
                }
            }
        }
        if (isAnnotationMethod) {
            LOGGER.info("Method: {} annotated @CacheResult", method.getName());
            Map<MethodArgs, Object> methodArgsObjectMap = CACHE.get(method.getName());
            if (methodArgsObjectMap != null) {
                LOGGER.info("Method: {} has cache. Cache: {}", method.getName(), methodArgsObjectMap);
                final MethodArgs methodArgs = getMethodArgs(invocation.getArguments());
                LOGGER.info("Check cache result by method with args: {}({})", method.getName(), invocation.getArguments());
                Object result = methodArgsObjectMap.get(methodArgs);
                if (result != null) {
                    LOGGER.info("Return result from cache: method: {}({}), result: {}", method.getName(), invocation.getArguments(), result);
                    return result;
                } else {
                    LOGGER.info("Call original method and record result into cache");
                    result = invocation.proceed();
                    methodArgsObjectMap.put(methodArgs, result);
                    return result;
                }
            } else {
                LOGGER.info("Method: {} not cache.", method.getName());
                Object result = invocation.proceed();
                methodArgsObjectMap = new ConcurrentHashMap<>();
                methodArgsObjectMap.put(getMethodArgs(invocation.getArguments()), result);
                return result;
            }
        }
        return invocation.proceed();
    }

    private MethodArgs getMethodArgs(Object[] args) {
        LinkedList<Object> linkedArgs = new LinkedList<>();
        Collections.addAll(linkedArgs,args);
        return new MethodArgs(linkedArgs);
    }

    public static class MethodArgs {
        private final LinkedList<Object> args;

        public MethodArgs(LinkedList<Object> args) {
            this.args = args;
        }

        public LinkedList<Object> getArgs() {
            return args;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MethodArgs that = (MethodArgs) o;
            return args.equals(that.args);
        }

        @Override
        public int hashCode() {
            return args.hashCode();
        }

        @Override
        public String toString() {
            return "MethodArgs{" +
                    "args=" + args +
                    '}';
        }
    }
}
