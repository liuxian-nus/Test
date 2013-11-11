<%-- 
    Document   : entertainmentRequest
    Created on : Nov 11, 2013, 10:38:18 PM
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
                <div class="large-6 columns">
                    <strong> Organize your event with us: </strong>
                    <br>
                    <div class="panel"> 
                  <img src="/IRMSCustomer-war/images/entertainment/theater.jpg"/>
                    
                    </div>
                    <div>
                        We provide an exclusive Coral Island Resort Theater for external shows. 
                        The theater is capable to host 500 audience at the same time. 
                        If you would like to host your show at Coral Island Resort, please fill in your 
                        information. 
                        Our external show managers will contact you within 2 working days.
                        
                    </div>
                    
                
                
                </div>
                
                <div class="large-6 columns">
                    <form data-abide id="member" action="entertainmentRegisterResult" method="POST">
                        <fieldset>
                            <legend>Event Registration</legend>
                            <div class="row">
                            </div>
                            <div class="row">


                                <div class="large-5 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Event Name*</strong></label>
                                </div>
                                <div>
                                    <div class="large-7 columns">
                                        <input required type="text" name="eventName">
                                        <small class="error">Please enter your event name.</small>
                                    </div>
                                </div>

                            </div>
                            <br>
                            <div class="row">
                                <div class="large-5 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Contact Name*</label>
                                </div>
                                <div>
                                    <div class="large-7 columns">
                                        <input required type="text" name="name">
                                        <small class="error">Please enter your name.</small>
                                    </div>
                                </div>

                            </div>

                            <br>
                            <div class="row">

                                <div class="large-5 columns">  
                                    <label for="right-label" class="left-align,inline"><strong>E-mail*</label>
                                </div>
                                <div>
                                    <div class="large-7 columns">
                                        <input type="email" required placeholder="e-mail" name="e-mail" >
                                        <small class="error">Please enter a valid e-mail address.</small>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="row">

                                <div class="large-5 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Contact Number*</strong></label>
                                </div>
                                <div>
                                    <div class="large-7 columns">
                                        <input required pattern="[0-9]{8}" type="text" name="contact" placeholder="mobile phone number">
                                        <small class="error">Please enter a valid phone number.</small>
                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <div class="large-5 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Show Description</label>
                                </div>
                            </div> 
                     
                                <div class="row">     
                                    <div class="large-12 columns">
                               
                         
                                        <textarea style="width: 400px;height: 150px;"name ="description" rows="100" cols="50" placeholder="Please decribe your show"></textarea>
                                   
                                    </div>
                                        
                            </div>
                            <br>
                            <div class="row">
                                <div class="large-2 columns large-offset-9">
                                    <input type="submit" class="small button" value="Submit">
                                </div>
                            </div>
                        </fieldset>  

                    </form>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>

        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                    '.js><\/script>')
        </script> 
        <script src="js/foundation.min.js"></script>
        <script>
                  $(document).foundation();
        </script>
    </body>
</html>
