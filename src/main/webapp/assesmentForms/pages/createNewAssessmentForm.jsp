<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/createNewAssesmentForm.js"></script>
 <script src="Content/slimScroll/jquery.slimscroll.min.js"></script>

<%--  <script>
        $(function () {
            $('.filter').slimScroll({
                height: '250px'
            });
        });
    </script> --%>
    
    <script type="text/javascript">    

    $(document).ready(function(){
    	
    	document.getElementById('btn2').style.display = 'none';
    	document.getElementById('btn1').style.display = '';
    	
    });	
    
function editPreview(tableID){
	
	  
	<%String imagedata = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata);%>
	var dataURL = '<%=imagedata%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas = document.getElementById('Background');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj, 0, 0);
    };
    
	var i = 0;
	var Pic;
	 for(var i in LAYERS){
	  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
	// alert(i);
}

	 document.getElementById('imageData').value = Pic;
	
	 document.getElementById('tname').innerHTML = editTempName;
	 previewAssessment(tableID);
}
    
    
    </script>
    
  <style>
        .order {
            margin-bottom: 10px;
            border-bottom: dotted 1px #A4A4A4;
            padding: 5px;
        }
        .subfilters{
        	padding: 10px 10px 10px 10px;background-color: #e8e8e8;min-height: 44px;
        }
    </style>
    
    <div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Create New Assessment Form</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>

	<s:form action="addPreviewAssesmentForms" id="savefrm" enctype="multipart/form-data" method="post" theme="simple">
 <div id="wrapper"> 
 <div id="page-wrapper">
    <div >
                            <div class="col-lg-12 col-md-12 topback2">      
                             <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="color:red; margin-bottom: 10px;padding:0px;">
                    Note : Do not include field for Practitioner Name, Patient Name and Condition, they are inserted into all Assessment Form by default.
                   </div>     
                            
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
                            			<div class="form-inline">
                            		<div class="form-group">
                            			<s:textfield onblur="clearEditor()" name="templateName" id="templateName" placeholder="Enter Template Name"  cssClass="form-control showToolTip" onchange="setTemplateName(this.value)"></s:textfield>
					    				<label  id = "tempnameError" class="text-danger"></label>
                            		</div>
                            		<div class="form-group">
                            			<s:select name="formtype" id="formtype" 
										list="#{'0':'Select Form Type','1':'Combine Form','2':'Only Header'}"
										cssClass="form-control chosen-select" ></s:select>
                            		</div>
                            		<div class="form-group" style="width: 5%;">
                            			<s:textfield name="rowsno" id="rowsno" value="1" cssClass="form-control" style="width: 100%;"/>
                            		</div>
                            		<div class="form-group" style="width: 35%;">
                            			<s:textfield maxlength="500" name="hdrcolumn" id="hdrcolumn" placeholder="Enter Column name separate by ,"  cssClass="form-control showToolTip" style="width: 100%;" />
                            		</div>
                            		<div class="form-group">
                            			<h4 style="color: brown;">OR</h4>
                            		</div>
                            		<div class="form-group">
                            			<s:if test="templateNameList.size!=0">
					    				<s:select list="templateNameList" listKey="id" listValue="templateName" title="Select" theme="simple" name="templateName" id="tempListName" headerValue="Select Template" 
                                  		headerKey="0" placeholder="Enter Template Name"  cssClass="form-control showToolTip chosen-select" onchange="setTemplateDetails(this.id)"/>
                                  		</s:if>
                            		</div>
                            		<div class="form-group">
                            			 <s:if test="specializationList.size!=0">
		                                  <s:select list="specializationList" listKey="id" listValue="discipline" title="Select Speclalization" theme="simple" name="specialization" id="specialization" headerValue="Select Specialization" 
		                                  		headerKey="0" placeholder="Enter Template Name"  cssClass="form-control showToolTip chosen-select" /> 
		                                </s:if>
                            		</div>
                            	</div>
                            </div>
                               
                   
					
					<div class="col-lg-4 col-md-4 col-sm-4 hidden">
					  <div class="form-group hidden">
					    <label for="exampleInputEmail1">Template Note</label>
					    <s:textarea maxlength="500" name="templateNote" id="templateNote" placeholder="Enter Template Note, max 500 char"    cssClass="form-control showToolTip" ></s:textarea>
					  </div>
					  
					  <div class="from-inline" style="display: inline-flex;width: 100%;">
					  	<div class="form-group" style="width:100%;">
					  		<label for="exampleInputEmail1">Select Header History</label>
					  		<select name="hdrhisory" id="hdrhisory" class="form-control">
					  			<option value="0">Select Header History</option>
					  		</select>
					  	</div>
					  
					  </div>
					  
					</div>
					
                                                   
                    	</div>
                    </div>
                </div>


