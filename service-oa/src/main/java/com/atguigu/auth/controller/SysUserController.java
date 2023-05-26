package com.atguigu.auth.controller;


import com.atguigu.auth.service.SysUserService;
import com.atguigu.model.system.SysUser;
import com.atguigu.util.result.Result;
import com.atguigu.vo.system.SysUserQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-05-25
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @ApiOperation("用户分页查询")
    @GetMapping("{page}/{limit}")
    public Result<Page<SysUser>> pageUser(@PathVariable long page,
                                          @PathVariable int limit,
                                          SysUserQueryVo sysUserQueryVo){
        //创建分页对象
        Page<SysUser> pageParam = new Page<>(page,limit);
        //封装查询条件按
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        //根据参数的值是否空来判断是否需要封装条件
        String username = sysUserQueryVo.getKeyword();
        String createTimeBegin = sysUserQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysUserQueryVo.getCreateTimeEnd();

        if(StringUtils.hasText(username)){
            wrapper.like(SysUser::getUsername,username);
        }
        if (StringUtils.hasText(createTimeBegin)){
            wrapper.ge(SysUser::getCreateTime,createTimeBegin);
        }
        if (StringUtils.hasText(createTimeEnd)){
            wrapper.le(SysUser::getCreateTime,createTimeEnd);
        }

        IPage<SysUser> pageModel = sysUserService.page(pageParam, wrapper);

        return Result.ok(pageParam);
    }

    @ApiOperation("根据名字获取用户")
    @GetMapping("/get/{id}")
    public Result<SysUser> queryByName(@PathVariable Long id){
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @ApiOperation("保存用户")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody SysUser sysUser){
        sysUserService.save(sysUser);
        return Result.ok().message("保存成功");
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public Result<Object> update(@RequestBody SysUser sysUser){
        sysUserService.updateById(sysUser);
        return Result.ok().message("修改成功");
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable Integer id){
        sysUserService.removeById(id);
        return Result.ok().message("删除成功");
    }

    //修改用户状态
    @ApiOperation("修改用户状态")
    @GetMapping("/update/{id}/{status}")
    public Result<Object> updateStatus(@PathVariable Long id,@PathVariable int status){
        sysUserService.updateStatus(id,status);
        return Result.ok();
    }

}

