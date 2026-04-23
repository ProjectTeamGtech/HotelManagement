<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<script type="text/javascript" src="inventory/js/addvendor.js"></script>
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
<script type="text/javascript" src="diarymanagement/js/lmhregister.js"></script>
<%-- <script type="text/javascript" src="diarymanagement/js/addPatient.js"></script>
 --%>
 
 <%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>



<link href="_assets/newtheme/css/main.css" rel="stylesheet" />
 
  <style>
	.nav-tabs { border-bottom: 2px solid #DDD;background-color: #f5f5f5; }
	.nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover { border-width: 0; }
	.nav-tabs > li > a { border: none; color: #666; }
	.nav-tabs > li.active > a, .nav-tabs > li > a:hover { border: none; color: #43b9be !important; background: transparent; }
	.nav-tabs > li > a::after { content: ""; background: #43b9be; height: 2px; position: absolute; width: 100%; left: 0px; bottom: -1px; transition: all 250ms ease 0s; transform: scale(0); }
	.nav-tabs > li.active > a::after, .nav-tabs > li:hover > a::after { transform: scale(1); }
	.tab-nav > li > a::after { background: #21527d none repeat scroll 0% 0%; color: #fff; }
	.tab-pane { padding: 15px 0; }
	.tab-content{padding: 0px 10px 0px 15px;}
	.card {background: #FFF none repeat scroll 0% 0%; box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.3); margin-bottom: 30px; }
#searchText{ 
 width: 420% !important;
 }
 /*Now the CSS*/
* {margin: 0; padding: 0;}

/* input:focus { 
	color: #DE2A00  
} */
.btn{
border-radius: 0.75rem;
}
.tree ul {
	padding-top: 15px; position: relative;
	
	-transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

.tree li {
	float: left; text-align: center;
	list-style-type: none;
	position: relative;
	padding: 20px 5px 0 5px;
	
	-transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*We will use ::before and ::after to draw the connectors*/

.tree li::before, .tree li::after{
	content: '';
	position: absolute; top: 0; right: 50%;
	border-top: 1px solid #ccc;
	width: 50%; height: 20px;
}
.tree li::after{
	right: auto; left: 50%;
	border-left: 1px solid #ccc;
}

/*We need to remove left-right connectors from elements without 
any siblings*/
.tree li:only-child::after, .tree li:only-child::before {
	display: none;
}

/*Remove space from the top of single children*/
.tree li:only-child{ padding-top: 0;
	    width: 100%;
    position: absolute;
}

/*Remove left connector from first child and 
right connector from last child*/
.tree li:first-child::before, .tree li:last-child::after{
	border: 0 none;
}
/*Adding back the vertical connector to the last nodes*/
.tree li:last-child::before{
	border-right: 1px solid #ccc;
	border-radius: 0 5px 0 0;
	-webkit-border-radius: 0 5px 0 0;
	-moz-border-radius: 0 5px 0 0;
}
.tree li:first-child::after{
	border-radius: 5px 0 0 0;
	-webkit-border-radius: 5px 0 0 0;
	-moz-border-radius: 5px 0 0 0;
}

/*Time to add downward connectors from parents*/
.tree ul ul::before{
	content: '';
	position: absolute; top: 0; left: 50%;
	border-left: 1px solid #ccc;
	width: 0; height: 15px;
}

.tree li a{
	border: 1px solid #ccc;
	padding: 4px 8px;
	text-decoration: none;
	color: #666;
	font-family: arial, verdana, tahoma;
	font-size: 11px;
	display: inline-block;
	
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	
	-transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*Time for some hover effects*/
/*We will apply the hover effect the the lineage of the element also*/
.tree li a:hover, .tree li a:hover+ul li a {
	background: #c8e4f8; color: #000; border: 1px solid #94a0b4;
}
/*Connector styles on hover*/
.tree li a:hover+ul li::after, 
.tree li a:hover+ul li::before, 
.tree li a:hover+ul::before, 
.tree li a:hover+ul ul::before{
	border-color:  #94a0b4;
}
.settitle{
	    margin: 0px;
    text-align: center;
    color: #5cb85c;
}
/*Thats all.*/
 
 .ajpadzero{
	 padding: 0 !important;
 }
 .ajlineheightzero{
	 line-height: 0 !important;
 }
 .ajsolidborder{
 	border: 1px solid rgba(0, 0, 0, 0.1) !important;
 }
/*  #county_chosen{
 	padding-top: 0px !important;
 } */
 
 .table-bordered tr:hover {
	background-color: #b3ebec;
}

.form-control:focus { 
    outline: none !important;
    border-color: #719ECE !important;
    box-shadow: 0 0 10px #719ECE !important;
}
/* div.chosen-container a{
	outline: none !important;
    border-color: #719ECE !important;
    box-shadow: 0 0 10px #719ECE !important;
} */

.btn-primary{
background-color: #15536E !important;
}
 </style>
 <script>
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
</script>

  <s:if test="isfromaddpatientlmh==1">
  <SCRIPT type="text/javascript">
	/* document.onreadystatechange = function(){
      if (document.readyState === "complete") {
    	var typeid=document.getElementById("dob").value;
      	if(typeid!=''){
      		getAgendDays(typeid);
      	}
      	var whopay = document.getElementById("whopay").value;
      	enabledFiled(whopay);
       	
      	if(whopay=='Third Party'){
      		var type=document.getElementById("type").value;
      		if(type!='0'){
      			setTPName(type);
      		}
      		
      	}
      }
  }    */
 window.onload= function(){
	  <%-- <%if(loginfo.isPhysio()){%>
	    document.getElementById("savebtn").disabled=true;
	  <%}%> --%>
  	var typeid=document.getElementById("dob").value;
  	getAgendDays(typeid);
  	document.getElementById("yuviloader").style.display="none";
  	document.getElementById("addpatientbtn").style.display="none";
  	 if ($('#typeName').val() == '52' || document.getElementById("isCampArea").value==1) {
         $('#campareaId').show();
     }
  	  if(document.getElementById("planids") && document.getElementById("planids").value>0){
  		 var activeplan= document.getElementById("planids").value;
  			document.getElementById("plan"+activeplan).disabled="";
  		}
  	//alert("hello"+document.getElementById("regularpatients").value);
  	 if(document.getElementById("regularpatients").value==1){
  		//document.getElementById("regularpatients").value
     	document.getElementById("plan"+activeplan).disabled=true;
     	document.getElementById("plan3").disabled=false;
     	document.getElementById("plan4").disabled=false;
     	document.getElementById("plan5").disabled=false;
     	
     }
   } 

 </script>
 </s:if> 
 <s:else>
 <script>
 window.onload= function(){
	 <%-- <%if(loginfo.isPhysio()){%>
	    document.getElementById("savebtn").disabled=true;
	  <%}%> --%>
  	document.getElementById("yuviloader").style.display="none";
	document.getElementById("addpatientbtn").style.display="none";
	/* showAllPatientPopUp(); */
	  if(document.getElementById("planids") && document.getElementById("planids").value>0){
		  var activeplan= document.getElementById("planids").value;
		 	document.getElementById("plan"+activeplan).disabled="";
		   }
	  if(document.getElementById("regularpatients").value==1){
	  		//document.getElementById("regularpatients").value
	     	document.getElementById("plan"+activeplan).disabled=true;
	     	document.getElementById("plan3").disabled=false;
	     	document.getElementById("plan4").disabled=false;
	     	document.getElementById("plan5").disabled=false;
         } 
	  }
 </script>
 </s:else>
<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" style="padding: 0">
<%String actionvalue="saveRegistration"; %> 
<s:if test="isfromaddpatientlmh==1">
	<%actionvalue="updateRegistration"; %>
</s:if>

 <% String cghs="";
	 String wcl="";
 	String hidediv="";
 	String insurance="";
 	String hidecol="";
 	String hideenroll="";
 %>
<s:if test="isfromaddpatientlmh==1">
	<s:if test="tptypenamestatus==''">
       <% hidediv="hidden"; %>
 	</s:if>
 	<s:elseif test="tptypenamestatus=='CGHS'">
	  	<%cghs="hidden"; 
	  	hidediv="";
	  	hidecol="hidden";
	  	%>
	</s:elseif>
 	<s:elseif test="tptypenamestatus=='WCL'">
	      <% hidediv="";
	      wcl="hidden";
	      hidecol="hidden";%>
	</s:elseif>
	<s:elseif test="tptypenamestatus=='INSURANCE'">
		<%hidediv="";
		cghs="hidden";
		wcl="hidden";
		insurance="hidden"; 
		%>
	</s:elseif>
	<s:if test="statusenrollcode==1">
		<%hideenroll="";
	  	%>
	</s:if>
</s:if>
<s:else>
	<s:if test="whopay=='Client'">
		<%hidediv = "hidden"; 
		hideenroll="hidden";
		%>
	</s:if>
</s:else>
<script>
document.addEventListener('keypress', function (e) {
    if (e.keyCode === 13 || e.which === 13) {
        e.preventDefault();
        searchChargeByEmpCode();
        return false;
    }
    
});
</script>

 <form action="<%=actionvalue%>" theme="simple" name="clientfrm" id="clientfrm" enctype="multipart/form-data">
 						<s:hidden id="isfromaddpatientlmh" name="isfromaddpatientlmh"></s:hidden>
 						<s:hidden id="isfromaddpatientlmh123" name="isfromaddpatientlmh123" value="1"></s:hidden>
 						<s:hidden name="physioipd" id="physioipd" value="1"></s:hidden>
 						<s:if test="isfromaddpatientlmh==1">
 							<s:hidden id = "id" name = "id"></s:hidden>
 						</s:if>
                        <s:hidden id="profileimg" name="profileimg"></s:hidden>


                        <input type="hidden" id="doblbl" name="doblbl" />
                        <input type="hidden" id="addressErrorlbl" name="addressErrorlbl" />
                        <input type="hidden" id="townErrorlbl" name="townErrorlbl" />
                        <input type="hidden" id="postCodeErrorlbl" name="postCodeErrorlbl" />
                        <input type="hidden" id="savetpbtn" name="savetpbtn" />
                        <input type="hidden" id="gpnameErrorlbl" name="gpnameErrorlbl" />
 						<input type="hidden" id="mobNoErrorlbl" name="mobNoErrorlbl" />
 						<input type="hidden" id="genderErrorlbl" name="genderErrorlbl" />
 						<input type="hidden" id="adhnoErrorlbl" name="adhnoErrorlbl" />
 						<input type="hidden" id="campAreaErrorlbl" name="campAreaErrorlbl" />
 						<s:hidden id="isCampArea" name="isCampArea"></s:hidden>
 						
 
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4><%=loginfo.getPatientname_field()%> Registration</h4>
									</div>
								</div>
								<div class="row">
									
									<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12" style="margin-top: 2px;margin-bottom: 2px;">
										<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12" style="margin-top: 2px;margin-bottom: 2px;">
										<s:textfield name="serachtxt" id="searchtxt" cssStyle="width:90%;border: 1px solid !important;" placeholder="Search" onclick="showAllPatientPopUp()" cssClass="form-control showToolTip ajsolidborder"></s:textfield> 
										<!-- <a class="btn btn-primary" onclick="serachpatientbyUHID()" >Search</a> -->
										<!-- <a class="btn btn-primary"  >Search</a> -->
									</div>
								 <%if(!loginfo.isMatrusevasang()){ %> 	
									<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12" style="margin-top: 2px;margin-bottom: 2px;margin-right: -114px">
									   
									   <%if(!loginfo.isPhysio() && !loginfo.isAmravati()){ %>
									       <s:textfield name="serachtxt1" id="searchcode" cssStyle="width:70%;border: 1px solid !important;" placeholder="Search by Employee Id"  cssClass="form-control showToolTip ajsolidborder"></s:textfield> 
									       
									   <%}else{ %>
										<s:textfield name="serachtxt" id="search" cssStyle="width:70%;border: 1px solid !important;" placeholder="Search by Employee Id" onclick="showAllUserPopUp()" cssClass="form-control showToolTip ajsolidborder"></s:textfield> 
										<%} %>
										<!-- <a class="btn btn-primary" onclick="serachpatientbyUHID()" >Search</a> -->
										<!-- <a class="btn btn-primary"  >Search</a> -->
									</div>
								 <%} %>	
									<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12 text-left" style="margin-top: 4px;margin-bottom: 2px;">
										<s:if test="isfromaddpatientlmh!=0">
											<label><strong>UHID :</strong></label> 
											<label><s:property value="Abrivationid"/></label>
										</s:if>	
									</div>
										<%-- <div class="col-md-2 col-lg-2 col-xs-12 col-sm-12 text-left" style="margin-top: 10px;margin-bottom: 2px;">
										<s:if test="isfromaddpatientlmh!=0">
										<input type="button" value="Account" onclick="openPopup('Statement?clientId=<s:property value="id"/>')">
										</s:if>
										</div> --%>
									<s:if test="isfromaddpatientlmh==0">
										<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 text-right" style="margin-top: 6px;margin-bottom: 2px;">
											<input type="radio" id="regwopd" name="opdoption" value="regwopd" style="margin: 0px !important;" checked="checked" onchange="hidevaldiv(0)">
											<label for="regopt">New Registration With OPD</label>&emsp;&emsp;&emsp;&emsp;
											<input type="radio" id="onlyreg" name="opdoption" value="onlyreg" style="margin: 0px !important;" onchange="hidevaldiv(1)">
											<label for="opdopt">New Registration </label>
										</div>
									</s:if>
									<s:else>
										<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 text-right" style="margin-top: 10px;margin-bottom: 2px;">
											<input type="radio" id="regwopd" name="opdoption" value="regwopd" style="margin: 0px !important;" checked="checked" onchange="hidevaldiv(0)">
											<label for="regopt">Update Patient With OPD</label>&emsp;&emsp;&emsp;&emsp;
											<input type="radio" id="onlyreg" name="opdoption" value="onlyreg" style="margin: 0px !important;" onchange="hidevaldiv(1)">
											<label for="opdopt">Update Patient Only </label>
										</div>
									</s:else>
										<%if (loginfo.isReport() == true) {
											%>
											<div class="bs-docs-example">
            									<div class="btn-toolbar" style="margin: 0;">
									              <div class="btn-group">
									                <button class="btn  active dropdown-toggle" style="border-radius: 0.50 rem;" data-toggle="dropdown">Report <span class="caret"></span></button>
									                <ul class="dropdown-menu" style="left: -116px;">
                                                            <li><a href="#" onclick="openPopup('paymentReportChargesRpt')"><i class="fa fa-caret-right"></i> Payment Report Detailed</a></li>
                                                            <li><a href="#" onclick="openPopup('smallpaymentReportChargesRpt')"><i class="fa fa-caret-right"></i> Payment Report Summary</a></li>
                                                            <li><a href="#" onclick="openPopup('userwisepaymentreportSummary')"><i class="fa fa-caret-right"></i> User Wise Payment Report</a></li>
                                                            <li><a href="#" onclick="openPopup('payment_report_combinnedChargesRpt')"><i class="fa fa-caret-right"></i>Payment Report Combined</a></li>
               										 </ul>
								              	</div><!-- /btn-group -->
								            </div><!-- /btn-toolbar -->
								          </div>
	                            <%} %>
								</div>
									<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
                                    <!-- Nav tabs -->
                                    <!-- <div class="card" > -->
                                    <div class="" style="border: 1px solid rgba(0, 0, 0, 0.1);    margin-bottom: 5px;">
                                    <ul class="nav nav-tabs hidden" role="tablist">
                                        <li role="presentation" class="active"><a href="#one" aria-controls="one" role="tab" data-toggle="tab">Name/Address</a></li>
                                        <li role="presentation"><a href="#two" aria-controls="two" role="tab" data-toggle="tab">Contacts</a></li>
                                        <li role="presentation"><a href="#three" aria-controls="three" role="tab" data-toggle="tab">Third Party</a></li>
                                        <li role="presentation"><a href="#four" aria-controls="four" role="tab" data-toggle="tab">Reference</a></li>
                                        <li role="presentation"><a href="#five" aria-controls="five" role="tab" data-toggle="tab">Others</a></li>
                                        <li role="presentation"><a href="#six" aria-controls="six" role="tab" data-toggle="tab">Notes</a></li>
                                        <li role="presentation"><a href="#seven" aria-controls="seven" role="tab" data-toggle="tab">Health Records</a></li>
                                        
                                    </ul>
                                    <!-- Tab panes -->
                                    <div class="tab-content">
                                        <div role="tabpanel" class="active" id="one" style="padding-top: 15px">
                                        	<div class="row" id = "firstNameErrorlbl" >
                                        		<div class="col-lg-3 col-md-3 hidden">
                                        			<div class="col-lg-12 col-md-12">
													<div id="vdivid" style="display: none;"><video id="videoID" autoplay style="width:100%;"></video></div>
														<div id="cdivid" style="display: none;"><canvas id="canvasID" style="border: 1px solid black;width: 88%;height: 112px;"></canvas></div>
													<div id="imgdivid"><img src="liveData/<s:property value="clientimg"/>" style="width:88%;"></div>
													</div>
													<br>
													<div class="col-lg-12 col-md-12">
													<a href="#" onclick="capture()" class="btn btn-primary">Capture</a>
													<a href="#" class="btn btn-primary" onclick="retake()">Update Photo</a>
													</div>
													<script type="text/javascript">

	
											var video = document.getElementById('videoID');
											var canvas = document.getElementById('canvasID');
											var context = canvas.getContext('2d');
									
											window.URL = window.URL || window.webkitURL;
											navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia	|| 
									                                 navigator.mozGetUserMedia || navigator.msGetUserMedia;
									
											navigator.getUserMedia({
												video : true
												//audio : true
											}, function(stream) {
												video.src = window.URL.createObjectURL(stream);
											}, function(e) { console.log('Something wrong has happened:', e); });
									
											
											function capture() 
											{
												context.drawImage(video, 0, 0, canvas.width, canvas.height);
												document.getElementById('cdivid').style.display = '';
												document.getElementById('vdivid').style.display = 'none';
												
												var imageData =  canvas.toDataURL();
												document.getElementById('profileimg').value = imageData;
											};
											
											function retake(){
												document.getElementById('cdivid').style.display = 'none';
												document.getElementById('vdivid').style.display = '';
												document.getElementById('imgdivid').style.display = 'none';
												
												
											}
									
											
											function send()
									        {
												var imageData =  canvas.toDataURL();
												var xmlhttp = new XMLHttpRequest();
												xmlhttp.open("POST", "/ww/receiver", true);
												xmlhttp.send(imageData);
									        };
									        
									
										</script>
										<div class="col-lg-12 col-md-12 hidden" style="margin-top: 15px;" >
											<label><%=loginfo.getPatientname_field() %> Image</label>
												<s:file name="userImage" id="userImage" cssStyle="width: 100%;"/>									
										</div>
												<div class="col-lg-12 col-md-12">
															<!--
													We will create a family tree using just CSS(3)
													The markup will be simple nested lists
													-->
													
													<div class="tree" style="margin-top: 25px;">
													<h4 class="settitle">Family Hierarchy</h4>
														<ul>
															<li>
																<a href="#">Father</a>
																<ul>
																	<li>
																		<a href="#">Daughter</a>
																		<ul>
																			<li><a href="#">Grand Child</a></li>
																			<li>
																				<a href="#">Grand Child</a>
																				<!--<ul>
																					<li><a href="#">Great Grand Child</a></li>
																				</ul>
																			--></li>
																		</ul>
																	</li>
																</ul>
															</li>
														</ul>
													</div>		
																
													</div> 
                                        		</div>
                                        		
                                        		<%-- <div class="col-lg-12 col-md-12">
                                        			<s:textfield></s:textfield><button>Search</button>
                                        		</div> --%>
                                        		
                                        		<!-- Start  NEW Registration code-->
                                        		<div class="col-lg-12 col-md-12" style="padding: 0;margin-bottom: 5px;">
                                        			<!-- First Column Start -->
                                        			<div class="col-lg-4 col-md-4">
                                        				<div class="col-lg-12 col-md-12 ajpadzero" style="background-color: powderblue;">
	                                        				<h4 >&nbsp;Personal Details</h4>
	                                        			</div>
	                                        			<div class="hidden">
															<label style="color:orange;">Aadhar Number</label>
														<s:textfield id="adhno" name="adhno" title="Enter Patient Aadhar No" theme="simple"  cssClass="form-control showToolTip"
																 data-toggle="tooltip" placeholder="Enter Patient Aadhar No" maxlength="12" style="border-color: chocolate;"/>
															<label  id = "adhnoError" class="text-danger"></label>
												
														</div>
                                        				<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero"  style="margin-top: 10px">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Salutation</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8" >
	                                        					<s:select  id="title1" name="title" list="initialList" onchange="setGender(this.value)"  theme="simple" cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip" headerValue="Select" headerKey="" cssStyle="width:100%;"/>
														 		<label  id = "titleError" class="text-danger"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>First Name</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textfield id="firstName" name="firstName"  theme="simple"  cssClass="form-control showToolTip ajsolidborder"
												  					list="pdl_firstname" onkeyup="getpatientfordatalist(this.value,'pdl_firstname','firstname')"
												  					onblur="initialFirstCap(this);" data-toggle="tooltip" placeholder="Enter First Name" cssStyle="width:100%;"/>
												  				<datalist id="pdl_firstname">
												  				</datalist> 
																<label  id = "firstNameError" class="text-danger"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Middle Name</label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textfield id="middleName" name="middleName"  theme="simple"  cssClass="form-control showToolTip ajsolidborder"
																	list="pdl_middlename" onkeyup="getpatientfordatalist(this.value,'pdl_middlename','middlename')"
																	onblur="initialFirstCap(this);" data-toggle="tooltip" placeholder="Enter Middle Name"/>
																<datalist id="pdl_middlename">
																</datalist> 
																<label  id = "middleNameError" class="text-danger"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero" id ="lastNameErrorlbl">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Last Name</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textfield id="lastName" name="lastName"   theme="simple" cssClass="form-control showToolTip ajsolidborder"
																	list="pdl_lastName" onkeyup="getpatientfordatalist(this.value,'pdl_lastName','surname')"
																	onblur="initialFirstCap(this);" data-toggle="tooltip" placeholder="Enter Last Name"/>
																<datalist id="pdl_lastName">
																</datalist> 
																<label  id = "lastNameError" class="text-danger"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Father Name</label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textfield  name="fathername"   theme="simple" cssClass="form-control showToolTip ajsolidborder"
																	list="pdl_fathername" onkeyup="getpatientfordatalist(this.value,'pdl_fathername','fathername')"
																	data-toggle="tooltip" placeholder="Enter Father Name"/>
																<datalist id="pdl_fathername">
																</datalist>
																<label  class="text-danger"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Mother Name</label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textfield  name="mothername"   theme="simple" cssClass="form-control showToolTip ajsolidborder"
																	list="pdl_mothername" onkeyup="getpatientfordatalist(this.value,'pdl_mothername','mothername')"
												 					data-toggle="tooltip" placeholder="Enter Mother Name"/>
																<datalist id="pdl_mothername">
																</datalist>
																<label  class="text-danger"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero" >
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>DOB</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textfield id="dob" name="dob"
																	cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																	placeholder="Enter DOB" readonly = "true" onchange="getAgendDays(this.value)"/>	
																<label id = "dobError" class="text-danger" ></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Age/Year</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8" >
	                                        					<div class="col-lg-4 col-md-4" style="padding: 0;">
	                                        						<input type="text" name="age" value="" id="age" class="form-control showToolTip ajsolidborder" title="" onchange="allnumeric(this.value)"
 																	data-toggle="tooltip" style="width:90%;" placeholder="Age" >
	                                        						<label class="text-danger" ></label>
	                                        					</div>
	                                        					<div class="col-lg-4 col-md-4" style="padding: 0;">
	                                        						<input type="number" placeholder="month" style="width:90%;"  id="month" class="form-control ajsolidborder"  />
	                                        						<label class="text-danger" ></label>
	                                        					</div>
	                                        					<div class="col-lg-4 col-md-4" style="padding: 0;">
	                                        						<input type="number" placeholder="days"  style="width:100%;" id="days" class="form-control ajsolidborder"  />
	                                        						<label class="text-danger" ></label>
	                                        					</div>
	                                        				</div>
	                                        				
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Gender</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:select id="gender" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip ajsolidborder"
																	data-toggle="tooltip"  headerKey="0" headerValue="Select"/>																	
																<label id = "genderError" class="text-danger"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Marital Status</label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:select id="maritalsts" name="maritalsts" list="{'Single','Married','Divorced','Separated'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip ajsolidborder"/>
	                                        				</div>
	                                        			</div>
	                                        		</div>
                                        			<!-- First Column END -->
                                        			
                                        			<!-- Second Column Start -->
                                        			<div class="col-lg-4 col-md-4">
                                        				<div class="col-lg-12 col-md-12 ajpadzero" style="background-color: powderblue;">
	                                        				<h4 >&nbsp;Contact Details</h4>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                        					<label>Contact No</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8" >
	                                        					<s:textfield id="mobNo" name="mobNo" 
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		list="pdl_mobNo" onkeyup="getpatientfordatalist(this.value,'pdl_mobNo','mobno')"
																		placeholder="Enter Mobile No."  maxlength="10"/>	
																<datalist id="pdl_mobNo">
																</datalist>
																<label id = "mobNoError" class="text-danger"></label>	
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero">
	                                        				<div class="col-lg-4 col-md-4">
	                                        					<label>Email</label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textfield id="email" name="email" 
																	cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																	placeholder="Enter Email." />
																<label id = "emailError" class="text-danger"></label>		
	                                        				</div>
	                                        			</div>
                                        				<div class="col-lg-12 col-md-12 ajpadzero">
	                                        				<div class="col-lg-4 col-md-4">
	                                        					<label>Address</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textarea id="address" name="address"  onblur="allFirstInitCap(this.id);"
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip" placeholder="Enter Address" />
																<label id = "addressError" class="text-danger"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-1 ajpadzero" style="margin-bottom: 10px;">
	                                        				<div class="col-lg-4 col-md-4">
	                                        					<label>Town/City</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8" id="citydiv">
	                                        					<s:select list="citylist"  listKey="city" listValue="city" id="town" onchange="getStateAjax(this.value)" cssClass="form-control showToolTip chosen-select" 
																headerKey="0" headerValue="Select City" name="town" />
																<label id = "townError" class="text-danger hidden"></label>
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero" style="margin-bottom: 10px;">
	                                        				<div class="col-lg-4 col-md-4">
	                                        					<label>State</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8" id="statediv">
	                                        					<s:select list="statelist" cssClass="form-control showToolTip chosen-select" onchange="getCitiesajax(this.value)" name="county" id="county"
							 										listKey="name" listValue="name" headerKey="0" headerValue="Select State"  />
							 									<!-- <label class="text-danger"></label> -->
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero">
	                                        				<div class="col-lg-4 col-md-4">
	                                        					<label>Country</label><label><span class="text-danger">*</span></label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:if test="%{#countryList != 'null'}">
																	<s:select id="country" name="country" list="countryList"
																		headerKey="0" headerValue="Select Country"
																		labelposition="left" title="Select your country from list"
																		cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" />
																</s:if>		
																
	                                        				</div>
	                                        			</div>
	                                        			<div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px">
	                                        				<div class="col-lg-4 col-md-4" style="margin-top: 8px">
	                                        					<label>Pin Code</label>
	                                        				</div>
	                                        				<div class="col-lg-8 col-md-8">
	                                        					<s:textfield id="postCode" name="postCode"
																	cssClass="form-control showToolTip ajsolidborder"
																	list="pdl_postCode" onkeyup="getpatientfordatalist(this.value,'pdl_postCode','postcode')"
																	data-toggle="tooltip" placeholder="Enter Post Code" onblur="initialCap(this);" maxlength="6"/>	
																<datalist id="pdl_postCode">
																</datalist>
																<label id = "postCodeError" class="text-danger"></label>
	                                        				</div>
	                                        			</div>
                                        			</div>
                                        			<!-- Second Column END -->
                                        			
                                        			<!-- Third Column Start -->
                                        			<div class="col-lg-4 col-md-4 hidden" style="overflow-y:scroll;height: 350px;">
						                            	<div class="col-lg-12 col-md-12 ajpadzero" style="background-color: powderblue;">
						                        			<h4 >&nbsp;Payee Details</h4>
						                        		</div>
							                       		<div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px;">
							                       			<div class="col-lg-4 col-md-4">
								                            	<label>Patient Type</label><span class="text-danger reqd-info">*</span>   					
							                   				</div>
							                   				<div class="col-lg-8 col-md-8">
							                   					<s:select id="whopay" name="whopay"
																	list="#{'Client':'Self','Third Party':'Third Party'}"
																	cssClass="form-control showToolTip chosen" data-toggle="tooltip" onchange="enabledFiled(this.value)"></s:select>
																<label  id = "whopayError" class="text-danger"></label>
							                   				</div>
							                   			</div>
							                   			<%String edittphide =""; %>
							                   			<s:if test="whopay=='Client' || typeName==54">
							                   				<%edittphide = "hidden"; %>
							                   			</s:if>
							                   			
							                   			<div class="col-lg-12 col-md-12 ajpadzero">
							                       			<div class="col-lg-4 col-md-4">
								                                 <label>Payer Type</label><span class="text-danger reqd-info">*</span>        					
							                   				</div>
							                   				<div class="col-lg-8 col-md-8" id="typepayerdiv">
							                   					<s:select id="type"  name="type"
																	list="thirdPartyTypeList" listKey="id" listValue="type" headerValue="Select Payer Type" 
																	headerKey="0"  cssClass="form-control showToolTip chosen"
																	data-toggle="tooltip" onchange="setTPName(this.value)" />
																<label  id = "tpError" class="text-danger"></label>	
							                   					<%-- <s:if test="whopay=='Client'">
							                   						<s:select id="type"  name="type"
																	list="thirdPartyTypeList" listKey="id" listValue="type" headerValue="Select TP Type" 
																	headerKey="0"  cssClass="form-control showToolTip chosen"
																	data-toggle="tooltip" onchange="setTPName(this.value)" />
																<label  id = "tpError" class="text-danger"></label>	
							                   					</s:if>
							                   					<s:else>
							                   						<s:select id="type"  name="type"
																	list="thirdPartyTypeList" listKey="id" listValue="type" headerValue="Select TP Type" 
																	headerKey="0"  cssClass="form-control showToolTip chosen"
																	data-toggle="tooltip" onchange="setTPName(this.value)" />
																	<label  id = "tpError" class="text-danger"></label>	
							                   					</s:else> --%>
							                   				</div>
							                   			</div> 
							                   			
							                   			<div class="col-lg-12 col-md-12 ajpadzero">
							                       			<div class="col-lg-4 col-md-4">
								                            	<label>Payer</label><span class="text-danger reqd-info">*</span>   		         					
							                   				</div>
							                   				<div class="col-lg-8 col-md-8" id="tpnameErrorlbl">
							                   					<%-- <s:if test="whopay=='Client'">
							                   						<select id = "typeName" name = "typeName" disabled="true" class="form-control showToolTip ppstyle chosen" data-toggle="tooltip">
																		<option value="0">Select Third Party</option>
																	</select>
																	<label  id = "tpnameError" class="text-danger"></label>	
							                   					</s:if>
							                   					<s:else>
							                   						<s:select cssClass="form-control showToolTip chosen"
																		data-toggle="tooltip" id="typeName"
																		name="typeName" list="thirdPartyTypeNameList" listKey="id"
																		listValue="thirdPartyCompanyName" headerKey="0" theme="simple"
																		headerValue="Select Third Party"/>
																	<label  id = "tpnameError" class="text-danger"></label>	
							                   					</s:else> --%>
							                   					<s:select cssClass="form-control showToolTip chosen"
																		data-toggle="tooltip" id="typeName" onchange='setRegistrationCharge(this.value)'
																		name="typeName" list="thirdPartyTypeNameList" listKey="id"
																		listValue="thirdPartyCompanyName" headerKey="0" theme="simple"
																		headerValue="Select Payer"/><br>
																		
																<label  id = "tpnameError" class="text-danger"></label>	
							                   					 <!-- <input type="text" name="campArea" id="campArea" placeholder="Enter Area" style="display: none;" /> -->
							                   					
							                   				</div>
							                   			</div>
							                   			
							                   			<div class="col-lg-12 col-md-12 ajpadzero" id="campareaId" style="display:none;">
							                   				<div class="col-lg-4 col-md-4">
								                            	<label>Camp Area</label><span class="text-danger reqd-info">*</span>   		         					
							                   				</div>
							                   				 <div class="col-lg-8 col-md-8" >
							                   				  <s:textfield name="campArea" id="campArea" placeholder="Enter Area" cssClass="form-control" cssStyle="border:1px solid;"></s:textfield>
							                   				 <label  id = "campAreaError" class="text-danger"></label>
							                   				  </div>
							                   			</div>
							                   			
							                   			<div class="<%=edittphide%>" id="hidethirdpartydetails">
							                   			<div class="col-lg-12 col-md-12 ajpadzero">
							                       			<div class="col-lg-4 col-md-4">
								                                <label style="color:orange;">Membership No</label>       					
							                   				</div>
							                   				<div class="col-lg-8 col-md-8">
							                   					<s:if test="whopay=='Client'">
							                   						<s:textfield id="tpmemb" name="tpmemb" disabled="true"  theme="simple"  cssClass="form-control showToolTip ajsolidborder"
																		data-toggle="tooltip" placeholder="Enter Membership No"/>	
							                   					</s:if>
							                   					<s:else>
							                   						<s:textfield id="tpmemb" name="tpmemb"  title="Enter Membership No" theme="simple"  cssClass="form-control showToolTip ajsolidborder"
																		data-toggle="tooltip" placeholder="Enter Membership No"/>	
							                   					</s:else>
							                   					
							                   				</div>
							                   			</div>
							                   			<div class="col-lg-12 col-md-12 ajpadzero">
							                       			<div class="col-lg-4 col-md-4">
								                                <label>Policy No.</label>        					
							                   				</div>
							                   				<div class="col-lg-8 col-md-8">
							                   					<s:textfield name="policyNo" id="policyNo" 
																	cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																	placeholder="Enter Policy No." />	
							                   				</div>
							                   			</div>
							                   			<div class="col-lg-12 col-md-12 ajpadzero">
							                       			<div class="col-lg-4 col-md-4">
								                                 <label>Policy Expiry Date</label>       					
							                   				</div>
							                   				<div class="col-lg-8 col-md-8">
							                   					 <s:textfield name="expiryDate" id="expiryDate" readonly = "true"
																	cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																	placeholder="Enter Expiry Date" />	
							                   				</div>
							                   			</div>
							                   			<div class="col-lg-12 col-md-12 ajpadzero">
							                       			<div class="col-lg-4 col-md-4">
								                                 <label>Policy Excess</label>       					
							                   				</div>
							                   				<div class="col-lg-8 col-md-8">
							                   					 <s:textfield name="policyExcess" id="policyExcess" 
																	cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																	placeholder = "Enter Value"/>	
							                   				</div>
							                   			</div>
							                   			</div>
							                   			
							                   			<div class="<%=hideenroll %>" id="addenrollcode">
							                   			<%if(loginfo.isLmh() && !loginfo.isPhysio() && !loginfo.isMatrusevasang()){ %>
							                   				<div class="col-lg-12 col-md-12 ajpadzero">
								                       			<div class="col-lg-4 col-md-4">
									                            	<label>Enrollment Code</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					<s:textfield name="enrollcode" id="enrollcode"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter Enrollment Code" required="true"/>	 
								                   				</div>
								                   			</div>
								                   			
								                   			<div class="col-lg-12 col-md-12 ajpadzero">
								                       			<div class="col-lg-4 col-md-4">
									                            	<label>Employee Name</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					<s:textfield name="clientName" id="clientName"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter Employee Name" required="true"/>	 
								                   				</div>
								                   			</div>
								                   			<%} %>
							                   			</div>
							                   			
							                   			<div class="<%=hidediv %>" id="addtpdiv">
								                   			<div class="col-lg-12 col-md-12 ajpadzero <%=hidecol %>" id="policyholderdiv">
								                       			<div class="col-lg-4 col-md-4" >
									                                <label>Policy Holder</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8">
								                   					<s:textfield name="policyholder" id="policyholder"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter colliery" required="true"/>	 
								                   				</div>
								                   			</div>
								                   			<div class="col-lg-12 col-md-12 ajpadzero" id="compnamediv">
								                       			<div class="col-lg-4 col-md-4">
									                                <label>Employee Name</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8">
								                   					 <s:textfield name="compname" id="compname"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter Employee Name" required="true"/>	
								                   				</div>
								                   			</div>
								                   			<div class="col-lg-12 col-md-12 ajpadzero" id="neisnodiv">
								                       			<div class="col-lg-4 col-md-4">
									                            	<label>NEIS/Card No</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					<s:textfield name="neisno" id="neisno"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter NEIS/Card No" required="true"/>	 
								                   				</div>
								                   			</div>
								                   			<div class="col-lg-12 col-md-12 ajpadzero <%=insurance %>" id="designationbytpdiv">
								                       			<div class="col-lg-4 col-md-4">
									                                <label>Designation</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					 <s:textfield name="designationbytp" id="designationbytp"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter Designation" required="true"/>
								                   				</div>
								                   			</div>
								                   			<div class="col-lg-12 col-md-12 ajpadzero" id="relationvbytpdiv">
								                       			<div class="col-lg-4 col-md-4">
									                                <label>Relation</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					 <s:textfield name="relationvbytpe" id="relationvbytpe"
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter Relation" required="true"/>	
								                   				</div>
								                   			</div>
								                   			<div class="col-lg-12 col-md-12 ajpadzero <%=wcl %>" id="claimbytpdiv">
								                       			<div class="col-lg-4 col-md-4">
									                                <label>Claim ID</label>				
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					 <s:textfield name="claimbytp" id="claimbytp"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter Claim ID" required="true"/>	
								                   				</div>
								                   			</div>
								                   			<div class="col-lg-12 col-md-12 ajpadzero <%=wcl %>" id="unitstationdiv">
								                       			<div class="col-lg-4 col-md-4">
									                                 <label>Unit/Station</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					 <s:textfield name="unitstation" id="unitstation"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter unit/station" required="true"/>	
								                   				</div>
								                   			</div>
								                   			<div class="col-lg-12 col-md-12 ajpadzero <%=cghs%>" id="collierydiv">
								                       			<div class="col-lg-4 col-md-4">
									                                <label>Colliery</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					 <s:textfield name="colliery" id="colliery"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter colliery" required="true"/>
								                   				</div>
								                   			</div>
								                   			<div class="col-lg-12 col-md-12 ajpadzero <%=cghs%>" id="areabytpdiv">
								                       			<div class="col-lg-4 col-md-4">
									                                <label>Area</label>					
								                   				</div>
								                   				<div class="col-lg-8 col-md-8" >
								                   					 <s:textfield name="areabytp" id="areabytp"  
																		cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
																		placeholder="Enter colliery" required="true"/>	
								                   				</div>
								                   			</div>
								                   			
							                   			</div>
							                   			
						                       		</div>
                                        			
                                        			<!-- Third Column END -->
                                        		</div>
                                        		<!-- Personal details end -->
                                        		
                       <!-- Payee Start -->
                       	<div class="col-lg-12 col-md-12" style="padding: 0;">
                       	<%if(loginfo.isLmh()){ %>
                       		<input type="hidden" id="islmh" value="1">
                       	<%} %>
                            <!-- First Column Start Payeee -->
                       <%if(!loginfo.isMatrusevasang()){ %>     
                           <div class="col-lg-4 col-md-4">         				
                   			 <div class="col-lg-12 col-md-12 ajpadzero" style="background-color: powderblue;" >
                   				<h4 >&nbsp;Relative Details</h4>
                   			</div>
                  				<div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px;">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Name</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   					<s:textfield id="emergencyContName" name="emergencyContName"
									cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
									placeholder="Enter Relative Contact Name" />		
                   				</div>
                   			</div>
                   			<div class="col-lg-12 col-md-12 ajpadzero">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Phone No</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   						<s:textfield id="emergencyContNo" name="emergencyContNo"
										cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
										placeholder="Enter Relative Contact No." />
										<label id = "emergencyContNoError" class="text-danger"></label>		
                         		</div>
                         	</div>
                         			
                         			
                         	<div class="hidden">
                         		<label> Of Birth</label>
								<span id='bpldiv'> 
								<s:select list="birthPlaceList" headerKey="" headerValue="Select birth place" listKey="name" listValue="name" name="birthplace" theme="simple" cssClass="form-control chosen-select"></s:select> 
								</span>
								<input type="button" value="add" class='btn' onclick='addbirthplacePopup()'>
                          	</div>
                          	<div class="hidden">
                          		<label>Birth Time(HH:MM)</label>
								<div class="form-inline hhmm">
									<div class="form-group">
								  		<s:select  name="hourls" id="hourls" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									</div>
								<div class="form-group hidden-xs">
								   :
								</div>
								<div class="form-group">
									<s:select  name="minutels" id="minutels" list="minuteList" cssClass="form-control mmwidthmang" headerKey="00" headerValue="00"/>								   
								</div>
								</div>
                          	</div>
                          	<div class="hidden">
                          		<label>Known As</label>
								<s:textfield id="knownAs" name="knownAs"
								title="Enter Salutation" cssClass="form-control showToolTip "
								data-toggle="tooltip" placeholder="Enter Known As" />	
                   			</div>
                   		
                  			</div>
                  	<%} %> 
                  			
                 <%if(loginfo.isMatrusevasang()){ %>	
                  	<div class="col-lg-4 col-md-4">             				
                   			<div class="col-lg-12 col-md-12 ajpadzero" style="background-color: powderblue;" >
                   				<h4 >&nbsp;Other Details</h4>
                   			</div>
                   			<div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px;" hidden="true">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Name</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   					<s:textfield id="emergencyContName" name="emergencyContName"
									cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
									placeholder="Enter Relative Contact Name" />		
                   				</div>
                   			</div>
                   			<div class="col-lg-12 col-md-12 ajpadzero" hidden="true">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Phone No</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   						<s:textfield id="emergencyContNo" name="emergencyContNo"
										cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
										placeholder="Enter Relative Contact No." />
										<label id = "emergencyContNoError" class="text-danger"></label>		
                         		</div>
                         	</div>
                         	<div class="hidden">
                          		<label>Birth Time(HH:MM)</label>
								<div class="form-inline hhmm">
									<div class="form-group">
								  		<s:select  name="hourls" id="hourls" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									</div>
								<div class="form-group hidden-xs">
								   :
								</div>
								<div class="form-group">
									<s:select  name="minutels" id="minutels" list="minuteList" cssClass="form-control mmwidthmang" headerKey="00" headerValue="00"/>								   
								</div>
								</div>
                          	</div>
                         			
                   			<div class="col-lg-12 col-md-12 ajpadzero ajlineheightzero" style="margin-top: 10px">
	                             <div class="col-lg-4 col-md-4" style="margin-top: 10px">
	                                  <label>Patient Occupation</label>
	                             </div>
	                             <div class="col-lg-8 col-md-8">
	                                <s:select id="patientoccu" name="patientoccc" list="{'salaried','business','other'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip ajsolidborder"/>
	                             </div>
	                        </div>
	                        
                   		  <div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px;">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Patient Monthly Income</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   					<s:textfield id="patientincm" name="patientincm"
									cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
									placeholder="Enter Patient Monthly Income" />		
                   				</div>
                   			</div>
                   			
                   			<div class="col-lg-12 col-md-12 ajpadzero">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Patient Husband occupation</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
	                                <s:select id="patienthus_husocc" name="patienthusocc" list="{'salaried','business','other'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip ajsolidborder"/>
	                             </div>
                         	</div>
                   			
                   			<div class="col-lg-12 col-md-12 ajpadzero">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Patient Husband Monthly Income</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   						<s:textfield id="patienthusincome" name="patienthusincome"
										cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
										placeholder="Enter Husband Monthly Income." />
										<label id = "emergencyContNoError" class="text-danger"></label>		
                         		</div>
                         	</div>
                         	
                         	<div class="col-lg-12 col-md-12 ajpadzero">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Patient Education</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   						<s:textfield id="patientEductn" name="patientEductn"
										cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
										placeholder="Enter Husband Monthly Income." />
										<label id = "emergencyContNoError" class="text-danger"></label>		
                         		</div>
                         	</div>
                         	
                         	<div class="col-lg-12 col-md-12 ajpadzero">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Patient Husband Education</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   						<s:textfield id="pathusbEductn" name="pathusbEductn"
										cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
										placeholder="Enter Husband Monthly Income." />
										<label id = "emergencyContNoError" class="text-danger"></label>		
                         		</div>
                         	</div>
                         
                           <div class="col-lg-12 col-md-12 ajpadzero">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Religion</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
	                                <s:select id="religion" name="religion" list="{'Hindu','muslim','christian','buddhist','sikh','jain','other'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip ajsolidborder"/>
	                             </div>
                         	</div>	
                         	
                         	<div class="col-lg-12 col-md-12 ajpadzero">
                   				<div class="col-lg-4 col-md-4" style="margin-top: 10px;">
                   					<label>Cast</label>
                   				</div>
                   				<div class="col-lg-8 col-md-8">
                   						<s:textfield id="cast" name="cast"
										cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip"
										placeholder="Enter Husband Monthly Income." />
										<label id = "emergencyContNoError" class="text-danger"></label>		
                         		</div>
                         	</div>
                   		</div>	
                  	<%} %>
                  			
                       		<!-- First Column End -->
                       		<!-- Second Column Start -->
                       	<%if(!loginfo.getClinicid1().equals("dental_clinic")) {%>	
                       		 <div class="col-lg-4 col-md-4 " id="departmentdiv" style="height: 175px;">
                       		 	<div class="col-lg-12 col-md-12 ajpadzero" style="background-color: powderblue;">
                        			<h4 >&nbsp;Department Details</h4>
                        		</div>
                        		
	                   			<%-- <div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px;">
	                       			<div class="col-lg-4 col-md-4">
		                                 <label>Payer Type</label><span class="text-danger reqd-info">*</span>        					
	                   				</div>
	                   				<div class="col-lg-8 col-md-8" id="typepayerdiv">
	                   					<s:select id="type"  name="type"
											list="thirdPartyTypeList" listKey="id" listValue="type" headerValue="Select Payer Type" 
											headerKey="0"  cssClass="form-control showToolTip chosen"
											data-toggle="tooltip" onchange="setTPName(this.value)" />
										<label  id = "tpError" class="text-danger"></label>	
	                   				</div>
	                   			</div>
	                   			
	                   			<div class="col-lg-12 col-md-12 ajpadzero" >
	                       			<div class="col-lg-4 col-md-4">
		                                 <label>Payer</label><label><span class="text-danger">*</span></label>					       					
	                   				</div>
	                   				<div class="col-lg-8 col-md-8">
	                   					<s:select cssClass="form-control showToolTip chosen"
												data-toggle="tooltip" id="typeName"
												name="typeName" list="thirdPartyTypeNameList" listKey="id"
												listValue="thirdPartyCompanyName" headerKey="0" theme="simple"
												headerValue="Select Payer"/>
										<label  id = "tpnameError" class="text-danger"></label>			
	                   				</div>
	                   			</div> --%>
	                   			
	                       		<%-- <div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px;">
	                       			<div class="col-lg-4 col-md-4">
		                                 <label>Category</label><label><span class="text-danger">*</span></label>					       					
	                   				</div>
	                   				<div class="col-lg-8 col-md-8">
	                   					<s:select list="#{'General':'General','Camp':'Camp','Student':'Student','Kamal Nath':'Kamal Nath','Employee':'Employee','BPL':'BPL'}" id="patientcategory" headerKey="" headerValue="Select Category" name="patientcategory" cssClass="form-control showToolTip chosen-select"></s:select>
	                   					<label></label>				
	                   				</div>
	                   			</div> --%>
	                   			
	                   			<div class="col-lg-12 col-md-12 ajpadzero" style="margin-top: 10px;">
	                       			<div class="col-lg-4 col-md-4">
		                                 <label>Department</label><label><span class="text-danger">*</span></label>					       					
	                   				</div>
	                   				<div class="col-lg-8 col-md-8">
	                   						 <s:select cssClass="form-control showToolTip chosen-select"
												id="diaryUsersss" name="diaryUser" list="departmentList" listKey="id"
												listValue="discipline" headerKey="0" theme="simple"
												headerValue="Select Department " />	 
	                   						 <%-- <s:select cssClass="form-control showToolTip chosen-select"
												id="diaryUsersss" name="diaryUser" list="userList" listKey="id"
												listValue="diaryUser" headerKey="0" theme="simple"
												headerValue="Select Department " />	  --%>
	                   				</div>
	                   			</div>
	                   			
                       		</div>
                       	<%} %>
                       		<!-- Second Column End -->
                       		<!-- Third Column Start -->
                       		 <div class="col-lg-4 col-md-4 hidden" id="paymentdiv">
                       		 	<div class="col-lg-12 col-md-12 ajpadzero" style="background-color: powderblue;">
                        			<h4 >&nbsp;Payment Details 
                        			<%if(loginfo.isPhysio()) {%>
                        			<s:if test="isfromaddpatientlmh==1">
                        			&emsp;<b>(Active Plan: Plan<s:property value="planid"/>)</b>
                        			</s:if>
                        			<%} %></h4>
                        		</div>
                        		<%if(loginfo.isPhysio()){ %>
                        		<div class="col-lg-12 col-md-12 ajpadzero">
                        		<div class="col-lg-12 col-md-12" id="chargediv" style="padding-top: 10px; padding-bottom: 6px;">
                        		    <%--  <div class="col-lg-6 col-md-6">
		                                 <label>Plan Name</label>   					
	                   				</div>
	                   				<div class="col-lg-6 col-md-6" id="plandiv">
	                                        					<s:select list="planlist"  listKey="planid" listValue="plan" id="plan" onchange="getChargeAjax(this.value)" cssClass="form-control showToolTip chosen-select" 
																headerKey="" headerValue="Select Plan" name="planid" data-toggle="tooltip" />
																<label id = "planError" class="text-danger hidden"></label>
	                                        				</div> --%>
	                                        				<s:hidden name="planid" id="planids"/>
	                                        				<s:hidden name="regularpatient" id="regularpatients"></s:hidden>
	         
	                                        			    <input type="button"  id="plan1" disabled="disabled" class="btn btn-primary active"  onclick="setRegistrationChargePhysioplan('250','1','2847')" value="Plan1">&emsp;
	                                        				<input type="button"  id="plan2" disabled="disabled" class="btn btn-primary active"  onclick="setRegistrationChargePhysioplan('150','2','2848')" value="Plan2">&emsp;
	                                        				<input type="button"  id="plan3" disabled="disabled" class="btn btn-primary active"  onclick="setRegistrationChargePhysioplan('100','3','2849')" value="Plan3">&emsp;
	                                        				<input type="button"  id="plan4" disabled="disabled" class="btn btn-primary active"  onclick="setRegistrationChargePhysioplan('400','4','2850')" value="Plan4">&emsp;
	                                        			    <input type="button"  id="plan5"  class="btn btn-primary active"  onclick="setRegistrationChargePhysioplan('100','5','2851')" value="Plan5">
	                                        	
                        		</div>
                        		</div>
                        		<% } %>
                        		<div class="col-lg-12 col-md-12 ajpadzero">
                        		     <%-- <div class="col-lg-6 col-md-6">
		                                 <label>Charges</label>   					
	                   				</div>
	                   				<div class="col-lg-6 col-md-6" id="chargediv">
	                                        					<s:select list="chargelist"  listKey="charge" listValue="charge" id="charges" onchange="setRegistrationChargePhysioplan(this.value)" cssClass="form-control showToolTip chosen-select" 
																headerKey="0" headerValue="Select Charges" name="charges" />
																<label id = "chargeError" class="text-danger hidden"></label>
	                                        				</div> --%>
                        		</div>
	                       		<div class="col-lg-12 col-md-12 ajpadzero">
	                       			<div class="col-lg-6 col-md-6">
		                                 <label>Registration Charge</label>   					
	                   				</div>
	                   				<div class="col-lg-6 col-md-6">
	                   					<s:if test="regchargeapplied==1">
	                   						<s:textfield cssClass="form-control showToolTip ajsolidborder" cssStyle="text-align: right;" readonly="true" onchange="calculatepatientafterDiscount()" name="registrationcharge" id="registrationcharge" ></s:textfield>
	                   					</s:if>
	                   					<s:else>
	                   						<s:textfield cssClass="form-control showToolTip ajsolidborder" cssStyle="text-align: right;" readonly="true" onchange="calculatepatientafterDiscount()" name="registrationcharge" id="registrationcharge" ></s:textfield>
	                   					</s:else>
	                   					
	                   				</div>
	                   			</div>
	                   			<%if(loginfo.isLmh_consultation_charge()){ %>
		                   			<div class="col-lg-12 col-md-12 ajpadzero hidden">
		                       			<div class="col-lg-6 col-md-6">
			                                  <label>Consultation Charge</label>        					
		                   				</div>
		                   				<div class="col-lg-6 col-md-6">
		                   					<s:textfield cssClass="form-control showToolTip ajsolidborder" cssStyle="text-align: right;" onchange="calculatepatientafterDiscount()" name="consultationcharge" id="consultationcharge" value="0"></s:textfield>	
		                   				</div>
		                   			</div>
	                   			<%} %>
	                   			
	                   			<%if(loginfo.isLmh_other_charge()){ %>
		                   			<div class="col-lg-12 col-md-12 ajpadzero hidden">
		                       			<div class="col-lg-6 col-md-6">
			                                 <label>Other Charge</label>               					
		                   				</div>
		                   				<div class="col-lg-6 col-md-6">
		                   					<s:textfield cssClass="form-control showToolTip ajsolidborder" cssStyle="text-align: right;" onchange="calculatepatientafterDiscount()" name="othercharge" id="othercharge" value="0"></s:textfield>	
		                   				</div>
		                   			</div>
	                   			<%} %>
	                   			<%if(loginfo.isLmh_discount()){ %>
		                   			<div class="col-lg-12 col-md-12 ajpadzero">
		                       			<div class="col-lg-6 col-md-6">
			                                 <label>Discount Type</label>               					
		                   				</div>
		                   				<div class="col-lg-6 col-md-6">
		                   					<s:select list="#{'0':'%','1':'RS'}"  onchange="changepatientdisctype()" cssClass="form-control showToolTip chosen-select ajsolidborder" name="discounttype" id="discounttype"></s:select>
		                   				</div>
		                   			</div>
		                   			
	                   		
		                   			<div class="col-lg-12 col-md-12 ajpadzero">
		                       			<div class="col-lg-6 col-md-6">
			                                 <label>Discount</label>               					
		                   				</div>
		                   				<div class="col-lg-6 col-md-6">
		                   					<s:textfield cssClass="form-control showToolTip ajsolidborder" cssStyle="text-align: right;" onchange="calculatepatientafterDiscount()" name="discountvalue" id="discountvalue" value="0"></s:textfield>	
		                   				</div>
		                   			</div>
	                   			<%} %>
	                   			<div class="col-lg-12 col-md-12 ajpadzero" style="border-top: 1px solid black !important;">
	                       			<div class="col-lg-6 col-md-6">
		                                 <label>Payment Mode</label>               					
	                   				</div>
	                   				<div class="col-lg-6 col-md-6">
	                   					<s:select onchange="changeInvoiceNote(this.value)" list="#{'Cash':'Cash','D/Card':'D/Card','Credit':'Credit','UPI':'UPI'}" cssClass="form-control showToolTip chosen-select ajsolidborder" name="paymenttype" id="paymenttype"></s:select>
	                   				</div>
	                   			</div>
	                   			<div class="col-lg-12 col-md-12 ajpadzero" >
	                       			<div class="col-lg-6 col-md-6">
		                                 <label>Net Amount</label>               					
	                   				</div>
	                   				<div class="col-lg-6 col-md-6" >
	                   					<s:textfield cssClass="form-control showToolTip ajsolidborder" cssStyle="text-align: right;" readonly="true" name="netamount" id="netamount" ></s:textfield>	
	                   				</div>
	                   			</div>
	                   			<div class="col-lg-12 col-md-12 ajpadzero" id="invNoteBox" style="display: none;">
	                       			<div class="col-lg-6 col-md-6">
		                                 <label>Note</label>               					
	                   				</div>
	                   				<div class="col-lg-6 col-md-6" >
	                   					<s:textfield cssClass="form-control showToolTip ajsolidborder" name="invoiceNote" id="invoiceNote" ></s:textfield>	
	                   				</div>
	                   			</div>
	                   			
                       		</div>
                       		<!-- Third Column END -->
                       		<%if(loginfo.isMatrusevasang()) {%>
                       		<div class="col-lg-4 col-md-4" id="oldpatient" style="height: 200px;padding-top: 3%;">
                       		 
                       		     <div class="col-lg-6 col-md-6" style="background-color: powderblue;">
		                                 <h5>Old Patient ?</h5>               					
	                   				</div>
	                       			<div class="col-lg-2 col-md-2">
		                                <input type="checkbox" id="chkbtn" name="chkbtn" >					       					
	                   				</div>
	                   				<div class="col-lg-4 col-md-4 "  id="oldpatientdiv" style="display: none; padding-top: 2px;">
			                             <s:textfield readonly="true" onchange="setRegiCharge(this.value)" name="fromdate"  id="fromdate" cssClass="form-control" 
			                             cssStyle="width:80%; background-color:powderblue;" placeholder=" Select Date" theme="simple"/>
		                         </div>
                       		</div>
                       		<%} %>
                       	</div>
                       	
                       	
						<div class="row hidden">	
							<div class="col-lg-4 col-md-4">
								<label>Referred By</label>
								<div id="reference_other" style="display: none;">
										<s:textfield id="txt_reference" name="otherRef" type="text"
											cssClass="form-control showToolTip" data-toggle="tooltip"
											placeholder="Enter New Reference" />
											<br>
								</div>
								<s:select id="reference" name="reference" list="refrenceList" listValue="reference" listKey="id"
									headerKey="0" headerValue="Select Reference"
									title="Select your Reference from list"
									cssClass="form-control showToolTip ddlAddNew" data-toggle="tooltip"/>
								<label  id = "refError" class="text-danger"></label>							
							</div>
						</div>
						<div class="row hidden">	
							<div class="col-lg-4 col-md-4">
								<label><s:checkbox name ="hospitalborn"></s:checkbox><b> Hospital Born !</b></label>
							</div>
							<div class="col-lg-4 col-md-4">
								<label>Document List</label>
							 	<s:select list="docuList" name='document_name' id='document_name' listKey="id" listValue="name" headerKey="" headerValue="Select Document" cssClass="chosen"></s:select>  
							</div>
							<div class="col-lg-4 col-md-4">
								<label>Document Data</label>
								<s:textfield cssClass="form-control showToolTip " name='documentValue' tile='Document Value' placeholder='Document Value'></s:textfield>
							</div>		
						</div>	
				</div>
           </div>
          <div role="tabpanel" class="tab-pane hidden" id="two">
          		<div class="row">
					<div class="col-lg-3 col-md-3">
						<label>Home Phone No.</label>
						<s:textfield id="homeNo" name="homeNo"
							title="Enter Home Contact No"
							cssClass="form-control showToolTip" data-toggle="tooltip"
							placeholder="Enter Home No." />		
						<label id = "homeNoError" class="text-danger"></label>
													
					</div>
					
					<div class="col-lg-3 col-md-3">
						<label>Work Phone No.</label>
						<s:textfield id="workNo" name="workNo"
							title="Enter Work Contact No"
							cssClass="form-control showToolTip" data-toggle="tooltip"
							placeholder="Enter Work Mobile." />	
						<label id = "workNoError" class="text-danger"></label>								
					</div>	
					
					
				
					
					<div class="col-lg-3 col-md-3">
						<label>Preferred Contact Mode</label>
						<s:select id="prefContactMode" name="prefContactMode"
							headerValue="Select Contact Mode"
							list="{'No Preference','Work','Home','Mobile','Email','EmailCc'}"
							title="Select Prefered Contact Mode" 
							cssClass="form-control showToolTip" data-toggle="tooltip" />
						
					</div>
					<div class="col-lg-3 col-md-3">
						
																				
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3 col-md-3">
						<label>Email CC</label>
						<s:textfield id="emailCc" name="emailCc" title="Enter EmailCc"
							cssClass="form-control showToolTip" data-toggle="tooltip"
							placeholder="Enter Email Cc." />	
						<label id = "emailCcError" class="text-danger"></label>								
					</div>
					<div class="col-lg-3 col-md-3">
					</div>	
					<div class="col-lg-3 col-md-3">
					</div>
					<div class="col-lg-3 col-md-3" ></div>
				</div>
		</div>
        <div role="tabpanel" class="tab-pane hidden" id="three">
		</div>     
        <div role="tabpanel" class="tab-pane hidden" id="four">
        	<div class="row">
				<div class="col-lg-3 col-md-3">
					<label>GP/Hospital Name</label>
					<s:select cssClass="form-control showToolTip chosen"
						data-toggle="tooltip"  id="gptypeName"
						name="gptypeName" list="surgeryList" listKey="id"
						listValue="gptypeName" headerKey="0" theme="simple"
						headerValue="Select GP/Hospital Name" onchange="setGPDataList(this.value)"/>
				</div>
				<div class="col-lg-3 col-md-3" style="margin-top: 22px;">
					<a href="#" onclick="addNewDoctorSurgery()" class="btn btn-primary" > <i class="fa fa-plus"></i> New GP/Hospital</a>
				</div>
				<div class="col-lg-3 col-md-3">
					<label>GP/Consultant Name</label>
					<select id = "gpname" name = "gpid"  class="form-control showToolTip chosen" data-toggle="tooltip">
						<option value="0">Select GP/Consultant</option>
					</select>
					<label id = "gpnameError" class="text-danger"></label>
				</div>
				<div class="col-lg-3 col-md-3" style="margin-top: 22px;">
					<a href="#" onclick="addNewGpData()" class="btn btn-primary" > <i class="fa fa-plus"></i> New GP/Consultant</a>
				</div>
			</div>
         </div>
         <div role="tabpanel" class="tab-pane" id="five">
			<div class="row">
				<div class="col-lg-3 col-md-3">
					<label>Source of Introduction</label>
					<div id="sourceOfIntro_other" style="display: none;">
						<s:textfield id="txt_sourceOfIntro" name="otherSourceOfIntro"
							 onblur="addOtherSourceOfIntro(this.value)" onchange="initialCap(this)"
							cssClass="form-control showToolTip" data-toggle="tooltip"
							placeholder="Enter New Source Of Introduction" />
						<br>	
					</div>
						<s:select id="sourceOfIntro" name="sourceOfIntro"
							list="sourceOfIntroList" headerKey="0" listValue="sourceOfIntro" listKey="id"
							headerValue="Select Source Of Introduction"
							title="Select Source Of Introduction" 
							cssClass="form-control showToolTip ddlAddNew" data-toggle="tooltip"/>								
				</div>
				<div class="col-lg-3 col-md-3">
					<label>Occupation</label>
					<div id="occupation_other" style="display: none;">
						<s:textfield id="txt_occupation" name="otherOccupation" type="text"  
							cssClass="form-control showToolTip" data-toggle="tooltip"
							placeholder="Enter New Occupation" />
						<br>	
					</div>		
					<s:select id="occupation" name="occupation" listValue="occupation" listKey="id"
						list="clientOccupationList" headerKey="0"
						headerValue="Select Occupation"
						title="Select your Occupation from list"
						cssClass="form-control showToolTip ddlAddNew" data-toggle="tooltip" />
				</div>
				<div class="col-lg-3 col-md-3">
					<label>Referred Date</label>
					<s:textfield name="referedDate" id="referedDate"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Refered Date" />								
				</div> 
				<div class="col-lg-3 col-md-3">
					<label>NHS Number</label>
					<div id="nhsdiv">
						<s:textfield id="nhsNumber" name="nhsNumber"
							cssClass="form-control showToolTip" data-toggle="tooltip"
							placeholder="Enter NHS Number" />
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 15px;">
				<div class="col-lg-3 col-md-3">
					<label>Speciality</label>
					<div id="treatmentType_other" style="display: none;">
						<s:textfield id="txt_treatmentType" name="otherCondition"
							cssClass="form-control showToolTip" data-toggle="tooltip"
							placeholder="Enter New Speciality" />
						<br>	
					</div>
					<s:select id = "treatmentType" name = "treatmentType" list="condtitionList" headerValue="Select Condition" headerKey="0" listKey="id" listValue="treatmentType" cssClass="form-control showToolTip ppstyle ddlAddNew"
						data-toggle="tooltip" theme="simple"></s:select>
					<label  id = "conError" class="text-danger"></label>
				</div>	
				<div class="col-lg-3 col-md-3">
					<label>Source Of Intro Name</label>			
					<s:textfield id="sourceOfIntroName" name="sourceOfIntroName"
						title="Enter Source Of Introduction Name"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Source Of Introduction Name" />
				</div>
				<div class="col-lg-3 col-md-3">
					<label>Employer Name</label>			
					<s:textfield id="employerName" name="employerName"
						title="Enter Employer Name"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Employer Name" />
				</div>
				<div class="col-lg-3 col-md-3">
					<label><%=loginfo.getPatientname_field() %> Type</label>
					<s:select id="patientType" name="patientType"
						list="{'Other','Private','NHS'}" title="Select Patient Type"
						headerValue="Select Patient Type"
						cssClass="form-control showToolTip chosen" data-toggle="tooltip" />
				</div>	
			</div>
		</div>
        <div role="tabpanel" class="tab-pane" id="six">
        	<div class="row">
				<div class="col-lg-4 col-md-4">
					<label><%=loginfo.getPatientname_field() %> Note</label>
					<s:textarea rows="4" cols="40" name="clientNote" id="clientNote"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Client Note" onblur="initialFirstCap(this);" />								
				</div> 
				<div class="col-lg-4 col-md-4">
					<label>Account Note</label>
					<s:textarea rows="4" cols="40" name="accountNote" id="accountNote"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Account Note" onblur="initialFirstCap(this);" />								
				</div> 
				<div class="col-lg-4 col-md-4">
					<label>Critical Information Note</label>
					<s:textarea rows="4" cols="40" name="clinicalNote" id="clinicalNote"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Clinical Information Note" onblur="initialFirstCap(this);" />								
				</div> 
			</div>
          </div>
          <div role="tabpanel" class="tab-pane" id="seven">
                 <div class="row">
					<div class="col-lg-2 col-md-2">
						<label style="color:orange;">Height cm</label>
						<input type="text" value="" class="form-control" placeholder="Enter Height">						
					</div> 
					<div class="col-lg-2 col-md-2">
						<label style="color:orange;">Weight kg's</label>
						<input type="text" value="" class="form-control"  placeholder="Enter Weight">							
					</div> 
					<div class="col-lg-2 col-md-2">
						<label style="color:orange;">BMI</label>
							<input type="text" value="" class="form-control"  placeholder="Enter BMI">							
					</div>
					<div class="col-lg-2 col-md-2">
						<label style="color:orange;">Pulse</label>
						<input type="text" value="" class="form-control"  placeholder="Enter Pulse">								
					</div>
					<div class="col-lg-2 col-md-2">
						<label style="color:orange;">Sys-BP</label>
							<input type="text" value="" class="form-control"  placeholder="Enter Sys-BP">							
					</div>
					<div class="col-lg-2 col-md-2">
						<label style="color:orange;">Dia-BP</label>
							<input type="text" value="" class="form-control" placeholder="Enter Dia-BP">							
					</div> 
				</div>
            </div>
                                        
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12" >
		
		<div class="col-lg-8 col-md-12" >
		<!-- <input type="button" value="OPD Preview" class="btn btn-info active" onclick="openPopup('getAllPrintDataNotAvailableSlot?action=dashboard')">&emsp;&emsp; -->
		 	<input type="button" value="OPD Preview" class="btn btn-info active" onclick="openPopup('lmhopdpreviewFinder')">&emsp;&emsp;
			<input type="button" value="Department OPD" class="btn btn-info active" onclick="openPopup('departmentopdFinder')">&emsp;&emsp;
			<input type="button" value="Patient List" class="btn btn-info active" onclick="openPopup('manageClient')">&emsp;&emsp;
		<s:if test="isfromaddpatientlmh!=0">
			<input type="button" value="View Account" class="btn btn-info active" onclick="openPopup('Statement?clientId=<s:property value="id"/>')">&emsp;&emsp;
			<input type="button" value="Add Charges" class="btn btn-info active" onclick="openPopup('debitAdditional?clientId=<s:property value="id"/>')">&emsp;&emsp;
			<input type="button" value="Investigation" class="btn btn-info active" onclick="openPopup('Investigation')">
		</s:if>
		</div>
		
		<div class="col-lg-4 col-md-12" style="text-align: center;">
<!-- 		<a href="addCompleteInfoRegistration"  class="btn btn-primary active" style="width: 100px">Reset</a>&emsp;&emsp;&emsp;
 -->			<input type="submit" id="savebtn" value="Save"  style="width: 100px" class="btn btn-success active">
		</div> 
			<%-- <s:submit value="Save" onclick="return validateAllDetails()" cssClass="btn btn-primary" /> --%>
			
			
		</div>
	</div>
	</div>
	</div>
			<s:token />					
 </form>
</div>
 
<%--  <s:form action="editClient" theme="simple" id="editclientfrm" > --%>
  <s:form action="editRegistration" theme="simple" id="editclientfrm" >
 	<input type="hidden" id="selectedid" name="selectedid">
 	<input type="hidden" value="1" name="isfromaddpatient">
 </s:form>
 
<div class="col-lg-1 col-md-1"></div>









 <!-- Modal All Client Search Div -->
	<div class="modal fade popoverpop" id="clientSearchDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:10005">
		<div class="modal-dialog modal-lg asd">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" style="margin-top: 0px !important">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id=""><%=loginfo.getPatientname_field() %> Search</h4>
				</div>
				<div class="modal-body">
					<s:include value="/diarymanagement/pages/allClient.jsp"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
 <s:form action="editempRegistration" theme="simple" id="edituserfrm" >
 	<input type="hidden" id="selectedid1" name="selectedid1">
 	<input type="hidden" value="1" name="isfromadduser">
 </s:form>
 
<div class="modal fade popoverpop" id="employeeSearchDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:10005">
		<div class="modal-dialog modal-lg asd">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" style="margin-top: 0px !important">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="">Employee Search</h4>
				</div>
				<div class="modal-body">
					<s:include value="/diarymanagement/pages/allUser.jsp"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>




<!-- Add GP Details Modal -->
	<%-- <div class="modal fade" id="gpDetailsPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New GP
						Details</h4>
				</div>
				<div class="modal-body">
						<%@ include file="/thirdParties/pages/addNewGp.jsp"%>	
						
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveNewGpData()">Save</button>

					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div> --%>
			
			
			
	<%-- <div class="modal fade" id="bpmodal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add Birth Place
						</h4>
				</div>
				<div class="modal-body">
						
					<input type="text" class='form-control' id='bptext'>				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveBirthplace()">Save</button>

					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>		 --%>
			
			


<!-- Add Third Party Details Modal -->
	<%-- <div class="modal fade" id="thirdPartyDetailsPopup1" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Third Party
						Details</h4>
				</div>
				<div class="modal-body">
						<%@ include file="/thirdParties/pages/addNewTp.jsp"%>	
						
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveTpDetails()">Save</button>

					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div> --%>
<!-- Add New Doctor Surgery Details Modal -->
	<%-- <div class="modal fade" id="doctorSurgeryPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Doctor Surgery</h4>
				</div>
				<div class="modal-body">
						<%@ include file="/thirdParties/pages/addNewDoctSurgery.jsp"%>	
						
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveSurgeryDetails()">Save</button>

					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>				 --%>				
<input type="hidden" id="chosenTypedText">

<%-- <s:form action="addThirdParty" id="addthirpartfrm" onsubmit="return setTypeField()" target="formtarget">
	<input type="hidden" name="type" id="hiddentype">
</s:form> --%>

<div class="overlay" id="yuviloader" style="z-index: 9999999;height: -webkit-fill-available;">
  <div class="spinner"></div>
  <div class="label">Loading</div>
</div>

<!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93)" id="newloaderPopup" aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->

  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
  
  
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
    
    
    
  </script>
  
  <script type="text/javascript">
  $(document).ready(function () {
      $('#typeName').change(function () {
    	  
          if ($('#typeName').val() == '52' || document.getElementById("isCampArea").value==1) {
              $('#campareaId').show();
          }
          else {
              $('#campareaId').hide();
          }
          /*  $("#campareaId :selected").text();  */
           
         /*  var text = campArea.options[campArea.selectedIndex].text;  */
      });
      
  });
  
  $(function () {
      $("#chkbtn").click(function () {
          if ($(this).is(":checked")) {
              $("#oldpatientdiv").show();
          } else {
              $("#oldpatientdiv").hide();
          }
      });
  });
</script>
 
  
  
