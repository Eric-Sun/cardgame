package com.h13.cardgame.core.controller;

import com.h13.cardgame.core.exceptions.EnergyNotEnoughException;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.service.TaskService;
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
 * Date: 13-3-19
 * Time: 下午6:07
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    private static Log LOG = LogFactory.getLog(CaptainController.class);
    @Autowired
    TaskService taskService;

    @RequestMapping("/d")
    @ResponseBody
    public String d(HttpServletRequest request, HttpServletResponse response) {
        long cid = new Long(request.getParameter("cid"));
        long uid = new Long(request.getParameter("uid"));
        long taskId = new Long(request.getParameter("taskId"));
        try {
            taskService.d(cid, taskId);
            return DTOUtils.getOriginalResponse(uid, cid);
        } catch (ParameterIllegalException e) {
            LOG.error("", e);
            return DTOUtils.getFailureResponse(-1, cid, ParameterIllegalException.CODE);
        } catch (EnergyNotEnoughException e) {
            LOG.error("", e);
            return DTOUtils.getFailureResponse(-1, cid, EnergyNotEnoughException.CODE);
        }
    }
}
