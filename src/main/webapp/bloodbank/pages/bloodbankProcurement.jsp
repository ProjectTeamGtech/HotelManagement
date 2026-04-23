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
            text-align: left;
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
			    padding: 0px 5px 0px 5px;
			    width: 93px;
			    position: absolute;
			    text-align: center;
        }
        .baksetp1{
	        	background-color: #16a085;
			    color: #fff;
			    padding: 0px 5px 0px 5px;
			    width: 93px;
			    position: absolute;
			    text-align: center;
        }
        .baksetp2{
	        	background-color: #daae71;
			    color: #fff;
			    padding: 0px 5px 0px 5px;
			    width: 93px;
			    position: absolute;
			    text-align: center;
        }
        .hest{height: 20px !important;}
    </style>
</head>
<body>

<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-9">
                        <div class="form-inline">
                              <a class="btn btn-primary hidden" href="#" data-toggle="modal" data-target="#cpo">Create PO</a>
                              <a class="btn btn-primary" href="#" onclick="openPopup('purorderProcurement')" >Purchase Order</a>
						</div>
						 
                        </div>
                        <div class="col-md-3 text-right">
                        
                              
                                <a id="CPHcontent_HyperLink2" class="btn btn-primary hidden" href="completeProcurement">Completed PO</a>
                                <a id="CPHcontent_HyperLink3" class="btn btn-primary hidden" href="PoPayment">Payment PO</a>
                        </div>
                    </div> 
                    
                    <div class="col-lg-12 col-xs-12 col-md-12">
                        <table class="table my-table xlstable table-bordered" id="tablesort" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 3%;">Sr.No</th>
                                    <th style="width: 3%;">PO.No</th>
                                    <th style="width: 6%;">PO Date</th>
                                    <th style="width: 8%;">Tin No</th>
                                    <th style="width: 60%;">Supplier Name</th>
                                    <th style="width: 6%;text-align:right;	">Quantity</th>
                                    <th style="width: 8%;"></th>
                                    <th>Edit</th>
                                    
                                    
                                    
                                </tr>
                            </thead>
                            <tbody>
                                
                                <tr>
                                    <td>01</td>
                                    <td>452SS</td>
                                    <td>23/05/2017</td>
                                    <td>5456ASDF</td>
                                    <td>ABC Blood Supplier</td>
                                    <td>20</td>
                                    <td><a class="baksetp1" style="color:#fff;" href="#" onclick="#">Goods Received</a></td>
                                    <td>
                                    	<a href="#"><i class="fa fa-pencil" style="color:#d9534f;" aria-hidden="true"></i></a>
                                    </td>
                                </tr>
							                        
                            </tbody>

                        </table><br />
                        
                        
                    </div>

</body>
</html>