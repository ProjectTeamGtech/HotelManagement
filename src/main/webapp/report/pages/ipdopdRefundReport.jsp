<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 --%>    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js">
	
</script>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<style>
        .padright {
            padding-left: 40px;
        }
        .table.table {
            color: RGBA(85, 85, 85, 0.85);
            background-color: #fff;
        }

        .comtitle {
            font-size: 13px;
            background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
            color: rgb(255, 255, 255);
        }

        .marbot25 {
            margin-bottom: 25px;
        }

        .editcompany {
            float: right;
            font-size: 17px;
            color: #fff;
        }

        .borright {
            border-right: 1px dashed rgb(192, 192, 192);
        }

        .buildinglogo {
            width: 60%;
            margin-top: 30px;
        }
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
            border-top: 0;
            min-height: auto !important;
        }
        .miheight {
            min-height: auto !important;
        }
        .my-table th {
            background-color: #424A5D;
            color: #fff !important;
            border-bottom: 1px solid #DFD8D4;
            border-right: 1px solid #DFD8D4;
            border-top: 1px solid #DFD8D4;
            padding: 3px 3px 4px 5px;
            text-align: left;
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 1px 7px 1px 7px !important;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
                display: inline-block;
            }
        .sidebar-xs #header .branding {
            width: 100%;
            padding-top: 7px;
            text-align: center;
        }
        .theight {
            height: 21px;
        }
    </style>
    
    <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
        .addcatego {
            float: right;
            margin-top: -40px;
            margin-right: 30px;
        }
        .sort{
        width: 15%;padding-top: 2px;
        }
                   .setborba{
	background-color: #efefef !important;
    padding-top: 5px !important;
}
 .dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus {
    background-image: linear-gradient(to bottom, #777 0, #777 100%) !important;
    
}
.dropdown-menu {
    padding: 0px 0 !important;
    margin: 0px 0 0 !important;
}
ul.dt-button-collection.dropdown-menu>* {
    -webkit-column-break-inside: avoid;
    break-inside: avoid;
    border-bottom: 1px solid rgba(0, 0, 0, 0.5) !important;
}
 @media print
{
.loc{
			background-color: #6699cc  !important;
			color: white;
		}
body {
    font-size: 9px !important;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 9px !important;
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
    	font-size: 9px !important;
	}
	p {
	    margin: 0 0 2.5px !important;
	    font-size: 9px !important;
	}
	


.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #ccc !important;
    color: #000 !important;
    font-size: 9px !important;
}


}
        
    </style>
    <SCRIPT type="text/javascript" >
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
		$("#expected_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
	});
    
    </SCRIPT>
    
     <script type="text/javascript" src="common/js/pagination.js"></script>
     <script type="text/javascript" src="accounts/js/additionalCharges.js"></script>
