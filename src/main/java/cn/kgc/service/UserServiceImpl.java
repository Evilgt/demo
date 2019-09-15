package cn.kgc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.kgc.dao.UserMapper;
import cn.kgc.pojo.User;

@Service("userService")
public class UserServiceImpl     implements UserService {

	@Resource
	public UserMapper userMapper;

	@Override
	public List<User> getAlluser() {
		return userMapper.getAlluser();
	}

	@Override
	public int adduser(User user) {
		return userMapper.adduser(user);
	}
}
