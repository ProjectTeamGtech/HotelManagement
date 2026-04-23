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
}.tableback{
background-color: #4E7894;
}
.table-bordered>thead>tr>td {
    background-color: #fff;
    color: #fff;
    font-size: 12px;
    /* border-top: 1px solid #00dec8 !important; */
}
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

 <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>





<script type="text/javascript" src="housekeeping/js/laundry.js"></script>

</head>
<body>
<div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topback2">
                        <div class="col-md-12 martop6">
                            <s:form cssClass="form-inline" action="laundryHousekeepingdashboard" theme="simple">
                            	<div class="form-group titlepadright">
                                    <label for="exampleInputEmail3">Laundry Management -</label>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                    <s:select list="linenList" listKey="id" listValue="product_name" name="product_id" headerKey="0" headerValue="Select Linen" cssClass="form-control" />
                              
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                    <select class="form-control">
							           <option>Select Inhouse</option>
							           <option>Yes</option>
							           <option>No</option>
							         </select>
                                </div>
                                <div class="form-group hidden">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                        <s:select list="userstaffList" listKey="id" listValue="fullname" name="staff_id" headerKey="0" headerValue="Select Staff" cssClass="form-control" />
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <input type="text" class="form-control" id="fromdate" name="fromdate" placeholder="Date From">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <input type="text"  class="form-control" id="todate" name="todate" placeholder="Date To">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                    <select class="form-control">
						           <option>Select Status</option>
						           <option>Open</option>
						           <option>Confirmed</option>
						         </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Go</button>
                                <button  class="btn btn-primary hidden">View Reports</button>
                                <button title="print" class="btn btn-primary"><i class="fa fa-print"></i></button>
                                <a href="#" title="print" onclick="addRow('tableLaundry')" class="btn btn-primary">Add Linen</a>
                            </s:form>
                        </div>
                        
                        
                    </div> 
                    <div class="">
                        <div class=" col-lg-12 col-md-12 col-sm-12">
                            <table class="table table-bordered" cellspacing="0" id="tableLaundry" width="100%" style="width:100%;">
                                <thead>
                                    <tr class="tableback">
                                        <th style="width: 8%;">Linen</th>
                                        <th style="width: 5%;">Qty</th>
                                        <th style="width: 7%;">Inhouse</th>
                                        <th style="width: 20%;">Service Provider / Contact No / Email</th>
                                        <th style="width: 11%;">Delivered / Collected By</th>
                                        <th>Collection Date / Time</th>
                                        <th>Processing Linen</th>
                                        <th style="width: 17%;">Note</th>
                                        <th style="width: 5%;">Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody> 
                                  <s:iterator value="laundryList">
                                        <tr id="<s:property value="id"/>">
                                        <td><s:property value="product_name"/></td>
                                        <s:if test="status==1">
                                        <td><s:property value="stock"/> / <s:property value="quantity"/> </td>
                                        </s:if>
                                        <s:else>
                                           <td><s:property value="stock"/> / <s:property value="quantity"/> </td>
                                        </s:else>
                                        <td><s:property value="inhouse"/></td>
                                        <s:if test="inhouse=='No'">
                                        <td><s:property value="vendor"/> <br> <s:property value="contact"/> / <s:property value="email"/></td>
                                        </s:if>
                                        <s:else>
                                          <td></td>
                                        </s:else>
                                        <td> <s:property value="staffname"/><br> <s:property value="collect_by"/></td>
                                        <s:if test="status==1">
                                        <td><s:property value="collected_date"/> / <s:property value="time"/></td>
                                        </s:if>
                                        <s:else>
                                           <td>Processing</td>
                                        </s:else>
                                        <td><s:property value="processing" /></td>
                                        <td><s:property value="notes"/></td>
                                        <s:if test="status==0">
                                          <td>Open</td>
                                        </s:if>
                                        <s:else>
                                           <td>Completed</td>
                                        </s:else>                                        
                                        <s:if test="status==0">
                                           <td><a href="#" onclick="editLinen('<s:property value="id"/>')" class="btn btn-primary">Complete</a></td>
                                         </s:if>
                                         <s:else>
                                            <td ><button onclick="editQuantitylinen('<s:property value="id"/>')" disabled="disabled" class="btn btn-primary">Update</button></td> 
                                        </s:else>
                                        </tr>
                                    </s:iterator>           
                                      
                                </tbody>
                            </table>
                        </div>
                         
                         
                        
                    </div>
                    <br>
                     
                        <s:form action="laundryHousekeepingdashboard" name="housekeepingForm" id="housekeepingForm"
								theme="simple">
							<div class="col-lg-12 col-md-12" style="margin-top:15px;">
								<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
									Total:<label class="text-info"><s:property value="totalRecords" /></label>
								</div>
								<%@ include file="/common/pages/pagination.jsp"%>
						</div>

						</s:form> 
                     
                     
                    
                </div>
                
                
                
                
                
