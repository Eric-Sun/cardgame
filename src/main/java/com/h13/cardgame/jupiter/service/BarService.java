package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.BarCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.exceptions.UserDontHaveThisCityException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.helper.BarHelper;
import com.h13.cardgame.jupiter.helper.CityCardHelper;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.vo.BarVO;
import com.h13.cardgame.jupiter.vo.CaptainCityCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BarService {

    @Autowired
    BarHelper barHelper;
    @Autowired
    CityCardHelper cityCardHelper;

    /**
     * 获得city的酒馆的信息
     *
     * @param uid
     * @param cid
     * @return
     */
    public BarVO show(long uid, long cid) throws CityCardNotExistsException, CityCardIsNotYoursException {
        BarCO bar = barHelper.show(cid);
        BarVO barVO = new BarVO();
        List<String> cardIdList = bar.getList();
        for (String idStr : cardIdList) {
            long cardId = new Long(idStr);
            CityCardCO cityCard = cityCardHelper.get(cid, cardId);
            CaptainCityCardVO vo = DTOUtils.toCaptianCtiyCardVO(cityCard);
            barVO.getList().add(vo);
        }
        return barVO;
    }


    public void recruit(long uid, long cid, long cardId) throws UserNotExistsException, UserDontHaveThisCityException {
        barHelper.recruit(uid, cid, cardId);
    }


}
