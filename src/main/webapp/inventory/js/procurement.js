var count = 1;
var gprice = 0;
function setProductListAjax(supid) {
	var url = "productProcurement?supid=" + supid + "&count=" + count + " ";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setProductListAjaxRequest;
	req.open("GET", url, true);

	req.send(null);

}

function setProductListAjaxRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("procurmentprodlistajax").innerHTML = req.responseText;
		}
	}
}

var grow = 0;
function setbrandname(bid, row) {
	grow = row;
	var vendor = document.getElementById("vendor").value;

	var url = "brandProcurement?bid=" + bid + "&row=" + row + "&vendor="
			+ vendor + " ";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setbrandnameRequest;
	req.open("GET", url, true);

	req.send(null);
}

function setbrandnameRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("tdproduct" + grow).innerHTML = req.responseText;
		}
	}
}

function setproductdetails(pid, row) {
	grow = row;
	var url = "pdtailsProcurement?pid=" + pid + "&row=" + row + " ";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setproductdetailsRequest;
	req.open("GET", url, true);

	req.send(null);
}

function setproductdetailsRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var temp = data.split('~');

			document.getElementById("tdprodcode" + grow).innerHTML = temp[0]
					+ '/' + temp[1];
			document.getElementById("tdmrp" + grow).innerHTML = temp[2];
			document.getElementById("tdpurprice" + grow).innerHTML = temp[3];
			gprice = temp[3];
			// document.getElementById("tdstock"+grow).innerHTML
			document.getElementById("txtQty" + grow).value = '1';
			document.getElementById("tdtotal" + grow).innerHTML = temp[3];

			settotal();
			// document.getElementById("tdproduct"+grow).innerHTML =
			// req.responseText;
		}
	}
}

function settotal() {
	var sum = 0;

	for (var i = 0; i < count; i++) {

		var val = parseInt(document.getElementById("tdtotal" + i).innerHTML);

		var qty = document.getElementById("txtQty" + i).value;

		if (qty == "") {
			continue;
		}

		sum = sum + val;

	}

	document.getElementById("lblPOTotal").innerText = sum;
}

function setprotctTotal(qty, row) {
	var qty = document.getElementById("txtQty" + row).value;
	var price = document.getElementById("tdpurprice" + row).innerHTML;

	var total = parseFloat(price) * parseInt(qty);
	document.getElementById("tdtotal" + row).innerHTML = total;
	settotal();
}

function purchaseProduct() {

	document.getElementById('hdnrowcount').value = count;
	document.getElementById('procurementfrm').submit();

}

function AddTableRow() {

	count++;
	var vid = document.getElementById('vendor').value;
	setProductListAjax(vid);
}

function confirmpo() {

	var t = confirm("Do you Want to Confirm?");
	if (t == true) {

		return true;
	} else {
		return false;
	}

}

function confirmpoRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			document.location.reload();
		}
	}
}

function confirmdelete() {

	var t = confirm("Do you Want to Delete?");
	if (t == true) {

		return true;
	} else {

		return false;
	}

}

function editpo() {

	$("#editcat").modal("show");

}

function setProductData(id) {

	var url = "setproductajaxProcurement?id=" + id + " ";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setProductDataRequest;
	req.open("GET", url, true);

	req.send(null);
}

function setProductDataRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("productx1").innerHTML = req.responseText;
		}
	}
}

function setproductName(id) {

	var vendor = document.getElementById("vendor").value;
	var url = "getproductnamesProcurement?id=" + id + "&vendor=" + vendor + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setproductNameRequest;
	req.open("GET", url, true);

	req.send(null);
}

function setproductNameRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("productx2").innerHTML = req.responseText;
		}
	}
}

function editpo(id) {

	var url = "editpoProcurement?id=" + id + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = editpoRequest;
	req.open("GET", url, true);

	req.send(null);

}

function editpoRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var data = req.responseText;
			var str = data.split("~");

			document.getElementById("id").value = str[0];
			document.getElementById("vendor").value = str[10];

			document.getElementById("productx1").innerHTML = str[12];
			document.getElementById("productx2").innerHTML = str[13];
			document.getElementById("brandname").value = str[11];
			document.getElementById("product").value = str[1];

			document.getElementById("lblidcode").innerText = str[1] + "/"
					+ str[14];
			document.getElementById("mrp").innerText = str[5];
			document.getElementById("purchase_price").innerText = str[2];
			document.getElementById("quantity").value = str[3];
			document.getElementById("total").innerText = str[4];

			$("#editcat").modal('show');

		}
	}
}

function setproductinfo(id) {

	var url = "pdtailsProcurement?pid=" + id + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setproductinfoRequest;
	req.open("GET", url, true);

	req.send(null);

}

function setproductinfoRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {

			var str = req.responseText;
			var tmp = str.split("~");

			document.getElementById("lblidcode").innerText = tmp[0] + "/"
					+ tmp[1];
			document.getElementById("mrp").innerText = tmp[2];
			document.getElementById("purchase_price").innerText = tmp[3];
			document.getElementById("quantity").value = 1;
			document.getElementById("total").innerText = tmp[3];

		}
	}
}

function updatepo() {

	var id = document.getElementById("id").value;
	var vendor = document.getElementById("vendor").value;
	var prodid = document.getElementById("lblidcode").innerHTML;
	var pro_id = prodid.split("/");
	var brand_id = document.getElementById("brandname").value;
	var product = document.getElementById("product").value;
	var mrp = parseFloat(document.getElementById("mrp").innerHTML);
	var pur_price = parseFloat(document.getElementById("purchase_price").innerHTML);
	var stock = parseFloat(document.getElementById("stock").innerHTML);
	var quantity = document.getElementById("quantity").value;
	var total = parseFloat(document.getElementById("total").innerHTML);

	var url = "updateproProcurement?id=" + id + "&vendor=" + vendor
			+ "&prodid=" + pro_id[0] + "&brand_id=" + brand_id + "&product="
			+ product + "&mrp=" + mrp + "&purchase_price=" + pur_price
			+ "&stock=" + stock + "&quantity=" + quantity + "&total=" + total
			+ "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = updatepoRequest;
	req.open("GET", url, true);

	req.send(null);

}

function updatepoRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			$("#editcat").modal('hide');
			document.testform.submit();
		}
	}
}

var cell;
var row;
var index = 1;
var btnid;

function addnewRow(tableId) {

	index++;
	count++;
	var vendorid = document.getElementById("vendor").value;
	var table = document.getElementById(tableId);
	var rowCount = table.rows.length;
	row = table.insertRow(rowCount);
	var counts = rowCount - 1;
	var url = "addnewrowProcurement?rowcount=" + counts + "&vendorid="
			+ vendorid + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = addnewrowRequest;
	req.open("GET", url, true);

	req.send(null);
}

function addnewrowRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			row.innerHTML = req.responseText;
		}
	}
}

var tindex = 0;

function addnewpo(tableId) {

	var pid = document.getElementById("mainproduct").value;
	if (pid == "0") {

		jAlert('error', "Please select product!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} else {

		var table = document.getElementById(tableId);
		var rowCount = table.rows.length;
		row = table.insertRow(rowCount);
		var counts = rowCount - 1;
		tindex = counts;
		var url = "addnewpoProcurement?count=" + counts + "&pid=" + pid + "";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = addnewpoRequest;
		req.open("GET", url, true);

		req.send(null);
	}

}

function addnewpoRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			row.innerHTML = req.responseText;
			$("#product_id" + tindex).trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});
		}
	}
}

function submitPo() {

	document.getElementById("poform").submit();
}

function goodReceipt(id, pid) {

	var url = "getreceiptProcurement?id=" + id + "&pid=" + pid + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = goodReceiptRequest;
	req.open("GET", url, true);

	req.send(null);

}

function goodReceiptRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var str = req.responseText;
			var data = str.split("~");
			document.getElementById("orderdata").innerHTML = data[0];
			document.getElementById("receiveddata").innerHTML = data[1];

			var tcount = Number(document.getElementById("tcount").value);

			for (var i = 0; i < tcount; i++) {

				$("#purchase_date" + i).datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange : yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});

				$("#expiry_date" + i).datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange : yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});

			}

			$("#viewproduct").modal("show");
		}
	}
}

function cretaePo() {

	var vendorid = document.getElementById("vendorid").value;

	if (vendorid == "0") {
		jAlert('error', "Please select supplier!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} else {

		createpoForVendor(vendorid);

	}

}

function createpoForVendorRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			document.getElementById("productVendor").innerHTML = req.responseText;
			$("#cpo").modal("show");
		}
	}
}

function createpoForVendor(vendorid) {

	var url = "getvendorproductProcurement?vendorid=" + vendorid + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = createpoForVendorRequest;
	req.open("GET", url, true);

	req.send(null);

}

function shoeunitprice(price, indx) {
	var qty = document.getElementById("qtyreq" + indx).innerHTML;
	var medcinetype = document.getElementById("medcinetype" + indx).innerHTML;

	var result = 0;
	if (medcinetype == 'Tablet') {

		var pack = Number(document.getElementById("pack" + indx).innerHTML);
		result = parseFloat(price) / parseInt(pack);
	} else {
		result = parseFloat(price);
	}

	num = parseFloat(result);
	// num = num.toString(); //If it's not already a String
	// num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the
	// hundredths place
	// Number(num); //If you need it back as a Number
	num = roundTwo(num);

	document.getElementById("sale_price" + indx).value = num;

}

function roundTwo(val) {

	val = Math.round(val * 100) / 100;
	// val=Math.floor(val);
	return val;
}

function roundAbs(val) {

	val = Math.round(val);

	return val;
}

function setAllVoucher(val) {

	var count = document.getElementById("tcount").value;

	for (var i = 0; i < count; i++) {

		document.getElementById("voucherno" + i).value = val;
	}
}

var gprodid = 0;
var gindex = 0;

function opennewBatchProduct(pid, index) {

	gprodid = pid;
	gindex = index;
	$("#newpo").modal("show");

}

function savetoProductPo(tableId) {

	document.getElementById(tableId).className = "table my-table xlstable table-bordered";
	var batch = document.getElementById("batch").value;
	var expiry = document.getElementById("expiry").value;
	var qty = document.getElementById("qty").value;

	if (batch == "") {
		jAlert('error', "Please enter batch!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (expiry == "") {
		jAlert('error', "Please select expiry date!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (qty == "") {

		jAlert('error', "Please enter quantity!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {

		var table = document.getElementById(tableId);
		var rowCount = table.rows.length;
		row = table.insertRow(rowCount);
		var url = "addtempproductProcurement?count=" + rowCount + "&pid="
				+ gprodid + "&batch=" + batch + "&expiry=" + expiry + "&qty="
				+ qty + "";

		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = savetoProductPoRequest;
		req.open("GET", url, true);

		req.send(null);
	}
}

function savetoProductPoRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var str = req.responseText;
			var data = str.split("~");
			row.innerHTML = data[0];
			document.getElementById("newproduct" + gindex).value = data[1];
			$("#newpo").modal("hide");
		}
	}
}

var allvoucher = "0";

function getVoucherAmt() {

	allvoucher = "0";
	$('.case').each(function() { // 
		if (this.checked == true) {
			allvoucher = allvoucher + "," + this.value;
		}// 
	});

	var url = "getvouchertotalProcurement?vouchers=" + allvoucher + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getVoucherAmtRequest;
	req.open("GET", url, true);

	req.send(null);

}

function getVoucherAmtRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			document.getElementById("paymentAmount").value = req.responseText;
			document.getElementById("allvoucher").value = allvoucher;
		}
	}
}

function myFunction(btn, indx) {
	if (btn == "Cash") {
		document.getElementById("cheqType" + indx).disabled = "disabled";
		document.getElementById("cheqNo" + indx).disabled = "disabled";
		document.getElementById("bankName" + indx).disabled = "disabled";
		document.getElementById("bankid" + indx).disabled = "disabled";
		// document.getElementById("handoverTo"+indx).disabled="disabled" ;
		document.getElementById("cheq_receiver" + indx).disabled = "disabled";
	} else {
		document.getElementById("cheqType" + indx).disabled = "";
		document.getElementById("cheqNo" + indx).disabled = "";
		document.getElementById("bankName" + indx).disabled = "";
		document.getElementById("bankid" + indx).disabled = "";
		// document.getElementById("handoverTo"+indx).disabled="" ;
		document.getElementById("cheq_receiver" + indx).disabled = "";
	}
}

function myFunction2(btn, indx) {
	var totalcount = Number(document.getElementById("totalcount").value);
	var selectallid = document.getElementById("selectallid").checked;

	if (selectallid) {
		for (var i = 0; i < totalcount; i++) {
			if (btn == "Cash" || btn == "NEFT" || btn == "0") {
				document.getElementById("cheqType" + i).disabled = "disabled";
				document.getElementById("cheqNo" + i).disabled = "disabled";
				document.getElementById("bankName" + i).disabled = "disabled";
				document.getElementById("bankid" + indx).disabled = "disabled";
				// document.getElementById("handoverTo"+indx).disabled="disabled"
				// ;
				document.getElementById("cheq_receiver" + i).disabled = "disabled";
			} else {
				document.getElementById("cheqType" + i).disabled = "";
				document.getElementById("cheqNo" + i).disabled = "";
				document.getElementById("bankName" + i).disabled = "";
				document.getElementById("bankid" + i).disabled = "";
				// document.getElementById("handoverTo"+indx).disabled="" ;
				document.getElementById("cheq_receiver" + i).disabled = "";
			}
			if (i != indx) {
				if (btn == "0") {
					document.getElementById("paymentType" + i).value = "0";
				} else if (btn == "Cash") {
					document.getElementById("paymentType" + i).value = "Cash";
				} else if (btn == "NEFT") {
					document.getElementById("paymentType" + i).value = "NEFT";
				} else if (btn == "Cheque") {
					document.getElementById("paymentType" + i).value = "Cheque";
				}
			}
		}
	} else {
		if (btn == "Cash" || btn == "NEFT") {
			document.getElementById("cheqType" + indx).disabled = "disabled";
			document.getElementById("cheqNo" + indx).disabled = "disabled";
			document.getElementById("bankName" + indx).disabled = "disabled";
			document.getElementById("bankid" + indx).disabled = "disabled";
			// document.getElementById("handoverTo"+indx).disabled="disabled" ;
			document.getElementById("cheq_receiver" + indx).disabled = "disabled";
		} else {
			document.getElementById("cheqType" + indx).disabled = "";
			document.getElementById("cheqNo" + indx).disabled = "";
			document.getElementById("bankName" + indx).disabled = "";
			document.getElementById("bankid" + indx).disabled = "";
			// document.getElementById("handoverTo"+indx).disabled="" ;
			document.getElementById("cheq_receiver" + indx).disabled = "";
		}
	}

}
function myFunction3(indx) {
	var totalcount = Number(document.getElementById("totalcount").value);
	var selectallid = document.getElementById("selectallid").checked;
	var cheq_receiver = document.getElementById("cheq_receiver" + indx).value;
	if (selectallid) {
		for (var i = 0; i < totalcount; i++) {
			if (i != indx) {
				document.getElementById("cheq_receiver" + i).value = cheq_receiver;
			}
		}
	} else {
		document.getElementById("cheq_receiver" + indx).value = cheq_receiver;
	}
}

function myFunction4(indx) {
	var totalcount = Number(document.getElementById("totalcount").value);
	var selectallid = document.getElementById("selectallid").checked;
	var cheqType = document.getElementById("cheqType" + indx).value;
	if (selectallid) {
		for (var i = 0; i < totalcount; i++) {
			if (i != indx) {
				document.getElementById("cheqType" + i).value = cheqType;
			}
		}
	} else {
		document.getElementById("cheqType" + indx).value = cheqType;
	}
}

