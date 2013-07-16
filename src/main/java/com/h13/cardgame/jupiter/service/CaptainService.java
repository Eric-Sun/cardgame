package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.helper.CityCardHelper;
import com.h13.cardgame.jupiter.vo.CaptainCityCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptainService {

    @Autowired
    CityCardHelper cityCardHelper;

    /**
     * 获得一个captain卡
     *
     * @param cid
     * @param uid
     * @param captianCityCardId
     * @return
     * @throws CityCardNotExistsException
     */
    public CaptainCityCardVO get(long uid, long cid, long captianCityCardId)
            throws CityCardNotExistsException, CityCardIsNotYoursException {
        CityCardCO cityCard = cityCardHelper.get(cid, captianCityCardId);
        return toVO(cityCard);
    }

    private CaptainCityCardVO toVO(CityCardCO cityCard) {
        CaptainCityCardVO vo = new CaptainCityCardVO();
        vo.setCardId(cityCard.getCardId());
        vo.setDesc(cityCard.getDesc());
        vo.setIcon(cityCard.getIcon());
        vo.setId(cityCard.getId());
        vo.setName(cityCard.getName());
        return vo;
    }

}
