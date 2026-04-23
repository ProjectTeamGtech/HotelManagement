<%@taglib uri="/struts-tags" prefix="s"%>


		<div class="panel panel-primary">
			<div class="panel-body">
`				<div class="row">
					<div class="col-lg-12">
						<h4><b>Location Profile</b></h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>Location Name :</label>
							<s:property value="locationname"/>
						</div>
						<div class="form-group">
							<label>Country:</label>
							<s:property value="country"/>
						</div>
						<div class="form-group">
							<label>City :</label>
							<s:property value="city"/>
						</div>
						<div class="form-group">
							<label>Address:</label>
							<s:property value="address"/>
						</div>
						<div class="form-group">
							<label>Post Code :</label>
							<s:property value="pinCode"/>
						</div>
						<div class="form-group">
							<label>Contact No. :</label>
							<s:property value="contactNo"/>
						</div>
						<div class="form-group">
							<label>Location Direction :</label>
							<%String str = (String)session.getAttribute("locdirection"); %>
							<span><%=str.toString() %></span>
							<%-- <s:property value="loc_direction"/> --%>
						</div>
						<div class="form-group">
							<label>Map:</label>
							<img src="liveData/locationMap/<s:property value="userImageFileName"/>">
						</div>
						
						
					</div>
				</div>
				
			</div>
		</div>