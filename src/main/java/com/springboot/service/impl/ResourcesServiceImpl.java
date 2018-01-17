package com.springboot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.mapper.ResourcesMapper;
import com.springboot.model.Resources;
import com.springboot.service.ResourcesService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesService {

	@Autowired
	private ResourcesMapper resourcesMapper;

	@Override
	public PageInfo<Resources> selectByPage(Resources resources, int start, int length) {
		int page = start / length + 1;
		Example example = new Example(Resources.class);
		// 分页查询
		PageHelper.startPage(page, length);
		List<Resources> userList = selectByExample(example);
		return new PageInfo<>(userList);
	}

	@Override
	public List<Resources> queryAll() {
		return resourcesMapper.queryAll();
	}

	@Override
	@Cacheable(cacheNames = "resources", key = "#map['userId'].toString()+#map['type']")
	public List<Resources> loadUserResources(Map<String, Object> map) {
		return resourcesMapper.loadUserResources(map);
	}

	@Override
	public List<Resources> queryResourcesListWithSelected(Integer rid) {
		return resourcesMapper.queryResourcesListWithSelected(rid);
	}

}
