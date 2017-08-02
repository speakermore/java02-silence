<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- base获得在地址栏访问时项目的绝对路径（这里获得的路径是需要加上表单里action提交的地址的，目的是：这样就可以锁定到控制器相应的方法上，然后就可以做相应的处理） -->
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<!DOCTYPE html >
<html>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>工作日志页面</title>
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
			#attendance_foot{margin-top: 15px}
			#attendance_rate{margin-top: 35px}
			#main-attend,#head-main{margin-top: 30px;}
			#main-attend ul li{border-bottom: 1px dashed gray;}
			#main-attend ul{border-bottom: 1px dashed gray;}
			h6,h3{margin-left: 16px}
			h6{color: gray; font-size: 8px;padding-top: 20px;}
		</style>
		<script type="text/javascript">
			function jump() {
				window.location.href="stuAttendance/addDiary";
			}
		</script>
</head>
<body>
		<!--学生自己的工作日志主体开始-->
		<div id="man_atten" class="container-fluid">
			<div class="row">
				<!--头部信息开始-->
					<div id="stu_info">
						<h3>欢迎来到${diarys[0].stuName}的工作日志页面</h3>
					</div>
				</div>
						<h5>学号：${diarys[0].stuNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓名：${diarys[0].stuName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 班级：${diarys[0].className}</h5>
			
				<!--头部信息结束-->

				<!--显示内容开始-->
				<!--显示内容头部开始-->
				<div class="row">
					<form id="head-main" class="form-inline col-sm-9" action="stuAttendance/selectStuDiaryByTime" method="get">
						<div class="form-group">
							<label for="exampleInputName2">工作日志查看时间：</label>
							<select class="form-control" id="time" name="choice">
								<option value="0" <c:if test="${choice=='0'}">selected</c:if>>--请选择查看时间--</option>
								<option value="1" <c:if test="${choice=='1'}">selected</c:if>>本周</option>
								<option value="2" <c:if test="${choice=='2'}">selected</c:if>>上周</option>
								<option value="3" <c:if test="${choice=='3'}">selected</c:if>>本月</option>
								<option value="4" <c:if test="${choice=='4'}">selected</c:if>>上月</option>
							</select>
						</div>
						<button id="btn" type="submit" class="btn btn-default">GO</button>
						<input type="hidden" name="stuId" value="${student.id}"/>
						<input type="hidden" name="classId" value="${student.classId}"/>
						<input type="hidden" name="curPage" value="1"/> 
					</form>
					<div id="attendance_rate" class="col-sm-3"><button type="submit" class="btn btn-default" onclick="jump()">新增工作日志</button></div>
				</div>
				<!--显示内容头部结束-->

				<!--显示内容主体开始-->
				<div id="main-attend">
					<c:if test="${info==''}">
						<ul class="list-unstyled">
							<c:forEach items="${diarys}" var="diary">
							<li>
								<c:if test="${fn:length(diary.diaryContent)<=60}">
									<span class="col-sm-11">${diary.diaryContent}</span>
								</c:if>
								<c:if test="${fn:length(diary.diaryContent)>60}">
									<span class="col-sm-11">${fn:substring(diary.diaryContent,0,59)}......</span>
								</c:if>
								<a href="stuAttendance/selectStuDiaryDetail?stuId=${diary.stuNo}&stuName=${diary.stuName}&className=${diary.className}&diaryCommitTime=${diary.diaryCommitTime}&diaryContent=${diary.diaryContent}"><span class="col-sm-1">查看/修改</span></a>
							</li>
							<h6>日志提交时间:<fmt:formatDate value="${diary.diaryCommitTime}" pattern="yyyy年MM月dd日" /></h6>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${info=='此时间段没有日志信息。'}">
						<ul class="list-unstyled">
							<li>${info}</li>
						</ul>
					</c:if>
				</div>
				<!--显示内容主体结束-->
				<!--显示内容尾部开始-->
				<div id="attendance_foot" class="row">
					<div class="col-sm-4">
						<ul class="pagination">
							<li><span>共${maxRecord}条记录&nbsp;${curPage}/${maxPage}页</span></li>
							<li>
							<c:if test="${choice==null}">
								<a href="stuAttendance/selectStuDiary?stuId=${student.id}&classId=${student.classId}&curPage=1"><span>首页</span></a>
							</c:if>
							<c:if test="${choice!=null}">
								<a href="stuAttendance/selectStuDiaryByTime?stuId=${student.id}&classId=${student.classId}&choice=${choice}&curPage=1"><span>首页</span></a>
							</c:if>
							</li>
							<li>
							<c:if test="${choice==null}">
								<a href="stuAttendance/selectStuDiary?stuId=${student.id}&classId=${student.classId}&curPage=${curPage-1}"><span>上一页</span></a>
						    </c:if>
						    <c:if test="${choice!=null}">
								<a href="stuAttendance/selectStuDiaryByTime?stuId=${student.id}&classId=${student.classId}&choice=${choice}&curPage=${curPage-1}"><span>上一页</span></a>
							</c:if>
							</li>
							<li>
							<c:if test="${choice==null}">
								<a href="stuAttendance/selectStuDiary?stuId=${student.id}&classId=${student.classId}&curPage=${curPage+1}"><span>下一页</span></a>
							</c:if>
							<c:if test="${choice!=null}">
								<a href="stuAttendance/selectStuDiaryByTime?stuId=${student.id}&classId=${student.classId}&choice=${choice}&curPage=${curPage+1}"><span>下一页</span></a>
							</c:if>
							</li>
							<li>
							<c:if test="${choice==null}">
								<a href="stuAttendance/selectStuDiary?stuId=${student.id}&classId=${student.classId}&curPage=${maxPage}"><span>尾页</span></a>
							</c:if>
							<c:if test="${choice!=null}">
								<a href="stuAttendance/selectStuDiaryByTime?stuId=${student.id}&classId=${student.classId}&choice=${choice}&curPage=${maxPage}"><span>尾页</span></a>
								</c:if>
							</li>
						</ul>
					</div>
					<c:if test="${choice==null}">
					<form class="navbar-form navbar-left" role="search" action="stuAttendance/selectStuDiary" method="get">
						<span>跳转至：</span>
						<div class="form-group">
							<input id="page"  name="curPage" type="text" class="form-control" placeholder="页码" value="">
							<input type="hidden" name="stuId" value="${student.id}"/>
							<input type="hidden" name="classId" value="${student.classId}"/>
						</div>
						<button name='bottomGo' type="submit" class="btn btn-default">GO</button>
						<span id="goInfo"></span>
					</form>
					</c:if>
					<c:if test="${choice!=null}">
					<form class="navbar-form navbar-left" role="search" action="stuAttendance/selectStuDiaryByTime" method="get">
						<span>跳转至：</span>
						<div class="form-group">
							<input id="page"  name="curPage" type="text" class="form-control" placeholder="页码" value="">
							<input type="hidden" name="stuId" value="${student.id}"/>
							<input type="hidden" name="classId" value="${student.classId}"/>
							<input type="hidden" name="choice" value="${choice}"/>
						</div>
						<button name='bottomGo' type="submit" class="btn btn-default">GO</button>
						<span id="goInfo"></span>
					</form>
					</c:if>
				</div>
				<!--显示内容尾部结束-->
				<!--显示内容结束-->
			</div>
		<!--学生自己的工作日志主体结束-->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(function () {  //验证输入的页码格式正不正确
			$("#page").bind('input propertychange',function() {
				var page=$("#page").val();
				var ex = /^\d+$/;
				if(!(ex.test(page))){//如果不是整数
					$("[name='bottomGo']").attr("disabled","true");
					$("#goInfo").html("请输入一个正整数！");
				}else{  //如果是整数
					$("[name='bottomGo']").removeAttr("disabled");
				}
			});
		});
		</script>
</body>
</html>