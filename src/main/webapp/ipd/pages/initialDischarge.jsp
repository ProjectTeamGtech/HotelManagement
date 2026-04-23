 <%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Discharge Dashboard</title>
<!--<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
  -->


         <link rel="stylesheet" href="_assets/css/priscription/hospitalresponsive.css">
<!-- <link rel="stylesheet" href="_assets/newtheme/css/main.css"> -->
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
  <script type="text/javascript" src="ipd/js/discharge.js"></script>
   
    <script type="text/javascript" src="common/js/pagination.js"></script>
    
    <style>
.has-warning .radio,
.has-warning .checkbox,
.has-warning .radio-inline,
.has-warning .checkbox-inline {
  color: #616f77;
}

.has-warning .checkbox-custom > i {
  border-color: #ffcd33;
}

.checkbox-custom,
.checkbox-custom-alt {
    padding-left: 20px;
    cursor: pointer;
    margin-top: 0px !important;
    margin-bottom: 0px !important;
}

.checkbox-custom input,
.checkbox-custom-alt input {
  position: absolute;
  opacity: 0;
}

.checkbox-custom input:checked + i,
.checkbox-custom-alt input:checked + i {
  border-color: #428bca;
  background-color: #428bca;
}

.checkbox-custom input:checked + i:before,
.checkbox-custom-alt input:checked + i:before {
  top: 3px;
  left: 3px;
  width: 12px;
  height: 12px;
  background-color: #fff;
}

.checkbox-custom input:checked + span .active,
.checkbox-custom-alt input:checked + span .active {
  display: inherit;
}

.checkbox-custom input[type='radio'] + i,
.checkbox-custom input[type='radio'] + i:before,
.checkbox-custom-alt input[type='radio'] + i,
.checkbox-custom-alt input[type='radio'] + i:before {
  border-radius: 50%;
}

.checkbox-custom input[disabled] + i,
.checkbox-custom-alt input[disabled] + i {
  border-color: #e2e2e2;
  background-color: #f2f2f2;
}

.checkbox-custom input[disabled] + i:before,
.checkbox-custom-alt input[disabled] + i:before {
  background-color: #e2e2e2;
}

.checkbox-custom > i,
.checkbox-custom-alt > i {
  position: relative;
  display: inline-block;
  width: 20px;
  height: 20px;
  margin-top: 0px;
  margin-right: 0px;
  margin-left: -18px;
  line-height: 1;
  vertical-align: middle;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.2);
  -webkit-transition: all 0.2s;
  -moz-transition: all 0.2s;
  transition: all 0.2s;
}

.checkbox-custom > i:before,
.checkbox-custom-alt > i:before {
  position: absolute;
  top: 50%;
  left: -100%;
  width: 0;
  height: 0;
  background-color: transparent;
  content: "";
  -webkit-transition: all 0.2s;
  -moz-transition: all 0.2s;
  transition: all 0.2s;
  z-index: 1;
}

.checkbox-custom > span,
.checkbox-custom-alt > span {
  margin-left: -20px;
}

.checkbox-custom > span .active,
.checkbox-custom-alt > span .active {
  display: none;
}

.checkbox-custom:hover > i,
.checkbox-custom-alt:hover > i {
  border-color: #22beef;
}

.checkbox-custom.checkbox-custom-sm input:checked + i:before,
.checkbox-custom-alt.checkbox-custom-sm input:checked + i:before {
  top: 2px;
  left: 2px;
  width: 10px;
  height: 10px;
}

.checkbox-custom.checkbox-custom-sm > i,
.checkbox-custom-alt.checkbox-custom-sm > i {
  width: 16px;
  height: 16px;
  margin-right: 6px;
  margin-left: -18px;
}

.checkbox-custom.checkbox-custom-lg input:checked + i:before,
.checkbox-custom-alt.checkbox-custom-lg input:checked + i:before {
  top: 3px;
  left: 3px;
  width: 22px;
  height: 22px;
}

.checkbox-custom.checkbox-custom-lg > i,
.checkbox-custom-alt.checkbox-custom-lg > i {
  width: 30px;
  height: 30px;
}

.checkbox-custom-alt input:checked + i {
  background-color: transparent;
  border-color: #666;
  color: #666;
}

.checkbox-custom-alt input:checked + i:before {
  top: 2px;
  left: 2px;
  width: auto;
  height: auto;
  background-color: transparent;
  opacity: 1;
}

.checkbox-custom-alt input[type='radio']:checked + i:before {
  left: 1px;
}

.checkbox-custom-alt input[disabled] + i {
  border-color: #e2e2e2;
  background-color: #f2f2f2;
}

.checkbox-custom-alt input[disabled] + i:before {
  background-color: transparent;
  color: #ccc;
}

.checkbox-custom-alt > i {
  width: 18px;
  height: 18px;
  background-color: transparent;
  border: 2px solid #dfdfdf;
}

.checkbox-custom-alt > i:before {
  content: "\f00c";
  top: 0;
  left: 0;
  display: inline-block;
  font-family: "FontAwesome";
  font-style: normal;
  font-weight: normal;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 11px;
  opacity: 0;
  -webkit-transition: all 0.2s;
  -moz-transition: all 0.2s;
  transition: all 0.2s;
}

.checkbox-custom-alt.checkbox-custom-sm > i:before {
  font-size: 9px;
}

.checkbox-custom-alt.checkbox-custom-sm input:checked + i:before {
  font-size: 9px;
  top: 1px;
}

