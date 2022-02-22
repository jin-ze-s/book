package com.atguigu.book.servlet;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.dao.impl.UserDaoImpl;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.UserService;
import com.atguigu.book.service.impl.UserServiceImpl;
import com.atguigu.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


@SuppressWarnings("all")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private UserDao userDao = new UserDaoImpl();

    /**
     * �����¼��ҵ������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user = WebUtils.mapToBean(request.getParameterMap(), new User());
        User loginUser = userService.login(user);
        if(loginUser != null){
            request.getSession().setAttribute("user", loginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp")
                    .forward(request,response);
        }else {
            request.setAttribute("message", "�û������������");
            request.setAttribute("username", user.getUsername());
            request.getRequestDispatcher("/pages/user/login.jsp")
                    .forward(request, response);
        }
    }

    /**
     * ����ע���ҵ������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user = WebUtils.mapToBean(request.getParameterMap(), new User());
        String code = request.getParameter("code");
        String codeTrue = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        if (code.equalsIgnoreCase(codeTrue)) {
            request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
            boolean register = userService.register(user);
            if (register) {
                request.getRequestDispatcher("/pages/user/regist_success.jsp")
                        .forward(request, response);
            } else {
                request.setAttribute("message", "�û����Ѵ���!");
                request.setAttribute("username", user.getUsername());
                request.getRequestDispatcher("/pages/user/regist.jsp")
                        .forward(request, response);
            }
        }else {
            request.setAttribute("message", "��֤�����!");
            request.setAttribute("username", user.getUsername());
            request.getRequestDispatcher("/pages/user/regist.jsp")
                    .forward(request, response);
        }
    }

    /**
     * �ж��û����Ƿ����
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void usernameIsOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        User user = userDao.queryOneByUsername(username);
        response.setContentType("text/html; charset=utf-8");
        if (user != null){
            response.getWriter().write("�û����Ѵ��ڣ�");
        }
    }

    /**
     * ����ע��ҵ��
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        �����ǰ�ͻ�����������ĻỰ
            request.getSession().invalidate();
//            �ض�����ҳ
            response.sendRedirect(request.getContextPath());
    }



}
