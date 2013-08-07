package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.jupiter.exceptions.*;
import com.h13.cardgame.jupiter.service.SquardService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-24
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/squard")
public class SquardController {
    @Autowired
    SquardService squardService;

    @RequestMapping("/onCaptain")
    @ResponseBody
    public String onCaptain(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1;
        try {
            cid = new Long(request.getParameter("cid"));
            uid = new Long(request.getParameter("uid"));
            long squardCityCardId = new Long(request.getParameter("squardCityCardId"));
            long captainCityCardId = new Long(request.getParameter("captainCityCardId"));
            squardService.onCaptain(uid, cid, squardCityCardId, captainCityCardId);
            return DTOUtils.getOriginalResponse(request, response, uid, cid);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.PASSPORT_REGIETER, e);
            return DTOUtils.getFailureResponse(request, response, -1, -1, CityCardIsNotYoursException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.PASSPORT_REGIETER, e);
            return DTOUtils.getFailureResponse(request, response, -1, -1, CityCardNotExistsException.CODE);
        } catch (SquardHaveCaptainException e) {
            LogWriter.warn(LogWriter.PASSPORT_REGIETER, e);
            return DTOUtils.getFailureResponse(request, response, -1, -1, SquardHaveCaptainException.CODE);
        }
    }


    @RequestMapping("/offCaptain")
    @ResponseBody
    public String offCaptain(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1;
        try {
            cid = new Long(request.getParameter("cid"));
            uid = new Long(request.getParameter("uid"));
            long squardCityCardId = new Long(request.getParameter("squardCityCardId"));
            squardService.offCaptain(uid, cid, squardCityCardId);
            return DTOUtils.getOriginalResponse(request, response, uid, cid);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.PASSPORT_REGIETER, e);
            return DTOUtils.getFailureResponse(request, response, -1, -1, CityCardIsNotYoursException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.PASSPORT_REGIETER, e);
            return DTOUtils.getFailureResponse(request, response, -1, -1, CityCardNotExistsException.CODE);
        } catch (SquardDontHaveCaptainException e) {
            LogWriter.warn(LogWriter.PASSPORT_REGIETER, e);
            return DTOUtils.getFailureResponse(request, response, -1, -1, SquardDontHaveCaptainException.CODE);
        }
    }

}
