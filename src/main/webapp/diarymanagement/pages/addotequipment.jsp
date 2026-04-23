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
        margin-left: -6px;
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
     
     
     
                  <s:form theme="simple">
                  <div class="modal-body">
                      <div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                              <form>
                              		<s:hidden name="priscdate" id="eotpriscdate"/>
                              		<s:hidden name="priscdateandtime" id="eotpriscdateandtime"/>
                              		 <input type="hidden" name="priscautoid" id="eotpriscautoid" value="0"/>
                                  <div class="form-group prepaymd hidden">
                                      <label class="radio-inline disabled">
                                          <input type="radio" name="paymode" id="eotpaymode1" value="option1" disabled> Pre-Pay
                                      </label>
                                      <label class="radio-inline disabled">
                                          <input type="radio" name="paymode" id="eotpaymode2" value="option2" disabled> Post-Pay
                                      </label>
                                      <label class="radio-inline">
                                          <input type="radio" name="paymode" id="eotpaymode3" value="option3" checked> Other
                                      </label>

                                  </div>
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3 marleft9 hidden">
                                      <div class="form-group">
                                          <label>Patient Bar Code</label>
                                          <p>C31128A00175</p>
                                      </div>
                                  </div>
                                  
                                  
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
                                      <div class="form-group">
                                          <label>Date / Time</label>
                                          <p id="eotdatetimeid">21/11/2015 13:58 PM</p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
                                      
                                  </div>
                                 <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
                                  <div class="form-group">
                                        <label>Template Name</label>
	                          		<input type="text" name="mdtemplatename" id="eotmdtemplatename" class="form-control"
	                          		placeholder="Enter Template Name">
                          		</div>
                          </div>
                          
                          <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
                                  <div class="form-group">
                                        <label>Select Template Name</label>
                                        <div id="eotprisctemplatediv">
			                          		<s:select onchange="showTemplateoteq(this.value)" id="eottemplatename" name="templatename" list="oteqtemplateNameList"
			                          		headerKey="0" headerValue="Select Template"
			                          		listKey="id" listValue="templatename" cssClass="form-control chosen-select"/>
			                          	</div>
                          		</div>
                          </div>
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3 hidden">
                                      <div class="form-group">
                                          <label>Doctor Incharge</label>
                                          <p id="eotpriscdoctornameid">Dr.Abhishek Pandey</p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3 hidden">
                                      <div class="form-group">
                                          <label>Repeat Prescription</label>
                                           <%-- <select class="form-control">
                                                  <option value="25/11/2015">25/11/2015</option>
                                                  <option value="25/11/2015">25/11/2015</option>
                                              </select> --%>
                                             
                                             <div id="eotrpeatdivajax">
	                                              <s:select onchange="editparentprisc(this.value)" name="repeatdate" id="eotrepeatdate" list="parentPriscList"
	                                              listKey="id" listValue="date" cssClass="form-control chosen-select"
	                                              headerKey="0" headerValue="Select Date" />
                                              </div>
                                      </div>
                                  </div>
                              </form>
                          </div>
                      </div>
                      
                      <br>
                      
                      <div class="row">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martpomin15">
                              <table class="table" cellspacing="0" width="100%">
                                  <thead>
                                      <tr><!--
                                          <th class="thfont">Code</th>
                                         --><!--  <th class="thfont">Type</th> --><!--
                                          <th class="thfont">Category</th>
                                          --><th class="thfont">OT Equipment</th>
                                          <!-- <th class="thfont">Strength</th> -->
                                          <!--<th class="thfont">Dosage</th>
                                          --><th class="thfont">Quantity</th>
                                         <th class="thfont">Description</th> 
                                         <th class="thfont"></th> 

                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr>
                                          <td style="width: 7%;display: none;">
                                              <select class="form-control" name="prisccode" id="eotprisccode">
                                                  <option value="2097">2097</option>
                                                  <option value="2098">2098</option>
                                                  <option value="2099">2099</option>
                                                  <option value="2099">3001</option>
                                                  <option value="3002">3002</option>
                                                  <option value="3003">3003</option>
                                                  <option value="3003">3004</option>
                                                  <option value="3005">3005</option>
                                                  <option value="3006">3006</option>
                                              </select>
                                          </td>
                                          <td style="width: 10%;display: none;">
                                          	<s:select cssClass="form-control showToolTip chosen-select" name="prisctype" id="eotprisctype" list="mdicneTypeList" listKey="name" listValue="name" headerKey="0" headerValue="Select Type"/>
                                             <%--  <select class="form-control" name="prisctype" id="eotprisctype">
                                                  <option value="Tablet">Tablet</option>
                                                  <option value="Capsul">Capsul</option>
                                                  <option value="Oinment">Oinment</option>
                                                  <optionvalue="Liquied">Liquied</option>
                                                  <option value="Injection">Injection</option> --%>
                                                
                                              </select>
                                          </td>
                                          <td style="width: 30%;display: none;">
                                             	<s:select onchange="setMedicineName(this.value)" cssClass="form-control showToolTip chosen-select" name="mdicinecategory" id="eotmdicinecategory" list="mdicinecategoryList" listKey="id" listValue="name" headerKey="0" headerValue="Select Category"/>
                                          </td>
                                          <td id="eotmdicinediv" style="width: 15%" >
                                        
                                           <%--  <select class="form-control showToolTip chosen" id="eotmdicicnename" name="mdicicnename"><option value="0">Select Medicine</option></select> --%>
                                           	<s:select cssClass="form-control showToolTip chosen-select" name="mdicinename" id="eotmdicinename" list="assetList"
                                           listKey="id" listValue="name" headerKey="0" headerValue="Select Equipment"	>
                                           	
                                           	</s:select>
                                           
                                          </td>
                                          <td style="width:5%;display:none;">
                                          	<!--  <input type="text" class="form-control" id="eotpriscfreq" name="priscfreq"> -->
                                              <select class="form-control" id="eotpriscfreq" name="priscfreq">
                                                  <option>100 MG</option>
                                                  <option>40 MG</option>
                                                  <option>10 MG</option>
                                                  <option>500 MG</option>
                                                  <option>50 MG</option>
                                                  <option>500 Grm</option>

                                              </select> 
                                          </td> 
                                          <td style="width: 12%;display: none;">
                                          
                                             <s:select cssClass="form-control showToolTip chosen-select" name="priscdose" id="eotpriscdose" list="dosageList" listKey="name" listValue="name" headerKey="0" headerValue="Select Dosage"/> 
                                          </td>
                                          <td style="width: 3%;">
                                          
                                          <div class="col-sm-6 col-xs-6 col-md-6">
                                          <input type="number" class="form-control" id="eotpriscdays" name="priscdays" style="width: 76px;margin-left: -9px;">
                                           
                                             
                                          </div>
                                          <div class="col-sm-6 col-xs-6 col-md-6" style="display: none;" >
                                          		<p class="dayw">Days</p>
                                              <select style="display: none;" name="priscdurationtype" id="eotpriscdurationtype" class="form-control follow" style="width:54px;margin-left: -20px;">
                                                  <option value="Days">Days</option>
                                                  <option value="Week">Week</option>
                                                  <option value="Month">Month</option>
                                              </select>
                                          </div>
                                          </td>
                                          <td style="width: 0%; display: none;"><input type="text" class="form-control" id="eotprisctotal" name="prisctotal" readonly="readonly"></td>
											<td style="width: 19%;">
											<input type="text" id="eotpriscdosenotes" name="eotpriscdosenotes" class="form-control"/>
											
											</td>
											<td style="width: 5%;">
                                           <a href="#" type="button" onclick="addTempEquipment()" title="Add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
											</td>
                                      </tr>

                                  </tbody>
                              </table>
                   
                              <form class="form-horizontal hidden dosen">
                                  <div class="form-group">
                                      <p for="inputEmail3" class="col-sm-2 col-md-2 col-lg-2 col-xs-2 control-label">Dose Notes</p>
                                      <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6" >
                                         <%--  <select class="form-control" name="priscdosenotes" id="eotpriscdosenotes">
                                              <option value="0">Select Dose Notes</option>
                                              <option value="dose1">dose1</option>
                                              <option value="dose2">dose2</option>
                                          </select> --%>
                                          
                                          <s:select id="eotpriscdosenotes" name="priscdosenotes" list="dosagenoteList"
                                          listKey="name" listValue="name" cssClass="form-control showToolTip chosen-select"
                                          headerKey="0" headerValue="Select Dosage Note"/>

                                      </div>
                                      <div class="col-sm-4 col-md-4 col-lg-4 col-xs-4">
                                          <a href="#" type="button" onclick="addTempPriscription()" title="Add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
                                           
                                          

                                      </div>
                                  </div>

                              </form>
                          </div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 tableheight">
                              <table class="table table-bordered" cellspacing="0" width="100%">
                                  <thead>
                                      <tr class="tableback">
                                         
                                         <!--  <th class="med9">Type</th> -->
                                          <th class="med21">Equipment</th>
                                          
                                          <th class="med9">Quantity</th>
                                        
                                          <th class="med30">Description</th>
                                         
                                        <!-- <th>Edit</th> -->
                                        <th>Delete</th>

                                      </tr>
                                  </thead>
                                 
                                  <tbody id="eotprisctable">
                                      <tr>
                                          
                                          <td>Tablet</td>
                                          <td>DOMPRAZ</td>
                                          <td>500 MG</td>
                                          <td>1-0-1</td>
                                          <td>15 Days</td>
                                          <td>30</td>
                                          <td></td>
                                          <td><center><a href="" title="Delete"><i class="fa fa-trash-o"></i></a></center></td>
                                      </tr>
                                      <tr>
                                          
                                          <td>Tablet</td>
                                          <td>NEPHTOR</td>
                                          <td>100 MG</td>
                                          <td>1-0-1</td>
                                          <td>15 Days</td>
                                          <td>30</td>
                                          <td></td>
                                          <td><center><a href="" title="Delete"><i class="fa fa-trash-o"></i></a></center></td>
                                      </tr>

                                  </tbody>
                              </table>

                          </div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden martop10">
                              <div class="col-lg-2 col-sm-2 col-xs-2 col-md-2">
                                  <form class="marleft9">
                                      <div class="form-group">
                                          <p for="exampleInputEmail1">Followup After</p>
               	                           <div class="col-sm-6 col-xs-6 col-md-6">
                                              <!-- <input type="tel" name="followupsqty"  class="form-control" id="eotfollowupsqty"> -->
                                                <select name="followupsqty" id="eotfollowupsqty" class="form-control fodays">
                                             	<%for(int i=0;i<=90;i++){ %>
                                             		<option value="<%=i %>"><%=i %></option>
                                             	<%} %>
                                             </select>
                                          </div>
                                          <div class="col-sm-6 col-xs-6 col-md-6">
                                              <select name="followupstype" id="eotfollowupstype" class="form-control fodays">
                                                  <option value="Days">Days</option>
                                                  <!-- <option value="Week">Week</option>
                                                  <option value="Month">Month</option> -->
                                              </select>
                                          </div>
                                      </div>

                                  </form>
                              </div>
                              <div class="col-lg-6 col-md-6 col-xs-6 martopmin10">
                                  <form>
                                      <div class="form-group">
                                          <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                              <p for="exampleInputEmail1" class="marleft9">Advice</p>
                                          </div>
                                          <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="eotlanguage1" value="option1" checked> English
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="eotlanguage2" value="option2"> Regional
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="eotlanguage3" value="option3"> Hindi
                                              </label>
                                          </div>
                                          
                                          
                                      </div>

                                  </form>
                                  <form class="form-horizontal">
                                     
                                      <textarea name="priscadvoice" id="eotpriscadvoice" class="form-control setyerx" rows="3"></textarea>

                                  </form>
                              </div>
                              <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">


                              </div>
                          </div>
                          </s:form>
                      </div>
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
				  $('.tableheight').slimScroll({
				   		height : '100px',
				   		railVisible: true,
						alwaysVisible: true
				  });
 				});
 				  $(function () {
              $('[data-toggle="tooltip"]').tooltip()
          })
             </script>

     