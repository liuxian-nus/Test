<%-- 
    Document   : member
    Created on : Sep 21, 2013, 2:31:13 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>   
<%
    String loginStatus="false";
    session.setAttribute("loginStatus", loginStatus);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
                    <div class="row">
               
                <div class="large-4 columns">
                    <form id="member" action="memberInfo" method="POST">
                        <fieldset>
                            <legend>Member</legend>
                                  <p>${message}</p>
                        
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>E-mail</label>
                                    <input id="input-name" type="text" name="email">
                                </div>
                            </div>
                             
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Password</label>
                                    <input id="input-name" type="password" name="password">
                                </div>
                            </div>
                            
                            <input type="hidden" name="loginStatus" value="false"/>
                          

                            <br>
                            <ul class="button-group">

                                  <li><input type="submit" class="button" value="Login"></li>
                                  <li><a href="memberRegister" class="button">Register</a></li>
                                  <li><a href="memberForgetPassword" >Forget Password</a></li>
                                              
                            </ul>
 
                        </fieldset>         
                    </form>
                </div>
                     <div class="large-7 large-offset-1 columns">
                         <div class="row">
                           <img src="/IRMSCustomer-war/images/templatemo_image_01_new.png" alt="Image 01"/>
                         </div>
                         <div class="row">
                             <ul>
                                 <br>
                                 <li><strong>Enjoy Coral Island Resort Member Exclusive Promotions</strong></li>
                                 <br>
                                 <li><strong>Get rebase for every dollar you spend at Coral Island Resort</strong></li>
                                 <br>
                                 <li><strong>Accumulate your points to upgrade to VIP members</strong></li>
                                 
                             </ul>
                         
                         </div>
                         
                     </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
