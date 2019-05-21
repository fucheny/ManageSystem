<%-- 
    Document   : modifypasswordsecond
    Created on : 2010-10-20, 8:49:11
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<%
		String pw1 = request.getParameter("pw");
		String pw2 = (String) session.getAttribute("pw");
		if (!pw1.equals(pw2)) {
			out.println("<script>alert('原密码错误');window.location='modifypasswordfirst.jsp';</script>");
		}
		String password1 = request.getParameter("password1");
		String userId = (String) session.getAttribute("userId");
	%>


	<%
		//        // UserDAO userDAO=new UserDAO();
		//         if(userDAO.updatePassword(userId, password1)){

		//             out.println("修改成功");
		//         }else{
		//             out.println("修改失败");
		//         }
	%>
</body>
</html>
