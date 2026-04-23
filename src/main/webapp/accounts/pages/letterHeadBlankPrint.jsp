<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="common/BootstrapNew/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
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
    margin-bottom: 0px !important;
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
    border: 1px solid #555;
    font-weight: normal;
    font-size: 12px;
    height: 23px;
}

 @media print
{
.imgheights{
height: 75px !important;
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
    margin-bottom: 0px !important;
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
.form-control {
    border:none !important;
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
    font-size: 12px;
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
    border: 1px solid #555 !important;
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
.form-group {
    margin-top: 0px;
}

textarea.form-control {
    height: auto;
    background-color: #dff0d84a;
}
.form-control {
    background-color: #dff0d84a;
}
</style>
</head>

<body>
<div class="col-lg-12 col-xs-12 col-md-12">
<s:form action="saveoptimalformNotAvailableSlot" id="saveoptionalform" theme="simple" method="post">
	<!--<div class="row" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <div class="clinicname"><s:property value="clinicName"/> <span class="rgeno"> <s:if test="registerno!=null">Reg. No : <s:property value="registerno"/> </s:if></span></div>
					<div class="clicniaddress"> <s:property value="clinicaddress"/><br></div>
						<div class="bottext mabset">Tel/Fax:<s:property value="clinicPhone"/>, E: <s:property value="clinicemail"/>, W: <s:property value="websiteUrl"/><br></div>
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 hidden text-right" style="padding-left:0px;padding-right:0px;">
				<p style="margin: 0px;"><b></b></p>
				<p style="margin: 0px;"></p>
				<p style="margin: 0px;">Reg.No. 091334</p>
				<p style="margin: 0px;"></p>
				<p style="margin: 0px;">Time: <s:property value="sTime"/> to <s:property value="endTime"/> </p>
				
			</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	-->
	<div class="row letterheadhgt" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
			<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
			<div class="col-lg-1 col-md-1"></div>
	</div>
	
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
			<div class="col-lg-7 col-md-7 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
				<s:hidden  name="clientId"></s:hidden>
				<s:hidden  name="opdid"></s:hidden>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">UHID</label><span>: <s:property value="abrivationid"/> </span> <span>&nbsp; | &nbsp;</span> <label for="inputEmail3" class="control-label">OPD ID</label><span>: <s:property value="opdid" /></span>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Patient Name</label><span>: <s:property value="client"/> </span><span class="hidden-print">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Age / Gender</label><span class="hidden-print">: <s:property value="agegender" /><span class="hidden-print"> &nbsp; | &nbsp;</span></span><label for="inputEmail3" class="control-label hidden-print">Weight</label><span class="hidden-print">: <s:property value="weight"/> kgs  &nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Height</label><span class="hidden-print">:<s:property value="height"/> cm</span>
				</div> 
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Age / Gender</label><span>: <s:property value="agegender" /></span>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">City</label><span>: <s:property value="city"/> </span>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Contact</label><span>: <s:property value="mobile"/> </span>
				</div>
				
				
			</div>
			<div class="col-lg-5 col-md-5 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
				<div class="form-group hidden-print" style="margin-bottom: 3px;">
					<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a><br>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Date</label><span>: <script>
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
				
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">BMI</label><span>: <s:property value="bmi"/></span>&nbsp; | &nbsp;<label for="inputEmail3" class="control-label">Pulse</label><span>: <s:property value="pulse"/>/mm</span><span class="hidden-print">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Sys-BP / Dia-BP</label><span class="hidden-print">: <s:property value="sysbp"/> mm HG / <s:property value="diabp" /> mm HG</span>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Temperature</label><span>: ________ </span>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Sys-BP / Dia-BP</label><span>: <s:property value="sysbp"/> mm HG / <s:property value="diabp" /> mm HG</span>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Weight</label><span>: <s:property value="weight" /> kg</span>&nbsp; | &nbsp;<label for="inputEmail3" class="control-label">Height</label><span>:<s:property value="height"/> cm</span>
				</div>
			</div>
		</div>
		
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<div class="form-group" style="margin-bottom: 3px;">
					<b>Consultant : <s:property value="practitonerName"/> (<s:property value="userQualification"/>) | <s:property value="jobtitle"/> <s:if test="useregno!=null">| Register No:<s:property value="useregno"/></s:if></b>
				</div>
			</div>
			<span class="hidden hidden-lg hidden-md hidden-sm visible-print" style="margin-top: 700px;position: absolute;">Printed By: <s:property value="printedBy"/></span>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<s:if test="blankletterhead==0">
	</s:if>
	<s:else>
	
	
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<b>Complaints:</b>
				<div class="row">
				<div class="col-lg-9 col-xs-9 col-sm-9">
					<p>Dimness of vision : DV / NV / Both : RE/LE/BE, Pain, Redness, Watering, Itching, Floaters,Flashes.</p>
					<p>Headache : Vomitting, Giddiness.</p>
					<p>Wants : Routine check up/Change of glass/C.L,Iop check up, Surgery.</p>
					<p>Follow Up : Feels better/same/worsening</p>
				</div>
				<div class="col-lg-3 col-xs-3 col-sm-3">
					<p>DM</p>
					<p>HTN</p>
					<p>IHD</p>
					<p>Resp allergy</p>
					<p>Drug allergy</p>
				</div>
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<div class="row">
				<div class="col-lg-3 col-xs-3 col-sm-3">
					<b>R.E</b>
					<table class="table table-bordered table-responsive">
						<tbody>
							<tr>
								<td style="width: 31%;">Vision</td>
								<td><b>D</b></td>
								<td><b>N</b></td>
							</tr>
							<tr>
								<td>Un aided</td>
								<td><s:textfield cssClass="form-control" name="re_unaided_d" id="re_unaided_d" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_unaided_n" name="re_unaided_n" readonly="readonly"/></td>
							</tr>
							<tr>
								<td>With Glass</td>
								<td><s:textfield cssClass="form-control" id="re_withglass_d" name="re_withglass_d" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_withglass_n" name="re_withglass_n" readonly="readonly"/></td>
							</tr>
							<tr>
								<td>GT+P.H</td>
								<td><s:textfield cssClass="form-control" id="re_gtph_d" name="re_gtph_d" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_gtph_n" name="re_gtph_n" readonly="readonly"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-lg-3 col-xs-3 col-sm-3">
					<b>L.E</b>
					<table class="table table-bordered table-responsive">
						<tbody>
							<tr>
								<td><b>D</b></td>
								<td><b>N</b></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" name="le_unaided_d" id="le_unaided_d" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_unaided_n" name="le_unaided_n" readonly="readonly"/></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" id="le_withglass_d" name="le_withglass_d" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_withglass_n" name="le_withglass_n" readonly="readonly"/></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" id="le_gtph_d" name="le_gtph_d" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_gtph_n" name="le_gtph_n" readonly="readonly"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-lg-3 col-xs-3 col-sm-3">
					<b>IOP (mm Hg)</b>
					<table class="table table-bordered table-responsive">
						<tbody>
							<tr>
								<td style="width: 31%;"></td>
								<td><b>R</b></td>
								<td><b>L</b></td>
							</tr>
							<tr>
								<td>Air</td>
								<td><s:textfield cssClass="form-control" id="air_r" name="air_r" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="air_l" name="air_l" readonly="readonly"/></td>
							</tr>
							<tr>
								<td>Perkins</td>
								<td><s:textfield cssClass="form-control" id="perkins_r" name="perkins_r" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="perkins_l" name="perkins_l" readonly="readonly"/></td>
							</tr>
							<tr>
								<td>Appl</td>
								<td><s:textfield cssClass="form-control" id="appl_r" name="appl_r" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="appl_l" name="appl_l" readonly="readonly"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-lg-3 col-xs-3 col-sm-3">
					<b>Keratometry</b>
					<table class="table table-bordered table-responsive">
						<tbody>
							<tr>
								<td><s:textfield cssClass="form-control" id="keratometry1" name="keratometry1" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="keratometry2" name="keratometry2" readonly="readonly"/></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" id="keratometry3" name="keratometry3" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="keratometry4" name="keratometry4" readonly="readonly"/></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" id="keratometry5" name="keratometry5" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="keratometry6" name="keratometry6" readonly="readonly"/></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" id="keratometry7" name="keratometry7" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="keratometry8" name="keratometry8" readonly="readonly"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<div class="row">
				<div class="col-lg-6 col-xs-6 col-sm-6">
					<b>R.E</b>
					<table class="table table-bordered table-responsive">
						<tbody>
							<tr>
								<td style="width: 31%;"></td>
								<td><b>S</b></td>
								<td><b>C</b></td>
								<td><b>A</b></td>
								<td><b>VA</b></td>
								<td><b>NV</b></td>
								<td><b>ADD</b></td>
							</tr>
							<tr>
								<td>Using Glass</td>
								<td><s:textfield cssClass="form-control" id="re_usingglass_s" name="re_usingglass_s" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_usingglass_c" name="re_usingglass_c" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_usingglass_a" name="re_usingglass_a" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_usingglass_va" name="re_usingglass_va" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_usingglass_nv" name="re_usingglass_nv" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_usingglass_add" name="re_usingglass_add" readonly="readonly"/></td>
							</tr>
							<tr>
								<td>A.R</td>
								<td><s:textfield cssClass="form-control" id="re_ar_s" name="re_ar_s" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_ar_c" name="re_ar_c" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_ar_a" name="re_ar_a" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_ar_va" name="re_ar_va" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_ar_nv" name="re_ar_nv" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_ar_add" name="re_ar_add" readonly="readonly"/></td>
							</tr>
							<tr>
								<td>Ace</td>
								<td><s:textfield cssClass="form-control" id="re_ace_s" name="re_ace_s" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_ace_c" name="re_ace_c" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" name="re_ace_a" id="re_ace_a" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_ace_va" name="re_ace_va" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" name="re_ace_nv" id="re_ace_nv" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="re_ace_add" name="re_ace_add" readonly="readonly"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-lg-6 col-xs-6 col-sm-6">
					<b>L.E</b>
					<table class="table table-bordered table-responsive">
						<tbody>
							<tr>
								<td><b>S</b></td>
								<td><b>C</b></td>
								<td><b>A</b></td>
								<td><b>VA</b></td>
								<td><b>NV</b></td>
								<td><b>ADD</b></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" name="le_usingglass_s" id="le_usingglass_s" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_usingglass_c" name="le_usingglass_c" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" name="le_usingglass_a" id="le_usingglass_a" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_usingglass_va" name="le_usingglass_va" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" name="le_usingglass_nv" id="le_usingglass_nv" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_usingglass_add" name="le_usingglass_add" readonly="readonly"/></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" name="le_ar_s" id="le_ar_s" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_ar_c" name="le_ar_c" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" name="le_ar_a" id="le_ar_a" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_ar_va" name="le_ar_va" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" name="le_ar_nv" id="le_ar_nv" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_ar_add" name="le_ar_add" readonly="readonly"/></td>
							</tr>
							<tr>
								<td><s:textfield cssClass="form-control" name="le_ace_s" id="le_ace_s" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_ace_c" name="le_ace_c" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" name="le_ace_a" id="le_ace_a" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_ace_va" name="le_ace_va" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" name="le_ace_nv" id="le_ace_nv" readonly="readonly"/></td>
								<td><s:textfield cssClass="form-control" id="le_ace_add" name="le_ace_add" readonly="readonly"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				
				
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<div class="row">
				<div class="col-lg-6 col-xs-6 col-sm-6">
					<b>R.E</b>
					<div class="form-group">
						<label>Lacrimal System: Patent: <span style="font-weight: normal;">Reg of clear fluid &nbsp;|&nbsp;Reg of purulent material</span></label>
					</div>
					<div class="form-group">
						<label>Lids: <span style="font-weight: normal;">O.K/Meibomianitis</span></label>
					</div>
					<div class="form-group">
						<label>Conjunctiva + Cornea AC: <span style="font-weight: normal;">Lens</span></label>
					</div>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
							<div class="form-group">
								<img src="img/re1.jpg" class="img-responsive" style="width: 90%;margin-left: auto;margin-right: auto;"/> 
							</div>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9" style="padding-left:0px">
							<div class="form-group">
								<!-- <textarea rows="3" cols="2" class="form-control" id="lens_left1" name="lens_left1"></textarea> -->
								<s:textarea rows="3" cols="2" cssClass="form-control" id="lens_left1" name="lens_left1"></s:textarea>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
							<div class="form-group">
								<img src="img/re2.jpg" class="img-responsive" style="width: 90%;margin-left: auto;margin-right: auto;"/> 
							</div>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9" style="padding-left:0px">
							<div class="form-group">
								<!-- <textarea rows="3" cols="2" class="form-control" id="lens_left2" name="lens_left2"></textarea> -->
								<s:textarea rows="3" cols="2" cssClass="form-control" id="lens_left2" name="lens_left2"></s:textarea>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label>Dignosis:</label>
						<!-- <textarea rows="2" cols="2" class="form-control" id="diagnosisarea" name="diagnosisarea"></textarea> -->
						<s:textarea rows="3" cols="2" cssClass="form-control" id="diagnosisarea" name="diagnosisarea"></s:textarea>
					</div>
					<div class="form-group">
						<label>Advice:</label>
						<p>1.Glasses.</p>
						<p>2.Medical Treatment.</p>
						<p>3.Surgery: RE/LE : Phoco+IOL</p>
					</div>
				</div>
				<div class="col-lg-6 col-xs-6 col-sm-6">
					<b>L.E</b>
					<div class="form-group">
						<label>Patent: <span style="font-weight: normal;">Reg of clear fluid &nbsp;|&nbsp;Reg of purulent material &nbsp;|&nbsp; O.K/Meibomianitis</span></label>
					</div>
					<br>
					<div class="form-group">
						<label>Conjunctiva + Cornea AC: <span style="font-weight: normal;">Lens</span></label>
					</div>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
							<div class="form-group">
								<img src="img/le1.jpg" class="img-responsive" style="width: 90%;margin-left: auto;margin-right: auto;"/> 
							</div>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9" style="padding-left:0px">
							<div class="form-group">
								<!-- <textarea rows="3" cols="2" class="form-control" id="lens_right1" name="lens_right1"></textarea> -->
								<s:textarea rows="3" cols="2" cssClass="form-control" id="lens_right1" name="lens_right1"></s:textarea>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
							<div class="form-group">
								<img src="img/le2.jpg" class="img-responsive" style="width: 90%;margin-left: auto;margin-right: auto;"/> 
							</div>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9" style="padding-left:0px">
							<div class="form-group">
								<!-- <textarea rows="3" cols="2" class="form-control" id="lens_right2" name="lens_right2"></textarea> -->
							<s:textarea rows="3" cols="2" cssClass="form-control" id="lens_right2" name="lens_right2"></s:textarea>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label>Follow Up:</label>
						<!-- <textarea rows="6" cols="2" class="form-control" id="followup" name="followup"></textarea> -->
						<s:textarea rows="3" cols="2" cssClass="form-control" id="followup" name="followup"></s:textarea>
					</div>
				</div>
				
				
				</div>
			</div>
			<br>
			<br>
			<span class="hidden-lg hidden-md hidden-sm visible-print pull-right">Printed By: <s:property value="printedBy"/></span>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	</s:else>
	<div class="row hidden-print">
		<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right" style="padding-left:0px;padding-right:0px;">
					<div class="form-group" style="margin-bottom: 3px;">
						<a href="#" onclick="myFunction()"  class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" >Print</a>
					</div>
				</div>
			</div>
		<div class="col-lg-1 col-md-1"></div>
		
	</div>
	</s:form>
</div>



<script src="popupdialog/dialog/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="common/BootstrapNew/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<script>
function myFunction() {
    window.print();
}
</script>
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
</body>
</html>