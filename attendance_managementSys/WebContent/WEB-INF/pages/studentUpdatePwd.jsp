<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 获得在地址栏访问时的项目的绝对路径，具体的访问时要拼上提交的表单的action属性的值， -->
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>学生修改登录密码页面</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	body{
    		margin: 50px;
    	}
    </style>
  </head>
<body>
	<div class="container">
  		<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">修改登录密码</h3>
				</div>
				<div class="panel-body">
					<form action="stuAttendance/updateStuPwd" role="form" class="form-horizontal" method="post">
				<!--把标签和控件放在一个带有 class .form-group 的 <div> 中。这是获取最佳间距所必需的。-->
						<div class="form-group">
							<!--control-label是控制label标签内容的显示以及加粗，col-xs-xx是控制label占多少个单元，居右显示-->
							<label for="studentNo" class="control-label col-xs-4">学号：</label>
							<div class="col-xs-4">
								<!--form-control是对控制文本框圆角、宽度和高度-->
								<input type="text" id="studentNo" name="stuNo" class="form-control" value="${student.stuNo}"/>
							</div>
						</div>
						<div class="form-group">
							<!--control-label是控制label标签内容的显示以及加粗，col-xs-xx是控制label占多少个单元，居右显示-->
							<label for="oldPwd" class="control-label col-xs-4">旧密码：</label>
							<div class="col-xs-4">
								<!--form-control是对控制文本框圆角、宽度和高度-->
								<input type="password" id="oldPwd" name="stuPwd" class="form-control" value="${student.stuPwd}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="newPwd" class=" control-label col-xs-4">新密码：</label>
							<div class="col-xs-4">
								<input type="password" id="newPwd" name="stuNewPwd" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="confirmPwd" class=" control-label col-xs-4">确认密码：</label>
							<div class="col-xs-4">
								<input type="password" id="confirmPwd" name="stuRePwd" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="captcha" class="control-label col-xs-4">验证码：</label>
							<div class="col-xs-2" style="display: inline;">
								<input type="text" id="captcha" class="form-control" />
							</div>
							<div style="display: inline;">
								<label class="left"></label>
								<img src="" title="看不清，换一张" alt="看不清，换一张" />&nbsp;&nbsp;<a>换一张</a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-4">
								<input type="submit" class="btn btn-primary col-xs-5" style="margin-left:35px;" value="提交" />
							</div>
						</div>
						<div id="remind">${message}</div>
					</form>
				</div>
			</div>
  	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>