package com.atguigu.auth.controller;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.model.system.SysRole;
import com.atguigu.util.result.Result;
import com.atguigu.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    //http://localhost:8800/admin/system/role/findAll

    @Autowired
    private SysRoleService sysRoleService;

/*    @GetMapping("/findAll")
    public List<SysRole> findAll(){
        return sysRoleService.list();
    }*/

    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public Result<List<SysRole>> findAll(){
        return Result.ok(sysRoleService.list());
    }

    //条件分页查询
    @ApiOperation("条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result<Page<SysRole>> PageQuery(@PathVariable Long page,
                            @PathVariable Integer limit,
                            SysRoleQueryVo vo){

        //1 分页条件信息
        Page<SysRole> pageParam = new Page<>(page,limit);
        //2 判断条件，封装条件
        String roleName = vo.getRoleName();
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(roleName)){
            wrapper.like(SysRole::getRoleName, roleName);
        }
        //3 调用方法分页
//        Page<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        sysRoleService.page(pageParam, wrapper);
        System.out.println("======================"+pageParam);
        return Result.ok(pageParam);
    }

    //添加用户
    @ApiOperation("添加用户")
    @PostMapping("/save")
    public Result<Object> add(@RequestBody SysRole role){
        if (sysRoleService.save(role)) {
            return Result.ok().message("添加成功");
        }else{
            return Result.fail().message("添加失败");
        }
    }

    //先根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("/query/{id}")
    public Result<SysRole> queryById(@PathVariable Long id){
        SysRole role = sysRoleService.getById(id);

        /*try {
            int i = 10/0;
        } catch (Exception e) {
            throw new GuiGuException(2001, "自定义异常执行了");
        }*/
        return Result.ok(role);
    }
    //最终修改
    @ApiOperation("根据id修改")
    @PutMapping("/update/role")
    public Result<Object> update(@RequestBody SysRole role){
        if (sysRoleService.updateById(role)) {
            return Result.ok().message("修改成功");
        }else {
            return Result.fail().message("修改失败");
        }
    }

    //根据id删除
    @ApiOperation("根据id删除")
    @DeleteMapping("/delete/role")
    public Result<Object> deleteById(Long id){
        if (sysRoleService.removeById(id)) {
            return Result.ok().message("删除成功");
        }else{
            return Result.fail().message("删除失败");
        }
    }

    //批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("/batchDelete")
    public Result<Object> batchDelete(@RequestBody List<Long> ids){
        if (sysRoleService.removeByIds(ids)) {
            return Result.ok().message("删除成功");
        }else {
            return Result.fail().message("删除失败");
        }
    }



}
