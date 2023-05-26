package com.atguigu.auth;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MpDemo1Test {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    void getAll(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        System.out.println(sysRoles);
    }

    @Test
    void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("role");
        sysRole.setRoleCode("0");
        sysRole.setDescription("角色管理员");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println("rows = " + rows);
        System.out.println("sysRole.getId() = " + sysRole.getId());
    }

    @Test
    void update() {
        //先id找出来
        SysRole sysRole = sysRoleMapper.selectById(9);
        //修改内容
        sysRole.setRoleName("role角色管理员");
        //执行修改方法
        int rows = sysRoleMapper.updateById(sysRole);
        System.out.println("rows = " + rows);
    }

    //根据id删除
    @Test
    void deleteById() {
        int rows = sysRoleMapper.deleteById(9);
    }

    //批量删除

    @Test
    void delete() {
        int rows = sysRoleMapper.deleteBatchIds(Arrays.asList(8, 9));
        System.out.println("rows = " + rows);
    }

    @Test
    void testQuery1() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", "系统管理员");
        List<SysRole> sysRoles = sysRoleMapper.selectList(wrapper);
        System.out.println(sysRoles);
    }

    //Lambda
    @Test
    void testQuery2() {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getRoleName, "系统管理员");
        List<SysRole> sysRoles = sysRoleMapper.selectList(wrapper);
        System.out.println(sysRoles);
    }
}
