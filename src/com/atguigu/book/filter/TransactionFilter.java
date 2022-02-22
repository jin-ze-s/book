package com.atguigu.book.filter;

import com.atguigu.book.utils.DBUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Tom 2022/2/14 16:53
 * �����������������·���µ��������󣬼���try catchά������
 */
public class TransactionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
        filterChain.doFilter(servletRequest, servletResponse);
            DBUtils.commitAndClose();
        }catch (Exception e){
            DBUtils.rollbackAndClose();
            e.printStackTrace();
//            ���ﻹҪ�׳��쳣����tomcat���������񣬲�����ʾ����ҳ��
            throw new RuntimeException();
        }
    }

    @Override
    public void destroy() {

    }
}
