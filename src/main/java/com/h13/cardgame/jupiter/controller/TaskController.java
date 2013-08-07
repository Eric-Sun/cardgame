package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.jupiter.exceptions.*;
import com.h13.cardgame.jupiter.service.TaskService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.jupiter.vo.TaskGroupVO;
import com.h13.cardgame.jupiter.vo.TaskVO;
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
    @Autowired
    TaskService taskService;

    /**
     * 完成任务
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/d")
    @ResponseBody
    public String d(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1;
        try {
            cid = new Long(request.getParameter("cid"));
            uid = new Long(request.getParameter("uid"));
            long taskId = new Long(request.getParameter("taskId"));
            List<Object> resultList = taskService.d(uid, cid, taskId);
            boolean flag = (Boolean) resultList.get(1);
            if (!flag) {
                // 需要获取下一个任务
                List<TaskVO> taskList = taskService.nextTask(uid, cid);
                return DTOUtils.getSucessResponse(request, response, uid, cid, resultList.get(0), taskList);
            }
            return DTOUtils.getSucessResponse(request, response, uid, cid, resultList.get(0));
        } catch (UserNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, UserNotExistsException.CODE);
        } catch (EnergyNotEnoughException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, EnergyNotEnoughException.CODE);
        } catch (TaskIsOverException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, TaskIsOverException.CODE);
        } catch (TaskCompletedTooManyException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, TaskCompletedTooManyException.CODE);
        } catch (TaskIsNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, TaskIsNotExistsException.CODE);
        } catch (TaskGroupIsNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, TaskGroupIsNotExistsException.CODE);
        } catch (EquipmentStorageIsFullException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, EquipmentStorageIsFullException.CODE);
        } catch (TaskIsCooldownException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, TaskIsCooldownException.CODE);
        } catch (SquardStorageIsFullException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, SquardStorageIsFullException.CODE);
        } catch (UserDontHaveThisCityException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, UserDontHaveThisCityException.CODE);
        } catch (CaptainStorageIsFullException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, CaptainStorageIsFullException.CODE);
        } catch (LevelIsTopException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, LevelIsTopException.CODE);
        }
    }

    @RequestMapping("")
    @ResponseBody
    public String task(HttpServletRequest request, HttpServletResponse response) {

        long cid = -1;
        long uid = -1;
        try {
            cid = new Long(request.getParameter("cid"));
            uid = new Long(request.getParameter("uid"));
            List<TaskVO> taskList = taskService.task(uid, cid);
            List<TaskGroupVO> taskGroupList = taskService.taskGroup(uid, cid);
            return DTOUtils.getSucessResponse(request, response, uid, cid, taskGroupList, taskList);
        } catch (UserNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, UserNotExistsException.CODE);
        } catch (TaskIsNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, TaskIsNotExistsException.CODE);
        } catch (TaskGroupIsNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, TaskGroupIsNotExistsException.CODE);
        } catch (UserDontHaveThisCityException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, UserDontHaveThisCityException.CODE);
        }
    }


}
