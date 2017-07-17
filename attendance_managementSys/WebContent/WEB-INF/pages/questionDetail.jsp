<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    	
    	#searchMore span{
    		margin-left: 500px;
    	}
    	
    	#questionButton{
    		margin-left: 550px;
    		margin-top: 10px;
    	}
    </style>
  </head>
<body>
	<div class="container">
			<!--提问开始-->
			<div id="questionHeader" class="row">
				<div id="question" class="col-xs-6">
					<h4>问题</h4>
				</div>
				<div id="questionAuthorInfo" class="col-xs-6">
					<span>名字：</span>
					<span>时间：</span>
				</div>
				<button id="questionButton" class="glyphicon glyphicon-pencil">回答</button>
			</div>
			<!--提问结束-->
			<!--提问对应的回答开始-->
			<div id="answer" class="row" style="margin-top: 10px;">
				<div id="questionTitle">回答1</div>
				<div id="questionTitle">回答2</div>
				<div id="questionTitle">回答3</div>
				<div id="questionTitle">回答4</div>
  			<div id="searchMore"><span>查看全部n个回答</span></div>
			</div>
			 <!--提问对应的回答开始-->
			 
  	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>