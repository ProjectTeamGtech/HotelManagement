<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<style>
.my-table th{
background-color: #01a725 !important;
padding-top: 10px !important;
padding-bottom: 10px !important;

}
.my-table td{

color: #1d1010 !important;
padding-top: 10px !important;
padding-bottom: 10px !important;
font-family: sans-serif !important;

}
.fnt{

}
</style>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "hsnwisegst",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$(".fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		
		minDate : '0',
		changeMonth : true,
		changeYear : true

	});

	$(".toDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
});

</script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 fnt">
<div class="row details" style="background: #145d21 !important;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h3 align="center" style="font-weight: bold"> <i class="fa fa-cog"></i> HOSPITALS  <i class="fa fa-cog"></i>  </h3>

									</div>

</div>	

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top:20px" >
<table class="my-table xlstable" style="width:100%;border: 2px dashed black !important;margin-bottom: 20px !important;" >
<tr style="border: 2px dashed grey !important;">
<th style="width:10%;text-align:center; ">Sr.No</th>
<th>Hospital</th>
<th>Clinic ID</th>
<th style="width:30%;text-align:center; ">IP Address</th>
<th>Expiry Date</th>
<th>Subscription Date</th>

<th>Status</th>
<!-- <th>Settings</th> -->
</tr>

<%int i=0; %>
<s:iterator value="cliniclist">
<tr style="border: 2px dashed #4E7894 !important;">
<td style="width:10%;text-align:center; "><%i=i+1; %><%=i %></td>
<td><s:property value="clinicName"/></td>
<td><s:property value="clinicID"/></td>
<td style="text-align:center; "><s:property value="ipaddr"/></td>
<%-- <td><s:property value="expirey_date"/></td> --%>
<td><input type="text" class="form-control fromDate"  value="<s:property value="expirey_date"/>" name="expirey_date" readonly="readonly" onchange="give_expirey_date('<s:property value="clinicID"/>',this.value,'expdt')" style="width:50%;border: 2px solid blue"></td>

<td><input type="text" class="form-control toDate"  value="<s:property value="subscription_date"/>" name="subscription_date" readonly="readonly" onchange="give_expirey_date('<s:property value="clinicID"/>',this.value,'subdt')" style="width:50%;border: 2px solid blue"></td>

<s:if test="active_clinic">
<td><button class="btn btn-success" onclick="sethospitalstatus('<s:property value="clinicID"/>','0')">Active</button></td>
</s:if>
<s:else>
<td><button class="btn btn-danger" onclick="sethospitalstatus('<s:property value="clinicID"/>','1')">DeActive</button></td>
</s:else>

</tr>
</s:iterator>
</table>
</div>
</div>