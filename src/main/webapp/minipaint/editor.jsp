<%@taglib uri="/struts-tags" prefix="s"%>

<link rel="stylesheet" type="text/css" href="minipaint/styles/stylesEditor.css" />
<link rel="stylesheet" type="text/css" media="print" href="minipaint/styles/print.css" />
<link rel="stylesheet" type="text/css" href="minipaint/styles/menu.css" />



<div id="canvas_container">
	<div id="menu_left">
		<div id="menu_left_container"></div>
		<div style="clear:both;"></div>
		
		<!-- <div class="block">
			<input style="width:98%;border:1px solid #393939;" type="text" id="color_hex" value="#000000" onKeyUp="TOOLS.set_color_manual(this)" /><br />
			<div id="main_colour_rgb">
				Red:   <input id="rgb_r" onKeyUp="TOOLS.set_color_rgb(this, 'r')" type="text" value="255" /><br />
				Green: <input id="rgb_g" onKeyUp="TOOLS.set_color_rgb(this, 'g')" type="text" value="255" /><br />
				Blue:  <input id="rgb_b" onKeyUp="TOOLS.set_color_rgb(this, 'b')" type="text" value="255" />
			</div>
		</div> -->
		<!-- <div class="block" id="info"></div>
		<div class="block" id="action_attributes"></div> -->
		
		<div>
		
		
		<a onclick="MENU.do_menu(['file_new']);" href="javascript:void(0);" class="sameas"><i class="fa fa-file-text-o iambamaned1 seteditoricon" title="New"></i></a>
		<a onclick="MENU.do_menu(['file_open']);" href="javascript:void(0);" class="sameas"><i class="fa fa-folder-open-o iambamaned2 seteditoricon" title="Open"></i></a>
		<a href="javascript:void(0);" onclick = "showPopup();" class="sameas"><i class="fa fa-download iambamaned2 seteditoricon" title="Import from Existing"></i></a>
		<a onclick="MENU.do_menu(['file_save']);" href="javascript:void(0);" class="sameas"><i class="fa fa-floppy-o iambamaned3 seteditoricon" title="Save"></i></a>
		<a onclick="MENU.do_menu(['edit_undo']);" href="javascript:void(0);" class="sameas"><i class="fa fa-undo iambamaned1 seteditoricon" title="Undo"></i></a>
		<a onclick="MENU.do_menu(['edit_redo']);" href="javascript:void(0);" class="sameas"><i class="fa fa-repeat iambamaned4 seteditoricon" title="Redo"></i></a>

		<a onclick="MENU.do_menu(['edit_copy']);" href="javascript:void(0);" class="sameas"><i class="fa fa-files-o iambamaned2 seteditoricon" title="Copy"></i></a>
		<a onclick="MENU.do_menu(['edit_paste']);" href="javascript:void(0);" class="sameas"><i class="fa fa-clipboard iambamaned2 seteditoricon" title="Paste"></i></a>
				<a onclick="MENU.do_menu(['file_print']);" href="javascript:void(0);" class="sameas"><i class="fa fa-print iambamaned5 seteditoricon" title="Print"></i></a>
				
		
		<!-- <a onclick="MENU.do_menu(['file_new']);" href="javascript:void(0);" class="sameas"><img src = "common/img/new.ico" class="imgwiemr" title="New"></a>
		<a onclick="MENU.do_menu(['file_open']);" href="javascript:void(0);" class="sameas"><img src = "common/img/open.ico" class="imgwiemr" title="Open"></a>
		<a href="javascript:void(0);" onclick = "showPopup();" class="sameas"><img src = "common/img/import2.ico" class="imgwiemr" title="Import from Existing"> </a>
		<a onclick="MENU.do_menu(['file_save']);" href="javascript:void(0);" class="sameas"><img src = "common/img/save.ico" class="imgwiemr" title="Save"></a> 
		<a onclick="MENU.do_menu(['edit_undo']);" href="javascript:void(0);" class="sameas"><img src = "common/img/undo.ico" class="imgwiemr" title="Undo"></a>
		<a onclick="MENU.do_menu(['edit_redo']);" href="javascript:void(0);" class="sameas"><img src = "common/img/redo.ico" class="imgwiemr" title="Redo"></a>
		<a onclick="MENU.do_menu(['file_print']);" href="javascript:void(0);" class="sameas"><img src = "common/img/print.ico" class="imgwiemr" title="Print"></a>-->
 	
		
