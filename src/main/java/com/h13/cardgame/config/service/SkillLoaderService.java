package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.SkillCO;
import com.h13.cardgame.cache.service.SkillCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.jupiter.dao.SkillDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SkillLoaderService {

    private static Log LOG = LogFactory.getLog(SkillLoaderService.class);

    @Autowired
    SkillDAO skillDAO;
    @Autowired
    SkillCache skillCache;

    public void load() throws LoadException {
        try {
            List<SkillCO> list = skillDAO.getAll();
            for (SkillCO skill : list) {
                skillCache.put(skill);
                LOG.info("loaded skill info. " + skill);
            }
            LOG.info("load skill info successfully.");
        } catch (Exception e) {
            throw new LoadException("load skill info error", e);
        }
    }

}
