package com.h13.cardgame.core.controller;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.exceptions.ServerErrorException;
import com.h13.cardgame.core.service.CaptainService;
import com.h13.cardgame.core.utils.DTOUtils;
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
@RequestMapping("/captain")
public class CaptainController {
    private static Log LOG = LogFactory.getLog(CaptainController.class);
    @Autowired
    CaptainService captainService;

    @RequestMapping("")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        try {
            cid = new Long(request.getParameter("cid"));
            CaptainCO captain = captainService.get(cid);
            return JSON.toJSONString(captain);
        } catch (ParameterIllegalException e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ParameterIllegalException.CODE);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
        }
    }

    @RequestMapping("/create")
    @ResponseBody
    public String create(HttpServletRequest request, HttpServletResponse response) {
        long uid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            String name = request.getParameter("name");
            CaptainCO captain = captainService.create(uid, name);
            return DTOUtils.getSucessResponse(uid, captain.getId(), JSON.toJSONString(captain));
        } catch (ParameterIllegalException e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(uid, -1, ParameterIllegalException.CODE);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(uid, -1, ServerErrorException.CODE);
        }
    }


}
