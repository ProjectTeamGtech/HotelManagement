<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="/struts-tags" prefix="s"%>
    <script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

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


<body>
<s:form action="saveotnotesNotAvailableSlot" theme="simple">
<s:hidden name="id" id="id"/>
	<div class="col-lg-1 col-md-1"></div>
	<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderset">
		<div class="panel-title">
			<h2 class="text-center padbotom">Voice Recording</h2>
		</div>
		<div class="">
		
		
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<div class="">
									<div class="col-lg-7 col-md-7">
										<div class="form-group">
											<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting1(this)" title="Microphone" id="changer"></img>
										
									  </div>
									</div>
									<div class="col-lg-5 col-md-5">
									<p align="right"><input type="button" value="Refresh" onclick="window.location.reload()" class="btn btn-primary"></p>
									</div>
									    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;"><!--
									     <textarea name="notes" cols="" rows="10" tabindex="119" class="form-control foemse" title=""></textarea>
									    -->
									    	<s:textarea style="height: 250px;" rows="10" cols="8" id="otnotes" name="otnotes"
											cssClass="showToolTip form-control" data-toggle="tooltip"
											title="Enter Other Template Text" placeholder="Enter Other Template Text" ></s:textarea>
									    </div>
									    
									   
									  </div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print" style="margin-top: 10px;">
									<div class="form-group">
									    <input type="submit" class="btn btn-primary" value="Print">
									   <input type="submit" class="btn btn-primary" value="Save">
									  </div>
								</div>
		
		</div>
	
	</div>
		
	
	<div class="col-lg-1 col-md-1"></div>



								
								
								
								
								


</s:form>
</body>
</html>

<script type="text/javascript">

var r = document.getElementById('result');

</script>

<script>
function startConverting1(element) {

   var abc=element.src.split('/');
   
     /* var right = "ivmartel-dwv-817a606/viewers/static/cicon/mic_off.png";
         var left = "ivmartel-dwv-817a606/viewers/static/cicon/mic.png"; */
         
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

