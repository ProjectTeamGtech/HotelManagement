<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="common/js/masters.js"></script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>MFG Master </h4>

			</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 2px solid ;padding:10px; text-align: center;" >
<h3><b>Add New MFG</b></h3>
<form action="savemfgMaster" method="post" id="savemfgfrm">

<div class="form-group">
<label  style="width: 20%">MFG Name</label>
<input type="text" name="name" class="form-control" style="width: 20%" required="required" id="mfg_name"><br><br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
<label  style="width: 11%" class=" hidden" id="existlbl">*Mfg Name already Exist </label><br>


<a href="#" class='btn btn-primary' style="width: 10%"  onclick="checkexistmfg()">Add</a>
</div>
</form>
</div>
</div>