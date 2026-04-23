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

  <div class="page-header navbar navbar-fixed-top">
  <s:form class="form-inline" action="hisvideotutorialNabh" id="hisvideotutorialfrm" theme="simple" >
			<s:hidden name="action" id="action"></s:hidden>
  </s:form>
  								
            <div class="page-header-inner ">
                <!-- logo start -->
                <div class="page-logo">
                    <!-- <a href="index.jsp"> -->
                    <a href="#">
                        <img src="nabh/img/vidtutoorial.png" alt="logo" class="logo-default" style="width: 175px;height: 53px" /> </a> 
                    <div class="menu-toggler sidebar-toggler">
                        <span></span>
                    </div>
                </div>
                <!-- logo end -->
                 <form class="search-form-opened" action="hisvideotutorialNabh" method="POST">
                    <div class="input-group">
                    	<s:hidden name="action"></s:hidden>
                    	<s:textfield cssClass="form-control" placeholder="Search..." name="querysearch"></s:textfield>
                       <!--  <input type="text" class="form-control" placeholder="Search..." name="querysearch"> -->
                        <span class="input-group-btn">
                          <a href="javascript:;" class="btn submit">
                             <i class="icon-magnifier"></i>
                           </a>
                        </span>
                    </div>
                    
                </form>
                <!-- start mobile menu -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                    <span></span>
                </a>
               <!-- end mobile menu -->
                <!-- start header menu -->
                <div class="top-menu">
                	<ul class="breadcrumb">
                       			<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
								<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
									<%if(breadcrumbs.isIscurrent()){ %>
										<li><%=breadcrumbs.getName()%></li>
									<%}else{ %>
										<%if(breadcrumbs.getOn()){ %>
											<li ><a style="color: #1a49d0;" href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
										<%}else{ %>
											<li><%=breadcrumbs.getName()%></li>
										<%} %>
									<%} %>
								<%} %>
								<s:if test="action=='OPD'">
									<li>OPD  </li>
								</s:if>
								<s:elseif test="action=='Video_Training_Dash'">
									<li>About Yuvicare  </li>
								</s:elseif>
								<s:elseif test="action=='IPD'">
									<li>IPD  </li>
								</s:elseif>
								<s:elseif test="action=='OT'">
									<li>OT  </li>
								</s:elseif>
								<s:elseif test="action=='Pharmacy'">
									<li>Pharmacy  </li>
								</s:elseif>
								<s:elseif test="action=='Inventory'">
									<li>Inventory  </li>
								</s:elseif>
								<s:elseif test="action=='Investigation'">
									<li>Investigation  </li>
								</s:elseif>
								<s:elseif test="action=='MRD'">
									<li>MRD  </li>
								</s:elseif>
								<s:elseif test="action=='Account'">
									<li>Account  </li>
								</s:elseif>
								
								<s:elseif test="action=='Casualty'">
									<li>Casualty  </li>
								</s:elseif>
								<s:elseif test="action=='PACs'">
									<li>PAC's  </li>
								</s:elseif>
								<s:elseif test="action=='Discharge'">
									<li>Discharge Dashboard  </li>
								</s:elseif>
								<s:elseif test="action=='Prescription'">
									<li>Prescription Dashboard  </li>
								</s:elseif>
								<s:elseif test="action=='Indent'">
									<li>Indent  </li>
								</s:elseif>
								<s:elseif test="action=='TPA'">
									<li>TPA  </li>
								</s:elseif>
								<s:elseif test="action=='Analytics'">
									<li>Analytics  </li>
								</s:elseif>
								<s:elseif test="action=='Payroll'">
									<li>Payroll  </li>
								</s:elseif>
								<s:elseif test="action=='Patients'">
									<li>Patients  </li>
								</s:elseif>
								<s:elseif test="action=='Dietary'">
									<li>Dietary  </li>
								</s:elseif>
								
								<s:elseif test="action=='Day_Care'">
									<li>Day Care  </li>
								</s:elseif>
								<s:elseif test="action=='Vaccination'">
									<li>Vaccination  </li>
								</s:elseif>
				
							</ul>
                    <ul class="nav navbar-nav pull-right hidden">
                    	 
 						<li class="dropdown dropdown-user">
	 						<div class="input-group">
	                        	<span>Video Tutorial</span>
	                    	</div>
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
 			  
             <div class="sidebar-container" >
 				<div class="sidemenu-container navbar-collapse collapse fixed-menu" style="background-color: black;">
	                <div id="remove-scroll">
	                     <ul class="sidemenu  page-header-fixed scrollfixeddown" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px;">
	                        <li class="sidebar-toggler-wrapper hide">
	                            <div class="sidebar-toggler">
	                                <span></span>
	                            </div>
	                        </li>
	                        <li class="sidebar-user-panel">
	                              
	                        </li>
	                       <li class="nav-item start">
	                             <a href="MainDashBoard"  class="nav-link nav-toggle"> <i class="fas fa-tachometer-alt"></i>
	                                <span class="title">Home</span> 
	                            </a>
	                        </li> 
	                        
	                        <s:if test="action=='Video_Training_Dash'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Video_Training_Dash')" class="nav-link nav-toggle"> <i class="fas fa-tachometer-alt"></i>
	                                <span class="title">About Yuvicare </span> 
	                            </a>
	                        </li> 
	                        
	                          <s:if test="action=='OPD'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('OPD')" class="nav-link nav-toggle"> <i class="fas fa-band-aid"></i>
	                                <span class="title">OPD</span> 
	                            </a>
	                        </li> 
	                         <s:if test="action=='IPD'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('IPD')" class="nav-link nav-toggle"> <i class="fas fa-procedures"></i>
	                                <span class="title">IPD</span> 
	                            </a>
	                        </li> 
	                        <s:if test="action=='OT'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('OT')" class="nav-link nav-toggle"> <i class="fas fa-diagnoses"></i>
	                                <span class="title">OT</span> 
	                            </a>
	                        </li> 
	                        
	                         <s:if test="action=='Pharmacy'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Pharmacy')" class="nav-link nav-toggle"> <i class="fa fa-medkit"></i>
	                                <span class="title">Pharmacy</span> 
	                            </a>
	                        </li> 
	                        
	                        <s:if test="action=='Inventory'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Inventory')" class="nav-link nav-toggle"> <i class="fa fa-warehouse"></i>
	                                <span class="title">Inventory</span> 
	                            </a>
	                        </li> 
	                        
	                          
	                        <s:if test="action=='Investigation'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Investigation')" class="nav-link nav-toggle"> <i class="fas fa-microscope"></i>
	                                <span class="title">Investigation</span> 
	                            </a>
	                        </li> 
	                        
	                        <s:if test="action=='MRD'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('MRD')" class="nav-link nav-toggle"> <i class="fa fa-file-text"></i>
	                                <span class="title">MRD</span> 
	                            </a>
	                        </li> 
	                        
	                         <s:if test="action=='Account'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Account')" class="nav-link nav-toggle"> <i class="fas fa-file-invoice-dollar"></i>
	                                <span class="title">Account</span> 
	                            </a>
	                        </li> 
	                        
	                        <s:if test="action=='Casualty'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Casualty')" class="nav-link nav-toggle"> <i class="fas fa-hospital"></i>
	                                <span class="title">Casualty</span> 
	                            </a>
	                        </li>
	                        
	                        <s:if test="action=='PACs'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('PACs')" class="nav-link nav-toggle"> <i class="fas fa-x-ray"></i>
	                                <span class="title">PAC's</span> 
	                            </a>
	                        </li>
	                        <s:if test="action=='Discharge'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Discharge')" class="nav-link nav-toggle"> <i class="fas fa-id-card-alt"></i></i>
	                                <span class="title">Discharge</span> 
	                            </a>
	                        </li>
	                        <s:if test="action=='Prescription'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Prescription')" class="nav-link nav-toggle"> <i class="fas fa-prescription"></i>
	                                <span class="title">Prescription</span> 
	                            </a>
	                        </li>
	                        <s:if test="action=='Indent'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Indent')" class="nav-link nav-toggle"> <i class="fa fa-cube"></i>
	                                <span class="title">Indent</span> 
	                            </a>
	                        </li>
	                        <s:if test="action=='TPA'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('TPA')" class="nav-link nav-toggle"> <i class="fas fa-id-badge"></i></i>
	                                <span class="title">TPA</span> 
	                            </a>
	                        </li>
	                        <s:if test="action=='Analytics'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Analytics')" class="nav-link nav-toggle"> <i class="fa fa-bar-chart"></i>
	                                <span class="title">Analytics</span> 
	                            </a>
	                        </li>
	                        <s:if test="action=='Payroll'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Payroll')" class="nav-link nav-toggle"> <i class="fas fa-money-check-alt"></i>
	                                <span class="title">Payroll</span> 
	                            </a>
	                        </li>
	                        <s:if test="action=='Patients'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Patients')" class="nav-link nav-toggle"> <i class="fas fa-user-injured"></i>
	                                <span class="title">Patients</span> 
	                            </a>
	                        </li>
	                        
	                         <s:if test="action=='Dietary'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Dietary')" class="nav-link nav-toggle"> <i class="fas fa-utensils"></i>
	                                <span class="title">Dietary</span> 
	                            </a>
	                        </li>
	                         <s:if test="action=='Day_Care'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Day_Care')" class="nav-link nav-toggle"> <i class="fa fa-calendar-check-o"></i>
	                                <span class="title">Day Care</span> 
	                            </a>
	                        </li>
	                        
	                         <s:if test="action=='Vaccination'">
	                        	<li class="nav-item start active open">
	                        </s:if>
	                        <s:else>
	                        	<li class="nav-item start">
	                        </s:else>
	                             <a href="#" onclick="dovideotutorialsubmit('Vaccination')" class="nav-link nav-toggle"> <i class="fas fa-syringe"></i>
	                                <span class="title">Vaccination</span> 
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
            
