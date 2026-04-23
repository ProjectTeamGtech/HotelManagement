<%@page import="com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO"%>
<%@page import="com.apm.DiaryManagement.eu.bi.ClientDAO"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="inventory/js/addvendor.js"></script>
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
<%-- <script type="text/javascript" src="diarymanagement/js/addPatient.js"></script>
 --%>
 <link href="_assets/newtheme/css/main.css" rel="stylesheet" />
 
 <%LoginInfo login = LoginHelper.getLoginInfo(request); %>
 <script>
 
 </script>
 


 <SCRIPT type="text/javascript">

     window.onload= function(){
    	var typeid=document.getElementById("dob").value;
    	getAgendDays(typeid);
    	
     }

 </script> 
    	 <style>
	.nav-tabs { border-bottom: 2px solid #DDD;background-color: #f5f5f5; }
	.nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover { border-width: 0; }
	.nav-tabs > li > a { border: none; color: #666; }
	.nav-tabs > li.active > a, .nav-tabs > li > a:hover { border: none; color: #339966 !important; background: transparent; }
	.nav-tabs > li > a::after { content: ""; background: #339966; height: 2px; position: absolute; width: 100%; left: 0px; bottom: -1px; transition: all 250ms ease 0s; transform: scale(0); }
	.nav-tabs > li.active > a::after, .nav-tabs > li:hover > a::after { transform: scale(1); }
	.tab-nav > li > a::after { background: #21527d none repeat scroll 0% 0%; color: #fff; }
	.tab-pane { padding: 15px 0; }
	.tab-content{padding: 0px 10px 0px 15px;}
	.card {background: #FFF none repeat scroll 0% 0%; box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.3); margin-bottom: 30px; }
 
 
 /*Now the CSS*/
* {margin: 0; padding: 0;}

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
 
 
 </style>
 
 <div class="col-lg-1 col-md-1"></div>
 
		<div class="col-md-10 col-lg-10 col-xs-12 col-sm-12">
			<div class="">
				<div class="row details">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<h4>Update Patient Details / <s:property value = "title" /> <s:property value="firstName"/> <s:property value="lastName"/></h4>
					</div>
				</div>
				
				<div class="">
					<div class="row">
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label>Email ID:</label>
							</div>
							<div class="col-lg-9">
							
							<input type="hidden" name="puruhid" id="puruhid" value="">

								<input type="text" id="pureemail" name="pureemail"
									class="form-control showToolTip"
									onblur=""
									title="Enter Email"
									data-toggle="tooltip " value="" />
							</div>
						</div>
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label>Salutation:</label>
							</div>
							<div class="col-lg-9">
								
								<s:select id="title123" name="title"  list="initialList" title="Select" theme="simple" cssClass="form-control" headerValue="Select" headerKey="0" cssStyle="width:100%;"/>
							</div>
						</div><br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label>First / Given Name:</label>
							</div>
							<div class="col-lg-9">
								
								<input type="text" id="purefname" name="purefname"
									class="form-control showToolTip" 
									title="First Name" data-toggle="tooltip"
									value="" />
							</div>
						</div>
						 
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label>Last / Family Name:</label>
							</div>
							<div class="col-lg-9">

								<input type="text" id="purelname" name="purelname"
									class="form-control showToolTip" 
									title="Last Name" data-toggle="tooltip"
									value="" />
							</div>
						</div><br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label>Gender</label>
							</div>
							<div class="col-lg-9">

								<s:select id="gender123" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip "
														data-toggle="tooltip" title="Select Gender"  headerKey="0" headerValue="Select"/>
							</div>
						</div>
							
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label>Mobile Number:</label>
							</div>
							<div class="col-lg-9">

								<input type="text" readonly="readonly" id="puremob" name="puremob"
									class="form-control showToolTip"
									 title="Mobile Number"
									data-toggle="tooltip" maxlength="10" value="" onchange="checkexistingmobno()"/>
							</div>
						</div>
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label>Date of Birth:</label>
							</div>
							<div class="col-lg-4">

								<input type="text" id="puredob" name="puredob"
									class="form-control showToolTip"
									 title="Date of Birth" readonly="readonly"
									data-toggle="tooltip" value=""  onchange="getAgendDays2(this.value)"/>
							</div>
							<div class="col-lg-3" id='dobe'>
							</div>
						</div>
						
						
						<div class="col-lg-12" style="padding-top: 20px;">
						
									<div class="col-lg-3">
									<h5>Relative Column:</h5> 
									</div>
									<div class="col-lg-3">
										<label>Name:</label> <input type="text" id='relativename' 
										   class='form-control' >
									</div>
									
									<div class="col-lg-3">
										<label>Contact No.:</label><input type="text" id='relativecontact' 
										  class='form-control' >
									</div>
									
									<div class="col-lg-3">
										<label>Relation:</label>
										<select id='relativerelation' class='form-control'>
										<option value="Brother">Brother</option>
										<option value="Mother">Mother</option>
										<option value="Father">Father</option>
										<option value="Uncle">Uncle</option>
										<option value="Sister">Sister</option>
										<option value="Grandson">Grandson</option>
										<option value="Grand daughter">Grand daughter</option>
										<option value="Other">Other</option>
										</select>
									</div>
									
						</div>
						
						<div class="col-lg-12" style="padding-top: 10px;">
						
							<div class="col-lg-3">
							<label>Address:</label>
								<input type="text" id='address' class='form-control'>
							</div>
							
							<div class="col-lg-3">
							<label>Pincode:</label>
								<input type="text" id='pincode' maxlength="6" class='form-control'>
							</div>
							
							<div class="col-lg-3">
							<label>City:</label>
							<span id='citydiv2'>
								<s:select list="citylist" listKey="city" listValue="city" cssClass='form-control chosen-select' id='city' onchange="getStateAjaxnew2(this.value)"></s:select>
							</span>
							</div>
							
							<input type="hidden" id="uploadhideul">
							<div class="col-lg-3">
							<label>State:</label>
							<span id='statediv2'>
								<s:select list="statelist" listKey="name" listValue="name" cssClass='form-control chosen-select' id='state' onchange="getCitiesajax2(this.value)"></s:select>
							</span>
							</div>
							
							
							
						</div>
						
						<div class="col-lg-12" style="padding-bottom: 10px;">
						<br>
						<s:form id="upload" method="post" action="upopddocEmr"
							enctype="multipart/form-data" theme="simple">
							
						<div class="col-lg-3">
						<label>Document Type:</label>
							<select class='form-control' id='docType'>
								<option value="Adhaar">Adhaar</option>
								<option value="Pan Card">Pan Card</option>
								<option value="Voting Card">Voting Card</option>
							</select>
						</div>	
						
						<div class="col-lg-3">
						<input type="hidden" id='docimg'>
						<label>Identity Document</label>
							<div id="drop" style="background-color: #efefef;">
								Document  <a>Upload</a>
								<s:file name="fileUpload" theme="simple"  accept="image/*" required="true" onchange="getf(this.value,'docimg')">
								</s:file>
							</div>

						</div>
						
						<div class="col-lg-3">
						<s:hidden name='hiddenval' id='profileimg'></s:hidden>
						<label>Upload Photo</label>
							<span id="filename" style="color: white"></span>
							<div id="drop" style="background-color: #efefef;">
								Photo <a>Upload</a>
								<s:file name="files" theme="simple" id='' accept="image/*" required="true" onchange="getf(this.value,'profileimg')">
								</s:file>
							</div>

						</div>
						
						
						<div class="col-lg-3">
							<ul class="popmodals123">
								<!-- The file uploads will be shown here -->
							</ul>
						</div>
						
						</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
								

		






			

