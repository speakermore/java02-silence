<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- base获得在地址栏访问时项目的绝对路径（这里获得的路径是需要加上表单里action提交的地址的，目的是：这样就可以锁定到控制器相应的方法上，然后就可以做相应的处理） -->
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>学生查看出勤率</title>
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
				<h3><strong>${student.stuName}</strong>的出勤率</h3></div>
				<!--头部信息结束-->

				<!--显示内容开始-->
				<!--显示内容头部开始-->
				<form id="head-main" class="form-inline " action="stuAttendance/selectStuAttendanceRecordByTime" method="post">
					<div class="form-group">
						<label for="exampleInputName2">出勤率时间：</label>
						<select class="form-control" id="time" name="choice">
							<c:if test="${choice==null}">
								<option value="0">--请选择查看出勤率的时间--</option>
								<option value="1">本周</option>
								<option value="2">上周</option>
								<option value="3">本月</option>
								<option value="4">上月</option>
							</c:if>
						<%-- 	<c:if test="${choice==0&&choice!=null}">
								<option value="0">--请选择查看考勤的时间--</option>
								<option value="1">本周</option>
								<option value="2">上周</option>
								<option value="3">本月</option>
								<option value="4">上月</option>
							</c:if> --%>
							<c:if test="${choice!=null}">
								<c:forEach items="${choiceTime}" var="c">
								<option value="${c.id}">${c.times}</option>
								</c:forEach>
							</c:if>	
						</select>
					</div>
					<button id="btn" type="submit" class="btn btn-default">GO</button>
					 <input type="hidden" name="stuId" value="${student.id}"/>
					<input type="hidden" name="classId" value="${student.classId}"/>
					<input type="hidden" name="curPage" value="1"/> 
				</form>
				<!--显示内容头部结束-->

				<!--显示内容主体开始-->
				<div id="main-attend">
					<ul class="list-unstyled">
						<li>
								<span class="col-sm-2">学号</span>
								<span class="col-sm-1">姓名</span>
								<span class="col-sm-3">班级姓名</span>
								<span class="col-sm-3">出勤率</span>
						</li>
						<c:forEach items="${attendanceRecord}" var="a">
						<li>
						 	<span class="col-sm-2">${a.stuNo}</span>
							<span class="col-sm-1">${a.stuName}</span>
							<span class="col-sm-3">${a.className}</span>
							<span class="col-sm-3"><fmt:formatDate value="${a.attendanceComeTime}" pattern="yyyy年MM月dd日 HH:mm:ss" /></span>
						</li>
					  </c:forEach>
					</ul>

				</div>
				<!--显示内容主体结束-->
				<!--显示内容尾部开始-->
				<div id="attendance_foot" class="row">
					<div class="col-sm-4">
						<ul class="pagination">
							<li><span>共${maxRecord}条记录&nbsp;${curPage}/${maxPage}页</span></li>
							<li>
								<c:if test="${choice==null}">
								<a href="stuAttendance/selectStuAttendanceRecord?stuId=${student.id}&classId=${student.classId}&curPage=1"><span>首页</span></a>
								</c:if>
								<c:if test="${choice!=null}">
								<a href="stuAttendance/selectStuAttendanceRecordByTimes?stuId=${student.id}&classId=${student.classId}&choice=${choice}&curPage=1"><span>首页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${choice==null}">
								<a href="stuAttendance/selectStuAttendanceRecord?stuId=${student.id}&classId=${student.classId}&curPage=${curPage-1}"><span>上一页</span></a>
								</c:if>
								<c:if test="${choice!=null}">
								<a href="stuAttendance/selectStuAttendanceRecordByTimes?stuId=${student.id}&classId=${student.classId}&choice=${choice}&curPage=${curPage-1}"><span>上一页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${choice==null}">
								<a href="stuAttendance/selectStuAttendanceRecord?stuId=${student.id}&classId=${student.classId}&curPage=${curPage+1}"><span>下一页</span></a>
								</c:if>
								<c:if test="${choice!=null}">
								<a href="stuAttendance/selectStuAttendanceRecordByTimes?stuId=${student.id}&classId=${student.classId}&choice=${choice}&curPage=${curPage+1}"><span>下一页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${choice==null}">
								<a href="stuAttendance/selectStuAttendanceRecord?stuId=${student.id}&classId=${student.classId}&curPage=${maxPage}"><span>尾页</span></a>
								</c:if>
								<c:if test="${choice!=null}">
								<a href="stuAttendance/selectStuAttendanceRecordByTimes?stuId=${student.id}&classId=${student.classId}&choice=${choice}&curPage=${maxPage}"><span>尾页</span></a>
								</c:if>
							</li>
						</ul>
					</div>
					<form class="navbar-form navbar-left" role="search" action="stuAttendance/selectStuAttendanceRecord" method="get">
						<span>跳转至：</span>
						<div class="form-group">
							<input type="text" name="curPage" class="form-control" placeholder="页码" value="">
							<input type="hidden" name="stuId" value="${student.id}"/>
							<input type="hidden" name="classId" value="${student.classId}"/>
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
		<!-- <script type="text/javascript">
		 $(function() {
			$("#btn").click(function() {
				var choice=$("#time").val();
				$.ajax({
					url:"stuAttendance/selectStuAttendanceRecordByTime",
					type:"post",
					data:{"stuId":${student.id},"classId":${student.classId},"choice":choice,"curPage":1},
					dataType:"json",
					success:function(data){
						$("#time").attr("value",data.choice);
					}
				})
			});
		}); 
		</script> -->
</body>
</html>