package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Admin;

public interface AdminService extends BaseService<Admin> {

	PageInfo<Admin> selectByPage(Admin admin, Integer pageNum, Integer pageSize);

	PageInfo<Admin> selectByPage(Admin admin, Integer pageNum, Integer pageSize, String beginTime, String endTime);

	Admin selectByUsername(String username);

	void delAdmin(String adminId);
	
	void save(Admin admin,String roleId);
}
