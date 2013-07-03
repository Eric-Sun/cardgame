package com.h13.cardgame.jupiter.interceptor;

import com.h13.cardgame.jupiter.service.CityService;
import com.h13.cardgame.jupiter.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-3
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class JupiterInterceptor implements HandlerInterceptor {

    @Autowired
    CityService cityService;
    @Autowired
    TaskService taskService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            long cid = new Long(httpServletRequest.getParameter("cid"));
            long uid = new Long(httpServletRequest.getParameter("uid"));
            cityService.flushEnergy(uid, cid);
            taskService.flushTaskStatus(uid, cid);
            return true;
        } catch (Exception e) {
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