function myFunction5(indx) {
	var totalcount = Number(document.getElementById("totalcount").value);
	var selectallid = document.getElementById("selectallid").checked;
	var cheqNo = document.getElementById("cheqNo" + indx).value;
	if (selectallid) {
		for (var i = 0; i < totalcount; i++) {
			if (i != indx) {
				document.getElementById("cheqNo" + i).value = cheqNo;
			}
		}
	} else {
		document.getElementById("cheqNo" + indx).value = cheqNo;
	}
}

function myFunction6(indx) {
	var totalcount = Number(document.getElementById("totalcount").value);
	var selectallid = document.getElementById("selectallid").checked;
	var bankName = document.getElementById("bankName" + indx).value;
	if (selectallid) {
		for (var i = 0; i < totalcount; i++) {
			if (i != indx) {
				document.getElementById("bankName" + i).value = bankName;
			}
		}
	} else {
		document.getElementById("bankName" + indx).value = bankName;
	}
}

function myFunction7(indx) {
	var totalcount = Number(document.getElementById("totalcount").value);
	var selectallid = document.getElementById("selectallid").checked;
	var handoverTo = document.getElementById("handoverTo" + indx).value;
	if (selectallid) {
		for (var i = 0; i < totalcount; i++) {
			if (i != indx) {
				document.getElementById("handoverTo" + i).value = handoverTo;
			}
		}
	} else {
		document.getElementById("handoverTo" + indx).value = handoverTo;
	}
}
function myFunction8(indx) {
	var totalcount = Number(document.getElementById("totalcount").value);
	var selectallid = document.getElementById("selectallid").checked;
	var bankid = document.getElementById("bankid" + indx).value;
	var sel = document.getElementById("bankid" + indx);
	var bankname = sel.options[sel.selectedIndex].text;
	if (selectallid) {
		for (var i = 0; i < totalcount; i++) {
			if (i != indx) {
				document.getElementById("bankid" + i).value = bankid;
				document.getElementById("bankName" + i).value = bankname;
				document.getElementById("bankName" + i).readOnly = true;
			} else {
				document.getElementById("bankName" + i).value = bankname;
				document.getElementById("bankName" + i).readOnly = true;
			}
		}
	} else {
		document.getElementById("bankid" + indx).value = bankid;
		document.getElementById("bankName" + indx).value = bankname;
		document.getElementById("bankName" + indx).readOnly = true;
	}
}
function setPayAmt(total, idx) {

	if (document.getElementById("check" + idx).checked == true) {

		document.getElementById("paymentAmount" + idx).value = roundAbs(total);
		document.getElementById("check" + idx).value = "1";
	} else {

		document.getElementById("paymentAmount" + idx).value = "0";
		document.getElementById("check" + idx).value = "0";
	}
}

function isValid() {

	var totalcount = Number(document.getElementById("totalcount").value);
	var flag = false;
	var isError = false;
	var numbers = /^[0-9]+$/;
	var count = 1;
	var mainpayType = "";
	var maincheqNo = "";
	var mainbankid = "";
	for (var i = 0; i < totalcount; i++) {

		var ob = document.getElementById("check" + i);
		if (ob.checked == true) {
			flag = true;
			var payType = document.getElementById("paymentType" + i).value;
			var paymentAmount = document.getElementById("paymentAmount" + i).value;
			var bankName = document.getElementById("bankName" + i).value;
			var cheqNo = document.getElementById("cheqNo" + i).value;
			var handoverTo = document.getElementById("handoverTo" + i).value;
			var cheq_receiver = document.getElementById("cheq_receiver" + i).value;
			var bankid = document.getElementById("bankid" + i).value;
			var balance = document.getElementById("balance" + i).value;
			/*
			 * if(Number(paymentAmount)>Number(balance)){ jAlert('error',
			 * "Payment amount can not be greater than balance amount!", 'Error
			 * Dialog'); setTimeout(function() { $("#popup_container").remove();
			 * removeAlertCss(); }, alertmsgduration); isError=true; }else
			 */
			if (payType == '0') {
				jAlert('error', "Please select payment mode!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				isError = true;
			} else if (payType == "Cheque") {

				if (bankName.length < 1) {
					isError = true;
					document.getElementById("lblBankNameError" + i).innerText = "please enter Bank Name!";
				} else {
					document.getElementById("lblBankNameError" + i).innerText = "";
				}
				if (cheqNo.length < 1) {
					document.getElementById("lblChequeNoError" + i).innerText = "please enter cheque number!";
					isError = true;
				} else {
					document.getElementById("lblChequeNoError" + i).innerText = "";
				}
				if (paymentDate.length < 1) {
					document.getElementById("lblDateError").innerText = "please enter payment date!";
					isError = true;
				} else {
					document.getElementById("lblDateError").innerText = "";
				}
				if (handoverTo.length < 1) {
					document.getElementById("lblPresonNameError" + i).innerText = "please enter handover to!";
					isError = true;
				} else {
					document.getElementById("lblPresonNameError" + i).innerText = "";
				}
				if (cheq_receiver.length < 1) {
					document.getElementById("lblChequeReceiverError" + i).innerText = "please enter Cheque Receiver to!";
					isError = true;
				} else {
					document.getElementById("lblChequeReceiverError" + i).innerText = "";
				}
				//commenting because manoj sir told 
				//if (bankid == "0") {
					//isError = true;
					//jAlert('error', "Please select bank!", 'Error Dialog');
					//setTimeout(function() {
					//	$("#popup_container").remove();
					//	removeAlertCss();
					//}, alertmsgduration);
				//}

			} else if (!paymentAmount.match(numbers)) {

				isError = true;
				document.getElementById("lblPayAmtError" + i).innerText = "please enter Valid Payment amount!";
			} else {

				document.getElementById("lblPayAmtError" + i).innerText = "";
			}
			if (count == 1) {
				mainpayType = payType;
				maincheqNo = cheqNo;
				mainbankid = bankid;
			} else {
				if (mainpayType != payType) {
					jAlert('error', "Please select same paymode!",
							'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					isError = true;
				} else if (mainpayType == "Cheque") {
					if (maincheqNo !== cheqNo) {
						jAlert('error', "Please enter same cheque no.!",
								'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
						isError = true;
					} else if (mainbankid != bankid) {
						jAlert('error', "Please enter same bank!",
								'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
						isError = true;
					}
				}

			}

			count++;
		}

	}

	if (!flag) {

		isError = true;
		jAlert('error', "Please select at least one voucher!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	var isledger = 0;
	if (document.getElementById("ledgername")) {
		isledger = 1;
		var ledgername = document.getElementById("ledgername").value;
		if (ledgername == '0') {
			isError = true;
			jAlert('error', "Please select ledger!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	}

	if (isError == true) {
		return false;
	} else {
		var alltotalamt = Number(document.getElementById("alltotal").value);
		if (alltotalamt < 0) {
			return false;
			jAlert('error', "Total amount can not be in negative!",
					'Error Dialog');

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else {
			if (isledger == 1) {
				checkledgerPreviousBalance();
			} else {
				var x = confirm("Are You Sure!!");
				if (x) {
					$("#dashboardloaderPopup").modal('show');
					document.getElementById("salebutton").innerHTML = "";
					document.getElementById("paymentfrm").submit();
					return true;
				} else {
					return false;
				}
			}

		}
	}
}

function checkledgerPreviousBalance() {
	var ledgername = document.getElementById("ledgername").value;
	var url = "getpreviousledgerbalanceProcurement?ledgername=" + ledgername
			+ "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = checkledgerPreviousBalanceRequest;
	req.open("GET", url, true);
	req.send(null);
}

function checkledgerPreviousBalanceRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			var data = Number(str);
			var alltotalamt = Number(document.getElementById("alltotal").value);
			if (alltotalamt > data) {
				return false;
				jAlert('error', "Total amount can not be greater than ledger!",
						'Error Dialog');

				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			} else {
				document.getElementById("salebutton").innerHTML = "";
				document.getElementById("paymentfrm").submit();
				return true;
			}
		}
	}
}

function deleteRow(r) {

	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("prodTable").deleteRow(i);
	calTotalAmt();

}

function deleteTemp(r) {

	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("mynewtab").deleteRow(i);

}

function viewOrder(id, pid) {

	var url = "vieworderProcurement?id=" + id + "&pid=" + pid + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = viewOrderRequest;
	req.open("GET", url, true);

	req.send(null);

}

function viewOrderRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var str = req.responseText;
			var data = str.split("~");
			document.getElementById("orderdata").innerHTML = data[0];
			document.getElementById("receiveddata").innerHTML = data[1];
			document.getElementById("pheadData").innerHTML = data[2];
			document.getElementById("accountDiv").innerHTML = data[3];
			$("#viewproduct").modal("show");
		}
	}
}

var vendorstateid = 0;

function getvendorProduct(vendorid) {

	$("#baselayout1loaderPopup").modal("show");
	var url = "getvendorproductProcurement?vendorid=" + vendorid + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getvendorProductRequest;
	req.open("GET", url, true);

	req.send(null);

}

function getvendorProductRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var str = req.responseText;

			var data = str.split("~");

			document.getElementById("prodListId").innerHTML = data[0];
			document.getElementById("product_id").value = prodid;
			$("#product_id").trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});

			document.getElementById("debit").value = data[1];
			document.getElementById("credit").value = data[2];
			vendorstateid = data[3];
			$("#baselayout1loaderPopup").modal("hide");
		}
	}
}

function addProductForProcurement(tableId) {
	var warehouse = document.getElementById("warehouse").value;
	/*
	 * var category_id= document.getElementById("category_id").value; var
	 * subcategory_id= document.getElementById("subcategory_id").value;
	 */
	var product_id = document.getElementById("product_id").value;
	var vendorid = document.getElementById("vendorid").value;
	/* var qty =document.getElementById("qty").value; */
	tindex = Number(document.getElementById("tempcount").value);
	var procurementid = document.getElementById("procurementid").value;
	var haslocation = document.getElementById("haslocation").value;

	if (warehouse == '0' && haslocation == '1') {

		jAlert('error', "Please select store!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (vendorid == '0') {

		jAlert('error', "Please select supplier!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (product_id == '0') {

		jAlert('error', "Please select product!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}

	else {

		var table = document.getElementById(tableId);
		var rowCount = table.rows.length;
		row = table.insertRow(rowCount);
		var repeat = document.getElementById("repeat").value;
		counts = tindex;
		var url = "addnewproductProcurement?vendorid=" + vendorid
				+ "&catalogueid=" + product_id + "&count=" + counts
				+ "&repeat=" + repeat + "&procurementid=" + procurementid
				+ "&warehouse=" + warehouse + "";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = addProductForProcurementRequest;
		req.open("GET", url, true);

		req.send(null);

	}

}

function addProductForProcurementRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var str = req.responseText;
			if (str == "0") {

				jAlert('error', "Product already exists!", 'Error Dialog');

				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);

			} else {
				var data = str.split("~");
				row.innerHTML = data[0];
				document.getElementById("vendorName").innerHTML = data[1];
				document.getElementById("procurementid").value = data[2];
				/*
				 * $("#expiry_date"+tindex+"").datepicker({
				 * 
				 * dateFormat : 'dd-mm-yy', yearRange: yearrange, minDate :
				 * '30-12-1880', changeMonth : true, changeYear : true
				 * 
				 * });
				 */
				$('#expiry_date' + tindex + '').MonthPicker({
					UseInputMask : true,
					ValidationErrorMessage : 'Invalid Date!'
				});
				document.getElementById("repeat").value = "0";
				tindex++;
				document.getElementById("tempcount").value = tindex;

				calVatTotal();
			}

		}
	}
}

function calSalPer() {

	$('.dclass').each(function() {

		var i = this.value;
		var mrp = document.getElementById("mrp" + i).value;
		var pack = document.getElementById("pack" + i).value;
		var result = 0;
		result = parseFloat(mrp) / parseInt(pack);
		var num = parseFloat(result);
		num = roundTwo(num);
		document.getElementById("sale_price" + i).value = num;

	});

	if (isfromlongpo == '1') {
		getvendorstateforlongpo();
	}else{
		if (document.getElementById("isfromuploadstock")) {
			if (document.getElementById("isfromuploadstock").value == "1") {
				calTotalAmt();
			} else if (document.getElementById("calculatereqqtyafterpackchange")) {
				calTotalAmt();
			}
		} else if (document.getElementById("calculatereqqtyafterpackchange")) {
			calTotalAmt();
		}
	}

}

function calTotalAmt() {

	var total = 0;

	$('.dclass')
			.each(
					function() {

						var i = this.value;
						var rate = Number(document
								.getElementById("purchase_price" + i).value);
						var qty = Number(document.getElementById("received_qty"
								+ i).value);
                        var pack = document.getElementById("pack" + i).value;
                        
                        var discrate = Number(document
								.getElementById("discount" + i).value);
						//For parihar		
						var schemerate = Number(document
								.getElementById("scheme" + i).value);
                       
						/*
						 * var maxorder=
						 * Number(document.getElementById("maxorder"+i).value);
						 * if(qty>maxorder){ jAlert('Success', "Product is
						 * Crossing Max Order Guidelines!", 'Success Dialog');
						 * 
						 * setTimeout(function() {
						 * $("#popup_container").remove(); removeAlertCss(); },
						 * alertmsgduration); }
						 */

						var temp = parseFloat(rate) * parseFloat(qty);
						temp = roundTwo(temp);
						document.getElementById("amount" + i).innerHTML = temp;
						total = total + temp;

						if (document.getElementById("newreceived_qty" + i)) {
							var pack = document.getElementById("pack" + i).value;
							document.getElementById("newreceived_qty" + i).value = parseFloat(pack)
									* parseFloat(qty);
						}

					});
	total = roundTwo(total);
	document.getElementById("subTotal").value = total;
	getNetAmtVat();

}
function calSubTotal() {

	var total = 0;

	$('.dclass')
			.each(
					function() {

						var i = this.value;
						var rate = Number(document
								.getElementById("purchase_price" + i).value);
						var qty = Number(document.getElementById("received_qty"
								+ i).value);
						/*
						 * var maxorder=
						 * Number(document.getElementById("maxorder"+i).value);
						 * 
						 * if(qty>maxorder){ jAlert('Success', "Product is
						 * Crossing Max Order Guidelines!", 'Success Dialog');
						 * 
						 * setTimeout(function() {
						 * $("#popup_container").remove(); removeAlertCss(); },
						 * alertmsgduration); }
						 */
						var temp = parseFloat(rate) * parseFloat(qty);
						temp = roundTwo(temp);
						document.getElementById("amount" + i).innerHTML = temp;
						total = total + temp;

						var vat = Number(document.getElementById("vat" + i).value);
						if (document.getElementById("netamount" + i)) {
							var tempvatamount = parseFloat(rate)
									* parseFloat(vat) / 100;
							tempvatamount = tempvatamount * qty;
							tempvatamount = roundTwo(tempvatamount);
							document.getElementById("netamount" + i).innerHTML = roundTwo(temp
									+ tempvatamount);
							if (document.getElementById("gstcalamount" + i)) {
								document.getElementById("gstcalamount" + i).innerHTML = roundTwo(tempvatamount);
							}
						}

						if (document.getElementById("newreceived_qty" + i)) {
							var pack = document.getElementById("pack" + i).value;
							document.getElementById("newreceived_qty" + i).value = parseFloat(pack)
									* parseFloat(qty);
						}
						//For parihar
						var discrate = Number(document
								.getElementById("discount" + i).value);
								
						var schemerate = Number(document
								.getElementById("scheme" + i).value);
								
					 	
                        var discamt=temp*parseFloat(discrate) / 100;
						var schemeamt=temp-parseFloat(discamt);
						var schmeamtafterdis=0;
						if(schemerate!='0'){
							schmeamtafterdis=schemeamt*(schemerate)/100
						}
						var discminus=temp-(discamt+schmeamtafterdis);
						discminus = roundTwo(discminus);
						document.getElementById("discountamt" + i).innerHTML = parseFloat(discminus);
						document.getElementById("totalschmedisc" + i).value = parseFloat(discminus);
						document.getElementById("disamt" + i).innerHTML=roundTwo(discamt+schmeamtafterdis);
					 			
                     //end for parihar
					});
	total = roundTwo(total);
	document.getElementById("subTotal").value = total;

	// 03 Sep 2018
	if (document.getElementById("isfromeditpo")) {
		var isfromeditpo = document.getElementById("isfromeditpo").value;
		if (isfromeditpo == '1') {
			calVatDiscount()
		}
	}
}

