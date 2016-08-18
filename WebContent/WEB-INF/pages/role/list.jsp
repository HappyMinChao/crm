<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>

<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
		$(function(){
			$(".op_button_delete").click(function(){
				var id=$(this).next().val();
				$("#delete_form").attr("action","${ctp}/role/"+id).submit(); 
			});
		});
	</script>
</head>

<body class="main">

	<div class="page_title">
		角色管理
	</div>
	
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/role/input'">
			新建
		</button>
	</div>
	<form action="" id="delete_form" method="post">
		<input name="_method" value="DELETE" type="hidden"/>
	</form>
	<form action="role-list">

		<!-- 列表数据 -->
		<br />
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title" >
						编号
					</th>
					<th class="data_title" >
						角色名
					</th>
					<th class="data_title" >
						角色描述
					</th>
					<th class="data_title">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
				
				<c:forEach var="role" items="${page.content }">
					<tr>
						<td class="data_cell" style="text-align:right;padding:0 10px;">${role.id}</td>
						<td class="data_cell" style="text-align:center;">${role.name}</td>
						<td class="data_cell" style="text-align:left;">${role.description}</td>
						<td class="data_cell" style="text-align:center;">${role.enabled ? "有效":"无效"}</td>
						<td class="data_cell">
							<img onclick="window.location.href='assign/${role.id}'" title="分配权限" src="${ctp }/static/images/bt_linkman.gif" class="op_button" />
							<img title="删除" src="${ctp }/static/images/bt_del.gif" class="op_button_delete" />
							<input type="hidden" value="${role.id}"/>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			<atguigu:page page="${page}" queryString=""></atguigu:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
		
	</form>
</body>
</html>
