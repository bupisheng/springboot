package com.springboot.mapper;

import org.apache.ibatis.annotations.Param;

import com.springboot.model.RoleResources;
import com.springboot.util.mapper.MyMapper;

public interface RoleResourcesMapper extends MyMapper<RoleResources>{

	void deleteByResouceId(@Param("resourceId")Integer resourceId);
}
