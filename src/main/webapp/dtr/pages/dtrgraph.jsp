<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->



<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">




    <!-- ============================================
    ================= Stylesheets ===================
    ============================================= -->
    <!-- vendor css files -->
   <link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="mis/assets/css/vendor/animate.css">
        <link rel="stylesheet" href="mis/assets/css/vendor/font-awesome.min.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/animsition/css/animsition.min.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/morris/morris.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/rickshaw/rickshaw.min.css">
   


    <!-- project main css files -->
      <link rel="stylesheet" href="mis/assets/css/main.css">
    <!--/ stylesheets -->
    <!-- ==========================================
    ================= Modernizr ===================
    =========================================== -->
    <script src="mis/assets/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    <!--/ modernizr -->

   


</head>





<body id="his" class="appWrapper sidebar-xs-forced">


    <div id="wrap">
        <!-- ===============================================
        ================= HEADER Content ===================
        ================================================ -->
       
        <!--/ HEADER Content  -->
        
        
        <%@page import="com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO"%>
<%@page import="com.apm.AssesmentForms.eu.bi.AssessmentFormDAO"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.AssesmentForms.web.action.Template"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<script type="text/javascript" src="assesmentForms/js/createNewAssesmentForm.js"></script>
<script type="text/javascript" src="assesmentForms/js//jquery.table2excel.js"></script>
<%@page import="java.util.ArrayList"%>
<script type="text/javascript">
$(document).ready(function(){
document.getElementById("linechart1").style.display='none';
document.getElementById("linechart2").style.display='none';
document.getElementById("linechart13").style.display='none';
document.getElementById("linechart14").style.display='none';
	<%-- <%String columnStr = (String)session.getAttribute("columnStr");%>
	var columnstr1 = '<%=columnStr%>';
	var temp = columnstr1.split("#");
	//alert(temp);
	//alert(temp.length);
	
	var i = 0;
		<%ArrayList<String> list1 = (ArrayList<String>)session.getAttribute("assessmentFormsList");
     	for(String s : list1){%>
     	//alert(temp[i]);
     	var fieldId1 = temp[i];
     	//alert(fieldId1);
		document.getElementById(fieldId1).innerHTML = '<%=s%>';
		
		i++;
		<%}%>
      --%>
	
      
      <%Template template = (Template)session.getAttribute("assmnttemplatedetails");%>
  	<%if(template.isIncludeImage()){%>
  		document.getElementById('addcanvasdiv').style.display = 'none';
  	<%}%>
  	
	
	<%String imagedata = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata);%>
	var dataURL = '<%=imagedata%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas = document.getElementById('editupdateimgTemplate');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj, 0, 0);
    
    var iwidth = imageObj.width;
    var iheight = imageObj.height;
    
    //alert(iwidth + '/' + iheight)
    };
	
});	

function editUpdateImageCanvas(){
	
	<%String imagedata1 = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata1);%>
	var dataURL = '<%=imagedata1%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas1 = document.getElementById('Background');
    var context1 = canvas1.getContext('2d');
    context1.drawImage(imageObj, 0, 0);
   
    };
	
	 $( '#editimageCanvas' ).modal( "show" );
	
}



var i = 0;

function saveTemplateDetails(){
	var Pic;
	 for(var i in LAYERS){
		  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
		// alert(i);
	}
	 
	 document.getElementById('imageData').value = Pic;
	 
	 document.getElementById('temp_form').submit();
}
/* 
function print(){
	alert('hi');
	window.print();
} */

</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>DTR Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="">
										<div>


