
<!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/nursingcare.css" rel="stylesheet"
	type="text/css" />
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- New theme 30 01 2018 -->
<script type="text/javascript" src="diagnosis/js/nursingcarplan.js"></script>

<div class="marto tableborderprint letterheadhgt" style="height: 100px;">
	<div id="newpost" class="col-lg-12 col-md-12 col-sm-12 borderbot">
		<link href="common/css/printpreview.css" rel="stylesheet" />
		<%@ include file="/accounts/pages/letterhead.jsp"%>
	</div>
</div>

<div class="">

	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor"
		style="padding-top: 5px; border-bottom: 1px solid #6699cc; padding-bottom: 5px; background-color: rgba(91, 192, 222, 0.16);">
		<%-- 			<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
							<s:if test="casualtyipd==0">
							<b class="setticolors">Nursing Care Plan</b>
								
							</s:if>
							 <s:else>
								<b class="setticolors">Casualty SUMMARY</b>
							</s:else> 
								
						</div>
						</div>
						<!-- <div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div> -->
						
					</div>
					</div>
				</div> --%>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6"
			style="padding-left: 0px; padding-right: 0px;">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<b for="inputEmail3" class="control-label">UHID</b>
				</div>
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<b for="inputEmail3" class="control-label">Patient Name</b>
				</div>

				<div class="form-group marbot3 "
					style="margin-bottom: 0px !important;">
					<s:if test="agegender!=''">
						<b for="inputEmail3" class="control-label">Age / Gender</b>
					</s:if>
				</div>
				<s:if test="contact!=''">
					<div class="form-group marbot3"
						style="margin-bottom: 0px !important;">
						<b for="inputEmail3" class="control-label">Contact</b>
					</div>
				</s:if>
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<b for="inputEmail3" class="control-label">Address</b>
				</div>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"
				style="padding: 0px;">
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<span>: <s:property value="regno" /></span>
				</div>
				<div class="form-group " style="margin-bottom: 0px !important;">
					<span>: <s:property value="client" />
					</span>
				</div>

				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<s:if test="agegender!=''">
						<span>: <s:property value="agegender" /></span>
					</s:if>
				</div>
				<s:if test="contact!=''">
					<div class="form-group marbot3"
						style="margin-bottom: 0px !important;">
						<span>: <s:property value="contact" /></span>
					</div>
				</s:if>
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<span>: <s:property value="address" />
					</span>
				</div>
			</div>






		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right"
			style="padding-left: 0px; padding-right: 0px;">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<b for="inputEmail3" class="control-label">Adm. ID</b>
					<s:if test="admissiondate!=''">
						<br>
						<b for="inputEmail3" class="control-label">Adm. Date</b>
					</s:if>
				</div>
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<b for="inputEmail3" class="control-label">Ward / Bed</b>
				</div>
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<b for="inputEmail3" class="control-label">Payee</b>
					<s:if test="mlcno!=''">
						<b for="inputEmail3" class="control-label">/MLC</b>
					</s:if>

				</div>
				<%-- <s:if test="familyDetails!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Family Details</b> 
							</div> 
						</s:if> --%>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"
				style="padding: 0px;">
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<%-- <span>: <s:property value="id"/>/<s:property value="num_admission"/></span>   --%>
					<span>: <s:property value="ipdseqno" /></span>
					<s:if test="admissiondate!=''">
						<br>
						<span>: <s:property value="admissiondate" />
						</span>
					</s:if>
				</div>
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<span>: <s:property value="wardid" /> / <s:property
							value="bedid" /></span>
				</div>
				<div class="form-group marbot3"
					style="margin-bottom: 0px !important;">
					<span>: <s:property value="thirdParty" /></span>
					<s:if test="mlcno!=''">
						<span>/ <s:property value="mlcno" /></span>
					</s:if>
					<s:if test="mlcrefdoctor!=''">
						<span>/ <s:property value="mlcrefdoctor" /></span>
					</s:if>
				</div>
				<%-- <s:if test="familyDetails!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="relativename"/>,<s:property value="relationcont"/> (<s:property value="relation"/>)</span> 
							</div>
						</s:if> --%>
			</div>









		</div>
	</div>
</div>
<div
	class="col-lg-12 col-md-12 col-sm-12 col-xs-12 headingbari marginleftrightzeroi">
	<!-- <b>Nursing Care Plan </b> -->
</div>


<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">






	<div id="printnursingplancare_div">
		<%-- <span class="print-visible hidden-md hidden-lg">Hospital: <s:property value="clinicName"/> </span>&nbsp&nbsp&nbsp
<span class="print-visible hidden-md hidden-lg">Patient Name: <s:property value="client"/> </span>&nbsp&nbsp&nbsp
<span class="print-visible hidden-md hidden-lg">UHID: <s:property value="regno"/> </span>&nbsp&nbsp&nbsp
<span class="print-visible hidden-md hidden-lg">Ward Id: <s:property value="wardid"/> </span>&nbsp&nbsp&nbsp
<span class="print-visible hidden-md hidden-lg">Bed ID: <s:property value="bedid"/> </span>&nbsp&nbsp&nbsp
<span class="print-visible hidden-md hidden-lg">Date: <s:property value="date"/> </span>&nbsp&nbsp&nbsp --%>

		<div class="row details">

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4 class="colorwhitprint">Nursing Care Plan</h4>

			</div>
		</div>

		<table
			class="table table-striped table-responsive tableborderprint tablewidthipdreportsprint">
			<tr>
				<td></td>



			</tr>
			<thead>


				<th width="14.28%" class="tableborderprint"><b>Assessment</b></th>
				<th width="14.28%" class="tableborderprint"><b>Diagnosis</b></th>
				<th width="14.28%" class=" tableborderprint"><b>Inference</b></th>
				<th width="14.28%" class="tableborderprint"><b>Planning</b></th>
				<th width="14.28%" class=" tableborderprint"><b>Intervention</b></th>
				<th width="14.28%" class=" tableborderprint"><b>Rationale</b></th>
				<th width="14.28%" class=" tableborderprint"><b>Evaluation</b></th>
			</thead>

			<tbody>

				<tr>

					<td align="justify" class="tableborderprint">
						<h5>
							<b>Subjective</b>
						</h5> <s:property value="subjectivecare" />


						<h5>
							<b>Objective</b>
						</h5> <s:property value="objectivecare" />
					</td>
					<td align="justify" class="tableborderprint">
						<!-- It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). -->
						<s:property value="diagnosiscare" />
					</td>


					<td align="justify" class="tableborderprint"><s:property
							value="inferencecare" /> <!--  It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). -->
					</td>
					<td class=" tableborderprint"><s:property value="planningcare" />
						<!-- 	It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). -->
					</td>
					<td align="justify" class="tableborderprint"><s:property
							value="interventioncare" /> <!-- It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). -->
					</td>
					<td align="justify" class="tableborderprint"><s:property
							value="rationalecare" /> <!-- 	 It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). -->
					</td>
					<td align="justify" class="tableborderprint"><s:property
							value="evaluationcare" /> <!--  It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). -->
					</td>
			</tbody>


		</table>


	</div>
	<!--  <input onClick="printnursingplancare('printnursingplancare_div');" type="submit" class="fadeIn fourth  pull-right" value="Print"> -->
	<input onClick="printpage();" type="submit"
		class="fadeIn fourth  pull-right" value="Print">

</div>


</body>
</html>


