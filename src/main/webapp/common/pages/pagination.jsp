<%@ taglib uri="/struts-tags" prefix="s"%>



<%
	//Pagination pagination = (Pagination) request.getAttribute("pagination");
	Pagination pagination = (Pagination) request
			.getAttribute("pagination");
	boolean previous = false;
	if (pagination.getPage_number() > 1) {
		previous = true;
	}
	boolean next = true;
	if (pagination.getTotal_pages() == pagination.getPage_number()) {
		next = false;
	}
%>

<%@page import="com.apm.common.utils.Pagination"%>
<div class="col-lg-4 col-md-4 col-sm-4 hidden-xs text-center">


	<input type="text" style="display: none;" name="pagination.sortColumn"
		id="sortColumn" value="<%=pagination.getSortColumn()%>" /> <input
		type="text" style="display: none;" name="pagination.sortOrder"
		id="sortOrder" value="<%=pagination.getSortOrder()%>" />

	<ul class="pagination" style="margin:0">
		<%
			if (previous) {
		%>
		<li><a href="#"
			onclick="fnPagination(4,<%=pagination.getTotal_pages()%>);"> <i
				class="fa fa-angle-double-left"></i> First
		</a></li>
		<%
			} else {
		%>
		<li class="disabled"><a href="#"
			onclick="fnPagination(4,<%=pagination.getTotal_pages()%>);"> <i
				class="fa fa-angle-double-left"></i> First
		</a></li>
		<%
			}
		%>

		<%
			if (previous) {
		%>
		<li><a href="#"
			onclick="fnPagination(3,<%=pagination.getTotal_pages()%>);"> <i
				class="fa fa-angle-left"></i> Previous
		</a></li>

		<%
			} else {
		%>
		<li class="disbled"><a href="#"
			onclick="fnPagination(3,<%=pagination.getTotal_pages()%>);"> <i
				class="fa fa-angle-left"></i> Previous
		</a></li>
		<%
			}
		%>

		<input name="pagination.page_number" id="page_number"
			class="pagination-textbox" <%if (!previous && !next) {%>
			readonly="readonly" <%}%> style="width: 10px;" maxlen="1"
			value="<%=pagination.getPage_number()%>" type="hidden"
			onclick="fnPagination(7,<%=pagination.getTotal_pages()%>);" />



		<%
			if (next) {
		%>
		<li><a href="#"
			onclick="fnPagination(1,<%=pagination.getTotal_pages()%>);"> <i
				class="fa fa-angle-right"></i> Next
		</a></li>

		<%
			} else {
		%>
		<li class="disabled"><a href="#"
			onclick="fnPagination(1,<%=pagination.getTotal_pages()%>);"> <i
				class="fa fa-angle-right"></i> Next
		</a></li>
		<%
			}
		%>
		<%
			if (next) {
		%>

		<li><a href="#"
			onclick="fnPagination(2,<%=pagination.getTotal_pages()%>);"> <i
				class="fa fa-angle-double-right"></i> Last
		</a></li>
		<%
			} else {
		%>
		<li class="disbled"><a href="#"
			onclick="fnPagination(2,<%=pagination.getTotal_pages()%>);"> <i
				class="fa fa-angle-double-right"></i> Last
		</a></li>
		<%
			}
		%>
	</ul>

</div>
<div class="col-lg-4 col-md-4 col-sm-4 hidden-xs text-right" style="padding: 0px;">
	<%-- <s:select cssClass="form-control" onchange="fnPagination(5,0);" list="#{'15':'15','20':'20','25':'25','50':'50','100':'100','0':'All'}" name="pagination.page_size" id="page_size" value="#request.pagination.page_size" style="max-width:100px;"/> --%>
	<s:if test="isfrominventorycatalogue==1">
		<s:select cssClass="form-control" onchange="fnPagination(5,0);" list="#{'15':'15','25':'25','50':'50','100':'100','500':'500','1000':'1000','2000':'2000','4000':'4000','8000':'8000','12000':'12000'}" name="pagination.page_size" id="page_size" value="#request.pagination.page_size" style="max-width:100px;"/>
	</s:if>
	<s:else>
		<s:select cssClass="form-control" onchange="fnPagination(5,0);" list="#{'15':'15','25':'25','50':'50','100':'100','500':'500','1000':'1000','2000':'2000','4000':'4000','8000':'8000','12000':'12000'}" name="pagination.page_size" id="page_size" value="#request.pagination.page_size" style="max-width:100px;"/>
	</s:else>
	
	
</div>





