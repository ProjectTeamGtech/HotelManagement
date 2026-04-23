<%@ taglib uri="/struts-tags"  prefix="s"%>
<s:if test="bedlist.size>0">
<s:iterator value="bedlist">
<s:if test="status==1">

 <style>
 
 /* IPD Dashboard Bad */

 
	.tile {
    min-height: 124px;
}
.setbordericon1 {
    text-align: center;
    padding: 0px;
}
.set2div{
	padding: 0px;
    border-right: 1px solid rgba(241, 239, 239, 0.1);
    width: 9%;
	margin-top: -5px;
	text-align: center;
	min-height: 100px;
}

.setbordericon {
    border-right: 1px solid rgba(241, 239, 239, 0.1);
    padding: 0px;
    text-align: center;
    padding-top: 3px;
    padding-bottom: 3px;
    opacity: 0.6;
}
.setbordericon:hover {
    border-right: 1px solid rgba(241, 239, 239, 0.1);
    padding: 0px;
    text-align: center;
    padding-top: 3px;
    padding-bottom: 3px;
    opacity: 1;
}

.set12manage{
	padding: 2px 5px 1px 0px;
    border-bottom: 1px solid rgba(241, 239, 239, 0.1);
    line-height: 21px;
    opacity: 0.6;
}
.set12manage:hover{
	padding: 2px 5px 1px 0px;
    border-bottom: 1px solid rgba(241, 239, 239, 0.1);
    line-height: 21px;
    opacity: 1;
}
.bg-lightred1 {
    background-color: #a54d4a !important;
    color: white !important;
}
.bg-lightredtp {
    background-color: #396 !important;
    color: white !important;
}
.bg-greenseaum {
    background-color: #95a2a9 !important;
    color: white !important;
}


@media (max-width: 991px){

.tile {
    min-height: 145px;
}
}

@media only screen and (max-width: 992px) {
	.tile {
    min-height: 145px;
}
}

 




