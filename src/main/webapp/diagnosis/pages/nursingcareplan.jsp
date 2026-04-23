 <!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/nursingcare.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="_assets/newtheme/js/ui.js"></script>
 <%@taglib prefix="s" uri="/struts-tags" %>
<!-- New theme 30 01 2018 -->
<script type="text/javascript" src="diagnosis/js/nursingcarplan.js"></script>

 <form action="savenursingplanIpdDashboard" >
<s:hidden  name="clientid"></s:hidden>
<s:hidden  name="ipdid"></s:hidden>
 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 headingbari marginleftrightzeroi">
<b>Nursing Care Plan </b>
 </div>

  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
 
 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 padingleftrightzeroi">
 <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
    <h4 class="active nursingcareh4"> Assessment  
    
    
    </h4>
    
 

    <!-- Login Form -->
    
      <img   src="cicon/mic_off.png" class="img-responsive micimginursing" onclick="startConverting1(this)" title="Microphone" id="changer">
       
      <small >Subjective  
       
      </small>
      	<textarea class="fadeIn second textareanursingcare" data-toggle="tooltip" rows="6" 
								title="" name="subjectivecare"  id="subjective" placeholder="" ></textarea>
      
      
     <img   src="cicon/mic_off.png" class="img-responsive micimginursingb" onclick="startConverting2(this)" title="Microphone" id="changer">
      
       <small>Objective</small>
      	<textarea class="fadeIn second textareanursingcare" data-toggle="tooltip" rows="6" 
								title="" name="objectivecare"  id="objective" placeholder="" ></textarea>
      
    

    <!-- Remind Passowrd -->
    <div id="">
    <br>
    </div>

  </div>
</div>
 </div>
 
 
 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 padingleftrightzeroi">
 <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
       <h4 class="active nursingcareh4"> Diagnosis </h4>
    
 

    <!-- Login Form -->
     
    
       
      <!--  <select id="selectbasic" name="selectbasic" class="selectpicker form-control fadeIn second nursingplanselectinput">
      <option value="2">Select Diagnosis</option>
      <option value="1">1 Needs help of 1 person</option>
      <option value="0">0 Needs help of 2+ people</option>
    </select> -->
    
    <s:select list="nursingdiagnosislist" onchange="selectplanningcare(this.value)" listKey="id" listValue="name" name="nursingdiagnosis" title="select diagnosis"
										cssClass="form-control newslectad"  > </s:select>
										
									
     <div style="height: 15px;"></div>
     
     	 <img   src="cicon/mic_off.png" class="img-responsive micimginursingc" onclick="startConverting3(this)" title="Microphone" id="changer">
     	
     	<textarea class="fadeIn second textareanursingcare" data-toggle="tooltip" rows="10" 
								title="" name="diagnosiscare"  id="diagnosiscare" placeholder="" ></textarea>
 

    <!-- Remind Passowrd -->
    <div id="">
    <br>
    </div>

  </div>
</div>
 </div>
 
 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 padingleftrightzeroi hidden">
 <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
        <h4 class="active nursingcareh4"> Inference </h4>
    
 

    <!-- Login Form -->
 
   <img   src="cicon/mic_off.png" class="img-responsive micimginursingd" onclick="startConverting4(this)" title="Microphone" id="changer">
     	<textarea class="fadeIn second textareanursingcare" data-toggle="tooltip" rows="14" 
								title="" name="inferencecare"  id="inferencecare" placeholder="" ></textarea>
      
      
 

    <!-- Remind Passowrd -->
    <div id="">
    <br>
    </div>

  </div>
</div>
 </div>
 
 
 
 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 padingleftrightzeroi">
  
 <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
      <h4 class="active nursingcareh4"> Planning </h4>
    
 

    <!-- Login Form -->
 
    
       <div id="tdplanning">
        <select id="selectbasic" name="selectbasic" class="form-control fadeIn second nursingplanselectinput">
     <%--  <option value="2">Select Planning</option>
      <option value="1">1 Needs help of 1 person</option>
      <option value="0">0 Needs help of 2+ people</option> --%>
    </select> 
        </div>
       <div style="height: 15px;"></div>
     
    	<img   src="cicon/mic_off.png" class="img-responsive micimginursinge" onclick="startConverting5(this)" title="Microphone" id="changer">  	
    	 
    	<textarea class="fadeIn second textareanursingcare" data-toggle="tooltip" rows="10" 
								title="" name="planningcare"  id="planningcare" placeholder="" ></textarea>
 

    <!-- Remind Passowrd -->
    <div id="">
    <br>
    </div>

  </div>
