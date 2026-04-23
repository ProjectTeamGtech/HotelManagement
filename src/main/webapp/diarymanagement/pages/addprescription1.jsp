  <%@ taglib uri="/struts-tags" prefix="s" %>  
  <style>
  
  
.martop10{
    margin-bottom: 10px;
    margin-left: -93px;
}
.addwidth{
    width: 596px !important;
}
.agewidth{
    width: 65px !important;
}
.left20{
    margin-left: 20px;
}
.left10{
    margin-left: 10px;
}
.lableright{
    float: right;
}
.modal-dialog {
  /*   width: 900px; */
    margin: 30px auto;
}
.modal-header {
    min-height: 16.43px;
    padding: 6px 8px 4px 11px !important;
    border-bottom: 1px solid #E5E5E5;
    background: #2BB1FF none repeat scroll 0% 0% !important; 
}
.modal-body {
    position: relative;
    padding: 10px 7px !important; 
}

.form-inline .form-control {
    background-color: #F5F5F5 !important; 
}
.rowblank{
 margin-right: 0px !important; 
    margin-left: 0px !important; 
}

.btnset{
    float: right;
    margin-top: 25px;
}

.form-control:focus {
    border-color: #2BB1FF !important; 
    
}
.form-control {
    background-color: #F5F5F5 !important;
}
.mname{
    margin-left: -26px;
}
.advice{
    width: 148px;
}
.content-grids {
    background: #3391E7 none repeat scroll 0% 0%;
    padding: 10px 0px;
}

.docsig{
    float: right;
}

