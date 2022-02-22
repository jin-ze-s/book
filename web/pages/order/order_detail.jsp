<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含 base标签 css样式和jquery文件--%>
	<%@include file="/pages/common/header.jsp"%>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif">
			<span class="wel_word">订单明细</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>

	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
			</tr>
			<c:forEach items="${requestScope.orderItems}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>${item.count}</td>
				<td>${item.price}</td>
				<td>${item.total_price}</td>
			</tr>
			</c:forEach>
		</table>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
