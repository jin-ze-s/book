package com.atguigu.book.filter;

import com.atguigu.book.utils.DBUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Tom 2022/2/14 16:53
 * 事务过滤器，给工程路径下的所有请求，加上try catch维持事务
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
//            这里还要抛出异常，让tomcat服务器捕获，才能显示错误页面
            throw new RuntimeException();
        }
    }

    @Override
    public void destroy() {

    }
}
