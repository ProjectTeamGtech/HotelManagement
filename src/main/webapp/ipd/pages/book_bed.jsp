<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.*"%>
<%@ taglib uri="/struts-tags"  prefix="s"%> 


<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

 <!-- vendor css files -->
      
        <!--/ stylesheets -->
        <%@page import="com.apm.common.utils.DateTimeUtils"%>
		<%@page import="java.util.Calendar"%>
		<link rel="stylesheet" href="_assets/css/priscription/Notification.css">
         <link rel="stylesheet" href="_assets/css/priscription/hospitalresponsive.css">
         <link href="common/assets/css/style.css" rel="stylesheet" />
         
    <script type="text/javascript" src="ipd/js/discharge.js"></script>  
    <script type="text/javascript" src="ipd/js/package.js"></script> 
    <script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
    <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>  
    <script type="text/javascript" src="emr/js/addnursingcare.js"></script>
    
	 <!-- BookBed.jsp Javascript -->
	 <script type="text/javascript" src="emr/js/addInvestigation.js"></script>
	  <script type="text/javascript" src="ipd/js/addcharge.js"></script>
      <script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
	  <script type="text/javascript" src="diarymanagement/js/sendsms.js"></script>
	  <script type="text/javascript" src="diarymanagement/js/sendApmtAttachnment.js"></script>
	  
	    <script type="text/javascript" src="emr/js/clinical_notes.js"></script>
	  
	  <script type="text/javascript" src="tools/js/sendLetter.js"></script>
     <!-- /BookBed.jsp -->    
     
     <!-- jQuery File Upload Dependencies -->
<%-- <script src="common/assets/js/jquery.ui.widget.js"></script>
<script src="common/assets/js/jquery.iframe-transport.js"></script>  --%>
<script src="common/assets/js/jquery.fileupload.js"></script>


<!-- JavaScript Includes -->
<script src="common/assets/js/jquery.knob.js"></script> 
<script src="common/assets/js/script.js"></script> 
   
      <link rel="stylesheet" href="plugin/slidervitals/infinityCarousel.css">
      
      
      <script type="text/javascript" src="plugin/slidervitals/infinityCarousel.js"></script>
      	<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
	<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>
 
    <style>
.loktable th{
text-align:center;
border: 2px dashed;
height: 50px;
}
.loktable td{
text-align:center;
border: 2px dashed;
height: 50px;
}    
    .lkclass th{
text-align: center !important;
}

