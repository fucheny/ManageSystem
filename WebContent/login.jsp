<%-- 
    Document   : main
    Created on : 2015-11-15, 10:15:22
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title> <script type="text/javascript"
		src="<%=request.getContextPath()%>/res/js/jquery.min.js"></script>

	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/res/css/add.css" type="text/css"
		media="screen" />
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/res/css/bootstrap.min.css"
		type="text/css" media="screen" />


	<!--<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">-->

	<script>
          
//更换验证码
  function changeVerifyCode(){

    //1、如果用<iframe>实现，则重新加载<iframe>的内容
  	//verifyCodeFrame.location.reload();

  	//2、如果用<img>实现，则修改<img src=url>的url
  	//这里有一个小技巧，如果给url赋相同的值，浏览器不会重新发出请求，因此用js生成一个即时毫秒数做url中的参数
  	t=new Date().getTime();
  	document.myform.verifyCodeImg.src="<%=request.getContextPath()%>
		/ImageServlet?t="
					+ t;
		}
	</script>
</head>
<body>
	<div class="jumbotron">


		<div class="div_from_aoto" style="width: 500px;">
			<h3>欢迎使用简单学生管理系统</h3>

			<FORM action="${pageContext.request.contextPath}/LoginServlet?method=login"
				method="post" name="myform">
				<div class="control-group">
					<label class="laber_from">学号</label>
					<div class="controls">
						<input class="input_from" type=text name="userID"><p
								class=help-block></p>
					</div>
				</div>
				<div class="control-group">
					<label class="laber_from">密码</label>
					<div class="controls">
						<input class="input_from" type=password name="password"><p
								class=help-block></p>
					</div>
				</div>

				<div class="control-group">
					<label class="laber_from">验证码</label>
					<div class="controls">
						<input class="input_from" type=text name="verifyCode"> <img
							name="verifyCodeImg"
							src="<%=request.getContextPath()%>/ImageServlet"> <a
								href="javascript:changeVerifyCode()">看不清？</a>
					</div>
				</div>

				<div class="control-group">
					<label class="laber_from"></label>
					<button class="btn btn-lg btn-primary " type="submit">登 陆</button>
				</div>
			</FORM>
		</div>
	</div>
</body>
</html>
