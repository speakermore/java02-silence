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
		<title>修改工作日志页面</title>
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		<style>
			#attendance_foot form {
				margin-left: -80px;
			}
			
			#attendance_foot {
				margin-top: 200px;
			}
			
			#main-attend,
			#head-main {
				margin-top: 50px;
			}
		</style>
</head>
<body>
		<!--学生修改工作日志主体开始-->
		<div id="man_atten" class="container-fluid">
			<div class="row">
					<div id="stu_info" class="col-sm-6"><h3>欢迎来到修改工作日志页面</h3></div>
			</div>
			<div class="row clearfix">
				<div class="col-sm-12 column">
					<div class="page-header">
						<h1>
					请填写如下信息： 
				</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<!--填写工作日志信息开始-->
				<form class="form-horizontal">
					<div class="form-group">
						<label for="inputID" class="col-sm-2 control-label">学生ID</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="inputID" placeholder="学生ID">
						</div>
					</div>
					<div class="form-group">
						<label for="inputName" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="inputName" placeholder="姓名">
						</div>
					</div>
					<div class="form-group">
						<label for="inputDairy" class="col-sm-2 control-label">日志内容</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="inputDairy" placeholder="日志内容"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="inputQuestion" class="col-sm-2 control-label">提问</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="inputQuestion" placeholder="提问"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">保存</button>
							<button type="submit" class="btn btn-default">重置</button>
						</div>
					</div>
				</form>
				<!--填写工作日志信息结束-->
			</div>
		</div>
		<!--学生修改工作日志主体结束-->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
</body>
</html>