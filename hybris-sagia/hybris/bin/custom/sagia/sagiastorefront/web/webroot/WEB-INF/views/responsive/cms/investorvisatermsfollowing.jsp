<%@ page trimDirectiveWhitespaces="true" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
        <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
          <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
            <c:if test="${component.visible}">
              <!-- <section id="strategyServices" class="strategyServices pt-100">
                <div class="strategicTitle wow zoomIn animated" data-wow-duration="1s">
                  <h1>${component.title}</h1>
                </div>
                <div class="serviceSectionV3TabsContents serviceSectionV3Col">
                                            <c:forEach var="currentComponent2" items="${listOfFollowings}" varStatus="loop">
                                                        <div class="serviceSectionV3Description animated zoomIn">
                                                        <p>${currentComponent2.number}</p>
                                                            <p>${currentComponent2.description}</p>
                                                        </div>
                                            </c:forEach>
                                </div>
                                <img src="${component.backgroundImage.url}">
                                                          </div>
                                </section> -->


                                
<section class="trmsFollowingSection invVisa-ptb">
  
    <div class="trmsFollowingSectionWrap">
    <div class="container-fluid">
      <div class="investorVisaTitle">
        <h2 id="sectionTitle3" class="sectionTitle-animation">${component.title}</h2>
      </div>
     
        
               <div class="followTermsList">
        
     
            
              <ul>
                <div class="row">
                    <c:forEach var="currentComponent2" items="${listOfFollowings}" varStatus="loop">
                  <div class="col-lg-6 col-xl-4">
                      
                      <li  class="wow fadeInUp animated" data-wow-delay="0.2s" data-wow-duration="1s"><span>${currentComponent2.number}</span>
                        ${currentComponent2.description}
          </li>
                  </div>
                </c:forEach>
                </div>
               
          
          
    </ul> 
    
      </div>
      
    </div>
    </div>
    </section>


    <!-- Find out visa type -->

  

    