function calVatTotal() {

	var vat = 0;
	$('.dclass').each(function() {

		var i = this.value;
		var mrp = Number(document.getElementById("purchase_price" + i).value);
		var temvat = Number(document.getElementById("vat" + i).value);

		var temp = parseFloat(mrp) * parseFloat(temvat) / 100;
		vat = vat + temp;

	});
	vat = roundTwo(vat);
	getNetAmtVat();

}

function caldiscountTot() {

	var totaldisc = 0;
	$('.dclass').each(function() {

		var i = this.value;
		var rate = Number(document.getElementById("purchase_price" + i).value);
		var qty = Number(document.getElementById("received_qty" + i).value);
		var discrate = Number(document.getElementById("discount" + i).value);

		var temp = parseFloat(rate) * parseInt(qty);
		var discamt = temp * discrate / 100;
		totaldisc = totaldisc + discamt;
	});

	document.getElementById("discount").value = roundTwo(totaldisc);
	calDisc();
}

function getNetAmtVat() {

	var vatRates = '';
	var totalAmt = 0;
	var discAmtRates = 0;
	// Picachu 18-12-2018
	var discsubtotal = Number(document.getElementById("subTotal").value);
	var disctype = Number(document.getElementById("disctype").value);
	var discountmain = Number(document.getElementById("discount").value);
	var hiddenclinicid = document.getElementById("hiddenclinicid").value;
	var discratenew = 0;

	if (disctype == '1') {
		if (discountmain > discsubtotal) {
			document.getElementById("discount").value = 0;
			jAlert('error', "Entered discount greater than subtotal:"
					+ discsubtotal + "!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			discountmain = 0;
		} else if (discountmain < 0) {
			document.getElementById("discount").value = 0;
			jAlert('error', "Entered discount can't in negative!",
					'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
		discratenew = (discountmain / discsubtotal) * 100.0;
		discratenew = roundTwo(discratenew);
	}
	$('.dclass')
			.each(
					function() {

						var i = this.value;
						var rate = Number(document
								.getElementById("purchase_price" + i).value);
						var vat = Number(document.getElementById("vat" + i).value);
						var qty = Number(document.getElementById("received_qty"
								+ i).value);
						var discrate = Number(document
								.getElementById("discount" + i).value);
						//For parihar		
						var schemerate = Number(document
								.getElementById("scheme" + i).value);		
								//var schemerate=0;
						// var disctype=
						// document.getElementById("disctype").value;
						if (disctype == '1') {
							document.getElementById("discount" + i).value = 0;
							// discrate=0;
							discrate = discratenew;
						} else {
							if (discrate > 100) {
								discrate = 0;
								document.getElementById("discount" + i).value = 0;
								jAlert('error',
										"Entered discount greater than 100!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
							} else if (discrate < 0) {
								discrate = 0;
								document.getElementById("discount" + i).value = 0;
								jAlert('error',
										"Entered discount can't in negative!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
							}
						}

						var temp = parseFloat(rate) * parseFloat(qty);
						
						 //for parihar	
						var discamt=temp*parseFloat(discrate) / 100;
						var schemeamt=temp-parseFloat(discamt);
						var schmeamtafterdis=0;
						if(schemerate!='0'){
							schmeamtafterdis=schemeamt*(schemerate)/100
						}
						var discminus=temp-(discamt+schmeamtafterdis);
						discminus = roundTwo(discminus);
						document.getElementById("discountamt" + i).innerHTML = parseFloat(discminus);
						document.getElementById("totalschmedisc" + i).value = parseFloat(discminus);
						document.getElementById("disamt" + i).innerHTML=roundTwo(discamt+schmeamtafterdis);
						
						if(hiddenclinicid){
							var tempvatamount = parseFloat(discminus)
									* parseFloat(vat) / 100;
							document.getElementById("netamount" + i).innerHTML = roundTwo(discminus
									+ tempvatamount);
							if (document.getElementById("gstcalamount" + i)) {
								document.getElementById("gstcalamount" + i).innerHTML = roundTwo(tempvatamount);
							}		
					  }else{		

						if (document.getElementById("netamount" + i)) {
							var tempvatamount = parseFloat(rate)
									* parseFloat(vat) / 100;
							tempvatamount = tempvatamount * qty;
							tempvatamount = roundTwo(tempvatamount);
							document.getElementById("netamount" + i).innerHTML = roundTwo(temp
									+ tempvatamount);
							if (document.getElementById("gstcalamount" + i)) {
								document.getElementById("gstcalamount" + i).innerHTML = roundTwo(tempvatamount);
							}
						}
					}
						vatRates = vatRates + "," + vat;
						totalAmt = totalAmt + "," + temp + "~" + vat;
						//discrate=discrate+discrate;
						//schemerate=schemerate+schemerate;
						discAmtRates = discAmtRates + "," + temp + "~" + vat
								+ "~" + discrate+"~" + schemerate;
					});
	var url = "calnetvatProcurement?totalvat=" + vatRates + "&totalamt="
			+ totalAmt + ",&totaldiscrate=" + discAmtRates + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getNetAmtVatRequest;
	req.open("GET", url, true);

	req.send(null);

}

function getNetAmtVatRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			document.getElementById("tTaxData").innerHTML = req.responseText;
			calVatDiscount();
		}
	}
}

function calVatDiscount() {

	var index = document.getElementById("vatcount").value;
	var disctotal = 0;
	var totalvat = 0;
	for (var i = 0; i < index; i++) {

		var vatrate = Number(document.getElementById("vatrate" + i).innerHTML);
		var totalVatAmt = Number(document.getElementById("totalVatAmt" + i).innerHTML);
		var vatgiven = Number(document.getElementById("vatgiven" + i).value);

		var temTaxAmt = vatgiven * parseFloat(vatrate) / 100;
		if (vatgiven < 0) {
			document.getElementById("vatgiven" + i).value = totalVatAmt;
			vatgiven = totalVatAmt;
			temTaxAmt = 0;
			jAlert('error', "Taxable Amt. can't be negative.!!!",
					'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (vatgiven > totalVatAmt) {
			document.getElementById("vatgiven" + i).value = totalVatAmt;
			vatgiven = totalVatAmt;
			temTaxAmt = 0;
			jAlert('error', "Taxable Amt. can't be greater than total Amt.!!!",
					'Error Dialog');

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
		document.getElementById("taxAmt" + i).innerHTML = roundTwo(temTaxAmt);
		document.getElementById("taxAmtVal" + i).value = roundTwo(temTaxAmt);
		var disc = parseFloat(totalVatAmt - vatgiven);
		totalvat = totalvat + temTaxAmt;

		document.getElementById("discvat" + i).innerHTML = roundTwo(disc);
		document.getElementById("discvatVal" + i).value = roundTwo(disc);
		disctotal = disctotal + disc;

	}
	
	if(hiddenclinicid){
		getdistotatlforparihar();
	}else{
		
    var subTotal = Number(document.getElementById("subTotal").value);
	var debit = Number(document.getElementById("debit").value);
	var subcharge = Number(document.getElementById("subcharge").value);
	var credit = Number(document.getElementById("credit").value);

	var disctype = document.getElementById("disctype").value;
	if (disctype == '0') {
		document.getElementById("discount").value = roundTwo(disctotal);
	}
	var totaldisc = Number(document.getElementById("discount").value);
	var plus = subTotal + totalvat + credit + subcharge;
	var minus = totaldisc + debit;
	var net = plus - minus;
	totalvat = roundTwo(totalvat);

	document.getElementById("vat").value = roundTwo(totalvat);
	// document.getElementById("subDisc").innerHTML=roundTwo(disctotal);
	//document.getElementById("netpay").value = Math.round(net);
	//document.getElementById("nettemp").value = Math.round(net);
	net=roundAbs(net);
	//document.getElementById("netpay").value = net;
	document.getElementById("nettemp").value =net;

	if (vendorstateid == stateid) {

		var divide = totalvat / 2;
		document.getElementById("sgst").value = roundTwo(divide);
		document.getElementById("cgst").value = roundTwo(divide);
		document.getElementById("tsgst").value = roundTwo(divide);
		document.getElementById("tcgst").value = roundTwo(divide);

	} else {

		document.getElementById("igst").value = totalvat;
		document.getElementById("tigst").value = totalvat;
	}
	if (document.getElementById("discountedamt")) {
		var discountedamt = subTotal - totaldisc;
		document.getElementById("discountedamt").value = roundTwo(discountedamt);
	}
	
   }
}

function savePoReceived() {
	var isdelivermemo = document.getElementById("isdelivermemo").checked;
	var delivermemoid = document.getElementById("delivermemoid").value;
	var delivermemodate = document.getElementById("delivermemodate").value;
	var voucherno = document.getElementById("voucherno").value;
	var voucherdate = document.getElementById("voucherdate").value;
	var security_date = document.getElementById("security_date").value;
	var security_no = document.getElementById("security_no").value;
	var firstnetamout = document.getElementById("firstnetamout").value;
	var lmhAccess = document.getElementById("lmhAccess").value;
	var clinicid=document.getElementById("hiddenclinicid").value;
	
	var fool = false;
	if (isdelivermemo == true) {
		if (delivermemoid == "") {
			fool = true;
			jAlert('error', "Please enter deliver memo no!", 'Error Dialog');

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (delivermemoid.includes("'")) {
			fool = true;
			jAlert('error', "Please enter invoice no without ' this symbol!",
					'Error Dialog');

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (delivermemodate == "") {
			fool = true;
			jAlert('error', "Please select deliver memo date!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (firstnetamout == '') {
			fool = true;
			jAlert('error', "Please enter GRN Amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (parseFloat(firstnetamout) <= 0) {
			fool = true;
			jAlert('error', "Please enter GRN Amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	} else {

		if (voucherno == "") {
			fool = true;
			jAlert('error', "Please enter invoice no!", 'Error Dialog');

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (voucherno.includes("'")) {
			fool = true;
			jAlert('error', "Please enter invoice no without ' this symbol!",
					'Error Dialog');

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (voucherdate == "") {
			fool = true;
			jAlert('error', "Please select invoice date!", 'Error Dialog');

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (security_date == "") {
			fool = true;
			jAlert('error', "Please select security date!", 'Error Dialog');

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (security_no == "") {
			fool = true;
			jAlert('error', "Please select security no!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (firstnetamout == '') {
			fool = true;
			jAlert('error', "Please enter GRN Amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (parseFloat(firstnetamout) <= 0) {
			fool = true;
			jAlert('error', "Please enter GRN Amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	}

	if (fool) {

	} else {
		var error = false;

		$('.dclass')
				.each(
						function() {
							var i = this.value;

							var batch_no = document.getElementById("batch_no"
									+ i).value;
							var expiry = document.getElementById("expiry_date"
									+ i).value;
							var hsnno = document.getElementById("hsnno" + i).value;
							var mfg = document.getElementById("mfg" + i).value;
							var mrp = document.getElementById("mrp" + i).value;
							var sale_price = document
									.getElementById("sale_price" + i).value;
							var vat = document.getElementById("vat" + i).value;
							var purchase_price = document
									.getElementById("purchase_price" + i).value;
							var received_qty = document
									.getElementById("received_qty" + i).value;
							var pack = document.getElementById("pack" + i).value;
							var discount = document.getElementById("discount"
									+ i).value;
							var freeqty = document
									.getElementById("freeqty" + i).value;
							var reqqunt=document.getElementById("qun" + i).innerHTML;
							if (!checkNumberOrNotofprocurement(pack)) {
								jAlert('error', "Please enter valid pack!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (hsnno == '') {
								jAlert('error', "Please enter HSN No!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (mfg == '') {
								jAlert('error', "Please enter MFG!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (mrp == '') {
								jAlert('error', "Please enter MRP!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (parseFloat(mrp) < 0) {
								jAlert('error', "Please enter valid MRP!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (sale_price == '') {
								jAlert('error', "Please enter sale price!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (parseFloat(sale_price) < 0) {
								jAlert('error', "Please enter valid per unit!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (vat == 'GST') {
								jAlert('error', "Please select GST rate!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (purchase_price == ''
									|| purchase_price == '0') {
								jAlert('error', "Please enter rate!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (parseFloat(purchase_price) < 0) {
								jAlert('error', "Please enter valid rate!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (parseFloat(mrp) < parseFloat(purchase_price)) {
								jAlert(
										'error',
										"Purchase Price is greater than Sale Price!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (batch_no == '') {
								jAlert('error', "Please enter batch no!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (lmhAccess==0 && expiry == '') {
								jAlert('error', "Please select expiry!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (expiry!='' && expiry.split("/")[0] > 12) {
								jAlert('error',
										"Please select valid expiry date!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (received_qty == '') {
								jAlert('error', "Please enter pack quantity!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (!checkNumberOrNotofprocurement(received_qty)) {
								jAlert('error', "Please enter valid pack qty!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (parseFloat(discount) < 0) {
								jAlert('error', "Please enter valid discount!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							} else if (Number(freeqty) < 0) {
								jAlert('error', "Please enter valid free qty!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
							}
							if(clinicid=='vspm'){
							     if(Number(received_qty) > Number(reqqunt) && reqqunt!=''){
								jAlert('error', "Please enter valid quantity less than Pack qty!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								error = true;
								
								
							}
						}	

							/*
							 * else {
							 * 
							 * error=false; }
							 */

						});

		if (!error) {
			// 18-12-2018
			// While doing GRN when user enter same invoice number for Same
			// supplier then don't allow user to enter in GRN procurement
			// document.getElementById("formMyPo").submit();
			var discount = document.getElementById("discount").value;
			var surcharge = document.getElementById("subcharge").value;
			var credit = document.getElementById("credit").value;
			var debit = document.getElementById("debit").value;
			var netpay = document.getElementById("netpay").value;

			if (parseFloat(discount) < 0) {
				jAlert('error', "Please enter valid discount!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			} else if (parseFloat(surcharge) < 0) {
				jAlert('error', "Please enter valid surcharge!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			} else if (parseFloat(credit) < 0) {
				jAlert('error', "Please enter valid credit!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			} else if (parseFloat(debit) < 0) {
				jAlert('error', "Please enter valid debit!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			} else if (parseFloat(netpay) < 0) {
				jAlert('error', "Please enter valid net payble amt!",
						'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			} else if (parseFloat(firstnetamout) != parseFloat(netpay)) {
				jAlert('error', "GRN amount and net payable amount not match!",
						'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}

			else {
				checkVendorRepeatVoucher();
			}

		}

	}

}

function checkVendorRepeatVoucher() {
	var voucher = document.getElementById("voucherno").value;
	var vendorid = document.getElementById("vendorid").value;
	var isfromeditpo = 0;
	var procurementid = document.getElementById("procurementid").value;
	if (document.getElementById("isfromeditpo")) {
		isfromeditpo = 1;
	}
	if (document.getElementById("isfromlongpo")) {
		vendorid = document.getElementById("vendoridlongpo").value;
	}
	var url = "vendorvoucherexistanceProcurement?vendorid=" + vendorid
			+ "&isfromeditpo=" + isfromeditpo + "&procurementid="
			+ procurementid + "&voucher=" + voucher + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = checkVendorRepeatVoucherRequest;
	req.open("GET", url, true);
	req.send(null);
}
function checkVendorRepeatVoucherRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			if (data == '1') {

				jAlert('error', "Invoice no. already present!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				document.getElementById("voucherno").value = "";
			} else {
				var x = confirm("Are You Sure!!");
				if (x) {
					$("#dashboardloaderPopup").modal('show');
					document.getElementById("formMyPo").submit();
				}
			}
		}
	}
}

function addNewProd() {

	var vendorid = document.getElementById("vendorid").value;

	if (vendorid == '' || vendorid == "0") {
		jAlert('error', "Please select supplier!", 'Error Dialog');

		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} else {

		var url = "vendornameProcurement?vendorid=" + vendorid + "";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = addNewProdRequest;
		req.open("GET", url, true);

		req.send(null);

	}

}

function addNewProdRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			document.getElementById("suppliername").innerHTML = req.responseText;
			$("#addnewproduct").modal("show");
		}
	}
}

var venid = 0;
function saveProductProcurement() {

	var vendorid = document.getElementById("vendorid").value;
	var genericname = document.getElementById("genericname").value;
	var prod_name = document.getElementById("prodname").value;
	var loc = document.getElementById("location").value;
	venid = vendorid;

	var url = "saveproductajaxProcurement?vendorid=" + vendorid
			+ "&genericname=" + genericname + "&prod_name=" + prod_name
			+ "&location=" + loc + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = saveProductProcurementRequest;
	req.open("GET", url, true);

	req.send(null);

}
var prodid = 0;
function saveProductProcurementRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			$("#addnewproduct").modal("hide");
			var pid = req.responseText;
			getvendorProduct(venid);
			prodid = pid;
			document.getElementById("repeat").value = pid;

		}
	}
}

function chkvoucher(val) {

	var url = "checkvoucherProcurement?voucher=" + val + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = chkvoucherRequest;
	req.open("GET", url, true);
	req.send(null);

}

function chkvoucherRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			document.getElementById("errvoucher").innerText = "";

			var res = req.responseText;
			if (res == "1") {

				document.getElementById("errvoucher").innerText = "voucher number is exist!";
				document.getElementById("voucherno").value = "";
			}

		}
	}
}

var isfromeditponew = 0;
function setTindex() {

	tindex = Number(document.getElementById("tindex").value);

/*	$('.dclass').each(function() {

		var i = this.value;
		$("#expiry_date" + i).datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
	});*/

	if (document.getElementById("isfromeditpo")) {
		var isfromeditpo = document.getElementById("isfromeditpo").value;
		if (isfromeditpo == '1') {
			isfromeditponew = 1;
			// 16 Nov 2018
			// Pharmacy state and vendor state not set while edit grn
			// calSubTotal();
			getvendorstateforlongpo();
		}
	}
}

function callPopup(val) {

	var url = "getvouchersvendorProcurement?vendorid=" + val + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = callPopupRequest;
	req.open("GET", url, true);

	req.send(null);

}

function callPopupRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			document.getElementById("alldata").innerHTML = req.responseText;
			$('#returnpro').modal("show");
		}
	}
}

function openHideDiv(id) {

	if (document.getElementById('vprod' + id).style.display == '') {
		document.getElementById('vprod' + id).style.display = 'none';
	} else {
		document.getElementById('vprod' + id).style.display = '';
	}

}

function allreturnprod() {

	var all = 0;

	$('.dcase').each(function() {

		if (this.checked == true) {

			var d = this.value;
			var qty = document.getElementById("retqty" + d).value;
			var data = d + "-" + qty;
			all = all + "," + data;
		}

	});

	document.getElementById("all").value = all;
	document.getElementById("rtform").submit();

}

var stateid = 0;
var isfromlongpo = '0';
function setState(val) {
	isfromlongpo = val;

	/* var url="getstateProcurement"; */
	var url = "getstateBookAppointmentAjax";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setStateRequest;
	req.open("GET", url, true);

	req.send(null);

}
function setStateRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			stateid = req.responseText;
			if (isfromlongpo == '1') {
				calSalPer();
			}
			if (isfromeditponew == 1) {
				calSubTotal();
			}
			if (document.getElementById("isfromuploadstock")) {
				if (document.getElementById("isfromuploadstock").value == "1") {
					// calTotalAmt();
					calSalPer();
				}
			}
		}
	}
}

function calDisc() {

	var disc = parseFloat(document.getElementById("discount").value);
	if (disc > 0) {

		var index = document.getElementById("vatcount").value;
		var discalloc = parseFloat(disc / index);
		for (var i = 0; i < index; i++) {

			var vatgiven = parseFloat(document
					.getElementById("totalVatAmt" + i).innerHTML);
			var vatrate = Number(document.getElementById("vatrate" + i).innerHTML);
			var tmp = vatgiven - discalloc;
			document.getElementById("vatgiven" + i).value = parseFloat(tmp);
		}

		calVatDiscount();
	}

}

var procureid
function openConfirmPopup(id) {
	$("#dashboardloaderPopup").modal("show");
	document.getElementById("proc_id").value = id;
	procureid = id;
	var url = "getconfirmprodProcurement?id=" + id + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = openConfirmPopupRequest;
	req.open("GET", url, true);
	req.send(null);
}
function openConfirmPopupRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var str = data.split("~");
			document.getElementById("innerData").innerHTML = str[0];
			document.getElementById("vendorname").innerHTML = str[1];
			document.getElementById("vendorid").value = str[2];
			document.getElementById("pono").innerHTML = str[3];
			document.getElementById("podate").innerHTML = str[4];
			// document.getElementById("podate").innerHTML=str[5];
			document.getElementById("tfootconfirmpo").innerHTML = str[6];
			var isagreement = str[7];
			if (isagreement == '1') {
				document.getElementById("cancelconfimpobtn").disabled = true;
				document.getElementById("saveconfimpobtn").disabled = true;
				document.getElementById("addnewbtndiv").style.display = "none";
			}
			document.getElementById("newwarehosuedivaprovepo").innerHTML = str[8];
			$("#warehouse").trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});
			document.getElementById("poSequence").innerHTML = str[9];
			
			
			$("#dashboardloaderPopup").modal("hide");
			$("#confirmprod").modal("show");
			var po_discType = document.getElementById("po_discType").value;
			changePoDiscType1(po_discType);
			//setAllConfirmData();
		}
	}
}

function changePoDiscType1(val){
	if(val==0){
		document.getElementById("po_disc").readOnly= true;
		document.getElementById("discount").readOnly=false;
		$('.myclassJ').each(function() {
			document.getElementById("discounts"+this.value).readOnly = false;
		});
	}else{
		document.getElementById("po_disc").readOnly= false;
		document.getElementById("discount").readOnly=true;
		$('.myclassJ').each(function() {
			document.getElementById("discounts"+this.value).readOnly = true;
		});
	}
	setAllConfirmData();
}


function openPrintPopup(id) {

	var url = "printconfirmProcurement?id=" + id + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = openPrintPopupRequest;
	req.open("GET", url, true);

	req.send(null);

}
function openPrintPopupRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var data = req.responseText;
			var str = data.split("~");
			document.getElementById("innerData1").innerHTML = str[0];
			document.getElementById("vendorname1").innerHTML = str[1];
			document.getElementById("printremark").innerHTML = str[2];
			$("#printprod").modal("show");
		}
	}
}

function submitCOnfiirm() {
	var flagcheck = false;
	$('.myclassJ')
			.each(
					function() {
						var i = this.value;
						var amt = document.getElementById('qty' + i).value;
						var rate = document.getElementById('rate' + i).value;
						var discount = document.getElementById('discounts' + i).value;
						var temp = 0;
						if (amt == '' || amt == '0') {
							flagcheck = true;
							jAlert('error', "Please enter quantity!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						} else if (Number(amt) < 0) {
							flagcheck = true;
							jAlert('error',
									"Please enter non negative quantity!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						} else if (!checkNumberOrNotofprocurement(amt)) {
							jAlert('error', "Please enter valid qty !",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
							flagcheck = true;
						} else if (rate == '' || rate == '0') {
							flagcheck = true;
							jAlert('error', "Please enter rate!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						} else if (Number(rate) < 0) {
							flagcheck = true;
							jAlert('error', "Please enter non negative rate!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						} else if (Number(discount) != 0) {
							if (Number(discount) < 0) {
								flagcheck = true;
								jAlert('error',
										"Please enter non negative discount !",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
							} else if (Number(discount) > 100) {
								flagcheck = true;
								jAlert(
										'error',
										"Please enter discount less than 100%!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
							}
						}
					});
	if (!flagcheck) {
		$("#cnfirm").modal("show");

		/*
		 * var r = confirm("Are you sure you want to send email to supplier?");
		 * if (r == true) { document.getElementById("mailcheck").value="0";
		 * }else { document.getElementById("mailcheck").value="1"; }
		 */

	}

}

function sureSub(val) {
	$("#cnfirm").modal("hide");
	if (val) {
		document.getElementById("mailcheck").value = "0";
	} else {
		document.getElementById("mailcheck").value = "1";
	}
	$("#dashboardloaderPopup").modal("show");
	document.getElementById("procform").submit();
}
function getprodData(id) {

	var vendorid = document.getElementById("vendorid").value;
	var url = "setproductProcurement?id=" + id + "&vendorid=" + vendorid + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getprodDataRequest;
	req.open("GET", url, true);

	req.send(null);
}
function getprodDataRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var str = data.split("~");
			var dd = str[0].split("^");
			// document.getElementById("medicine_type").value=dd[0];
			document.getElementById("pack").value = dd[1];
			document.getElementById("medicine_shedule").value = dd[2];
			document.getElementById("subtype").innerHTML = str[1];

		}
	}
}

function viewallsupplier(pid) {

	var url = "viewsupplierhistoryProcurement?pid=" + pid + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = viewallsupplierRequest;
	req.open("GET", url, true);

	req.send(null);

}
function viewallsupplierRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var str = data.split("~");
			document.getElementById("innerProduct").innerHTML = str[0];
			document.getElementById("tabdata").innerHTML = str[1];
			$("#viewdetailspro").modal("show");

		}
	}
}

function calDiscNet(val) {
	var net = document.getElementById("nettemp").value;
	var netPay = roundAbs(net - val);
	document.getElementById("netpay").value = netPay;
	var subTotal = Number(document.getElementById("subTotal").value);
	var totaldisc = Number(document.getElementById("discount").value);
	if (document.getElementById("discountedamt")) {
		var discountedamt = subTotal - totaldisc;
		document.getElementById("discountedamt").value = roundTwo(discountedamt);
	}
}

function getDiscType(val) {

	if (val == '0') {
		document.getElementById("discount").readOnly = true;
		$('.dclass').each(function() {

			var i = this.value;
			document.getElementById("discount" + i).readOnly = false;
		});
	} else {
		$('.dclass').each(function() {

			var i = this.value;
			document.getElementById("discount" + i).value = 0;
			document.getElementById("discount" + i).readOnly = true;
		});
		document.getElementById("discount").readOnly = false;
	}
	if(cashPoDisc==1){
		document.getElementById("disctype").value=val;
		document.getElementById("discount").value = document.getElementById("poDiscAmt").value;
		cashPoDisc = 0;
		setState(1);
	}else{
		document.getElementById("discount").value = 0;
		getNetAmtVat();
	}
	
}

function getVendWarehouse(val) {

	var url = "warehousecategory1Procurement?id=" + val + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getVendWarehouseRequest;
	req.open("GET", url, true);

	req.send(null);
}

function getVendWarehouseRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("categorydiv").innerHTML = req.responseText;
			$("#category_id").trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});
		}
	}
}

function getvendorsubcategory(id) {

	var url = "getvendorsubcategoriesProcurement?id=" + id + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getvendorsubcategoryRequest;
	req.open("GET", url, true);

	req.send(null);
}

function getvendorsubcategoryRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("subdiv").innerHTML = req.responseText;
			$("#subcategory_id").trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});
		}
	}
}

function getVendorSubPoproduct(id) {

	$("#dashboardloaderPopup").modal("show");
	var vendorid = document.getElementById("vendorid").value;
	var category = document.getElementById("category_id").value;
	var url = "setvendorproductProcurement?medicinetype=" + id + "&category="
			+ category + "&vendorid=" + vendorid + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getVendorSubPoproductRequest;
	req.open("GET", url, true);

	req.send(null);
}

function getVendorSubPoproductRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("proddiv").innerHTML = req.responseText;
			$("#product_id").trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});
			$("#dashboardloaderPopup").modal("hide");
		}
	}
}

var fromconfirmpopup = '0';
function deletePO(r, id) {
	if (confirm("If you delete this product then this product will delete permanataly from purchase order. Are you sure, you want to delete this product?")) {
		var i = r.parentNode.parentNode.rowIndex;
		document.getElementById("prodTable").deleteRow(i);
		fromconfirmpopup = '1';
		deletefromdb(id);
	}
	// setAllConfirmData();
}

function deletefromdb(id) {

	var url = "deletepoajaxProcurement?id=" + id + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = deletefromdbRequest;
	req.open("GET", url, true);
	req.send(null);
}

function deletefromdbRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			// calTotalAmt();
			var data = req.responseText;
			if(data==1){
				setAllConfirmData();
			}else{
				jAlert('error', "You can't delete last product of purchase order!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				openConfirmPopup(procureid);
			}
			
		}
	}
}

var tmpid = 0;
function reqnewGrn() {
	var flag = false;
	var warehouse = document.getElementById("warehouse").value;
	if (warehouse == '0') {
		jAlert('error', "Please select warehouse !", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		flag = true;
	}

	/*
	 * var categoryid= document.getElementById("category_id").value; var
	 * flag=false; if(categoryid=='0'){ jAlert('error', "Please select category
	 * !", 'Error Dialog'); setTimeout(function() {
	 * $("#popup_container").remove(); removeAlertCss(); }, alertmsgduration);
	 * flag=true; } var subcategory=
	 * document.getElementById("subcategory_id").value;
	 * 
	 * if(subcategory=="0"){ jAlert('error', "Please select sub category !",
	 * 'Error Dialog'); setTimeout(function() { $("#popup_container").remove();
	 * removeAlertCss(); }, alertmsgduration); flag=true;
	 *  }
	 */
	var product_id = document.getElementById("product_id").value;
	var discount = document.getElementById("discount").value;
	var rate = document.getElementById("rate").value;
	if (product_id == "0") {
		jAlert('error', "Please select product !", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		flag = true;
	} else if (rate == '' || rate == '0') {
		jAlert('error', "Please enter rate !", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		flag = true;
	} else if (Number(rate) < 0) {
		jAlert('error', "Please enter non negative rate !", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		flag = true;
	}
	if (discount != '0') {
		if (Number(discount) < 0 || Number(discount) > 100) {
			jAlert('error', "Please enter non negative discount !",
					'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			flag = true;
		}
	}

	if (!flag) {
		var qty = document.getElementById("qty").value;
		if (qty == "0" || qty == '') {
			jAlert('error', "Please enter qty !", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			flag = true;
		} else if (Number(qty) < 0) {
			jAlert('error', "Please enter non negative qty !", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			flag = true;
		} else if (!checkNumberOrNotofprocurement(qty)) {
			jAlert('error', "Please enter valid qty !", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			flag = true;
		} else {
			if (confirm("Are you sure!")) {
				$("#dashboardloaderPopup").modal("show");
				var table = document.getElementById("prodTable");
				var rowCount = table.rows.length;
				row = table.insertRow(rowCount);
				var vendorid = document.getElementById("vendorid").value;
				var proc_id = document.getElementById("proc_id").value;
				var url = "addnewgrnproductProcurement?product_id="
						+ product_id + "&qty=" + qty + "&vendorid=" + vendorid
						+ "&procurementid=" + proc_id + "&count=" + rowCount
						+ "&warehouse=" + warehouse + "&discount=" + discount
						+ "&rate=" + rate + "";
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				} else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}

				req.onreadystatechange = reqnewGrnRequest;
				req.open("GET", url, true);

				req.send(null);
			}
		}
	}

}
function reqnewGrnRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var ff = req.responseText;
			$("#dashboardloaderPopup").modal("hide");
			if (ff != '0') {
				row.innerHTML = req.responseText;
				setAllConfirmData();
			} else {

				jAlert('error', "This product is exists!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);

			}

		}
	}
}

function setdiscRateVendor(id) {

	var vendorid = document.getElementById("vendorid").value;
	var url = "setproddiscvendorProcurement?id=" + id + "&vendorid=" + vendorid
			+ "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setdiscRateVendorRequest;
	req.open("GET", url, true);

	req.send(null);
}
function setdiscRateVendorRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			var data = str.split("~");
			document.getElementById("rate").value = data[0];
			document.getElementById("discount").value = data[1];
		}
	}
}

function setAll(l) {

	if (l.checked == true) {

		$('.case').each(function() {

			this.checked = true;

		});

	} else {
		$('.case').each(function() {

			this.checked = false;

		});

	}

	if (l.checked == true) {
		var count = document.getElementById("totalcount").value;
		var tot = 0;
		for (var i = 0; i < count; i++) {
			document.getElementById("check" + i).value = "1";
			var bal = Number(document.getElementById("balance" + i).value);
			document.getElementById("paymentAmount" + i).value = bal;
			tot = tot + bal;
		}
		var returnedamt = Number(document.getElementById("sumofreturn").value);
		var nowremain = tot - returnedamt;
		document.getElementById("alltotal").value = roundTwo(nowremain);
		// document.getElementById("alltotal").value=tot;
	} else {
		var count = document.getElementById("totalcount").value;
		for (var i = 0; i < count; i++) {
			document.getElementById("paymentAmount" + i).value = 0;
			document.getElementById("check" + i).value = "0";
		}

		document.getElementById("alltotal").value = 0;

	}

}

function setTempAll() {

	var count = document.getElementById("totalcount").value;
	var tot = 0;
	for (var i = 0; i < count; i++) {

		var d = document.getElementById("check" + i);
		if (d.checked == true) {
			document.getElementById("check" + i).value = "1";
			var bal = Number(document.getElementById("balance" + i).value);
			var payamt = document.getElementById("paymentAmount" + i).value;
			if (payamt == '') {

			} else if (payamt == 0) {

			} else {
				bal = Number(payamt);
			}
			document.getElementById("paymentAmount" + i).value = bal;
			tot = tot + bal;
		} else {
			document.getElementById("paymentAmount" + i).value = 0;
			document.getElementById("check" + i).value = "0";
		}

	}

	// document.getElementById("alltotal").value=tot;

	var returnedamt = Number(document.getElementById("sumofreturn").value);
	var refundcheck = document.getElementById("refundcheck");
	var nowremain = 0;
	if (refundcheck.checked == true) {
		nowremain = tot - returnedamt;
	} else {
		nowremain = tot;
	}

	document.getElementById("alltotal").value = roundTwo(nowremain);

}

function getsubcat(id) {
	var url = "getsubcatProcurement?id=" + id + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getsubcatRequest;
	req.open("GET", url, true);

	req.send(null);
}
function getsubcatRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("subcatdiv").innerHTML = req.responseText;
			$("#subcategory_id0").trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});
		}
	}
}

