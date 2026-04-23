 <%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
 <script type="text/javascript" src="appointment/js/ledger.js"></script> 
 <style>
 	.scroll{
 		overflow: scroll;
	    width: 100%;
	    height: 420px;
	    overflow-x: hidden;
	    border: solid 2px #4e7894;
 	}
 </style>
 <div class="manascommheader row" >
                                    
                                    
         <img src="dashboardicon/Financei.png" class="img-responsive prescripiconcircle" style="margin: 0px;padding: 1px">
        
          
          <h4>Create New Ledger Head</h4>
          
          
          
            
            
                                   
                                </div>
                                
                                
                                 <!-- Accounts Common Menu -->
                                
                                <div class="row ">
                               	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose hidden">
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('ExpenceManagement')" type="button" class="btn btn-success" >Create Voucher</button>
                               			</a>
                               		</div>
                               		
                               		
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('ledgAppointmentType')" type="button" class="btn btn-success" >Create New Ledger</button>
                               			</a>
                               		</div>
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('viewledrportAppointmentType')" type="button" class="btn btn-success" >View Ledger Report</button>
                               			</a>
                               		</div>
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('aheadAppointmentType')" type="button" class="btn btn-success" >Add New Ledger Head</button>
                               			</a>
                               		</div>
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('ocrptsAppointmentType')" type="button" class="btn btn-success" >Opening / Closing Report</button>
                               			</a>
                               		</div>
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('tbAppointmentType')" type="button" class="btn btn-success" >Trial Balance</button>
                               			</a>
                               		</div>
                               		
                               	</div>
                                	
                                <div class="row hidden">
                               	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
                               	 	
                               	 </div>
                               	</div>		
                                <br/>   
     
     
     
     <%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>    
 <div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>	

<br/>

<s:form action="newaheadAppointmentType" id="newaheadfrm" theme="simple"> 
<s:hidden name="hdnnewahead" id="hdnnewahead"/>
<s:hidden name="hdnnewobal" id="hdnnewobal"/>
<s:hidden name="actype" id="actype"/>
<s:hidden name="aheadname22" id="aheadname22"/>
</s:form>


<s:form action="aheadAppointmentType" id="servicefrm" theme="simple"> 
<s:hidden name="aheadname" id="selectedledgernameid"/>
<s:hidden name="aheadnametxt" id="aheadnametxt"/>
<s:hidden name="hdnsearchtext" id="hdnsearchtext"/>

</s:form>



<s:form action="saveaheadAppointmentType" id="aheadfrm" theme="simple"> 
		 <div class="row">
			<div class="col-lg-12">
			
				<div class="col-lg-3">
						<s:select name="aheadname" id="aheadname" list="aheadNameList" listKey="id" listValue="name"
			headerKey="0" headerValue="Select Account Head" cssClass="form-control chosen-select"
			onchange="showSelectedAheadServices()"
			/>
			</div>
			
			<div class="col-lg-2">
					<input type="text" name="newahead" id="newahead" class="form-control"
					placeholder="Enter New Account Head"/>
					
			</div>
			
			<div class="col-lg-2">
				<s:select name="hdnactype" id="hdnactype" list="#{'0':'Select Account Type','1':'Credit','2':'Debit'}" 
				cssClass="form-control chosen-select" ></s:select>
			</div>
			
			<div class="col-lg-2">
					<input type="number" name="openingbal" id="openingbal" class="form-control"
					placeholder="Enter Opening Balance " value="0"/>
					
			</div>
			
				<div class="col-lg-2">
						<s:select name="aheadnamex22" id="aheadnamex22" list="aheadNameList" listKey="id" listValue="name"
			headerKey="0" headerValue="Group Account Head" cssClass="form-control chosen-select"
			
			/>
			</div>
			
			<div class="col-lg-1">
				<input type="button" value="Add" onclick="addnewahead()" class="btn-primary btn">
			</div>
			
			<div class="col-lg-2 hidden" onclick="opencPopup('ocrptsAppointmentType')">
				<a href="#">
					<input type="button" value="Opening Closing Report" onclick="addnewahead()" class="btn-primary btn-lg">
				</a>
			</div>
			
			
			</div>
		</div>
		</br>
		 <div class="row hidden">
			<div class="col-lg-12">
				<div class="col-lg-2">
				<a href="#" onclick="opencPopup('tbAppointmentType')">
					Trial Balance
				</a>
				</div>
			
			</div>
		</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">		
			<h3 class="viewledrcenter padingleftrightzeroi"><b>Add New Ledger Head</b></h3>
			<br/>


<div class="scroll ">

		<table class="my-table tablexls " id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
					
					
		
					    	<th>Services</th>
						
						</tr>
						</thead>
					
							<tbody>
					 	<s:iterator value="aheadserviceList" status="rowstatus">
					 	<s:if test="parentid==''||(parentid==aheadname)">
					 	<tr>
								<td>
									<input type="checkbox" id="chledger<s:property value="id"/>" name="chahead" class="chaheadcase"
									value="<s:property value="id"/>"
									>
									<s:property value="name"/>  --<s:property value="aheadname"/>--<s:property value="parentid"/>
								</td>
								
							</tr>
					 	</s:if>
					 	<s:else>
					 	<tr><td><input type="checkbox" id="chledger<s:property value="id"/>" name="chahead" class="chaheadcase hidden"
									value="<s:property value="id"/>"
									><s:property value="name"/> </td></tr>
					 	</s:else>
							
						
						</s:iterator> 

					
							</tbody>
							
						</table>
						
						</div>
	</div>					
					<br>	
					
					
					<s:hidden name="hdnaheadserviceid" id="hdnaheadserviceid"/>
					<s:hidden name="dbselectedservices" id="dbselectedservices"/>
					
					
					 <div class="row col-lg-12 col-md-12 col-xs-12 col-sm-12">
		            <div class="form-group text-right hidden-print">
                                    	<a type="button" class="btn btn-primary btn-lg"  title="Print" onclick="saveAheadServices()">Save</a>
                                    </div>
                              </div> 
	</s:form>
	
	
	<br/>
	
	 <div class="row hidden">
	<div class="col-lg-12">
	
		<div class="col-lg-3">
		
			<h3><s:property value="aheadnametxt"/></h3>
				
			</div>
			<div class="col-lg-2">
				<input type="text" name="searchtext" id="searchtext" class="form-control" 
				placeholder="Search by Invoce Number">
			</div>
			<div class="col-lg-2">
				<input type="button" value="Go" onclick="showSelectedAheadServices()" class="btn-primary btn-lg">
			</div>
			
		<div class="scroll">	
		<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
					
					
		
					    	<th>Date</th>
							<th>Vch No.</th>
							<th>Debit</th>
							
							<th>Credit</th>
							
						
						</tr>
						</thead>
					<s:if test="ledgerreport.size()>0">
							<tbody>
								<s:iterator value="ledgerreport" status="rowstatus">
							<tr>
								<td><s:property value="commencing"/></td>
								<td><s:property value="id"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx"/></td>
								
								
							</tr>
						
						</s:iterator>

					
							</tbody>
							
							</s:if>
							<s:else>
								<tr>
									<td><h4>No Record Found!!!</h4></td>
								</tr>
							</s:else>
							
							</table>
							
							</div>
	
	</div>
	
	</div>
	
	
	
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
                        
	
	
	