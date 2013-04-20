package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.PackageCO;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.cache.service.PackageCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.core.dao.CardDAO;
import com.h13.cardgame.core.dao.PackageDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-14
 * Time: 上午1:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PackageLoaderService {
    private static Log LOG = LogFactory.getLog(PackageLoaderService.class);

    @Autowired
    PackageDAO packageDAO;
    @Autowired
    PackageCache packageCache;

    public void load() throws LoadException {
        try {
            List<PackageCO> list = packageDAO.getAll();
            for (PackageCO pck : list) {
                packageCache.put(pck);
                LOG.info("loaded package info. " + pck);
            }
            LOG.info("load package info successfully.");
        } catch (Exception e) {
            throw new LoadException("load package info error", e);
        }
    }
}
