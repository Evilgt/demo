package cn.kgc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.kgc.pojo.User;

@Mapper
public interface UserMapper {
	//查询列表
	List<User> getAlluser();

	// 增加用户
	int adduser(User user);

}
