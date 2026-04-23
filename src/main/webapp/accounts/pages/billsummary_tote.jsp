<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<link rel="stylesheet" href="_assets/newtheme/css/main.css">


<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>



<%@page import="com.apm.main.common.constants.Constants"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script type="text/javascript">
$(window).on('load', function() {
	$('#newloaderPopup').modal('hide');
});
	$(document).ready(function() {
		 $('#newloaderPopup').modal('show');
	});
	 /* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	                
	            )
	        ); });   */
	        
	        
	        bkLib.onDomLoaded(function() {
	           
	        	 new nicEditor().panelInstance('emailBody');
	        	 $('.nicEdit-panelContain').parent().width('500px');
	        	 $('.nicEdit-panelContain').parent().next().width('500px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px');
	      });
	      
	      
	      /* window.onload =function(){  */
	    	  $(document).ready(function(){
		      	  var tt= Number(document.getElementById("tthidden").value);
				  document.getElementById("word").innerHTML=convertNumberToWords(tt);
	    	  });
		 /*  }; */
	      
	      
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
            font-size: 11px;
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
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 11px;
    border-right: none !important;
    border-left: none !important;
    text-transform: uppercase;
}
.form-group {
    margin-top: 0px !important;
}
 @media print
{
body {
    font-size: 11px !important;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 11px !important;
    border-right: none !important;
    border-left: none !important;
    text-transform: uppercase;
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
	    margin: 0 0 2.5px !important;
	    font-size: 11px !important;
	}
	
	.form-group {
    margin-bottom: 0px !important;
}
.setotas {
    padding: 0px;
    text-align: right;
    color: #008000 !important;
    font-size: 20px;
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
    font-size: 10px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:25px !important;
	color: firebrick !important;
}
.titleset {
    margin: 0px;
    color: #6699cc !important;
    border-bottom: 1px dashed #efefef;
    font-size: 15px;
    line-height: 20px;
}
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #ccc !important;
    color: #000 !important;
    font-size: 11px !important;
}
.setotas{
	 padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 20px !important;
}
.clicniaddress{
	font-size: 11px !important; font-weight: bold;
}

}
    </style>
    <style>
    .table.table {
    color: #000;
}
    body {
    	color: #000;
	}
	.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 180px;
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
}

</style>

</style>



