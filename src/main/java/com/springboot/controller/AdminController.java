package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.springboot.aop.SystemLogAop;
import com.springboot.model.Admin;
import com.springboot.model.Role;
import com.springboot.service.AdminService;
import com.springboot.service.RoleService;
import com.springboot.util.JSONUtil;
import com.springboot.util.StringUtil;
import com.springboot.util.constant.ConstantUtil;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultConst;
import com.springboot.util.result.ResultUtil;

/**
 * 管理员controller
 * 
 * @author Augus
 * @email augus.bps@qq.com
 * @time 2018年1月19日上午10:48:46
 *
 */
@Controller
@RequestMapping("/back/admin")
public class AdminController {

	@Autowired
	private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private AdminService adminService;

	@SystemLogAop(module="管理员模块",methods="管理员列表")
	@GetMapping("/list")
	public String list(HttpServletRequest request, Admin admin, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize, String beginTime, String endTime, Model model) {
		LOGGER.info("获取管理员列表：admin={},pageNum={},pageSize={},beginTime={},endTime={}", JSONUtil.objectToJson(admin),
				pageNum, pageSize, beginTime, endTime);
		try {
			PageInfo<Admin> pageInfo = adminService.selectByPage(admin, pageNum, pageSize, beginTime, endTime);
			model.addAttribute("page", pageInfo);
			model.addAttribute("endTime", endTime);
			model.addAttribute("beginTime", beginTime);
			model.addAttribute("username", admin.getUsername());
			LOGGER.info("pageInfo={}", JSONUtil.objectToJson(pageInfo));
		} catch (Exception e) {
			LOGGER.error("获取管理员列表出现异常e={}", e.getMessage(), e);
		}
		return "admin/admin_list";
	}

	@GetMapping("/toAdd")
	public String toAdd(HttpServletRequest request, Model model) {
		List<Role> list = roleService.selectByExample(null);
		model.addAttribute("roles", list);
		return "admin/admin_add";
	}

	@SystemLogAop(module="管理员模块",methods="添加管理员")
	@ResponseBody
	@PostMapping("/add")
	public Result add(Admin admin, String roleId, HttpServletRequest request) {
		LOGGER.info("新增后台用户admin={}，roleId={}", JSONUtil.objectToJson(admin), roleId);
		try {
			if (StringUtil.isEmpty(admin.getUsername()) || StringUtil.isEmpty(admin.getPassword())
					|| StringUtil.isEmpty(roleId)) {
				return ResultUtil.error(ResultConst.CODE_500, ResultConst.PARAM_ERROR);
			}
			Admin loadAdmin = adminService.selectByUsername(admin.getUsername());
			if (null != loadAdmin) {
				return ResultUtil.error(ResultConst.CODE_500, ResultConst.USERNAME_IS_EXIST);
			}
			adminService.save(admin, roleId);
			return ResultUtil.success(ResultConst.CODE_200, ResultConst.SAVE_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增后台用户出现异常e={}", e.getMessage(), e);
			return ResultUtil.error(ResultConst.CODE_500, ResultConst.SAVE_FAIL);
		}
	}

	@SystemLogAop(module="管理员模块",methods="修改管理员状态")
	@ResponseBody
	@PostMapping("/updateStatus")
	public Result updateStatus(String id) {
		LOGGER.info("修改状态id={}", id);
		try {
			Admin admin = adminService.selectByKey(id);
			Admin updateAdmin = new Admin();
			updateAdmin.setId(id);
			if (admin.getStatus() == ConstantUtil.STATUS_ENABLE) {
				updateAdmin.setStatus(ConstantUtil.STATUS_UNENABLE);
			} else {
				updateAdmin.setStatus(ConstantUtil.STATUS_ENABLE);
			}
			adminService.updateNotNull(updateAdmin);
			return ResultUtil.success(ResultConst.CODE_200, ResultConst.UPDATE_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("修改状态出现异常e={}", e.getMessage(), e);
			return ResultUtil.error(ResultConst.CODE_500, ResultConst.UPDATE_FAIL);
		}
	}
	
	@SystemLogAop(module="管理员模块",methods="删除管理员")
	@ResponseBody
	@PostMapping("/del")
	public Result delete(String id) {
		LOGGER.info("删除管理员id={}", id);
		try {
			adminService.delAdmin(id);
			return ResultUtil.success(ResultConst.CODE_200, ResultConst.DELETE_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("删除管理员出现异常e={}", e.getMessage(), e);
			return ResultUtil.error(ResultConst.CODE_500, ResultConst.DELETE_FAIL);
		}
	}

}
