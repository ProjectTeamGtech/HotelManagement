<%@taglib uri="/struts-tags" prefix="s"%>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>YUVICARE Tax Master </h4>

			</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 2px solid ;padding:10px; text-align: center;" >
<h3><b>Add New Tax</b></h3>
<form action="saveToTaxmasterMaster" method="post">

<div class="form-group">
<label  style="width: 20%">Name</label>
<input type="text" name="name" class="form-control" style="width: 20%" required="required"><br>
<label  style="width: 20%">Percent</label>
<input type="number" min="0" max="100" name="percent" class="form-control" style="width: 20%" required="required"><br>
<label  style="width: 20%" >Tax type</label>
<s:if test="taxtype1">
<input type="radio" name='taxtype' value='1' required="required" > Type 1
</s:if>
<s:if test="taxtype2">
<input type="radio" name='taxtype' value='2' required="required" > Type 2
</s:if>
<s:if test="taxtype3">
<input type="radio" name='taxtype' value='3'  required="required" > Type 3
</s:if>
<br>
<label  style="width: 20%"></label><br>

<input type="submit" class='btn btn-primary' style="width: 10%" value="Add" >
</div>
</form>
</div>
</div>