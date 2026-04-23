<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.main.common.constants.Constants"%>
<script type="text/javascript" src="accounts/js/viewstatement.js"></script>
<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
 %>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<%
String hideprint="";
String color="";
String textcolor="";
String discreq="";
String discreqlist="";
if(loginfo.getIskunal()==1){
	hideprint="hidden-print";
}else{
	hideprint="";
}
%>
<script type="text/javascript">
    
window.onload = function () {
	
	var autocharge= document.getElementById('chkautocharge').value;
	if(autocharge=="1"){
		document.getElementById('autocharge').checked=true;
	} else {
		document.getElementById('autocharge').checked=false;
	}
	
		
	};
	

	$(document).ready(function() {

		$("#fdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#tdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		});
	});
	
</script>

<style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #43b9be !important;
    margin-bottom: 15px;
}
        .adformback {
            border: 1px solid;
            padding: 10px 0px 0px;
            margin-top: 0px;
            width: 98%;
            margin-left: 9px;
        }
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0px;
            text-align: right;
            font-size: 12px;
        }
        .marbot15 {
            margin-bottom: 15px;
        }
        .martop15 {
            margin-top: 15px;
        }
        .diagtitle {
            background-color: #000;
            color: #FFF;
            padding: 10px;
            font-weight: normal;
            padding-top: 12px !important;
        }
        .bednosele {
            width: 10%;
            margin-top: -40px;
        }
        .textareaheight{
        height: 50px !important;;
        }
       
       .paddtop{
        padding: 0px 0px 14px 2px;
    	}
        .widthtabhedth1{
        	width: 30%;
        }
        .widthtabhedth2{
        	width: 7%;
        }
        .admissionbackgreen {
		    width: 210px;
		}
		.form-group {
    		margin-top: 4px;
		}
		.pad8{
			padding-top: 8px;
		}
		.backgrey{
			        background-color: rgba(149, 222, 91, 0.19);
		}
		.pnameback{
			    background-color: #f5f5f5;
    			margin-top: -7px;
		}
		.panel-primary {
		    border-color: #43b9be;
		}
		.padsign{
			padding-top: 100px;
			padding-left:0px;
			padding-right:0px;
		}
		.help-block {
		    display: block;
		    margin-top: 0px !important;
		    margin-bottom: 0px !important;
		    color: #737373;
		}
		 .bordertopgreen1 {
    border-top: 2px solid #43b9be;
}
  .panel-primary {
      border-color: #43b9be;
  }
  .padsign{
   padding-top: 40px;
  }
  .help-block {
    display: block;
    margin-top: 0px;
    margin-bottom: 0px;
    color: #737373;
}
h3, .h3 {
    font-size: 16px;
    font-weight: bold;
    color: #6699cc;
    margin-top: 0px;
    margin-bottom: 5px;
}
.form-group {
    margin-bottom: 4px !important;
}
p {
    margin: 0 0 5.5px !important;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.settopbac {
    background-color: #ddd;
}
.totalbor {
    background-color: #f5f5f5;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 5px 2px 5px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 12px;
    border-right: none !important;
    border-left: none !important;
}

 @media print
{
.settopbac {
    background-color: #ddd !important;
}
.totalbor {
    background-color: #f5f5f5 !important;
}

    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
	}
	p {
	    margin: 0 0 5.5px !important;
	    font-size: 9px !important;
	}
	.form-group {
    margin-bottom: 4px !important;
}
.setotas {
    padding: 0px;
    text-align: right;
    color: #008000 !important;
    font-size: 12px;
}
.wordscolr{
	    color: #d07878 !important;
    text-transform: uppercase;
}
.titleset {
    margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    line-height: 20px;
}
h4, .h4 {
    font-size: 12px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:12px !important;
	color: firebrick !important;
}
.titleset {
    margin: 0px;
    color: #6699cc !important;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
}
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}
.table>tbody>tr>td, .table>tfoot>tr>td {
    font-size: 9px !important; 
}
.setotas{
	 padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 12px !important;
}
p {
    margin: 0 0 2.5px !important;
    font-size: 12px !important;
}
.clicniaddress {
    font-size: 11px !important;
    font-weight: bold;
}
}
    </style>
    <style>
	.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 160px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 11px;
    font-weight: bold;
}
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
.titleset{
	margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
}
label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: bold;
}
td, th {
    padding: 0px 3px 0px 5px !important;
    border-right: 1px solid #eee !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
}
.setotas{
	  padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 12px;
}
p {
    margin: 0 0 2.5px !important;
    font-size: 12px !important;
}
@media print {
  .form-control.nobug {
    border: 0;
    outline: 0;
    box-shadow: none;
  }
  .form-control.nobug {
    transition: none; /*-- THIS IS IMPORTANT -- */
  }
}
.equalfont{
font-size: 12px !important;
}
</style>
<style>
    .disabled {
        pointer-events: none;
        cursor: default;
    }
