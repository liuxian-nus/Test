<%-- 
    Document   : eventRegister
    Created on : Oct 9, 2013, 6:10:44 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="base.jsp"></jsp:include>
            <title>Event Register</title>
            <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
            <link rel="stylesheet" href="/resources/demos/style.css" />
            <script>
                
                        $(function() {
                    $("#datepicker").datepicker();
                });
            </script>
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <form data-abide id="member" action="eventRegisterResult" method="POST">
                <fieldset>
                    <legend>Event Registration</legend>
                    <div class="row">
                        <div class="large-6 columns">
                            <h4><strong>Contact Details</strong></h4>
                        </div>
                        <div class="large-6 columns">
                            <h4><strong>Event Details</strong></h4>
                        </div>

                    </div>


                    <div class="row">
                        <div class="large-6 columns">

                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Title</label>
                            </div>
                            <div class="large-7 columns">
                                <select name="title" id="customDropdown">
                                    <option value="Mr.">Mr.</option>
                                    <option value="Miss">Miss</option>
                                    <option value="Mrs.">Mrs.</option>
                                </select>
                            </div>

                        </div>
                        <div class="large-6 columns">
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
                    </div>
                    <br>
                    <div class="row">
                        <div class="large-6 columns">
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Name*</label>
                            </div>
                            <div>
                                <div class="large-7 columns">
                                    <input required type="text" name="name">
                                    <small class="error">Please enter your name.</small>
                                </div>
                            </div>
                        </div>
                        <div class="large-6 columns">
                            <div class="large-5 columns">  
                                <label for="right-label" class="left-align,inline"><strong>Type of Event*</label>
                            </div>
                            <div class="large-7 columns">  
                                <select id="customDropdown" name="eventType">
                                    <option>Conference</option>
                                    <option>Dinner</option>
                                    <option>Dinner and Dance</option>
                                    <option>Exhibition</option>
                                    <option>Meeting</option>
                                    <option>Seminar</option>
                                    <option>Wedding</option>
                                </select>
                            </div>

                        </div>
                    </div>

                    <br>
                    <div class="row">
                        <div class="large-6 columns">
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
                        <div class="large-6 columns">
                             <div class="large-5 columns">  
                                <label for="right-label" class="left-align,inline"><strong>Estimated Start Date</label>
                            </div>
                            <div class="large-7 columns">
                                <input type="text" id="datepicker" />
                            </div>

                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="large-6 columns">

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
                        <div class="large-6 columns">
                               <div class="large-5 columns">  
                                <label for="right-label" class="left-align,inline"><strong>Estimated End Date</label>
                            </div>
                            <div class="large-7 columns">
                                <input type="text" id="datepicker" />
                            </div>

                        </div>

                    </div>
                    <br>
                    <div class="row">
                        <div class="large-6 columns">
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Address</label>
                            </div>
                            <div>
                                <div class="large-7 columns">
                                    <input required type="text" name="address">
                                    <small class="error">Please enter your address.</small>
                                </div>
                            </div>
                        </div>
                        <div class="large-6 columns">
                          
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Number of Attendees</label>
                            </div>
                            <div>
                                <div class="large-7 columns">
                                    <input required type="text" name="eventScale">
                                    <small class="error">Please enter number of attendees.</small>
                                </div>
                            </div>
                        </div>

                        </div>
                    
                    <br>
                    <div class="row">
                        <div class="large-6 columns">
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Country of Residence</label>
                            </div>
                            
                                <div class="large-7 columns">
                                    <select name="countryOfResidence" id="customDropdown">
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
                        <div class="large-6 columns">
                               
                          
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Estimated Budget</label>
                            </div>
                            <div>
                                <div class="large-7 columns">
                                    <input required type="text" name="estimatedBudget">
                                    <small class="error">Please enter your estimated.</small>
                                </div>
                            </div>
                        </div>
                        </div>
                    
                    <br>
                    <div class="row">
                        <div class="large-6 columns">
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Company</label>
                            </div>
                            <div>
                                <div class="large-7 columns">
                                    <input type="text" name="company">
                                    <small class="error">Please enter your Company.</small>
                                </div>
                            </div>
                        </div>
                        <div class="large-6 columns">

                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="large-6 columns"> 
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Industry</label>
                            </div>
                            <div class="large-7 columns"> 

                                <select required id="customDropdown" name="industry">

                                    <option>Banking,Finance&Insurance</option>
                                    <option>Biomedical&Healthcare</option>
                                    <option>IT&Electronics</option>
                                    <option>Oil,Gas,Petrol&Chemical </option>
                                    <option>Engineering&Manufacturing </option>
                                </select>
                            </div>
                        </div>
                        <div class="large-6 columns">
                               <div class="large-1 columns">
                                      <input type="checkbox" name="subscribe" value="true">
                                  </div>
                                  <div class="large-11 columns"> 
                                      <label>I wish to publicize my event on Coral Island Website.</label>
                                  </div>

                        </div>

                    </div>


                    <br>



                    <div class="row">
                        <div class="large-6 columns">
                            <div class="large-5 columns">
                                <label for="right-label" class="left-align,inline"><strong>Preferred Language</label>
                            </div>
                            <div class="large-7 columns"> 

                                <select required id="customDropdown" name="preferredLanguage">
                                    <option value="English">English</option>
                                    <option value="Chinese">Chinese</option>
                                    <option value="Malay">Malay</option>
                                    <option value="Japanese">Japanese</option>
                                </select>
                            </div>      
                        </div>
                        <div class="large-6 columns">
                                  
                               
                              </div>
                        </div>    
               

                    <!--
          
                        
                    -->     
                    <br>

                    <div class="row">
                        <div class="large-2 columns large-offset-10">
                            <input type="submit" class="small button secondary" value="Submit">
                        </div>
                    </div>
                </fieldset>  

            </form>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
