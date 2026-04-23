<%@ taglib uri="/struts-tags"  prefix="s"%>
<!doctype html>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<html class="no-js" lang=""> 
<link href="_assets/newtheme/js/vendor/owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="_assets/newtheme/js/vendor/owl-carousel/owl.theme.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="common/plugin/settingplugin/css/style.css"> <!-- Resource style -->

    <link href="dashboard/upcomingfeatures/css/site.css" rel="stylesheet" type="text/css" />
    <link href="ChatJs/css/chat.css" rel="stylesheet" type="text/css" />
    <script src="ChatJs/js/pusher.min.js" type="text/javascript"></script> 
    <script src="ChatJs/js/jquery-1.8.2.min.js" type="text/javascript"></script>
    
        <link href="emr/css/dropdownuse/jquery-ui.css" rel="stylesheet" />   
        <script src="emr/css/dropdownuse/jquery-ui.js"></script>

        <!--pqSelect dependencies-->
        <link href="emr/css/dropdownuse/pqselect.dev.css" rel="stylesheet" />
        <script src="emr/css/dropdownuse/pqselect.dev.js"></script>
        <script src="emr/js/jquery.sieve.js"></script>
      <link href="emr/plugin/side-slider.css" rel="stylesheet" type="text/css" media="screen">

 <style><!--

 .user-list .user-list-item {
    background-position: 2px center;
    background-repeat: no-repeat;
    cursor: pointer;
    margin: 5px;
    overflow: hidden;
}
.profile-picture {
    display: block;
    float: left;
    height: 26px;
    width: 26px;
    margin-right:5px;
}
.profile-status.online {
    background-image: url("ChatJs/Images/chat-online.png");
    background-repeat: no-repeat;
}
.profile-status {
    display: block;
    float: right;
    height: 7px;
    margin: 8px 4px 0;
    width: 7px;
}
.content {
    margin: 0px 20px 0 30px;
    line-height: 27px;
}
   small{color:#fff ; font-size: 14px}
            .account a{ color :#333; background: #eee; border: 1px solid #ccc;padding: 5px; border-radius: 5px;display: inline-block;}
            pre{line-height: 11px;font-size: 11px;background: #fafafa;border: 1px solid #ccc; padding: 10px}
            .hide{font-size: 19px ;color:red ; font-weight: bold;display: none}
            .connexion {font-size: 19px ;color:green ; font-weight: bold;}
            body {
                background-color: #fff;
                color: #616f77;
            }
            .panel-body {
    padding: 5px;
    color: #000;
}
            ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}   
@media (min-width: 768px){
.apointfinderset{
	width: 76% !important;
}
 
} 
.colortog{
	color: #555 !important;
    font-size: 14px;
    line-height: 18px;
    margin-bottom: 0px;
    /* position: absolute; */
    text-align: center;
}
.imgseth{
	width: 65%;
    margin-left: auto;
    margin-right: auto;
}
.card-container {
    position: relative;
    min-height: 116px;
}
.paddingnil{
	padding:0px !important;
}
.tdseticon{
width: 15%;
    padding-right: 15px;
}
.owl-prev {
   display: none;
}
.owl-next {
   display: none;
}

.setimgu{
	position: absolute; 
	display: none; 
	top: -5px; 
	right: -5px; 
	padding: 2px 7px; 
	text-decoration: none; 
	color: rgb(0, 0, 0); 
	font-size: 20px; 
	font-family: Arial, sans-serif;
}
.setimgw{
	position: fixed; 
	/*right: 126px;*/ 
	bottom: 15px; 
	visibility: visible; 
	z-index: 9; 
	background: transparent; 
	border: 0px; 
	padding: 10px 10px 0px 0px; 
	float: left; 
	margin-right: 0px; 
	backface-visibility: hidden; 
	opacity: 1;
}

.cd-nav-container1 {
  position: fixed;
  z-index: 4;
  top: 0;
  right: 0;
  width: 80%;
  height: 100%;
  overflow-y: auto;
  background-color: #ffffff;
  /* Force Hardware Acceleration in WebKit */
  -webkit-transform: translateZ(0);
  -moz-transform: translateZ(0);
  -ms-transform: translateZ(0);
  -o-transform: translateZ(0);
  transform: translateZ(0);
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  -webkit-transform: translateX(100%);
  -moz-transform: translateX(100%);
  -ms-transform: translateX(100%);
  -o-transform: translateX(100%);
  transform: translateX(100%);
  -webkit-transition: -webkit-transform 0.4s 0s, box-shadow 0s 0.4s;
  -moz-transition: -moz-transform 0.4s 0s, box-shadow 0s 0.4s;
  transition: transform 0.4s 0s, box-shadow 0s 0.4s;
}
.cd-nav-container1.is-visible {
  -webkit-transform: translateX(0);
  -moz-transform: translateX(0);
  -ms-transform: translateX(0);
  -o-transform: translateX(0);
  transform: translateX(0);
  -webkit-overflow-scrolling: touch;
  box-shadow: -4px 0 30px rgba(0, 0, 0, 0.2);
  -webkit-transition: -webkit-transform 0.4s 0s, box-shadow 0s 0s;
  -moz-transition: -moz-transform 0.4s 0s, box-shadow 0s 0s;
  transition: transform 0.4s 0s, box-shadow 0s 0s;
}
.cd-nav-container1 header {
      padding: 0px 0px 0px 15px;
    height: 24px;
    position: relative;
}
.cd-nav-container1 h3 {
  	font-size: 1.6rem;
    color: #5c4b51;
    line-height: 10px;
}
@media only screen and (min-width: 700px) {
  .cd-nav-container1 {
    width: 65%;
  }
  
}

/*Close 1  */
.cd-close-nav1 {
  /* 'X' close icon */
  position: absolute;
  height: 44px;
  width: 44px;
  /* set the right position value so that it overlaps the .cd-nav-trigger*/
  right: 6.25%;
  top: 15%;
  bottom: auto;
  -webkit-transform: translateY(-50%);
  -moz-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  -o-transform: translateY(-50%);
  transform: translateY(-50%);
  /* image replacement */
  overflow: hidden;
  text-indent: 100%;
  white-space: nowrap;
  -webkit-transition: opacity 0.2s;
  -moz-transition: opacity 0.2s;
  transition: opacity 0.2s;
}
.cd-close-nav1::after, .cd-close-nav1::before {
  /* lines of 'X' icon */
  content: '';
  position: absolute;
  height: 3px;
  width: 32px;
  left: 50%;
  top: 50%;
  background-color: #5c4b51;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}
.cd-close-nav1::after {
  -webkit-transform: translateX(-50%) translateY(-50%) rotate(45deg);
  -moz-transform: translateX(-50%) translateY(-50%) rotate(45deg);
  -ms-transform: translateX(-50%) translateY(-50%) rotate(45deg);
  -o-transform: translateX(-50%) translateY(-50%) rotate(45deg);
  transform: translateX(-50%) translateY(-50%) rotate(45deg);
}
.cd-close-nav1::before {
  -webkit-transform: translateX(-50%) translateY(-50%) rotate(-45deg);
  -moz-transform: translateX(-50%) translateY(-50%) rotate(-45deg);
  -ms-transform: translateX(-50%) translateY(-50%) rotate(-45deg);
  -o-transform: translateX(-50%) translateY(-50%) rotate(-45deg);
  transform: translateX(-50%) translateY(-50%) rotate(-45deg);
}
.no-touch .cd-close-nav1:hover {
  opacity: .8;
}
@media only screen and (min-width: 700px) {
  .cd-close-nav1 {
    right: 0.14%;
  }
}
@media (min-width:1200px) {
  .cd-nav1 li {
    width: 20%;
    float: left;
    /* 116px is the navigation header height  and the menu items will be allocated in 2 rows */
   height: calc((56vh - 162px)/2);
    min-height: 115px;
  }
  .cd-nav1 li:nth-of-type(2n) {
    border-right-width: 1px;
  }
  .cd-nav1 li:nth-of-type(3n) {
    border-right-width: 1;
  }
  .cd-nav1 li:nth-of-type(5n) {
    border-right-width: 1;
  }
  .cd-nav1 p {
    font-size: 14px;
  }
  
}
.cd-nav1 {
  background-color: #f2f2f2;
  padding: 0px;
}
.cd-nav1::after {
  clear: both;
  content: "";
  display: table;
}
.cd-nav1 li {
  width: 20%;
  float: left;
  /* 68px is the navigation header height  and the menu items will be allocated in 3 rows */
  height: calc((100vh - 225px)/3);
  min-height: 120px;
  border: 1px solid #ffffff;
  border-top: none;
  border-left: none;
}
.cd-nav1 li:nth-of-type(2n) {
    border-right-width: 1px;
  }
  .cd-nav1 li:nth-of-type(3n) {
    border-right-width: 1;
  }
  .cd-nav1 li:nth-of-type(5n) {
    border-right-width: 1;
  }
.cd-nav1 p {
    width: 100%;
    left: 0;
    top: calc(50% + 15px);
    color: #5c4b51;
    -webkit-transition: color 0.2s;
    -moz-transition: color 0.2s;
    transition: color 0.2s;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
}


.cd-nav1 a {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
  text-align: center;
  -webkit-transition: background-color 0.2s;
  -moz-transition: background-color 0.2s;
  transition: background-color 0.2s;
}
.no-touch .cd-nav1 a:hover img {
  -webkit-animation: cd-shock 0.3s;
  -moz-animation: cd-shock 0.3s;
  animation: cd-shock 0.3s;
}
.no-touch .cd-nav1 li a:hover span *,
.cd-nav1 li.cd-selected1 a span * {
  /* on hover or if selected - change text and icon color*/
  stroke: #ffffff;
}


.cd-nav1 span, .cd-nav p {
  position: absolute;
}
.cd-nav1 span {
  top: calc(50% - 48px);
  left: 50%;
  -webkit-transform: translateX(-50%);
  -moz-transform: translateX(-50%);
  -ms-transform: translateX(-50%);
  -o-transform: translateX(-50%);
  transform: translateX(-50%);
  height: 48px;
  width: 48px;
}
.cd-nav1 span * {
  -webkit-transition: stroke 0.2s;
  -moz-transition: stroke 0.2s;
  transition: stroke 0.2s;
}
.cd-nav1 p {
  width: 100%;
  left: 0;
  top: calc(50% + 15px);
  color: #5c4b51;
  -webkit-transition: color 0.2s;
  -moz-transition: color 0.2s;
  transition: color 0.2s;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  position: absolute;
}

.newlokesh {
	margin:3px;
    border: 3px solid #ddd;
    border-radius: 4px;
    padding: 15px;
    width: 140px;
    background-color: #6699CC ;
}
.newlokesh:hover {
    box-shadow: 4px 4px 4px 4px green;
}
 .colortog{
 /* font-size: larger !important; */
 font-size: 14 px !important;
 font-weight: bold;}
</style>

<%--  <script>
$(document).ready(function(){
	document.addEventListener("contextmenu", function(e){
	e.preventDefault();
	}, false);
});
</script>  
 --%>
		 <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>

        <!-- ====================================================
        ================= CONTENT ===============================
        ===================================================== -->
        <section onload=display_ct();>
            <div class="page page-dashboard">
                <!-- row -->
                <div class="row">
                    <!-- col -->
                    <div class="col-md-4 col-lg-4">
                    	<!-- Hospital Logo -->
                    	<span><img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" class="img-responsive" style="margin-left: auto;margin-right: auto;margin-bottom: 10px;margin-top: 15px;"/></span>
                       
                        <!-- tile -->
                        <section class="tile bg-blacky widget-appointments" style="border-top: 1px solid rgba(221, 221, 221, 0.33);">
                             <!-- tile header -->
                            <div class="tile-header dvd dvd-btm text-center hidden-xs hidden-sm">
                                <h1 class="custom-font"><s:property value="clinicname"/></h1>
                            </div>
                            <!-- /tile header -->
                            <!-- tile body -->
                            <div class="tile-body hidden-xs hidden-sm">
                                <!-- row -->
                                <div class="row">
                                    <!-- col -->
                                    <div class="col-md-6 hidden-xs text-center">
                                        <h4 class="text-light"><s:property value="city"/></h4>
                                        <div style="width: 100%;">
                                            <canvas id="c1" class="CoolClock:minovateClock:50"></canvas>
                                          <form action="">
										    <p>
										      <input type="text" id="time" style="background-color: #757575 !important;border: none;text-align: center;"/>
										    </p>
										  </form>
                                        </div>

                                    </div>
                                    <!-- /col -->
                                    <!-- col -->
                                    <div class="col-md-6 b-l text-center">
                                        <span class="day"><s:property value="date"/></span><br />
                                        <span class="month"><s:property value="month"/>, <span id="year"></span></span>
                                    </div>
                                    <!-- /col -->
                                </div>
                                <!-- /row -->
                            </div>
                           
                            <!-- /tile body -->
                            <!-- tile footer -->
                                  <div class="tile-footer dvd dvd-top">

                                    <div class="owl-carousel" id="appointments-carousel">
                                         <s:iterator value="eventList">
                                        <div>
                                            <p class="text-center text-strong"><small class="text-thin"><i class="fa fa-calendar"></i> <s:property value="fromdate"/>, &nbsp;<i class="fa fa-clock-o"></i> <s:property value="time"/>,<br> Venue: <s:property value="place"/></small></p>
                                            <p>
                                            <h5 class="mt-10 mb-0 text-strong"><s:property value="eventname"/></h5>
                                         
                                           
                                            <small class="text-thin text-transparent-white"><s:property value="description"/></small>
                                            </p>
                                        
                                        </div>
              							</s:iterator>
              							<s:if test="bdaylist.size!=0">  
                     
                        <div class=""style="width:100%">
                            
                               <div >         <s:iterator value="bdaylist"><label> <i class="fa fa-birthday-cake" style=" color:Tomato"> </i>  &nbsp;<span class=" text-strong"><%= loginInfo.getClinicName() %> Wishes You A Very Happy BirthDay <i><s:property value="name"/></i></span> </label></s:iterator>
                                          </div>
                       
                        </div>
                        
                        </s:if>
                                    </div>
                                </div>
                            <!-- /tile footer -->
                            <!-- <div class="col-lg-12 col-md-12 col-sm-12" style="padding-right:0px;padding-left:0px;">
                             <a href="#"><img src="_assets/img/bannerapp1.jpg" class="img-responsive"/></a>
                            </div> -->
                   <div class="col-lg-12 col-md-12 col-sm-12 hidden" style="padding-left: 0px;padding-right: 0px;">
                            	<div class="panel panel-default">
					<div class="panel-heading" style="background-color: #757575;border-top: none;margin-top: -1px;margin-right: -1px;">
						<span class="glyphicon glyphicon-list-alt"></span><b style="font-weight:normal;">Upcoming Features</b>
					</div>
					
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12">
							 
								<ul class="demo1">
							         <li class="news-item">
							          <table cellpadding="4">
							           <tr>
							            <td class="tdseticon"><img src="dashboardicon/waste.png" width="60" class="img-responsive" style="margin-right: 15px;"/></td>
							            <td><p style="color:#6699CC;">House Keeping : <span style="color:#000;">Scheduling the clening of various areas of a hospital. </span></p></a></td>
							           </tr>
							          </table>
							         </li>
							         
							         <li class="news-item">
							          <table cellpadding="4">
							           <tr>
							            <td class="tdseticon"><img src="dashboardicon/ambulancevisit.png" width="60" class="img-responsive" style="margin-right: 15px;"/></td>
							            <td><p style="color:#6699CC;">Ambulance Services Management : <span style="color:#000;">Normal care/ Critical care ambulance services</span></p></a></td>
							           </tr>
							          </table>
							         </li>
							         <li class="news-item">
							          <table cellpadding="4">
							           <tr>
							            <td class="tdseticon"><img src="dashboardicon/diet.png" width="60" class="img-responsive" style="margin-right: 15px;"/></td>
							            <td><p style="color:#6699CC;">Dietary Management : <span style="color:#000;">Allow to user to create the food items groups and food items available in the hospital kichen</span></p></a></td>
							           </tr>
							          </table>
							         </li>
							         <li class="news-item">
							          <table cellpadding="4">
							           <tr>
							            <td class="tdseticon"><img src="dashboardicon/rotamanage.png" width="60" class="img-responsive" style="margin-right: 15px;"/></td>
							            <td><p style="color:#6699CC;">Rota Management : <span style="color:#000;">In Rota we manage all staff information with there scheduling structure.</span></p></a></td>
							           </tr>
							          </table>
							         </li>
							         <li class="news-item">
							          <table cellpadding="4">
							           <tr>
							            <td class="tdseticon"><img src="dashboardicon/pacs.png" width="60" class="img-responsive" style="margin-right: 15px;"/></td>
							            <td><p style="color:#6699CC;">PAC's : <span style="color:#000;">Hospital manage DICOM image with our PACS dashboard with editable format.</span></p></a></td>
							           </tr>
							          </table>
							         </li>
							        </ul>
							</div>
						</div>
					</div>
				</div>
                            </div>
                        </section>
                        <!--<img src="_assets/img/Hospital.png" class="img-responsive">-->
                        </div>
                      
                    <!-- col-->
                    <div class="col-md-8 col-lg-8">
                        <div class="col-lg-12 col-md-12 hidden" style="margin-bottom: 15px;">
                            <div class="form-inline">
							  <div class="form-group apointfinderset">
							    <input type="email" class="form-control" id="exampleInputEmail3" placeholder="Appoinment Finder" style="width:100%;">
							  </div>
							  <button type="submit" class="btn btn-default">Search!</button>
							  <button type="submit" onclick="opencPopup('widgetsDiaryMangent')" class="btn btn-default">Edit Widgets</button>
							</div>
                        </div>
                        <!-- col -->
                        <%boolean flag= false; %>
                        <%if(!loginInfo.getWarningmsg().equals("")){ %>
                        <p><font size="5" face="sans-serif"><marquee bgcolor="#d9d9ff" behavior="alternate" scrolldelay="80" scrollamount="3"><%=loginInfo.getWarningmsg()%></marquee></p>
                         <%} %>
                         <% if(loginInfo.isManageopd()){%>
                        <div class="card-container col-lg-2 col-md-3 col-sm-3 col-xs-4 hidden">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
	                        
	                        <%if(loginInfo.getJobTitle().equals("Practitioner")) {%> 
	                        	<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=doctorday&doctor=<%=loginInfo.getId()%>')"><img src="dashboardicon/stethoscope.png" class="img-responsive imgseth"></img></a>
	                        	<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=doctorday&doctor=<%=loginInfo.getId()%>')" class="colortog"> OPD</a>
	                        	<%flag=true; %>
	                        <%}else{ %>
	                        	
	                        	 <%if(loginInfo.getJobTitle().equals("Admin") || loginInfo.getJobTitle().equals("Technician")) {%> 
	                        	 	<a href="#" onclick="opencPopup('opdBookAppointmentAjax')"><img src="dashboardicon/stethoscope.png" class="img-responsive imgseth"></img></a>
	                        		<a href="#" onclick="opencPopup('opdBookAppointmentAjax')" class="colortog"> OPD</a>
	                        	  <%}else{ %>
	                        	  		<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=doctorday&doctor=1')"><img src="dashboardicon/stethoscope.png" class="img-responsive imgseth"></img></a>
	                        	<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=doctorday&doctor=1')" class="colortog">OPD</a>
	                        	  
	                        	  <%} %>
	                        	
	                        	
	                     
	                       <%} %> 
                        </div>
                        </div>
                            <%} %>
                            <% if(loginInfo.isManageopd()){%>
                             <% if(loginInfo.isDoctor_opd()){%>
                         <div class="card-container col-lg-2 col-md-3 col-sm-3 col-xs-4 hidden">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
	                        
	                        	<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=doctorday&doctor=<%=loginInfo.getId()%>')"><img src="dashboardicon/stethoscope.png" class="img-responsive imgseth"></img></a>
	                        	<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=doctorday&doctor=<%=loginInfo.getId()%>')" class="colortog">Old OPD</a>
	                        	
                        </div>
                        </div>
                        <%} %>
                         <%} %>
                        <!-- new opd -->
                        <% if(loginInfo.isManageopd()){%>
                       <div class="card-container col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
	                        
	                        	<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=newopd&doctor=<%=loginInfo.getId()%>')"><img src="dashboardicon/stethoscope.png" class="img-responsive imgseth"></img></a>
	                        	<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=newopd&doctor=<%=loginInfo.getId()%>')" class="colortog">My OPD</a>
	                        	
                        </div>
                        </div>
						<%} %>
                        
                        
                         <% if(loginInfo.isToken_display()){%>
                          <div class="card-container col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
	                        
	                        	<a href="#" onclick="opencPopup('dsplayBookAppointmentAjax')"><img src="dashboardicon/stethoscope.png" class="img-responsive imgseth"></img></a>
	                        	<a href="#" onclick="opencPopup('dsplayBookAppointmentAjax')" class="colortog">Token Display</a>
	                        	
                        </div>
                        </div>
                        <%} %>
                        <!-- /col -->
                        <%if(flag==true){ %>
                        <% if(loginInfo.isManageopd()){%>
                          	<% if(loginInfo.isDaily_opd()){%>
                        		<div class="card-container col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
	                        		<a href="#" onclick="opencPopup('opdNotAvailableSlot')"><img src="dashboardicon/stethoscope.png" class="img-responsive imgseth"></img></a>
	                        		<a href="#" onclick="opencPopup('allUserNotAvailableSlot')" class="colortog">Day Dashboard</a>
	                    		</div>
                        		</div>
                          	<%} %>
                          <%} %>
                        <%} %>
                        <% if(loginInfo.isManageipd()){%>
                          <!-- col -->
                        <%if(loginInfo.isDirect_ipd()){ %>  
                        <div class="card-container col-lg-2 col-md-3 col-sm-3 col-xs-4">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" data-toggle="modal"  data-target="#wards"><img src="dashboardicon/hospital-bed.png" class="img-responsive imgseth"></img></a>
		                        
		                     <!--    <a href="#" onclick="opencPopup('IpdDashboard?action=0')" class="colortog"> IPD</a> -->
		                        
		                          <a href="#" data-toggle="modal" class="colortog" data-target="#wards"> IPD</a>
	                        </div>
                        </div>
                        <%}else{ %>
                          <div class="card-container col-lg-2 col-md-3 col-sm-3 col-xs-4">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
	                       <!--  revert one page  -->
		                    <!--     <a href="#" onclick="openSamePopup('IpdDashboard?action=0')"><img src="dashboardicon/hospital-bed.png" class="img-responsive imgseth"></img></a>
		                        
		                        <a href="#" onclick="openSamePopup('IpdDashboard?action=0')" class="colortog"> IPD</a>  -->
		                         <a href="#" onclick="opencPopup('IpdDashboard?action=0')"><img src="dashboardicon/hospital-bed.png" class="img-responsive imgseth"></img></a>
		                        
		                        <a href="#" onclick="opencPopup('IpdDashboard?action=0')" class="colortog"> IPD</a> 
		                          
	                        </div>
                        </div>
                        
                        <%} %>
                        
                        <%} %>
                        <!-- /col -->
                        
                         <% if(loginInfo.isOt()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('otdbBookAppointmentAjax')" ><img src="dashboardicon/OT.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('otdbBookAppointmentAjax')" class="colortog">OT</a>
	                        </div>
                        </div>
                        <%} %>
                        
                        <%if(loginInfo.isCasualty()){ %>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('IpdDashboard?action=1')" ><img src="dashboardicon/casualty.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('IpdDashboard?action=1')" class="colortog">Casualty</a>
	                        </div>
                        </div>
                        <%} %>
                        
                         <% if(loginInfo.isManageipd()){%>
                          <!-- col -->
                       <!--  <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('IpdDashboard?action=0')"><img src="dashboardicon/nurse.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('IpdDashboard?action=0')" class="colortog"> Nursing</a>
	                        </div>
                        </div> -->
                        <%} %>
                        <!-- /col -->
                        
                        <!-- col -->
                         <% if(loginInfo.isManageemr()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('getPatientRecordEmr')"><img src="dashboardicon/electrocardiogram.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('getPatientRecordEmr')" class="colortog"> EMR</a>
	                        </div>
                        </div>
                        <%} %>
                        
                        <!-- /col -->
           				<%if(loginInfo.isPacks()){ %>
           				  <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Pacs?clientid=0')"><img src="dashboardicon/pacs.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Pacs?clientid=0')" class="colortog"> PAC's</a>
	                        </div>
                        </div>
                        <%} %>
                        <%if(loginInfo.isDischarge()){ %>
                         <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('InitialDischarge')"><img src="dashboardicon/discharge.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('InitialDischarge')" class="colortog"> Discharge</a>
	                        </div>
                        </div>
                        <%} %>
                         
                         <% if(loginInfo.isManageprisc()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Prescription')"><img src="dashboardicon/pills-1.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Prescription')" class="colortog"> Prescription </a>
	                        </div>
                        </div>
                        <%} %>
                        <%if(loginInfo.isPharmacy()){ %>
                        <%if(loginInfo.getClinicUserid().equals("aureus")){ %>
                        	<div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                        <a href="#" onclick="openSamePopup('salepriscPharmacy')"><img src="dashboardicon/pharmacy.png" class="img-responsive imgseth"></img></a>
			                        <a href="#" onclick="openSamePopup('salepriscPharmacy')" class="colortog">Sale Pharmacy</a>
		                        </div>
                        	</div>
                        	<div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                        <a href="#" onclick="openSamePopup('onlinerequestpharPharmacy')"><img src="dashboardicon/pharmacy.png" class="img-responsive imgseth"></img></a>
			                        <a href="#" onclick="openSamePopup('onlinerequestpharPharmacy')" class="colortog"> Pharmacy</a>
		                        </div>
                        	</div>
                        <%}else{ %>
                        	<div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                        <a href="#" onclick="openSamePopup('salepriscPharmacy')"><img src="dashboardicon/pharmacy.png" class="img-responsive imgseth"></img></a>
			                        <a href="#" onclick="openSamePopup('salepriscPharmacy')" class="colortog"> Pharmacy</a>
		                        </div>
                        	</div>
                        <%} %>
                        
                       	
                       <%} %>
                           <!-- col -->
                          <% if(loginInfo.isInventory()){%>
                       		<div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
	                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                        <a href="#" onclick="openSamePopup('listProduct?isfromcathlab=0')"><img src="dashboardicon/inventory.png" class="img-responsive imgseth"></img></a>
			                        <a href="#" onclick="openSamePopup('listProduct?isfromcathlab=0')" class="colortog"> Inventory</a>
		                        </div>
                            </div>
                        
                        <%} %>
                        <%if(loginInfo.isCathlab()){ %>
                        	<div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
	                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                        <a href="#" onclick="openSamePopup('listProduct?isfromcathlab=1')"><img src="dashboardicon/cathlab.png" class="img-responsive imgseth"></img></a>
			                        <a href="#" onclick="openSamePopup('listProduct?isfromcathlab=1')" class="colortog"> Cath Lab</a>
		                        </div>
                       	 	</div>
                        <%} %>
                        
                        <!-- /col -->
                        
                        <%if(loginInfo.isIndent()){ %>
                          <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                      <a href="#" onclick="openSamePopup('transferdashboardProduct')"><img src="dashboardicon/indent_request.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="openSamePopup('transferdashboardProduct')" class="colortog"> Indent
		                         <!--  <a href="transferdashboardProduct" ><img src="dashboardicon/indent_request.png" class="img-responsive imgseth"></img></a>
		                        <a href="transferdashboardProduct"  class="colortog"> Indent -->
		                        	<s:if test="indentstaus==1">
		                       			<img src="dashboardicon/newdiet.gif"></img>
		                       		</s:if>
		                        </a>
	                        </div>
                        </div>
                        <%} %>
                        
                          <% if(loginInfo.isBloodbak()){%>
	                       <!--  <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                        <a href="#" onclick="opencPopup('requestbloodBloodbank')"><img src="dashboardicon/blood-donation.png" class="img-responsive imgseth"></img></a>
			                        <a href="#" onclick="opencPopup('requestbloodBloodbank')" class="colortog"> Blood Bank</a>
		                        </div>
	                        </div> -->
	                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                        <a href="#" onclick="openSamePopup('requestbloodBloodbank')"><img src="dashboardicon/blood-donation.png" class="img-responsive imgseth"></img></a>
			                        <a href="#" onclick="openSamePopup('requestbloodBloodbank')" class="colortog"> Blood Bank</a>
		                        </div>
	                        </div>
                   		<%} %>
                        
                        <!-- /col -->
                        <!-- col -->
                           <% if(loginInfo.isManageinvst()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Investigation')"><img src="dashboardicon/microscope.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Investigation')" class="colortog"> Investigation</a>
	                        </div>
                        </div>
                        <%} %>
                        <% if(loginInfo.isTpa()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Tpa')"><img src="dashboardicon/tpa.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Tpa')" class="colortog"> TPA</a>
	                        </div>
                        </div>
                        <%} %>
                        <!-- /col -->
                         <% if(loginInfo.isInvestigation_chart()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                         <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
                          <a href="#" onclick="opencPopup('templateInvestigation')"><img src="dashboardicon/investgraph.png" class="img-responsive imgseth"></img></a>
                          <a href="#" onclick="opencPopup('templateInvestigation')" class="colortog"> Analytics</a>
                         </div>
                        </div>
                         <%} %>                       
                        
                  	
                        
                         <!-- col -->
                        
                         <% if(loginInfo.isFullFinance()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4 hidden">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('OutstandingReport')"><img src="dashboardicon/accounts.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('OutstandingReport')" class="colortog"> Billing</a>
	                        </div>
                        </div>
                        <%} %>
                        
                       
                        
                        <!-- /col -->
                         <!-- col -->
                          <% if(loginInfo.isPayroll()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       	 	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
                       	 	<%if(loginInfo.isPayrollaccess()) {%>
		                        <a href="#" onclick="opencPopup('PayrollEmployee?status=1')"><img src="dashboardicon/payroll.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('PayrollEmployee?status=1')" class="colortog"> Payroll</a>
		                        <%}else{ %>
		                         <a href="#" onclick="opencPopup('PayrollEmployee?status=0')"><img src="dashboardicon/payroll.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('PayrollEmployee?status=0')" class="colortog"> Payroll</a>
		                        <%} %>
								 <!-- <a href="#" onclick="opencPopup('managecompanyPayrollDashBoard')"><img src="dashboardicon/payroll.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('managecompanyPayrollDashBoard')" class="colortog"> Payroll</a> -->
		                        
	                        </div>
                        </div>
                        <%} %>
                        <!-- /col -->
                         <!-- col -->
                        
                         <% if(loginInfo.isExpences()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('ExpenceManagement?action=0')"><img src="dashboardicon/expensis.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('ExpenceManagement?action=0')" class="colortog"> Voucher</a>
	                        </div>
                        </div>
                        <%} %>
                        <!-- /col -->
                        <% if(loginInfo.isFullFinance()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                       <a href="#" onclick="opencPopup('ExpenceManagement?action=0')"><img src="dashboardicon/Financei.png" class="img-responsive imgseth"></img></a>
		                       <a href="#" onclick="opencPopup('ExpenceManagement?action=0')" class="colortog">Accounts</a>
	                        </div>
	                        
                        </div>
                         <%} %>
                        
                        <!-- col -->
                        <% if(loginInfo.isApmtfinder()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('inputFinder')"><img src="dashboardicon/shirt.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('inputFinder')" class="colortog"> Appointment Finder</a>
	                        </div>
                        </div>
                        <%} %>
                        <!-- /col -->
                      
                       
                        <!-- col -->
                        
                        <%if(loginInfo.isMisaccess()) {%>
                        <% if(loginInfo.isManagemis()){%>
                        <!-- /col --> 
                         <!-- col -->
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('MisChart')"><img src="dashboardicon/mis.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('MisChart')" class="colortog"> MIS</a>
	                        </div>
                        </div>
                        <%} %>
                       <%} %>
                        <!-- col -->
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4 hidden">
                            <div class="card">
                                <div class="front bg-grey">
                                    <!-- row -->
                                    <div class="row">
                                        <center>
                                            <i class="fa fa-users fa-4x marbot10"></i>
                                            <p class="normalmd" class="colortog">Third Parties</p>
                                        </center>
                                    </div>
                                    <!-- /row -->
                                </div>
                                <div class="back bg-grey">
                                    <!-- row -->
                                    <div class="row">
                                        <!-- col -->
                                        <div class="col-xs-12">
                                            <a href="#" onclick="opencPopup('showListThirdParty')" style="color:#555;"><i class="fa fa-eye fa-4x marbot10"></i> View Now</a>
                                        </div>
                                        <!-- /col -->
                                        <!-- /col -->
                                    </div>
                                    <!-- /row -->
                                </div>
                            </div>
                        </div>
                        <!-- /col -->
                       
                      
                          <!-- col -->
                          <% if(loginInfo.isManageclient()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('manageClient')"><img src="dashboardicon/clints.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('manageClient')" class="colortog"> <%=loginInfo.getPatientname_field() %>s</a>
	                        	
	                        </div>
                        </div>
                        <%} %>
                         <%if(loginInfo.isMrd()){ %>
                         <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Mrd')"><img src="dashboardicon/mrd.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Mrd')" class="colortog"> MRD</a>
	                        </div>
                        </div>
                        <%} %>
                        
                        <!-- /col -->
                        
                        <% if(loginInfo.isSheduler()) { %>
                        
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('staffNotAvailableSlot')" ><img src="dashboardicon/schedule.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('staffNotAvailableSlot')" class="colortog">Scheduler</a>
	                        </div>
                        </div>
                        <%} %>
                        
                        <% if(loginInfo.isHousekeeping()) { %>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('laundryHousekeepingdashboard')"><img src="dashboardicon/waste.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('laundryHousekeepingdashboard')" class="colortog"> Housekeeping</a>
	                        </div>
                        </div>
                        <%} %>
                        
                        
                        
                        <%if(loginInfo.isDietery()){ %>
                         <div class="card-container hidden col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('dietaryDietarydetails')"><img src="dashboardicon/diet.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('dietaryDietarydetails')" class="colortog">Dietary</a>
	                        </div>
                        </div>
                        <%} %>
                        <%if(loginInfo.isDietery()){ %>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('dietaryDietarydetails')"><img src="dashboardicon/diet.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('dietaryDietarydetails')" class="colortog">Dietary</a>
	                        </div>
                        </div>
                        <%} %>
                        <%-- 	<%if(loginInfo.isCafeteria()){ %>
                        	<div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Cafeteria')"><img src="dashboardicon/chef.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Cafeteria')" class="colortog">Cafeteria</a>
	                        </div>
                        	</div>
                        	<%} %> --%>
                       <%--  <%if(loginInfo.isCafeteria()){ %>
                        <div class="card-container hidden col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Cafeteria')"><img src="dashboardicon/chef.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Cafeteria')" class="colortog">Cafeteria</a>
	                        </div>
                        </div>
                        <%} %> --%>
                        
                        <%-- <%if(loginInfo.isPackages()){ %>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('packagesDiaryMangent')"><img src="dashboardicon/packages.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('packagesDiaryMangent')" class="colortog">Packages</a>
	                        </div>
                        </div>
                        <%} %> --%>
                        
                        <%-- <%if(loginInfo.isAmbulance()) { %>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#"><img src="dashboardicon/ambulancevisit.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" class="colortog"> Ambulance</a>
	                        </div>
                        </div>
                        <%} %> --%>
                        
                        <%if(loginInfo.isBank_deposite()) { %>
                        
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Bankdeposite')"><img src="dashboardicon/bank.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Bankdeposite')" class="colortog"> Bank Deposit</a>
	                        </div>
                        </div>
                        <%} %> 
                        
                        
                        
                        <%-- <%if(loginInfo.isAccount_reconcilation()){ %>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('Accountreconcilation')"><img src="dashboardicon/reconciliation.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('Accountreconcilation')" class="colortog"> Account Reconciliation</a>
	                        </div>
                        </div>
                        <%} %> --%>
                        
                        <!-- <div class="card-container hidden col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('paymentInvestigation')"><img src="dashboardicon/invetpay.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('miswidgetDiaryMangent')" class="colortog"> Investigation Payment</a>
	                        </div>
                        </div> -->
                        
                       
                         <% if(loginInfo.getSuperadminid()==1 ||loginInfo.getUserType()==2 || loginInfo.isRefund()) {%>  
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                       <a href="#" onclick="opencPopup('refundrequestdashboardAdditional')"><img src="dashboardicon/Refund.png" class="img-responsive imgseth"></img></a>
		                       <a href="#" onclick="opencPopup('refundrequestdashboardAdditional')" class="colortog">Refund
		                       		<s:if test="refundstatus==0">
		                       			<img src="dashboardicon/newdiet.gif"></img>
		                       		</s:if>
		                       </a>
	                        </div>
                        </div>
                        <%} %>
                        
                        <% if(loginInfo.getSuperadminid()==1 ||loginInfo.getUserType()==2 ||loginInfo.isDiscount()) {%>  
                         <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                       <a href="#" onclick="opencPopup('discountrequestdashboardProcessingAccount')"><img src="dashboardicon/Approval.png" class="img-responsive imgseth"></img></a>
		                       <a href="#" onclick="opencPopup('discountrequestdashboardProcessingAccount')" class="colortog">Discount
		                       		<s:if test="discountstatus==1">
		                       			<img src="dashboardicon/newdiet.gif" ></img>
		                       		</s:if>
		                       </a>
		                       
	                        </div>
                        </div>
                      <%} %> 
            
                         
                        <!--  // Adarsh For -->
                        <%if(!loginInfo.isPayrollaccess()){ %>
                         <% if(loginInfo.isMyhr()){%>
                           <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                         <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
                          
                          <a href="#" onclick="opencPopup('PayrollEmployee?status=0')"><img src="dashboardicon/ambulance.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('PayrollEmployee?status=0')" class="colortog"> My HR</a>
                         </div>
                        </div>
                        <%} }%>
                         <!-- /col -->
                       <%--  <% if(loginInfo.isNabh_quality()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('MisChart?kpiaction=kpireports')"><img src="dashboardicon/KPI.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('MisChart?kpiaction=kpireports')" class="colortog"> Quality</a>
	                        </div>
                        </div>
                        <%} %> --%>
                        <% if(loginInfo.isNabh_quality()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="openSamePopup('Nabh')"><img src="dashboardicon/KPI.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="openSamePopup('Nabh')" class="colortog"> NABH</a>
	                        </div>
                        </div>
                        <%} %>
                          <!-- col -->
                        
						<%if(loginInfo.isCommunication()){ %>
                       <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="openSamePopup('viewvisitingconsultIpdAjax')"><img src="dashboardicon/doctor.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="openSamePopup('viewvisitingconsultIpdAjax')" class="colortog"> Consultants</a>
	                        </div>
                        </div>
                        <%} %>
                         <%if(loginInfo.isMarketing()){ %>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('marketingDiaryMangent')"><img src="dashboardicon/marketing.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('marketingDiaryMangent')" class="colortog"> CRM</a>
	                        </div>
                        </div>
                        <%} %>
                        
                        <%if(loginInfo.isVoice_recording()){ %>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="openPopup('recordingDiaryMangent')"><img src="dashboardicon/voicerecoder.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="openPopup('recordingDiaryMangent')" class="colortog"> Voice Recorder</a>
	                        </div>
                        </div>
                        <%} %>
                         <% if(loginInfo.isEmergency_lbl()){%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="opencPopup('emergencylabelDiaryMangent')"><img src="dashboardicon/emergencylbl.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="opencPopup('emergencylabelDiaryMangent')" class="colortog"> Emergency Label</a>
	                        </div>
                        </div>
                        <%} %>
                        
                            <% if(loginInfo.isDaycare()){%>
                         <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" class="" onclick="opencPopup('IpdDashboard?action=2')"><img src="dashboardicon/DayCare.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" class=" colortog">Day Care</a>
	                        </div>
                        </div>
                        
                        <%} %>
                        <% if(loginInfo.getSuperadminid()==1 ||loginInfo.getUserType()==2 || loginInfo.isVaccination()) {%>  
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" class=""  onclick="opencPopup('calNotAvailableSlot?actionType=newopd&doctor=<%=loginInfo.getVacinator()%>')"><img src="dashboardicon/vaccine.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" class=" colortog"  onclick="opencPopup('calNotAvailableSlot?actionType=newopd&doctor=<%=loginInfo.getVacinator()%>')">Vaccination</a>
	                        </div>
                        </div>
                         <%} %>
                         <%if(loginInfo.getSuperadminid()==1 ||loginInfo.isVideo_training() || loginInfo.getUserType()==2){ %>
                         <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4" oncontextmenu="return false;">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" onclick="openSamePopup('hisvideotutorialNabh')"><img src="nabh/img/tutoriallogo.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" onclick="openSamePopup('hisvideotutorialNabh')" class="colortog"> Video Training</a>
	                        </div>
                        </div> 
                         <%} %>
                        <% if(loginInfo.getUserType()==2) {%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#cd-nav" class="cd-nav-trigger"><img src="dashboardicon/settings.png" class="img-responsive imgseth"></img></a>
		                        <a href="#cd-nav" class="cd-nav-trigger colortog"> Setting</a>
	                        </div>
                        </div>
                        <%} %>
                         
                        
                        
                           <% if(loginInfo.getSuperadminid()==1 ||loginInfo.isSetup_master() || loginInfo.getUserType()==2) {%>
                         
   						<div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                       <a href="#" onclick="opencPopup('bedlistmasterBed?selectedid=22')"><img src="dashboardicon/master.png" class="img-responsive imgseth"></img></a>
		                       <a href="#" onclick="opencPopup('bedlistmasterBed?selectedid=22')" class="colortog">Setup Master</a>
	                        </div>
	                        </div>
	                       
                         <%} %>
                        
                        <% if(loginInfo.isShow_hospital_admin()) {%>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                        <a href="#" class="" onclick="opencPopup('UserAdministration')"><img src="dashboardicon/administartion.png" class="img-responsive imgseth"></img></a>
		                        <a href="#" class=" colortog"> All Hospital Administartion</a>
	                        </div>
                        </div>
                        <%} %>
                     
                        <%if(loginInfo.isMedicine_barcode()){ %>
	                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
	                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                       <a href="http://localhost:8080/HISLIVE/medicinebarcodePharmacy?isfromdashbaord=1&isbalgopal=<%=loginInfo.isBalgopal()%>&country=<%=loginInfo.getCountry()%>" target="_blank"><img src="dashboardicon/barcode_icon.png" class="img-responsive imgseth"></img></a>
			                       <a href="http://localhost:8080/HISLIVE/medicinebarcodePharmacy?isfromdashbaord=1&isbalgopal=<%=loginInfo.isBalgopal()%>&country=<%=loginInfo.getCountry()%>" target="_blank" class="colortog">Product Barcode</a>
		                        </div>
		                    </div>
	                    <%} %>
	                     <%if(loginInfo.isPackages()){ %>
	                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4">
	                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
			                        <a href="#" class="" onclick="opencPopup('packagelistPackageMaster')"><img src="dashboardicon/packages.png" class="img-responsive imgseth"></img></a>
			                      <a href="#" onclick="opencPopup('bedlistmasterBed?selectedid=22')" class="colortog">Packages</a>
		                        </div>
		                    </div>
	                    <%} %>
                </div>
                		
                		<!-- <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                       		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center paddingnil">
		                       <a href="Smartaccount" target="_blank"><img src="dashboardicon/Financei.png" class="img-responsive imgseth"></img></a>
		                       <a href="Smartaccount" target="_blank" class="colortog">Smart Accounting</a>
	                        </div>
	                        
                        </div> -->
                <!-- /row -->
                 
            </div>
           
        </section>
        

        
        
        
        <!--/ CONTENT -->
