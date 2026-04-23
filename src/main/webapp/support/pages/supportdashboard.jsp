<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="common/tablesortnew/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="plugin/checkbox/style.css">
<style>
	table.dataTable {
    clear: both;
    margin-top: 0px !important;
    margin-bottom: 0px !important;
    max-width: none !important;
    border-collapse: separate !important;
}
.searchset {
    margin-top: -35px !important;
    margin-left: -15px !important;
}
table.dataTable thead .sorting:after, table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_desc:after, table.dataTable thead .sorting_asc_disabled:after, table.dataTable thead .sorting_desc_disabled:after {
    top: 2px;
}
.chosen-container-single .chosen-single div b {
    margin-top: -2px;
}
.chosen-container-single .chosen-single {
    height: 19px;
    line-height: 18px !important;
}
</style>
</head>
<body>
<div class="row details mainheader">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4><i class="fa fa-life-ring" aria-hidden="true"></i> Support Dashboard</h4>
									</div>
									<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form id="InitialDischarge" name="InitialDischarge" action="/HIS14June/InitialDischarge" method="post" class="form-inline">
											  <div class="form-group">
											    <input type="text" name="fromdate" value="" id="fromdate" class="form-control hasDatepicker" placeholder="From Date">
											  </div>
											  <div class="form-group">
											    <input type="text" name="todate" value="" id="todate" class="form-control hasDatepicker" placeholder="To Date">
											  </div>
											  <input type="submit" id="InitialDischarge_0" value="GO" class="btn btn-primary">
											  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
											  <a href="#" class="btn btn-primary">Reports</a>
										  </form>
										</div>
										</div>
										
								</div>
								
								<div class="col-lg-12 col-md-12 paddingnil">
											<table class="table table-hover table-bordered" id="tablesort">
												<thead>
													<tr>
														<td style="width: 5%;">#Tkt No</td>
														<td style="width: 11%;">Date | Time</td>
														<td style="width: 14%;">Hospital Name | User ID</td>
														<td style="width: 7%;">City</td>
														<td style="width: 10%;">Query</td>
														<td style="width: 27%;">Comment</td>
														<td style="width: 9%;">Assign To Tiger</td>
														<td style="width: 8%;"></td>
														<td>Completed Date | Time </td>
													</tr>
												</thead>
												<tbody>
												<tr>
														<td>#123</td>
														<td>17/06/2017 | 05:32 PM</td>
														<td>Mahadev Hospital | MSShadev</td>
														<td>Bilaspur</td>
														<td>Improvement</td>
														<td>Improve Your Module</td>
														<td>
															<select class="form-control chosen-select">
															  <option>Select Tiger</option>
															  <option>Adarsh Pande</option>
															  <option>Ajay Agrawal</option>
															  <option>Dipanjay</option>
															</select>
														</td>
														<td>
															
														</td>
														<td></td>
													</tr>
												<tr>
														<td>#123</td>
														<td>17/06/2017 | 05:32 PM</td>
														<td>Mahadev Hospital | MSShadev</td>
														<td>Bilaspur</td>
														<td>Improvement</td>
														<td>Improve Your Module</td>
														<td>
															<select class="form-control chosen-select">
															  <option>Adarsh Pande</option>
															  <option>Ajay Agrawal</option>
															  <option>Dipanjay</option>
															</select>
														</td>
														<td>
															<input id="checkbox-2" class="checkbox-custom" name="checkbox-2" type="checkbox" onclick="disableCHK()">
												        <label id="lblCHK2" for="checkbox-2" class="checkbox-custom-label">Processing</label>
														</td>
														<td></td>
													</tr>
													<tr>
														<td>#123</td>
														<td>17/06/2017 | 05:32 PM</td>
														<td>Mahadev Hospital | MSShadev</td>
														<td>Bilaspur</td>
														<td>Improvement</td>
														<td>Improve Your Module</td>
														<td>
															<select class="form-control chosen-select">
															  <option>Adarsh Pande</option>
															  <option>Ajay Agrawal</option>
															  <option>Dipanjay</option>
															</select>
														</td>
														<td>
															<input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        	<label for="checkbox-1" class="checkbox-custom-label">Completed</label>
														</td>
														<td>17/06/2017 | 06:32 PM</td>
													</tr>
													
												</tbody>
											</table>
										</div>
										
  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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
  
  <script type="text/javascript" src="common/tablesortnew/jquery.dataTables.js"></script>
    <script type="text/javascript" src="common/tablesortnew/dataTables.bootstrap.js"></script>
    
     <script>
	  $(document).ready(function() {
	      $('#tablesort').DataTable();
	  } );
	  
	 </script>
	 
	 <script>
				
						
					function disableCHK()
                	{
                	document.getElementById("lblCHK2").textContent="Completed";
                	document.getElementById("checkbox-2").disabled = true;
                	}
                	function disableCHK1()
                	{
                	document.getElementById("lblCHK3").textContent="Completed";
                	document.getElementById("checkbox-3").disabled = true;
                	}
                	function disableCHK2()
                	{
                	document.getElementById("lblCHK4").textContent="Completed";
                	document.getElementById("checkbox-4").disabled = true;
                	}
                	function disableCHK3()
                	{
                	document.getElementById("lblCHK5").textContent="Completed";
                	document.getElementById("checkbox-5").disabled = true;
                	}
                	
						
						
                </script>
  
</body>
</html>