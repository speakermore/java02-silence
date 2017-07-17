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
    <title>回答页面</title>
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
    	
    	#answerHeader{
    		margin-bottom: 20px;
    		width: 840px;
    		height: 50px;
    		line-height: 50px;
    		background: white;
    	}
    	
    	#submit{
    		margin-left: 700px;
    		margin-top: 5px;
    	}
    </style>
   </head>
	<body>
  	<div class="container">
  		<!--回答头部开始-->
			<div id="answerHeader" class="row">
				<div id="question" class="col-xs-5">
					<h4>问题</h4>
				</div>
				<div id="questionAuthorInfo" class="col-xs-5">
					<span>名字：</span>
					<span>时间：</span>
				</div>
			</div>
			<!--回答头部结束-->
			<!--回答正文开始-->
			<form action="">
				<div class="">
					<textarea name="answer" class="ckeditor">写回答...</textarea>
				</div>
				<input id="submit" type="submit" name="submit" value="提交回答" />
			</form>
			<!--回答正文结束-->
		</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
    	CKEDITOR.replace("answer");
    </script>
  </body>
</html>