<img src="_assets/img/leftcorneropa.png" class="img-responsive maindashback hidden">

<nav class="cd-nav-container" id="cd-nav">
		<header>
			<h3>Settings</h3>
			<a href="#0" class="cd-close-nav">Close</a>
		</header>

		<ul class="cd-nav">
		<li data-menu="careers">
				<a href="#" onclick="openPopup('profileClinicRegistration')">
					<span>
						<img src="dashboardicon/manageclinic.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Profile</p>
				</a>
			</li>
			
			<li data-menu="projects">
			<%if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
				<a href="#" onclick="openPopup('UserProfile')">
					<span>
						<img src="dashboardicon/manageuser.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Manage User</p>
				</a>
				<%} %>
			</li>
		
			<li class="cd-selected" data-menu="index">
			<%if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
				<a href="#" onclick="openPopup('DiaryMangent')">
					<span>
						<img src="dashboardicon/diary.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Manage Diary</p>
				</a>
				<%} %>
			</li>

			

		
			<%-- <li data-menu="about">
				<a href="#" onclick="openPopup('roleAccessSettingClinicRegistration')">
					<span>
						<img src="dashboardicon/accesspri.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Access privilege</p>
				</a>
			</li> --%>
			<%-- <li data-menu="services">
				<a href="#" onclick="openPopup('changePasswordPageResetPassword')">
					<span>
						<img src="dashboardicon/changepwd.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Change Password</p>
				</a>
			</li> --%>

			
			<%-- <li data-menu="contact">
				<a href="#" onclick="openPopup('locationClinicRegistration')">
					<span>
						<img src="dashboardicon/cliniclocation.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Location</p>
				</a>
			</li> --%>
			<%-- <li data-menu="contact">
				<a href="#" onclick="openPopup('EmailTemplate')">
					<span>
						<img src="dashboardicon/viewemailtemp.png" class="img-responsive iconimgsize"/>
					</span>
					<p>View Email Template</p>
				</a>
			</li> --%>
			<%-- <li data-menu="contact">
				<a href="#" onclick="openPopup('checkEmailSendEmailTemplate')">
					<span>
						<img src="dashboardicon/configemailtemp.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Configure Email Template</p>
				</a>
			</li> --%>
			<%-- <li data-menu="contact">
				<a href="#" onclick="openPopup('SMSTemplate')">
					<span>
						<img src="dashboardicon/smstemplet.png" class="img-responsive iconimgsize"/>
					</span>
					<p>View SMS Template</p>
				</a>
			</li> --%>
			<%-- <li data-menu="contact">
				<a href="#" onclick="opencPopup('Sendsms')">
					<span>
						<img src="dashboardicon/smst.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Send SMS</p>
				</a>
			</li> --%>
			<%if(loginInfo.getSuperadminid()==1){ %>
			<li data-menu="contact">
				<a href="#" onclick="opencPopup('getwidgetDiaryMangent')">
					<span>
						<img src="dashboardicon/widgets.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Personalized Widgets</p>
				</a>
			</li>
			 <% }%>
			<li data-menu="contact" class="hidden">
				<a href="#" onclick="opencPopup('gynicassesmentformIpd')">
					<span>
						<img src="" class="img-responsive iconimgsize"/>
					</span>
					<p>Gynic Form</p>
				</a>
			</li>
		</ul> <!-- .cd-3d-nav -->
	</nav>
	
	
	<nav class="cd-nav-container1" id="cd-nav1">
		<header>
			<h3>Blood Bank</h3>
			<a href="#0" class="cd-close-nav1">Close</a>
		</header>

		<ul class="cd-nav1">
			<li class="cd-selected1" data-menu="bloodreq">
				<a href="#" onclick="openPopup('requestbloodBloodbank')">
					<span>
						<img src="bloodbank/image/requestblood.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Request Blood</p>
				</a>
			</li>

			<li data-menu="donordet">
				<a href="#" onclick="openPopup('donorlistBloodbank')">
					<span>
						<img src="bloodbank/image/donarlist.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Donor Details</p>
				</a>
			</li>

			<li data-menu="donablood">
				<a href="#" onclick="openPopup('donatepatientBloodbank')">
					<span>
						<img src="bloodbank/image/donateblood.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Donate Blood</p>
				</a>
			</li>
			<li data-menu="bankdepo">
				<a href="#" onclick="openPopup('banklistBloodbank')">
					<span>
						<img src="bloodbank/image/bloodbank.png" class="img-responsive iconimgsize"/>
					</span>
					<p>Bank Details</p>
				</a>
			</li>
			<li data-menu="instock">
				<a href="#" onclick="openPopup('instockBloodbank')">
					<span>
						<img src="bloodbank/image/instock.png" class="img-responsive iconimgsize"/>
					</span>
					<p>In Stock</p>
				</a>
			</li>
			
		</ul> <!-- .cd-3d-nav -->
	</nav>
	
	
	<div class="cd-overlay"><!-- shadow layer visible when navigation is visible --></div>

   <!--***************************************************** chat html *******************************************************-->
       
       <% if(loginInfo.getClinicUserid().equals("sevenstar")){%>
        <div id="pusherChat" class="">
            <div id="membersContent">                
                 <span id="expand"><span class="close" style="text-shadow: none;opacity: 1;"><i class="fa fa-angle-down" style="color: #fff;" aria-hidden="true"></i></span><span class="open"><i class="fa fa-angle-up" aria-hidden="true"></i></span></span>
                <h2><span id="count"></span> online users</h2>
                <div class="scroll" style="background-color: #fff; display: none;">
                    <div id="members" style="padding: 6px;"></div>
                </div>
            </div>
            <!-- chat box template -->
            <!--<div id="templateChatBox">
                <div class="pusherChatBox">
                    <span class="state">
                        <span class="pencil">
                            <img src="assets/pencil.gif" />
                        </span>
                        <span class="quote">
                            <img src="assets/quote.gif" />
                        </span>
                    </span>
                     <span class="expand"><span class="close">&#x25BC;</span><span class="open">&#x25B2;</span></span>
                    <span class="closeBox">x</span>
                    <h2><a href="#" title="go to profile"><img src="" class="imgFriend" /></a> <span class="userName"></span></h2>
                    <div class="slider">
                        <div class="logMsg">
                            <div class="msgTxt">
                            </div>
                        </div>
                        <form method="post" name="#123">
                            <textarea  name="msg" rows="3" ></textarea>
                            <input type="hidden" name="from" class="from" />
                            <input type="hidden" name="to"  class="to"/>
                            <input type="hidden" name="typing"  class="typing" value="false"/>
                        </form>
                    </div>
                </div>
            </div>
            --><!-- chat box template end --><!--

            <div class="chatBoxWrap">
                <div class="chatBoxslide"></div>
                <span id="slideLeft"> <img src="assets/quote.gif" />&#x25C0;</span> 
                <span id="slideRight">&#x25B6; <img src="assets/quote.gif" /></span>
            </div>
        --></div>
        
        <%} %>        <!--***************************************************** end chat html *******************************************************-->
        

