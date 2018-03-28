package com.springboot.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.model.Admin;
import com.springboot.model.OperationLog;
import com.springboot.service.OperationLogService;
import com.springboot.util.SessionUtil;
import com.springboot.util.constant.ConstantUtil;

/**
 * 操作日志
 * 
 * @author BPS
 * @time 2017年10月11日下午2:33:48
 *
 */
@Aspect
@Component
public class LogAopAspect {
	// 获取开始时间
	private long BEGIN_TIME;
	// 获取结束时间
	private long END_TIME;
	// 定义本次log实体
	private OperationLog log = new OperationLog();

	@Autowired
	private OperationLogService logService;

	@Pointcut("execution(public * com.springboot.controller..*.*(..))")
	private void controllerAspect() {
	}

	/**
	 * 方法开始执行
	 */
	@Before("controllerAspect()")
	public void doBefore() {
		BEGIN_TIME = new Date().getTime();
	}

	/**
	 * 方法结束执行
	 */
	@After("controllerAspect()")
	public void after() {
		END_TIME = new Date().getTime();
	}

	/**
	 * 方法结束执行后的操作
	 */
	@AfterReturning(value = "controllerAspect()", returning = "result")
	public void doAfter(Object result) {

		if (log.getState() == 1 || log.getState() == 0) {
			log.setActionTime(END_TIME - BEGIN_TIME);
			log.setCreateTime(new Date(BEGIN_TIME));
			// 存入数据库
			logService.save(log);
		}
	}

	/**
	 * 方法有异常时的操作
	 */
	@AfterThrowing(value = "controllerAspect()", throwing = "ex")
	public void doAfterThrow(Exception ex) {
	}

	/**
	 * 方法执行
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("rawtypes")
	@Around("controllerAspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// 获取当前登陆用户信息
		Admin admin = (Admin) SecurityUtils.getSubject().getSession().getAttribute(ConstantUtil.SESSION_USER);
		if (admin == null) {
			log.setLoginAccount("无");
		} else {
			log.setLoginAccount(admin.getUsername());
		}
		// 拦截的实体类，就是当前正在执行的controller
		Object target = pjp.getTarget();
		// 拦截的方法名称。当前正在执行的方法
		String methodName = pjp.getSignature().getName();
		// 拦截的方法参数
		Object[] args = pjp.getArgs();
		System.out.println(args + "-----");
		// 拦截的放参数类型
		Signature sig = pjp.getSignature();
		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature)) {
			throw new IllegalArgumentException("该注解只能用于方法");
		}
		msig = (MethodSignature) sig;
		Class[] parameterTypes = msig.getMethod().getParameterTypes();
		String[] strings = msig.getParameterNames(); // 参数
		Object object = null;

		Method method = null;
		try {
			method = target.getClass().getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		if (null != method) {
			// 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
			if (method.isAnnotationPresent(SystemLogAop.class)) {
				SystemLogAop systemlog = method.getAnnotation(SystemLogAop.class);
				log.setModule(systemlog.module());
				log.setMethod(systemlog.methods());
				log.setLoginIp(SessionUtil.getIp());
				log.setActionUrl(SessionUtil.getRequestURI());
				String desc = admin.getUsername() + "," + systemlog.module() + "：" + systemlog.methods() + ",参数："
						+ strings;
				try {
					object = pjp.proceed();
					desc += ",执行成功";
					log.setState((short) 1);
				} catch (Throwable e) {
					desc += ",执行失败";
					log.setState((short) 0);
				}
				log.setDescription(desc);
			} else {// 没有包含注解
				object = pjp.proceed();
				log.setDescription("此操作不包含注解");
				log.setState((short) -1);
			}
		} else { // 不需要拦截直接执行
			object = pjp.proceed();
			log.setDescription("不需要拦截直接执行");
			log.setState((short) -1);
		}
		return object;
	}

}
