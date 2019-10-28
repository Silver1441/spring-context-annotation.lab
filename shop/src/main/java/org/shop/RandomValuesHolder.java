package org.shop;

import org.shop.annotation.InjectRandomInt;

public class RandomValuesHolder {

    @InjectRandomInt(minValue = 0, maxValue = 10)
    private int value1;

    @InjectRandomInt(minValue = 5, maxValue = 8)
    private int value2;

    @InjectRandomInt(minValue = 1, maxValue = 99)
    private int value3;

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getValue3() {
        return value3;
    }
}
