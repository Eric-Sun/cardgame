package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.exceptions.UserDontHaveThisCityException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.service.BarService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.jupiter.vo.BarVO;
import com.h13.cardgame.jupiter.vo.CaptainCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/bar")
public class BarController {

    @Autowired
    BarService barService;

    /**
     * 酒馆的队长列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("")
    @ResponseBody
    public String show(HttpServletRequest request, HttpServletResponse response) {
        long uid = -1;
        long cid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            BarVO bar = barService.show(uid, cid);
            return DTOUtils.getSucessResponse(request, response, uid, cid, bar);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, CityCardIsNotYoursException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, CityCardNotExistsException.CODE);
        } catch (UserDontHaveThisCityException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, UserDontHaveThisCityException.CODE);
        } catch (UserNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, UserNotExistsException.CODE);
        }
    }


    /**
     * 酒馆中captian的详细信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/captain")
    @ResponseBody
    public String captain(HttpServletRequest request, HttpServletResponse response) {
        long uid = -1;
        long cid = -1;
        uid = new Long(request.getParameter("uid"));
        cid = new Long(request.getParameter("cid"));
        long cardId = new Long(request.getParameter("cardId"));
        CaptainCardVO card = barService.getCaptainCard(uid, cid, cardId);
        return DTOUtils.getSucessResponse(request, response, uid, cid, card);
    }


    @RequestMapping("/recruit")
    @ResponseBody
    public String recruit(HttpServletRequest request, HttpServletResponse response) {

        long uid = -1;
        long cid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            long cardId = new Long(request.getParameter("cardId"));
            barService.recruit(uid, cid, cardId);
            return DTOUtils.getOriginalResponse(request, response, uid, cid);
        } catch (UserDontHaveThisCityException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, CityCardIsNotYoursException.CODE);
        } catch (UserNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, CityCardIsNotYoursException.CODE);
        }
    }
}
