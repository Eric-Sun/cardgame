package com.h13.cardgame.config;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class Configuration {
    public static class Scheduler {
        public static String ENERGY_UP_S = "scheduler:energy_up_s";
    }

    public static class Core {
        public static String RECUIT_SILVER_1 = "core:recuit_silver_1";
    }

    public static class Captain {
        public static String LEVEL = "captain:level";
    }

    public static int ATTACK_TARGET_PAGE_SIZE = 10;

    public static class Troop {
        public static int INIT_ATTACK_MIN = 0;
        public static int INIT_ATTACK_MAX = 0;
        public static int INIT_DEFENCE_MAX = 0;
        public static int INIT_DEFENCE_MIN = 0;
    }
}
