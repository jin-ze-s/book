package com.atguigu.book.test;

import com.atguigu.book.pojo.User;
import com.atguigu.book.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author Tom 2022/1/31 14:42
 */
public class TestUserService {

    @Test
    public void register(){
        String username = "admin";
        String password = "123456";
        String email = "123@qq.com";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        boolean register = new UserServiceImpl().register(user);
        System.out.println(register);
    }

    @Test
    public void login(){
        String username = "jinzes";
        String password = "990830";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = new UserServiceImpl().login(user);
        System.out.println(user);
    }

}
