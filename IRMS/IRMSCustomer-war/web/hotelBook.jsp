<%-- 
    Document   : hotelSearch
    Created on : 2013-10-7, 22:30:39
    Author     : liuxian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/IRMSCustomer-war/css/flipclock.css" type="text/css" media="screen">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src="/IRMSCustomer-war/js/jquery.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/ddsmoothmenu.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/foundation.min.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/vendor/custom.modernizr.js"></script>
        <script type="text/javascript" src="/IRMSCustomer-war/js/countdown.js"></script> 
        <jsp:include page="base.jsp"></jsp:include>

        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <!--Progress Widget-->
            <div class="row">
                <ul class="button-group round even-4">
                    <li><a href="#" class="button secondary small"><strong>Search your hotel ></strong></a></li>
                    <li><a href="#" class="button small" disabled ><strong>Fill in your information ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Make payment ></strong></a></li>
                    <li><a href="#" class="button small secondary"><strong>Confirm your reservation ></strong></a></li>
                </ul>
            </div>
            <div class ="row">
                <!--<div class="panel">-->  
                <div class="large-7 columns">
                    <div class="panel callout" >
                        <div class="row">
                            <div class="large-8 columns">
                            <c:if test="${data.reservationHotelNo == 1}">
                                <h6>Reservation for <strong class="right-align,inline"> Orchard Hotel</strong></h6>
                            </c:if>
                            <c:if test="${data.reservationHotelNo == 2}">
                                <h6>Hotel Reservation for <strong class="right-align,inline"> Marina Hotel</strong></h6>
                            </c:if>
                            <c:if test="${data.reservationHotelNo == 3}">
                                <h6>Hotel Reservation for <strong class="right-align,inline"> BeachView Hotel</strong></h6>
                            </c:if>
                            <h6>Room Type: <strong class="right-align,inline"> ${data.reservationRoomType} </strong></h6>
                            <h6>Check-In Date: <strong class="right-align,inline"> <fmt:formatDate type="date" pattern="MMM dd yyyy" 
                                            value="${data.rcCheckInDate}" /> </strong></h6>
                            <h6>Check-Out Date: <strong class="right-align,inline"><fmt:formatDate type="date" pattern="MMM dd yyyy" 
                                            value="${data.rcCheckOutDate}" /></strong></h6>
                        </div>

                        <div class="large-4 columns">
                            <c:if test="${data.reservationHotelNo == 1}">
                                <a class="th radius" href="/IRMSCustomer-war/images/gallery/orchard.jpg">
                                    <img src="/IRMSCustomer-war/images/gallery/orchard.jpg">
                                </a>
                            </c:if>
                            <c:if test="${data.reservationHotelNo == 2}">
                                <a class="th radius" href="/IRMSCustomer-war/images/gallery/marina.jpg">
                                    <img src="/IRMSCustomer-war/images/gallery/marina.jpg">
                                </a>
                            </c:if>
                            <c:if test="${data.reservationHotelNo == 3}">
                                <a class="th radius" href="/IRMSCustomer-war/images/gallery/beachview.jpg">
                                    <img src="/IRMSCustomer-war/images/gallery/beachview.jpg">
                                </a>
                            </c:if>

                        </div>
                    </div>

                </div>
            </div> 
            <div class="large-5 columns">
                <div class="panel">
                    <div class="row">
                        <div class='large-12 columns'>
                            <label for="right-label" class="left-align,inline" style="color:#4d4d4d"><h4><strong>Coral Island Members</strong></h4></label>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${member == null}">
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline" style="color:#4d4d4d"><h6>Redeem your points and save money now!</h6></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-4 columns">                               
                                    <a href="member" class="small button">Log In</a>
                                </div>
                                <div class="large-8 columns">
                                    <a href="memberRegister" style="color:#4d4d4d"> Not a member?</a>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline" style="color:#4d4d4d"><h6><strong>Welcome back, ${member.memberName}</strong></h6></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline" style="color:#4d4d4d"><h6><strong>You have ${member.coin} coins.</strong></h6></label>
                                </div>
                            </div>

                        </c:otherwise>
                    </c:choose>


                </div>
            </div>
        </div>


        <div class ="row">
            <div class="large-7 columns">
                <div class="panel callout">
                    <form data-abide action="hotelPay" method="POST">
                        <fieldset>
                            <legend>Hotel Reservation</legend>
                            <p>${message}</p>
                            <div class="row">
                                <div class="large-2 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Title</label>
                                </div>
                                <div class="large-5 columns">
                                    <label for="right-label" class="left-align,inline"><strong>First Name</label>
                                </div>
                                <div class="large-5 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Last Name</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-2 columns">
                                    <select name="title" id="customDropdown">
                                        <option value="Mr.">Mr.</option>
                                        <option value="Miss">Miss</option>
                                        <option value="Mrs.">Mrs.</option>
                                        <option value="Mrs.">Ms.</option>
                                        <option value="Mrs.">Dr.</option>
                                    </select>
                                </div>
                                <div class="large-5 columns">
                                    <input required type="text" id="input-first-name"  placeholder="firstName" name="firstName">
                                    <small class="error">Please enter your first name.</small>
                                </div>
                                <div class="large-5 columns">
                                    <input required type="text" id="input-last-name"  placeholder="lastName" name="lastName">
                                    <small class="error">Please enter your last name.</small>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Email</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <input required type="text" id="input-email"  placeholder="email" name="email">
                                    <small class="error">Please enter your email.</small>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Email (re-confirm)</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-12 columns">
                                    <input required type="text" id="input-email"  placeholder="email" name="email">
                                    <small class="error">Please enter your email.</small>
                                </div>
                            </div>

                            <div class="row">                    
                                <div class="large-6 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Phone Number</label>
                                </div>
                                <div class="large-6 columns">
                                    <label for="right-label" class="left-align,inline"><strong>Country of Passport</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-6 columns">
                                    <input  required pattern="[0-9]{8}" required type="text" id="input-phoneNo"  placeholder="phoneNo" name="phoneNo">
                                    <small class="error">Please enter your phone number.</small>
                                </div>
                                <div class="large-6 columns">
                                    <select required name="nationality" id="customDropdown">
                                        <option value="Afghan">Afghan</option>
                                        <option value="Swedish">Swedish</option>
                                        <option value="Albanian">Albanian</option>
                                        <option value="Algerian">Algerian</option>
                                        <option value="American">American</option>
                                        <option value="Andorran">Andorran</option>
                                        <option value="Angolan">Angolan</option>
                                        <option value="Antiguans">Antiguans</option>
                                        <option value="Barbudans">Barbudans</option>
                                        <option value="Argentinean">Argentinean</option>
                                        <option value="Armenian">Armenian</option>
                                        <option value="Aruban">Aruban</option>
                                        <option value="Australian">Australian</option>
                                        <option value="Austrian">Austrian</option>
                                        <option value="Azerbaijani">Azerbaijani</option>
                                        <option value="Bahamian">Bahamian</option>
                                        <option value="Bahraini">Bahraini</option>
                                        <option value="Bangladeshi">Bangladeshi</option>
                                        <option value="19">Barbadian</option>
                                        <option value="20">Belarusian</option>
                                        <option value="21">Belgian</option>
                                        <option value="22">Belizean</option>
                                        <option value="23">Beninese</option>
                                        <option value="24">Bermudian</option>
                                        <option value="25">Bhutanese</option>
                                        <option value="26">Bolivian</option>
                                        <option value="27">Bosnian</option>
                                        <option value="28">Batswana</option>
                                        <option value="29">Brazilian</option>
                                        <option value="30">Bruneian</option>
                                        <option value="31">Bulgarian</option>
                                        <option value="32">Burkinabe</option>
                                        <option value="33">Burundian</option>
                                        <option value="34">Cambodian</option>
                                        <option value="35">Cameroonian</option>
                                        <option value="36">Canadian</option>
                                        <option value="37">Cape Verdean</option>
                                        <option value="38">Caymanian</option>
                                        <option value="39">Central African</option>
                                        <option value="40">Chadian</option>
                                        <option value="41">Chilean</option>
                                        <option value="Chinese">Chinese</option>
                                        <option value="43">Christmas Islanders</option>
                                        <option value="44">Cocossian </option>
                                        <option value="45">Colombian</option>
                                        <option value="46">Comorian</option>
                                        <option value="47">Congolese</option>
                                        <option value="48">Congolese</option>
                                        <option value="49">Cook Islander</option>
                                        <option value="50">Costa Rican</option>
                                        <option value="51">Ivorian</option>
                                        <option value="52">Croatian</option>
                                        <option value="53">Cuban</option>
                                        <option value="54">Cypriot</option>
                                        <option value="55">Czech</option>
                                        <option value="56">Danish</option>
                                        <option value="57">Djiboutian</option>
                                        <option value="58">Dominican</option>
                                        <option value="59">Dominican</option>
                                        <option value="60">Ecuadorian</option>
                                        <option value="61">Egyptian</option>
                                        <option value="62">Salvadoran</option>
                                        <option value="63">Equatorial Guinean</option>
                                        <option value="64">Eritrean</option>
                                        <option value="65">Estonian</option>
                                        <option value="66">Ethiopian</option>
                                        <option value="67">Falkland Islander</option>
                                        <option value="68">Faroese</option>
                                        <option value="69">Fijian</option>
                                        <option value="70">Finnish</option>
                                        <option value="71">French</option>
                                        <option value="72">French</option>
                                        <option value="73">French Polynesian</option>
                                        <option value="74">French</option>
                                        <option value="75">Gabonese</option>
                                        <option value="76">Gambian</option>
                                        <option value="77">Georgian</option>
                                        <option value="78">German</option>
                                        <option value="79">Ghanaian</option>
                                        <option value="80">Gibraltar</option>
                                        <option value="81">Greek</option>
                                        <option value="82">Greenlander</option>
                                        <option value="83">Grenadian</option>
                                        <option value="84">Guadeloupe</option>
                                        <option value="85">Guamanian</option>
                                        <option value="86">Guatemalan</option>
                                        <option value="87">Guernsey</option>
                                        <option value="88">Guinean</option>
                                        <option value="89">Guinea-Bissauan</option>
                                        <option value="90">Guyanese</option>
                                        <option value="91">Haitian</option>
                                        <option value="92">Herzegovinian</option>
                                        <option value="93">Honduran</option>
                                        <option value="94">Chinese</option>
                                        <option value="95">Hungarian</option>
                                        <option value="96">Icelander</option>
                                        <option value="97">Indian</option>
                                        <option value="98">Indonesian</option>
                                        <option value="99">Iranian</option>
                                        <option value="100">Iraqi</option>
                                        <option value="101">Irish</option>
                                        <option value="102">Manx</option>
                                        <option value="103">Israeli</option>
                                        <option value="104">Italian</option>
                                        <option value="105">Jamaican</option>
                                        <option value="106">Japanese</option>
                                        <option value="107">Jersey</option>
                                        <option value="108">Jordanian</option>
                                        <option value="109">Kazakhstani</option>
                                        <option value="110">Kenyan</option>
                                        <option value="111">Kittian and Nevisian</option>
                                        <option value="112">North Korean</option>
                                        <option value="113">South Korean</option>
                                        <option value="114">Kuwaiti</option>
                                        <option value="115">Kyrgyz</option>
                                        <option value="116">Laotian</option>
                                        <option value="117">Latvian</option>
                                        <option value="118">Lebanese</option>
                                        <option value="119">Lesotho</option>
                                        <option value="120">Liberian</option>
                                        <option value="121">Libyan</option>
                                        <option value="122">Liechtensteiner</option>
                                        <option value="123">Lithuanian</option>
                                        <option value="124">Luxembourger</option>
                                        <option value="125">Chinese</option>
                                        <option value="126">Macedonian</option>
                                        <option value="127">Malagasy</option>
                                        <option value="128">Malawian</option>
                                        <option value="129">Malaysian</option>
                                        <option value="130">Maldivan</option>
                                        <option value="131">Malian</option>
                                        <option value="132">Maltese</option>
                                        <option value="133">Marshallese</option>
                                        <option value="134">Martiniquais</option>
                                        <option value="135">Mauritanian</option>
                                        <option value="136">Mauritian</option>
                                        <option value="137">Mayotte</option>
                                        <option value="138">Mexican</option>
                                        <option value="139">Micronesian</option>
                                        <option value="140">Moldovan</option>
                                        <option value="141">Monacan</option>
                                        <option value="142">Mongolian</option>
                                        <option value="143">Montenegrin</option>
                                        <option value="144">Montserrat</option>
                                        <option value="145">Moroccan</option>
                                        <option value="146">Mozambican</option>
                                        <option value="147">Burmese</option>
                                        <option value="148">Namibian</option>
                                        <option value="149">Nauruan</option>
                                        <option value="150">Nepalese</option>
                                        <option value="151">Netherlander</option>
                                        <option value="152">Dutch</option>
                                        <option value="153">New Caledonia</option>
                                        <option value="154">New Zealander</option>
                                        <option value="155">Nicaraguan</option>
                                        <option value="156">Nigerian</option>
                                        <option value="157">Nigerian</option>
                                        <option value="158">Niue</option>
                                        <option value="159">Norfolk Island</option>
                                        <option value="160">Northern Mariana Islands</option>
                                        <option value="161">Norwegian</option>
                                        <option value="162">Omani</option>
                                        <option value="163">Pakistani</option>
                                        <option value="164">Palauan</option>
                                        <option value="165">Palestinian</option>
                                        <option value="166">Panamanian</option>
                                        <option value="167">Papua New Guinean</option>
                                        <option value="168">Paraguayan</option>
                                        <option value="169">Peruvian</option>
                                        <option value="170">Filipino</option>
                                        <option value="171">Pitcairn</option>
                                        <option value="172">Polish</option>
                                        <option value="173">Portuguese</option>
                                        <option value="174">Puerto Rico</option>
                                        <option value="175">Qatari</option>
                                        <option value="176">Reunion</option>
                                        <option value="177">Romanian</option>
                                        <option value="178">Russian</option>
                                        <option value="179">Rwandan</option>
                                        <option value="180">Saint Barth&#233;lemy</option>
                                        <option value="181">Saint Helena</option>
                                        <option value="182">Kittitian, Nevisian</option>
                                        <option value="183">Saint Lucian</option>
                                        <option value="184">San Marinese</option>
                                        <option value="185">Saint Pierre and Miquelon</option>
                                        <option value="186">Vincentian</option>
                                        <option value="187">Samoan</option>
                                        <option value="188">Sammarinese</option>
                                        <option value="189">Sao Tomean</option>
                                        <option value="190">Saudi</option>
                                        <option value="191">Senegalese</option>
                                        <option value="192">Serbian</option>
                                        <option value="193">Seychellois</option>
                                        <option value="194">Sierra Leonean</option>
                                        <option selected="selected" value="Singaporean">Singaporean</option>
                                        <option value="Slovakian">Slovakian</option>
                                        <option value="Slovenian">Slovenian</option>
                                        <option value="Solomon Islander">Solomon Islander</option>
                                        <option value="Somali">Somali</option>
                                        <option value="South Afriican">South African</option>

                                        <option value="Spanish">Spanish</option>
                                        <option value="Sri Lankan">Sri Lankan</option>
                                        <option value="Sudanese">Sudanese</option>
                                        <option value="Surinamer">Surinamer</option>
                                        <option value="206">Svalbard and Jan Mayen Islands</option>
                                        <option value="Swazi">Swazi</option>
                                        <option value="Swedish">Swedish</option>
                                        <option value="Swish">Swiss</option>
                                        <option value="Syrian">Syrian</option>
                                        <option value="Taiwanese">Taiwanese</option>
                                        <option value="Tajik">Tajik</option>
                                        <option value="Tanzanian">Tanzanian</option>
                                        <option value="Thai">Thai</option>
                                        <option value="215">East Timorese</option>
                                        <option value="216">Togolese</option>
                                        <option value="217">Tokelauan</option>
                                        <option value="218">Tongan</option>
                                        <option value="219">Trinidadian or Tobagonian</option>
                                        <option value="220">Tunisian</option>
                                        <option value="221">Turkish</option>
                                        <option value="222">Turkmenistan</option>
                                        <option value="223">Turks and Caicos Islands</option>
                                        <option value="Tuvaluan">Tuvaluan</option>
                                        <option value="Ugandan">Ugandan</option>
                                        <option value="Ukrainian">Ukrainian</option>
                                        <option value="Emirian">Emirian</option>
                                        <option value="British">British</option>
                                        <option value="American">American</option>

                                        <option value="231">Uruguayan</option>
                                        <option value="232">Uzbekistani</option>
                                        <option value="233">Ni-Vanuatu</option>
                                        <option value="234">Vatican City</option>
                                        <option value="235">Venezuelan</option>
                                        <option value="236">Vietnamese</option>
                                        <option value="237">British</option>
                                        <option value="238">American</option>
                                        <option value="239">Welsh</option>
                                        <option value="240">Sahrawi</option>
                                        <option value="241">Yemenite</option>
                                        <option value="242">Zambian</option>
                                        <option value="243">Zimbabwean</option>
                                        <option value="244">Others</option>
                                    </select>
                                </div>
                            </div>



                            <div class="row">
                                <div class="large-12 columns"> 
                                    <input type="submit" class="small button secondary" name="paymentMethod"value="Continue with Payment">
                                    <c:if test="${member != null}">
                                        <input type="submit" class="small button secondary" name="paymentMethod"value="Pay with my coins">
                                    </c:if>
                                </div>   
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                <p>${redeemMessage}</p>
                                </div>
                            </div>
                            </div>
                        </fieldset>
                    </form>                     
                </div>

                <div class="large-5 columns">
                    <div class="panel">
                        <fieldset>
                            <legend style="color:#4d4d4d">Booking Summary</legend>
                            <p>${message}</p>
                            <div class="row">
                                <div class="large-7 columns">
                                    <label for="right-label" class="left-align,inline"><h6>Rate: </h6></label>
                                </div>
                                <div class="large-5 columns">
                                    <label for="right-label" class="right-align,inline"><strong>${roomPrice}</strong></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-7 columns">
                                    <label for="right-label" class="left-align,inline"><h6>Number of nights:</h6></label>
                                </div>
                                <div class="large-5 columns">
                                    <label for="right-label" class="right-align,inline"><strong>${data.reservationRoomCount}</strong></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="large-7 columns">
                                    <label for="right-label" class="left-align,inline"><h6>Total:</h6></label>
                                </div>
                                <div class="large-5 columns">
                                    <label for="right-label" class="right-align,inline"><strong>${totalPrice}</strong></label>
                                </div>
                            </div>


                    </div>
                    </fieldset>
                    <br><br>

                </div>
            </div>



            <jsp:include page="footer.jsp"></jsp:include>
            <script>
                document.write('<script src=' +
                        ('__proto__' in {} ? 'global/js/vendor/zepto' : 'global/js/vendor/jquery') +
                        '.js><\/script>')
            </script> 

            <script>
                $(document).foundation();
            </script>
    </body>
</html>
