<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript"
	src="diarymanagement/js/viewClientProfile.js"></script>
	<script type="text/javascript"
	src="diarymanagement/js/addPatientTab.js"></script>
	
<style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
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
		    border-color: #339966;
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
    border-top: 2px solid #339966;
}
  .panel-primary {
      border-color: #339966;
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
    margin-bottom: 4px !important;
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
.settopbac {
    background-color: #ddd !important;
}
.sehe{
	height: 135px !important;
}
.totalbor {
    background-color: #f5f5f5 !important;
}

    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
	}
	p {
	    margin: 0 0 5.5px !important;
	    font-size: 9px !important;
	}
	.form-group {
    margin-bottom: 4px !important;
}
.setotas {
    padding: 0px;
    text-align: right;
    color: #008000 !important;
    font-size: 12px;
}
.wordscolr{
	    color: #d07878 !important;
    text-transform: uppercase;
}
.titleset {
    margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    line-height: 20px;
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
}
.titleset {
    margin: 0px;
    color: #6699cc !important;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
}
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}
.setotas{
	 padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 12px !important;
}
p {
    margin: 0 0 2.5px !important;
    font-size: 12px !important;
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
    font-size: 12px;
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
}
.setotas{
	  padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 12px;
}
p {
    margin: 0 0 2.5px !important;
    font-size: 12px !important;
}
</style>
 <link href="common/css/printpreview.css" rel="stylesheet" />
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript">
$(window).on('load', function() {
	$('#newloaderPopup').modal('hide');
});
$(document).ready(function(){
	 $('#newloaderPopup').modal('show');
});


	/* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	            )
	        ); });  */
	        
	        bkLib.onDomLoaded(function() {
		           
	        	// new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('declarationNotes');
	        	 $('.nicEdit-panelContain').parent().width('100%');
	        	 $('.nicEdit-panelContain').parent().next().width('100%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });
</script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<div class="">
							<div class="">
								<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Patient Profile</h4>
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
										
<s:form action="displayProfileClient" id="viewProfile" theme="simple">
<div class="col-lg-12 col-md-12 col-sm-12 topback2 hidden-print">
											<div class="col-lg-1 col-md-1 hidden-print" style="text-align: right;margin-top: 6px;" >
		<label>Patient :</label>
	</div>
	<div class="col-lg-4 col-md-4 hidden-print">
		<s:textfield name="client" id="client" readonly="true"
			cssClass="form-control" onclick="showPopUp()" data-toggle="modal"
			data-target="#clientSearch" />
		<s:hidden name="clientId" id="clientId"></s:hidden>
	</div>
	<div class="col-lg-2 col-md-2 hidden-print">
		<s:submit value="Go" cssClass="btn btn-primary"></s:submit>
	</div>
	
	<div class="col-lg-3 col-md-3 hidden-print">
		<s:select name="dectitle" id="dectitle" list="declerationTitleList"
		listKey="id" listValue="name" cssClass="form-control chosen-select" onchange="updateDeclration(this.value)"
		headerKey="0" headerValue="Select Decleration"/>
	</div>
										</div>
										<div class="marto displaynoneweb letterheadhgt">
										<div id="newpost" class="col-lg-12 col-md-12 col-sm-12 borderbot">
											
												<%@ include file="/accounts/pages/letterhead.jsp" %>
										</div>
            </div>

	<s:if test="clientId!=null">
	<section class="tile tile-simple printpadbot">
                                                        <!-- tile body -->
                                                        <div class="tile-body p-0">
                                                           <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
                                                           <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
					</div>
			<div class="col-lg-7 col-md-7 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
			
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Patient Name</label><span>: <s:property value="title" /> <s:property value="firstName" /> <s:property value="lastName" /> </span><span class="hidden-print">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Age / Gender</label><span class="hidden-print">: <s:property value="age" /> / <s:property value="gender"/></span>
				</div>
				
				<s:if test="apmtId!=''">
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">OPD No.</label><span>: <s:property value="apmtId" /></span>
				</div>
				</s:if>
				<s:else>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">IP No.</label><span>: <s:property value="ipdid" /></span>
				</div>
				</s:else>
				
				
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Age / Gender</label><span>: <s:property value="age" /> / <s:property value="gender"/></span>
				</div>
				<%if(loginfo.getIskunal()!=1){ %>
				
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Contact No</label><span>: <s:property value="mobNo" /></span>
				</div>
				<%} %>
				<%if(loginfo.getIskunal()!=1){ %>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Address</label><span>: <s:property value="clientaddress" />, <s:property value="town" /><!--<s:property value="postCode" /> --></span><span class="hidden-print">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label  hidden-print">Contact</label><span class="hidden-print">: <s:property value="mobNo" /></span>
				</div>
				<%} %>
			</div>
			<div class="col-lg-5 col-md-5 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
			<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label print-visible hidden-md hidden-lg">Date</label><span class="print-visible hidden-md hidden-lg">: <script>
  var currentDate = new Date(),
      day = currentDate.getDate(),
      month = currentDate.getMonth() + 1,
      year = currentDate.getFullYear();
  document.write(day + "/" + month + "/" + year)
</script> 
&nbsp;|&nbsp;
<script>
	var currentTime = new Date(),
      hours = currentTime.getHours(),
      minutes = currentTime.getMinutes();

	if (minutes < 10) {
	 minutes = "0" + minutes;
  }

	var suffix = "AM";
	if (hours >= 12) {
    suffix = "PM";
    hours = hours - 12;
	}
	if (hours == 0) {
	 hours = 12;
	}

	document.write(hours + ":" + minutes + " " + suffix)
</script>
</span>
				</div>
				
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">UHID</label><span class="">: <%-- <s:property value="abrivationid" /> --%><s:property value="patientIdAbrivation" /></span>
				</div>
				<s:if test="addmissiondate!=''">
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Admission Date</label><span class="">:<s:property value="addmissiondate" /></span>
				</div>
				</s:if>
				<s:if test="dischargedate!=''">
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Discharge Date</label><span class="">: <s:property value="dischargedate" /></span>
				</div>
				</s:if>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Payee</label><span>: 
					<s:if test="whopay=='Self'">
					
					<s:property value="whopay" />
					</s:if>
					
					<s:else>
					<s:property value="thirdPartyCompanyName" />
					</s:else></span>
					<s:hidden name="whopay" id="whopay"/>
				</div>
				
				
			</div>
		</div>
		
		
                                                            <!--<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 hidden">
                                                                <table width="100%">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td width="60%" valign="top" align="center">
                                                                                <div>

                                                                                    <table width="90%" border="0" cellspacing="0" cellpadding="0" valign="top" align="right" class="table">
                                                                                        <tbody>
                                                                                            <tr class="backti">
                                                                                                <th>Other Details</th>
                                                                                                <th></th>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <th class="borright"><b>Employer Name</b></th>
                                                                                                <th><s:property value="employerName" /></th>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright"><b>Occupation</b></td>
                                                                                                <td><s:property value="occupation" /></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright"><b>Source of Intro</b></td>
                                                                                                <td><s:property value="sourceOfIntro" /></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright"><b>Conditions</b></td>
                                                                                                <td><s:property value="treatmentType" /></td>
                                                                                            </tr>
                                                                                            <tr class="alt_tr">
                                                                                                <td class="borright"><b>GP Name</b></td>
                                                                                                <td><s:property value="gpname" />, <s:property value="surgeryName"/>, <s:property value="gpAddress"/>, <s:property value="gpTown"/>, <s:property value="gpPostCode"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright"><b>Insurance Co</b></td>
                                                                                                <td><s:property value="thirdPartyCompanyName" />, <s:property value="tpAddress"/>, <s:property value="tpTown"/>, <s:property value="tpPostCode"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <th class="borright"><b>Policy No</b></th>
                                                                                                <th><s:property value="policyNo" /></th>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <th class="borright"><b>Note</b></th>
                                                                                                <th><s:property value="note" /></th>
                                                                                            </tr>
                                                                                        </tbody>
                                                                                    </table>

                                                                                </div>
                                                                            </td>

                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>

                                                        --></div>
                                                        <!-- /tile body -->
                                                    </section>
		<div class="">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;">
			<div class="form-group" style="margin-bottom: 3px;">
					<b>Consultant: 
					<%if(loginfo.getIskunal()!=1){%>
					<s:property value="practitionerName" />(<s:property value="qualification" />)
					<%}else{ %>
					<s:property value="drqualification" />
					<%} %>
					</b>
				</div>
		</div>
				
				
			</div>
		
	<div class="col-lg-12" style="text-align:center ;padding-top: 15px;-webkit-print-color-adjust: exact;">
			<%-- <label style="font-size: 22px"><s:property value="declarationTitle"/></label> --%>
			<s:hidden name="declarationTitle" id="declarationTitle"></s:hidden>
			<span class="savedeclarationclientheading" style="font-size: 22px"><s:property value="declarationTitle"/></span>
		</div>
<div class="col-lg-12 col-md-12" style="text-align: justify;">
<%-- <textarea style="height: 250px;" id="declarationtextid" name="declarationtext" cols="8" rows="10" placeholder="Enter Text here"  data-toggle="tooltip" tabindex="119" class="showToolTip form-control" ><%=session.getAttribute("declarationNotes")%> </textarea> --%>
<textarea style="height: 250px;" rows="10" cols="10" id="declarationNotes" name="declarationNotes"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Declaration" placeholder="Enter Declaration"><%=session.getAttribute("declarationNotes")%></textarea>
</div>
</s:if>
</s:form>
 
<s:if test="clientId!=null">
<div class="col-lg-12 col-md-12 hidden-print" style="margin-bottom:10px; padding: 0px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;text-align: right;">
		                                <!-- <a class="btn btn-primary savebtn savebigbtn" style="line-height: 45px; text-decoration: none;" onclick="window.print();"> Print</a> -->
	                                  		<s:if test="status==0">
	                                  		<br>
	                                  		<a class="btn btn-primary savebtn savebigbtn" style="line-height: 45px; text-decoration: none;" onclick="saveDeclarationData()"> Save  </a>
		                                	</s:if>
		                                	<s:else>
		                                	<a class="btn btn-primary savebtn savebigbtn" style="line-height: 45px; text-decoration: none;" onclick="saveDeclarationData()"> Update</a>
		                                	</s:else>
		                                	
		                                </div>
		                            </div>
		                        </div>
	
			</s:if>
<s:if test="status==0">
<s:form action="savedeclarationClient" id="savedeclarationform">
	<s:hidden name="clientId"></s:hidden>
	<s:hidden name="practid"></s:hidden>
	<s:hidden name="ipdid"></s:hidden>
	<s:hidden name="apmtId"></s:hidden>
	<s:hidden name="declarationNotes" id="declarationNotes1"></s:hidden>
	<s:hidden name="declarationTitle" id="declarationTitle1"></s:hidden>
</s:form>
</s:if>
<s:else>
<s:form action="updatedeclarationClient" id="savedeclarationform">
	<s:hidden name="declarationid"></s:hidden>
	<s:hidden name="clientId"></s:hidden>
	<s:hidden name="practid"></s:hidden>
	<s:hidden name="ipdid"></s:hidden>
	<s:hidden name="declarationNotes" id="declarationNotes1"></s:hidden>
	<s:hidden name="declarationTitle" id="declarationTitle1"></s:hidden>
</s:form>
</s:else>


<!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);z-index: 99" id="newloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->

<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Patient Search</h4>
			</div>
			<div class="modal-body">
				<%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>		
</div>
									</div>
								</div>
							</div>
						</div>
						
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
 