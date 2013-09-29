<%-- 
    Document   : memberForgetPassword
    Created on : Sep 23, 2013, 7:45:22 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>



            <div class="row">

                <form id="search-form" action="memberForgetPasswordResult" method="POST">
                    <fieldset>
                        <legend>Reset Password</legend>

                        <div class="row">
                            <div class="small-2 columns">
                                <label for="right-label" class="left-align,inline"><strong>Security Question</label>
                            </div>
                            <div class="small-10 columns"> 

                                <select id="customDropdown" name="question">
                                    <option value="question1">What is your mother's original surname?</option>
                                    <option value="question2">What is the name of your primary school?</option>
                                    <option value="question3">What is your best friend's name?</option>
                                    <option value="question4">What is your favourite food?</option>
                                </select>
                            </div>

                        </div>

                     <div class="row">
                            <div class="small-2 columns">
                                <label for="right-label" class="left-align,inline"><strong>Answer</label>
                            </div>
                            <div class="small-10 columns"> 

                               <input type="text" placeholder="Security Question Answer" name="answer">
                            </div>

                        </div>
                        
                        <div class="row">
                            <div class="small-2 columns">
                                <label for="right-label" class="left-align,inline"><strong>E-mail</label>
                            </div>
                            <div class="small-10 columns"> 

                               <input type="text" placeholder="Registered email address" name="email">
                            </div>

                        </div>
      
                        <br>
                        <div class="row">
                            <div class="large-12 columns">
                                <input type="submit" class="small button" value="Submit">

                            </div>
                        </div>
                    </fieldset>         
                </form>
            </div>
        </div>


    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
