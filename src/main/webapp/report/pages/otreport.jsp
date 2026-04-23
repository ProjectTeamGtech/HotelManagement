<%@page import="com.apm.Registration.eu.entity.Currency"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "OT Report",
					filename: "OT_Report",
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

										<h4>OT Report</h4>

									</div>
								</div>

<div class="row ">

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="otreportReport" theme="simple" id = "opdtatreportfrm">
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
					<s:select name="otroom" list="otroomlist" listKey="name" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="Select OT" theme="simple" ></s:select>
				</div>
				<div class="form-group">
					<s:select name="otuser1" list="surgeonlist" listKey="id" listValue="fullname" cssClass="form-control chosen-select" headerKey="0" headerValue="Select OT User" theme="simple" ></s:select>
				</div>
				<div class="form-group">
				<s:select  name="anesthesia" id="anesthesia" list="anesthesiaList"
				listKey="id" listValue="reference" cssClass="form-control showToolTip chosen-select"
				headerKey="0" headerValue="Select Anesthesia" />
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
					<td style="color:#eee">Date</td>
					<td style="color:#eee" class="text-center">OT No.</td>
					<td style="color:#eee" class="text-center">UHID</td>
					<td style="color:#eee"class="text-center"> Patient Name</td>
					<td style="color:#eee"class="text-center"> Mob No</td>
					<td style="color:#eee"class="text-center"> IPD No.</td>
					<td style="color:#eee"class="text-center"> Surgeon Name</td>
					<td style="color:#eee"class="text-center"> Anesthesia Dr Name</td>
					<td style="color:#eee"class="text-center"> Assistant Dr Name</td>
					<td style="color:#eee"class="text-center"> Amount</td>
					<td style="color:#eee;" class="text-center">Procedure</td>
					<td style="color:#eee;" class="text-center">Duration</td>
				</tr>
				<s:if test="otreportlist.size!=0">
					<s:iterator value="otreportlist">
						<tr>
							<td><%=i++ %></td>
							<td ><s:property value="commencing"/></td> 
							<td class="text-center"><s:property value="diaryusername"/></td>
							<td class="text-center"><s:property value="abrivationid"/></td>
							<td class="text-center"><s:property value="clientname"/></td>
							<td class="text-center"><s:property value="Mobno"/></td>
							<td class="text-center"><s:property value="ipdseqno"/></td>
							<td class="text-center"><s:property value="surgeon"/></td>
							<td class="text-center"><s:property value="anesthesia"/></td>
							<td class="text-center"><s:property value="asistantdoctor"/></td>
							<td class="text-center"><s:property value="payment"/></td>
							<td class="text-center"><s:property value="procedures"/></td>
							<td class="text-center"><s:property value="duration"/></td>
						</tr>
					</s:iterator>
				</s:if> 
				</table>
</div>
</div>


<s:form action="otreportReport" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromDate"></s:hidden>
							     <s:hidden name="toDate"></s:hidden>
                                  <s:hidden name="otroom"></s:hidden>
                                   <s:hidden name="anesthesia"></s:hidden>
                                    <s:hidden name="otuser1"></s:hidden>
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