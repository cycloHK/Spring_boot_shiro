package com.stu.spring_boot_shiro.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //获取到工厂以参数形式返回
        this.context = applicationContext;
    }
    public static Object getBean(String beanName){
        //根据bean 获取工厂的指定对象
        return context.getBean(beanName);
    }
}
