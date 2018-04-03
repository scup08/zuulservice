package com.lzh.zuulservice.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzh.common.model.dto.response.ObjectDataResponse;
import com.lzh.zuulservice.service.RefreshRouteService;

@RestController  
public class RefreshController {  
    @Autowired  
    RefreshRouteService refreshRouteService;  
    @Autowired  
    ZuulHandlerMapping zuulHandlerMapping;  
  
    @GetMapping("/refreshRoute")  
    public String refresh() {  
        refreshRouteService.refreshRoute();  
        return "refresh success";  
    }  
  
    @RequestMapping("/watchRoute")  
    public ObjectDataResponse<Set<String>> watchNowRoute() {  
        //可以用debug模式看里面具体是什么  
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();  
        
        
        
        Set<String> rs = handlerMap.keySet();
        
//        handlerMap.keySet().forEach(key -> rs.put(key,(ZuulController)handlerMap.get(key)));
        
        return new ObjectDataResponse<Set<String>>(rs);  
    }  
  
} 