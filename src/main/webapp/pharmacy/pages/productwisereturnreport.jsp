<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
 <style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style>


<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>
 
	$(document).ready(function() {

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
		
		document.addEventListener("contextmenu", function(e){
    		e.preventDefault();
    		}, false); 
	});
	function printcancelInvoiceReportExcel() {

        $(".tablecancelinvoice").table2excel({
					exclude: ".noExl",
					name: "Payment List",
					filename: "paymentreport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
</script>


								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Product Wise Return Report</h4>

									</div>
								</div>
								<s:form action="productwisereturnreportPharmacy" theme="simple">
								<%-- <div class="hidden-print">
											<ul class="breadcrumb">
												&nbsp;
												<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
												<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
													<%if(breadcrumbs.isIscurrent()){ %>
														<li><%=breadcrumbs.getShowingname()%></li>
													<%}else{ %>
														<%if(breadcrumbs.getOn()){ %>
															<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
														<%}else{ %>
															<li><%=breadcrumbs.getName()%></li>
														<%} %>
													<%} %>
													
												<%} %>
											</ul>
										</div> --%>
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
									
										<div class="form-inline">
											<div class="form-group" style="width:7%;">
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div>
											
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right">
												
												<a href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
				 <table class="table table-responsive table-striped" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                 <thead>
                                    <tr class="tableback">
 										<th class="" style="width: 4%;">Sr.No</th>     
 										<th style="width: 9%;padding: 2px 3px 2px 0px;">Bill No.</th>  
 										<th style="width: 15%;padding: 2px 3px 2px 0px;">Name of Patient</th>
 										<th style="width: 9%;padding: 2px 3px 2px 0px;">Date</th>            
                                        <th style="width: 35%;padding: 2px 3px 2px 0px;">Name of Drug</th>
                                        <th style="width: 6%;padding: 2px 3px 2px 0px;">HSN</th> 
                                        
                                        <th class="hidden" style="width: 4%;">Pkg</th>
                                        <th  class="hidden" style="width: 5%;padding: 2px 3px 2px 0px;">Mfg</th>
                                        <th style="width: 9%;padding: 2px 3px 2px 0px;">Batch</th>
                                        <th style="width: 10%;padding: 2px 3px 2px 0px;">Exp. Dt</th>
                                        <!-- <th style="width: 5%;text-align:right;padding: 2px 3px 2px 0px;">AV</th> -->
                                        <th style="width: 4%;padding: 2px 3px 2px 0px;text-align: center;">Qty</th> 
                                          <th style="width: 4%;padding: 2px 3px 2px 0px;text-align: center;">MRP</th>
                                        <th style="width: 6%;text-align:right;padding: 2px 3px 2px 0px;">CGST</th>
                                        <th style="width: 6%;text-align:right;padding: 2px 3px 2px 0px;">SGST</th>
                                      
                                        <th style="width: 6%;padding: 2px 3px 2px 0px;" class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <%int i=0; %>
                                	  <tbody>
                                	  <s:iterator value="productwisereturnreport">
                                    	<tr>
                                    	<td><%=(++i) %></td>
                                    	  <td><s:property value="billno"/></td>
                                    	  <td><s:property value="clientname"/></td>
                                    	  <td><s:property value="date"/></td>
                                        <td><s:property value="mdicinenametxt"/></td>
                                        <td><s:property value="hsnno"/></td>
                                        
                                        <td class="hidden">6</td>
                                        <td class="hidden"><s:property value="mfg"/></td>
                                        <td><s:property value="batch_no"/></td>
                                        <td><s:property value="expiry_date"/></td>
                                      <%--   <td style="text-align:right;text-transform: uppercase;"><s:property value="av"/></td> --%>
                                      <td style="text-align: center;"><s:property value="saleqty"/></td>
                                      <td style="text-align: center;"><s:property value="mrp"/></td>
                                        <td style="text-align:right;text-transform: uppercase;"><s:property value="cgst"/></td>
                                        <td style="text-align:right;text-transform: uppercase;"><s:property value="sgst"/></td>
                                        
                                        <td class="text-right"><s:property value="temptot"/></td>
                                    </tr>
                                     </s:iterator>
                                     <tr style="background-color: gainsboro;color: black;">
					                                <td>Total</td>
					                               	<td></td>
					                               	<td></td>
					                               	<td></td>
					                               	<td></td>
					                                <td></td>
					                               	<td></td>
					                               	<td></td>
					                               	<td><s:property value="totalqty"/></td>
					                               	<td></td>
					                               	<td></td>
					                               	<td></td>
					                               	<td><s:property value="actualtemptot"/></s></td>
					                                </tr>
                                </tbody>
                            </table>
		</div>

<table class="my-table tablecancelinvoice" id = "cancelinvoiceReportTable " style="width: 100%;font-size: 8px;display:none;">    
           <thead>
                                    <tr class="tableback">
 										<th class="" style="width: 4%;">Sr.No</th>     
 										<th style="width: 9%;padding: 2px 3px 2px 0px;">Bill No.</th>  
 										<th style="width: 15%;padding: 2px 3px 2px 0px;">Name of Patient</th>
 										<th style="width: 9%;padding: 2px 3px 2px 0px;">Date</th>            
                                        <th style="width: 35%;padding: 2px 3px 2px 0px;">Name of Drug</th>
                                        <th style="width: 6%;padding: 2px 3px 2px 0px;">HSN</th> 
                                        
                                        <th class="hidden" style="width: 4%;">Pkg</th>
                                        <th  class="hidden" style="width: 5%;padding: 2px 3px 2px 0px;">Mfg</th>
                                        <th style="width: 9%;padding: 2px 3px 2px 0px;">Batch</th>
                                        <th style="width: 10%;padding: 2px 3px 2px 0px;">Exp. Dt</th>
                                        <!-- <th style="width: 5%;text-align:right;padding: 2px 3px 2px 0px;">AV</th> -->
                                        <th style="width: 4%;padding: 2px 3px 2px 0px;text-align: center;">Qty</th> 
                                          <th style="width: 4%;padding: 2px 3px 2px 0px;text-align: center;">MRP</th>
                                        <th style="width: 6%;text-align:right;padding: 2px 3px 2px 0px;">CGST</th>
                                        <th style="width: 6%;text-align:right;padding: 2px 3px 2px 0px;">SGST</th>
                                      
                                        <th style="width: 6%;padding: 2px 3px 2px 0px;" class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <%int k=0; %>
                                	  <tbody>
                                	  <s:iterator value="productwisereturnreport">
                                    	<tr>
                                    	<td><%=(++k) %></td>
                                    	  <td><s:property value="billno"/></td>
                                    	  <td><s:property value="clientname"/></td>
                                    	  <td><s:property value="date"/></td>
                                        <td><s:property value="mdicinenametxt"/></td>
                                        <td><s:property value="hsnno"/></td>
                                        
                                        <td class="hidden">6</td>
                                        <td class="hidden"><s:property value="mfg"/></td>
                                        <td><s:property value="batch_no"/></td>
                                        <td><s:property value="expiry_date"/></td>
                                      <%--   <td style="text-align:right;text-transform: uppercase;"><s:property value="av"/></td> --%>
                                      <td style="text-align: center;"><s:property value="saleqty"/></td>
                                      <td style="text-align: center;"><s:property value="mrp"/></td>
                                        <td style="text-align:right;text-transform: uppercase;"><s:property value="cgst"/></td>
                                        <td style="text-align:right;text-transform: uppercase;"><s:property value="sgst"/></td>
                                        
                                        <td class="text-right"><s:property value="total"/></td>
                                    </tr>
                                     </s:iterator>
                                     
                                </tbody>
</table>

  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>



	