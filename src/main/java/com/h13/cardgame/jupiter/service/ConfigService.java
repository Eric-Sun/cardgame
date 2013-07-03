package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.service.ConfigurationCache;
import com.h13.cardgame.jupiter.dao.ConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取全局配置的相关信息
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
            confCache.putToQueue(confKey + "," + confValue);
        } else {
            confCache.get(confKey);
        }
        return confValue;
    }

}
