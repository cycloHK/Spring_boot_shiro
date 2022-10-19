package com.stu.spring_boot_shiro.service;

import com.stu.spring_boot_shiro.entity.Perms;
import com.stu.spring_boot_shiro.entity.Role;
import com.stu.spring_boot_shiro.entity.User;

import java.util.List;

public interface UserService {
    //注册用户
    void register(User user);

    //根据用户名查询用户信息
    User findByUserName(String userName);

    //根据用户查询角色
    User findRolesByUserName(String userName);

    //根据角色id拿到不同权限的集合
    List<Perms> findPermissionByRoleId(String id);

}
