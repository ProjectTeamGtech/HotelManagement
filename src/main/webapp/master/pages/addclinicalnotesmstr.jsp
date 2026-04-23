<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Add Clinical notes</h4>

									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<!-- 	<div class="col-lg-1 col-md-1">
			<a class="btn btn-primary" href="#" onclick="opencPopup('addclinicalnotesmstrMaster')"><i
				class="fa fa-plus"></i> Add</a>
		</div> -->
								</div>
								
								<s:form action="saveclinicalnotesmstrMaster">
								
								<label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name"/>
					
							
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<table>
							<tr>
								<td><br><s:submit cssClass=" showToolTip form-control btn btn-primary"></s:submit></td>
							</tr>	
							</table>
							</div>		
								<br>
								</s:form>
							
								</div>
</div>