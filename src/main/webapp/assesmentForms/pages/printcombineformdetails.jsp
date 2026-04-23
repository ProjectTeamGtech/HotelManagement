<%@page import="com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO"%>
<%@page import="com.apm.Master.eu.bi.MasterDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="com.apm.AssesmentForms.eu.bi.AssessmentFormDAO"%>
<%@page import="com.apm.AssesmentForms.web.action.Template"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<script type="text/javascript" src="assesmentForms/js/createNewAssesmentForm.js"></script>
<%@page import="java.util.ArrayList"%>
<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />



<style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #43b9be !important;
    margin-bottom: 15px;
}
        .adformback {
            border: 1px solid;
            padding: 10px 0px 0px;
            margin-top: 0px;
            width: 98%;
            margin-left: 9px;
        }
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0px;
            text-align: right;
            font-size: 12px;
        }
        .marbot15 {
            margin-bottom: 15px;
        }
        .martop15 {
            margin-top: 15px;
        }
        .diagtitle {
            background-color: #000;
            color: #FFF;
            padding: 10px;
            font-weight: normal;
            padding-top: 12px !important;
        }
        .bednosele {
            width: 10%;
            margin-top: -40px;
        }
        .textareaheight{
        height: 50px !important;;
        }
       
       .paddtop{
        padding: 0px 0px 14px 2px;
    	}
        .widthtabhedth1{
        	width: 30%;
        }
        .widthtabhedth2{
        	width: 7%;
        }
        .admissionbackgreen {
		    width: 210px;
		}
		.form-group {
    		margin-top: 4px;
		}
		.pad8{
			padding-top: 8px;
		}
		.backgrey{
			        background-color: rgba(149, 222, 91, 0.19);
		}
		.pnameback{
			    background-color: #f5f5f5;
    			margin-top: -7px;
		}
		.panel-primary {
		    border-color: #43b9be;
		}
		.padsign{
			padding-top: 100px;
			padding-left:0px;
			padding-right:0px;
		}
		.help-block {
		    display: block;
		    margin-top: 0px !important;
		    margin-bottom: 0px !important;
		    color: #737373;
		}
		 .bordertopgreen1 {
    border-top: 2px solid #43b9be;
}
  .panel-primary {
      border-color: #43b9be;
  }
  .padsign{
   padding-top: 40px;
  }
  .help-block {
    display: block;
    margin-top: 0px;
    margin-bottom: 0px;
    color: #737373;
}
h3, .h3 {
    font-size: 16px;
    font-weight: bold;
    color: #6699cc;
    margin-top: 0px;
    margin-bottom: 5px;
}
.form-group {
    margin-bottom: 0px !important;
}

p {
    margin: 0 0 5.5px !important;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.settopbac {
    background-color: #ddd;
}
.totalbor {
    background-color: #f5f5f5;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 5px 2px 5px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 12px;
    border-right: none !important;
    border-left: none !important;
}

 @media print
{
    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
	}
	p {
	    margin: 0 0 5.5px !important;
	    font-size: 9px !important;
	}
	.form-group {
    margin-bottom: 0px !important;
}

.titleset {
    color: blank !important;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    font-weight: bold !important;
    line-height: 20px;
    padding: 2px 0px 0px 6px !important;
    background-color: #efefef !important;
    -webkit-print-color-adjust: exact !important; 
}
h4, .h4 {
    font-size: 12px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:12px !important;
	color: firebrick !important;
	text-transform: uppercase;
}

.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}
}
    </style>
    <style>

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
    font-size: 10px !important; 
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
td, th {
    padding: 0px 3px 0px 5px !important;
    border-right: 1px solid #eee !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
	text-transform: uppercase;
}
.form-group {
    margin-top: 0px;
}
</style>



