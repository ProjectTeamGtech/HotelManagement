<html>
<head>
    <script type="text/javascript">
        function showHide() 
        {
            if(document.getElementById("color_dropdown").selectedIndex == 3) 
            {
                document.getElementById("hidden_html").style.display = ""; // This line makes the DIV visible
            } 
            else {            
                document.getElementById("hidden_html").style.display = "none"; // This line hides the DIV
            }
        }
    </script>
</head>
<body>

Choose a color: 
<select id="color_dropdown" onchange="showHide()"> 
    <option>Red</option>
    <option>Green</option>
    <option>Blue</option>
    <option>Other</option>
</select>

<div id="hidden_html" style="display:none;">
<p>Other: <input type="text"> </p>
</div>

</body>
</html> 
