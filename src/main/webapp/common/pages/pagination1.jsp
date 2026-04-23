<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@page import="com.apm.common.utils.Pagination"%>
<%
boolean previous = false;
boolean next = false;
%>
<%
					//Pagination pagination = (Pagination) request.getAttribute("pagination");
					Pagination pagination = (Pagination)request.getAttribute("pagination");
						previous = false;
					if(pagination.getPage_number() > 1){
						previous = true;
					}
						 next = true;
					if(pagination.getTotal_pages() == pagination.getPage_number()){
						next = false;
					}
				%>


		
		<input type="text" style="display: none;" name="pagination.sortColumn" id="sortColumn" value="<%=pagination.getSortColumn()%>"/>
		<input type="text" style="display: none;" name="pagination.sortOrder" id="sortOrder" value="<%=pagination.getSortOrder()%>"/>
		
			
		<%-- <table width="10%">
			<tr>
			  <td class="pagination-label" width="10%" nowrap="nowrap">
			   
			  </td>
			  <td>
			  	<%if(previous){%>
			  	<a href="#" onclick="fnPagination(4,<%=pagination.getTotal_pages()%>);">
			  		<input type="button" value="First"/>
			  	</a>
			  	<%}else{ %>
			  		<input type="button" value="First" disabled="disabled"/>
			  	<%} %>
			  </td>
			  <td>
			  	<%if(previous){%>
			  	<a href="#" onclick="fnPagination(3,<%=pagination.getTotal_pages()%>);">
			  		<input type="button" value="Previous"/>
			  	</a>
			  	<%}else{ %>
			  		<input type="button" value="Previous" disabled="disabled"/>
			  	<%} %>
			  </td>
			  <td class="pagination-label" nowrap="nowrap"></td>
			  <td>
			  	<input  name="pagination.page_number" id="page_number" class="pagination-textbox" <%if(!previous && !next) {%>readonly="readonly"<%}%>
			  	style="width: 10px;" maxlen="1" value="<%=pagination.getPage_number()%>" type="hidden" onclick="fnPagination(7,<%=pagination.getTotal_pages()%>);"/></td>
			  <td class="pagination-label" nowrap="nowrap"></td>
			  
			  <td>
			  	<% if(next){ %>
			  	<a href="#" onclick="fnPagination(1,<%=pagination.getTotal_pages()%>);">
			  		<input type="button" value="Next"/>
			  	</a>
			  	<%}else{ %>
			  		<input type="button" value="Next" disabled="disabled"/>
			  	<%} %>
			  </td>
			  <td><% if(next){ %>
			  	<a href="#" onclick="fnPagination(2,<%=pagination.getTotal_pages()%>);">
			  		<input type="button" value="Last"/>
			  	</a>
			  	<%}else{ %>
			  		<input type="button" value="Last" disabled="disabled"/>
			  	<%} %>
			  </td>
			  <td>&nbsp;</td>
			 
			  <td class="pagination-linkoff" style="" nowrap="nowrap">
			  	<s:select onchange="fnPagination(5,0);" list="#{'5':'5','10':'10','50':'50','100':'100','0':'All'}" theme="simple" name="pagination.page_size" id="page_size" value="#request.pagination.page_size"/>
			  </td>
			 
			  
			</tr>
					
			
		</table> --%>
		