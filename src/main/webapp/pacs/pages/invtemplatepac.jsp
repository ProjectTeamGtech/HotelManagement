<%@taglib uri="/struts-tags" prefix="s"%>


 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="thirdParties/js/otnotes.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<style>
.foemse{
	border-left: none !important;
    border-right: none !important;
    border-top: none !important;
}
.topback2 {
        background-color: #6699CC;
    padding: 10px 10px 0px 10px !important;
    color:#fff;
}
.borderset{
	border: 1px solid #6699CC;
    padding: 0px;
}
.padbotom{
	padding-bottom: 10px;
	    color: #339966;
}
.setem{
	margin-left: -70px;
}
.micimg{
	float: left;
    width: 7%;
}



</style>

<style type="text/css">
    table { page-break-inside:auto }
    tr    { page-break-inside:avoid; page-break-after:auto }
    thead { display:table-header-group }
    tfoot { display:table-footer-group }
</style>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Pacs Report</h4>
								
								</div>
								<div class="col-lg-7 col-md-7">
										<div class="form-group">
											<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting();" title="Microphone" id="changer"></img>
										
									  </div>
									</div>
								
							</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('other_template_text');
	        	 $('.nicEdit-panelContain').parent().width('600px');
	        	 $('.nicEdit-panelContain').parent().next().width('600px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });
 
 
 window.onload  = function(){
	 
	// nicEditors.findEditor('other_template_text').setContent("hello");
 }
 
 var counter = 0;

function AddFileUpload1()
{
     var div = document.createElement('DIV');
     div.id = counter;
     div.className = "col-lg-10 col-md-10 padnil";
     div.innerHTML = '<input id="fileUpload" name = "fileUpload" type="file" class="indopborder" />'; 
     
     var div1 = document.createElement('DIV');
     div1.id = counter;
     div1.className = "col-lg-2 col-md-2";
     
     div1.innerHTML =  '<a href="#" id="Button' + counter + '" ' + 'onclick = "RemoveFileUpload('+counter+')"><i class="fa fa-times text-danger" style="margin-top: -7px;position: absolute;"></i></a>';
                    
     document.getElementById("drop").appendChild(div);
     document.getElementById("drop").appendChild(div1);
     counter++;
     
     
    // alert(isFileSelected(counter));
}
 
 
 function showtemplatedata(id){
 var url = "templatePacs?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showtemplatedataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
 }
 
 function showtemplatedataRequest(){
 	if (req.readyState == 4) {
			if (req.status == 200) {
			
				//document.getElementById("allPatient").innerHTML = req.responseText;
				 nicEditors.findEditor('other_template_text').setContent(req.responseText);
				
	         	
				
	         }
		}
 }

</script>


<s:form action="reportPacs" id="master_form" theme="simple" method="post" enctype="multipart/form-data">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			
			<%-- <label>Select Investigation</label><label class="text-danger">*</label>
			    
			    <s:select list="invstList" name="invname" id="invname" 
			     listKey="id"  cssClass="showToolTip form-control chosen-select"
			      listValue="id" headerKey="0" headerValue="Select Investigation"></s:select> <br>  --%>
			      
			      
			
			    <label>Select Template</label><label class="text-danger">*</label>
			    
			    <s:select onchange="showtemplatedata(this.value)" list="templateList" name="name" id="name" 
			     listKey="id"  cssClass="showToolTip form-control chosen-select"
			      listValue="name" headerKey="0" headerValue="Select Template"></s:select> <br> 
				<label>Title</label><label class="text-danger">*</label>
				<s:textfield id="title" name="title"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Title" placeholder="Enter Title" onkeyup="initialCap(this)"/>
					<label>Other Template Text</label><label class="text-danger">*</label>
		
			<!-- 	<textarea rows="10" cols="6" id="other_template_text" name="other_template_text"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Other Template Text" placeholder="Enter Other Template Text" ></textarea> -->
					
					<textarea style="height: 250px;" rows="10" cols="8" id="other_template_text" name="other_template_text"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Other Template Text" placeholder="Enter Other Template Text" ></textarea>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

<s:hidden name="selectedinvstid" id="selectedinvstid"/>
<s:hidden name="Multipacsid" id="Multipacsid"/>
									        <div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
									        	<div class="col-lg-5 col-md-5 col-xs-12 col-sm-12" style="text-align:left;">
					                      			Attached File: <div id="attachdfilediv" class="attached"></div>
					                      		</div>
					                      		<div class="col-lg-7 col-md-7" style="float:right;margin-top: -4px;">
														 <button id="Button1" class="btn btn-warning" type="button" onclick = "AddFileUpload1()"><i class="fa fa-paperclip"></i> Attach Files</button>
													</div>
									        </div>
												<br>
												<br>
												<br>
											  <div class="">
												<input type="hidden" id="fileUpload" name = "fileUploadd" />
												  <div id = "drop" class="col-lg-12 col-md-12" style="padding-top: 5px;">
									      			  <!--FileUpload Controls will be added here -->
									      			  <div id="upload"></div>
									      			  <div id = "draftAttachments"></div>
					    						</div>
		   									</div>
	                      					 <input type="submit" class="btn btn-primary btncr hidden" value="Save" >
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" />
			<s:reset cssClass="btn btn-primary" />
			<!-- <a href="OtherTemplate" class="btn btn-primary">Back</a> -->
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>


<script><!--<!--
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
   location.reload();
   }
   
   

       
         
     }
</script>


