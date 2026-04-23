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


<link rel="icon" href="assets/img/favicon.ico">
<title>YUVICARE</title>
<!-- Bootstrap core CSS -->


<script type="text/javascript" src="bloodbank/js/bloodbank.js"></script>  
<script>
	//NProgress.start();
	
	function goreferesh() {
    
       document.getElementById("searchText").value="";
       document.location.reload();
    }
   
	
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
}
.titlepadright{
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
							<div class="">
								<div class="">
									<a style="display: none;" href="#" onclick="opencPopup('')" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Blood
										Bank </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form action="" theme="simple" cssClass="form-inline search">
										<div class="form-group titlepadright">
		                                    <label for="exampleInputEmail3">Blood Stock <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></label>
		                                </div>
										 <a href="#" class="btn btn-primary" onclick="addRow('bloodtable')"><i class="fa fa-plus-circle"></i> Add Blood Group</a>
										</s:form>
										</div>
										</div>
										<div class="col-lg-4 col-md-4 col-sm-4" style="padding-left:0px;">
											<table class="table my-table xlstable table-bordered" id="bloodtable" style="width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>Blood Group</th>
                                                <th>No of Bags</th>
                                                <th>Expiry Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <s:iterator value="bloodgroupList">
                                            <tr id="<s:property value="id"/>">
                                                <td><s:property value="blood_group"/></td>
                                                <td><s:property value="no_bags"/></td>
                                                <td><s:property value="expiry_date"/></td>
                                                <td><a href="#" onclick="editgroupajax(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>

                                            </tr>
                                            </s:iterator>
                                        
                                        </tbody>
                                   
                                    </table>
										</div>
										
								</div>
							</div>
						</div>


								<s:form action="addgroupBloodbank" id="tmpbloodform">
								 
								   <s:hidden name="blood_group" id="tblood_group"></s:hidden>
								   <s:hidden name="no_bags" id="tno_bags"></s:hidden>
								   <s:hidden name="expiry_date" id="texpiry_date"></s:hidden>
								   <s:hidden name="id" id="tid"></s:hidden>
								</s:form>	




					
			<!-- page end-->
		</section>

	<!-- /MAIN CONTENT -->
	<!--main content end-->


<!--Edit Modal-->
    <div class="modal fade" id="editbgroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
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
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Expiry Date*</label>
                                <div class="col-sm-7">
                                    <input class="form-control text-input" name="no_bags" type="text">
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
                                    <input id="name" class="form-control text-input"  name="name" type="text">
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
                                    <input id="age" class="form-control validate[required] text-input"  name="age" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label " for="phone">Phone<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="phone" class="form-control validate[,custom[phone]] text-input" name="phone" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label " for="email">Email<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="email" class="form-control validate[required,custom[email]] text-input" name="email"  type="text">
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
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="last_donet_date">Last Donation Date</label>
                                <div class="col-sm-7">
                                    <input id="last_donation_date" class="form-control  hasDatepicker" name="last_donation_date" type="text">
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
