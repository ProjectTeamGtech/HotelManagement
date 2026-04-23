<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->



<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">


	<script type="text/javascript" src="inventory/js/procurement.js"></script> 

   
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
            padding: 8px 10px;
        }
        .red{
            color:red;
        }
        select {
            text-transform: none;
            height: 26px !important;
        }
        .chosen-container {
    font-size: 14px;
    width: 100% !important;
}
    </style>
</head>





<body id="his" class="appWrapper sidebar-xs-forced">
	
	<s:form action="savepoProcurement" id="procurementfrm" theme="simple">
		<input type="hidden" name="hdnrowcount" id="hdnrowcount"/>
		<section id="">
		<div class="">
		<div class="pageheader">
		<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
			<div class="form-inline">
			
				<div class="form-group hidden">
						<s:select name="vendor" id="vendor" cssClass="form-control chosen-select" list="vendorList"
						listKey="id" listValue="name" headerKey="0"
						headerValue="Select Supplier"
						onchange="setProductListAjax(this.value)" />
				</div>
				<div class="form-group pull-right">
					
					<input name="ctl00$CPHcontent$HFDomain" id="CPHcontent_HFDomain" value="2" type="hidden"> 
					<input name="ctl00$CPHcontent$HFSuppId" id="CPHcontent_HFSuppId" value="29" type="hidden">
				</div>
			</div>
			
		</div>
		<div class="">
		
		<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
			<div class="col-lg-5 col-md-5 col-xs-5" style="padding: 0px;border-right: 1px solid #efefef;min-height: 500px;">
				<table class="table my-table xlstable table-bordered" style="width: 100%;" id="mytable" >
			<thead>
				<tr>
					<th style="width:82%;">Product Name</th>
					<th>Quantity</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="procurmentprodlistajax">
				<tr>
					<td>
						<select name="" class="form-control chosen-select">
							    <option value="0">Select Product</option>
							    <option value="1">Paracitamol (Brand Name / Supplier name)</option>
							    <option value="2">Paracitamol (Brand Name1 / Supplier name1)</option>
							</select>
					</td>
					<td><input class="productQty form-control" onkeyup="getTotal(1)" id="txtQty1" type="text"></td>
					<td><a href=""><i class="fa fa-times fa-2x" style="color:#d9534f;" aria-hidden="true"></i></a></td>
				</tr>

			</tbody>

		</table>
		<div class="col-lg-12 col-xs-12" style="padding:0px;margin-top:15px;">
			<div class="col-lg-12 col-md-12 text-right">
			
			<p>Total : Rs.<label id="lblPOTotal">00.00</label></p>
			</div>
		</div>
		<div class="col-lg-12 col-xs-12" style="padding:0px;margin-top:15px;">
			<div class="col-lg-6 col-md-6 text-left">
			<input value="Add Product" onclick="addnewRow('mytable')" class="btn btn-primary" type="button">
			</div>
			<div class="col-lg-6 col-md-6 text-right">
			<input value="Create PO" onclick="purchaseProduct();" class="btn btn-primary" type="button"></div>
			</div>
		</div>
		
		
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7">
			
			</div>
			<table class="table my-table xlstable table-bordered hidden" style="width: 100%;" id="mytable" >
			<thead>
				<tr>
					<th>Sr.</th>
					<th>Product ID/ Code</th>
					<th>Product Name</th>
					<th>Generic Name</th>
					<th style="text-align:right;">MRP</th>
					<th style="text-align:right;">Purchase Price</th>
					<th>In Stock</th>
					<th>Purchase Quantity</th>
					<th style="text-align:right;">Total</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="procurmentprodlistajax">
				<tr>
					<td>1.</td>
					<td>0</td>
					<td></td>
					<td><!--<select onchange="GetProductDetails(1)" class="ProductName" id="ddl1"><option value="0">Select Product</option><option value="562">DYTOR 100MG</option><option value="617">VANLID INJ (1GM)</option><option value="618">TARGOCID INJ (200MG)</option><option value="746">NEPHTOR TAB</option><option value="748">LAXIX INJ(10MG IN 2ML)</option><option value="761">METOZ (2.5/5MG)</option><option value="805">URITOP/FONTON 100TAB (100MG)</option><option value="2147">MOX Regulator</option><option value="2154">OT lights LED</option><option value="2155">Anaesthesia Rotameter 3 Dimension</option><option value="2157">Nitrous Oxide Regulators</option><option value="2158">Double Gauge Medical Nitrous Oxide Regulators</option><option value="2159">Oxygen MOX regulators</option><option value="2161">Twin Stage Twin Gauge Mox Regulators</option><option value="2163">Ambu Bag</option><option value="2164">Anaesthesia Face Mask</option><option value="2165">Ambu Silicone Face Masks</option><option value="2166">Medical Gas Pipeline Alarm</option><option value="2167">Single Gas Pipeline Alarm     Zoom     SGPA Single Gas Pipeline Alarm</option><option value="2168">Line Pressure Alarm</option><option value="2170">Medical Gauges</option><option value="2171">Pressure gauges 0-16 Oxygen</option><option value="2172">Disposable Guedel Airways</option><option value="2173">Suction Trolley</option><option value="2174">Deluxe Electric Suction Trolley</option><option value="2175">Motorised Suction Trolley</option><option value="2176">Jacketed Flow Meter</option><option value="2177">Metal BPC Flow meters</option><option value="2181"> Infant Bed / Child Cot / Baby Bassinet</option><option value="2182">I.C.U. Bed, Electric, 7 Function with X-ray Permeable Backrest</option><option value="2183">Fowler Bed Manual</option><option value="2185">Semi-Fowler Bed, Manual</option><option value="2186">Delivery Table / Delivery Couch</option><option value="2187"> Emergency Trolley</option><option value="2188">Stretcher Single Fold With Two Wheels</option><option value="2189">Doctor Stool</option><option value="2190">Orthopaedic Bed, Fowler</option><option value="2191">Foam Mattress Plain</option><option value="2192">Comfy Hydraulic Stretcher Trolley, 5 Functions</option><option value="2195">Semi-Fowler Bed, Manual</option><option value="2196">Instrument Cabinet</option><option value="2197">Instrument &amp; Medicine Cabinet</option><option value="2200">Office Tables</option><option value="2201">CEFTUM 250 TAB</option><option value="2202">REFLIN INJ(500MG)</option><option value="2203">TAXIM INJ(1GM)</option><option value="2204">ZOACT(1.5GM)</option><option value="2205">CEFPRAJ INJ (1.5GM)</option><option value="2206">GELCUIL SYP</option><option value="2207">VITAMIN K INJ</option><option value="2208">STABLON TAB(12.5MG)</option><option value="2209">MINIPRESS</option><option value="2210">LARINATE</option><option value="2211">NEOMOLFEBRINIL INJ</option><option value="2213">Test Item 001</option><option value="2214">GLIMY 1 TAB(1MG)</option><option value="2215">QURON(100MG/5ML)</option><option value="2216">CONSTEZ POWDER(10MG)</option><option value="2217">Anawin</option><option value="2228">Abacavir Sulfate</option><option value="2229">Abarelix</option></select>
                                    --></td>
					<td style="text-align:right;"></td>
					<td style="text-align:right;"></td>
					<td></td>
					<td><input class="productQty form-control" onkeyup="getTotal(1)" id="txtQty1" type="text"></td>
					<td style="text-align:right;"></td>
					<td><a href=""><i class="fa fa-times fa-2x" style="color:#d9534f;" aria-hidden="true"></i></a></td>
				</tr>

			</tbody>

		</table>
		
			
		</div>
		
		
		<br />

		<div style="text-align: right; text-align: right; font-size: medium; font-weight: bold;"></div>
		<div style="height: 29px">
		
		<div style="text-align: right; width: 50%; float: right;">
			
		</div>
		</div>



		</div>



		</div>

		</section>
		<!--/ CONTENT -->
</s:form>





    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="addvendor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add Manufacture</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Name<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Address :</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="3"></textarea>
                                </div>
                            </div>
                           
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Email ID<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Brand Name<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Mobile (Primary)<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Mobile (Secondory) :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Min Delivery Time<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Add/Edit</button>
                </div>
            </div>
        </div>
    </div>


</body>
</html>
