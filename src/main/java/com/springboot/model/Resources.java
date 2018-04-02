package com.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 资源
 * 
 * @author BPS
 * @time 2018年1月12日上午10:54:17
 *
 */
@Getter
@Setter
@ToString
@Table(name="t_resources")
public class Resources {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;// 资源名称
	private String resUrl;// 资源url
	private Integer type;// 资源类型 1:菜单 2：按钮
	private Integer parentId;// 父资源
	private Integer sort;// 排序
	private String permission;//权限标识
	@Transient
	private String checked; // 是否选择
	@Transient
	private List<Resources> sonList = new ArrayList<Resources>();
	@Transient
	private String parentName;

}
