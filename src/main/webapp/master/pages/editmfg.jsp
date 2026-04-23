<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/masters.js"></script>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>MFG Master </h4>

			</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 2px solid ;padding:10px; text-align: center;" >
<h3><b>Edit MFG</b></h3>
<form action="updatemfgMaster" method="post" id="savemfgfrm">

<div class="form-group">
<label  style="width: 20%">Name</label>
<input type="text" name="name" id="mfg_name" class="form-control" style="width: 20%" required="required" value="<s:property value='name'/>"><br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
<label  style="width: 11%" class=" hidden" id="existlbl">*Mfg Name already Exist </label><br>
<label  style="width: 20%"></label><br>
<a href="#" class='btn btn-primary' style="width: 10%"  onclick="checkexistmfg()">Update</a>
</div>
<s:hidden name='id'/>
</form>
</div>
</div>