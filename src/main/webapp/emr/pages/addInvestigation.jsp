 <%@ taglib uri="/struts-tags" prefix="s" %>  
 
 
  <%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

 <!-- project main css files -->
      <!--   <link rel="stylesheet" href="_assets/newtheme/css/main.css"> -->

<link rel="stylesheet" href="pharmacy/searchexport/dataTables.bootstrap.css">
<link rel="stylesheet" href="pharmacy/searchexport/buttons.bootstrap.css">
<%LoginInfo investloginfo = LoginHelper.getLoginInfo(request);%>
<style>
         .form-control {
    border: 1px solid rgba(0, 0, 0, 0.1) !important;
   background-color: #ffffff !important;
}
         .marleft14 {
             margin-left: 5px;
         }
         .priswidth {
             width: 13%;
         }
         .note {
             width: 25%;
         }
         /*.modal-lg {
             width: 1260px;
         }*/
         .manbtn {
             margin-left: -16px;
             margin-right: -13px;
         }

         .widset {
             width: 130%;
         }

         .marleft6 {
             margin-left: 6px;
         }

         .border {
             border: 1px solid;
             padding: 3px 25px 10px 10px;
         }

         .borderlanguage {
             border: 1px solid;
             padding: 3px 25px 8px 10px;
         }

        

         .thfont {
             font-weight: normal;
         }

         .marleft17 {
             margin-left: 17px;
         }
         .open{
             color:red;
         }
         .deliverd {
             color: green;
         }
         .process {
             color: blue;
         }
         .payrece {
           
		    margin-top: -18px;
         }
         .backicon {
             background-color: #C0C0C0;
             border: 1px solid #3D3D3D;
             padding: 5px 3px 2px 5px;
             color: #000;
             width: 100%;
         }
         .wrapperfixed {
             display: inline-block;
             margin-top: 35px;
             padding: 0px 0px 0px;
             width: 100%;
             height: 600px;
         }
        
         
         .pname{
             color:#fff;
         }
         .marleft9 {
             margin-left: -9px;
         }
         .martopmin10 {
             margin-top: -10px;
         }
         .martpomin15 {
             margin-top: -15px;
         }
         .follow {
             width: 42px;
    			margin-left: -16px;
         }
         .tableback {
    background-color: #4E7894;
    color: rgb(255, 255, 255);
    font-style: normal;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 0px 0px 0px 1px;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 0px solid #ddd;
    border-bottom: 1px solid #ddd !important;
}
.form-control {
    padding: 0px 4px !important;
}
        .martop6{
            margin-top: 6px;
        }
        .marleftmin6{
        margin-left: -6px !important;
        }
        .investtypr{
        width: 50% !important;
        }
        .intname{
            width: 40%;
        }
       
		.invet1{
			       width: 33% !important;
		}
		.invet2{
			    width: 38% !important;
		}
		.invet3{
			    width: 14% !important;
		}
		.invet4{
			    width: 14% !important;
		}
		.martop10{
			margin-top:10px;
		}
		h5, .h5 {
		    font-size: 14px !important;
		}
		.backshdow{
			background-color:rgba(92, 184, 92, 0.13);
		    padding-top: 10px;
		    border: 2px solid #5cb85c;
		}
		.tab-content .tab-pane {
    padding: 6px 0px 0px 0px;
}

     </style>
     
      <script>
     
     $(document).ready(function() {
         
 		$("#invstDate").datepicker({

 			dateFormat : 'dd-mm-yy',
 			yearRange : yearrange,
 			minDate : '30-12-1880',
 			changeMonth : true,
 			changeYear : true

 		});

 		$("#toDate").datepicker({

 			dateFormat : 'dd-mm-yy',
 			yearRange : yearrange,
 			minDate : '30-12-1880',
 			changeMonth : true,
 			changeYear : true

 		});

 	});
     
     </script>
     
     
  
     
     
 				<div class="modal-body" style="  overflow-x: hidden;  overflow-y:scroll;
    width: 100%;
    height: 450px;" >
 						<s:form theme="simple">
 						
 						<input type="hidden" name="invstautoid" id="invstautoid" value="0"/>
   						 <div class=" ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;">
                              <form>
                                  <div class="form-group hidden">
                                      <label class="radio-inline disabled">
                                          <input type="radio" name="inlineRadioOptions" id="ipaymode1" value="option1" disabled> Pre-Pay
                                      </label>
                                      <label class="radio-inline disabled">
                                          <input type="radio" name="inlineRadioOptions" id="ipaymode2" value="option2" disabled> Post-Pay
                                      </label>
                                      <label class="radio-inline">
                                          <input type="radio" name="inlineRadioOptions" id="ipaymode3" value="option3" checked> Other
                                      </label>

                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-12 col-sm-12 marleft9">
                                      <div class="form-group">
                                          <b>Patient Bar Code</b>
                                          <p>C31128A00175</p>
                                      </div>
                                  </div>


                                  <div class="col-lg-3 col-md-4 col-xs-12 col-sm-12">
                                      <div class="form-group">
                                          <b>Date / Time</b>
                                          <p id="invstdatetimeid">21/11/2015 13:58 PM</p>
                                      </div>
                                  </div>
                                 <%if(investloginfo.isBams1()){ %>
                                  <div class="col-lg-3 col-md-4 col-xs-12 col-sm-12">
                                     <div class="form-group">
                                          <b>Select Date</b>
                                      <s:textfield readonly="true" name="fromDate" id="invstDate"
									            cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
                                      </div>
                                     
                                  </div>
                                  <%} else{%>
                                  <input type="hidden" id="invstDate">
                  					<%} %> 

                                  <div class="col-lg-3 col-md-4 col-xs-12 col-sm-12">
                                      <div class="form-group">
                                          <b>Doctor Incharge</b>
                                          <p id="invstpriscdoctornameid"></p>
                                      </div>
                                  </div>
                                  
                                  <div class="col-lg-3 col-md-4 col-xs-12 col-sm-12">
                                      <div class="form-group">
                                            <%--   <s:select cssStyle="width:140px;" cssClass="form-control" id="invstreporttype" name="invstreporttype"
                                              list="invstReportTypeList" listKey="id" listValue="name"
                                              headerKey="0" headerValue="Select report Type"
                                              /> --%>
                                           <%--    <%LoginInfo l = LoginHelper.getLoginInfo(request); %>
                                               <% if(l.getUserType()==2){%>
												  <s:select id="jobtitle" name="jobtitle" list="jobTitleList"
													title="Select Job title" 
													cssClass="form-control showToolTip notranslate" data-toggle="tooltip" />
												<%}else{ %>
													<s:select id="jobtitle" name="jobtitle" list="jobTitleList"
													title="Select Job title"  disabled="true"
													cssClass="form-control showToolTip notranslate" data-toggle="tooltip" />
												<% }%>
                                               --%>
                                               <input type="hidden" id="jobtitle" name="jobtitle" value="<%=investloginfo.getJobTitle() %>"/>
                                              	<s:select name="invstreporttype" id="invstreporttype" style="display: none;"
													list="#{'Numerical':'Numerical','Writeup':'Writeup','Text':'Text','Hybreed':'Hybreed'}"
													cssClass="form-control notranslate" onchange=""></s:select>
                                              
                                         </div>
                                        </div>

                              </form>
                          </div>
                      </div>

                      <div class="row">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martpomin15" style="margin-top: 3px;">
	                          <div class="col-lg-5 col-md-5 col-xs-12" style="padding:0px;">
	                          		<div class="col-lg-12 col-md-12 col-xs-12 backshdow">
	                          		
	                          		  <div class="form-group">
										    <label for="email">Investigation Package</label> <span style="float: right;display:none;"><div class="form-group"><label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0" style="color: #cc6161;"><input type="checkbox" id="enable-show"><i></i> Urgent</label></div></span>
										    <div id="pkginvtypediv" class="notranslate">
										    	 <s:select onchange="showPackageInvstTypeNameData(this.value)"  name="invpkg" id="invpkg" cssClass="form-control chosen-select  notranslate"
                                              list="pkgsList" listKey="id" listValue="name" 
                                              headerKey="0" headerValue="Select Package" tabindex="1"
                                              />
                                              </div>
										  </div>
	                          		
	                          			  <div class="form-group hidden">
										    <label for="email">Investigation Section </label> <span style="float: right;display:none;"><div class="form-group"><label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0" style="color: #cc6161;"><input type="checkbox" id="enable-show"><i></i> Urgent</label></div></span>
										    	 <s:select onchange="showInvstTypeNameData(this.value)" name="invsection" id="invsection" cssClass="form-control chosen-select  notranslate"
                                              list="invSectionList" listKey="id" listValue="name" 
                                              headerKey="0" headerValue="Select Section" tabindex="1"
                                              />
										  </div>
	                          			  <div class="form-group">
										    <label for="email">Investigation Type</label>
										    
										      <div id="invtypediv" class="notranslate">
										    	 <s:select onchange="showNameData(this.value)" name="invsttype" id="invsttype" cssClass="form-control chosen-select  notranslate"
                                              list="invsTypeList" listKey="id" listValue="name" 
                                              headerKey="0" headerValue="Select Type" tabindex="1"
                                              />
                                             </div>
										  </div>
										  <div class="form-group">
										   <!-- <label for="email">Test Name &nbsp;&nbsp;<a class="hidden" href="#" onclick="openhiddendiv('hidedivnew')"><i class="fa fa-plus-square fa-2x"></i></a></label>
										  <div class="form-inline" id="hidedivnew" style="display: none;padding: 3px 0px 3px 4px;background-color: #5f5f5f82;">
										  	<div class="form-group">
										        <input type="text" id="newinvsname" placeholder="enter test name" class="form-control" />
										   </div>
										   <div class="form-group">
										        <input type="button" class="btn btn-primary" value="Save New" onclick="savenewinvsName()" />
										   </div>
										  </div> -->
										   
										    
										  <div class="six columns" >
								             <article>
								               <input id="search" name="search" class="form-control" placeholder="search here" type="text" data-list=".default_list" autocomplete="off">
								               <div id="scrolltable" style="overflow:scroll; width: 100%;height: 120px;">
								               	<ul class="vertical default_list" id="invstnametd" style="padding: 0px;list-style: none;">
								               </ul>
								               </div>
								             </article>
								           </div>
										   
										    	
										  </div>
										  <div class="form-group">
										  	 <a href="#" type="button" onclick="addTempInvestigaation()" title="Add" class="btn btn-success" style="width:100%;" tabindex="3"><i class="fa fa-plus"></i> Add</a>
										  </div>
	                          				<table class="table hidden" cellspacing="0" width="100%">
                                  <thead>
                                      <tr>
                                          <th class="thfont intest hidden">Test Code</th>
                                          <th class="thfont investtypr"><b>Investigation Type</b></th>
                                          <!-- <th class="thfont investtypr">Investigation Group</th> -->
                                          <th class="thfont intname"><b>Test Name</b></th>
                                        <!--   <th class="thfont inspeci">Sample Specimen</th>
                                          <th class="thfont inreport">Report Type</th>
                                          <th class="thfont">Units</th> -->
											<th></th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr>
                                          <td class="hidden">
                                              <select class="form-control" id="invstcode" name="invstcode">
                                                  <option>lab434</option>
                                                  <option>lab433</option>
                                                  <option>lab434</option>
                                                  <option>lab427</option>
                                                  <option>lab434</option>
                                                  <option>lab421</option>
                                                  <option>lab434</option>
                                                  <option>lab423</option>
                                                  <option>lab434</option>
                                              </select>
                                          </td>
                                          <td>
                                             
                                                
                                              
                                          </td>
                                          <td id="grouptd" style="display: none;">
                                              <select onchange="showNameData(this.value)" class="form-control" name="invstgroup" id="invstgroup">
                                                  <option value="0" >Select Group</option>
                                                  
                                              </select>
                                          </td>
                                          <td>
                                          	
                                             
                                              
                                              
                                          </td>
                                          
                                        <td>
                                         <div class="">
		                                     
		                                      
		                                     
		
		                                  </div>
                                        </td>
                                       
                                       
                                           <td  id="specimentd" style="display: none;">
                                          <input type="hidden" name="normvalue" id="normvalue">
                                              <select onchange="showreport(this.value)" class="form-control" name="specimen" id="specimen">
                                                 <option value="0" >Select Sample Specimen</option>

                                              </select>
                                          </td> 
                                          
                                         
                                       
                                          <td id="unittd" style="display: none;">
                                              <s:select cssClass="form-control" name="invstUnit" id="invstUnit"
                                              	list="invstUnitList" listKey="id" listValue="name"
                                              	headerKey="0" headerValue="Select Unit"
                                              />
                                              
                                          </td>
                                          
                                          
                                         

                                      </tr>

                                  </tbody>
                              </table>
	                          		</div>
	                          		<div class="col-lg-12 col-md-12 col-xs-12 martopmin10" style="padding: 0px;margin-top:5px;">
                                  <form>
                                      <div class="form-group">
                                          <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                              <p for="exampleInputEmail1" class="marleft9">Advice</p>
                                          </div>
                                          <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" id="language1" value="option1" checked> English
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" id="language2" value="option2"> Regional
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" id="language3" value="option3"> Hindi
                                              </label>
                                          </div>


                                      </div>

                                  </form>
                                  <form class="form-horizontal">

                                      <textarea id="advoice"  name="advoice" class="form-control" rows="3"></textarea>

                                  </form>
                                  
                                 <!--  <div class="col-lg-12 col-md-12 col-xs-12" style="margin-top:15px;">
										<textarea id="transliterateTextarea" class="form-control"></textarea>
									  </div> -->
                              </div>
	                          </div>
	                          <div class="col-lg-7 col-md-7 col-xs-12" style="padding:0px;">
	                          		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 tableheight1" style="padding-right:0px;width: 100% !important;height: 428px;
    overflow: auto!important;">
                              <table class="table table-bordered" cellspacing="0" width="100%">
                                  <thead>
                                      <tr class="tableback">

                                          <th class="invet1" style="width: 30% !important;">Investigation</th>
                                        <!--   <th class="invest9">Invest Group</th> -->
                                        <%if(!investloginfo.isMarkhosp()){ %>
                                          <th class="invet2" style="width: 50% !important;">Test Name</th>
                                           <s:if test="isConsultationForm==1">
                                          	<th>Value</th>
                                          </s:if>
                                         <%} %>
                                          <!--
                                          <th class="invet3">Sample Specimen</th>
                                          <th class="invet4">Report Type</th>
                                          <th class="invet5">Units</th>
                                          -->
                                          
                                         
                                          <th class="invet6">Delete</th>

                                      </tr>
                                  </thead>
                                  <tbody id="invstlistid" class="notranslate">
                                      <tr>
                                      
                                      </tr>

                                  </tbody>
                              </table>

                          </div>
	                          </div>
                              <br />
                              
                               
                       
                           <!--  save btn -->
                             
                              <div class="row inaddbtn">

                                 
                              </div>

                             
                          </div>
                          
                          

                      </div>
                      
                      </s:form>
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
                      var options = [];

                      $( '.dropdown-menu a' ).on( 'click', function( event ) {

                         var $target = $( event.currentTarget ),
                             val = $target.attr( 'data-value' ),
                             $inp = $target.find( 'input' ),
                             idx;

                         if ( ( idx = options.indexOf( val ) ) > -1 ) {
                            options.splice( idx, 1 );
                            setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
                         } else {
                            options.push( val );
                            setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
                         }

                         $( event.target ).blur();
                            
                         console.log( options );
                         return false;
                      });
                      
                      
					                      
                      </script>
                      <script>
             $(function() {
				  /* $('.tableheight1').slimScroll({
				   		height : '420px',
				   		railVisible: true,
						alwaysVisible: true
				  }); */
				  $('.sctrolname').slimScroll({
				   		height : '170px',
				   		railVisible: true,
						alwaysVisible: true
				  });
 				});
 				 
             </script>
             
             
                 <!-- JS -->
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
  <script src="inventory/js/searchtext/javascripts/initializers.js"></script>
  <!-- JS ends -->
  
     <script>
    	$(function() {
	    $('#scrolltable').slimScroll({
	   		height : '120px',
	   		railVisible: true,
			alwaysVisible: true
	  });
	
	 });
    </script>
    
  
                      
                     