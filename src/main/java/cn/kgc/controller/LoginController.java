package cn.kgc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.kgc.util.MultipartUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.kgc.pojo.User;
import cn.kgc.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class LoginController {

	@Resource
	private UserService userService;
	@Resource
	private RedisTemplate redisTemplate;

	@RequestMapping("/getallUser")
	public List<User> select(HttpSession session) {
			List<User> list = userService.getAlluser();
			redisTemplate.opsForValue().set(session.getId(),list,30, TimeUnit.MINUTES);
			System.out.println("8001");
			return list;
	}

	@RequestMapping("/getValue1")
	public  List<User> getValue(HttpSession session){
	List<User> list= (List<User>) redisTemplate.opsForValue().get(session.getId());
		return  list;
	}



	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String adduser(User user) {
		System.out.println(user.getUserCode());
		int re = userService.adduser(user);
		if (re > 0) {
			return "yes";
		}
		return "no";
	}

	//文件上传
	@RequestMapping(value = "/adduser1", method = RequestMethod.POST)
	public String adduser1(User user, @RequestParam("attach") MultipartFile attach, HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = MultipartUtil.name(attach, request, session);
		if (map.get("idPicPath") != null) {
			user.setIdPicPath((String) map.get("idPicPath"));
			int re = userService.adduser(user);
			if (re > 0) {
				return "yes";
			}
		}
		return "no";
	}
}