<section class="investorVisaType invVisa-ptb countrytypeEnglish" id="investorVisaTypeSec" style="display:none">
    <link rel="stylesheet" type="text/css" media="all" href="/_ui/responsive/theme-alpha/css/select2.min.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="/_ui/responsive/theme-alpha/css/animate.min.css"/>


    <div class="container ">
         <div class="investorVisaTitle">
      <h1 id="sectionTitle3" class="sectionTitle-animation"><spring:theme code="portal.investor.visa.type.label"/></h1>
  
  
      <div class="investorVisaTypeContent">
        <div class="row">
          <div class="col-md-6">
             <div class="investorVisaTNationality investorVisaTypeItem">
         <label class="visatypeLabel" for="visaCountry"><spring:theme code="portal.investor.visa.nationality.label"/> </label>
     
      
  <select class="form-control visatypeLabelSelect" id="visaCountry" onchange="nationalityChange()">  
      <option selected value=""><spring:theme code="portal.investor.visa.nationality.selector.label"/></option>
      <option value="AF" data-capital="Kabul">Afghanistan</option>
      <option value="AX" data-capital="Mariehamn">Aland Islands</option>
      <option value="AL" data-capital="Tirana">Albania</option>
      <option value="DZ" data-capital="Algiers">Algeria</option>
      <option value="AS" data-capital="Pago Pago">American Samoa</option>
      <option value="AD" data-capital="Andorra la Vella">Andorra</option>
      <option value="AO" data-capital="Luanda">Angola</option>
      <option value="AI" data-capital="The Valley">Anguilla</option>
      <option value="AG" data-capital="St. John's">Antigua and Barbuda</option>
      <option value="AR" data-capital="Buenos Aires">Argentina</option>
      <option value="AM" data-capital="Yerevan">Armenia</option>
      <option value="AW" data-capital="Oranjestad">Aruba</option>
      <option value="AU" data-capital="Canberra">Australia</option>
      <option value="AT" data-capital="Vienna">Austria</option>
      <option value="AZ" data-capital="Baku">Azerbaijan</option>
      <option value="BS" data-capital="Nassau">Bahamas</option>
      <!--<option value="BH" data-capital="Manama">Bahrain</option>-->
      <option value="BD" data-capital="Dhaka">Bangladesh</option>
      <option value="BB" data-capital="Bridgetown">Barbados</option>
      <option value="BY" data-capital="Minsk">Belarus</option>
      <option value="BE" data-capital="Brussels">Belgium</option>
      <option value="BZ" data-capital="Belmopan">Belize</option>
      <option value="BJ" data-capital="Porto-Novo">Benin</option>
      <option value="BM" data-capital="Hamilton">Bermuda</option>
      <option value="BT" data-capital="Thimphu">Bhutan</option>
      <option value="BO" data-capital="Sucre">Bolivia</option>
      <option value="BA" data-capital="Sarajevo">Bosnia and Herzegovina</option>
      <option value="BW" data-capital="Gaborone">Botswana</option>
      <option value="BR" data-capital="Brasília">Brazil</option>
      <option value="IO" data-capital="Diego Garcia">British Indian Ocean Territory</option>
      <option value="BN" data-capital="Bandar Seri Begawan">Brunei Darussalam</option>
      <option value="BG" data-capital="Sofia">Bulgaria</option>
      <option value="BF" data-capital="Ouagadougou">Burkina Faso</option>
      <option value="BI" data-capital="Bujumbura">Burundi</option>
      <option value="CV" data-capital="Praia">Cabo Verde</option>
      <option value="KH" data-capital="Phnom Penh">Cambodia</option>
      <option value="CM" data-capital="Yaoundé">Cameroon</option>
      <option value="CA" data-capital="Ottawa">Canada</option>
      <option value="KY" data-capital="George Town">Cayman Islands</option>
      <option value="CF" data-capital="Bangui">Central African Republic</option>
      <option value="TD" data-capital="N'Djamena">Chad</option>
      <option value="CL" data-capital="Santiago">Chile</option>
      <option value="CN" data-capital="Beijing">China</option>
      <option value="CX" data-capital="Flying Fish Cove">Christmas Island</option>
      <option value="CC" data-capital="West Island">Cocos (Keeling) Islands</option>
      <option value="CO" data-capital="Bogotá">Colombia</option>
      <option value="KM" data-capital="Moroni">Comoros</option>
      <option value="CK" data-capital="Avarua">Cook Islands</option>
      <option value="CR" data-capital="San José">Costa Rica</option>
      <option value="HR" data-capital="Zagreb">Croatia</option>
      <option value="CU" data-capital="Havana">Cuba</option>
      <option value="CW" data-capital="Willemstad">Curaçao</option>
      <option value="CY" data-capital="Nicosia">Cyprus</option>
      <option value="CZ" data-capital="Prague">Czech Republic</option>
      <option value="CI" data-capital="Yamoussoukro">Côte d'Ivoire</option>
      <option value="CD" data-capital="Kinshasa">Democratic Republic of the Congo</option>
      <option value="DK" data-capital="Copenhagen">Denmark</option>
      <option value="DJ" data-capital="Djibouti">Djibouti</option>
      <option value="DM" data-capital="Roseau">Dominica</option>
      <option value="DO" data-capital="Santo Domingo">Dominican Republic</option>
      <option value="EC" data-capital="Quito">Ecuador</option>
      <option value="EG" data-capital="Cairo">Egypt</option>
      <option value="SV" data-capital="San Salvador">El Salvador</option>
      <option value="GQ" data-capital="Malabo">Equatorial Guinea</option>
      <option value="ER" data-capital="Asmara">Eritrea</option>
      <option value="EE" data-capital="Tallinn">Estonia</option>
      <option value="ET" data-capital="Addis Ababa">Ethiopia</option>
      <option value="FK" data-capital="Stanley">Falkland Islands</option>
      <option value="FO" data-capital="Tórshavn">Faroe Islands</option>
      <option value="FM" data-capital="Palikir">Federated States of Micronesia</option>
      <option value="FJ" data-capital="Suva">Fiji</option>
      <option value="FI" data-capital="Helsinki">Finland</option>
      <option value="MK" data-capital="Skopje">Former Yugoslav Republic of Macedonia</option>
      <option value="FR" data-capital="Paris">France</option>
      <option value="PF" data-capital="Papeete">French Polynesia</option>
      <option value="GA" data-capital="Libreville">Gabon</option>
      <option value="GM" data-capital="Banjul">Gambia</option>
      <option value="GE" data-capital="Tbilisi">Georgia</option>
      <option value="DE" data-capital="Berlin">Germany</option>
      <option value="GH" data-capital="Accra">Ghana</option>
      <option value="GI" data-capital="Gibraltar">Gibraltar</option>
      <option value="GR" data-capital="Athens">Greece</option>
      <option value="GL" data-capital="Nuuk">Greenland</option>
      <option value="GD" data-capital="St. George's">Grenada</option>
      <option value="GU" data-capital="Hagåtña">Guam</option>
      <option value="GT" data-capital="Guatemala City">Guatemala</option>
      <option value="GG" data-capital="Saint Peter Port">Guernsey</option>
      <option value="GN" data-capital="Conakry">Guinea</option>
      <option value="GW" data-capital="Bissau">Guinea-Bissau</option>
      <option value="GY" data-capital="Georgetown">Guyana</option>
      <option value="HT" data-capital="Port-au-Prince">Haiti</option>
      <option value="VA" data-capital="Vatican City">Holy See</option>
      <option value="HN" data-capital="Tegucigalpa">Honduras</option>
      <option value="HK" data-capital="Hong Kong">Hong Kong</option>
      <option value="HU" data-capital="Budapest">Hungary</option>
      <option value="IS" data-capital="Reykjavik">Iceland</option>
      <option value="IN" data-capital="New Delhi">India</option>
      <option value="ID" data-capital="Jakarta">Indonesia</option>
      <option value="IR" data-capital="Tehran">Iran</option>
      <option value="IQ" data-capital="Baghdad">Iraq</option>
      <option value="IE" data-capital="Dublin">Ireland</option>
      <option value="IM" data-capital="Douglas">Isle of Man</option>
      <option value="IL" data-capital="Jerusalem">Israel</option>
      <option value="IT" data-capital="Rome">Italy</option>
      <option value="JM" data-capital="Kingston">Jamaica</option>
      <option value="JP" data-capital="Tokyo">Japan</option>
      <option value="JE" data-capital="Saint Helier">Jersey</option>
      <option value="JO" data-capital="Amman">Jordan</option>
      <option value="KZ" data-capital="Astana">Kazakhstan</option>
      <option value="KE" data-capital="Nairobi">Kenya</option>
      <option value="KI" data-capital="South Tarawa">Kiribati</option>
      <!--<option value="KW" data-capital="Kuwait City">Kuwait</option>-->
      <option value="KG" data-capital="Bishkek">Kyrgyzstan</option>
      <option value="LA" data-capital="Vientiane">Laos</option>
      <option value="LV" data-capital="Riga">Latvia</option>
      <option value="LB" data-capital="Beirut">Lebanon</option>
      <option value="LS" data-capital="Maseru">Lesotho</option>
      <option value="LR" data-capital="Monrovia">Liberia</option>
      <option value="LY" data-capital="Tripoli">Libya</option>
      <option value="LI" data-capital="Vaduz">Liechtenstein</option>
      <option value="LT" data-capital="Vilnius">Lithuania</option>
      <option value="LU" data-capital="Luxembourg City">Luxembourg</option>
      <option value="MO" data-capital="Macau">Macau</option>
      <option value="MG" data-capital="Antananarivo">Madagascar</option>
      <option value="MW" data-capital="Lilongwe">Malawi</option>
      <option value="MY" data-capital="Kuala Lumpur">Malaysia</option>
      <option value="MV" data-capital="Malé">Maldives</option>
      <option value="ML" data-capital="Bamako">Mali</option>
      <option value="MT" data-capital="Valletta">Malta</option>
      <option value="MH" data-capital="Majuro">Marshall Islands</option>
      <option value="MQ" data-capital="Fort-de-France">Martinique</option>
      <option value="MR" data-capital="Nouakchott">Mauritania</option>
      <option value="MU" data-capital="Port Louis">Mauritius</option>
      <option value="MX" data-capital="Mexico City">Mexico</option>
      <option value="MD" data-capital="Chișinău">Moldova</option>
      <option value="MC" data-capital="Monaco">Monaco</option>
      <option value="MN" data-capital="Ulaanbaatar">Mongolia</option>
      <option value="ME" data-capital="Podgorica">Montenegro</option>
      <option value="MS" data-capital="Little Bay, Brades, Plymouth">Montserrat</option>
      <option value="MA" data-capital="Rabat">Morocco</option>
      <option value="MZ" data-capital="Maputo">Mozambique</option>
      <option value="MM" data-capital="Naypyidaw">Myanmar</option>
      <option value="NA" data-capital="Windhoek">Namibia</option>
      <option value="NR" data-capital="Yaren District">Nauru</option>
      <option value="NP" data-capital="Kathmandu">Nepal</option>
      <option value="NL" data-capital="Amsterdam">Netherlands</option>
      <option value="NZ" data-capital="Wellington">New Zealand</option>
      <option value="NI" data-capital="Managua">Nicaragua</option>
      <option value="NE" data-capital="Niamey">Niger</option>
      <option value="NG" data-capital="Abuja">Nigeria</option>
      <option value="NU" data-capital="Alofi">Niue</option>
      <option value="NF" data-capital="Kingston">Norfolk Island</option>
      <option value="KP" data-capital="Pyongyang">North Korea</option>
      <option value="MP" data-capital="Capitol Hill">Northern Mariana Islands</option>
      <option value="NO" data-capital="Oslo">Norway</option>
      <!--<option value="OM" data-capital="Muscat">Oman</option>-->
      <option value="PK" data-capital="Islamabad">Pakistan</option>
      <option value="PW" data-capital="Ngerulmud">Palau</option>
      <option value="PA" data-capital="Panama City">Panama</option>
      <option value="PG" data-capital="Port Moresby">Papua New Guinea</option>
      <option value="PY" data-capital="Asunción">Paraguay</option>
      <option value="PE" data-capital="Lima">Peru</option>
      <option value="PH" data-capital="Manila">Philippines</option>
      <option value="PN" data-capital="Adamstown">Pitcairn</option>
      <option value="PL" data-capital="Warsaw">Poland</option>
      <option value="PT" data-capital="Lisbon">Portugal</option>
      <option value="PR" data-capital="San Juan">Puerto Rico</option>
      <!--<option value="QA" data-capital="Doha">Qatar</option>-->
      <option value="CG" data-capital="Brazzaville">Republic of the Congo</option>
      <option value="RO" data-capital="Bucharest">Romania</option>
      <option value="RU" data-capital="Moscow">Russia</option>
      <option value="RW" data-capital="Kigali">Rwanda</option>
      <option value="BL" data-capital="Gustavia">Saint Barthélemy</option>
      <option value="KN" data-capital="Basseterre">Saint Kitts and Nevis</option>
      <option value="LC" data-capital="Castries">Saint Lucia</option>
      <option value="VC" data-capital="Kingstown">Saint Vincent and the Grenadines</option>
      <option value="WS" data-capital="Apia">Samoa</option>
      <option value="SM" data-capital="San Marino">San Marino</option>
      <option value="ST" data-capital="São Tomé">Sao Tome and Principe</option>
      <option value="SA" data-capital="Riyadh">Saudi Arabia</option>
      <option value="SN" data-capital="Dakar">Senegal</option>
      <option value="RS" data-capital="Belgrade">Serbia</option>
      <option value="SC" data-capital="Victoria">Seychelles</option>
      <option value="SL" data-capital="Freetown">Sierra Leone</option>
      <option value="SG" data-capital="Singapore">Singapore</option>
      <option value="SX" data-capital="Philipsburg">Sint Maarten</option>
      <option value="SK" data-capital="Bratislava">Slovakia</option>
      <option value="SI" data-capital="Ljubljana">Slovenia</option>
      <option value="SB" data-capital="Honiara">Solomon Islands</option>
      <option value="SO" data-capital="Mogadishu">Somalia</option>
      <option value="ZA" data-capital="Pretoria">South Africa</option>
      <option value="KR" data-capital="Seoul">South Korea</option>
      <option value="SS" data-capital="Juba">South Sudan</option>
      <option value="ES" data-capital="Madrid">Spain</option>
      <option value="LK" data-capital="Sri Jayawardenepura Kotte, Colombo">Sri Lanka</option>
      <option value="PS" data-capital="Ramallah">State of Palestine</option>
      <option value="SD" data-capital="Khartoum">Sudan</option>
      <option value="SR" data-capital="Paramaribo">Suriname</option>
      <option value="SZ" data-capital="Lobamba, Mbabane">Swaziland</option>
      <option value="SE" data-capital="Stockholm">Sweden</option>
      <option value="CH" data-capital="Bern">Switzerland</option>
      <option value="SY" data-capital="Damascus">Syrian Arab Republic</option>
      <option value="TW" data-capital="Taipei">Taiwan</option>
      <option value="TJ" data-capital="Dushanbe">Tajikistan</option>
      <option value="TZ" data-capital="Dodoma">Tanzania</option>
      <option value="TH" data-capital="Bangkok">Thailand</option>
      <option value="TL" data-capital="Dili">Timor-Leste</option>
      <option value="TG" data-capital="Lomé">Togo</option>
      <option value="TK" data-capital="Nukunonu, Atafu,Tokelau">Tokelau</option>
      <option value="TO" data-capital="Nukuʻalofa">Tonga</option>
      <option value="TT" data-capital="Port of Spain">Trinidad and Tobago</option>
      <option value="TN" data-capital="Tunis">Tunisia</option>
      <option value="TR" data-capital="Ankara">Turkey</option>
      <option value="TM" data-capital="Ashgabat">Turkmenistan</option>
      <option value="TC" data-capital="Cockburn Town">Turks and Caicos Islands</option>
      <option value="TV" data-capital="Funafuti">Tuvalu</option>
      <option value="UG" data-capital="Kampala">Uganda</option>
      <option value="UA" data-capital="Kiev">Ukraine</option>
      <!--<option value="AE" data-capital="Abu Dhabi">United Arab Emirates</option>-->
      <option value="GB" data-capital="London">United Kingdom</option>
      <option value="US" data-capital="Washington, D.C.">United States of America</option>
      <option value="UY" data-capital="Montevideo">Uruguay</option>
      <option value="UZ" data-capital="Tashkent">Uzbekistan</option>
      <option value="VU" data-capital="Port Vila">Vanuatu</option>
      <option value="VE" data-capital="Caracas">Venezuela</option>
      <option value="VN" data-capital="Hanoi">Vietnam</option>
      <option value="VG" data-capital="Road Town">Virgin Islands (British)</option>
      <option value="VI" data-capital="Charlotte Amalie">Virgin Islands (U.S.)</option>
      <option value="EH" data-capital="Laayoune">Western Sahara</option>
      <option value="YE" data-capital="Sana'a">Yemen</option>
      <option value="ZM" data-capital="Lusaka">Zambia</option>
      <option value="ZW" data-capital="Harare">Zimbabwe</option>
    </select>


    
  
  
  
      </div>
          </div>
          <div class="col-md-6">
              <div class="investorVisaHaveType investorVisaTypeItem">
         <label class="visatypeLabel" for="visaCountry">Choose one of the following.</label>
        <select disabled ="disabled" class="visatypeLabelSelect counrtyVisaType" id="counrtyVisaType" onchange="counrtyVisaTypeChange()" class="form-control">
          <option selected disabled>Please select one option</option>
         <option value="1">I am a US, UK or Schengen resident or business or tourist visa holder</option>
  <option value="2"> I am a GCC resident</option>
  <option value="3">I belong to a licensed entity from the Ministry of Investment</option>
  <option value="4">None of the Above</option>
  
  
  
        </select>
  
       
  
      </div>
          </div>
        </div>
     
     
  
  
      <div class="investorVisaTypeItem investorVisaStatus " style="display: none;">
        <div class="eligibleStatusInfo">
          
          <div class="visaType1" style="display:none;">
             <h3 class="instendVisaStatus">You are eligible for online <span>Instant </span> Visa </h3>
             <a href="https://visa.mofa.gov.sa/" target="_blank" class="visaTypeApplyBtn"><span>Apply now</span></a>
          </div>
          <div class="visaType2" style="display:none;">
            <h3 class="onlineVisaStatus" >You are eligible for <span>Online</span> Visa  </h3>
             <a href="https://visa.mofa.gov.sa/" target="_blank" class="visaTypeApplyBtn"><span>Apply now</span></a>
          </div>
          <div class="visaType0" style="display:none;">
             <h2 class=" embassyStatus" > Embassy/Consulate  </h2>
         <a href="#invVisaApplyEnquiry" class="invVisaThemeBtn">Contact us</a>
          </div>
  
       
       
        </div>
  
       
      </div>
  
      
  
  
      </div>
  
  </div>
  
    </div>
  

  </section>


