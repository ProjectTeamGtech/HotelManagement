var devMode = false;

///*
devMode = true; //*/

var isRunningIE6 = false;

if($.browser.msie && $.browser.version=="6.0") {
	isRunningIE6 = true;
	$(document).ready(function() {  
		$("#old_browser_warning").slideDown();
		$("#warn_close").click(function() {
			$("#old_browser_warning").slideUp();
		});
	});
}

/*function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}*/


//jquery function, called every time when document is loaded
//used to add methods and bind events to elements
$(document).ready(function() {
	
	$(".submit_button,.cool_link").click(function(){

		var anchor = $(this).children("a:first").attr("href");
		
		if(anchor != null){
			window.location = anchor;
		}
		
	});
	
	
	
	// jquery tools plugin for showing tool tip on form input fields
	// tool tips are taken from title attribute of input field
	$("form :input").each(function() { 
		var inputType = $(this).attr('type');
		if(inputType == 'text' || inputType == 'password' || inputType == 'select' || inputType == 'textarea'){
		//alert(inputType);
			$(this).tooltip( {
	
				// place tooltip on the right edge of input field
				position : "center right",
		
				// a little tweaking of the position
				offset : [ 10, 10 ],
		
				// use the built-in slide effect
				effect : "slide",
		
				// custom opacity setting
				opacity : 1,
				
				
		
				// events on which tool tip are shown and hidden
				events : {
					def : "mouseenter,mouseleave",		// default setting
					input : "focus,blur mouseleave",				// if fields are input type i.e. textbox, textarea etc.
					widget : "focus,blur mouseleave",				// if fields are widget type i.e. dropdown, chackbox, radio etc.
					tooltip : "mouseenter,mouseleave"	// if fields are itself tool tip
				},
				delay:1000		// delay for tool tip hide
		
			});
		}
	
	});
	
	
	
	
	
	
});










