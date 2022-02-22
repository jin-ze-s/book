package com.atguigu.book.service;

import com.atguigu.book.pojo.User;

/**
 * @author Tom 2022/1/31 13:54
 */
public interface UserService {

    /**
     * 用户注册的业务逻辑方法
     * @param user 注册的用户
     * @return 是否注册成功
     */
    boolean register(User user);

    /**
     * 用户登录的业务逻辑方法
     * @param user 登录的用户
     * @return 是否登陆成功
     */
    User login(User user);


}
