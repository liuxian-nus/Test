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
                                    <label>password</label>
                                    <input type="text" name ="password" value="${data.memberPassword}"/>
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
                                    <input type="text" name="date" value ="${data.memberDob.getDate()}"/>
                                </div>   
                            </div>
                            
                            <div class ="row">
                                <div class="large-12 columns">
                                    <label>Month</label>
                                    <input type="text" name="month" value ="${data.memberDob.getMonth()}"/>
                                </div>   
                            </div>
                            
                            <div class ="row">
                                <div class="large-12 columns">
                                    <label>Year</label>
                                    <input type="text" name="year" value ="${data.memberDob.getYear()}"/>
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
                                      <option value="0">- Select -</option>
                                      <option value="1">Male</option>
                                      <option value="2">Female</option>
                                      <option value="3">Others</option>
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
                                <div class="small-1 columns">
                                    <input type="checkbox" name="subscribe" value="${Boolean.toString(data.isSubscriber)}"/>
                                </div>
                                <div class="small-11 columns"> 
                                    <strong><label><strong>I want to subscribe latest updates.</label>
                                </div>
                                
                            </div>
                               
                                
                            <div class="row">
                                <div class="large-12 columns">
                                    <input type="submit" class="small button" value="Confirm">

                                </div>
                            </div>
                            
                           
                        </fieldset>
                        
                    </form>
                    
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
