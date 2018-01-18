package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.model.Admin;
import com.springboot.util.JSONUtil;
import com.springboot.util.StringUtil;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultConst;
import com.springboot.util.result.ResultUtil;

@Controller
public class LoginController {

	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@GetMapping(value = "/login")
	public String login() {
		return "/login";
	}

	@ResponseBody
	@PostMapping(value = "/login")
	public Result login(HttpServletRequest request, Admin user) {
		LOGGER.info("后台用户登录：user={}", JSONUtil.objectToJson(user));
		if (StringUtil.isEmpty(user.getUsername()) || StringUtil.isEmpty(user.getPassword())) {
			return ResultUtil.error(ResultConst.CODE_500, ResultConst.USERNAME_OR_PASSWORD_IS_NULL);
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
			LOGGER.info("用户登录成功：username={}", user.getUsername());
			return ResultUtil.success(ResultConst.CODE_200, ResultConst.USER_LOGIN_SUCCESS);
		} catch (LockedAccountException lockedException) {
			token.clear();
			LOGGER.error("用户被锁定：username={}", user.getUsername());
			return ResultUtil.error(ResultConst.CODE_500, ResultConst.USER_IS_LOCKED);
		} catch (AuthenticationException authenticationException) {
			token.clear();
			LOGGER.error("用户名或密码错误：username={},password={}", user.getUsername(), user.getPassword());
			return ResultUtil.error(ResultConst.CODE_500, ResultConst.USERNAME_OR_PASSWORD_ERROR);
		}

	}

}
