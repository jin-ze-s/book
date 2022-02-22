package com.atguigu.book.test;

import com.atguigu.book.dao.impl.UserDaoImpl;
import com.atguigu.book.pojo.User;
import org.junit.Test;

/**
 * @author Tom 2022/1/31 13:42
 */
public class TestUserDao {

    @Test
    public void addOne(){
        String username = "jinzes";
        String password = "990830";
        String email = "736213595@qq.com";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        boolean isSuccess = new UserDaoImpl().addOne(user);
        System.out.println(isSuccess);
    }

    @Test
    public void queryOne(){
        String username = "jinzes";
        String password = "990830";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = new UserDaoImpl().queryOne(user);
        System.out.println(user);
    }

    @Test
    public void queryOneByUsername(){
        String username = "jinzess";
         User user = new UserDaoImpl().queryOneByUsername(username);
        System.out.println(user);
    }

}
