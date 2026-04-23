<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript"
	src="thirdParties/js/outstandingReport.js"></script>

<script type="text/javascript">
	 /* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	                
	            )
	        ); });   */
	        
	        
	        bkLib.onDomLoaded(function() {
	           
	        	 new nicEditor().panelInstance('emailBody');
	        	 $('.nicEdit-panelContain').parent().width('500px');
	        	 $('.nicEdit-panelContain').parent().next().width('500px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px');
	      });
</script>
<script>
	$(function() {
		$("#exceedLmtActionPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 235,
			width : 200,
			modal : true,
			buttons : {

				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

		$("#sendEmailPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 460,
			width : 460,
			modal : true,
			buttons : {
				"Send" : function() {
					sendSaveEmail();

				},
				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

		$("#sendSMSPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 350,
			width : 460,
			modal : true,
			buttons : {
				"Send" : function() {
					sendSaveSMS();

				},
				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

		$("#makeCallPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 350,
			width : 460,
			modal : true,
			buttons : {
				"Save" : function() {
					saveCommunication();

				},
				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

	});
</script>

<div class="">
	<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Action Report</h4>
									</div>
								</div>
</div>



<h2 align="center">
	<s:property value="thirdPartyName"></s:property>
	Action Report
</h2>

<div class="row hidden">
	<div class="col-lg-12">

		<a class="btn btn-primary" data-toggle="modal" data-target="#exceedLmtActionPopUp2">Perform
			More Actions</a>
	</div>
</div>
<br/>
<div class="table-responsive">
	<table class="table table-hover table-striped table-condensed table-bordered">
		<thead>
			<tr>
				<th>Type</th>
				<th>Date</th>
				<th>Time</th>
				<th>Notes</th>
			</tr>
		</thead>
		<tbody>
			<s:if test="outstandingActionList.size!=0">
				<s:iterator value="outstandingActionList" status="rowstatus">
					<tr>
						<td><s:property value="type" /></td>
						<td><s:property value="date" /></td>
						<td><s:property value="time" /></td>
						<td><s:property value="notes" /></td>
					</tr>

				</s:iterator>
			</s:if>
		</tbody>
	</table>
</div>
<s:else>
						There is no Record found!!
					</s:else>


<s:if test="outstandingActionList!=null">
	<s:form action="getActionListOutstandingReport" name="paginationForm"
		id="paginationForm" theme="simple">
		<s:hidden name="thirdPartyId1"></s:hidden>
		<s:hidden name="thirdPartyContactno1"></s:hidden>
		<s:hidden name="thirdPartEmail1"></s:hidden>
		<s:hidden name="thirdPartyName1"></s:hidden>
		<div class="col-lg-12 col-md-12" style="padding:0px;">
			<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
				Total:<b><s:property value="totalRecords" /> 
			</div>
			<%@ include file="/common/pages/pagination.jsp"%>
		</div>
	</s:form>

</s:if>


<div class="modal fade" id="exceedLmtActionPopUp2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Select An Action</h4>
      </div>
      <div class="modal-body">      
      <s:hidden id = "thirdPartyId" name = "thirdPartyId"/>
	<s:hidden id = "thirdPartyName" name = "thirdPartyName"/>
		<div class="row">
			<div class="col-lg-12">
				<a class="btn btn-primary width-full"  data-toggle="modal" data-target="#sendEmailPopUp2"><i class="fa fa-message"></i> Send Email</a>
			</div>
		</div>   
		<br/>   
		<div class="row">
			<div class="col-lg-12">
				<a class="btn btn-primary width-full" data-toggle="modal" data-target="#sendSMSPopUp2"><i class="fa fa-message"></i> Send SMS</a>
			</div>
		</div>   
		<br/>   
		<div class="row">
			<div class="col-lg-12">
				<a class="btn btn-primary width-full" data-toggle="modal" data-target="#makeCallPopUp2"><i class="fa fa-phone"></i> Make a Call</a>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="sendEmailPopUp2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel2">Send Email</h4>
      </div>
      <div class="modal-body">      
		<div class="row">
			<div class="col-lg-12">
				<label>To:</label>
				<s:textfield theme="simple" id="thirdPartEmail"
					name="thirdPartEmail"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Email" placeholder="Enter Email Id"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Cc:</label>
				<s:textfield theme="simple" id="ccEmail"
					name="ccEmail"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Cc" placeholder="Enter Cc"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Subject:</label>
				<s:textfield theme="simple" id="subject"
					name="subject"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter subject" placeholder="Enter subject"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Body:</label>
				<textarea class="form-control showToolTip" data-toggle="tooltip" title="Write content" placeholder="Write content" name="emailBody" id="emailBody" cols="40" rows="3"></textarea>
			</div>
		</div>   
      </div>
      <div class="modal-footer">
      
      	 <button type="button" class="btn btn-primary"  onclick="sendSaveEmail()">Send</button>
      	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="sendSMSPopUp2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel3">Send SMS</h4>
      </div>
      <div class="modal-body">      
		<div class="row">
			<div class="col-lg-12">
				<label>Contact No:</label>
				<s:textfield theme="simple" id="thirdPartyContactno"
					name="thirdPartyContactno"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Phone/Mobile No" placeholder="Enter Phone/Mobile No"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Note:</label>
				<textarea name="smsNote" id="smsNote" rows="3" class="form-control showToolTip" data-toggle="tooltip" title="Enter Message" placeholder="Enter Message"></textarea>
			</div>			
		</div>   
      </div>
      <div class="modal-footer">
      	      	 <button type="button" class="btn btn-primary"  onclick="sendSaveSMS()">Send</button>
      	
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="makeCallPopUp2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel3">Send SMS</h4>
      </div>
      <div class="modal-body">      
		<div class="row">
			<div class="col-lg-12">
				<label>Contact No:</label>
				<s:textfield theme="simple" id="thirdPartyContactno"
					name="thirdPartyContactno"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Phone/Mobile No" placeholder="Enter Phone/Mobile No"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Note:</label>
				<textarea name="callNote" id="callNote" rows="3" class="form-control showToolTip" data-toggle="tooltip" title="Enter Message" placeholder="Enter Message"></textarea>
			</div>			
		</div>   
      </div>
      <div class="modal-footer">
      
      <button type="button" class="btn btn-primary"  onclick="saveCommunication()">Save</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>



