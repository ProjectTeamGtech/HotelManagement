<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->
<%@ taglib prefix="s" uri="/struts-tags"%>


<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>YUVICARE</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>

<style>
.padright {
	padding-left: 40px;
}

.table.table {
	color: RGBA(85, 85, 85, 0.85);
	background-color: #fff;
}

.comtitle {
	font-size: 13px;
	background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
	color: rgb(255, 255, 255);
}

.marbot25 {
	margin-bottom: 25px;
}

.editcompany {
	float: right;
	font-size: 17px;
	color: #fff;
}

.borright {
	border-right: 1px dashed rgb(192, 192, 192);
}

.buildinglogo {
	width: 60%;
	margin-top: 30px;
}

#sidebar .panel-group .panel>.panel-heading+.panel-collapse>.panel-body
	{
	border-top: 0;
}

.miheight {
	min-height: 650px;
}

.my-table th {
	background-color: #424A5D;
	color: #fff !important;
	border-bottom: 1px solid #DFD8D4;
	border-right: 1px solid #DFD8D4;
	border-top: 1px solid #DFD8D4;
	padding: 3px 3px 4px 5px;
	text-align: left;
	font-weight: bold;
	font-size: 11px;
	background-size: 100% 100%;
}

.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
	.table>thead>tr>td, .table>thead>tr>th {
	padding: 5px 7px 7px 7px !important;
}

.sidebar-xs #header .branding>a {
	background-position: 6px 10px;
	width: 100% !important;
	font-size: 21px;
	padding: 0px 1px 2px 15px;
	text-align: center;
	color: #fff;
}

.sidebar-xs #header .branding>a>span {
	display: inline-block;
}

.sidebar-xs #header .branding {
	width: 100%;
	padding-top: 7px;
	text-align: center;
}

.theight {
	height: 21px;
}
.thcolor{
background-color: #339966 !important;
}
</style>


<SCRIPT type="text/javascript">
	function sortBranchIncrement(id) {

		document.getElementById("b1").value = id;
		document.branchform.submit();
	}
</SCRIPT>




</head>








<body id="his" class="appWrapper sidebar-xs-forced">

	<section id="">

		<div class="container-fluid">
			<a>
				<h3 style="color: #339966;">
					<strong>SALARY DECIDER</strong>
				</h3>
			</a>
		</div>


		<div class="page page-sidebar-xs-layout">
			<div class="col-lg-4">

				<s:form action="Payrollincrement">
				<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-lg-8 col-md-8 col-sm-8" style="margin-left: -27px">
				<s:textfield theme="simple" name="searchtext"
						placeholder="Search Employee By Name" cssClass="form-control"></s:textfield>
					
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4" >
				<input type="submit" value="Go" class="btn btn-primary" style="background-color: #339966;">
				</div>
				</div>
						
				</s:form>
			</div>
			

			<div class="pageheader">
				<div class="col-lg-12 col-md-12 col-sm-12 marbot25">
					<%--   <div class="">
                            
                                            <b>Branch Name :</b>
                                            <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" listValue="branch" onchange="sortBranchIncrement(this.value)" cssClass="selectstyle">
                                           </s:select>
                                       
                        </div> --%>



					<br />



					<table class="table my-table xlstable table-bordered"
						style="width: 60%;">
						<thead>
							<tr >
								<th class="thcolor">Sr. No.</th>
								<th class="thcolor">Employee Name</th>
								<th class="thcolor">Basic</th>
								<th class="thcolor">Department</th>
								<th class="thcolor">Last Modified Date</th>
							</tr>
						</thead>
						<tbody>
							<%
								int i = 0;
							%>
							<s:iterator value="incrementlist">

								<tr>
									<td><%=(++i)%></td>
									<td><a style="color: #339966"
										href="editPayrollincrement?selectedid=<s:property value="emp_id"/>"
										title="view Increament"><s:property value="emp_name" /></a></td>
									<td><s:property value="basic" /></td>
									<td><s:property value="department"/></td>
									<td><s:property value="incre_date" /></td>
								</tr>
							</s:iterator>
						</tbody>

					</table>

				</div>
			</div>

			<form action="Payrollincrement" name="branchform">

				<input type="hidden" name="branch_id" id="b1">
			</form>

		</div>

	</section>
	<!--/ CONTENT -->








	<!--Edit Modal-->
	<!-- Modal -->
	<div class="modal fade" id="adddeduc" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Deduction Details</h4>
				</div>
				<div class="modal-body row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-5 control-label">Name</label>
								<div class="col-sm-7">
									<input type="email" class="form-control" id="inputEmail3">
									<small>(Enter PF or ESI or others)</small>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-5 control-label">Deduction
									Type</label>
								<div class="col-sm-7">
									<select name="otstatus" class="selectstyle form-control">
										<option value="Percentage">Percentage</option>
										<option value="Fixed">Fixed</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-5 control-label">Amount</label>
								<div class="col-sm-7">
									<input type="email" class="form-control" id="inputEmail3">
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Add/Edit</button>
				</div>
			</div>
		</div>
	</div>




</body>
</html>
