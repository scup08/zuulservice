package com.lzh.zuulservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzh.common.model.dto.response.ObjectCollectionResponse;
import com.lzh.zuulservice.model.dto.request.ZuulRouteRequest;
import com.lzh.zuulservice.model.entity.TGatewayApiDefine;
import com.lzh.zuulservice.service.RefreshRouteService;

@RestController  
public class RefreshController {  
    @Autowired  
    RefreshRouteService refreshRouteService;  
//    @Autowired  
//    ZuulHandlerMapping zuulHandlerMapping; 
  
    @GetMapping("/refreshRoute")  
    public String refresh() {  
        refreshRouteService.refreshRoute();  
        return "refresh success";  
    }  
  
    @RequestMapping("/watchRoute")  
    public ObjectCollectionResponse<TGatewayApiDefine> watchNowRoute(ZuulRouteRequest zuulRouteRequest) {  
        //可以用debug模式看里面具体是什么  
//        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();  
        
    	List<TGatewayApiDefine> list = refreshRouteService.selectAll(zuulRouteRequest);
        
//        Set<String> rs = handlerMap.keySet();
        
//        handlerMap.keySet().forEach(key -> rs.put(key,(ZuulController)handlerMap.get(key)));
        
//        return new ObjectDataResponse<Set<String>>(rs);  
    	return new ObjectCollectionResponse<TGatewayApiDefine>(list);
    }  
  
} 