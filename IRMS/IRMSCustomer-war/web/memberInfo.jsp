<%-- 
    Document   : memberInfo
    Created on : Sep 21, 2013, 4:54:20 PM
    Author     : Jieqiong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>

<%
    String email=(String)request.getAttribute("email");
    String password=(String) request.getAttribute("password");
    String loginStatus=(String)session.getAttribute("loginStatus");
    System.out.println("loginStatus:"+loginStatus);
    session.setAttribute("email",email);
    session.setAttribute("password", password);
%> 
<html>
    <head>
        <jsp:include page="base.jsp"></jsp:include>

        </head>
        <body onload="checkInfo()">
            <script type="text/javascript">
            $(document).ready(infoCheck());
            </script>
        <jsp:include page="header.jsp"></jsp:include>
        <form action="logOut">
            <button type="Submit" class="button"> log out</button>
        </form>

        <form>      
            <input type="hidden" name="email" value="${data.memberEmail}"/>
            <input type="hidden" name="loginStatus" value="${data2}"/>
        </form>


        <div class="section-container vertical-tabs" data-section="vertical-tabs">
            <section>
                <p class="title" data-section-title><a href="#"><strong>Member Service </strong></a></p>
                <div class="content" data-section-content>
                    <p style="color:#4d4d4d">You have ${data.coin} coins accumulated.</p>

                </div>
            </section>
            <section>
                <p class="title" data-section-title><a href="#" onclick="checkInfo()"><strong>Edit your personal profile</strong></a></p>
                <div class="content" data-section-content>
                    <form id="search-form" action="memberInfoEditionConfirmation" method="POST" >

                        <div class="row">
                            <div class="large-12 columns">
                                <label><strong>Name</strong></label>
                                <input type ="text" name ="userName"  value="${data.memberName}"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-12 columns">
                                <label><strong>Email</strong></label>

                                <input type="text" required name ="email" readonly="readonly" value="${data.memberEmail}"/>


                            </div>
                        </div>

                        <div class="row"> 
                            <div class="large-12 columns">
                                <label><strong>Mobile number</strong></label>
                                <input type="text" name="mobile" value="${data.memberHP}"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="large-4 columns">
                                <label><strong>Nationality</strong></label>
                            </div>
                            <div class="large-4 columns">
                                <label><strong>Gender</strong></label>
                            </div>
                            <div class="large-4 columns">
                                <label><strong>Marital Status</strong></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="large-4 columns">
                                <input type="hidden" id="nationality" value="${data.nationality}"/>
                                <select required name="nationality" id="nationalityDropdown">
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
                                    <option value="Barbadian">Barbadian</option>
                                    <option value="Belarusian">Belarusian</option>
                                    <option value="Belgian">Belgian</option>
                                    <option value="Belizean">Belizean</option>
                                    <option value="Beninese">Beninese</option>
                                    <option value="Bermudian">Bermudian</option>
                                    <option value="Bhutanese">Bhutanese</option>
                                    <option value="Bolivian">Bolivian</option>
                                    <option value="Bosnian">Bosnian</option>
                                    <option value="Bastswana">Batswana</option>
                                    <option value="Brazilian">Brazilian</option>
                                    <option value="Bruneian">Bruneian</option>
                                    <option value="Bulgarian">Bulgarian</option>
                                    <option value="Burkinabe">Burkinabe</option>
                                    <option value="Burundian">Burundian</option>
                                    <option value="Cambodian">Cambodian</option>
                                    <option value="Cameroonian">Cameroonian</option>
                                    <option value="Canadian">Canadian</option>
                                    <option value="Cape Verdean">Cape Verdean</option>
                                    <option value="Caymanian">Caymanian</option>
                                    <option value="Central Afrian">Central African</option>
                                    <option value="Chadian">Chadian</option>
                                    <option value="Chilean">Chilean</option>
                                    <option value="Chinese">Chinese</option>
                                    <option value="Christmas Islanders">Christmas Islanders</option>
                                    <option value="Cocossian">Cocossian </option>
                                    <option value="Colombian">Colombian</option>
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
                                    <option value="Singaporean">Singaporean</option>
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

                            <div class="large-4 columns">

                                <input type="hidden" id="maritalStatus" value="${data.maritalStatus}"/>
                                <select required id="maritalStatusDropdown" name="maritalStatus">
                                    <option value="Single">Single</option>
                                    <option value="Married">Married</option>
                                    <option value="Widowed">Widowed</option>
                                    <option value="Divorced">Divorced</option>
                                </select>

                            </div>
                            <div class="large-4 columns">
                                <input type="hidden" id ="gender" value ="${data.gender}"/>
                                <select required id="genderDropdown" name="gender">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Others">Others</option>
                                </select>

                            </div>
                        </div>

                        <div class="row">
                            <div class="large-12 columns">
                                <label><strong>Date of Birth</strong></label>
                            </div>
                        </div>
                        <div class ="row">
                            <div class="large-4 columns">

                                <input type="hidden" id="date" value="${data.memberDob.getDate()}"/>
                                <select required name="date" id="dateDropdown" value="${data.memberDob.getDate()}"/>
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



                            <div class="large-4 columns">

                                <input type="hidden" id="month" value ="${data.memberDob.getMonth()+1}"/>

                                <select required name="month" id="monthDropdown">

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




                            <div class="large-4 columns">

                                <input type="hidden" id="year" value ="${data.memberDob.getYear()+1900}"/>
                                <select required name="year" id="yearDropdown">

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









                        <div class="row">
                            <div class="large-12 columns">
                                <label><strong>Security Question</strong></label>
                                <input type="hidden" id ="securityQuestion" value ="${data.securityQuestion}"/>
                                <!-- <button type="button" onclick="checkInfo()">Check</button>-->
                                <select required id="securityQuestionDropdown" name="securityQuestion">
                                    <option value="What is your mother's original surname?">What is your mother's original surname?</option>
                                    <option value="What is the name of your primary school?">What is the name of your primary school?</option>
                                    <option value="What is your best friend's name?">What is your best friend's name?</option>
                                    <option value="What is your favourite food?">What is your favourite food?</option>
                                </select>
                            </div>                                        
                        </div>


                        <div class ="row">
                            <div class="large-12 columns">
                                <label><strong>Security Question answer</strong></label>
                                <input type="text" name="answer" value ="${data.answer}"/>
                            </div>   
                        </div>


                        <div class="row">

                            <div class="large-12 columns">
                                <label><strong>Subscription</strong></label>

                                <input type="hidden" id ="subscribe" value ="${data.isSubscriber()}"/>

                                <select required id="subscribeDropdown" name="subscribe">
                                    <option value="true">Yes, I would like to receive news and updates.</option>
                                    <option value="false">No, I don't want the latest updates.</option>

                                </select>
                            </div>  

                        </div>


                        <input type="hidden" name="password" value="${data.memberPassword}"/>
                        <br>
                        <div class="row">
                            <div class="large-12 columns">
                                <input type="submit" class="small button" value="Confirm">    
                            </div>                                
                        </div>
                    </form>

                </div>                      
            </section>

            <section>
                <p class="title" data-section-title><a href="#"><strong>Reset Password</strong></a></p>
                <div class="content" data-section-content>


                    <form data-abide id="resetPassword" action="resetMemberPasswordConfirmation" method="POST">
                        <fieldset>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label><strong>Old password</strong></label>
                                    <input id="input-name" type="password" name="oldPwd">
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label><strong>New Password</strong></label>
                                    <input required id="newPwd1" type="password" name="newPwd1">
                                    <small class="error">Please enter a password consists of lowercase, uppercase and number.</small>
                                </div>
                            </div>

                            <div class="row">
                                <div class="large-12 columns">
                                    <label><strong>Confirm New Password </strong></label>
                                    <input required id="newPwd2" type="password" name="newPwd2" onblur="checkPass()">
                                    <small class="error" id="confirmMessage">Please confirm your password.</small>
                                </div>
                            </div>

                            <p>${message}</p>

                            <input type="hidden" name="email" value="${data.memberEmail}"/>
                        <!--    <input type="hidden" name="isLogin" value="true"/>-->

                            <br>

                            <div class="row">
                                <div class="large-12 columns">
                                    <input type="submit" class="small button" value="Confirm">
                                </div>

                            </div>

                            </ul>

                        </fieldset>         
                    </form>
                </div>
            </section>



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
        <script>
            function checkInfo() {
                console.log("********in check function******");
                // Console.log("********in check information function******");
                //Store the password field objects into variables ...
                var securityQuestion = document.getElementById('securityQuestion').value;
                var gender = document.getElementById('gender').value;
                var subscribe = document.getElementById('subscribe').value;
                console.log(subscribe);
                var maritalStatus = document.getElementById('maritalStatus').value;
                console.log(maritalStatus);
                var year = document.getElementById('year').value;
                var month = document.getElementById('month').value;
                var date = document.getElementById('date').value;
                var nationality = document.getElementById('nationality').value;



                console.log(securityQuestion);
                //look for security question
                for (var i = 0; i < 3; i++) {
                    console.log("inside loop");

                    if (document.getElementById("securityQuestionDropdown").options[i].value == securityQuestion) {

                        document.getElementById("securityQuestionDropdown").options[i].selected = true;
                        console.log(document.getElementById("securityQuestionDropdown").options[i]);
                    }
                }
                //look for gender

                for (var i = 0; i < 2; i++) {
                    console.log("inside loop");

                    if (document.getElementById("genderDropdown").options[i].value == gender) {

                        document.getElementById("genderDropdown").options[i].selected = true;
                        console.log(document.getElementById("genderDropdown").options[i]);
                    }
                }

                // look for subscription

                for (var i = 0; i < 2; i++) {
                    console.log("inside loop");

                    if (document.getElementById("subscribeDropdown").options[i].value == subscribe) {
                        console.log(subscribe);
                        document.getElementById("subscribeDropdown").options[i].selected = true;
                        console.log(document.getElementById("subscribeDropdown").options[i]);
                    }
                }

                // look for maritalStatus

                for (var i = 0; i < 3; i++) {
                    console.log("inside loop");

                    if (document.getElementById("maritalStatusDropdown").options[i].value == maritalStatus) {

                        document.getElementById("maritalStatusDropdown").options[i].selected = true;
                        console.log(document.getElementById("maritalStatusDropdown").options[i]);
                    }
                }
                // look for year
                for (var i = 0; i < 100; i++) {
                    console.log("inside loop");

                    if (document.getElementById("yearDropdown").options[i].value == year) {

                        document.getElementById("yearDropdown").options[i].selected = true;
                        console.log(document.getElementById("yearDropdown").options[i]);
                    }
                }
                // look for month
                for (var i = 0; i < 12; i++) {
                    console.log("inside loop");

                    if (document.getElementById("monthDropdown").options[i].value == month) {

                        document.getElementById("monthDropdown").options[i].selected = true;
                        console.log(document.getElementById("monthDropdown").options[i]);
                    }
                }
                // look for day
                for (var i = 0; i < 31; i++) {
                    console.log("inside loop");

                    if (document.getElementById("dateDropdown").options[i].value == date) {

                        document.getElementById("dateDropdown").options[i].selected = true;

                        console.log(document.getElementById("dateDropdown").options[i]);
                    }
                }

                for (var i = 0; i < 240; i++) {
                    console.log("inside loop");

                    if (document.getElementById("nationalityDropdown").options[i].value == nationality) {

                        document.getElementById("nationalityDropdown").options[i].selected = true;
                        console.log(document.getElementById("nationalityDropdown").options[i]);
                    }
                }
            }


        </script>
        <script>
        function checkPass()
        {
        console.log("********in checkpass function******");
        //Store the password field objects into variables ...
        var pass1 = document.getElementById('newPwd1');
        var pass2 = document.getElementById('newPwd2');
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
