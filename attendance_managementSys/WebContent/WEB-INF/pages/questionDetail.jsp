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
    <title>提问详细页面</title>
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
    	
    	#questionHeader{
    		background: white;
    	}
    	
    	#questionAuthorInfo,#question{
    		height: 40px;
    		line-height: 40px;
    	}
    	
    	#answer>div{
    		margin: 10px;
    		background: white;
    	}
    	
    	#answerButton{
    		margin-left: 550px;
    		margin-top: 10px;
    	}
    </style>
  </head>
<body>
	<div class="container">
			<!--提问开始-->
			<div id="questionHeader" class="row">
				<input type="hidden" id="questionId" name="questionId" value="${question.id }"/>
				<div class="row">
					<div id="question" class="col-xs-6">
						<h5><strong>问题:${question.questionContent }</strong></h5>
					</div>
					<div id="questionAuthorInfo" class="col-xs-6">
						<span>提问者名字：${question.stuName }</span>&nbsp;&nbsp;
						<span>提问者所在班级：${question.className }</span>&nbsp;&nbsp;
						<span>提问时间：<fmt:formatDate value="${question.questionTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</div>
				</div>
				<div class="row">
					<button id="answerButton" class="glyphicon glyphicon-pencil">回答</button>
				</div>
			</div>
			<!--提问结束-->
			<!--提问对应的回答开始-->
			<div id="answer" class="row" style="margin-top: 10px;">
				<c:choose>
					<c:when test="${stuAnswerNo==0&&tecAnswerNo==0 }">
						${answerInfo }
					</c:when>
					<c:otherwise>
						<c:forEach items="${stuAnswers }" var="s">
							<div class="row page_main">
								<c:if test="${stuAnswerNo!=0 }">
					  				<div id="left" class="col-sm-3">
										<span>回答者姓名：</span><span>${s.stuName }</span><br/>
										<span>回答者所属班级：</span><span>${s.className }</span><br/>
										<span>回答时间：</span><span><fmt:formatDate value="${s.answerTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span><br/>
									</div>
						  			<div id="answerContent" class="col-sm-9">
						  			${s.answerContent }
						  			</div>
						  		</c:if>
					  		</div>
						</c:forEach>
						<c:forEach items="${tecAnswers }" var="t">
							<div class="row page_main">
								<c:if test="${tecAnswerNo!=0 }">
					  				<div id="left" class="col-sm-3">
										<span>回答者姓名：</span><span>${t.tecName }</span><br/>
										<span>回答时间：</span><span><fmt:formatDate value="${t.answerTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span><br/>
									</div>
						  			<div id="answerContent" class="col-sm-9">
						  			${t.answerContent }
						  			</div>
						  		</c:if>
					  		</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			 <!--提问对应的回答结束 -->
			 
  	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
	    $(function () {
	    	$("#answerButton").click(function () {
	    		var questionId = $("#questionId").val();
				window.location.href="tecAttendance/jumpAnswerPage?questionId="+questionId;
			})
		})
    </script>
</body>
</html>