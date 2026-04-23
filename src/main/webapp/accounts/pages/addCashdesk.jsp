
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="accounts/js/cashdesk.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="CashDesk">CashDesk List</a></li>
			<li class="active"> Add Cashdesk </li>
		</ol>
	</div>
</div>	

<s:form action="saveCashDesk"  theme="simple" id="category_form"> 
	<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
		<div class="panel-body">			
			<div class="row">
				<div class="col-lg-12">
					<div class="form-group">
					<label>Client</label>
					<s:textfield name="client" id="client" readonly="true" cssClass="form-control" 
							     onclick="showPopUp()" data-toggle="modal" data-target="#clientSearch"/>
					<label  id = "clientError" class="text-danger"></label>	
					<s:hidden name="clientId" id="clientId"></s:hidden>
				</div>
				
				</div>
				</div>	
			
				<div class="panel-body">
					<label>Amount</label>
					<s:textfield name="amount" id="amount" cssClass="form-control" />
					<label  id = "amountError" class="text-danger"></label>
				</div>	
				</div>	
				</div>
		</div>
			
		<div class="col-lg-3 col-md-2"></div>
	</div>
	
	
		<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
		<s:submit cssClass="btn btn-primary" value="Save" onclick="return saveValidation()"/>
		<s:reset cssClass="btn btn-primary" />
		<a href="backCashDesk" class="btn btn-primary">Back</a>
	</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>	
</s:form>


<!-- Modal Client Search-->
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
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>