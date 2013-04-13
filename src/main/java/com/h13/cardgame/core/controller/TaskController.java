package com.h13.cardgame.core.controller;

import com.h13.cardgame.core.exceptions.EnergyNotEnoughException;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.exceptions.RandomRewardException;
import com.h13.cardgame.core.exceptions.TaskIsOverException;
import com.h13.cardgame.core.service.TaskService;
import com.h13.cardgame.core.utils.DTOUtils;
import com.h13.cardgame.core.vo.TaskGroupVO;
import com.h13.cardgame.core.vo.TaskVO;
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
            List<Object> resultList = taskService.d(cid, taskId);
            boolean flag = (Boolean) resultList.get(1);
            if (!flag) {
                // 需要获取下一个任务
                List<TaskVO> taskList = taskService.nextTask(cid);
                return DTOUtils.getSucessResponse(uid, cid, resultList.get(0), taskList);
            }
            return DTOUtils.getSucessResponse(uid, cid, resultList.get(0));
        } catch (ParameterIllegalException e) {
            LOG.error("", e);
            return DTOUtils.getFailureResponse(-1, cid, ParameterIllegalException.CODE);
        } catch (EnergyNotEnoughException e) {
            LOG.error("", e);
            return DTOUtils.getFailureResponse(-1, cid, EnergyNotEnoughException.CODE);
        } catch (TaskIsOverException e) {
            LOG.error("", e);
            return DTOUtils.getFailureResponse(-1, cid, TaskIsOverException.CODE);  //To change body of catch statement use File | Settings | File Templates.
        } catch (RandomRewardException e) {
            LOG.error("", e);
            return DTOUtils.getFailureResponse(-1, cid, RandomRewardException.CODE);
        }
    }

    @RequestMapping("")
    @ResponseBody
    public String task(HttpServletRequest request, HttpServletResponse response) {

        long cid = new Long(request.getParameter("cid"));
        long uid = new Long(request.getParameter("uid"));
        try {
            List<TaskVO> taskList = taskService.task(cid);
            List<TaskGroupVO> taskGroupList = taskService.taskGroup(cid);
            return DTOUtils.getSucessResponse(uid, cid, taskGroupList, taskList);
        } catch (ParameterIllegalException e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(uid, cid, ParameterIllegalException.CODE);
        }
    }


}
