package com.atguigu.book.dao;

import com.atguigu.book.pojo.User;

public interface UserDao {
    /**
     * �����û����������ѯ�û�
     * @param user ��ѯ���û�����
     * @return ����һ���û�����
     */
    User queryOne(User user);

    /**
     * ע��ɹ�������û�
     * @param user ��ӵ��û�����
     * @return �����Ƿ���ӳɹ�
     */
    boolean addOne(User user);


    User queryOneByUsername(String username);
}
