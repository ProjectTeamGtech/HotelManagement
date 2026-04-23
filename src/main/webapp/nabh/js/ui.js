$(function() {
  $(".expand").on( "click", function() {
    // $(this).next().slideToggle(200);
    $expand = $(this).find(">:first-child");
    
    if($expand.text() == "+") {
      $expand.text("-");
    } else {
      $expand.text("+");
    }
  });
});
			function switchVisibleNewCommittee () {
			if (document.getElementById('switchVisibleNewCommittee1')) {
			
			if (document.getElementById('switchVisibleNewCommittee1').style.display == 'none') {
			document.getElementById('switchVisibleNewCommittee1').style.display = 'block';
			document.getElementById('switchVisibleNewCommittee2').style.display = 'none';
			}
			else {
			document.getElementById('switchVisibleNewCommittee1').style.display = 'none';
			document.getElementById('switchVisibleNewCommittee2').style.display = 'block';
			}
			}
			}
			function switchVisibleUpdateCheckPointStatus () {
			if (document.getElementById('switchVisibleUpdateCheckPointStatus1')) {
			
			if (document.getElementById('switchVisibleUpdateCheckPointStatus1').style.display == 'none') {
			document.getElementById('switchVisibleUpdateCheckPointStatus1').style.display = 'block';
			document.getElementById('switchVisibleUpdateCheckPointStatus2').style.display = 'none';
			}
			else {
			document.getElementById('switchVisibleUpdateCheckPointStatus1').style.display = 'none';
			document.getElementById('switchVisibleUpdateCheckPointStatus2').style.display = 'block';
			}
			}
			}
			
			
			function switchVisibleEditCommittee () {
			if (document.getElementById('switchVisibleNewCommittee1')) {
			
			if (document.getElementById('switchVisibleNewCommittee1').style.display == 'none') {
			document.getElementById('switchVisibleNewCommittee1').style.display = 'block';
			document.getElementById('switchVisibleEditCommittee2').style.display = 'none';
			}
			else {
			document.getElementById('switchVisibleNewCommittee1').style.display = 'none';
			document.getElementById('switchVisibleEditCommittee2').style.display = 'block';
			}
			}
			}
			 
 
function switchVisibleNewCommittee () {
			if (document.getElementById('switchVisibleNewCommittee1')) {
			
			if (document.getElementById('switchVisibleNewCommittee1').style.display == 'none') {
			document.getElementById('switchVisibleNewCommittee1').style.display = 'block';
			document.getElementById('switchVisibleNewCommittee2').style.display = 'none';
			}
			else {
			document.getElementById('switchVisibleNewCommittee1').style.display = 'none';
			document.getElementById('switchVisibleNewCommittee2').style.display = 'block';
			}
			}
			}
			 

function switchVisibleUpdateCheckPointStatusnewss(indicatorid) {
	if (document.getElementById('switchVisibleUpdateNewCheckPointStatus'+indicatorid)) {
		if (document.getElementById('switchVisibleUpdateNewCheckPointStatus'+indicatorid).style.display == 'none') {
			document.getElementById('switchVisibleUpdateNewCheckPointStatus'+indicatorid).style.display = 'block';
			document.getElementById('switchVisibleUpdateNewCheckPointStatusnew'+indicatorid).style.display = 'none';
		}else {
			document.getElementById('switchVisibleUpdateNewCheckPointStatus'+indicatorid).style.display = 'none';
			document.getElementById('switchVisibleUpdateNewCheckPointStatusnew'+indicatorid).style.display = 'block';
		}
	}
}



