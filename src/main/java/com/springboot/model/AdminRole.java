package com.springboot.model;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name="t_admin_role")
public class AdminRole {

	private String adminId;
	private Integer roleId;

}
