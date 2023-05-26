package com.atguigu.auth.controller;

import com.atguigu.util.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @PostMapping("/login")
    public Result<Map<String,Object>> login(){
        System.out.println("hello git");
        Map<String,Object> map = new HashMap<>();
        map.put("token", "admin-token");
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.ok(map);
    }

    @GetMapping("/info")
    public Result<Map<String,Object>> info(){
        Map<String,Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avater","");
        return Result.ok(map);
    }

    @PostMapping("/logout")
    public Result<Object> logout(){
        return Result.ok();
    }

}