.lkclass td{
text-align: center !important;
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
#upload {
    background-color: #fff;
    padding: 0px;
    border-radius: 0px;
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
				min-height: 560px;
			}
			.initaiback{
				background-color: rgba(255, 165, 0, 0.62) !important;
				    min-height: 560px;
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

.fa-2x {
    font-size: 20px !important;
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

@media (max-width: 768px){
.bookbedboxirf{margin-left: 62px;}
 
	
}
@media (max-width: 960px){
.bookbedboxirf{margin-left: 60px;}
 
	
}

@media (max-width: 991px){

.bookbedboxirf{margin-left: 60px;} 
}

@media (max-width: 600px){
.searchbookbedjsp{width: 30.10%;}
.searchbookbedjspwidth{width: 173px;} 
.bookbedboxirf {
   margin-left: -21px !important;
}
}
@media (max-width: 601px){
.navnavtabstabsdarkleftmarg {
    margin-left: 0px;
}
.searchbookbedjsp{width: 30.10%;}
.searchbookbedjspwidth{width: 173px;} 
.bookbedboxirf {
   margin-left: -21px !important;
}
}


.modalfollowup {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1000000000 !important; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-contentfollowup {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 60%;
    z-index: 5;
}

/* The Close Button */
.closefollowup {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.closefollowup:hover,
.closefollowup:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

 
        </style>
<script>
bkLib.onDomLoaded(function() {
	
	
   
	// new nicEditor().panelInstance('emailBodyLetter');
	 new nicEditor({maxHeight : 2500}).panelInstance('emailBodyLetter');
	
	 
	 $('.nicEdit-panelContain').parent().width('100%');
	 $('.nicEdit-panelContain').parent().next().width('98%');
	 
	 $('.nicEdit-main').width('98%');
	
	 //document.getElementById("user").disabled = 'disabled';
	
});



</script>

<script>
window.onload = function () {
 
 currencySign = '.Rs'; 
 /* getbellcolor(); */

}
</script>


<script>

 $(document).ready(function(){
	  
  //called when key is pressed in textbox
  $("#quantity").keypress(function (e) {
     //if the letter is not digit then display error and don't type anything
     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#errmsg").html("Digits Only").show().fadeOut("slow");
               return false;
    }
   });
  
  
   /* setInterval(function(){ 
    getbellcolor();  
    callsendsms();
   }, 1000*60); */
  
   $("#fdt1").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
   $("#tdt1").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
  
    $("#vdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
    $("#bdate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
    var todayDate = new Date().getDate();
     $("#stddate").datepicker({

	   dateFormat : 'dd-mm-yy',
	   yearRange: yearrange,
	   minDate : '30-12-1880',
	   changeMonth : true,
	   changeYear : true,
	   maxDate: new Date(new Date().setDate(todayDate))
	 });
	   $("#enddate").datepicker({
	
	   dateFormat : 'dd-mm-yy',
	   yearRange: yearrange,
	   minDate : '30-12-1880',
	   changeMonth : true,
	   changeYear : true,
	   maxDate: new Date(new Date().setDate(todayDate))
	 });
	 
	 $("#editstddate").datepicker({

		   dateFormat : 'dd-mm-yy',
		   yearRange: yearrange,
		   minDate : '30-12-1880',
		   changeMonth : true,
		   changeYear : true,
		   maxDate: new Date(new Date().setDate(todayDate))
		 });
	  $("#pkgfromdate").datepicker({

	   dateFormat : 'dd-mm-yy',
	   yearRange: yearrange,
	   minDate : '30-12-1880',
	   changeMonth : true,
	   changeYear : true
	
	 });
	   
	   $("#pkgtodate").datepicker({
	
	   dateFormat : 'dd-mm-yy',
	   yearRange: yearrange,
	   minDate : '30-12-1880',
	   changeMonth : true,
	   changeYear : true
	
	 });
    
    
  
  });

</script>
     <style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style>
   
     <!-- ====================================================
        ================= Application Content ===================
        ===================================================== -->
        <div id="wrap" class="animsition" >

           
                                <!-- tile -->
                                <section class="tile tile-simple">


                                    <!-- tile body -->
                                    <div class="tile-body p-0">
                                        <div>
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs tabs-dark navnavtabstabsdarkleftmarg topone" role="tablist">
                                                <li class="hidden" role="presentation"><a href="#Small" aria-controls="Small" role="tab" data-toggle="tab"><i class="fa fa-th" style="color:#424a5d;"></i></a></li>
                                                <li role="presentation" class="active"><a href="#Medium" aria-controls="Medium" role="tab" data-toggle="tab"><i class="fa fa-th-large" style="color:#424a5d;"></i></a></li>
                                                <!--<li role="presentation"><a href="#Large" aria-controls="Large" role="tab" data-toggle="tab">Large</a></li>-->
                                                <li><a href="#" onclick="loadcommonbell()"  title="Notification"><i class="fa fa-bell" style="color:#424a5d;"></i></a></li>
                                                
                                                <!-- repeat code for desktop -->
                                                <li style="cursor: default;border-right: 1px solid rgba(0, 0, 0, 0.1);text-align: center;cursor:pointer;" class="bedcount " title="Discharge Initiated" onclick="openIpdPopup('InitialDischarge?filter=1&maintype=1')"><span><span class="dischargedisplaynone">Initiated</span> <br><span style="color: #e05d6f;font-size: 16px;font-weight: bold;"><s:property value="totolintitaldischarge"/></span></span> </li>
                                                <li style="cursor: default;border-right: 1px solid rgba(0, 0, 0, 0.1);text-align: center;cursor:pointer;" class="bedcount " title="Discharged Patient" onclick="openIpdPopup('InitialDischarge?filter=6&maintype=2')"><span><span class="dischargedisplaynone">Discharged</span><br><span style="color: #e05d6f;font-size: 16px;font-weight: bold;"><s:property value="totaldischarge"/></span></span> </li> 
                                                <li style="cursor: default;border-right: 1px solid rgba(0, 0, 0, 0.1);text-align: center;" class="bedcount " title="Inhouse Patient" onclick="openPopup(' currentpatientsSummary')"><span><span class="dischargedisplaynone">Inhouse Patient </span><br><span style="color: #e05d6f;font-size: 16px;font-weight: bold;"><s:property value="totalbookedbed"/></span></span> </li>
                                                <li style="cursor: default;text-align: center;" class="bedcount"><span title="Total Bed"><span class="dischargedisplaynone">Total Beds</span> <br> <span style="color: #e05d6f;font-size: 16px;font-weight: bold;"><s:property value="totalbed"/></span></span> </li>
                                                
                                                <!-- repeat code for responsive -->
                                               
                                                
                                                <li class="hidden"><a href="#" style="color:#fff;" onclick="printIpdExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a></li>
                                            </ul>
											<s:form action="IpdDashboard" id="ipddashboardfrm"  theme="simple">
												<s:hidden name="androidpractid"></s:hidden>
												<s:hidden name="isfromandroid"></s:hidden>
												<s:hidden name="androidpractuserid"></s:hidden>
												<s:hidden name="excessamtbt" id="excessamtbt"></s:hidden>
							                    <!-- Right-side navigation -->
							                    <ul class="nav-right pull-right list-inline marto36 uiphoset searchbookbedjsp">
							                    <li class="uiphoset12" style="width: 48%;">
							                    	<form id="live-search" action="" class="styled" method="post">
														   <input type="text" placeholder="Search Patient/Bed" id="filter" value="" class="form-control searchbookbedjspwidth ipdsearchbox" style="text-transform: uppercase;"/>
														</form>
							                       </li>
							                      <li class="lihidden">
							                      <s:if test="casualtyipd==2">
							                      </s:if>
							                      <s:else>
							                          <s:select name="wardid" id="wardid" list="wardList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="All" onchange="showWardBed(this.value)"/>
							                      </s:else>
							                      </li>
							                      <%-- 
							                        <li>
							                         
							                            <s:select list="#{'0':'Sort','1':'By Available','2':'By Booked'}" theme="simple" name="filter_status" onchange="showWardBed(this.value)" cssClass="form-control"  ></s:select>
							                        </li> --%>
							                        
							                         <li class="lihidden">
							                            <s:select list="#{'0':'Inactive Bed','1':'Active Bed'}" theme="simple" name="activefilter" onchange="showBedstatus(this.value)" cssClass="form-control"  ></s:select>
							                        </li>
							                        <li class="lihidden">
							                        	<input type="submit" class="btn btn-primary" onclick="submitExcessamtbt()" value="Execss Amt">
							                        </li>
							                    </ul>
							                    <!-- Right-side navigation end -->
							
							 				</s:form>
                                            <!-- Tab panes -->
                                            <%-- <div class="hidden-print" >
								<ul class="breadcrumb">
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getName()%></li>
										<%}else{ %>
											<%if(breadcrumbs.getOn()){ %>
												<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
											<%}else{ %>
												<li><%=breadcrumbs.getName()%></li>
											<%} %>
										<%} %>
										
									<%} %>
								</ul>
							</div> --%>
                                            <div class="tab-content">

                                                <div role="tabpanel" class="tab-pane" id="Small">

                                                    <div class="wrap-reset">
                                                    
                                                        <!-- row -->
                                                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;margin-left: -11px;">
                                                        	<%-- <%@ include file="/ipd/pages/bedcode.jsp"%> --%>
                                                        </div>
                                                        <!-- /row -->
														
                                                    </div>

                                                </div>

                                                <div role="tabpanel" class="tab-pane medumclass active" id="Medium">
                                                

                                                    <div class="wrap-reset">
                                                      
                                                        <!-- row -->
                                                        <br>
                                                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 bookbedboxirf" >
                                                      		<%@ include file="/ipd/pages/bedcode1.jsp" %>
                                                        </div>
                                                        <!-- /row -->
                                                        
                                                    </div>
                                                  
                                      	        

                                                </div>
                                            </div>

                                        </div>

                                    </div>
                                    <!-- /tile body -->

                                </section>
                                <!-- /tile -->

                 
            <!--/ CONTENT -->






        </div>
        <!--/ Application Content -->
   
     <!-- The modal For fo;;ow up -->
<div id="followupmodal" class="modalfollowup">

  <!-- Modal content -->
  <div class="modal-contentfollowup">
    <span class="closefollowup" id="followclose">&times;</span>
    <br>
    <p><b>Follow Up Date</b>  &nbsp; &nbsp; &nbsp; &nbsp;<input style="width:20%" type ="text" id="lokeshfollowdatenew" class="form-control" name="followupdatexyz"> &nbsp; &nbsp; &nbsp;<button class="btn btn-primary" onclick="setfollowupdata()" required="required"">Set Follow up</button></p>
  </div>

</div>
     
     
     <!--Vitals  Modal Design By Abhi 16oct2017 -->
	<div id="vitals" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Daily Care for <label id="pnamevital"></label> &nbsp;|&nbsp; <a href="#" title="Edit"><i class="fa fa-pencil" style="color: #fff;"></i></a> &nbsp;|&nbsp; <a href="#" onclick="PrintDiv123();" title="Print"><i class="fa fa-print" style="color: yellow;"></i></a></h4>
	        <input type="hidden" id="vitaltime" value="00.00" />
	      </div>
	      <div class="modal-body" style="padding: 0px !important;background-color: rgba(221, 221, 221, 0.28);">
	      <div class="row">
	      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: -5px;">
	      		<div role="tabpanel" style="padding: 0px !important;">
	      			<ul class="nav nav-tabs" role="tablist" style="background-color: #fff;">
                        	<li role="presentation" class="active" ><a href="#vitasl123" aria-controls="vitasl123" role="tab" data-toggle="tab" aria-expanded="true">Vitals</a></li>
                            <li role="presentation"><a href="#inout" aria-controls="inout" role="tab" data-toggle="tab" aria-expanded="true">Intake/Output</a></li>
                            <li role="presentation"><a href="#iv" aria-controls="iv" role="tab" data-toggle="tab" aria-expanded="false"> IV</a></li>
                            <li role="presentation"><a href="#equipment" aria-controls="equipment" role="tab" data-toggle="tab" aria-expanded="false"> Equipment</a></li>
                        </ul>
                        <%int k=0; %>
                        <!-- Tab panes -->
                        <div class="tab-content">
                        	<div role="tabpanel" class="tab-pane active" id="vitasl123" style="padding: 0px !important;">
                        		<div class="tile-body">
                        			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="infinity-carousel">
										  <button class="nav prev"></button>
										  <button class="nav next" title="Save & Next"></button>
										  <div class="center" >
										    <div class="slides">
										    
										     <s:iterator value="vitalMasterList">
										      <% if(k==0) { %>
										     <div class="active"> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a> 
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} else { %>
										         <div class=""> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>)</h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} k++;  %>
										      </s:iterator>
										     <div class="hidden"> 
										         anything in here 2
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										     <!--  
										      <div class=""> 
										         anything in here 3
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										      <div class=""> 
										         anything in here 4
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div>  -->
										     
										    </div>
										  </div>
										</div>
									<div id="dvContents" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:0px;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md  visible-print" style="background-color: #fff;border-bottom:1px solid #ddd;">
											<center><h3>Vitals</h3></center>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
												<label>Patient Name:</label>
												<span>Mr. Nitin R Gawande</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Age / Gender:</label>
													<span>28 yr / Male (Self)</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Ward / Bed:</label>
													<span>1F-CASUALTY 135 </span>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
													<label>Date / Time:</label>
													<span>16-10-2017 / 06:34:45</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Heart Beat:</label>
													<span>80 BPM</span>
												</div>
											</div>
										</div>
									
										<div id="vitalgraph" class="collapse">
											
										</div>
									</div>
						        </div>
                        		</div>
                        	</div>
                        	<%k=0; %>
                        	<div role="tabpanel" class="tab-pane" id="inout" style="padding: 0px !important;">
                        		<div class="tile-body">
                        		    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="infinity-carousel">
										  <button class="nav prev"></button>
										  <button class="nav next" title="Save & Next"></button>
										  <div class="center" >
										    <div class="slides">
										    
										     <s:iterator value="vitalMasterIOList">
										      <% if(k==0) { %>
										     <div class="active"> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} else { %>
										         <div class=""> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} k++;  %>
										      </s:iterator>
										      
										     <div class="hidden"> 
										         anything in here 2
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										     <!--  
										      <div class=""> 
										         anything in here 3
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										      <div class=""> 
										         anything in here 4
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div>  -->
										     
										    </div>
										  </div>
										</div>
									<div id="dvContents" class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md  visible-print" style="background-color: #fff;border-bottom:1px solid #ddd;">
											<center><h3>Vitals</h3></center>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
												<label>Patient Name:</label>
												<span>Mr. Nitin R Gawande</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Age / Gender:</label>
													<span>28 yr / Male (Self)</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Ward / Bed:</label>
													<span>1F-CASUALTY 135 </span>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
													<label>Date / Time:</label>
													<span>16-10-2017 / 06:34:45</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Heart Beat:</label>
													<span>80 BPM</span>
												</div>
											</div>
										</div>
									
									</div>
						        </div>
                        		</div>
                        	</div>
                        	<%k=0; %>
                        	<div role="tabpanel" class="tab-pane" id="iv" style="padding: 0px !important;">
                        		<div class="tile-body">
                        		    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="infinity-carousel">
										  <button class="nav prev"></button>
										  <button class="nav next" title="Save & Next"></button>
										  <div class="center" >
										    <div class="slides">
										    
										     <s:iterator value="vitalMasterIVList">
										      <% if(k==0) { %>
										     <div class="active"> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} else { %>
										         <div class=""> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} k++;  %>
										      </s:iterator>
										      
										     <div class="hidden"> 
										         anything in here 2
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										     <!--  
										      <div class=""> 
										         anything in here 3
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										      <div class=""> 
										         anything in here 4
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div>  -->
										     
										    </div>
										  </div>
										</div>
									<div id="dvContents" class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md  visible-print" style="background-color: #fff;border-bottom:1px solid #ddd;">
											<center><h3>Vitals</h3></center>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
												<label>Patient Name:</label>
												<span>Mr. Nitin R Gawande</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Age / Gender:</label>
													<span>28 yr / Male (Self)</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Ward / Bed:</label>
													<span>1F-CASUALTY 135 </span>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
													<label>Date / Time:</label>
													<span>16-10-2017 / 06:34:45</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Heart Beat:</label>
													<span>80 BPM</span>
												</div>
											</div>
										</div>
									
										<div id="vitalgraph" class="collapse">
											<div id="vgraph1" style="min-width: 758px; height: 400px; margin: 0 auto"></div>
										</div>
									</div>
						        </div>
                        		</div>
                        	</div>
                        	<%k=0; %>
                        	<div role="tabpanel" class="tab-pane" id="equipment" style="padding: 0px !important;">
                        		<div class="tile-body">
                        		  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="infinity-carousel">
										  <button class="nav prev"></button>
										  <button class="nav next" title="Save & Next"></button>
										  <div class="center" >
										    <div class="slides">
										    
										     <s:iterator value="vitalMasterEquipmentList">
										      <% if(k==0) { %>
										     <div class="active"> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} else { %>
										         <div class=""> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} k++;  %>
										      </s:iterator>
										      
										     <div class="hidden"> 
										         anything in here 2
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										     <!--  
										      <div class=""> 
										         anything in here 3
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										      <div class=""> 
										         anything in here 4
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div>  -->
										     
										    </div>
										  </div>
										</div>
									<div id="dvContents" class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md  visible-print" style="background-color: #fff;border-bottom:1px solid #ddd;">
											<center><h3>Vitals</h3></center>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
												<label>Patient Name:</label>
												<span>Mr. Nitin R Gawande</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Age / Gender:</label>
													<span>28 yr / Male (Self)</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Ward / Bed:</label>
													<span>1F-CASUALTY 135 </span>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
													<label>Date / Time:</label>
													<span>16-10-2017 / 06:34:45</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Heart Beat:</label>
													<span>80 BPM</span>
												</div>
											</div>
										</div>
										<div id="vitalgraph" class="collapse">
											<div id="vgraph1" style="min-width: 758px; height: 400px; margin: 0 auto">
											</div>
																							<script>
												//vital graph created by abhi
												
												Highcharts.chart('vgraph1', {
												    chart: {
												        type: 'line'
												    },
												    title: {
												        text: 'Graph'
												    },
												    subtitle: {
												        text: 'Dt:16-10-2017'
												    },
												    xAxis: {
												        categories: ['1hr', '2hr', '3hr', '4hr', '5hr', '6hr', '7hr', '8hr', '9hr', '10hr', '11hr', '12hr']
												    },
												    yAxis: {
												        title: {
												            text: 'Temperature (°C)'
												        }
												    },
												    plotOptions: {
												        line: {
												            dataLabels: {
												                enabled: true
												            },
												            enableMouseTracking: false
												        }
												    },
												    series: [{
												        name: 'XYZ',
												        data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
												    }, {
												        name: 'ABC',
												        data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
												    }]
												});
												</script>
										</div>
									</div>
						        </div>
                        		</div>
                        	</div>
                        </div>
	      		</div>
	      	
	      	</div>
	      
	      	
	      </div>
	      </div>
	      <div class="modal-footer hidden">
	        <button type="button" class="btn btn-primary">Save</button>
	      </div>
	    </div>
	  </div>
	</div>
     
     
     
     
     <!-- Nursing Notes Modal -->
     <!-- Design By Abhi -->
     <div id="nursingnotes" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-sm">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Nursing Notes</h4>
	      </div>
	      <div class="modal-body">
	      <div class="row">
	      	<div class="col-lg-12 col-md-12">
	      		<div class="col-lg-6 col-md-6" style="padding: 0px;">
	      			<div class="from-group">
	        		<label>Nursing Staff</label>
	        		<p>Aruna Verma</p>
	        	</div>
	      		</div>
	      		<div class="col-lg-6 col-md-6" style="padding: 0px;">
	      			<div class="from-group">
	        		<label>Shift / Schedule</label>
	        		<p>Morning / 8.00 to 12.00</p>
	        	</div>
	      		</div>
	        	
	        </div>
	      </div>
	      <div class="row" style="margin-top: 15px;">
	      	<div class="col-lg-12 col-md-12">
	        	<div class="from-group">
	        		<label>Write Notes</label>
	        		<textarea rows="3" cols="3" class="form-control" style="background-color: rgba(255, 248, 220, 0.43) !important;"></textarea>
	        	</div>
	        </div>
	      </div>
	      	
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary">Save</button>
	      </div>
	    </div>
	  </div>
	</div>
     
     
  
     <!-- Booked BedModal -->
      <div class="modal fade modal-draggable" id="ipdpopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true" data-keyboard="false" data-backdrop="static">
          <div class="modal-dialog modal-md">
              <div class="modal-content">
                  <div class="modal-header bg-primary">
                      <button type="button" class="close" onclick="goreferesh()">
                          <span aria-hidden="true">&times;</span><span class="sr-only" >Close</span>
                      </button>
                       <%
        String dischargefun1=""; 
        if(!loginfo.isNewdischargecard()){
        	dischargefun1="opennewdis()";
        	
        }else{
        	dischargefun1="showdischargepoup()";
        } %> 
                      <h4 class="modal-title">Ward:&nbsp; <span id="ipdbednoid"></span>&nbsp; |&nbsp; Name&nbsp;:  &nbsp;<span id="ipdclientnameid"></span> &nbsp;|&nbsp; Age&nbsp;:&nbsp; <span class="marlefclock4" id="ipdageid"></span> &nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#" style="color: yellow;" onclick="showipdprintpoup()" title="Print Admission Form"><i class="fa fa-print" aria-hidden="true"></i></a></span> &nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#" style="color: yellow;" onclick="showdischargeprintpage()" title="Print Discharge Form"><i class="fa fa-print" aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#" style="color: yellow;" onclick="openEditClientPrintPopupIpd()" title="Edit Profile"><i class="fa fa-pencil" aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#" style="color: yellow;" onclick="sendLetterPopup()" title="Send Email"><i class="fa fa-envelope-o" aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#" style="color: yellow;" onclick="sendsmsipdpopupopen()" title="Send SMS"><i class="fa fa-commenting-o" aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#" style="color: yellow;" onclick="printlabel()" title="Print label"><i class="fa fa-tag" aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span id="editSave1"><a href="#" style="color: yellow;" onclick="getdataofbmi()" title="Edit"><i class="fa fa-medkit" aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a onclick="<%=dischargefun1 %>"  href="#" style="color: yellow;" ><i class="fa fa-camera-retro" aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span id=""><a href="#" style="color: yellow;" onclick="ipdbcodegen()" title="Barcode"><i class="fa fa-barcode" aria-hidden="true"></i></a></span>&nbsp;&nbsp;</h4>
                  </div>
                  <div class="modal-body">
                   <div class="hidden" id="ipddaytodayheaderaction">
                      <a href="#"> praful G</a> 
                      <a href="#" title="Edit Client Record" ><img src="_assets/img/edit.png"></a> 
                      <a href="#" title="Log" ><img src="_assets/img/log.png"></a>  
                      <a href="#" title="EMR" ><img src="_assets/img/emr.png"></a>  
                      <a href="#" title="Assessment Form" ><img src="_assets/img/asmnt.png"></a>
                      </div> 


                      
                      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padnil">
                      <!-- lokesh -->
                      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 heightsetbmi" >
                      <div class="hidden" id="bmiid">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Height <small>cm</small></label>
										<input type="number" id="height" onchange="calbmi()"style="width:80%">
										<p id="height2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Weight <small>Kg's</small></label>
										<input type="number" id="weight" style="width:80%" onchange="calbmi()">
										<p id="weight2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">BMI</label>
										<input type="number" id="bmi" style="width:80%">
										<p id="bmi2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Pulse</label>
										<input type="number" id="pulse" style="width:80%">
										<p id="pulse2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sys-BP</label>
										<input type="number" id="sysbp" style="width:80%">
										<p id="sysbp2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Dia-BP</label>
										<input type="number" id="diabp" style="width:80%">
										<p id="diabp2"></p>
									</div>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sugar Fasting</label>
										<p id="sugarfasting2"></p>
										<input type="number" id="sugarfasting" style="width:80%">
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Post Meal</label>
										<input type="number" id="postmeal" style="width:80%">
										
										<p id="postmeal2"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">BSA</label>
										<input type="number" id="bsa" style="width:80%">
										<p id="bsa2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								</div>
							</div>
							</div>
							</div>
                      
                          <div class="col-lg-3 col-md-3 hidden-sm hidden-xs" style="">
                              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bookdebedcss popupbedheaight popupbedpoup width100">
                                  
                                  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padnil" id="timagename">
                                  <img src="popicons/avatar2.png" style="width:100%;">
                                  </div>
                                      

                                      <div class="row maboticsa hidden">
                                          
                                          <div class="col-lg-6 col-md-6 col-xs-6">
                                              <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 dateleft">
                                                  <i class="fa fa-birthday-cake bigfont whitecolr"></i>
                                              </div>
                                              <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 calentext padleftnil" >
                                                 <p id="ipddobid"></p> 
                                              </div>
                                          </div>


                                      </div>
                                      <div class="row maboticsa hidden">
                                          <div class="col-lg-2 col-md-2 col-xs-2">
                                              <i class="fa fa-home bigfont whitecolr"></i>
                                          </div>
                                          <div class="col-lg-9 col-md-9 col-xs-9 padleftnil" >
                                             <p id="ipdtownid"></p> 
                                          </div>
                                      </div>
                                      <div class="row maboticsa hidden">
                                          <div class="col-lg-2 col-md-2 col-xs-2">
                                              <i class="fa fa-user-md bigfont whitecolr"></i>
                                          </div>
                                          <div class="col-lg-9 col-md-9 col-xs-9 padleftnil" >
                                            <p id="ipddoctornameid"></p>  
                                          </div>
                                      </div>
                                      
                                      <div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 7px;padding: 0px;">
                                      <s:if test="casualtyipd==0">
                                             <span style="display: inline-flex;">IPD NO: <p id="ipdadmissionid"></p> </span>
                                       </s:if>
                                         <s:elseif test="casualtyipd==2">
                                             <span style="display: inline-flex;">DC ID: <p id="ipdadmissionid"></p> </span>
                                       </s:elseif>
                                       <s:else>
                                       		<span style="display: inline-flex;">Cau ID: <p id="ipdadmissionid"></p> </span>
                                       </s:else> 
                                      </div>
                                      <div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 7px;padding: 0px;">
                                             <span style="display: inline-flex;">UHID:  <p id="ipdpatientid"></p>  </span>
                                      </div>
                                      
                                     <%--  <div class="col-lg-5 col-md-5 col-xs-5" style="margin-top: 7px;padding: 0px;">
                                      <s:if test="casualtyipd==0">
                                             <span style="display: inline-flex;">IPD ID: <p id="ipdadmissionid"></p> </span>
                                       </s:if>
                                       <s:else>
                                       		<span style="display: inline-flex;">Cau ID: <p id="ipdadmissionid"></p> </span>
                                       </s:else> 
                                      </div>
                                      <div class="col-lg-7 col-md-7 col-xs-7" style="margin-top: 7px;text-align: right;padding: 0px;">
                                             <span style="display: inline-flex;">UHID:  <p id="ipdpatientid"></p>  </span>
                                      </div> --%>
                                      
                                      <!--
                                      <div class="row fontad">
                                          <div class="col-lg-6 col-md-6 col-xs-6">
                                              Patient Code:
                                          </div>
                                          <div class="col-lg-4 col-md-4 col-xs-4 padleftnil">
                                             <p> 2014/07/843</p>
                                          </div>
                                      </div>
                                  
                              --></div>
                              <div class="col-lg-12 col-md-12 col-xs-12 hidden">
                              		<p>Ward : <span>Genral / 07</span></p>
                              		<p>Name : <span>Mr. Ajay Joshi</span></p>
                              		<p>Age : <span>48 yr</span></p>
                              		<p>City : <span>Nagpur</span></p>
                              		<p>Consultant : <span>Dr.Ookalkar</span></p>
                              </div>
                              
                         <div id="ipddaytodayheaderaction" class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
                         <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden">
                         <a href="#" > praful G</a> 
                         </div>
                          
                          
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;padding-right: 0px;"> 
                         	<section class="tile widget-chat">
                                <!-- tile body -->
                                <div class="tile-body nursenote" style="padding: 0px;" id="nursemsgdiv">
                                    <%-- <ul class="chats">
                                        <li class="">
                                            <div class="media">
                                                <div class="media-body">
                                                    <p class="media-heading"><a role="button" tabindex="0" class="name" style="color: #16a085;">Aruna charbe </a><br><span class="datetime">15/10/2017 11:33 AM</span></p>
                                                    <span class="body" style="font-size: 10px;text-align: justify;">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </span>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="">
                                            <div class="media">
                                                <div class="media-body">
                                                    <p class="media-heading"><a role="button" tabindex="0" class="name" style="color: #16a085;">Abhi sharma</a><br><span class="datetime">14/10/2017 11:33 AM</span></p>
                                                    <span class="body" style="font-size: 10px;text-align: justify;">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </span>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="">
                                            <div class="media">
                                                <div class="media-body">
                                                    <p class="media-heading"><a role="button" tabindex="0" class="name" style="color: #16a085;">Jaya verma </a><br><span class="datetime">13/10/2017 11:33 AM</span></p>
                                                    <span class="body" style="font-size: 10px;text-align: justify;">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </span>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
 --%>
                                </div>
                                <!-- /tile body -->

                                <!-- tile footer -->
                                <div class="tile-footer" style="padding: 10px 0px 0px 0px;">
                                    <div class="chat-form" style="padding: 2px;">
                                        <div class="input-group">
                                            <input type="text" id="nursemsg" class="form-control" placeholder="Type notes here...">
                                                <span class="input-group-btn">
                                                    <button class="btn btn-default" onclick="saveNurseNoteAjax()" type="button"><i class="fa fa-chevron-right"></i></button>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                                <!-- /tile footer -->
                            </section>
                        </div>
                         </div>
                          </div>
                          <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 paddingrih">
                          <md-content class="md-padding checkboxdemoBasicUsage checkboxDemo1"> 
                          	<fieldset class="standard"> 
                          		<legend>Patient</legend> 
                          		<div layout="row" layout-wrap="" class="layout-wrap layout-row"> 
		                			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
		                				<%if(loginfo.isIpd_admission()){ %>
		                                  <a href="#" onclick="openeditipd()">
		                                      <div class="thumbnail">
		                                          <img src="popicons/edit.png" alt="..." class="">
		                                          <div class="caption"><p align="center" class="fontpup">Admission</p></div>
		                                      </div>
		                                  </a>
						              <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										      <img src="popicons/edit.png" alt="..." class="">
		                                          <div class="caption"><p align="center" class="fontpup">Admission</p></div>
									    	</div>
				    					<%} %>
		                              </div>
		                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
		                              <%if(loginfo.isIpd_declaration()){ %>
		                                  <a href="#" onclick="openClientPrintPopupIpd()">
		                                      <div class="thumbnail">
		                                          <img src="popicons/eye.png" alt="..." class="">
		                                          <div class="caption"><p align="center" class="fontpup">Declaration</p></div>
		                                      </div>
		                                  </a>
		                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										      <img src="popicons/eye.png" alt="..." class="">
		                                          <div class="caption"><p align="center" class="fontpup">Declaration</p></div>
									    	</div>
				    					<%} %>
		                              </div>
                              
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 hidden">
                                  <a href="#" onclick="showEmrWindoe()">
                                      <div class="thumbnail">
                                          <img src="popicons/consultation_note.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Notes</p></div>
                                      </div>
                                  </a>
                              </div>
                              
                             <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                             <%if(loginfo.isIpd_log()){ %>
                                  <a href="#">
                                      <div class="thumbnail" onclick="openClientLogPopupIpd()">
                                          <img src="cicon/treatform.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Log</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										       <img src="cicon/treatform.png" alt="..." class="">
                                               <div class="caption"><p align="center" class="fontpup">Log</p></div>
									    	</div>
				    					<%} %>
                              </div>
                               
                              <div class="col-lg-3 col-md-2 col-sm-2 col-xs-3 hidden">
                                  <a href="#">
                                      <div class="thumbnail" onclick="showipdprintpoup()">
                                          <img src="cicon/print.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Print IPD Details</p></div>
                                      </div>
                                  </a>
                              </div>
                            <%--   <s:if test="">
                               <div class="col-lg-3 col-md-2 col-sm-2 col-xs-3 ">
                                  <a href="#">
                                      <div class="thumbnail" onclick="showdischargepoup()">
                                          <img src="cicon/discharge.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Discharge Form</p></div>
                                      </div>
                                  </a>
                              </div>
                              </s:if>
                               --%>
                               <s:if test="casualtyipd==0 || casualtyipd==2 ">
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                              <%if(loginfo.isIpd_discharge()){ %>
                                  <a href="#" data-toggle="modal" onclick="showInitiateDischarge()">
                                      <div class="thumbnail">
                                          <img src="cicon/dischargebed.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Discharge</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										       <img src="cicon/dischargebed.png" alt="..." class="">
                                                <div class="caption"><p align="center" class="fontpup">Discharge</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              </s:if>
                              
                            <% if(loginfo.getUserType()==2 || loginfo.isCancel_admsn()) { %>
                              
                                 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                      <a href="#" onclick="opencancelIpd()">
                                      <div class="thumbnail">
                                             <img src="popicons/edit.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Cancel Admission</p></div>
                                      </div>
                                  </a>
                              </div>     
                              
                              <%} %>
                              
                              <s:if test="casualtyipd!=0">
	                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
					    				<a href="#" data-toggle="modal" onclick="openadmitpatient(<s:property value='casualtyipd'/>)">
	                                      <div class="thumbnail">
	                                          <img src="cicon/dischargebed.png" alt="..." class="">
	                                          <div class="caption"><p align="center" class="fontpup">Admit Patient</p></div>
	                                      </div>
	                                  </a>
	                              </div>
                              </s:if>
                               <s:if test="casualtyipd==1">
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset hidden">
				    				<a href="#" data-toggle="modal" data-target="#threepopup">
                                      <div class="thumbnail">
                                          <img src="cicon/dischargebed.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Admit Patient</p></div>
                                      </div>
                                  </a>
                              </div>
                              </s:if>
                              
                             
                              
                              <div class="col-lg-3 col-md-2 col-sm-2 col-xs-3 hidden">
                                  <a href="#" data-toggle="modal" data-target="#dietmodal">
                                      <div class="thumbnail">
                                          <img src="cicon/dieticon.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Diet Care</p></div>
                                      </div>
                                  </a>
                              </div>
                              
                              
                              
                              
                              
                              
                        	</div> 
                          	</fieldset> 
                          </md-content>
                          
                          <md-content class="md-padding checkboxdemoBasicUsage checkboxDemo1"> 
                          	<fieldset class="standard"> 
                          		<legend>Smart Care</legend> 
                          		<div layout="row" layout-wrap="" class="layout-wrap layout-row"> 
		                			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
		                			<%if(loginfo.isIpd_emr()){ %>
		                                 <a href="#" onclick="showEmrWindoe()">
		                                      <div class="thumbnail">
		                                          <img src="popicons/emr.png" alt="..." class=" ">
		                                          <div class="caption"><p align="center" class="fontpup">EMR</p></div>
		                                      </div>
		                                  </a>
		                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										       <img src="popicons/emr.png" alt="..." class=" ">
		                                          <div class="caption"><p align="center" class="fontpup">EMR</p></div>
									    	</div>
				    					<%} %>
		                              </div>
		                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
		                              <%if(loginfo.isIpd_prescription()){ %>
		                                 <a href="#" onclick="showipdpriscwindow()">
		                                     <div class="thumbnail">
		                                         <img src="popicons/medicine.png" alt="..." class="">
		                                         <div class="caption"><p align="center" class="fontpup">Request Prescription</p></div>
		                                     </div>
		                                 </a>
		                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										        <img src="popicons/medicine.png" alt="..." class="">
		                                         <div class="caption"><p align="center" class="fontpup">Request Prescription</p></div>
									    	</div>
				    					<%} %>
		                              </div>
                                
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                               <%if(loginfo.isIpd_investigation()){ %>
                                 <a href="#" onclick="showInvestigationwindow()">
                                     <div class="thumbnail">
                                         <img src="popicons/asmnt.png" alt="..." class="">
                                         <div class="caption"><p align="center" class="fontpup">Request Investigation</p></div>
                                     </div>
                                 </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										        <img src="popicons/asmnt.png" alt="..." class="">
                                                <div class="caption"><p align="center" class="fontpup">Request Investigation</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                              <%if(loginfo.isIpd_reqconsultant()){ %>
                                  <a href="#" data-toggle="modal" onclick="showVisitConsult()">
                                      <div class="thumbnail">
                                          <img src="cicon/ipdconsult.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Request Consultant</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										       <img src="cicon/ipdconsult.png" alt="..." class="">
                                               <div class="caption"><p align="center" class="fontpup">Request Consultant</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                              <%if(loginfo.isIpd_nursingcare()){ %>
                                  <a href="#" data-toggle="modal" onclick="showipdnursingwindow()">
                                      <div class="thumbnail">
                                          <img src="cicon/nursingicon.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Request Nursing Care</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										       <img src="cicon/nursingicon.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Request Nursing Care</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                              <%if(loginfo.isIpd_reqblood()){ %>
                                  <!-- <a href="#" data-toggle="modal" data-target="#requestblood">
                                      <div class="thumbnail">
                                          <img src="cicon/bloodrequest.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Request Blood</p></div>
                                      </div>
                                  </a> -->
                                  <a href="#">
                                       <div class="thumbnail" onclick="requestBloodBank()">
                                          <img src="cicon/bloodrequest.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Request Blood</p></div>
                                      </div>
                                   </a>
                                    <%}else{ %>
								    		<!-- <div class="thumbnail disabled" id="" >
										     <img src="cicon/bloodrequest.png" alt="..." class="">
                                             <div class="caption"><p align="center" class="fontpup">Request Blood</p></div>
									    	</div> -->
									    	
									    	<a href="#">
			                                       <div class="thumbnail disabled" >
			                                          <img src="cicon/bloodrequest.png" alt="..." class="">
			                                          <div class="caption"><p align="center" class="fontpup">Request Blood</p></div>
			                                      </div>
                                   			</a>
				    					<%} %>
                              </div>
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                               <%if(loginfo.isIpd_autocare()){ %>
                                  <a href="#">
                                      <!-- <div class="thumbnail" onclick="problem_listing()">
                                          <img src="cicon/autocare.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Auto Care</p></div>
                                          <div class="caption"><p align="center" class="fontpup">Auto Care</p></div>
                                      </div> -->
                                      <div class="thumbnail" onclick="clinicalnotes()">
                                          <img src="cicon/autocare.png" alt="..." class="">
                                          <!-- <div class="caption"><p align="center" class="fontpup">Clinical Notes</p></div> -->
                                          <div class="caption"><p align="center" class="fontpup">Clinical Notes</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										     <img src="cicon/autocare.png" alt="..." class="">
                                             <!-- <div class="caption"><p align="center" class="fontpup">Auto Care</p></div> -->
                                               <div class="caption"><p align="center" class="fontpup">Clinical Notes</p></div>
									    	</div>
				    					<%} %>
                                  
                              </div>
                              
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                               <%if(loginfo.isIpd_autocare()){ %>
                                  <a href="#">
                                       <div class="thumbnail" onclick="problem_listing()">
                                          <img src="cicon/autocare.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Auto Care</p></div>
                                      </div>
                                   </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										     <img src="cicon/autocare.png" alt="..." class="">
                                               <div class="caption"><p align="center" class="fontpup">Auto Care</p></div>
									    	</div>
				    					<%} %>
                                  
                              </div>
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                              <%if(loginfo.isIpd_treatmentlog()){ %>
                                  <a href="#">
                                      <div class="thumbnail" onclick="treatment_sheet()">
                                          <img src="cicon/treatform.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Treatment Records</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										      <img src="cicon/treatform.png" alt="..." class="">
                                              <div class="caption"><p align="center" class="fontpup">Treatment Records</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                  <!-- <a href="#" data-toggle="modal" data-target="#Vitalpop">
                                      <div class="thumbnail" onclick="">
                                      	  <img src="cicon/emr.png" alt="...">
                                          <div class="caption"><p align="center" class="fontpup">Vital Statistics</p></div>
                                      </div>
                                  </a> -->
                                  <a href="#" onclick="showvital()">
                                      <div class="thumbnail">
                                      	  <img src="cicon/emr.png" alt="...">
                                          <div class="caption"><p align="center" class="fontpup">Vital Statistics</p></div>
                                      </div>
                                  </a>
                              </div>
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                               <%if(loginfo.isIpd_forms()){ %>
                                  <a href="#">
                                      <div class="thumbnail" onclick="openAssesmentFormPopupIpd()">
                                          <img src="popicons/asmnt.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Forms</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										      <img src="popicons/asmnt.png" alt="..." class="">
                                               <div class="caption"><p align="center" class="fontpup">Forms</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                 <a href="#" onclick="openuploaddoc()">
                                      <div class="thumbnail" onclick="openuploaddoc()">
                                          <img src="popicons/uploaddocs.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Upload Docs</p></div>
                                      </div>
                                  </a>
                              </div>
                               <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                               <%if(loginfo.isIpd_autocare()){ %>
                                  <a href="#">
                                      <div class="thumbnail" onclick="opennursingcareplan()">
                                          <img src="cicon/careplan3.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Nursing Care Plan</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
              <div class="thumbnail disabled" id="" >
               <img src="cicon/careplan3.png" alt="..." class="">
                                             <div class="caption"><p align="center" class="fontpup">Nursing Care Plan</p></div>
              </div>
             <%} %>
                                  
                              </div>
                              
                              <!--   21 May 2018  -->
                              	 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                 	<a href="#" onclick="openRMONotesplan()">
                                      <div class="thumbnail" onclick="openRMONotesplan()">
                                         <img src="cicon/notes.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Day To Day Notes</p></div>
                                      </div>
                                  </a>
                              	</div>
                              	
                              	
                           <!-- lokesh -->   	
                              	
 								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                      <a href="#" onclick="immunizationopen()">
                                      <div class="thumbnail">
                                             <img src="cicon/pregnancysm.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Vaccine</p></div>
                                      </div>
                                  </a>
                              	</div> 
                              	
                              	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                      <a href="#" onclick="" id="followuppop">
                                      <div class="thumbnail">
                                             <img src="cicon/followup.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Follow Up</p></div>
                                      </div>
                                  </a>
                              	</div>  
                               	
                               	 <!--   21 Aug 2018  -->
                              	 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                 	<a href="#" onclick="returnnursmedicine()">
                                      <div class="thumbnail" onclick="returnnursmedicine()">
                                          <img src="cicon/returnmed.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Return Medicine</p></div>
                                      </div>
                                  </a>
                              	</div>
                              	
                              	 <!-- lokesh 16 sept 2018  -->
                              	 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                 	<a href="#" onclick="immunizationopen1()">
                                      <div class="thumbnail" onclick="immunizationopen1()">
                                          <img src="cicon/Anc_icon.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Antenatal Schedule</p></div>
                                      </div>
                                  </a>
                              	</div>
                              	
                              	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                      <a href="#" onclick="openInvestigationdash1()" id="">
                                      <div class="thumbnail">
                                             <img src="cicon/invst.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Show Investigation</p></div>
                                      </div>
                                  </a>
                              	</div>  
                              	
                              	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                      <a href="#" onclick="openipdwardpopup()" id="">
                                      <div class="thumbnail">
                                            <i class='fa fa-inr ' style="margin-left: 34px;color: #333;"></i>
                                          <div class="caption"><p align="center" class="fontpup" >Rate Category</p></div>
                                      </div>
                                  </a>
                              	</div>  
                              	
                              	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                                      <a href="#" onclick="openipdshiftpatient()" >
                                      <div class="thumbnail">
                                      	<i class="fa fa-arrows" aria-hidden="true"  style="margin-left: 34px;color: #333;"></i>
                                          <div class="caption"><p align="center" class="fontpup" >Shift Patient</p></div>
                                      </div>
                                  </a>
                              	</div>  
                              	
                              	
                               </div> 
	                          	</fieldset> 
	                          </md-content>
                          
                          <md-content class="md-padding checkboxdemoBasicUsage checkboxDemo1"> 
                          	<fieldset class="standard"> 
                          		<legend>Account</legend> 
                          		<div layout="row" layout-wrap="" class="layout-wrap layout-row"> 
		                			 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
		                		 <%if(loginfo.isIpd_addcharges()){ %>
                                  <a href="#">
                                      <div class="thumbnail" onclick="showAddchargePopup()">
                                          <img src="popicons/view_treatment.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Add Charges</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										      <img src="popicons/view_treatment.png" alt="..." class="">
                                              <div class="caption"><p align="center" class="fontpup">Add Charges</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                              <%if(loginfo.isIpd_createinvoice()){ %>
                                  <a href="#">
                                  	<s:form action="Accounts" id="accountchargefrm" target="formtarget">
									<s:hidden name="clientId" id="accountChargeClientid"/>
									<s:hidden name="thirdParty" id="accountchargethirdparty"/>
									<s:hidden name="transactionType" id="accountchargetransactionType"/>
									<s:hidden name="location" id="accountsLocationid"/>
									<s:hidden name="client" id="accountclientid"/>
									<s:hidden name = "payby" id ="accountpayby"></s:hidden>
									<input type="hidden" name="autoselect" id="autoselect" value="1">
                                      <div class="thumbnail" onclick="redirectToCreateCharge()">
                                          <img src="popicons/invoice.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Create Invoice</p></div>
                                      </div>
                                      </s:form>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										     <img src="popicons/invoice.png" alt="..." class="">
                                             <div class="caption"><p align="center" class="fontpup">Create Invoice</p></div>
									    	</div>
				    					<%} %>
                              </div>
                             
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                               <%if(loginfo.isIpd_recordpayment()){ %>
                                  <a href="#">
                                  	<s:form action="ProcessingAccount" id="accountpaymentfrm" method="post" 
              							onsubmit="return redirectToRecordPayment()" target="formtarget">
									<s:hidden name="clientId" id="accountpaymentClientid"/>
									<s:hidden name="thirdParty" id="accountpaymentthirdparty"/>
									<s:hidden name="transactionType" id="accountpaymenttransactionType"/>
									<s:hidden name="location" id="accountspaymentLocationid"/>
									<s:hidden name="client" id="accountpaymentclientid"/>
									<s:hidden name="fromDate" id="accountspaymentfromDateid"/>
									<s:hidden name="toDate" id="accountspaymenttoDateid"/>
									<s:hidden name = "payby" id ="accountPaymentPayby"></s:hidden>
                                      <div class="thumbnail" onclick="redirectToRecordPayment()">
                                          <img src="popicons/cash_desk.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Record Payment</p></div>
                                      </div>
                                      </s:form>
                                  </a>
                                  
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										     <img src="popicons/cash_desk.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Record Payment</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              
                             <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                             <%if(loginfo.isIpd_advandref()){ %>
                                  <a href="#">
                                      <div class="thumbnail" onclick="showcrddebit()">
                                          <img src="cicon/raise_credit.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Advance & Refund</p></div>
                                      </div>
                                  </a>
                                    <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										     <img src="cicon/raise_credit.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Advance & Refund</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
                              <%if(loginfo.isIpd_viewaccount()){ %>
                                  <a href="#">
                                  <s:form action="Statement" id="view_acc_frm" method="post" onsubmit="return redirectToViewAcc()" target="formtarget">
									<s:hidden name="clientId" id="viewAccClientid"/>
									<s:hidden name="thirdParty" id="viewAccthirdparty"/>
									<s:hidden name="transactionType" id="viewAcctransactionType"/>
									<s:hidden name="location" id="viewAccLocationid"/>
									<s:hidden name="client" id="viewAccclientname"/>
									<s:hidden name = "payby" id ="viewAccPayby"></s:hidden>
									<s:hidden name="fromDate" id="viewAccfromDateid"/>
									<s:hidden name="ipdid" id="accipdid"></s:hidden>
									<s:hidden name="toDate" id="viewAcctoDateid"/>
                                      <div class="thumbnail" onclick="redirectToViewAcc()">
                                          <img src="popicons/view_account.png" alt="...">
                                          <div class="caption"><p align="center" class="fontpup">View Account</p></div>
                                      </div>
                                       </s:form>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										     <img src="popicons/view_account.png" alt="...">
                                            <div class="caption"><p align="center" class="fontpup">View Account</p></div>
									    	</div>
				    					<%} %>
                              </div>
                              
                              <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset"><!--
                                  <a href="#" data-toggle="modal" data-target="#pacakgesp">
                                      
                                      --><s:hidden name="clientId" id="pcclientid"/>
                                      <s:hidden name="ipdid" id="pcipdid"></s:hidden>
                                       <%if(loginfo.isIpd_packages()){ %>
                                      <a href="#" onclick="getpackage()">
                                      <div class="thumbnail">
                                          <img src="cicon/raise_credit.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Packages</p></div>
                                      </div>
                                  </a>
                                   <%}else{ %>
								    		<div class="thumbnail disabled" id="" >
										     <img src="cicon/raise_credit.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Packages</p></div>
									    	</div>
				    					<%} %>
                              </div>
                                
	                        	</div> 
	                          	</fieldset> 
	                          </md-content>
	                          <md-content class="md-padding checkboxdemoBasicUsage checkboxDemo1 hidden"> 
                          	<fieldset class="standard"> 
                          		<legend>Communications</legend> 
                          		<div layout="row" layout-wrap="" class="layout-wrap layout-row"> 
		                			
                              
	                        	</div> 
	                          	</fieldset> 
	                          </md-content>
                          
                          
                          
                              

                             <!--  <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                  <a href="discharge.html">
                                      <div class="thumbnail">
                                          <img src="_assets/img/modify.png" alt="..." style="width: 64px; height: 64px;">
                                          <div class="caption"><p align="center" class="fontpup">Discharge</p></div>
                                      </div>
                                  </a>
                              </div> -->

                              
                              
                              
                                
                              
                              
                              
                          </div>
                      </div>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-primary hidden" onclick="goreferesh()" >Close</button>
                  </div>
              </div>
          </div>
      </div>
      
      
      <!--Ipd Ward charge Selection List  -->
      <div class="modal fade popoverpop" id="wardselect" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md" style="margin-right: 100px">
			<div class="modal-content" style="width: 30%">
			<div class="modal-header bg-primary">
			<h5>Rate Category</h5>
			</div>
			<div class="modal-body">
			<label>Patient Name:</label><span id='clname'></span><br>
			<label>Ward Name:</label><span id='wnamed'></span><br>
			<label>Select Ward:</label>
			<s:select list="wardList" listKey="id" listValue="name" headerKey="0" headerValue="Select Ward" cssClass='chosen-select' name='wdname' id='wdname'> </s:select>
			</div>
			<div class="modal-footer" style="text-align: right;">
			<input type="button" class='btn btn-primary' value='Save'  onclick="savewardchargerateforbed()">
			</div>
			
		</div>
		</div>
		</div>		
		
		
		<!--Ipd Shift Patient  -->
      <div class="modal fade popoverpop" id="shiftpatientpopup" style="z-index: 10000;"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md" style="margin-right: 100px">
			<div class="modal-content" style="width: 60%">
				<div class="modal-header bg-primary">
					Shift Patient Popup
				</div>
				<div class="modal-body">
					<div class="row">
						<input type="hidden" id="shiftpatient_oldwardid">
						<input type="hidden" id="shiftpatient_oldbedid">
						<input type="hidden" id="shiftpatient_admissionid">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							 <div class="col-lg-6 col-md-6">
								   <label>Patient Name:</label>
							 </div>
							 <div class="col-lg-6 col-md-6">
								   <span id='shiftpatient_name'></span>
							 </div>
						</div>
						<br>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							 <div class="col-lg-6 col-md-6">
								   <label>Select Ward:</label>
							 </div>
							 <div class="col-lg-6 col-md-6">
								 <div id="shiftpatient_warddiv"></div>
							 </div>
						</div>
						<br>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							 <div class="col-lg-6 col-md-6">
								   <label>Select Bed:</label>
							 </div>
							 <div class="col-lg-6 col-md-6">
								  <div id="shiftpatient_beddiv"></div>
							 </div>
						</div>
					</div>
					
					
				</div>
				<div class="modal-footer" style="text-align: right;">
					<a href="#" class='btn btn-primary' id="saveshiftpatient" onclick="validateShiftPatient()">Save</a>
				</div>
			</div>
		</div>
		</div>		
      
      
      	<div class="modal fade popoverpop" id="addchargepopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md" style="margin-right: 375px">
			<div class="modal-content" style="width: 125%">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Add Charges for <span id="addchargehead"></span></h4>
				</div>
				<div class="modal-body">
				
					<jsp:include  page="/ipd/pages/addcharges.jsp" />
				</div>
				
				<s:form action="estimateCharges" theme="simple" id="estimatefrm" target="formtarget">
					<s:hidden name="clientId" id="estimateclientid"/>
					<s:hidden name="actionType" value="0"/>
				</s:form>
				
				<div class="modal-footer">
				
				<button type="button" class="btn btn-primary" 
							onclick="createestimate()">View
							Estimate</button>
				
					<button type="button" class="btn btn-primary" 
						onclick="createChargeAndUpdateAccount('Charge')">Create
						Charge</button>
					<!--  <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="createInvoice('Invoice')">Invoice Now / Pay Later</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="submitInvoice()">Submit Invoice</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="paynowForInvoice()">Record Payment</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="paynowForInvoice()">Cash Desk</button> -->
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<s:form action="invstcashAdditional" id="cashdeskfrm1" > 
									
			<s:hidden name="clientId" id="cashClientid"/>
			<s:hidden name="thirdParty" id="cashthirdparty"/>
			<s:hidden name="location" id="cashLocationid"/>
			<s:hidden name="client" id="cashclientname"/>
			<s:hidden name = "payby" id ="cashPayby"></s:hidden>
			<s:hidden name="creditDebitCharge" id="creditDebitCharge" value="0"/>
			<s:hidden name="appointmentid" id="cashAppointmentid"/>
			<s:hidden name="invoiceid" id="cashinvoiceid"/>
			
			
	
	
		
    </s:form>
    
	
	
		
	<input type="hidden" id="hiddenTotal"/>
	  	<div class="modal fade popoverpop" id="addchargepopupipdopdinv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Add Charges for <span id="addinvnewchargehead"></span></h4>
				</div>
				<div class="modal-body">
				
					<jsp:include  page="/ipd/pages/invaddcharge.jsp" />
				</div>
				
				<s:form action="estimateCharges" theme="simple" id="estimatefrm" target="formtarget">
					<s:hidden name="clientId" id="estimateclientid"/>
					<s:hidden name="actionType" value="0"/>
				</s:form>
				
				<div class="modal-footer">
				
				 <button type="button" class="btn btn-primary" 
							onclick="createChargeAndUpdateAccount('cash')">Cash Desk</button>
				
					<button type="button" class="btn btn-primary" 
							onclick="createestimate()">View
							Estimate</button>
				
				
					<button type="button" class="btn btn-primary" 
						onclick="createipdopdChargeAndUpdateAccount('Charge')">Create
						Charge</button>
					
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- Initial Discharge -->
<div class="modal fade popoverpop" id="initialdischarge" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document" style="width: 95%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="mydislabel">Discharge Status For (Patient Name)</h4>
      </div>
      <div class="modal-body" style="padding:0px !important;">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0px;padding-right: 0px;margin-top: -5px;">
         
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback initaiback">
        	<h3 class="text-center textbomana">Initiate Discharge</h3>
        	<img src="cicon/initialdischarge.png" alt="..." class="imagestfix">
        <%if(loginfo.getDischarge_validation()==1){ %>	
        	 <%if(loginfo.getUserType()==2||loginfo.getJobTitle().equals("Practitioner")||loginfo.getJobTitle().equals("RMO")||loginfo.getJobTitle().equals("NURSE")||loginfo.getJobTitle().equals("Reception")){ %> 		 
        	<h4 class="text-center"><a href="#" onclick="setInitiateDischargeStatus('dis_initiate_status','dis_initiate_time')" type="button" class="btn btn-primary btnwidtfixed"><span id="disinitbtnid">START</span></a></h4>
        	<%}else{ %>
        		<h4 class="text-center"><a href="#"  type="button" class="btn btn-primary btnwidtfixed"><span id="disinitbtnid">START</span></a></h4>
        	<%} %>
        <%}else{ %>
        	<h4 class="text-center"><a href="#" onclick="setInitiateDischargeStatus('dis_initiate_status','dis_initiate_time')" type="button" class="btn btn-primary btnwidtfixed"><span id="disinitbtnid">START</span></a></h4>
        <%} %>	
        	</div>
       
        
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback">
        	<h3 class="text-center textbomana">Discharge Form</h3>
        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
        		<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
        <%
        String dischargefun=""; 
        if(!loginfo.isNewdischargecard()){
        	dischargefun="showdischargepoup()";
        }else{
        	dischargefun="opennewdis()";
        } %>		
       <%if(loginfo.getDischarge_validation()==1) {%> 		
        <%if(loginfo.getUserType()==2||loginfo.getJobTitle().equals("Practitioner")||loginfo.getJobTitle().equals("RMO")||loginfo.getJobTitle().equals("NURSE")||loginfo.getJobTitle().equals("Reception")){ %> 		 
       			<a href="#"  onclick="<%=dischargefun %>" style="color:#000;"><img src="cicon/dichargeform.png" alt="..." class="imagestfix123"></a>
        <%}else{ %>
       				 <a href="#"  style="color:#000;"><img src="cicon/dichargeform.png" alt="..." class="imagestfix123"></a>
        <%} %>	
        <%}else{ %>
        	<a href="#"  onclick="<%=dischargefun %>" style="color:#000;"><img src="cicon/dichargeform.png" alt="..." class="imagestfix123"></a>
        <%} %>		
        		</div>
        		<div class="col-lg-8 col-md-8 col-xs-8" style="padding:0px;">
        			<h4 class="text-center" style="min-height: 50px;padding-top: 12px;" id="disformcompletedid"></h4>
        		</div>
        	</div>
        	
        	
        	<section class="widget-todo" fullscreen="isFullscreen04" ng-controller="TodoWidgetCtrl" style="background-color: #fff;">
                                <!-- tile body -->
                                <div class="tile-body dischpoints" style="padding-top: 6px;" id="disformcheckdiv">
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
                           
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding-top: 15px;background-color: #fff;">
                            	<div class="form-group">
								    <textarea class="form-control" id="exampleFormControlTextarea1" style="background-color: rgba(245, 245, 220, 0.24) !important;" rows="3" placeholder="Write Remark"></textarea>
								  </div>
                            </div>
        	</div>
        
        
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback">
        	<h3 class="text-center textbomana">Medicine Kit</h3>
        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
        		<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
            <%if(loginfo.getDischarge_validation()==1){ %>
            <%if(loginfo.getUserType()==2||loginfo.getJobTitle().equals("Practitioner")||loginfo.getJobTitle().equals("RMO")||loginfo.getJobTitle().equals("NURSE")||loginfo.getJobTitle().equals("Pharmacist")||loginfo.getJobTitle().equals("Reception")||loginfo.getJobTitle().equals("BILLING")){ %> 		
      		   	  <a href="#"  onclick="completeMedicine()" style="color:#000;"><img src="cicon/Medicinekit.png" alt="..." class="imagestfix123"></a>
        	<%}else{ %>
        		<a href="#"   style="color:#000;"><img src="cicon/Medicinekit.png" alt="..." class="imagestfix123"></a>
      
        	<%} %>
        	<%}else{ %>
        	 	<a href="#"  onclick="completeMedicine()" style="color:#000;"><img src="cicon/Medicinekit.png" alt="..." class="imagestfix123"></a>
        	<%} %>
        		</div>
        		<div class="col-lg-8 col-md-8 col-xs-8" style="padding:0px;">
        			<h4 class="text-center" style="min-height: 50px;padding-top: 12px;" id="dismedicinecompletedid"></h4>
        		</div>
        	</div>
        	
        		<section class="widget-todo" fullscreen="isFullscreen04" ng-controller="TodoWidgetCtrl" style="background-color: #fff;">
                                <!-- tile body -->
                                <div class="tile-body dischpoints" style="padding-top: 6px;" id="dismedicalcheckdiv">
                                	<ul>
                  						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Received any drug returned</label></li>
                  						<li><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Cleared all outstanding bills</label></li>
                  					</ul>
                                </div>
                                <!-- /tile body -->
                            </section>
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding-top: 15px;background-color: #fff;">
                            	<div class="form-group">
								    <textarea class="form-control" id="exampleFormControlTextarea1" style="background-color: rgba(245, 245, 220, 0.24) !important;" rows="3" placeholder="Write Remark"></textarea>
								  </div>
                            </div>
        	</div>
        	
        
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback">
        	<h3 class="text-center textbomana">Billing Settlement</h3>
        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
        		<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
      <%if(loginfo.getDischarge_validation()==1) {%>
       	<%if(loginfo.getUserType()==2||loginfo.getJobTitle().equals("Practitioner")||loginfo.getJobTitle().equals("RMO")||loginfo.getJobTitle().equals("Reception")||loginfo.getJobTitle().equals("BILLING")){ %> 		
      		   		<a href="#"  onclick="completeAccount()" style="color:#000;"><img src="cicon/Accountsettle.png" alt="..." class="imagestfix123"></a>
        <%}else{ %>
        		<a href="#"  onclick="" style="color:#000;"><img src="cicon/Accountsettle.png" alt="..." class="imagestfix123"></a>
        <%} %>
       <%}else{ %>
       	<a href="#"  onclick="completeAccount()" style="color:#000;"><img src="cicon/Accountsettle.png" alt="..." class="imagestfix123"></a>
        <%} %>
        		</div>
        		<div class="col-lg-8 col-md-8 col-xs-8" style="padding:0px;">
        			<h4 class="text-center" style="min-height: 50px;padding-top: 12px;" id="disbillcompletedid"></h4>
        		</div>
        	</div>
        	<section class="widget-todo" fullscreen="isFullscreen04" ng-controller="TodoWidgetCtrl" style="background-color: #fff;">
        						 <!-- tile body -->
                                <div class="tile-body dischpoints" style="padding-top: 6px;" id="disbillingcheckdiv">
                                	<ul>
                  						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Received Medical Record file for final Billing</label></li>
                  						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Completed Billing Formality</label></li>
                  						<li class="setlimet"><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Payment Received</label></li>
                  						<li><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="" type="checkbox" name="dosage"><i></i>Printed Final Discharge Invoice & sent Discharge file to ward</label></li>
                  					</ul>
                                </div>
                                <!-- /tile body -->
                            </section>
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding-top:15px;background-color: #fff;">
                            	<div class="form-group">
								    <textarea class="form-control" id="exampleFormControlTextarea1" style="background-color: rgba(245, 245, 220, 0.24) !important;" rows="3" placeholder="Write Remark"></textarea>
								  </div>
                            </div>
        	</div>
       
        
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback">
        	<h3 class="text-center textbomana">Nursing Advice</h3>
        	<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 seticons">
        		<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
        	 <%-- 	<%if(loginfo.getUserType()==2||loginfo.getJobTitle().equals("Practitioner")||loginfo.getJobTitle().equals("RMO")||loginfo.getJobTitle().equals("NURSE")){ %>
        	 --%>
        	   
        		<%if(loginfo.getDischarge_validation()==1){%>
        			<%if(loginfo.getUserType()==2||loginfo.getJobTitle().equals("Practitioner")||loginfo.getJobTitle().equals("RMO")||loginfo.getJobTitle().equals("NURSE")){ %>
        				<a href="#"  onclick="setDischargeStatus('dis_nursing_status','dis_nursing_time')" style="color:#000;"><img src="cicon/nursepateint.png" alt="..." class="imagestfix123"></a>
        			<%}else{ %>
        					<a href="#"   style="color:#000;"><img src="cicon/nursepateint.png" alt="..." class="imagestfix123"></a>
        			<%} %>
        		<%}else{ %>
        			<a href="#"  onclick="setDischargeStatus('dis_nursing_status','dis_nursing_time')" style="color:#000;"><img src="cicon/nursepateint.png" alt="..." class="imagestfix123"></a>
        		<%} %>
        		
        		</div>
        		<div class="col-lg-8 col-md-8 col-xs-8" style="padding:0px;">
        			<h4 class="text-center" style="min-height: 50px;padding-top: 12px;" id="disnursingcompletedid"></h4>
        		</div>
        	</div>
        	
        	
        	<section class="widget-todo" fullscreen="isFullscreen04" ng-controller="TodoWidgetCtrl" style="background-color: #fff;">
        						<!-- tile body -->
                                <div class="tile-body dischpoints" style="padding-top: 6px;" id="disnursingcheckdiv">
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
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding-top:15px;background-color: #fff;">
                            	<div class="form-group">
								    <textarea class="form-control" id="exampleFormControlTextarea1" style="background-color: rgba(245, 245, 220, 0.24) !important;" rows="3" placeholder="Write Remark"></textarea>
								  </div>
                            </div>
        	</div>
        
       
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback completback">
        	<h3 class="text-center textbomana">Complete Discharge</h3>
        	<img src="cicon/completdis.png" alt="..." class="imagestfix">
       <%--  	<%if(loginfo.getUserType()==2||loginfo.getJobTitle().equals("Practitioner")||loginfo.getJobTitle().equals("RMO")||loginfo.getJobTitle().equals("NURSE")){ %>
       --%>  <%if(true){ %>  	<h4 class="text-center"><a href="#" onclick="endDischarge()" type="button" class="btn btn-primary btnwidtfixed">END</a></h4>
        	<%}else{ %>
        	<h4 class="text-center"><a href="#"  type="button" class="btn btn-primary btnwidtfixed">END</a></h4>
        	<%} %>
        	</div>
       
        	
        	<%-- <s:form action="endIpd" id="enddischargefrm" theme="simple" > --%>
        	<s:form action="endCommonnew" id="enddischargefrm" theme="simple" >
        		<s:hidden name="isfromdischargedashbaord" id="isfromdischargedashbaord" value="0"></s:hidden>
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


<div id="infohourlycharges" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  style="z-index: 9998;">
  <div class="modal-dialog modal-sm" role="document">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Info</h4>
      </div>
      <div class="modal-body" id='chrgname'>
        	
      </div>
      <div class="modal-footer">
     
      </div>
    </div>

  </div>
</div>

<!-- Investigagtion Process -->
<div class="modal fade" id="investigag" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Investigagtion Status For (Patient Name)</h4>
      </div>
      <div class="modal-body" style="min-height: 252px;">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0px;padding-right: 0px;">
         <a href="" style="color:#000;">
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback initaiback">
        	<h3 class="text-center textbomana">Start Investigation</h3>
        	<img src="cicon/initialdischarge.png" alt="..." class="imagestfix">
        	<h4 class="text-center"><a href="#" type="button" class="btn btn-primary btnwidtfixed"><span id="disinitbtnid">START</span></a></h4>
        	
        	</div>
        </a>
        <a href="#"  style="color:#000;">
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback">
        	<h3 class="text-center textbomana">Ordered</h3>
        	<img src="cicon/orderin.png" alt="..." class="imagestfix">
        	<h4 class="text-center"><i class="fa fa-check fa-2x text-success"></i><br><small>(Completed)</small><hr><p class="dischptext"> 04-04-2016 / 01:10:00 PM</p></h4>
        	
        	</div>
        </a>
        <a href="#" style="color:#000;">
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback">
        	<h3 class="text-center textbomana">Charges paid</h3>
        	<img src="cicon/cashin.png" alt="..." class="imagestfix">
        	<h4 class="text-center"><i class="fa fa-check fa-2x text-success"></i><br><small>(Paid)</small><hr><p class="dischptext"> 04-04-2016 / 01:20:00 PM</p></h4>
        	</div>
        </a>
        <a href="#" style="color:#000;">
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback">
        	<h3 class="text-center textbomana">Completed</h3>
        	<img src="cicon/completedin.png" alt="..." class="imagestfix">
        	<h4 class="text-center"><i class="fa fa-spinner fa-pulse fa-2x text-danger"></i></h4>
        	</div>
        </a>
        <a href="#" style="color:#000;">
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback">
        	<h3 class="text-center textbomana">Approved</h3>
        	<img src="cicon/inlab.png" alt="..." class="imagestfix">
        	<h4 class="text-center"><i class="fa fa-spinner fa-pulse fa-2x text-danger"></i></h4>
        	</div>
        </a>
        <a href="#"  style="color:#000;">
        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 disableback completback">
        	<h3 class="text-center textbomana">Complete Investigation</h3>
        	<img src="cicon/completdis.png" alt="..." class="imagestfix">
        	<h4 class="text-center"><a href="#"  type="button" class="btn btn-primary btnwidtfixed">END</a></h4>
        	
        	</div>
        </a>
        </div>
      </div>
    </div>
  </div>
</div>


		<!-- create Prescription -->
		 <div class="modal fade" id="priscriptionpopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content" style="margin-left:-100px;margin-right:-100px;">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="priscmyModalLabel">Create Prescription For <b class="pname">NAME: </b></h5>
				</div>
				<div class="modal-body" style="padding:0px;">
					<jsp:include page="/diarymanagement/pages/addpriscription.jsp" />
				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-primary hidden"
						onclick="saveTemplae(0)">Save Template</button>
					<button type="button" class="btn btn-primary" id="prescs_save_btn" onclick="addIpdPrisc(0)">Save</button>
					<%if(loginfo.isPrisc_savenprint()){ %>
						 <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)" id="prescs_save_print_btn">Save & Print</button>
					<%} %>
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<%-- <s:hidden id="admi_disc_ipd_prisclist"></s:hidden> --%>
	<!-- edit mdcine name popup -->
	<div class="modal fade" id="edtmdcinenamepopupid" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Medicine Name
						</h4>
				</div>
				<div class="modal-body">
						<div class="row">
							<div class="col-lg-3">
								<label>Medicine Name</label>
							</div>
							<div class="col-lg-9">
								
								<input type="text" id = "priscmdcineedit" name = "priscmdcineedit" class="form-control showToolTip" onblur="setPuresevaExistingClientData()"
									placeholder="Enter Medicine Name" title="Enter Medicine Name" data-toggle="tooltip "  />
							</div>
						</div>
						<br>
						
						<%if(loginfo.getOutoprisc()==1){ %>
						<div class="row">
							<div class="col-lg-3">
								<label>Search Medicine</label>
							</div>
							<div class="col-lg-9">
								
								<s:select cssClass="form-control showToolTip chosen-select" 
								name="mdicinenamesrch" id="mdicinenamesrch" 
								onchange="getsrchdmdicinename(this.value)" 
								list="medicineList" tabindex="1" listKey="id" 
								listValue="genericname" headerKey="0" 
								headerValue="Select Medicine">	</s:select>
							</div>
						</div>
						<br>
						<%} %>
						
						
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary"
						onclick="updatemdcinename();" data-dismiss="modal">Save</button>
					  <!-- <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->
				</div>
			</div>
		</div>
	</div>
	
	
		<!-- add invesgtigation Modal -->
	 <div class="modal fade" id="investigationpopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.43);">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Create Investigation For <b class="pname" id="invstcmyModalLabel">NAME: </b></h5>
				</div>
				<div class="modal-body" style="padding:0px;">
						
						
					<jsp:include page="/emr/pages/addInvestigation.jsp" />
				    
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id='investigationsavebtn' onclick="saveIpdInvestigation(0)">Save</button>
						<%if(loginfo.isInvest_savenprint()){ %>
				<button type="button" class="btn btn-primary"
						onclick="saveIpdInvestigation(1)">Save & Print</button>
<%} %>
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<!--Vital Popup -->
	<div class="modal fade" id="vitalmod" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-xs setbig" >
			<div class="modal-content">
				<div class="modal-header bg-primary">
				
				<h3>Vitals
				<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button></h3>
				</div>
				<div class="modal-body">
				 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 20px;padding-left: 30px;" >
				 <label>Select Date :</label>
				 <input type="text" class='form-control picker' id='datevital' style='width: 12%' readonly="readonly" placeholder="Select date"> 
									 &nbsp;&nbsp;
				 					  <label>Hours : </label>
                                      <select class="form-control " id='vhh' style='width: 7%'>
                                      <%for(int i=0;i<24;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
                                     &nbsp;&nbsp;
                              		  <label>Minutes : </label>
                                      <select class="form-control " id='vmm' style='width: 7%'>
                                      <%for(int i=0;i<60;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
				 
				 
				 </div>
				 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 30px;">
				 <table class='my-table loktable' style="width: 100%">
				 <tr><th>Vital</th><th>Value</th><th>Min-Max</th><th>Unit</th></tr>
				 <s:iterator value="vitalList">
					<tr>
					
					<td id="namev<s:property value='id'/>"><s:property value='name'/></td>
					
					<td><input type="text" class='form-control lokloop' style="width: 40%;height: 35px" id="<s:property value='id'/>" placeholder="Enter <s:property value='name'/> Value" ></td>
					<td><s:property value='min_value_m'/> - <s:property value='max_value_m'/></td>
					<td id="unitv<s:property value='id'/>"><s:property value='unit'/></td>
					</tr>				 
				 
				 </s:iterator>
				  </table>
				 </div>
				</div>
				<div class="modal-footer" >
				<input type="button" class='btn btn-primary' value="Save Vitals" onclick="savipdvitals()">
				</div>
			</div>
		</div>
	</div>			
	
	<!-- Send sms Details Modal -->
	 <div class="modal fade popoverpop" id="semdsmspopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Send Sms</h4>
				</div>
				<div class="modal-body">
				         <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				         	
					         	<div class="form-group">
								    <label for="exampleInputEmail1">Practitioner</label>
								    <s:textfield name="smsuserName" id="smsuserNameletter" readonly="true" cssClass="form-control"  value="Client"  />
									<label  id = "smsunameError" class="text-danger"></label>	
								 </div>
						 	
						 	
								 <div class="form-group">
								    <label for="exampleInputEmail1">Mobile No</label>
								    <s:textfield name="smsmobile" id="smsmobile"  cssClass="form-control"   />
									<label  id = "smsmobileError" class="text-danger"></label>	
								 </div>
						 	
						 	<div class="form-group">
								    <label for="exampleInputEmail1">Select Template</label>
								    <s:select onchange="getsmsInvsttemplatetxt(this.value)" cssClass="form-control showToolTip chosen" name="smstmlate" id="smstmlate" list="smsTemplateList" listKey="id" listValue="templateName" headerKey="0" headerValue="Select Template"/>
									<label  id = "smstmplateError" class="text-danger"></label>		
								 </div>
						 	
						 	
								<div class="form-group">
								    <label for="exampleInputEmail1">Enter text here</label>
								    <s:textarea placeholder="Max 160 character" maxlength="160" cols="60" rows="6" name="smstxt" id="smstxt"  cssClass="form-control"   />
								 </div>
						 	
						</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="sendsms()" style="margin-top:10px;">Send</button>

					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" style="margin-top:10px;">Close</button>
				</div>
			</div>
		</div>
	</div>
   
   
   <!-- create Diet Management -->
		 <div class="modal fade popoverpop" id="dietmodal" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Create Diet Chart For <b class="pname">NAME: Mr.Manoj Mishra  | AGE:34 / Male</b></h5>
				</div>
				<div class="modal-body">
					<div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4 marleft9">
                                      <div class="form-group">
                                          <b>Patient Bar Code</b>
                                          <p>C31128A00175</p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Date / Time</b>
                                          <p>04/10/2016 14:59:30 </p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Nutrition Incharge</b>
                                          <p>Dr. Kishor Deshmukh</p>
                                      </div>
                                  </div>
                                  
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      
                                  </div>
                              
                          </div>
                      </div>
                      <div class="row">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martpomin15">
                              <br>
                              <table class="table" cellspacing="0" width="100%">
                                  <thead>
                                      <tr>
                                          <th class="investtypr" style="width: 21% !important;"><b>Investigation Type</b></th>
                                          <th class="thfont intname"><b>Test Name</b></th>
                                          <th class="thfont intname"><b>Give Reason</b></th>
										  <th></th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr>
                                          <td>
                                              <select name="invsttype" class="form-control chosen-select">
											    <option value="0">Select Diet</option>
											    <option value="1">Atkins Diet</option>
											    <option value="2">Zone Diet</option>
											    <option value="3">Vegetarian Diet</option>
											    <option value="4">Vegan Diet</option>
											    <option value="5">Weight Watchers Diet</option>
											    <option value="6">Raw Food Diet</option>
											    <option value="7">Mediterranean Diet</option>
											</select>
                                          </td>
                                         
                                          <td>
                                              <div style="height: 100px;overflow: scroll;">
                                              <select class="form-control" name="invstname">
                                                 <option value="0">Select Food</option>
                                                 <option value="1">Dry vegetable</option>
                                                 <option value="2">Banana</option>
                                                 <option value="3">Tomato Soup</option>
                                              </select>
                                              </div>
                                          </td>
                                          <td>
                                              <textarea name="advoice" class="form-control" rows="3"></textarea>
                                          </td>
                                          
                                        <td>
                                         <div class="">
		                                      <a href="#" type="button" title="Add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
		                                  </div>
                                        </td>

                                      </tr>

                                  </tbody>
                              </table>
                             
                              <div class="row inaddbtn">

                                 
                              </div>

                             
                          </div>
                          <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: 100%; height: 150px;"><div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 tableheight1" style="overflow: hidden; width: 100%; height: 150px;">
                              <table class="table table-bordered" cellspacing="0" width="100%">
                                  <thead>
                                      <tr class="tableback">

                                          <th class="invet1">Diet Name</th>
                                          <th class="invet2">Reason</th>
                                          <th class="invet4">Report Type</th>
                                          <th class="invet6">Delete</th>

                                      </tr>
                                  </thead>
                                  <tbody>
                                    <tr>
										<td>Dry vegetable</td>
										<td>Calorie consumption from carbs limited to 20 grams each day. Carb sources are mainly from salad and vegetables which are low in starch.</td>
										<td>Text</td>
										<td><a herf="#"><i style="cursor: pointer;" class="fa fa-trash-o"></i></a></td>
                                  	</tr>
                                  </tbody>
                              </table>

                          </div><div class="slimScrollBar" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 150px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martop10">
                             
                              <div class="col-lg-6 col-md-6 col-xs-6 martopmin10 marleft9">
                                  <form>
                                      <div class="form-group">
                                          <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                              <p for="exampleInputEmail1" class="marleft9">Advice</p>
                                          </div>
                                          <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" value="option1" checked=""> English
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" value="option2"> Regional
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" value="option3"> Hindi
                                              </label>
                                          </div>


                                      </div>

                                  </form>
                                  <form class="form-horizontal">

                                      <textarea name="advoice" class="form-control" rows="3"></textarea>

                                  </form>
                              </div>
                              <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">


                              </div>
                          </div>

                      </div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Save</button>
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
   



   <!-- Request Blood to Blood Bank-->
   <div class="modal fade popoverpop" id="requestblood" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
			 <form action="addrequestBloodbank" method="post" id="requestform">
			  
			  	<input type="hidden" name="clientid" id="bloodclientid" >
			  	<input type="hidden" name="ipdid" id="bloodsipdid" >
			  	<input type="hidden" name="requestfrom" id="brequestfrom" value="IPD" >
			  
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Request Blood</h5>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12">
						
						<div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;">
							<div class="col-lg-6 col-md-6 col-xs-12">
								<div class="form-group">
									<label for="exampleInputEmail1">Date</label>
									<s:textfield name="date" id="blooddate" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12">
								<div class="form-group">
									<label for="exampleInputEmail1">Time</label>
									<s:select list="timeList" name="time" cssClass="form-control" />
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12">
								<div class="form-group" id="blood_group_div">
								    <label for="exampleInputEmail1">Select Blood Group</label>
								    <select name="blood_group" id="blood_group" class="form-control chosen-select">
											    <option value="A+">A+</option>
												<option value="A-">A-</option>
												<option value="AB+">AB+</option>
												<option value="AB-">AB-</option>
												<option value="B+">B+</option>
												<option value="B-">B-</option>
												<option value="O+">O+</option>
												<option value="O-">O-</option>
											</select>
								  </div>
								  
								  <div class="form-group" >
								    <label for="exampleInputEmail1">Blood/Component Required</label>
								    <select name="bloodbank_component" id="bloodbank_component" class="form-control chosen-select">
								    		<option value="0">Select Blood Component </option>
											<option value="1">Red Cell Concentrate / RCC</option>
											<option value="2">Whole Blood / WB</option>
											<option value="3">Fresh Frozen Plasma / FPP</option>
											<option value="4">Cryoprecipitate of AHF / Factor VIII /Cryo</option>
											<option value="5">Platelet Concentrate /PC</option>
											<option value="6">Platelet Apheresis / Single Doner Platelets / SDP</option>
									</select>
								  </div>
								  
								   <div class="form-group" >
								    <label for="exampleInputEmail1">ID-NAT Tested</label>
								    <select name="bloodbank_idnattested" id="bloodbank_idnattested" class="form-control chosen-select">
								    		<option value="1">Yes</option>
											<option value="0">No</option>
									</select>
								  </div>
								  
								  <div class="form-group" >
								    <label for="exampleInputEmail1">Leuco Depleted</label>
								    <select name="bloodbank_leuco_depleted" id="bloodbank_leuco_depleted" class="form-control chosen-select">
								    		<option value="0">Select Leuco Depleted</option>
								    		<option value="---">---</option>
											<option value="LD">LD</option>
											<option value="LR">LR</option>
											<option value="Plain">Plain</option>
									</select>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Required Quantity(Unit)</label>
								    <select name="qty" id="blood_bank_qty" class="form-control chosen-select">
									    <option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								  </div>
						</div>
								
						</div>
					
					</div>
                      
				</div>
				<div class="modal-footer">
					<button type="button" onclick="return addbloodrequest()" class="btn btn-primary">Submit</button>
				</div>
				</form>
			</div>
		</div>
	</div>
   
   
   
   
   	<!-- Send Letter Details Modal -->
		<s:form id="upload" method="post" action="uploadSendMailInstantMsg"
					enctype="multipart/form-data" theme="simple">

	<div class="modal fade popoverpop" id="sendLetterPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			
     
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Send Email/Letter</h4>
				</div>
				<div class="modal-body" id="sendemail">
				
						<div class="row">
         <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
         <div class="col-lg-2 col-md-2 col-xs-12 col-sm-2 text-right">
				<label> User :</label>
		 </div>
		 <div class="col-lg-10 col-md-10 col-xs-12 col-sm-10">
				<%-- <s:select id="userNameletter" name="userName"  
				list="{'Client'}" cssClass="form-control  showToolTip chosen" 
				value="userName" onchange="setNameLetter(this.value)"></s:select>
				<label  id = "unameError" class="text-danger"></label>	 --%>
				<s:textfield name="userName" id="userNameletter" readonly="true" cssClass="form-control"  value="Client"  />
				<label  id = "unameError" class="text-danger"></label>	
		</div>
		</div>
		
		
		
		 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-2 text-right">
				<label>Select Template :</label>
		</div>
			  <div class="col-lg-10 col-md-10 col-xs-12 col-sm-10">
				<select id = "templateName" name = "templateName" onchange="showTemplateDetails(this.id)" class="form-control showToolTip chosen" data-toggle="tooltip">
						<option value="0">Select Template Name</option>
				</select>					
			</div>
		</div>
		
					
	
		<s:hidden name="id" id="id"></s:hidden>
		<s:hidden name="user" id="userletter"></s:hidden>
		<%-- <div class="col-lg-12">
		 <div class="col-lg-3">
			<label>User Name</label>
		</div>
		 <div class="col-lg-8">
			<s:textfield name="user" id="userletter" readonly="true" cssClass="form-control" />
				     <label  id = "userError" class="text-danger"></label>	
		<s:hidden name="id" id="id"></s:hidden>
		<s:hidden name="user" id="userletter"></s:hidden>
		</div>
		</div> --%>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		  <div class="col-lg-2 col-md-2 col-xs-12 col-sm-2 text-right">
			<label>Email ID : </label>
		</div>
		  <div class="col-lg-10 col-md-10 col-xs-12 col-sm-10">
			<s:textfield name="email" id="gpLetterEmail" cssClass="form-control showToolTip" title="Enter EmailId"
								data-toggle="tooltip" placeholder="Enter EmailId"/>
			<label  id = "emailError" class="text-danger"></label>	
		</div>
		</div>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-2 text-right">
			<label>Cc :</label>
		</div>
		 <div class="col-lg-10 col-md-10 col-xs-12 col-sm-10">
			<s:textfield theme="simple" id = "gpLetterccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />			
		</div>
		</div>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
		 <div class="col-lg-2 col-md-2 col-xs-12 col-sm-2 text-right">
			<label>Subject :</label>
		</div>
		 <div class="col-lg-10 col-md-10 col-xs-12 col-sm-10">
			<input type="text" name= "subject" id = "gpLettersubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">			
		</div>
		</div>
		
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-2 text-right">
			<label>Attachments :</label>
			</div>
			<div class=" col-md-2 col-lg-2 col-sm-2 col-xs-12" style="margin-bottom: 15px;">
			 <input id="Button1" class="btn btn-default"  type="button" value="Attach Files" onclick = "AddFileUpload()" />
			 </div>
			 <div class="col-lg-8 col-md-8 col-xs-12 col-sm-8">
			 <input type="hidden" id="fileUpload" name = "fileUploadd" />
 				 <div id = "drop">
 					
      			  <!--FileUpload Controls will be added here -->
      			  <div id="upload"></div>
      			  <div id = "draftAttachments"></div>
    			</div>
    			 
			 </div>
			 
				
   			</div>
		
       <br/>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-2 text-right">
				<label>Body :</label>
				</div>
				<div class="col-lg-10 col-md-10 col-xs-12 col-sm-10">
				<s:textarea cssStyle="height:2500px;" class="form-control showToolTip textarea"  data-toggle="tooltip" title="Write content" placeholder="Write content" name="body" id="emailBodyLetter" />
			</div>
			</div>
			
			
			   			
		</div>
		
      </div>
						
			<div class="modal-footer">
			<div class="row">
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="pdfFileIdLetter">
			</div></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="sendmail">
			</div></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="printId">
			</div></div>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<!-- <button type="button" id="saveId" class="btn btn-primary" onclick="return createPdf()">Save as pdf</button> -->
		<button type="button" id="saveId" class="btn btn-primary"  onClick="fileUpload1(this.form,'sendLetterEmailInstantMsg','upload'); return false;">Send Mail</button>
		<button type="button" class="btn btn-primary"  onclick="return createPdf()">Print</button>
		<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
      </div></div></div>
     
    </div>
  </div>
</div> 

</s:form>




<!-- Auto Charge-->
	 <div class="modal fade" id="addautocharge" tabindex="-1" role="dialog"
 aria-labelledby="lblsemdsmspopup" aria-hidden="true" style="z-index: 9997;">
  <div class="modal-dialog modal-md" style="width: 65%">
   <div class="modal-content">
    <div class="modal-header bg-primary">
     <button type="button" class="close" data-dismiss="modal">
      <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
     </button>
     <h4 class="modal-title" id="myModalLabel">Add Standard Charges for <span id="updatestdcharge"></span></h4>
    </div>
    <div class="modal-body">
      <div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #f5f5f5;padding: 10px 0px 10px 0px;border: 1px dashed #ddd;">
             
              <div class="col-lg-12 col-md-12" style="">
              
              <div class="col-lg-12 col-xs-12 col-md-12" style="margin-bottom: 10px;border-bottom: 1px dashed #ddd;line-height: 22px;">
              	<label class="checkbox inline">
				 <!--  <input onclick="toggleforenddate()" type="checkbox" name="stdbackdatechk" id="stdbackdatechk" value="option3"> Backdate -->
				  <input onclick="toggleforautochargenew(this.checked)" type="checkbox" name="stdbackdatechk" id="stdbackdatechk" value="option3"> Backdate
				</label>
              </div>
             
	              <div class="col-lg-6 col-md-6">
	               <label>Start Date/Time</label>
	                <s:textfield  cssClass="form-control" readonly="true" name="stddate"   placeholder="Start Date" id="stddate" />
	               </div>
	               <div class="col-lg-6 col-md-6">
	                <div id="autochargepp1" class="hidden">
		               	<label>End Date/Time</label>
		                <s:textfield readonly="true" cssClass="form-control" placeholder="End Date" id="enddate" name="enddate"  />
	               	 </div>
	               </div>
             
               
              </div>
              
              <div class="col-lg-12 col-md-12" style="margin-top:10px;">
               <div class="col-lg-6 col-md-6">
               <div class="form-inline">
                <div class="form-group" style="width:48%;">
                 <s:select name="hour" theme="simple" id="starthour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00" cssStyle="width:100%;"/>
                </div>
                <div class="form-group" style="width:48%;">
                <s:select name="minute" theme="simple" id="startminute" list="minuteList" cssClass="form-control" headerKey="00" headerValue="00" cssStyle="width:100%;"/>
                </div>
               </div>
               </div>
               <div class="col-lg-6 col-md-6">
               <div id="autochargepp2" class="hidden">
                <div class="form-inline">
                <div class="form-group" style="width:48%;">
                 <s:select name="hour" theme="simple" id="endhour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00" cssStyle="width:100%;"/>
                </div>
                <div class="form-group" style="width:48%;">
                <s:select name="minute" theme="simple" id="endminute" list="minuteList" cssClass="form-control" headerKey="00" headerValue="00" cssStyle="width:100%;"/>
                </div>
               </div>
               </div>
               </div>
              </div>
              </div>
             
             <div class="col-lg-12" style="padding:0px;">
             
             <div class="col-lg-12 col-md-12 col-xs-12" style="padding-left:0px;">
              <div>
         <legend>Standard Charges<span style="margin-left:26%">On Date/Time</span ><span style="margin-left:11%">Off Date/Time</span></legend>
          <div id="scrollpan">
          <%--  <s:checkbox id="selects" name="selects" type="checkbox"/>Select All <br> --%>
          <div style="overflow: scroll; height: 300px;">
           <span id="resultsearch">
           <s:iterator value="standardChargesList">
            <input class="scase" type="checkbox" id="ch<s:property value="id"/>" name="ch<s:property value="id"/>" onclick="" value="<s:property value="id"/>">
             <s:property value="name"/> <input type="button" value="update" onclick="modifyStdPopup(<s:property value="id"/>)" /> <br>
           </s:iterator>
           </span>
           </div>
           <input onclick="toggleforenddate()" type="hidden" name="checkStandardChargeExistid" id="checkStandardChargeExistid" value="<s:property value="checkStandardChargeExist"/>" />
           </div>
       </div>
             </div>
             
               
                 
      </div>
    </div>
    <div class="modal-footer smsbora">
     <button class="hidden" type="button" class="btn btn-primary"
      onclick="updatestandardCharges()">Update Account</button>
     
     <button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
    </div>
   </div>
  </div>
 </div>
   
  
<div id="editautocharge" class="modal fade" role="dialog" style="z-index: 9999;background-color: rgba(0, 0, 0, 0.5);">
          <div class="modal-dialog modal-sm">
           <!-- Modal content-->
           
           <div class="modal-content">
           
             <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
              
               <h4 class="modal-title">Edit Standard Charges for (<label id="editupdatestdcharge"></label>) </h4>
             </div>
             <div class="modal-body">
              
                <input type="hidden"  id="editstdipdid" />
               <input type="hidden" id="editstdchargeid" />
               <input type="hidden"  id="editstdstartend" />
               <input type="hidden"  id="editstdclientId"  />
               <input type="hidden"  id="editstdondate" />
               <input type="hidden"  id="editstdoffdate"  />
               <input type="hidden"  id="editstdxhildid"  />
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               		
	               <label>Charge Name:</label>
	                <label id="editstdchargename"></label>
	               
               </div>
               
               
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;border-bottom: 1px solid #ddd;">
               		<label>Date/Time</label>
	                <s:textfield  cssClass="form-control" readonly="true" name="stddate" id="editstddate"  placeholder="Start Date"  />
               </div>
               
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;border-bottom: 1px solid #ddd;margin-bottom: 15px;">
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 15px;margin-bottom: 15px;">
	                    <label>Hour</label>
	                    <s:select name="hour" theme="simple" id="editstarthour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00" cssStyle="width:100%;"/>
                    </div>
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 15px;margin-bottom: 15px;">
                        <label>Minute</label>
                        <s:select name="minute" theme="simple" id="editstartminute" list="minuteList" cssClass="form-control" headerKey="00" headerValue="00" cssStyle="width:100%;"/>
                    </div>
               </div>
               
              
              
             </div>
                
             <div class="modal-footer">
               <input type="button" class="btn btn-success disblebtnone" onclick="updatestDdatetime()" value="Update" />
             </div>
           </div>
          
       
         </div>
     
       </div>
  
    <!-- Priscription Popup -->
	 <div class="modal fade" id="viewpriscpopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Notification </h4>
				</div>
				<div class="modal-body">
				<div role="tabpanel">
				  <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#pres1" aria-controls="pres1" role="tab" data-toggle="tab">Prescription</a></li>
                                    <li role="presentation"><a href="#nurscare" aria-controls="nurscare" role="tab" data-toggle="tab">Nursing Care</a></li>
                                    <li role="presentation"><a href="#dietarycare" aria-controls="newlet" role="tab" data-toggle="tab">Dietary</a></li>
                               		<li role="presentation"><a href="#investigationalert" aria-controls="investigationalert" role="tab" data-toggle="tab">Investigation</a></li>
                                </ul>
				 			<div id="bellheight2" class="tab-content">
                                    <!-- tab in tabs -->
                                    <div role="tabpanel" class="tab-pane active" id="pres1">
                                        <!-- row -->
                                        <div class="">
                                            <!-- col -->
                                            <div class="">
                                              <table width="100%" class="table table-border">
							              		<tr style="background-color: #4E7894;color: #fff !important;">
							              			<th class="meditabwid">Medicine Name</th>
							              			<th>Dosage</th>
							              			<th>Confirm</th>
							              			<th>Note</th>
							              			<th>Time</th>
							              			<th>Remark</th>
							              			<th>Stop</th>
							              		</tr>
							              			<tbody id="ddprisc">
							              			  
							              		    </tbody>	
							              		</table>
                                            </div>
                                            <!-- /col -->
                                        </div>
                                        <!-- /row -->
                                    	</div>
                                    	<div role="tabpanel" class="tab-pane" id="nurscare">
                                        <!-- row -->
                                        <div class="">
                                            <!-- col -->
                                            <div class="">
                                                     <!-- Nursing Data -->
                                                        <table width="100%" class="table table-border">
							              		<tr style="background-color: #4E7894;color: #fff !important;">
							              			<th class="meditabwid">Task Name</th>
							              			<th>Frequency</th>
							              			<th>Confirm</th>
							              			<!--<th>Note</th>
							              		--></tr>
							              			<tbody id="ddnursing">
							              			  
							              		    </tbody>	
							              		</table>
                                            </div>
                                            <!-- /col -->
                                        </div>
                                        <!-- /row -->
                                    	</div>
                                    	<div role="tabpanel" class="tab-pane" id="dietarycare">
                                          <!-- row -->
                                        <div class="">
                                            <!-- col -->
                                            <div class="">
                                               <table width="100%" class="table table-border">
							              			<tr style="background-color: #4E7894;color: #fff !important;">
							              			<th class="meditabwid">Patient Name</th>
							              			<th>Morning</th>
							              			<th>Breakfast</th>
							              			<th>Mid Morning</th>
							              			<th>Lunch</th>
							              			<th>After Lunch</th>
							              			<th>Tea Time</th>
							              			<th>Evening Snacks</th>
							              			<th>Dinner</th>
							              			<th>Bedtime</th>
							              			</tr>
							              			<tbody id="dddietary">
							              			  
							              		    </tbody>	
							              		</table>
                                            </div>
                                            <!-- /col -->
                                        </div>
                                        <!-- /row -->
                                    	</div>
                                    	
                                    	
                                    	<!--   03 OCT 2017 added investigation information -->
                                    	 <!-- tab in tabs -->
                                    	<div role="tabpanel" class="tab-pane" id="investigationalert">
                                        <!-- row -->
                                        <div class="">
                                        <!-- col -->
                                        	<div class="">
                                              <table width="100%" class="table table-border">
							              		<tr style="background-color: #4E7894;color: #fff !important;">
							              			<th>Sr.No</th>
							              			<th>Investigation Type</th>
							              			<th>Date</th>
							              			<th>Print</th>
							              			<th>Pacs Report</th>
							              		</tr>
							              			<tbody id="tbodyinvestigationalert">
							              		    </tbody>	
							              		</table>
                                            </div>
                                            <!-- /col -->
                                        	</div>
                                        	<!-- /row -->
                                    	</div>
                                		<!-- End Investigation   -->
                                	</div>
                                	
			</div>
				
				
				   
						
				         
				</div>
				<div class="modal-footer smsbora">
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
   



    <!--Add Visiting Consult Modal-->
    <!-- Modal -->
    <div class="modal fade" id="addvisitingconsult" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add Visiting Consultant</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal" action="addvisitingIpdDashboard" id="visitform" method="post">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Select Doctor<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <s:select list="visitingConsultDoctors" listKey="id" listValue="fullname" cssClass="form-control" name="practitionerid" id="visitingConsultDoctors" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Date :</label>
                                <div class="col-sm-8">
                                    <s:textfield cssClass="form-control" name="date" id="vdate"></s:textfield>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Time<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <s:select list="visitingtimeList" cssClass="form-control" name="time" id="time"/>
                                </div>
                            </div>
                           
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Fees<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                   <s:textfield cssClass="form-control" id="fees" name="fees"></s:textfield>
                                </div>
                            </div>                           
						 <s:hidden name="clientid" id="vclientid"></s:hidden> 
						 <s:hidden name="ipdid" id="vipdid"></s:hidden>
						 <s:hidden name="id" id="visitid"></s:hidden>                            
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="submitvisitingconsult()" class="btn btn-primary">Add/Edit</button>
                </div>
            </div>
        </div>
    </div>
    
 



   <!-- Visiting Consult Popup -->

	 <div class="modal fade" id="visitingconsult" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Visiting Consult for <label id="lblvisiting"></label></h4>
				</div>
				<div class="modal-body">
				<div class="col-lg-12 text-right">
						<a href="#" class="btn btn-primary" onclick="addvisitingconsult()" type="button">Add</a>
				</div>
				         	<div class="col-lg-12">
								<table class="table table-bordered" cellspacing="0" width="100%">
								<thead>
								<tr>
								   <th>ID</th>
								   <th>Doctor Name</th>
								   <th>Date</th>
								   <th>Time</th>
								   <th>Fees</th>
								   <th>Status</th>
								   <th>Payment</th>
								   <th>Edit</th>
								   <th>Delete</th>
								   
								</tr>
								</thead>
								<tbody id="tbvisited">
								  <s:iterator value="visitingConsultList">
								<tr>
								   <td><s:property value="id"/></td>
								   <td><s:property value="fullname"/></td>
								   <td><s:property value="date"/></td>
								   <td><s:property value="time"/></td>
								   <td><s:property value="fees"/></td>
								   
								   <s:if test="status==1">
								     <td> <a class="btn btn-primary" onclick="" href="#">Visited</a></td>
								   </s:if>
								   <s:else>
								      <td> <a class="btn btn-primary" onclick="" href="#">Not Visited</a></td>
								   </s:else>
								   <s:if test="payment==1">
								     <td> <a class="btn btn-primary" onclick="" href="#">Paid</a></td>
								   </s:if>
								   <s:else>
								      <td> <a class="btn btn-primary" onclick="" href="#">Not Paid</a></td>
								   </s:else>
								   
								   
								 </tr>  
								 </s:iterator>
								</tbody>
								</table>
							</div>
							
						
				</div>
				<div class="modal-footer smsbora">
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>







    <!--Prescription Modal -->
<div class="modal fade" id="commonbellpopup" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
			<button  type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			
				<h3 class="modal-title custom-font">Date: <s:property value="priscdateandtime"/> </h3>
			</div>
			<div class="modal-body" style="height: 460px;">
			<div role="tabpanel">
				  <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#pres4" aria-controls="pres1" role="tab" data-toggle="tab">Prescription</a></li>
                                    <li role="presentation"><a href="#nurscare5" aria-controls="nurscare" role="tab" data-toggle="tab">Nursing Care</a></li>
                                    <li role="presentation"><a href="#newlet6" aria-controls="newlet" role="tab" data-toggle="tab">Dietary Management</a></li>
                                </ul>
				 <div id="bellheight" class="tab-content">
                                    <!-- tab in tabs -->
                                    <div role="tabpanel" class="tab-pane active" id="pres4">
                                        <!-- row -->
                                        <div class="">
                                            <!-- col -->
                                            <div class="">
                                              <table class="table table-bordered" width="100%" id="page_printer">
						<thead>
							<tr>
								<th class="allbed1">Patient  Details (<b id="count">0</b>)</th>
								<th>Nursing Schedule</th>
								
							</tr>
						</thead>

						<tbody id="pricscommonlist">
							<!--<s:iterator value="ipdpricsdoselist">							
								
									
							       
									            <tr>
												<td><span id="c<s:property value="id"/>"><i class="fa fa-bell"></i></span> / <s:property value="wardname"/> <br> <s:property value="clientname" /> <br><s:property value="age" /></td>
												<td>
												<table class="table table-bordered" width=100%>
												<thead>
												<tr>
												<th class="mednawidth dosatable">Task Name</th>
												<th class="text-center dosatable">08:00</th>
												<th class="text-center dosatable">09:00</th>
												<th class="text-center dosatable">10:00</th>
												<th class="text-center dosatable">12:00</th>
												<th class="text-center dosatable">14:00</th>
												<th class="text-center dosatable">16:00</th>
												<th class="text-center dosatable">18:00</th>
												<th class="text-center dosatable">20:00</th>
												<th class="text-center dosatable">21:00</th>
												<th class="text-center dosatable">22:00</th>
												<th class="bednote dosatable"> Notes</th>
												</tr>
												</thead>
												<tbody>
												     <s:if test="ipdPriscList.size>0">
												     <% int i=0; %>
												    <s:iterator value="ipdPriscList">
													<tr>
													<td><s:property value="mdicinenametxt" /></td>
													
													
													<s:if test="dosesize==3">
													<td></td>
														<s:if test="dosevalue1!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos1" id="dos1" onclick="toggleConfirmation(%{id},'dos1',%{dos1})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													<td></td>
													<td></td>
													<td></td>
														<s:if test="dosevalue2!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos2" id="dos2" onclick="toggleConfirmation(%{id},'dos2',%{dos2})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													
													<td></td>
													<td></td>
														<s:if test="dosevalue3!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos3" id="dos3" onclick="toggleConfirmation(%{id},'dos3',%{dos3})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													<td></td>
													<td><s:property value="dosenotes"/></td>
													</s:if>
													<s:if test="dosesize==4">
													<s:if test="dosevalue1!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos1" id="dos1" onclick="toggleConfirmation(%{id},'dos1',%{dos1})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													<td></td>
													<td></td>
													<td></td>
														<s:if test="dosevalue2!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos2" id="dos2" onclick="toggleConfirmation(%{id},'dos2',%{dos2})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													
													<td></td>
													<s:if test="dosevalue3!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos3" id="dos3" onclick="toggleConfirmation(%{id},'dos3',%{dos3})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													<td></td>
														
													<td></td>
													 <s:if test="dosevalue4!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos3" id="dos3" onclick="toggleConfirmation(%{id},'dos3',%{dos3})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
														<td><s:property value="dosenotes"/></td>
													</s:if> 
													 
													
													</tr>
													</s:iterator>
													</s:if>
													</tbody>
													</table>
												</td>
												
																		  
							</s:iterator>
						-->
						
						</tbody>

					</table>
                                            </div>
                                            <!-- /col -->
                                        </div>
                                        <!-- /row -->
                                    	</div>
                                    	<div role="tabpanel" class="tab-pane" id="nurscare5">
                                        <!-- row -->
                                        <div class="">
                                            <!-- col -->
                                            <div class="">
                                                     <!-- Nursing Data -->
                                                     <table class="table table-bordered" width="100%" id="page_printer">
						<thead>
							<tr>
								<th class="allbed1">Patient  Details (<b id="count">0</b>)</th>
								<th>Nursing Schedule</th>
								
							</tr>
						</thead>

						<tbody id="nursingcommonlist">
							<!--<s:iterator value="nursingdoseList">							
								
									
							       
									            <tr>
												<td><span id="c<s:property value="id"/>"><i class="fa fa-bell"></i></span> / <s:property value="wardname"/> <br> <s:property value="clientname" /> <br><s:property value="age" /></td>
												<td>
												<table class="table table-bordered" width=100%>
												<thead>
												<tr>
												<th class="mednawidth dosatable">Task Name</th>
												<th class="text-center dosatable">08:00</th>
												<th class="text-center dosatable">09:00</th>
												<th class="text-center dosatable">10:00</th>
												<th class="text-center dosatable">12:00</th>
												<th class="text-center dosatable">14:00</th>
												<th class="text-center dosatable">16:00</th>
												<th class="text-center dosatable">18:00</th>
												<th class="text-center dosatable">20:00</th>
												<th class="text-center dosatable">21:00</th>
												<th class="text-center dosatable">22:00</th>
												<th class="bednote dosatable"> Notes</th>
												</tr>
												</thead>
												<tbody>
												     <s:if test="ipdNursingList.size>0">
												     <% int i=0; %>
												    <s:iterator value="ipdNursingList">
													<tr>
													<td><s:property value="taskname" /></td>
													
													
													<s:if test="dosesize==3">
													<td></td>
														<s:if test="dosevalue1!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos1" id="dos1" onclick="togglenursingConfirm(%{id},'dos1',%{dos1})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													<td></td>
													<td></td>
													<td></td>
														<s:if test="dosevalue2!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos2" id="dos2" onclick="togglenursingConfirm(%{id},'dos2',%{dos2})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													
													<td></td>
													<td></td>
														<s:if test="dosevalue3!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos3" id="dos3" onclick="togglenursingConfirm(%{id},'dos3',%{dos3})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													<td></td>
													<td><s:property value="notes"/></td>
													</s:if>
													<s:if test="dosesize==4">
													<s:if test="dosevalue1!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos1" id="dos1" onclick="togglenursingConfirm(%{id},'dos1',%{dos1})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													<td></td>
													<td></td>
													<td></td>
														<s:if test="dosevalue2!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos2" id="dos2" onclick="togglenursingConfirm(%{id},'dos2',%{dos2})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													
													<td></td>
													<s:if test="dosevalue3!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos3" id="dos3" onclick="togglenursingConfirm(%{id},'dos3',%{dos3})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
													<td></td>
														
													<td></td>
													 <s:if test="dosevalue4!=0">
														    <td id="d<%=(++i)%><s:property value="ipdid"/>"><center><s:checkbox theme="simple" name="dos3" id="dos3" onclick="togglenursingConfirm(%{id},'dos3',%{dos3})" /></center></td>
														</s:if>
														<s:else>
														   <td></td>
														</s:else>
														<td><s:property value="notes"/></td>
													</s:if> 
													 
													
													</tr>
													</s:iterator>
													</s:if>
													</tbody>
													</table>
												</td>
																		  
							</s:iterator>
						-->
						</tbody>

					</table>
                                                     
                                            </div>
                                            <!-- /col -->
                                        </div>
                                        <!-- /row -->
                                    	</div>
                                    	<div role="tabpanel" class="tab-pane" id="newlet6">
                                        <!-- row -->
                                                                <div class="">
                                           
                                            <div class="col-md-12">
                                            <!-- Nursing Data -->
                                         <table class="table table-bordered" width="100%" id="page_printer">
											<thead>
											<!--<tr>
												--><th class="allbed1">Patient  Details (<b id="count">0</b>)</th>
												<!--<th>Nursing Schedule</th>
											-->
											<th class="text-center dosatable">Morning</th>
											<th class="text-center dosatable">Breakfast</th>
											<th class="text-center dosatable">Mid Morning</th>
											<th class="text-center dosatable">Lunch</th>
											<th class="text-center dosatable">After Lunch</th>
											<th class="text-center dosatable">Tea Time</th>
											<th class="text-center dosatable">Evening Snacks</th>
											<th class="text-center dosatable">Dinner</th>
											<th class="text-center dosatable">Bedtime</th>
											<!--</tr>
											--></thead>
												<tbody id="dietarylist">
											</tbody>
										</table>
				                     </div>
            					    <!-- /col -->
                    				</div>
		   	    
                                        <!-- /row -->
                                    	</div>
                                	</div>
			</div>
				
			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="printDiv('page_printer');">Print</button>
					
				</div>
		</div>
	</div>
</div>





<s:form action="getPatientRecordEmr" id="getPatientRecord" method="post" onsubmit="return redircttoNewEmr()" target="formtarget">
        <s:hidden name="diaryUser" id="diaryUserApmt"/>
        <s:hidden id = "conditionsApmt"  name = "conditionsApmt"></s:hidden>
        <s:hidden id="clientnameApmt" name="clientname"/>
        <s:hidden name="action" id="hdnaction"/>
        <s:hidden name="caldate" id="caldate"/>
        <s:hidden name="apmtId" id="apmtId"/>
             
   </s:form>



<!-- Modal -->
<div id="investipop" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Approved Investigation</h4>
      </div>
      <div class="modal-body">
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
        <div class="investipopset">
        	<table class="table tbale-responsive">
        		<thead>
        			<tr>
        				<th style="width: 22%;">Request Date/Time</th>
        				<th style="width: 59%;">Investigation</th>
        				<th style="width: 17%;">Approved Date/Time</th>
        			</tr>
        		</thead>
        		<tbody id="investtbody">
        			<tr>
        				<td>Admin / 18-09-2017 02:32</td>
        				<td>RENAL FUNCTION TEST (RFT) ~ BLOOD UREA, CREATININE, URIC ACID</td>
        				<td>19-09-2017 04:32</td>
        			</tr>
        		</tbody>
        	</table>
        </div>
        	
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<!--RMO notes  Modal -->
<div class="modal fade popoverpop" id="rmonotespopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Add Day To Day Notes <span id="addrmohead"></span></h4>
				</div>
				<div class="modal-body">
				 <div class="depart1" style="overflow: scroll; width: 100%; height: 150px;">
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <!-- <thead>
                                <tr>
                                	<td>Day</td>
                                	<td></td>
                                </tr>
                            </thead> -->
                            <tbody id="prermonote">
                             
                            </tbody>
                        </table>
                    </div>
                     <div style="width: 100%; height: 175px;">
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <tbody id="todayrmonote">
                            
                            </tbody>
                        </table>
                    </div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" 
						onclick="savermonotes()">Save
						</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>



<!--Vital Statatics  Modal -->
<div id="Vitalpop" class="modal fade" role="dialog" style="background-color: rgba(0, 0, 0, 0.44);">
  <div class="modal-dialog modal-lg" style="width: 97%;">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Vital Statistics</h4>
      </div>
      <div class="modal-body">
      <div class="row">
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors" style="border-bottom: 4px double #ddd;font-size: 16px;color: firebrick;">Vital Statistics</b>
								
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
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								  <b for="inputEmail3" class="control-label">Contact</b>
							</div>
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: SNH/17091400195</span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: Mrs. SUSHILA  KUJUR </span>
							</div>
							
							 <div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: 51Years / Female</span>
							</div>
							
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									  <span>: 09425521701</span>
								</div>
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: Bhavana Nagar Raipur, Raipur- </span> 
							</div>
						</div>
						
						
						
							
						
						
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Adm. ID/NO</b>           		 
								
								<br><b for="inputEmail3" class="control-label">Adm. Date</b>
							
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Ward / Bed</b>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Payee</b>
							  
						</div>
						
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Family Details</b> 
							</div>
						
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: 212/1</span>            		 
						
								  <br><span>: 18-09-2017 11:07:20 </span>
								  
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: 1F-CASUALTY 126 / HE04</span> 
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: Self</span>
							  
						</div>
						
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: AKANKSHA TIRKEY,9425521701 (DAUGHTER)</span> 
							</div>
						
						</div>
					</div>
					</div>
      </div>
      	<div class="row">
      		<div class="col-lg-12 col-md-12 col-xs-12">
      			<table class="table table-hover table-striped gridexample table-responsive">
      				<thead>
      					<tr>
      						<th style="width:1%;"></th>
      						<th>08</th>
      						<th>09</th>
      						<th>10</th>
      						<th>11</th>
      						<th>12</th>
      						<th>13</th>
      						<th>14</th>
      						<th>15</th>
      						<th>16</th>
      						<th>17</th>
      						<th>18</th>
      						<th>19</th>
      						<th>20</th>
      						<th>21</th>
      						<th>22</th>
      						<th>23</th>
      						<th>00</th>
      						<th>01</th>
      						<th>02</th>
      						<th>03</th>
      						<th>04</th>
      						<th>05</th>
      						<th>06</th>
      						<th>07</th>
      						<th>Total</th>
      					</tr>
      				</thead>
      				<tbody>
      					<tr>
      						<td style="border-bottom: none !important;">Temp F</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">HR</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">RR</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">BP</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">CVP..Hrly</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">MODE</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">Fio2</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">TV-I/E</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">PEEP</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">RR-I/E</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">SpO2</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">GCS</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">PRL-Rt</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control" style="background-color: rgba(255, 248, 220, 0.55) !important;"></td>
      					</tr>
      					<tr>
      						<td style="border-bottom: none !important;">PRL-Lt</td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control backkpi"></td>
      						<td style="border-bottom: none !important;padding: 1px 1px 1px 1px !important;"><input type="text" class="form-control"></td>
      					</tr>
      				</tbody>
      			</table>
      		</div>
      	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>


<!-- packages model -->
<div class="modal fade" id="pacakgesp" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Packages</h4>
      </div>
      <form action="savePkgDataIpdDashboard">
      <div class="modal-body">
     	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
        	<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #efefef;padding: 5px 5px 5px 5px;">
        		<div class="form-inline">
				  <div class="form-group">
				  	<input type="hidden" name="ipdid" id="pkgipdid">
				  	<input type="hidden" name="clientid" id="pkgclienid">
				    <label for="exampleInputName2">Start Date</label>
				    <s:textfield  name="pkgfromdate" id="pkgfromdate" cssClass="form-control"/>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail2">End Date</label>
				    <s:textfield  name="pkgtodate" id="pkgtodate" cssClass="form-control"/>
				  </div>
				  
				  <div class="form-group">
				    <label for="exampleInputName2">HH:MM</label>
									  <div class="form-group">
									    <s:select name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="0" headerValue="Select"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select name="minute" id="minute" list="minuteList" cssClass="form-control mmwidthmang" headerKey="0" headerValue="Select"/>
									  </div>
				  </div>
				   <div class="form-group">
				   <input type="button" value="Pkg" onclick="openpatientpkgmaster()" class='btn btn-primary'>
				   </div>
				  <!--
				  <span style="color: crimson;">3 Days</span>
				  <span style="float: right;font-size: 15px;margin-top: 4px;">Code: 123</span>
				--></div>
        	</div>
        	
        	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
        		<div class="col-lg-9 col-md-9 col-xs-8" style="padding: 0px;">
        		<div class="col-lg-12 col-md-12 col-xs-12" style="padding-left: 0px;">
        		<div class="form-inline">
					  <div class="form-group" style="width: 65%;">
					  <input type="hidden" name='tpnew' id='tpnew'>
					    <label class="sr-only" for="exampleInputEmail3">Email address</label>
					  <s:select onchange="showPackageData(this.value)" name="packagename" id="packagename" list="packageList"
					  	listKey="id" listValue="name" headerKey="0" headerValue="Select Package"
					  	cssClass="form-control chosen-select" style="width:100%;"
					  />
					  </div>
					  <div class="form-group">
					    <label class="sr-only" for="exampleInputEmail3">Email address</label>
					  <input type="text" placeholder="Change Name" id="newpackagename" name="newpackagename" class="form-control"/>
					  </div>
					  <button class="btn btn-warning hidden" data-toggle="collapse" data-target="#packagedemo">Add More</button>
					</div>
        		</div>
        	</div>
        	<div class="col-lg-3 col-md-3 col-xs-4" style="padding-right: 0px;">
        		<div class="form-group">
        			<span style="float: right;font-size: 15px;color: green;">Amount : <input type="text" id="pkgamtid" name="newpackageamt" onblur="changePackageTotal(this.value)" name="pkgamtid" class="form-control" style="width: 60%;float: right;"/></span>
        		</div>
        	</div>
        	</div>
        	
        	<div id="packagedemo" class="collapse">
				<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
        			<div class="form-inline">
						  <div class="form-group" style="width: 426px;">
						    <label class="sr-only" for="exampleInputEmail3">Email address</label>
						    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="Enter name" style="width:100%;background-color: aliceblue;">
						  </div>
						  <div class="form-group" style="width: 45px;">
						    <label class="sr-only" for="exampleInputPassword3">Password</label>
						    <input type="text" class="form-control" id="exampleInputPassword3" placeholder="%" style="width:100%;background-color: aliceblue;">
						  </div>
						  <button type="submit" class="btn btn-info">+</button>
						</div>
        		</div>
			</div>
        	
        	
        	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
	        		<table class="table" style="border: none;">
	        		<thead>
	        			<tr>
	        				<th style="width: 60%;background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        			</tr>
	        		</thead>
	        			<tbody id="pkgdtailbody">
	        				<!--<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Ortho Surgen</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="50%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.50,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">OT Charge</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="20%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.20,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Anesthesia Surgen</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Equipment</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Other</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;background-color: cornsilk;">
	        					<td style="font-size: 14px;"><b>Total</b></td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="100%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><b><input style="text-align: right;" type="text" class="form-control" value="Rs.1,00,000.00"></b></td>
	        					<td></td>
	        				</tr>
	        			--></tbody>
	        		</table>
        	</div>
        </div>
      </div>
      <div class="modal-footer" style="padding-right: 10px;">
        <input type="submit" onclick="return validatepackage()" class="btn btn-primary" style="margin-top: 15px;" value="Apply Package">
      </div>
      </form>
    </div>
  </div>
</div>


 <div class="modal fade" id="pkgeditlistmodal" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    Modal content
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Edit Applied Packages</h4>
      </div>
      <form action="savepkgDataeditIpdDashboard">
      	<div class="modal-body">
     		
     			<div id ="appliedpkglistdiv" style="width: 50%">
			
      		</div>
      		<div style="width: 100%">
      		<br>
      		<input type="hidden" name='hidenipdid' id='hidenipdid'>
      		<div class='form-inline'>
      		<label>Fromdate :</label><span ><input type="text" name='fdt1' id='fdt1'  class='form-control' style="width: 14%" ></span>
      		 <s:select name="hrpkg1" theme="simple" id="hrpkg1" list="hourList" cssClass="form-control" headerKey="0" headerValue="Select" cssStyle="width:7%;"/>
      		  <s:select name="mntpkg1" theme="simple" id="mntpkg1" list="minuteList" cssClass="form-control" headerKey="0" headerValue="Select" cssStyle="width:7%;"/>
      	
      		<label>Todate :</label><span><input type="text" name='tdt1' id='tdt1' class='form-control' style="width: 14%" ></span>
      		 <s:select name="hrpkg" theme="simple" id="hrpkg" list="hourList" cssClass="form-control" headerKey="0" headerValue="Select" cssStyle="width:7%;"/>
      		  <s:select name="mntpkg" theme="simple" id="mntpkg" list="minuteList" cssClass="form-control" headerKey="0" headerValue="Select" cssStyle="width:7%;"/>
      		<label>Amount </label> :<input type="number" name='amtpkgedit' class='form-control' id='amtpkgedit' style="width: 10%">		
      		</div>
      		</div>
     
      	<br>
      	<div id='listeditpkg'>
      	<table class='my-table table'>
      	<thead>
      	<tr>
      	<th>Sr. No.</th>
      	<th>Name</th>
      	<th>Amount</th>
      	</tr>
      	</thead>
      	<tbody id='listeditpkgbody'>
      	
      	</tbody>
      	</table>
      	</div>
      	<div>
      	<input type="submit" class='btn btn-success'>
      	</div>
     	</div>
      </form>
     </div>
     </div>
     </div>
    	





<!-- Three Icon show on Admin Patient Modal design by abhi 27oct2017 -->
<div id="threepopup" class="modal fade" role="dialog" style="background-color: rgba(0, 0, 0, 0.63);">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Modal Header</h4>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
        					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 padrigset">
				    				<a href="#" onclick="leftcasualtyoatient()">
                                      <div class="thumbnail">
                                          <img src="cicon/dischargebed.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Left</p></div>
                                      </div>
                                  </a>
                              </div>
                              <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 padrigset">
				    				<a href="#" onclick="openadmitpatient()">
                                      <div class="thumbnail">
                                          <img src="cicon/dischargebed.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Shifted To IPD</p></div>
                                      </div>
                                  </a>
                              </div>
                              <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 padrigset">
				    				<a href="#">
                                      <div class="thumbnail">
                                          <img src="cicon/dischargebed.png" alt="..." class="">
                                          <div class="caption"><p align="center" class="fontpup">Shifted To OT</p></div>
                                      </div>
                                  </a>
                              </div>
        		
        	</div>
        </div>
      </div>
      <div class="modal-footer hidden">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>




<!--Upload Model-->
<div class="modal fade" id="uploaddoc" style="z-index: 9999" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="uploadDocTitle">Upload New Document</h4>
			</div>

			<div class="modal-body">
				<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<s:form id="upload" method="post" action="uploadDocumentsEmr" enctype="multipart/form-data" theme="simple">
					<s:hidden name="isvideo" id="isvideo"></s:hidden>
					
					<div class="form-group">
						<s:select cssClass="form-control fbackwhi" headerKey="0"
						headerValue="Select Type" name="doctType" id="doctType" onchange="setemruploaddocAjax(this.value,'doctype')"
						list="{'GP Doc','TP Doc','Medical Record','Consultant Report','Assessment Report','Investigation','Admission Form','Discharge Form','Nursing','Others'}" cssStyle="margin-bottom: 10px !important;"></s:select>
					</div>
					
					<div class="form-group">
						<s:textarea cssClass="form-control fbackwhi" onblur="setemruploaddocAjax(this.value,'disc')"
						placeholder="Enter Document Note" rows="3" name="documentDesc"
						id="documentDesc"></s:textarea>
					</div>
					
					
					<span id="filename" style="color: white"></span>


					<div id="drop" style="background-color: #efefef;">
						Drop Here <a>Browse</a>
						<s:file name="files" theme="simple">
						</s:file>
					</div>

					<ul class="popmodals123">
						<!-- The file uploads will be shown here -->
					</ul>

				</s:form>
				</div>
					
				</div>
				



			</div>
			<div class="modal-footer">
				<s:form action="addDocumentsEmr" method="post" theme="simple">
					<s:hidden id="clientname" value="%{clientname}" name="clientname"></s:hidden>
					<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
					<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
					<s:hidden id="editDoctId" name="editDoctId"></s:hidden>
					<s:hidden id="ipdopdemr" value="1" name="ipdopdemr"></s:hidden>
					<s:hidden id="docapmtId" name="apmtId" />
					<!-- <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->

					<button type="submit" class="btn btn-primary start">Save</button>
				</s:form>

			</div>
		</div>
	</div>
</div>
<!--/Upload Model-->



<div class="modal fade" id="cancelipdpopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="">Confirm Cancel Admission?</h4>
				</div>
				<div class="modal-body">
				<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				    
				     <textarea rows="4" placeholder="enter cancel notes" cols="5" name="cancelnotes" id="cancelnotes" class="form-control"></textarea>
				</div>
				</div>
					
				</div>
				<div class="modal-footer">
				    <button  type="button" class="btn btn-primary" onclick="cancelIpd()" >Ok</button>
					<button  type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>




<!-- Discharge Last View  or Discharge Care -->
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


<div class="modal fade popoverpop" style="background: rgba(255, 255, 255, 0.93) !important;z-index: 4 !important;" id="circleloading" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" aria-hidden="true">
		<div class="modal-dialog"   role="document">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	


<!-- create Nursing Care -->
		 <div class="modal fade" id="addnursing" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="nursingmyModalLabel">Create Nursing Care For <b class="pname">NAME: </b></h4>
				</div>
				<div class="modal-body" id="nursingopoup">
					<jsp:include page="/diarymanagement/pages/addNursingCare.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveNursing()">Save</button>
					
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

     



<!-- package Edit Apllied -->

 <div class="modal fade" id="pkgeditlistmodal" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static">
		
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" style="margin-left: -200px !important;margin-right: -200px !important;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"> Select Applied Package </h4>
      </div>
      <div class="modal-body">

			
			<div class="col-lg-12 col-md-12 col-sm-12">
		<div id ="appliedpkglistdiv">
			
      		</div>
      		</div>
      		</div>
      <div class="modal-footer">
       <input type="button" class="btn btn-danger" onclick="" data-dismiss="modal" value="Save">
        
        
      </div>
    </div>

  </div>
</div>





<!-- IPD Patient Report -->
<table class="my-table tableipdxls" id = "ipdPatientTable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
		    <tr>
		     <th>Sr No</th>
		     <th>UHID</th> 
		     <th>Ward/Bed</th>
		     <th>Patient Name</th>
		     <th>Payee</th>
		     <th>Admiting Doctor</th>
		     <th>Refered Doctor</th>
		     <th>MLC No</th>
		     <th>MLC Refered Doctor</th>
		     <th>Diagnosis</th>
		     <th>Date Of Admission</th>
		     <th>Date Of Discharged</th>
		     <th>Total Days</th>
		     <th>Status</th>
		    </tr>
		    </thead>
             <tbody>
             <%int i=0; %>
          <%--    <s:iterator value="ipdPatientReport">
               
                <tr>
                <td><%=(++i) %></td>
               <td><s:property value="abrivationid"/></td>
               <td><s:property value="wardbedname"/></td>
                <td><s:property value="clientname"/></td>
                <td><s:property value="whopay"/></td>
                <td><s:property value="practitionername"/></td>
                <td><s:property value="refferedby"/></td>
                <td><s:property value="mlcno"/></td>
                <td><s:property value="mlcrefdoctor"/></td>
                <td><s:property value="conditionname"/></td>
                <td><s:property value="doanew"/></td>
                <td><s:property value="dodnew"/></td>
                <td><s:property value="totalDays"/></td>
                <td><s:property value="status"/></td>
                </tr>
             </s:iterator>  --%>
             </tbody> 
 			</table>










<script type="text/javascript" src="plugin/Stepsforvital/jq.stepform.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>


<script>
var followupbtn= document.getElementById("followuppop");
var followupmodal= document.getElementById("followupmodal");
var followclose= document.getElementById("followclose");
followupbtn.onclick = function() {
	followupmodal.style.display = "block";
	$("#lokeshfollowdatenew").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		
		changeMonth : true,
		changeYear : true,
		minDate:'0',
	});
}
followclose.onclick = function() {
	followupmodal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == followupmodal) {
    	followupmodal.style.display = "none";
    }
}



function printIpdExcel() {

    $(".tableipdxls").table2excel({
				exclude: ".noExl",
				name: "Ipd Patients List",
				filename: "ipdPatientList",
				fileext: ".xls",
				exclude_img: true,
				exclude_links: true,
				exclude_inputs: true
			});
     }
</script>




	<script>
	$(document).ready(function(){
		    $("#filter").keyup(function(){
		 
		        // Retrieve the input field text and reset the count to zero
		        var filter = $(this).val(), count = 0;
		 
		        // Loop through the comment list
		        $(".commentlist").each(function(){
		 
		            // If the list item does not contain the text phrase fade it out
		            if ($(this).text().search(new RegExp(filter, "i")) < 0) {
		                $(this).fadeOut();
		 
		            // Show the list item if the phrase matches and increase the count by 1
		            } else {
		                $(this).show();
		                count++;
		            }
		        });
		 
		        // Update the count
		        var numberItems = count;
		        $("#filter-count").text("Number of Comments = "+count);
		    });
		});
</script>	 

   



<script>
	$('#element').toolbar( options );
</script>


  

   
   <script><!--
   
   $('#selects').click(function(event) {  //on click 
        if(this.checked) { // check select status
            $('.scase').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"               
            });
        }else{
            $('.scase').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
            });         
        }
      });    
   
   
   
   
   
   
 $(document).on(
   "click",
   ".btn_prescription",
   function() {
    if ($(this).find('i').hasClass("fa-minus-square")) {
     $(this).find('i').addClass("fa-plus-square").removeClass(
       "fa-minus-square");
    } else {
     $(this).find('i').addClass("fa-minus-square").removeClass(
       "fa-plus-square");
    }

   });
 $(function() {
  $('#pop-height').slimScroll({
   		height : '500px'
  });
  $('#precriptionpopup').slimScroll({
        height: '502px'
  });
  $('#investipopup').slimScroll({
        height: '459px'
  });
   $('#sendemail').slimScroll({
        height: '500px'
  });
  $('#bellheight').slimScroll({
        height: '400px'
  });
  $('#bellheight2').slimScroll({
        height: '420px'
  });
  $('.investipopset').slimScroll({
      height: '420px'
});
  $('.dischpoints').slimScroll({
      height: '365px',
      railVisible: true,
	  alwaysVisible: true
});
  $('.nursenote').slimScroll({
      height: '165px',
      railVisible: true,
	  alwaysVisible: true
});

 });
</script>


<script>
function openimmu(){
	
}
</script>

<script>


	$(".modal-draggable .modal-dialog").draggable({
        handle: ".modal-header"
    });
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
	
<script>
//vital graph created by abhi

Highcharts.chart('vgraph1', {
    chart: {
        type: 'line'
    },
    title: {
        text: 'Graph'
    },
    subtitle: {
        text: 'Dt:16-10-2017'
    },
    xAxis: {
        categories: ['1hr', '2hr', '3hr', '4hr', '5hr', '6hr', '7hr', '8hr', '9hr', '10hr', '11hr', '12hr']
    },
    yAxis: {
        title: {
            text: 'Temperature (°C)'
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: true
            },
            enableMouseTracking: false
        }
    },
    series: [{
        name: 'XYZ',
        data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
    }, {
        name: 'ABC',
        data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
    }]
});
</script>

   <script type="text/javascript">
   //print vitals given by abhi 16-10-2017
        function PrintDiv123() {
            var contents = document.getElementById("dvContents").innerHTML;
            var frame1 = document.createElement('iframe');
            frame1.name = "frame1";
            frame1.style.position = "absolute";
            frame1.style.top = "-1000000px";
            document.body.appendChild(frame1);
            var frameDoc = frame1.contentWindow ? frame1.contentWindow : frame1.contentDocument.document ? frame1.contentDocument.document : frame1.contentDocument;
            frameDoc.document.open();
            frameDoc.document.write('<html><head><title>Patient Vitals</title>');
            frameDoc.document.write('</head><body>');
            frameDoc.document.write(contents);
            frameDoc.document.write('</body></html>');
            frameDoc.document.close();
            setTimeout(function () {
                window.frames["frame1"].focus();
                window.frames["frame1"].print();
                document.body.removeChild(frame1);
            }, 500);
            return false;
        }
    </script>


   
