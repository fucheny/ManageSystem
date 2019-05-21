<%-- 
    Document   : list_Questions
    Created on : 2017-9-17, 19:06:01
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/js/jquery.min.js"></script>
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


	<form action="StudentServlet?method=listByPage&" method="post" id="f1"
		class="form-inline">
		<div class="row alert alert-info form-inline"
			style="margin: 0px; padding: 5px;">
			<div class="form-group">
				<input type="text" class="form-control" name="no"
					placeholder="请输入学号" />
			</div>
			<input type="submit" value="查询学生" class="btn btn-success" /> 
			<a href="${pageContext.request.contextPath}/admin/StudentServlet?method=insertPre" class="btn btn-info">添加学生</a> 
			<a href="${pageContext.request.contextPath}/admin/student/import.jsp" class="btn btn-info">导入学生</a> 
			<a href="${pageContext.request.contextPath}/admin/StudentServlet?method=exportExcel" class="btn btn-info">导出学生</a> 
			<input type="button" value="删除" class="btn btn-danger" onClick="selectStudent()">
		</div>
	</form>

	<form name="Studentform"
		action="${pageContext.request.contextPath}/admin/StudentServlet?method=deleteBatchByNos"
		method="post">
		<%
			
		%>
		<table class="table table-striped table-bordered">
			<tr>
				<th width="10" align="center"><input type="checkbox"
					name="ifAll" id="ifAll" onClick="checkAll()"></th>
				<th>学号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>出生日期</th>
				<th>班级</th>
				<th>操作</th>


			</tr>
			</tr>
			<c:forEach items="${pageBean.list}" var="s">
				<tr>
					<td><input type="checkbox" name="selectFlag" value="${s.no} ">
					</td>
					<td>${s.no}</td>
					<td>${s.name}</td>
					<td>${s.age}</td>
					<td>${s.birthday}</td>
					<td>${s.clazzName}</td>
					<td><a
						href="${pageContext.request.contextPath}/admin/StudentServlet?method=updatePre&no=${s.no}"
						class="btn btn-info">更新</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7">第 ${pageBean.currentPage } /
					${pageBean.totalPage } &nbsp;&nbsp; 每页显示${pageBean.pageSize }条
					&nbsp;&nbsp;&nbsp; 总的记录数${pageBean.totalSize } &nbsp;&nbsp;&nbsp; <c:if
						test="${pageBean.currentPage !=1 }">
						<a href="StudentServlet?method=listByPage&no=${no}&currentPage=1">首页</a>
						| <a
							href="StudentServlet?method=listByPage&no=${no}&currentPage=${pageBean.currentPage-1 }">上一页</a>
					</c:if> <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
						<c:if test="${pageBean.currentPage == i }">
			  				${i}
			  			</c:if>
						<c:if test="${pageBean.currentPage != i }">
							<a
								href="StudentServlet?method=listByPage&no=${no}&currentPage=${i}">${i}</a>
						</c:if>

					</c:forEach> <c:if test="${pageBean.currentPage !=pageBean.totalPage }">
						<a
							href="StudentServlet?method=listByPage&no=${no}&currentPage=${pageBean.currentPage+1 }">下一页</a> | 
			  			<a
							href="StudentServlet?method=listByPage&no=${no}&currentPage=${pageBean.totalPage }">尾页</a>
					</c:if>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
