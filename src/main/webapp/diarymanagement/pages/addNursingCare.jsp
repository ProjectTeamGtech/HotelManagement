  <%@ taglib uri="/struts-tags" prefix="s" %>
  
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
                 font-weight: normal !important;
    			color: #000;
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
               
			    margin-top: -18px !important;
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
                 width: 37px;
    			margin-left: -11px;
         }
         .fodays {
		    width: 58px;
		    margin-left: -11px;
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
        .dayw{
        margin-left: -6px !important;
    	margin-top: 6px;
        }
        .dosen{
            margin-top: 11px;
    margin-left: -51px;
        }
        .martop10{
         margin-top:10px;
        }
        h5, .h5 {
		    font-size: 14px !important;
		}
        .pdate{
    width: 10% !important;
}
.pnamewidth{
    width: 17% !important;
}
.setyerx{
    margin-top: 8px;
}
     </style>
     
     
     
                  <s:form action="saveNursing" name="mynursingform" theme="simple">
                  <div class="">
                    
      					<s:hidden name="clientid" id="nclientid"></s:hidden>
      					<s:hidden name="ipdid" id="nipdid"></s:hidden>
      					<s:hidden name="conditionid" id="nconditionid"></s:hidden>
      					<s:hidden name="practitionerid" id="npractitionerid"></s:hidden>
                      <div class="row">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                              <form>
                              		
                                  <div class="form-group prepaymd hidden">
                                      <label class="radio-inline disabled">
                                          <input type="radio" name="paymode" id="paymode1" value="option1" disabled> Pre-Pay
                                      </label>
                                      <label class="radio-inline disabled">
                                          <input type="radio" name="paymode" id="paymode2" value="option2" disabled> Post-Pay
                                      </label>
                                      <label class="radio-inline">
                                          <input type="radio" name="paymode" id="paymode3" value="option3" checked> Other
                                      </label>

                                  </div>
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3 marleft9">
                                      <div class="form-group">
                                          <label>Patient Bar Code</label>
                                          <p>C31128A00175</p>
                                      </div>
                                  </div>
                                  
                                  
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
                                      <div class="form-group">
                                          <label>Date / Time</label>
                                          <p id="ppdatetime">21/11/2015 13:58 PM</p>
                                      </div>
                                  </div>
                                 
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
                                      <div class="form-group">
                                          <label>Doctor Incharge</label>
                                          <p id="nursingdoctornameid">Dr.Abhishek Pandey</p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
                                      <div class="form-group">
                                          <label>Repeat Care</label>
                                           <%-- <select class="form-control">
                                                  <option value="25/11/2015">25/11/2015</option>
                                                  <option value="25/11/2015">25/11/2015</option>
                                              </select> --%>
                                             
                                             <div id="rpeatdivajax11">
	                                              <s:select onchange="editparentprisc(this.value)" name="repeatdate" id="repeatdate" list="parentPriscList"
	                                              listKey="id" listValue="date" cssClass="form-control chosen-select"
	                                              headerKey="0" headerValue="Select Date" />
                                              </div>
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
                                         <!--  <th class="thfont">Type</th> -->
                                          <th class="thfont">Category</th>
                                          <!-- <th class="thfont">Strength</th> -->
                                          <th class="thfont">Taskname</th>
                                          <th class="thfont">Frequency</th>
                                          <th class="thfont">Duration</th>
                                         <!--<th class="thfont">Notes</th> 
                                         --><th class="thfont"></th> 

                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr>
                                    
                                          <!--<td style="width: 10%">
                                          	<s:select cssClass="form-control showToolTip chosen-select" name="prisctype" id="prisctype" list="mdicneTypeList" listKey="name" listValue="name" headerKey="0" headerValue="Select Type"/>
                                             <%--  <select class="form-control" name="prisctype" id="prisctype">
                                                  <option value="Tablet">Tablet</option>
                                                  <option value="Capsul">Capsul</option>
                                                  <option value="Oinment">Oinment</option>
                                                  <optionvalue="Liquied">Liquied</option>
                                                  <option value="Injection">Injection</option> --%>
                                                
                                              </select>
                                          </td>
                                          --><td style="width: 30%">
                                             	<s:select onchange="setNursingtask(this.value)" cssClass="form-control showToolTip chosen-select" name="nursingtype_id" id="nursingtype_id" list="nursingcategorylist" listKey="id" listValue="name" headerKey="0" headerValue="Select Category"/>
                                          </td>
                                          <td id="nursingdiv" style="width: 15%" >
                                        
                                           <%--  <select class="form-control showToolTip chosen" id="mdicicnename" name="mdicicnename"><option value="0">Select Medicine</option></select> --%>
                                           	<s:select cssClass="form-control showToolTip chosen-select" name="taskname" id="taskname" list="nursingdetails"
                                           listKey="taskname" listValue="taskname" headerKey="0" headerValue="Select Taskname"	>
                                           	
                                           	</s:select>
                                           
                                          </td>
                                          <td style="width:5%;display:none;">
                                          	<!--  <input type="text" class="form-control" id="priscfreq" name="priscfreq"> -->
                                              <select class="form-control"  name="freq">
                                                  <option>100 MG</option>
                                                  <option>40 MG</option>
                                                  <option>10 MG</option>
                                                  <option>500 MG</option>
                                                  <option>50 MG</option>
                                                  <option>500 Grm</option>

                                              </select> 
                                          </td> 
                                          <td style="width: 12%">
                                          
                                             <s:select cssClass="form-control showToolTip chosen-select" name="priscdose" id="freq" list="dosageList" listKey="name" listValue="name" headerKey="0" headerValue="Select Frequency"/> 
                                          </td>
                                          <td style="width: 9%;">
                                          
                                          <div class="col-sm-6 col-xs-6 col-md-6">
                                         <!--  <input type="text" class="form-control" id="priscdays" name="priscdays" style="width: 21px;margin-left: -9px;"> -->
                                             <select name="priscdays" id="nursingcdays" class="form-control follow">
                                             	<%for(int i=1;i<=90;i++){ %>
                                             		<option value="<%=i %>"><%=i %></option>
                                             	<%} %>
                                             </select>
                                             
                                          </div>
                                          <div class="col-sm-6 col-xs-6 col-md-6" >
                                          		<p class="dayw">Days</p>
                                              <select style="display: none;" name="priscdurationtype" id="priscdurationtype" class="form-control follow" style="width:54px;margin-left: -20px;">
                                                  <option value="Days">Days</option>
                                                  <option value="Week">Week</option>
                                                  <option value="Month">Month</option>
                                              </select>
                                          </div>
                                          </td>
                                          <td style="width: 0%; display: none;"><input type="text" class="form-control" id="prisctotal" name="prisctotal" readonly="readonly"></td>
											<!--<td style="width: 19%; display: none;">
											<s:select id="priscdosenotes" name="priscdosenotes" list="dosagenoteList"
                                          listKey="name" listValue="name" cssClass="form-control showToolTip chosen-select"
                                          headerKey="0" headerValue="Select Note"/>
											</td>
											--><td style="width: 5%;">
                                           <a href="#" type="button" onclick="addtempnursing(0)" title="Add" class="btn btn-success"><i class="fa fa-plus"></i></a>
											</td>
                                      </tr>

                                  </tbody>
                              </table>
                   
                              <form class="form-horizontal hidden dosen">
                                  <div class="form-group">
                                      <p for="inputEmail3" class="col-sm-2 col-md-2 col-lg-2 col-xs-2 control-label">Dose Notes</p>
                                      <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6" >
                                         <%--  <select class="form-control" name="priscdosenotes" id="priscdosenotes">
                                              <option value="0">Select Dose Notes</option>
                                              <option value="dose1">dose1</option>
                                              <option value="dose2">dose2</option>
                                          </select> --%>
                                          
                                          <s:select id="priscdosenotes" name="priscdosenotes" list="dosagenoteList"
                                          listKey="name" listValue="name" cssClass="form-control showToolTip chosen-select"
                                          headerKey="0" headerValue="Select Dosage Note"/>

                                      </div>
                                      <div class="col-sm-4 col-md-4 col-lg-4 col-xs-4">
                                          <a href="#" type="button" onclick="addTempPriscription()" title="Add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
                                           
                                          

                                      </div>
                                  </div>

                              </form>
                          </div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                          <div class="tableheight123">
                          	<table class="table table-bordered" cellspacing="0" width="100%">
                                  <thead>
                                      <tr class="tableback">
                                         
                                         <!--  <th class="med9">Type</th> -->
                                          <th class="med21">Category</th>
                                          <th class="med9">Task Name</th>
                                          <th class="med8">Frequency</th>
                                           <th class="med8">Duration</th>
                                          <!--<th class="med9">Notes</th>
                                         
                                        --><!-- <th>Edit</th> -->
                                        <th class="med9">Delete</th>

                                      </tr>
                                  </thead>
                                 
                                  <tbody id="nursingtable">
                                  	
                                      

                                  </tbody>
                              </table>
                          </div>

                          </div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-bottom: 0px;">
                              <div class="col-lg-2 col-sm-2 col-xs-2 col-md-2">
                                  <form class="marleft9">
                                      <div class="form-group">
                                          <p for="exampleInputEmail1">Followup After</p>
               	                           <div class="col-sm-6 col-xs-6 col-md-6" style="padding: 0px;">
                                              <!-- <input type="tel" name="followupsqty"  class="form-control" id="followupsqty"> -->
                                                <select name="followupsqty" id="followupsqty" class="form-control fodays">
                                             	<%for(int i=0;i<=90;i++){ %>
                                             		<option value="<%=i %>"><%=i %></option>
                                             	<%} %>
                                             </select>
                                          </div>
                                          <div class="col-sm-6 col-xs-6 col-md-6" style="padding: 0px;">
                                              <select name="followupstype" id="followupstype" class="form-control fodays">
                                                  <option value="Days">Days</option>
                                                  <!-- <option value="Week">Week</option>
                                                  <option value="Month">Month</option> -->
                                              </select>
                                          </div>
                                      </div>

                                  </form>
                              </div>
                              <div class="col-lg-6 col-md-6 col-xs-6" style="padding: 0px;">
                                  <form>
                                      <div class="form-group">
                                          <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                              <p for="exampleInputEmail1" class="marleft9">Advice</p>
                                          </div>
                                          <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language1" value="option1" checked> English
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language2" value="option2"> Regional
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language3" value="option3"> Hindi
                                              </label>
                                          </div>
                                          
                                          
                                      </div>

                                  </form>
                                  <form class="form-horizontal">
                                     
                                      <textarea name="priscadvoice" id="priscadvoice" class="form-control setyerx" rows="3"></textarea>

                                  </form>
                              </div>
                              <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">


                              </div>
                          </div>
                          </s:form>
                      </div>
                  
                  
                  
            
              <script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
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
             $(function() {
				  $('.tableheight123').slimScroll({
				   		height : '280px',
				   		railVisible: true,
						alwaysVisible: true
				  });
 				});
 				  $(function () {
              $('[data-toggle="tooltip"]').tooltip()
               })
            </script>

     