</head>
<body>
<div class="">
	<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Refund Report</h4>

									</div>
							</div>
							<div style="padding-top: 10px;" class="print-visible hidden-md hidden-lg"></div>
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck hidden-print">
                       <div class="col-md-12">
                          <div class="form-inline">
						  	<s:form action="ipdopdRefundReportSummary" theme="simple">
						  		<div class="form-group" style="width: 7%;">
						  			<s:textfield id="fromDate" name="fromDate"  cssClass="form-control" placeholder="From Date" style="width:100%;"/>
						  		</div>
						  
						  		<div class="form-group" style="width: 7%;">
						  			<s:textfield  name="toDate" id="toDate"  cssClass="form-control" placeholder="To Date" style="width:100%;"/>
						  		</div>
				<%if(loginInfo.isLmh()){ %>
				<input type="text" id="invoiceid" name="invoiceid" class="form-control">
				<%}else if (loginInfo.isBalgopal()){%>
				
					 <s:select name="ipdopdwise" id="ipdopdwise" 
				          list="#{'0':'Invoice Type','1':'OPD','2':'IPD','3':'INVESTIGATION','8':'OPD REFUND','9':'IPD REFUND'}"
				           cssClass="form-control" ></s:select>
				<%}else{ %>
				     <s:select name="ipdopdwise" id="ipdopdwise" 
			        	list="#{'0':'Invoice Type','1':'OPD','2':'IPD','3':'INVESTIGATION'}"
				        cssClass="form-control" ></s:select>
				  <%} %>
						  		<div class="form-group">
						  		<button type="submit" class="btn btn-primary">Go</button>
						  		</div>
					<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printpage()">Print</a>
											</div>
						  	<a type="button" id="btnxls" title="Save As XLS" 	onclick="printExcel()" class="btn btn-primary"><i
					class="fa fa-file-excel-o"></i></a>
						  	
						   </s:form>
						   </div>
						  
                       </div>
                    </div> 
                    <div class="" id="printableArea">
                    <%int i=1; %>
                   <s:if test="parentrefundrequestlist.size!=0">
                        <table class="my-table xlstable" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr bgcolor="#3c6ea0" style="color: white;" class="loc">
                                	<td style="width: 5%;">Sr. No</td>
                                	<td style="width: 3%;" class="hidden">ID</td>
                                	<td style="width: 13%;">Receipt No</td>
                                	<td style="width: 13%;">Request Date</td>
                                    <td style="width: 13%;">Requested By</td>
                                  	<td style="width: 16%;">Patient Name</td>
                                  	<td>Invoice Type</td>
                                	<td style="width: 8%;">Amount</td>
                                	<td style="width: 20%;">Refund Note</td>
                                	<td style="width: 15%;">Approve Note</td>
                                	<td>Approved by</td>
                                	<td style="width: 8%;">Advance/Invoice</td>
                                	<td style="width: 10%;">Invoice</td >
                             
                                	<!-- <th style="text-align:center;width: 1%;">Delete</th> -->
                                </tr>
                            </thead>
                            <tbody>
                            
                              <s:iterator value="parentrefundrequestlist">
              					<tr>
              						<td><%=i%></td>
              						<td class="hidden"><s:property value="id" /></td>
              						<td>
              							<%if(loginInfo.isSeq_no_gen()){%>
												(<s:property value="physicalid"/>)
										<%} %>  
										<s:property value="creditid"/>
              						</td>
              						<td><s:property value="requested_date" /></td>
              						<td><s:property value="requested_userid" /></td>
              						<%-- <td><s:property value="clientId" /></td> --%>
              						<td><s:property value="client" /></td>
              						<td><s:property value="invoicetype" /></td>
              						<td><s:property value="charges" /></td>
              						<td><s:property value="refundnote" /></td>
              						<td><s:property value="approve_note" /></td>
              						<td><s:property value="approve_userid" /></td>
              						<td>
              							<s:if test="isfromadvance==0">
              								From Advance
              							</s:if>
              							<s:else>
              								From Invoice
              							</s:else>
              						</td>
              						<td>
              						<%if(loginInfo.isLmh()){ %>
              						<s:property value="manualinvoiceid" />
              						<%}else{ %>
              							<%if(loginInfo.isSeq_no_gen()){%>
											<s:property value="ipdopdseq" />
										<%}else{%> 
											<s:property value="manualinvoiceid" />
										<%}} %>
              						
              						</td>
              						
              						
              					</tr>
              					<%i++; %>
              				</s:iterator>
                            </tbody>
                        </table>
                         </s:if>
                         <%int ii=1; %>
                         <%if(!loginInfo.isBalgopal()){ %>
                         
                         <s:if test="autorefundlist.size!=0">
                         <table class="my-table xlstable" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr bgcolor="#3c6ea0" style="color: white;" class="loc">
                                	<td style="width: 5%;">Sr. No</td>
                                	<td style="width: 13%;">Receipt No</td>
                                	<td style="width: 13%;">Date</td>
                                    <td style="width: 13%;">Requested By</td>
                                  	<td style="width: 16%;">Patient Name</td>
                                  	<td style="width: 8%;">Refund Type</td>
                                  	<td style="width: 8%;">Amount</td>
                                </tr>
                            </thead>
                            <tbody>
                            
                              <s:iterator value="autorefundlist">
              					<tr>
              						<td><%=ii%></td>
              						<td>
              							<%if(loginInfo.isSeq_no_gen()){%>
												(<s:property value="physicalid"/>)
										<%} %>  
										<s:property value="creditid"/>
              						</td>
              						<td><s:property value="date" /></td>
              						<td><s:property value="userid" /></td>
              						<td><s:property value="client" /></td>
              						<td>Auto Refund</td>
              						<td><s:property value="debit" /></td>
              					</tr>
              					<%ii++; %>
              				</s:iterator>
                            </tbody>
                         </table>
                         </s:if>
                         <s:else>
                         	<s:if test="parentrefundrequestlist.size==0">
                         	<table class="my-table xlstable" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr bgcolor="#3c6ea0" style="color: white;" class="loc">
                                	<td style="width: 5%;">Sr. No</td>
                                	<td style="width: 3%;" class="hidden">ID</td>
                                	<td style="width: 13%;">Receipt No</td>
                                	<td style="width: 13%;">Request Date</td>
                                    <td style="width: 13%;">Requested By</td>
                                  	<td style="width: 16%;">Patient Name</td>
                                  	<td>Invoice Type</td>
                                	<td style="width: 8%;">Amount</td>
                                	<td style="width: 20%;">Refund Note</td>
                                	<td style="width: 15%;">Approve Note</td>
                                	<td>Approved by</td>
                                	<td style="width: 8%;">Advance/Invoice</td>
                                	<td style="width: 10%;">Invoice</td >
                             
                                	<!-- <th style="text-align:center;width: 1%;">Delete</th> -->
                                </tr>
                            </thead>
                            	<tbody>
                            
                                </tbody>
                        	</table>
                         	</s:if>
                         </s:else>
                         <%}else{ %>
                         <%int iii=1; %>
                         <%int iiii=1; %>
                         	<s:if test="ipdRefundList.size!=0">
		                         <table class="my-table xlstable" style="width: 100%;text-transform: uppercase;">
		                            <thead>
		                                <tr bgcolor="#3c6ea0" style="color: white;" class="loc">
		                                	<td style="width: 5%;">Sr. No</td>
		                                	<td style="width: 13%;">Receipt No</td>
		                                	<td style="width: 13%;">Date</td>
		                                    <td style="width: 13%;">Requested By</td>
		                                  	<td style="width: 16%;">Patient Name</td>
		                                  	<td style="width: 8%;">Refund Type</td>
		                                  	<td style="width: 8%;">Amount</td>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            
		                              <s:iterator value="ipdRefundList">
		              					<tr>
		              						<td><%=iii%></td>
		              						<td>
		              							<%if(loginInfo.isSeq_no_gen()){%>
														(<s:property value="physicalid"/>)
												<%} %>  
												<s:property value="creditid"/>
              								</td>
		              						<td><s:property value="date" /></td>
		              						<td><s:property value="userid" /></td>
		              						<td><s:property value="clientName" /></td>
		              						
		              						<td>Auto Refund</td>
		              						<td><s:property value="debitAmount" /></td>
		              					</tr>
		              					<%iii++; %>
		              				</s:iterator>
		                            </tbody>
		                         </table>
                          </s:if>
                          <s:if test="opdRefundList.size!=0">
		                         <table class="my-table xlstable" style="width: 100%;text-transform: uppercase;">
		                            <thead>
		                                <tr bgcolor="#3c6ea0" style="color: white;" class="loc">
		                                	<td style="width: 5%;">Sr. No</td>
		                                	<td style="width: 13%;">Receipt No</td>
		                                	<td style="width: 13%;">Date</td>
		                                    <td style="width: 13%;">Requested By</td>
		                                  	<td style="width: 16%;">Patient Name</td>
		                                  	<td style="width: 8%;">Refund Type</td>
		                                  	<td style="width: 8%;">Amount</td>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            
		                              <s:iterator value="opdRefundList">
		              					<tr>
		              						<td><%=iiii%></td>
		              						<td>
		              							<%if(loginInfo.isSeq_no_gen()){%>
														(<s:property value="physicalid"/>)
												<%} %>  
												<s:property value="creditid"/>
              								</td>
		              						<td><s:property value="date" /></td>
		              						<td><s:property value="userid" /></td>
		              						<td><s:property value="clientName" /></td>
		              						
		              						<td>Auto Refund</td>
		              						<td><s:property value="debitAmount" /></td>
		              					</tr>
		              					<%iiii++; %>
		              				</s:iterator>
		                            </tbody>
		                         </table>
                          </s:if>
	                        <s:if test="ipdRefundList.size!=0">
	                        
	                        </s:if>
	                        <s:elseif test="opdRefundList.size!=0">
	                        
	                        </s:elseif>
                            <s:else>
                         	<s:if test="parentrefundrequestlist.size==0">
                         	<table class="my-table xlstable" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr bgcolor="#3c6ea0" style="color: white;" class="loc">
                                	<td style="width: 5%;">Sr. No</td>
                                	<td style="width: 3%;" class="hidden">ID</td>
                                	<td style="width: 13%;">Receipt No</td>
                                	<td style="width: 13%;">Request Date</td>
                                    <td style="width: 13%;">Requested By</td>
                                  	<td style="width: 16%;">Patient Name</td>
                                  	<td>Invoice Type</td>
                                	<td style="width: 8%;">Amount</td>
                                	<td style="width: 20%;">Refund Note</td>
                                	<td style="width: 15%;">Approve Note</td>
                                	<td>Approved by</td>
                                	<td style="width: 8%;">Advance/Invoice</td>
                                	<td style="width: 10%;">Invoice</td >
                             
                                	<!-- <th style="text-align:center;width: 1%;">Delete</th> -->
                                </tr>
                            </thead>
                            	<tbody>
                            
                                </tbody>
                        	</table>
                         	</s:if>
                         </s:else>
                         <%} %>
                    </div>
	</div>
	
</div>

</div>

	<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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
  
<script>
    function printDiv2(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
    //document.body.innerHTML = oldPage;

    //Print Page
    setTimeout(function () {
        print_page();
    }, 2000);

    function print_page() {
        window.print();
    }

    //Restore orignal HTML
    setTimeout(function () {
        restore_page();
    }, 3000);

    function restore_page() {
        document.body.innerHTML = oldPage;
    }
}
	</script>
	
	<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "report",
			filename : "RefundReport",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}
	</script>


</body>
</html>