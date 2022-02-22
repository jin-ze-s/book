package com.atguigu.book.dao;

import com.atguigu.book.pojo.User;

public interface UserDao {
    /**
     * 根据用户名和密码查询用户
     * @param user 查询的用户对象
     * @return 返回一个用户对象
     */
    User queryOne(User user);

    /**
     * 注册成功后，添加用户
     * @param user 添加的用户对象
     * @return 返回是否添加成功
     */
    boolean addOne(User user);


    User queryOneByUsername(String username);
}
