<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="_assets/newtheme/css/main.css" rel="stylesheet" />
<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" />

<script type="text/javascript" src="ipd/js/admissionform.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="ipd/js/consultationforms.js"></script>


<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="ipd/js/dischargeform.js"></script>

<script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
<style>
.nicEdit-panelContain {
	background-color: #9fcdd0 !important;
}

.nicEdit-main {
	background: #e8fbff;
}

.medname {
	font-size: 13px !important;
	font-weight: bold !important;
}

.setbackcolor {
	background-color: beige;
}

::placeholder {
	color: green;
}

.micimg {
	float: right !important;
	width: 3% !important;
	margin-top: 1px !important;
}

.adformback {
	border: 1px solid;
	padding: 10px 0px 0px;
	margin-top: 0px;
	width: 98%;
	margin-left: 9px;
}

.minheaighsys {
	height: 38px;
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

.dischargebtn {
	width: 99px !important;
	float: right !important;
	margin-right: 0px !important;
	margin-bottom: 20px !important;
}

.bodertitright {
	border-top: 2px solid #c0c0c0;
	border-right: 2px solid #c0c0c0;
	border-bottom: 2px solid #c0c0c0;
	padding: 8px 0px 8px 0px;
}

.bodertitleft {
	border-top: 2px solid #c0c0c0;
	border-left: 2px solid #c0c0c0;
	border-bottom: 2px solid #c0c0c0;
	padding: 8px 0px 8px 0px;
}

.textareaheight {
	height: 50px !important;;
}

.paddtop {
	padding: 0px 0px 14px 2px;
}

.widthtabhedth1 {
	width: 22%;
}

.widthtabhedth2 {
	width: 7%;
}

.backgrey {
	background-color: rgba(149, 222, 91, 0.19);
}

.mar0 {
	margin-top: 23px;
}

.bordertopgray {
	border-top: 1px solid #c7c7c7;
	border-bottom: 1px solid #c7c7c7;
}

.panel-primary {
	border-color: #339966;
}

.pnameback {
	background-color: #f5f5f5;
	margin-top: -7px;
	padding-top: 10px;
}

.admissionbackgreen {
	background-color: #339966;
	color: #fff;
	width: 223px;
}

.form-inline .form-control {
	display: inline-block;
	vertical-align: middle;
}

.tooltip {
	position: relative;
	display: inline-block;
	opacity: 1;
	z-index: 0;
}

.tooltip .tooltiptext {
	visibility: hidden;
	width: 180px;
	text-align: justify;
	padding: 6px 9px;
	background-color: #555;
	color: #fff;
	position: absolute;
	z-index: 1;
	bottom: 125%;
	left: 50%;
	margin-left: -60px;
	opacity: 0;
	transition: opacity 1s;
}

.tooltip .tooltiptext::after {
	content: "";
	position: absolute;
	top: 100%;
	left: 10%;
	margin-left: 37px;
	border-width: 5px;
	border-style: solid;
	border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
	visibility: visible;
	opacity: 1 !important;
}
</style>
<style>
h3, .h3 {
	margin-top: 9px !important;
	margin-bottom: 9px !important;
}

.textprimegreen {
	background-color: #339966;
	color: #fff;
}

.textprime {
	background-color: rgba(102, 153, 204, 0.85) !important;
	color: #fff;
}

.secconbtn {
	width: 100%;
	background-color: #f5f5f5;
	color: #222222 !important;
	text-align: left;
	font-size: 12px;
	height: 24px !important;
}

.sebaclcons {
	background-color: rgb(246, 246, 246) !important;
	text-shadow: none;
	color: #222 !important;
	text-align: left;
	font-size: 12px;
}

.table>thead>tr>th {
	background-color: rgba(221, 221, 221, 0.85);
	color: #222;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
}

h4, .h4, h5, .h5, h6, .h6 {
	margin-top: 3.5px;
	margin-bottom: 3.5px;
}

.fa-2x {
	font-size: 16px;
	cursor: pointer;
}

.lkclass {
	width: 50%;
}

.lkclass th {
	text-align: center !important;
}

.lkclass td {
	text-align: center !important;
}
</style>
<script type="text/javascript">


bkLib.onDomLoaded(function() {

    if(document.getElementById('discharge_default')){
    	new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('discharge_default');
    }
});

    
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div class="row">
		
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-12 col-xs-12 col-md-12 textprime"
						style="margin-bottom: 15px;">
						<h5>NOTES</h5>
					</div>


					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                  <form action="savedailynotesStatement">
						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
							<%-- 	<s:select list="discharge_default_list"
									onchange="getdisctemplateCF(this.value)" listKey="id"
									listValue="name" cssClass="form-control" headerKey="0"
									headerValue="Select Template"></s:select> --%>
								<input type="text" name="disdefaulttempname"
									class="form-control setbackcolor"
									placeholder="Enter template name">

								<div class="form-group">
									<img src="cicon/mic_off.png" class="img-responsive micimg12"
										onclick="startConverting155(this,'discharge_default')"
										title="Microphone" id="changer"
										style="width: 2.5%; margin-left: 10px; margin-top: -16px;"></img>
								</div>
							</div>
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="example" id="discharge_default" />
						</div>
						 <s:hidden name="ipdid" id="ipdid"></s:hidden>
						 <input type="hidden" value="0" name="isnote">
						 <s:hidden name="clientId" id="clientid"/>
						 <s:hidden name = "id" id ="id"></s:hidden>
						 <input type="hidden" name="actiontype" value="1">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
				<input type="submit" class="btn btn-primary" value="Update" >
				</div>
						</form>
					</div>

				</div>
				
			</div>
</body>
</html>