.checkbox-custom-alt.checkbox-custom-lg input:checked + i:before {
  font-size: 18px;
  top: 4px;
  left: 4px;
}
    .checkbox-custom-alt input:checked + i {
    background-color: transparent;
    border-color: #666;
    color: #5cb85c;
}
.checkbox-custom-alt > i {
    width: 18px;
    height: 18px;
    background-color: transparent;
    border: none;
}
.form-control {
    border: 1px solid #ddd;
    background-color: #fff;
}
    .modal-header {
    border-bottom: 1px solid #e5e5e5;
    background-color: #e5e5e5;
        padding: 5px 10px 5px 10px !important;
}
.form-group {
    margin-bottom: 0px !important;
}
    input[type="checkbox"] {
    margin: 1px 0 0;
    margin-top: 1px \9;
    line-height: normal;
}
.checkbox input[type="checkbox"], .checkbox-inline input[type="checkbox"] {
    position: absolute;
    margin-left: -17px;
    margin-top: 4px \9;
}
    .carousel-control.left {
   	background-image: none !important;
   	line-height: 140px;
}
.carousel-control.right {
   	background-image: none !important;
   	line-height: 140px;
}
    .disabled {
    z-index: 1000 !important;
    background-color: lightgrey !important;
    opacity: 0.6 !important;
    pointer-events: none !important;
}
   
    .updatebtn{
    	float: right;
    height: 18px !important;
    font-size: 10px;
    line-height: 7px;
    }
            .page {
                padding: 0px;
            }
            .tile .tile-header {
                padding: 0px 0px;
            }
            .portlets.sortable {
                padding-right: 2px;
                padding-left: 2px;
            }
            .tile .tile-body {
                padding: 6px 0px 6px 6px;
                text-transform: uppercase;
            }
            p {
                margin: 0 0 0px;
                font-size: 12px;
            }
            .tile .tile-header h1{
                font-size: 12px;
            }
            
            a {
                text-decoration: none !important;
            }
            .paddingnilleft {
                padding-left: 0px;
            }
            .minheight72 {
                min-height: 121px;
            }
            .tile {
                margin-bottom: 4px;
                border-radius: 10px;
            }
           
            .table > thead > tr > th {
                padding: 0px;
            }
            .table > thead > tr th:first-child{
                padding-left: 5px;
            }
            .table > tbody > tr td:first-child{
                padding-left: 6px;
            }
            .table > tbody > tr > td{
                padding: 0px;
            }
           .setbordericon{
           	border-right: 1px solid rgba(241, 239, 239, 0.1);
		    padding: 0px;
		    text-align: center;
		    padding-top: 3px;
		    padding-bottom: 3px;
           }
           
            .bghead {
                background-color: rgb(242, 242, 242);
            }
            .tile[class*="bg-"]:not(.bg-default) .dvd, .tile.dvd[class*="bg-"]:not(.bg-default), .tile-header[class*="bg-"]:not(.bg-default) .dvd, .tile-header.dvd[class*="bg-"]:not(.bg-default), .tile-widget[class*="bg-"]:not(.bg-default) .dvd, .tile-widget.dvd[class*="bg-"]:not(.bg-default), .tile-body[class*="bg-"]:not(.bg-default) .dvd, .tile-body.dvd[class*="bg-"]:not(.bg-default), .tile-footer[class*="bg-"]:not(.bg-default) .dvd, .tile-footer.dvd[class*="bg-"]:not(.bg-default) {
                border-color: none;
            }
            .tile .dvd.dvd-btm {
                border-bottom-width: 0px;
            }
            .bellcolr{
                color:rgb(242, 242, 242) !important;
            }
            .bellcolr:hover {
                color: #808080;
            }
            .tile .tile-header .controls .settings > i:last-of-type {
                display: block;
            }
            .padtop7 {
                padding-top: 4px;
    			margin-right: 5px;
            }
            .table > tbody > tr td {
                border-color: none;
            }
            .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
                border-top: none;
            }
            
            .mainbox {
                width: 215px;
            }
            .medumclass {
                padding: 0px 0px 0px 18px !important;
            }
            .tab-content .tab-pane {
                padding: 6px 16px 0px 16px;
            }
            .smallbell {
                font-size: 12px;
                float: right;
            }
            strong {
                font-weight: 700;
                color: yellow;
            }
            legend {
    			margin-bottom: 5px;
			}
            .martop4 {
                margin-top: 4px;
            }
            .minheightsmall{
                min-height: 66px;
            }
            .padsamll {
			    padding-top: 6px;
			    margin-right: -6px;
			}
			.font11 {
                font-size: 10px !important;
            }
            .nitific{
                margin-left: -8px;
            }
            .nitific15{
				margin-left:-15px;
				}
            .tabcolor{
            color: #555;
            }
            .extended{
            width: 370px;
            }
            .nitificsamll{
            float: right;
		    font-size: 10px;
		    margin-top: 6px;
            }
            .prstotip{
            font-size: 12px;
            }
            .webui-popover {
                width: 40% !important;
            }
            .marto36{
                margin-top: -31px !important;
            }
            input[type="checkbox"] {
    			margin-right: 5px;
			}
           
			.blink_me {
			  animation: blinker 1s linear infinite;
			  color:#f7aaed;
			}
			
			@keyframes blinker {  
			  50% { opacity: 0; }
			}

		.red {
		 color: red;
		}
		
		.modal-header {
		 border-bottom: 1px solid #e5e5e5;
		 background-color: #e5e5e5;
		 padding: 0px 10px 0px 10px;
		}
		
		
		
		.noborder {
		 border-top: none !important;
		}
		
		.border {
		 border-top: 1px solid rgba(192, 192, 192, 0.25) !important;
		}
		.width100{
			width:100%;
		}
		.table > tbody + tbody {
			    border-top: 0px solid #ddd !important;
			}
			.dosatable{
			    background-color: #D3D3D6 !important;
    			color: #555 !important;
			}
			.activeback{
				background-color: rgb(129, 171, 109);
    			min-height: 135px;
    			border-left: 1px solid #f0f0f0;
			}
			.disableback{
				background-color: #E6E6E6;
				min-height: 365px;
    			border-left: 1px solid #e8e4e4;
    			padding-left: 0px;
    			padding-right: 0px;
			}
			.dischptext{
				font-size: 11px !important;
				margin-top:5px;
			}
			.dischariconab{
				    position: absolute;
			}
			.textbomana{
			        font-size: 13px !important;
				    margin-top: 0px !important;
				    min-height: 21px !important;
				    padding: 7px 0px 7px 0px !important;
				    margin-bottom: 0px;
				    font-weight: bold;
			}
			.imagestfix{
				width: 70% !important;
			    margin-left: auto !important;
			    margin-right: auto !important;
			    display: block !important;
			    padding-top: 10px !important;
			}
			.imagestfix123{
				width: 100% !important;
			    padding-top: 10px !important;
			}
			.completback{
				background-color: rgba(0, 128, 0, 0.43) !important;
				min-height: 620px;
			}
			.initaiback{
				background-color: rgba(255, 165, 0, 0.62) !important;
				    min-height: 620px;
			}
			.btnwidtfixed{
				width: 75% !important;
			    height: 85px !important;
			    font-size: 23px;
			    line-height: 70px;
			}
			.fontflag{
				font-size:18px;
			}
			
			
			.checkboxdemoBasicUsage legend {
    color: #339966;
}
md-content fieldset legend, md-dialog-content fieldset legend {
    font-size: 12px;
    margin-bottom: 0;
    border: 0;
    display: inline;
    width: auto;
    padding: 0 4px;
}
.checkboxdemoBasicUsage.checkboxDemo1 div {
        border-radius: 7px;
        margin-left: -2px;
}

