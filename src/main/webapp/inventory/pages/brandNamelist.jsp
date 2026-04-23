<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->


<%@ taglib prefix="s" uri="/struts-tags" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <SCRIPT type="text/javascript" src="inventory/js/managebrand.js"></SCRIPT> 

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
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
            border-top: 0;
            min-height: auto !important;
        }
        .miheight {
            min-height: auto !important;
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
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 5px 7px 7px 7px !important;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
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
    </style>


    <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }.checkbox {
    position: relative;
    display: block;
    min-height: 13px;
    margin-top: 0px;
    margin-bottom: 0px;
}
.bgreen{
	    background-color: rgba(22, 160, 133, 0.12);
    padding: 10px 15px 15px 15px;
    min-height: 443px;
}
.list-group.no-border .list-group-item {
    border-width: 1px 0;
    border-left: 3px solid;
}
.list-group-item {
    position: relative;
    display: block;
    padding: 10px 11px;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid #ddd;
}
.rightborder{
	    
	    min-height: 450px;
}
.setback5{
	    background-color: rgba(0, 0, 0, 0.02);
    min-height: 450px;
        padding-top: 10px;
}
.checkbox-custom-alt > i {
    width: 16px;
    height: 16px;
    background-color: transparent;
    border: 2px solid #dfdfdf;
    margin-right: 6px;
    margin-left: -18px;
}
.checkbox-custom-alt input:checked + i:before {
    top: 1px;
    left: 1px;
    width: auto;
    height: auto;
    background-color: transparent;
    opacity: 1;
}
.bg-lightred {
    background-color: #e05d6f !important;
    color: white !important;
}
    </style>
</head>





