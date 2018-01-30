package com.springboot.service;

import com.springboot.model.RoleResources;

public interface RoleResourcesService extends BaseService<RoleResources>{

	void deleteByResouceId(Integer resourceId);
}
