<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="ipd/js/renderpage.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>



<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Bed Occupancy List",
					filename: "BedOccupancyReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

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
	
	
});


function setSorting(column,order){
	if(order=='asc'){
		order = 'desc';
	}else{
		order = 'asc';
	}
	document.getElementById('orderby').value = column;
	document.getElementById('order').value = order;
	document.getElementById('invoicerportfrm').submit();
}


</script>

<div class="">
         <div class="">
							
							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
							
							
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Render Charges Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="renderChargesrptReport" theme="simple" id = "occupancyfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group" >
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" cssStyle="width: 80px;"  placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" >
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" cssStyle="width: 80px;"  placeholder="to date"></s:textfield>
			</div>
			
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
				<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
				 <input onclick="renderPayment()" type='button' value='Charge Payment' class='btn btn-primary' >
			</div>
			
		
	</div>
	</div>
	</s:form>
	
	<%int i=1; %>
	<table class="my-table xlstable" style="width: 100%">
				<tr>
				    <td style="width: 2%;" class="hidden-print">
                         <input type="checkbox" id="selecctall"/>
                    </td>
				    <th>Sr. No.</th>
	                <th>Patient Name</th>
					<th>Charge Name</th>
					<th>charge amount</th>
					<th>Charge discount</th>
					<th>Total Amount</th>
					<th>share amount</th>
					<th>Balance amount</th>
					<th>paid amount</th>
					<th>Practitioner Name</th>
					<th>Userid</th>
					<th>Date_time</th>
					
					
					
				
				</tr>
				
				
				<s:if test="Renderchrgesreport.size!=0">
							<s:iterator value="Renderchrgesreport" status="rowstatus">
							<tr>
							    <td class="hidden-print">
                                   	<input type="checkbox" class="case" value="<s:property value="Chargeid"/>" onclick="settotalchargedata()" class="form-control" />
                                </td>
								<td><%=i++ %></td>
								<td><s:property value="ClientName"/></td>
								<td><s:property value="Chargename"/></td>
								<td><s:property value="Charges"/></td>
								<td><s:property value="Discountx"/></td>
								<td><s:property value="TotalAmount"/></td>
								<td><s:property value="Shared_amount"/></td>
								<td><input type="text" value=<s:property value="Balance"/> id="<s:property value="Chargeid"/>" name="balance" class="textcase"/></td>
								<td><s:property value="PayAmount"/></td>
								<td><s:property value="Docid"/></td>
								<td><s:property value="Userid"/></td>
								<td><s:property value="Date_time"/></td>
							</tr>
							</s:iterator>
						</s:if>
						
						<s:else>
					No Record Found!!
				</s:else>
				
</table><br>
	
											
										
									</div>
								</div>
							</div>
						</div>
<%-- <div class="hidden">
<span  id="hiddentotalchrge"></span></div> --%>
 <input type="hidden" name="chrge" id="hiddentotalchrge">
 <input type="hidden" name="chargeid" id="hiddenchargeid">
 <input type="hidden" name="balnceamountt" id="hiddenbalnceamount">
<!-- Render Payment -->
<div id="rnderpaymentpopup" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="modal-title">Render Payment <label id="patientnm"></label></h4>
        </div>
  <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                  
                   
                   
                   <div class="form-group" id="sitting_num" >
        				 <label>Total chrge</label><label class="text-danger">*</label>
				              <input type="number" id="to_charge" name="sitting" readonly="readonly"
				                Class="showToolTip form-control" data-toggle="tooltip" />
        	          </div>
                   
                  
        	 <%--   
        	   <div class="form-group">
				     <s:select id="doctor" name="doctor" list="Consultantdoclist" listValue="doctor" listKey="id"
									headerKey="" headerValue="Select Doctor"
									title="Select Doctor form list"
									cssClass="form-control showToolTip ddlAddNew" data-toggle="tooltip"/>
			      </div> --%>
			      
		             			
                <%-- <div class="form-group" >
        				 <label>Charge Name</label><label class="text-danger">*</label>
        				 <span id="chrge_name"  Class="form-control"></span>
				            
        	    </div>
        
        	     <div class="form-group" id="sitting_num" >
        				 <label>Charge</label><label class="text-danger">*</label>
				              <input type="number" id="charge" name="sitting" readonly="readonly"
				                Class="showToolTip form-control" data-toggle="tooltip" />
        	    </div>  --%>
        	    
        	    
        	     <div class="form-group" id="Payment_id" >
        				 <label>Payment mode</label><label class="text-danger">*</label>
				             <s:select  list="#{'Cash':'Cash','D/Card':'D/Card','Credit':'Credit','UPI':'UPI'}" cssClass="form-control showToolTip chosen-select ajsolidborder" name="paymenttype" id="paymenttype"></s:select>

        	    </div>
        	    
      
           
      		</div>
     </div>
     <div class="modal-footer">
     	<button type="button" class="btn btn-primary"
							onclick="saverenderpayment()" style="margin-top: 15px;">Save</button>
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
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
