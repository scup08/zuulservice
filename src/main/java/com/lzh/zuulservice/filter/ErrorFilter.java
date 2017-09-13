package com.lzh.zuulservice.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ErrorFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
//		ZuulException exception = findZuulException(ctx.getThrowable());
//        Throwable throwable = ctx.getThrowable();
//        ZuulException exception = (ZuulException) throwable.getCause().getCause();
//        HttpServletResponse response = ctx.getResponse();
//        log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
        System.out.println("error ,,,,,,,,,,,,,,,,,,,,,,,,,");
        
//        ctx.set("error.status_code", HttpServletResponse.SC_SERVICE_UNAVAILABLE);
//        ctx.set("error.exception", throwable.getCause());
//        ctx.set("error.message", "lllllllllllllllll");
        ctx.getResponse().setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE) ;

//		log.warn("Error during filtering", exception);
//        response.setAttribute("javax.servlet.error.exception", new ZuulException("vvvvv", HttpServletResponse.SC_SERVICE_UNAVAILABLE, null));

//		if (StringUtils.hasText(exception.errorCause)) {
//        response.setAttribute("javax.servlet.error.message", "llllllllllllllzzzzzzza");
//		}
        return null;
	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 10;
	}
	ZuulException findZuulException(Throwable throwable) {
		if (throwable.getCause() instanceof ZuulRuntimeException) {
			// this was a failure initiated by one of the local filters
			return (ZuulException) throwable.getCause().getCause();
		}

		if (throwable.getCause() instanceof ZuulException) {
			// wrapped zuul exception
			return (ZuulException) throwable.getCause();
		}

		if (throwable instanceof ZuulException) {
			// exception thrown by zuul lifecycle
			return (ZuulException) throwable;
		}

		// fallback, should never get here
		return new ZuulException(throwable, HttpServletResponse.SC_SERVICE_UNAVAILABLE, null);
	}
}
