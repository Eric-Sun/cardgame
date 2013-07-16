package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.CityCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午6:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CaptianHelper {

    @Autowired
    StorageHelper storageHelper;

    @Autowired
    CityCardHelper cityCardHelper;


}
