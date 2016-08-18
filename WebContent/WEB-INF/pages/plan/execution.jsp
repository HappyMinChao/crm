<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>执行计划</title>
    <script type="text/javascript">
    $(function(){
    	$(".common_button").click(function(){
    		var id=$(this).attr("id");//saveresult 21
    		id = id.split("-")[1];//planId
    		var result= $(this).prev().val();//执行的结果
    		
    		var params={"result":result,"_method":"PUT"};
    		var url="${ctp}/plan/ajax_exe/"+id;
    		$.post(url,params,function(data){
    			if(data==1){
    				alert("保存成功！！");
    				var changId=$("#changId").val();
    				window.location.href="${ctp}/plan/execution/"+changId;
    			}else{
    				alert("保存失败！！");
    			}
    		});
    		return false;
    	});
    });
    </script>
  </head>
  <body class="main">
	<span class="page_title">执行计划</span>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/chance/stop/${chance.id }'">终止开发</button>
		<button class="common_button" onclick="window.location.href='${ctp}/plan/make/${chance.id }'">制定计划</button>
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>			
		<button class="common_button" onclick="window.location.href='${ctp}/chance/finish/${chance.id }'">开发成功</button>
	</div>
  	<form action="/crm_/plan/execute" method="post">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${chance.id }&nbsp;</td>
				
				<th>机会来源</th>
				<td>${chance.source }&nbsp;</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${chance.custName }&nbsp;</td>
				
				<th>成功机率（%）</th>
				<td>${chance.rate }&nbsp;</td>
			</tr>
			
			<tr><th>概要</th>
				<td colspan="3">${chance.title }&nbsp;</td>
			</tr>
			
			<tr>
				<th>联系人</th>
				<td>${chance.contact }&nbsp;</td>
				<th>联系人电话</th>
				<td>${chance.contactTel }&nbsp;</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${chance.description }&nbsp;</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${chance.createBy.name }&nbsp;</td>
				<th>创建时间</th>
				<td>
				<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>		
			<tr>					
				<th>指派给</th>
				<td>${chance.designee.name }&nbsp;</td>
			</tr>
		</table>
	
	<br />
	
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th width="200px">日期</th>
				<th>计划</th>
				<th>执行效果</th>
			</tr>
			<c:forEach items="${plans}" var="plan">
				<tr>
					<td class="list_data_text">
						<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/>
					</td>
					<td class="list_data_ltext">${plan.todo }&nbsp;</td>
					<td>
						<input <c:if test="${plan.result!=null }">disabled="disabled"</c:if> class="result" id="result-${plan.id }" type="text" size="50" value="${plan.result}" />
						<button class="common_button" id="saveresult-${plan.id }">保存</button>
						<input type="hidden" id="changId" value="${chance.id }"/>
					</td>
				</tr>
			</c:forEach>
		</table>	
  </form>
  </body>
</html>
