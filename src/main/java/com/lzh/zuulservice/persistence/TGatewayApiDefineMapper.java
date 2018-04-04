package com.lzh.zuulservice.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzh.common.annotation.MyBatisRepository;
import com.lzh.common.persistence.CrudMapper;
import com.lzh.zuulservice.model.entity.TGatewayApiDefine;

@MyBatisRepository
public interface TGatewayApiDefineMapper extends CrudMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TGatewayApiDefine record);

    int insertSelective(TGatewayApiDefine record);

    TGatewayApiDefine selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TGatewayApiDefine record);

    int updateByPrimaryKey(TGatewayApiDefine record);
    
    List<TGatewayApiDefine> selectAll(@Param("offset") int offset, @Param("limited") int limited);
    
}