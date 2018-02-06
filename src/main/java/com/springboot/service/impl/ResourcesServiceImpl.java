package com.springboot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.mapper.ResourcesMapper;
import com.springboot.model.Resources;
import com.springboot.service.ResourcesService;
import com.springboot.service.RoleResourcesService;
import com.springboot.util.StringUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesService {

	@Autowired
	private ResourcesMapper resourcesMapper;
	@Autowired
	private RoleResourcesService roleResourcesService;

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
	// @Cacheable(cacheNames = "resources", key =
	// "#map['adminId'].toString()+#map['type']")
	public List<Resources> loadAdminResources(Map<String, Object> map) {
		return resourcesMapper.loadAdminResources(map);
	}

	@Override
	public List<Resources> queryResourcesListWithSelected(Integer rid) {
		return resourcesMapper.queryResourcesListWithSelected(rid);
	}

	@Override
	public PageInfo<Resources> selectByPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Resources> list = resourcesMapper.queryAllWithParentName();
		return new PageInfo<Resources>(list);
	}

	@Override
	public List<Resources> queryAllMenus(Resources resources) {
		Example example = new Example(Resources.class);
		Example.Criteria criteria = example.createCriteria();
		if (resources.getType() != null) {
			criteria.andEqualTo("type", resources.getType());
		}
		List<Resources> list = selectByExample(example);
		return list;
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		if (ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				String id = ids[i];
				if (StringUtil.isNotEmpty(id)) {
					roleResourcesService.deleteByResouceId(Integer.parseInt(id));
					resourcesMapper.deleteByPrimaryKey(Integer.parseInt(id));
				}
			}
		}

	}

	@Override
	public Resources queryResourceWithParent(Resources resources) {
		
		return null;
	}

}
