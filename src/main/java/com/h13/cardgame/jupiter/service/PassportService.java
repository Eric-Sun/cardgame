package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.jupiter.dao.UserDAO;
import com.h13.cardgame.jupiter.exceptions.MailExistedException;
import com.h13.cardgame.jupiter.exceptions.UserNameExistedException;
import com.h13.cardgame.jupiter.exceptions.UserNameOrPwdErrorException;
import com.h13.cardgame.jupiter.utils.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class PassportService {
    private Log LOG = LogFactory.getLog(PassportService.class);
    @Autowired
    UserDAO userDAO;

    public long register(String mail, String name, String pwd) throws UserNameExistedException, MailExistedException {
        String pwdAfterMd5 = MD5Util.getMD5String(pwd);
        long uid = userDAO.create(mail, name, pwdAfterMd5);
        LOG.info("passport|register|" + mail + "|" + name + "|" + pwd + "|" + uid);
        return uid;
    }


    public long login(String mail, String pwd) throws UserNameOrPwdErrorException {
        String pwdAfterMd5 = MD5Util.getMD5String(pwd);
        long uid = userDAO.login(mail, pwdAfterMd5);
        LOG.info("passport|login|" + mail + "|" + pwd + "|" + uid);
        return uid;
    }
}
