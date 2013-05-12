package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.DropGroupCO;
import com.h13.cardgame.cache.service.DropGroupCache;
import com.h13.cardgame.jupiter.dao.DropGroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-12
 * Time: 下午6:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DropGroupService {

    @Autowired
    DropGroupDAO dropGroupDAO;

    @Autowired
    DropGroupCache dropGroupCache;

    public DropGroupCO get(long dropGroupId) {
        DropGroupCO dg = dropGroupCache.get(dropGroupId);
        if (dg == null) {
            // load data from db
            dg = dropGroupDAO.get(dropGroupId);
            dropGroupCache.put(dg);
        }
        return dg;
    }

}
