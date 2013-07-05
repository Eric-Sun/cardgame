package com.h13.cardgame.jupiter.controller;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.jupiter.exceptions.CityExistsException;
import com.h13.cardgame.jupiter.exceptions.UserDontHaveThisCityException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.service.CityService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@RequestMapping("/city")
public class CityController {
    private static Log LOG = LogFactory.getLog(CityController.class);
    @Autowired
    CityService cityService;

    @RequestMapping("")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1;
        try {
            cid = new Long(request.getParameter("cid"));
            uid = new Long(request.getParameter("uid"));
            CityCO city = cityService.get(uid, cid);
            return DTOUtils.getSucessResponse(uid, cid, city);
        } catch (UserNotExistsException e) {
            LogWriter.warn(LogWriter.CITY, e);
            return DTOUtils.getFailureResponse(-1, cid, UserNotExistsException.CODE);
        } catch (UserDontHaveThisCityException e) {
            LogWriter.warn(LogWriter.CITY, e);
            return DTOUtils.getFailureResponse(-1, cid, UserDontHaveThisCityException.CODE);
        }
    }

    @RequestMapping("/create")
    @ResponseBody
    public String create(HttpServletRequest request, HttpServletResponse response) {
        long uid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            String name = request.getParameter("name");
            CityCO city = cityService.create(uid, name);
            return DTOUtils.getSucessResponse(uid, city.getId(), JSON.toJSONString(city));
        } catch (UserNotExistsException e) {
            LogWriter.warn(LogWriter.CITY, e);
            return DTOUtils.getFailureResponse(uid, -1, UserNotExistsException.CODE);
        } catch (CityExistsException e) {
            LogWriter.warn(LogWriter.CITY, e);
            return DTOUtils.getFailureResponse(uid, -1, CityExistsException.CODE);
        }
    }


}
