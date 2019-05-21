<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cn.byau.homework.dao.LogInfoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.byau.homework.entity.LogInfo"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>show-loginfo</title>
        <style type="text/css">
            .style1{
                width: 1000px;
                margin: 0px;
                margin-bottom:20px;
                border:1px solid #96C2F1;
                background-color: #EFF7FF;
                border-collapse:collapse
            }

            .style1 td{
                border:1px solid #000000;
                /*border-collapse:collapse ;*/
            }
            .style1 th {
                border:1px solid #000000;
                background-color: #0099CC;
            }


        </style>   
    </head>
    <body>

        <h1>日志数据</h1>
        <table border="1"  class="style1" >
            <tr align="center">
                <th>用户Id</th>
                <th>姓名</th>
                <th>登陆时间</th>
            </tr>
            
            <%
                LogInfoDAO sdao = new LogInfoDAO();
                ArrayList<LogInfo> list = sdao.list();
                //多条记录用while循环
                for (LogInfo s : list) {
            %>

            <tr align="center">
                <td>
                    <%=s.getUserId()%>
                </td>
                <td>
                    <%=s.getUsername()%>
                </td>
                <td>
                    <%=s.getLogintime()%>
                </td>

            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
