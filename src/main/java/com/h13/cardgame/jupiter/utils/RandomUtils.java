package com.h13.cardgame.jupiter.utils;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-6-28
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class RandomUtils {

    /**
     * 用于在min和max之间随机
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max) {
        Random random = new Random();
        int r = random.nextInt(max - min);
        return min + r;
    }

}
