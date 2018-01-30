package com.springboot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mapper.RoleResourcesMapper;
import com.springboot.model.RoleResources;
import com.springboot.service.RoleResourcesService;

@Service
public class RoleResourcesServiceImpl extends BaseServiceImpl<RoleResources> implements RoleResourcesService {

	@Autowired
	private RoleResourcesMapper roleResourcesMapper;
	
	@Override
	public void deleteByResouceId(Integer resourceId) {
		roleResourcesMapper.deleteByResouceId(resourceId);
	}


}
