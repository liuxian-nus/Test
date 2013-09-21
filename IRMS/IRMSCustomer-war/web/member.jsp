<%-- 
    Document   : member
    Created on : Sep 21, 2013, 2:31:13 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
                    <div class="row">
                <div clas="large-9" colums></div>
                <div class="large-3" columns>
                    <form id="member" action="restaurantSearch" method="POST">
                        <fieldset>
                            <legend>Member</legend>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label>E-mail</label>
                                    <input id="input-name" type="text" name="keyword">
                                </div>
                            </div>
                             
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Password</label>
                                    <input id="input-name" type="password" name="password">
                                </div>
                            </div>
                          

                            <br>
                            <div class="row">
                                 <div class="large-12 columns">
                                <input type="submit" class="tiny button, button secondary" value="Login">
                            
                                 </div>
                            </div>

                            <br>
                            <div class="row">
                                 <div class="large-12 columns">
                                <input type="submit" class="tiny button" value="Register New Member">
                            
                                 </div>
                            </div>
                        </fieldset>         
                    </form>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
