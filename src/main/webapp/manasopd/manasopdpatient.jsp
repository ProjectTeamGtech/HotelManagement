<!doctype html>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>                    
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script type="text/javascript">

$(document).ready(function(){
  <%if(loginfo.getUserType()==5) {%>
	//alert("hi");
	document.getElementById("diciplineName123").value='<%loginfo.getDicipline();%>'
	$("#diciplineName123").trigger("chosen:updated");
	$(".chosen-select").chosen({allow_single_deselect: true});
	<%}%>
	
	});
	
</script>
<style>
 														
/* #diciplineName123_chosen{
width: 307px !important;
    min-width: 10% !important;
}
#diaryUsersss_chosen{
width: 307px !important;
    min-width: 10% !important;
border: 0 !important;
    outline: 0;
    background-color: #ceecf2 !important;
    border-bottom: 1px solid #0000002e !important;
    font-size: larger;
} * #diaryUsersss{
width: 33%;
border: 0 !important;
    outline: 0;
    background-color: #ceecf2 !important;
    border-bottom: 1px solid #0000002e !important;
    font-size: larger;
}  */
.newbtn{
position: absolute !important;
    font-size: 14px !important;
    color: #fff !important;
    background-color: #15536E !important;
    border-color: #15536E !important;
    margin-right: -203px !important;
    border-radius: 0.50rem; 
    padding-top: 1px;
}
</style>

                 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                         <div class="col-lg-3 col-md-3 col-xs-12 col-sm-12" style="padding-top: 2%;">
                                 <label style="font-size: medium;font-weight: 500;">Step 1.&emsp;Select Department</label>
                        <s:select id="diciplineName123" name="discipline" list="disciplineList" listKey="id" listValue="discipline"
						headerKey="0" headerValue="Select Speciality"
						title="Select Discipline" 
						cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"  theme="simple" onchange="showselecteddr(this.value)"/>
                        </div>
                        <div class="col-lg-3 col-md-3 col-xs-12 col-sm-12" style="padding-top: 2%;">
                       
                                 <label style="font-size: medium;font-weight: 500;">Step 2.&emsp;Select Doctor</label>
                                  <div  id="selecteddrwithsp">
                        <s:select id="diaryUsersss" name="diaryUser" list="userList" listKey="id" listValue="diaryUser"
						headerKey="0" headerValue="Select Doctor"
						title="Select Doctor" 
						cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"  theme="simple"  onchange="hidelabe(this.value)"/>
						</div>
                        </div>
                           
                             <div class='col-lg-3 col-md-3 col-xs-12 col-sm-12' style="padding-top: 2%;">
                             <label style="font-size: medium;font-weight: 500;">Step 3.&emsp;Select Appointment Date</label>
                             
                              <s:textfield  name="commencing" cssClass="form-control" id="commencing" onchange="getslot()" readonly="true"/>
                             
                             </div> 
 							                           
                             <div class='col-lg-3 col-md-3 col-xs-12 col-sm-12' style="padding-top: 4%;" >
 							
 						
 							<input onclick="getslot()" type="button" id="" value=" Available Slot" class="btn btn-primary"/>
 							<input onclick="showdisplaynewopd()" type="button" value=" Refresh " class="btn btn-primary hidden" style="padding: 39px 40px 10px;">
 						</div> 
                       </div>     
                       	
           