function addNewItem() {
	var category_id = document.getElementById("category_id0").value;
	var subcategory_id = document.getElementById("subcategory_id0").value;
	var prodtype = document.getElementById("prodtype0").value;
	var product_name = document.getElementById("product_name0").value;
	var pack = document.getElementById("pack0").value;
	var mrp = document.getElementById("mrp0").value;
	var purchase_price = document.getElementById("purchase_price0").value;
	var sale_price = document.getElementById("sale_price0").value;
	var mfg = document.getElementById("mfg0").value;
	var vat = document.getElementById("vat0").value;
	var description = document.getElementById("description0").value;

	var url = "addnewitemProcurement?category_id=" + category_id
			+ "&subcategory_id=" + subcategory_id + "&prodtype=" + prodtype
			+ "&product_name=" + product_name + "&pack=" + pack + "&mrp=" + mrp
			+ "&purchase_price=" + purchase_price + "&sale_price=" + sale_price
			+ "&mfg=" + mfg + "&vat=" + vat + "&description=" + description
			+ "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = addNewItemRequest;
	req.open("GET", url, true);
	req.send(null);
}

function addNewItemRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			$("#addproduct").modal("hide");
		}
	}
}

/*
 * function setVendorProductForPo(id){
 * 
 * var warehouse = document.getElementById("warehouse").value;
 * if(warehouse=='0'){ jAlert('error', "Please Select Warehouse!", 'Error
 * Dialog'); setTimeout(function() { $("#popup_container").remove();
 * removeAlertCss(); }, alertmsgduration); }else{
 * $("#baselayout1loaderPopup").modal("show");
 * 
 * var url="setvendorpoproductProcurement?id="+id+"&warehouse="+warehouse+""; if
 * (window.XMLHttpRequest) { req = new XMLHttpRequest(); } else if
 * (window.ActiveXObject) { isIE = true; req = new
 * ActiveXObject("Microsoft.XMLHTTP"); } req.onreadystatechange =
 * setVendorProductForPoRequest; req.open("GET", url, true); req.send(null); } }
 * 
 * function setVendorProductForPoRequest(){ if (req.readyState == 4) { if
 * (req.status == 200) { var str=req.responseText; var data= str.split("~");
 * document.getElementById("proddiv").innerHTML=data[0];
 * $("#product_id"+tindex).trigger("chosen:updated");
 * $(".chosen").chosen({allow_single_deselect: true});
 * $("#baselayout1loaderPopup").modal("hide"); vendorstateid =data[1]; } } }
 */

