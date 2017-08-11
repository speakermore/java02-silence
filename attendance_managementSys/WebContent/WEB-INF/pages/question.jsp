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
  		<!--头部导航开始-->
  		<!-- <div id="topNav" class="row">
  				<button id="myQuestion" class="btn btn-group col-xs-offset-1 col-xs-4 glyphicon glyphicon-question-sign" data-toggle="button" >&nbsp;我的提问</button>
  				<button id="myAnswer" class="btn btn-group col-xs-offset-2 col-xs-4 glyphicon glyphicon-list-alt " data-toggle="button">&nbsp;我的回答</button>
  		</div> -->
  		<!--头部导航结束-->
  		<!--正文开始-->
  		<div id="bodyContent">
  			<!--搜索表单开始开始-->
  			<div id="search" class="row" style="margin-top: 50px;">
  				<form action="" role ="form" class="container-fluid form-horizontal">
							<div class="form-group col-sm-10">
								<!--placeholder:默认值的聚焦隐藏，失焦显示-->
								<!--class="form-control input-group-lg-->
								<input placeholder="输入关键字，多个关键字之间用空格分隔" class="form-control input-lg" type="text" />
							</div>
							<div class="form-group col-sm-2">
								<!--input-lg是让样式变大-->
								<input class="form-control input-lg" type="submit" value="搜索" />
							</div>
						</form>
  			</div>
  			<!--搜索表当结束-->
  			<!--提问正文开始-->
  			<div id="questionContext" class="row">
  				<c:forEach items="${tenQuestion }" var="t">
	  				<div class="row page_main">
			  			<a href="tecAttendance/lookQuestionDetail?questionId=${t.id}">
			  				<div id="left" class="col-sm-3">
								<input id="questionId" type="hidden" value="${t.id }"/>
								<span>学号：</span><span>${t.stuNo }</span><br/>
								<span>姓名：</span><span>${t.stuName }</span><br/>
								<span>班级：</span><span>${t.className }</span><br/>
								<span>提交时间：</span><span><fmt:formatDate value="${t.questionTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span><br/>
							</div>
				  			<div id="question" class="col-sm-9">
				  				<div id="questionContent" style="color: black">${t.questionContent }</div>
				  				<div id="look" style="padding-left: 800px;">查看</div>
				  				<!-- <div id="answerContent">最新回答内容</div>
				  				<div id="answerLink" class="glyphicon glyphicon-pencil">回答</div> -->
				  			</div>
			  			</a>
			  		</div>
	  			</c:forEach>
	  			<!-- <div class="question">
	  				<div id="questionTitle">提问1</div>
	  				<div id="answerContent">最新回答内容</div>
	  				<div id="answerLink" class="glyphicon glyphicon-pencil">回答</div>
	  			</div>
	  			<div class="question">
	  				<div id="questionTitle">提问1</div>
	  				<div id="answerContent">最新回答内容</div>
	  				<div id="answerLink" class="glyphicon glyphicon-pencil">回答</div>
	  			</div>
	  			<div class="question">
	  				<div id="questionTitle">提问1</div>
	  				<div id="answerContent">最新回答内容</div>
	  				<div id="answerLink" class="glyphicon glyphicon-pencil">回答</div>
	  			</div> -->
	  			<c:if test="${weekQuestionNo>10 }">
	  			<button id="searchMore" class="btn btn-primary ">查看更多</button>
	  			</c:if>
	  			<c:forEach items="${weekQuestion }" var="w">
		  			<div class="row page_main">
			  			<a href="tecAttendance/lookQuestionDetail?questionId=${w.id}">
			  				<div id="left" class="col-sm-3">
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
  		<!--foot开始-->
  		
  		<!--foot开始-->
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