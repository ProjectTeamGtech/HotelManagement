<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->



<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">




   

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
            min-height: auto !important;
        }
        .miheight {
            min-height: auto !important;
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
            padding: 5px 7px 7px 7px !important;
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
        .theight {
            height: 21px;
        }
    </style>


    <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
    </style>
</head>





<body id="his" class="appWrapper sidebar-xs-forced">

   
        <section id="">

            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-6">
                            Manage Inventory | Storage location | View 
                        </div>
                        <div class="col-md-6 text-right">
                            <a href="#" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addvendor">Add Storage Location</a>
                        </div>
                    </div> 
                    <div class="">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Manager Name</th>
                                    <th>Address</th>
                                    <th>Area Name</th>
                                    <th>Phone No</th>
                                    <th>Edit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>warehouse 1</td>
                                    <td>sandip</td>
                                    <td>Wadi 123, Amravati Road</td>
                                    <td>Wadi</td>
                                    <td>07123345672</td>
                                    <td><a href="#"><i class="fa fa-edit"></i></a></td>

                                </tr>
                            </tbody>

                        </table><br />
                        
                        
                    </div>

                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->








    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="addvendor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add Storage Location</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Storage Name :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Manager Name :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                           
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Address  :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">City :</label>
                                <div class="col-sm-8">
                                    <select name="ctl00$CPHcontent$ddlCity" onchange="javascript:setTimeout('__doPostBack(\'ctl00$CPHcontent$ddlCity\',\'\')', 0)" id="CPHcontent_ddlCity" style="height:26px;width:307px;">
                                        <option selected="selected" value="1">Nagpur</option>
                                        <option value="2">Raipur</option>
                                        <option value="3">PUNE</option>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Area  :</label>
                                <div class="col-sm-8">
                                    <select name="ctl00$CPHcontent$ddlArea" id="CPHcontent_ddlArea" style="height:26px;width:307px;">
                                        <option value="0">Select Area</option>
                                        <option value="16">Ajni</option>
                                        <option value="17">Amar Jyoti Nagar</option>
                                        <option value="19">Amravati Road</option>
                                        <option value="108">Ayodhya Nagar</option>
                                        <option value="21">Baidyanath Square</option>
                                        <option value="10">Bajaj Nagar</option>
                                        <option value="25">Bajarang Chowk</option>
                                        <option value="27">Bajeria</option>
                                        <option value="32">Bezonabaugh</option>
                                        <option value="18">Bharat Nagar</option>
                                        <option value="33">Binaki Layout</option>
                                        <option value="34">Butibori</option>
                                        <option value="20">Central Avenue</option>
                                        <option value="35">Central Avenue Road</option>
                                        <option value="22">Chandrabhaga Nagar</option>
                                        <option value="24">Chhaoni</option>
                                        <option value="12">Civil Lines</option>
                                        <option value="26">Congress Nagar</option>
                                        <option value="28">Cotton Market</option>
                                        <option value="23">Dattwadi</option>
                                        <option value="5">Dhantoli</option>
                                        <option value="6">Dharampeth</option>
                                        <option value="29">Dindayal Nagar</option>
                                        <option value="37">Gaddi Godam</option>
                                        <option value="38">Gandhi Bagh</option>
                                        <option value="39">Gandhinagar</option>
                                        <option value="40">Ganesh Nagar</option>
                                        <option value="36">Ganesh Peth</option>
                                        <option value="41">Gayatri Nagar</option>
                                        <option value="42">Gittikhadan</option>
                                        <option value="43">Gokul Peth</option>
                                        <option value="8">Gokulpeth</option>
                                        <option value="44">Gopal Nagar</option>
                                        <option value="45">Hiiwari Layout</option>
                                        <option value="46">Hingna Road</option>
                                        <option value="47">Imamwada</option>
                                        <option value="48">Itwari</option>
                                        <option value="49">Jaiprakash Nagar</option>
                                        <option value="50">Jaripatka</option>
                                        <option value="51">Kadbi Chowk</option>
                                        <option value="52">Kamal Chowk</option>
                                        <option value="53">Kamptee</option>
                                        <option value="54">Kamptee Road</option>
                                        <option value="1">khamla</option>
                                        <option value="55">Khamla Road</option>
                                        <option value="56">Kotwal Nagar</option>
                                        <option value="57">Laxmi Nagar</option>
                                        <option value="58">Madhav Nagar</option>
                                        <option value="3">Mahal</option>
                                        <option value="59">Manewada</option>
                                        <option value="60">Manewada Road</option>
                                        <option value="61">Medical Colleage Square</option>
                                        <option value="62">MIDC</option>
                                        <option value="63">Mohammedali Road</option>
                                        <option value="64">Mohan Nagar</option>
                                        <option value="65">Mominpura</option>
                                        <option value="66">Nag Road</option>
                                        <option value="30">Nagpur GPO</option>
                                        <option value="67">Nandanvan</option>
                                        <option value="2">Nandanwan</option>
                                        <option value="68">Narayanpeth</option>
                                        <option value="69">Narendra Nagar</option>
                                        <option value="70">Nehru Nagar</option>
                                        <option value="71">New Shukrawari</option>
                                        <option value="72">New Subedar Layout</option>
                                        <option value="73">North Ambazari Road</option>
                                        <option value="74">Panchpaoli</option>
                                        <option value="75">Panchpawali Chowk</option>
                                        <option value="76">Pawan Bhumi</option>
                                        <option value="77">Prasad Nagar</option>
                                        <option value="78">Pratap Nagar</option>
                                        <option value="79">Ram Nagar</option>
                                        <option value="11">Ramdaspeth</option>
                                        <option value="80">Rameshwari Road</option>
                                        <option value="81">Rameshwari Square</option>
                                        <option value="82">Rangari</option>
                                        <option value="83">Ravi Nagar</option>
                                        <option value="84">Reshim Bagh</option>
                                        <option value="85">Ring Road</option>
                                        <option value="14">Sadar</option>
                                        <option value="86">Sai Nagar</option>
                                        <option value="15">Sakkardara</option>
                                        <option value="87">Sanjay Nagar</option>
                                        <option value="13">Seminary Hills</option>
                                        <option value="9">Shankar Nagar</option>
                                        <option value="88">Shanti Nagar</option>
                                        <option value="89">Shardhanand Peth</option>
                                        <option value="90">Shivaji Nagar</option>
                                        <option value="7">Shivajinagar</option>
                                        <option value="91">Siras Peth</option>
                                        <option value="4">Sitabuldi</option>
                                        <option value="92">Sonegaon</option>
                                        <option value="93">Subhash Nagar</option>
                                        <option value="94">Subhash Road</option>
                                        <option value="95">Surendra Nagar</option>
                                        <option value="96">Swawlambi Nagar</option>
                                        <option value="97">Timki</option>
                                        <option value="98">Trimurti Nagar</option>
                                        <option value="99">Tulsibagh</option>
                                        <option value="100">Ujwal Nagar</option>
                                        <option value="101">Untkhana</option>
                                        <option value="102">Vivekanand Nagar</option>
                                        <option value="103">Wadi</option>
                                        <option value="104">Wanjari Nagar</option>
                                        <option value="105">Wardha Road</option>
                                        <option value="31">Wardhaman Nagar</option>
                                        <option value="106">Zingabai Takli</option>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Phone no :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Add/Edit</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="addbrand" class="modal fade" data-backdrop-limit="1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add Brands</h4>
                </div>
                <div class="modal-body">
                    <div class="input-group martop10">
                        <input id="txtmainmenus" class="form-control" style="width:400px;" value="" type="text">
                        <div class="input-group-btn" style="width:0% !important">
                            <button class="btn btn-success btn-Addmenu" id="btnAddmenu">Add Brand</button>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 15px;">
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <p>1. Abc</p>
                            <p>4. Abc</p>
                            <p>7. Abc</p>
                            <p>10. Abc</p>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <p>2. Abc</p>
                            <p>5. Abc</p>
                            <p>8. Abc</p>
                            <p>11. Abc</p>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <p>3. Abc</p>
                            <p>6. Abc</p>
                            <p>9. Abc</p>
                            
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>

 

    <script>
        $('#addbrand').on('show', function () {
            $('#addvendor').css('opacity', .5);
            $('#addvendor').unbind();
        });
        $('#addbrand').on('hidden', function () {
            $('#addvendor').css('opacity', 1);
            $('#addvendor').removeData("modal").modal({});
        });
    </script>
</body>
</html>
