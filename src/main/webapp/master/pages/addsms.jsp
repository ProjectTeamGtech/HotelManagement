<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="mrd/js/mrd.js"></script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>SMS Template Master </h4>

			</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 2px solid ;padding:10px; text-align: center;" >
<h3><b>Add New SMS Template</b></h3>
<form action="savesmsMaster" method="post" id="savesmsfrm">
<label  style="width: 20%">SMS Invoice Type</label>
<div class="form-group">
 <s:select name="sms_itype" id="smsitypeid" cssClass="form-control" list="smsitypelist" listKey="id" listValue="sms_itype" headerKey="0" headerValue="Select  Invoice Type" cssStyle="width:20%;" onchange="getsmstypelist(this.value)" />
</div>
<label  style="width: 20%">SMS Type</label>
<div class="form-group" id="smstypelist">
 <s:select name="sms_type" id="smstypeid" cssClass="form-control chosen-select" 
                                              list="smstypelist" listKey="id" listValue="sms_type" 
                                              headerKey="0" headerValue="Select  SMS Type" cssStyle="width:20%;" onchange="checkalreadyexist(this.value)"/>
</div>
<label  style="width: 20%">SMS</label>
<div class="form-group">
<s:textarea rows="5" cols="35" class="form-control" id="smstxt" name="smstxt" placeholder="160 Character Only" style=" margin: 0px; width: 260px; height: 156px;" maxlength="160" ></s:textarea><br><br>

<a href="#" class='btn btn-primary' style="width: 10%"  onclick="validsave()">Add</a>
</div>
</form>
</div>
</div>