legend {
    background: none !important;
}
fieldset {
    font-size: 14px;
    border: 1px solid #6699CC;
    padding: 0px 0px 0px 10px;
    border-radius: 0.5em;
    margin-bottom: 5px;
}
.thumbnail>img, .thumbnail a>img {
    margin-left: auto;
    margin-right: auto;
    width: 45%;
    margin-top: -4px;
}
.thumbnail {
    height: 70px;
    width: 100%;
}
.thumbnail:hover {
    border: 1px solid #339966;
    box-shadow:1px 1px 1px;
}
.fontpup {
    font-size: 12px;
    line-height: 15px;
}
.padrigset{
	padding: 0px 0px 0px 6px;
}
.popupbedheaight {
    min-height: 130px;
}
.paddingrih {
    padding-left: 0px !important;
    padding-right: 10px !important;
}
.bedcount{
	    padding: 3px 15px 0px 15px;
    background-color: #f5f5f5;
    color: #000;
        line-height: 17px;
}

.seticons{
	  padding: 0px;
    background-color: rgba(230, 230, 230, 0.49);
    padding-bottom: 5px;
    min-height: 88px;
}
.backkpi{
	background-color: rgba(255, 248, 220, 0.55) !important;
}
.tdpadnil{
	padding: 1px 1px 1px 1px !important;
}
.active{
	background-color: #fff;
}


.lined-paper p, .lined-paper ul, .lined-paper ol {
    margin-bottom: 0px;
    font-size: 9px;
    padding-top: 5px !important;
}
.checkbox {
    font-weight: normal;
    font-size: 11px;
    text-align: justify;
}

.checkbox-custom, .checkbox-custom-alt {
    padding: 0px 10px 0px 20px;
    cursor: pointer;
    margin-top: 0px !important;
    margin-bottom: 0px !important;
}
.setlimet{
	border-bottom: 1px solid #efefef;
}
ul, ol {
    margin-top: 0;
    margin-bottom: 0px;
}


.threeiocn{
	margin-left: auto;
    margin-right: auto;
    width: 75%;
}
.set4titime{
	    margin-bottom: 0px;
    margin-top: 0px;
    font-weight: bold;
    text-transform: uppercase;
}
        </style>
    
    
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


.lined-paper p, .lined-paper ul, .lined-paper ol {
    margin-bottom: 0px;
    font-size: 9px;
    padding-top: 5px !important;
}
.checkbox {
    font-weight: normal;
    font-size: 11px;
    text-align: justify;
}

.checkbox-custom, .checkbox-custom-alt {
    padding: 0px 10px 0px 20px;
    cursor: pointer;
    margin-top: 0px !important;
    margin-bottom: 0px !important;
}
.setlimet{
	border-bottom: 1px solid #efefef;
}
ul, ol {
    margin-top: 0;
    margin-bottom: 0px;
        list-style: none;
    padding: 0px;
}


hr {
    margin-top: 2px !important;
    margin-bottom: 2px !important;
}
.fontzitime{
	font-size:10px !important;
}
.my-table td {
    padding: 1px 1px 1px 2px !important;
}
.my-table caption {
    font-size: 1.4em;
    font-weight: bold;
    padding: 2px !important;
    text-align: center !important; 
}
.botbtn{
	    margin-top: 30px !important; 
}
.my-table th {
    font-size: 11px;
}
.fa-2x {
    font-size: 15px;
}

.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 135px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 12px;
    font-weight: bold;
}
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
.titleset{
	margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
    background-color: #efefef;
     
}
label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: bold;
    
}

.setticolors{
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
}
.form-group {
    margin-top: 0px;
}

@media screen {
  #printSection {
      display: none;
  }
}

@media print {
  body * {
   /*  visibility:hidden; */
  }
  #printSection, #printSection * {
    visibility:visible;
  }
  #printSection {
    position:absolute;
    left:0;
    top:0;
  }
  
}



 @media print
{
	
 
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}

}


.tooltip {
    opacity: 1;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 180px;
    text-align: left;
    padding: 0px 0;
    background-color:#555;
    color:#fff;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 20%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 1s;
}
.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 10%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1 !important;
}
.bggcolor{
background-color: #4E7894;
color: white;
}
</style>
<style>
	.loc{
		background-color: #6699cc;
		color: white;
	}
	@media print {
		.loc{
			background-color: #6699cc  !important;
			color: white;
		}
	}
	
	body {
	font-family: 'Open Sans', sans-serif !important;
}
</style>
<script>
	var idis_initiate_status = '0';
	var idis_form_status='0';
	idis_nursing_status='0';
