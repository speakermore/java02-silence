<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!-- base获得在地址栏访问时项目的绝对路径（这里获得的路径是需要加上表单里action提交的地址的，目的是：这样就可以锁定到控制器相应的方法上，然后就可以做相应的处理） -->
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>老师查看工作日志页面</title>
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		<style>
			#head-main{margin-top: 50px;}
			#search{margin-bottom: 30px;}
			body{margin: 20px 50px}
		</style>
</head>
<body>
		<!--老师查看工作日志开始-->
		<div id="main_attend" class="container-fluid">
			<div class="row">
				<!--头部信息开始-->
				<div class="row">
					<div id="stu_info" class="col-sm-6">
						<h3>欢迎来到工作日志页面</h3>
					</div>
				</div>
				<!--头部信息结束-->

				<!--搜索框开始-->
				<div id="search" class="row">
					<form id="head-main" class="form-inline " action="tecAttendance/lookDiary?curPage=1" method="post">
						<div class="form-group">
							<label for="diaryCommitTime1">提交时间：</label>
							<input id="diaryCommitTime1" name="diaryCommitTime1" class="date_test form-control" value="${diaryCommitTime1 }" />&nbsp;-&nbsp;
							<input id="diaryCommitTime2" name="diaryCommitTime2" class="date_test form-control" value="${diaryCommitTime2 }" />
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
					<div id="verifyDiaryExistInfo"></div>
					<div id="diaryInfo">${diaryInfo }</div>
				</div>
				<!--搜索框结束-->

				<!--分页主体开始-->
				<c:forEach items="${diarys }" var="d">
					<div id="page_main" class="row">
						<a href="tecAttendance/lookDiaryDetail?diaryCommitTime=${d.diaryCommitTime }&stuId=${d.stuId}">
							<div id="left" class="col-sm-3">
								<input id="stuId" type="hidden" value="${d.stuId }"/>
								<span>学号：</span><span>${d.stuNo }</span><br/>
								<span>姓名：</span><span>${d.stuName }</span><br/>
								<span>班级：</span><span>${d.className }</span><br/>
								<span>提交时间：</span><span><fmt:formatDate value="${d.diaryCommitTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span><br/>
							</div>
							<div id="right" class="col-sm-9">
								<div id="partDiaryContent" style="color: black">
								${fn:substring(d.diaryContent,0,65) }...
								</div>
								<div id="queryDiaryDeatil" style="float: right;">
								查看日志详情
								</div>
							</div>
						</a>
					</div><hr/>
				</c:forEach>
				<!--分页主体结束-->
				<!--分页底部开始-->
				<div id="page_foot" class="row">
					<div class="col-sm-4">
						<ul class="pagination">
							<li><span>共${maxRecord}条记录&nbsp;${curPage}/${maxPage}页</span></li>
							<li>
								<c:if test="${diaryCommitTime1==null&&diaryCommitTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
									<a href="tecAttendance/jumpLookDiary?curPage=1"><span>首页</span></a>
								</c:if>
								<c:if test="${diaryCommitTime1!=null||diaryCommitTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
									<a href="tecAttendance/lookDiarys?diaryCommitTime1=${diaryCommitTime1 }&diaryCommitTime2=${diaryCommitTime2 }&stuClass=${stuClass }&stuNo=${stuNo }&stuName=${stuName }&curPage=1"><span>首页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${diaryCommitTime1==null&&diaryCommitTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
									<a href="tecAttendance/jumpLookDiary?curPage=${curPage-1}"><span>上一页</span></a>
								</c:if>
								<c:if test="${diaryCommitTime1!=null||diaryCommitTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
									<a href="tecAttendance/lookDiarys?diaryCommitTime1=${diaryCommitTime1 }&diaryCommitTime2=${diaryCommitTime2 }&stuClass=${stuClass }&stuNo=${stuNo }&stuName=${stuName }&curPage=${curPage-1}"><span>上一页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${diaryCommitTime1==null&&diaryCommitTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
									<a href="tecAttendance/jumpLookDiary?curPage=${curPage+1}"><span>下一页</span></a>
								</c:if>
								<c:if test="${diaryCommitTime1!=null||diaryCommitTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
									<a href="tecAttendance/lookDiarys?diaryCommitTime1=${diaryCommitTime1 }&diaryCommitTime2=${diaryCommitTime2 }&stuClass=${stuClass }&stuNo=${stuNo }&stuName=${stuName }&curPage=${curPage+1}"><span>下一页</span></a>
								</c:if>
							</li>
							<li>
								<c:if test="${diaryCommitTime1==null&&diaryCommitTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
									<a href="tecAttendance/jumpLookDiary?curPage=${maxPage}"><span>尾页</span></a>
								</c:if>
								<c:if test="${diaryCommitTime1!=null||diaryCommitTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
									<a href="tecAttendance/lookDiarys?diaryCommitTime1=${diaryCommitTime1 }&diaryCommitTime2=${diaryCommitTime2 }&stuClass=${stuClass }&stuNo=${stuNo }&stuName=${stuName }&curPage=${maxPage}"><span>尾页</span></a>
								</c:if>
							</li>
						</ul>
					</div>
					<c:if test="${diaryCommitTime1==null&&diaryCommitTime2==null&&stuClass==null&&stuNo==null&&stuName==null }">
					<form class="navbar-form navbar-left" action="tecAttendance/jumpLookDiary" role="search" method="get" >
						<span>跳转至：</span>
						<div class="form-group">
							<input id="curPage" type="text" name="curPage" class="form-control" placeholder="页码">
						</div>
						<button id="btn2" name="btn2" type="submit" class="btn btn-default">GO</button>
					</form>
					</c:if>
					<c:if test="${diaryCommitTime1!=null||diaryCommitTime2!=null||stuClass!=null||stuNo!=null||stuName!=null }">
					<form class="navbar-form navbar-left" role="search" action="tecAttendance/lookDiarys" method="get">
						<span>跳转至：</span>
						<div class="form-group">
							<input type="hidden" name="diaryCommitTime1" value="${diaryCommitTime1 }"/>
							<input type="hidden" name="diaryCommitTime2" value="${diaryCommitTime2 }"/>
							<input type="hidden" name="stuClass" value="${stuClass }"/>
							<input type="hidden" name="stuNo" value="${stuNo }"/>
							<input type="hidden" name="stuName" value="${stuName }"/>
							<input id="curPage" name="curPage" type="text" class="form-control" placeholder="页码">
						</div>
						<button id="btn2" name="btn2" type="submit" class="btn btn-default">GO</button>
					</form>
					</c:if>
				</div>
				<!-- 分页底部结束 -->
			</div>
		</div>
		<!--老师查看工作日志结束-->
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
	    			$("#diaryCommitTime1").blur(function () {
						if($(this).val()!="" && $("#diaryCommitTime2").val()!=""){
							$.ajax({
	    						url:"tecAttendance/verifyDiaryExist",
	    						type:"post",
	    						dataType:"json",
	    						data:{"diaryCommitTime1":$("#diaryCommitTime1").val(),"diaryCommitTime2":$("#diaryCommitTime2").val()},
	    						success:function(data){
	    							if(!data.succeed){
	        							$("#verifyDiaryExistInfo").html(data.message);
	    							}else{
	        							$("#verifyDiaryExistInfo").html("");
	    							}
	    						}
		    				});		
						}
					}); 
	    			
	    			/* 当光标离开第二个时间选择框时，如果第一个时间选择框不为空时，判断该时间段是否有工作日志 */
	    			$("#diaryCommitTime2").blur(function () {
						if($(this).val()!="" && $("#diaryCommitTime1").val()!=""){
							$.ajax({
	    						url:"tecAttendance/verifyDiaryExist",
	    						type:"post",
	    						dataType:"json",
	    						data:{"diaryCommitTime1":$("#diaryCommitTime1").val(),"diaryCommitTime2":$("#diaryCommitTime2").val()},
	    						success:function(data){
	    							if(!data.succeed){
	        							$("#verifyDiaryExistInfo").html(data.message);
	    							}else{
	        							$("#verifyDiaryExistInfo").html("");
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