<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务管理</title>
	<script type="text/javascript">
		$(function(){
			$(".allotToSelect").change(function(){
				//alert(this);
				$(this).closest("form").attr("action","${ctp}/service/update").submit();
				//alert(method);
				//你确定吗
			});
		});
	</script>
<body>
	
	<div class="page_title">
		客户服务管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form:form action="${ctp }/service/allot/list" method="post" modelAttribute="service">
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<input type="text" name="search_LIKES_serviceType" />
				</td>
				<th>
					概要
				</th>
				<td>
					<input type="text" name="search_LIKES_serviceTitle" />
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<input type="text" name="search_LIKES_customer.name" />
				</td>
				<th>
					创建时间
				</th>
				<td>
					<input type="text" name="search_GTS_createDateStart" size="10" />
					-
					<input type="text" name="search_LTS_createDateEnd" size="10" />
				</td>
			</tr>
		</table>
		
	</form:form>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						服务类型
					</th>
					<th>
						概要
					</th>
					<th>
						客户
					</th>
					<th>
						创建人
					</th>
					<th>
						创建时间
					</th>
					<th>
						分配给
					</th>
					<th>
						操作
					</th>
				</tr>
				
				<c:forEach var="service" items="${page.content }">
					<tr>
						<td class="list_data_number">
							${service.id}
						</td>
						<td class="list_data_text">
							${service.serviceType}
						</td>
						<td class="list_data_ltext">
							${service.serviceTitle}
						</td>
	
						<td class="list_data_text">
							${service.customer.name}
						</td>
						<td class="list_data_text">
							${service.createdby.name}
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${service.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td>
						<form:form action="" method="post" modelAttribute="service">
							<input type="hidden" name="_method" value="PUT">
							<input type="hidden" name="id" value="${service.id}">
							<form:select path="allotTo" class="allotToSelect">
								<form:option value="--请选择--"></form:option>
								<form:options items="${userList }" itemLabel="name" itemValue="id"/>
							</form:select>
						</form:form>
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp }/service/delete?id=${service.id}'" 
								title="删除" src="${ctp }/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
			</table>
		<%-- <tags:pagination page="${page}" paginationSize="5"/> --%>
		<%-- <tags:page page="${page}" queryString="${queryString}"></tags:page> --%>
		<atguigu:page page="${page}" queryString="${queryString}"></atguigu:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>

</body>
</html>




