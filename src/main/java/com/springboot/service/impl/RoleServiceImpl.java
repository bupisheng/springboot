package com.springboot.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.mapper.RoleMapper;
import com.springboot.model.Role;
import com.springboot.service.RoleService;


@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public PageInfo<Role> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectAll();
		return new PageInfo<Role>(list);
	}


}
