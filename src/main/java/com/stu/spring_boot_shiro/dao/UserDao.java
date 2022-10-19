package com.stu.spring_boot_shiro.dao;

import com.stu.spring_boot_shiro.entity.Perms;
import com.stu.spring_boot_shiro.entity.Role;
import com.stu.spring_boot_shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDao {
    //新增用户
    void save(User user);
    //查询用户
    User findByUserName(String userName);

    //根据用户查询角色
    User findRolesByUserName(String userName);

    //根据角色id拿到不同权限的集合
    List<Perms> findPermissionByRoleId(String id);
}