function setvendorPoProduct(id) {

	var url = "setvendorprodProcurement?id=" + id + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = setvendorPoProductRequest;
	req.open("GET", url, true);
	req.send(null);
}
function setvendorPoProductRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var str = req.responseText;
			var data = str.split("~");
			document.getElementById("categoryid").value = str[0];
			document.getElementById("subcategoryid").value = str[1];
			document.getElementById("rate").value = str[2];
		}
	}
}

function setAllConfirmData() {

	var total = 0;
	var netamount = 0;
	var gsttotal = 0;
	$('.myclassJ')
			.each(
					function() {

						var i = this.value;
						var amt = document.getElementById('qty' + i).value;
						var rate = document.getElementById('rate' + i).value;
						var disc = 0;
						var temp = 0;
                        //for vspm -validation code commented
						//if (amt == '' || amt == '0') {
							//document.getElementById('qty' + i).value = 0;
							//jAlert('error', "Please enter quantity!",
									//'Error Dialog');
							//setTimeout(function() {
								//$("#popup_container").remove();
								//removeAlertCss();
							//}, alertmsgduration);
						//} else if (Number(amt) < 0) {
						//	document.getElementById('qty' + i).value = 0;
							//jAlert('error',
								//	"Please enter non negative quantity!",
								//	'Error Dialog');
							//setTimeout(function() {
								//$("#popup_container").remove();
								//removeAlertCss();
							//}, alertmsgduration);
						//} //else if (!checkNumberOrNotofprocurement(amt)) {
							//jAlert('error', "Please enter valid qty !",
							//		'Error Dialog');
							//setTimeout(function() {
								//$("#popup_container").remove();
								//removeAlertCss();
							//}, alertmsgduration);
						//} 
						//else if (rate == '' || rate == '0') {
							//document.getElementById('rate' + i).value = 0;
							//jAlert('error', "Please enter rate!",
							//		'Error Dialog');
							//setTimeout(function() {
							//	$("#popup_container").remove();
							//	removeAlertCss();
						//	}, alertmsgduration);
						//} else if (Number(rate) < 0) {
						//	document.getElementById('rate' + i).value = 0;
							//jAlert('error', "Please enter non negative rate!",
							//		'Error Dialog');
							//setTimeout(function() {
							//	$("#popup_container").remove();
							//	removeAlertCss();
							//}, alertmsgduration);
						//}

						var tot = roundTwo(amt * rate);
						total = total + tot;
						tot = roundTwo(tot);
						document.getElementById('total' + i).value = tot;
						var vat = Number(document.getElementById("approve_gst"+ i).value);
						var tempvatamount = parseFloat(rate) * parseFloat(vat)/ 100;
						tempvatamount = tempvatamount * amt;
						
						var xtotal = roundTwo(tempvatamount + tot);
						if (document.getElementById('discounts' + i)) {
							disc = document.getElementById('discounts' + i).value;
							temp = xtotal * disc / 100;
						}
						xtotal = roundTwo(xtotal - temp);
						document.getElementById("nettotal" + i).value = xtotal;
						netamount = xtotal + netamount;

						var tempgst = tempvatamount * disc /100;
						tempvatamount = tempvatamount - tempgst;
						
						gsttotal = gsttotal + tempvatamount;
						tempvatamount = roundTwo(tempvatamount);
						document.getElementById("netgsttotal" + i).value = roundTwo(tempvatamount);
						
					});
	if (fromconfirmpopup == '0') {
		var po_discType = document.getElementById("po_discType").value;
		if(po_discType==0){
			document.getElementById('totalAll').innerHTML = roundTwo(total);
			document.getElementById('netgsttotalAll').innerHTML = roundTwo(gsttotal);
			document.getElementById('nettotalAll').innerHTML = roundAbs(netamount);
		}else{
			var temptotal = total;
			document.getElementById('totalAll').innerHTML = roundTwo(total);
			var po_disc = document.getElementById("po_disc").value;
			var discratenew = (po_disc / total) * 100.0;
			var tempgst = gsttotal * discratenew /100;
			gsttotal = gsttotal - tempgst;
			document.getElementById('netgsttotalAll').innerHTML = roundTwo(gsttotal);
			netamount = temptotal - po_disc;
			netamount = netamount + gsttotal; 
			document.getElementById('nettotalAll').innerHTML = roundAbs(netamount);
		}
	}else{
		document.getElementById('totalAll').innerHTML = roundTwo(total);
		document.getElementById('netgsttotalAll').innerHTML = roundTwo(gsttotal);
		document.getElementById('nettotalAll').innerHTML = roundTwo(netamount);
	}
	if (fromconfirmpopup == '1') {
		calTotalAmt();
	}

}

var obj;
function chkNameExist(d) {
	obj = d;
	var url = "catnameexistCatalogue?name=" + d.value + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = chkNameExistRequest;
	req.open("GET", url, true);
	req.send(null);

}
function chkNameExistRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var res = req.responseText;
			if (res == "1") {

				obj.value = '';
				jAlert('error', "Product Name Exist!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}

		}
	}
}

/*
 * var isfromrequest='0'; function savenewCatalogue(val){
 * 
 * isfromrequest =val; var medicinename=
 * document.getElementById("prodname").value; var genname=
 * document.getElementById("genericname").value; var vendorid=
 * document.getElementById("vendorid").value; var pack=
 * document.getElementById("pack").value; var subcategory=
 * document.getElementById("subcategory").value; var productcategoryid =
 * document.getElementById("productcategoryid").value; var addgst =
 * document.getElementById("addgst").value; var pro_code ="";
 * if(document.getElementById("pro_code")){ pro_code =
 * document.getElementById("pro_code").value; } if(productcategoryid=='0'){
 * jAlert('error', "Please select product category!", 'Error Dialog');
 * setTimeout(function() { $("#popup_container").remove(); removeAlertCss(); },
 * alertmsgduration); }else if(subcategory=='0' || subcategory==''){
 * 
 * jAlert('error', "Please select product type!", 'Error Dialog');
 * setTimeout(function() { $("#popup_container").remove(); removeAlertCss(); },
 * alertmsgduration); } else if(medicinename==''){
 * 
 * jAlert('error', "Please enter product name!", 'Error Dialog');
 * setTimeout(function() { $("#popup_container").remove(); removeAlertCss(); },
 * alertmsgduration);
 *  } else if(genname==''){
 * 
 * jAlert('error', "Please enter genericname!", 'Error Dialog');
 * setTimeout(function() { $("#popup_container").remove(); removeAlertCss(); },
 * alertmsgduration); } else if(vendorid=='' || vendorid=='0'){
 * 
 * jAlert('error', "Please select supplier!", 'Error Dialog');
 * setTimeout(function() { $("#popup_container").remove(); removeAlertCss(); },
 * alertmsgduration); } else if(pack=='' || pack=='0'){
 * 
 * jAlert('error', "Please enter pack!", 'Error Dialog'); setTimeout(function() {
 * $("#popup_container").remove(); removeAlertCss(); }, alertmsgduration); }
 * else if(addgst==''){
 * 
 * jAlert('error', "Please select GST!", 'Error Dialog'); setTimeout(function() {
 * $("#popup_container").remove(); removeAlertCss(); }, alertmsgduration); }
 * else {
 * 
 * $("#baselayout1loaderPopup").modal("show"); var
 * url="savecatalogueProcurement?prodname="+medicinename+"&genericname="+genname+"&vendorid="+vendorid+"&pack="+pack+"&subcategory="+subcategory+"&productcategoryid="+productcategoryid+"&addgst="+addgst+"&pro_code="+pro_code+"";
 * if (window.XMLHttpRequest) { req = new XMLHttpRequest(); } else if
 * (window.ActiveXObject) { isIE = true; req = new
 * ActiveXObject("Microsoft.XMLHTTP"); }
 * 
 * req.onreadystatechange = savenewCatalogueRequest; req.open("GET", url, true);
 * req.send(null); }
 *  }
 * 
 * function savenewCatalogueRequest(){ if (req.readyState == 4) { if (req.status ==
 * 200) { if(isfromrequest=='0'){ var str=req.responseText; var data=
 * str.split("~"); document.getElementById("proddiv").innerHTML=data[0];
 * $("#product_id").trigger("chosen:updated");
 * $(".chosen").chosen({allow_single_deselect: true});
 * $("#baselayout1loaderPopup").modal("hide");
 * $("#addnewproduct").modal("hide"); vendorstateid =data[1]; }else{ var
 * warehouse =document.getElementById("warehouse").value;
 * $("#baselayout1loaderPopup").modal("hide");
 * $("#addnewproduct").modal("hide"); setProductofStoreInPO(warehouse);
 *  }
 *  } } }
 */

function checkExpiryInfo(v) {

	var datestr = v.split("/");
	var month = Number(datestr[0]);
	var expiryDateAlert = document.getElementById("expiryDateAlert").value;
	var date1 = new Date(); // current date
	var date2 = new Date(datestr[1], --month, 28); // //Remember, months are 0
													// based in JS
	var year1 = date1.getFullYear();
	var year2 = date2.getFullYear();
	var month1 = date1.getMonth();
	var month2 = date2.getMonth();
	if (month1 === 0) { // Have to take into account
		month1++;
		month2++;
	}
	var numberOfMonths;
	numberOfMonths = (year2 - year1) * 12 + (month2 - month1) + 1;

	if (expiryDateAlert > 0) {

		if (numberOfMonths <= 0) {

			jAlert('error', "Product is Expired!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);

		} else if (numberOfMonths < expiryDateAlert) {

			// alert
			jAlert('error', "Product expiry Alert in " + numberOfMonths
					+ " Months !", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	}

}

/* json fuction to store temp terms n cond */
function savetoprocure(procureid,val) {
	var procureid1 = procureid;
	var terms = document.getElementById("termsncond").value;
	terms = terms.replace("\t\t\t\t\t\t\t\t", "");
	var dataObj = {
		"procureid" : "" + procureid1 + "",
		"terms" : "" + terms + "",

	};
	var data1 = JSON.stringify(dataObj);
	$.ajax({

		url : "savetermsandconditionProcurement",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,

		success : function(data) {
			var terms1 = data.terms;
			terms1.replace("\n", "<br>");
			document.getElementById("termsncond").value = terms;
			document.getElementById("termsshowprint").innerHTML = terms1;
			if(val==1){
				savepaymenttoprocure(document.getElementById("procurementidterm").value);
			}
		},
		error : function(result) {
			console.log("error in saving diagnosis");
		}
	});

}

// 09 July 2018

function changeDMTextbox(val) {
	if (val == true) {
		document.getElementById("voucherno").readOnly = true;
		// document.getElementById("voucherdate").readOnly = true;
		document.getElementById("security_no").readOnly = true;
		// document.getElementById("security_date").readOnly = true;
		document.getElementById("delivermemoid").readOnly = false;
		// document.getElementById("delivermemodate").readOnly = false;
	} else {
		document.getElementById("voucherno").readOnly = false;
		// document.getElementById("voucherdate").readOnly = false;
		document.getElementById("security_no").readOnly = false;
		// document.getElementById("security_date").readOnly = false;
		document.getElementById("delivermemoid").readOnly = true;
		// document.getElementById("delivermemodate").readOnly = true;
	}
}

function setVendorProductForDM(id) {
	$("#baselayout1loaderPopup").modal("show");

	var url = "setvendorfordmProcurement?id=" + id + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = setVendorProductForDMRequest;
	req.open("GET", url, true);
	req.send(null);
	/* } */
}

function setVendorProductForDMRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			document.getElementById("dmtbody").innerHTML = str;
			$("#baselayout1loaderPopup").modal("hide");

		}
	}
}

