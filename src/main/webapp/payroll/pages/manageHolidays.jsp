<!doctype html>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
  
    <link href="payroll/css/glDatePicker.default.css" rel="stylesheet" />
    <script src="payroll/js/glDatePicker.min.js"></script>
    <script type="text/javascript" src="payroll/js/payrollmaster.js"></script>
    
    <style>
        .padright {
            padding-left: 40px;
        }
        .table.table {
            color: RGBA(85, 85, 85, 0.85);
            background-color: #fff;
        }

        .comtitle {
            font-size: 13px;
            background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
            color: rgb(255, 255, 255);
        }

        .marbot25 {
            margin-bottom: 25px;
        }

        .editcompany {
            float: right;
            font-size: 17px;
            color: #fff;
        }

        .borright {
            border-right: 1px dashed rgb(192, 192, 192);
        }

        .buildinglogo {
            width: 60%;
            margin-top: 30px;
        }
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
            border-top: 0;
           
        }
        .miheight {
            min-height: 650px;
        }
        .my-table th {
            background-color: #424A5D;
            color: #fff !important;
            border-bottom: 1px solid #DFD8D4;
            border-right: 1px solid #DFD8D4;
            border-top: 1px solid #DFD8D4;
            padding: 3px 3px 4px 5px;
            text-align: left;
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 0px 7px 0px 7px !important;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
                display: inline-block;
            }
        .sidebar-xs #header .branding {
            width: 100%;
            padding-top: 7px;
            text-align: center;
        }




        .container {
            margin: 20px auto;
        }

        .year {
            margin: 0 0 8px;
            color: #548383;
            text-align: center;
            font-size: 4em;
        }

        .description {
            margin: 0 0 64px;
            color: #acc2c2;
            text-align: center;
            font-size: 2em;
        }

        .ulmonth {
            display: flex;
            flex-wrap: wrap;
            width: 740px;
            margin: -14px auto -14px;
            list-style: none;
        }

        /* Safari/iOS fix: Adjusting both z-index and transform on <article>
   causes text anti-aliasing to die within subsequent <article>
   elements during the transition. Moving z-index to the parent <li>
   fixes this. */
        .limonth {
            position: relative;
            z-index: 1;
            width: 25%;
            height: 160px;
            transition: z-index;
            transition-delay: .4s;
        }

        article {
            position: absolute;
            top: 50%;
            left: 50%;
            border-bottom: 8px solid #dfe7e7;
            background-color: #fff;
            cursor: pointer;
            transform: translate(-50%, -50%) scale(.25);
            transition: transform .4s;
        }

        /* Firefox fix: The outline style stretches to include absolutely
   positioned child elements (in this case, the offset dismiss
   button). This looks weird. The fix is to have a <div> with no
   child elements, position it to have the same edges as <article>,
   and apply the outline style there. */
        .outline {
            position: absolute;
            z-index: -1;
            top: 0;
            bottom: -8px;
            left: 0;
            right: 0;
        }

        article:focus {
            outline: none;
        }

            article:focus .outline {
                outline: 4px solid #dab08c;
            }

        .dismiss {
            display: block;
            opacity: 0;
            position: absolute;
            top: -28px;
            right: -28px;
            width: 48px;
            height: 48px;
            border: 4px solid #fff;
            border-radius: 50%;
            color: #fff;
            background-color: #666;
            cursor: pointer;
            transition: opacity .4s;
        }

            .dismiss::before {
                content: '\f00d';
                display: block;
                position: absolute;
                top: 50%;
                left: 50%;
                font: 1.7em/1 'FontAwesome';
                transform: translate(-50%, -50%);
            }

        .binding {
            height: 40px;
            background-color: #dab08c;
        }

            .binding::before,
            .binding::after {
                content: '';
                display: block;
                position: absolute;
                top: 8px;
                width: 24px;
                height: 24px;
                border-radius: 50%;
                background-color: #fff;
            }

            .binding::before {
                left: 25%;
            }

            .binding::after {
                right: 25%;
            }

        article h1 {
            height: 52px;
            margin: 16px;
            text-align: center;
            font-size: 3.2em;
        }

        table {
            width: 592px;
            margin: 118px;
            table-layout: fixed;
            border-collapse: separate;
            border-spacing: 4px;
        }

        th {
            position: relative;
            width: 80px;
            height: 32px;
            padding: 0 0 12px;
            text-align: center;
        }

            th::after {
                content: '';
                display: block;
                position: absolute;
                top: 0;
                left: 0;
                width: 80px;
                height: 20px;
                background-color: #acc2c2;
                transition: opacity .4s;
            }

        td {
            position: relative;
            width: 80px;
            height: 64px;
            padding: 4px;
            vertical-align: top;
            background-color: #dfe7e7;
        }

            td:empty {
                background-color: transparent;
            }

        .is-holiday {
            color: #fff;
            background-color: #548383;
        }

        .day {
            opacity: 0;
            font-size: 1.1em;
            font-weight: bold;
            transition: opacity .4s;
        }

        .split {
            position: absolute;
            bottom: 4px;
            right: 4px;
        }

        .holiday {
            opacity: 0;
            margin-top: 8px;
            font-size: .8em;
            transition: opacity .4s;
        }

        .notes {
            width: 708px;
            margin: 64px auto 0;
            color: #548383;
            line-height: 1.8;
        }

        .inactive {
            pointer-events: none;
        }

        /* Chrome/Safari/iOS fix: Bumping up the z-index from the start of
   the expand animation until the end of the shrink animation
   prevents unnecessary repaints on subsequent <article> elements. */
        .active {
            z-index: 2;
            transition-delay: 0s;
        }

            .active article {
                cursor: auto;
                transform: translate(-50%, -50%) scale(1);
            }

       
        .active .dismiss,
        .active .day,
        .active .holiday {
            opacity: 1;
        }

        /* Chrome/Safari/iOS fix: The centered "Sun" text jumps to the right
   when the transition ends. Oddly enough, setting text-indent to 0%
   prevents this. */
        .active th {
            text-indent: 0%;
        }

            .active th::after {
                opacity: 0;
            }

    </style>


