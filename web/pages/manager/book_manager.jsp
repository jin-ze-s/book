<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--静态包含 base标签 css样式和jquery文件--%>
	<%@include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function () {
			var isDel = function () {
				var name = this.parentElement.parentElement.firstElementChild.innerHTML;
				return confirm("确定删除【"+name+"】吗？")
			};

			$(".delete").live("click",isDel);

			$("#skip_btn").click(function () {
				var pageNo = $("#pn_input").val();
				if(pageNo > ${requestScope.page.pageTotal} || pageNo < 1){
					alert("请输入正确的页码！");
					return;
				}
				location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
			})

		})
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%--静态包含manager管理模块的菜单--%>
		<%@include file="/pages/common/manage_menu.jsp"%>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.author}</td>
					<td>${item.sales}</td>
					<td>${item.stock}</td>
					<td><a href="manager/bookServlet?action=queryBookById&id=${item.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a href="manager/bookServlet?action=delete&id=${item.id}&pageNo=${requestScope.page.pageNo}" class="delete">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
