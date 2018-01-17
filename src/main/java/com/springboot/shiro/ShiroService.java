package com.springboot.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Resources;

import com.springboot.service.ResourcesService;
import com.springboot.util.StringUtil;

@Service
public class ShiroService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShiroService.class);
	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	@Autowired
	private ResourcesService resourcesService;
	
	
	public Map<String, String> loadFilterChainDefinitions(){
		Map<String, String> map = new LinkedHashMap<String,String>();
		map.put("loginout", "loginout");
		map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/images/**", "anon");
		map.put("/lib/**", "anon");
		List<Resources> resources = resourcesService.queryAll();
		String permission;
		for (Resources resource : resources) {
			if (StringUtil.isNotEmpty(resource.getResUrl())) {
				permission = "perms["+resource.getResUrl()+"]";
				map.put(resource.getResUrl(), permission);
			}
		}
		map.put("/**", "authc");
		return map;
	}
	
	/**
	 * 重新加载权限
	 */
	public void updatePermission(){
		synchronized (shiroFilterFactoryBean) {
			AbstractShiroFilter shiroFilter = null;
			try {
				shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
			} catch (Exception e) {
				throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
			}
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
			
			//清空之前的权限控制
			manager.getFilterChains().clear();
			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
			shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
		    //重新构建生成
		    Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
		    String url,chainDefinition;
		    for (Map.Entry<String, String> entry : chains.entrySet()) {
				url = entry.getKey();
				chainDefinition = entry.getValue().trim().replace(" ", "");
				manager.createChain(url, chainDefinition);
			}
		    LOGGER.info("更新权限成功！");
		}
	}
}
