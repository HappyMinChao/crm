<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	$(function(){
		//执行计划
		$("#execute").click(function(){
			var chanceId=$("#chanceId").val();	
			window.location.href="${ctp}/plan/execution/"+chanceId;
		});
		
		//新建
		$("#createPlan").click(function(){
			//获取todo
			var todo=$("#todo").val();
			//获取chance.id
			var chanceId=$("#chanceId").val();
			//获取date
			var date=$("#date").val();
			//整合参数json
			var params={"todo":todo,"chance.id":chanceId,"date":date};
			//前往的url
		 	var url="${ctp}/plan/ajax/";
		
			$.post(url,params,function(id){
				if(id>0){
					alert("新建成功！！");
				}
				var $tr=$("<tr id='plan-delete-${plan.id}'></tr>");
				$tr.append("<td class='list_data_text'>"+date+"&nbsp;</td>")
				   .append("<td class='list_data_ltext'><input type='text' size='50' value='"+todo+"' id='todo-"+id+"'/><button class='update_button' id='"+id+"'>更新</button><button class='delete_button' id='"+id+"' title='"+todo+"'>删除</button></td>")
				   .appendTo($(".data_list_table"));
				
			});
			return false;
		});
		
		$(".update_button").click(function(){//更新（保存）
			//获取planId
			var id = $(this).attr("id");
			//获取todo
			var todo=$(this).prev().val();
			
			var params={"todo":todo,"_method":"PUT"};
			var url="${ctp}/plan/ajax/"+id;
			$.post(url,params,function(callData){
				if(callData==1){
					alert("修改成功！！");
				}
			});  
			return false; 
		});
		
		$(".delete_button").click(function(){//删除
			
			var label=$(this).attr("title");
			var falg=confirm("确认删除"+label+"计划？");
			if(!falg) return false;
			
			//获取planId
			var id = $(this).attr("id");
			//获取chance.id
			var chanceId=$("#chanceId").val();	
		
			var params={"_method":"DELETE"};
			var url="${ctp}/plan/ajax/"+id;
			$.post(url,params,function(callData){
				if(callData==1){
					alert("删除成功！！");
				}
				$("#plan-delete-"+id).remove();
			}); 
			return false;
		});
	});
</script>
<head>
	<title>制定计划</title>
</head>
<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute">
			执行计划
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
	
		<form action="/crm_/plan/make" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>

				<td>
					${chance.id }
				</td>
				<th>
					机会来源
				</th>

				<td>
					${chance.source }
				</td>
			</tr>
			<tr>
				<th>
					客户名称
				</th>
				<td>
					${chance.custName }
				</td>
				<th>
					成功机率（%）
				</th>

				<td>
					${chance.rate }
				</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					${chance.title }
				</td>
			</tr>
			<tr>
				<th>
					联系人
				</th>

				<td>
					${chance.contact }
				</td>
				<th>
					联系人电话
				</th>

				<td>
					${chance.contactTel }
				</td>
			</tr>
			<tr>
				<th>
					机会描述
				</th>
				<td colspan="3">
					${chance.description }
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${chance.createBy.name }
				</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th>
					指派给
				</th>
				<td>
					${chance.designee.name }
				</td>

			</tr>
		</table>

		<br />
		
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th width="200px">
					日期
				</th>
				<th>
					计划项
				</th>
			</tr>
			<c:forEach items="${plans}" var="plan">
				<tr id="plan-delete-${plan.id}">
					<td class="list_data_text">
						<fmt:formatDate value="${plan.date}" pattern="yyyy-MM-dd"/>
						&nbsp;
					</td>
					<td class="list_data_ltext">
						<input type="text" size="50" <c:if test='${plan.result!=null}'> disabled="disabled"</c:if>
							value="${plan.todo}" id="todo-${plan.id}"/>
						<button class="update_button" id="${plan.id}">
							更新
						</button>
						<button class="delete_button" id="${plan.id}" title="${plan.todo}">
							删除
						</button>
					</td>
				</tr> 
			</c:forEach>
		</table>
		<div class="button_bar">
			<button class="common_button" id="createPlan">
				新建
			</button>
		</div>
		<input type="hidden" id="chanceId" value="${chance.id }" />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					日期
					<br />
					(格式: yyyy-mm-dd)
				</th>
				<td>
					<input type="text" id="date"id="date" />
					&nbsp;
				</td>
				<th>
					计划项
				</th>
				<td>
					<input type="text" id="todo" size="50" id="todo" />
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
