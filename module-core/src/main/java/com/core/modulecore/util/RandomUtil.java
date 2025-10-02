package com.core.modulecore.util;

import java.util.Random;

public class RandomUtil {

    private final static Random random = new Random();


    public static int getRandomInt() {
        return random.nextInt(Integer.MAX_VALUE);
    }

    public static long getRandomLong() {
        return random.nextLong(Long.MAX_VALUE);
    }

}
