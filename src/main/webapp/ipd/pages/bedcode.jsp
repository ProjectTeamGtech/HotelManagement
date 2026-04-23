 		<%@ taglib uri="/struts-tags"  prefix="s"%>
 		
<s:if test="bedlist.size>0">
<s:iterator value="bedlist">
<s:if test="status==1">
<!-- col -->
<div class="col-lg-1 col-sm-2 col-md-2 portlets sortable smallbedic">
    <!-- tile -->
         <s:if test="excessAmt==1">
        	 <section class="tile bg-darkred portlet">
        </s:if>
        <s:else>
        	 <section class="tile bg-lightred portlet">
        </s:else>
            <!-- tile header -->
            <div class="tile-header dvd dvd-btm minheight28" style="text-align: center;">

                <h1 class="custom-font font11"><s:property value="wardname"/></h1>
                <h1 class="custom-font font11"> <strong><s:property value="bedname"/> </strong></h1>
 				<!-- Notification dropdown start-->
 				<div id="header_inbox_bar" class="dropdown nitificsamll hidden imanage">
                   <span id="m<s:property value="addmissionid"/>"  onclick="getajaxnotification(<s:property value="addmissionid"/>)" style="cursor:pointer"  href="#"  aria-haspopup="true" aria-expanded="true" data-placement="auto">
        		      <i class="fa fa-bell">
        		      </i>
          
      </span><br> 
  
         <s:if test="initialdischargeStatus==0">
				<span onclick="setinitialDischargeIpd('dis_initiate_status','dis_initiate_time','<s:property value="treatmentepisodeid"/>',0)" style="color:#0EFF00;cursor:pointer;position:absolute;z-index: 999;"><i class="fa fa-flag fontflag" title="Initiate Discharge Process"></i></span>        
        </s:if>
        <s:else>
        
             <span onclick="setinitialDischargeIpd('dis_initiate_status','dis_initiate_time','<s:property value="treatmentepisodeid"/>',1)" style="color:orange;cursor:pointer;position:absolute;z-index: 999;"><i class="fa fa-flag fontflag" title="Discharge Initiated" ></i></span>
        </s:else>
       
                    
                    <!--<s:if test="autochargeraised==0">
                    <span onclick="getstdcharge(<s:property value="addmissionid"/>,<s:property value="clientid"/>,'<s:property value="stdChargeID"/>',<s:property value="checkStandardChargeExist"/>,<s:property value="wardid"/>)" style="color:#0EFF00;cursor:pointer;z-index: 999;"><i class="fa fa-plus nitific" title="Auto Charge"></i></span>
                    </s:if>
                    <s:else>
                       <span onclick="getstdcharge(<s:property value="addmissionid"/>,<s:property value="clientid"/>,'<s:property value="stdChargeID"/>',<s:property value="checkStandardChargeExist"/>,<s:property value="wardid"/>)" style="color:orange;cursor:pointer;z-index: 999;"><i class="fa fa-plus nitific" title="Auto Charge"></i></span>
                    </s:else>
  
  -->
  
</div>
            </div>
            <!-- /tile header -->
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
'<s:property value="imagename"/>','<s:property value="action"/>')">
                      <!-- tile body -->
                <div class="tile-body">
                    <p><s:property value="age"/> yr / <s:property value="gender"/></p>
                    <p><s:property value="admissiondate"/></p>
                </div>
                <!-- /tile body -->
            </a>
            </section>
    
    <!-- /tile -->
</div>
<!-- /col -->
</s:if>
<s:else>
<!-- col -->
<div class="col-lg-1 col-sm-2 col-md-2 portlets sortable smallbedic">
    <!-- tile -->
    <!-- tile -->
    <a href="inputIpd?wardid=<s:property value="wardid"/>&bedid=<s:property value="id"/>&action=<s:property value="action"/>">
        <section class="tile bg-greensea portlet minheightsmall">

            <!-- tile header -->
            <div class="tile-header dvd dvd-btm" style="text-align: center;">
                <h1 class="custom-font font11"><s:property value="wardname"/></h1>
                <h1 class="custom-font font11"><strong><s:property value="bedname"/></strong></h1>

            </div>
            <!-- /tile header -->
            <!-- tile body -->
            <div class="tile-body">
                <center><p>Available Bed</p>
                <i class="fa fa-bed"></i></center>
            </div>
            <!-- /tile body -->

        </section>
    </a>
    <!-- /tile -->
    <!-- /tile -->


</div>
<!-- /col -->
</s:else>
</s:iterator>
</s:if>