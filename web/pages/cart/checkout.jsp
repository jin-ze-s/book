<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
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
			<span class="wel_word">结算</span>
	<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>

	<div id="main">
		<c:if test="${not empty sessionScope.errorMsg}">
			<h1>${sessionScope.errorMsg}</h1>
		</c:if>
		<c:if test="${empty sessionScope.errorMsg}">
		<h1>你的订单已结算，订单号为${sessionScope.order}</h1>
		</c:if>

	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
