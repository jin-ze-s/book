package com.atguigu.book.service;

import com.atguigu.book.pojo.User;

/**
 * @author Tom 2022/1/31 13:54
 */
public interface UserService {

    /**
     * �û�ע���ҵ���߼�����
     * @param user ע����û�
     * @return �Ƿ�ע��ɹ�
     */
    boolean register(User user);

    /**
     * �û���¼��ҵ���߼�����
     * @param user ��¼���û�
     * @return �Ƿ��½�ɹ�
     */
    User login(User user);


}
