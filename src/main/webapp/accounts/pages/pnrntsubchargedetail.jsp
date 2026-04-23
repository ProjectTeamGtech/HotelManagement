<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="accounts/js/viewstatement.js"></script>

<%@page import="com.apm.main.common.constants.Constants"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>


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
.spannew{
display: inline-block;
margin-top: -21px;
    margin-left: 15px;
}
.zoomprint{
zoom:98%;
}
.nxt{
white-space: nowrap;
}
.pnt{
white-space: nowrap;
margin-left:28px !important;
}
.cndiv{
margin-bottom: 30px;
}
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
    height: 135px;
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
</style>

<s:hidden name="clientId" id="clientId"/>	
<div class="">



<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 zoomprint">
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
						<b>Patient Name</b>
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
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>IP No.</b>
						</div>
					<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="ipdseqno"/></span>
						</div>
						</div>
			
				<s:if test="employeenamebytp!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Employee Name </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp;&nbsp;<s:property value="employeenamebytp"/>
						</div>
						</div>	
						</s:if>
						
						<s:if test="policyholder!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Policy Holder Name </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="policyholder"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="relationofuser!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Relation </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="relationofuser"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="companyname!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Company Name </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="companyname"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="neiscardno!=''">
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
						<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 ">
						<b>Consultant Name</b>
						</div>
						<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
						&nbsp;&nbsp; <span class="pnt" style="margin-left:32px;"><label>:</label>&nbsp;&nbsp;&nbsp;<s:property value="ipdconsultant"/></span>
						
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
<%} %>
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
	<s:if test="designation!=''">
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
						<label>:</label>&nbsp;&nbsp; <span style="" class="spannew"><s:property value="thirdParty"/></span>
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
	<s:if test="unit_station!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Unit/Station</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="unit_station"/></span>
						</div>
						</div>
						</s:if>
						
	<s:if test="claimid!=''">
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
	<s:if test="colliery!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Colliery ID</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="colliery"/></span>
						</div>
						</div>
						</s:if>
	<s:if test="areatp!=''">
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
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;margin-top: 7px;margin-bottom: 5px;">
					
					
					<%if(loginfo.getIskunal()==1){ %>
					<s:if test="finalDiagnosis!=''">
					<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1 hidden-print"></div>
					<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding-left:0px;padding-right:0px;">
						<div class="form-group" style="margin-bottom: 0px !important;">
							<span><b>Diagnosis</b> : <b><%String diagnosis=(String)session.getAttribute("finaldiagnosis"); %>
							<%-- <s:property value="finalDiagnosis"/> --%>
							<%=diagnosis.toString() %></b></span>
						</div>
					</div>
					<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1"></div>
					</s:if>
					<%} %>
					</div>
				</div>
				<%int i=0; %>
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
                                                                <th style="width: 4%">Sr.No.</th>
                                                                	<th style="width: 10%">Date</th>
                                                                    <th style="width: 45%;color:#ffffff !important;">Charge Name</th>
                                                                    <th style="color:#ffffff !important;" class="text-center">Code</th>
                                                                    <%if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){ %>
																			
	                                                                <% }%>
	                                                                
                                                                    
                                                                    <th style="color:#ffffff !important;" class="text-center">Unit Cost</th>
                                                                    <th style="color:#ffffff !important;" class="text-center">Qty</th>
                                                                    <th style="color:#ffffff !important;" class="text-right" style="width: 25%;">Total</th>
                                                                </tr>
                                                                </thead>
                                                                <tfoot style="color:green;">
                                                                	<tr style="border-top:2px solid black !important;">
                                                                		<td></td>
                                                                		<%if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){ %>
																			<td></td>
	                                                                	<% }%>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td class="text-right"><strong class="inline-block">Amount Charged:</strong></td>
                                                                		<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="tpstotal"/></td>
                                                                	</tr>
                                                                	<tr>
                                                                		<td></td>
                                                                		<%if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){ %>
																			<td></td>
	                                                                	<% }%>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td></td>
                                                                		<td class="text-right nxt"><strong class="inline-block">Amount Within Package :</strong></td>
                                                                		<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="subpkgamttot"/></td>
                                                                	</tr>
                                                                	
                                                                </tfoot>
                                                                <tbody>
                                                                 
	                                                                <tr style="background-color: #f5f5f5;">
	                                                              
	                                                                
	                                                    <td colspan="2"><strong><s:property value="masterchargetype"/></strong></td>
	                                                                	<%if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){ %>
																			<td></td>
	                                                                	<% }%>
	                                                                	<td></td>
	                                                                	
	                                                                	<td></td>
																		<td colspan="2" style="text-align: right;color: #5cb851;">Sub Total :<%=Constants.getCurrency(loginfo)%><s:property value="tpstotal"/></td>
									                                </tr>
									                           
                                                                <s:iterator value="assesmentList">
                                                                
																<tr>
																  <td><%=++i %></td>
																  <td><s:property value="commencing"/></td>
																<td class="padletab">
																	<%-- <s:property value="chargeid"/> --%>
																<s:property value="appointmentType"/>
																
																</td>
																		<td><s:property value="tpcode"/></td>
																	
																	
																	
																	<td class="text-right">Rs.<s:property value="unitcharge"/></td>
																	<%if(loginfo.isSaimed()){ %>
																	<td class="text-center"><s:property value="Qty"/></td>
																	<%}else{%>
																	<td class="text-center"><s:property value="Quantity"/></td>
																	<%} %>
																	
																	<td class="text-right" id="chargetotalamt<s:property value="chargeid"/>"><%=Constants.getCurrency(loginfo)%><s:property value="chargeTotal"/></td>
																</tr>
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
                                                        
                                                        
                                                    </div>
                                                    
                                                    
                                                    
                                   
                                                  
                                        
                                                    
			</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>
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
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Update Charge</h4>
      </div>
      <div class="modal-body" >
      	<table class="table table-bordered" cellspacing="0" width="100%">
      		<tbody id="chargedtailsbody">
      		
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
