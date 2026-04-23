<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="com.apm.Emr.eu.entity.Investigation"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
 
<script type="text/javascript" src="emr/js/addInvestigation.js"></script> 
<script type="text/javascript" src="emr/js/viewinvestigation.js"></script> 
<style>
.newtb{
border: 1px solid ;
}
.invname{
font-size: 14px large !important ;
font-weight: bolder;

}

/* .numeric{
 border-bottom: 4px dashed grey;
padding-bottom: 30px; 
}

.writeup{
border-bottom: 4px dashed grey;
padding-bottom: 30px;
} */


h4{
text-align: left;
}

@media print {
.printrow{
color:white !important;
}
}
</style>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Multi Investigation Edit </h4>

									</div>

</div>	
<form action="multiinvsthandleInvestigation" id='it'>
<select id='databy' name='databy' onchange="reload()" >

<option value="default">Default</option>
<s:if test="addr=='ipd'">
<option value="ipd" selected="selected">Ipd</option>
</s:if>
<s:else><option value="ipd" >Ipd</option></s:else>
</select>

<input type="hidden" name="clientid" value="<s:property value='clientId'/>">
</form>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<h3 align="center"><label>Patient Name:</label><s:property value="clientname"/></h3>

<%-- <label> Age/Gender:</label><s:property value="agegender"/> --%>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id='editmulti'>
<form action="savemultiinvstlistInvestigation" method="post" id='invfrm'>
<div style="text-align: right;margin-bottom: 20px;">
<input type="submit" class='btn btn-success' value="Save All" style="height: 50px !important;width: 100px !important"> 
<input type="button" class='btn btn-danger' value="Approve All" style="height: 50px !important;width: 100px !important" onclick="setapprov()"> 
<input type="button" class='btn btn-warning' value="Print All" style="height: 50px !important;width: 100px !important" onclick="printit()"> 


