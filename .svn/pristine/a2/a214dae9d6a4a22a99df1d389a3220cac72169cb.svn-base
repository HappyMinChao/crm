<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript">
	$(function(){
		$("#delete").click(function(){
			var id=$(this).prev().val();
			$("#del_form").attr("action","${ctp }/activity/"+id).submit();
		});
	});
</script>
<head>
	<title>交往记录管理</title>
</head>
<body>
	<div class="page_title">
		交往记录管理
	</div>
	<div class="button_bar">
		<button class="common_button"
			onclick="window.location.href='${ctp}/activity/input?customerId=${customer.id }'">
			新建
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);"> 
			返回
		</button>
	</div>
	<form action="${ctp }/activity/${activity.id}" method="POST" id="del_form">
		<input type="hidden" name="_method" value="DELETE">
		<input type="hidden" name="customerId" value="${customer.id}">
	</form>
	<form action="${ctp }/activity/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户编号
				</th>
				<td>${customer.no}</td>
				<th>
					客户名称
				</th>
				<td>${customer.name}</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						时间
					</th>
					<th>
						地点
					</th>
					<th>
						概要
					</th>
					<th>
						详细信息
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="activity" items="${page.content }">
					<tr>
						<td class="list_data_text">
							<fmt:formatDate value="${activity.date }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_ltext">
							${activity.place}
						</td>
						<td class="list_data_ltext">
							${activity.title}
						</td>
						<td class="list_data_ltext">
							${activity.description}
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/activity/input?id=${activity.id }'" 
								title="编辑" src="${ctp }/static/images/bt_edit.gif" class="op_button" />
								
							<input type="hidden" value="${activity.id}"/>
							<img id="delete" 
								title="删除" src="${ctp }/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
			</table>	
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
		<atguigu:page page="${page}" queryString="${queryString}"></atguigu:page>
	</form>
</body>
</html>