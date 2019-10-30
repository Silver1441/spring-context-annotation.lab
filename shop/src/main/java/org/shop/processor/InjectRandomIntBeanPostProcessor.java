package org.shop.processor;

import org.shop.annotation.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            checkFieldForAnnotation(field, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private void checkFieldForAnnotation(Field field, Object bean) {
        if (field.isAnnotationPresent(InjectRandomInt.class)) {
            field.setAccessible(true);
            validateAnnotatedFieldWithType(field);
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            ReflectionUtils.setField(field, bean, getRandomInt(annotation.minValue(), annotation.maxValue()));
        }
    }

    private void validateAnnotatedFieldWithType(Field field) {
        if (field.getType() != int.class && field.getType() != long.class) {
            throw new IllegalArgumentException("the field under annotation must be int or long. Current type: "
                    + field.getType());
        }
    }

    private int getRandomInt(int minValue, int maxValue) {
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("maxValue must be greater than minValue");
        }
        return new Random().nextInt((maxValue - minValue) + 1) + minValue;
    }
}
