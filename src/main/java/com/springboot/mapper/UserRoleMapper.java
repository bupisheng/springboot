package com.springboot.mapper;

import java.util.List;

import com.springboot.model.UserRole;
import com.springboot.util.mapper.MyMapper;

public interface UserRoleMapper extends MyMapper<UserRole>{
	public List<Integer> findUserIdByRoleId(Integer roleId);
}
