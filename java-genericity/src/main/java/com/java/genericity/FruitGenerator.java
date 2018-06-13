package com.java.genericity;

import java.util.Random;

/**
 * Created by liwenchao on 2018-05-25.
 *
 * @author liwenchao
 */
public class FruitGenerator implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random random = new Random();
        return fruits[random.nextInt(3)];
    }
}
