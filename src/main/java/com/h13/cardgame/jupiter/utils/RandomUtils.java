package com.h13.cardgame.jupiter.utils;

import com.h13.cardgame.cache.co.CardRewardItemCO;
import com.h13.cardgame.cache.co.CommonRewardItemCO;
import com.h13.cardgame.cache.co.DropGroupDataCO;

import java.util.List;
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

    /**
     * 随机掉落item
     *
     * @param item
     * @return
     */
    public static  int randomCommonItem(CommonRewardItemCO item) {
        if (!item.isDrop())
            return 0;
        if (item.getMax() == item.getMin())
            return item.getMax();
        Random random = new Random();
        int v = random.nextInt(item.getMax() - item.getMin());
        return item.getMax() + v;
    }

    public static  long randomCard(List<CardRewardItemCO> cardDropList,int weightSum) {
        int random = new Random().nextInt(weightSum);
        int v = 0;
        for (CardRewardItemCO cardItem : cardDropList) {
            v += cardItem.getWeight();
            if (random < v)
                return cardItem.getCardId();
        }
        return -1;
    }

}
