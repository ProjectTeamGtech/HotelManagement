<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.Emr.eu.entity.Emr"%>
<%@page import="java.util.ArrayList"%>


	<%@page import="com.apm.Pacs.eu.entity.Pacs"%>
<div class="row" style="margin-top: 20px;">
		<link href="common/css/printpreview.css" rel="stylesheet" />
		<%@ include file="/accounts/pages/letterhead.jsp" %>
	</div>

<br><br>
<div class="row" style="margin-top:10px;">
<div class="col-lg-10 col-md-10 col-xs-10" style="font-size: 15px;margin-right:20px !important;"><!--
	 Notes for UHID :  <b><s:property value="clientname"/></b> | Name : <b></b> | Age : <b><s:property value="age"/> / <s:property value="gender"/></b>

--></div>
</div>

<style>
.micimg{
	float: left;
    width: 27%;
}

</style>
</style>
<script type="text/javascript" src="pacs/js/pacs.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('otnotes');
	        	 $('.nicEdit-panelContain').parent().width('98%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });

</script>

<%ArrayList<Pacs>pacsimgList = (ArrayList<Pacs>)session.getAttribute("pacsimgList"); %>
<div class="panel-body sidebar">
						<%for(Pacs pacs : pacsimgList){ %>
								<div class="row">
										<div class="col-lg-9 col-md-9 col-xs-9">
										
											
												<img src="liveData/pacsimg/<%=pacs.getId() %>.png" class="img-responsive">
											
											
										
										</div>
										
										<div class="col-lg-3 col-md-3 col-xs-3">
											<label> <a href="#" onclick="showfindingdataAjax(<%=pacs.getId() %>)" data-toggle="modal" data-target="#addfinding"  title="Edit Findings" style="margin-right: 5px;">Findings : </a> </label>
											<%=pacs.getFinding() %>
										</div>
									</div>
							<%} %>
						
						
						<div class="row hidden-print">
							<div class="col-lg-10 col-md-10 col-xs-10" style="font-size: 20px;">
								<input style="margin-top: 20px;width:99px;" type="button" class="btn btn-primary"  value="Print" onclick="printpage()">
							</div>
						</div>
						
						
						
						<s:form action="findingPacs" theme="simple" id="findingfrm">
						<s:hidden name="id" id="fid"/>
									<div id="addfinding" class="modal fade modal-draggable" role="dialog">
									  <div class="modal-dialog modal-sm">
									    <!-- Modal content-->
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal">&times;</button>
									        <h4 class="modal-title">Update Findings</h4>
									      </div>
									      <div class="modal-body">
									        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
												<div class="">
												<div class="col-lg-7 col-md-7">
													<div class="form-group">
														<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting1(this)" title="Microphone" id="changer"></img>
													
												  </div>
												</div>
												    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;"><!--
												     <textarea name="notes" cols="" rows="10" tabindex="119" class="form-control foemse" title=""></textarea>
												    -->
												    	<s:textarea  style="height: 250px;" rows="10" cols="8" id="otnotes" name="otnotes"
														cssClass="showToolTip form-control" data-toggle="tooltip"
														title="Enter Other Template Text" placeholder="Enter finding" ></s:textarea>
												    </div>
												    
												   
												  </div>
											</div>
									      </div>
									      <div class="modal-footer">
									       <input type="button" onclick="updatefindings()"  class="btn btn-primary pull-right" value="Save">
											
									      </div>
									    </div>
									
									  </div>
									</div>
									</s:form>
									
									
									<script>
									function startConverting1(element) {
									
									   var abc=element.src.split('/');
									   
									     var right = "cicon/mic_off.png";
									         var left = "cicon/mic.png";
									         element.src = element.bln ? right : left;
									         element.bln = !element.bln;
									         
									       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
									   if(abc[5]=="mic_off.png")
									   {
									    startConverting();
									   }
									   else{
									   //var textvalue=document.getElementById("otnotes").value;
									  // localStorage.setItem("xx",textvalue);
									  // location.reload();
									   }
									   
									   
									
									       
									         
									     }
									</script>	