<script type="text/javascript">
$(document).ready(function(){
	
	<%Template template = (Template)session.getAttribute("assmnttemplatedetails");%>
	<%if(template.isIncludeImage()){%>
		document.getElementById('addcanvasdiv').style.display = 'none';
	<%}%>
	
	
	<%-- <%String columnStr = (String)session.getAttribute("columnStr");%>
	var columnstr1 = '<%=columnStr%>';
	var temp = columnstr1.split("#"); --%>
	//alert(temp);
	//alert(temp.length);
	
	var i = 0;
		<%-- <%ArrayList<String> list1 = (ArrayList<String>)session.getAttribute("assessmentFormsList");
     	for(String s : list1){%>
     	//alert(temp[i]);
     	var fieldId1 = temp[i];
     	//alert(fieldId1);
		 document.getElementById(fieldId1).value = '<%=s%>';
		
		i++;
		<%}%> --%>
     
	
	
	<%String imagedata = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata);%>
	var dataURL = '<%=imagedata%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
   /*  var canvas = document.getElementById('editupdateimgTemplate');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj, 0, 0); */
    
    var canvas1 = document.getElementById('editupdateimgTemplate');
    var context1 = canvas1.getContext('2d');
    context1.drawImage(imageObj, 0, 0);
    
    var iwidth = imageObj.width;
    var iheight = imageObj.height;
    
    //alert(iwidth + '/' + iheight)
    };
    
    
    
	
});	

function editUpdateImageCanvas(){
	
	<%String imagedata1 = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata1);%>
	var dataURL = '<%=imagedata1%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas1 = document.getElementById('Background');
    var context1 = canvas1.getContext('2d');
    context1.drawImage(imageObj, 0, 0);
   
    };
	
	 $( '#editimageCanvas' ).modal( "show" );
	
}



var i = 0;

function saveTemplateDetails(){
	var Pic;
	 for(var i in LAYERS){
		  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
		// alert(i);
	}
	 
	 document.getElementById('imageData').value = Pic;
	 
	 document.getElementById('temp_form').submit();
}
/* 
function print(){
	alert('hi');
	window.print();
} */

</script>




<s:form action="combinesaveAssesmentForms" id="temp_form" theme="simple"> 
<s:hidden name="repeat" id="repeat"/>
<s:hidden name="imageData" id="imageData"/>


