package com.atguigu.auth.service;

import com.atguigu.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-05-25
 */
public interface SysUserService extends IService<SysUser> {

    //修改用户状态
    void updateStatus(Long id, int status);
}
