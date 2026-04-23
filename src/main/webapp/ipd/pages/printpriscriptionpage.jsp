<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.Ipd.web.form.IpdForm"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Font Awesome Example</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!-- <link href="_assets/newtheme/css/main.css" rel="stylesheet" />

<link href="common/css/printpreview.css" rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" /> -->

<style>

 body {
      margin: 300px;
      padding: 200px;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 500vh;
      background: #f0f0f0;
    }

.rectangle {
    width: 900px;
      /* Width of the rectangle */
    height: 120px; /* Height of the rectangle */
  /*   background-color: #4CAF50;  */ /* Background color */
    border: 2px solid #333;  /* Border around the rectangle */
    margin-top: 15%;
    margin-left: 20px;
    transform: translateX(0%);
    padding-top: 1%;
    
}


.rectangle1 {
    width: 900px;;  /* Width of the rectangle */
    height:70px; /* Height of the rectangle */
  /*   background-color: #4CAF50;  */ /* Background color */
    border: 2px solid #333;  /* Border around the rectangle */
    margin-top: 10px;
    margin-left: 20px;
}
label{

 font-size: 18px;
 font-weight: bolder;
}

.table>tbody>tr>td{

    padding: 5px 7px 5px 8px !important;
    line-height: 2.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: bold;
    font-size: 13px;
}

.table>thead>tr>th{

    padding: 5px 7px 2px 5px !important;
    line-height: 2.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: bold;
    font-size: 13px;
    
}

/* Style for Name and Designation */
.name-designation {
    position: fixed;
    bottom: 100px; /* Distance from the bottom to the footer */
    left: 50%;
    transform: translateX(-50%);
    text-align: center;
    background-color: #f1f1f1;
    padding: 10px;
    border-radius: 5px;
    font-size: 18px;
}

/* footer {
    background-color: #333;
    color: white;
    text-align: center;
    padding: 20px;
    position: relative;
}
 */
@media print{

.table>tbody>tr>td{

    padding: 5px 7px 2px 5px !important;
    line-height: 2.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: bold;
    font-size: 18px;
}

.table>thead>tr>th{

    padding: 5px 7px 2px 5px !important;
    line-height: 2.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: bold;
    font-size: 18px;
    
}

  body {
    margin-right: 80mm; /* Shifts content 50mm from the left */
  }

}

</style>
<body >
<%
	IpdForm ipdForm = (IpdForm) session.getAttribute("ipdFormCF");
%>
<div class="container" style="margin-left: 150px;">
 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 30%;">
 <div class="rectangle">
 
 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
 <div class="col-lg-7 col-md-7 col-xs-7 col-sm-7 text-left">
  <label>Patient Name :&nbsp;<s:property value="Client"/></label> 
 </div>
 <div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <label>Date :&nbsp;<s:property value="Date1"/></label>
 </div>
 </div>

 
 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 6px;">
 <div class="col-lg-7 col-md-7 col-xs-7 col-sm-7 text-left">
  <label>UHID :&nbsp;<s:property value="Abrivationid"/></label>
 </div>
 <div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <label>Age/Gender :&nbsp;<s:property value="Agegender"/></label>
 </div>
 </div>
 
 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 6px;">
 <div class="col-lg-7 col-md-7 col-xs-7 col-sm-7 text-left ">
  <label>Address :&nbsp;<s:property value="Address"/></label>
 </div>
 <div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <label>Phone No.:&nbsp;<s:property value="Contact"/> </label>
 </div>
 </div>
 
 
 
  </div>
 
 </div>
 
  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
  <div class="rectangle1">
  
    <i class="fa-solid fa-caret-up"  style="font-size:30px;margin-left: 25px;padding-top: 16px;"></i> 
    <label style="padding-top: 14px;">:</label>
    <%
	   ArrayList<Bed> ipdConditionids = (ArrayList<Bed>) session.getAttribute("finalConditions");
	%>
	   <%
		 for (Bed bed : ipdConditionids) {
	   %>			
   <label style="padding-top: 14px;">&nbsp;<%=bed.getConditionname()%></label>
  
    
   <%} %>
       </div>
  </div>
  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
  <label style="margin-left:20px;padding-top: 10px;"><b>Rx</b></label>
  
  </div>
  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
  

       
       <table class="table" style="margin-left: 20px;border-spacing: 40px;width: 100%;"> 
								<thead style="background-color: rgb(102 153 204 / 85%);"> 
									<tr class="headings" > 
									<th style="width: 1%;">Sr.</th>
									<th style="width: 6%;">Name of Drug</th>
									<th style="width: 2%;">Dose</th>
									<th style="width: 3%;">Frequency</th>
									<th style="width: 2%;">Days</th>
									<th style="width: 10%;">Remark</th>
									
									</tr>
								</thead>
								<tbody>
									<%int i=1; %>
									<s:iterator value="priscriptionListprachi">
									
									<tr>
									<td style="width: 1%;"><%=i %></td>
									<td style="width: 8%;"><s:property value="Mdicinenametxt"/></td>
									<td style="width: 2%;"><s:property value="unit"/>&nbsp;<s:property value="unitextension"/></td>
									<td style="width: 3%;"><s:property value="Regional"/></td>
									<td style="width: 2%;"><s:property value="Priscdays"/>&nbsp;दिन</td>
									<td style="width: 10%;"><s:property value="Prisctimename"/></td>
									
									</tr>
									
									<%i++; %>
									
									</s:iterator>
									
									
									
									</tbody>
									</table>
													
 </div>
									<br>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<label style="padding-top: 10px;margin-left: 20px;"><b>INSTRUCTIONS:</b></label>
									<br><br>
								 <p style="margin-left: 20px;font-size: 17px;line-height: 1.8;">
									• ढीले सूती कपड़े पहनें।<br>
									• दिन में 2 बार नहाएं और अपने अंडरगारमेंट्स रोजाना बदलें।<br>
									• ऐसे वातावरण से बचें जहां पसीना आता हो।<br>
                                    • अपने कपड़े अलग रखें और उन्हें अलग धोएं।<br>
                                    • हमेशा डॉक्टर द्वारा बताई गई दवा की अवधि का पालन करें।<br>
                                    • स्वयं दवा न लें, कोई ओटीसी क्रीम या तेल, मेहंदी, हेयर डाई न लगाएं।
                                  
                                  </p>
									</div>
									
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				 
				 <label style="padding-top: 10px;margin-left: 20px;"><b>FOLLOW UP:</b></label>
				 <br>
			      <div style="font-size: 23px;margin-left: 20px;">
			      <%=ipdForm.getDiscadvnotes().toString()%>
			   
			      
			      </div>
			   </div>						
			</div>					
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 name-designation">
				
									
				<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
				 </div>
					<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
					<p style="font-size: 16px;font-weight: bold;">
					
					
					
					
					&nbsp;Dr. Prachi Bhattar <br>
					M.D.(Skin), Gold Medalist<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;Reg.No.CGMC/6643
					
					</p>
				 </div>
				 </div>	
				 
						
					
				  <div class="col-lg-4 col-md-4 hidden-print" style="margin-left: 50%; align-items: center;padding-top: 10px;">
							<input type="button" class="btn btn-primary " value="Print" onclick="printpage()">
						</div>	
	
 
  
</body>
</html>