</script>

 <script type="text/javascript">
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
    
    
    function printExcel() {

        $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Discharge List",
					filename: "DischargeList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
</script>



<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
</head>
<body>
<div class="">
							<div class="hidden-print">
								<div class="row <!-- details mainheader-->" >
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<img src="manasmaindashboard/images/Discharge_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
										 	<h4>Discharge Dashboard</h4>
										</div>
										<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
											<img src="dashboardicon/discharge.png" class="img-responsive prescripiconcircle">
										</div>
										<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
										<h4>Discharge Dashboard</h4>
										</div> -->
									</div>
									<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form cssClass="form-inline" action="InitialDischarge?IpdpatientType=0" theme="simple">
											  <div class="form-group">
											    <s:textfield cssClass="form-control" id="searchtext" name="searchtext" placeholder="Search Patient" />
											  </div>
											  <div class="form-group">
											    <s:textfield cssClass="form-control" id="fromdate" name="fromdate" placeholder="From Date" autocomplete="off"/>
											  </div>
											<div class="form-group">
											    <s:textfield cssClass="form-control" id="todate" name="todate" placeholder="To Date" autocomplete="off"/>
											  </div> 
											   <s:select headerKey="" headerValue="Select days" cssClass="form-control hidden" list="#{'2':'2 days', '4':'4 days', '6':'6 days' , '8':'8 days', '10':'10 days'}" theme="simple" name="todays" />  
         
             <s:select  headerKey="" headerValue="Select Status" cssClass="form-control" list="#{'1':'Initiated','2':'Form Completed', '3':'Medicine', '4':'Account' , '5':'Nursing', '6':'Discharged'}" name="filter_status1" />  
         	 <s:select  headerKey="0" headerValue="Select Type" cssClass="form-control" list="#{'1':'Self','2':'Third Party'}" name="patient_type" />
             
             <s:submit  cssClass="btn btn-primary" value="GO"></s:submit> 
             
             <a href="#" onclick="gorefereshnew()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
            <s:hidden name="refresh" value="0" id="refresh" ></s:hidden>
            
          <!--   <a href="#" class="btn btn-warning" onclick="printDiv('page_printer');">Print</a> -->
            <a href="#" class="btn btn-warning" onclick="printpage()">Print</a>
            <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a> 
            <div class="form-group" style="float: right;">
												<div class="form-inline" style="float: right;margin-top: 5px;color: #000;">
												
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#6699cc"></i> Initiated
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#c7d8c0;"></i> From Completed
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgb(222, 184, 135)"></i> Medicine
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgb(153, 153, 153)"></i> Account
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#fff"></i> Nursing
											    </label>
											  </div>
											</div>
											</div>
										  </s:form>
										  
										</div>
										</div>
								</div>
								<div class="row">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="page_printer">
										<div>
											
											<table class="table table-responsive " style="width: 100%;text-transform: uppercase;" id="shub">
												<thead>
													<tr>
													<td style="width: 13%;" class="manastableheader">IPD ID | UHID</td>
													<!-- <td style="width: 14%;">Doctor Name</td> -->
													<td style="width: 31%;"class="manastableheader">Patient Details</td>
													<td style="width: 11%;text-align: center;"class="manastableheader">Initiated</td>
													<td style="width: 12%;text-align: center;"class="manastableheader">Form Completed</td>
													<td style="width: 10%;text-align: center;"class="manastableheader">Medicine</td>
													<td style="width: 10%;text-align: center;"class="manastableheader">Account</td>
													<td style="width: 10%;text-align: center;"class="manastableheader">Nursing</td>
													
													<td style="width: 10%;text-align: center;"class="manastableheader">Discharge End Time</td>
													<td style="width: 10%;text-align: center;"class="manastableheader">Discharge Date</td>
													<td style="width: 10%;text-align: center;"class="manastableheader">Invoice No. </td>
													<td class="hidden-print manastableheader"></td>
													<td class="hidden-print manastableheader"></td>
													<td class="hidden-print manastableheader"></td>
												</tr>
													</thead>
													<tbody>
															<s:iterator value="bedlist">
																<%boolean flag = false; %>
																<tr>
																
																<td><%-- <s:property value="addmissionid"/> --%>
																<%if(loginInfo.getIpd_abbr_access()==1){ %>
																 	<s:property value="newipdabbrseq"/>
																 <%}else{ %>
																 	<s:property value="ipdseqno"/>
																<%} %>| <s:property value="patientIdAbrivation"/></td>
																<%-- <td><small><s:property value="practitionername"/></small></td> --%>
																<%-- <td><s:property value="patientIdAbrivation"/></td> --%>
																<td >
																
																	<div class=""> 
																	<span style="color: #000;" ><s:property value="clientname"/></span> | <s:property value="age"/> / <s:property value="gender"/> | <s:property value="whopay"/> | <s:property value="wardname"/> / <s:property value="bedname"/> 
																 | <span style="color: #e84946;"><s:property value="practitionername"/></span>
																  <%-- <span class="tooltiptext">
																  	<ul style="list-style: none;margin: 0px;padding: 0px 0px 0px 0px;color: #e84946;;">
																  		<li><s:property value="practitionername"/></li>
																  	</ul>
																  </span> --%>
																</div>
																</td>
																<s:if test="dis_initiate_status>0">
																	<td style="background-color: aliceblue;">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><s:property value="dis_initiate_date"/> <s:property value="dis_initiate_time"/></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:if>
																<s:else>
																	<%flag=true; %>
																	<td class="text-center" style="background-color: aliceblue;">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><a href="#" onclick="updatedischargestatusdb(<s:property value="treatmentepisodeid"/>,'dis_initiate_status','dis_initiate_time','1')" type="button" class="btn btn-primary">Completed</a></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:else>
																
																<s:if test="dis_form_status>0">
																	<td style="background-color: rgba(235, 255, 226, 0.59);">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><s:property value="dis_form_date"/> <s:property value="dis_form_time"/> <a href="#" class="hidden-print" onclick="opencPopup('dischargeCommonnew?selectedid=<s:property value="addmissionid"/>')" style="float: right;"><i class="fa fa-pencil fa-2x"></i></a></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:if>
																<s:else>
																	<%flag=true; %>
																	<td class="text-center" style="background-color: rgba(235, 255, 226, 0.59);">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><a href="#" onclick="openPopup('dischargeCommonnew?selectedid=<s:property value="addmissionid"/>')" type="button" class="btn btn-primary">Completed</a></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:else>
																
																
																<s:if test="dis_mdicine_status>0">
																	<td style="background-color: rgba(222, 184, 135, 0.18);">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><s:property value="dis_mdicine_date"/> <s:property value="dis_mdicine_time"/></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:if>
																<s:else>
																<%flag=true; %>
																	<td class="text-center" style="background-color: rgba(222, 184, 135, 0.18);">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><a href="#" onclick="updatedischargestatusdb(<s:property value="treatmentepisodeid"/>,'dis_mdicine_status','dis_mdicine_time',<s:property value="dis_initiate_status"/>)" type="button" class="btn btn-primary">Completed</a></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:else>
																
																<s:if test="dis_bill_status>0">
																	<td style="background-color: rgba(153, 153, 153, 0.1);">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><s:property value="dis_bill_date"/> <s:property value="dis_bill_time"/></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:if>
																<s:else>
																<%flag=true; %>
																	<td class="text-center" style="background-color: rgba(153, 153, 153, 0.1);">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <%-- <tr><td style="border:0px;text-align:center;"><a href="#" onclick="directCmpAccountForm(<s:property value="treatmentepisodeid"/>,'dis_bill_status','dis_bill_time',<s:property value="dis_initiate_status"/>,<s:property value="clientid"/>,<s:property value="balance"/>,<s:property value="addmissionid"/>)" type="button" class="btn btn-primary">Completed</a></td></tr> --%>
								                                            	<tr><td style="border:0px;text-align:center;"><a href="#" onclick="directCmpAccountFormNew(<s:property value="treatmentepisodeid"/>,'dis_bill_status','dis_bill_time',<s:property value="dis_initiate_status"/>,<s:property value="clientid"/>,<s:property value="balance"/>,<s:property value="addmissionid"/>)" type="button" class="btn btn-primary">Completed</a></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:else>
																
																	<s:if test="dis_nursing_status>0">
																	<td style=" ">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><s:property value="dis_nursing_date"/> <s:property value="dis_nursing_time"/></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:if>
																<s:else>
																<%flag=true; %>
																	<td class="text-center">
																		<table style="border:0px;width:100%;" cellpadding="0" cellspacing="0">
								                                            <tbody>
								                                                <tr><td style="border:0px;text-align:center;"><a href="#" onclick="setnursingadvoice(<s:property value="treatmentepisodeid"/>)" type="button" class="btn btn-primary">Completed</a></td></tr>
								                                            </tbody>
								                                        </table>
																	</td>
																</s:else>
																
																<td style="border:0px;text-align:center;"><s:property value="discharge_end_date"/></td>
																<td style="border:0px;text-align:center;">
																		<s:if test="treatmentstatus==1">
																			<s:property value="dischargeDate"/>
																		</s:if>
																	
																</td> 
																
																<s:if test="treatmentstatus==0">
																	<td class="hidden-print"><i class="fa fa-user-times fa-2x" style="color: #fb6d6d;" title="Not Discharged" aria-hidden="true"></i></td>
																	<td class="hidden-print"><a href="#" data-toggle="modal" onclick="showInitiateDischargedash(<s:property value="treatmentepisodeid"/>,<s:property value="clientid"/>,<s:property value="addmissionid"/>,'<s:property value="patientIdAbrivation"/>','<s:property value="clientname"/>','<s:property value="age"/>','<s:property value="gender"/>','<s:property value="wardname"/>','<s:property value="bedname"/>','<s:property value="practitionername"/>')" title="Discharge Checklist"><i class="fa fa-check-square-o fa-2x" aria-hidden="true" style="color: #5e9e5f;"></i></a></td>
																	<td class="hidden-print"></td>
																</s:if>
																<td><s:property value="invoiceno"/></td>
																<s:else>
																	<td class="hidden-print"> 
																    	<a href="#"onclick="openPopup('printdischargeCommonnew?clientid=<s:property value="clientid"/>&selectedid=<s:property value="addmissionid"/>')"><i class="fa fa-print fa-2x" title="Print" style="color: #555;"></i></a> 
																    </td>
																    <td class="hidden-print"><a href="#" data-toggle="modal" onclick="showInitiateDischargedash(<s:property value="treatmentepisodeid"/>,<s:property value="clientid"/>,<s:property value="addmissionid"/>,'<s:property value="patientIdAbrivation"/>','<s:property value="clientname"/>','<s:property value="age"/>','<s:property value="gender"/>','<s:property value="wardname"/>','<s:property value="bedname"/>','<s:property value="practitionername"/>')" title="Discharge Checklist"><i class="fa fa-check-square-o fa-2x" aria-hidden="true" style="color: #5e9e5f;"></i></a></td>
																    <td class="text-center hidden-print"> <a href="#" onclick="opencancelDischarge(<s:property value="addmissionid"/>,<s:property value="treatmentepisodeid"/>)"> <i class="fa fa-times fa-2x text-danger" title="Cancel" aria-hidden="true"></i></a></td>
																</s:else>
																</tr>
														</s:iterator>
																</tbody>
																
															</table>
															
												
										</div>
									</div>
									
									<s:form action="InitialDischarge" name="paginationForm" id="paginationForm" theme="simple">
							    
							    
									<div class="col-lg-12" style="margin-top: 15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /> 
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
								 <s:hidden name="searchtext"></s:hidden>
								 <s:hidden name="fromdate"></s:hidden>
								 <s:hidden name="todate"></s:hidden>
								 <s:hidden name="todays"></s:hidden>
								 <s:hidden name="filter_status1"></s:hidden>
							</s:form> 
									
							</div>
						</div>
						</div>
						
						<input type="hidden" name="dischargedataid" id="dischargedataid">	
						
						
						
						<%-- <s:form action="endIpd" id="enddischargefrm" theme="simple" >
        					<s:hidden name="clientid" id="endclientid"/>
        						<s:hidden name="clientip" id="endclientip"/>
        						<s:hidden name="treatmentEpisode" id="endtreatmentEpisode"/>
        	</s:form> --%>
        	
        	<div class="print-visible hidden-md hidden-lg">
        							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 loc">
										<h4>DISCHARGE DASHBOARD</h4>
									</div>
								    
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
										<table class="xlstable" style="width: 100%;text-transform: uppercase;">
												<thead>
													<tr class="loc">
														<td style="width: 5%;">SR.No</td>
														<td style="width: 13%;">IPD ID | UHID</td>
														<td style="width: 31%;">Patient Details</td>
														<td style="width: 11%;text-align: center;">Initiated</td>
														<td style="width: 12%;text-align: center;">Form Completed</td>
														<td style="width: 10%;text-align: center;">Medicine</td>
														<td style="width: 10%;text-align: center;">Account</td>
														<td style="width: 10%;text-align: center;">Nursing</td>
														<td style="width: 10%;text-align: center;">Discharge End Time</td>
														<td style="width: 10%;text-align: center;">Discharge Date</td>
													</tr>
												</thead>
												<tbody>
													<%int countsss=0; %>
													<s:iterator value="bedlist">
														<tr>
															<td style="text-align: center;"><%=(++countsss) %></td>
															<td><s:property value="ipdseqno"/>| <s:property value="patientIdAbrivation"/></td>
															<td >
																<s:property value="clientname"/> | <s:property value="age"/> / <s:property value="gender"/> | <s:property value="wardname"/> / <s:property value="bedname"/>(<s:property value="practitionername"/>) 
															</td>
															<s:if test="dis_initiate_status>0">
																<td style="border:0px;text-align:center;"><s:property value="dis_initiate_date"/> <s:property value="dis_initiate_time"/></td>
															</s:if>
															<s:else>
															    <td style="border:0px;text-align:center;">-</td>
							                                </s:else>
															<s:if test="dis_form_status>0">
																<td style="border:0px;text-align:center;"><s:property value="dis_form_date"/> <s:property value="dis_form_time"/></td>
							                                </s:if>
															<s:else>
							                                    <td style="border:0px;text-align:center;">-</td>
															</s:else>
															<s:if test="dis_mdicine_status>0">
							                                    <td style="border:0px;text-align:center;"><s:property value="dis_mdicine_date"/> <s:property value="dis_mdicine_time"/></td>
															</s:if>
															<s:else>
																<td style="border:0px;text-align:center;">-</td>
							                                </s:else>
															<s:if test="dis_bill_status>0">
															     <td style="border:0px;text-align:center;"><s:property value="dis_bill_date"/> <s:property value="dis_bill_time"/></td>
							                                </s:if>
															<s:else>
															     <td style="border:0px;text-align:center;">-</td>
							                               </s:else>
														   <s:if test="dis_nursing_status>0">
														         <td style="border:0px;text-align:center;"><s:property value="dis_nursing_date"/> <s:property value="dis_nursing_time"/></td>
							                               </s:if>
														   <s:else>
															     <td style="border:0px;text-align:center;">-</td>
							                               </s:else>
														   <td style="border:0px;text-align:center;"><s:property value="discharge_end_date"/></td>
														   <td style="border:0px;text-align:center;">
																<s:if test="treatmentstatus==1">
																	<s:property value="dischargeDate"/>
																</s:if>
															</td>
													</tr>
												</s:iterator>
											</tbody>
										</table> 
									</div>
								
        		
        	</div>
						
						
						
</body>



<!-- Discharge Last View  or Discharge Care -->
<div class="modal fade" id="selbed" tabindex="-1" role="dialog" style=" z-index: 9998;"
	aria-labelledby="" aria-hidden="false" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id=""> Select Bed </h4>
				</div>
				<div class="modal-body">
				<s:form theme="simple" action="changebedInitialDischarge" > 
				<div class="row">
				  <s:hidden name="treatmentepisodeid" id="tpeid"></s:hidden>
				  <s:hidden name="ipdid" id="tipdid"></s:hidden>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				    
				  <div class="col-lg-6 col-md-6" style="padding-left: 0px;">
					   <s:select onchange="setBedList(this.value)" theme="simple" list="wardlist" listKey="id" listValue="wardname" 
                                               cssClass="form-control warres" name="wardid"  
                                               headerKey="0" headerValue="Select Ward"/>
				 </div>  
				    
				 <div class="col-lg-6 col-md-6" id="bedlistdiv" style="padding-right: 0px;">
				 	  <s:select  list="bedlist" theme="simple" listKey="id" listValue="bedname" 
                                                   cssClass="form-control" name="bedid" 
                                                   headerKey="0" headerValue="Select Bed"/>
				 </div>
				 </s:form>
				</div>
				</div>
					
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"  >Ok</button>
				</div>
			</div>
			
			<input type="hidden" name="balance" id="balanceid" />     
		</div>
	</div>












<!-- Discharge Popup Copied by Abhi 3 Nov 2017 -->
<div class="modal fade popoverpop" id="initialdischarge" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<input type="hidden" id="isfromdashboard_disc">
  <div class="modal-dialog modal-lg" role="document" style="width: 95%;">
    <div class="modal-content">
      <div class="modal-header hidden-print">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title hidden-print" id="mydislabel">Discharge Status For (Patient Name)</h4>
      </div>
      <div class="modal-body" style="padding:0px !important;">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0px;padding-right: 0px;margin-top: -5px;">
         
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 hidden-print disableback initaiback">
	        	<h3 class="text-center textbomana">Initiate Discharge</h3>
	        	<img src="cicon/initialdischarge.png" alt="..." class="imagestfix">
	        	<h4 class="text-center"><a href="#" onclick="setInitiateDischargeStatus('dis_initiate_status','dis_initiate_time')" type="button" class="btn btn-primary btnwidtfixed"><span id="disinitbtnid">START</span></a></h4>
        	</div>
 				
 			<div id="printThis" class="col-lg-8 col-md-8 col-xs-12 col-sm-12" style="padding: 0px;">
 				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-md visible-print" style="padding: 0px;">
 					<div class=" letterheadhgt" style="height: 135px;">
						<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
								 <link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp" %>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
						<div class="">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
									<div class="col-lg-4 col-md-4 col-xs-4">
									</div>
									<div class="col-lg-4 col-md-4 col-xs-4">
										<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
											<b class="setticolors">DISCHARGE CHECKLIST</b>
										</div>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:0px;padding-right:0px;">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">UHID</b>
								</div>
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Patient Name</b>
								</div>
								
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Age / Gender</b>
								</div>
								
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<p >: <span id="patientIdAbrivationprint"></span></p> 
								</div>
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<p>: <span id="clientnameprint"></span> </p>
								</div>
								 <div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<p >: <span id="agegenderprint"></span></p>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Adm. ID/NO</b>           		 
								</div>
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Ward / Bed</b>
								</div>
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Doctor Name</b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<p>: <span id="admissionidprint"></span></p>            		 
								</div>
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									<p>: <span id="wardbedprint"></span></p> 
								</div>
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
										<p>: <span id="practitionerprint"></span></p> 
								</div>
							</div>
						</div>
					</div>
 				</div>
 				
 			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 disableback">
	        	<h3 class="text-center textbomana">Discharge Form</h3>
	        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
	        		<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
	        			<a href="#"  onclick="showdischargepoupnew()" style="color:#000;"><img src="cicon/dichargeform.png" alt="..." class="imagestfix123"></a>
	        		</div>
	        		<div class="col-lg-8 col-md-8 col-xs-8" style="padding:0px;">
	        			<h4 class="text-center" style="min-height: 50px;padding-top: 12px;" id="disformcompletedid"></h4>
	        		</div>
	        	</div>
        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
        	
        		<section class="widget-todo" fullscreen="isFullscreen04" ng-controller="TodoWidgetCtrl" style="background-color: #fff;">
                      <!-- tile body -->
                      <div class="tile-body" style="padding-top: 6px;    min-height: 593px;" id="disformcheckdiv">
                      		<ul>
        						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Written Order for Discharge</label></li>
        						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage" checked><i></i>Intimation (SMS) to Billing</label></li>
        						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage" checked><i></i>Intimation (SMS) to Pharmacy</label></li>
        						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage" checked><i></i>Intimation (SMS) to Patient Relative</label></li>
        						<li><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Prepared Discharge Summary</label></li>
        					</ul>
                      </div>
                      <!-- /tile body -->
                 </section>
                </div>     
                <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 hidden" style="padding-top: 15px;background-color: #fff;">
                	<div class="form-group">
						<textarea class="form-control" id="exampleFormControlTextarea1" style="background-color: rgba(245, 245, 220, 0.24) !important;" rows="3" placeholder="Write Remark"></textarea>
					</div>
                </div>
        	</div>
        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 disableback">
	        	<h3 class="text-center textbomana">Medicine Kit</h3>
	        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
	        		<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
	        			<a href="#"  onclick="completeMedicine()" style="color:#000;"><img src="cicon/Medicinekit.png" alt="..." class="imagestfix123"></a>
	        		</div>
	        		<div class="col-lg-8 col-md-8 col-xs-8" style="padding:0px;">
	        			<h4 class="text-center" style="min-height: 50px;padding-top: 12px;" id="dismedicinecompletedid"></h4>
	        		</div>
	        	</div>
        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
        		<section class="widget-todo" fullscreen="isFullscreen04" ng-controller="TodoWidgetCtrl" style="background-color: #fff;">
                       <!-- tile body -->
                       <div class="tile-body" style="padding-top: 6px;    min-height: 593px;" id="dismedicalcheckdiv">
                       	<ul>
         						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Received any drug returned</label></li>
         						<li><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Cleared all outstanding bills</label></li>
         					</ul>
                       </div>
                       <!-- /tile body -->
                </section>
              </div>
                <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 hidden" style="padding-top: 15px;background-color: #fff;">
                	<div class="form-group">
						<textarea class="form-control" id="exampleFormControlTextarea1" style="background-color: rgba(245, 245, 220, 0.24) !important;" rows="3" placeholder="Write Remark"></textarea>
					</div>
                </div>
        	</div>
        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 disableback">
	        	<h3 class="text-center textbomana">Billing Settlement</h3>
	        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
	        		<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
	        			<a href="#"  onclick="completeAccount()" style="color:#000;"><img src="cicon/Accountsettle.png" alt="..." class="imagestfix123"></a>
	        		</div>
	        		<div class="col-lg-8 col-md-8 col-xs-8" style="padding:0px;">
	        			<h4 class="text-center" style="min-height: 50px;padding-top: 12px;" id="disbillcompletedid"></h4>
	        		</div>
	        	</div>
	        <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
        		<section class="widget-todo" fullscreen="isFullscreen04" ng-controller="TodoWidgetCtrl" style="background-color: #fff;">
				 	<!-- tile body -->
                    <div class="tile-body" style="padding-top: 6px;    min-height: 593px;" id="disbillingcheckdiv">
                      	<ul>
        						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Received Medical Record file for final Billing</label></li>
        						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Completed Billing Formality</label></li>
        						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Payment Received</label></li>
        						<li><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Printed Final Discharge Invoice & sent Discharge file to ward</label></li>
        					</ul>
                    </div>
                      <!-- /tile body -->
          		</section>
          	</div>
                <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 hidden" style="padding-top:15px;background-color: #fff;">
                	<div class="form-group">
						<textarea class="form-control" id="exampleFormControlTextarea1" style="background-color: rgba(245, 245, 220, 0.24) !important;" rows="3" placeholder="Write Remark"></textarea>
					</div>
                </div>
        	</div>
        	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 disableback">
	        	<h3 class="text-center textbomana">Nursing Advice</h3>
	        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
	        		<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
	        			<a href="#"  onclick="setDischargeStatus('dis_nursing_status','dis_nursing_time')" style="color:#000;"><img src="cicon/nursepateint.png" alt="..." class="imagestfix123"></a>
	        		</div>
	        		<div class="col-lg-8 col-md-8 col-xs-8" style="padding:0px;">
	        			<h4 class="text-center" style="min-height: 50px;padding-top: 12px;" id="disnursingcompletedid"></h4>
	        		</div>
	        	</div>
        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
        	
        		<section class="widget-todo" fullscreen="isFullscreen04" ng-controller="TodoWidgetCtrl" style="background-color: #fff;">
 					<!-- tile body -->
                    <div class="tile-body" style="padding-top: 6px;min-height: 593px;" id="disnursingcheckdiv">
                    	<ul>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Drug returned</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Requested / Handover feedback form to be filled by Patient</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Checked Reports Requested with Actual Completion</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Checked Daily Treatment Record for all treatment given to patient</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Checked billing account for all charges as per Daily Treatment Record</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage" checked><i></i>Completed & Sent Medical Record file to Billing Department</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Removed all invasive lines and catheters</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Checked patient body for dressing, ECG leads, marks, etc</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Receipt Discharge File</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Handover Discharge file to Patient Relative</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Signature taken by patient / relatives in the Discharge File handover Register/ Form</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Instructed after discharge treatment care, prescription dosage, followup etc to patient</label></li>
      						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Collected filled feedback form by patient / relative</label></li>
      						<li><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Received all hospital assets from patient / relative (TV remote, cloths etc)</label></li>
      					</ul>
                    </div>
                    <!-- /tile body -->
                 </section>
                </div>
                 <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 hidden" style="padding-top:15px;background-color: #fff;">
                  	<div class="form-group">
					  <textarea class="form-control" id="exampleFormControlTextarea1" style="background-color: rgba(245, 245, 220, 0.24) !important;" rows="3" placeholder="Write Remark"></textarea>
					</div>
                 </div>
        	</div>
 		</div>     
 				
        
        
       
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 hidden-print disableback completback">
        	<h3 class="text-center textbomana">Complete Discharge</h3>
        	<img src="cicon/completdis.png" alt="..." class="imagestfix">
        	<h4 class="text-center"><a href="#" onclick="endDischarge()" type="button" class="btn btn-primary btnwidtfixed">END</a><br>
        		<!-- <a href="#" id="btnPrint" style="margin-top: 15px;" type="button" class="btn btn-primary btnwidtfixed">PRINT</a> -->
        		<a href="#" onclick="printDiv('printThis')" style="margin-top: 15px;" type="button" class="btn btn-primary btnwidtfixed hidden">PRINT</a>
        		<a href="#" onclick="closeIntitalpopup()" style="margin-top: 15px;" type="button" class="btn btn-primary btnwidtfixed hidden">CLOSE</a>
        	</h4>
        	
        	</div>
       
        	
        	<%-- <s:form action="endIpd" id="enddischargefrm" theme="simple" > --%>
        	<s:form action="endCommonnew" id="enddischargefrm" theme="simple" >
        		<s:hidden name="isfromdischargedashbaord" id="isfromdischargedashbaord" value="1"></s:hidden>
        		<s:hidden name="clientid" id="endclientid"/>
        		<s:hidden name="clientip" id="endclientip"/>
        		<s:hidden name="treatmentEpisode" id="endtreatmentEpisode"/>
        	</s:form>
        </div>
        
      </div>
     <div class="modal-footer" style="padding:0px;">
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
	</div>
    </div>
  </div>
</div>
<!-- Discharge Last View  or Discharge Care -->
<div class="modal fade" id="viedschnadv" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="">Discharge Nursing Advoice</h4>
				</div>
				<div class="modal-body">
				<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				 <p id="piddvadv">
				 
				 </p>
				 <br>
				 <div class="col-lg-6 col-md-6">
					   <input type="button" value="Complete" onclick="doup('dis_nursing_status','dis_nursing_time')" class="btn btn-primary" />
				 </div>
				</div>
				</div>
					
				</div>
				<div class="modal-footer">
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


<div class="modal fade" id="readmittpopup" tabindex="-1" role="dialog" style="  z-index: 9997;"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm" style="width: 30%">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="">Confirm Readmit Patient?</h4>
				</div>
				<div class="modal-body">
				<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 form-inline">
				<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 form-group">
   					<label> Select Ward :</label>
   					</div>
   					<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 form-group"> 
   					<s:select name="" list="wardlist" listKey="id" listValue="wardname" cssClass="form-control chosen-select" headerKey="0" id="wardname" headerValue="All Wards" theme="simple" onchange = "getbeds(this.value)" style="width:37% !important"></s:select></p>
   				</div>
   				</div>
   				<br>
   				<br>
   				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 form-inline">	
   					<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 form-group">
   					<label> Select Bed :</label>
   					</div>
   					<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 form-group" id="bedlist"> 
   					</div>
   					</div>
   					<br>
   				<br>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				    
				     <textarea rows="2" placeholder="enter notes" cols="5" name="admitnotes" id="admitnotes" class="form-control"></textarea>
				</div>
				</div>
					
				</div>
				<div class="modal-footer">
				    <button  type="button" class="btn btn-primary" onclick="cancelDischarge()" >Ok</button>
					<button  type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>




<div class="modal fade" id="viewdch" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="">Final Invoicing still not Settled </h4>
				</div>
				<div class="modal-body">
				<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				 <p>
				 	"Final Invoicing is still not Settled". Please complete it before discharging.
				 </p>
				 <br>
				 <div class="col-lg-6 col-md-6">
				 	<input type="button" value="View Account" onclick="openviewAccount()" class="btn btn-primary" /> 
				 </div>
				 <div class="col-lg-6 col-md-6">
					   <input type="button" value="Its ok, Discharge" onclick="submitdischarge()" class="btn btn-primary" />
				 </div>
				</div>
				</div>
					
				</div>
				<div class="modal-footer">
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
			
			<input type="hidden" name="balance" id="balanceid" />     
		</div>
	</div>

   
<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);z-index: 10000000" id="circleloading" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" style="z-index: 100000">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	



