package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Role;

public interface RoleService extends BaseService<Role>{

	PageInfo<Role> selectByPage(Integer pageNum, Integer pageSize);
}
