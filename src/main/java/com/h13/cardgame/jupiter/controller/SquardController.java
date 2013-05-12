package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.exceptions.ServerErrorException;
import com.h13.cardgame.jupiter.service.SquardService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.vo.AttackTargetVO;
import com.h13.cardgame.jupiter.vo.CombatAttributesVO;
import com.h13.cardgame.jupiter.vo.SimpleSquardVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-20
 * Time: 下午11:09
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/squard")
public class SquardController {
    private static Log LOG = LogFactory.getLog(SquardController.class);
    @Autowired
    SquardService squardService;

    @RequestMapping("/searchAttackTarget")
    @ResponseBody
    public String search(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        int pageNum = 1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            if (request.getParameter("pageNum") != null)
                pageNum = new Integer(request.getParameter("pageNum"));
            cid = new Long(request.getParameter("cid"));
            List<AttackTargetVO> list = squardService.searchAttackTarget(cid, pageNum);
            return DTOUtils.getSucessResponse(uid, cid, list);
        } catch (UserNotExistsException e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, UserNotExistsException.CODE);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
        }
    }


    @RequestMapping("/simpleList")
    @ResponseBody
    public String simpleList(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            SimpleSquardVO simpleSquard = squardService.getSimpleSquard(cid);
            return DTOUtils.getSucessResponse(uid, cid, simpleSquard);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
        }
    }

    @RequestMapping("/largeList")
    @ResponseBody
    public String largeList(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            SimpleSquardVO simpleSquard = squardService.getLargeSquard(cid);
            return DTOUtils.getSucessResponse(uid, cid, simpleSquard);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
        }
    }

    @RequestMapping("/addMember")
    @ResponseBody
    public String addMember(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            long ccId = new Long(request.getParameter("ccId"));
            squardService.addMember(cid, ccId);
            return DTOUtils.getOriginalResponse(uid, cid);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
        }
    }

    @RequestMapping("/getCombatAttributes")
    @ResponseBody
    public String getCombatAttributes(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            CombatAttributesVO ca = squardService.getCombatAttributes(cid);
            return DTOUtils.getSucessResponse(uid, cid, ca);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
        }
    }


}
