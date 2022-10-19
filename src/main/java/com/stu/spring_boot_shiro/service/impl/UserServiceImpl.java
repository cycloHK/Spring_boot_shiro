package com.stu.spring_boot_shiro.service.impl;

import com.stu.spring_boot_shiro.dao.UserDao;
import com.stu.spring_boot_shiro.entity.Perms;
import com.stu.spring_boot_shiro.entity.Role;
import com.stu.spring_boot_shiro.entity.User;
import com.stu.spring_boot_shiro.service.UserService;
import com.stu.spring_boot_shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        //处理业务 Md5 加密 加盐
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        //2.加盐 加密
        Md5Hash md5Hash = new Md5Hash(user.getPassWord(), user.getSalt(), 1024);
        user.setPassWord(md5Hash.toHex());
        //3.调用dao
        userDao.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User findRolesByUserName(String userName) {
        return userDao.findRolesByUserName(userName);
    }

    @Override
    public List<Perms> findPermissionByRoleId(String id) {
        return userDao.findPermissionByRoleId(id);
    }
}
