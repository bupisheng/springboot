package com.springboot.mapper;

import java.util.List;
import java.util.Map;

import com.springboot.model.Resources;
import com.springboot.util.mapper.MyMapper;

public interface ResourcesMapper extends MyMapper<Resources> {

	List<Resources> queryAll();

	List<Resources> loadAdminResources(Map<String, Object> map);

	List<Resources> queryResourcesListWithSelected(Integer rid);
}
