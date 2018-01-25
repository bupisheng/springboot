package com.springboot.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.springboot.model.Admin;

public class PasswordUtil {
	private final static String ALGORITHM_NAME = "md5";
	private final static Integer HASH_ITERATIONS = 2;

	public void encryptAdminPassword(Admin admin) {
		String newPassword = new SimpleHash(ALGORITHM_NAME, admin.getPassword(),
				ByteSource.Util.bytes(admin.getUsername()), HASH_ITERATIONS).toHex();
		admin.setPassword(newPassword);

	}
}
