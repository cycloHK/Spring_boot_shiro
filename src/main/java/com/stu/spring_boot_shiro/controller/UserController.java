package com.stu.spring_boot_shiro.controller;

import com.stu.spring_boot_shiro.entity.User;
import com.stu.spring_boot_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 用户认证
     */
    @RequestMapping("register")
    public String register(User user){
        try {
            userService.register(user);
            return "redirect:/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/regitser.jsp";
        }
    }



    /**
     * 退出登录
     * @return
     */
    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();  //退出用户
        return "redirect:/login.jsp";
    }

    /**
     * 用来处理 身份认证
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("login")
    public String login(String userName,String passWord){

        //获取主体对象
        try{
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(userName,passWord));
            return "redirect:/index.jsp";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名错误! ");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误！ ");
        }
        return "redirect:/login.jsp";
    }
}
