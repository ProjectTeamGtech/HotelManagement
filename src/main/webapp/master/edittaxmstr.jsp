<%@taglib uri="/struts-tags" prefix="s"%>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>YUVICARE Tax Master </h4>

			</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 2px solid ;padding:10px; text-align: center;" >
<h3><b>Edit New Tax</b></h3>
<form action="updatetaxmasterMaster" method="post">

<div class="form-group">
<label  style="width: 20%">Name</label>
<input type="text" name="name" class="form-control" style="width: 20%" required="required" value="<s:property value='name'/>"><br>
<label  style="width: 20%">Percent</label>
<input type="number" min="0" max="100" name="percent" class="form-control" style="width: 20%" required="required" value="<s:property value='percent'/>"><br>
<label  style="width: 20%"></label><br>
<input type="submit" class='btn btn-primary' style="width: 10%" value="Update" >
</div>
<s:hidden name='id'/>
</form>
</div>
</div>