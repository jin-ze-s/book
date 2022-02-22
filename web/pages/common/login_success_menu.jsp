<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 2022/2/6
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="orderServlet?action=pageByUserId">我的订单</a>
    <a href="userServlet?action=logout" id="logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