</style>
<s:hidden name="clientId" id="clientId"/>	
<div class="">



<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row letterheadhgt" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
			<div class="" style="padding-left:0px;padding-right:0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%if(!loginfo.isHidelogobillinv()){ %>
				<%@ include file="/accounts/pages/letterhead.jsp" %>
			<%} %>
			</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors">Provisional Bill</b>
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
					</div>
					</div>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;padding-top: 10px;">
			<%if(loginfo.getIskunal()!=1){ %>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Reg. No</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="abrivationid" />/<s:property value="clientId" /></span>
						</div>
				</div>
				
				<%} %>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b><%=loginfo.getPatientname_field() %> Name</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="client"/></span>
						</div>
				</div>
						
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Age / Gender</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="agegender"/></span>
						</div>
				</div>
						
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>UHID</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="abrivationid"/></span>
						</div>
						</div>
						<s:if test="ipdseqno!=''">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>IP No.</b>
						</div>
					<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><%if(loginfo.getIpd_abbr_access()==1){ %>
										<s:property value="newipdabbr"/>
										<%}else{ %>
										<s:property value="ipdseqno"/>
										<%} %> </span>
						</div>
						</div>
			</s:if>
				<s:if test="employeenamebytp!='' && employeenamebytp!='undefined'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Employee Name </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp;<s:property value="employeenamebytp"/>
						</div>
						</div>	
						</s:if>
						
						<s:if test="policyholder!='' && policyholder!='undefined'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Policy Holder Name </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="policyholder"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="relationofuser!='' && relationofuser!='undefined'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Relation </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="relationofuser"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="companyname!='' && companyname!=null" >
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Company Name </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="companyname"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="neiscardno!='' && neiscardno!='undefined'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>NEIS / Card No</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="neiscardno"/></span>
						</div>
						</div>	
						</s:if>
						
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Consultant Name</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style="margin-left:1px;"><s:property value="ipdconsultant"/></span>
						
						</div>
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 hidden">
						<b>Age on Admission</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7 hidden">
						<label>:</label>&nbsp;&nbsp; <span style="margin-left:1px;"><s:property value="Ageonaddmission"/></span>
						
						</div>
						</div>
				
				<%-- <div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Address</label><span>: <s:property value="address"/>, <s:property value="clienttown"/>, <s:property value="clientpostcode"/> </span>
				</div> --%>
			</div>
			
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;padding-top: 10px;">
				<%if(loginfo.getIskunal()!=1){ %>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Date</b>
						</div>
					<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""></span>
						<span class=""><script>
  var currentDate = new Date(),
      day = currentDate.getDate(),
      month = currentDate.getMonth() + 1,
      year = currentDate.getFullYear();
  document.write(day + "/" + month + "/" + year)
</script> 
&nbsp;|&nbsp;
<script>
	var currentTime = new Date(),
      hours = currentTime.getHours(),
      minutes = currentTime.getMinutes();

	if (minutes < 10) {
	 minutes = "0" + minutes;
  }

	var suffix = "AM";
	if (hours >= 12) {
    suffix = "PM";
    hours = hours - 12;
	}
	if (hours == 0) {
	 hours = 12;
	}

	document.write(hours + ":" + minutes + " " + suffix)
</script>
</span>
</div>
</div>
<%} %>					<s:if test="ipdseqno!=''">
						<s:if test="admissionDate!=''">	
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Admission Date</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="admissionDate"/></span>
						</div>
						</div>
						</s:if>
						<s:if test="dischargeDate!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Discharge Date</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="dischargeDate"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="dischargehead!=''">
						<s:if test="dischargestatus!=4">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Dis.Status</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp;
						<s:if test="dischargestatus==1">
						<span style="">CASE</span>
						</s:if>
						<s:elseif test="dischargestatus==2">
						<span style="">TRANSFER</span>
						</s:elseif>
						<s:elseif test="dischargestatus==3">
						<span style="">DEATH</span>
						</s:elseif>
						<s:elseif test="dischargestatus==7">
						<span style="">LAMA</span>
						</s:elseif>
						<s:elseif test="dischargestatus==8">
						<span style="">DAMA</span>
						</s:elseif>
						<s:else>
						<span style="">DISCHARGE</span>
						</s:else>
						</div>
						</div>
						</s:if>
						</s:if>
						</s:if>
	<s:if test="designation!='' && designation!='undefined'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Designation</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="designation"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="thirdParty!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Payer</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="thirdParty"/></span>
						</div>
						</div>
	</s:if>

	<s:if test="policyNo!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Policy No</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="policyNo"/></span>
						</div>
						</div>
	</s:if>
	
	
	<s:if test="companyname=='CGHS'">
	<s:if test="unit_station!='' && unit_station!='undefined'" >
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Unit/Station</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="unit_station"/></span>
						</div>
						</div>
						</s:if>
						
	<s:if test="claimid!='' && claimid!='undefined'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Claim ID</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="claimid"/></span>
						</div>
						</div>
						</s:if>
	</s:if>
	
	<s:if test="companyname=='WCL'">
	<s:if test="colliery!='' && colliery!='undefined'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Colliery ID</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="colliery"/></span>
						</div>
						</div>
						</s:if>
	<s:if test="areatp!='' && areatp!='undefined'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Area</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="areatp"/></span>
						</div>
						</div>
						
						</s:if>
	</s:if>
			</div>
		</div>
		
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
	<div class="row">
	<s:if test="ipdseqno!=0">
	<h4 class="hidden-print" style="display: flex;margin-left: 107px">Auto Charge 
		     <ul class="settings" style="padding: 0px;list-style: none; margin-left: 10px;margin-bottom: 0px;">
		      	<s:hidden name="autocharge" id="chkautocharge"></s:hidden>
                                    <li>
                                        <div class="form-group" style="margin: 0px;">
                                           <div class="onoffswitch greensea" id="salebilldiv">
                                                    <input type="checkbox" name="onoffswitch" onclick="setAutocharge()" class="onoffswitch-checkbox" id="autocharge">
                                                    <label class="onoffswitch-label" for="autocharge">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                        </div>
                                    </li>
                                </ul>
                                
                                
                              </h4>
                              </s:if>
                               <div class="form-group" style="width: 85px;margin-left: 1110px;">IPDs
                             <s:form action="detailStatement" id="detailfrm" theme="simple" >
			
			<s:select  name="selectipdid" listKey="ipdid"
				headerValue="All" headerKey="" listValue="ipdseqno"
				list="ipdseqlist"  cssClass="form-control" id="ipdidnew" onchange="selectedipdid()"/>
		</s:form>
	</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;margin-top: 7px;margin-bottom: 5px;">
					
					
							<%if(loginfo.getIskunal()==1){ %>
					<s:if test="finalDiagnosis!=''">
					<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1 hidden-print"></div>
					<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding-left:0px;padding-right:0px;">
						<div class="form-group" style="margin-bottom: 0px !important;">
							<span><b>Diagnosis</b> : <b><%--  --%>
							<%String diagnosis=(String)session.getAttribute("finaldiagnosis"); %>
							<%-- <s:property value="finalDiagnosis"/> --%>
							<%=diagnosis.toString() %>
							</b></span>
						</div>
					</div>
					<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1"></div>
					</s:if>
					<%} %>
					</div>
				</div>
	
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding: 0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<div class="">
														<form action="updateinvoicechargesStatement"  method="post" id="chargeidform">
														<s:hidden name="allchargeids" id="allchargeids"></s:hidden>
														<s:hidden name="clientId" />	
                                                        <div>
                                                            <table class="table">
                                                                <thead>
                                                                <tr>
                                                               <%-- <%if(loginfo.getIskunal()==1||loginfo.isEditchargesacs()){ %>
                                                                <th></th>
                                                                <%} %> --%>
                                                                <th></th>
                                                                  <th></th>
                                                                    <th style="width: 48%;color:#ffffff !important;">Charge Name</th>
                                                                   	<th style="color:#ffffff !important;width: 15%" class="text-center">Charge Type</th> 
                                                                    <th style="color:#ffffff !important;width: 15%" class="text-center">Code</th>
                                                                    
	                                                                
                                                                    
                                                                    <th style="color:#ffffff !important;width: 10%" class="text-right">Unit Cost</th>
                                                                    <th style="color:#ffffff !important;width: 10%" class="text-center">Qty</th>
                                                                    <th style="color:#ffffff !important;" class="text-right" style="width: 25%;">Total</th>
                                                                    <%if(!loginfo.isBalgopal()){ %>
                                                                    <th style="color:#ffffff !important;" class="text-right" style="width: 25%;">Render By</th>
                                                                    <%} %>
                                                                  <%int y=0; %>
                                                                </tr>
                                                                </thead>
                                                                <tfoot style="color:green;">
                                                                	<tr>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td class="text-right "><strong class="inline-block">Total :</strong></td>
                                                                		<td class="text-right equalfont"><%=Constants.getCurrency(loginfo)%><s:property value="dchtotal"/></td>
                                                                	
                                                                	</tr>
                                                                </tfoot>
                                                                <tbody>
                                                                <%int i=0; int j=0; %>
                                                                  <s:iterator value="masterAssessmentList">
                                                                  
                                                                  <s:if test="stdchargests">
                                                               		<% color="#A5CEFB";
                                                               		textcolor="black";
                                                               		%>   
                                                                  </s:if>
                                                                  <s:else>
                                                                  <% color="#f5f5f5";
                                                                  textcolor="";
                                                                  %>
                                                                  
                                                                  </s:else>
                                                                 
	                                                                <tr style="background-color: <%=color%>">
	                                                                
	                                                               <%--  <td><input type="checkbox" id="selectcheck"  onclick="breakage1('<s:property value="hidecode"/>')" class="hidden-print"/></td> --%>
	                                                                
	                                                                	<td colspan="3" class="equalfont" style="font-size: 12px !important"><strong><s:property value="name"/></strong></td>
	                                                                	<td class="text-center"><select  id="masterdisc<%=i%>" class="<%=discreqlist%> form-control nobug" style="width:70%" onchange="validatepayby(<%=i%>,this.value)"> 
																		<option value="" >select</option>
																		<option value="0" >Self</option>
																		<option value="1">Third Party</option></td>
	                                                                	<td></td>
	                                                                	<td></td>
																		<td colspan="2" class="equalfont" style="text-align: right;color: #5cb851;font-size: 12px !important;color: <%=textcolor%>">Sub Total :<%=Constants.getCurrency(loginfo)%><s:property value="Subtotal"/></td>
									                                </tr>
									                                <div>
                                                                <s:iterator value="assesmentList" id="">
                                                                
                                                                <s:if test="discreq">
                                                                  <%discreq="readonly"; 
                                                                  discreqlist="disabled";
                                                                  %>
                                                                  </s:if>
                                                                  <s:else>
                                                                   <%discreq=""; 
                                                                  discreqlist="";
                                                                  %>
                                                                  </s:else>
																<tr id="hidebreakage" class="hidll<s:property value='hidecode'/>">
																
																<td>
																<%if(loginfo.isEditchargesacs()){ %>
																     <!-- comment because manoj sir told  -->
<%-- 																<a href="#" onclick="confirmdelete(<s:property value='selectipdid'/>,<s:property value='assesmentid'/>)" class="<%=discreqlist%>"><img src='common/images/delete.gif'></img></a>
 --%>																<a href="#" onclick="confirmdelete('',<s:property value='assesmentid'/>)" class="<%=discreqlist%>"><img src='common/images/delete.gif'></img></a>
																
																<%--Manoj sir asked to comment 20/06/2024 --%>
																<%-- <s:if test="selectipdid!=''">
																<s:if test="appointmentid==0">
																<a href="#" onclick="confirmdelete(<s:property value='selectipdid'/>,<s:property value='assesmentid'/>)" class="<%=discreqlist%>"><img src='common/images/delete.gif'></img></a>
																</s:if>
																</s:if>
																<s:else>
																<s:if test="appointmentid==0">
																<a href="#" onclick="confirmdelete(<s:property value='ipdidnew'/>,<s:property value='assesmentid'/>)" class="<%=discreqlist%>""><img src='common/images/delete.gif'></img></a>
																</s:if>
																</s:else> --%>
																<%} %>
																</td>
																
																
																<td class="padletab" style="font-size:12px !important">
																	<s:property value="chargeid"/>
																	</td>
																<%if(loginfo.isEditchargesacs()){ %>
																<td>
																<%-- <input type="text" value="<s:property value="aptmname"/>" class="form-control" onchange='changeAptmType1(<s:property value="chargeid"/>,this.value)' style="width: 80%"> --%>
																<a href="#" class="equalfont <%=discreqlist%>" onclick="showChargeEditPopup(<s:property value="ipdidnew"/>,<s:property value="stdchargeid"/>,<s:property value="assesmentid"/>,<s:property value="stdcharge"/>,'<s:property value="aptmname"/>',<s:property value="chargeid"/>,'<s:property value="name"/>')">
																		   <s:property value="appointmentType"/>)
																		   <s:if test="ipdid!=0">
																		   		<s:property value="ward"/>
																		   </s:if>
																		   <s:if test="newshftcharge!=0">
																		   		<b style="color: red">In Process...</b>
																		   </s:if>
																		   <%if(loginfo.getClinicUserid().equals("ngppadole")){ %>
																		    &emsp; (<s:property value="showdate"/>)
																		   <%} %>
																		   <s:if test="discreq">
																		    &emsp; <span class="hidden-print"style="color:red">(Disc Req)</span> 
																		    </s:if>
																		    <s:if test="manualcharge==1">
																		    &emsp; <span class="hidden-print"style="color:#429e12">(Manual Charge)</span> 
																		    </s:if>
																		    
																		</a>
																		<s:if test="curstatus==1">
																			<b style="color: red">In Process...</b>
																		</s:if>
																</td>
																<%}else{ %>
																<td>
																	<s:if test="dna==true">
																		<s:property value="appointmentType"/> <span style="color:red">(DNA)</span>
																	</s:if>
																	<s:else>
																		<a href="#" class="equalfont <%=discreqlist%>"  onclick="showChargeEditPopup(<s:property value="ipdidnew"/>,<s:property value="stdchargeid"/>,<s:property value="assesmentid"/>,<s:property value="stdcharge"/>,'<s:property value="aptmname"/>',<s:property value="chargeid"/>,'<s:property value="name"/>')">
																		   
																		   <s:property value="appointmentType"/>)
																		   <s:if test="ipdid!=0">
																		   		<s:property value="ward"/>
																		   </s:if>
																		   <s:if test="newshftcharge!=0">
																		   		<b style="color: red">In Process...</b>
																		   </s:if>
																		   <s:if test="discreq">
																		    &emsp; <span class="hidden-print" style="color:red">(Disc Req)</span> 
																		    </s:if>
																		    <s:if test="manualcharge==1">
																		    &emsp; <span class="hidden-print"style="color:#429e12">(Manual Charge)</span> 
																		    </s:if>
																		</a>
																		<%-- <%}else{ %>
																		<s:property value="appointmentType"/>
																		   <s:if test="ipdid!=0">
																		   		<s:property value="ward"/>
																		   </s:if>
																		   <s:if test="newshftcharge!=0">
																		   		<b style="color: red">In Process...</b>
																		   </s:if>
																		<%}%> --%>
																		<s:if test="curstatus==1">
																			<b style="color: red">In Process...</b>
																		</s:if>
																	</s:else> 
																	</td><%} %>
																	 <td class="text-center">
																	 <%if(loginfo.isEditchargesacs()||loginfo.getUserType()==2){ %>
																	 <input type="hidden" id="chargeiddd<%=i%>_<%=j%>" value="<s:property value="chargeid"/>"> 
																	<select name="paybyclient" id="paybyclient<%=i%>_<%=j%>" class="<%=discreqlist%> form-control nobug" style="width:70%" onchange="changepaytype(this.value,<s:property value="chargeid"/>,<s:property value="clientid"/>)"> 
																	<s:if test="payBy==0">
																		<option value="0" selected="selected">Self</option>
																		<option value="1">Third Party</option>
																	</s:if>
																	<s:else>
																		<option value="0" >Self</option>
																		<option value="1" selected="selected">Third Party</option>
																	</s:else>
																	</select>
																	<%}else{ %>
																	<s:if test="payBy==0">
																	Self
																	</s:if>
																	<s:else>
																	Third Party
																	</s:else>
																	<%} %>
																	 </td> 
																<%if(loginfo.isEditchargesacs()||loginfo.getIskunal()==1){ %>
																	<td><input type="text" <%=discreq%> value="<s:property value="apmtcode"/>" class="form-control nobug" onchange='changeAptmTypeCode1(<s:property value="chargeid"/>,this.value)' style="text-align: center;">
																	 </td>
																	 <%}else{ %>
																	 <td class="text-center"><s:property value="apmtcode"/></td>
																	 <%} %> 
																	
																	<%-- <td><s:property value="appointmentType"/></td> --%>
																	<%-- <%if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){ %>
																		<td class="text-center">
																		<input type="checkbox" class="changeqtyclass hidden-print" value="<s:property value="chargeid"/>" ></td>
																	<% }%> --%>
																	
																	
																	<input type="hidden" class="dsdsclasss"  value="<%=j%>">
																	<%if(loginfo.isEditchargesacs()||loginfo.getIskunal()==1){ %>
																      <td class="text-center"><input type="text" <%=discreq%> style="text-align: center;"  value="<s:property value="charges"/>"onchange='changeUnitCost(<s:property value="chargeid"/>,this.value)' class="form-control nobug" id="changeunitq<s:property value="chargeid"/>"></td>
														     <%}else{ %>
																	 <td class="text-right">Rs.<s:property value="charges"/></td>
																	<%} %>
																	<input type="hidden" id="originalunitcst<s:property value="chargeid"/>" value="<s:property value="charges"/>"/>
																	<%if(loginfo.isEditchargesacs()||loginfo.getIskunal()==1){if(loginfo.isSaimed()){ %>
																	 
																		<td class="text-center">
																		<input type="hidden" id="chargeamt<s:property value="chargeid"/>" value="<s:property value="charges"/>" >
																		<input style="text-align: center;" maxlength="3" size="5" <%=discreq%> type="Number" name="changeqtxt<s:property value="chargeid"/>" id="changeqtxt<s:property value="chargeid"/>"
																		onchange="changeChargeQtyNew(<s:property value="chargeid"/>,this.value)" class="form-control nobug "
																		onkeypress="return isNumberKey(event,this);" value="<s:property value="Qty"/>"></td>
																		
																	<%}else{ %>
																	<td class="text-center">
																	<input type="hidden" id="chargeamt<s:property value="chargeid"/>" value="<s:property value="charges"/>" >
																	<input style="text-align: center;" maxlength="3" size="5" <%=discreq%> type="Number" name="changeqtxt<s:property value="chargeid"/>" id="changeqtxt<s:property value="chargeid"/>"
																	onchange="changeChargeQtyNew(<s:property value="chargeid"/>,this.value)" class="form-control nobug "
																	onkeypress="return isNumberKey(event,this);" value="<s:property value="Quantity"/>"></td>
																	    
																	<%}}else{%>
																		<td class="text-center"><s:property value="Qty"/></td>
																	<%} %>
																	<td class="text-right" style="font-size:12px !important;" id="chargetotalamt<s:property value="chargeid"/>"><%=Constants.getCurrency(loginfo)%><s:property value="chargeTotal"/></td>
																	  <%if(!loginfo.isBalgopal()){ %>
																	<td style="vertical-align: middle;"><a href="#" onclick="renderpopup('<s:property value="chargeid"/>','<s:property value="appointmentType"/>','<s:property value="charges"/>')"><i style="vertical-align: sub;" class="fa fa-edit"></i></a></td>
																	<%} %>
																
																</tr>
																<%j++; %>
																</s:iterator>
																</div>
																 <% i++; %>
																</s:iterator>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        
                                                       <%--  <div>
                                                        	<%if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){ %>
																<input type="button" onclick="updateAllChargesQty()" class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="Update"/>
																
															<% }%>
                                                        </div> --%>
                                                        </form>
                                                        
                                                         <div>
                                                        	<%if(loginfo.isEditchargesacs() || loginfo.getAcaccess().equals("1")){ %>
																<a href="#" onclick="openPacsPopup('prntchrgStatement?ipdid=<s:property value="selectipdid"/>')"><input type="button"  class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="Preview"/></a>
																<a href="#" onclick="refreshPkgData(<s:property value="ipdseqno"/>,<s:property value="clientId" />)"><input type="button"  class="btn btn-primary savebigbtn hidden-print" style="float: right; margin-right: 4px" value="Update"/></a>
																
															<% }%>
															<%if(loginfo.isIndv_discount()){ %>
														 <a href="#" onclick="openPacsPopup('indvdiscountStatement?clientid=<s:property value="clientId" />')"><input type="button"  class="btn btn-primary savebigbtn hidden-print" style="float: right; margin-right: 4px" value="Discount"/></a> 
														 <%} %> 
														  <a href="#" onclick="openPacsPopup('Accounts?clientId=<s:property value="clientId" />')"><input type="button"  class="btn btn-primary savebigbtn hidden-print" style="float: right; margin-right: 4px;width: 14%" value="Create Invoice"/></a> 
                                                        </div>
                                                    </div>
                                                    
                                                    
                                                    
                                   
                                                    <div class="">
                                            <!-- col -->
                                            	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;margin-top:15px;">
                                            		<div class="col-lg-8 col-xs-6 col-md-6 col-sm-6" style="padding: 0px;">
                                            		<div class="form-group hidden">
                                            			<p style="margin: 0px 0px 4px 0px;">Payment Mode : <s:property value="paymode"/>
                                            				<s:if test="paymode=='Cheque'">
							                            		(<s:property value="chequeno"/>)
							                         		</s:if></p>
							                         		<p style="margin: 0px 0px 4px 0px;">Prepared By : <s:property value="preparedby"/></p>
							                         		<p style="margin: 0px 0px 4px 0px;">Notes : <s:property value="submitInvoiceNotes"/></p>
                                            		</div>
                                            		</div>
                                            		
                                            		
                                            	</div>
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden" style="padding:0px;">
								               <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left hidden" style="padding-top: 80px;">
								               	 <s:if test="payby!='Third Party'">
															<span>Authorized Signatory</span>
												</s:if>
								               </div>
								               <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding:0px;">
								               <div class="form-group" style="color: #d07878;text-transform: uppercase;">
												<span>In Words : <s:property value="totalinword"/> Only.</span>
											</div>
								               </div>
								               
								               </div>
                                            <!-- /col -->
                                        </div>
                                        
                                                    
			</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
	<div class="row <%=hideprint%>">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding: 0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<h4 id = "headingTitle">Log</h4>
				<div class="table-responsive" id="appoint-height">
					<table id="dataList" class="table">

					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>


