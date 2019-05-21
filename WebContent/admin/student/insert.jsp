<%-- 
    Document   : insert
    Created on : 2010-9-20, 10:21:47
    Author     : Administrator
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>添加学生</h1>
        <form action="${pageContext.request.contextPath}/admin/StudentServlet?method=insert" method="post">
            <table border="0">
                <tr>
                    <td>学号</td>
                    <td><input type="text" name="no" value="20154091009"></td>

                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" value="张三"></td>

                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age" value="25"></td>

                </tr>
                <tr>
                    <td>出生日期</td>
                    <td><input type="text" name="birthday" value="1997-10-01"></td>

                </tr>
                <tr>
                    <td>班级</td>
                    <td>
                        <SELECT  name="clazzNo" style="width: 130px;">
                            <c:forEach items="${clazzList}" var="clazz">
                            
                            <option value="${clazz.clazzNo}"> ${clazz.clazzName} </option>
                            </c:forEach>
                        </SELECT>
                    </td>

                </tr>
            </table>
            <input type="submit" value="添加">
        </form>

    </body>
</html>
