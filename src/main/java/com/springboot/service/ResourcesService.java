package com.springboot.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Resources;

public interface ResourcesService extends BaseService<Resources>{

    PageInfo<Resources> selectByPage(Resources resources, int start, int length);

    List<Resources> queryAll();

    List<Resources> loadUserResources(Map<String,Object> map);

    List<Resources> queryResourcesListWithSelected(Integer rid);
}
