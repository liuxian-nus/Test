<%-- 
    Document   : memberInfo
    Created on : Sep 21, 2013, 4:54:20 PM
    Author     : Jieqiong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h5> Welcome back, dear ${data.memberName}</h5>

        <div class="section-container vertical-tabs" data-section="vertical-tabs">
            <section>
                <p class="title" data-section-title><a href="#"><strong>Edit your personal profile</strong></a></p>
                <div class="content" data-section-content>


                    <form id="search-form" action="memberInfoEditionConfirmation" method="POST">
                        <fieldset>
                            <legend>Your information</legend>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Name</label>
                                    <input type ="text" name ="userName"  value="${data.memberName}"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Email</label>
                                    <input type="text" name ="email" readonly="readonly" value="${data.memberEmail}"/>
                                </div>   
                            </div>
                                
     

                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Mobile number</label>
                                    <input type="text" name="mobile" value="${data.memberHP}"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Nationality</label>
                                    <input type="text" name ="nationality" value="${data.nationality}"/>
                                </div> 
                            </div>

                            <div class ="row">
                                <div class="large-12 columns">
                                    <label>Date</label>
                                    <select required name="date" class="customDropdown" value="${data.memberDob.getDate()}"/>
                                    <option value="0">Day</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                    </select>
                                </div>   
                            </div>

                            <div class ="row">
                                <div class="large-12 columns">
                                    <label>Month</label>
                                    <input type="text" name="month" value ="${data.memberDob.getMonth()+1}"/>
                                </div>   
                            </div>

                            <div class ="row">
                                <div class="large-12 columns">
                                    <label>Year</label>
                                    <input type="text" name="year" value ="${data.memberDob.getYear()+1900}"/>
                                </div>   
                            </div>


                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Marital Status</label>
                                    <input type="text" name ="maritalStatus" value="${data.maritalStatus}"/>
                                </div> 
                            </div>

                            <div class="row">
                                <div class="small-2 columns">
                                    <label>Gender</label>
                                    <select required id="customDropdown" name="gender" value="${data.gender}"/>
                                    <option value="--Select--">- Select -</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Others">Others</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label>Security Question</label>
                                    <input type="text" name ="securityQuestion" value ="${data.securityQuestion}"/>
                                </div>                                        
                            </div>

                            <div class ="row">
                                <div class="large-12 columns">
                                    <label>Security Question answer</label>
                                    <input type="text" name="answer" value ="${data.answer}"/>
                                </div>   
                            </div>


                            <div class="row">
                                <p>${message}</p>
                                <div class="small-1 columns">
                                    <input type="checkbox" name="subscribe" value="${Boolean.toString(data.isSubscriber)}"/>
                                </div>
                                <div class="small-11 columns"> 
                                    <strong><label><strong>I want to subscribe latest updates.</label>
                                </div>

                            </div>
                                
                                <input type="hidden" name="password" value="${data.memberPassword}"/>


                            <div class="row">
                                <div class="large-12 columns">
                                    <input type="submit" class="small button" value="Confirm">

                                </div>
                            </div>

                        </fieldset>

                    </form>
                                    
                                        <div class="row">
                                            <div class="large-12 columns">
                                                <form action="resetMemberPassword"><input type="Submit" value ="Reset Password"/>
                                                    <input type="hidden" name="email" value="${data.memberEmail}"/>
                                                </form>
                                                <!--         <a href="resetMemberPassword" color="#000000"> Reset Password</a> -->

                                            </div> 
                                        </div>


                </div> -->
            </section>
            <section>
                <p class="title" data-section-title><a href="#"><strong>Member Service</strong></a></p>
                <div class="content" data-section-content>
                    <p>Order food for your event!</p>
                </div>
            </section>
            <section>
                <p class="title" data-section-title><a href="#"><strong>Section 3</strong></a></p>
                <div class="content" data-section-content>
                    <p>Content of section 3.</p>
                </div>
            </section>
        </div>


        <jsp:include page="footer.jsp"></jsp:include>

        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'js/vendor/zepto' : 'js/vendor/jquery') +
                    '.js><\/script>')
        </script>
        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>
