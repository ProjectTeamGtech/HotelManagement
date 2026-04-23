<%@taglib uri="/struts-tags" prefix="s" %>
<%-- <script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="accounts/js/accounts.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/popupscript.js"></script>
<script type="text/javascript" src="diarymanagement/js/commonAppointmentView.js"></script> --%>

<script type="text/javascript" src="treatmentEpisode/js/treatmentEpisodeList.js"></script>
<script>
	$(function() {
		$( "#clientSearchDiv").dialog({
			autoOpen: false,
			resizable: true,
			height: 600,
			width: 650,
			modal: true,
			buttons: {
				
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			}
		});
		
		
	
	});
	function showTreatment(){
		var chk=0;
		var clientId = document.getElementById('tempclientid').value;
		 document.getElementById('clienterror').innerHTML="";
		
		if(clientId==0 || clientId==null || clientId == undefined){
			var clienterror = document.createElement("label");
			clienterror.innerHTML = "Select Client";
	        document.getElementById('clienterror').appendChild(clienterror);
	        chk=1;
	     }  
		if(chk==1)
	    {
	       return false;
	    }
	    else
	    {
	    	 return true;
	    }
		
		
	}
	</script>
<div class="row">
<div class="col-lg-12 col-md-12">
			<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Treatment Episode</h4>
									</div>
								</div>
			</div>
</div>
<br>
<s:form action="showTreatmentEpisode" theme="simple">
<s:hidden name="clientId" id="tempclientid"></s:hidden> 
	<div class="">
	<label>Clients</label>
	<div class="form-inline">
  <div class="form-group" style="width: 33%;">
    <s:textfield name="client" id="client"  readonly="true"
				cssClass="form-control" onclick="showPopUp()" data-toggle="modal" data-target="#clientSearch" cssStyle="width: 99%;"/>
			
			<label id="clienterror"  class="text-danger"></label>
  </div>
  <s:submit value="Show Treatment Episode" onclick="return showTreatment()" theme="simple" cssClass="btn btn-primary"></s:submit>
</div>
	</div>
		<br>		
<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
				<%int count = 1; %>
					<tr>
						<th>Sr.No.</th>
						<th>Status</th>
						<th class="sortable <s:if test="#attr.pagination.sortColumn.equals('clientname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'clientname');" style="color:#fff;"> Name</a></th>
						<th>Authorised Sessions</th>
						<th class="text-center">Edit</th>
						<th class="text-center"> Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="treatmentEpisodeList.size!=0">
						<s:iterator value="treatmentEpisodeList">
						<tr>	
							
									
									<td><%=count%></td>
									<s:if test="trtmentStatus==0">
										<td><a href="#">Ongoing</a></td>
									</s:if>
									<s:else>
										<td><a href="#">Discharged</a></td>
									</s:else>
									<td><span id = "nametd<%=count%>"><s:property  value="treatmentEpisodeName" /></span></td>
									<td><s:property value="sessions"/></td>
									
									<s:url action="editTreatmentEpisode" id="edit">
    									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
									<s:if test="trtmentStatus==0">
										<td class="text-center"><a href="#"
										class="text-info" onclick="showEditTreatmentEpisodePopup('<s:property value="id"/>')">
										<i class="fa fa-edit"></i>
									</a></td>
										
									</s:if>
									<s:else>
										<td style="text-align: center;"><a href="#" title="can't edit as client discharged">Can't Edit</a></td>
									</s:else>
     								    								<s:url action="deleteTreatmentEpisode" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
									<s:param name="clientId" value="%{clientId}"></s:param>
									<s:param name="clientName" value="%{clientName}"></s:param>
									</s:url>
									
									<td class="text-center"><s:a href="%{delete}"
										onclick="return commonConfirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
									</s:a></td>	
							</tr>
						<%count++;%>
						</s:iterator>
						
					</s:if>
					</tbody>
							
				</table>  			

				<s:else>
						There is no Treatment Episode found!!
				</s:else>
			</div>
		</div>
	</div>
	
		</s:form>
	
<s:if test="treatmentEpisodeList!=null">
<s:form action="moveTreatmentEpisode" name="paginationForm" id="paginationForm"
	theme="simple">
<s:hidden id = "clientId1" name="clientId1"></s:hidden>
<s:hidden id = "clientName1" name="clientName1"></s:hidden>
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>

</s:form>
</s:if>




 <!-- Add Treatment Episode -->
	<div class="modal fade" id="addTreatmentEpisodeDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Create Treatment
						Episode</h4>
				</div>
				<div class="modal-body">
					<%@ include file="/treatmentEpisode/pages/addTreatmentEpisode.jsp"%>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary disblebtnone"
						onclick="editSaveTreatment();">Save</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

  <script>
   $(document).ready(function () {
	    $(".disblebtnone").on("click", function() {
	        $(this).attr("disabled", "disabled");
	        doWork();
	    });
	});
   </script>
