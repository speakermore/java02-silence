<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- base获得在地址栏访问时项目的绝对路径（这里获得的路径是需要加上表单里action提交的地址的，目的是：这样就可以锁定到控制器相应的方法上，然后就可以做相应的处理） -->
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />   
<!DOCTYPE html >
<html>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>学生首页</title>
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		<style>
			#zhengwen {
				margin-top: 10px;
			}
			
			#btn2 {
				margin-left: 500px;
			}
			
			#lists {
				margin-top: 20px;
			}
			
			#listRight {
				border: 1px solid darkgray;
			}
		</style>
	</head>
<body>
	<div id="top" class="container-fluid">
		<!--头部导航开始-->
		<div class="row">
			<!--头部左边导航开始-->
			<div id="topLeft" class="col-sm-7">
				<div class="row">
					<ul class="nav navbar-nav nav-pills">
						<li><img src="#" alt="学生头像" class="img-thumbnail"
							height="100px" width="100px" /></li>
						<li class="active"><a href="#"> <span
								class="badge pull-right">+42</span> 点赞
						</a></li>
						<li><a href="#"> <span class="badge pull-right">+100</span>
								积分
						</a></li>
					</ul>
				</div>
			</div>
			<!--头部左边导航结束-->
			<!--头部右边导航开始-->
			<div>${student.id}</div><span>${student.classId}</span>
			<div id="topRight" class="col-sm-5">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="stuAttendance/selectStuAttendanceRecord?stuId=${student.id}&classId=${student.classId}&curPage=1">查看考勤记录</a></li>
					<li><a href="stuAttendance/selectStuAttendanceRecord?stuId=${student.id}&classId=${student.classId}&curPage=1">查看出勤率</a></li>
					<li><a href="stuAttendance/updateStuPwd">修改密码</a></li>
				</ul>
			</div>
			<!--头部右边导航结束-->
		</div>
		<!--头部导航结束-->
	</div>

	<!--正文开始-->
	<div id="zhengwen" class="container-fluid">
		<!--考勤按钮开始-->
		<div id="btn2" class="row">
			<div class="col-sm-3">
				<button type="button" id="come" class="btn btn-success">签到</button>
				<div id="comeInfo"></div>
			</div>
			<div class="col-sm-3">
				<button type="button" id="back" class="btn btn-success">签退</button>
				<div id="leaveInfo"></div>
			</div>
		</div>
		<!--考勤按钮结束-->
		<!--列表内容开始-->
		<div id="lists" class="row">
			<div id="listLeft" class="col-sm-2">
				<div class="list-group">
					<a href="#" class="list-group-item active"> 提问 </a> <a href="#"
						class="list-group-item">工作日志</a>
				</div>
			</div>
			<div id="listRight" class="col-sm-10"
				style="height: 300px; width: 1000px;">提问答复页面</div>
		</div>
		<!--列表内容结束-->
	</div>
	<!--正文结束-->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#come").click(function() {    //签到的AJAX
				$.ajax({
					url:"stuAttendance/insertComeTime",
					type:"post",
					data:{"stuId":${student.id}},
					dataType:"json",    //返回的数据类型是map所以最好用JSON
					success:function(data){
						if(data.success==0){
							$("#comeInfo").html(data.info);
							$("#come").attr("disabled","true");
						}else if(data.success==1){
							$("#comeInfo").html(data.info);
							$("#come").removeAttr("disabled");
						}else{
							$("#comeInfo").html(data.info);
							$("#come").attr("disabled","true");
						}
					}
				});
			});
			
			$("#back").click(function() {    //签退AJAX
				$.ajax({
					url:"stuAttendance/saveBackTime",
					type:"post",
					data:{"stuId":${student.id}},
					dataType:"json",
					success:function(data){
						if(data.success==0){
							$("#leaveInfo").html(data.message);
							$("#back").attr("disabled","true");
						}else if(data.success==1){
							$("#leaveInfo").html(data.message);
							$("#back").removeAttr("disabled");
						}else{
							$("#leaveInfo").html(data.message);
							$("#back").attr("disabled","true");
							
						}
					}
				});
			});
		});
	</script>
</body>
</html>