</head>





<body id="his" class="appWrapper sidebar-xs-forced">

       
        <section id="content">

             <div class="col-lg-6 col-md-6" style="background-color: #efefef;">
					<a>
                        <h4><strong>Holiday</strong> Setting</h4>
                       
                    </a>
                    
                    
            </div>
            <div class="col-lg-6 col-md-6" style="background-color: #efefef;">
					<a>
                        <h4><strong><b>Holiday</b></strong> List</h4>
                       
                    </a>
                    
                    
            </div>
              
            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                    <%--     <div style="width:400px;">
                            <b  >Branch Name:  </b>    
                             <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" listValue="branch" cssClass="selectstyle form-control">
                            </s:select>  
                        </div> --%>
                        <div class="">
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <div id="showcalender" gldp-el="mydate" style="width:400px;z-index: 9 !important;"></div>
                            </div>
                        </div>
                    </div>

                        <table class="table my-table xlstable table-bordered" style="width: 40%; float:right;">
                            <thead>
                                <tr>
                                    <th>Sr. No.</th>
                                    <th>Date</th>
                                    <th>Events</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%int i=0; %>
                                <s:iterator value="holidayList">
                             
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="date"/></td>
                                    <td><s:property value="event"/></td>
                                </tr>
                                </s:iterator>
                            </tbody>

                        </table>

                </div>

               

            </div>

        </section>
        
        <!--/ CONTENT -->


    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="addholiday" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add Holiday</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Date</label>
                                <input type="text" class="form-control" id="dateholiday" name="date">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Holiday For</label>
                                    <input type="email" class="form-control" id="event" name="event">
                                <input type="hidden" id="selectedid" value=""/>
                            </div>    
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="sendToHolidays()">Add/Edit</button>
                </div>
            </div>
        </div>
    </div>

    
    <script type="text/javascript"><!--
    
       var listdata="";
       
  
        $(window).load(function () {
        
            getListHolidays();
            
            var today=new Date();
            var year=today.getFullYear();
            var month=today.getMonth();
            var day=today.getDate();              
        
   
            $('#showcalender').glDatePicker(
                {
                    showAlways: true,
                    cssName: 'default',
                    selectedDate: new Date(year, month, day),
                    specialDates: [
                                                          
                                   {
                            			date: new Date(2016,0, 14),
                            			data: { message: "Ambedkar Jayanti"},
                            			repeatMonth: false
                                   },     
                                  
                        
                    ],
                    onClick: function (target, cell, date, data) {
                        
                        var dd = date.getDate();
    					var mm = date.getMonth()+1; //January is 0!

  					    var yyyy = date.getFullYear();
    					if(dd<10){
        						dd='0'+dd
    					} 
    					if(mm<10){
        						mm='0'+mm
    					} 
    					var selectedDate = dd+'/'+mm+'/'+yyyy;                        
                        showInputModel(selectedDate,data);
                    }
                }
                );
        });
        
        
        function showInputModel(date,data) {
        
        
           document.getElementById("dateholiday").value=date;
           
           if(data!=null){
           
                var str=data.message.split("/"); 
                document.getElementById("event").value=str[0];
                document.getElementById("selectedid").value=str[1];
                
           }
           else {
                document.getElementById("event").value="";
                document.getElementById("selectedid").value="0";
                  
           }
          
           $('#addholiday').modal( "show" ); 
        
        
        }
        
        
        function getListHolidays() {
        
                var url="listholidaysPayrollMaster";
             
   				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
				}   

    			req.onreadystatechange = getListHolidaysRequest;
    			req.open("GET", url, true); 
              
    			req.send(null);
        
        }
        function getListHolidaysRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
 			
 				 listdata=req.responseText;
 			 }
   	 		}
		}
        
        
      
    </script>

</body>
</html>
