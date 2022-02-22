<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员注册页面</title>
	<%--静态包含 base标签 css样式和jquery文件--%>
	<%@include file="/pages/common/header.jsp"%>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}
	</style>
	<script type="text/javascript">
		$(function () {


			$("#username").blur(function () {
				var serialize = $("#form_01").serialize();
				$.get("${basePath}userServlet","action=usernameIsOk&"+serialize,function (msg) {
						$(".errorMsg").html(msg)
				},"text")
			});

			$("#code").click(function () {
				this.src = "${basePath}kaptcha.jpg?"+new Date();
			});

			$("#sub_btn").click(function () {
				var errorMsg = $(".errorMsg");
				//1.验证用户名是否合法
				var username = $("#username").val();
				var patt = /^\w{5,12}$/;
				if(!patt.test(username)){
					errorMsg.text("用户名不合法！");
					return false;
				}
				//2.验证密码是否合法
				var password = $("#password").val();
				if(!patt.test(password)){
					errorMsg.text("密码不合法！");
					return false
				}
				//3.验证确认密码是否合法
				var rePassword = $("#repwd").val();
				if(rePassword !== password){
					errorMsg.text("确认密码错误！");
					return false;
				}
				//4.验证邮箱是否合法
				var email = $("#email").val();
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if (!emailPatt.test(email)){
					errorMsg.text("邮箱格式不正确！");
					return false;
				}
				//5.确认验证码是否为空（因为验证码的验证要通过服务端，静态页面只需要）
				var verCode = $("input[name=code]").val().trim();
				if( verCode == null || verCode.length===0){
					errorMsg.text("验证码错误！");
					return false;
				}
				errorMsg.text("");
			});
		});
	</script>
</head>
<body>
<div id="login_header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

	<div id="l_content">
		<span class="login_word">欢迎注册</span>
	</div>

	<div id="content">
		<div class="login_form">
			<div class="login_box">
				<div class="tit">
					<h1>注册尚硅谷会员</h1>
					<%--TODO--%>
					<span class="errorMsg">${requestScope.message}</span>
				</div>
				<div class="form">
					<form action="/book/userServlet" method="post" id="form_01">
						<label>用户名称：</label>
						<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
							   value="${requestScope.username}"/>
						<br />
						<br />
						<label>用户密码：</label>
						<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
						<br />
						<br />
						<label>确认密码：</label>
						<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
						<br />
						<br />
						<label>电子邮件：</label>
						<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
						<br />
						<br />
						<label>验证码：</label>
						<input class="itxt" type="text" style="width: 110px;"  name="code"/>
						<img alt="" src="kaptcha.jpg" style="float: right; margin-right: 45px; height: 40px; width: 120px" id="code">
						<%--隐藏域--%>
						<input type="hidden" name="action" value="register">
						<br />
						<br />
						<input type="submit" value="注册" id="sub_btn" />

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
