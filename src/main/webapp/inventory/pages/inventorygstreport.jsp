<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<SCRIPT type="text/javascript">
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Purchase GST report",
					filename: "gstreport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}

	 $(document).ready(function(){
			   
			   $("#fromdate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
				$("#todate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
				
				$("#billDate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
				
				
				
				
				
				
				
			   
      });


</SCRIPT>




<style>
	hr {
    margin-top: 8px;
    margin-bottom: 8px;
}
.imflag{
	display: inline-block;
    width: 88%;
}
.bg-lightred {
    background-color: #e05d6f !important;
}
.table>tbody>tr>td, .table>tfoot>tr>td {
    border-top: none !important; 
}
.bortopbot{
	border-bottom: 1px dashed #555;
    border-top: 1px dashed #555;
}
.bortopbot1{
	border-bottom: 1px solid #555;
    border-top: 1px solid #555;
}
.suppliname{
color:#6699cc;
}
 b, strong {
    font-weight: 900;
}   
.topback2 {
    background-color: #f5f5f5;
    padding: 10px 10px 10px 0px;
}
</style>
<style>
	.loc{
		background-color: #6699cc;
		color: white;
	}
	@media print {
		.loc{
			background-color: #6699cc  !important;
			color: white;
		}
	}
</style>

</head>
<body>
	<div class="print-visible hidden-md hidden-lg letterheadhgt" >
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div>								
							
							
							<div class="">
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
									<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding: 0px;">
									
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
											<div class="col-md-10">
				                           <s:form cssClass="form-inline search" action="inventorygstreportProduct" theme="simple">
				                            <div class="form-group">
					                           		<span class="text-uppercase"><b>GRN GST REPORT</b> &nbsp; - &nbsp;</span>
					                       </div>
										  	<div class="form-group" style="width:10%" >
										  		<s:textfield readonly="true" name="fromdate" id="fromdate" cssClass="form-control" placeholder="From Date" cssStyle="width:100%" />
										  	</div>
										  	<div class="form-group" style="width:10%">
										  		<s:textfield readonly="true" name="todate" id="todate" cssClass="form-control" placeholder="To Date" cssStyle="width:100%" />
										  	</div>
										  	<div class="form-group" >
										  		<s:select list="warehouseList" id="warehouse_id" name="warehouse_id" listKey="id" listValue="name" headerKey="0" headerValue="Select Store" cssClass="form-control chosen-select"></s:select>
										  	</div>
										  	<div class="form-group" >
										  		<s:select cssClass="form-control" list="#{'0':'Purchase GRN', '1':'Return GRN'}" name="filter_type" />
										  	</div>
										  	<%-- <div class ="form-group" style="width:10%" >
										  		<s:textfield readonly="true" name="billDate" id="billDate" cssClass="form-control" placeholder="Bill Date" cssStyle="width:100%" />
										  	</div> --%>
										  	<div class="form-group" >
							                   <s:select  style="width:12%" list="vendorList" id="vendorlist" name="vendor_id" listKey="id" listValue="name" headerKey="" headerValue="Select Vendor" cssClass="chosen-select"></s:select>
						                    </div>
						                    <div class="form-group">
											  <s:select  cssClass="form-control" list="#{'0':'Sort By Voucher Date', '1':'Sort By System Date'}" name="filter_sortby" />
											</div>
		                                    
										    <div class="form-group">
										  		<button type="submit" class="btn btn-primary">Go</button>
										  	</div>
										  <div class="form-group">
										  	<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
										  	<a href="#" onclick="printpage();" class="btn btn-primary hidden-print"><i class="fa fa-print"></i></a>
										  </div>
										</s:form>
				                        </div>
				                       
				                        <div class="col-md-2 text-right" style="padding:0px;">
				                        	
				                        </div>
										
										
										
										</div>
										</div>
								</div>
								
								<div id="page_printer" class="col-lg-12" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-sm hidden-md visible-print" style="padding: 0px;">
										<center><h3>GRN GST REPORT</h3> </center>	
									</div>
									<table class="table table-bordered xlstable" cellspacing="0" width="100%">
                                     <thead>
                                
                                <tr class="loc">
                                        <th>Sr. No</th>
                                        <s:if test="filter_type==0">
                                        	<th>GRN No.</th>
                                        </s:if>
                                        <s:else>
                                        	<th>Return No.</th>
                                        </s:else>
                                        <th>Date</th>
                                        <th>Voucher No.</th>
                                        
                                        <th>Supplier Name</th>
                                         <th>Bill Date</th>
                                        <th>GST No</th>
                                        
                                        <th class="text-right">0% Amount</th>
                                        
                                        <th class="text-right">5% Amount</th>
                                        <th class="text-right">2.5% SGST</th>
                                        <th class="text-right">2.5% CGST</th>
                                        <th class="text-right">5% IGST</th>
                                        
                                        <th class="text-right">12% Amount</th>
                                        <th class="text-right">6% SGST</th>
                                        <th class="text-right">6% CGST</th>
                                        <th class="text-right">12% IGST</th>
                                        
                                        <th class="text-right">18% Amount</th>
                                        <th class="text-right">9% SGST</th>
                                        <th class="text-right">9% CGST</th>
                                        <th class="text-right">18% IGST</th>
                                        
                                        <th class="text-right">28% Amount</th>
                                        <th class="text-right">14% SGST</th>
                                        <th class="text-right">14% CGST</th>
                                        <th class="text-right">28% IGST</th>
                                       
                                        <th class="text-right">Table Amount</th>
                                        <th class="text-right">Total GST</th>
                                        <th class="text-right">Bill Amount</th>
                                    </tr>
                                </thead>
                                	<tbody>
                                	<%int i=0; %>
                                <s:iterator value="vatReportList"> 
                                		<tr>
                                		<td><%=(++i) %></td>
                                		<td><s:property value="proSeqNo"/></td>
                                		<td><s:property value="date"/></td>
                                		<s:if test="filter_type==0">
                                			<td><a href="#" onclick="openPopup('grnprintProcurement?id=<s:property value="id"/>')"><s:property value="voucherno"/></a></td>
                                        </s:if>
                                        <s:else>
                                        	<td><s:property value="voucherno"/></td>
                                        </s:else>
                                        <td class=""><s:property value="vendor"/></td>
                                        <td class=""><s:property value="voucherdate"/></td>
                                       	<td><s:property value="tinno"/></td>
                                       	
                                       	<td class="text-right"><s:property value="zerotaxamt"/></td>
                                        
                                        <td class="text-right"><s:property value="fivetaxamt"/></td>
                                        <td class="text-right"><s:property value="fivesgst"/></td>
                                        <td class="text-right"><s:property value="fivecgst"/></td>
                                        <td class="text-right"><s:property value="fiveigst"/></td>
                                        
                                        <td class="text-right"><s:property value="twelvetaxamt"/></td>
                                        <td class="text-right"><s:property value="twelvesgst"/></td>
                                        <td class="text-right"><s:property value="twelvecgst"/></td>
                                        <td class="text-right"><s:property value="twelveigst"/></td>
                                        
                                        <td class="text-right"><s:property value="eighteentaxamt"/></td>
                                        <td class="text-right"><s:property value="eighteensgst"/></td>
                                        <td class="text-right"><s:property value="eighteencgst"/></td>
                                        <td class="text-right"><s:property value="eighteenigst"/></td>
                                        
                                        <td class="text-right"><s:property value="twenteightaxamt"/></td>
                                        <td class="text-right"><s:property value="twenteightsgst"/></td>
                                        <td class="text-right"><s:property value="twenteightcgst"/></td>
                                        <td class="text-right"><s:property value="twenteightigst"/></td>
                                       
                                        <td class="text-right"><s:property value="totalamt"/></td>
                                        <td class="text-right"><s:property value="totalVat"/></td>
                                        <td class="text-right"><s:property value="netamt"/></td>
                                       	
                                       </tr>    
                                    </s:iterator>
                                    <%--  <tr class="bortopbot1">
                                  	<td style="color: green;"><b>Final Total</b></td>
                                  	<td></td>
                                  	<td></td>
                                  	<td></td>
                                  	<td style="color: green;text-align:right;"><s:property value="allfivetatTot"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="allfivetatPer"/></td>
                                 	 <td style="color: green;text-align:right;"><s:property value="alltwelltaxTot"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alltwelltaxPer"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alleighteentaxTot"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alleighteentaxPer"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alltwentyeighttaxTot"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alltwentyeighttaxPer"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alltableValtot"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alltotvatTotal"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alltotalGross"/></td>
                                  	<td style="color: green;text-align:right;"><s:property value="alltotalNet"/></td>
                                 </tr> --%>
                                </tbody>
                            </table>
								</div>
							
									</div>
								</div>
							</div>
							
							
							
							<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '450px'
								    });
								});
							</script>
							
<script>
    function printDiv(divID) {
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
</body>
</html>