<div class="row <%=hideprint%>">
<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding: 0px;">
		<h4><b>Advance & Refund</b></h4>
			<s:actionmessage cssClass="messageDiv" />
			<div>
				<table class="table">
					<thead>
						<tr>
							<!-- <th>Date</th> -->
							<th title="Sort by Date" class="sortable <s:if test="#attr.pagination.sortColumn.equals('date')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
							<a href="#" onclick="fnPagination(6,'date');" style="color:#ffffff !important;">Date</a></th>
							<th>Payee</th>
							<th>Payment Mode</th>
							<th>Credit</th>
							<th>Debit</th>
							<th>Balance</th>
							<th>Receipt</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="creditList.size!=0">
							<s:iterator value="creditList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property value="commencing" /></td>
									<td><s:property value="payby" /></td>
									<s:if test="paymentmode=='prepayment'">
										<td>Credit Balance</td>
									</s:if>
									<s:else>
										<td><s:property value="paymentmode" /></td>
									 </s:else>
									
									<td><%=Constants.getCurrency(loginfo)%><s:property value="charges" /></td>
									<s:if test="advref==0">
										<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" /></td>
									</s:if>
									<s:else>
										<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" /> (Refund)</td>
									</s:else>
									<td><%=Constants.getCurrency(loginfo)%><s:property value="balancex" /></td>
									
									<td>
										<s:if test="debitTotalx==0">
											<a href="advprintCharges?id=<s:property  value="id" />">Print </a>
										</s:if>
									</td>
									
									</tr>
								</s:iterator>
								
								
							</s:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
                                                    
          <div class="col-lg-12 col-md-12 hidden-print" style="margin-bottom:10px;padding: 0px;">
          <div class="col-lg-1 col-md-1"></div>
		                            <div class="col-lg-10 col-md-10" style="padding:0px;">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;text-align: right;">
	                                  		<a href="#" onclick="printpage();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Print">Print</a>
		                                </div>
		                            </div>
		                            <div class="col-lg-1 col-md-1"></div>
		                        </div>       