<div class="row">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div id="login_main">
	<div id="login">
		  <section id="content">
		  
		  
		  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
		  		<%-- <div class="" style="height: 155px;">
					<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							<link href="common/css/printpreview.css" rel="stylesheet" />
							<%if(!loginfo.isHidelogobillinv()){ %>
							<%@ include file="/accounts/pages/letterhead.jsp" %>
							<%} %>
							
					</div>
				</div>
				 --%>
				<div class="row" style="margin-top: 1%;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						<div class="col-lg-4 col-md-4 col-xs-4">
						 <a href="#"  class="hidden" onclick="window.history.back(-1)" style="margin-left:-30px;float:left;background-color: grey;color: #fff;padding: 0px 16px 0px 16px;">Back</a>
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="margin-left: 250px;">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center; ">
								<s:if test="actionType=='viewpayment'">
									<b class="setticolors">RECEIPT</b>
								</s:if>
								<s:else>
								  <%if(loginfo.getIskunal()==1){ %>
									 <b class="setticolors"><s:property value="invoicename"/> BILL</b><%}else{ %>
									 
									 <b class="setticolors">INVOICE</b>
									<%} %>
								</s:else>
                           <s:if test="balancex==0">
								<span class="paidbtn setticolors">Paid</span>
							</s:if>
							<s:else>
								<!--<span class="unpaidbtn">Unpaid</span>-->
							</s:else>
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print hidden" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
								<a href="#"  class="hidden-print hidden" onclick="openPopup('patientlabelIpd?selectedid=<s:property value="clientId"/>')" style="margin-right:10px;float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Label Print</a>
							</div>
						</div>
					</div>
					</div>
				</div>
					<%if(loginfo.getIskunal()==1){ %>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">

						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label"><%=loginfo.getPatientname_field() %> Name</label>
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
						<s:if test="ipdid>0">
						
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
										<b>IP No.</b>
										</div>
										<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style="">
						
										<%if(loginfo.getIpd_abbr_access()==1){ %>
										<s:property value="newipdabbr"/>
										<%}else{ %>
										<s:property value="ipdseqno"/>
										<%} %> <s:property value="ipdid"/> </span>
										
								
								</div>
								</s:if>
						</div>	 
							 
						
						
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Address</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="address"/>, <s:property value="clienttown"/>, <s:property value="clientpostcode"/></span>
						</div>
						</div>
							
						<s:if test="mobno!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Contact</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="mobno"/></span>
						</div>
						</div>	
						</s:if>
						
						 <%if(loginfo.getInvestigation_newaccess().equals("0")){ %>
							<s:if test="wardname!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Ward/Bed</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="wardname"/>/<s:property value="bedname"/></span>
						</div>
						</div>
							
						</s:if>
						<%} %>				
						<div class="form-group" style="margin-bottom: 1px;">
							<s:if test="invoicename=='HD'">
									<b>Date</b><span>: <s:property value="fromDate"/></span>
									<b>To</b><span>: <s:property value="toDate"/></span>
                                </s:if>
						</div>
						
						<s:if test="payedbytp=='Third Party'">
						<s:if test="statusfortp==true">
						<s:if test="employeenamebytp!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Employee Name </b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="employeenamebytp"/></span>
						</div>
						</div>	
						</s:if>
						
						<s:if test="policyholder!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Policy Holder Name</b>
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
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Consultant Name</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="ipdconsultant"/></span>
						<s:if test="drqualification!=''">
						<span>(<s:property value="drqualification"/>)</span>
						</s:if>
						</div>
						</div>
						</s:if>
						</s:if> 
					</div>
					<%}else{ %>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;padding-top: 10px;">
						<div class="form-group marbot3" style="margin-bottom: 1px;">
						<s:if test="ipdid>0">
										<label for="inputEmail3" class="control-label">Admission ID</label><span>: 
										<%if(loginfo.getIpd_abbr_access()==1){ %>
										<s:property value="newipdabbr"/>
										<%}else{ %>
										<s:property value="ipdseqno"/>
										<%} %> <%-- <s:property value="ipdid"/> --%> </span>
										
										<span class="hidden-print">&nbsp; | &nbsp;</span> 
								</s:if>
							 
							 <s:if test="admissionDate!=''">
                                     <label for="inputEmail3" class="control-label"><b>Adm. Date</b></label><span>: <s:property value="admissionDate"/></span>            		 
                                </s:if>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label"></label><span><%=loginfo.getPatientname_field() %> Name : <s:property value="client"/> </span><span class="hidden-print">&nbsp; | &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><label for="inputEmail3" class="control-label hidden-print"></label><span class="hidden-print">Age / Gender : <s:property value="agegender" /></span>
						</div>
						<div class="form-group visible-print hidden-lg hidden-md hidden-sm hidden" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Age / Gender</label><span>: <s:property value="agegender" /></span>
						</div>						
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label"></label><span>Address : <s:property value="address"/>, <s:property value="clienttown"/>, <s:property value="clientpostcode"/> </span> 
						</div>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label"></label><span>Contact : <s:property value="mobno"/></span>
						</div>
						<s:if test="wardname!=''">
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Ward/Bed</label><span>: <s:property value="wardname"/>/<s:property value="bedname"/></span>
						</div>
						</s:if>						
						<div class="form-group" style="margin-bottom: 1px;">
							<s:if test="invoicename=='HD'">
									<label for="inputEmail3" class="control-label">Date</label><span>: <s:property value="fromDate"/></span>
									<label for="inputEmail3" class="control-label">To</label><span>: <s:property value="toDate"/></span>
                                </s:if>
						</div>
						<s:if test="payedbytp=='Third Party'">
						<s:if test="statusfortp==true">
						<s:if test="employeenamebytp!=''">
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Employee Name</label><span>: <s:property value="employeenamebytp"/></span>
						</div>	
						</s:if>
						<s:if test="policyholder!=''">
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Policy Holder Name</label><span>: <s:property value="policyholder"/></span>
						</div>	
						</s:if>
						<s:if test="relationofuser!=''">
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Relation</label><span>: <s:property value="relationofuser"/></span>
						</div>	
						</s:if>
						<s:if test="companyname!=''">
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Company name</label><span>: <s:property value="companyname"/></span>
						</div>	
						</s:if>
						<s:if test="neiscardno!=''">
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">NEIS / Card No</label><span>: <s:property value="neiscardno"/></span>
						</div>	
						</s:if>
						
						
						</s:if>
						</s:if>
					</div>
					<%} %>
					<%if(loginfo.getIskunal()==1){ %>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;padding-top: 10px;">
						<div class="form-group" style="margin-bottom: 1px;">
						<s:if test="payAmount > 0">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
							<b>
											<s:if test="actionType=='viewpayment'">
												Bill No.
											</s:if>
											<s:else>
												Bill No.
											</s:else>
										</b>
						</div>
							<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
							<label>:</label>&nbsp;&nbsp; <span style="">
							<%if(loginfo.isSeq_no_gen()){%>
										<s:property value="ipdopdseqno"/> <span class="hidden-print">(Ref Id:<s:property value="invoiceid"/>)</span>
										<%}else{%>
										<s:property value="invoiceid"/>
										<%} %> 
										(<s:property value="invoicename"/>)
										</span>
									</div>
									</div>
									</s:if>
									<s:else>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
										<b>
										
											<s:if test="actionType=='viewpayment'">
												Bill No.
											</s:if>
											<s:else>
												Bill No.
											</s:else>
										</b>
										</div>
										<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
										<label>:</label>&nbsp;&nbsp; <span style="">
										 <%if(loginfo.isSeq_no_gen()){%>
										<s:property value="ipdopdseqno"/> <span class="hidden-print">(Ref Id:<s:property value="invoiceid"/>)</span>
										<%}else{%>
										<s:property value="invoiceid"/>
										<%} %> 
										(<s:property value="invoicename"/>)
										</span>
										</div>
										</div>
									</s:else>
						</div>
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
						
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							 <%if(loginfo.getInvestigation_newaccess().equals("0")){ %><b>Invoice Date</label><span>: <s:property value="dateTime"/></span> <%} %>
						</div>
						
						<s:if test="payby=='Third Party'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Payer</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="payeename"/></span>
						</div>
						</div>
						
						
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
						</s:if>
						
						<s:else>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Payee</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="payeename"/></span>
						</div>
						</div>
						</s:else>
						
						
						<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
								<s:if test="ipdid>0">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Status</b>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style="">
						<s:if test="dischargeDate!=''">
							  <s:if test="dischargestatus==3">
							  Expired
							  </s:if>
							  <s:else>
							  Discharged
							  </s:else>
						</s:if>
						<s:else>
						Not Discharge
						</s:else>
						</span>
						</div>
						</div>
						</s:if>
                            <input type="hidden" name="hiddenclientid" id="hiddenclientid" value="<s:property value="clientId"/>">
						</div>
						
						<s:if test="payedbytp=='Third Party'">
						<s:if test="statusfortp==true">
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
						</s:if>
						<s:if test="companyname=='CGHS'">
						<s:if test="unit_station!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Unit/Station</b>
						</div>
						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="unit_station"/></span>
						</div>
						</div>
						</s:if>
						<s:if test="claimid!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<b>Claim ID</b>
						</div>
						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
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
						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
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
						</s:if>
					</div>
					<%}else{ %>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label"></label><span>Invoice Date : <s:property value="dateTime"/></span> 
						</div>
						<div class="form-group" style="margin-bottom: 1px;">
						<s:if test="payAmount > 0">
							<span><b>
											<s:if test="actionType=='viewpayment'">
												Receipt No
											</s:if>
											<s:else>
												Invoice No
											</s:else>
										</b> : <%if(loginfo.isSeq_no_gen()){%>
										<%-- <s:property value="ipdopdseqno"/> --%>
										<%if(loginfo.isLmh()){ %>
												<%-- <s:property value="dayseqno"/> --%>
												<s:property value="ipdopdseqno"/>
											<%}else{ %>
												<s:property value="ipdopdseqno"/>
											<%} %> 
										<span class="hidden-print">(Ref Id:<s:property value="invoiceid"/>) </span>
										<%}else{%><s:property value="invoiceid"/>
										<%} %> 
										(<s:property value="invoicename"/>)</span>
									</s:if>
									<s:else>
										<label for="inputEmail3" class="control-label"><b>
											<s:if test="actionType=='viewpayment'">
												Receipt No
											</s:if>
											<s:else>
												Invoice No
											</s:else>
										</b></label><span>:<%if(loginfo.isSeq_no_gen()){%>
										<%-- <s:property value="ipdopdseqno"/> --%>
										
										<%if(loginfo.isLmh()){ %>
												<%-- <s:property value="dayseqno"/> --%>
												<s:property value="ipdopdseqno"/>
											<%}else{ %>
												<s:property value="ipdopdseqno"/>
											<%} %> 
										<span class="hidden-print">(Ref Id:<s:property value="invoiceid"/>) </span>
										<%}else{%><s:property value="invoiceid"/>
										<%} %> 
										(<s:property value="invoicename"/>)</span>
									</s:else>
							<span> / <label for="inputEmail3" class="control-label"></label><span>UHID : <s:property value="abrivationid"/></span></span><s:if test="ipdid==0|| ipdid==''"><span>&nbsp; | &nbsp;</span> <label for="inputEmail3" class="control-label"></label><span>OPD ID : <s:property value="opdid" /></span></s:if>
						</div>
						<%if(loginfo.isBalgopal()){ %>
							<s:if test="ipdid!=0">
									<s:if test="fathername!=''">
										<div class="form-group marbot3" style="margin-bottom: 1px;">
											<label for="inputEmail3" class="control-label">Father Name</label><span>: <s:property value="fathername"/></span>
										</div>
									</s:if>
							</s:if>
						<%}%>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
									
							<s:if test="payby=='Third Party'">
							<label for="inputEmail3" class="control-label">Payer</label><span>: <s:property value="payeename"/></span>
								<s:if test="policyNo!=''">
                                   <label for="inputEmail3" class="control-label">, Policy No</label><span>: <s:property value="policyNo"/></span> 
                                 </s:if>
                                 <s:else>
                                 </s:else>
							</s:if>
							<s:else>
							<label for="inputEmail3" class="control-label"></label><span>Payee : <s:property value="payeename"/></span>
							</s:else>
							
							
						</div>
						
						<%if(loginfo.isSeq_no_gen()){%>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
						<label for="inputEmail3" class="control-label"></label><span> Receipt ID :
							<%-- <s:property value="physical_paymentid"/>  --%>
											<%if(loginfo.isLmh()){ %>
												<%-- <s:property value="paymentLMHSequence"/> --%>
												<s:property value="physical_paymentid"/>
											<%}else{ %>
												<s:property value="physical_paymentid"/>
											<%} %> 
							</span>
						</diV>
						<%} %>
						
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<s:if test="dischargeDate!=''">
                                	<label for="inputEmail3" class="control-label">Discharge Date</label><span>: <s:property value="dischargeDate"/></span> 
                                	<span class="hidden-print">&nbsp; | &nbsp;</span>           
                                </s:if>
                                <span class="hidden-print">
                                	<s:if test="ipdid>0">
	                              <s:if test="dischargeDate!=''">
	                                  <s:if test="dischargestatus==3">
	                                   <label for="inputEmail3" class="control-label">Status</label><span>: Expired</span>
									</s:if>
									<s:else>
									 <label for="inputEmail3" class="control-label">Status</label><span>: Discharged</span>
									</s:else>
	                              </s:if>
	                              <s:else>
	                                  <label for="inputEmail3" class="control-label">Status</label><span>: Not Discharge</span>
	                              </s:else>
                            </s:if>
                                </span>
							
                          
						</div>
						<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
								<s:if test="ipdid>0">
								  <s:if test="dischargeDate!=''">
	                                   <s:if test="dischargestatus==3">
	                                   <label for="inputEmail3" class="control-label">Status</label><span>: Expired</span>
									</s:if>
									<s:else>
									 <label for="inputEmail3" class="control-label">Status</label><span>: Discharged</span>
									</s:else>
	                                  
	                              </s:if>
	                              <s:else>
	                                  <label for="inputEmail3" class="control-label">Status</label><span>: Not Discharge</span>
	                              </s:else>
                            </s:if>
                            <input type="hidden" name="hiddenclientid" id="hiddenclientid" value="<s:property value="clientId"/>">
						</div>
						<s:if test="payedbytp=='Third Party'">
						<s:if test="statusfortp==true">
						<s:if test="designation!=''">
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Designation</label><span>: <s:property value="designation"/></span> 
						</div>
						</s:if>
						</s:if>
						<s:if test="companyname=='CGHS'">
						<s:if test="unit_station!=''">
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Unit/Station</label><span>: <s:property value="unit_station"/></span> 
						</div>
						</s:if>
						<s:if test="claimid!=''">
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Claim ID</label><span>: <s:property value="claimid"/></span> 
						</div>
						</s:if>
						</s:if>
						<s:if test="companyname=='WCL'">
						<s:if test="colliery!=''">
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Colliery ID</label><span>: <s:property value="colliery"/></span> 
						</div>
						</s:if>
						<s:if test="areatp!=''">
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Area</label><span>: <s:property value="areatp"/></span> 
						</div>
						</s:if>
						</s:if>
						</s:if>
					</div>
					<%} %>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;margin-top: 7px;margin-bottom: 5px;">
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
					<%if(loginfo.getIskunal()!=1){ %>
						<div class="form-group" style="margin-bottom: 0px !important;">
							<span><b>Consultant</b> : 
							<s:if test="ipdconsultant!='' || ipdconsultant!=null">
							 <%if(loginfo.isBalgopal()){ %>
						        <s:property value="ipdconsultant"/> &nbsp; <%-- (<s:property value="Owner_qualification"/>)  --%>
							  
							  <%}else{%>
							    <s:property value="ipdconsultant"/> (<s:property value = "owner_qualification"/>)
							    <%} %>
							</s:if>
							<s:else>
							       
							</s:else>
							</span>
						</div>
					<%} %>	
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
					<div class="form-group" style="margin-bottom: 0px !important;">
					<s:if test="refereddr!=''">
							<span><b>Reffered Doctor</b> : 
							
							    <s:property value="refereddr"/>
							
							</span>
							</s:if>
						</div>
					</div>
					<%if(!loginfo.isParihar()){ %>
					<s:if test="finalDiagnosis!=''">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
						<div class="form-group" style="margin-bottom: 0px !important;">
							<span><b>Diagnosis</b> : <s:property value="finalDiagnosis"/></span>
						</div>
					</div>
					</s:if>
					<%} %>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
						<div class="table-responsive"> 
							<table class="" border=1> 
								<thead> 
									<tr> 
										<th class="th1"><s:if test="invoice_date==0">Date</s:if></th> 
										<th class="th2">Code</th> 
										<th class="th3">Service Name (Notes)<a href="#" onclick="showinvoicechargesByDate('<s:property value="billsummary"/>',<s:property value="invoiceid"/>,<s:property value="totalAmountx"/>,0)"> <i class="fa fa-arrow-down"></i></a></th> 
										<th style="text-align: center;" class="th5">Qty</th>
										<th class="th2">Unit Charge</th>
										<s:if test="discstatus1"> 
										<th class="th2 hidden-print">Discount</th>
										</s:if> 
										<th style="text-align:right;" class="th4 hidden">Rate</th>										 
										<th style="text-align:right;" class="th6">Amount</th> 
									</tr> 
								</thead> 
								<tbody id="viewinvoicetbody"> 
								<s:iterator value="masterAssessmentList">
	                              <tr class="totalbor">
	                                    <td><b><s:if test="name=='Bed Charge'">Accommodation Charges</s:if>
	                                    
	                                     <s:elseif test="name=='Ipd Registration Charge'">Admission Charges</s:elseif>
	                                    <s:else> <s:property value="name"/></s:else>
	                                     </b></td>
	                                    <td></td>
										<td></td>
										
										<td></td>
										<s:if test="discstatus1">
										<td class="hidden-print"></td>
										</s:if>
										<td></td>
										<td style="text-align:right;color: #5cb85c;">Sub Total <%=Constants.getCurrency(loginfo)%><s:property value="charge"/></td> 
	                              		<!-- <td></td> -->
	                              </tr>
	                              <s:iterator value="wardwisechargelist">
	                              <s:if test="isbedcharge==1">
