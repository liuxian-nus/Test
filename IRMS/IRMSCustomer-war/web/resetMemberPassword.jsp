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
        <jsp:include page="header.jsp"></jsp:include>
        <h1>reset member password</h1>
        <%
            String email=request.getParameter("email");
            if(email=="")%> <a href="accessDenied" you have no access to this page/>         
        
         <form id="resetPassword" action="resetMemberPasswordConfirmation" method="POST">
                        <fieldset>
                        
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Old password</label>
                                    <input required id="oldPwd" type="password" name="oldPwd">
                                    
                                </div>
                            </div>                 
                             
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>New Password</label>
                                    <input required id="newPwd1" type="password" name="newPwd1">
                                    <small class="error">Please enter a password consists of lowercase, uppercase and number.</small>
                                </div>
                            </div>
                                                       
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Confirm New Password </label>
                                    <input required id="newPwd2" type="password" name="newPwd2" onblur="checkPass()">
                                </div>
                            </div>
                            
                            <p>${message}</p>
                            
                          <input type="hidden" name="email" value="${data.memberEmail}"/>

                            <br>
                            <ul class="button-group">

                                  <li><input type="submit" class="button" value="Submit"></li>
                                  
                            
    
                            </ul>
 
                        </fieldset>         
                    </form>
          <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