.tableback{
    background-color: #4E7894;
color: rgb(255, 255, 255);
font-style: normal;
}
  
  
  </style>  
    
    
    <div class="">
                       
                           
                            <div class="row martop10">
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 mangae2col">
                                        <label for="exampleInputName2" class="lableright">Date</label>
                                    </div>
                                    <form class="form-inline">
                                        <div class="form-group">
                                            <s:textfield cssClass="form-control botlnea" name="priscdate" id="priscdate"/>
                                            <input type="email" class="form-control" id="exampleInputEmail2">
                                        </div>
                                        <div class="form-group left10">
                                            <label for="exampleInputEmail2">S.No.</label>
                                            <input type="email" class="form-control" id="exampleInputEmail2">
                                        </div>

                                    </form>
                                </div>
                            </div>
                            <hr />
                            <div class="row martop10">
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 namema">
                                        <label for="exampleInputName2" class="lableright">Reg.No</label>
                                    </div>
                                    <form class="form-inline">
                                        <div class="form-group">
                                            <input type="text" class="form-control botlnea" name="regno" id="regno">
                                            <input type="email" class="form-control" id="exampleInpu1tEmail2">
                                        </div>

                                    </form>
                                </div>
                            </div>

                            <div class="row martop10">
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 namema">
                                        <label for="exampleInputName2" class="lableright">Name</label>
                                    </div>
                                    <form class="form-inline">
                                        <div class="form-group">
                                            <input type="text" class="form-control namewidth" name="priscclientname" id="priscclientname">

                                        </div>
                                        <div class="form-group left20">
                                            <label for="exampleInputEmail2">Age</label>
                                            <input type="email" class="form-control agewidth" name="priscclientage" id="priscclientage">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail2">Sex</label>
                                            <select class="form-control">
                                                <option value="1">Male</option>
                                                <option value="2">Female</option>

                                            </select>
                                        </div>

                                    </form>
                                </div>
                            </div>
                            <div class="row martop10">
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 namema">
                                        <label for="exampleInputName2" class="lableright">Address</label>
                                    </div>
                                    <form class="form-inline">
                                        <div class="form-group">
                                            <input type="text" class="form-control addwidth" name="priscaddress" id="priscaddress">

                                        </div>

                                    </form>
                                </div>
                            </div>
                            <hr />
                            <div class="row ">
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose hidden-print">
                                    <form>
                                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Category</label>
                                              	<s:select onchange="setMedicineName(this.value)" cssClass="form-control showToolTip chosen" name="mdicinecategory" id="mdicinecategory" list="mdicinecategoryList" listKey="id" listValue="name" headerKey="0" headerValue="Select Type"/>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
                                            <div class="form-group" id="mdicinediv">
                                                <label for="exampleInputEmail1">Drug Name</label>
                                            	<select class="form-control showToolTip chosen" id="mdicicnename" name="mdicicnename"><option value="0">Select Medicine</option></select>
                                            </div>
                                        </div>
                                        
                                         <div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
                                            <div class="form-group mname">
                                                <label for="exampleInputEmail1">Strength</label>
                                                <input type="text" class="form-control" id="priscfreq" name="priscfreq">
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
                                            <div class="form-group mname">
                                                <label for="exampleInputEmail1">Dose</label>
                                               <!--  <input type="text" class="form-control" id="priscdose" name="priscdose"> -->
                                               <s:select cssClass="form-control showToolTip chosen" name="priscdose" id="priscdose" list="dosageList" listKey="name" listValue="name" headerKey="0" headerValue="Select Dose"/>
                                            </div>
                                        </div>
                                     
                                        <div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
                                            <div class="form-group mname">
                                                <label for="exampleInputEmail1">Duration</label>
                                                <input type="text" class="form-control" id="priscdays" name="priscdays">
                                            </div>
                                        </div>

                                        <div class="col-lg-1 col-md-1 col-xs-12 col-sm-12 addbtnas" id="priscbtndiv">
                                            <a href="#" onclick="insertPriscription()" type="submit" class="btn btn-primary btnset">ADD</a>
                                        </div>
                                        
                                          <div  style="display: none;" class="col-lg-2 col-md-2 col-xs-12 col-sm-12 addbtnas" id="backtoaddid">
                                            <a href="#" onclick="backtoaddPriscription()" type="submit" class="btn btn-primary btnset">Back to ADD</a>
                                        </div>

                                    </form>
                                </div>
                            </div>

                            <div class="row rowblank" style="height: 300px;overflow: scroll;">
                                <table class="table table-bordered" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="tableback">
                                            <!-- <th>Date</th> -->
                                            <th>Drug Name</th>
                                            <th>Dose</th>
                                            <th>Strength</th>
                                            <th>D</th>
                                            <th>Edit</th>
                                            <th>Delete</th>

                                        </tr>
                                    </thead>
                                    <tbody id="prisctable">
                                        <tr>
                                            <td>6/11/2015</td>
                                            <td>Rosavel 10</td>
                                            <td>10 mg</td>
                                            <td>1-1</td>
                                            <td>10</td>

                                        </tr>
                                        <tr>
                                            <td>4/11/2015</td>
                                            <td>Gemking 500</td>
                                            <td>500 mg</td>
                                            <td>1-1</td>
                                            <td>5</td>

                                        </tr>
                                        <tr>
                                            <td>1/11/2015</td>
                                            <td>Amtas 2.5</td>
                                            <td>100 mg</td>
                                            <td>1</td>
                                            <td>7</td>

                                        </tr>
                                        <tr>
                                            <td>30/10/2015</td>
                                            <td>Acarbose</td>
                                            <td>200 mg</td>
                                            <td>1-1-1</td>
                                            <td>3</td>

                                        </tr>
                                        <tr>
                                            <td>25/10/2015</td>
                                            <td>Saridon</td>
                                            <td>450 mg</td>
                                            <td>1-1</td>
                                            <td>5</td>

                                        </tr>


                                    </tbody>
                                </table>
                            </div>

                            <div class="row martop10 martop10only">
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 namema">
                                        <label for="exampleInputName2" class="lableright">Advice</label>
                                    </div>
                                    <form class="form-inline">
                                        <div class="form-group">
                                            <textarea class="form-control namewidth" rows="3"></textarea>

                                        </div>
                                        <div class="form-group docsig">

                                            <textarea class="form-control " rows="3" placeholder="Doctor Signature"></textarea>
                                        </div>


                                    </form>
                                </div>
                            </div>

                           
                        
                    </div>