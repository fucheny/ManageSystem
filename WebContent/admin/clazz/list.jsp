<%-- 
    Document   : list_Questions
    Created on : 2012-9-17, 19:06:01
    Author     : Administrator
--%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
        <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>JSP Page</title>
        <script>
            function del(classNo) {
                if (confirm("你确定要删除吗")) {
                    window.location = "${pageContext.request.contextPath}/admin/ClazzServlet?method=delete&clazzNo=" + classNo;
                }
            }
        </script>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/admin/ClazzServlet?method=insertPre">添加班级</a> 
        <h1>班级数据</h1>
        <table class="table  table-striped">

            <tr>
                <th>班级号</th>

                <th>班级名</th>



                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${clazzList}" var="c">

            <tr>
                <td>
                    ${c.clazzNo}
                </td>
                <td>
                     ${c.clazzName}
                </td>


                <td>
                    <a href="${pageContext.request.contextPath}/admin/ClazzServlet?method=updatePre&clazzNo=${c.clazzNo}">更新</a>  
                </td>
                <td>
                    <a href="javascript:del('${c.clazzNo}')">删除</a>
                </td>
            </tr>
          </c:forEach>
        </table>
    </body>
</html>
