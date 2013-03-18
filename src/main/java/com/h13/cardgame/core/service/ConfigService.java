package com.h13.cardgame.core.service;

import com.h13.cardgame.cache.service.ConfigurationCache;
import com.h13.cardgame.core.dao.ConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ConfigService {

    @Autowired
    ConfigurationCache confCache;
    @Autowired
    ConfigDAO confDAO;

    public String get(String confKey) {
        String confValue = confCache.get(confKey);
        if (confValue == null) {
            confValue = confDAO.get(confKey).getValue();
            confCache.put(confKey, confValue);
        } else {
            confCache.get(confKey);
        }
        return confValue;
    }

}
