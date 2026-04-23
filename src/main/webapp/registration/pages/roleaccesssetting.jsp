<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script type="text/javascript" src="registration/js/clinic.js"></script>
</head>
<body>
<div class="">
	<div class="">
		<div class="row details">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<h4>Role Access Setting1</h4>
			</div>
		</div>
	<div class="row ">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<div>
<div class="col-lg-12 col-md-12 topback2">
	<div class="col-lg-3 col-md-2">
		<s:select list="jobTitleList"
		cssClass="form-control showToolTip chosen-select" name="jobtitle"
		listKey="jobtitle" listValue="jobtitle" headerKey="0" onchange="setJobTitleSetting(this.value)" headerValue="Select Job Title"></s:select>
	</div>
	<div class="col-lg-1 col-md-1">
	</div>
	<div class="col-lg-3 col-md-2">
	</div>
	<div class="col-lg-1 col-md-1">
	</div>
	<div class="col-lg-3 col-md-2">
	</div>
	<div class="col-lg-1 col-md-1">
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th>Roles</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<input type="button" name="update" cssClass="btn btn-primary" id="update" value="update">
	</div>
</div>
						

											
										</div>
									</div>
								</div> 
							</div>
						</div>
						
						
	 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>					
</body>
</html>