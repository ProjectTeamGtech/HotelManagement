<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
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
 <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
<SCRIPT type="text/javascript">
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Lab report",
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
				                           <s:form cssClass="form-inline search" action="vatreportProduct" theme="simple">
				                            <div class="form-group">
					                           		<span class="text-uppercase"><b>GST Report</b> &nbsp; - &nbsp;</span>
					                           </div>
										  	<div class="form-group" style="width:10%" >
										  		<s:textfield name="fromdate" id="fromdate" readonly="true" cssClass="form-control" placeholder="From Date" cssStyle="width:100%" />
										  	</div>
										  	<div class="form-group" style="width:10%">
										  		<s:textfield name="todate" id="todate" readonly="true" cssClass="form-control" placeholder="To Date" cssStyle="width:100%" />
										  	</div>
										  	 <%if(loginInfo.getUserType()==2 || loginInfo.getPharmacyUserType()==2 ) {%>
												<div class="form-group">
													<s:select list="locationListPharmacy" name="location" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
											     	</s:select> 
											     </div>
											<%}%>  
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
										<center><h3>GST Report</h3> </center>	
									</div>
									
									<table class="table table-bordered xlstable" cellspacing="0" width="100%">
                                     <thead>
                                
                                <tr class="loc">
                                        <td>Vr.No</td>
                                        <td>Vr.Date</td>
                                        <td>Supplier Name</td>
                                        <td>GST No</td>
                                        <td class="text-right">5Taxable</td>
                                        <td class="text-right">5% GST</td>
                                        <td class="text-right">12Taxable</td>
                                        <td class="text-right">12% GST</td>
                                        <td class="text-right">18Taxable</td>
                                        <td class="text-right">18% GST</td>
                                        <td class="text-right">28Taxable</td>
                                        <td class="text-right">28% GST</td>
                                        <td class="text-right">TableGST</td>
                                        <td class="text-right">TotalGST</td>
                                        <td class="text-right">GrossAmt</td>
                                        <td class="text-right">NetAmt</td>
                                    </tr>
                                </thead>
                                	<tbody>
                                <s:iterator value="vatReportList"> 
                                	<s:iterator value="listVat">  
                                 		<tr>
                                        <td><a href="#" onclick="openPopup('grnprintProcurement?id=<s:property value="id"/>')"><s:property value="voucherno"/></a></td>
                                        <td><s:property value="date"/></td>
                                       	<td class=""><s:property value="vendor"/></td>
                                       	<td><s:property value="tinno"/></td>
                                       	<td class="text-right"><s:property value="fiveTaxable"/></td>
                                       	<td class="text-right"><s:property value="fivetaxPer"/></td>
                                       	<td class="text-right"><s:property value="twellTaxable"/></td>
                                       	<td class="text-right"><s:property value="twelltaxPer"/></td>
                                       	<td class="text-right"><s:property value="eighteenTaxable"/></td>
                                       	<td class="text-right"><s:property value="eighteentaxPer"/></td>
                                       	<td class="text-right"><s:property value="twentyeightTaxable"/></td>
                                       	<td class="text-right"><s:property value="twentyeighttaxPer"/></td>
                                       	<td class="text-right"><s:property value="tableVal"/></td>
                                       	<td class="text-right"><s:property value="totalVat"/></td>
                                       	<td class="text-right"><s:property value="grossVat"/></td>
                                       	<td class="text-right"><s:property value="netVal"/></td>
                                        <td class="hidden"><a href="#" data-toggle="modal" data-target="#history">history</a></td>
                                       </tr>    
                                    </s:iterator>
                                    <tr class="bortopbot">
                                		<td style="color: red;">Group Total</td>
                                		<td></td>
                                		<td></td>
                                		<td></td>
                                		<td style="color: red;text-align:right;"><s:property value="fiveTaxable"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="fivetaxPer"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="twellTaxable"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="twelltaxPer"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="eighteenTaxable"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="eighteentaxPer"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="twentyeightTaxable"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="twentyeighttaxPer"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="tableVal"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="totalVat"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="grossVat"/></td>
                                		<td style="color: red;text-align:right;"><s:property value="netVal"/></td>
                                	</tr>
                                	 </s:iterator>
                                	 <tr class="bortopbot1">
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
                                 </tr>
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