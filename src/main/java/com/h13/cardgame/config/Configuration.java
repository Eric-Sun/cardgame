package com.h13.cardgame.config;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class Configuration {
    public static class CORE {
        public static String ENERGY_UP_S = "scheduler:energy_up_s";
    }

    public static class City {
        public static String LEVEL = "captain:level";
    }

    public static class CARD {
        public static String ATTACK_MIN_KEY = "attackMin";
        public static String ATTACK_MAX_KEY = "attackMax";
        public static String DEFENCE_MAX_KEY = "defenceMax";
        public static String DEFENCE_MIN_KEY = "defenceMin";
        public static String MAX_SLOT_KEY = "maxSlot";
        public static String MIN_SLOT_KEY = "minSlot";
        public static String E_CARD_ID_KEY = "eCardId";
        public static String SILVER_KEY = "silver";
        public static int E_CARD_ID_DEFAULT_VALUE = -1;
    }

    public static int ATTACK_TARGET_PAGE_SIZE = 10;

    public static class Troop {
        public static int INIT_ATTACK_MIN = 0;
        public static int INIT_ATTACK_MAX = 0;
        public static int INIT_DEFENCE_MAX = 0;
        public static int INIT_DEFENCE_MIN = 0;
    }

    public static class SQUARD {
        public static int DEFAULT_SQUARD_U_CARD_ID_VALUE = -1;
    }


    public static enum CACHE {
        QUEUE_CARD_KEY, QUEUE_CITY_KEY, QUEUE_CITYCARD_KEY,
        QUEUE_CONFIGURATION_KEY, QUEUE_DROPGROUP_KEY,
        QUEUE_LEVEL_KEY, QUEUE_SCHEDULER_KEY, QUEUE_STORAGE_KEY, QUEUE_TASK_KEY,
        QUEUE_TASKGROUP_KEY, QUEUE_TROOP_KEY
//        public  static  int QUEUE_CARD_KEY = 0;
//        public static int QUEUE_CITY_KEY = 1;
//        public static int QUEUE_CITYCARD_KEY = 2;
//        public static int QUEUE_CONFIGURATION_KEY = 3;
//        public static int QUEUE_DROPGROUP_KEY = 4;
//        public static int QUEUE_LEVEL_KEY = 5;
//        public static int QUEUE_SCHEDULER_KEY = 6;
//        public static int QUEUE_STORAGE_KEY = 7;
//        public static int QUEUE_TASK_KEY = 8;
//        public static int QUEUE_TASKGROUP_KEY = 9;
//        public static int QUEUE_TROOP_KEY = 10;

    }

}
