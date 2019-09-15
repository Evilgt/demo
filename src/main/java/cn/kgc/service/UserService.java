package cn.kgc.service;

import java.util.List;

import cn.kgc.pojo.User;

public interface UserService {
	List<User> getAlluser();

	// 增加用户
	int adduser(User user);
}
