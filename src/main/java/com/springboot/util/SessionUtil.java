package com.springboot.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtil {

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes == null ? null : requestAttributes.getRequest();
	}

	public static HttpSession getSession() {
		return getRequest().getSession(false);
	}

	public static String getRealRootPath() {
		return getRequest().getSession().getServletContext().getRealPath("/");
	}

	public static String getIp() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request.getRemoteAddr();
		}
		return null;
	}

	public static String getRequestURI() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request.getRequestURI();
		}
		return null;
	}

	public static Object getSessionAttribute(String name) {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getSession().getAttribute(name);
	}

	public static void setSessionAttribute(String name, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.getSession().setAttribute(name, value);
		}
	}

	public static Object getRequestAttribute(String name) {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getAttribute(name);
	}

	public static void setRequestAttribute(String name, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.setAttribute(name, value);
		}
	}

	public static String getContextPath() {
		return getRequest().getContextPath();
	}

	public static void removeSessionAttribute(String name) {
		getRequest().getSession().removeAttribute(name);
	}

}
