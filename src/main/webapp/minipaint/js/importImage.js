var globalfilepath = "";
function showPopup(){
	 $( '#selectImage' ).modal( "show" );
}

function setImage(filepath){
	globalfilepath = filepath;
	
	 var $img = $('<img>', { src: filepath });
     var canvas = $('#myCanvas')[0];
   
     var context = canvas.getContext('2d');
     context.clearRect(0, 0, canvas.width, canvas.height);


     $img.load(function() {
       context.drawImage(this, 0, 0);
     });
     
}

function setSelectedImageToEditor(){
	
	 var $img = $('<img>', { src: globalfilepath });
     var canvas = $('#Background')[0];
   
     var context = canvas.getContext('2d');
     context.clearRect(0, 0, canvas.width, canvas.height);

     $img.load(function() {
       context.drawImage(this, 0, 0);
     });
     $( '#selectImage' ).modal( "hide" );
	
}