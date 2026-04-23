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
        .vertical {
        	list-style: none;
		    padding: 7px;
		    border: 1px solid #efefef;
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
       
    </style>
</head>
<body>



										<div class="col-lg-12 col-md-12 topback2">
										<div class="form-inline">
										   <div class="form-group">
										    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="Search Suplier">
										  </div>
										   <button type="submit" class="btn btn-primary" onclick="searchDonor()">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" class="btn btn-primary pull-right" data-toggle="modal" data-target="#addsupp" title="Add Supplier" >Add Supplier</a>
										</div>
										</div>


<div class="col-lg-12 col-md-12 col-xs-12">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;" id="tablesort">
                            <thead>
                                <tr>
                                	<th width="width: 2%;">Sr.No</th>
                                    <th>Tin No</th>
                                    <th>Supplier Name</th>
                                    <th style="width: 40%;">Address</th>
                                    <th>State</th>
                                    <th>City</th>
                                    <th>Email</th>
                                    <th class="hidden">Phone No</th>
                                    <th>Mobile No</th>
                                    <th class="hidden">Product Name</th>
                                    <th>Delivery Time</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                               
                                <tr>
                                    
                                </tr>
                                
                            </tbody>

                        </table><br>
                        
                    </div>
                    
                    
                    <!-- Modal -->
<div id="addsupp" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add Supplier</h4>
      </div>
      <div class="modal-body">
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;padding-top: 15px;">
       		<div class="col-lg-2 col-md-2">
       			<div class="form-group">
				    <label for="email">TIN No</label><span style="color:red">*</span>
				    <input type="text" class="form-control" name="tinno" id="tinno">
				  </div>
       		</div>
       		
       		<div class="col-lg-4 col-md-4">
       			<div class="form-group">
				    <label for="email">Supplier Name</label>
				    <input type="text" class="form-control" name="name" id="name">
				  </div>
       		</div>
       		<div class="col-lg-2 col-md-2">
       			<div class="form-group">
				    <label for="email">Email</label>
				    <input type="text" class="form-control" name="email" id="email">
				  </div>
       		</div>
       		<div class="col-lg-2 col-md-2">
       			<div class="form-group">
				    <label for="email">Phone No</label>
				    <input type="text" class="form-control" name="mobile_pri" id="phone">
				  </div>
       		</div>
       		<div class="col-lg-2 col-md-2">
       			<div class="form-group">
				    <label for="email">Delivery Time</label>
				     <select class="form-control chosen" name="min_delivery_time" id="min_delivery">
												    <option value="0">Select Day</option>
												    <option value="1 Day">1 Day</option>
												    <option value="2 Day">2 Day</option>
												    <option value="3 Day">3 Day</option>
												    <option value="4 Day">4 Day</option>
												    <option value="5 Day">5 Day</option>
												</select>
				  </div>
       		</div>
       		
       </div>
       <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-bottom: 15px;">
       		<div class="col-lg-6 col-md-6">
       			<div class="form-group">
				    <label for="email">Address</label>
				    <input type="text" class="form-control" name="address" id="address">
				  </div>
       		</div>
       		<div class="col-lg-2 col-md-2">
       			<div class="form-group">
				    <label for="email">State</label>
				   <select name="state" id="state" class="form-control chosen" onchange="getCities(this.value)">
    <option value="0">Select State</option>
    <option value="1">Maharashtra</option>
    <option value="2">MadhyaPradesh</option>
    <option value="3">Uttar Pradesh</option>


</select>
												    
				  </div>
       		</div>
       		<div class="col-lg-2 col-md-2">
       			<div class="form-group" id="citydiv">
				    <label for="email">City</label>
				    <select name="city" id="city" class="form-control chosen">
    <option value="0">Select City</option>
    <option value="1">Nagpur</option>
    <option value="2">Akola</option>
    <option value="3">Amaravati</option>
    <option value="4">Pune</option>
    <option value="5">Mumbai</option>
    <option value="6">Bhopal</option>
    <option value="7">Indore</option>
    <option value="8">Jabalpur</option>
    <option value="9">Lucknow</option>
    <option value="10">Varanasi</option>


</select>
												   
				  </div>
       		</div>
       		<div class="col-lg-2 col-md-2">
       			<div class="form-group">
				    <label for="email">Post Code</label>
				    <input type="text" class="form-control" name="postcode" id="postcode">
				  </div>
       		</div>
       		
       		
       		
       </div>
       <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-bottom: 15px;">
       		
       		
       		
       		<div class="col-lg-6 col-md-6 " style="padding-top: 15px;">
       		
       		<div class="col-lg-12 col-md-12 col-sm-12 hidden" style="padding:0px;">
       			<p>Bank Details</p>
       			<div class="col-mg-6 col-lg-6" style="padding: 0px;">
       			<div class="form-group">
					    <label for="email">Bank Name</label>
					    <input type="text" class="form-control">
					  </div>
       				<div class="form-group">
					    <label for="email">IFSC Code</label>
					    <input type="text" class="form-control">
					  </div>
					  <div class="form-group">
					    <label for="email">Account No</label>
					    <input type="text" class="form-control">
					  </div>
       			</div>
       			<div class="col-mg-6 col-lg-6">
       				
       			</div>
       			
       			
       		
       		</div>
       		
       		</div>
       		<div class="col-lg-6 col-md-6" style="padding-top: 15px;">
       			<form class="form-inline">
       				<p>Product Allocated </p>
       				<div class="six columns" >
		             <article>
		              
		               <div id="scrolltable">
		               	<ul class="vertical default_list" id="allprod">
		                 <li><input type="checkbox" onclick="selectAll(this)" /> Select All</li>
		                 
		                 	
				     
				            <li> <input class="case" type="checkbox" value="" /> BT Set</li>
				            <li> <input class="case" type="checkbox" value="" /> Blood Bag</li>
				            <li> <input class="case" type="checkbox" value="" /> Chemical</li>
						  
		                
		               </ul>
		               </div>
		               
		             </article>
		           </div>
       					 
       					 
       					 
				     
				     
				    
				                     
							   
				  <button type="submit" class="btn btn-default hidden"><i class="fa fa-plus" aria-hidden="true"></i></button>
				</form>
       		</div>
       		
       		
       		
       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>

  </div>
</div>

</body>
</html>