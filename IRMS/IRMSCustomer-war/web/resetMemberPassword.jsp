<%-- 
    Document   : resetMemberPassword
    Created on : 27-Sep-2013, 16:04:39
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
    <body>
        <h1>reset member password</h1>
        
         <form id="resetPassword" action="resetMemberPasswordConfirmation" method="POST">
                        <fieldset>
                        
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Old password</label>
                                    <input id="input-name" type="password" name="oldPwd">
                                </div>
                            </div>
                             
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>New Password</label>
                                    <input id="input-name" type="password" name="newPwd1">
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Confirm New Password </label>
                                    <input id="input-name" type="password" name="newPwd2">
                                </div>
                            </div>
                            
                          <input type="hidden" name="email" value="${data.memberEmail}"/>

                            <br>
                            <ul class="button-group">

                                  <li><input type="submit" class="button" value="Submit"></li>
                                  
                            
    
                            </ul>
 
                        </fieldset>         
                    </form>
    </body>
</html>