<div class="setimgw hidden-xs">	
<s:if test="expirytime<15">
 <marquee><h3 style="background-color: red !important;color:white;">Hi,Your Subscription is Expiring in <s:property value="expirytime"/> Days</h3></marquee>
 </s:if> </div>
 
		<div class="sideslider" id="sideslider">
									    <div class="sideslider-tab" style="top: 25px;left: -48px;font-size: 15px;font-family: unset; color: red;width: 156px;padding-left: 41px;"> <div style="  transform: rotate(-270deg) !important;margin-left: -37px"><strong>S<br>U<br>P<br>P<br>O<br>R<br>T </strong></div></div>
									        <div id="sideslider-smartbutton">
									           
											
														<a href="#" class="radio" onclick="openWin()" style="display:block">
			<img src="support/letstalk.png" style="border:0;display:block;width:60%;" alt="Support">
		</a>
									            <div class="sideclear"></div>
									        </div>
									
									    
									    <div class="sideslider-close sideslider-close_en hidden">Close&nbsp;</div>
									
									</div>

	<!-- Modal for IPD wards - lokesh -->
<div id="wards" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" style="margin-left: -350px !important;margin-right: -350px !important;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"> Select Ward For Ipd </h4>
      </div>
      <div class="modal-body">
   <%--  <div class="form-group"  style="width: 100%">
				<s:select name="wardid"  style="width: 100%" list="wardlist" listKey="id" listValue="wardname"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="" headerValue="All Wards" id="newwardid" ></s:select>
			</div> --%>
			
			<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="newlokesh   col-lg-3 col-md-3 col-sm-3" onclick="openIpd('0')" data-dismiss="modal">
			<p align="center" style="color: white">All Wards</p>
			</div>
			<s:iterator value="wardlist">
			
			<div class="newlokesh   col-lg-3 col-md-3 col-sm-3"  onclick="openIpd('<s:property value="id"/>')" data-dismiss="modal">
			<p align="center" style="color: white"><s:property value="wardname"/></p>
			</div>
			</s:iterator>
      		</div>
      <div class="modal-footer">
       <!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->
         
        
      </div>
    </div>

  </div>
