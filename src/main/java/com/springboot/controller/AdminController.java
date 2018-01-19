package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Admin;
import com.springboot.service.AdminService;
import com.springboot.util.JSONUtil;
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
	private AdminService adminService;

	@GetMapping("/list")
	public String list(HttpServletRequest request, Admin admin, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "1") Integer pageSize,String beginTime,String endTime,Model model) {
		LOGGER.info("获取管理员列表：admin={},pageNum={},pageSize={},beginTime={},endTime={}", JSONUtil.objectToJson(admin), pageNum, pageSize,beginTime,endTime);
		try {
			PageInfo<Admin> pageInfo = adminService.selectByPage(admin, pageNum, pageSize, beginTime, endTime);
			model.addAttribute("page", pageInfo);
			LOGGER.info("pageInfo={}",JSONUtil.objectToJson(pageInfo));
			//return ResultUtil.successWithObj(ResultConst.CODE_200, ResultConst.GET_DATA_SUCCESS, pageInfo);
		} catch (Exception e) {
			LOGGER.error("获取管理员列表出现异常e={}", e.getMessage(), e);
			//return ResultUtil.error(ResultConst.CODE_500, ResultConst.GET_DATA_FAIL);
		}
		return "admin/admin_list";
	}
}