<!-- Arabic Type-->
  
    
<section class="investorVisaType invVisa-ptb countrytypeArabic" id="investorVisaTypeSec" style="display:none">



    <div class="container ">
         <div class="investorVisaTitle">
      <h1 id="sectionTitle3" class="sectionTitle-animation"> <spring:theme code="find.visa.type.title"/></h1>
  
  
      <div class="investorVisaTypeContent">
        <div class="row">
          <div class="col-md-6">
             <div class="investorVisaTNationality investorVisaTypeItem">
         <label class="visatypeLabel" for="visaCountry"><spring:theme code="visa.find.nationality"/></label>
     
         
<select class="form-control visatypeLabelSelect" id="visaCountryAr" onchange="nationalityChangeAr()">  
    <option disabled selected value=""><spring:theme code="visa.country.label"/> </option>
    <option value="AF" data-capital="Kabul">  <spring:theme code="country.1"/></option>
    <option value="AX" data-capital="Mariehamn">  <spring:theme code="country.2"/></option>
    <option value="AL" data-capital="Tirana"> <spring:theme code="country.3"/></option>
    <option value="DZ" data-capital="Algiers">  <spring:theme code="country.4"/></option>
    <option value="AS" data-capital="Pago Pago">  <spring:theme code="country.5"/></option>
    <option value="AD" data-capital="Andorra la Vella"> <spring:theme code="country.6"/></option>
    <option value="AO" data-capital="Luanda"> <spring:theme code="country.7"/></option>
    <option value="AI" data-capital="The Valley"> <spring:theme code="country.8"/></option>
    <option value="AG" data-capital="St. John's"> <spring:theme code="country.9"/></option>
    <option value="AR" data-capital="Buenos Aires"> <spring:theme code="country.10"/></option>
    <option value="AM" data-capital="Yerevan">  <spring:theme code="country.11"/></option>
    <option value="AW" data-capital="Oranjestad"> <spring:theme code="country.12"/></option>
    <option value="AU" data-capital="Canberra"> <spring:theme code="country.13"/></option>
    <option value="AT" data-capital="Vienna"> <spring:theme code="country.14"/></option>
    <option value="AZ" data-capital="Baku"> <spring:theme code="country.15"/></option>
    <option value="BS" data-capital="Nassau"> <spring:theme code="country.16"/></option>
    <option value="BD" data-capital="Dhaka">  <spring:theme code="country.17"/></option>
    <option value="BB" data-capital="Bridgetown"> <spring:theme code="country.18"/></option>
    <option value="BY" data-capital="Minsk">  <spring:theme code="country.19"/></option>
    <option value="BE" data-capital="Brussels"> <spring:theme code="country.20"/></option>
    <option value="BZ" data-capital="Belmopan"> <spring:theme code="country.21"/></option>
    <option value="BJ" data-capital="Porto-Novo"> <spring:theme code="country.22"/></option>
    <option value="BM" data-capital="Hamilton"> <spring:theme code="country.23"/></option>
    <option value="BT" data-capital="Thimphu">  <spring:theme code="country.24"/></option>
    <option value="BO" data-capital="Sucre">  <spring:theme code="country.25"/></option>
    <option value="BA" data-capital="Sarajevo"> <spring:theme code="country.26"/></option>
    <option value="BW" data-capital="Gaborone"> <spring:theme code="country.27"/></option>
    <option value="BR" data-capital="Brasília"> <spring:theme code="country.28"/></option>
    <option value="IO" data-capital="Diego Garcia"> <spring:theme code="country.29"/></option>
    <option value="BN" data-capital="Bandar Seri Begawan">  <spring:theme code="country.30"/></option>
    <option value="BG" data-capital="Sofia">  <spring:theme code="country.31"/></option>
    <option value="BF" data-capital="Ouagadougou">  <spring:theme code="country.32"/></option>
    <option value="BI" data-capital="Bujumbura">  <spring:theme code="country.33"/></option>
    <option value="CV" data-capital="Praia">  <spring:theme code="country.34"/></option>
    <option value="KH" data-capital="Phnom Penh"> <spring:theme code="country.35"/></option>
    <option value="CM" data-capital="Yaoundé">  <spring:theme code="country.36"/></option>
    <option value="CA" data-capital="Ottawa"> <spring:theme code="country.37"/></option>
    <option value="KY" data-capital="George Town">  <spring:theme code="country.38"/></option>
    <option value="CF" data-capital="Bangui"> <spring:theme code="country.39"/></option>
    <option value="TD" data-capital="N'Djamena">  <spring:theme code="country.40"/></option>
    <option value="CL" data-capital="Santiago"> <spring:theme code="country.41"/></option>
    <option value="CN" data-capital="Beijing">  <spring:theme code="country.42"/></option>
    <option value="CX" data-capital="Flying Fish Cove"> <spring:theme code="country.43"/></option>
    <option value="CC" data-capital="West Island">  <spring:theme code="country.44"/></option>
    <option value="CO" data-capital="Bogotá"> <spring:theme code="country.45"/></option>
    <option value="KM" data-capital="Moroni"> <spring:theme code="country.46"/></option>
    <option value="CK" data-capital="Avarua"> <spring:theme code="country.47"/></option>
    <option value="CR" data-capital="San José"> <spring:theme code="country.48"/></option>
    <option value="HR" data-capital="Zagreb"> <spring:theme code="country.49"/></option>
    <option value="CU" data-capital="Havana"> <spring:theme code="country.50"/></option>
    <option value="CW" data-capital="Willemstad"> <spring:theme code="country.51"/></option>
    <option value="CY" data-capital="Nicosia">  <spring:theme code="country.52"/></option>
    <option value="CZ" data-capital="Prague"> <spring:theme code="country.53"/></option>
    <option value="CI" data-capital="Yamoussoukro"> <spring:theme code="country.54"/></option>
    <option value="CD" data-capital="Kinshasa"> <spring:theme code="country.55"/></option>
    <option value="DK" data-capital="Copenhagen"> <spring:theme code="country.56"/></option>
    <option value="DJ" data-capital="Djibouti"> <spring:theme code="country.57"/></option>
    <option value="DM" data-capital="Roseau"> <spring:theme code="country.58"/></option>
    <option value="DO" data-capital="Santo Domingo">  <spring:theme code="country.59"/></option>
    <option value="EC" data-capital="Quito">  <spring:theme code="country.60"/></option>
    <option value="EG" data-capital="Cairo">  <spring:theme code="country.61"/></option>
    <option value="SV" data-capital="San Salvador"> <spring:theme code="country.62"/></option>
    <option value="GQ" data-capital="Malabo"> <spring:theme code="country.63"/></option>
    <option value="ER" data-capital="Asmara"> <spring:theme code="country.64"/></option>
    <option value="EE" data-capital="Tallinn">  <spring:theme code="country.65"/></option>
    <option value="ET" data-capital="Addis Ababa">  <spring:theme code="country.66"/></option>
    <option value="FK" data-capital="Stanley">  <spring:theme code="country.67"/></option>
    <option value="FO" data-capital="Tórshavn"> <spring:theme code="country.68"/></option>
    <option value="FM" data-capital="Palikir">  <spring:theme code="country.69"/></option>
    <option value="FJ" data-capital="Suva"> <spring:theme code="country.70"/></option>
    <option value="FI" data-capital="Helsinki"> <spring:theme code="country.71"/></option>
    <option value="MK" data-capital="Skopje"> <spring:theme code="country.72"/></option>
    <option value="FR" data-capital="Paris">  <spring:theme code="country.73"/></option>
    <option value="PF" data-capital="Papeete">  <spring:theme code="country.74"/></option>
    <option value="GA" data-capital="Libreville"> <spring:theme code="country.75"/></option>
    <option value="GM" data-capital="Banjul"> <spring:theme code="country.76"/></option>
    <option value="GE" data-capital="Tbilisi">  <spring:theme code="country.77"/></option>
    <option value="DE" data-capital="Berlin"> <spring:theme code="country.78"/></option>
    <option value="GH" data-capital="Accra">  <spring:theme code="country.79"/></option>
    <option value="GI" data-capital="Gibraltar">  <spring:theme code="country.80"/></option>
    <option value="GR" data-capital="Athens"> <spring:theme code="country.81"/></option>
    <option value="GL" data-capital="Nuuk"> <spring:theme code="country.82"/></option>
    <option value="GD" data-capital="St. George's"> <spring:theme code="country.83"/></option>
    <option value="GU" data-capital="Hagåtña">  <spring:theme code="country.84"/></option>
    <option value="GT" data-capital="Guatemala City"> <spring:theme code="country.85"/></option>
    <option value="GG" data-capital="Saint Peter Port"> <spring:theme code="country.86"/></option>
    <option value="GN" data-capital="Conakry">  <spring:theme code="country.87"/></option>
    <option value="GW" data-capital="Bissau"> <spring:theme code="country.88"/></option>
    <option value="GY" data-capital="Georgetown"> <spring:theme code="country.89"/></option>
    <option value="HT" data-capital="Port-au-Prince"> <spring:theme code="country.90"/></option>
    <option value="VA" data-capital="Vatican City"> <spring:theme code="country.91"/></option>
    <option value="HN" data-capital="Tegucigalpa">  <spring:theme code="country.92"/></option>
    <option value="HK" data-capital="Hong Kong">  <spring:theme code="country.93"/></option>
    <option value="HU" data-capital="Budapest"> <spring:theme code="country.94"/></option>
    <option value="IS" data-capital="Reykjavik">  <spring:theme code="country.95"/></option>
    <option value="IN" data-capital="New Delhi">  <spring:theme code="country.96"/></option>
    <option value="ID" data-capital="Jakarta">  <spring:theme code="country.97"/></option>
    <option value="IR" data-capital="Tehran"> <spring:theme code="country.98"/></option>
    <option value="IQ" data-capital="Baghdad">  <spring:theme code="country.99"/></option>
    <option value="IE" data-capital="Dublin"> <spring:theme code="country.100"/></option>
    <option value="IM" data-capital="Douglas">  <spring:theme code="country.101"/></option>
    <option value="IL" data-capital="Jerusalem">  <spring:theme code="country.102"/></option>
    <option value="IT" data-capital="Rome"> <spring:theme code="country.103"/></option>
    <option value="JM" data-capital="Kingston"> <spring:theme code="country.104"/></option>
    <option value="JP" data-capital="Tokyo">  <spring:theme code="country.105"/></option>
    <option value="JE" data-capital="Saint Helier"> <spring:theme code="country.106"/></option>
    <option value="JO" data-capital="Amman">  <spring:theme code="country.107"/></option>
    <option value="KZ" data-capital="Astana"> <spring:theme code="country.108"/></option>
    <option value="KE" data-capital="Nairobi">  <spring:theme code="country.109"/></option>
    <option value="KI" data-capital="South Tarawa"> <spring:theme code="country.110"/></option>
    <option value="KG" data-capital="Bishkek">  <spring:theme code="country.111"/></option>
    <option value="LA" data-capital="Vientiane">  <spring:theme code="country.112"/></option>
    <option value="LV" data-capital="Riga"> <spring:theme code="country.113"/></option>
    <option value="LB" data-capital="Beirut"> <spring:theme code="country.114"/></option>
    <option value="LS" data-capital="Maseru"> <spring:theme code="country.115"/></option>
    <option value="LR" data-capital="Monrovia"> <spring:theme code="country.116"/></option>
    <option value="LY" data-capital="Tripoli">  <spring:theme code="country.117"/></option>
    <option value="LI" data-capital="Vaduz">  <spring:theme code="country.118"/></option>
    <option value="LT" data-capital="Vilnius">  <spring:theme code="country.119"/></option>
    <option value="LU" data-capital="Luxembourg City">  <spring:theme code="country.120"/></option>
    <option value="MO" data-capital="Macau">  <spring:theme code="country.121"/></option>
    <option value="MG" data-capital="Antananarivo"> <spring:theme code="country.122"/></option>
    <option value="MW" data-capital="Lilongwe"> <spring:theme code="country.123"/></option>
    <option value="MY" data-capital="Kuala Lumpur"> <spring:theme code="country.124"/></option>
    <option value="MV" data-capital="Malé"> <spring:theme code="country.125"/></option>
    <option value="ML" data-capital="Bamako"> <spring:theme code="country.126"/></option>
    <option value="MT" data-capital="Valletta"> <spring:theme code="country.127"/></option>
    <option value="MH" data-capital="Majuro"> <spring:theme code="country.128"/></option>
    <option value="MQ" data-capital="Fort-de-France"> <spring:theme code="country.129"/></option>
    <option value="MR" data-capital="Nouakchott"> <spring:theme code="country.130"/></option>
    <option value="MU" data-capital="Port Louis"> <spring:theme code="country.131"/></option>
    <option value="MX" data-capital="Mexico City">  <spring:theme code="country.132"/></option>
    <option value="MD" data-capital="Chișinău"> <spring:theme code="country.133"/></option>
    <option value="MC" data-capital="Monaco"> <spring:theme code="country.134"/></option>
    <option value="MN" data-capital="Ulaanbaatar">  <spring:theme code="country.135"/></option>
    <option value="ME" data-capital="Podgorica">  <spring:theme code="country.136"/></option>
    <option value="MS" data-capital="Little Bay, Brades, Plymouth"> <spring:theme code="country.137"/></option>
    <option value="MA" data-capital="Rabat">  <spring:theme code="country.138"/></option>
    <option value="MZ" data-capital="Maputo"> <spring:theme code="country.139"/></option>
    <option value="MM" data-capital="Naypyidaw">  <spring:theme code="country.140"/></option>
    <option value="NA" data-capital="Windhoek"> <spring:theme code="country.141"/></option>
    <option value="NR" data-capital="Yaren District"> <spring:theme code="country.142"/></option>
    <option value="NP" data-capital="Kathmandu">  <spring:theme code="country.143"/></option>
    <option value="NL" data-capital="Amsterdam">  <spring:theme code="country.144"/></option>
    <option value="NZ" data-capital="Wellington"> <spring:theme code="country.145"/></option>
    <option value="NI" data-capital="Managua">  <spring:theme code="country.146"/></option>
    <option value="NE" data-capital="Niamey"> <spring:theme code="country.147"/></option>
    <option value="NG" data-capital="Abuja">  <spring:theme code="country.148"/></option>
    <option value="NU" data-capital="Alofi">  <spring:theme code="country.149"/></option>
    <option value="NF" data-capital="Kingston"> <spring:theme code="country.150"/></option>
    <option value="KP" data-capital="Pyongyang">  <spring:theme code="country.151"/></option>
    <option value="MP" data-capital="Capitol Hill"> <spring:theme code="country.152"/></option>
    <option value="NO" data-capital="Oslo"> <spring:theme code="country.153"/></option>
    <option value="PK" data-capital="Islamabad">  <spring:theme code="country.154"/></option>
    <option value="PW" data-capital="Ngerulmud">  <spring:theme code="country.155"/></option>
    <option value="PA" data-capital="Panama City">  <spring:theme code="country.156"/></option>
    <option value="PG" data-capital="Port Moresby"> <spring:theme code="country.157"/></option>
    <option value="PY" data-capital="Asunción"> <spring:theme code="country.158"/></option>
    <option value="PE" data-capital="Lima"> <spring:theme code="country.159"/></option>
    <option value="PH" data-capital="Manila"> <spring:theme code="country.160"/></option>
    <option value="PN" data-capital="Adamstown">  <spring:theme code="country.161"/></option>
    <option value="PL" data-capital="Warsaw"> <spring:theme code="country.162"/></option>
    <option value="PT" data-capital="Lisbon"> <spring:theme code="country.163"/></option>
    <option value="PR" data-capital="San Juan"> <spring:theme code="country.164"/></option>
    <option value="CG" data-capital="Brazzaville">  <spring:theme code="country.165"/></option>
    <option value="RO" data-capital="Bucharest">  <spring:theme code="country.166"/></option>
    <option value="RU" data-capital="Moscow"> <spring:theme code="country.167"/></option>
    <option value="RW" data-capital="Kigali"> <spring:theme code="country.168"/></option>
    <option value="BL" data-capital="Gustavia"> <spring:theme code="country.169"/></option>
    <option value="KN" data-capital="Basseterre"> <spring:theme code="country.170"/></option>
    <option value="LC" data-capital="Castries"> <spring:theme code="country.171"/></option>
    <option value="VC" data-capital="Kingstown">  <spring:theme code="country.172"/></option>
    <option value="WS" data-capital="Apia"> <spring:theme code="country.173"/></option>
    <option value="SM" data-capital="San Marino"> <spring:theme code="country.174"/></option>
    <option value="ST" data-capital="São Tomé"> <spring:theme code="country.175"/></option>
    <option value="SA" data-capital="Riyadh"> <spring:theme code="country.176"/></option>
    <option value="SN" data-capital="Dakar">  <spring:theme code="country.177"/></option>
    <option value="RS" data-capital="Belgrade"> <spring:theme code="country.178"/></option>
    <option value="SC" data-capital="Victoria"> <spring:theme code="country.179"/></option>
    <option value="SL" data-capital="Freetown"> <spring:theme code="country.180"/></option>
    <option value="SG" data-capital="Singapore">  <spring:theme code="country.181"/></option>
    <option value="SX" data-capital="Philipsburg">  <spring:theme code="country.182"/></option>
    <option value="SK" data-capital="Bratislava"> <spring:theme code="country.183"/></option>
    <option value="SI" data-capital="Ljubljana">  <spring:theme code="country.184"/></option>
    <option value="SB" data-capital="Honiara">  <spring:theme code="country.185"/></option>
    <option value="SO" data-capital="Mogadishu">  <spring:theme code="country.186"/></option>
    <option value="ZA" data-capital="Pretoria"> <spring:theme code="country.187"/></option>
    <option value="KR" data-capital="Seoul">  <spring:theme code="country.188"/></option>
    <option value="SS" data-capital="Juba"> <spring:theme code="country.189"/></option>
    <option value="ES" data-capital="Madrid"> <spring:theme code="country.190"/></option>
    <option value="LK" data-capital="Sri Jayawardenepura Kotte, Colombo"> <spring:theme code="country.191"/></option>
    <option value="PS" data-capital="Ramallah"> <spring:theme code="country.192"/></option>
    <option value="SD" data-capital="Khartoum"> <spring:theme code="country.193"/></option>
    <option value="SR" data-capital="Paramaribo"> <spring:theme code="country.194"/></option>
    <option value="SZ" data-capital="Lobamba, Mbabane"> <spring:theme code="country.195"/></option>
    <option value="SE" data-capital="Stockholm">  <spring:theme code="country.196"/></option>
    <option value="CH" data-capital="Bern"> <spring:theme code="country.197"/></option>
    <option value="SY" data-capital="Damascus"> <spring:theme code="country.198"/></option>
    <option value="TW" data-capital="Taipei"> <spring:theme code="country.199"/></option>
    <option value="TJ" data-capital="Dushanbe"> <spring:theme code="country.200"/></option>
    <option value="TZ" data-capital="Dodoma"> <spring:theme code="country.201"/></option>
    <option value="TH" data-capital="Bangkok">  <spring:theme code="country.202"/></option>
    <option value="TL" data-capital="Dili"> <spring:theme code="country.203"/></option>
    <option value="TG" data-capital="Lomé"> <spring:theme code="country.204"/></option>
    <option value="TK" data-capital="Nukunonu, Atafu,Tokelau">  <spring:theme code="country.205"/></option>
    <option value="TO" data-capital="Nukuʻalofa"> <spring:theme code="country.206"/></option>
    <option value="TT" data-capital="Port of Spain">  <spring:theme code="country.207"/></option>
    <option value="TN" data-capital="Tunis">  <spring:theme code="country.208"/></option>
    <option value="TR" data-capital="Ankara"> <spring:theme code="country.209"/></option>
    <option value="TM" data-capital="Ashgabat"> <spring:theme code="country.210"/></option>
    <option value="TC" data-capital="Cockburn Town">  <spring:theme code="country.211"/></option>
    <option value="TV" data-capital="Funafuti"> <spring:theme code="country.212"/></option>
    <option value="UG" data-capital="Kampala">  <spring:theme code="country.213"/></option>
    <option value="UA" data-capital="Kiev"> <spring:theme code="country.214"/></option>
    <option value="GB" data-capital="London"> <spring:theme code="country.215"/></option>
    <option value="US" data-capital="Washington, D.C."> <spring:theme code="country.216"/></option>
    <option value="UY" data-capital="Montevideo"> <spring:theme code="country.217"/></option>
    <option value="UZ" data-capital="Tashkent"> <spring:theme code="country.218"/></option>
    <option value="VU" data-capital="Port Vila">  <spring:theme code="country.219"/></option>
    <option value="VE" data-capital="Caracas">  <spring:theme code="country.220"/></option>
    <option value="VN" data-capital="Hanoi">  <spring:theme code="country.221"/></option>
    <option value="VG" data-capital="Road Town">  <spring:theme code="country.222"/></option>
    <option value="VI" data-capital="Charlotte Amalie"> <spring:theme code="country.223"/></option>
    <option value="EH" data-capital="Laayoune"> <spring:theme code="country.224"/></option>
    <option value="YE" data-capital="Sana'a"> <spring:theme code="country.225"/></option>
    <option value="ZM" data-capital="Lusaka"> <spring:theme code="country.226"/></option>
    <option value="ZW" data-capital="Harare"> <spring:theme code="country.227"/></option>
  

