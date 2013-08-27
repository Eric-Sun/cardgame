package com.h13.cardgame.config;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class Configuration {
    public static class CONFIG {
        public static String ENERGY_UP_S = "energy_up_s";
        public static String BAR_RANDOM_GROUP = "bar_random_group";
        public static String CAPTAIN_INIT_TITLE = "captain.title.init";
        public static String CAPTAIN_INIT_TITLE_EXP = "captain.titleExp.init";
        public static String CAPTAIN_INIT_LEVEL = "captain.level.init";
        public static String CAPTAIN_INIT_LEVEL_EXP = "captain.levelExp.init";
        public static String BAR_FLUSH_HOUR = "bar.flush.hour";
        public static String BOARD_WORLD_CACHE_LIST_SIZE = "board.world.cache.list.size";
        public static String BOARD_WORLD_SHOW_SIZE = "board.world.show.size";
    }

    public static class City {
        public static int DEFAULT_CITY_BAR_SIZE_VALUE = 5;
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

    public static class CITY_CARD {
        public static String ATTACK_MAX_KEY = "attackMax";
        public static String ATTACK_MIN_KEY = "attackMin";
        public static String DEFENCE_MAX_KEY = "defenceMax";
        public static String DEFENCE_MIN_KEY = "defenceMin";
        public static String MAX_SLOT_KEY = "maxSlot";
        public static String CUR_SLOT_KEY = "curSlot";
        public static String U_CARD_ID_KEY = "uCardId";
    }

    public static class CAPTAIN_CITY_CARD {
        public static String LEVEL_KEY = "level";
        public static String LEVEL_EXP_KEY = "levelExp";
        public static String TITLE_KEY = "title";
        public static String TITLE_EXP_KEY = "titleExp";
        public static String SKILL_ID_KEY = "skillId";
        public static int DEFAULT_SKILL_ID_VALUE = 0;
        public static String ATTACK_MAX_KEY = "attackMax";
        public static String ATTACK_MIN_KEY = "attackMin";
        public static String DEFENCE_MAX_KEY = "defenceMax";
        public static String DEFENCE_MIN_KEY = "defenceMin";
    }

    public static int ATTACK_TARGET_PAGE_SIZE = 10;

    public static class Troop {
        public static int INIT_ATTACK_MIN = 0;
        public static int INIT_ATTACK_MAX = 0;
        public static int INIT_DEFENCE_MAX = 0;
        public static int INIT_DEFENCE_MIN = 0;
    }

    public static class SQUAD_CITY_CARD {
        public static int DEFAULT_SQUAD_U_CARD_ID_VALUE = -1;
        public static String CAPTAIN_ID_KEY = "captainId";
        public static int DEFAULT_CAPTAIN_ID_VALUE = 0;
        public static String CAPTAIN_ATTACK_MAX_KEY = "captainAttackMax";
        public static String CAPTAIN_ATTACK_MIN_KEY = "captainAttackMin";
        public static String CAPTAIN_DEFENCE_MAX_KEY = "captainDefenceMax";
        public static String CAPTAIN_DEFENCE_MIN_KEY = "captainDefenceMin";
        public static int CAPTAIN_INIT_ATTACK_MAX = 0;
        public static int CAPTAIN_INIT_ATTACK_MIN = 0;
        public static int CAPTAIN_INIT_DEFENCE_MAX = 0;
        public static int CAPTAIN_INIT_DEFENCE_MIN = 0;
    }

    public static class SKILL {
        public static int TARGET_TYPE_CAVALRY = 0;
        public static int TARGET_TYPE_BOWMAN = 1;
        public static int TARGET_TYPE_INFANTRY = 2;
        public static String TARGET_TYPE_KEY = "type";
        public static String TARGET_TYPE_BASE = "base";
    }


}