</div>
</div>
    <!--/ Application Content -->
      <!-- ============================================
    ============== Vendor JavaScripts ===============
    ============================================= -->
    
    <script src="common/plugin/settingplugin/js/main.js"></script>
    <script src="_assets/newtheme/js/vendor/owl-carousel/owl.carousel.js"></script>
    <script src="_assets/newtheme/js/vendor/coolclock/coolclock.js"></script>
    <script src="_assets/newtheme/js/vendor/coolclock/excanvas.js"></script>
    
 
 <script src="ChatJs/js/jquery.pusherchat.js"></script>
    <script>
            $.fn.pusherChat({
                'profilePage':true,
                'onFriendConnect': function(member){
                    if (member.id) $('#user_'+member.id).hide();  
                    if (!$('.account a:visible').html()) $('.hide').show();
                },
                'onFriendLogOut': function(member){
                    if (member.id) $('#user_'+member.id).show();  
                    if ($('.account a:visible').html()) $('.hide').hide();
                },
                'onSubscription':function(members){
                    if ($('.account a:visible').html()) $('.hide').hide();
                    $.each(members._members_map, function(val){
                        $('#user_'+val).hide();
                    });            
                }
            });
            
            
            function showWards(){
            	$('#wards').modal( "show" );
            }
        </script>
    <script src="dashboard/upcomingfeatures/scripts/jquery.bootstrap.newsbox.min.js"></script>
    <script type="text/javascript">
    $(function () {
        $(".demo1").bootstrapNews({
            newsPerPage: 3,
            autoplay: true,
			pauseOnHover:true,
            direction: 'up',
            navigation: false,
            newsTickerInterval: 4000,
            onToDo: function () {
                //console.log(this);
            }
        });
    });