<%-- 	                               <s:if test="isbedcharge==1||isbedcharge==2"> --%>
	                              	<tr class="totalbor">
	                                    <td><b><s:property value="wardname"/></b></td>
	                                    <td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<s:if test="discstatus1">
										<td class="hidden-print"></td>
										</s:if>
										<%-- <td style="text-align:right;color: #5cb85c;">Sub Total <%=Constants.getCurrency(loginfo)%><s:property value="charge"/></td> --%>
	                              		<!-- <td></td> -->
	                              	</tr>
	                              </s:if>
	                              <s:iterator value="assesmentList">
								<tr>
										<%-- <th scope="row">
											<s:if test="invoice_date==0">
												<s:property value="commencing"/>
											</s:if>
										</th>  --%>
										<td>
											<s:if test="invoice_date==0">
												<s:property value="commencing"/>
											</s:if>
										</td> 
										<td><s:property value="tpcode"/></td> 
										<td class="padletab">
											<s:if test="dna==true">
												<s:property value="appointmentType"/> 
												<%-- <s:if test="ipdid!=0">
													<s:property value="ward"/>
													
												</s:if>  --%>
												<s:if test="chargedisc>0">
												<span style="color:red">*</span>
												</s:if>
												<span style="color:red">(DNA)</span>
											</s:if>
											<s:else>
												<s:property value="appointmentType"/>
												<s:if test="chargedisc>0">
												<span style="color:red">*</span>
												</s:if>
											<%-- 	<s:if test="ipdid!=0">
													<s:property value="ward"/>
												</s:if>  --%>
											</s:else> 
										</td>
										<td class="text-center">
											<s:if test="pkginvissid==0">
												<s:property value="quantity"/>
											</s:if>
											<s:else>
											</s:else>
											
											
										</td>
										
										<td class="">
											<s:if test="pkginvissid==0">
												<%=Constants.getCurrency(loginfo)%><s:property value="unitcharge"/>
											</s:if>
											<s:else>
											</s:else>
											
										</td>
									<s:if test="discstatus1">	
										<td class="hidden-print">
										
											<s:if test="pkginvissid==0">
												<%=Constants.getCurrency(loginfo)%><s:property value="chargedisc"/>
											</s:if>
											<s:else>
											</s:else>
											
										</td>
																	<%-- <td><s:property value="appointmentType"/></td> --%>
										</s:if>
										
										<td class="text-right hidden">
											<s:if test="pkginvissid==0">
												<%=Constants.getCurrency(loginfo)%><s:property value="charges"/>
											</s:if>
											<s:else>
											</s:else>
											
										</td>										
										<td class="text-right">
											<s:if test="pkginvissid==0">
												<%=Constants.getCurrency(loginfo)%><s:property value="chargeTotal"/>
											</s:if>
											<s:else>
											</s:else>
											
											
										</td>
								</tr>
								</s:iterator>
								</s:iterator>
								</s:iterator>
									<tr class="settopbac">
										<th>Total</th>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<s:if test="discstatus1">
										<td class="hidden-print"></td>
										</s:if>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></td>
									</tr>
								</tbody> 
							</table> 
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-top: 8px;padding-bottom: 0px;">
					<div class="col-lg-8 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
						<%if(loginfo.getIskunal()!=1){ %>
						<div class="form-group" style="margin: 0px 0px 4px 0px;">
								<span for="inputEmail3" class="control-label">Payment Mode</span><span>: <s:property value="paymode"/> 
								<s:if test="paymode=='Cheque'">(<s:property value="chequeno"/>)</s:if>
								
								</span>
						</div>
						
						<div class="form-group" style="margin: 0px 0px 4px 0px;">
						 
								<span for="inputEmail3" class="control-label">Transaction No.</span><span>: <s:property value="Transactionno"/> 
								</span>
						</div>
						
						<%} %>
						<div class="form-group" style="margin: 0px 0px 4px 0px;">
							<span for="inputEmail3" class="control-label">Prepared By</span><span>: 
							<%if(loginfo.getIskunal()==1) {%> 
							<s:property value="userid"/></span>
							<%}else{%>
							<s:property value="preparedby"/></span>
							<%} %>
						</div>
						<s:if test="isparkingcharge==1">
							<div class="form-group" style="margin: 0px 0px 4px 0px;">
								<span for="inputEmail3" class="control-label">Note:</span><span>Parking charges discount applied.</span>
							</div>
						</s:if>
						<div class="form-group marbot3" style="margin: 0px 0px 4px 0px;">
						<%String delnots=DateTimeUtils.removeBreaks(request.getParameter("cancelNotes"));
						String nots=DateTimeUtils.removeBreaks(request.getParameter("submitInvoiceNotes"));
						%>
							<s:if test="deleted==1">
								<span for="inputEmail3" class="control-label">Cancel Notes</span><span>: <s:property value="cancelNotes"/></span>
							</s:if>
							<s:else>
							<%if(!nots.equals("")){ %>
								<span for="inputEmail3" class="control-label">Notes</span><span>: <s:property value="submitInvoiceNotes"/></span>
							<%} %>		
							</s:else>
							
						</div>
						<%String notes1=(String)session.getAttribute("notes");%>
						<%if(loginfo.isBalgopal()){%>
							<%if(!DateTimeUtils.isNull(notes1).equals("")){ %>
								<div class="form-group marbot3" style="margin: 0px 0px 4px 0px;">
									<s:if test="paymentNote!=''">
										<span for="inputEmail3" class="control-label">Appointment Note</span><span>: <%=notes1 %></span>
									</s:if>
								</div>
							<%} %>
						<%} %>
						<div class="form-group marbot3" style="margin: 0px 0px 4px 0px;">
							<s:if test="paymentNote!=''">
								<span for="inputEmail3" class="control-label">Payment Note</span><span>: <s:property value="paymentNote"/></span>
							</s:if>
						</div>
					</div>
					<div class="col-lg-2 col-xs-3 col-md-3 col-sm-3 setotas">
					 								   <s:if test="dicsAmount!='0.00'">
                                            			<p style="margin: 0px 0px 4px 0px;">Discount <small>(<s:property value="discount"/>
                                            			<s:if test="disctype==0">
                                            				%
                                            			</s:if>
                                            			<s:else>
                                            				<%=Constants.getCurrency(loginfo)%>
                                            			</s:else>
                                            			)</small> :</p>
                                            			</s:if>
                                            			<p style="margin: 0px 0px 4px 0px;">Net Payble Amount :</p>
                                            			<p style="margin: 0px 0px 4px 0px;">Payment Received :</p>
                                            			<p style="margin: 0px 0px 4px 0px;">Total Balance :</p>
                                            			
                   </div>
                   <div class="col-lg-2 col-xs-3 col-md-3 col-sm-3 setotas">
                                                       <s:if test="dicsAmount!='0.00'">
                                            			 <p style="margin: 0px 0px 4px 0px;"><span style=""><%=Constants.getCurrency(loginfo)%><s:property value="dicsAmount"/></span></p>
                                            			</s:if>
                                            			<p style="margin: 0px 0px 4px 0px;"><span style=""><%=Constants.getCurrency(loginfo)%><s:property value="netpayamount"/></span></p>
                                            			<input type="hidden" value="<s:property value="netpayamount"/>" id="tthidden" />
                                            			
                                            			
                                            			<p style="margin: 0px 0px 4px 0px;"><span><%=Constants.getCurrency(loginfo)%><s:property value="creditAmt"/></span></p>
                                            			<s:if test="actionType=='show'">
                                            				<p style="margin: 0px 0px 4px 0px;"><span><%=Constants.getCurrency(loginfo)%><s:property value="balancex"/></span></p>
														</s:if>
														<%double creditAmount = (Double)session.getAttribute("creditAmount"); %>
															<% if(creditAmount>0){%>
															<p style="margin: 0px 0px 4px 0px;">Credit Amount :<span><%=Constants.getCurrency(loginfo) + DateTimeUtils.changeFormat(creditAmount)%></span></p>
															<%} %>
                                            			
                  </div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right hidden" style="padding-left:0px;padding-right:0px;">
						<!--<div class="form-group marbot3">
							<label for="inputEmail3" class="control-label"><b>Total</b></label><span><b>: <%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></b></span>
						</div>
						--><
						<!--<div class="form-group marbot3">
							<label for="inputEmail3" class="control-label">Policy Excess</label><span>: <%=Constants.getCurrency(loginfo)%><s:property value="policyExcess"/></span>
						</div>
						-->
					</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-top: 0px;">
					<div class="col-lg-4">
						
							<div class="table-responsive">
							<%-- <s:if test="transactionList.size!=0">
							
							<table class="table">
							
								<thead> 
									<tr> 
										<th class="th8">Date</th> 
										<th class="th9">Payment Mode</th> 
										<th class="th10">Charge</th> 
									</tr> 
								</thead> 
								<tbody>
								
								<s:iterator value="transactionList">
									<tr>
										<td><s:property value="date"/></td>
										<td><s:property value="paymentmode"/></td>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
									</tr>
									</s:iterator>
								
								</tbody>
							</table>
							</s:if> --%>
						</div>
						
					</div>
					
					<div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">
						<%-- <s:if test="prepaymentList.size!=0">
						<div class="table-responsive">
							<table class="table">
								<thead> 
									<tr> 
										<th class="th8">Date</th> 
										<th class="th9">Advance Receipt</th> 
										<th class="th10">Note</th> 
										<th class="th11 text-right">Amount</th> 
									</tr> 
								</thead> 
								<tbody>
								<s:if test="prepaymentList.size!=0">
								<s:iterator value="prepaymentList">
									<tr>
										<td><s:property value="date"/></td>
										<s:if test="advref==0">
											<td>Receipt - (R.<s:property value="id"/>)</td>
										</s:if>
										<s:else>
											<td>Receipt - (RF.<s:property value="id"/>)</td>
										</s:else>
										<td><s:property value="note"/></td>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
									</tr>
									</s:iterator>
								</s:if>
								</tbody>
							</table>
						</div>
						</s:if> --%>
						
						<%-- <s:if test="refundList.size!=0">
						<div class="table-responsive">
							<table class="table">
								<thead> 
									<tr> 
										<th class="th8">Date</th> 
										<th class="th9">Refund Receipt</th> 
										<th class="th11 text-right">Amount</th> 
									</tr> 
								</thead> 
								<tbody>
								<s:if test="refundList.size!=0">
								<s:iterator value="refundList">
									<tr>
										<td><s:property value="date"/></td>
										<td>Receipt - (RF.<s:property value="id"/>)</td>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
									</tr>
									</s:iterator>
								</s:if>
								</tbody>
							</table>
						</div>
						</s:if> --%>
						
						
						<div class="table-responsive">
							<table class="" border=1>
								<thead> 
									<tr> 
										<th class="th8">Date</th> 
										<th class="th9">Receipt</th> 
										<th class="th10">Payment Mode</th> 
										<th class="th11 text-right">Amount</th> 
										<th class="th11 text-left">Type</th> 
									</tr> 
								</thead> 
								<tbody>
								<s:if test="prepaymentList.size!=0">
								<s:iterator value="prepaymentList">
									<tr>
										<td><s:property value="date"/></td>
										<s:if test="advref==0">
											<td>Receipt - (R.<s:property value="id"/>)(<s:property value="physical_payment_id"/>)</td>
										</s:if>
										<s:else>
											<td>Receipt - (RF.<s:property value="id"/>)(<s:property value="physical_payment_id"/>)</td>
										</s:else>
										 <td><s:property value="paymentmode"/></td>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										
										<s:if test="advref==0">
											<td>Advance Receipt</td>
										</s:if>
										<s:else>
											<td>Refund Receipt</td>
										</s:else>
									</tr>
									</s:iterator>
								</s:if>
								
								<s:if test="refundList.size!=0">
								<s:iterator value="refundList">
									<tr>
										<td><s:property value="date"/></td>
										<td>Receipt - (RF.<s:property value="id"/>)</td>
										 <td></td> 
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										<td>Refund Receipt</td>
									</tr>
									</s:iterator>
								</s:if>
								
								<s:iterator value="transactionList">
									<tr>
										<td><s:property value="fromDate"/></td>
										<td>Receipt - (R.<s:property value="id"/>)</td>
										<td><s:property value="paymentmode"/></td>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										<td>Payment(<s:property value="paymentmode"/>)</td>
									</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						
						
					</div>
						
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin-top: 25px;">
						<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-left" style="padding: 0px;">
							<%if(loginfo.getIskunal()==1){ %>
							<span>Prepared By:</span><br>
							<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-left" style="padding: 0px;">
							<span>Incharge Billing:</span><br>
							</div>
							<%}else{ %>
							<s:if test="payby!='Third Party'">
								<span>Authorized Signatory</span><br>
							</s:if>
							<span>[FOR <s:property value = "clinicName"/>]</span>
							<%} %>
							
							
							
						</div>
						<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 text-right" style="padding: 0px;">
							<%if(loginfo.getIskunal()==1){ %>
							<div class="form-group wordscolr">
								<label for="inputEmail3" class="control-label" style="color: #d07878;text-transform: uppercase;">In Words: </label> <span id="word" style="color: #d07878;text-transform: uppercase;"> </span> <span style="color: #d07878;text-transform: uppercase;"> ONLY </span><br> 
								<span>Authorized Signatory</span>
							</div>
							<%}else{ %>
							<div class="form-group wordscolr">
								<label for="inputEmail3" class="control-label" style="color: #d07878;text-transform: uppercase;"> </label> <span id="word" style="color: #d07878;text-transform: uppercase;"> </span> <span style="color: #d07878;text-transform: uppercase;">In Words : ONLY </span><br> 
								<span>Printed By: <s:property value="printedBy"/></span>
							</div>
							<%} %>
							<div class="form-group wordscolr">
								<s:if test="deleted==1">
									<span>Cancel By: <s:property value="cancelUserid"/> | <s:property value="cancelDT"/></span>
								</s:if>
						
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 hidden-print" style="margin-bottom:10px;padding: 0px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;text-align: right;">
			                                <a href="#" onclick="sendmail();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Send Email" id="mail">Email</i></a>
	                                  		<a href="#" onclick="printconsult();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Print" id="print">Print</a>
		                                </div>
		                            </div>
		                        </div>
				</div>
				
				
				
				
				
				
				
				
				
				</div>
				
				
				
				
				
				
				
				
				
				
				
		  </div>
		  
               
            </section>
	</div>
