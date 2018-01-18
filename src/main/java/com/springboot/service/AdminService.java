package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Admin;

public interface AdminService extends BaseService<Admin> {

	PageInfo<Admin> selectByPage(Admin admin, int start, int length);

	Admin selectByUsername(String username);

	void delAdmin(Integer adminId);
}
