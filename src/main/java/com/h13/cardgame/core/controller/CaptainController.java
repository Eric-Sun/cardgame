package com.h13.cardgame.core.controller;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.core.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午6:06
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/captain")
public class CaptainController {
    @Autowired
    CaptainService captainService;

    @RequestMapping("/")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) {
        long cid = new Long(request.getParameter("cid"));
        CaptainCO captain = captainService.get(cid);
        return JSON.toJSONString(captain);
    }

}