<input type="hidden" name='approve' id='approve' value="">
</div>
<%
ArrayList<Investigation> invstigationsList= new ArrayList<Investigation>();
invstigationsList=(ArrayList<Investigation>) session.getAttribute("multiinvestigationlist");
int i=0;
int index=0;
%>
<%if(invstigationsList!=null){ %>
		
		<%for(Investigation investigation:invstigationsList){ %>
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style=" margin-bottom: 50px; ">
		
		<%String isdeleted=investigation.getDeleted();
		if(isdeleted.equals("1")){
			isdeleted="hidden-print";
		}else{
			isdeleted="";
		}
		%>
		<!--Numeric  & Hybreed  -->
		<%if(investigation.getReporttype().equals("Numerical")||investigation.getReporttype().equals("Hybreed")){ %>
		<div class="numeric">
		
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" >
		<h4>Investigation Name : <b><span><%=investigation.getInvsttype() %></span></b> </h4>
		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" >
		 <h4 style="text-align: right;">Request Date : <b><%=investigation.getDate() %></b>  <a onclick="openEmrPopup('printInvestigation?selectedid=<%=investigation.getId()%>&sectionid=none')"><i class='fa fa-print'></i></a></h4>
		</div> 
		<table class="my-table newtb " style="width: 100%">
		<tr style="background-color: #4E7894 !important;color: white !important;height: 25px !important">
		<td style="width: 50%" class='invname'>Name</td>  <td style="width: 10%" class='invname'>Observation Value</td> <td class='invname'>Normal Value</td> <td>Unit</td> 
		</tr>
		<%int r=1, upratioid=0; %>
		<%for(Investigation findings:investigation.getFindinglist()){ %>
		
		<tr >
		<td  class='invname'>
		<input type="hidden" name='multiinvstigation[<%=index%>].id' value="<%=findings.getId()%>">
		<input type="hidden" name='multiinvstigation[<%=index%>].reporttype' value="<%=investigation.getReporttype()%>">
		<input type="hidden" name='multiinvstigation[<%=index%>].invsttypeid' value="<%=investigation.getId()%>">
		<%
		String onchnange="";
		%>
		<%=findings.getInvstname() %></td>
		
		<%
		
		if(findings.getObsvalue()!=null){
			if(findings.getObsvalue().equals("0")){
				findings.setObsvalue("");
			}
		}
		
		
		
		if(findings.getInvsttype().equals("URINE PC  RATIO")){
			if(r==1){
				upratioid = findings.getId()+2;
			}
			onchnange= "showupr("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("Iron Studies")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showirondata("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}else
		
		if(findings.getInvsttype().equals("URINE PROTEIN CREATININE RATIO    ")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showupcrndata("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("Urine Protein Creatinine Ratio")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showupcrndata("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("CALCIUM:Creatinine Ratio")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showcalcration("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
	
		if(findings.getInvsttype().equals("LIPID  PROFILE")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showlipidprofile("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("LIPID PROFILE  ~ TOTAL CHOL, TRIGLY, HDL, LDL, VLDL")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showsnhlipidprofile("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("TOTAL PROTEIN  ~  TOT PROTEIN, ALBUMIN, GLOBULIN, A/G RATIO")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showtotalprotein("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("TOTAL PROTEIN")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showtotalprotein("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}else
		if(findings.getInvsttype().equals("LIVER FUNCTION TEST LFT ")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showliverfunctionlft("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		
		else
		if(findings.getInvsttype().equals("TOTAL BILIRUBIN ~ Total Bilirubin, Direct Bilirubin, Indirect Bilirubin ")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showsnhliverfunction("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("LIVER FUNCTION  TEST ")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showliverfunction("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("PROTHROMBIN TIME (PT/INR) ~ TEST, CONTROL, INR")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showsnhprothombin("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("PROTHROMBIN  TIME")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showprothombin("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("CALCIUM:CREATININE RATIO")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showcalciumcreatin("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		//lokesh for kunal hspital
		if(findings.getInvsttype().equals("Bilirubin")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showkunalbilburin("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("Total Protein")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showkunaltotalprotien("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		
		else
		if(findings.getInvsttype().equals("Liver Function Test ( LFT )")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showkunallft("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("Extended Lipid Profile")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "showsnhlipidprofile("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
		else
		if(findings.getInvsttype().equals("Prothrombin Time ( PT ")){
			if(r==1){
				upratioid = findings.getId();
			}
			onchnange= "calprothombinpt("+r+",this.id,this.value,"+upratioid+")";
			r++;
		}
	
		
		%>
		
		
		
		
		
		
		<td ><input type="text" class="form-control"   name='multiinvstigation[<%=index%>].obsvalue'  id='obs<%=findings.getId() %>'   value="<%=findings.getObsvalue()%>" onchange="<%=onchnange%>"></td>
		<td class='invname'><%=findings.getNormvalue()%></td>
		<td class='invname'><%=findings.getInvstUnit()%></td>
		<%index=index+1; %>
		</tr>
		<%} %>
		</table>
		<div style="margin-top: 10px;">
		<label>Remark</label><br>
		<input type="hidden" name='remarklist[<%=i%>].id' value="<%=investigation.getId()%>">
		 <textarea class="form-control xx" rows="4" id="comment<%=i %>" name='remarklist[<%=i%>].remark'  style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"><%=investigation.getRemark() %></textarea>
		</div>
		</div>
		<%} %>
		
		
		
		<!--WriteUp  -->
		<%  if(investigation.getReporttype().equals("Writeup")){ %>
		<div class="writeup">
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" >
		<h4>Investigation Name : <b><span><%=investigation.getInvsttype() %></span></b> </h4>
		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" >
		 <h4 style="text-align: right;">Request Date : <b><%=investigation.getDate() %></b></h4>
		</div> 
		<table class="my-table newtb " style="width: 100%">
		<tr style="background-color: #4E7894 !important;color: white !important;height: 25px !important">
		<td style="width: 50%">Name</td>  <td style="width: 10%">Findings</td> <td>Method</td> <td>Unit</td> 
		</tr>
		<%for(Investigation findings:investigation.getFindinglist()){ %>
		
		<tr >
		<td  class='invname'>
		<input type="hidden" name='multiinvstigation[<%=index%>].id' value="<%=findings.getId()%>">
		<input type="hidden" name='multiinvstigation[<%=index%>].reporttype' value="<%=investigation.getReporttype()%>">
		<input type="hidden" name='multiinvstigation[<%=index%>].invsttypeid' value="<%=investigation.getId()%>">
		
		<%=findings.getInvstname() %></td>
		<td ><input type="text" class="form-control"   name='multiinvstigation[<%=index%>].findings'    value="<%=findings.getFindings()%>"></td>
		<td ><input type="text" class="form-control"   name='multiinvstigation[<%=index%>].methods'   value="<%=findings.getMethods()%> "></td>
		<td class='invname'><%=findings.getInvstUnit()%></td>
		<%index=index+1; %>
		</tr>
		<%} %>
		</table>
		<div style="margin-top: 10px;">
		<label>Remark</label><br>
		<input type="hidden" name='remarklist[<%=i%>].id' value="<%=investigation.getId()%>">
		 <textarea class="form-control xx" rows="4" id="comment<%=i %>" name='remarklist[<%=i%>].remark'  style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"><%=investigation.getRemark() %></textarea>
		</div>
		</div>
		<%} %>
		
		
		
		<!-- Text -->
		<%  if(investigation.getReporttype().equals("Text")){ %>
		<div class="writeup">
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" >
		<h4>Investigation Name : <b><span><%=investigation.getInvsttype() %></span></b> </h4>
		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" >
		 <h4 style="text-align: right;">Request Date : <b><%=investigation.getDate() %></b></h4>
		</div> 
		<%for(Investigation findings:investigation.getFindinglist()){ %>
		<table class="my-table newtb " style="width: 100%">
		<tr style="background-color: #4E7894 !important;color: white !important;height: 25px !important">
		<td style="width: 50%">Name</td>  <td style="width: 10%">Findings</td> <td>Method</td> <td>Unit</td> 
		</tr>
		<tr style="height: 25px !important">
		<td  class='invname'>
		<input type="hidden" name='multiinvstigation[<%=index%>].id' value="<%=findings.getId()%>">
		<input type="hidden" name='multiinvstigation[<%=index%>].reporttype' value="<%=investigation.getReporttype()%>">
		<input type="hidden" name='multiinvstigation[<%=index%>].invsttypeid' value="<%=investigation.getId()%>">
		
		<%=findings.getInvstname() %></td>
		<td >-</td>
		<td >-</td>
		<td class='invname'><%=findings.getInvstUnit()%></td>
		
		</tr>
		</table>
		<div style="margin-top: 10px;">
		
		<div style="width: 30%;margin-bottom: 10px;margin-left: 850px" >
		<h5>Select Template</h5>
		<select id='invstemplate' onchange='getinvstemplate1(this.value,<%=i %>)' class='form-control chosen' >
		<option value="default">All</option>
		<%for(Master master:investigation.getTemplatelist()){ %>
		<option value="<%=master.getId() %>"><%=master.getName() %></option>
		<%} %>
		</select>
		</div>
		<label>Findings:</label><br>
		<input type="hidden" name='remarklist[<%=i%>].id' value="<%=investigation.getId()%>">
		 <textarea class="form-control xx" rows="4" cols="6" id="comment<%=i %>" name='multiinvstigation[<%=index%>].findings'  style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"><%=findings.getFindings() %></textarea>
		</div>
		<%index=index+1; %>
		<%} %>
		</div>
		<%} %>
		
		</div>
		<%i=i+1; %>		
		<%} %>

<%} %>
<div style="text-align: center;margin-bottom: 20px;">
<input type="submit" class='btn btn-success' value="Save All" style="height: 50px !important;width: 100px !important"> 
<input type="button" class='btn btn-danger' value="Approve All" style="height: 50px !important;width: 100px !important" onclick="setapprov()"> 
<input type="button" class='btn btn-warning' value="Print All" style="height: 50px !important;width: 100px !important" onclick="printit()"> 
</div>
</form>
</div>




<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" id='printmulti' >
<%
ArrayList<Investigation> invstigationsListprint= new ArrayList<Investigation>();
invstigationsListprint=(ArrayList<Investigation>) session.getAttribute("multiinvestigationlist");

%>
<%if(invstigationsList!=null){ %>
		
		<%for(Investigation investigation:invstigationsListprint){ %>
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style=" margin-bottom: 50px; ">
		<%if(investigation.getRemark()==null){ %>
		<%investigation.setRemark(""); %>
		<%} %>
		
		<!--Numeric  & Hybreed  -->
		<%if(investigation.getReporttype().equals("Numerical")||investigation.getReporttype().equals("Hybreed")){ %>
		<div class="numeric">
		<h4>Investigation Name : <b><span><%=investigation.getInvsttype() %></span></b></h4>
		<table class="my-table newtb " style="width: 100%">
		<tr class='printrow' style="background-color: #4E7894 !important;color: white !important;height: 20px !important" >
		<td style="width: 50%" class='invname'>Name</td>  <td style="width: 10%" class='invname'>Observation Value</td> <td class='invname'>Normal Value</td> <td>Unit</td> 
		</tr>
		
		<%for(Investigation findings:investigation.getFindinglist()){ %>
		
		<tr >
		<td class='invname'><%=findings.getInvstname()%></td>
		<td class='invname'><%=findings.getObsvalue()%></td>
		<td class='invname'><%=findings.getNormvalue()%></td>
		<td class='invname'><%=findings.getInvstUnit()%></td>
		</tr>
		<%} %>
		</table>
		
		<%if(!investigation.getRemark().equals("")){ %>
		<div style="margin-top: 8px;">
		<label>Remark :</label>
		<span><%=investigation.getRemark() %></span>
		</div>
		<%} %>
		
		</div>
		<%} %>
		
		<!--Text-->
		<%if(investigation.getReporttype().equals("Text")){ %>
		<div class="writeup">
		<h4>Investigation Name : <b><span><%=investigation.getInvsttype() %></span></b></h4>
		<table class="my-table newtb " style="width: 100%">
		<tr style="background-color: #4E7894 !important;color: white !important;height: 20px !important" class='printrow'>
		<td style="width: 50%" class='invname'>Name</td>  <td style="width: 50%" class='invname'>Findings</td><!--  <td class='invname'>Methods</td> <td>Unit</td>  -->
		</tr>
		
		<%for(Investigation findings:investigation.getFindinglist()){ %>
		
		<tr >
		<td class='invname'><%=findings.getInvstname()%></td>
		<td class='invname'><%=findings.getFindings()%></td>
		<%-- <td class='invname'><%=findings.getMethods()%></td>
		<td class='invname'><%=findings.getInvstUnit()%></td> --%>
		</tr>
		<%} %>
		</table>
		
		<%if(!investigation.getRemark().equals("")){ %>
		<div style="margin-top: 8px;">
		<label>Remark :</label>
		<span><%=investigation.getRemark() %></span>
		</div>
		<%} %>
		
		</div>
		<%} %>
		
		
		<!--Write Up -->
		<%if(investigation.getReporttype().equals("Writeup")){ %>
		<div class="writeup">
		<h4>Investigation Name : <b><span><%=investigation.getInvsttype() %></span></b></h4>
		<table class="my-table newtb " style="width: 100%">
		<tr style="background-color: #4E7894 !important;color: white !important;height: 20px !important" class='printrow'>
		<td style="width: 50%" class='invname'>Name</td>  <td style="width: 10%" class='invname'>Findings</td> <td class='invname'>Methods</td> <td>Unit</td> 
		</tr>
		
		<%for(Investigation findings:investigation.getFindinglist()){ %>
		
		<tr >
		<td class='invname'><%=findings.getInvstname()%></td>
		<td class='invname'><%=findings.getFindings()%></td>
		<td class='invname'><%=findings.getMethods()%></td>
		<td class='invname'><%=findings.getInvstUnit()%></td>
		</tr>
		<%} %>
		</table>
		
		<%if(!investigation.getRemark().equals("")){ %>
		<div style="margin-top: 8px;">
		<label>Remark :</label>
		<span><%=investigation.getRemark() %></span>
		</div>
		<%} %>
		
		</div>
		<%} %>
		
		
	</div>	
		<%} %>
		
<%} %>


</div>
</div>
<script>
bkLib.onDomLoaded(function() {
    
	 //new nicEditor().panelInstance('declarationNotes');
	 /* new nicEditor({maxHeight : 250}).panelInstance('templatesec');
	 $('.nicEdit-panelContain').parent().width('98%');
	 $('.nicEdit-panelContain').parent().next().width('98%');
	 
	 $('.nicEdit-main').width('100%');
	  */
	// $('.nicEdit-main').height('480px');
	  
	  $('.xx').each(function() {
		  var x=this.id; 
		  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70,minHeight : 70}).panelInstance(this.id);	  
	  });
	  
	
});

function setapprov(){
	document.getElementById('approve').value='1';
	document.getElementById('invfrm').submit();
	
}

function printit(){
	
	
	 var printContents = document.getElementById('printmulti').innerHTML;
    var originalContents =document.getElementById('editmulti').innerHTML;

   /*  document.body.innerHTML */
   document.getElementById('editmulti').innerHTML= printContents;

    window.print();

   /*  document.body.innerHTML  */
   document.getElementById('editmulti').innerHTML= originalContents;
   document.getElementById('printmulti').innerHTML=printContents
}

function reload(){
	 document.getElementById('it').submit();
}
</script>