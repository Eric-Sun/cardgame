package com.h13.cardgame.core.helper;

import com.h13.cardgame.cache.co.PackageCO;
import com.h13.cardgame.core.dao.PackageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-21
 * Time: 上午12:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PackageHelper {

    @Autowired
    PackageDAO packageDAO;

    public PackageCO getByCid(long cid) {
        return packageDAO.getByCid(cid);
    }

}
