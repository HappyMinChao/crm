<%@tag import="org.apache.jasper.runtime.PageContextImpl"%><!-- make to shanshan -->
<%@ tag language="java" pageEncoding="UTF-8"%>

<!-- 定义标签属性 -->
<%@ attribute name="page" required="true" type="com.atuigu.crm.orm.Page" %>
<%@ attribute name="queryString" required="true" type="java.lang.String"%>


<div style="text-align:right; padding:6px 6px 0 0;">

	共 ${page.totalElements } 条记录
	&nbsp;&nbsp;
	
	当前第 ${page.pageNo }页/共 ${page.totalPages } 页
	&nbsp;&nbsp;
	
	<% if (page.isHasPrev()) {%>
		<a href='?pageNoStr=1&${queryString}'>首页</a>
		&nbsp;&nbsp;
		<a href='?pageNoStr=${page.prevPage }&${queryString}'>上一页</a>
		&nbsp;&nbsp;
	<% }%>
	
	 <% if(page.isHasNext()) {%>
		<a href='?pageNoStr=${page.nextPage }&${queryString}'>下一页</a>
		&nbsp;&nbsp;
		<a href='?pageNoStr=${page.totalPages }&${queryString}'>末页</a>
		&nbsp;&nbsp;
	<% }%>
	
	转到 <input id="pageNoStr" size='1'/> 页
	&nbsp;&nbsp;

</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		$("#pageNoStr").change(function(){
			
			var pageNo = $(this).val();
			var reg = /^\d+$/;
			if(!reg.test(pageNo)){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			var pageNo2 = parseInt(pageNo);
			window.location.href = window.location.pathname + "?pageNoStr=" + pageNo2+"&${queryString}";
		});
	});
</script>

