<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/masters.js"></script>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>SMS Template Master </h4>

			</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 2px solid ;padding:10px; text-align: center;" >
<h3><b>Edit SMS Template</b></h3>
<form action="updatesmsMaster" method="post" id="savemfgfrm">

<label style="width: 20%">SMS Invoice Type: -</label>
<div class="form-group">
 <s:label name="sms_itypename"></s:label>
</div>
<label  style="width: 20%">SMS Type: -</label>
<div class="form-group" id="smstypelist">
 <s:label name="sms_typename"></s:label>
</div>
<s:hidden name="sms_type"></s:hidden>
<s:hidden name="sms_itype"></s:hidden>
<label  style="width: 20%">SMS</label>
<div class="form-group">

<s:textarea rows="5" cols="35" class="form-control" id="smstxt" name="smstxt" placeholder="160 Character Only" style="margin: 0px; width: 260px; height: 156px;" maxlength="160" ></s:textarea><br><br>
<s:submit value="Update" cssStyle="width: 10%" cssClass='btn btn-primary'/>
</div>
<s:hidden name='id'/>
</form>
</div>
</div>