/* IPD Dashboard Bad */
 
 </style>

    <!-- col -->
    <div class="col-sm-2 col-md-2 col-lg-2 col-xs-2 commentlist portlets sortable mainbox bedsort">

        <!-- tile -->
        <s:if test="excessAmt==1">
        	<s:if test="mlccase==1">
        		<section class="tile portlet bg-lightred1">
        	</s:if>
        	<s:else>
        		<section class="tile portlet bg-darkred">
        	</s:else>
        </s:if>
        <s:else>
        	<s:if test="mlccase==1">
        		<section class="tile portlet bg-lightred1">
        	</s:if>
        	<s:else>
        	<s:if test="payby=='SELF'">
        	<section class="tile bg-lightred portlet">
        	</s:if>
        	<s:else>
        	<section class="tile bg-lightredtp portlet">
        	</s:else>
        		 
        	</s:else>
        	
        </s:else>
            <!-- tile header -->
            <div class="tile-header dvd dvd-btm" style="height: 23px;">
                <div class="row">
                    <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12">
                    	
                    		<!-- Notification dropdown start-->
			     				<div id="header_inbox_bar">
			     				<span id="m<s:property value="addmissionid"/>"  onclick="getajaxnotification(<s:property value="addmissionid"/>)" style="cursor:pointer"  href="#"  aria-haspopup="true" aria-expanded="true" data-placement="auto">
			     					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
							        	<i class="fa fa-bell"> </i>
							      	</div>
							      	</span>
			  					</div>
                    	
                    	
                    			<s:if test="initialdischargeStatus==0">
                    			<span onclick="setinitialDischargeIpd('dis_initiate_status','dis_initiate_time','<s:property value="treatmentepisodeid"/>',0)" style="color:#22beef;cursor:pointer;z-index: 999;">
                    			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
									<i class="fa fa-flag" title="Initiate Discharge Process"></i>     
			        			</div>
			        			</span>
			        			</s:if>
				       			 <s:else>
				       			 <span onclick="setinitialDischargeIpd('dis_initiate_status','dis_initiate_time','<s:property value="treatmentepisodeid"/>',1)" style="color:orange;cursor:pointer;z-index: 999;">
				       			 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
				            		 <i class="fa fa-flag" title="Discharge Initiated"></i>
				        			</div>
				        			</span>
				        		</s:else>
                    	
                    	
                    			<s:if test="autochargeraised==0">
                    			<span onclick="openStdChargePopup(<s:property value="tpid"/>,<s:property value="wardid"/>,<s:property value="clientid"/>,<s:property value="addmissionid"/>,'<s:property value="whopay"/>')" style="color:#22efd4;cursor:pointer;z-index: 999;">
			                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
									<i class="fa fa-clock-o" title="Auto Charge"></i>			                       	
			                     </div>
			                     </span>
			                    </s:if>
			                    <s:else>
			                    <span onclick="openStdChargePopup(<s:property value="tpid"/>,<s:property value="wardid"/>,<s:property value="clientid"/>,<s:property value="addmissionid"/>,'<s:property value="whopay"/>')" style="color:#ecf04e;cursor:pointer;z-index: 999;">
			                       <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">    
									<i class="fa fa-clock-o" title="Auto Charge"></i>			                           
			                      </div>
			                     </span>
			                      </s:else>
                    	<!-- openVitalClient -->
                    			<a href="#" data-toggle="modal" onclick="openvitalbed(<s:property value="clientid"/>,<s:property value="addmissionid"/>)" title="Vitals">
			                    	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
			                    		 <i class="fa fa-heartbeat"  aria-hidden="true" style="color: #ff909f;"></i>
			                    	</div>
                    			</a> 
                    	
                    		<s:if test="otstatus==0">
                    		<span style="cursor:pointer;" title="Book OT" onclick="openPopup('otdbNotAvailableSlot')">
                    			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
                    				 <i class="fa fa-circle-o" aria-hidden="true" style="color: white;"></i>
                    			</div>
                    			</span>
                    		</s:if>
                    		<s:elseif test="otstatus==1">
                    			<span style="cursor:pointer;" title="OT List" onclick="openPopup('listotEmr?apmtid=<s:property value="appointmentid"/>')"> 
                    			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
  									<i class="fa fa-circle-o" aria-hidden="true" style="color: #bff04e;"></i>
  									</div> 
  									</span>                 		
                    		</s:elseif>
                    		<s:elseif test="otstatus==2">
                    			<span style="cursor:pointer;" title="OT List" onclick="openPopup('listotEmr?apmtid=<s:property value="appointmentid"/>')">
                    			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
  									 <i class="fa fa-circle-o" aria-hidden="true" style="color: red;"></i>
  								</div>   
  								</span>               		
                    		</s:elseif>
                    		<s:elseif test="otstatus==3">
                    			<span style="cursor:pointer;" title="OT List" onclick="openPopup('listotEmr?apmtid=<s:property value="appointmentid"/>')">
                    			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
  									 <i class="fa fa-circle-o" aria-hidden="true" style="color: orange;"></i>   
  									</div> 
  									</span>              		
                    		</s:elseif>
                    		
                    		
                    		
                    	
                    	<s:if test="invsid>0">
                    		<span onclick="openGraph(<s:property value="clientid"/>)" style="cursor:pointer;" title="Investigation Chart">
		                    	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
		                    		 <i class="fa fa-line-chart" aria-hidden="true" style="color: #bff04e;"></i>
		                    	</div>
                    		</span> 
                    	</s:if>
                    	<span onclick="openPopup('detailStatement?clientId=<s:property value="clientid"/>')" style="cursor:pointer;" title="Charges Details">
		                    	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
		                    		 <i class="fa fa-book" aria-hidden="true" style="color: #bff04e;"></i>
		                    	</div>
                    		</span> 
                    	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
                    	   
                    	   
                    	</div>
                    	
                    	 <%-- <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 setbordericon">
                    	 	<s:if test="invseenstatus==1">
                    			<a href="#" title="Investigation" onclick="openAprovedInvestigation(<s:property value="addmissionid"/>)"><i class="fa fa-info-circle blink_me" aria-hidden="true"></i></a>
                    		</s:if>
                    	   	<s:else>
                    	   		<a href="#" title="Investigation" onclick="openAprovedInvestigation(<s:property value="addmissionid"/>)"><i class="fa fa-info-circle" aria-hidden="true"></i></a>
                    	   	</s:else>
                    	</div>  --%>
                    	
                    </div>
                    
                    <!--<div class="col-sm-1 col-md-1 col-lg-1 martop4" style="padding-right:0px;padding-left:0px;">
                    <span onclick="getvisitingconsult(<s:property value="addmissionid"/>,<s:property value="clientid"/>)">
                         <i class="fa fa-plus" title="Visiting Consult"></i>
                    </span>
                    </div>
                --></div>

            </div>
            <!-- /tile header -->
                     
                     <!-- tile body -->
            <div class="tile-body" style="border-top: 1px solid rgba(241, 239, 239, 0.1);">
            	<div class="col-sm-2 col-md-2 col-lg-2 col-xs-2 set2div">
            		<!-- <a href="#" title="EMR" onclick="openWin()" class="setbordericon1" title="EMR"  >
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12 set12manage">
            			<i class="fa fa-medkit" aria-hidden="true" style="color: #54d4f3;"></i>
            		</div>
            		</a> -->
            		<!-- <a href="#" title="Nursing Notes" data-toggle="modal" data-target="#nursingnotes" title="Nursing Note" > -->
            		<a href="#">
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12 set12manage">
            			<i class="fa fa-envelope" aria-hidden="true"></i>
            		</div>
            		</a>
            		<a href="#" class="setbordericon1">
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12 set12manage">
            			<i class="fa fa-inr"></i>
            		</div>
            		</a>
            		<a href="#" onclick="openprisciprionfromdashboard('<s:property value="id"/>','<s:property value="clientid"/>',
						'<s:property value="practitionerid"/>','<s:property value="conditionid"/>','<s:property value="clientname"/>',
						'<s:property value="practitionername"/>','<s:property value="dob"/>','<s:property value="town"/>',
						'<s:property value="addmissionid"/>','<s:property value="age"/>',
						'<s:property value="whopay"/>','<s:property value="tpid"/>',
						'<s:property value="tpname"/>','<s:property value="practitionerMob"/>',
						'<s:property value="bedname"/>','<s:property value="wardname"/>',
						'<s:property value="balance"/>','<s:property value="treatmentepisodeid"/>',
						'<s:property value="dis_initiate_time"/>','<s:property value="dis_initiate_status"/>',
						'<s:property value="dis_form_time"/>','<s:property value="dis_form_status"/>',
						'<s:property value="dis_mdicine_time"/>','<s:property value="dis_mdicine_status"/>',
						'<s:property value="dis_bill_time"/>','<s:property value="dis_bill_status"/>',
						'<s:property value="dis_nursing_time"/>','<s:property value="dis_nursing_status"/>',
						'<s:property value="imagename"/>','<s:property value="action"/>',
						'<s:property value="abrivationid"/>','<s:property value="ipdseqno"/>')">
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12 set12manage">
            			<i class="fa fa-medkit" aria-hidden="true"></i>
            		</div>
            		</a>
            		<!-- <div class="col-lg-12 col-md-12 col-xs-12 colsm-12 set12manage">
            			<a href="#" class="setbordericon1"><i class="fa fa-calendar-o"></i></a>
            		</div>  -->
            	</div>
            	<a href="#" onclick="showipdpopup('<s:property value="id"/>','<s:property value="clientid"/>',
