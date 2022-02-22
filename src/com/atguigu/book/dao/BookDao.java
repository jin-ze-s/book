package com.atguigu.book.dao;

import com.atguigu.book.pojo.Book;

import java.util.List;

public interface BookDao  {

    /**
     *
     * @param book ��ӵ�ͼ��
     * @return �Ƿ�ɹ�
     */
    boolean addOne(Book book);

    /**
     *
     * @param id Ҫɾ�����id
     * @return �Ƿ�ɹ�
     */
    boolean deleteOneById(int id);

    /**
     * �޸�ͼ��
     * @param book ���º��ͼ��
     * @return �Ƿ�ɹ�
     */
    boolean updateBook(Book book);

    /**
     *
     * @param id ��ѯ��id
     * @return ��Ӧid��ͼ��
     */
    Book queryOneById(int id);

    /**
     *
     * @return ���ز�ѯ��������ͼ��
     */
    List<Book> queryAll();

    /**
     *
     * @return ��������
     */
    int queryPageTotalCount();

    /**
     *
     * @param begin ��ʼ����
     * @param pageSize һҳ��ʾ��������
     * @return ��ǰҳ����
     */
    List<Book> queryItems(int begin, int pageSize);

    /**
     *
     * @param min ��С�۸�
     * @param max ��߼۸�
     * @return �ܵļ�¼��
     */
    int queryPageTotalCount(int min, int max);

    /**
     *
     * @param begin ��ʼ����
     * @param pageSize һҳ��ʾ������
     * @param min ��С�۸�
     * @param max ��߼۸�
     * @return ��ǰҳҳ��
     */
    List<Book> queryItems(int begin, int pageSize, int min, int max);
}
