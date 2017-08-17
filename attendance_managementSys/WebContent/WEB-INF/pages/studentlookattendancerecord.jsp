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
			#attendance_foot{margin-top: 160px;}
			#main-attend,#head-main{margin-top: 10px;}
			#b{position: relative;top: -35px;left:1100px;font-size: 18px;text-decoration: none;color: black;font-weight: bold;}
		</style>
</head>
<body>
		<!--学生查看考勤记录主体开始-->
		<div id="man_atten" class="container-fluid">
			<div class="row">
				<!--头部信息开始-->
				<h3>${student.stuName}的考勤记录</h3>
				<a href="stuAttendance/student" id="b">返回</a>
				<!--头部信息结束-->
			</div>

				<!--显示内容开始-->
				<!--显示内容头部开始-->
				<form id="head-main" class="form-inline " action="stuAttendance/selectStuAttendanceRecordByTime" method="post">
					<div class="form-group">
						<label for="exampleInputName2">选择查看考勤记录的时间：</label>
						<select class="form-control" id="time" name="choice">
								<option value="0" <c:if test="${choice=='0'}">selected</c:if>>--请选择查看考勤的时间--</option>
								<option value="1" <c:if test="${choice=='1'}">selected</c:if>>本周</option>
								<option value="2" <c:if test="${choice=='2'}">selected</c:if>>上周</option>
								<option value="3" <c:if test="${choice=='3'}">selected</c:if>>本月 </option>
								<option value="4" <c:if test="${choice=='4'}">selected</c:if>>上月</option>
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
								<span class="col-sm-3">到校时间</span>
								<span class="col-sm-3">离校时间</span>
						</li>
						<c:if test="${info==''}">
							<c:forEach items="${attendanceRecord}" var="a">
							<li>
							 	<span class="col-sm-2">${a.stuNo}</span>
								<span class="col-sm-1">${a.stuName}</span>
								<span class="col-sm-3">${a.className}</span>
								<span class="col-sm-3"><fmt:formatDate value="${a.attendanceComeTime}" pattern="yyyy年MM月dd日 HH:mm:ss" /></span>
								<span class="col-sm-3"><fmt:formatDate value="${a.attendanceBackTime}" pattern="yyyy年MM月dd日 HH:mm:ss" /></span>
							</li>
						  </c:forEach>
					  </c:if>
					  <c:if test="${info=='此时段没有考勤记录。'}">
							<li>${info}</li>
					  </c:if>
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
					<c:if test="${choice==null}">
						<form class="navbar-form navbar-left" role="search" action="stuAttendance/selectStuAttendanceRecord" method="get">
							<span>跳转至：</span>
							<div class="form-group">
								<input type="text" id="page"  name="curPage" class="form-control" placeholder="页码" value="">
								<input type="hidden" name="stuId" value="${student.id}"/>
								<input type="hidden" name="classId" value="${student.classId}"/>
							</div>
								<button type="submit" name="bottomGo" class="btn btn-default">GO</button>
								<span id="goInfo"></span>
						</form>
					</c:if>
					<c:if test="${choice!=null}">
						<form class="navbar-form navbar-left" role="search" action="stuAttendance/selectStuAttendanceRecordByTime" method="post">
							<span>跳转至：</span>
							<div class="form-group">
								<input type="text" id="page" name="curPage" class="form-control" placeholder="页码" value="">
								<input type="hidden" name="stuId" value="${student.id}"/>
								<input type="hidden" name="classId" value="${student.classId}"/>
								<input type="hidden" name="choice" value="${choice}"/>
							</div>
								<button type="submit" name="bottomGo" class="btn btn-default">GO</button>
								<span id="goInfo"></span>
						</form>
					</c:if>
				</div>
				<!--显示内容尾部结束-->
				<!-- 显示出勤率开始-->
				<form  class="form-inline " action="stuAttendance/selectStuAttendanceRate" method="post">
					<div class="form-group">
						<label for="exampleInputName2">选择查看出勤率的时间：</label>
						<input id="choiceTime1" name="choiceTime1" class="date_test form-control" value="${choiceTime1}"  />
						<input id="choiceTime2" name="choiceTime2" class="date_test form-control" value="${choiceTime2}""  />
					</div>
					<button id="btn" type="submit" class="btn btn-default">GO</button>
					<input type="hidden" name="stuId" value="${student.id}"/>
					<input type="hidden" name="classId" value="${student.classId}"/>
				</form>
				<div >
					<ul class="list-unstyled">
						<li>
								<span class="col-sm-3">学号</span>
								<span class="col-sm-3">姓名</span>
								<span class="col-sm-3">班级姓名</span>
								<span class="col-sm-3">出勤率</span>
						</li>
						<li>
						 	<span class="col-sm-3">${attStu[0].stuNo}</span>
							<span class="col-sm-3">${attStu[0].stuName}</span>
							<span class="col-sm-3">${attStu[0].className}</span>
							<span class="col-sm-3">${attRate}</span>
						</li>
					</ul>
				</div>
				<!-- 显示出勤率结束 -->
				<!--显示内容结束-->
			</div>
		<!--学生查看考勤记录主体结束-->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<script src="thirdpart/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
		<script src="thirdpart/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script type="text/javascript">
			$(function () {    //验证输入的页码格式正不正确
				$("#page").bind('input propertychange',function() {
					var page=$("#page").val();
					var ex = /^\d+$/;
					if(!(ex.test(page))){//如果不是整数
						$("[name='bottomGo']").attr("disabled","true");
						$("#goInfo").html("请输入一个正整数！");
					}
					if(ex.test(page)){  //如果是整数
						$("[name='bottomGo']").removeAttr("disabled");
					}
				});
				
				/*时间插件*/
				$(".date_test").datetimepicker({
				language:"zh-CN",		//"zh-CN"表示显示中文
				format:"yyyy-mm-dd",    //非常重要的并且常用的参数：格式化显示的日期格式，eg:2014-02-09
				autoclose:"true",		//"true"表示点击完时间之后自动关闭视图出口
				todayBtn:"true",    //添加一个“今天”按钮，点击之后在文本框显示今天的时间
				startView:2,       //日期时间选择器打开之后首先显示的视图。(3: 'year' for the 12-month overview)
				minView:"year"     //日期时间选择器所能够提供的最精确的时间选择视图。
				});
			});
		</script>
</body>
</html>