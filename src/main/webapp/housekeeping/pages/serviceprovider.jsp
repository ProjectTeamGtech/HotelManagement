<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<SCRIPT type="text/javascript" src="housekeeping/js/addmachine.js"></SCRIPT>

<style>
.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
.table-bordered>thead>tr>td {
    background-color: #fff; }
</style>


<SCRIPT type="text/javascript">

      $(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		
	});     

</SCRIPT>

</head>
<body>
<div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topback2">
                        <div class="col-md-12 martop6">
                            <s:form cssClass="form-inline" theme="simple" action="housekeepingserviceproviderHousekeepingdashboard">
                            	<div class="form-group titlepadright">
                                    <label for="exampleInputEmail3">Equipment Maintenance - </label>
                                </div>
                                
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <input type="text" class="form-control" name="fromdate" id="fromdate" placeholder="From Date">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <input type="text" class="form-control" name="todate" id="todate" placeholder="To Date">
                                </div>
                                <button type="submit" class="btn btn-primary">Go</button>
                                <button title="print" class="btn btn-primary"><i class="fa fa-print"></i></button>
                                <a type="button" href="#" onclick="addRow('machinetable')" class="btn btn-primary">Add Machine</a>
                            </s:form>
                        </div>
                    </div> 
                    
                    <div class="">
                        <div class=" col-lg-12 col-md-12 col-sm-12">
                            <table class="table table-bordered" cellspacing="0" width="100%" id="machinetable" style="width:100%;">
                                <thead>
                                    <tr class="tableback">
                                        <th>Equipment Type</th>
                                        <th>Brand</th>
                                        <th>Equipment Name</th>
                                        <th>Service Provider Name/ Contact No/ Email</th>
                                        <th>Service Frequency</th>
                                        <th>Warranty</th>
                                        <th>Service Due Date</th>
                                        <th>Reminder on</th>
                                        <th>Complain</th>
                                        <th>History</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                   <s:iterator value="serviceproviderList">
                                       <tr id="<s:property value="id"/>">
                                        <td><s:property value="equipment"/></td>
                                        <td><s:property value="brand"/></td>
                                        <td><s:property value="machine_name"/></td>
                                        <td><s:property value="name"/><br><s:property value="contact"/>, <s:property value="email"/></td>
                                        <td><s:property value="frequency"/></td>
                                        <td>10 Year</td>
                                        <td><s:property value="due_date"/></td>
                                        <td><s:property value="remainder_on"/></td>
                                        <td>Complain on 4/10/2016</td>
                                        <td><a href="#" onclick="getmachineservice('<s:property value="id"/>')">View</a></td>
                                        <td><a href="#" onclick="editMachine('<s:property value="id"/>')" class="btn btn-primary">Edit</a></td>
                                    </tr>
                                   </s:iterator>
                                     
                                    
                                    <!--<s:iterator value="serviceproviderList">
                                    <tr id="<s:property value="id"/>">
                                        <td><s:property value="name"/></td>
                                        <td><s:property value="company_name"/></td>
                                        <td><s:property value="phone1"/></td>
                                        <td><s:property value="address"/></td>
                                        <td><s:property value="sheduled"/></td>
                                        <td><s:property value="lastmaintenance"/></td>
                                        <td><s:property value="notes"/></td>
                                        <td><a href="#" onclick="editMachine(<s:property value="id"/>)" class="btn btn-primary">Edit</a></td>
                                    </tr>
                                    </s:iterator>
                                    --><!--
                                    <tr>
                                        <td>Machine 1</td>
                                        <td>Varun Dhawan</td>
                                        <td>+91-8565452352</td>
                                        <td>Flat no.202, Gaurav Apartment, behind tekadi, Nagpur</td>
                                        <td>Monthly</td>
                                        <td>15 Aug 2016</td>
                                        <td></td>
                                        <td><a href="" class="btn btn-primary">Edit</a></td>
                                    </tr>
                                --></tbody>
                            </table>
                        </div>
                    </div>
                    <s:form action="housekeepingserviceproviderHousekeepingdashboard" name="housekeepingserviceproviderForm" id="housekeepingserviceproviderForm"
								theme="simple">
							<div class="col-lg-12 col-md-12" style="margin-top:15px;">
								<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
											Total:<label class="text-info"><s:property value="totalRecords" />
									</label>
								</div>
								<%@ include file="/common/pages/pagination.jsp"%>
						</div>
						</s:form> 
                </div>
                
                <!-- View History -->
				<div class="modal fade" id="history" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="myModalLabel">Equipment Maintenance History Details</h4>
				      </div>
				      <div class="modal-body" style="min-height: 500px;">
				        <table class="table table-bordered" cellspacing="0" width="100%" id="machinetable" style="width:100%;">
                                <thead>
                                    <tr class="tableback">
                                    	<th>Sr.No</th>
                                    	<th>Complain</th>
                                        <th>Complain Date</th>
                                        <th>Note</th>
                                        <th>Resolve Date</th>
                                        <th>Status</th>
                                        <th>SMS/Email</th>
                                        <th>Edit</th>
                                    </tr>
                                </thead>
                                <tbody id='tbodyservice'>
                                    <!--<tr>
                                        <td>10/09/2015 </td>
                                        <td>Replaced part 7654, completed end to end cleanup</td>
                                        <td>Completed </td>
                                        <td><i class="fa fa-edit" class="disabled"></i></td>
                                    </tr>
                                    <tr>
                                        <td>16/09/2016 </td>
                                        <td>Oiled front and back for smooth functionin</td>
                                        <td>Completed </td>
                                        <td><i class="fa fa-edit" class="disabled"></i></td>
                                    </tr>
                                     <tr>
                                        <td>10/09/2016 </td>
                                        <td>Write Note</td>
                                        <td>Open </td>
                                         <td><a href="#"><i class="fa fa-edit"></i></a></td>
                                    </tr>
                                     <tr>
                                        <td><input type="text" class="form-control" value="10/09/2016"></td>
                                        <td><input type="text" class="form-control" placeholder="Enter Note"></td>
                                        <td>
                                        		<select class="form-control">
												  <option>Select</option>
												  <option>Open</option>
												  <option>Completed</option>
												</select>
                                        </td>
                                        <td><a href="#"><i class="fa fa-edit"></i></a></td>
                                    </tr>
                                   --></tbody>
                            </table>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				      </div>
				    </div>
				  </div>
				</div>
                
</body>
</html>