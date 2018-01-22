package com.lzh.zuulservice.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class TestFilter extends ZuulFilter{

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest();  
  
        System.out.println(String.format("%s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));  
  
        String username = request.getParameter("username");// 获取请求的参数  
//        if(null != username && username.equals("chhliu")) {// 如果请求的参数不为空，且值为chhliu时，则通过  
//            ctx.setSendZuulResponse(true);// 对该请求进行路由  
//            ctx.setResponseStatusCode(200);  
//            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态  
//        }else{  
//            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由  
//            ctx.setResponseStatusCode(401);// 返回错误码  
//            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容  
//            ctx.set("isSuccess", false);  
//        }  
        
		System.out.println("pre filter!!!!!!!!!!!!!!!!!!!!!!!!");
//			throw new RuntimeException("hahaha");
//		try {
//		} catch(Exception e) {
//			ctx.set("error.status_code", HttpServletResponse.SC_BAD_GATEWAY);
//	        ctx.set("error.exception", e.getCause());
//	        ctx.set("error.message", "lllllllllllllllll");
//	        System.out.println("22222222222222222");
//		}
//        Throwable throwable = ctx.getThrowable();
//		ctx.set("error.status_code", HttpServletResponse.SC_BAD_GATEWAY);
//        ctx.set("error.exception", new RuntimeException());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}
	
}