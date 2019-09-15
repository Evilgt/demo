package cn.kgc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 86158 on 2019/7/24.
 */
@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/setValue")
    public String setValue(){
        if(!template.hasKey("kgc")){
            template.opsForValue().append("kgc","张杰");
            return  "使用redis缓存保存数据成功";
        }else{
            template.delete("kgc");
            return "key值已存在";
        }
    }
    @RequestMapping("/getValue")
    public String getValue(){
        if(!template.hasKey("kgc")){
            return  "key不存在，请先保存数据";
        }else{
            String kgc=template.opsForValue().get("kgc");
            return "获取到缓存中的数据：kgc="+kgc;
        }
    }
}
