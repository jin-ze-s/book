package com.atguigu.book.service.impl;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.dao.impl.UserDaoImpl;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.UserService;

/**
 * @author Tom 2022/1/31 13:58
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user) {

            //先检查用户名是否已存在
             User tempUser = userDao.queryOneByUsername(user.getUsername());
            if (tempUser != null) {
                return false;
            } else {
                return userDao.addOne(user);
            }
    }

    @Override
    public User login(User user) {
         user = userDao.queryOne(user);
        if (user != null) {
            return user;
        } else {
            System.out.println("用户名或密码错误！");
            return null;
        }
    }


}
