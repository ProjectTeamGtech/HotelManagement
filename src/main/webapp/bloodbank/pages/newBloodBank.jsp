<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="_assets/newtheme/js/vendor/owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="_assets/newtheme/js/vendor/owl-carousel/owl.theme.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="common/plugin/settingplugin/css/style.css"> <!-- Resource style -->

    <link href="dashboard/upcomingfeatures/css/site.css" rel="stylesheet" type="text/css" />
    <link href="ChatJs/css/chat.css" rel="stylesheet" type="text/css" />
    <script src="ChatJs/js/pusher.min.js" type="text/javascript"></script> 
    <script src="ChatJs/js/jquery-1.8.2.min.js" type="text/javascript"></script>
    
    <script type="text/javascript" src="bloodbank/js/bloodbank.js"></script>  
<script>
	//NProgress.start();
	
	function goreferesh() {
    
       document.getElementById("searchText").value="";
       document.location.reload();
    }
   
	
</script>
<style>
.user-list .user-list-item {
    background-position: 2px center;
    background-repeat: no-repeat;
    cursor: pointer;
    margin: 5px;
    overflow: hidden;
}
.profile-picture {
    display: block;
    float: left;
    height: 26px;
    width: 26px;
    margin-right:5px;
}
.profile-status.online {
    background-image: url("ChatJs/Images/chat-online.png");
    background-repeat: no-repeat;
}
.profile-status {
    display: block;
    float: right;
    height: 7px;
    margin: 8px 4px 0;
    width: 7px;
}
.content {
    margin: 0px 20px 0 30px;
    line-height: 27px;
}
   small{color:#fff ; font-size: 14px}
            .account a{ color :#333; background: #eee; border: 1px solid #ccc;padding: 5px; border-radius: 5px;display: inline-block;}
            pre{line-height: 11px;font-size: 11px;background: #fafafa;border: 1px solid #ccc; padding: 10px}
            .hide{font-size: 19px ;color:red ; font-weight: bold;display: none}
            .connexion {font-size: 19px ;color:green ; font-weight: bold;}
            body {
                background-color: #fff;
                color: #616f77;
            }
            .panel-body {
    padding: 5px;
    color: #000;
}
            ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}   
@media (min-width: 768px){
.apointfinderset{
	width: 76% !important;
}
}  

.tdseticon{
width: 15%;
    padding-right: 15px;
}
.owl-prev {
   display: none;
}
.owl-next {
   display: none;
}

.setimgu{
	position: absolute; 
	display: none; 
	top: -5px; 
	right: -5px; 
	padding: 2px 7px; 
	text-decoration: none; 
	color: rgb(0, 0, 0); 
	font-size: 20px; 
	font-family: Arial, sans-serif;
}
.setimgw{
	position: fixed; 
	/*right: 126px;*/ 
	bottom: 15px; 
	visibility: visible; 
	z-index: 2147483639; 
	background: transparent; 
	border: 0px; 
	padding: 10px 10px 0px 0px; 
	float: left; 
	margin-right: 0px; 
	backface-visibility: hidden; 
	opacity: 1;
}

</style>
</head>
<body>

<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top:20px;">
	<div class="col-md-4 col-lg-4">
                        <!-- tile -->
                        <section class="tile bg-blacky widget-appointments">
                            <!-- tile header -->
                            <div class="tile-header dvd dvd-btm">
                                <h1 class="custom-font">Blood Bank Dashboard</h1>

                            </div>
                            <!-- /tile header -->
                            <!-- tile body -->
                            <div class="tile-body">
                                <!-- row -->
                                <div class="row">
                                    <!-- col -->
                                    <div class="col-md-6 text-center">
                                        <h4 class="text-light">Nagpur</h4>
                                        <div style="width: 100%;">
                                            <canvas id="c1" class="CoolClock:minovateClock:50"></canvas>
                                        </div>

                                    </div>
                                    <!-- /col -->
                                    <!-- col -->
                                    <div class="col-md-6 b-l text-center">
                                        <span class="day"><s:property value="date"/></span><br />
                                        <span class="month"><s:property value="month"/></span>
                                    </div>
                                    <!-- /col -->
                                </div>
                                <!-- /row -->
                            </div>
                            <!-- /tile body -->
                            <!-- tile footer -->
                                  <div class="tile-footer dvd dvd-top">
										<h4>Event Details</h4>
                                    <div class="owl-carousel" id="appointments-carousel">
                                         <s:iterator value="eventList">
                                        <div>
                                            <p class="text-center text-strong"><small class="text-thin"><i class="fa fa-calendar"></i> <s:property value="fromdate"/>, &nbsp;<i class="fa fa-clock-o"></i> <s:property value="time"/>,<br> Venue: <s:property value="place"/></small></p>
                                            <p>
                                            <h5 class="mt-10 mb-0 text-strong"><s:property value="eventname"/></h5>
                                            <small class="text-thin text-transparent-white"><s:property value="description"/></small>
                                            </p>
                                        </div>
              							</s:iterator>
                                    </div>
                                </div>
                            <!-- /tile footer -->
                        </section>
                        <!--<img src="_assets/img/Hospital.png" class="img-responsive">-->
                        </div>
							<div class="col-lg-8 col-xs-8 col-md-8">
						<div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		                        <a href="#" onclick="openPopup('requestbloodBloodbank')"><img src="dashboardicon/bloodstock.png" class="img-responsive"></img></a>
		                        <a href="#" onclick="openPopup('requestbloodBloodbank')"> Request Blood</a>
	                        </div>
                        </div>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		                        <a href="#" onclick="openPopup('donorlistBloodbank')"><img src="dashboardicon/donarlist.png" class="img-responsive"></img></a>
		                        <a href="#" onclick="openPopup('donorlistBloodbank')">Donor Details</a>
	                        </div>
                        </div>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		                        <a href="#" onclick="openPopup('donatepatientBloodbank')"><img src="dashboardicon/donateblood.png" class="img-responsive"></img></a>
		                        <a href="#" onclick="openPopup('donatepatientBloodbank')"> Donate Blood</a>
	                        </div>
                        </div>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		                        <a href="#" onclick="openPopup('#')"><img src="dashboardicon/bloodbank.png" class="img-responsive"></img></a>
		                        <a href="#" onclick="openPopup('#')"> Bank Details</a>
	                        </div>
                        </div>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		                        <a href="#" onclick="openPopup('instockBloodbank')"><img src="dashboardicon/bloodsample.png" class="img-responsive"></img></a>
		                        <a href="#" onclick="openPopup('donorlistBloodbank')"> In Stock</a>
	                        </div>
                        </div>
                        <div class="card-container col-lg-2 col-lg-2 col-md-3 col-sm-3 col-xs-3 hidden">
	                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		                        <a href="#" onclick="openPopup('bloodcampBloodbank')"><img src="dashboardicon/hospital-bed.png" class="img-responsive"></img></a>
		                        <a href="#" onclick="openPopup('bloodcampBloodbank')"> Camp Details</a>
	                        </div>
                        </div>
	</div>
</div>


<!--Edit Modal-->
    <div class="modal fade" id="editbgroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal" name="bloodgroupform">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Blood Group*</label>
                                <div class="col-sm-7">
                                    <select id="blood_group" class="form-control validate[required]" name="blood_group">
                                        <option value="">Select Blood Group</option>
                                        <option value="O+">O+ </option>
                                        <option value="O-">O- </option>
                                        <option value="A+" selected="selected">A+ </option>
                                        <option value="A-">A- </option>
                                        <option value="B+">B+ </option>
                                        <option value="B-">B- </option>
                                        <option value="AB+">AB+ </option>
                                        <option value="AB-">AB- </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">No of Bags*</label>
                                <div class="col-sm-7">
                                    <input id="no_bags" class="form-control validate[required] text-input" name="no_bags" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Expiry Date*</label>
                                <div class="col-sm-7">
                                    <input class="form-control text-input" name="no_bags" type="text">
                                </div>
                            </div>
                          <input type="hidden" id="bid" name="blood_group_id" >
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addUpdateBloodGroup()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>

    <!--Add Donor Modal-->
    <div class="modal fade" id="addDonor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Blood Donor</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form name="blooddonor_form" action="" method="post" class="form-horizontal" id="blooddonor_form">
                            <input name="action" value="edit" type="hidden">
                            <input name="blooddonor_id" value="2" type="hidden">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="first_name">Full Name<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="name" class="form-control text-input"  name="name" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="gender">Gender<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <label class="radio-inline">
                                        <input value="Male" class="tog validate[required]" name="gender" id="male" checked="checked" type="radio">Male
                                    </label>
                                    <label class="radio-inline">
                                        <input value="Female" class="tog validate[required]" name="gender" id="female" type="radio">Female
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="med_category_name">Age<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="age" class="form-control validate[required] text-input"  name="age" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label " for="phone">Phone<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="phone" class="form-control validate[,custom[phone]] text-input" name="phone" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label " for="email">Email<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="email" class="form-control validate[required,custom[email]] text-input" name="email"  type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="bloodgruop">Blood Group<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <select id="bg1" class="form-control validate[required]" name="blood_group">
                                        <option value="">Select Blood Group</option>
                                        <option value="O+">O+ </option>
                                        <option value="O-">O- </option>
                                        <option value="A+">A+ </option>
                                        <option value="A-">A- </option>
                                        <option value="B+">B+ </option>
                                        <option value="B-">B- </option>
                                        <option value="AB+">AB+ </option>
                                        <option value="AB-">AB- </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="last_donet_date">Last Donation Date</label>
                                <div class="col-sm-7">
                                    <input id="last_donation_date" class="form-control  hasDatepicker" name="last_donation_date" type="text">
                                </div>
                            </div>

                           <input type="hidden" id="id" name="id">
                            
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addUpdateBloodDonor()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>


<script src="common/plugin/settingplugin/js/main.js"></script>
    <script src="_assets/newtheme/js/vendor/owl-carousel/owl.carousel.js"></script>
    <script src="_assets/newtheme/js/vendor/coolclock/coolclock.js"></script>
    <script src="_assets/newtheme/js/vendor/coolclock/excanvas.js"></script>
 
 <script src="ChatJs/js/jquery.pusherchat.js"></script>
    <script>
            $.fn.pusherChat({
                'profilePage':true,
                'onFriendConnect': function(member){
                    if (member.id) $('#user_'+member.id).hide();  
                    if (!$('.account a:visible').html()) $('.hide').show();
                },
                'onFriendLogOut': function(member){
                    if (member.id) $('#user_'+member.id).show();  
                    if ($('.account a:visible').html()) $('.hide').hide();
                },
                'onSubscription':function(members){
                    if ($('.account a:visible').html()) $('.hide').hide();
                    $.each(members._members_map, function(val){
                        $('#user_'+val).hide();
                    });            
                }
            });
        </script>
    <script src="dashboard/upcomingfeatures/scripts/jquery.bootstrap.newsbox.min.js"></script>
    <script type="text/javascript">
    $(function () {
        $(".demo1").bootstrapNews({
            newsPerPage: 3,
            autoplay: true,
			pauseOnHover:true,
            direction: 'up',
            navigation: false,
            newsTickerInterval: 4000,
            onToDo: function () {
                //console.log(this);
            }
        });
    });
</script>
    
    <!--/ vendor javascripts -->
            <script>
            $(window).load(function(){
           
                // Initialize owl carousels
                $('#appointments-carousel').owlCarousel({
                    autoPlay: 5000,
                    stopOnHover: true,
                    slideSpeed : 300,
                    paginationSpeed : 400,
                    navigation: false,
                   
                    singleItem : true
                });
                //* Initialize owl carousels
            });
            
			
        </script>
        
        
        	 <script>
					var myWindow;
					function openWin() {
					    myWindow = window.open("letsTalkDiaryMangent", "", "width=450, height=600");
					}
			</script>
</body>
</html>