</select>


    
  
  
  
      </div>
          </div>
          <div class="col-md-6">
              <div class="investorVisaHaveType investorVisaTypeItem">
         <label class="visatypeLabel" for="visaCountry"><spring:theme code="visa.choose.others"/></label>
        <select disabled ="disabled" class="visatypeLabelSelect counrtyVisaType" id="counrtyVisaTypeAr" onchange="counrtyVisaTypeChangeAr()" class="form-control">
          <option selected disabled><spring:theme code="visa.choose.label"/></option>
         <option value="1"><spring:theme code="tourist.visa.holder"/></option>
  <option value="2"> <spring:theme code="gcc.resident"/></option>
  <option value="3"><spring:theme code="moi.license"/></option>
  <option value="4"><spring:theme code="nta"/></option>
  
  
  
        </select>
  
       
  
      </div>
          </div>
        </div>
     
     
  
        <div class="investorVisaTypeItem investorVisaStatus " style="display: none;">
          <div class="eligibleStatusInfo">
            
            <div class="visaType1" style="display:none;">
          <h3 class="instendVisaStatus"><spring:theme code="instend.visa.txt"/></h3>
               <a href="https://visa.mofa.gov.sa/" target="_blank" class="visaTypeApplyBtn"><span><spring:theme code="visa.apply.link.button"/></span></a>
            </div>
            <div class="visaType2" style="display:none;">
              <h3 class="onlineVisaStatus" ><spring:theme code="online.visa.txt"/></h3>
               <a href="https://visa.mofa.gov.sa/" target="_blank" class="visaTypeApplyBtn"> <span><spring:theme code="visa.apply.link.button"/></span></a>
            </div>
            <div class="visaType0" style="display:none;">
               <h2 class=" embassyStatus" > السفارة/القنصلية </h2>
           <a href="#invVisaApplyEnquiry" class="invVisaThemeBtn">تواصل معنا</a>
            </div>
    
         
         
          </div>
    
         
        </div>
  
      
  
  
      </div>
  
  </div>
  
    </div>
  




    
  
    
  
  </section>
  
<!-- Arabic Type End-->


    
                            </c:if>