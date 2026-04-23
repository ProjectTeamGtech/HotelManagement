<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="plugin/checkbox/style.css">

<link rel="stylesheet" type="text/css" href="plugin/notification/tappifications.css">
	

	
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
    .belowhed{
    	background-color: cadetblue !important;
    }
    .browncolor{
    	    background-color: brown !important;
    }
    .catitle{
    	    background-color: #FCCE75;
    padding: 5px;
    }
    .backkichet{
    	    padding-right: 0px;
    background-color: #957EAC;
    min-height: 540px;
    padding-left: 0px;
    }
    .breakfast{
    	    color: #fff;
    background-color: #9BC28B;
    }
    .midmorning{
    	    color: #fff;
    background-color: #71AAB1;
    }
    .lunch{
    	color: #fff;
    background-color: #927DAA;
    }
     .midafternoon{
    	color: #fff;
    background-color: #B37988;
    }
     .dinner{
    	color: #fff;
    background-color: #F4555A;
    }
     .midevening{
    	color: #fff;
    background-color: #FE9F4C;
    }
    
    .breakfast1{
    	    color: #9BC28B;
    }
    .midmorning1{
    color: #71AAB1;
    }
    .lunch1{
    	color: #927DAA;
    }
     .midafternoon1{
    	color: #B37988;
    }
     .dinner1{
    color: #F4555A;
    }
     .midevening1{
   color: #FE9F4C;
    }
    .btnclock{
    	    background-color: transparent;
    color: #555;
    font-size: 18px;
    border: none;
    }
    
  .clockdate { margin-bottom: 5px; font-size: 16px; display: block;}
  .clocktime { font-size: 14px; font-family: "Courier"; color: #000; margin: 2px; display: block;margin-top: -5px; }
</style>
</head>
<body>
<div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topback2 setmiheight">
                    <div class="col-lg-7 col-md-7" style="padding-left:0px;border-right: 1px solid; min-height: 585px;">
                    <div class="col-lg-3 col-md-3 martop6" style="padding-left: 0px;">
                            <div class="form-inline">
                            	<div class="form-group titlepadright">
                                    <label for="exampleInputEmail3" class="catitle">For Packing Team </label>
                                </div>
                                <button class="tappification-info btnclock" title="Time Slot For Meal"><i class="fa fa-clock-o" aria-hidden="true"></i></button>
                            </div>
                            
                            
                        </div>
                        <div class="col-lg-6 col-md-6 martop6">
						  <div class="form-group" style="text-align: center;">
						    <div style="clear:both;">
							    <div id="clock1"></div>
							  </div>
							  </div>
						  </div>
						  <div class="col-lg-3 col-md-3 martop6">
						  	<div class="form-group" style="text-align: right;">
						    <label for="exampleInputName2">Total Packing: 2</label><br>
						    <label for="exampleInputEmail2">Yet To Packed : 1</label>
						  </div>
						  </div>
                    </div>
                    <div class="col-lg-5 col-md-5">
                    <div class="col-lg-3 col-md-3 col-sm-3" style="padding-left:0px;">
                    	<label for="exampleInputEmail3" class="catitle">For Kichen Team </label>
                    </div>
                    <div class="col-lg-9 col-md-9 col-sm-9" style="padding-right:0px;">
                    	<form class="form-inline">
                    	<div class="form-group">
							    <select name="invsttype" class="form-control chosen-select">
											    <option value="0">Seacrh Day Plan</option>
											    <option value="1">Breakfast</option>
											    <option value="2">Midmorning Snack</option>
											    <option value="3">Lunch</option>
											    <option value="4">Midafternoon Snack</option>
											    <option value="5">Dinner</option>
											    <option value="6">Midevening Snack</option>
											</select>
							  </div>
							  <div class="form-group">
							    <select name="invsttype" class="form-control chosen-select">
											    <option value="0">Search Diet</option>
											    <option value="1">Oral Liquid Diet</option>
											    <option value="2">Balance Soft Diet</option>
											    <option value="3">Diabetic Diet</option>
											    <option value="4">Cardiac Diet (low fat diet)</option>
											    <option value="5">Renal Diet (low fat diet)</option>
											    <option value="6">Gastro Balnd Diet</option>
											    <option value="7">High Protein Diet</option>
											    <option value="8">High Protein Diabetic Diet</option>
											    <option value="9">Diabetic Renal Diet</option>
											</select>
							  </div>
							</form>
                    </div>
                    </div>
                        </div>
                        
                    </div>
                    
                    
                    
                    <div class="row">
                        <div class=" col-lg-12 col-md-12 col-sm-12">
                        <div class="col-lg-7 col-md-7 col-sm-7" style="padding-left:0px;">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>Sr.No</th>
                                    <th>Patient Name</th>
                                    <th>Ward / Bed</th>
                                    <th>View Diet</th>
                                    <th>Packed</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td>1</td>
                            	<td>Mr.Sukhendu Das</td>
                            	<td>SEMIPVT / 23</td>
                            	<td><a data-toggle="collapse" href="#viewhistory" aria-expanded="false" aria-controls="viewhistory" class="collapsed">Detail Diet</a></td>
                            	<td>6 / 6 </td>
                            	<td><a href="">Remove</a></td>
                            </tr>   
                            <tr style="background-color: rgb(221, 221, 221); height: 0px;" class="collapse" id="viewhistory" aria-expanded="true">
                                    <td colspan="999"> 
                                    <table class="table my-table xlstable table-bordered">
                                    	<tbody>
                                    		<tr>
                                    			<th class="belowhed" style="width:1%;"></th>
                                    			<th class="belowhed" style="width:15%;">Day Plan</th>
                                    			<th class="belowhed" style="width:16%;">Diet Plan</th>
                                    			<th class="belowhed" style="width:16%;">Diet</th>
                                    			<th class="belowhed" style="width:10%;">Feed</th>
                                    			<th class="belowhed" style="width:15%;">Packed By</th>
                                    			<th class="belowhed" style="width:18%;">Status</th>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square breakfast1" aria-hidden="true"></i></td>
                                    			<td>Breakfast</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Orange Juice</td>
                                    			<td>75 ML</td>
                                    			<td>Varun Dhawan</td>
                                    			<td>
                                    				<div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ready To Deliver</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square midmorning1" aria-hidden="true"></i></td>
                                    			<td>Midmorning Snack</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Applesauce</td>
                                    			<td>50 ML</td>
                                    			<td>Varun Dhawan</td>
                                    			<td>
                                    				 <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ready To Deliver</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square lunch1" aria-hidden="true"></i></td>
                                    			<td>Lunch</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Pudding</td>
                                    			<td>25 ML</td>
                                    			<td>Varun Dhawan</td>
                                    			<td>
                                    				 <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ready To Deliver</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square midafternoon1" aria-hidden="true"></i></td>
                                    			<td>Midafternoon Snack</td>
                                    			<td>Oral Liquid Diet</td>	
                                    			<td>Ice Cream</td>
                                    			<td>75 ML</td>
                                    			<td>Varun Dhawan</td>
                                    			<td>
                                    				 <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ready To Deliver</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square dinner1" aria-hidden="true"></i></td>
                                    			<td>Dinner</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Pureed peaches</td>
                                    			<td>50 ML</td>
                                    			<td>Varun Dhawan</td>
                                    			<td>
                                    				 <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ready To Deliver</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square midevening1" aria-hidden="true"></i></td>
                                    			<td>Midevening Snack</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Milk</td>
                                    			<td>75 ML</td>
                                    			<td>Varun Dhawan</td>
                                    			<td>
                                    				 <div>
												        <input id="checkbox-1" class="checkbox-custom" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ready To Deliver</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    	</tbody>
                                    </table>
                                   </td>
                              </tr> 
                              
                              
                              <tr>
                            	<td>2</td>
                            	<td>Mr.Jaysigh Rathod</td>
                            	<td>SEMIPVT / 25</td>
                            	<td><a data-toggle="collapse" href="#viewhistory01" aria-expanded="false" aria-controls="viewhistory01" class="collapsed">Detail Diet</a></td>
                            	<td>2 / 6 </td>
                            	<td><a href="">Remove</a></td>
                            </tr>   
                            <tr style="background-color: rgb(221, 221, 221); height: 0px;" class="collapse" id="viewhistory01" aria-expanded="true">
                                    <td colspan="999"> 
                                    <table class="table my-table xlstable table-bordered">
                                    	<tbody>
                                    		<tr>
                                    			<th class="belowhed" style="width:1%;"></th>
                                    			<th class="belowhed" style="width:15%;">Day Plan</th>
                                    			<th class="belowhed" style="width:16%;">Diet Plan</th>
                                    			<th class="belowhed" style="width:16%;">Diet</th>
                                    			<th class="belowhed" style="width:10%;">Feed</th>
                                    			<th class="belowhed" style="width:15%;">Packed By</th>
                                    			<th class="belowhed" style="width:18%;">Status</th>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square breakfast1" aria-hidden="true"></i></td>
                                    			<td>Breakfast</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Milk</td>
                                    			<td>75 ML</td>
                                    			<td>Abhay Mishra</td>
                                    			<td>
                                    				<div>
												        <input id="checkbox-1" class="checkbox-custom disabled" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ready To Deliver</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square midmorning1" aria-hidden="true"></i></td>
                                    			<td>Midmorning Snack</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Applesauce</td>
                                    			<td>50 ML</td>
                                    			<td>Abhay Mishra</td>
                                    			<td>
                                    				<div>
												        <input id="checkbox-1" class="checkbox-custom disabled" name="checkbox-1" type="checkbox" checked>
												        <label for="checkbox-1" class="checkbox-custom-label">Ready To Deliver</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square lunch1" aria-hidden="true"></i></td>
                                    			<td>Lunch</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Pudding</td>
                                    			<td>25 ML</td>
                                    			<td>
                                    				<select name="invsttype" class="form-control chosen-select">
													    <option value="0">select team</option>
													    <option value="1">Abhay Mishra</option>
													    <option value="2">Varun Dhawan</option>
													    <option value="3">Akhil Yadav</option>
													</select>
                                    			</td>
                                    			<td>
                                    				 <div>
												        <input id="checkbox-2" class="checkbox-custom" name="checkbox-2" type="checkbox" onclick="disableCHK()">
												        <label id="lblCHK2" for="checkbox-2" class="checkbox-custom-label">Processing</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square midafternoon1" aria-hidden="true"></i></td>
                                    			<td>Midafternoon Snack</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Ice Cream</td>
                                    			<td>75 ML</td>
                                    			<td>
                                    				<select name="invsttype" class="form-control chosen-select">
													    <option value="0">select team</option>
													    <option value="1">Abhay Mishra</option>
													    <option value="2">Varun Dhawan</option>
													    <option value="3">Akhil Yadav</option>
													</select>
                                    			</td>
                                    			<td>
                                    				 <div>
												        <input id="checkbox-3" class="checkbox-custom" name="checkbox-2" type="checkbox" onclick="disableCHK1()">
												        <label id="lblCHK3" for="checkbox-3" class="checkbox-custom-label">Processing</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square dinner1" aria-hidden="true"></i></td>
                                    			<td>Dinner</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Pureed peaches</td>
                                    			<td>50 ML</td>
                                    			<td>
                                    				<select name="invsttype" class="form-control chosen-select">
													    <option value="0">select team</option>
													    <option value="1">Abhay Mishra</option>
													    <option value="2">Varun Dhawan</option>
													    <option value="3">Akhil Yadav</option>
													</select>
                                    			</td>
                                    			<td>
                                    				<div>
												        <input id="checkbox-4" class="checkbox-custom" name="checkbox-2" type="checkbox" onclick="disableCHK2()">
												        <label id="lblCHK4" for="checkbox-4" class="checkbox-custom-label">Processing</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td><i class="fa fa-square midevening1" aria-hidden="true"></i></td>
                                    			<td>Midevening Snack</td>
                                    			<td>Oral Liquid Diet</td>	
                                    			<td>Milk</td>
                                    			<td>75 ML</td>
                                    			<td>
                                    				<select name="invsttype" class="form-control chosen-select">
													    <option value="0">select team</option>
													    <option value="1">Abhay Mishra</option>
													    <option value="2">Varun Dhawan</option>
													    <option value="3">Akhil Yadav</option>
													</select>
                                    			</td>
                                    			<td>
                                    				<div>
												        <input id="checkbox-5" class="checkbox-custom" name="checkbox-2" type="checkbox" onclick="disableCHK3()">
												        <label id="lblCHK5" for="checkbox-5" class="checkbox-custom-label">Processing</label>
												      </div>
                                    			</td>
                                    		</tr>
                                    	</tbody>
                                    </table>
                                   </td>
                              </tr> 
                            </tbody>
                        </table>
                        
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                        	<table class="table table-bordered" cellspacing="0" width="100%" id="machinetable" style="width:100%;">
                                <thead>
                                    <tr class="tableback">
                                    			<th style="width:25%;">Day plan</th>
                                       			<th style="width:30%;">Diet plan</th>
                                    			<th style="width:30%;">Diet</th>
                                    			<th style="width:15%;">Feed</th>
                                    			
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="breakfast">
                                    	<td>Breakfast</td>
                                        <td>Oral Liquid Diet</td>
                                        <td>Orange Juice</td>
                                        <td>75 ML</td>
                                    </tr>
                                     <tr class="breakfast">
                                    	<td>Breakfast</td>
                                        <td>Oral Liquid Diet</td>
                                        <td>Milk</td>
                                        <td>75 ML</td>
                                    </tr>
                                    
                                   <tr class="midmorning">
                                   		<td>Midmorning Snack</td>
                                        <td>Oral Liquid Diet</td>
                                        <td>Applesauce</td>
                                        <td>100 ML</td>
                                    </tr>
                                    <tr class="lunch">
                                    	<td>Lunch</td>
                                         <td>Oral Liquid Diet</td>
                                        <td>Pudding</td>
                                        <td>50 ML</td>
                                    </tr>
                                    
                                    <tr class="midafternoon">
                                    	<td>Midafeternoon Snack</td>	
                                         <td>Oral Liquid Diet</td>
                                        <td>Ice Cream</td>
                                        <td>150 ML</td>
                                    </tr>
                                    <tr class="dinner">
                                    	<td>Dinner</td>
                                         <td>Oral Liquid Diet</td>
                                        <td>Pureed peaches</td>
                                        <td>100 ML</td>
                                    </tr>
                                   
                                    <tr class="midevening">
                                    	<td>Midevening Snack</td>
                                         <td>Oral Liquid Diet</td>
                                        <td>Milk</td>
                                        <td>150 ML</td>
                                    </tr>
                                   
                                    
                                    
                                    
                                </tbody>
                            </table>
                        </div>
                        </div>
                    </div>
                    
                    
                   
                        	 
                     			<div class="extra-space"></div>
                     			<script type="text/javascript" src="plugin/notification/tappifications.js"></script>
			                      <script>
								    $('.tappification-info').click(function() {
								        $.tappification({
								            type: 'info',
								            message: 'Breakfast - 07:00 AM to 08:00 AM | Midmorning Snack - 09:00 AM to 10:00 AM | Lunch - 11:00 AM to 12:00 PM | Midafternoon - 04:00 PM to 05:00 PM | Dinner - 07:00 PM to 08:00 PM | Midevening - 09:00 PM to 10:00 PM',
								        });
								    });
								    $('.tappification-info').click();
			    			</script>
                        
                
                <script>
						(function($){$.clock={version:"2.0.1",locale:{}};t=[];$.fn.clock=function(d){var c={it:{weekdays:["Domenica","Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato"],months:["Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre"]},en:{weekdays:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],months:["January","February","March","April","May","June","July","August","September","October","November","December"]},es:{weekdays:["Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"],months:["Enero","Febrero","Marzo","Abril","May","junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"]},de:{weekdays:["Sonntag","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag"],months:["Januar","Februar","März","April","könnte","Juni","Juli","August","September","Oktober","November","Dezember"]},fr:{weekdays:["Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"],months:["Janvier","Février","Mars","Avril","May","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"]},ru:{weekdays:["???????????","???????????","???????","?????","???????","???????","???????"],months:["??????","???????","????","??????","???","????","????","??????","????????","???????","??????","???????"]}};return this.each(function(){$.extend(c,$.clock.locale);d=d||{};d.timestamp=d.timestamp||"z";y=new Date().getTime();d.sysdiff=0;if(d.timestamp!="z"){d.sysdiff=d.timestamp-y}d.langSet=d.langSet||"en";d.format=d.format||((d.langSet!="en")?"24":"12");d.calendar=d.calendar||"true";if(!$(this).hasClass("jqclock")){$(this).addClass("jqclock");}var e=function(g){if(g<10){g="0"+g}return g;},f=function(j,n){var r=$(j).attr("id");if(n=="destroy"){clearTimeout(t[r]);}else{m=new Date(new Date().getTime()+n.sysdiff);var p=m.getHours(),l=m.getMinutes(),v=m.getSeconds(),u=m.getDay(),i=m.getDate(),k=m.getMonth(),q=m.getFullYear(),o="",z="",w=n.langSet;if(n.format=="12"){o=" AM";if(p>11){o=" PM"}if(p>12){p=p-12}if(p===0){p=12}}p=e(p);l=e(l);v=e(v);if(n.calendar!="false"){z=((w=="en")?"<span class='clockdate'>"+c[w].weekdays[u]+", "+c[w].months[k]+" "+i+", "+q+"</span>":"<span class='clockdate'>"+c[w].weekdays[u]+", "+i+" "+c[w].months[k]+" "+q+"</span>");}$(j).html(z+"<span class='clocktime'>"+p+":"+l+":"+v+o+"</span>");t[r]=setTimeout(function(){f($(j),n)},1000);}};f($(this),d);});};return this;})(jQuery);
						
						/* Now apply on document ready to jsbin page */
						$(document).ready(function(){
						
						$.clock.locale = {"pt":{"weekdays":["Domingo","Segunda-feira", "Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira", "Sábado"],"months":["Janeiro","Fevereiro","Março","Abril", "Maio","Junho","Julho","Agosto","Setembro","October","Novembro", "Dezembro"] } };
						
						$("#clock1").clock();
						});
						
					function disableCHK()
                	{
                	document.getElementById("lblCHK2").textContent="Ready To Deliver";
                	document.getElementById("checkbox-2").disabled = true;
                	}
                	function disableCHK1()
                	{
                	document.getElementById("lblCHK3").textContent="Ready To Deliver";
                	document.getElementById("checkbox-3").disabled = true;
                	}
                	function disableCHK2()
                	{
                	document.getElementById("lblCHK4").textContent="Ready To Deliver";
                	document.getElementById("checkbox-4").disabled = true;
                	}
                	function disableCHK3()
                	{
                	document.getElementById("lblCHK5").textContent="Ready To Deliver";
                	document.getElementById("checkbox-5").disabled = true;
                	}
                	
						
						
                </script>
</body>
</html>