<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
		<div class=" letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
			
		</div>
	</div>
	
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors"><s:property value="templateName"/> <s:if test="ipdid!=0"><s:property value="wardname"/> / <s:property value="bedname"/></s:if></b>
								
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
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
								<s:if test="agegender!=''"><b for="inputEmail3" class="control-label">Age / Gender</b></s:if>
							</div>
							<s:if test="contact!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								  <b for="inputEmail3" class="control-label">Contact</b>
							</div>
							</s:if>
							
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="abrivationid"/></span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="clientFullName"/> </span>
							</div>
							
							 <div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<s:if test="agegender!=''"><span>: <s:property value="agegender" /></span></s:if>
							</div>
							<s:if test="contact!=''">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									  <span>: <s:property value="contact"/></span>
								</div>
							</s:if>
							
						</div>
						
						
						
							
						
						
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Doctor Name</b>           		 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="doctorname"/></span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="address"/> </span> 
							</div>
						</div>
					</div>
					</div>
	
	
	
	
	</div>





	<div class="col-lg-12 col-md-12 col-xs-12 widthcan" style="padding: 0px;">
		<div>
			<div>
		
			
			
		<div class="row hidden" id="addcanvasdiv">
			<div class="col-lg-12 col-md-12 col-xs-12">					
				<div class="col-lg-7 col-md-6 col-xs-12 canvwidth">
				`<canvas id="editupdateimgTemplate" height="450" width="700" ></canvas>
				</div>
			
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-print" style="margin-left: 5px;">
					<h3><a href="#" class="text-warning" onclick="editUpdateImageCanvas();" title="Edit Image"><i class="fa fa-edit"></i></a></h3>
				</div>
			</div>	
		</div>
			
			
			<div class="row rownew hidden">
			<div class="col-lg-4 col-md-4 col-xs-4">
				<div class="form-group">
					<label>Practitioner</label><label><span class="text-danger">*</span></label>
					<s:if test="%{#userList != 'null'}" >
						<s:select cssClass="form-control showToolTip chosen hidden-print" id="diaryUserAssessment" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setClientsAssessment(this.value)"   />
					</s:if>
				</div>
				<label  id = "diaryUserAssessmentError" class="text-danger"></label>
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4">
				<div class="form-group">
					<label>Patient</label><label><span class="text-danger">*</span></label>
					<s:select cssClass="form-control showToolTip chosen" id="clientnameAssessment" name="clientName" list="clientList" listKey="id" listValue="clientName" headerKey="0" theme="simple" headerValue="Select Patient"  onchange="setConditionAssessment(this.value)"  />
					
				</div>
				<label  id = "clientnameAssessmentError" class="text-danger"></label>	
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4">
				<div class="form-group">
					<label>Speciality</label><label><span class="text-danger">*</span></label>
					<s:select onchange="setAssesmentCondition(this.value)" cssClass="form-control showToolTip chosen" id="conditionAssessment" name="condition" list="conditionList" listKey="id" listValue="treatmentType" headerKey="0" theme="simple" headerValue="Select Speciality"    />
					
				</div>
			</div>
							
				
			</div>	
			 	
			
			<%-- </s:if>  --%>
				<s:hidden name="diaryUser" id="diaryUser"/>
				<s:hidden name="clientName" id="diaryUser"/>
				<s:hidden name="condition" id="condition"/>
				<s:hidden name="actionType" id ="actionType"/>
				<s:hidden name="mrdid" id ="mrdid"/>
				<s:hidden name="mrd_clientid" id ="mrd_clientid"/>
			 	
	<% ArrayList<Master>leftNameList = (ArrayList<Master>)session.getAttribute("leftNameList");%>
			<% ArrayList<Master>topNameList = (ArrayList<Master>)session.getAttribute("topNameList");%>
			<% String assementtemplateId = (String)session.getAttribute("assementtemplateId");
			
			Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			%>
			
			
			
			<table class="table table-striped gridexample table-responsive" id = "allusertable">
				<col/>
				<% for (Master m : topNameList){%>
					<col/>
				<%} %>
			
			<thead>
					<tr>
					<th></th>
					
					<% for (Master m : topNameList){%>
					 <th style="text-align: center;" id="wn1"><%=m.getName() %></th>
					<%} %>
				</tr>
			
			</thead>
			<tbody>
				<% 
				int ct=0; 
					
				int clinicendtime = leftNameList.size();
				clinicendtime = clinicendtime - ct;
				clinicendtime = clinicendtime + 1;
					
					int countslot = 1;
					String weekName[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
					String tempCt = "";
					String tempMinute = "";
					String subjrctid = "";
					
				
				%>
				
				
					<div id="tgOver1" class="tg-col-overlaywrapper" style="display: none;">
							<div class="tg-hourmarker tg-nowmarker" id="tgnowmarker" style="top: 0px;"> </div>
						</div>
				
				<%String classname="";int i=1;for(Master m :leftNameList){
					int cval=topNameList.size()*i;
					cval=cval-topNameList.size();
					int cp = cval+1;
					
					String dsplay = "";
					if(m.getFieldtype()!=null){
						if(!m.getFieldtype().equals("")){
							if(m.getFieldtype().equals("Heading")){
								dsplay = "none";
							}
						}
					}
					
					%>
					<tr>
					    <td style="font-size: 14px; font-weight: bold;"><span class="timeset"><%=m.getName() %></span></td>
						
						<%  for(int j=0;j<topNameList.size();j++){
								Master subj = topNameList.get(j);
							
								if(cp == countslot){
									if(j>0){
										classname = "yellow";
									}else{
										classname = "sub";
									}
									
								}else{
									
								}
							
						%>
							
							<td style="display: <%=dsplay %>" valign="top" id="<%=countslot %>" >
								<%for(int k=0;k<1;k++){ %>
										<%
											tempCt =  Integer.toString(ct);
											tempMinute = Integer.toString((5*k));
											if(ct <= 9) {
												tempCt = "0" + Integer.toString(ct);
											}
											if((5*k) <= 9) {
												tempMinute = "0" + Integer.toString((5*k));
											}
											
											MasterDAO masterDAO = new JDBCMasterDAO(connection);
											String combineformclientid = (String)session.getAttribute("combineformclientid");
											String combineformdoctorid = (String)session.getAttribute("combineformdoctorid");
											String combineformconditionid = (String)session.getAttribute("combineformconditionid");
											
											String marks = masterDAO.getCombineFormDetails(assementtemplateId,combineformclientid,combineformdoctorid,combineformconditionid,m.getId(),subj.getDisplayname());
											//String marks = masterDAO.getEnteredMarks(terms,classid,m.getId(),subj.getName());
										%>
											
										
									<%if(k==6){ %>
										<div    id="<%=(5*k) %>min<%=countslot %>-<%=subj.getId() %>-<%=m.getId() %>~<%=marks %>"  class="<%=classname %>" title="<%=tempCt %>:<%=tempMinute %>" style="text-align: center; " >
											
										</div>
									<%}else{ %>
										<div id="<%=(5*k) %>min<%=countslot %>-<%=subj.getId() %>-<%=m.getId() %>~<%=marks %>"  class="<%=classname %>" title="<%=tempCt %>:<%=tempMinute %>"  style="text-align: center;"></div>
									<% }%>
        							
        						<% }%>
								
							</td>
							<%countslot++; %>
								
						<% }%>
						
					
						<%ct++; %>
					</tr>
					<%i++; %>
				<% }%>
			</tbody>
			
				
				
			</table>
					
		
		</div>
	</div>
</div>

	<script>
window.onload = function(){
	 $('.sub').each(function() { //loop through each checkbox
		 var id = this.id;
		 var data = id.split('~');
		document.getElementById(id).innerHTML = '<span class="ss"  id="n'+id+'" name="n'+data[0]+'"></span>';  
		
		
		var mid="n"+id;
		document.getElementById(mid).innerHTML = data[1];
		
     });
}
</script>										
				


		
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden text-right hidden-print">
			<input type="submit"  class="btn btn-primary" value="Save" onclick="saveTemplateDetails();"/>
			<!-- <a class="btn btn-primary"	style="text-decoration: none" onclick="window.print();"> Print</a> -->
			<!-- <input type="button"  class="btn btn-primary" value="Print" onclick="print();"/> -->					

<!--         <button type="button"  class="btn btn-primary"  value="Save" onclick="savePreviewTemplate('templateTable')">Save</button>
 --> 		<s:reset cssClass="btn btn-primary" />		
		</div>
		
		
		
</s:form> 


	<div class="row hidden-print">
		<div class="col-lg-12 col-md-12 col-xs-12 col-md-12 text-right">
				<a class="btn btn-primary"	style="text-decoration: none" onclick="window.print();"> Print</a>
				<button class="btn btn-primary" id="expbtn">Export</button>
			
				<script>
				$("#expbtn").click(function(){
	
					$(".table2excel").table2excel({
						exclude: ".noExl",
						name: "Excel Document Name",
						filename: "myFileName",
						fileext: ".xls",
						exclude_img: true,
						exclude_links: true,
						exclude_inputs: true
					});
				});
			</script>
		</div>
		
		</div>
		



<!-- Modal -->
<s:form action="updatecombineformimgListAssessmentForm" id="editimage_form" theme="simple"> 
 <s:hidden name="templateId" id="templateId1"> </s:hidden>
  <s:hidden name="imageData" id="imageData1"/>
  <s:hidden name="actionType" id ="actionType1"/>
  <s:hidden name="id" id="id1"> </s:hidden>
   <s:hidden name="formtype" id="formtype"/>
  
     <s:hidden name="clientName" id="clientname11"/>
    <s:hidden id = "diaryUser1"  name = "diaryUser"></s:hidden>
    <s:hidden id = "condition1"  name = "condition"></s:hidden>
  
<div class="modal fade" id="editimageCanvas" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Image</h4>
				</div>
	     <div class="modal-body" style="height: 600px;  overflow-y: scroll;">
	      	<button type="button" class="btn btn-primary" onclick="saveEditImageTemplate();">Update Image</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
	      		<%@ include file="/minipaint/editor.jsp"%>
	      </div>
	      <div class="modal-footer">
	       <button type="button" class="btn btn-primary" onclick="saveEditImageTemplate();">Update Image</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
   
  </div>
</div>	
</s:form>

<script>
    function showhide()
     {
           var div = document.getElementById("newpost");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }
  </script>