<s:hidden name="imageData" id="imageData"/>
											<div class="col-lg-12 col-md-12 col-xs-12 widthcan">
		<div class="panel panel-primary">
			<div class="panel-body">
			

		<div class="row hidden-lg hidden-md hidden-sm visible-print">
			<link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
		</div>
		<br><br>
	</div>
			
			
			<div class="row">
				<div class="col-lg-12 col-md-12 assesformhead" >
					<h3><s:property value="templateName"/> (<s:property value="clientFullName"/>)
						<s:if test="ipdid!=0">
							<s:property value="wardname"/> / <s:property value="bedname"/>
						</s:if>
					</h3>
				</div>
			</div>	
			
			
		<div class="row" id="addcanvasdiv">
			<div class="col-lg-12 col-md-12 col-xs-12">					
				<div class="col-lg-7 col-md-6 col-xs-12 canvwidth">
				`<canvas id="editupdateimgTemplate" height="450" width="700" ></canvas>
				</div>
			
				<!-- <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-print" style="margin-left: 5px;">
					<h3><a href="#" class="text-warning" onclick="editUpdateImageCanvas();" title="Edit Image"><i class="fa fa-edit"></i></a></h3>
				</div> -->
			</div>	
		</div>
			
				<br/>
				
				
				
			<%--  <div class="row">
				<div class="col-lg-2"></div>	
				<div class="col-lg-2 col-md-2">	
					<label>Client Name</label>
				</div>
				 <div class="col-lg-6 col-md-2">
					<s:textfield name="clientName" id="client" cssClass="form-control showToolTip" onclick="showPopUp2()" data-toggle="modal" data-target="#clientSearch" readonly="readonly" disabled="disabled"/>
					<s:hidden name="clientId" id="clientId"></s:hidden> 
				</div> 
			</div> --%>
			
			<s:if test="actionType != 2">
			
					<table class="table table-striped table-responsive" width="100%">
						<col width="20%"/>
						<col width="80%"/>
						<tr>
							<td><label>Practitioner</label></td>
							<td><s:property value="diaryUser"/></td>
				 		</tr>
			
			
			
					<tr>
						<td><label>Client</label></td>
						<td><s:property value="clientName"/></td>
					</tr>
			
			
			
					<tr>
						<td><label>Condition / Diagnosis</label></td>
						<td><s:property value="condition"/></td>
					</tr>
				</table>
			</s:if> 
        
       
        <section>
        
        <%int i =0; String bgcolor = "white"; %>					
			<%ArrayList<Assessment>  fieldNameList = (ArrayList<Assessment>)session.getAttribute("fieldNameList");%>
        	<% if(fieldNameList!=null){%>
        		<% for(Assessment assessment:fieldNameList){  %>
            <div class="container-fluid">
                <div class="row">
                	
                	
                	<%i++; %>
                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                            <div id="linechart<%=i %>" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                        </div>
                        <%i++; %>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                            <div id="linechart<%=i %>" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
                        </div>

                    </div>
					
                </div>
                
                
            </div>
             
            <% }%>
            <% }%>
             
        </section>
        <!--/ CONTENT -->






    </div>


    <!--/ Application Content -->
    <!-- ============================================
    ============== Vendor JavaScripts ===============
    ============================================= -->
   
     <script src="mis/assets/js/vendor/jRespond/jRespond.min.js"></script>

        <script src="mis/assets/js/vendor/sparkline/jquery.sparkline.min.js"></script>

        <script src="mis/assets/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

        <script src="mis/assets/js/vendor/animsition/js/jquery.animsition.min.js"></script>

        <script src="mis/assets/js/vendor/screenfull/screenfull.min.js"></script>
       

        <script src="mis/assets/js/vendor/raphael/raphael-min.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.v2.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.min.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.layout.min.js"></script>
        <script src="mis/assets/js/vendor/rickshaw/rickshaw.min.js"></script>

    	<%-- <script src="mis/assets/js/vendor/daterangepicker/moment.min.js"></script>
        <script src="mis/assets/js/vendor/daterangepicker/daterangepicker.js"></script>
        <script src="mis/assets/js/vendor/datetimepicker/js/bootstrap-datetimepicker.min.js"></script> --%>
        <script src="mis/assets/js/vendor/morris/morris.min.js"></script>

		 <script src="mis/assets/js/vendor/flot/jquery.flot.min.js"></script>
        <script src="mis/assets/js/vendor/flot-tooltip/jquery.flot.tooltip.min.js"></script>
        <script src="mis/assets/js/vendor/flot-spline/jquery.flot.spline.min.js"></script>

        <script src="mis/assets/js/vendor/easypiechart/jquery.easypiechart.min.js"></script>



        <script src="mis/assets/js/vendor/countTo/jquery.countTo.js"></script>
        <!--/ vendor javascripts -->





        <!-- ============================================
        ============== Custom JavaScripts ===============
        ============================================= -->
        <script src="mis/assets/js/main.js"></script>
        <!--/ custom javascripts -->



<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>


    <!--/ vendor javascripts -->
    <!-- ============================================
    ============== Custom JavaScripts ===============
    ============================================= -->
    <script src="assets/js/main.js"></script>
    <script src="assets/js/vendor/hichart/highcharts.js"></script>
    <script src="assets/js/vendor/hichart/exporting.js"></script>
    <!--/ custom javascripts -->



//calculate fields value
<%StringBuffer datestr = new StringBuffer(); String date = ""; int p = 1; 
StringBuffer actualshiftstr = new StringBuffer(); String actualshift = "";
StringBuffer prehdbpsystolicstr = new StringBuffer(); String prehdbpsystolic = "";
StringBuffer prehdbpdiastolicstr = new StringBuffer(); String prehdbpdiastolic = "";
StringBuffer posthdbpsystolicstr = new StringBuffer(); String posthdbpsystolic = "";
StringBuffer posthdbpdiastolicstr = new StringBuffer(); String posthdbpdiastolic = "";
StringBuffer dryweightstr = new StringBuffer(); String dryweight = "";
StringBuffer prehdweightstr = new StringBuffer(); String prehdweight = "";
StringBuffer posthdweightstr = new StringBuffer(); String posthdweight = "";
StringBuffer targetweightlossstr = new StringBuffer(); String targetweightloss = "";
StringBuffer actualweightlossstr = new StringBuffer(); String actualweightloss = "";
StringBuffer dialyserreusestr = new StringBuffer(); String dialyserreuse = "";
StringBuffer fbvstr = new StringBuffer(); String fbv = "";
StringBuffer bfrstr = new StringBuffer(); String bfr = "";
%>


<% for(Assessment assessment:fieldNameList){  
	
	 Connection  connection = Connection_provider.getconnection();
     AssessmentFormDAO  assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
     String clientassesmentfieldid = (String)session.getAttribute("clientassesmentfieldid");
     ArrayList<Assessment>dataList = assessmentFormDAO.getRepeatFormData(template,assessment.getFiledname(),clientassesmentfieldid);
	
     if(p==1){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    	 	datestr.append("'"+data.getFiledValue()+"'" + ",");
	    	 }
	     }
     }
     
     if(p==3){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 actualshiftstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     if(p==4){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 prehdbpsystolicstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     if(p==5){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 prehdbpdiastolicstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     if(p==6){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 posthdbpsystolicstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     
     if(p==7){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 posthdbpdiastolicstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     if(p==8){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 dryweightstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     if(p==9){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 prehdweightstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     if(p==10){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 posthdweightstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     if(p==11){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 targetweightlossstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     if(p==12){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 actualweightlossstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     if(p==15){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 dialyserreusestr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     
     if(p==16){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 fbvstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
     
     if(p==17){
	     for(Assessment data : dataList){
	    	 if(!data.getFiledValue().equals("")){
	    		 bfrstr.append(data.getFiledValue() + ",");
	    	 }
	     }
     }
	
	p++;
}
	if(datestr.length()!=0){
		date = datestr.substring(0,datestr.length()-1);
	}
	if(actualshiftstr.length()!=0){
		actualshift = actualshiftstr.substring(0,actualshiftstr.length()-1);
	}
	if(prehdbpsystolicstr.length()!=0){
		prehdbpsystolic = prehdbpsystolicstr.substring(0,prehdbpsystolicstr.length()-1);
	}
	if(prehdbpdiastolicstr.length()!=0){
		prehdbpdiastolic = prehdbpdiastolicstr.substring(0,prehdbpdiastolicstr.length()-1);
	}
	if(posthdbpsystolicstr.length()!=0){
		posthdbpsystolic = posthdbpsystolicstr.substring(0,posthdbpsystolicstr.length()-1);
	}
	if(posthdbpdiastolicstr.length()!=0){
		posthdbpdiastolic = posthdbpdiastolicstr.substring(0,posthdbpdiastolicstr.length()-1);
	}
	
	if(dryweightstr.length()!=0){
		dryweight = dryweightstr.substring(0,dryweightstr.length()-1);
	}
	if(prehdweightstr.length()!=0){
		prehdweight = prehdweightstr.substring(0,prehdweightstr.length()-1);
	}
	if(posthdweightstr.length()!=0){
		posthdweight = posthdweightstr.substring(0,posthdweightstr.length()-1);
	}
	if(targetweightlossstr.length()!=0){
		targetweightloss = targetweightlossstr.substring(0,targetweightlossstr.length()-1);
	}
	if(actualweightlossstr.length()!=0){
		actualweightloss = actualweightlossstr.substring(0,actualweightlossstr.length()-1);
	}
	if(dialyserreusestr.length()!=0){
		dialyserreuse = dialyserreusestr.substring(0,dialyserreusestr.length()-1);
	}
	if(fbvstr.length()!=0){
		fbv = fbvstr.substring(0,fbvstr.length()-1);
	}
	if(bfrstr.length()!=0){
		bfr = bfrstr.substring(0,bfrstr.length()-1);
	}
	
	
	












%>
	
				


    <script>
        $(function () {
        	
        	 <%int j =1;  %>					
			
        	<% if(fieldNameList!=null){%>
        		<% for(Assessment assessment:fieldNameList){  %>
        		<%if(j>2){ %>
            $('#linechart<%=j%>').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: ''
                },
                //subtitle: {
                //    text: 'Source: WorldClimate.com'
                //},
                xAxis: {
                    categories: [<%=date%>]
                },
                yAxis: {
                    title: {
                        text: '<%=assessment.getFiledname() %>'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series: [{
                    
                    <%if(j==3){%>
                    	name: 'Actual Shift',
                    	data: [<%=actualshift%>]
                    <%}else if(j==4){%>
                    	name: 'Pre HD BP Systolic',
                    	data: [<%=prehdbpsystolic%>]
                    <%}else if(j==5){%>
                    	name: 'Pre HD BP Diastolic',
                    	data: [<%=prehdbpdiastolic%>]
                    <%}else if(j==6){%>
                    	name: 'Post HD BP Systolic',
                    	data: [<%=posthdbpsystolic%>]
                    <%}else if(j==7){%>
                    	name: 'Post HD BP Diastolic',
                    	data: [<%=posthdbpdiastolic%>]
                    <%}else if(j==8){%>
                    	name: 'Dry weigh',
                    	data: [<%=dryweight%>]
                    <%}else if(j==9){%>
                    	name: 'Pre-HD Weight',
                    	data: [<%=prehdweight%>]
                    <%}else if(j==10){%>
                    	name: 'Post-HD Weigh',
                    	data: [<%=posthdweight%>]
                     <%}else if(j==11){%>
                    	name: 'Target Weight loss',
                    	data: [<%=targetweightloss%>]
                    <%}else if(j==12){%>
                    	name: 'Actual Weight Loss',
                    	data: [<%=actualweightloss%>]
                    <%}else if(j==15){%>
                    	name: 'Dialyser Reuse',
                    	data: [<%=dialyserreuse%>]
                    <%}else if(j==16){%>
                    	name: 'FBV',
                    	data: [<%=fbv%>]
                    <%}else{%>
                    	name: 'BFR',
                    	data: [<%=bfr%>]
                    <%}%>
                }]
            });
            
             <%}%>
             <%j++;%>
            <%}%>
            <%}%>

        });
    </script>

											
										</div>
									</div>
								</div>
							</div>
						</div>



	


</body>
</html>
