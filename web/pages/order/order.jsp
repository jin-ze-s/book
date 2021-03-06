<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含 base标签 css样式和jquery文件--%>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>

	<div id="main">

		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="order">
			<tr>
				<td>${order.create_time}</td>
				<td>${order.price}</td>
				<c:if test="${order.status eq 0}">
					<td>未发货</td>
				</c:if>
				<c:if test="${order.status eq 1}">
					<td><a href="orderServlet?action=receiveOrder&orderId=${order.order_id}&pageNo=${requestScope.page.pageNo}">点击收货</a></td>
				</c:if>
				<c:if test="${order.status eq 2}">
					<td>已签收</td>
				</c:if>
				<td><a href="orderServlet?action=showOrderDetail&orderId=${order.order_id}">查看详情</a></td>
			</tr>
			</c:forEach>
		</table>

		<%@include file="/pages/common/page_nav.jsp"%>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
