<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
</style>
<script>
	$(document).ready(function() {

		
		
		$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#toDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		
		
		
		$("#followupDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#sittingDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#ed_sittingDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
});	
	
	</script>
		
<script type="text/javascript" src="housekeeping/js/laundry.js"></script>
</head>
<body>

 <div class="">
       <div class="col-lg-12 col-md-12 col-sm-12 topback2">
                        <div class="col-md-12 martop6">
                            <form class="form-inline">
                            	<div class="form-group">
                                    <label for="exampleInputEmail3"> Allocate Duty -</label>
                                </div>
                               <%--  <div class="form-group">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                    <s:select list="linenList" cssClass="form-control" listKey="id" listValue="product_name" name="product_id" headerKey="0" headerValue="Select Linen"></s:select> 
                                </div> --%>
                                
                            <div class="form-group">
                              <label>From Date : </label>
								<s:textfield readonly="true" name="Fromdate" id="fromDate"
									cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
							</div>
							
							<div class="form-group">
                              <label>To Date : </label>
								<s:textfield readonly="true" name="Todate" id="toDate"
									cssClass="form-control" theme="simple" placeholder="To date"></s:textfield>
                           </div>
							
							<div class="form-group">
								<label>Search by Staff Name : </label>
								<s:textfield theme="simple" name="searchText" placeholder="Search By name"  cssClass="form-control" />
							</div>
                                
                                
                                
                                <button class="btn btn-primary">Go</button>
                                <button class="btn btn-primary hidden">View Reports</button>
                                <button title="print" class="btn btn-primary"><i class="fa fa-print"></i></button>
                                <!--  <a href="#" onclick="openPopup('Catalogue')" title="Add Asset" class="btn btn-primary">Allocate duty</a>
                                 <input onclick="addallocateduty()" type='button' value='Allocate duty' class='btn btn-primary' > -->
                                 <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#ireq">Allocate duty</a>
                            </form>
                        </div>
                        
                    </div>
                <div class="">
                   
                    <div class="col-lg-12">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>Sr</th>
                                    <th>Name</th>
                                    <th>Department</th>
                                    <th>Description</th>
                                    <th>Date_Time</th>
                                    <th>Edit</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                              <%int i=0; %>
                              <s:iterator value="Stafflist">
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="Name" /></td>
                                    
                                    <td><s:property value="Department"/></td>
                                    <!--
                                    <td><s:property value="min_delivery_time"/></td>
                                    --><td><s:property value="Description"/></td>
                                    <td><s:property value="DateTime"/></td>
                                    </td>
                                    <td><a href="#" onclick="editstaffdetails(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
                                    <td>
						              <s:if test="Isduty==1">
									      Accepted
							          </s:if></td>
<%--                                     <td><a href="deletestockProduct?selectedid=<s:property value="id"/>" onclick="return cnfmdelete()"><i class="fa fa-trash text-danger"></i></a></td>
 --%>                                </tr>
                               </s:iterator>
                            </tbody>
                        </table><br />
                        
                    </div>

                </div>
                
 <div id="ireq" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="modal-title">Sitting of <label id="patientnm"></label></h4>
        </div>
  <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      	
      	     <s:hidden id="sitting_Clientid"></s:hidden>
      	     <input type="hidden" id="dept_opdid">
             <input type="hidden" id="sitting_appointmentid">
             
             <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group" id="sittingDeptDiv">
	      			<label>Staff List </label><label class="text-danger">*</label>
	      			<s:select list="Userlist" name='name' id='staff' listKey="id"  listValue="name" headerKey="" headerValue="All staff"  cssClass="form-control" ></s:select>
      			</div>
      		</div>
      		
      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		 <div class="form-group">
                <s:select  cssClass="form-control" list="#{'Admin':'Admin', 'Opd':'Opd','Ward':'Ward','Washroom':'Washroom'}" headerKey="" headerValue="select department" name="filter_sortby" id="dept_staff" />
			</div>
		</div>	
                  
      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
      	 <div class="form-group"><label>Description</label>
			      <s:textarea  name="description"  id="description" cssClass="showToolTip form-control" 
					  title="description" placeholder="Enter description"/><br>
		   </div>
     </div>
      	</div>            
                   	
      	 
      <!-- 	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"> -->
      
             
     </div>
     <div class="modal-footer">
     	<button type="button" class="btn btn-primary"
							onclick="savestaff()" style="margin-top: 15px;">Save</button>
        </div>
      </div>
    </div>
  </div> 
  
  <div id="editstaff" class="modal fade" role="dialog">

	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Edit Staff</h4>

			</div>

   <div class="modal-body">
           <div class="row">
				 <div class="col-lg-12 col-md-12">
					  <div class="panel-body">
                           <s:hidden id="id"></s:hidden>

            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		      <div class="form-group">
                     <s:select  cssClass="form-control" list="#{'Admin':'Admin', 'Opd':'Opd','Ward':'Ward','Washroom':'Washroom'}" headerKey="" headerValue="select department" name="filter_sortby" id="edit_staff" />
			     </div>
		    </div>	
                  
      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
      	  <div class="form-group"><label>Description</label>
			      <s:textarea  name="description"  id="edit_description" cssClass="showToolTip form-control" 
					  title="description" placeholder="Enter description"/><br>
		   </div>
     </div>
									
					</div>
			 </div>

					  <div class="col-lg-3 col-md-2"></div>
			   </div>

				<div class="row">

					<div class="col-lg-3 col-md-2"></div>
                    <div class="col-lg-6 col-md-8"></div>
					<div class="col-lg-3 col-md-2"></div>

				</div>

				<s:token></s:token>
	</div>
                <div class="modal-footer">
                       <button type="button" class="btn btn-primary"
				               onclick="updatestaff()" style="margin-top: 15px;">Update</button>
				    
		         </div>
        </div>
	 </div>
   </div>
</div>
        <s:form action="assetHousekeepingdashboard" name="assetForm" id="assetForm" theme="simple">
							<div class="col-lg-12 col-md-12">
								<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
											Total:<label class="text-info"><s:property value="totalRecords" /></label>
								</div>
								<%@ include file="/common/pages/pagination.jsp"%>
						</div>
						</s:form> 
            
       </div>
        </section>
        <!--/ CONTENT -->
      


</body>
</html>