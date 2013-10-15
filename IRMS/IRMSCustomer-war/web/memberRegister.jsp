

<%-- 
    Document   : memeberRegister
    Created on : Sep 21, 2013, 4:44:26 PM
    Author     : lionetdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>
            <script type="text/javascript" src="/IRMSCustomer-war/js/checkpass.js"></script>

        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
            <form data-abide id="member" action="memberRegisterResult" method="POST">
                <fieldset>
                    <legend>Member Registration</legend>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>User name</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input required type="text" placeholder="username" name="username">
                                <small class="error">Please enter your name.</small>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>E-mail</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="email" required placeholder="e-mail" name="e-mail" >
                                <small class="error">Please enter a valid e-mail address.</small>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Password</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input required type="password" name="password" id="password">
                                <small class="error">Please enter a password consists of lowercase, uppercase and number.</small>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Confirm password</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input required type="password" name="password2" id="password2" onblur="checkPass()">

                              <!--  <button type="button" onclick="checkPass()">Check</button>-->
                                <small class="error" id="confirmMessage">Please confirm your password.</small>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Mobile phone</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input required pattern="[0-9]{8}" type="text" name="mobile" placeholder="mobile phone number">
                                <small class="error">Please enter a mobile number.</small>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Nationality</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
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
                    </div>

                    <br>
                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Date of Birth</label>
                        </div>

                        <div class="small-3 columns">
                            <select required name="dateDay" class="customDropdown">
                       <!--         <option value="0">Day</option>-->
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
                        <div class="small-3 columns">
                            <select required name="dateMonth" id="button dropdown">
                         <!--       <option value="Month">Month</option>-->
                                <option value="1">Jan</option>
                                <option value="2">Feb</option>
                                <option value="3">Mar</option>
                                <option value="4">Apr</option>
                                <option value="5">May</option>
                                <option value="6">Jun</option>
                                <option value="7">Jul</option>
                                <option value="8">Aug</option>
                                <option value="9">Sep</option>
                                <option value="10">Oct</option>
                                <option value="11">Nov</option>
                                <option value="12">Dec</option>
                            </select>
                        </div>

                        <div class="small-4 columns">
                            <select required name="dateYear" id="customDropdown">
                        <!--        <option value="0">Year</option>-->
                                <option value="2013">2013</option>
                                <option value="2012">2012</option>
                                <option value="2011">2011</option>
                                <option value="2010">2010</option>
                                <option value="2009">2009</option>
                                <option value="2008">2008</option>
                                <option value="2007">2007</option>
                                <option value="2006">2006</option>
                                <option value="2005">2005</option>
                                <option value="2004">2004</option>
                                <option value="2003">2003</option>
                                <option value="2002">2002</option>
                                <option value="2001">2001</option>
                                <option value="2000">2000</option>
                                <option value="1999">1999</option>
                                <option value="1998">1998</option>
                                <option value="1997">1997</option>
                                <option value="1996">1996</option>
                                <option value="1995">1995</option>
                                <option value="1994">1994</option>
                                <option value="1993">1993</option>
                                <option value="1992">1992</option>
                                <option value="1991">1991</option>
                                <option value="1990">1990</option>
                                <option value="1989">1989</option>
                                <option value="1988">1988</option>
                                <option value="1987">1987</option>
                                <option value="1986">1986</option>
                                <option value="1985">1985</option>
                                <option value="1984">1984</option>
                                <option value="1983">1983</option>
                                <option value="1982">1982</option>
                                <option value="1981">1981</option>
                                <option value="1980">1980</option>
                                <option value="1979">1979</option>
                                <option value="1978">1978</option>
                                <option value="1977">1977</option>
                                <option value="1976">1976</option>
                                <option value="1975">1975</option>
                                <option value="1974">1974</option>
                                <option value="1973">1973</option>
                                <option value="1972">1972</option>
                                <option value="1971">1971</option>
                                <option value="1970">1970</option>
                                <option value="1969">1969</option>
                                <option value="1968">1968</option>
                                <option value="1967">1967</option>
                                <option value="1966">1966</option>
                                <option value="1965">1965</option>
                                <option value="1964">1964</option>
                                <option value="1963">1963</option>
                                <option value="1962">1962</option>
                                <option value="1961">1961</option>
                                <option value="1960">1960</option>
                                <option value="1959">1959</option>
                                <option value="1958">1958</option>
                                <option value="1957">1957</option>
                                <option value="1956">1956</option>
                                <option value="1955">1955</option>
                                <option value="1954">1954</option>
                                <option value="1953">1953</option>
                                <option value="1952">1952</option>
                                <option value="1951">1951</option>
                                <option value="1950">1950</option>
                                <option value="1949">1949</option>
                                <option value="1948">1948</option>
                                <option value="1947">1947</option>
                                <option value="1946">1946</option>
                                <option value="1945">1945</option>
                                <option value="1944">1944</option>
                                <option value="1943">1943</option>
                                <option value="1942">1942</option>
                                <option value="1941">1941</option>
                                <option value="1940">1940</option>
                                <option value="1939">1939</option>
                                <option value="1938">1938</option>
                                <option value="1937">1937</option>
                                <option value="1936">1936</option>
                                <option value="1935">1935</option>
                                <option value="1934">1934</option>
                                <option value="1933">1933</option>
                                <option value="1932">1932</option>
                                <option value="1931">1931</option>
                                <option value="1930">1930</option>
                                <option value="1929">1929</option>
                                <option value="1928">1928</option>
                                <option value="1927">1927</option>
                                <option value="1926">1926</option>
                                <option value="1925">1925</option>
                                <option value="1924">1924</option>
                                <option value="1923">1923</option>
                                <option value="1922">1922</option>
                                <option value="1921">1921</option>
                                <option value="1920">1920</option>
                                <option value="1919">1919</option>
                                <option value="1918">1918</option>
                                <option value="1917">1917</option>
                                <option value="1916">1916</option>
                                <option value="1915">1915</option>
                                <option value="1914">1914</option>
                                <option value="1913">1913</option>
                            </select>
                        </div>
                    </div>

                    <br>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Marital Status</label>
                        </div>
                        <div class="small-10 columns"> 

                            <select required id="customDropdown" name="marital">
                 <!--               <option value="0">- Select -</option>-->
                                <option value="Single">Single</option>
                                <option value="Married">Married</option>
                                <option value="Widowed">Widowed</option>
                                <option value="Divorced">Divorced</option>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Gender</label>
                        </div>
                        <div class="small-10 columns"> 

                            <select required id="customDropdown" name="gender">
                      <!--          <option value="0">- Select -</option>-->
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Others">Others</option>
                            </select>
                        </div>

                    </div>
                    <br>



                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Security question</label>
                        </div>
                        <div class="small-10 columns"> 

                            <select required id="customDropdown" name="securityQuestion">
                                <option value="What is your mother's original surname?">What is your mother's original surname?</option>
                                <option value="What is the name of your primary school?">What is the name of your primary school?</option>
                                <option value="What is your best friend's name?">What is your best friend's name?</option>
                                <option value="What is your favourite food?">What is your favourite food?</option>
                            </select>
                        </div>          
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Answer</label>
                        </div>
                        <div class="small-10 columns"> 

                            <input required type="text" placeholder="Security Question Answer" name="answer">
                            <small class="error">Please enter your security question answer.</small>
                        </div>

                    </div>

                    <div class="row">
                        <div class="small-1 columns">
                            <input type="checkbox" name="subscribe" value="true">
                        </div>
                        <div class="small-11 columns"> 
                            <strong><label><strong>I want to subscribe latest updates.</label>
                        </div>
                    </div>

                    <br>

                    <div class="row">
                        <input type="submit" class="small button" value="Submit">
                    </div>
                </fieldset>  

            </form>

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
        <script>
        function checkPass()
        {
        console.log("********in checkpass function******");
        //Store the password field objects into variables ...
        var pass1 = document.getElementById('password');
        var pass2 = document.getElementById('password2');
        console.log(pass1);
        //Store the Confimation Message Object ...
        var message = document.getElementById('confirmMessage');

        if (pass1.value !== pass2.value) {

        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        message.innerHTML = "Passwords Do Not Match!"
      
        }
        }
</script>

    </body>
</html>
