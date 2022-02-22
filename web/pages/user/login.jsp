<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
	<%--静态包含 base标签 css样式和jquery文件--%>
	<%@include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("#sub_btn").click(function () {
				var errorMsg = $(".errorMsg");
				var username = $("#username").val();
				var password = $("#password").val();
				var patt = /^\w{5,12}$/;
				var flag = patt.test(username);
				if(!flag){
				errorMsg.text("用户名格式错误！");
				return false;
				}
				flag = patt.test(password);
				if(!flag){
					errorMsg.text("密码格式错误！");
					return false;
				}
			})
		})
	</script>
</head>
<body>

		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">${empty requestScope.message ? "请输入用户名和密码" : requestScope.message}</span>
							</div>
							<div class="form">
								<form action="/book/userServlet" method="post">
								<!--<form action="login_success.jsp">-->
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"  id="username"
										   value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<input type="hidden" name="action" value="login">
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