// lokesh
function openCancelPopupNew(id) {

	document.getElementById("proc_id").value = id;

	var url = "getconfirmprodProcurement?id=" + id + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = openCancelPopupNewRequest;
	req.open("GET", url, true);

	req.send(null);

}
function openCancelPopupNewRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			var data = req.responseText;
			var str = data.split("~");
			document.getElementById("innerData").innerHTML = str[0];
			document.getElementById("vendorname").innerHTML = str[1];
			document.getElementById("vendorid").value = str[2];
			document.getElementById("pono").innerHTML = str[3];
			document.getElementById("podate").innerHTML = str[4];
			// document.getElementById("podate").innerHTML=str[5];
			document.getElementById("tfootconfirmpo").innerHTML = str[6];
			document.getElementById("newpopup").className = "hidden";
			document.getElementById("addpopnew").className = "hidden";

			$("#confirmprod").modal("show");
		}
	}
}

function cancelPO(procurementid) {
	if (!procurementid == '' || !procurementid == '0') {
		procureid = procurementid;
	} else {

	}
	var x = confirm("Are you sure want to cancel!!");
	if (x) {
		var url = "cancelPOProcurement?procureid=" + procureid + "";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.onreadystatechange = cancelPORequest;
		req.open("GET", url, true);
		req.send(null);
	}

}
function cancelPORequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var x = req.responseText;
			if (x == "yes") {
				window.location.reload();
			} else {
				alert("can't cancel");
			}
		}
	}
}

function getvendorstateforlongpo() {

	var procurementid = document.getElementById("procurementid").value;
	var url = "getvendorstateforlongpoBookAppointmentAjax?procurementid="
			+ procurementid + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getvendorstateforlongpoRequest;
	req.open("GET", url, true);

	req.send(null);
}
function getvendorstateforlongpoRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			vendorstateid = req.responseText;
			if (isfromlongpo == '1') {
				calTotalAmt();
			}
			if (isfromeditponew == 1) {
				setState(0);
			}
		}
	}
}

function converttowordpo() {
	var x = document.getElementById("totalnetamount").value;
	var words = toWords(x);
	document.getElementById("inword").innerHTML = words;
}
var th = [ '', 'Thousand', 'Million', 'Billion', 'Trillion' ];
var dg = [ 'Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven',
		'Eight', 'Nine' ];
var tn = [ 'Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen',
		'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen' ];
var tw = [ 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty',
		'Ninety' ];

function toWords(s) {
	s = s.toString();
	s = s.replace(/[\, ]/g, '');
	if (s != parseFloat(s))
		return '';
	var x = s.indexOf('.');
	if (x == -1)
		x = s.length;
	if (x > 15)
		return 'too big';
	var n = s.split('');
	var str = '';
	var sk = 0;
	for (var i = 0; i < x; i++) {
		if ((x - i) % 3 == 2) {
			if (n[i] == '1') {
				str += tn[Number(n[i + 1])] + ' ';
				i++;
				sk = 1;
			} else if (n[i] != 0) {
				str += tw[n[i] - 2] + ' ';
				sk = 1;
			}
		} else if (n[i] != 0) { // 0235
			str += dg[n[i]] + ' ';
			if ((x - i) % 3 == 0)
				str += 'Hundred ';
			sk = 1;
		}
		if ((x - i) % 3 == 1) {
			if (sk)
				str += th[(x - i - 1) / 3] + ' ';
			sk = 0;
		}
	}

	/*
	 * if (x != s.length) { var y = s.length; str += 'point '; for (var i=x+1; i<y;
	 * i++) str += dg[n[i]] +' '; }
	 */
	return str.replace(/\s+/g, ' ');
}

function printDMgrn(procid) {
	openPopup('grnprintProcurement?id=' + procid + '');
}

