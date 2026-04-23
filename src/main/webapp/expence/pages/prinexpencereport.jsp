<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Payment Voucher</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
</html>
<body style="margin: 0; padding: 0;">

  <div class=" letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			
			<br>
			<div class="clinicname" style="padding-left: 18%"><s:property value="name"/>
				</br>
			<div style="font-weight: lighter;font-size: 12px;">
				<span>Ledger Account</span><br>
				  <s:property value="vaddress"/>
			</div>
			</div>
			
			
			
			</div>
		</div>
	</div>






                  <table class="table table-bordered" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="tableback">
                                                        
                                                        <td>Date</td>
                                                         
                                                         <td>Particulars</td>
                                                         <td>Vch Type</td>
                                                         <td>Vch No</td>
                                                      
                                                        <td>Debit</td>
                                                       
                                                        <td>Credit</td>
                                                        
                                                        <td>Closing Balance</td>
                                                        
                                                    </tr>
                                                </thead>
                                                <tfoot style="background-color: rgba(239, 239, 239, 0.58);color: green;">
                                                	<tr>
                                                		<td></td>
                                                		<td></td>
                                                		<td></td>
                                                		
                                                		
                                                		<td style="font-size: 13px;font-weight: bold;">Total</td>
                                                		<td style="font-size: 13px;font-weight: bold;" class=""><%=Constants.getCurrency(loginfo)%><s:property value="ctotal"/></td>
                                                		<td style="font-size: 13px;font-weight: bold;" class=""><%=Constants.getCurrency(loginfo)%><s:property value="expenceTotal"/></td>
                                                		
                                                		
                                                	</tr>
                                                </tfoot>
                                                <tbody>
                                                <s:hidden name="date" id="defaultdate"></s:hidden>
                                                	<s:if test="expenceList.size>0">
                                                	<s:iterator value="expenceList">
                                                    <tr>
                                                        
                                                        <td><s:property value="caldate"/></td>
                                                        
                                                        <s:if test="xpayment == 'Opening'"> 
                                                        	<td>Opening Balance</td>
                                                        	<td></td>
                                                        	<td></td>
                                                      	</s:if>
                                                      	<s:else>
                                                      	
                                                      		<td><s:property value="category"/></td>
                                                        <td><s:property value="xpayment"/></td>
                                                        <td><s:property value="parantid"/></td>
                                                      	
                                                      	</s:else>
                                                      
                                                       
                                                        <td><%=Constants.getCurrency(loginfo)%><s:property value="credit"/></td>
                                                        <td class=""><%=Constants.getCurrency(loginfo)%><s:property value="amount"/></td>                                                        
                                                       
                                                       
                                                       
                                                       <td  class=""><%=Constants.getCurrency(loginfo)%><s:property value="closingBal"/></td>
                                                           
                                                    </tr>
                                                    </s:iterator>
                                                  </s:if>
                                                </tbody>
                                            </table>
                         
                </div>
                <div>
                    <!-- <p>Signature:</p> -->
                </div>
            </td>
        </tr>
        <tr>
            
        </tr>
    </table>
    
    
							<div  class="form-group text-right hidden-print">
									    <a type="button" style="padding: 2%;
    font-size: 18px;" class="btn btn-primary btn-lg savebigbtn" title="Print" onclick="printpage()">Print</a>
                                    
						</div>
</body>

