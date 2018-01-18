package com.springboot.mapper;

import java.util.List;

import com.springboot.model.AdminRole;
import com.springboot.util.mapper.MyMapper;

public interface AdminRoleMapper extends MyMapper<AdminRole>{
	public List<Integer> findAdminIdByRoleId(Integer roleId);
}
