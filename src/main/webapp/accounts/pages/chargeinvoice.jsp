<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


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

</head>
<body>

<div class=" letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				
			</div>
			
		</div>
	</div>
	
	<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
				<div class="">
					
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors">IN-PATIENT BILL</b>
								
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
					
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
						<div class="form-group marbot3" style="margin-bottom: 1px;">
						<s:if test="ipdid>0">
										<label for="inputEmail3" class="control-label">Admission ID</label><span>: <s:property value="ipdid"/> </span>
										<span class="hidden-print">&nbsp; | &nbsp;</span> 
								</s:if>
							 
							 <s:if test="admissionDate!=''">
                                     <label for="inputEmail3" class="control-label"><b>Adm. Date</b></label><span>: <s:property value="admissionDate"/></span>            		 
                                </s:if>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Patient Name</label><span>: <s:property value="client"/> </span><span class="hidden-print">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Age / Gender</label><span class="hidden-print">: <s:property value="agegender" /></span>
						</div>
						<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Age / Gender</label><span>: <s:property value="agegender" /></span>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Contact</label><span>: <s:property value="mobno"/></span>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Address</label><span>: <s:property value="address"/>, <s:property value="clienttown"/>, <s:property value="clientpostcode"/> </span> 
						</div>
						
						<div class="form-group" style="margin-bottom: 1px;">
							<s:if test="invoicename=='HD'">
									<label for="inputEmail3" class="control-label">Date</label><span>: <s:property value="fromDate"/></span>
									<label for="inputEmail3" class="control-label">To</label><span>: <s:property value="toDate"/></span>
                                </s:if>
						</div>
						
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Invoice Date</label><span>: <s:property value="date"/></span> 
						</div>
						<div class="form-group" style="margin-bottom: 1px;">
						<s:if test="payAmount > 0">
							<label for="inputEmail3" class="control-label"><b>
											<s:if test="actionType=='viewpayment'">
												Receipt No
											</s:if>
											<s:else>
												Invoice No
											</s:else>
										</b></label><span><b>: 0000<s:property value="invoiceid"/> (<s:property value="invoicename"/>)</b></span>
									</s:if>
									<s:else>
										<label for="inputEmail3" class="control-label"><b>
											<s:if test="actionType=='viewpayment'">
												Receipt No
											</s:if>
											<s:else>
												Invoice No
											</s:else>
										</b></label><span><b>: 0000<s:property value="invoiceid"/> (<s:property value="invoicename"/>)</b></span>
									</s:else>
							<span> / <label for="inputEmail3" class="control-label">UHID</label><span>: 0000<s:property value="clientId"/></span></span>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Payee</label><span>: <s:property value="payeename"/></span>,
							<s:if test="payby=='Third Party'">
                                   <label for="inputEmail3" class="control-label">Policy No</label><span>: <s:property value="policyNo"/></span> 
							</s:if>
							
							
							
						</div>
						
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<s:if test="dischargeDate!=''">
                                	<label for="inputEmail3" class="control-label">Discharge Date</label><span>: <s:property value="dischargeDate"/></span> 
                                	<span class="hidden-print">&nbsp; | &nbsp;</span>           
                                </s:if>
                                <span class="hidden-print">
                                	<s:if test="ipdid>0">
	                              <s:if test="dischargeDate!=''">
	                                   <label for="inputEmail3" class="control-label">Status</label><span>: Discharged</span>
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
	                                   <label for="inputEmail3" class="control-label">Status</label><span>: Discharged</span>
	                              </s:if>
	                              <s:else>
	                                  <label for="inputEmail3" class="control-label">Status</label><span>: Not Discharge</span>
	                              </s:else>
                            </s:if>
                            <input type="hidden" name="hiddenclientid" id="hiddenclientid" value="<s:property value="clientId"/>">
						</div>
					</div>
					</div>
				</div>
					<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 7px;margin-bottom: 5px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
						<div class="form-group" style="margin-bottom: 0px !important;">
							<span><b>Consultant</b> : </span>
						</div>
					</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 7px;margin-bottom: 5px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
						<table class="table">
							<thead>
								<tr>
									<th>Description</th>
									<th style="text-align: right;">Amount</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Registration Charges</td>
									<td style="text-align: right;">Rs.200</td>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 25px;">
						<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-left" style="padding: 0px;">
							<div class="form-group">
								<span>Cashier (Name)</span>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 text-right" style="padding: 0px;">
							<div class="form-group">
								<span>Reciver</span>
							</div>
						</div>
					</div>
					
					<div class="hidden-print">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right" style="padding-left:0px;padding-right:0px;">
					<div class="form-group" style="margin-bottom: 3px;">
						<a href="#" onclick="myFunction()"  class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" >Print</a>
					</div>
				</div>
			</div>
		
		
	</div>
				</div>
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