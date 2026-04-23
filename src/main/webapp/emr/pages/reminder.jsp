<%@ taglib uri="/struts-tags"  prefix="s"%>

<table class="table table-hover table-condensed table-bordered" id="reminderList">
		<thead>
			<tr>
				<th>#</th>
				<th class="text-center" style="width: 20%;">User Name</th>
				<th class="text-center" style="width: 50%;">Note</th>
				<th class="text-center" style="width: 20%;">Date/Time</th>
				<th class="text-center" style="width: 20%;">Alert Date/Time</th>
				<th class="text-center" style="width: 5%;">View</th>
				<th class="text-center" style="width: 5%;">Edit</th>
				<th class="text-center" style="width: 5%;">Delete</th>
			</tr>
		</thead>
		
		
		<tr>
		<%int remcnt = 1; %>
		<s:iterator value="reminderList">
		<tr>
				<td><%=remcnt %></td>
				<td class="text-center" style="width: 20%;"><s:property value="practitionerName"/></td>
				<td class="text-center" style="width: 50%;"><s:property value="description"/></td>
				<td class="text-center" style="width: 20%;"><s:property value="lastModified"/></td>
				<td class="text-center" style="width: 20%;"><s:property value="alertDate"/><s:property value="hh"/><s:property value="min"/></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="viewReminder(<s:property value="id"/>,'viewrem')">View</a></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="editReminder(<s:property value="id"/>,'editrem')"><i class="fa fa-edit"></i></a></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="deleteReminderAjax(<s:property value="id"/>)"><i class="fa fa-trash-o"></i></a></td>
				</tr>
			<%remcnt++; %>
		</s:iterator>
		</tr>
	
</table>




<!-- add reminder -->
	<div class="row" >
					<div class="col-lg-12"  id="addreminder" style="display: none; background-color: rgba(252, 158, 168, 0.44);">
					<br/>
					<div class="row">
					<div class="col-lg-1" ></div>						
						<div class="col-lg-1" >						
							<label>Alert Date :</label>	
						</div>
						<div class="col-lg-2">			
							<s:textfield  readonly="true" name="alert" id="alert" cssStyle="width:100%;" 
								cssClass="form-control" theme="simple" ></s:textfield>
						</div>
						
						<div class="col-lg-2" style="display: -webkit-box;">
							<label>Time : </label>
							<span>hh:</span>							
							<select name="hh" id="hh">
								<% String txt = ""; for(int i=1;i<=24;i++){ if(i<=9){txt = "0"+i;} else{txt = Integer.toString(i); }%>
									<option value="<%=txt %>"><%=txt %></option>
								<%} %>
							</select>
						</div>
						<div class="col-lg-2" style="display: -webkit-box;">
							<span>mm:</span>
							<select id="min" name="min">
								<option value="05">05</option>
								<option value="10">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
								<option value="25">25</option>
								<option value="30">30</option>
								<option value="35">35</option>
								<option value="40">40</option>
								<option value="45">45</option>
								<option value="50">50</option>
								<option value="55">55</option>
							</select>
						</div>
						</div>
						<br/>
						<div class="row">
						<div class="col-lg-1"></div>
						<div class="col-lg-1">
							<label>Note : </label>
						</div>
						<div class="col-lg-8">							
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Enter Reminder Note" name="reminderText"  id="reminderText" ></textarea>
						</div>
						</div>
						<br/>
						<div class="row">
						<div class="col-lg-2"></div>
						<div class="col-lg-8">	
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveReminderAjax();">Save</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddReminder()">Close</button>
						</div>
						</div>
						<br/>
						</div>
		
				</div> 
				
				<!-- view Reminder text -->
				
			<div class="row">
					<div class="col-lg-12"  id="viewrem" style="display: none; background-color: rgba(252, 158, 168, 0.44);">
					<br/>
						<div class = "row">
						<div class="col-lg-1"></div>
							<div class="col-lg-2 col-md-2">
								<label>Note:</label>
							</div>
							<div class="col-lg-8 col-md-8">
								<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="View Reminder" name="viewremindertext"  id="viewremindertext" readonly="readonly">hello world</textarea>
							</div>
						</div>
						<br>
						<div class = "row">
						<div class="col-lg-1"></div>
							<div class="col-lg-2 col-md-2">
								<label>Alert Date:</label>
							</div>
							<div class="col-lg-8 col-md-8">
							<s:textfield disabled="true" name="viewalert" id="viewalert"
								cssClass="form-control" theme="simple"> </s:textfield>		
							</div>
						</div>
						<br>
						<div class = "row">
						<div class="col-lg-3"></div>
							<div class="col-lg-4 col-md-4">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddReminder()">Close</button>
							</div>
						</div>
						<br>
				</div>
			</div>
				
			<!-- edit Reminder history -->
				
				<div class="row" >
					<div class="col-lg-12"  id="editrem" style="background-color: rgba(252, 158, 168, 0.44); display: none;">
					<br/>
						<div class = "row">
						<div class="col-lg-1"></div>
							<div class="col-lg-2 col-md-2">
								<label>Note:</label>
							</div>
							<div class="col-lg-8 col-md-8">
								<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Enter Body" name="editremindertext"  id="editremindertext"></textarea>
							</div>
						</div>
						<br>
						<div class = "row">
						<div class="col-lg-1"></div>
							<div class="col-lg-2 col-md-2">								
								<label>Alert Date:</label>						
							</div>	
							<div class="col-lg-4 col-md-2">				
							<s:textfield name="editalert" id="editalert"
								cssClass="form-control" theme="simple"> </s:textfield>	
							</div>	
						</div>
						<br>
						<div class = "row">
						<div class="col-lg-1"></div>
							<div class="col-lg-2 col-md-2">
								<label>Time:</label>
							</div>
							<div class="col-lg-2 col-md-2">
							<span>hh:</span>							
							<select name="edithh" id="edithh">
								<% txt = ""; for(int i=1;i<=24;i++){ if(i<=9){txt = "0"+i;} else{txt = Integer.toString(i); }%>
									<option value="<%=txt %>"><%=txt %></option>
								<%} %>
							</select>
							</div>
							<div class="col-lg-2 col-md-2">
							<span>mm:</span>							
							<select id="editmin" name="editmin">
								<option value="05">05</option>
								<option value="10">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
								<option value="25">25</option>
								<option value="30">30</option>
								<option value="35">35</option>
								<option value="40">40</option>
								<option value="45">45</option>
								<option value="50">50</option>
								<option value="55">55</option>
							</select>	
							</div>
							</div>						
				
						<br>
						<div class = "row">
						<div class="col-lg-3"></div>
							<div class="col-lg-4 col-md-4">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editsaveReminderAjax();">Save</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddReminder()">Close</button>
						</div>
						</div>
						<br/>
					</div>
				</div>
				