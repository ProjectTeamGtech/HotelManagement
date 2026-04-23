<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<link rel="stylesheet" href="_assets/newtheme/css/main.css">


<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>



<%@page import="com.apm.main.common.constants.Constants"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>


<script type="text/javascript">
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
	      
	      
	      window.onload =function(){ 
	      
	      		
                                            			      var tt= Number(document.getElementById("tthidden").value);
                                            			      document.getElementById("word").innerHTML=convertNumberToWords(tt);
	      };
	      
	      
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
    font-size: 11px;
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
	font-size:11px !important;
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
    font-size: 10px !important;
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
.uppercase{
text-transform:uppercase;
}
</style>



<div class="row">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div id="login_main">
	<div id="login">
		  <section id="content">
		  
		  
		  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		  		<div class="row letterheadhgt" style="height: 135px;">
					<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							<link href="common/css/printpreview.css" rel="stylesheet" />
							<%if(!loginfo.isHidelogobillinv()){ %>
							<%@ include file="/accounts/pages/letterhead.jsp" %>
							<%} %>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						<div class="col-lg-4 col-md-4 col-xs-4">
						 <a href="ProcessingAccount?clientId=<s:property value="clientId"/>" class="hidden-print" style="margin-left:-30px;float:left;background-color: grey;color: #fff;padding: 0px 16px 0px 16px;">Back</a>
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<s:if test="actionType=='viewpayment'">
									<b class="setticolors">RECEIPT</b>
								</s:if>
								<s:else>
								  <%if(!loginfo.getInvestigation_newaccess().equals("0")){ %>
									 <b class="setticolors uppercase"><s:property value="masterchargetype"/> BREAKAGE</b><%}else{ %>
									 
									 <b class="setticolors">INVOICE</b>
									<%} %>
								</s:else>
                           <s:if test="balancex==0">
								<span class="paidbtn">Paid</span>
							</s:if>
							<s:else>
								<!--<span class="unpaidbtn">Unpaid</span>-->
							</s:else>
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
								<a href="#"  class="hidden-print" onclick="openPopup('patientlabelIpd?selectedid=<s:property value="clientId"/>')" style="margin-right:10px;float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Label Print</a>
							</div>
						</div>
					</div>
					</div>
				</div>
					
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;padding-top: 10px;">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Patient Name</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="client"/></span>
						</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Age / Gender</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="agegender"/></span>
						</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">UHID</label>
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
	<%-- 					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Address</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="address"/>, <s:property value="clienttown"/>, <s:property value="clientpostcode"/></span>
						</div>
						</div>	
						
						<s:if test="mobno!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Contact</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="mobno"/></span>
						</div>
						</div>	
						</s:if>
						
						<s:if test="wardname!=''">
						<s:if test="wardname!=null">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Ward/Bed</label>
						</div> 
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="wardname"/><span>/</span><s:property value="bedname"/></span>
						</div>
						</div>
							</s:if>
						</s:if> --%>
						
						<s:if test="ipdid==0">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">OPD ID</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="opdid"/></span>
						</div>
						</div>	
						</s:if>
						
						<s:if test="invoicename=='HD'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Date</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="fromDate"/></span>
						</div>
						</div>
						</s:if>	
						
						<s:if test="invoicename=='HD'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">To </label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="toDate"/></span>
						</div>
						</div>
						</s:if>	
						
						<s:if test="invoicename=='HD'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">To </label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="toDate"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="payedbytp=='Third Party'">
						<s:if test="statusfortp==true">
						<s:if test="employeenamebytp!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Employee Name </label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="employeenamebytp"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="policyholder!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Policy Holder Name </label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="policyholder"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="relationofuser!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Relation </label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="relationofuser"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="companyname!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Company Name </label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="companyname"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="neiscardno!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">NEIS / Card No</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="neiscardno"/></span>
						</div>
						</div>
						</s:if>
						</s:if>
						</s:if>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Consultant Name&nbsp;&nbsp;</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="ipdconsultant"/></span>
						
						</div>
						</div>
						
						
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">&nbsp;&nbsp;</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label></label>&nbsp;&nbsp; <span style=""></span>
						<s:if test="drqualification!=''">
						<span>(<s:property value="drqualification"/>)</span>
						</s:if>
						</div>
						</div>
						</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left  " style="padding-left:0px;padding-right:0px;padding-top: 10px;">
						<s:if test="payAmount > 0">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Bill No.</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style="">
						<%if(loginfo.isSeq_no_gen()){%>
						<s:property value="ipdopdseqno"/> <span class="hidden-print">(Ref Id:<s:property value="invoiceid"/>)
						<%}else{ %>
						<s:property value="invoiceid"/>
						<%} %>
						(<s:property value="invoicename"/>)
						</span>
						</span>
						</div>
						</div>
						</s:if>
						
						<s:else>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Bill No.</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style="">
						<%if(loginfo.isSeq_no_gen()){%>
						<s:property value="ipdopdseqno"/>(Ref Id:<s:property value="invoiceid"/>)
						<%}else{ %>
						<s:property value="invoiceid"/>
						<%} %>
						(<s:property value="invoicename"/>)
						</span>
						</div>
						</div>
						
						</s:else>
						
						<s:if test="admissionDate!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Admission Date</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="admissionDate"/></span>
						</div>
						</div>
						</s:if>
						<s:if test="dischargeDate!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Discharge Date</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="dischargeDate"/></span>
						</div>
						</div>
						</s:if>
						<s:if test="payby=='Third Party'">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Payer</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="payeename"/></span>
						</div>
						</div>
						
						
						<s:if test="policyNo!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Policy No</label>
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
						<label for="inputEmail3" class="control-label">Payee</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="payeename"/></span>
						</div>
						</div>
						</s:else>
						
						<%-- <s:if test="ipdid>0">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Status</label>
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
						</s:if> --%>
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
						
						<input type="hidden" name="hiddenclientid" id="hiddenclientid" value="<s:property value="clientId"/>">
						
						<s:if test="payedbytp=='Third Party'">
						<s:if test="statusfortp==true">
						<s:if test="designation!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Designation</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="designation"/></span>
						</div>
						</div>
						</s:if>
						</s:if>
						</s:if>
						
						<s:if test="companyname=='CGHS'">
						<s:if test="unit_station!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Unit/Station</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="unit_station"/></span>
						</div>
						</div>
						</s:if>
						
						
						<s:if test="claimid!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Claim ID</label>
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
						<label for="inputEmail3" class="control-label">Colliery ID</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="colliery"/></span>
						</div>
						</div>
						</s:if>
						
						<s:if test="areatp!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Area</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="areatp"/></span>
						</div>
						</div>
						</s:if>
						</s:if>
						
						
					</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;margin-top: 7px;margin-bottom: 5px;">
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
					<%if(loginfo.getIskunal()!=1){ %>
						<div class="form-group" style="margin-bottom: 0px !important;">
							<span><b>Consultant</b> : 
							<s:if test="ipdconsultant!='' || ipdconsultant!=null">
							    <s:property value="ipdconsultant"/>
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
							
							<s:else>
							       
							</s:else>
							</span>
							</s:if>
						</div>
					</div>
					
					
					<s:if test="finalDiagnosis!=''">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
						<div class="form-group" style="margin-bottom: 0px !important;">
							<span><b>Diagnosis</b> : <%String diagnosis=(String)session.getAttribute("finaldiagnosis"); %>
							<%-- <s:property value="finalDiagnosis"/> --%>
							<%=diagnosis.toString() %></span>
						</div>
					</div>
					</s:if>
					</div>
				</div>
				<%int i=0; %>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
						<div class="table-responsive"> 
									
							<!-- pkg bifergation -->
							<s:if test="assesmentList.size()>0">
							<table class="table"> 
								<thead> 
									<tr> 
									<th style="width: 5%">Sr No.</th>
										<th class="th1" style="width: 10%"><s:if test="invoice_date==0">Date <a href="#" onclick="showinvoicechargesByDate('<s:property value="billsummary"/>',<s:property value="invoiceid"/>,<s:property value="totalAmountx"/>,1)"> <i class="fa fa-arrow-down"></i></a></s:if></th> 
										
										<th class="th3" style="width: 34%">Service Name (Notes) <a href="#" onclick="showinvoicechargesByDate('<s:property value="billsummary"/>',<s:property value="invoiceid"/>,<s:property value="totalAmountx"/>,0)"> <i class="fa fa-arrow-down"></i></a></th> 
										<th class="th2">Code</th> 
										<th style="text-align: center;width: 89px" class="th5">Qty</th>
										<th class="th2">Unit Charge</th> 
										 <s:if test="discstatus1">
										<th class="th2">Discount</th> 
										</s:if>
										<th style="text-align:right;" class="th4 hidden">Rate</th>										 
										<th style="text-align:right;" class="th6">Amount</th> 
									</tr> 
								</thead> 
								<tbody>
								<tbody id="viewinvoicetbody"> 
								
	                              <tr class="totalbor">
	                                    <td colspan="2"><b><s:if test="name=='Bed Charge'">Accommodation Charges</s:if>
	                                    <s:elseif test="name=='Ipd Registration Charge'">Admission Charge</s:elseif>
	                                    <s:else> <s:property value="masterchargetype"/></s:else>
	                                   
	                                    </b></td>
	                                    <td></td>
										<td></td>
										<td></td>
										<td></td>
										 <s:if test="discstatus1">
										<td></td>
										</s:if>
										<td style="text-align:right;color: #5cb85c;">Sub Total <%=Constants.getCurrency(loginfo)%><s:property value="tpstotal"/></td>
	                              </tr>
                                  <s:iterator value="assesmentList">
								<tr>
								<td><%=++i %></td>
										<%-- <th scope="row">
											<s:if test="invoice_date==0">
												<s:property value="commencing"/>
											</s:if>
										</th>  --%>
										<td>
												<s:property value="commencing"/>
										</td> 
										
										<td class="padletab">
											<s:if test="dna==true">
												<s:property value="appointmentType"/>
												<%-- <s:if test="ipdid!=0">
													<s:property value="ward"/>
												</s:if>  --%>
												<span style="color:red">(DNA)</span>
											</s:if>
											<s:else>
												<s:property value="appointmentType"/>
												<table >
												
												<s:iterator value="invstlist">
												<tr style="color: gray ;">
												<td>
												<s:property value="name"/>
												</td>
												</tr>
												</s:iterator>
												</table>
												<%-- <s:if test="ipdid!=0">
													<s:property value="ward"/>
												</s:if>  --%>
											</s:else> 
										</td>
										<td><s:property value="tpcode"/></td> 
										<td class="text-center">
											<s:property value="quantity"/>
											
											
										</td>
										
										<td class="">
											<%=Constants.getCurrency(loginfo)%><s:property value="unitcharge"/>
											
										</td>
										
										 <s:if test="discstatus1">
										<td class="">
											<s:if test="pkginvissid==0">
												<%=Constants.getCurrency(loginfo)%><s:property value="chargedisc"/>
											</s:if>
											<s:else>
											</s:else>
											
										</td>
											</s:if>						<%-- <td><s:property value="appointmentType"/></td> --%>
										
										<td class="text-right hidden">
											<%=Constants.getCurrency(loginfo)%><s:property value="charges"/>
											
										</td>										
										<td class="text-right">
											<%=Constants.getCurrency(loginfo)%><s:property value="chargeTotal"/>
											
											
										</td>
								</tr>
								</s:iterator>
								
								
								</tbody>
							</table>
							
							</s:if>
							
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-top: -8px;padding-bottom: 0px;">
					
					<div class="col-lg-8 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
					<%if(loginfo.getIskunal()!=1){ %>
						<div class="form-group" style="margin: 0px 0px 4px 0px;">
								<span for="inputEmail3" class="control-label">Payment Mode</span><span>: <s:property value="paymode"/> <s:if test="paymode=='Cheque'">(<s:property value="chequeno"/>)</s:if></span>
						</div>
						<div class="form-group" style="margin: 0px 0px 4px 0px;">
							<span for="inputEmail3" class="control-label">Prepared By</span><span>: <s:property value="preparedby"/></span>
						</div>
						
						<s:if test="isparkingcharge==1">
							<div class="form-group" style="margin: 0px 0px 4px 0px;">
								<span for="inputEmail3" class="control-label">Note:</span><span>Parking charges discount applied.</span>
							</div>
						</s:if>
						
						<div class="form-group marbot3" style="margin: 0px 0px 4px 0px;">
							<s:if test="deleted==1">
								<span for="inputEmail3" class="control-label">Cancel Notes</span><span>: <s:property value="cancelNotes"/></span>
							</s:if>
							<s:else>
								<span for="inputEmail3" class="control-label">Notes</span><span>: <s:property value="submitInvoiceNotes"/></span>
							</s:else>
						
						</div>
						<%} %>
					</div>
					
					<div class="col-lg-2 col-xs-3 col-md-3 col-sm-3 setotas">
					 								 <%--   <s:if test="dicsAmount!='0.00'">
                                            			<p style="margin: 0px 0px 4px 0px;">Discount <small>(<s:property value="discount"/>
                                            			<s:if test="disctype==0">
                                            				%
                                            			</s:if>
                                            			<s:else>
                                            				<%=Constants.getCurrency(loginfo)%>
                                            			</s:else>
                                            			)</small> :</p>
                                            			</s:if> --%>
                                            			<p style="margin: 0px 0px 4px 0px;">Amount Charged :</p>
                                            			<p style="margin: 0px 0px 4px 0px;">Amount Within Package :</p>
                                            			<!-- <p style="margin: 0px 0px 4px 0px;">Total Balance :</p> -->
                                            			
                   </div>
                   <div class="col-lg-2 col-xs-3 col-md-3 col-sm-3 setotas">
                                                      <%--  <s:if test="dicsAmount!='0.00'">
                                            			 <p style="margin: 0px 0px 4px 0px;"><span style=""><%=Constants.getCurrency(loginfo)%><s:property value="dicsAmount"/></span></p>
                                            			</s:if> --%>
                                            			<p style="margin: 0px 0px 4px 0px;"><span style=""><%=Constants.getCurrency(loginfo)%><s:property value="tpstotal"/></span></p>
                                            			<input type="hidden" value="<s:property value="netpayamount"/>" id="tthidden" />
                                            			
                                            			
                                            			<p style="margin: 0px 0px 4px 0px;"><span><%=Constants.getCurrency(loginfo)%><s:property value="pkgtotal"/></span></p>
                                            			<s:if test="actionType=='show'">
                                            				<%-- <p style="margin: 0px 0px 4px 0px;"><span><%=Constants.getCurrency(loginfo)%><s:property value="balancex"/></span></p> --%>
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
						
						<%if(loginfo.getInvestigation_newaccess().equals("0")){ %>
						<div class="table-responsive">
							<table class="table">
								<thead> 
									<tr> 
										<th class="th8">Date</th> 
										<th class="th9">Receipt</th> 
										<!-- <th class="th10">Note/Payment Mode</th>  -->
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
											<td>Receipt - (R.<s:property value="id"/>)</td>
										</s:if>
										<s:else>
											<td>Receipt - (RF.<s:property value="id"/>)</td>
										</s:else>
										<%-- <td><s:property value="note"/></td> --%>
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
										<td>Receipt - (RF.<s:property value="invoicee"/>)</td>
										<!-- <td></td> -->
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										<td>Refund Receipt</td>
									</tr>
									</s:iterator>
								</s:if>
								
								<s:iterator value="transactionList">
									<s:if test="paymentmode!='prepayment'">
									<tr>
										<td><s:property value="fromDate"/></td>
										<td>Receipt - (R.<s:property value="id"/>)</td>
										<%-- <td><s:property value="paymentmode"/></td> --%>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										<td>Payment(<s:property value="paymentmode"/>)</td>
									</tr>
									</s:if>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<%} %>
						
					</div>
						
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin-top: 25px;">
						<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-left" style="padding: 0px;">
							<%if(loginfo.getIskunal()!=1){ %>
							<s:if test="payby!='Third Party'">
								<span>Authorized Signatory</span><br>
							</s:if>
							<span>[FOR <s:property value = "clinicName"/>]</span>
							<%} %>
							
						</div>
						<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 text-right" style="padding: 0px;">
						
							<%-- <div class="form-group wordscolr">
								<label for="inputEmail3" class="control-label" style="color: #d07878;text-transform: uppercase;">In Words: </label> <span id="word" style="color: #d07878;text-transform: uppercase;"> </span> <span style="color: #d07878;text-transform: uppercase;"> ONLY </span><br> 
								<%if(loginfo.getIskunal()!=1){ %>
								<span>Printed By: <s:property value="printedBy"/></span>
								<%} %>
							</div> --%>
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
			                                <a href="#" onclick="sendmail();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Send Email">Email</i></a>
	                                  		<a href="#" onclick="printpage();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Print">Print</a>
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
  