<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

<script>
$(function() {
	  $('.dischpoints').slimScroll({
	      height: '520px',
	      railVisible: true,
		  alwaysVisible: true
	});
});

</script> 


<script type="text/javascript">
document.getElementById("btnPrint").onclick = function() {
    printElement(document.getElementById("printThis"));
    window.print();
}

function printElement(elem, append, delimiter) {
    var domClone = elem.cloneNode(true);

    var $printSection = document.getElementById("printSection");

    if (!$printSection) {
        var $printSection = document.createElement("div");
        $printSection.id = "printSection";
        document.body.appendChild($printSection);
    }

    if (append !== true) {
        $printSection.innerHTML = "";
    }

    else if (append === true) {
        if (typeof(delimiter) === "string") {
            $printSection.innerHTML += delimiter;
        }
        else if (typeof(delimiter) === "object") {
            $printSection.appendChlid(delimiter);
        }
    }

    $printSection.appendChild(domClone);
}
    </script>

<script>
    function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
    //document.body.innerHTML = oldPage;

    //Print Page
    setTimeout(function () {
        print_page();
    }, 2000);

    function print_page() {
        window.print();
    }

    //Restore orignal HTML
    setTimeout(function () {
        restore_page();
    }, 3000);

    function restore_page() {
        document.body.innerHTML = oldPage;
    }
}
	</script>

</html>
 
