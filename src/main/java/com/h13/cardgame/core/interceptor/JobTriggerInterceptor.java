package com.h13.cardgame.core.interceptor;

import com.h13.cardgame.core.utils.WebApplicationContentHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-22
 * Time: 下午5:49
 * To change this template use File | Settings | File Templates.
 */
public class JobTriggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getParameter("cid") == null) {
//            return true;
//        }
//        long cid = new Long(request.getParameter("cid"));
//        SchedulerService service = WebApplicationContentHolder.getApplicationContext().getBean(SchedulerService.class);
//        service.attemptTrigger(cid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
