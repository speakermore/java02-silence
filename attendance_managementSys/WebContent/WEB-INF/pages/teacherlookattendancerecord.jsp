<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				</div>
				<!--头部信息结束-->

				<!--显示内容开始-->
				<!--显示内容头部开始-->
				<form id="head-main" class="form-inline" action="tecAttendance/lookAttendanceRecord?curPage=1" method="post">
					<div class="form-group">
						<label for="attendanceTime1">考勤时间：</label>
						<input id="attendanceTime1" name="attendanceTime1" class="date_test form-control" value="${attendanceTime1 }" />&nbsp;-&nbsp;
						<input id="attendanceTime2" name="attendanceTime2" class="date_test form-control" value="${attendanceTime2 }" />
					</div>
					<div class="form-group">
						<label for="stuClass">班级：</label>
						<select id="stuClass" name="stuClass" class="form-control" >
							<option value="0" <c:if test="${stuClass == '0'}">selected</c:if>>--请选择查看的班级--</option>
							<option value="1" <c:if test="${stuClass == '1'}">selected</c:if>>1</option>
							<option value="2" <c:if test="${stuClass == '2'}">selected</c:if>>2</option>
							<option value="3" <c:if test="${stuClass == '3'}">selected</c:if>>3</option>
							<option value="4" <c:if test="${stuClass == '4'}">selected</c:if>>4</option>
						</select>
					</div>
					<div class="form-group">
						<label for="stuNo">学号：</label>
						<input id="stuNo" name="stuNo" class="form-control" type="text" value="${stuNo }" placeholder="请输入要查看的学生学号"/>
					</div>
					<div class="form-group">
						<label for="stuName">姓名：</label>
						<input id="stuName" name="stuName" class="form-control" type="text" value="${stuName }" placeholder="请输入要查看的学生姓名"/>
					</div>
					<button id="btn1" name="btn1" type="submit" class="btn btn-default">GO</button>
				</form>
				<div id="verifyStuExistInfo"></div>
				<div id="verifyAttendanceExistInfo"></div>
				<div id="attendanceRecordInfo">${attendanceRecordInfo }</div>
				<!--显示内容头部结束-->

				<!--显示内容主体开始-->
				<div id="main-attend">
					<ul class="list-unstyled">
						<li>
							<strong>
								<span class="col-sm-2">学号</span>
								<span class="col-sm-1">姓名</span>
								<span class="col-sm-3">班级名称</span>
								<span class="col-sm-3">到校时间</span>
								<span class="col-sm-3">离校时间</span>
							</strong>
						</li>
						
						<div id="main-attendContent">
							<c:forEach items="${attendanceRecords }" var="a">
								<li>
									<span class="col-sm-2">${a.stuNo }</span>
									<span class="col-sm-1">${a.stuName }</span>
									<span class="col-sm-3">${a.className }</span>
									<span class="col-sm-3"><fmt:formatDate value="${a.attendanceComeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
									<span class="col-sm-3"><fmt:formatDate value="${a.attendanceComeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</li>
							</c:forEach>
						</div>
						
					</ul>

				</div>
				<!--显示内容主体结束-->
				<!--显示内容尾部开始-->
				<div id="attendance_foot" class="row">
					<div class="col-sm-4">
						<ul class="pagination">
							<li><span>共${maxRecord}条记录&nbsp;${curPage}/${maxPage}页</span></li>
							<li>
								<c:if test="${attendanceTime1==null&&attendanceTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
									<a href="tecAttendance/jumpLookAttendanceRecord?curPage=1"><span>首页</span></a>
								</c:if>
								<c:if test="${attendanceTime1!=null||attendanceTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
									<a href="tecAttendance/lookAttendanceRecords?attendanceTime1=${attendanceTime1 }&attendanceTime2=${attendanceTime2 }&stuClass=${stuClass }&stuNo=${stuNo }&stuName=${stuName }&curPage=1"><span>首页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${attendanceTime1==null&&attendanceTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
									<a href="tecAttendance/jumpLookAttendanceRecord?curPage=${curPage-1}"><span>上一页</span></a>
								</c:if>
								<c:if test="${attendanceTime1!=null||attendanceTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
									<a href="tecAttendance/lookAttendanceRecords?attendanceTime1=${attendanceTime1 }&attendanceTime2=${attendanceTime2 }&stuClass=${stuClass }&stuNo=${stuNo }&stuName=${stuName }&curPage=${curPage-1}"><span>上一页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${attendanceTime1==null&&attendanceTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
									<a href="tecAttendance/jumpLookAttendanceRecord?curPage=${curPage+1}"><span>下一页</span></a>
								</c:if>
								<c:if test="${attendanceTime1!=null||attendanceTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
									<a href="tecAttendance/lookAttendanceRecords?attendanceTime1=${attendanceTime1 }&attendanceTime2=${attendanceTime2 }&stuClass=${stuClass }&stuNo=${stuNo }&stuName=${stuName }&curPage=${curPage+1}"><span>下一页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${attendanceTime1==null&&attendanceTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
									<a href="tecAttendance/jumpLookAttendanceRecord?curPage=${maxPage}"><span>尾页</span></a>
								</c:if>
								<c:if test="${attendanceTime1!=null||attendanceTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
									<a href="tecAttendance/lookAttendanceRecords?attendanceTime1=${attendanceTime1 }&attendanceTime2=${attendanceTime2 }&stuClass=${stuClass }&stuNo=${stuNo }&stuName=${stuName }&curPage=${maxPage}"><span>尾页</span></a>
								</c:if>
							</li>
						</ul>
					</div>
					<c:if test="${attendanceTime1==null&&attendanceTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
					<form class="navbar-form navbar-left" action="tecAttendance/jumpLookAttendanceRecord" role="search" method="get" >
						<span>跳转至：</span>
						<div class="form-group">
							<input id="curPage" type="text" name="curPage" class="form-control" placeholder="页码">
						</div>
						<button id="btn2" name="btn2" type="submit" class="btn btn-default">GO</button>
					</form>
					</c:if>
					<c:if test="${attendanceTime1!=null||attendanceTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
					<form class="navbar-form navbar-left" action="tecAttendance/lookAttendanceRecords" role="search" method="get" >
						<span>跳转至：</span>
						<div class="form-group">
							<input type="hidden" name="attendanceTime1" value="${attendanceTime1 }"/>
							<input type="hidden" name="attendanceTime2" value="${attendanceTime2 }"/>
							<input type="hidden" name="stuClass" value="${stuClass }"/>
							<input type="hidden" name="stuNo" value="${stuNo }"/>
							<input type="hidden" name="stuName" value="${stuName }"/>
							<input id="curPage" type="text" name="curPage" class="form-control" placeholder="页码">
						</div>
						<button id="btn2" name="btn2" type="submit" class="btn btn-default">GO</button>
					</form>
					</c:if>
					
				</div>
				<!--显示内容尾部结束-->
				<!--显示个人出勤率内容头部开始-->
				<form id="head-main" class="form-inline" action="tecAttendance/lookPersonalAttendanceRate" method="post">
					<h4>个人出勤率：</h4>
					<div class="form-group">
						<label for="attendanceTime3">考勤时间：</label>
						<input id="attendanceTime3" name="attendanceTime1" class="date_test form-control" value="${attendanceTime3 }" />&nbsp;-&nbsp;
						<input id="attendanceTime4" name="attendanceTime2" class="date_test form-control" value="${attendanceTime4 }" />
					</div>
					<div class="form-group">
						<label for="stuClass2">班级：</label>
						<select id="stuClass2" name="stuClass" class="form-control" >
							<option value="0" <c:if test="${stuClass2 == '0'}">selected</c:if>>--请选择查看的班级--</option>
							<option value="1" <c:if test="${stuClass2 == '1'}">selected</c:if>>1</option>
							<option value="2" <c:if test="${stuClass2 == '2'}">selected</c:if>>2</option>
							<option value="3" <c:if test="${stuClass2 == '3'}">selected</c:if>>3</option>
							<option value="4" <c:if test="${stuClass2 == '4'}">selected</c:if>>4</option>
						</select>
					</div>
					<div class="form-group">
						<label for="stuNo2">学号：</label>
						<input id="stuNo2" name="stuNo" class="form-control" type="text" value="${stuNo2 }" placeholder="请输入要查看的学生学号"/>
					</div>
					<div class="form-group">
						<label for="stuName2">姓名：</label>
						<input id="stuName2" name="stuName" class="form-control" type="text" value="${stuName2 }" placeholder="请输入要查看的学生姓名"/>
					</div>
					<button id="btn3" name="btn3" type="submit" class="btn btn-default">GO</button>
				</form>
				<div id="verifyStuExistInfo2"></div>
				<div id="verifyAttendanceExistInfo2"></div>
				<div id="personalVerify">${personalVerify }</div>
				<!--显示个人出勤率内容头部结束-->

				<!--显示个人出勤率内容主体开始-->
				<div id="main-attend" style="border: solid white 1px">
					<ul class="list-unstyled">
						<li>
							<strong>
								<span class="col-sm-3">学号</span>
								<span class="col-sm-3">姓名</span>
								<span class="col-sm-3">班级名称</span>
								<span class="col-sm-3">出勤率</span>
							</strong>
						</li>
						<li>
							<span class="col-sm-3">${personalAttendanceRecords[0].stuNo }</span>
							<span class="col-sm-3">${personalAttendanceRecords[0].stuName }</span>
							<span class="col-sm-3">${personalAttendanceRecords[0].className }</span>
							<span class="col-sm-3">${personalAttendanceRate}</span>
						</li>
					</ul>
				</div>
				<!--显示个人出勤率内容主体结束-->
				<!--显示班级出勤率内容头部开始-->
				<div>
					<form id="head-main" class="form-inline" action="tecAttendance/lookClassAttendanceRate" method="post">
						<h4 >班级出勤率：</h4>
						<div class="form-group">
							<label for="attendanceTimes5">考勤时间：</label>
							<input id="attendanceTimes5" name="attendanceTime1" class="date_test form-control" value="${attendanceTime5 }" />&nbsp;-&nbsp;
							<input id="attendanceTimes6" name="attendanceTime2" class="date_test form-control" value="${attendanceTime6 }" />
						</div>
						<div class="form-group">
							<label for="stuClass1">班级：</label>
							<select id="stuClass1" name="stuClass" class="form-control" >
								<option value="0" <c:if test="${stuClass1 == '0'}">selected</c:if>>--请选择查看的班级--</option>
								<option value="1" <c:if test="${stuClass1 == '1'}">selected</c:if>>1</option>
								<option value="2" <c:if test="${stuClass1 == '2'}">selected</c:if>>2</option>
								<option value="3" <c:if test="${stuClass1 == '3'}">selected</c:if>>3</option>
								<option value="4" <c:if test="${stuClass1 == '4'}">selected</c:if>>4</option>
							</select>
						</div>
						<button id="btn4" name="btn4" type="submit" class="btn btn-default">GO</button>
					</form>
				</div>
				<div id="classVerify">${classVerify }</div>
				<!--显示班级出勤率内容头部结束-->

				<!--显示班级出勤率内容主体开始-->
				<div id="main-attend">
							<strong>
								<span class="col-sm-2">出勤率:</span>
							</strong>
							<span class="col-sm-3">${classAttendanceRate}</span>
				</div>
				<!--显示班级出勤率内容主体结束-->
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
	    		/* 将时间输入框设置为日历选择框 */
	    		$(function (){
	    			$(".date_test").datetimepicker({
	    				language:"zh-CN",
	    				autoclose:"true",
	    				todayBtn:true,
	    				startView:2,
	    				minView:"year",
	    				format:"yyyy-mm-dd" //选中之后显示到的时间级别
	    			});
	    			
	    			/* 当光标离开第一个时间选择框时，如果第二个时间选择框不为空时，判断该时间段是否有考勤记录 */
	    			$("#attendanceTime1").blur(function () {
						if($(this).val()!="" && $("#attendanceTime2").val()!=""){
							$.ajax({
	    						url:"tecAttendance/verifyAttendanceExistInfo",
	    						type:"post",
	    						dataType:"json",
	    						data:{"attendanceTime1":$("#attendanceTime1").val(),"attendanceTime2":$("#attendanceTime2").val()},
	    						success:function(data){
	    							if(!data.succeed){
	        							$("#verifyAttendanceExistInfo").html(data.message);
	    							}else{
	        							$("#verifyAttendanceExistInfo").html("");
	    							}
	    						}
		    				});		
						}
					}); 
	    			
	    			/* 当光标离开第二个时间选择框时，如果第一个时间选择框不为空时，判断该时间段是否有考勤记录 */
	    			$("#attendanceTime2").blur(function () {
						if($(this).val()!="" && $("#attendanceTime1").val()!=""){
							$.ajax({
	    						url:"tecAttendance/verifyAttendanceExistInfo",
	    						type:"post",
	    						dataType:"json",
	    						data:{"attendanceTime1":$("#attendanceTime1").val(),"attendanceTime2":$("#attendanceTime2").val()},
	    						success:function(data){
	    							if(!data.succeed){
	        							$("#verifyAttendanceExistInfo").html(data.message);
	    							}else{
	        							$("#verifyAttendanceExistInfo").html("");
	    							}
	    						}
		    				});		
						}
					});
	    			
	    			/* 当光标离开班级选择框时，如果学号输入框不为空时，验证该班级是否有该学号的学生 */
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
	    			
	    			/* 当光标离开学号输入框时，如果班级选择框不为空时，验证该班级是否有该学号的学生 */
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
	    			
	    			/* 当光标离开个人考勤率的第一个时间选择框时，如果第二个时间选择框不为空时，判断该时间段是否有考勤记录 */
	    			$("#attendanceTime3").blur(function () {
						if($(this).val()!="" && $("#attendanceTime4").val()!=""){
							$.ajax({
	    						url:"tecAttendance/verifyAttendanceExistInfo",
	    						type:"post",
	    						dataType:"json",
	    						data:{"attendanceTime1":$("#attendanceTime3").val(),"attendanceTime2":$("#attendanceTime4").val()},
	    						success:function(data){
	    							if(!data.succeed){
	        							$("#verifyAttendanceExistInfo2").html(data.message);
	    							}else{
	        							$("#verifyAttendanceExistInfo2").html("");
	    							}
	    						}
		    				});		
						}
					}); 
	    			
	    			/* 当光标离开个人考勤率的第二个时间选择框时，如果第一个时间选择框不为空时，判断该时间段是否有考勤记录 */
	    			$("#attendanceTime4").blur(function () {
						if($(this).val()!="" && $("#attendanceTime3").val()!=""){
							$.ajax({
	    						url:"tecAttendance/verifyAttendanceExistInfo",
	    						type:"post",
	    						dataType:"json",
	    						data:{"attendanceTime1":$("#attendanceTime3").val(),"attendanceTime2":$("#attendanceTime4").val()},
	    						success:function(data){
	    							if(!data.succeed){
	        							$("#verifyAttendanceExistInfo2").html(data.message);
	    							}else{
	        							$("#verifyAttendanceExistInfo2").html("");
	    							}
	    						}
		    				});		
						}
					});
	    			
	    			/* 当光标离开个人考勤率班级选择框时，如果学号输入框不为空时，验证该班级是否有该学号的学生 */
	    			$("#stuClass2").blur(function () {
	    				if($("#stuClass2").val()!="0" && $("#stuNo2").val()!=null && $("#stuNo2").val()!=""){
		    				$.ajax({
	    						url:"tecAttendance/verifyStuExist",
	    						type:"post",
	    						dataType:"json",
	    						data:{"id":$("#stuClass2").val(),"stuNo":$("#stuNo2").val()},
	    						success:function(data){
	    							if(!data.succeed){
	    								$("#stuName2").attr("disabled","true");
	    								$("#btn3").attr("disabled","true");
	        							$("#verifyStuExistInfo2").html(data.message);
	    							}else{
	    								$("#stuName2").removeAttr("disabled");
	    								$("#btn3").removeAttr("disabled");
	        							$("#verifyStuExistInfo2").html("");
	    							}
	    						}
		    				});	
	    				}else{
	    					$("#verifyStuExistInfo2").html("");
							$("#stuName2").removeAttr("disabled");
	    					$("#btn3").removeAttr("disabled");
	    				}
	    			});
	    			
	    			/* 当光标离开学号输入框时，如果班级选择框不为空时，验证该班级是否有该学号的学生 */
	    			$("#stuNo2").blur(function () {
	    				if($("#stuClass2").val()!="0" && $("#stuNo2").val()!=null && $("#stuNo2").val()!=""){
		    				$.ajax({
	    						url:"tecAttendance/verifyStuExist",
	    						type:"post",
	    						dataType:"json",
	    						data:{"id":$("#stuClass2").val(),"stuNo":$("#stuNo2").val()},
	    						success:function(data){
	    							if(!data.succeed){
	    								$("#stuName2").attr("disabled","true");
	    								$("#btn3").attr("disabled","true");
	        							$("#verifyStuExistInfo2").html(data.message);
	    							}else{
	    								$("#stuName2").removeAttr("disabled");
	    								$("#btn3").removeAttr("disabled");
	        							$("#verifyStuExistInfo2").html("");
	    							}
	    						}
		    				});	
	    				}else{
	    					$("#verifyStuExistInfo2").html("");
							$("#stuName2").removeAttr("disabled");
	    					$("#btn3").removeAttr("disabled");
	    				}
	    			});
	    			
	    			/* 当输入页码不符合规范时，将提交按钮禁用，符合规范时又解禁 */
	    			$('#curPage').bind('input propertychange', function() {
						var page = $("#curPage").val();
						var regExp = /^\d+$/;
						if(!regExp.test(page)){
							$("#btn2").attr("disabled","true");
							$("#curPage").attr("placeholder","请输入一个整数!");
						}else{
							$("#btn2").removeAttr("disabled");
						}
					});
	    		});
	    </script>
</body>
</html>