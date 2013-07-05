package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.jupiter.exceptions.MailExistedException;
import com.h13.cardgame.jupiter.exceptions.UserNameExistedException;
import com.h13.cardgame.jupiter.exceptions.UserNameOrPwdErrorException;
import com.h13.cardgame.jupiter.service.PassportService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: sunbo
 * Date: 13-3-17
 * Time: 下午9:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    PassportService passportService;

    @RequestMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response) {

        LogWriter.logRequest(request);
        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        try {
            long uid = passportService.register(mail, name, pwd);
            return DTOUtils.getOriginalResponse(uid, -1);
        } catch (UserNameExistedException e) {
            LogWriter.warn(LogWriter.PASSPORT_REGIETER, e);
            return DTOUtils.getFailureResponse(-1, -1, UserNameExistedException.CODE);
        } catch (MailExistedException e) {
            LogWriter.warn(LogWriter.PASSPORT_REGIETER, e);
            return DTOUtils.getFailureResponse(-1, -1, MailExistedException.CODE);
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String mail = request.getParameter("mail");
        String pwd = request.getParameter("pwd");
        long cid = -1;
        try {
            cid = passportService.login(mail, pwd);
            return DTOUtils.getOriginalResponse(-1, cid);
        } catch (UserNameOrPwdErrorException e) {
            LogWriter.warn(LogWriter.PASSPORT_LOGIN, e);
            return DTOUtils.getFailureResponse(-1, cid, UserNameOrPwdErrorException.CODE);
        }
    }

}
