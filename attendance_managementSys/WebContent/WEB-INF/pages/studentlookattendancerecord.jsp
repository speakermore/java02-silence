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
		<title>学生查看考勤记录页面</title>
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
			#attendance_foot{margin-top: 200px;}
			#main-attend,#head-main{margin-top: 50px;}
		</style>
</head>
<body>
		<!--学生查看考勤记录主体开始-->
		<div id="man_atten" class="container-fluid">
			<div class="row">
				<!--头部信息开始-->
				<div class="row">
					<div id="stu_info" class="col-sm-6">
						<h3><strong>XXX</strong>的考勤记录</h3></div>
					<div id="attendance_rate" class="col-sm-6"><button type="submit" class="btn btn-default btn-lg">查看出勤率</button></div>
				</div>
				<!--头部信息结束-->

				<!--显示内容开始-->
				<!--显示内容头部开始-->
				<form id="head-main" class="form-inline ">
					<div class="form-group">
						<label for="exampleInputName2">考勤时间：</label>
						<input class="date_test" id="date_test" name="" />
					</div>
					<button type="submit" class="btn btn-default">GO</button>
				</form>
				<!--显示内容头部结束-->

				<!--显示内容主体开始-->
				<div id="main-attend">
					<ul class="list-unstyled">
						<li>
							<a href="">
								<span class="col-sm-2">学号</span>
								<span class="col-sm-2">姓名</span>
								<span class="col-sm-3">班级姓名</span>
								<span class="col-sm-5">考勤时间</span>
							</a>
						</li>
						<li>
							<a href=""><span class="col-sm-2">201232322</span>
								<span class="col-sm-2">张三</span>
								<span class="col-sm-3">计算机班</span>
								<span class="col-sm-5">2016-8-12:08:29:23</span></a>
						</li>
						<li>
							<a href=""><span class="col-sm-2">201232322</span>
								<span class="col-sm-2">张三</span>
								<span class="col-sm-3">计算机班</span>
								<span class="col-sm-5">2016-8-12:08:29:23</span></a>
						</li>
						<li>
							<a href=""><span class="col-sm-2">201232322</span>
								<span class="col-sm-2">张三</span>
								<span class="col-sm-3">计算机班</span>
								<span class="col-sm-5">2016-8-12:08:29:23</span></a>
						</li>
					</ul>

				</div>
				<!--显示内容主体结束-->
				<!--显示内容尾部开始-->
				<div id="attendance_foot" class="row">
					<div class="col-sm-4">
						<ul class="pagination">
							<li><span>共12条记录&nbsp;1/5页</span></li>
							<li>
								<a href="#"><span>首页</span></a>
							</li>
							<li>
								<a href="#"><span>上一页</span></a>
							</li>
							<li>
								<a href="#"><span>下一页</span></a>
							</li>
							<li>
								<a href="#"><span>尾页</span></a>
							</li>
						</ul>
					</div>
					<form class="navbar-form navbar-left" role="search">
						<span>跳转至：</span>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="页码">
						</div>
						<button type="submit" class="btn btn-default">GO</button>
					</form>
				</div>
				<!--显示内容尾部结束-->
				<!--显示内容结束-->
			</div>
		</div>
		<!--学生查看考勤记录主体结束-->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<script src="thirdpart/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="thirdpart/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script>
    		$(function (){
    			$(".date_test").datetimepicker({
    				language:"zh-CN",
    				autoclose:"true",
    				todayBtn:true,
    				startView:3,
    				minView:"year",
    				format:"yyyy-mm-dd hh:ii" //选中之后显示到的时间级别
    			});
    		});
   		 </script>
</body>
</html>