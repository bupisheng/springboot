package com.springboot.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.model.Resources;
import com.springboot.model.Admin;
import com.springboot.service.ResourcesService;
import com.springboot.service.AdminService;
import com.springboot.util.constant.ConstantUtil;

public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
	private AdminService userService;
	@Autowired
	private ResourcesService resourcesService;
	
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("adminId", admin.getId());
		List<Resources> resources = resourcesService.loadAdminResources(map);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Resources resource : resources) {
			info.addStringPermission(resource.getResUrl());
		}
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户输入的账号
		String username = (String) token.getPrincipal();
		Admin user = userService.selectByUsername(username);
		if (null == user) {
			throw new UnknownAccountException();
		}
		if (0 == user.getStatus()) {
			throw new LockedAccountException();//账号被锁定
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(username),getName());
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(ConstantUtil.SESSION_USER, user);
		session.setAttribute(ConstantUtil.SESSION_USER_ID, user.getId());
		return authenticationInfo;
	}

}
