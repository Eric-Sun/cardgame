package com.h13.cardgame.core.controller;

import com.h13.cardgame.core.exceptions.UserNameExistedException;
import com.h13.cardgame.core.service.UserService;
import com.h13.cardgame.core.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-17
 * Time: 下午9:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userSerivce;

    @RequestMapping("/create")
    @ResponseBody
    public String create(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        try {
            long uid = userSerivce.create(name, pwd);
            return DTOUtils.getOriginalResponse(uid, -1);
        } catch (UserNameExistedException e) {
            return DTOUtils.getFailureResponse(-1, -1, UserNameExistedException.CODE);
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

}
