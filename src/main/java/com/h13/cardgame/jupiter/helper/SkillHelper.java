package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.SkillCO;
import com.h13.cardgame.cache.service.SkillCache;
import com.h13.cardgame.jupiter.dao.CityCardDAO;
import com.h13.cardgame.jupiter.dao.SkillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-25
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SkillHelper {

    @Autowired
    SkillCache skillCache;
    @Autowired
    SkillDAO skillDAO;

    public SkillCO get(long skillId){
        SkillCO skillCO = skillCache.get(skillId);
        if(skillCO ==null){
            skillCO = skillDAO.get(skillId);
        }
        return skillCO;
    }


}