function setVendorProductForPo(id) {
	var warehouse = document.getElementById("warehouse").value;
	if (warehouse == '0') {
		jAlert('error', "Please Select Warehouse!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		$("#baselayout1loaderPopup").modal("show");
		var dataObj = {
			"id" : "" + id + "",
			"warehouse" : "" + warehouse + "",
		};
		var data1 = JSON.stringify(dataObj);
		$
				.ajax({

					url : "setvendorpoproduct1Procurement",
					data : data1,
					dataType : 'json',
					contentType : 'application/json',
					type : 'POST',
					async : true,

					success : function(data) {
						document.getElementById("proddiv").innerHTML = data.productlist;
						$("#product_id" + tindex).trigger("chosen:updated");
						$(".chosen").chosen({
							allow_single_deselect : true
						});
						$("#baselayout1loaderPopup").modal("hide");
						vendorstateid = data.stateid;
					},
					error : function(result) {
						console.log("Something goes wrong");
					}
				});
	}
}

var isfromrequest = '0';
function savenewCatalogue(id) {
	isfromrequest = id;
	var medicinename = document.getElementById("prodname").value;
	var genname = document.getElementById("genericname").value;
	var vendorid = document.getElementById("vendorid").value;
	var pack = document.getElementById("pack").value;
	
	if(document.getElementById("packHidden")){
		pack ="1";
	}
	var subcategory = document.getElementById("subcategory").value;
	var productcategoryid = document.getElementById("productcategoryid").value;
	var addgst = document.getElementById("addgst").value;
	var warehouse = document.getElementById("warehouse").value;
	var medicine_shedule = document.getElementById("medicine_shedule").value;
	var uom = document.getElementById("uom").value;
	var pro_code = "";
	if (document.getElementById("pro_code")) {
		pro_code = document.getElementById("pro_code").value;
	}
	if (productcategoryid == '0') {
		jAlert('error', "Please select product category!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (subcategory == '0' || subcategory == '') {

		jAlert('error', "Please select sub category!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (medicinename == '') {

		jAlert('error', "Please enter product name!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} else if (genname == '') {

		jAlert('error', "Please enter genericname!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	/*
	 * else if(vendorid=='' || vendorid=='0'){
	 * 
	 * jAlert('error', "Please select supplier!", 'Error Dialog');
	 * setTimeout(function() { $("#popup_container").remove(); removeAlertCss(); },
	 * alertmsgduration); }
	 */
	else if (warehouse == '' || warehouse == '0') {

		jAlert('error', "Please select warehouse!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (pack == '' || pack == '0') {

		jAlert('error', "Please enter pack!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (!checkNumberOrNotofprocurement(pack)) {

		jAlert('error', "Please enter valid pack!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (addgst == '') {

		jAlert('error', "Please select GST!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		if (isfromrequest != '2') {
			$("#baselayout1loaderPopup").modal("show");
		}
		
		var dataObj = {
			"prodname" : "" + medicinename + "",
			"genericname" : "" + genname + "",
			"vendorid" : "" + vendorid + "",
			"pack" : "" + pack + "",
			"subcategory" : "" + subcategory + "",
			"productcategoryid" : "" + productcategoryid + "",
			"addgst" : "" + addgst + "",
			"pro_code" : "" + pro_code + "",
			"genericname" : "" + genname + "",
			"warehouse" : "" + warehouse + "",
			"medicine_shedule" : "" + medicine_shedule + "",
			"uom" : ""+uom+"",
		};
		var data1 = JSON.stringify(dataObj);
		$
				.ajax({

					url : "savecatalogue1Procurement",
					data : data1,
					dataType : 'json',
					contentType : 'application/json',
					type : 'POST',
					async : true,

					success : function(data) {
						if (isfromrequest == '0') {
							document.getElementById("proddiv").innerHTML = data.productlist;
							$("#product_id").trigger("chosen:updated");
							$(".chosen").chosen({
								allow_single_deselect : true
							});
							$("#baselayout1loaderPopup").modal("hide");
							$("#addnewproduct").modal("hide");
							vendorstateid = data.stateid;
						} else if(isfromrequest=='1') {
							var warehouse = document
									.getElementById("warehouse").value;
							$("#baselayout1loaderPopup").modal("hide");
							$("#addnewproduct").modal("hide");
							setProductofStoreInPO(warehouse);
						}else{
							var warehouse = document
							.getElementById("warehouse").value;
							//$("#baselayout1loaderPopup").modal("hide");
							$("#addnewproduct").modal("hide");
							//$("#confirmprod").modal("hide");
							setProductofStoreInPO(warehouse);
							//openConfirmPopup(document.getElementById("proc_id").value);
						}

					},
					error : function(result) {
						console.log("Something goes wrong");
					}
				});
	}
}

function selectAllDMCheckBox(val) {

	if (val.checked == true) {
		$('.case').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
			// "checkbox1"
		});
	} else {
		$('.case').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
			// "checkbox1"
		});
	}
	calculatedmdata();
}

function calculatedmdata() {
	var cgst = 0.0;
	var sgst = 0.0;
	var igst = 0.0;
	var total = 0.0;
	var discount = 0.0;
	var netAmt = 0.0;
	$('.case')
			.each(
					function() {
						if (this.checked == true) {
							var i = this.value;
							var cgst0 = parseFloat(document
									.getElementById("cgst" + i).value);
							var sgst0 = parseFloat(document
									.getElementById("sgst" + i).value);
							var igst0 = parseFloat(document
									.getElementById("igst" + i).value);
							var total0 = parseFloat(document
									.getElementById("total" + i).value);
							var discount0 = parseFloat(document
									.getElementById("discount" + i).value);
							var netAmt0 = parseFloat(document
									.getElementById("netAmt" + i).value);
							cgst = cgst + cgst0;
							sgst = sgst + sgst0;
							igst = igst + igst0;
							total = total + total0;
							discount = discount + discount0;
							netAmt = netAmt + netAmt0;
						}
					});

	document.getElementById("givencgst").innerHTML = "Rs." + cgst;
	document.getElementById("givensgst").innerHTML = "Rs." + sgst;
	document.getElementById("givenigst").innerHTML = "Rs." + igst;
	document.getElementById("givensubtotal").innerHTML = "Rs." + total;
	document.getElementById("givendiscount").innerHTML = "Rs." + discount;
	document.getElementById("givennetamount").innerHTML = "Rs." + netAmt;
}

function calculateWithPoDisc() {
	var discount = Number(document.getElementById("discount").value);
	var qty = Number(document.getElementById("qty").value);
	var rate = Number(document.getElementById("rate").value);
	var temp = 0;
	var total = rate * qty;
	if (discount > 100) {
		document.getElementById("discount").value = 0;
	}
	temp = total * discount / 100;
	total = total - temp;
	document.getElementById("xtotal").value = total;
}

function saveCOnfiirm() {
	var flagcheck = false;
	$('.myclassJ')
			.each(
					function() {
						var i = this.value;
						var amt = document.getElementById('qty' + i).value;
						var rate = document.getElementById('rate' + i).value;
						var discount = document.getElementById('discounts' + i).value;
						var temp = 0;
						if (amt == '' || amt == '0') {
							flagcheck = true;
							jAlert('error', "Please enter quantity!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						} else if (Number(amt) < 0) {
							flagcheck = true;
							jAlert('error',
									"Please enter non negative quantity!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						} else if (!checkNumberOrNotofprocurement(amt)) {
							jAlert('error', "Please enter valid qty !",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
							flagcheck = true;
						} else if (rate == '' || rate == '0') {
							flagcheck = true;
							jAlert('error', "Please enter rate!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						} else if (Number(rate) < 0) {
							flagcheck = true;
							jAlert('error', "Please enter non negative rate!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						} else if (Number(discount) != 0) {
							if (Number(discount) < 0) {
								flagcheck = true;
								jAlert('error',
										"Please enter non negative discount !",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
							} else if (Number(discount) > 100) {
								flagcheck = true;
								jAlert(
										'error',
										"Please enter discount less than 100%!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
							}
						}
					});
	if (!flagcheck) {
		var r = confirm("Are you sure you want to save?");
		if (r == true) {
			document.getElementById("saveconfirmpo").value = "1";
			$("#dashboardloaderPopup").modal("show");
			document.getElementById("procform").submit();
		} else {
			document.getElementById("saveconfirmpo").value = "0";
		}
	}

}

function deletePONew(r, id) {
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("prodTable").deleteRow(i);
	var x = document.getElementById("deleteids").value;
	x = x + ',' + id;
	document.getElementById("deleteids").value = x;
	calTotalAmt();
	/*
	 * fromconfirmpopup='1'; deletefromdb(id);
	 */
	// setAllConfirmData();
}

function openAgreementConfirmPopup(id) {
	var dataObj = {
		"id" : "" + id + "",
	};
	var data1 = JSON.stringify(dataObj);
	$
			.ajax({

				url : "agreementconfirmpopupProcurement",
				data : data1,
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,

				success : function(data) {
					document.getElementById("innerData").innerHTML = data.productlist;
					document.getElementById("vendorname").innerHTML = data.vendorname;
					document.getElementById("vendorid").value = data.vendorid;
					document.getElementById("pono").innerHTML = data.agreementidid;
					document.getElementById("podate").innerHTML = data.datetime;
					document.getElementById("tfootconfirmpo").innerHTML = data.tfootproductlist;
					document.getElementById("proc_id").value = data.agreementidid;
					$("#confirmprod").modal("show");
				},
				error : function(result) {
					console.log("Something goes wrong");
				}
			});
}

var tmpid = 0;
function reqnewAgreement() {
	var flag = false;
	var warehouse = document.getElementById("warehouse").value;
	if (warehouse == '0') {
		jAlert('error', "Please select warehouse !", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		flag = true;
	}
	var product_id = document.getElementById("product_id").value;
	var discount = document.getElementById("discount").value;
	if (product_id == "0") {
		jAlert('error', "Please select product !", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		flag = true;
	}

	if (!flag) {
		var qty = document.getElementById("qty").value;
		var rate = document.getElementById("rate").value;
		if (qty == "0" || qty == '') {
			jAlert('error', "Please enter qty !", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			flag = true;
		} else {
			var table = document.getElementById("prodTable");
			var rowCount = table.rows.length;
			row = table.insertRow(rowCount);
			var vendorid = document.getElementById("vendorid").value;
			var proc_id = document.getElementById("proc_id").value;
			var url = "addnewagreementproductProcurement?product_id="
					+ product_id + "&qty=" + qty + "&vendorid=" + vendorid
					+ "&procurementid=" + proc_id + "&count=" + rowCount
					+ "&warehouse=" + warehouse + "&discount=" + discount
					+ "&rate=" + rate + "";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.onreadystatechange = reqnewAgreementRequest;
			req.open("GET", url, true);
			req.send(null);
		}
	}
}
function reqnewAgreementRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var ff = req.responseText;
			if (ff != '0') {
				row.innerHTML = req.responseText;
				setAllAgreConfirmData();
			} else {

				jAlert('error', "This product is exists!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);

			}
		}
	}
}

function deleteAgreement(r, id) {
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("prodTable").deleteRow(i);
	deleteagreementfromdb(id);
	// setAllConfirmData();
}

function deleteagreementfromdb(id) {

	var url = "deleteagreementajaxProcurement?id=" + id + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = deleteagreementfromdbRequest;
	req.open("GET", url, true);
	req.send(null);
}

function deleteagreementfromdbRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			setAllAgreConfirmData();
		}
	}
}

function saveAgreementConfirm() {
	var r = confirm("Are you sure you want to save?");
	if (r == true) {
		document.getElementById("saveconfirmpo").value = "1";
		document.getElementById("procform").submit();
	} else {
		document.getElementById("saveconfirmpo").value = "0";
	}

}

function submitAgreementConfirm() {
	var r = confirm("Are you sure you want to confirm?");
	if (r == true) {
		document.getElementById("saveconfirmpo").value = "0";
		document.getElementById("procform").submit();
	}
}

function cancelAgreement() {
	var r = confirm("Are you sure you want to cancel?");
	if (r == true) {
		document.getElementById("cancelconfirmpo").value = "1";
		document.getElementById("procform").submit();
	}
}

function setAllAgreConfirmData() {

	var total = 0;
	var netamount = 0;
	var gsttotal = 0;
	$('.myclassJ')
			.each(
					function() {

						var i = this.value;
						var amt = document.getElementById('qty' + i).value;
						var rate = document.getElementById('rate' + i).value;
						var disc = 0;
						var temp = 0;

						var tot = roundTwo(amt * rate);
						total = total + tot;
						tot = roundTwo(tot);
						document.getElementById('total' + i).value = tot;

						var vat = Number(document.getElementById("approve_gst"
								+ i).value);

						var tempvatamount = parseFloat(rate) * parseFloat(vat)
								/ 100;
						tempvatamount = tempvatamount * amt;
						gsttotal = gsttotal + tempvatamount;
						tempvatamount = roundTwo(tempvatamount);
						document.getElementById("netgsttotal" + i).value = roundTwo(tempvatamount);
						var xtotal = roundTwo(tempvatamount + tot);
						if (document.getElementById('discounts' + i)) {
							disc = document.getElementById('discounts' + i).value;
							temp = xtotal * disc / 100;
						}
						xtotal = roundTwo(xtotal - temp);
						document.getElementById("nettotal" + i).value = xtotal;
						netamount = xtotal + netamount;

					});

	document.getElementById('totalAll').innerHTML = roundTwo(total);
	document.getElementById('netgsttotalAll').innerHTML = roundTwo(gsttotal);
	document.getElementById('nettotalAll').innerHTML = roundTwo(netamount);

}

function openAgreementOrderPopup(id) {
	var dataObj = {
		"id" : "" + id + "",
	};
	var data1 = JSON.stringify(dataObj);
	$
			.ajax({

				url : "agreementorderpopupProcurement",
				data : data1,
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,

				success : function(data) {
					document.getElementById("orderinnerDataagreement").innerHTML = data.productlist;
					document.getElementById("ordervendorname").innerHTML = data.vendorname;
					document.getElementById("ordervendorid").value = data.vendorid;
					document.getElementById("orderpono").innerHTML = data.agreementidid;
					document.getElementById("orderpodate").innerHTML = data.datetime;
					document.getElementById("ordertfootagreement").innerHTML = data.tfootproductlist;
					document.getElementById("orderproc_id").value = data.agreementidid;
					setAllAgreOrderData();
					$("#orderagreement").modal("show");
				},
				error : function(result) {
					console.log("Something goes wrong");
				}
			});
}

function deleteOrderAgreement(r, id) {
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("prodTable").deleteRow(i);
}

function setAllAgreOrderData() {

	var total = 0;
	var netamount = 0;
	var gsttotal = 0;
	$('.myclassJ')
			.each(
					function() {
						if (this.checked == true) {
							var i = this.value;
							var amt = document.getElementById('qty' + i).value;
							var rate = document.getElementById('rate' + i).value;
							var disc = 0;
							var temp = 0;

							var tot = roundTwo(amt * rate);
							total = total + tot;
							tot = roundTwo(tot);
							document.getElementById('total' + i).value = tot;

							var vat = Number(document
									.getElementById("approve_gst" + i).value);

							var tempvatamount = parseFloat(rate)
									* parseFloat(vat) / 100;
							tempvatamount = tempvatamount * amt;
							gsttotal = gsttotal + tempvatamount;
							tempvatamount = roundTwo(tempvatamount);
							document.getElementById("netgsttotal" + i).value = roundTwo(tempvatamount);
							var xtotal = roundTwo(tempvatamount + tot);
							if (document.getElementById('discounts' + i)) {
								disc = document.getElementById('discounts' + i).value;
								temp = xtotal * disc / 100;
							}
							xtotal = roundTwo(xtotal - temp);
							document.getElementById("nettotal" + i).value = xtotal;
							netamount = xtotal + netamount;
						}
					});
	document.getElementById('totalAll').innerHTML = roundTwo(total);
	document.getElementById('netgsttotalAll').innerHTML = roundTwo(gsttotal);
	document.getElementById('nettotalAll').innerHTML = roundTwo(netamount);
}

function submitAgreementOrder() {
	var flag = false;
	var flag2 = false;
	var ids = "0";
	$('.myclassJ')
			.each(
					function() {
						if (this.checked == true) {
							var i = this.value;
							var reqqty = Number(document.getElementById('qty'
									+ i).value);
							var orderedqty = Number(document
									.getElementById('orderedqty' + i).value);
							var agreementqty = Number(document
									.getElementById('agreementqty' + i).value);
							var totalqty = agreementqty - orderedqty;
							ids = ids + "," + i;
							if (reqqty > agreementqty) {
								jAlert(
										'error',
										"Requested qty greater than agreement qty!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								flag2 = true;
							} else if (reqqty > totalqty) {
								jAlert(
										'error',
										"Requested qty greater than agreement qty!",
										'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								flag2 = true;
							}
							flag = true;
						}
					});
	if (!flag) {
		jAlert('error', "Please select at least one product!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		if (!flag2) {
			var r = confirm("Are you sure you want to confirm?");
			if (r == true) {
				document.getElementById("orderchildids").value = ids;
				document.getElementById("orderform").submit();
			}
		}

	}
}

function deleteReturnProductReq(val) {
	document.getElementById("parent_id").value = val;
	$('#deletemodel').modal("show");
}

function deleteReturnProductReq1() {
	var parentid = document.getElementById("parent_id").value;
	var delete_reason = document.getElementById("delete_reason").value;
	if (delete_reason == '') {
		jAlert('error', "Please enter cancel reason!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		var url = "deletereturntosupplierProduct?parentid=" + parentid
				+ "&delete_reason=" + delete_reason + "";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.onreadystatechange = deleteReturnProductReq1Request;
		req.open("GET", url, true);
		req.send(null);
	}
}
function deleteReturnProductReq1Request() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
		}
	}
}

function calReturnSupplierSubTotal() {

	var total = 0;
	var totalnetamount = 0;
	var totalgrossvatamt = 0;
	var totaldiscountamt = 0;
	var totalnetammt = 0;
	$('.akashreturnclass')
			.each(
					function() {
						var i = this.value;
						var returnqty = document
								.getElementById("returnqty" + i).value;
						var returnfreeqty = document
								.getElementById("returnfreeqty" + i).value;
						var pack = Number(document.getElementById("pack" + i).value);
						var rate = parseFloat(document
								.getElementById("purchase_price" + i).value);
						var qty = Number(document.getElementById("qty" + i).value);
						var discper = parseFloat(document
								.getElementById("discper" + i).value);
						var vat = Number(document.getElementById("vat" + i).value);
						var remainfreereturnqty = Number(document
								.getElementById("remainfreereturnqty" + i).value);
						var unitprice = rate / pack;
						if (returnqty == '') {
							returnqty = '0';
							jAlert('error', "Please enter return qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}

						if (returnfreeqty == '') {
							returnfreeqty = '0';
							jAlert('error', "Please enter return free qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}
						var totalreturnqty = Number(returnqty)
								+ Number(returnfreeqty);

						if (totalreturnqty > qty) {
							jAlert(
									'error',
									"Entered return qty greater than req. qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
							totalreturnqty = 0;
						} else if (totalreturnqty < qty) {
							jAlert('error',
									"Entered return qty less than req. qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
							totalreturnqty = 0;
						}

						if (returnfreeqty > remainfreereturnqty) {
							returnfreeqty = '0';
							jAlert(
									'error',
									"Entered free qty greater than remaining free qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}

						var temptotal = parseFloat(unitprice)
								* parseInt(Number(returnqty));
						temptotal = roundTwo(temptotal);
						var temptot = 0;
						var tempvat = 0;
						tempvat = temptotal * discper / 100;
						temptot = temptotal - tempvat;

						var discountamt = (temptotal / 100) * discper;
						discountamt = roundTwo(discountamt);

						var tempvatamount = parseFloat(temptot)
								* parseFloat(vat) / 100;
						var netamount = temptotal + tempvatamount;
						netamount = roundTwo(netamount);
						tempvatamount = roundTwo(tempvatamount);

						total = total + temptotal;
						totalnetamount = totalnetamount + netamount;
						totalgrossvatamt = totalgrossvatamt + tempvatamount;
						totaldiscountamt = totaldiscountamt + discountamt;
						totalnetammt = totalnetammt + (netamount - discountamt);
						tempvat = roundTwo(tempvat);

						document.getElementById("returntotal" + i).innerHTML = temptotal;
						document.getElementById("returndiscount" + i).innerHTML = discountamt;
						document.getElementById("returngstamount" + i).innerHTML = tempvatamount;
						document.getElementById("returnnetammt" + i).innerHTML = roundTwo(netamount
								- discountamt);

						document.getElementById("discount" + i).value = discountamt;
						document.getElementById("gstamount" + i).value = tempvatamount;
						document.getElementById("newtotal" + i).value = temptotal;

					});

	document.getElementById("subTotal").value = roundTwo(total);
	document.getElementById("discount").value = roundTwo(totaldiscountamt);
	document.getElementById("netpay").value = roundAbs(totalnetammt);
	document.getElementById("alltotvatTotal").value = roundTwo(totalgrossvatamt);
	var cgst = 0;
	var sgst = 0;
	var igst = 0;
	var isfromsamereturnstate = document.getElementById("isfromsamestate").value;
	if (isfromsamereturnstate == '1') {
		cgst = totalgrossvatamt / 2.0;
		sgst = totalgrossvatamt / 2.0;
		igst = 0;
	} else {
		cgst = 0;
		sgst = 0;
		igst = totalgrossvatamt;
	}
	document.getElementById("cgst").value = roundTwo(cgst);
	document.getElementById("sgst").value = roundTwo(sgst);
	document.getElementById("igst").value = roundTwo(igst);

	var index = document.getElementById("vatcount").value;
	var disctotal = 0;
	var totalvat = 0;
	for (var i = 0; i < index; i++) {
		var vatrate = Number(document.getElementById("vatrate" + i).innerHTML);
		var txtotal = 0;
		var txtaxable = 0;
		var txtdiscount = 0;
		var txdiscountamt = 0;
		$('.akashreturnclass')
				.each(
						function() {
							var x = this.value;
							var vat = Number(document.getElementById("vat" + x).value);
							if (vatrate == vat) {
								var returnqty = document
										.getElementById("returnqty" + x).value;
								var rate = parseFloat(document
										.getElementById("purchase_price" + x).value);
								var discper = parseFloat(document
										.getElementById("discper" + x).value);
								var pack = Number(document
										.getElementById("pack" + x).value);
								var unitprice = rate / pack;

								var temptotal = parseFloat(unitprice)
										* parseInt(Number(returnqty));
								temptotal = roundTwo(temptotal);
								var temptot = 0;
								var tempvat = 0;
								tempvat = temptotal * discper / 100;
								temptot = temptotal - tempvat;

								var discountamt = (temptotal / 100) * discper;
								discountamt = roundTwo(discountamt);

								var tempvatamount = parseFloat(temptot)
										* parseFloat(vat) / 100;
								var netamount = temptotal + tempvatamount;
								netamount = roundTwo(netamount);
								tempvatamount = roundTwo(tempvatamount);

								txtotal = txtotal + temptotal;
								txtaxable = txtaxable + temptot;
								txtdiscount = txtdiscount + discountamt;
								txdiscountamt = txdiscountamt + tempvatamount;
							}
						});
		// totalVatAmt , totalVatAmtinput
		document.getElementById("totalVatAmt" + i).innerHTML = roundTwo(txtotal);
		document.getElementById("totalVatAmtinput" + i).value = roundTwo(txtotal);

		// vatgiven , vatgivenspan
		document.getElementById("vatgivenspan" + i).innerHTML = roundTwo(txtaxable);
		document.getElementById("vatgiven" + i).value = roundTwo(txtaxable);

		// discvat,discvatVal
		document.getElementById("discvat" + i).innerHTML = roundTwo(txtdiscount);
		document.getElementById("discvatVal" + i).value = roundTwo(txtdiscount);

		// taxAmt, taxAmtVal
		document.getElementById("taxAmt" + i).innerHTML = roundTwo(txdiscountamt);
		document.getElementById("taxAmtVal" + i).value = roundTwo(txdiscountamt);
	}

}
function returntosuppliervalidate() {
	var flag = false;
	$('.akashreturnclass')
			.each(
					function() {
						var i = this.value;
						var returnqty = document
								.getElementById("returnqty" + i).value;
						var returnfreeqty = document
								.getElementById("returnfreeqty" + i).value;
						var pack = Number(document.getElementById("pack" + i).value);
						var rate = parseFloat(document
								.getElementById("purchase_price" + i).value);
						var qty = Number(document.getElementById("qty" + i).value);
						var discper = parseFloat(document
								.getElementById("discper" + i).value);
						var vat = Number(document.getElementById("vat" + i).value);
						var remainfreereturnqty = Number(document
								.getElementById("remainfreereturnqty" + i).value);
						var unitprice = rate / pack;
						var isnumberreturnqty = checkNumberOrNotofprocurement(returnqty);
						var isnumberreturnfreeqty = checkNumberOrNotofprocurement(returnfreeqty);
						if (returnqty == '') {
							jAlert('error', "Please enter return qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
							flag = true;
						}
						if (returnfreeqty == '' && flag == false) {
							flag = true;
							jAlert('error', "Please enter return free qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}
						if (!isnumberreturnqty && flag == false) {
							flag = true;
							jAlert('error', "Please enter valid return qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}

						if (!isnumberreturnfreeqty && flag == false) {
							flag = true;
							jAlert('error', "Please enter valid free qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}
						var totalreturnqty = Number(returnqty)
								+ Number(returnfreeqty);

						if (totalreturnqty > qty && flag == false) {
							flag = true;
							jAlert(
									'error',
									"Entered return qty greater than req. qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);

						} else if (totalreturnqty < qty && flag == false) {
							flag = true;
							jAlert('error',
									"Entered return qty less than req. qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}

						if (returnfreeqty > remainfreereturnqty
								&& flag == false) {
							flag = true;
							jAlert(
									'error',
									"Entered free qty greater than remaining free qty!",
									'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}
					});

	if (flag) {

	} else {
		var x = confirm("Are You Sure!!");
		if (x) {
			$("#dashboardloaderPopup").modal('show');
			document.getElementById("formMyPo").submit();
		}

	}
}

function addProductForGrnWithPoOtherBatch(vendor_id, procurementid, location,
		grnno, catalogueid, parentpoid) {
	tindex = Number(document.getElementById("tempcount").value);
	var table = document.getElementById('prodTable');
	var rowCount = table.rows.length;
	row = table.insertRow(rowCount);
	var counts = tindex;
	var url = "addnewproductingrnwithpoProcurement?vendorid=" + vendor_id
			+ "&procurementid=" + procurementid + "&count=" + counts
			+ "&location=" + location + "&grnno=" + grnno + "&catalogueid="
			+ catalogueid + "&parentpoid=" + parentpoid + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = addProductForGrnWithPoOtherBatchRequest;
	req.open("GET", url, true);
	req.send(null);
}

function addProductForGrnWithPoOtherBatchRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			var data = str.split("~");
			row.innerHTML = data[0];
			$('#expiry_date' + tindex + '').MonthPicker({
				UseInputMask : true,
				ValidationErrorMessage : 'Invalid Date!'
			});
			tindex++;
			document.getElementById("tempcount").value = tindex;
			calTotalAmt();
		}
	}
}

function addToPOSelectedList() {
	$("#dashboardloaderPopup").modal('show');
	var ids = "0";
	$('.dcase').each(function() {
		if (this.checked == true) {
			var i = this.value;
			var temppoid = document.getElementById("catalogueidold" + i).value;
			ids = ids + "," + temppoid;
		}
	});

	var url = "addtoselectedpoProcurement?data=" + ids + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = addToPOSelectedListRequest;
	req.open("GET", url, true);
	req.send(null);

}

function addToPOSelectedListRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			// window.location.reload();
			$('.dcase').each(function() { // loop through each checkbox
				this.checked = false; // deselect all checkboxes with class
				// "checkbox1"
			});
			getGRNWithPOSelectedList();
		}
	}
}

function selectAllCheckBoxPO(val) {

	if (val.checked == true) {
		$('.dcase').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
			// "checkbox1"
		});
	} else {
		$('.dcase').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
			// "checkbox1"
		});
	}
}
function submitGrnWithPoSearch() {
	var searchtext = document.getElementById("searchtext").value;
	var supplierPoId= document.getElementById("supplierPoId").value;
	var locationPoId= document.getElementById("locationPoId").value;
	document.getElementById("searchtextsave").value = searchtext;
	document.getElementById("supplierPoId1").value= supplierPoId;
	document.getElementById("locationPoId1").value= locationPoId;
	document.getElementById('searchgrnwithpo').submit();
}

function deleteLongPoProduct(r) {
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("prodTable").deleteRow(i);
	calTotalAmt();
}
function dmdashboardcall() {
	if (document.getElementById("location")) {
		var location = document.getElementById("location").value;
		if (location == '0') {
			jAlert('error', "Please select location from top filter first!",
					'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		} else {
			openSamePopup('dmdashboardProcurement');
		}
	} else {
		openSamePopup('dmdashboardProcurement');
	}

}

function dmdashboardvendorcall(vendorid) {

	if (document.getElementById("location")) {
		var location = document.getElementById("location").value;
		if (location == '0') {
			jAlert('error', "Please select location from top filter first!",
					'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		} else {
			openSamePopup('dmdashboardProcurement?vendorid=' + vendorid + '');
		}
	} else {
		openSamePopup('dmdashboardProcurement?vendorid=' + vendorid + '');
	}

}

function changegrnvendor(procurementid, vendor_id, vendor, voucherno) {
	document.getElementById("changepopup_vendor_div").innerHTML = vendor;
	document.getElementById("changepopup_vendorlist").value = "0";
	document.getElementById("changepopup_procurementid").value = procurementid;
	document.getElementById("changepopup_vendorid").value = vendor_id;
	document.getElementById("changepopup_voucherno").value = voucherno;
	/*
	 * var selectobject=document.getElementById("changepopup_vendorlist"); for
	 * (var i=0; i<selectobject.length; i++){ if (selectobject.options[i].value
	 * ==vendor_id ) selectobject.remove(i); }
	 */
	$('#changevendorpopup').modal("show");
}

function savechangevendor() {
	try {
		document.getElementById("changevendorbtnid").disabled = true;
		var newvendorid = document.getElementById("changepopup_vendorlist").value;
		var procurementid = document
				.getElementById("changepopup_procurementid").value;
		var oldvendorid = document.getElementById("changepopup_vendorid").value;
		var voucherno = document.getElementById("changepopup_voucherno").value;
		var errorflag = false;
		if (newvendorid == '0') {
			errorflag = true;
			jAlert('error', "Please select new supplier!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
		if (!errorflag) {
			document.getElementById("changevendorbtnid").disabled = true;
			var url = "changevendoridProcurement?newvendorid=" + newvendorid
					+ "&procurementid=" + procurementid + "&oldvendorid="
					+ oldvendorid + "&changepopup_voucherno=" + voucherno + "";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.onreadystatechange = savechangevendorRequest;
			req.open("GET", url, true);
			req.send(null);
		} else {
			document.getElementById("changevendorbtnid").disabled = false;
		}
	} catch (e) {
		window.location.reload();
	}
}
function savechangevendorRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			window.location.reload();
		}
	}
}

function checkNumberOrNotofprocurement(inputtxt) {
	//var numbers = /^[0-9]+$/;
	 var numbers =  /^-{0,1}\d*\.{0,1}\d+$/;

	if (inputtxt.match(numbers)) {
		return true;
	} else {
		return false;
	}
}

function changesubjectofpo(textdata) {
	var x = confirm("Are you sure you want to change subject?");
	if (x) {
		var grnno = document.getElementById("grnnooo").value;
		var dataObj = {
			"grnno" : "" + grnno + "",
			"textdata" : "" + textdata + "",
		};
		var data1 = JSON.stringify(dataObj);
		$.ajax({

			url : "savesubjectofpoProcurement",
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true,

			success : function(data) {
				window.location.reload();
			},
			error : function(result) {
				console.log("error in saving diagnosis");
			}
		});
	}

}

function calSalPerEdit(val) {
	var x = confirm("If you change pack then change rate also according to it.");
	if (x) {
		$('.dclass')
				.each(
						function() {
							var i = this.value;
							var mrp = document.getElementById("mrp" + i).value;
							var pack = document.getElementById("pack" + i).value;
							var result = 0;
							result = parseFloat(mrp) / parseInt(pack);
							var num = parseFloat(result);
							num = roundTwo(num);
							document.getElementById("sale_price" + i).value = num;
							var packqty = document
									.getElementById("received_qty" + i).value;
							/*
							 * document.getElementById("received_qty"+i).value=
							 * "";
							 * document.getElementById("received_qty"+i).focus();
							 * document.getElementById("received_qty"+i).value=
							 * packqty;
							 */
							if (val == i) {
								// document.getElementById("received_qty"+i).value="";
								document.getElementById("purchase_price" + i)
										.focus();
								document.getElementById("newreceived_qty" + i).value = Number(pack)
										* Number(packqty);

							}

						});
	}

}

function openGrnWithPo(val) {
	var test = false;
	if (document.getElementById("location")) {
		var loctioid = document.getElementById("location").value;
		if (loctioid == '') {
			test = true;

		} else if (loctioid == '0') {
			test = true;

		}
	}
	if (test) {
		jAlert('error', "Please select location from top!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		openSamePopup('requestedpoProcurement?isfromworkorder='+val);
	}
}

function openGrnWithoutPo() {
	var test = false;
	if (document.getElementById("location")) {
		var loctioid = document.getElementById("location").value;
		if (loctioid == '') {
			test = true;

		} else if (loctioid == '0') {
			test = true;

		}
	}
	if (test) {
		jAlert('error', "Please select location from top!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		openSamePopup('purorderProcurement');
	}
}

function changemailcontentofpo(textdata) {
	var x = confirm("Are you sure you want to change mail content?");
	if (x) {
		var grnno = document.getElementById("grnnooo").value;
		var dataObj = {
			"grnno" : "" + grnno + "",
			"textdata" : "" + textdata + "",
		};
		var data1 = JSON.stringify(dataObj);
		$.ajax({

			url : "savemailcontentofpoProcurement",
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true,

			success : function(data) {
				window.location.reload();
			},
			error : function(result) {
				console.log("error in saving diagnosis");
			}
		});
	}
}

function changeGRNPoDetails(textdata,columname) {

	var grnno = document.getElementById("grnnooo").value;
	var dataObj = {
		"grnno" : "" + grnno + "",
		"textdata" : "" + textdata + "",
		"columname" : ""+columname+"",
		"isdate" : "0",
	};
	var data1 = JSON.stringify(dataObj);
	$.ajax({

		url : "savepodetailsProcurement",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,

		success : function(data) {
			window.location.reload();
		},
		error : function(result) {
			console.log("error in saving diagnosis");
		}
	});

}

function changeGRNPoDates(textdata,columname){
	var grnno = document.getElementById("grnnooo").value;
	var dataObj = {
		"grnno" : "" + grnno + "",
		"textdata" : "" + textdata + "",
		"columname" : ""+columname+"",
		"isdate" : "1",
	};
	var data1 = JSON.stringify(dataObj);
	$.ajax({

		url : "savepodetailsProcurement",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,

		success : function(data) {
			window.location.reload();
		},
		error : function(result) {
			console.log("error in saving diagnosis");
		}
	});
}

function changePoDiscType(val){
	if(val==0){
		document.getElementById("po_disc").value= "0";
		document.getElementById("po_disc").readOnly= true;
		document.getElementById("discount").value="0";
		document.getElementById("discount").readOnly=false;
		$('.myclassJ').each(function() {
			document.getElementById("discounts"+this.value).value=0;
			document.getElementById("discounts"+this.value).readOnly = false;
		});
	}else{
		document.getElementById("po_disc").value= "0";
		document.getElementById("po_disc").readOnly= false;
		document.getElementById("discount").value="0";
		document.getElementById("discount").readOnly=true;
		$('.myclassJ').each(function() {
			document.getElementById("discounts"+this.value).value=0;
			document.getElementById("discounts"+this.value).readOnly = true;
		});
	}
	setAllConfirmData();
}

function calculatePoDisc(val){
	var subTotal = document.getElementById("totalAll").innerHTML;
	if(parseFloat(val)<0){
		document.getElementById("po_disc").value=0;
		jAlert('error', "Please enter valid discount!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(parseFloat(val)>parseFloat(subTotal)) {
		document.getElementById("po_disc").value=0;
		jAlert('error', "Please enter valid discount!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		setAllConfirmData();
	}
	
}

function openWorkOrder() {
	var test = false;
	if (document.getElementById("location")) {
		var loctioid = document.getElementById("location").value;
		if (loctioid == '') {
			test = true;

		} else if (loctioid == '0') {
			test = true;

		}
	}
	if (test) {
		jAlert('error', "Please select location from top!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		openSamePopup('requestedpoProcurement');
	}
}

/* json fuction to store temp terms n cond */
function savepaymenttoprocure(procureid) {
	var procureid1 = procureid;
	var terms = document.getElementById("paymenttermsncond").value;
	
	terms = terms.replace("\t\t\t\t\t\t\t\t\t", "");
	var dataObj = {
		"procureid" : "" + procureid1 + "",
		"terms" : "" + terms + "",

	};
	var data1 = JSON.stringify(dataObj);
	$.ajax({

		url : "savepaytermsandconditionProcurement",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,

		success : function(data) {
			var terms1 = data.terms;
			terms1.replace("\n", "<br>");
			document.getElementById("paymenttermsncond").value = terms;
			document.getElementById("termsshowpayprint").innerHTML = terms1;
		},
		error : function(result) {
			console.log("error in saving diagnosis");
		}
	});
}
var cashPoDisc=0;
function setPoDiscAmount(poDiscAmt){
	cashPoDisc=1;
	getDiscType(1);
}

function openNewProductPopup() {
	 document.getElementById("prodname").value = "";
	 document.getElementById("pack").value = "";
	 document.getElementById("productcategoryid").value="";
	 if(document.getElementById("pro_code")){
		 document.getElementById("pro_code").value=""; 
	 }
	 document.getElementById("addnew_vendorid").value = document.getElementById("vendorid").value;
	 $('#addnewproduct').modal("show");
}

function zoomOut(){
	var x=document.getElementById("megaidvd").className;
	if(x==''){
		document.getElementById("megaidvd").className='hidden';
		//document.getElementById("newidvd").className='hidden';
		//document.getElementById("pay_hist").className='col-lg-12 col-md-12 col-xs-12 ';
		document.getElementById("btnhistory").innerHTML='-';	
		document.getElementById("fixedscroll66").style.height='450px';
		$('.slimScrollDiv').slimScroll({
	   		height : '450px',
		});
	}else{
		document.getElementById("megaidvd").className='';
		//document.getElementById("newidvd").className='';
		//document.getElementById("pay_hist").className='col-lg-12 col-md-12 col-xs-12 hidden';
		document.getElementById("btnhistory").innerHTML='+';	
		//document.getElementById("fixedscroll77").style.height='450px';
		$('.slimScrollDiv').slimScroll({
	   		height : '250px',
		});
		//document.getElementById("fixedscroll66").style.height='150px';
	}
}

function selectAllCheckBox(val){
	
	if (val.checked == true) {
		$('.case').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class// "checkbox1"
		});
	} else {
		$('.case').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}


function chngecolor(id,trid){
	
	/*if (val.checked == true) {
		                          // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
			document.getElementById("chk").style.backgroundColor="lightblue";
			alert("hello");// "checkbox1"
	} else {
		 // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
			 document.getElementById("chk").style.backgroundColor="white";	
		
	}*/
	
	
	/* var checkbox = document.getElementById("checkbox"+id).value;*/
	 
	 
	/*alert(id);*/
	
	var checkbox = document.getElementById(id);
	
	/*alert(checkbox.checked);*/
	
	var tid='chk'+trid;
	
	if (checkbox.checked == true) {
        // loop through each checkbox
		document.getElementById("chk"+trid).style.backgroundColor="lightblue"; // deselect all checkboxes with class

      }else{
    	  
    	  document.getElementById("chk"+trid).style.backgroundColor="";
    	  
      }
	
	
	
}

//for parihar
function getdistotatlforparihar(){
	 var disctotal=0;
	 var totalvat=0;
	 //$('.cdschme')
			//.each(
				//	function() {

					//	count++;
						
					 			
                     //end for parihar
					//});
	 
	 
	 
	 
	//for (var i = 0; i <count; i++) {
		
		//var num=document.getElementById("disamt"+i).innerHTML;
		//var igst=document.getElementById("gstcalamount"+i).innerHTML;
		//igst=parseFloat(igst);
		//totalvat=totalvat+igst;
		//num=parseFloat(num);
	    //disctotal=disctotal+(num);
		
	//}
	
	$('.dclass')
			.each(
					function() {
                        var i=this.value;
						var num=document.getElementById("disamt"+i).innerHTML;
		                var igst=document.getElementById("gstcalamount"+i).innerHTML;
		                igst=parseFloat(igst);
		                totalvat=totalvat+igst;
		                num=parseFloat(num);
	                    disctotal=disctotal+(num);
					 			
                     //end for parihar
					});	
	
	//document.getElementById("discount").value = roundTwo(res);
	//document.getElementById("igst").value = roundTwo(totalgst);
	//document.getElementById("tigst").value = totalgst;
	
	var subTotal = Number(document.getElementById("subTotal").value);
	var debit = Number(document.getElementById("debit").value);
	var subcharge = Number(document.getElementById("subcharge").value);
	var credit = Number(document.getElementById("credit").value);

	var disctype = document.getElementById("disctype").value;
	if (disctype == '0') {
		document.getElementById("discount").value = roundTwo(disctotal);
	}
	var totaldisc = Number(document.getElementById("discount").value);
	var plus = subTotal + totalvat + credit + subcharge;
	var minus = totaldisc + debit;
	var net = plus - minus;
	totalvat = roundTwo(totalvat);

	document.getElementById("vat").value = roundTwo(totalvat);
	// document.getElementById("subDisc").innerHTML=roundTwo(disctotal);
	//document.getElementById("netpay").value = Math.round(net);
	//document.getElementById("nettemp").value = Math.round(net);
	net=roundAbs(net);
	document.getElementById("netpay").value = net;
	document.getElementById("nettemp").value =net;

	if (vendorstateid == stateid) {

		var divide = totalvat / 2;
		document.getElementById("sgst").value = roundTwo(divide);
		document.getElementById("cgst").value = roundTwo(divide);
		document.getElementById("tsgst").value = roundTwo(divide);
		document.getElementById("tcgst").value = roundTwo(divide);

	} else {

		document.getElementById("igst").value = totalvat;
		document.getElementById("tigst").value = totalvat;
	}
	if (document.getElementById("discountedamt")) {
		var discountedamt = subTotal - totaldisc;
		document.getElementById("discountedamt").value = roundTwo(discountedamt);
	}
	

	
}