</div>
</div>



<!-- Modal Email-->


<!-- Dropdown Modal -->
<s:form action="updchargeStatement" id="savechargefrm" theme="simple">
<div class="modal fade modal-draggable" id="dtailschargemodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document" style="width: 50%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Update Charge Details</h4>
      </div>
      <div class="modal-body" style="overflow-y: auto;height: 373px;">
      	<table class="table table-bordered" cellspacing="0" width="100%">
      		<tbody id="chargedtailsbody">
      		
      		</tbody>
      	</table>
       	
      </div>
      <div class="modal-footer">
        <!-- <button type="submit" class="btn btn-primary " style="width: 50%;margin-right: 156px" onclick="editdisctype()">UPDATE CHARGE</button> -->
        <a href="#"  class="btn btn-primary " style="width: 50%;margin-right: 156px" onclick="updatechargedata()">UPDATE CHARGE</a>
      </div>
    </div>
  </div>
</div> 

</s:form>
<!--

Bed Log model

-->

<s:form action="updbedStatement" id="savebedlogfrm" theme="simple">
<div class="modal fade modal-draggable" id="bedlogmodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Update Charge</h4>
      </div>
      <div class="modal-body" >
      	<table class="table table-bordered" cellspacing="0" width="100%">
      		<tbody id="bedlogbody">
      		
      		</tbody>
      	</table>
       	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary " onclick="editdisctype()">Save changes</button>
      </div>
    </div>
  </div>
