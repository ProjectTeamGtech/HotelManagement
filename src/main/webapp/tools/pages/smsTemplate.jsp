<script type="text/javascript" src="tools/js/emailTemplate.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<%@page import="com.apm.Tools.eu.entity.EmailTemplate"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>SMS Template</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="">
	
	<div class="col-lg-12 col-md-12 topback2">
		<a class=" btn btn-primary" href="addSMSTemplate"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	
</div>


<div class="row">
	<div class="col-lg-12">
		<s:hidden name="message" id="message"></s:hidden>
		<s:if test="hasActionMessages()">
			<script>
				var msg = " " + document.getElementById('message').value;
				showGrowl('', msg, 'success', 'fa fa-check');
			</script>
		</s:if>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div>
			<table id="results" class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-left">Template Name</th>
						<th class="text-left">Text</th>
						<th class="text-center" style="width: 5%;">Edit</th>
						<th class="text-center" style="width: 5%;">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="emailTemplateList.size!=0">
						<s:iterator value="emailTemplateList">
							<tr>
								<td><s:property value="templateName" /></td>
								<td><s:property value="text" /></td>
								<td class="text-center">
									<a href="editSMSTemplate?selectedid=<s:property value="id"/>" style="width: 5%;"> <i class="fa fa-edit"></i></a>
								</td>
								<td class="text-center">
									<a href="deleteSMSTemplate?selectedid=<s:property value="id"/>" onclick="return confirmedDelete()" class="text-danger" style="width: 5%;"> <i class="fa fa-trash-o"></i></a>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						There is no SMS Template found!!
				</s:else>
				</tbody>

			</table>
		</div>
	</div>
</div>
<!--<s:form action="EmailTemplate" name="paginationForm" id="paginationForm"
	theme="simple">

	<div class="row">
		<div class="col-lg-4 col-md-4 text-right">
			Showing all <label class="text-info">(<s:property
					value="pagerecords" /> of <s:property value="totalRecords" />
				Records)
			</label>
		</div>
		<%-- <%@ include file="/common/pages/pagination.jsp"%> --%>
	</div>
</s:form>-->

											

											
										</div>
									</div>
								</div>
							</div>
						</div>




