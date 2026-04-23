<!DOCTYPE html>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<meta name="description" content="">
<meta name="author" content="Dashboard">


<title>YUVICARE</title>
<!-- Bootstrap core CSS -->

<!--  <link rel="stylesheet" href="common/Font-Awesome-master/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<script type="text/javascript" src="bloodbank/js/bloodbank.js"></script>
<script type="text/javascript">
    
    function goreferesh() {
  
       document.getElementById("searchText").value="";
       document.searchform.submit();
    }
    
    
    $(document).ready(function() {

		$("#last_donation_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		
	});
	
</script>


  	
<style>
.pnew{
    width: 3%;

}
.pview{
    width: 4%;

}
.page{
    width: 7%;
}
.mainheader {
    background-color: #43b9be !important;
}.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}

</style>


</head>
<body>

	<!-- **********************************************************************************************************************************************************
     MAIN CONTENT
     *********************************************************************************************************************************************************** -->
	<!--main content start-->
	
		<section>

			<!-- page start-->
						<div class="">
								
							<div class="row ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<a style="display: none;" href="#" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Blood
										Bank </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form action="searchdonorBloodbank" theme="simple" cssClass="form-inline search" name="searchform">
										
										  <div class="form-group">
										    <s:textfield name="searchText" id="searchText" cssClass="form-control" placeholder="Bank Name Search.."/>
										  </div>
										  <div class="form-group">
										  <select class="form-control" name="blood_group">
		                                        <option value="">Select Blood Group</option>
		                                        <option value="O+">O+ </option>
		                                        <option value="O-">O- </option>
		                                        <option value="A+">A+ </option>
		                                        <option value="A-">A- </option>
		                                        <option value="B+">B+ </option>
		                                        <option value="B-">B- </option>
		                                        <option value="AB+">AB+ </option>
		                                        <option value="AB-">AB- </option>
		                                    </select>
										  </div>
										 
										  <button type="submit" class="btn btn-primary" onclick="">Go</button>
										  <a href="#" onclick="" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" type="button" class="btn btn-primary" onclick="addbankRow('banktable')"><i class="fa fa-plus-circle"></i> Add Blood Bank</a>
										</s:form>
										</div>
										</div>
										<div class="" style="">
											<table class="table my-table xlstable table-bordered" id="banktable" style="width: 50%;">
                                        <thead>
                                            <tr>
                                                <th>Bank Name</th>
                                                <th>Address</th>
                                                <th>Mobile</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <s:iterator value="bloodBankList">
                                           <tr id="<s:property value="id"/>">
                                           		<td><s:property value="name"/></td>
                                           		<td><s:property value="address"/></td>
                                           		<td><s:property value="mobile"/></td>
                                           		<td><a href="#" onclick="editbankajax(<s:property value="id"/>)"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;<a href="deletebankBloodbank?id=<s:property value="id"/>" onclick="return cnfmDelete()" ><i class="fa fa-trash text-danger"></i></a></td>
                                           </tr>
                                           </s:iterator>
										</tbody>
                                    </table>
										</div>
								</div>
							</div>
						</div>
			<!-- page end-->
		</section>
	<!-- /MAIN CONTENT -->
	<!--main content end-->





   <s:form action="addbankBloodbank" id="donorform">
   
   			 <s:hidden  name="id" id="bid"/>
            <s:hidden  name="name" id="bname"/>
            <s:hidden  name="address" id="baddress"/>
            <s:hidden  name="mobile" id="bmobile"/>
            
   </s:form>






<!--Edit Modal-->
    <div class="modal fade" id="editbgroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Blood Group</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal" name="bloodgroupform">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Blood Group*</label>
                                <div class="col-sm-7">
                                    <select id="blood_group" class="form-control validate[required]" name="blood_group">
                                        <option value="">Select Blood Group</option>
                                        <option value="O+">O+ </option>
                                        <option value="O-">O- </option>
                                        <option value="A+" selected="selected">A+ </option>
                                        <option value="A-">A- </option>
                                        <option value="B+">B+ </option>
                                        <option value="B-">B- </option>
                                        <option value="AB+">AB+ </option>
                                        <option value="AB-">AB- </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">No of Bags*</label>
                                <div class="col-sm-7">
                                    <input id="no_bags" class="form-control validate[required] text-input" name="no_bags" type="text">
                                </div>
                            </div>

                          <input type="hidden" id="bid" name="blood_group_id" >
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addUpdateBloodGroup()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>

    <!--Add Donor Modal-->
    <div class="modal fade" id="addDonor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Blood Donor</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form name="blooddonor_form" action="" method="post" class="form-horizontal" id="blooddonor_form">
                            <input name="action" value="edit" type="hidden">
                            <input name="blooddonor_id" value="2" type="hidden">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="first_name">Full Name<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="name" class="form-control validate text-input"  name="name" type="text">
                                    <label id="errname" style="color:red"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="gender">Gender<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <label class="radio-inline">
                                        <input value="Male" class="tog validate[required]" name="gender" id="male" checked="checked" type="radio">Male
                                    </label>
                                    <label class="radio-inline">
                                        <input value="Female" class="tog validate[required]" name="gender" id="female" type="radio">Female
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="med_category_name">Age<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="age" class="form-control validate[required] text-input"  maxlength="3" name="age" type="text">
                                    <label id="errage" style="color:red"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label " for="phone">Phone<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="phone" class="form-control validate[,custom[phone]] text-input" maxlength="10" name="phone" type="text">
                                    <label id="errphone" style="color:red"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label " for="email">Email<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="email" class="form-control validate[required,custom[email]] text-input" name="email"  type="text">
                                    <label id="erremail" style="color:red"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="bloodgruop">Blood Group<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <select id="bg1" class="form-control validate[required]" name="blood_group">
                                        <option value="">Select Blood Group</option>
                                        <option value="O+">O+ </option>
                                        <option value="O-">O- </option>
                                        <option value="A+">A+ </option>
                                        <option value="A-">A- </option>
                                        <option value="B+">B+ </option>
                                        <option value="B-">B- </option>
                                        <option value="AB+">AB+ </option>
                                        <option value="AB-">AB- </option>
                                    </select>
                                    <label id="errbg1" style="color:red"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="last_donet_date">Last Donation Date</label>
                                <div class="col-sm-7">
                                    <input id="last_donation_date" class="form-control" name="last_donation_date" type="text">
                                    <label id="errlast_donation_date" style="color:red"></label>
                                </div>
                            </div>

                           <input type="hidden" id="id" name="id">
                            
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addUpdateBloodDonor()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>



	
	


	


</body>
</html>
