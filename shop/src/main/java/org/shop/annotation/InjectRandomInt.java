package org.shop.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface InjectRandomInt {
    int minValue();
    int maxValue();
}