</script>
    
    <!--/ vendor javascripts -->
            <script>
            $(window).load(function(){
           
                // Initialize owl carousels
                $('#appointments-carousel').owlCarousel({
                    autoPlay: 5000,
                    stopOnHover: true,
                    slideSpeed : 300,
                    paginationSpeed : 400,
                    navigation: false,
                   
                    singleItem : true
                });
                //* Initialize owl carousels
            });
            
			
        </script>
        
        
        	 <script>
					var myWindow;
					function openWin() {
					    //myWindow = window.open("letsTalkDiaryMangent", "", "width=450, height=600");
					     myWindow = window.open("letsTalkDiaryMangent", "", "width=800, height=600, addressbar=no,");
					}
			</script>
			        
        
      <script src="diarymanagement/js/sideslide1.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $('#sideslider').sideSlider();

    });
</script>
        
        
</html>
<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
	<%@ include file="/application.jsp"%>
<div id="members"/>
</s:if>


<script type="text/javascript"> 

function digitalClock() {

  var currentTime = new Date();

  var hours = currentTime.getHours();
  var minutes = currentTime.getMinutes();
  var seconds = currentTime.getSeconds();

  if (minutes < 10) {
    minutes = "0" + minutes;
  }
  if (seconds < 10) {
    seconds = "0" + seconds;
  }

  currentTime = hours + ':' + minutes + ':' + seconds;
  document.getElementById("time").value = currentTime;

}

window.onload = function() {
  setInterval(digitalClock, 1000);
};
</script>
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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
    <!-- /* document.getElementById("istp").disabled = true; */
     -->
  




