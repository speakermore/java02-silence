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
		<title>显示具体工作日志页面</title>
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		<style>
			#dairy_main2{
				margin-top: 30px;
			}
			
			#dairy_content,#dairy_question {
				height: 300px;
				width: 600px;
			}
		</style>
</head>
<body>
		<!--查看具体的工作日志开始-->
		<div id="man_atten" class="container-fluid">
			<div class="row">
					<div id="stu_info" class="col-sm-6"><h3>欢迎来到查看工作日志页面</h3></div>
			</div>
			<!--显示主体日志信息开始-->
			<div id="dairy_main1" class="row">
				<div class="col-sm-2">学号：<span id="stuId">20132112</span></div>
				<div class="col-sm-2">姓名：<span id="stuName">张三</span></div>
				<div class="col-sm-2">班级编号：<span id="stuClassId">01</span></div>
				<div class="col-sm-6">提交时间：<span id="commitTime">2012-12-3-12:00</span></div>
			</div>
			<div id="dairy_main2" class="row">
				<div class="col-sm-6">日志内容：<textarea id="dairy_content">dxmfjeresxmkld,xmkjfcmxz,slkdsmjcnskxm,zdkjsdsjkdskddk</textarea></div>
				<div class="col-sm-6">提问内容：<textarea id="dairy_question">十万个为什么？钢铁是怎样炼成的？.......</textarea></div>
			</div>
			<!--显示主体日志信息结束-->
		</div>
		<!--查看具体的工作日志结束-->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
</body>
</html>