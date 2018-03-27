package com.springboot.model;

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 操作日志
 * 
 * @author Augus
 * @email augus.bps@qq.com
 * @time 2018年3月27日下午2:48:06
 *
 */
@Setter
@Getter
@ToString
@Table(name = "t_operation_log")
public class OperationLog {
	/**
	 * 日志id
	 */
	private Integer id;

	/**
	 * 当前操作人
	 */
	private String loginAccount;

	/**
	 * 当前操作人ip
	 */
	private String loginIp;

	/**
	 * 操作请求的链接
	 */
	private String actionUrl;

	/**
	 * 执行的模块
	 */
	private String module;

	/**
	 * 执行的方法
	 */
	private String method;

	/**
	 * 执行操作时间
	 */
	private Long actionTime;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 执行的时间
	 */
	private Date createTime;

	/**
	 * 该操作状态，1表示成功，0表示失败！
	 */
	private Short state;

}
