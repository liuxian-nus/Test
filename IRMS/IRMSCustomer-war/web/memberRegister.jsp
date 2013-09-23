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
        </head>
        <body>
        <jsp:include page="header.jsp"></jsp:include>
        <form id="member" action="memberRegister" method="POST">
          <fieldset>
                    <legend>Member Registration</legend>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>User name</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="text" placeholder="username">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>E-mail</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="text" placeholder="e-mail">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Password</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="password">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Confirm password</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="password">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Mobile phone</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="text" placeholder="mobile phone number">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="small-2 columns">
                            <label for="right-label" class="left-align,inline"><strong>Nationality</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <select name="nationality" id="customDropdown">
                                    <option value="1">Afghan</option>
                                    <option value="2">Swedish</option>
                                    <option value="3">Albanian</option>
                                    <option value="4">Algerian</option>
                                    <option value="5">American</option>
                                    <option value="6">Andorran</option>
                                    <option value="7">Angolan</option>
                                    <option value="8">Antiguans</option>
                                    <option value="9">Barbudans</option>
                                    <option value="10">Argentinean</option>
                                    <option value="11">Armenian</option>
                                    <option value="12">Aruban</option>
                                    <option value="13">Australian</option>
                                    <option value="14">Austrian</option>
                                    <option value="15">Azerbaijani</option>
                                    <option value="16">Bahamian</option>
                                    <option value="17">Bahraini</option>
                                    <option value="18">Bangladeshi</option>
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
                                    <option value="42">Chinese</option>
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
                                    <option selected="selected" value="195">Singaporean</option>
                                    <option value="196">Slovakian</option>
                                    <option value="197">Slovenian</option>
                                    <option value="198">Solomon Islander</option>
                                    <option value="199">Somali</option>
                                    <option value="200">South African</option>
                                    <option value="201">South Georgia and South Sandwich Islands</option>
                                    <option value="202">Spanish</option>
                                    <option value="203">Sri Lankan</option>
                                    <option value="204">Sudanese</option>
                                    <option value="205">Surinamer</option>
                                    <option value="206">Svalbard and Jan Mayen Islands</option>
                                    <option value="207">Swazi</option>
                                    <option value="208">Swedish</option>
                                    <option value="209">Swiss</option>
                                    <option value="210">Syrian</option>
                                    <option value="211">Taiwanese</option>
                                    <option value="212">Tajik</option>
                                    <option value="213">Tanzanian</option>
                                    <option value="214">Thai</option>
                                    <option value="215">East Timorese</option>
                                    <option value="216">Togolese</option>
                                    <option value="217">Tokelauan</option>
                                    <option value="218">Tongan</option>
                                    <option value="219">Trinidadian or Tobagonian</option>
                                    <option value="220">Tunisian</option>
                                    <option value="221">Turkish</option>
                                    <option value="222">Turkmenistan</option>
                                    <option value="223">Turks and Caicos Islands</option>
                                    <option value="224">Tuvaluan</option>
                                    <option value="225">Ugandan</option>
                                    <option value="226">Ukrainian</option>
                                    <option value="227">Emirian</option>
                                    <option value="228">British</option>
                                    <option value="229">American</option>
                                    <option value="230">American</option>
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
                            <label for="right-label" class="left-align,inline"><strong>Mobile phone</label>
                        </div>
                        <div>
                            <div class="small-10 columns">
                                <input type="text" placeholder="mobile phone number">
                            </div>
                        </div>
                    </div>

    

                </fieldset>  
            
        </form>
                
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
