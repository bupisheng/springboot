package com.springboot.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.springboot.mapper.OperationLogMapper;
import com.springboot.model.OperationLog;
import com.springboot.service.OperationLogService;


@Service
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog> implements OperationLogService {

	@Resource
	private OperationLogMapper operationLogMapper;

	@Override
	public PageInfo<OperationLog> selectByPage(OperationLog operationLog, Integer pageNum, Integer pageSize,
			String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return null;
	}


	


}
