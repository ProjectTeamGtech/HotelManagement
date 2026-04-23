<!DOCTYPE html>
<html lang="en">
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.ArrayList"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Barcode Generator</title>
    <link rel="stylesheet" href="style.css">
</head>
<style>


body {
    font-family: Arial, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    background-color: #f4f4f4;
}

.container {
    text-align: center;
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    align-items: center;
}

label {
    margin: 10px 0 5px 0;
    font-weight: bold;
}

input, select {
    margin-bottom: 15px;
    padding: 10px;
    width: 100%;
    max-width: 100px;
    box-sizing: border-box;
    text-align: center;
}

#barcode {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}
</style>
<body>
 <%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
  
    	<%if(session.getAttribute("totalPatientLabelList")!=null){%>
    			<%ArrayList<Client>totalBarcodeList = (ArrayList<Client>)session.getAttribute("totalPatientLabelList"); %>
    			<%for(Client barcode : totalBarcodeList) %><% {%>
    			<%if(barcode.getIpdid()==null) {
    			
    				barcode.setIpdid("");
    			
    			}%>
    <div class="container">
        <h1>Barcode Generator</h1>

        <!-- Symbology Selection -->
  
        <select id="symbology" class="hidden">
            <option value="CODE128">Code 128</option>
           
        </select>

        <!-- Data Input -->
       
        <input type="text" id="data" placeholder="Enter data here" value="<%=barcode.getAbrivationid()%>" class="hidden">

        <!-- Size Selection -->
        <label for="size">Choose Barcode Size:</label>
        <select id="size">
            <option value="1">100%</option>
            <option value="2">200%</option>
            <option value="3">300%</option>
        </select>

        <!-- Barcode Display -->
        <div id="barcode"></div>
    </div>
    <%}} %>

    <!-- Script Includes -->
    <script src="https://cdn.jsdelivr.net/npm/jsbarcode@3.11.0/dist/JsBarcode.all.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js"></script>
    <script src="diarymanagement/js/script.js"></script>
</body>
</html>