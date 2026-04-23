<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>



<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Edit Clinical Intervation</h4>

									</div>
								</div>
							
					
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<!-- 	<div class="col-lg-1 col-md-1">
			<a class="btn btn-primary" href="#" onclick="opencPopup('addclinicalnotesmstrMaster')"><i
				class="fa fa-plus"></i> Add</a>
		</div> -->
								</div>
								
								<s:form action="updateclinicalIntervationMaster">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								<label>Name</label>
									<%-- <s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name"></s:textfield> --%>
					<input type="text" name="name" class="showToolTip form-control" value='<s:property value="name"/>'>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								<label>clinical problem</label>
								<s:select name="parentid" list="clinicalproblemlist" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="All " theme="simple"  ></s:select>
					</div>	
					
						<s:hidden name="id"></s:hidden>
					<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">	
						<br>
					<input type="submit" class="  btn btn-primary">
					</div>			
					</div>
								</s:form>
						
							
							
						
							
							
							
							
								
							
							
							
					