package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.UnitsCardCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.jupiter.helper.CardHelper;
import com.h13.cardgame.jupiter.utils.DataUtils;
import com.h13.cardgame.jupiter.vo.UnitsCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-19
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CommonService {

    @Autowired
    CardHelper cardHelper;

    public List<UnitsCardVO> getAllUnitsCards() throws LoadException {
        List<UnitsCardCO> list = cardHelper.getAllUnitsCards();
        List<UnitsCardVO> returnList = new LinkedList<UnitsCardVO>();
        for (UnitsCardCO co : list) {
            UnitsCardVO vo = new UnitsCardVO();
            vo.setId(co.getId());
            vo.setIcon(co.getIcon());
            vo.setName(co.getName());
            vo.setDesc(co.getDesc());
            vo.setAttackMax(DataUtils.getIntData(co.getSpecData(), Configuration.CARD.ATTACK_MAX_KEY));
            vo.setAttackMin(DataUtils.getIntData(co.getSpecData(), Configuration.CARD.ATTACK_MIN_KEY));
            vo.setDefenceMax(DataUtils.getIntData(co.getSpecData(), Configuration.CARD.DEFENCE_MAX_KEY));
            vo.setDefenceMin(DataUtils.getIntData(co.getSpecData(), Configuration.CARD.DEFENCE_MIN_KEY));
            vo.setSilver(DataUtils.getIntData(co.getSpecData(), Configuration.CARD.SILVER_KEY));
            vo.seteCardId(DataUtils.getIntData(co.getSpecData(), Configuration.CARD.E_CARD_ID_KEY));
            returnList.add(vo);
        }
        Collections.sort(returnList);
        return returnList;
    }

}
