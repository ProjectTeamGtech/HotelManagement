<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
           
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 1px 7px 1px 7px !important;
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
        }
        .addcatego {
            float: right;
            margin-top: -40px;
            margin-right: 30px;
        }
        .baksetp{
        	    background-color: #616f77;
			    color: #fff;
			    padding: 1px 5px 1px 5px;
			    text-align: center;
        }
    </style>
</head>
<body>
   <div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;border-right: 1px solid #efefef;min-height: 550px;">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-12">
                            <form class="form-inline">
						   <div class="form-group hidden">
						  	<select name="" class="form-control chosen-select">
							    <option value="0">Select Days</option>
							    <option value="1">30 Days</option>
							    <option value="2">45 Days</option>
							    <option value="2">60 Days</option>
							</select>
						  </div>
						  <div class="form-group hidden">
						  	<select name="" class="form-control chosen-select">
							    <option value="0">Before</option>
							    <option value="1">After</option>
							</select>
						  </div>
								  
								  <div class="form-group">
								    <label class="sr-only" for="pwd">Password:</label>
								    <input type="text" class="form-control" placeholder="Date">
								  </div>
								  <button type="submit" class="btn btn-primary">Go</button>
								</form>
                        </div>
                    </div> 
                    <div class="col-lg-12 col-xs-12 col-md-12">
                    <table class="table my-table xlstable table-bordered" id="tablesort" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 2%;">No</th>
                                    <th style="width: 6%;">Last Date</th>
                                    <th style="width: 20%;">Supplier Name</th>
                                    <th style="text-align:right;">Order Amount</th>
                                    <th style="text-align:right;">Amount Paid</th>
                                    <th style="text-align:right;">Balace Amount</th>
                                    <th></th>
                                </tr>
                            </thead>
                            
                            <tbody>
                                <tr>
                                    <td>65</td>
                                    <td>21/05/2017</td>
                                    <td>ABC Blood Supplier</td>
                                    <td style="text-align:right">Rs.50000</td>
                                    <td style="text-align:right;color:green;">Rs.20000</td>
                                    <td style="text-align:right;color:red;">Rs.10000</td>
                                    <td><a href="#" class="baksetp" style="color:#fff;" onclick="#">Payment and History</a></td>
                                </tr>
                            </tbody>

                        </table>
                    </div>
                       
                        
                        
                    </div>
</body>
</html>