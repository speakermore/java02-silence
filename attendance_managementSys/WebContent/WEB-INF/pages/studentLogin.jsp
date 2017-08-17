<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 获得在地址栏访问时的项目的绝对路径，具体的访问时要拼上提交的表单的action属性的值， -->
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>登录页面</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link type="text/css" rel="stylesheet" href="../css/register3.css" />
<style type="text/css">
body {
	margin: 50px;
	background: rgb(247, 248, 250);
}
</style>
</head>
<body>
	<article class="container">
		<!--登录开始-->
		<div class="row" style="width: 450px; margin-left: 300px;">
			<h3>
				<strong><span style="color: #337AB7;">用户登录</span>&nbsp;&nbsp;<em><span
						style="color: #ADADAD;">/&nbsp;&nbsp;LOGIN</span></em></strong>
			</h3>
			<hr />
		</div>
		<div class="row">
			<!--form-horizontal是调节该表单是行内元素还是块元素，有form-horizontal和form-inline-->
			<form method="post" action="stuAttendance/studentLogin" role="form" class="form-horizontal" target="questions">
				<!--把标签和控件放在一个带有 class .form-group 的 <div> 中。这是获取最佳间距所必需的。-->
				<div class="form-group">
					<!--control-label是控制label标签内容的显示以及加粗，col-xs-xx是控制label占多少个单元，居右显示-->
					<label for="uName" class="control-label col-xs-4">学号：</label>
					<div class="col-xs-4">
						<!--form-control是对控制文本框圆角、宽度和高度-->
						<input type="text" id="stuNo" name="stuNo" class="form-control" value="" />
					</div>
					<div class="col-xs-4">
						${info}
					</div>
				</div>
				<div class="form-group">
					<label for="uPwd" class=" control-label col-xs-4">密码：</label>
					<div class="col-xs-4">
						<input type="password" id="uPwd" name="stuPwd" class="form-control" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="captcha" class="control-label col-xs-4">验证码：</label>
					<div class="col-xs-2" style="display: inline;">
						<input type="text" id="captcha" name="captcha" class="form-control" />
					</div>
					<div style="display: inline;">
						<label class="left"></label> <img src="" title="看不清，换一张"
							alt="看不清，换一张" />&nbsp;&nbsp;<a>换一张</a>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-4">
						<input type="submit" name="submit" class="btn btn-primary col-xs-5"
							style="margin-left: 35px;" value="登录" />
					</div>
				</div>
			</form>
		</div>
	</article>
	<!--登录结束-->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>