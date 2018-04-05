package com.lzh.zuulservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.lzh.zuulservice.model.dto.request.ZuulRouteRequest;
import com.lzh.zuulservice.model.entity.TGatewayApiDefine;
import com.lzh.zuulservice.persistence.TGatewayApiDefineMapper;  
  
@Service  
public class RefreshRouteService {  
    @Autowired  
    ApplicationEventPublisher publisher;  
  
    @Autowired  
    RouteLocator routeLocator;  
    
    @Autowired  
    TGatewayApiDefineMapper tGatewayApiDefineMapper;  
  
    public void refreshRoute() {  
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);  
        publisher.publishEvent(routesRefreshedEvent);  
    }  
    
    public List<TGatewayApiDefine> selectAll(ZuulRouteRequest zuulRouteRequest){
    	
    	List<TGatewayApiDefine> list = tGatewayApiDefineMapper.selectAll(zuulRouteRequest.getOffset(), zuulRouteRequest.getLimited());
    	
    	return list;
    }
}  