<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <!-- 获得在地址栏访问时的项目的绝对路径，具体的访问时要拼上提交的表单的action属性的值， -->
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>提问页面</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	body{
    		margin: 50px;
    		background: rgb(247, 248, 250);
    	}
    	
    	.page_main{
    		margin: 10px;
    		background: white;
    	}
    	
    	.question>div{
    		padding: 5px;
    	}
    	
    	#searchMore{
    		margin-left: 550px;
    	}
    	
    	#answerLink{
    		margin-left: 500px;
    		padding-top: 10px;
    	}
    </style>
  </head>
<body>
	<div class="container">
  		<!--正文开始-->
  		<div id="bodyContent">
  			<!--提问正文开始-->
  			<div id="questionContext" class="row">
  				<c:forEach items="${tenQuestion }" var="t">
	  				<div class="row page_main">
			  			<a href="tecAttendance/lookQuestionDetail?questionId=${t.id}">
			  				<div id="left" class="col-sm-3">
								<input id="stuId" type="hidden" value="${t.stuId }"/>
								<input id="questionId" type="hidden" value="${t.id }"/>
								<span>学号：</span><span>${t.stuNo }</span><br/>
								<span>姓名：</span><span>${t.stuName }</span><br/>
								<span>班级：</span><span>${t.className }</span><br/>
								<span>提交时间：</span><span><fmt:formatDate value="${t.questionTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span><br/>
							</div>
				  			<div id="question" class="col-sm-9">
				  				<div id="questionContent" style="color: black">${t.questionContent }</div>
				  				<div id="look" style="padding-left: 800px;">查看</div>
				  			</div>
			  			</a>
			  		</div>
	  			</c:forEach>
	  			<c:if test="${weekQuestionNo>10 }">
	  			<button id="searchMore" class="btn btn-primary ">查看更多</button>
	  			</c:if>
	  			<c:forEach items="${weekQuestion }" var="w">
		  			<div class="row page_main">
			  			<a href="tecAttendance/lookQuestionDetail?questionId=${w.id}">
			  				<div id="left" class="col-sm-3">
								<input id="stuId" type="hidden" value="${w.stuId }"/>
								<span>学号：</span><span>${w.stuNo }</span><br/>
								<span>姓名：</span><span>${w.stuName }</span><br/>
								<span>班级：</span><span>${w.className }</span><br/>
								<span>提交时间：</span><span><fmt:formatDate value="${w.questionTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span><br/>
							</div>
				  			<div id="question" class="col-sm-9">
				  				<div id="questionContent" style="color: black">${w.questionContent }</div>
				  				<div id="look" style="padding-left: 800px;">查看</div>
				  			</div>
			  			</a>
			  		</div>
	  			</c:forEach>
  			</div>
  			<!--提问正文结束-->
  		</div>
  		<!--正文结束-->
  	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
	    $(function () {
	    	$("#searchMore").click(function () {
				window.location.href="tecAttendance/jumpWeekQuestion";
			})
		})
    </script>
</body>
</html>