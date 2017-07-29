<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- base获得在地址栏访问时项目的绝对路径（这里获得的路径是需要加上表单里action提交的地址的，目的是：这样就可以锁定到控制器相应的方法上，然后就可以做相应的处理） -->
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>老师登录成功的页面</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.btn {
	margin-left: 50px;
	margin-top: 200px;
}
#top{
	margin-left: 200px;
}
#bottom{
	padding-left: 20px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div id="tecLogin">${tecLogin }</div>
		<div class="row" id="top">
		<button type="button" class="btn btn-info col-sm-4">给学生注册</button>
		<button type="button" class="btn btn-info col-sm-4">查看学生考勤记录</button>
		</div>
		<div class="row" id="bottom">
		<button type="button" class="btn btn-info col-sm-3">查看学生工作日志</button>
		<button type="button" class="btn btn-info col-sm-4">修改密码</button>
		<button type="button" class="btn btn-info col-sm-3">查看学生考勤率</button>
		</div>
	</div>
	<div id="tecUpdatePwd" class="col-sm-3">${update}</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function () {
			$("button:eq(0)").click(function () {
				window.location.href="tecAttendance/reg";
			})
			$("button:eq(3)").click(function () {
				window.location.href="tecAttendance/jumpUpdatePage";
			});
			$("button:eq(1)").click(function () {
				window.location.href="tecAttendance/jumpLookAttendanceRecord?curPage=1";
			});
		});
	</script>
</body>
</html>