</div>
 </div>
 
 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 padingleftrightzeroi">
 <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
    <h4 class="active nursingcareh4"> Intervention </h4>
    
 

    <!-- Login Form -->
 
         <div id="tdintervention">
       <select id="selectbasic" name="selectbasic" class="form-control fadeIn second nursingplanselectinput">
     <!--  <option value="2">Select Intervention</option>
      <option value="1">1 Needs help of 1 person</option>
      <option value="0">0 Needs help of 2+ people</option> -->
    </select>
     </div>
       <div style="height: 15px;"></div>
       
    	
    	
    	 
    	 <img   src="cicon/mic_off.png" class="img-responsive micimginursinge" onclick="startConverting6(this)" title="Microphone" id="changer">
    	    	
    	<textarea class="fadeIn second textareanursingcare" data-toggle="tooltip" rows="10" 
								title="" name="interventioncare"  id="interventioncare" placeholder="" ></textarea>
      
      
 

    <!-- Remind Passowrd -->
    <div id="">
    <br>
    </div>

  </div>
</div>
 </div>
 
 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 padingleftrightzeroi hidden">
 <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
    <h4 class="active nursingcareh4"> Rationale </h4>
    
 

    <!-- Login Form -->
 
       <%--  
       <select id="selectbasic" name="selectbasic" class="form-control fadeIn second nursingplanselectinput">
      <!-- <option value="2">Select Rationale</option>
      <option value="1">1 Needs help of 1 person</option>
      <option value="0">0 Needs help of 2+ people</option> -->
    </select> --%>
      <s:select list="rationalelist" listKey="id" listValue="name" name="nursingrationale" title="select rationale" onchange="selectrationalenotes(this.value)"
										cssClass="form-control newslectad"  > </s:select>
       <div style="height: 15px;"></div>
       
     	 
     	 
     	 <img   src="cicon/mic_off.png" class="img-responsive micimginursinge" onclick="startConverting7(this)" title="Microphone" id="changer">
     	
     	<textarea class="fadeIn second textareanursingcare" data-toggle="tooltip" rows="10" 
								title="" name="rationalecare"  id="rationalecare" placeholder="" ></textarea>
      
      
 

    <!-- Remind Passowrd -->
    <div id="">
    <br>
    </div>

  </div>
</div>
 </div>
 
 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 padingleftrightzeroi">
 <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
    <h4 class="active nursingcareh4"> Evaluation </h4>
    
 

    <!-- Login Form -->
  
   
   
   
   <img   src="cicon/mic_off.png" class="img-responsive micimginursing" onclick="startConverting8(this)" title="Microphone" id="changer">
  
  	<textarea class="fadeIn second textareanursingcare" data-toggle="tooltip" rows="14" 
								title="" name="evaluationcare"  id="evaluationcare" placeholder="" ></textarea>

    <!-- Remind Passowrd -->
    <div id="">
    <br>
    </div>

  </div>
</div>
 </div>
 

</div>

  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
  
  
   <!--  <input onClick="printnursingplancare('printnursingplancare_div');" type="submit" class="fadeIn fourth  pull-right" value="Print"> -->
  <input type="submit" class="fadeIn fourth  pull-right" value="Save">
  <a class="btn btn-primary" onclick="submitintoEmr()">Insert into EMR</a>&nbsp;&nbsp;&nbsp;



<div id="printnursingplancare_div" class="hidden">

<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

											<h4 class="colorwhitprint">Abnormal Uterine Bleeding</h4>

									</div>
								</div>

<table class="table table-striped table-responsive tableborderprint tablewidthipdreportsprint" >
				<thead>
					 
					 
					<th width="14.28%" class="tableborderprint"><b>Assessment</b></th>
					<th width="14.28%" class="tableborderprint"><b>Diagnosis</b></th>
					<th width="14.28%" class=" tableborderprint"><b>Inference</b></th>
					<th width="14.28%" class="tableborderprint"><b>Planning</b></th>
					<th width="14.28%" class=" tableborderprint"><b>Intervention</b></th>
					<th width="14.28%" class=" tableborderprint"><b>Rationale</b></th>
					 <th width="14.28%" class=" tableborderprint"><b>Avaluation</b></th>
				</thead>
				
					<tbody >
					 
								<tr>
								 
								 <td align="justify" class="tableborderprint">
							<h5><b>Subjective</b></h5>	It is a long established
fact that a reader will be
distracted by the
readable content of a
page when looking at
its layout. The point of
using Lorem Ipsum is
that it has a more-orless
normal distribution
of letters, as opposed
to using 'Content here,
content here', making it


							<h5><b>Objective</b></h5>	
							It is a long established
fact that a reader will be
distracted by the
readable content of a
page when looking at
its layout. The point of
using Lorem Ipsum is
that it has a more-orless
normal distribution
of letters, as opposed
to using 'Content here,
content here', making it
								 </td>
										 <td align="justify" class="tableborderprint">
								 It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
								 </td>
								
							 
								 <td align="justify" class="tableborderprint">
								 It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
								 </td>
								<td class=" tableborderprint">
								It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
								</td>
								 <td align="justify" class="tableborderprint">
								 It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
								 </td>
									 <td align="justify" class="tableborderprint">
								 It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
								 </td>
								 	 <td align="justify" class="tableborderprint">
								 It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
								 </td>
								
								 
								
							 
				</tbody>
				
				
</table>


</div>
  
  </div>

 </form>
 
 
 
 

</body></html>

	
 