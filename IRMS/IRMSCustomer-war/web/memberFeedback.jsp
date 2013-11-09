<%-- 
    Document   : memberFeedback
    Created on : Nov 9, 2013, 3:38:55 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Feedback Page</title>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <h1>Member Feedback!</h1>
            <h3>Welcome, ${member.memberName}!</h3>
        <form id="search-form" action="memberFeedbackResult" method="POST">
            
            <fieldset> 
                <legend>Feedback Form</legend>
                <h6>Thank you for your feedback.Your voice is appreciated.</h6>
                <div class="row">
                    <div class="large-8 columns">
                        <label>Feedback Title</label>
                        <input name="title" type="text" placeholder="Please put your feedback keywords here">
                    </div>


                    <div class="large-4 columns">
                        <label for="customDropdown1">Department</label>
                        <select id="customDropdown1" class="medium" name="department">
                            <option DISABLED>Please select which department you would like to suggest to</option>
                            <option>Accommodation & Hotel Department</option>
                            <option>Attraction Management Department</option>
                            <option>Convention Hall & Event Department</option>
                            <option>Entertainment & Show Department</option>
                            <option>FB Catering Department</option>
                            <option>Shopping Mall Management Department</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="large-12 columns">
                        <label>Feedback Content</label>
                        <textarea name ="content" rows="10" cols="50" placeholder="Please put your feedback here"></textarea>
                    </div>
                </div>

                <div class="row">
                    <div class="large-8 columns">
                        <label>Feedback Rating</label>
                        <div class="row">
                            <label style="float: left"for="radio1"><input name="rating" type="radio" id="radio1" value="1" style="display:inline" CHECKED><span class="custom radio checked"></span>1 Very Poor </label>
                            <label style="float: left"for="radio1"><input name="rating" type="radio" id="radio1" value="2" style="display:inline"><span class="custom radio"></span>2 Poor </label>
                            <label style="float: left"for="radio1"><input name="rating" type="radio" id="radio1" value="3" style="display:inline"><span class="custom radio"></span>3 Average </label>
                            <label style="float: left"for="radio1"><input name="rating" type="radio" id="radio1" value="4" style="display:inline"><span class="custom radio"></span>4 Good </label>
                            <label style="float: left"for="radio1"><input name="rating" type="radio" id="radio1" value="5" style="display:inline"><span class="custom radio"></span>5 Very Good </label>
                        </div>
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
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
