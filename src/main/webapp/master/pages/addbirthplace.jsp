<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="common/js/masters.js"></script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>Birth Place Master </h4>

			</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 2px solid ;padding:10px; text-align: center;" >
<h3><b>Add New Birth Place</b></h3>
<form action="savebirthplaceMaster" method="post" id="savebirthplacefrm">

<div class="form-group">
<label  style="width: 20%">Birth Place Name</label>
<input type="text" name="name" class="form-control" style="width: 20%" required="required" id="birthplace_name"><br><br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
<label  style="width: 11%" class=" hidden" id="existlbl">*Birth Place Name already Exist </label><br>


<a href="#" class='btn btn-primary' style="width: 10%"  onclick="checkexistbirthplace()">Add</a>
</div>
</form>
</div>
</div>