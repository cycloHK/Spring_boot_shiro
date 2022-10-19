package com.stu.spring_boot_shiro.utils;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

//自定义salt 实现序列化
public class MyByteSource extends SimpleByteSource implements Serializable {

    public MyByteSource(String string) {
        super(string);
    }
}