</div>
</div>
</div>





<!-- Modal Email-->
<div class="modal fade" id="sendEmailPopUp2" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				<%-- 	<div class="col-lg-4 col-md-4">				
						<label>Show Template Name</label>
					</div>
					<div class="col-lg-5 col-md-5">		
						<s:select id="templateName" name="templateName" listKey="id"
							headerValue="All" headerKey="All" listValue="templateName" list="templateNameList" 
							value="templateName" cssClass="form-control" onchange="showTemplateDetails(this.id)"></s:select>
						<s:hidden name="templateName" id="templateName"></s:hidden>
					</div> --%>
					</div>
						<div class="form-group">
							<label>To:</label>
							<s:if test="payby=='Third Party'">
								<s:textfield theme="simple" id = "thirdPartEmail" name = "payeeEmail"
								cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver"
								title="Enter Email Id" data-toggle="tooltip" />
							</s:if>
							
							<s:else>
								<s:textfield theme="simple" id = "thirdPartEmail" name = "email"
								cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver"
								title="Enter Email Id" data-toggle="tooltip" />
							
							</s:else>
							
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "ccEmail" name = "ccEmail"
								cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "subject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject"
								placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="60" cols="60"
								title="Enter Body" name="emailBody"  id="emailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox"
								name="ispdf" id="ispdf" checked="checked">
						</div>
						
						
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="sendPdfMail();">Send</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);z-index: 99" id="newloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->


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
    
    
    
	function printconsult() {
		document.getElementById("print").style.display='none';
		document.getElementById("mail").style.display='none';
		window.print();
		
	}
  </script>

