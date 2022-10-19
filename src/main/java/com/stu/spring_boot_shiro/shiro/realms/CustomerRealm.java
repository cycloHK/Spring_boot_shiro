package com.stu.spring_boot_shiro.shiro.realms;

import com.stu.spring_boot_shiro.entity.Perms;
import com.stu.spring_boot_shiro.entity.User;
import com.stu.spring_boot_shiro.service.UserService;
import com.stu.spring_boot_shiro.utils.ApplicationContextUtils;
import com.stu.spring_boot_shiro.utils.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;


//自定义realm
public class CustomerRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获取身份信息
        String principal = (String) principals.getPrimaryPrincipal();
        //根据主身份信息获角色， 和权限信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("UserService");
        User user = userService.findRolesByUserName(principal);
        System.out.println("访问数据库 授权");
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                    simpleAuthorizationInfo.addRole(role.getName());

                    //权限信息
                    List<Perms> perms =  userService.findPermissionByRoleId(role.getId());
                    System.out.println("perms" + perms);
                    if(!CollectionUtils.isEmpty(perms) && perms.get(0) != null){
                        perms.forEach(perms1 -> {
                            simpleAuthorizationInfo.addStringPermission(perms1.getName());
                        });
                    }
            });
            return simpleAuthorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String principal = (String) token.getPrincipal();
        //获取Server对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("UserService");
        //根据用户信息查询 用户数据
        User user = userService.findByUserName(principal);
        System.out.println("访问数据库 认证");
        //用户存在
        if(!ObjectUtils.isEmpty(user)){
            //返回账号信息
            return new SimpleAuthenticationInfo(user.getUserName(),user.getPassWord(), new MyByteSource(user.getSalt()),this.getName());
        }

        return null;
    }

}

