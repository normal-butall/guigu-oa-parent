package com.atguigu.auth;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MpDemo2Test {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    void getAll(){
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }

}
