<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="inventory/js/managecatalogue.js"></script> 

<s:form action="sveledAppointmentType" id="ledgerfrm" theme="simple"> 
<s:hidden name="hdnledgerserviceid" id="hdnledgerserviceid"/>
<s:hidden name="dbselectedservices" id="dbselectedservices"/>
 <div class="row">
			<div class="col-lg-12">
			<div class="col-lg-3">
				<s:select name="secstorename" id="secstorename" list="secstoreList" listKey="id" listValue="name"
			headerKey="0" headerValue="Select Store" cssClass="form-control chosen-select"
			onchange="showSelectedServices()" style="width: 289px !important;"
			/>
			</div>
			
			</div>
</div>
<br/>
<div class="scroll" style="height: 60%;overflow: scroll;" >
		<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
					
					
		
					    	<th>Select Catalogue</th>
						
						</tr>
						</thead>
					
							<tbody>
					 	<s:iterator value="catlogList" status="rowstatus">
							<tr>
								<td>
									<input type="checkbox" id="chcatlog<s:property value="id"/>" name="catlogname" class="catlogcase"
									value="<s:property value="id"/>"
									>
									<s:property value="name"/>
								</td>
								
							</tr>
						
						</s:iterator> 

					
							</tbody>
							
						</table>
						</div>
						
						
						 <div class="row col-lg-12 col-md-12 col-xs-12 col-sm-12">
		            <div class="form-group text-right hidden-print">
                                    	
                                    	<button onclick="saveLedgerServices()" type="button" class="btn btn-primary addvoucher lg" id="btngo" title="Print">Save</button>
                                    	 
                                    </div>
                              </div> 
                              
                              
                     </s:form>
						
						
						
						 
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
                        