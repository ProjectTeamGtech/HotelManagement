<%@taglib uri="/struts-tags" prefix="s"%>

<style>
.head{
line-height: 2;
  color:white;
  vertical-align: middle;
}
.imglokesh{
height: 100px;
width: 100%;
border-radius: 8px;
}
.name{
font-weight: bold;
}
</style>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div style="text-align: center;background-color: green;height: 40px;">
	<h3 class="head">User Signatures</h3>
</div>
</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 2px solid gray;min-height: 30px; ">
<form method="post" action="saveSignMaster"  enctype='multipart/form-data' onsubmit="return validdate()">
	<div class="form-inline" style="padding: 10px;">
	
	<s:hidden name="subuploadfilesContentType"></s:hidden>
	<s:hidden name="subuploadfilesFileName"></s:hidden>
			<div class="form-group" style="width:20%;">
				<s:select list="userProfileList" name='name' listKey="id" listValue="name" headerKey="0" headerValue="Select User" 
				id='userid' cssClass="chosen-select" cssStyle='width:10%'></s:select>
			</div>
			<div class="form-group" style="width:20%;">
			<s:file accept="image/*" cssClass="form-control" theme="simple" name="subuploadfiles" id='fll'></s:file>
			</div>
			<div class="form-group" style="width:5%;">
			<s:submit value="Add" theme="simple" cssClass="btn btn-success"></s:submit>
			</div>
	</div>		
</form>	
</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 50px;">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<s:iterator value='signatureList'>
			
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="text-align: center;">
				
				<img alt="Not Found" src='liveData/signature/<s:property value="filename"/>' class='imglokesh'>
				<h4 class="name"><s:property value="initial"/> <s:property value="firstname"/>  <s:property value="lastname"/></h4>
			</div>
			
		</s:iterator>
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
    
    
    function validdate(){
    	$('#baselayout1loaderPopup').modal( "show" );
    	var user=document.getElementById('userid').value;
    	var file=document.getElementById('fll').value;
    	var flag=true;
    	if(user=='0'){
    		alert('Select User');
    		flag= false;
    	}
    	if(file==''){
    		alert('Select File');
    		flag= false;
    	}
    	if(!flag){
    		$('#baselayout1loaderPopup').modal( "hide" );
    	}
    	
    	return flag;
    	
    }
    </script>