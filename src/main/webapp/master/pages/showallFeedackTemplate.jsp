<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<style>
.text-center{
text-align: center !important;
}
</style>
<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>
	<div class="row details">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
</div><h4>&nbsp;&nbsp;FeedbacK Master</h4>
</div>
	<div class="row ">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<br>									
<s:form action="showallFeedackTemplateMaster" theme="simple" id = "feedbackform">


<div class="form-inline">
			<div class="form-group">
			<div class="form-group">
		
				<s:select name="treatmenttype" id="treatmenttype" 
				list="#{'opd':'OPD','ipd':'IPD','hd':'HD'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			
			
					<div class="form-group">
					<s:submit value="go" theme="simple" cssClass="btn btn-primary"></s:submit>
					</div>
					<div class="form-group">
					<A class="btn btn-primary" HREF='addfeedbackMaster' >Add Template</A>
					</div>
			<br>
			<br>
			</div>
			</div>
			</s:form>	
		
			<table class="my-table xlstable" style="width: 100%">
			<tr style="text-align: center;">
			<th  class="text-center">Sr. No. </th>
			<th  class="text-center" style="width: 90%">Feedback Template</th>
			<th class="text-center">edit</th>
			<th class="text-center">delete</th>
			
			
			</tr>
			<%ArrayList<Client> list=(ArrayList<Client>)request.getAttribute("feedbacklist");
			int val=0;
			%>
				<s:iterator value="feedbacklist">
			<tr>
			 <td class="text-center"><%=val+1 %></td>
				<%-- <td class="text-center"><s:property value="feedbackname"/><input type="hidden" name='feedback<s:property value="feedbackid"/>' value='<s:property value="feedbackname"/>'/></td> --%>
				 <td class="text-center"><%=list.get(val).getFeedbackname().toString() %><input type="hidden" name='feedback<s:property value="feedbackid"/>' value='<s:property value="feedbackname"/>'/></td> 
				<td class="text-center"> <a href="#" onclick="opencPopup('editFeedBackMaster?id=<s:property value="feedbackid" />')"> <i class="fa fa-edit"></i></a></td>
				<td class="text-center"><a href="deleteFeedBackMaster?id=<s:property value="feedbackid"/>"><i class="fa fa-trash-o"></i></a></td>	
  <%val++; %>
			</tr>
			</s:iterator>
		</table>
		<br>
	
</div>
<br>
<br>

</div>