<div class="row">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="border-right: 1px dashed #ddd;padding-right: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 subfilters">
			<div class="form-inline">
				<div class="form-group">
					<a href="#" class="btn btn-primary" onclick="opencPopup('AssesmentMasterForms')">Add/Edit Fields</a>
				</div>
				<div class="form-group">
					<a href="#" class="btn btn-primary" onclick="goreferesh()" title="Refresh"><i class="fa fa-refresh"></i></a>
				</div>
			</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding-top: 10px;">
				<div class="col-lg-5 col-md-5 col-xs-12 col-sm-5" style="padding-left: 0px;">
		<div class="panel panel-default">
         	<div class="panel-heading">
             	<h3 class="panel-title"><i class="fa fa-database"></i> Available Fields</h3>
             </div>
           <div id="available1">  
			 </div>
			 <div id="available2">                                       
             <s:if test="fieldList.size!=0">
				<s:select size="20" multiple="true" list="fieldList"  id = "list1" labelposition="left" title="Select Fields" listKey="id"
					listValue="filedname" cssClass="form-control showToolTip" data-toggle="tooltip" />
			</s:if> 
			</div>                      
          </div>
     </div>

 	<div class="col-lg-1 col-md-1 col-xs-12 col-sm-1" style="padding: 114px 0px 0px 0px;">
 		<a type="button" class="btn btn-success"  onclick="addHeading()" title="Add Heading"  style="width:100%;">  <i class="fa fa-plus"></i></a><br /><br />
     	<a type="button" class="btn btn-info"  onclick="removeItem('table1')" title="Add Fields" style="width:100%;">  <i class="fa fa-angle-double-right"></i></a><br /><br />
        <a type="button" class="btn btn-info" onclick="addItem('table1')" title="Remove Fields" style="width:100%;"><i class="fa fa-angle-double-left"></i></a>
   </div>
   
    <div class="col-lg-5 col-md-5 col-xs-12 col-sm-5">
    	<div class="panel panel-default">
        	<div class="panel-heading">
            	<h3 class="panel-title"><i class="fa fa-check-square-o"></i> Selected Fields</h3>
            </div>
             <div id="selected1">			  
			  </div>
			 <div id="selected2">			
            	<select size="20" class="form-control" id = "list2" multiple="multiple"></select> 	
             </div> 
        </div>
   	</div>
   	
   	 <div class="col-lg-1 col-md-1 col-xs-12 col-sm-1" style="padding: 114px 0px 0px 0px;">
     	<a class="btn btn-info"  onclick="listbox_move('list2','up')" style="width:100%;"><i class="fa fa-chevron-up"></i></a><br /><br />
     	<a class="btn btn-info"  onclick="listbox_move('list2','down')" style="width:100%;"><i class="fa fa-chevron-down"></i></a>
     </div>
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 subfilters">
			<div class="form-inline">
				<div class="form-group">
					<s:checkbox id="includeImage" name="includeImage"/> Without Image
				</div>
				<div class="form-group" style="float: right;">
					<s:select name="layout" id="layout" 
				list="#{'0':'1 Col Layout','1':'2 Col Layout','2':'3 Col Layout','3':'Multi Col Layout'}"
				cssClass="form-control chosen-select" ></s:select>
				</div>
			</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding-top: 10px;">
				<div class="panel panel-default">
        	<div class="panel-heading">
            	<h3 class="panel-title"><i class="fa fa-desktop"></i> Set Fields Type & Size</h3>
            </div>
            <div class="panel-body">
            	<table class="table table-hover" id = "table1">
            		 <thead>
                     	<tr>
                        	<th>Field Name</th>
                        	<th>Type</th>
                        	<th>Size</th>
                        	<th>Repeat</th>
                        </tr>
                    </thead>
                 	<tbody>
                 	
                 	</tbody>
                 </table>                               
             </div>
         </div>
			
			</div>
			
			
		</div>
	</div>


	
	
	
</div>
	


<div id="btn1">
 <a class="btn btn-primary pull-right" onclick="setPreview('table1')"><i class="fa fa-hand-o-right"></i> NEXT</a><br />
</div>
<div id="btn2">
 <a class="btn btn-primary pull-right" onclick="editPreview('table1')"><i class="fa fa-hand-o-right"></i> NEXT</a><br />
</div>       
	</div>
	
</div>            
</div>

<!-- Modal -->
<div class="modal fade" id="previewAssment" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<!-- <div class="modal fade" id="previewAssment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" > -->
  <div class="modal-dialog modal-lg" style="height: 700px;">
    <div class="modal-content">
   
   	<s:hidden name="imageData" id="imageData"/>
   	<s:hidden name="lableValueData" id="lableValueData"/>
   	<s:hidden name="typeValueData" id="typeValueData"/>
   	<s:hidden name="sizeValueData" id="sizeValueData"/>
   	<s:hidden name="repeatValueData" id="repeatValueData"/>
   	<s:hidden name="hiddenTemplateName" id="hiddenTemplateName"/>
   	<s:hidden name="tempaction" id="tempaction"/>
   	<s:hidden name="tName" id="tName"/>
   	   <div class="modal-header" >
   	   		<div class="modal-header bg-primary">
	        	<button type="button" class="close" data-dismiss="modal" ><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	   			 <div class = "row"><div class = "col-lg-4"> <h4 class="modal-title" id="myModalLabel">Preview Assessment</h4></div>&nbsp;<div class = "col-lg-6"><h4 id="tname"></h4></div></div>
	      		</div>
	      	</div>
      		<div class="modal-body" style="height: 600px; overflow: scroll;">
      


 <div class="row">
		<div class="col-lg-2 "></div>
		<div class="col-lg-8 " id="tableId">
			<div class="table-responsive">
			<table class="table table-striped table-hover table-condensed" id = "table2">		
		<tbody>	
					
		</tbody>
		</table>
		

</div>
</div>
</div>

<div class="row">
		<div class="col-lg-1 ">
  		  <button type="submit"  class="btn btn-primary"  value="Save" onclick="insertImage()">Save</button>
     	</div>
   		 <div class="col-lg-1 ">
       		<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
    	</div>
</div>

 <div class="row" id="mnipaint">
<%@ include file="/minipaint/editor.jsp"%>
</div>  
</div>      
<div class="modal-footer">
 
</div>

</div>
</div>
</div>
</s:form>
											

											
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

