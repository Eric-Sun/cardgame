package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.jupiter.exceptions.CaptainCityCardLevelIsTopException;
import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.exceptions.SilverNotEnoughException;
import com.h13.cardgame.jupiter.service.CaptainService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.jupiter.vo.CaptainCityCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/captain")
public class CaptainController {

    @Autowired
    CaptainService captainService;

    @RequestMapping("")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1;
        long captainCityCardId = -1;
        try {
            cid = new Long(request.getParameter("cid"));
            uid = new Long(request.getParameter("uid"));
            captainCityCardId = new Long(request.getParameter("captainCityCardId"));
            CaptainCityCardVO captainCityCard = captainService.get(uid, cid, captainCityCardId);
            return DTOUtils.getSucessResponse(request, response, uid, cid, captainCityCard);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, SilverNotEnoughException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, SilverNotEnoughException.CODE);
        }
    }

    @RequestMapping("/upgradeLevel")
    public String upgradeLevel(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1;
        try {
            long captainCityCardId = new Long(request.getParameter("captainCityCardId"));
            String otherCaptainCityCardIdArray = request.getParameter("otherCaptainCityCardIdArray");
            captainService.upgradeCaptainLevel(uid, cid, captainCityCardId, otherCaptainCityCardIdArray);
            return DTOUtils.getOriginalResponse(request, response, uid, cid);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, CityCardIsNotYoursException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, CityCardNotExistsException.CODE);
        } catch (CaptainCityCardLevelIsTopException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, CaptainCityCardLevelIsTopException.CODE);
        }
    }

}
