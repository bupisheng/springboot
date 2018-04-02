package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.model.Resources;
import com.springboot.model.Admin;
import com.springboot.service.ResourcesService;
import com.springboot.util.JSONUtil;
import com.springboot.util.constant.ConstantUtil;

@Controller
@RequestMapping("/back")
public class IndexController {

	private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private ResourcesService resourcesService;

	@GetMapping("/welcome")
	public String welcome(){
		return "welcome";
	}
	
	/**
	 * 获取当前登录的用户菜单
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String getMenu(HttpServletRequest request, Model model) {
		LOGGER.info("获取菜单");
		try {
			Admin admin = (Admin) SecurityUtils.getSubject().getSession().getAttribute(ConstantUtil.SESSION_USER);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("adminId", admin.getId());
			map.put("type", ConstantUtil.RESOURCE_TYPE_MENU);
			map.put("parentId", ConstantUtil.RESOURCE_PARENT_NULL);
			List<Resources> parentMenus = resourcesService.loadAdminResources(map);
			for (Resources resource : parentMenus) {
				buildNodes(resource, admin.getId());
			}
			model.addAttribute("menus",parentMenus);
			System.out.println(JSONUtil.objectToJson(parentMenus));
		} catch (Exception e) {
			LOGGER.error("获取菜单出现异常e={}", e.getMessage(), e);
		}
		return "index";
	}

	/**
	 * build子节点
	 * 
	 * @param parent
	 */
	private void buildNodes(Resources parent, String adminId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminId", adminId);
		map.put("parentId", parent.getId());
		map.put("type", ConstantUtil.RESOURCE_TYPE_MENU);
		List<Resources> childs = resourcesService.loadAdminResources(map);
		if (childs != null && childs.size() > 0) {
			parent.setSonList(childs);
			for (Resources resource : childs) {
				buildNodes(resource, adminId);
			}
		}
	}
}
