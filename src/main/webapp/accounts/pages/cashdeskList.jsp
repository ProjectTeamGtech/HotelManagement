<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="accounts/js/cashdesk.js"></script>
<%@taglib uri="/struts-tags" prefix="s"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li class="active">Cashdesk List</li>
		</ol>
	</div>
</div>

<div class="row">	
	<div class="col-lg-10 col-md-10">
		<s:form action="searchCashDesk" theme="simple" id = "clientFrm">
		<div class="col-lg-8 col-md-8">
			<div class="input-group">			
				<s:textfield theme="simple" name="searchText"
					placeholder="Search By First name, Last name" size="80" cssClass="form-control" />
				<span class="input-group-btn"> <input type="submit"
					value="Go" class="btn btn-primary" />
				</span>				
			</div>
			</div>
		</s:form>
		

<div class="col-lg-2 col-md-2">
	<a href="addCashDesk" class="btn btn-primary"
		style="text-decoration: none"><i class="fa fa-plus"></i> Add New</a>
</div>

</div>
</div>
<br><br>

<div class="row">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="results"	class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="sortable <s:if test="#attr.pagination.sortColumn.equals('firstname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'firstname');">Patient Name</a></th>
						<th class="text-center">Amount</i></th>
						<th class="text-center">Date & Time</th>
						<th class="text-center" style="width: 5%;">Edit</th>
						<th class="text-center" style="width: 5%;">Delete</th>
					</tr>					
				</thead>
				<tbody>
						<s:if test="cashDeskList.size!=0">
						<s:iterator value="cashDeskList">						
						<tr>
						<td><s:property value="clientName" /> 

							<td><%=Constants.getCurrency(loginfo) %><s:property value="amount" /></i></td>
							<td><s:property value="datetime" /></td>

							<s:hidden value="%{id}" name="id"></s:hidden>
							<s:url action="editCashDesk" id="edit">
								<s:param name="selectedid" value="%{id}"></s:param>
							</s:url>
							<td class="text-center"><s:a href="%{edit}"
									cssClass="text-warning">
									<i class="fa fa-edit"></i>
								</s:a></td>
							<s:url action="deleteCashDesk" id="delete">
								<s:param name="selectedid" value="%{id}"></s:param>
							</s:url>
							<td class="text-center"><s:a href="%{delete}"
									onclick="return confirmedDelete()" cssClass="text-danger">
									<i class="fa fa-trash-o"></i>
								</s:a></td>								
						</tr>
					</s:iterator>
					</s:if>
				</tbody>
				
				<s:else>
						There is no Cashdesk List found!!
				</s:else>
			</table>
		</div>
	</div>
</div>



<s:form action="moveCashDesk" name="paginationForm" id="paginationForm" theme="simple">
	<div class="col-lg-12 col-md-12" style="padding: 0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
			Total:<label class="text-info"><s:property value="totalRecords" />
			</label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>



