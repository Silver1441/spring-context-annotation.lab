package org.shop.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectRandomInt.class)) {
                field.setAccessible(true);
                InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);

                ReflectionUtils.setField(field, bean, getRandomInt(annotation.minValue(), annotation.maxValue()));
            }
        }

        return bean;
    }

    private int getRandomInt(int minValue, int maxValue) {
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return new Random().nextInt((maxValue - minValue) + 1) + minValue;
    }
}
