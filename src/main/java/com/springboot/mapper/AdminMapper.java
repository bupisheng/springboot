package com.springboot.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.model.Admin;
import com.springboot.util.mapper.MyMapper;

public interface AdminMapper extends MyMapper<Admin> {

	List<Admin> selectAdminList(@Param("admin") Admin admin, @Param("beginDate") Date beginDate,
			@Param("endDate") Date endDate);
}
