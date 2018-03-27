package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.OperationLog;

public interface OperationLogService extends BaseService<OperationLog> {

	PageInfo<OperationLog> selectByPage(OperationLog operationLog, Integer pageNum, Integer pageSize, String beginTime,
			String endTime);

}
