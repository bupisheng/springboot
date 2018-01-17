package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.User;

public interface UserService extends BaseService<User> {

	PageInfo<User> selectByPage(User user, int start, int length);

	User selectByUsername(String username);

	void delUser(Integer userid);
}
