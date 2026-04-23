
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="CashDesk">CashDesk List</a></li>
			<li class="active"> Edit CashDesk </li>
		</ol>
	</div>
</div>	

<s:form action="updateCashDesk"  theme="simple" id="category_form"> 
<s:hidden id="id" name="id" />
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
		<div class="panel-body">			
			<div class="row">
				<div class="col-lg-12">
					<div class="form-group">
		<label>Patient Name</label>		
		<s:textfield id = "clientName" name = "clientName" readonly="true" theme="simple" cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Patient Name" placeholder="Enter Patient Name"/>
	</div>
				
				</div>
				</div>	
			
				<div class="panel-body">
		<label>Amount</label>		
		<s:textfield id = "amount" name = "amount" theme="simple" cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter amount" placeholder="Enter amount"/>
	</div>	
				</div>	
			</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
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