'<s:property value="practitionerid"/>','<s:property value="conditionid"/>','<s:property value="clientname"/>',
'<s:property value="practitionername"/>','<s:property value="dob"/>','<s:property value="town"/>',
'<s:property value="addmissionid"/>','<s:property value="age"/>',
'<s:property value="whopay"/>','<s:property value="tpid"/>',
'<s:property value="tpname"/>','<s:property value="practitionerMob"/>',
'<s:property value="bedname"/>','<s:property value="wardname"/>',
'<s:property value="balance"/>','<s:property value="treatmentepisodeid"/>',
'<s:property value="dis_initiate_time"/>','<s:property value="dis_initiate_status"/>',
'<s:property value="dis_form_time"/>','<s:property value="dis_form_status"/>',
'<s:property value="dis_mdicine_time"/>','<s:property value="dis_mdicine_status"/>',
'<s:property value="dis_bill_time"/>','<s:property value="dis_bill_status"/>',
'<s:property value="dis_nursing_time"/>','<s:property value="dis_nursing_status"/>',
'<s:property value="imagename"/>','<s:property value="action"/>','<s:property value="abrivationid"/>','<s:property value="ipdseqno"/>')">

            	<div class="col-sm-10 col-md-10 col-lg-10 col-xs-10" style="padding: 0px;line-height: 18px;">
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12" style="padding: 0px 0px 0px 10px;">
            			<p style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;"><s:property value="wardname"/> <strong><s:property value="bedname"/></strong> <span><b style="color: gold;">(<s:property value="ipdseqno"/>)</b></span></p>
            		</div>
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12" style="padding: 0px 0px 0px 10px;">
            			<p style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;"><s:property value="clientname"/></p>
            			<p class="hidden">Dignosis 123</p>
            		</div>
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12" style="padding: 0px 0px 0px 10px;">
            			<p style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;"><s:property value="age"/>  / <s:property value="gender"/>  <b style="font-size: 8px;white-space: nowrap; overflow: hidden;text-overflow: ellipsis;">(<s:property value="payby"/>)</b></p>
            		</div>
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12" style="padding: 0px 0px 0px 10px;">
            			<p style="display: -webkit-inline-box;"><s:property value="admissiondate"/></p> <small style="font-size: 9px;color: rgba(178, 187, 191, 0.98);">-<s:property value="abrivationid"/></small>
            		</div>
            		<div class="col-lg-12 col-md-12 col-xs-12 colsm-12" style="padding: 0px 0px 0px 10px;">
            			<p style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;"> <s:property value="practitionername"/> </p>
            		</div>
            	</div>
            </a>
            
            </div>
            <!-- /tile body -->

         </section>
        <!-- /tile -->

    </div>
    <!-- /col -->
    </s:if> 
    <s:else>
    <!-- col -->
    <div class="col-sm-2 col-md-2 col-lg-2 col-xs-2 portlets commentlist sortable mainbox bedsort">
        <!-- tile -->
        <!-- tile -->
        <s:if test="isactive==1">
        	 <a onclick="openPopup('inputIpd?wardid=<s:property value="wardid"/>&bedid=<s:property value="id"/>&action=<s:property value="action"/>')" href="#">
            <section class="tile bg-greensea portlet">

               
                <!-- tile body -->
                <div class="tile-body" style="padding-top: 22px;">
                
                    <center>
                    	<p><i class="fa fa-bed"></i></p>
                    	<p class="custom-font"><s:property value="wardname"/> <strong><s:property value="bedname"/></strong></p>
                    	<p class="custom-font">Available Bed</p>
                    
                    </center>
                </div>
                <!-- /tile body -->

            </section>
        	</a>
        </s:if>
        <s:else>
        	<a href="#">
            <section class="tile bg-greenseaum portlet">

               
                <!-- tile body -->
                <div class="tile-body" style="padding-top: 22px;">
                
                    <center>
                    	<p><i class="fa fa-bed"></i></p>
                    	<p class="custom-font"><s:property value="wardname"/> <strong><s:property value="bedname"/></strong></p>
                    	<p class="custom-font">Not Available Bed</p>
                    
                    </center>
                </div>
                <!-- /tile body -->

            </section>
        	</a>
        </s:else>
        
        <!-- /tile -->
        <!-- /tile -->


    </div>
    <!-- /col -->
    </s:else>
     </s:iterator>    
   </s:if>
   
   
   
    <script>
					var myWindow;
					function openWin() {
					     myWindow = window.open("emrnewEmr", "", "width=1400, height=800, addressbar=no,");
					}
			</script>