<body id="his" class="appWrapper sidebar-xs-forced">

    
    
    <div class="col-lg-12 col-md-12 col-md-12">
    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;">
									<div class="row">
									
									<div class="col-lg-2 col-md-2 rightborder" style="padding-right:0px;">
										<div class="setovehe">
									  	<table class="table table-hover table-bordered"> 
									  	<thead> 
									  		<tr> 
									  			<th>Department</th> 
									  		</tr> 
									  	</thead>
									  		<tbody class="depart1"> 
									  			<tr> 
									  				<th>
									  					<label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all1"><i></i> All</label>
									  				</th> 
									  			</tr> 
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i> Accounts</label>
									  				</th>
									  				
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i> Administration</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i> Anaesthesia</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i> Assistant Surgeon</label>
									  				</th>
									  			</tr>
									  			
												
									  			
									  		</tbody> 
									  	</table>
									  	</div>
									</div>
									<div class="col-lg-2 col-md-2 rightborder" style="padding:0px;">
										<div class="setovehe">
									  	<table class="table table-hover table-bordered"> 
									  	<thead> 
									  		<tr> 
									  			<th>Designation</th> 
									  		</tr> 
									  	</thead>
									  		<tbody class="desig1"> 
									  			<tr> 
									  				<th>
									  					<label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all2"><i></i> All</label>
									  				</th> 
									  			</tr> 
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select2"><input type="checkbox"><i></i> Accountant</label>
									  				</th>
									  				
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select2"><input type="checkbox"><i></i> Accountant Assistant</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select2"><input type="checkbox"><i></i> Admin Assistant</label>
									  				</th>
									  			</tr>
									  			
												
									  			
									  		</tbody> 
									  	</table>
									  	</div>
									</div>
									<div class="col-lg-2 col-md-2 rightborder" style="padding:0px;">
									
											<div class="form-group hidden">
										<div class="col-lg-8 col-md-8" style="padding-left:0px;padding-right:0px;">
											 <label for="exampleInputFile">Import Contact</label>
									    <input type="file" name="userFile" value="">
									    <p class="help-block">File should be in .XLS, .CSV</p>
										</div>
										<div class="col-lg-4 col-md-4" style="padding-left:0px;padding-right:0px;">
										<div class="form-group">
											<button type="submit" class="btn btn-primary" style="margin-top: 24px;">Import</button>
										</div>
										</div>
									  </div>
									  <div class="form-group">
									  <div class="setovehe">
									  	<table class="table table-hover table-bordered"> 
									  	<thead> 
									  		<tr> 
									  			<th>Name</th> 
									  		</tr> 
									  	</thead>
									  		<tbody class="name1"> 
									  			<tr> 
									  				<th>
									  					<label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all3"><i></i> All</label>
									  				</th> 
									  			</tr> 
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3"><input type="checkbox"><i></i> Mr.Abhinav Parmar</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3"><input type="checkbox"><i></i> Mr.Jitendra Binzade</label>
									  				</th>
									  			</tr>
									  			
									  		</tbody> 
									  	</table>
									  	</div>
									  </div>
									
									
									<div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;margin-top:15px;">
										<div class="form-group">
									    <label for="exampleInputEmail1">Selected Emails</label>
									    <textarea name="numbers" cols="" rows="3" class="form-control"></textarea>
									  </div>
									</div>
									
									  
									  
									</div>
									<div class="col-lg-6 col-md-6 setback5">
									 <div class="form-group">
									    <label for="exampleInputEmail1">Select Template</label>
									    <select name="smsTemplate" class="form-control">
										    <option value="2">KIDNEY BIOPSY SAMPLE</option>
										    <option value="3">Reminder for Referral</option>
										    <option value="4">Advance reminder</option>
										    <option value="8">Happy Diwali</option>
										
										
										</select>
									  </div>
									  <div class="form-group">
									    <label for="exampleInputEmail1">Subject</label>
									    <input type="text" name="subject" value="" class="form-control" placeholder="Enter Subject">
									  </div>
									  <div class="form-group">
									  	<textarea name="smstxt" cols="" rows="10" class="form-control"></textarea>
									  </div>
									  
									  <button type="button" class="btn btn-primary pull-right" style="height: 45px !important;font-size: 16px;">Send</button>
									</div>
									</div>
									</div>
    	
    </div>
    
    
        <section id="">

            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck" style="width:40%;">
                        <div class="col-md-6">
                            Product Name
                        </div>
                        <div class="col-md-6 text-right">
                          
                        </div>
                    </div> <br /><br />
                    <div class="">
                        <table class="table my-table xlstable table-bordered" style="width: 40%;">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Supplier Name</th>
                                    <th>Product Name</th>
                                    <th>Edit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%int i=0; %>
                                 <s:iterator value="brandnameList">
                                <tr id="<s:property value="id"/>">
                                    <td><%=(++i) %></td>
                                    <td><s:property value="name"/></td>
                                    <td><s:property value="brand"/></td>
                                    <td><a href="#" onclick="editbrnd(<s:property value="id"/>,<%=i%>)"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;<a href="#" onclick="confirmdelete(<s:property value="id"/>)"><i class="fa fa-trash"></i></a></td>
                                </tr>
                                </s:iterator>
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="name"/></td>
                                    <td><input type="text" name="brand" id="brand"></td>
                                    <td><a href="#" onclick="addbrand()"><i class="fa fa-plus-square"></i></a></td>
                                </tr>
                            </tbody>

                              <s:hidden name="id" id="vendor"></s:hidden>
                        </table><br />
                        
                        
                    </div>

                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->

  <form action="viewvendorInventory" name="xyzname">
    <input type="hidden" name="id" />
  </form>


<script>
            $(window).load(function(){

                $('#select-all').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select input').prop('checked', false);
                    }
                });
                $('#select-all1').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select1 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select1 input').prop('checked', false);
                    }
                });
                $('#select-all2').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select2 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select2 input').prop('checked', false);
                    }
                });
                $('#select-all3').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select3 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select3 input').prop('checked', false);
                    }
                });

            });
        </script>
        
        <script>
				             $(function() {
								  $('.depart1').slimScroll({
								   		height : '425px',
								   		railVisible: true,
										alwaysVisible: true
								  });
								  $('.desig1').slimScroll({
								   		height : '425px',
								   		railVisible: true,
										alwaysVisible: true
								  });
								   $('.name1').slimScroll({
								   		height : '425px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>


</body>
</html>
