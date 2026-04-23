<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="plugin/checkbox/style.css">

<style>
.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
.table-bordered>thead>tr>td {
    background-color: #fff; }
    .setmiheight{
    	    height: 55px;
    }
  .clockdate { margin-bottom: 5px; font-size: 16px; display: block;}
  .clocktime { font-size: 14px; font-family: "Courier"; color: #000; margin: 2px; display: block;margin-top: -5px; }
</style>
</head>
<body>
<div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 hidden topback2">
                        <div class="col-md-12 martop6">
                            <div class="form-inline">
                            	<div class="form-group titlepadright">
                                    <label for="exampleInputEmail3">Patient Wise History <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></label>
                                </div>
                                 <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <input type="text" class="form-control" name="fromdate" placeholder="Search Patient">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <input type="text" class="form-control" name="fromdate" placeholder="From Date">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <input type="text" class="form-control" name="todate" placeholder="To Date">
                                </div>
                                <button type="submit" class="btn btn-primary">Go</button>
                            </div>
                        </div>
                    </div> 
                    
                    <div class="row hidden">
                        <div class=" col-lg-12 col-md-12 col-sm-12">
                            <table class="table table-bordered" cellspacing="0" width="100%" id="machinetable" style="width:52%;">
                                <thead>
                                    <tr class="tableback">
                                        <th>Sr.No</th>
                                        <th>Patient Name</th>
                                        <th>Age / Gender</th>
                                        <th>Ward / Bed</th>
                                        <th>Repeat Diet</th>
                                        <th>History</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>01</td>
                                        <td>Mr.Sukhendu Das</td>
                                        <td>42 / Male</td>
                                        <td>SEMIPVT / 23</td>
                                        <td>until discharge</td>
                                        <td><a href="#" onclick="openPopup('printdiethistoryInvestigation')">view</a></td>
                                    </tr>
                                    <tr>
                                        <td>02</td>
                                        <td>Mr.Jaysigh Rathod</td>
                                        <td>54 / Male</td>
                                        <td>SEMIPVT / 25</td>
                                        <td>2 week</td>
                                        <td><a href="#" onclick="openPopup('printdiethistoryInvestigation')">view</a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topback2 setmiheight">
                        <div class="col-lg-4 col-md-4 martop6">
                            <div class="form-inline">
                            	<div class="form-group titlepadright">
                                    <label for="exampleInputEmail3">Meat Delivery Dashboard <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></label>
                                </div>
                                	<div class="form-group">
                                   <select name="invsttype" class="form-control chosen-select">
											    <option value="0">Select Ward</option>
											    <option value="1">Semiprivate</option>
											    <option value="2">Private</option>
											    <option value="3">General 01</option>
											    <option value="4">General 02</option>
											    <option value="5">General 03</option>
											    <option value="6">ICU 01</option>
											    <option value="7">ICU 02</option>
											</select>
											
                                </div>	
                                </div>
                                
                            </div>
                            <div class="col-lg-4 col-md-4 martop6">
						  <div class="form-group" style="text-align: center;">
						    <div style="clear:both;">
							    <div id="clock1"></div>
							  </div>
							  </div>
						  </div>
						  <div class="col-lg-4 col-md-4 martop6">
						  <div class="form-group" style="text-align: right;">
						    <label for="exampleInputName2">Total Order: 2</label><br>
						    <label for="exampleInputEmail2">Yet To Completed : 1</label>
						  </div>
                        </div>
                        </div>
						  
                        </div>
                        
                        
                    
                    
                    <div class="row">
                        <div class=" col-lg-12 col-md-12 col-sm-12">
                            <table class="table table-bordered" cellspacing="0" width="100%" id="machinetable" style="width:100%;">
                                <thead>
                                    <tr class="tableback">
                                        <th>Patient Name</th>
                                        <th>Ward / Bed</th>
                                        <th style="text-align: center;background-color: #9BC189;">07:00 AM - 08:00 AM<br>Breakfast</th>
                                        <th style="text-align: center;background-color: #6DAAB2;">09:00 AM - 10:00 AM<br>Midmorning Snack</th>
                                        <th style="text-align: center;background-color: #927BA9;">11:00 AM - 12:00 PM<br>Lunch</th>
                                        <th style="text-align: center;background-color: #AE7786;">04:00 PM - 05:00 PM<br>Midafternoon Snack</th>
                                        <th style="text-align: center;background-color: #F4555A;">07:00 PM - 08:00 PM<br>Dinner</th>
                                        <th style="text-align: center;background-color: #FCA052;">09:00 PM - 10:00 PM<br>Evening Snack</th>
                                       
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Mr.Sukhendu Das</td>
                                        <td>SEMIPVT / 23</td>
                                        <td>
													  <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Orange Juice / 75 ML</label>
												      </div>
										</td>
                                        <td> <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label"> Applesauce / 50 ML</label>
												      </div></td>
                                        <td> <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Pudding / 75 ML</label>
												      </div> </td>
                                        <td>
                                        	 <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ice Cream / 75 ML</label>
												      </div>
                                        </td>
                                        <td>
                                        	 <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Pureed Peaches / 75 ML</label>
												      </div>
                                        </td>
                                        <td>
                                        	 <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Milk / 75 ML</label>
												      </div>
                                        </td>
                                       
                                    </tr>
                                    <tr>
                                        <td>Mr.Jaysigh Rathod</td>
                                        <td>SEMIPVT / 25</td>
                                        <td>
                                        			<div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">AppleJuice / 75 ML</label>
												      </div>
                                        </td>
                                        <td style="text-align: center;">-</td>
                                        <td>
                                        			<div>
												        <input id="checkbox-2" class="checkbox-custom" name="checkbox-1" type="checkbox">
												        <label for="checkbox-2" class="checkbox-custom-label">AppleJuice / 75 ML</label>
												      </div>
                                        </td>
                                        <td style="text-align: center;">-</td>
                                        <td>
                                        			<div>
												        <input id="checkbox-3" class="checkbox-custom" name="checkbox-1" type="checkbox">
												        <label for="checkbox-3" class="checkbox-custom-label">AppleJuice / 75 ML</label>
												      </div>
                                        </td>
                                        <td style="text-align: center;">-</td>
                                       
                                        
                                        
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    
                     <script>
						(function($){$.clock={version:"2.0.1",locale:{}};t=[];$.fn.clock=function(d){var c={it:{weekdays:["Domenica","Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato"],months:["Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre"]},en:{weekdays:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],months:["January","February","March","April","May","June","July","August","September","October","November","December"]},es:{weekdays:["Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"],months:["Enero","Febrero","Marzo","Abril","May","junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"]},de:{weekdays:["Sonntag","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag"],months:["Januar","Februar","März","April","könnte","Juni","Juli","August","September","Oktober","November","Dezember"]},fr:{weekdays:["Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"],months:["Janvier","Février","Mars","Avril","May","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"]},ru:{weekdays:["???????????","???????????","???????","?????","???????","???????","???????"],months:["??????","???????","????","??????","???","????","????","??????","????????","???????","??????","???????"]}};return this.each(function(){$.extend(c,$.clock.locale);d=d||{};d.timestamp=d.timestamp||"z";y=new Date().getTime();d.sysdiff=0;if(d.timestamp!="z"){d.sysdiff=d.timestamp-y}d.langSet=d.langSet||"en";d.format=d.format||((d.langSet!="en")?"24":"12");d.calendar=d.calendar||"true";if(!$(this).hasClass("jqclock")){$(this).addClass("jqclock");}var e=function(g){if(g<10){g="0"+g}return g;},f=function(j,n){var r=$(j).attr("id");if(n=="destroy"){clearTimeout(t[r]);}else{m=new Date(new Date().getTime()+n.sysdiff);var p=m.getHours(),l=m.getMinutes(),v=m.getSeconds(),u=m.getDay(),i=m.getDate(),k=m.getMonth(),q=m.getFullYear(),o="",z="",w=n.langSet;if(n.format=="12"){o=" AM";if(p>11){o=" PM"}if(p>12){p=p-12}if(p===0){p=12}}p=e(p);l=e(l);v=e(v);if(n.calendar!="false"){z=((w=="en")?"<span class='clockdate'>"+c[w].weekdays[u]+", "+c[w].months[k]+" "+i+", "+q+"</span>":"<span class='clockdate'>"+c[w].weekdays[u]+", "+i+" "+c[w].months[k]+" "+q+"</span>");}$(j).html(z+"<span class='clocktime'>"+p+":"+l+":"+v+o+"</span>");t[r]=setTimeout(function(){f($(j),n)},1000);}};f($(this),d);});};return this;})(jQuery);
						
						/* Now apply on document ready to jsbin page */
						$(document).ready(function(){
						
						$.clock.locale = {"pt":{"weekdays":["Domingo","Segunda-feira", "Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira", "Sábado"],"months":["Janeiro","Fevereiro","Março","Abril", "Maio","Junho","Julho","Agosto","Setembro","October","Novembro", "Dezembro"] } };
						
						$("#clock1").clock();
						});
						
                </script>
                
                
</body>
</html>