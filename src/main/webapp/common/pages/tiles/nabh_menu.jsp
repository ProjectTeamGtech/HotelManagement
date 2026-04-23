<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<%@page import="java.util.ArrayList"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
 <script type="text/javascript" src="nabh/js/newnabh.js"></script>
<script>
$(document).ready(function(){
	document.addEventListener("contextmenu", function(e){
	e.preventDefault();
	}, false);
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

  <div class="page-header navbar navbar-fixed-top">
  <s:form class="form-inline" action="Nabh" id="nabhfrm" theme="simple" >
		<s:hidden name="action" id="action"></s:hidden>
  </s:form>
            <div class="page-header-inner ">
                <!-- logo start -->
                <div class="page-logo">
                    <a href="index.jsp">
                        <img src="nabh/img/logo.png" alt="logo" class="logo-default" /> </a> 
                    <div class="menu-toggler sidebar-toggler">
                        <span></span>
                    </div>
                </div>
                <!-- logo end -->
                 <%-- <form class="search-form-opened" action="#" method="GET">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search..." name="query">
                        <span class="input-group-btn">
                          <a href="javascript:;" class="btn submit">
                             <i class="icon-magnifier"></i>
                           </a>
                        </span>
                    </div>
                </form> --%>
                    <div class="hidden-print">
                       		<ul class="breadcrumb">
                       		&nbsp;
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
					</div>
                <!-- start mobile menu -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                    <span></span>
                </a>
               <!-- end mobile menu -->
                <!-- start header menu -->
                <div class="top-menu">
                    <ul class="nav navbar-nav pull-right">
                    	 
 						<li class="dropdown dropdown-user">
                            <%-- <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <img alt="" class="img-circle " src="img/dp.svg" />
                                <span class="username username-hide-on-mobile"> Kiran </span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                                <li>
                                    <a href="user_profile.jsp">
                                        <i class="icon-user"></i> Profile </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="icon-settings"></i> Settings
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="icon-directions"></i> Help
                                    </a>
                                </li>
                                <li class="divider"> </li>
                                <li>
                                    <a href="lock_screen.jsp">
                                        <i class="icon-lock"></i> Lock
                                    </a>
                                </li>
                                <li>
                                    <a href="login.jsp">
                                        <i class="icon-logout"></i> Log Out </a>
                                </li>
                            </ul> --%>
                        </li>
                        <!-- end manage user dropdown -->
 						 
                    </ul>
                </div>
            </div>
        </div>
        
        
        
        
        
        
        	<!-- start sidebar menu -->
 			  
             <div class="sidebar-container">
 				<div class="sidemenu-container navbar-collapse collapse fixed-menu">
	                <div id="remove-scroll">
	                    <ul class="sidemenu  page-header-fixed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
	                        <li class="sidebar-toggler-wrapper hide">
	                            <div class="sidebar-toggler">
	                                <span></span>
	                            </div>
	                        </li>
	                        <li class="sidebar-user-panel">
	                             
	                        </li>
	                        <s:if test="action=='roadmapandscope'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                          
	                            <%-- <a href="index.jsp" class="nav-link "> <i class="fa fa-tachometer"></i> <span class="title">Dashboard</span>
	                            </a> --%>
	                            <a href="#" class="nav-link" onclick="donabhsubmit('roadmapandscope')"> <i class="fa fa-map"></i> <span class="title">Roadmap & Scope</span>
	                            </a>
	                        </li>
	                         
	                        
	                        
	                         <s:if test="action=='nabhdashboard'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item">
	                        </s:else>
	                            <%-- <a href="index.jsp" class="nav-link "> <i class="fa fa-tachometer"></i> <span class="title">Dashboard</span>
	                            </a> --%>
	                            <a href="#" class="nav-link" onclick="donabhsubmit('nabhdashboard')"> <i class="fa fa-tachometer"></i> <span class="title">Dashboard</span>
	                            </a>
	                        </li>
	                         
	                         
	                         
	                         
	                         
	                         
	                         
	                       <!--  <li class="nav-item  "> -->
	                        <s:if test="action=='accreditationtracker'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                            <%-- <a href="Accreditation_Tracker.jsp" class="nav-link nav-toggle"> <i class="fa fa-check-square"></i>
	                                <span class="title">Accreditation Tracker</span> 
	                            </a> --%>
	                            <a href="#" onclick="donabhsubmit('accreditationtracker')" class="nav-link nav-toggle"> <i class="fa fa-check-square"></i>
	                                <span class="title">Compliance Tracker</span> 
	                            </a>
	                        </li>
	                          
	                         
	                      <!-- <li class="nav-item  "> -->
	                       <s:if test="action=='kpinabh'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                            <%-- <a href="QI_Tracker.jsp" class="nav-link nav-toggle"> <i class="fa fa-list"></i>
	                                <span class="title">QI Tracker</span> 
	                            </a> --%>
	                            <a href="#" onclick="donabhsubmit('kpinabh')" class="nav-link nav-toggle"> <i class="fa fa-list"></i>
	                                <span class="title">Quality Indicators</span> 
	                            </a>
	                        </li>
	                        
	                        
	                          <li class="nav-item hidden"> 
	                           <%--  <a href="File_Submissions.jsp" class="nav-link nav-toggle"> <i class="fa fa-upload"></i>
	                                <span class="title">File Submission </span> 
	                            </a> --%>
	                             <a href="#" onclick="" class="nav-link nav-toggle"> <i class="fa fa-user"></i>
	                                <span class="title">Self Assessment</span> 
	                            </a>
	                        </li>   
	                           
	                   
                        <li class="nav-item hidden"> 
                               <a href="#" class="nav-link nav-toggle">
	                                <i class="fa fa-users"></i>
	                                <span class="title">Governance</span>
	                                <span class="arrow"></span>
	                            </a> 
	                            <%--  <a href="#" onclick="donabhsubmit('Committee')" class="nav-link nav-toggle">
	                                <i class="fa fa-users"></i>
	                                <span class="title">Committee</span>
	                                <span class="arrow"></span> 
	                            </a>--%>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <%-- <a href="Committee.jsp" class="nav-link ">
	                                        <span class="title">Committee</span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('Committee')" class="nav-link ">
	                                        <span class="title">Committee</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <%-- <a href="Meetings.jsp" class="nav-link ">
	                                        <span class="title">Meetings</span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('Meetings')" class="nav-link ">
	                                        <span class="title">Meetings</span>
	                                    </a> 
	                                </li>
	                                 
	                            </ul>
	                        </li>
                            
                            
                            
                            
                             <li class="nav-item hidden"> 
                                <a href="#" class="nav-link nav-toggle">
	                                <i class="fa fa-calculator"></i>
	                                <span class="title">Action Tracker</span>
	                                <span class="arrow"></span>
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <%-- <a href="Gaps_Tracker_Units.jsp" class="nav-link ">
	                                        <span class="title">Units</span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('Units')" class="nav-link ">
	                                        <span class="title">Units</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <%-- <a href="Gaps_Tracker_Task.jsp" class="nav-link ">
	                                        <span class="title">Tasks</span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('Tasks')" class="nav-link ">
	                                        <span class="title">Tasks</span>
	                                    </a>
	                                </li>
	                                 
	                            </ul>
	                        </li>
                            
                            
                            
                             <s:if test="action=='PostersandVisuals' ||  action=='resource_forms' || action=='resource_qi_tools' || action=='resource_training_material' || action=='resource_video'">
	                        				<li class="nav-item start active open">
			                        </s:if>
			                        <s:else>
			                        	<li class="nav-item ">
			                        </s:else>
                           
	                            <%-- <a href="Resource_Library.jsp" class="nav-link nav-toggle">
	                                <i class="fa fa-book"></i>
	                                <span class="title">Resource Library</span>
	                                <span class="arrow"></span>
	                                 
	                            </a> --%>
                                <a href="#" class="nav-link nav-toggle">
	                                <i class="fa fa-book"></i>
	                                <span class="title">Resource Library</span>
	                                <span class="arrow"></span>
	                                 
	                            </a>
                                
	                            <ul class="sub-menu">
                                
                                
                           
	                                <li class="nav-item  hidden">
	                                    <%-- <a href="Resource_Library.jsp" class="nav-link ">
	                                        <span class="title">All</span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('AllResourceLiberary')" class="nav-link ">
	                                        <span class="title">All</span>
	                                    </a>
	                                </li>
                                    <s:if test="action=='resource_forms'">
	                        				<li class="nav-item start active open">
			                        </s:if>
			                        <s:else>
			                        	<li class="nav-item ">
			                        </s:else>
	                                    <%-- <a href="javascript:;" class="nav-link nav-toggle">
	                                        Forms and SOPS
	                                        <span class="arrow"></span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('resource_forms')" class="nav-link">
	                                        Forms and SOPS
	                                    </a>
	                                    <ul class="sub-menu hidden">
	                                        <li class="nav-item">
	                                            <a href="javascript:;" class="nav-link nav-toggle">
	                                                <i class="fa fa-bell-o"></i> Arrow Toggle
	                                                <span class="arrow "></span>
	                                            </a>
	                                            <ul class="sub-menu">
	                                                <li class="nav-item">
	                                                    <a href="javascript:;" class="nav-link">
	                                                        <i class="fa fa-calculator"></i> Sample Link 1</a>
	                                                </li>
	                                                <li class="nav-item">
	                                                    <a href="#" class="nav-link">
	                                                        <i class="fa fa-clone"></i> Sample Link 2</a>
	                                                </li>
	                                                <li class="nav-item">
	                                                    <a href="#" class="nav-link">
	                                                        <i class="fa fa-cogs"></i> Sample Link 3</a>
	                                                </li>
	                                            </ul>
	                                        </li>
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-file-pdf-o"></i> Sample Link 1</a>
	                                        </li>
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-rss"></i> Sample Link 2</a>
	                                        </li>
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-hdd-o"></i> Sample Link 3</a>
	                                        </li>
	                                    </ul>
	                                </li>
	                                 
	                                 <s:if test="action=='PostersandVisuals'">
	                        				<li class="nav-item start active open">
			                        </s:if>
			                        <s:else>
			                        	<li class="nav-item ">
			                        </s:else>
	                                   <%--  <a href="email_view.jsp" class="nav-link ">
	                                        <span class="title">Posters and Visuals</span>
	                                    </a> --%>
	                                     <a href="#" onclick="donabhsubmit('PostersandVisuals')" class="nav-link ">
	                                        <span class="title">Posters and Visuals</span>
	                                    </a>
	                                </li>
                                    <s:if test="action=='resource_qi_tools'">
	                        				<li class="nav-item start active open">
			                        </s:if>
			                        <s:else>
			                        	<li class="nav-item ">
			                        </s:else>
	                                   <%--  <a href="email_view.jsp" class="nav-link ">
	                                        <span class="title">QI Tools</span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('resource_qi_tools')" class="nav-link ">
	                                        <span class="title">QI Tools</span>
	                                    </a>
	                                </li>
                                    <s:if test="action=='resource_training_material'">
	                        				<li class="nav-item start active open">
			                        </s:if>
			                        <s:else>
			                        	<li class="nav-item">
			                        </s:else>
	                                   <%--  <a href="email_view.jsp" class="nav-link ">
	                                        <span class="title">Training Materials</span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('resource_training_material')" class="nav-link ">
	                                        <span class="title">Training Materials</span>
	                                    </a>
	                                </li>
                                     <s:if test="action=='resource_video'">
	                        				<li class="nav-item start active open">
			                        </s:if>
			                        <s:else>
			                        	<li class="nav-item">
			                        </s:else>
	                                   <%--  <a href="email_view.jsp" class="nav-link ">
	                                        <span class="title">Videos</span>
	                                    </a> --%>
	                                    <a href="#" onclick="donabhsubmit('resource_video')" class="nav-link ">
	                                        <span class="title">Videos</span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
                       
                       
                                 
	                             
	                              
	                             
	                             
	                         <s:if test="action=='filesubmission'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                         
	                           <%--  <a href="File_Submissions.jsp" class="nav-link nav-toggle"> <i class="fa fa-upload"></i>
	                                <span class="title">File Submission </span> 
	                            </a> --%>
	                             <a href="#" onclick="donabhsubmit('filesubmission')" class="nav-link nav-toggle"> <i class="fa fa-upload"></i>
	                                <span class="title">File Submission </span> 
	                            </a>
	                        </li>   
	                        
	                         
	                        
	                        
	                        
	                        
	                        
	                    </ul>
	                </div>
                </div>
            </div>
            <!-- end sidebar menu --> 
            <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="baselayout1loaderPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
            
