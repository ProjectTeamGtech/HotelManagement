<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
.listing-item{
  list-style: none;
  display: inline-block;
  width: 100%;
  padding: 0;
  margin: 0;
}
li {
    display: list-item;
    text-align: -webkit-match-parent;
}
/*---------- c. experience -----------*/
.wrap-card {
  position: relative;
  box-shadow: 0px 0px 0px #818181;
  transition: all 0.2s ease 0s;
  -webkit-transform: translateY(0px) translateX(0px);
}
.wrap-card:before,
.wrap-card:after {
  display: inline-block;
  position: absolute;
  content: " ";
  width: 100%;
  height: 3px;
  background: #818181;
  -webkit-transform: scaleX(0);
  transition: all 0.2s ease;
}
.wrap-card:before {
  top: 0;
  left: 0;
  -webkit-transform-origin: 0 0;
}
.wrap-card:after {
  bottom: 0;
  left: 0;
  -webkit-transform-origin: 100% 0;
}

.wrap-card:hover:after,
.wrap-card:hover:before {
  -webkit-transform: scaleX(1);
}
.card {
  background: #ffffff;
  padding: 10px 15px 0px 15px;
  margin-bottom: 0px;
  border: 1px solid rgba(129, 129, 129, 0.1);
}
.card:before,
.card:after {
  display: inline-block;
  position: absolute;
  content: " ";
  width: 3px;
  height: 100%;
  background: #818181;
  -webkit-transform: scaleY(0);
  transition: all 0.2s ease 0.2s;
}
.card:before {
  top: 0;
  left: 0;
  -webkit-transform-origin: 0 100%;
}
.card:after {
  top: 0;
  right: 0;
  -webkit-transform-origin: 0 0;
}
.card:hover:after,
.card:hover:before {
  -webkit-transform: scaleY(1);
}
.iconcirclest{
	    text-align: center;
    margin-top: 0px;
    margin-bottom: 10px;
    font-size: 18px !important;
}
.imgiconst{
    width: 75%;
    margin-left: auto;
    margin-right: auto;
}
hr {
    margin-top: 10px;
    margin-bottom: 10px;
    border: 0;
    border-top: 1px solid #eee;
}
.setcheck{
	float: right;
    margin-top: -43px;
}
.rolaccess{
width: 9%;
    float: right;
    position: relative;
    margin-top: -33px;
}
.paddingbothside{
	padding-left: 5px;padding-right: 5px;margin-bottom: 10px;
}
</style>
</head>
<body>
<section>


<div>
	<div class="row">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;">
		<p class="text-justify">Welcome to Smart YUVICARE!!! Now you can set Widgets manually and this will set in MIS dashboard..</p>
		<div class="rolaccess hidden">
		<select class="form-control">
		  <option>Select Job Title</option>
		  <option>Admin</option>
		  <option>Pathlab</option>
		</select>
		</div>
	</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-left: 0px;padding-right: 0px;">
			<ul class="listing-item">
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                              OPD Patient
                            </h2>
                            <p class="job">
                             <img src="mis/assets/images/OPD_2.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             IPD Patient
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/IPD_2.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Bed Status
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/bed_2.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Daily Summary
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/daily.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Invoice & Billing
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/invoice.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Payment Mode
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/pay.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Advance & Refund
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/advref.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Account Summary
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/accountinfo.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Clinical View
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/clinic.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Expenses Summary
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/expense2.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 paddingbothside">
                        <div class="wrap-card">
                          <div class="card">
                            <h2 class="iconcirclest">
                             Patient View
                            </h2>
                            <p class="job">
                              <img src="mis/assets/images/location.png" class="img-responsive imgiconst"></img>
                            </p>
                            <p class="company">
                              <div class="checkbox setcheck">
							    <label>
							      <input type="checkbox"> 
							    </label>
							  </div>
                            </p>
                            <hr>
                            <div class="text-detail">
                              <p class="text-justify">
                                Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. 
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    
                  </ul>
		</div>
		
		<div class="col-lg-12 col-sm-12 col-xs-12" style="margin-bottom:10px;">
		<div class="text-right">
			<a href="#" class="btn btn-primary" tupe="button">Save Changes</a>
		</div>
		</div>
	</div>
</div>
</section>
</body>
</html>