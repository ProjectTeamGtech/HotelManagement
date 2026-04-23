 <%@ taglib uri="/struts-tags" prefix="s" %>  
 
 
  <%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<style>
         
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
			    width: 10% !important;
		}
		.martop10{
			margin-top:10px;
		}
		h5, .h5 {
		    font-size: 14px !important;
		}
		.content1 {
     height: 285px;
  overflow-y: scroll;
}
.content1::-webkit-scrollbar { 
  /* This is the magic bit */
  display: none;
}
     </style>
     
     
  
     
     
 				<div class="modal-body" >
 						<s:form theme="simple">
 						
 						<input type="hidden" name="invstautoid" id="invstautoid" value="0"/>
 						<div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                          	Template Name <input type="text" name="templatetext" id="templatetext" placeholder="Enter Template Name"
                          	class="form-control">
                          </div>
                         </div>
                          
   						 <div class="row " style="display: none;">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                              <form>
                                  <div class="form-group">
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
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4 marleft9">
                                      <div class="form-group">
                                          <b>Patient Bar Code</b>
                                          <p>C31128A00175</p>
                                      </div>
                                  </div>


                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Date / Time</b>
                                          <p id="invstdatetimeid">21/11/2015 13:58 PM</p>
                                      </div>
                                  </div>

                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Doctor Incharge</b>
                                          <p id="invstpriscdoctornameid">Dr.Abhishek Pandey</p>
                                      </div>
                                  </div>
                                  
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                            <%--   <s:select cssStyle="width:140px;" cssClass="form-control" id="invstreporttype" name="invstreporttype"
                                              list="invstReportTypeList" listKey="id" listValue="name"
                                              headerKey="0" headerValue="Select report Type"
                                              /> --%>
                                              <%LoginInfo l = LoginHelper.getLoginInfo(request); %>
                                               <% if(l.getUserType()==2){%>
												  <s:select id="jobtitle" name="jobtitle" list="jobTitleList"
													title="Select Job title" 
													cssClass="form-control showToolTip" data-toggle="tooltip" />
												<%}else{ %>
													<s:select id="jobtitle" name="jobtitle" list="jobTitleList"
													title="Select Job title"  disabled="true"
													cssClass="form-control showToolTip" data-toggle="tooltip" />
												<% }%>
                                              
                                              	<s:select name="invstreporttype" id="invstreporttype" style="display: none;"
													list="#{'Numerical':'Numerical','Writeup':'Writeup','Text':'Text'}"
													cssClass="form-control" onchange=""></s:select>
                                              
                                         </div>
                                        </div>
                              </form>
                          </div>
                      </div>

                      <div class="row">
                     
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martpomin15">
                              <br />
                              <table class="table" cellspacing="0" width="100%">
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
                                              <s:select onchange="showNameData(this.value)" name="invsttype" id="invsttype" cssClass="form-control chosen-select"
                                              list="invsTypeList" listKey="id" listValue="name" 
                                              headerKey="0" headerValue="Select Type"
                                              />
                                                
                                              
                                          </td>
                                          <td id="grouptd" style="display: none;">
                                              <select onchange="showNameData(this.value)" class="form-control" name="invstgroup" id="invstgroup">
                                                  <option value="0" >Select Group</option>
                                                  
                                              </select>
                                          </td>
                                          <td>
                                          	
                                             
                                              
                                              <div id="invstnametd" style="height: 100px;overflow: scroll;">
                                              		 <select onchange="showspecimen(this.value)" class="form-control" name="invstname" id="invstname">
                                                 <option value="0" >Select Name</option>
                                              </select>
                                              </div>
                                          </td>
                                          
                                        <td>
                                         <div class="">
		                                      <a href="#" type="button" onclick="addTempInvestigaation()" title="Add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
		                                      
		                                     
		
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
                             
                              <div class="row inaddbtn">

                                 
                              </div>

                             
                          </div>
                          
                          
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 content1 tableheight1">
                              <table class="table table-bordered" cellspacing="0" width="100%">
                                  <thead>
                                      <tr class="tableback">

                                          <th class="invet1">Investigation</th>
                                        <!--   <th class="invest9">Invest Group</th> -->
                                          <th class="invet2">Test Name</th>
                                          <th class="invet3">Sample Specimen</th>
                                          <th class="invet4">Report Type</th>
                                          <th class="invet5">Units</th>
                                          <th class="invet6">Delete</th>

                                      </tr>
                                  </thead>
                                  <tbody id="invstlistid">
                                      <tr>

                                  </tbody>
                              </table>

                          </div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martop10 hidden">
                             
                              <div class="col-lg-6 col-md-6 col-xs-6 martopmin10 marleft9">
                                  <form>
                                      <div class="form-group">
                                          <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                              <p for="exampleInputEmail1" class="marleft9">Advice</p>
                                          </div>
                                          <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" id="ilanguage1" value="option1" checked> English
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" id="ilanguage2" value="option2"> Regional
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" id="ilanguage3" value="option3"> Hindi
                                              </label>
                                          </div>


                                      </div>

                                  </form>
                                  <form class="form-horizontal">

                                      <textarea id="advoice"  name="advoice" class="form-control" rows="3"></textarea>

                                  </form>
                              </div>
                              <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">


                              </div>
                          </div>

                      </div>
                      
                      </s:form>
                     </div> 
                      
                   
                  
                       <script>
			             $(function() {
							  $('.tableheight1').slimScroll({
							   		height : '150px',
							   		railVisible: true,
									alwaysVisible: true
							  });
			 				});
             		</script>
                      
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
                     
                      
                     