<script>
	function convertNumberToWords(amount) {
    var words = new Array();
    words[0] = '';
    words[1] = 'One';
    words[2] = 'Two';
    words[3] = 'Three';
    words[4] = 'Four';
    words[5] = 'Five';
    words[6] = 'Six';
    words[7] = 'Seven';
    words[8] = 'Eight';
    words[9] = 'Nine';
    words[10] = 'Ten';
    words[11] = 'Eleven';
    words[12] = 'Twelve';
    words[13] = 'Thirteen';
    words[14] = 'Fourteen';
    words[15] = 'Fifteen';
    words[16] = 'Sixteen';
    words[17] = 'Seventeen';
    words[18] = 'Eighteen';
    words[19] = 'Nineteen';
    words[20] = 'Twenty';
    words[30] = 'Thirty';
    words[40] = 'Forty';
    words[50] = 'Fifty';
    words[60] = 'Sixty';
    words[70] = 'Seventy';
    words[80] = 'Eighty';
    words[90] = 'Ninety';
    amount = amount.toString();
    var atemp = amount.split(".");
    var number = atemp[0].split(",").join("");
    var n_length = number.length;
    var words_string = "";
    if (n_length <= 9) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < n_length; i++) {
            received_n_array[i] = number.substr(i, 1);
        }
        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
            n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                if (n_array[i] == 1) {
                    n_array[j] = 10 + parseInt(n_array[j]);
                    n_array[i] = 0;
                }
            }
        }
        value = "";
        for (var i = 0; i < 9; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                value = n_array[i] * 10;
            } else {
                value = n_array[i];
            }
            if (value != 0) {
                words_string += words[value] + " ";
            }
            if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Crores ";
            }
            if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Lakhs ";
            }
            if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Thousand ";
            }
            if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                words_string += "Hundred and ";
            } else if (i == 6 && value != 0) {
                words_string += "Hundred ";
            }
        }
        words_string = words_string.split("  ").join(" ");
    }
    return words_string;
}
</script>  
  