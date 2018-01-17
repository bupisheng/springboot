package com.springboot.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.model.Resources;
import com.springboot.service.ResourcesService;
import com.springboot.shiro.MyShiroRealm;
import com.springboot.util.StringUtil;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);
	@Autowired(required = false)
	private ResourcesService resourcesService;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeadProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 方便在thymeleaf中使用shiro标签
	 * 
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		LOGGER.info("shiroConfiguration.shiroFilter----->");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 设置登录
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 设置登录成功后跳转路径
		shiroFilterFactoryBean.setSuccessUrl("/");
		// 未授权的界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		//filterChainDefinitionMap.put("/loginout", "loginout");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/lib/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");

		List<Resources> resourcesList = resourcesService.queryAll();
		for (Resources resources : resourcesList) {
			if (StringUtil.isNotEmpty(resources.getResUrl())) {
				String permission = "perms[" + resources.getResUrl() + "]";
				filterChainDefinitionMap.put(resources.getResUrl(), permission);
			}
		}

		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm
		securityManager.setRealm(myShiroRealm());
		// 自定义缓存实现，使用redis
		securityManager.setCacheManager(redisCacheManager());
		// 自定义session管理使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * 所以我们需要修改下doGetAuthenticationInfo中的代码;
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5"); // 散列算法 MD5
		hashedCredentialsMatcher.setHashIterations(2);// 散列次数，相当于两次MD5加密
		return hashedCredentialsMatcher;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式,所以需要开启代码支持
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/**
	 * 配置shiro redisManager 使用的shiro-redis开源插件
	 * 
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(1800); // 缓存过期时间
		redisManager.setTimeout(timeout);
		// redisManager.setPassword(password);//密码
		return redisManager;
	}

	/**
	 * cacheManager 缓存redis实现
	 * 
	 * @return
	 */
	public RedisCacheManager redisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * redisSessionDao shiro sessionDao层的实现 通过redis
	 * 
	 * @return
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * shiro session管理
	 * 
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
}
