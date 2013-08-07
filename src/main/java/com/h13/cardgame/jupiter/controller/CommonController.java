package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.jupiter.service.CommonService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.vo.UnitsCardVO;
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
 * Date: 13-7-19
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    CommonService commonService;

    @RequestMapping("/unitsCards")
    @ResponseBody
    public String units(HttpServletRequest request, HttpServletResponse response) throws LoadException {
        List<UnitsCardVO> list = commonService.getAllUnitsCards();
        return DTOUtils.getSucessResponse(request, response, -1, -1, list);
    }


}
