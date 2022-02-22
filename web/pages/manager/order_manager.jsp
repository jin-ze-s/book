<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含 base标签 css样式和jquery文件--%>
	<%@include file="/pages/common/header.jsp"%>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%--静态包含manager管理模块的菜单--%>
		<%@include file="/pages/common/manage_menu.jsp"%>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>状态</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="page">
			<tr>
				<td>${page.create_time}</td>
				<td>${page.price}</td>
				<td><a href="orderServlet?action=showOrderDetail&orderId=${page.order_id}">查看详情</a></td>
				<c:if test="${page.status eq 0}">
				<td><a href="orderServlet?action=sendOrder&orderId=${page.order_id}&pageNo=${requestScope.page.pageNo}">点击发货</a></td>
				</c:if>
				<c:if test="${page.status eq 1}">
					<td>已发货</td>
				</c:if>
				<c:if test="${page.status eq 2}">
					<td>已收货</td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
