<%@taglib uri="/struts-tags" prefix="s"%>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Assessment Forms</a></li>
			<li class="active">Modify Assessment Form</li>
		</ol>
	</div>
</div>


<div id="wrapper"> 
<div id="page-wrapper">
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel-heading">                                
				<div class="col-lg-2 col-md-3"></div>
				<div class="col-lg-3 col-md-2">
					<h3><i class="fa fa-file-text-o"></i> Template Name </h3>
				</div>
				<div class="col-lg-4 col-md-2">
					<s:if test="templateNameList.size!=0">
					<h3><s:select list="templateNameList" listKey="id" listValue="templateName" title="Select" theme="simple" name="templateName" id="templateName" 
						placeholder="Enter Template Name"  cssClass="form-control showToolTip " onchange="setTemplateDetails(this.value)"></s:select></h3>
					<label  id = "tempnameError" class="text-danger"></label>
					</s:if>
				</div>                          
			</div>
		</div>
</div>

<hr/>
<br/>

<div class="row">
	<div class="col-lg-2">
  		<div class="panel panel-default">
         	<div class="panel-heading">
             	<h3 class="panel-title"><i class="fa fa-database"></i> Existing Fields</h3>
             </div>                                                        
             <s:if test="%{#templateFieldList != 'null'}">
				<s:select size="20" multiple="true" list="templateFieldList"  id = "list1" labelposition="left" title="Select Template" listKey="id"
					headerKey="0" headerValue="Select Template" listValue="filedname" cssClass="form-control showToolTip" data-toggle="tooltip"  />
			 </s:if>                     
          </div>
     </div>
     
      	<div class="col-lg-1" style="padding-top:134px;">
 		<button class="btn btn-info"  style="font-size: 20px; height: 40px; width: 50px;" onclick="addHeading()"><i class="fa fa-plus-square "></i> </button><br /><br />
     	<button class="btn btn-info"  style="font-size: 20px; height: 40px; width: 50px;" onclick="removeItem('table1')"><i class="fa fa-plus-square "></i></button><br /><br />
        <button class="btn btn-info"  style="font-size: 20px; height: 40px; width: 50px;" onclick="addItem('table1')"><i class="fa fa-minus-square"></i> </button>
   </div>
   
    <div class="col-lg-2">
    	<div class="panel panel-default">
        	<div class="panel-heading">
            	<h3 class="panel-title"><i class="fa fa-check-square-o"></i> Selected Fields</h3>
            </div>
            	<select size="20" class="form-control" id = "list2" multiple="multiple"></select> 	
        </div>
   	</div>
   	
   	 <div class="col-lg-6">
      	<div class="panel panel-default">
        	<div class="panel-heading">
            	<h3 class="panel-title"><i class="fa fa-desktop"></i> Set Feilds Type & Size</h3>
            </div>
            <div class="panel-body">
            	<table class="table table-hover" id = "table1">
            		 <thead>
                     	<tr>
                        	<th>Feild Name</th>
                       	 	<th>Type</th>
                        	<th>Size</th>
                        </tr>
                    </thead>
                 	<tbody>
                 	</tbody>
                 </table>                               
             </div>
         </div>
      </div>
</div>

 <button class="btn btn-info pull-right"  style="font-size: 20px; height: 40px; width: 50px;" onclick="setPreview('table1')"><i class="fa fa-hand-o-right"></i> NEXT</button><br />
        
	</div>
</div>            
</div>