<!-- Lienen Modal -->
<div class="modal fade" id="linen" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Add Linen</h4>
      </div>
      <div class="modal-body">
        <div class="row">
        <div class="col-lg-12 col-md-12">
        	<div>
        	<div class="col-lg-4 col-md-4">
        	<div class="form-group">
			    <label for="exampleInputEmail1">Select Floor</label>
			    <select class="form-control">
                                        <option>Select</option>
                                        <option>Floor 1</option>
                                        <option>Floor 2</option>
                                        <option>Floor 3</option>
                                        <option>Floor 4</option>
                                    </select>
			  </div>
        	</div>
        	<div class="col-lg-4 col-md-4">
        	<div class="form-group">
			    <label for="exampleInputPassword1">Select Ward</label>
			    <select class="form-control">
                                        <option>Select</option>
                                        <option>ward 1</option>
                                        <option>ward 2</option>
                                    </select>
			  </div>
        	</div>
        	<div class="col-lg-4 col-md-4"></div>
		</div>
        </div>
        </div>
        <div class="row">
        <div class="col-lg-12 col-md-12" style="margin-bottom: 5px;">
        	<div>
        	<div class="col-lg-8 col-md-8">
        	<form class="form-inline">
        	<div class="form-group">
			    <label class="sr-only" for="exampleInputEmail1">Select linen</label>
			    <select class="form-control">
                                        <option>Select Linen</option>
                                        <option selected>Pillow</option>
                                        <option>Bed Sheet</option>
                                        <option>Curtain</option>
                                    </select>
			  </div>
			  <div class="form-group">
			    <label class="sr-only" for="exampleInputAmount">Write Qty</label>
			    <div class="input-group">
			      <input type="text" class="form-control" id="exampleInputAmount" value="2" placeholder="Write Qty">
			    </div>
			  </div>
			  <button type="submit" class="btn btn-primary"><i class="fa fa-minus"></i></button>
			</form>
        	</div>
        	<div class="col-lg-4 col-md-4"></div>
		</div>
        </div>
        </div>
        <div class="row">
        <div class="col-lg-12 col-md-12">
        	<div>
        	<div class="col-lg-8 col-md-8">
        	<form class="form-inline">
        	<div class="form-group">
			    <label class="sr-only" for="exampleInputEmail1">Select linen</label>
			    <select class="form-control">
                                        <option>Select Linen</option>
                                        <option>Pillow</option>
                                        <option>Bed Sheet</option>
                                        <option>Curtain</option>
                                    </select>
			  </div>
			  <div class="form-group">
			    <label class="sr-only" for="exampleInputAmount">Write Qty</label>
			    <div class="input-group">
			      <input type="text" class="form-control" id="exampleInputAmount" placeholder="Write Qty">
			    </div>
			  </div>
			  <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i></button>
			</form>
        	</div>
        	<div class="col-lg-4 col-md-4"></div>
		</div>
        </div>
        </div>
        
        <div class="row" style="margin-top: 15px;">
        <div class="col-lg-12 col-md-12">
        	<div>
        	<div class="col-lg-4 col-md-4">
        	<div class="form-group">
			    <label for="exampleInputEmail1">Select Staff</label>
			    <select class="form-control">
                                        <option>Select</option>
                                        <option>Aruna Waghe</option>
                                        <option>Nilesh Sahu</option>
                                        <option>Dilip Verma</option>
                                    </select>
			  </div>
        	</div>
        	<div class="col-lg-4 col-md-4">
        	<div class="form-group">
        	 <label for="exampleInputEmail1">Alot linen to</label>
			    <input class="form-control" type="text" placeholder="Alot linen to">
			  </div>
        	</div>
        	<div class="col-lg-4 col-md-4">
        	<div class="checkbox" style="margin-top: 24px;">
	        <label>
	          <input type="checkbox"> Checked for Current Date and Time
	        </label>
	      </div>
        	</div>
		</div>
        </div>
        </div>
        <div class="row">
        <div class="col-lg-12 col-md-12">
        	<div>
        	<div class="col-lg-4 col-md-4">
        	<div class="form-group">
			    <label for="exampleInputEmail1">Expected Date</label>
			  <input class="form-control" type="text" placeholder="expected date">
			  </div>
        	</div>
        	<div class="col-lg-4 col-md-4">
        	<div class="form-group">
        	 <label for="exampleInputEmail1">Collected By</label>
			    <input class="form-control" type="text" placeholder="collected by">
			  </div>
        	</div>
        	<div class="col-lg-4 col-md-4">
        	<div class="checkbox" style="margin-top: 24px;">
	        <label>
	          <input type="checkbox"> Checked for Current Date and Time
	        </label>
	      </div>
        	</div>
		</div>
        </div>
        </div>
        <div class="row">
        <div class="col-lg-12 col-md-12">
        	<div>
        	<div class="col-lg-8 col-md-8">
        	<div class="form-group">
			    <label for="exampleInputEmail1">Write Note</label>
			  <textarea class="form-control" rows="3" placeholder="write note"></textarea>
			  </div>
        	</div>
        	
        	<div class="col-lg-4 col-md-4">
        	
        	</div>
		</div>
        </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>
                
</body>
</html>