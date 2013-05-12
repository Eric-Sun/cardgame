package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.jupiter.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-20
 * Time: 下午10:54
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UserHelper {

    @Autowired
    UserDAO userDAO;

    /**
     * 检查uid存在与否，如果true存在，false不存在
     * @param uid
     * @return
     */
    public boolean check(long uid) {
        return userDAO.check(uid);
    }

}
