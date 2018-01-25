package com.springboot.model;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name="t_role_resources")
public class RoleResources {

	private Integer roleId;
	private String resourcesId;
	
}
