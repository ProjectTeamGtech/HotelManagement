<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->


<%@ taglib prefix="s" uri="/struts-tags"%>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <SCRIPT type="text/javascript" src="inventory/js/addcategory.js"></SCRIPT>

    
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
        }
        .topheadbaxck1 {
            background-color: rgb(239, 239, 239);
            padding: 8px 11px;
        }
        .marr5 {
            margin-right: 5px !important;
        }
    </style>
</head>





<body id="his" class="appWrapper sidebar-xs-forced">

    
        <section id="">

            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck1">
                        <form class="form-inline" >
                            <div class="input-group martop10">
                                <input id="txtmainmenus" class="form-control" style="width:400px;" type="text">
                                <div class="input-group-btn" style="width:0% !important">
                                    <button class="btn btn-success btn-Addmenu" onclick="addcategory()" style="margin-right: 4px;">Add MainCategory</button>
                                    <a class="btn btn-warning" role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample"> Add Sub Category</a>
                                </div>

                            </div>
                            <div class="collapse" id="collapseExample">
                                <div class="">
                                    
                                    <div class="input-group" id="subcategoryPannel" style="margin-top: 10px; display: block;">
                                    <form name="subform" action="savesubProductinventory" method="post">
                                        <div id="selectList" class="selectListClass">
                                        
                                         <s:select list="categoryList" id="selectlistofmenu" name="category_id" listKey="id" listValue="name" cssClass="form-control" cssStyle="width:400px;" title="Select Main Category">
                                        </s:select>
                                        </div>
                                        <input id="txtsubmenus" name="subcategory" class="form-control" style="width:400px;margin-left:3px;" type="text">
                                        <div class="input-group-btn" style="width:0% !important">
                                            <button class="btn btn-success btn-Addsubmenu" id="btnAddsubmenu" onclick="addSubMenu()">Add SubCategory</button>
                                            <button class="btn btn-warning btncancelPanel" id="btncancel" style="margin-left:3px;">Cancel</button>
                                        </div>
                                       </form>
                                    </div>
                                  
                                </div>
                            </div>
                        </form>
                       
                    </div> <br /><br /><br /><br /><br />
                    
                    <s:iterator value="categoryList">
                    <div class="">
                        <div class="panel panel-default" style="width: 54%;">
                            <div class="panel-heading" id="m<s:property value="id"/>">
                                <h4><span><s:property value="name"/></span>
                                
                                <s:if test="id!=2" >
                                	<button onclick="editmcategory(<s:property value="id"/>,'<s:property value="name"/>')" class="btn btn-primary btn-xs pull-right editMainmenu" data-id="34">Edit</button> <button onclick="deletemcategory(<s:property value="id"/>)" class="btn btn-primary btn-xs pull-right btndeleteMainmenu marr5">Delete</button></h4>
                                </s:if>
                            </div>
                        <div class="panel-body">
                            <ul class="list-group">
                               <s:iterator value="subcategoryList">
                                <li class="list-group-item" id="s<s:property value="id"/>">
                                    <span><s:property value="name" /> </span>
                                    
                                     <s:if test="id!=4">
                                   <button onclick="editsubcategory(<s:property value="id"/>,'<s:property value="name"/>')" class="btn btn-primary btn-xs pull-right editsubmenu" data-id="263">Edit</button> <button onclick="deletesubcategory(<s:property value="id"/>)" data-id="263" class="btn btn-primary btn-xs pull-right btndeletesubmenu marr5">Delete</button>
                                    </s:if>
                                </li> 
                                </s:iterator>
                            </ul>
                         </div>
                         
                       </div>
                       </s:iterator>

                    </div>

                    

                </div>

               
 <form name="mainform" id="mainform" action="saveaddProductinventory" method="post">
    
       <input type="hidden" id="xname" name="name"> 
    </form>

        </section>
        <!--/ CONTENT -->



    
    




    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="editcat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Edit</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Product ID :</label>
                                <div class="col-sm-9">
                                    2182
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Section :</label>
                                <div class="col-sm-9">
                                    <select id="ddlSection" class="PopDDl" onchange="getCategory();"><option value="11">OT Equipment</option><option value="12">Other</option><option value="13">Medical Equipment</option><option value="14">Baby Care</option><option value="15">Healthy Wealthy</option><option value="16">Ready to Eat</option><option value="17">Cloth Care</option><option value="20">Furniture</option><option value="21">Medicine</option><option value="23">Exclusive Store1</option><option value="24">Exclusive2</option><option value="25">Exclusive2</option><option value="26">Exclusive3</option><option value="27">person care2</option><option value="28">ssssssss</option><option value="29">test</option><option value="30"></option><option value="31"></option><option value="32"></option><option value="33">Other</option></select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Category :</label>
                                <div class="col-sm-9">
                                    <select id="ddlCatagory" class="PopDDl"><option value="4">Hospital Bed-ICU</option><option value="6">Hospital Bed-Fowler</option><option value="7">Hospital Bed-Plain</option><option value="8">Hospital Bed-Semi-Flower</option><option value="9">Obstetric Tables</option><option value="10">Trolly</option><option value="11">Stretchers</option><option value="12">Doctor Chair &amp; Stools</option><option value="209">Baby Crib</option><option value="210">Beds - Orthopaedic</option><option value="211">Beds Mattress</option><option value="220">STRETCHER TROLLEY</option><option value="221">INSTRUMENT TROLLEY</option><option value="222">EXAMINATION COUCH</option><option value="223">WHEEL CHAIR</option><option value="224">Medical Cabinets Cupboards</option><option value="225">Waiting Chairs &amp; Benches</option><option value="226">Office Conference, Coffee Tables</option></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Brand :</label>
                                <div class="col-sm-9">
                                    <select name="ctl00$CPHcontent$ddlPopUpBrads" id="CPHcontent_ddlPopUpBrads" style="width:200px;">
                                        <option value="133">Sun Pharma</option>
                                        <option value="134">Narang</option>
                                        <option value="135">PHCD</option>
                                        <option value="137">Bajaj</option>
                                        <option value="138">Philips</option>
                                        <option value="139">ATICO</option>
                                        <option value="141">SONOSITE</option>
                                        <option value="158">TOSHIBA</option>
                                        <option value="161">Assure</option>
                                        <option value="195">COVIDIEN</option>
                                        <option value="199">ConvaTec</option>
                                        <option value="202">Medtronic</option>
                                        <option value="204">Metrex</option>
                                        <option value="1206">NIPRO</option>
                                        <option value="1207">Prevail</option>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Name :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Infant Bed / Child Cot / Baby Bassinet">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">MRP :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 18000">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Purchase Price :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 14200">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Sale Price :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 15000">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Update</button>
                </div>
            </div>
        </div>
    </div>


   

</body>
</html>
