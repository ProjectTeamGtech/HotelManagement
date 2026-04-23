<%@page import="com.apm.Registration.eu.entity.Currency"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Investigation TAT Report",
					filename: "InvestigationTATReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
});
</script>
<style>
.text-center{
text-align: center;
}
</style>
<div class="">
	<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
	<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Investigation TAT Report</h4>

									</div>
								</div>

<div class="row ">

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="investigationtatReport" theme="simple" id = "investigationtatfrm">
<div class="col-lg-12 col-md-12 topback2 hidden-print">
<div class="form-inline">
			
			<div class="form-group" style="width:7%;">
			FROM DATE
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:5%;">
			TO DATE	
			</div>
			<div class="form-group" style="width:7%;">
			<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="form-group">
												<s:select headerKey="0" headerValue="Select All" cssClass="form-control" list="#{'1':'Collect', '2':'Collected', '3':'Completed' , '4':'Approved'}" name="filter_status" />
			</div>
			<div class="form-group">
												<s:select headerKey="0" headerValue="Select All" cssClass="form-control" list="#{'IPD':'IPD', 'OPD':'OPD', 'URGENT':'URGENT'}" name="filter_ward" />
			</div>
			<div class="form-group" style="width: 10%;">
												<s:select name="invsttype" id="invest" cssClass="form-control chosen-select" 
                                              list="invsTypeList" listKey="id" listValue="name" 
                                              headerKey="0" headerValue="Select Investigation Type" cssStyle="width:100%;"/>
											</div>
											
											<div class="form-group" style="width: 10%;">
											<s:select headerKey="" headerValue="Select Delete/Active" cssStyle="width:90%" cssClass="form-control" list="#{'0':'Active', '1':'Deleted'}" name="isdeleted" />
											</div>
			&nbsp;&nbsp;&nbsp;
			
			
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
				<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
				</div>

</div>
</div>
</s:form>
<%int i=1; %>
<table class="my-table xlstable" style="width: 100%">

				<tr bgcolor="#4E7894" >
				<td style="color:#eee;">Sr. No.</td>
				<td style="width: 9%;color:#eee;">Requested</td>
				<td style="width: 17%;color:#eee;">I.ID | Req. ID | UHID</td>
				<td style="width: 15%;color:#eee;">Patient Details</td>
				<td style="width: 15%;color:#eee;">Mob No</td>
				<td style="width: 20%;text-align: left;color:#eee;">Investigation</td>
				<td style="width: 9%;text-align: center;color:#eee;">Collect Date</td>
				<td style="width: 9%;text-align: center;color:#eee;">Collected Date</td>
				<td style="width: 9%;text-align: center;color:#eee;">Completed Date</td>
				<td style="width: 9%;text-align: center;color:#eee;">Approved Date</td>
				<td style="width: 9%;text-align: center;color:#eee;">Time(collect-approve)(Min)</td>
				</tr>
				<s:if test="investigationtatlist.size!=0">
				<s:iterator value="investigationtatlist">
				<tr>
				<td><%=i++ %></td>
				<td ><s:property value="date"/></td>
							<td><s:property value="id" /> | <s:property value="invreq" />
								| <s:property value="abrivationid" /></td>
				<td><s:if test="deleted==0">
								<s:if test="urgentward==1">
									<span > <s:property
											value="fullname" /> | <s:if test="ipdid!=0">
											<s:property value="wardname" /> / <s:property
												value="bedname" />
											<span style="color: palevioletred;"><s:if
													test="validnote==1">
												</s:if></span>
										</s:if>
									</span>
								</s:if>
								<s:else>
									<span> <s:property value="fullname" /> <s:if
											test="ipdid!=0">
											<s:property value="wardname" /> / <s:property
												value="bedname" />
										</s:if>
									</span>
								</s:else>
							</s:if>
							<s:else>
								<span style="text-decoration: line-through; cursor: default;">
									<s:property value="fullname" /> | <s:if test="ipdid!=0">
										<s:property value="wardname" /> / <s:property value="bedname" />
									</s:if>
								</span>
							</s:else>
							</td>
							<td class="text-center"><s:property value="Mobile"/></td>
							<td class="text-left"><s:if test="deleted==0">
																<span style="font-size: 9px;"><s:property value="invsttype" /></span>
															</s:if>
															<s:else>
																<span style="text-decoration: line-through;cursor: default;font-size: 9px;"><s:property value="invsttype" /></span>
															</s:else></td>
				<td class="text-center"><s:property value="date"/></td>
				<td class="text-center"><s:property value="collect_date" /></td>
				<td class="text-center"><s:property value="update_date" /></td>
				<td class="text-center"><s:property value="complete_date" /></td>
				<td class="text-center"><s:property value="new_invistigation" /></td>
				
				 </tr>
				</s:iterator>
				</s:if> 
				</table>
</div>
</div>


<s:form action="investigationtatReport" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromDate"></s:hidden>
							     <s:hidden name="toDate"></s:hidden>
							      <s:hidden name="filter_status"></s:hidden>
							      <s:hidden name="filter_ward"></s:hidden>
							      <s:hidden name="invsttype"></s:hidden>
							      <s:hidden name="isdeleted"></s:hidden>
								<div class="col-lg-12 col-md-12" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form> 
							</div>
							<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>