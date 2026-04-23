<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->

<%@taglib prefix="s" uri="/struts-tags" %>
<html class="no-js" lang="">
<!--<![endif]-->



<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="_assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="payroll/js/department.js">
    
    </script>
   
   <script type="text/javascript">
   $(document).ready(function(){
 	  
       $("#date_format").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});  
		});
   </script>
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
            
        }
        .miheight {
            min-height: 650px;
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
            padding-top: 0px;
            text-align: center;
        }
       
    </style>


</head>





<body id="his" class="appWrapper sidebar-xs-forced">

    <div id="wrap" class="animsition">
        <!-- ===============================================
        ================= HEADER Content ===================
        ================================================ -->
        <!--/ HEADER Content  -->
        <!-- =================================================
        ================= CONTROLS Content ===================
        ================================================== -->
            
        <!-- ====================================================
        ================= CONTENT ===============================
        ===================================================== -->
        <div class="container-fluid" style="background-color: #efefef;">
					<a href="">
                        <h4><strong>Hospital Information</strong></h4>
                    </a>
                    </div> 
        
        <section id="">

            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <table width="100%">
                            <tbody>
                                <tr>
                                    <td width="60%" valign="top" align="center">
                                        <div>

                                            <table width="90%" border="0" cellspacing="0" cellpadding="0" valign="top" align="right" class="table">
                                                <tbody>
                                                    <tr class="title_tr"><td colspan="2" align="left" class="comtitle" style="background-color: #396 !important;">Hospital Details <a href="#" class="editcompany" onclick="editCompanyData()"><i class="fa fa-edit"></i></a></td></tr>
                                                    <tr>
                                                        <td class="borright"><b>Hospital Name</b></td>
                                                        <td><s:property value="company_name"/></td>
                                                    </tr>
                                                    <tr style="background:#f4f2eb;height:30px;">
                                                        <td class="borright" id="dateformat"><b>Date</b></td>
                                                        <td><s:property value="date_format"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright"><b>IT Month</b></td>
                                                        
                                                        <td>
                                                      <s:if test="itmonth==1">
                                                      January
                                                      </s:if><s:elseif test="itmonth==2">
                                                      February
                                                      </s:elseif><s:elseif test="itmonth==3">
                                                        March
                                                      </s:elseif><s:elseif test="itmonth==4">
                                                      April
                                                      </s:elseif><s:elseif test="itmonth==5">
                                                       May
                                                      </s:elseif><s:elseif test="itmonth==6">
                                                       June
                                                      </s:elseif><s:elseif test="itmonth==7">
                                                      July
                                                      </s:elseif><s:elseif test="itmonth==8">
                                                       August
                                                      </s:elseif><s:elseif test="itmonth==9">
                                                       September
                                                      </s:elseif><s:elseif test="itmonth==10">
                                                      October
                                                      </s:elseif><s:elseif test="itmonth==11">
                                                      November
                                                      </s:elseif><s:else >
                                                      December
                                                      </s:else>
                                                      
                                                      
                                                      
                                                        </td>
                                                        
                                                        
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright"><b>TIN No</b></td>
                                                        <td><s:property value="tinno"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright"><b>PAN No</b></td>
                                                        <td><s:property value="pan_no"/></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright"><b>ESI No</b></td>
                                                        <td><s:property value="esi_no"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright"><b>PF No</b></td>
                                                        <td><s:property value="pf_no"/></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright"><b>Hourly Type</b></td>
                                                        <td><s:property value="hourly_type"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright"><b>Fixed Hours</b></td>
                                                        <td><s:property value="fixed_hour"/></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright"><b>No. of Hours</b></td>
                                                        <td><s:property value="no_hours"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright"><b>OT Status</b></td>
                                                        <td><s:property value="ot_status"/></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright"><b>Permissions</b></td>
                                                        <td><s:property value="permissions"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright"><b>Permission Penalty</b></td>
                                                        <td><s:property value="permi_penalty"/></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright"><b>No. of Permissions</b></td>
                                                        <td><s:property value="no_permission"/></td>
                                                    </tr>
													<tr>
													<td>
													<a href="#" onclick="openPacsPopup('newpayrollPayrollDashBoard')">Test</a>
													</td>
													</tr>
                                                </tbody>
                                            </table>

                                        </div>
                                    </td>
                                    <!-- <td valign="top">

                                        <div align="center">
                                            <a href="#popupcompanylogo" style="text-decoration:none;">
                                                <img height="100px" src="payroll/images/uplaod_image.png">
                                                <br>
                                                <b>Change Logo</b>
                                            </a>
                                        </div>
                                        <div align="center">
                                            <img src="payroll/images/companylogo.png" class="img-responsive buildinglogo" />
                                        </div>

                                    </td> -->
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->






    </div>


    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
          <s:form cssClass="form-horizontal" action="updatePayrollDashBoard" theme="simple">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Hospital Details</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <div class="col-md-6 col-lg-6 col-xs-6">
                           
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">Hospital Name</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="company_name" class="form-control" id="company_name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-4 control-label">*IT Month</label>
                                    <div class="col-sm-8">
                                        <select name="itmonth" id="itmonth" class="selectstyle form-control">
                                            <option value="">Select Month</option>
                                            <option value="1">January</option>
                                            <option value="2">February</option>
                                            <option value="3" selected="selected">March</option>
                                            <option value="4">April</option>
                                            <option value="5">May</option>
                                            <option value="6">June</option>
                                            <option value="7">July</option>
                                            <option value="8">August</option>
                                            <option value="9">September</option>
                                            <option value="10">October</option>
                                            <option value="11">November</option>
                                            <option value="12">December</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">PAN No</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="pan_no" class="form-control" id="pan_no">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">PF No</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="pf_no" class="form-control" id="pf_no">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">*Fixed Hours</label>
                                    <div class="col-sm-8">
                                        <select name="fixed_hour" id="fixed_hour" class="selectstyle form-control">
                                            <option value="daily" selected="selected">Daily</option>
                                            <option value="weekly">Weekly</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">*OT Status</label>
                                    <div class="col-sm-8">
                                        <select name="ot_status" id="ot_status" class="selectstyle form-control">
                                            <option value="yes" selected="selected">Yes</option>
                                            <option value="no">No</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">*Permission Penalty</label>
                                    <div class="col-sm-8">
                                        <select name="permi_penalty" id="permi_penalty" class="selectstyle form-control">
                                            <option value="HL" selected="selected">Half Leave</option>
                                            <option value="FL">Full Leave</option>
                                        </select>
                                    </div>
                                </div>
                        </div>
                        <div class="col-md-6 col-lg-6 col-xs-6">
                                
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-4 control-label">Date</label>
                                    <div class="col-sm-8">
										<input type="text" name="date_format" class="form-control" id="date_format">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">TIN No</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="tinno" class="form-control" id="tinno">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">ESI No</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="esi_no" class="form-control" id="esi_no">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">*Hourly Type</label>
                                    <div class="col-sm-8">
                                        <select name="hourly_type" id="hourly_type" class="selectstyle form-control">
                                            <option value="fixed">Fixed</option>
                                            <option value="flexible" selected="selected">Flexible</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">*No. of Hours</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="no_hours" class="form-control" id="no_hours">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">*Permissions</label>
                                    <div class="col-sm-8">
                                        <select name="permissions" id="permissions" class="selectstyle form-control">
                                            <option value="1">One Hrs</option>
                                            <option value="2" selected="selected">Two Hrs</option>
                                            <option value="3">Three Hrs</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-4 control-label">*No. of Permissions</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="no_permission" class="form-control" id="no_permission">
                                    </div>
                                </div>

                              <s:hidden name="id" id="id"></s:hidden>
                        </div>
                    </div>
                  
                   
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-primary" value="Update"/>
                </div>
            </div>
             </s:form>
        </div>
    </div>
   

</body>
</html>
