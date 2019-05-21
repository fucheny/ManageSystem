<%-- 
    Document   : list_Questions
    Created on : 2017-9-17, 19:06:01
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
        <script>
            function checkAll() {
                var selectFlag = document.getElementsByName("selectFlag");
                for (var i = 0; i < selectFlag.length; i++) {
                    selectFlag[i].checked = document.getElementById("ifAll").checked;
                }
            }

            function selectStudent() {
                var selectFlag = document.getElementsByName("selectFlag");
                var flag = false;
                for (var i = 0; i < selectFlag.length; i++) {
                    if (selectFlag[i].checked) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    alert("请选择需要删除的学生");
                    return;
                }
                if (window.confirm("确认删除吗")) {
                    document.Studentform.submit();

                }
            }

        </script>
    </head>
    <body>
        
        
        <form
		action="StudentServlet?method=listByPage&"
		method="post" id="f1" class="form-inline">
		<div class="row alert alert-info form-inline"
			style="margin: 0px; padding: 5px;">
			
			 <a
				href=""
				class="btn btn-info">添加用户</a> 
                               
    <a href=""
				class="btn btn-info">导出用户</a>
         <input   type="button" value="删除" class="btn btn-danger" onClick="">
		</div>
	</form>
         
        <form name="Studentform"  action="" method="post">
            <%

            %>
            <table class="table table-striped table-bordered" >
                <tr>
                    <th width="10"  align="center">
                        <input type="checkbox" name="ifAll" id="ifAll"
                               onClick="checkAll()">
                    </th>
                    <th>userID</th>
                    <th>姓名</th>
                    <th>Email</th>
                    <th>photo</th>
                    <th>用户类型</th>
                    <th>管理的班级</th>
                    <th>操作</th>
                </tr>
                </tr>
               <c:forEach items="${userList}" var="user">
                <tr>
                    <td>
                        <input type="checkbox" name="selectFlag" 
                               value="${user.userID} ">
                    </td>
                     <td>
                        ${user.userID}
                    </td>
                    <td>
                        ${user.userName}
                    </td>
                    <td>
                        ${user.email}
                    </td>
                    <td>
                       <img src= "${user.photo}" width="50px" height="50px">
                    </td>
                    <td>
                        <c:if test="${user.userType=='01'}">
                                                                                      管理员
                        </c:if>
                        <c:if test="${user.userType=='02'}">
                                                                                       普通用户
                        </c:if>
                        
                    </td>
                    <td>
                        ${user.clazzName}
                    </td>
                <td>
                    <a href=""  class="btn btn-info">更新</a>
                    <a href=""  class="btn btn-info">修改密码</a>    
                </td>
                </tr>     
                </c:forEach>
              
                
            </table>
        </form>
    </body>
</html>
