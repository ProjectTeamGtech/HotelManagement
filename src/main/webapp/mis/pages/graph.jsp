<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Expence.eu.entity.Expence"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<%@page import="com.apm.Mis.web.form.MisChartForm"%>
<html> 

   <%String graphName = (String)session.getAttribute("graphName"); %>

  <%MisChartForm misChartForm = (MisChartForm)session.getAttribute("misChartForm");%>
    <%String action = (String)session.getAttribute("graphaction");%>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>YUVICARE</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

    </head>

    <body id="" class="appWrapper">
    
    <div class="row">
    <div class="col-lg-12 col-md-12">
    
    <%if(action.equals("opd")){ %>
       <div id="opdg" style="width:100%;height: 500px; margin: 0 auto"></div>		
     <script>
   $(function () {
    // Create the chart
    $('#opdg').highcharts({
       chart: {
            type: 'column'
        },
        title: {
            text: '<%=graphName%>'
        }, 	
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '<%=graphName%>'
            }

        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.1f}%'
                }
            }
        },

        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
        },

        series: [{
            name: 'OPD',
            colorByPoint: true,
            data: [{
                name: 'Booked',
                y: <%=misChartForm.getBookedAppointment()%>,
                drilldown: 'Booked'
            }, {
                name: 'Completed',
                y: <%=misChartForm.getTotalCompleted()%>,
                drilldown: 'Completed'
            }, {
                name: 'DNA',
                y: <%=misChartForm.getTotaldna()%>,
                drilldown: 'DNA'
            },
            {
                name: 'Not Completed',
                y: <%=misChartForm.getNotCompleted()%>,
                drilldown: 'NOT Completed'
            },{
                name: 'OT',
                y: 0.0,
                drilldown: 'OT'
             }
            ]
        }],
        drilldown: {
            series: [{
                name: 'Booked',
                id: 'Booked',
                data: [
                    [
                        'v11.0',
                        24.13
                    ],
                    [
                        'v8.0',
                        17.2
                    ],
                    [
                        'v9.0',
                        8.11
                    ]
                ]
            }, {
                name: 'Completed',
                id: 'Completed',
                data: [
                    [
                        'v40.0',
                        5
                    ],
                    [
                        'v41.0',
                        4.32
                    ],
                    [
                        'v42.0',
                        3.68
                    ],
                    [
                        'v39.0',
                        2.96
                    ],
                    [
                        'v36.0',
                        2.53
                    ],
                    
                    [
                        'v35.0',
                        0.85
                    ],
                    [
                        'v38.0',
                        0.6
                    ],
                    [
                        'v30.0',
                        0.14
                    ]
                ]
            }, {
                name: 'DNA',
                id: 'DNA',
                data: [
                    [
                        'v35',
                        2.76
                    ],
                    [
                        'v36',
                        2.32
                    ],
                    [
                        'v37',
                        2.31
                    ],
                    [
                        'v34',
                        1.27
                    ],
                    [
                        'v32',
                        0.15
                    ]
                ]
            },
            {
                name: 'Not Completed',
                id: 'Not Completed',
                data: [
                    [
                        'v35',
                        2.76
                    ],
                    [
                        'v36',
                        2.32
                    ],
                    [
                        'v37',
                        2.31
                    ],
                    [
                        'v34',
                        1.27
                    ],
                    [
                        'v32',
                        0.15
                    ]
                ]
            },{
                name: 'OT',
                id: 'OT',
                data: [
                    [
                        'v35',
                        2.76
                    ],
                    [
                        'v36',
                        2.32
                    ],
                    [
                        'v37',
                        2.31
                    ],
                    [
                        'v34',
                        1.27
                    ],
                    [
                        'v32',
                        0.15
                    ]
                ]
            }
            ]
        }
     });
    
    });
  </script>         
    <% } 
      else if(action.equals("ipd")){%>

        <div id="ipdg" style="width:100%;height: 500px; margin: 0 auto"></div>
        <SCRIPT type="text/javascript">
        $(function () {
         // Create the chart
         $('#ipdg').highcharts({
             
                 chart: {
            type: 'column'
        },
        title: {
            text: '<%=graphName%>'
        }, 	
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '<%=graphName%>'
            }

        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.1f}%'
                }
            }
        },

        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
        },

        series: [{
            name: 'IPD',
            colorByPoint: true,
            data: [{
                name: 'New Admission',
                y: <%=misChartForm.getNewadmission()%>,
                drilldown: 'New Admission'
            }, {
                name: 'Inhouse Patients',
                y: <%=misChartForm.getInhousepatients()%>,
                drilldown: 'Inhouse Patients'
            }, {
                name: 'Discharge Patients',
                y: <%=misChartForm.getDischargepatients()%>,
                drilldown: 'Discharge Patients'
               }
            ]
        }],
        drilldown: {
            series: [{
                name: 'New Admission',
                id: 'New Admission',
                data: [
                    [
                        'v11.0',
                        24.13
                    ],
                    [
                        'v8.0',
                        17.2
                    ],
                    [
                        'v9.0',
                        8.11
                    ]
                ]
            }, {
                name: 'Inhouse Patients',
                id: 'Inhouse Patients',
                data: [
                    [
                        'v40.0',
                        5
                    ],
                    [
                        'v41.0',
                        4.32
                    ],
                    [
                        'v42.0',
                        3.68
                    ],
                    [
                        'v39.0',
                        2.96
                    ],
                    [
                        'v36.0',
                        2.53
                    ],
                    
                    [
                        'v35.0',
                        0.85
                    ],
                    [
                        'v38.0',
                        0.6
                    ],
                    [
                        'v30.0',
                        0.14
                    ]
                ]
            }, {
                name: 'Discharge Patients',
                id: 'Discharge Patients',
                data: [
                    [
                        'v35',
                        2.76
                    ],
                    [
                        'v36',
                        2.32
                    ],
                    [
                        'v37',
                        2.31
                    ],
                    [
                        'v34',
                        1.27
                    ],
                    [
                        'v32',
                        0.15
                    ]
                ]
            }
            ]
        }   
         });
       });
      
      </SCRIPT>
     <%}%>
    </div>
    </div>




  

 </body>
</html>
