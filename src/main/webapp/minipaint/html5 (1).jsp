<script src="jquery-1.10.2.js" type="text/javascript"></script>

<p>Drag to create ellipse</p>
<div id="container">
    <canvas id="canvas" class="subcanvs" ></canvas>
    <canvas id="overlay" class="subcanvs"></canvas>
</div>


<a href="#" onclick='$("#file").trigger("click");'>Add Local Image</a>
<input type="file" id="file" style="display:none;">

<a href="#" onclick='drawCircle();'>Circle</a>

<a href="#" onclick='drawCanvasLine();'>Line</a>

<a href="#" onclick='drawCanvasRect();'>Rect</a>

<a href="#" onclick='hello();'>Add Text</a>

<style>

    body {
        background-color: ivory;
    }
    #container {
        position:relative;
        border:1px solid blue;
        width:300px;
        height:200px;
    }
    .subcanvs {
        position:absolute;
        width:100%;
        height:100%;
        cursor: crosshair;
    }

</style>

<script type="text/javascript">

var action = 'default';


$(document).ready(function()
	    {
	
	$('#file').change(function(e){
        var file = e.target.files[0],
          imageType = /image.*/;

        if (!file.type.match(imageType))
          return;

        var reader = new FileReader();
        reader.onload = fileOnload;
        reader.readAsDataURL(file);
      });

      function fileOnload(e) {
    	 // alert(e.target.result )
        var $img = $('<img>', { src: e.target.result });
        var canvas = $('#canvas')[0];
        var context = canvas.getContext('2d');

        $img.load(function() {
          context.drawImage(this, 0, 0);
        });
      }
   

});	


function hello(){
	
	/* var dataURL = canvas.toDataURL();
	
	alert(dataURL) */
	
	 // generate the image data
    var Pic = document.getElementById("canvas").toDataURL("image/png");
    Pic = Pic.replace(/^data:image\/(png|jpg);base64,/, "");
    alert(Pic);

    /* // Sending the image data to Server
    $.ajax({
        type: 'POST',
        url: 'Save_Picture.aspx/UploadPic',
        data: '{ "imageData" : "' + Pic + '" }',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (msg) {
            alert("Done, Picture Uploaded."); 
        }
    }); */
}



window.onload = function() {
   drawEx1();
   
     var $img = $('<img>', { src: "image/body.jpg" });
        var canvas = $('#canvas')[0];
        var context = canvas.getContext('2d');

        $img.load(function() {
          context.drawImage(this, 0, 0);
        });
}

var image1 = null;

function drawEx1() {
    image1 = new Image();
    image1.src = "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";
    image1.addEventListener('load', drawImage1);
}

function drawImage1() {
    var canvas  = document.getElementById("canvas");
    var context = canvas.getContext("2d");

    context.drawImage(image1, 10, 10);
}


var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var overlayCanvas = document.getElementById("overlay");
var overlay = overlayCanvas.getContext("2d");

/* ctx.beginPath();
ctx.rect(20,40,40,30);
ctx.fillStyle="gold";
ctx.stroke();
ctx.fill(); */

$("#overlay").css({
    left: -500,
    top: 0
});

var canvasOffset = $("#canvas").offset();
var offsetX = canvasOffset.left;
var offsetY = canvasOffset.top;

var startX;
var startY;
var x;
var y;
var isDown = false;

function drawEllipse(x, y, context) {
    context.beginPath();
    context.moveTo(startX, startY + (y - startY) / 2);
    context.bezierCurveTo(startX, startY, x, startY, x, startY + (y - startY) / 2);
    context.bezierCurveTo(x, y, startX, y, startX, startY + (y - startY) / 2);
    context.closePath();
    context.stroke();
}

function drawRect(x,y,context){
	
	 context.beginPath();
	 context.moveTo(startX, startY + (y - startY) / 2);
     context.rect(188, 50, 200, 100);
     context.fillStyle = 'yellow';
     context.fill();
     context.lineWidth = 7;
     context.strokeStyle = 'black';
     context.stroke();
}

function drawLine(x, y, context){
	
	 context.beginPath();
     context.moveTo(startX, startY + (y - startY) / 2);
     context.lineTo(x, y);
     context.stroke();
}



function handleMouseDown(e) {
    mouseX = parseInt(e.clientX - offsetX);
    mouseY = parseInt(e.clientY - offsetY);

    // Put your mousedown stuff here
    startX = mouseX;
    startY = mouseY;
    overlay.clearRect(0, 0, overlayCanvas.width, overlayCanvas.height);
    $("#overlay").css({
        left: 0,
        top: 0
    });
    isDown = true;
}

function handleMouseUp(e) {
    mouseX = parseInt(e.clientX - offsetX);
    mouseY = parseInt(e.clientY - offsetY);

    // Put your mouseup stuff here
    isDown = false;
    $("#overlay").css({
        left: -500,
        top: 0
    });
    
    if(action == 'circle'){
    	 drawEllipse(mouseX, mouseY, ctx);	
   	 }
    if(action == 'line'){
   	 	drawLine(mouseX, mouseY, ctx);	
  	 }
    
    if(action == 'rect'){
    	drawRect(mouseX,mouseY,ctx);
    }
   
}

function handleMouseOut(e) {
    mouseX = parseInt(e.clientX - offsetX);
    mouseY = parseInt(e.clientY - offsetY);

    // Put your mouseOut stuff here
    isDown = false;
}

function handleMouseMove(e) {
    mouseX = parseInt(e.clientX - offsetX);
    mouseY = parseInt(e.clientY - offsetY);

    // Put your mousemove stuff here
    if (!isDown) {
        return;
    }
    overlay.clearRect(0, 0, overlayCanvas.width, overlayCanvas.height);
    if(action == 'circle'){
    	drawEllipse(mouseX, mouseY, overlay);	
    }
    if(action == 'line'){
   	 	drawLine(mouseX, mouseY, ctx);	
  	 }
    if(action == 'rect'){
    	drawRect(mouseX,mouseY,ctx);
    }
    
}


function drawCircle(){
	action  = 'circle';
}

function drawCanvasLine(){
	action = 'line';
}

function drawCanvasRect(){
	
	action  = 'rect';
}

 $("#canvas").mousedown(function (e) {
    handleMouseDown(e);
});
$("#overlay").mousemove(function (e) {
    handleMouseMove(e);
}); 
 $("#overlay").mouseup(function (e) {
    handleMouseUp(e);
});
$("#overlay").mouseout(function (e) {
    handleMouseOut(e);
});
 

function addText(){
	 action = 'text'
	/* 
	 if(action == 'text'){
		 canvas.addEventListener("mousedown", getPosition, false);
	 } */
	 
}



function getPosition(event)
{

	var txtmessage = prompt("Enter the URL of the image.");
	//alert(txtmessage)
	
  var x = event.x;
  var y = event.y;

  var canvas = document.getElementById("canvas");

  x -= canvas.offsetLeft;
  y -= canvas.offsetTop;
  
  ctx.font = '20pt Calibri';
  ctx.fillStyle = 'blue';
  ctx.fillText(txtmessage, x, y);

 // alert("x:" + x + " y:" + y);
 
 
}
</script>