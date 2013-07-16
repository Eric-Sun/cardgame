package com.h13.cardgame.jupiter.controller;

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
        try{
            CaptainCityCardVO captainCityCard = captainService.get(uid, cid, captainCityCardId) ;
            return DTOUtils.getSucessResponse(uid, cid, captainCityCard);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(-1, cid, SilverNotEnoughException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(-1, cid, SilverNotEnoughException.CODE);
        }
    }

}
