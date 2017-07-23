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
		<title>老师查看学生考勤记录页面</title>
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
			#verifyStuExistInfo{
			margin-left: 700px
			}
		</style>
</head>
<body>
		<!--老师查看考勤记录主体开始-->
		<div id="man_atten" class="container-fluid">
			<div class="row">
				<!--头部信息开始-->
				<div class="row">
					<div id="stu_info" class="col-sm-6">
						<h3>欢迎来到查看考勤记录页面</h3></div>
					<div id="attendance_rate" class="col-sm-6"><button type="submit" class="btn btn-default btn-lg">查看出勤率</button></div>
				</div>
				<!--头部信息结束-->

				<!--显示内容开始-->
				<!--显示内容头部开始-->
				<form id="head-main" class="form-inline ">
					<div class="form-group">
						<label for="attendanceTime">考勤时间：</label>
						<input id="attendanceTime1" name="attendanceTime1" class="date_test form-control" />&nbsp;-&nbsp;<input id="attendanceTime2" name="attendanceTime2" class="date_test form-control" />
					</div>
					<div class="form-group">
						<label for="stuClass">班级：</label>
						<select id="stuClass" name="stuClass" class="form-control">
							<option value="0">--请选择查看的班级--</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</div>
					<div class="form-group">
						<label for="stuNo">学号：</label>
						<input id="stuNo" name="stuNo" class="form-control" type="text" value="" placeholder="请输入要查看的学生学号"/>
					</div>
					<div class="form-group">
						<label for="stuName">姓名：</label>
						<input id="stuName" name="stuName" class="form-control" type="text" value="" placeholder="请输入要查看的学生姓名"/>
					</div>
					<button id="btn1" name="btn1" type="button" class="btn btn-default">GO</button>
				</form>
				<div id="verifyStuExistInfo"></div>
				<!--显示内容头部结束-->

				<!--显示内容主体开始-->
				<div id="main-attend">
					<ul class="list-unstyled">
						<li>
							<a href="">
								<span class="col-sm-2">学号</span>
								<span class="col-sm-1">姓名</span>
								<span class="col-sm-3">班级名称</span>
								<span class="col-sm-3">到校时间</span>
								<span class="col-sm-3">离校时间</span>
							</a>
						</li>
						<div id="main-attendContent">
							<li>
								<a href=""><span class="col-sm-2">201232322</span>
									<span class="col-sm-1">张三</span>
									<span class="col-sm-3">计算机班</span>
									<span class="col-sm-3">2016-8-12:08:29:23</span>
									<span class="col-sm-3">2016-8-12:08:29:23</span>
								</a>
							</li>
							<li>
								<a href=""><span class="col-sm-2">201232322</span>
									<span class="col-sm-1">张三</span>
									<span class="col-sm-3">计算机班</span>
									<span class="col-sm-3">2016-8-12:08:29:23</span>
									<span class="col-sm-3">2016-8-12:08:29:23</span></a>
							</li>
							<li>
								<a href=""><span class="col-sm-2">201232322</span>
									<span class="col-sm-1">张三</span>
									<span class="col-sm-3">计算机班</span>
									<span class="col-sm-3">2016-8-12:08:29:23</span>
									<span class="col-sm-3">2016-8-12:08:29:23</span></a>
							</li>
						</div>
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
						<button id="btn2" name="btn2" type="submit" class="btn btn-default">GO</button>
					</form>
				</div>
				<!--显示内容尾部结束-->
				<!--显示内容结束-->
			</div>
		</div>
		<!--老师查看考勤记录主体结束-->
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
	    				startView:2,
	    				minView:"year",
	    				format:"yyyy-mm-dd" //选中之后显示到的时间级别
	    			});
	    			
	    			$("#stuClass").blur(function () {
	    				if($("#stuClass").val()!="0" && $("#stuNo").val()!=null && $("#stuNo").val()!=""){
		    				$.ajax({
	    						url:"tecAttendance/verifyStuExist",
	    						type:"post",
	    						dataType:"json",
	    						data:{"id":$("#stuClass").val(),"stuNo":$("#stuNo").val()},
	    						success:function(data){
	    							if(!data.succeed){
	    								$("#stuName").attr("disabled","true");
	    								$("#btn1").attr("disabled","true");
	        							$("#verifyStuExistInfo").html(data.message);
	    							}else{
	    								$("#stuName").removeAttr("disabled");
	    								$("#btn1").removeAttr("disabled");
	        							$("#verifyStuExistInfo").html("");
	    							}
	    						}
		    				});	
	    				}else{
	    					$("#verifyStuExistInfo").html("");
							$("#stuName").removeAttr("disabled");
	    					$("#btn1").removeAttr("disabled");
	    				}
	    			});
	    			
	    			$("#stuNo").blur(function () {
	    				if($("#stuClass").val()!="0" && $("#stuNo").val()!=null && $("#stuNo").val()!=""){
		    				$.ajax({
	    						url:"tecAttendance/verifyStuExist",
	    						type:"post",
	    						dataType:"json",
	    						data:{"id":$("#stuClass").val(),"stuNo":$("#stuNo").val()},
	    						success:function(data){
	    							if(!data.succeed){
	    								$("#stuName").attr("disabled","true");
	    								$("#btn1").attr("disabled","true");
	        							$("#verifyStuExistInfo").html(data.message);
	    							}else{
	    								$("#stuName").removeAttr("disabled");
	    								$("#btn1").removeAttr("disabled");
	        							$("#verifyStuExistInfo").html("");
	    							}
	    						}
		    				});	
	    				}else{
	    					$("#verifyStuExistInfo").html("");
							$("#stuName").removeAttr("disabled");
	    					$("#btn1").removeAttr("disabled");
	    				}
	    			});
	    			$("#btn1").click(function () {
	    				$.ajax({
	    					url:"tecAttendance/lookAttendanceRecord",
	    					type:"post",
	    					data:{"attendanceTime1":$("#attendanceTime1").val(),"attendanceTime2":$("#attendanceTime2").val(),"id":$("#stuClass").val(),"stuNo":$("#stuNo").val(),"stuName":$("#stuName").val()},
	    					dataType:"json",
	    					success:function(data){
	    						var content = $("#main-attendContent");
	    						content.empty();
	    						for(var i = 0 ;i < data.length;i++){
	    							$("<li><span class='col-sm-2'>"+data[i].stuNo+
	    									"</span><span class='col-sm-1'>"+data[i].stuName+
	    									"</span><span class='col-sm-3'>"+data[i].className+
	    									"</span><span class='col-sm-3'>"+data[i].attendanceComeTime+
	    									"</span><span class='col-sm-3'>"+data[i].attendanceBackTime+
	    									"</span></li>").appendTo(content);
	    						}
	    					}
	    				});
					}); 
	    		});
	    </script>
</body>
</html>