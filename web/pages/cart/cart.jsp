<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含 base标签 css样式和jquery文件--%>
	<%@include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function () {
			$(".delete").click(function () {
				return confirm("确定删除【"+$(this).attr("bookname")+"】吗");
			});
			$(".clear").click(function () {
				return confirm("确定清空购物车吗？")
			});
			$(".updateItem").blur(function () {
				if (this.value !== $(this).attr("bookcount")) {
				if(confirm("确定更改【"+this.parentElement.parentElement.firstElementChild.innerHTML+"】的商品数量为"+this.value+"吗？")){

						location.href = "${basePath}cartServlet?action=updateItemCount&id="+$(this).attr("bookId")+"&count="+this.value;
				}else {
					this.value = $(this).attr("bookcount");
				}
            }
			}
			);
		})
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif">
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>

	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
            <c:if test="${ empty sessionScope.cart.cartItems}">
				<tr>
					<td colspan="5"><a href="index.jsp" style="text-decoration: none">亲，你购物车还没商品哦！请去首页添加吧！</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.cartItems}">
			<c:forEach items="${sessionScope.cart.cartItems}" var="item">
			<tr>
				<td>${item.value.name}</td>
				<td>
					<input type="number" value="${item.value.count}" bookcount="${item.value.count}" bookId="${item.value.id}" style="width: 40px" class="updateItem">
				</td>
				<td>${item.value.price}</td>
				<td>${item.value.totalPrice}</td>
				<td><a class="delete" href="${basePath}cartServlet?action=deleteItem&id=${item.value.id}" bookname="${item.value.name}">删除</a></td>
			</tr>
			</c:forEach>
		</table>
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a  class="clear" href="cartServlet?action=clearItem">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
