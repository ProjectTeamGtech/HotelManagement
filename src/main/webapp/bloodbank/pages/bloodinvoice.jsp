<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>

.borderbot{
	border-bottom: 2px solid #000;
    padding-top: 15px;
    padding-bottom: 15px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 12px;
    font-weight: bold;
}
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
</style>
</head>
<body>

<div class="col-lg-12 col-xs-12 col-md-12">
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <div class="clinicname">Red Cell Hospital &amp; Blood Bank <span class="rgeno">Lic. No : 123456 </span></div>
					<div class="clicniaddress"> 301/B, Neeti Gaurav Complex, 21 Central Bazar Road, Ramdaspeth, Nagpur<br></div>
						<div class="bottext mabset">Tel/Fax:07126634800, E: redcell@yahoo.co.in, W: http://www.redcell.com/<br></div>
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 hidden text-right" style="padding-left:0px;padding-right:0px;">
				<p style="margin: 0px;"><b></b></p>
				<p style="margin: 0px;"></p>
				<!--<p style="margin: 0px;">Reg.No. 091334</p>
				--><p style="margin: 0px;"></p>
				<!--<p style="margin: 0px;">Time: 07:00 to 24:00 </p>-->
				
			</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;border-bottom: 1px solid #ddd;">
			<div class="col-lg-7 col-md-7 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
			<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label print-visible hidden-md hidden-lg">Date</label><span class="print-visible hidden-md hidden-lg">: <script>
  var currentDate = new Date(),
      day = currentDate.getDate(),
      month = currentDate.getMonth() + 1,
      year = currentDate.getFullYear();
  document.write(day + "/" + month + "/" + year)
</script>22/5/2017 
&nbsp;|&nbsp;
<script>
	var currentTime = new Date(),
      hours = currentTime.getHours(),
      minutes = currentTime.getMinutes();

	if (minutes < 10) {
	 minutes = "0" + minutes;
  }

	var suffix = "AM";
	if (hours >= 12) {
    suffix = "PM";
    hours = hours - 12;
	}
	if (hours == 0) {
	 hours = 12;
	}

	document.write(hours + ":" + minutes + " " + suffix)
</script>7:54 PM
</span>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Patient Name</label><span>: Mr. Abhi Parmar </span><span class="hidden-print">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Age / Gender</label><span class="hidden-print">: 17/Male<span class="hidden-print"> &nbsp; | &nbsp;</span></span><label for="inputEmail3" class="control-label hidden-print">Weight</label><span class="hidden-print">:  kgs  &nbsp; | &nbsp;</span>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Age / Gender</label><span>: 17/Male</span>
				</div>
				<div class="form-group hidden-print" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">City</label><span>: Nagpur </span><span class="">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label">Contact</label><span>: 08446545681 </span>
				</div>
			</div>
			
			


			<div class="col-lg-5 col-md-5 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Blood Group</label><span>: O+</span>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Weight</label><span>:  kg</span>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">City</label><span>: Nagpur </span><span class="">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label">Contact</label><span>: 08446545681 </span>
				</div>
			</div>
		</div>
		
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<div class="form-group" style="margin-bottom: 3px;">
					<b style="border-bottom: 4px double #ddd;">Dr. Sanil Koyli</b>
				</div>
				
			</div>
			
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
				<table class="table table-bordered">
				    <thead>
				      <tr>
				        <th>SR.No</th>
				        <th>PARTICULARS</th>
				        <th>NO.OF UNITS</th>
				        <th class="text-right">COST PER UNITS</th>
				        <th class="text-right">AMOUNT</th>
				      </tr>
				    </thead>
				    <tbody>
				      <tr>
				        <td>1</td>
				        <td>Blood Processing Charges</td>
				        <td>10</td>
				        <td class="text-right">Rs.1200.00</td>
				        <td class="text-right">Rs.1200.00</td>
				      </tr>
				    </tbody>
				  </table>
				
			</div>
			
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<div class="row hidden-print">
		<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right" style="padding-left:0px;padding-right:0px;">
					<div class="form-group" style="margin-bottom: 3px;">
						<a href="#" onclick="myFunction()" class="btn btn-primary">Print</a>
					</div>
				</div>
			</div>
		<div class="col-lg-1 col-md-1"></div>
		
	</div>
</div>

	
</body>
</html>