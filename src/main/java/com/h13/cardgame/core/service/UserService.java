package com.h13.cardgame.core.service;

import com.h13.cardgame.core.dao.UserDAO;
import com.h13.cardgame.core.exceptions.UserNameExistedException;
import com.h13.cardgame.core.exceptions.UserNameOrPwdErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-17
 * Time: 下午9:46
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public long create(String name, String pwd) throws UserNameExistedException {
        return userDAO.create(name, pwd);
    }


    public long login(String name, String pwd) throws UserNameOrPwdErrorException {
        return userDAO.login(name, pwd);
    }
}