<!-- 		<a onclick="MENU.do_menu(['edit_cut']);" href="javascript:void(0);"><img src = "common/img/cut.ico" title="Cut"></a>
 -->		<!-- <a onclick="MENU.do_menu(['edit_copy']);" href="javascript:void(0);" class="sameas"><img src = "common/img/copy.ico" class="imgwiemr" title="Copy"></a>
		<a onclick="MENU.do_menu(['edit_paste']);" href="javascript:void(0);" class="sameas"><img src = "common/img/paste.ico" class="imgwiemr" title="Paste"></a> -->
		
		
		</div>
		<div id="main_colour" onclick="TOOLS.toggle_color_select();"></div>
		<div style="padding:3px 0px 3px 3px;" class="block" id="all_colors"></div>
	</div>
	 <div id="layers_container" style="display: none;">
		  <div id="preview">
			 <canvas id="canvas_preview"></canvas>
				 <div style="padding-left:5px;">
				<input onclick="DRAW.zoom(-1);" style="width:30px;" class="layer_add" type="button" value="-" />
				<input onclick="DRAW.zoom(+1);" style="width:30px;" class="layer_add" type="button" value="+" />
				<b>Zoom: </b><span id="zoom_nr">100</span>%
				<br />
				<input id="zoom_range" type="range" value="100" min="50" max="1000" step="50" oninput="DRAW.zoom(this.value);" />  
			</div>  
		</div> 
		  <div id="layers_base" style="display: none;">
			<b>Layers</b> <a title="Add new layer" class="layer_add" onclick="LAYER.layer_add();return false;" href="#">+</a>
			<a title="Move down" onclick="LAYER.move_layer('down');return false;" class="layers_arrow" href="#">&darr;</a>
			<a title="Move up" onclick="LAYER.move_layer('up');return false;" class="layers_arrow" href="#">&uarr;</a>
			<a title="Set opacity" onclick="LAYER.set_alpha();return false;" class="layers_arrow" href="#">A</a>
			<div style="" id="layers"></div>
		</div>   
	</div> 
	<div id="canvas_layers">
		<canvas id="canvas_back"><div class="error">Your browser doesn't support canvas.</div></canvas>
		<canvas id="Background"></canvas>
		<div id="canvas_more"></div>
		<canvas id="canvas_front"></canvas> 
	</div>
	<div id="resize-w"></div>
	<div id="resize-h"></div>
	<div id="resize-wh"></div>
	<div id="popup"></div>
</div>

<progress id="uploadprogress" min="0" max="100" value="0">0</progress>
<div style="display:none;" id="tmp"></div>

<script src="minipaint/libs/menu.js"></script>
<script src="minipaint/libs/imagefilters.js"></script>
<script src="minipaint/libs/glfx.js"></script> 
<script src="minipaint/libs/html5slider.js"></script> 
<script src="minipaint/libs/exif.js"></script>
<script src="minipaint/libs/exif-binaryajax.js"></script> 

<script src="minipaint/js/popup.js"></script>
<script src="minipaint/js/controlls.js"></script> 
<script src="minipaint/js/tools.js"></script>
<script src="minipaint/js/draw.js"></script>
<script src="minipaint/js/layers.js"></script>
<script src="minipaint/js/helpers.js"></script>
<script src="minipaint/js/menu_actions.js"></script>
<script src="minipaint/js/settings.js"></script>
<script src="minipaint/js/main.js"></script> 
<script src="minipaint/js/importImage.js"></script> 



 
<!-- Modal User Search-->

 <div class="modal fade" id="selectImage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Select Image</h4>
      </div>
      <div class="modal-body">
      <div class = "row">
       <div class = "col-lg-4 col-md-4">
      	<%--  <s:if test="%{#importImageList != 'null'}">
				<s:select size="20"  list="importImageList"  id = "list1" labelposition="left" title="Select Image" listKey="filepath"
					listValue="imagename" cssClass="form-control showToolTip" data-toggle="tooltip"  onchange="setImage(this.value)"/>
			 </s:if> --%>
      </div>
      <div class = "col-lg-8 col-md-8">
      	 <canvas id="myCanvas" width="400" height="300"></canvas>		 
      
      </div>
      </div>
     
      </div>
      <div class="modal-footer">
      	<input type="button" class="btn btn-primary" value = "Select" onclick="setSelectedImageToEditor()">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>  


