/**
 *   24 May 2018
 */

function donabhsubmit(action) {
       $('#baselayout1loaderPopup').modal( "show" );
       document.getElementById("action").value=action;
       document.getElementById("smartaccfrm").submit(); 
}