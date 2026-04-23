<style>
.navbrcontainer {
	width: 100%;
	padding: 0px 0px 0px 16px;
	text-align: left;
}

.navbrcontainer li {
	list-style-type: none;
	padding-top: 10% !important;
}

.navbrcontainer ul {
	padding: 0px;
}

li-x {
	list-style: none;
}

.li-x .head {
	cursor: pointer;
	font-size: 14px;
	font-weight: bold;
	color: #16303c;
	text-transform: capitalize;
}
.head i{
padding-right: 15px;
}
.li-x .out {
	padding-left: 10px;
	font-size: 11px;
}

a {
	color: #185c63;
}
.logo{
padding-top: 10px;
}




/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  background: #f1f1f1; 
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background: #a5c6d6; 
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #555; 
}
</style>

<div class="logo" style="border:1px solid #343e5033">
		<img src="manasopd/assets/images/Manas_Yuvicare_Logo.svg"
			style="height: 56px; width: 100px;">
	</div>
<div class='navbrcontainer'>
	
	<ul>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="onlinerequestpharPharmacy" title="Main Dashboard"><i class="fa fa-users" aria-hidden="true"></i> <span>Online Dash.</span></a>
			</span>
		</li>
		
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="Pharmacy" title="Main Dashboard"><i class="fa fa-users" aria-hidden="true"></i> <span>Dashboard</span></a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				
			</span>
		</li>
		<li class='li-x' style="list-style: none;"><span class='head'
			data-toggle="collapse" data-target=".transac"> <i
				class="fa fa-random"></i> Transactions
		</span>
			<ul class='transac collapse out'>
				<li><a href="#" onclick="opencPopup('ExpenceManagement')"><i class="fa fa-caret-right"></i>
						Manage Voucher</a></li>
				<li><a href="#" data-toggle="collapse"
					data-target=".create_voucher"><i class="fa fa-caret-right"></i> Create Voucher</a>

					<ul class='create_voucher collapse out'>
						<li><a href="#" onclick="showselectedvouchrpopup('Payment')"><i class="fa fa-caret-right"></i>Payment</a></li>
						<li><a href="#" onclick="showselectedvouchrpopup('Receipt')"><i class="fa fa-caret-right"></i>
								Receipt</a></li>
						<li><a href="#" onclick="showselectedvouchrpopup('Contra')"><i class="fa fa-caret-right"></i>
								Contra</a></li>
						<li><a href="#" onclick="showselectedvouchrpopup('Journal')"><i class="fa fa-caret-right"></i>Journal</a></li>
						<li><a href="#" onclick="showselectedvouchrpopup('Opening')"><i class="fa fa-caret-right"></i>
								Opening</a></li>
						<li><a href="#" onclick="showselectedvouchrpopup('Purchase')"><i class="fa fa-caret-right"></i>
								Purchase</a></li>
					</ul></li>
			</ul>
			</li>

		<li class='li-x' style="list-style: none;"><a class='head'
			href="#" title="Banking" data-toggle="collapse"
			data-target=".bankingv"> <i class="fa fa-university"></i> Banking
		</a>
			<ul class='bankingv collapse out'>
				<li><a href="#"><i class="fa fa-caret-right"></i> Bank
						Reconciliation</a></li>
				<li><a href="#"><i class="fa fa-caret-right"></i> Account
						Transfer</a></li>


			</ul></li>

		<li class='li-x' style="list-style: none;"><a href="#"
			class='head' data-toggle="collapse" data-target=".ledger"
			title="Ledger"><i class="fa fa-balance-scale"></i> Ledger</a>
			<ul class='ledger collapse out'>
				<li class=""><a href="#"
					onclick="openPopup('viewledrportAppointmentType')"><i
						class="fa fa-caret-right"></i>  Ledger Dashboard</a></li>
				<li><a href="#" onclick="openPopup('ledgAppointmentType')"><i
						class="fa fa-caret-right"></i> Manage Ledger</a></li>

				<li><a href="#" onclick="openPopup('aheadAppointmentType')"><i
						class="fa fa-caret-right"></i> Manage Ledger Head</a></li>
			</ul></li>

		<li class='li-x' style="list-style: none;"><a href="#"
			title="Reports" class='head' data-toggle="collapse"
			data-target=".Reports"><i class="fa fa-list-alt"></i>  Reports </a>
			<ul class='Reports collapse out'>
				<li><a href="#"><i class="fa fa-caret-right"></i> Unpaid
						Account</a></li>
				<li><a href="#"><i class="fa fa-caret-right"></i> Sales
						Payment</a></li>
				<li><a href="#"><i class="fa fa-caret-right"></i> Incoming
						Stmt</a></li>
				<li><a href="#" onclick="openPopup('tbAppointmentType')"><i
						class="fa fa-caret-right"></i>Trial Balance</a></li>

			</ul></li>
			
			
			<li class='li-x' style="list-style: none;">
                                                <a href="#" title="Analysis" class='head' data-toggle="collapse" data-target=".Analysis"><i class="fa fa-bar-chart-o"></i> Analysis</a>
                                                <ul   class='Analysis collapse out'>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Cash Flow</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Funds Flow</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Ratio Analysis</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i>Trend Analysis</a></li>
                                                    
                                                   
                                                </ul>
                                            </li>
                                           



	</ul>


</div>

