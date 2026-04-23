 <%@ taglib uri="/struts-tags" prefix="s"%>
 <div class="row col-lg-8 col-md-12 col-xs-12 col-sm-12">

                <div class="">
                    <div class="panel-body">
                       
                        <div class="row marto">
                           <link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp" %>
							
                        </div>
                 </div>   
             </div> 
          </div>
         
         
		
						<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
					
					
		
						<th>Equipment</th>
						<th>Quantity</th>
						<th>Description</th>
						
						</tr>
						</thead>
					
							<tbody>
								<s:iterator value="listot">
									<tr>
										<td><s:property value="equipment"/></td>
										<td><s:property value="quantity"/></td>
										<td><s:property value="description"/></td>
									</tr>
								
								</s:iterator>
					
							</tbody>
							
						</table>
						
					<br>	
					 <div class="row col-lg-12 col-md-12 col-xs-12 col-sm-12">
		            <div class="form-group text-right hidden-print">
                                    	<!--<a type="button" onclick="openPopup('checkavailabilityPharmacy?selectedid=<s:property value="id"/>')" class="btn btn-primary btn-lg">Check Availability</a>
                                    	--><a type="button" class="btn btn-primary btn-lg"  title="Print" onclick="printpage()">Print</a>
                                    </div>
                              </div>        