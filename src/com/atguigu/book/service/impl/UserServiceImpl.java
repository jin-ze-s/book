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

            //�ȼ���û����Ƿ��Ѵ���
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
            System.out.println("�û������������");
            return null;
        }
    }


}