</div> 

</s:form>

<!-- Render Charges Popup -->
<div id="rnderpopup" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm" style="width: 30%; height: 30%">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="modal-title">Render By <label id="patientnm"></label></h4>
        </div>
  <div class="modal-body">
      <div class="row">  
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                   <input type="hidden" id="charge_id">
        	   
        	   <div class="form-group">
				     <s:select id="doctor" name="Fullname" list="Consultantdoclist" listValue="Fullname" listKey="id"
									headerKey="" headerValue="Select Doctor"
									title="Select Doctor form list"
									cssClass="form-control chosen-select" data-toggle="tooltip"/>
			      </div>
			      <br>
		             			
                <div class="form-group" >
        				 <label>Charge Name</label><label class="text-danger">*</label>
        				 <span id="chrge_name"  Class="form-control"></span>
				            
        	    </div>
        	    
        	    <br>
        
        	     <div class="form-group" id="sitting_num" >
        				 <label>Charge</label><label class="text-danger">*</label>
				              <input type="number" id="charge" name="sitting" readonly="readonly"
				                Class="showToolTip form-control" data-toggle="tooltip" />
        	    </div>
        	   
      
           
      		</div>
     </div>
     <div class="modal-footer">
     	<button type="button" class="btn btn-primary"
							onclick="saverender()" style="margin-top: 15px;">Save</button>
          </div>
       </div>
     </div>
   </div>
</div>  

<script>
    function showhide()
     {
           var div = document.getElementById("newpost");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }
  </script>
 <script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/chosen_v1.1.0/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
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
