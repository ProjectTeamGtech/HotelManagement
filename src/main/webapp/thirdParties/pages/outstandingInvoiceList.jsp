<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
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
		    border-color: #339966;
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
    border-top: 2px solid #339966;
}
  .panel-primary {
      border-color: #339966;
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
	font-size: 10px !important; font-weight: bold;
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
}
</style>





<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>













<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
	<div class="col-lg-12 col-md-12 col-xs-12">
		<div class="row letterheadhgt" style="height: 135px;">
					<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							<link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
				</div>
				
				<s:iterator value="selectedClientList">
				<div class="row">
	
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors">INVOICE</b>
								
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
					</div>
					</div>
				</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">UHID</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Patient Name</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">D.O.B</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="id"/></span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="title"/><s:property value="firstName"/> <s:property value="lastName"/></span>
							</div>
							
							 <div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="address"/> <s:property value="town"/> <s:property value="postCode"/></span>
							</div>
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="dob"/></span> 
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Payee</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Policy No.</b>           		 
							</div>
							
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="payeename"/> <s:property value="payeeadress"/> <s:property value="payeeTown"/> <s:property value="payeePostcode"/></span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<s:if test="payby=='Third Party'">
											<span>: <s:property value="policyNo"/></span> 
									</s:if>
								
							</div>
						</div>
						
					</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
						<div class="table-responsive"> 
							<table class="table"> 
								<thead> 
									<tr> 
										<th class="th1">Date</th> 
										<th class="th2">Invoice No</th> 
										<th style="text-align:right;" class="th3">Debit</th> 
										<th style="text-align:right;" class="th4">Credit</th> 
										<th style="text-align:right;" class="th5">Balance</th> 
									</tr> 
								</thead> 
								<tfoot>
									<tr style="color: green;">
										<td></td>
										<td><b>Total:</b></td>
										<td style="text-align:right;"><b><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx"/></b></td>
										<td style="text-align:right;"><b><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx"/></b></td>
										<td style="text-align:right;"><b><%=Constants.getCurrency(loginfo)%><s:property value="balanceTotalx"/></b></td>
									</tr>
								</tfoot>
								<tbody> 
								<s:iterator value="selecttedInvoiceList">
								<tr>
									<td><s:property value="commencing"/></td>
									<td>0000<s:property value="invoiceid"/></td>
									<td style="text-align:right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitAmountx"/></td>
									<td style="text-align:right;"><%=Constants.getCurrency(loginfo) %><s:property value="creditTotalx"/></td>
									<td style="text-align:right;"><%=Constants.getCurrency(loginfo) %><s:property value="balancex"/></td>
								</tr>
							</s:iterator>
	                              
								</tbody> 
							</table>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 hidden-print" style="margin-bottom:10px;padding: 0px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;text-align: right;">
	                                  		<a href="#" onclick="printpage();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Print">Print</a>
		                                    
		                                </div>
		                            </div>
		                        </div>
				</div>
				
			</s:iterator>	
	</div>
		
		
			
			<%-- (<s:property value="locationName"/>) --%>
				<%-- <s:property value="payeeEmail"/><br> --%>
				<%-- <s:property value="payeeConatctNo"/> --%>
				<%-- <label>Total : </label> <%=Constants.getCurrency(loginfo)%><s:property value="totalAmount"/> --%>
		
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
			
		
