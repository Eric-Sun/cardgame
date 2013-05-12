package com.h13.cardgame.jupiter.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebApplicationContentHolder {
	
	private static ServletContext ctxt = null;
	
	private WebApplicationContentHolder(){
		
	}
	public static void setServletContext(ServletContext context){
		WebApplicationContentHolder.ctxt = context;
	}
	
	public static ApplicationContext getApplicationContext(){
		return WebApplicationContextUtils.getWebApplicationContext(ctxt);  
	}

}
