package com.springboot.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.mapper.AdminMapper;
import com.springboot.mapper.AdminRoleMapper;
import com.springboot.model.Admin;
import com.springboot.model.AdminRole;
import com.springboot.service.AdminService;
import com.springboot.util.DateUtils;
import com.springboot.util.StringUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

	@Resource
	private AdminMapper adminMapper;
	@Resource
	private AdminRoleMapper adminRoleMapper;

	@Override
	public PageInfo<Admin> selectByPage(Admin admin, Integer pageNum, Integer pageSize) {
		Integer page = pageNum / pageSize + 1;
		Example example = new Example(Admin.class);
		Example.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotEmpty(admin.getUsername())) {
			criteria.andLike("username", "%" + admin.getUsername() + "%");
		}
		if (admin.getId() != null) {
			criteria.andEqualTo("id", admin.getId());
		}
		if (admin.getEnable() != null) {
			criteria.andEqualTo("enable", admin.getEnable());
		}
		// 分页查询
		PageHelper.startPage(page, pageSize);
		List<Admin> adminList = selectByExample(example);
		return new PageInfo<Admin>(adminList);
	}

	@Override
	public Admin selectByUsername(String username) {
		Example example = new Example(Admin.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("username", username);
		List<Admin> userList = selectByExample(example);
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void delAdmin(Integer adminId) {
		// 删除用户表
		mapper.deleteByPrimaryKey(adminId);
		// 删除用户角色表
		Example example = new Example(AdminRole.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("adminId", adminId);
		adminRoleMapper.deleteByExample(example);
	}

	@Override
	public PageInfo<Admin> selectByPage(Admin admin, Integer pageNum, Integer pageSize, String beginTime,
			String endTime) {
		Date beginDate = null, endDate = null;
		if (StringUtil.isNotEmpty(beginTime)) {
			beginDate = DateUtils.parseDate(beginTime);
		}
		if (StringUtil.isNotEmpty(endTime)) {
			endDate = DateUtils.parseDate(endTime);
		}
		// 分页查询
		PageHelper.startPage(pageNum, pageSize);
		List<Admin> adminList = adminMapper.selectAdminList(admin, beginDate, endDate);
		return new PageInfo<Admin>(adminList);
	}
}
