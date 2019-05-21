<%-- 
    Document   : update
    Created on : 2012-9-21, 14:12:03
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>修改试题</h1>
        

        <form action="${pageContext.request.contextPath}/admin/StudentServlet?method=update" method="post">
           <table border="0">
            <tr>
                <td>学号</td>
                <td><input type="text" name="no" value="${s.no}"></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="name" value="${s.name}"></td>
          </tr>
            <tr>
                <td>年龄</td>
                <td><input type="text" name="age" value="${s.age}"></td>

            </tr>
            
            <tr>
                <td>出生日期</td>
                <td><input type="text" name="birthday" value="${s.birthday}"></td>
            </tr>
            
              <tr>
                    <td>班级</td>
                    <td>
                        <SELECT  name="clazzNo" style="width: 130px;">
                            <c:forEach items="${clazzList}" var="clazz">
                            
                            <option value="${clazz.clazzNo}" <c:if test="${clazz.clazzNo==s.clazzNo}">selected </c:if> > ${clazz.clazzName} </option>
                            </c:forEach>
                        </SELECT>
                <tr>
                    <td><input type="submit" value="更新"></td>
                </tr>
            </table>

        </form>



    </body>
</html>
