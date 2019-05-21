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
			alert("请选择需要删除的作业");
			return;
		}
		if (window.confirm("确认删除吗")) {
			document.Studentform.submit();
		}
	}
</script>
</head>
<body>
	<form action="StudentServlet?method=queryHomework" method="post"
		id="f1" class="form-inline">
		<div class="row alert alert-info form-inline"
			style="margin: 0px; padding: 5px;">
			<div class="form-group">
				<input type="text" class="form-control" name="name"
					placeholder="请输入作业名称" />
			</div>
			<input type="submit" value="查询作业" class="btn btn-success" /> <a
				href="StudentServlet?method=queryHomework" class="btn btn-info">查询作业</a>

			<a href="StudentServlet?method=exportExcel" class="btn btn-info">导出作业</a>
			<input type="button" value="删除" class="btn btn-danger"
				onClick="selectStudent()">
		</div>
	</form>

	<form name="Studentform"
		action="StudentServlet?method=deleteBatchByNos" method="post">
		<%
			
		%>
		<table class="table table-striped table-bordered">
			<tr>
				<th width="10" align="center"><input type="checkbox"
					name="ifAll" id="ifAll" onClick="checkAll()"></th>
				<th>标题</th>
				<th>课程</th>
				<th>布置时间</th>
				<th>操作</th>

			</tr>
			</tr>
			<c:forEach items="${homeworkList}" var="s">
				<tr>
					<td><input type="checkbox" name="selectFlag" value="${s.no} ">
					</td>
					<td>${s.title}</td>
					<td>${s.kecheng}</td>
					<td>${s.oktime}</td>
					<td><a
						href="${pageContext.request.contextPath}/StudentServlet?method=updatehomework&no=${s.no}"
						class="btn btn-info">更新</a></td>
				</tr>
			</c:forEach>


		</table>
	</form>
</body>
</html>
