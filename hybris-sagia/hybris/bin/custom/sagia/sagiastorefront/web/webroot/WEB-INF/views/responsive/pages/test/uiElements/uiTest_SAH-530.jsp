<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<%-- TODO: SAH-530 --%>
<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Final Exit Visa Application</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Service Details</a>
            <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div>Show Service History<span>&#x27f6;</span></div>
                <div class="hidden">Hide Service History<span>&times;</span></div>
            </button>
        </div>
    </div>
</div>



<div class="mainSection mainSection_dark mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="expandableContent" id="expand01">
           
            <div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                            <span class="iconElement iconElement_history"><icon:history/></span>                            
                            History</div>
                            <div class="searchInputBox searchInputBox_slim">
                                <input class="searchInputBox-input" type="text" placeholder="Search"/>
                            </div>
                            <ul class="historyList">
                                <li class="historyList-item historyList-item_current">
                                    <a href="#" class="historyList-link">
                                        <div class="historyList-id">500009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">06 Nov 2017</div>
                                            <div class="historyList-status historyList-status_process">In Process</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="historyList-item">
                                    <a href="" class="historyList-link">
                                        <div class="historyList-id">200009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">02 Nov 2017</div>
                                            <div class="historyList-status historyList-status_accepted">Accepted</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="historyList-item">
                                    <a href="" class="historyList-link">
                                        <div class="historyList-id">100009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">01 Nov 2017</div>
                                            <div class="historyList-status historyList-status_rejected">Rejected</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="historyList-item">
                                    <a href="" class="historyList-link">
                                        <div class="historyList-id">100009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">01 Nov 2017</div>
                                            <div class="historyList-status historyList-status_rejected">Rejected</div>
                                        </div>
                                    </a>
                                </li>   
                            </ul>
                            <div class="emptyIndicator">
                                No History List
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            
            
            <div class="expandableContent-main">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-headline">
                                    <icon:info/>
                                    Applicants List: 9000056
                                </div>
                                <button type="submit" class="btn">
                                    Request
                                </button>
                            </div>

                            <div class="tableModule tableModule_slim">
                              <table class="tableModule-table">
                                <thead class="tableModule-head">
                                  <tr>
                                    <th>Applicants Name</th>
                                    <th>Iqama Number</th>
                                    <th>Iqama Expiry Date</th>
                                    <th>Nationality</th>
                                    <th>Nationality Note</th>
                                    <th>Profession</th>
                                    <th>Sagia Investor Number</th>
                                  </tr>
                                </thead>
                                <tbody class="tableModule-body">
                                  <tr>
                                    <td>Robin</td>
                                    <td>1235678</td>
                                    <td>Raj. 28 , 1437</td>
                                    <td>American</td>
                                    <td>Indian Origin</td>
                                    <td>Investor</td>
                                    <td>700288</td>
                                  </tr>
                                  <tr>
                                    <td>Theodore</td>
                                    <td>1235678</td>
                                    <td>Raj. 22 , 1421</td>
                                    <td>Serbian,<br>Montenegrin</td>
                                    <td>-</td>
                                    <td>Investor</td>
                                    <td>1100922</td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>

                        </div>   
                    </div>
                </div>  


                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">


                          <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-headline">
                                    <icon:contact-details/>
                                    Contact Details
                                </div>
                            </div>


                            <div class="row">
                              <div class="col-md-6">
                                <dl class="dlList dlList_separated dlList_marginBottom">
                                  <dt>Region</dt>
                                  <dd>SAGIA Madina Office</dd>
                                  <dt>Contact E-mail Adress</dt>
                                  <dd>a@b.com</dd>
                                </dl>
                              </div>
                              <div class="col-md-6">
                                <dl class="dlList dlList_separated dlList_lineThrough">
                                  <dt>Contact Phone Number</dt>
                                  <dd>9661212121</dd>
                                  <dt class="dlList-empty">&nbsp;</dt>
                                  <dd class="dlList-empty">&nbsp;</dd>
                                </dl>
                              </div>
                            </div>


                            <div class="contentModule-separator"></div>


                            <div class="contentModule-headline">
                                <icon:enquiry3/>
                                Reason for the Application
                            </div>


                            <div class="formTextArea">
                                <div class="form-group">
                                    <textarea id="inputNewMessage" class="form-control" placeholder="."></textarea>
                                    <label class="control-label" for="">
                                        * Message
                                    </label>
                                </div>
                            </div>




                            <div class="contentModule-separator"></div>


                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-headline">
                                    <icon:documents/>
                                    Attachments
                                </div>
                            </div>







                            <ul class="downloadList downloadList_maxHeight">
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Final Exit Application Form
                                    </div>
                                    <div class="downloadList-actions">
                                        <a href="#" class="link link_nowrap">
                                            <span class="iconElement iconElement_cloud"><icon:download/></span>
                                            Download
                                        </a>
                                    </div>
                                </li>
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Approval letter from Company
                                    </div>
                                    <div class="downloadList-actions">
                                        <a href="#" class="link link_nowrap">
                                            <span class="iconElement iconElement_cloud"><icon:download/></span>
                                            Download
                                        </a>
                                    </div>
                                </li>   
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Copy of Iqama
                                    </div>
                                    <div class="downloadList-actions">
                                        <a href="#" class="link link_nowrap">
                                            <span class="iconElement iconElement_cloud"><icon:download/></span>
                                            Download
                                        </a>
                                    </div>
                                </li>  
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Copy of Passport
                                    </div>
                                    <div class="downloadList-actions">
                                        <a href="#" class="link link_nowrap">
                                            <span class="iconElement iconElement_cloud"><icon:download/></span>
                                            Download
                                        </a>
                                    </div>
                                </li>     
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Copy of Passport
                                    </div>
                                    <div class="downloadList-actions">
                                        <a href="#" class="link link_nowrap">
                                            <span class="iconElement iconElement_cloud"><icon:download/></span>
                                            Download
                                        </a>
                                    </div>
                                </li>         
                            </ul>




















                            <div class="contentModule-separator"></div>


                            <div class="contentModule-headline">
                                <%-- <icon:contact-details/> --%>
                                Final Exit Visa Application Comments
                            </div>


                            <div class="formTextArea">
                                <div class="form-group">
                                    <textarea id="inputNewMessage" class="form-control" placeholder="."></textarea>
                                    <label class="control-label" for="">
                                        * Message
                                    </label>
                                </div>
                            </div>

                            
                        </div>   
                    </div>
                </div> 

            </div>
        </div>
    </div>
</div>

















































<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Final Exit Visa Application</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Service Details</a>
        </div>
    </div>
</div>





<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                        <div class="contentModule-headline">
                            <icon:person/>
                            Applicants List: 9000056
                        </div>
                        <button type="submit" class="btn">
                            Request
                        </button>
                    </div>
        
                    <div class="tableModule tableModule_slim">
                      <table class="tableModule-table">
                        <thead class="tableModule-head">
                          <tr>
                            <th style="width: 25px;"></th>
                            <th>Applicants Name</th>
                            <th>Iqama Number</th>
                            <th>Iqama Expiry Date</th>
                            <th>Nationality</th>
                            <th>Nationality Note</th>
                            <th>Profession</th>
                            <th>Sagia Investor Number</th>
                          </tr>
                        </thead>
                        <tbody class="tableModule-body">
                          <tr>
                            <td>
                                <div class="formRadioButton">
                                    <div class="form-group">
                                        <div class="form-item">
                                            <input id="hasMomra1" name="radioButtonName1" class="form-control" value="true" checked="checked" type="radio">
                                            <label for="hasMomra1" class="control-label"><span></span></label>
                                        </div>             
                                    </div>
                                </div>
                            </td>
                            <td>Robin</td>
                            <td>1235678</td>
                            <td>Raj. 28 , 1437</td>
                            <td>American</td>
                            <td>Indian Origin</td>
                            <td>Investor</td>
                            <td>700288</td>
                          </tr>
                          <tr>
                            <td>
                                <div class="formRadioButton">
                                    <div class="form-group">
                                        <div class="form-item">
                                            <input id="hasMomra2" name="radioButtonName1" class="form-control" value="true" checked="checked" type="radio">
                                            <label for="hasMomra2" class="control-label"><span></span></label>
                                        </div>             
                                    </div>
                                </div>
                            </td>
                            <td>Theodore</td>
                            <td>1235678</td>
                            <td>Raj. 22 , 1421</td>
                            <td>Serbian,<br>Montenegrin</td>
                            <td>-</td>
                            <td>Investor</td>
                            <td>1100922</td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
        
                </div>   
            </div>
        </div>  
        
        
        <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
        
        
                  <div class="contentModule-headline">
                      <icon:info/>
                      Applicants Details
                  </div>
        
        
                    <div class="row">
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                    Applicants Name
                                    </label>                
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Iqama Number
                                    </label>                
                                </div>
                            </div>
                        </div>
        
                         <div class="col-md-6">
                            <div class="formInputBox formInputBox_group ">
                                <div class="form-group">
                                    <input id="" class="form-control js-form-control_date flatpickr-input" placeholder="." value="" readonly="readonly" type="text">
                                    <label class="control-label" for="">
                                        Iqama Expiry date
                                    </label>
                                    <div class="formInputBox-append">
                                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                                    </div>              
                                </div>
                            </div>
                        </div>
        
                        <div class="col-md-6">
                            <div class="formSelectBox">
                                <div class="form-group">
                                    <select name="" class="js-select2 form-control select2-hidden-accessible" tabindex="-1" aria-hidden="true">
                                        <option></option>
                                        <option value="1"></option>
                                    </select>
                                    <label class="control-label" for="">Nationality</label>
                                </div>
                            </div>
                        </div>
        
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Enter Country if Passport is different from Nationality
                                    </label>                
                                </div>
                            </div>
                        </div>
        
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Profession
                                    </label>                
                                </div>
                            </div>
                        </div>
        
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        SAGIA Investor Number
                                    </label>                
                                </div>
                            </div>
                        </div>
                      
                    </div>
        
        
        
                </div>
            </div>
        </div>
        
        
        <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
        
        
        
                    <div class="contentModule-headline">
                      <icon:contact-details/>
                      Contact Details
                  </div>
        
        
                    <div class="row">
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                    Region
                                    </label>                
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Contact Phone Number
                                    </label>                
                                </div>
                            </div>
                        </div>
        
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Contact E-mail Adress
                                    </label>                
                                </div>
                            </div>
                        </div>
                      
                    </div>
        
        
        
                    <div class="contentModule-separator"></div>
        
        
        
                    <div class="contentModule-headline">
                        <icon:enquiry3/>
                        Reason for the Application
                    </div>
        
        
                    <div class="formTextArea">
                        <div class="form-group">
                            <textarea id="inputNewMessage" class="form-control" placeholder="."></textarea>
                            <label class="control-label" for="">
                                * Message
                            </label>
                        </div>
                    </div>
        
        
        
        
                    <div class="contentModule-separator"></div>
        
        
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                        <div class="contentModule-headline">
                            <icon:documents/>
                            Attachments
                        </div>
                    </div>
        
        
        
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="formInputFile">
                                <div class="form-group">
                                    <input id="file08" name="file08" class="form-control js-inputFile" value="" type="file">
                                    <input id="text08" name="text08" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                    <label class="control-label " for="">Document</label>
                                    <div class="form-icon form-icon_browse">
                                        <icon:upload/>
                                    </div>
                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                    </div>
                                </div>
                            </div>
                            <div class="formInputFile">
                                <div class="form-group">
                                    <input id="file08" name="file08" class="form-control js-inputFile" value="" type="file">
                                    <input id="text08" name="text08" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                    <label class="control-label " for="">Document</label>
                                    <div class="form-icon form-icon_browse">
                                        <icon:upload/>
                                    </div>
                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                    </div>
                                </div>
                            </div>
                            <div class="formInputFile">
                                <div class="form-group">
                                    <input id="file08" name="file08" class="form-control js-inputFile" value="" type="file">
                                    <input id="text08" name="text08" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                    <label class="control-label " for="">Document</label>
                                    <div class="form-icon form-icon_browse">
                                        <icon:upload/>
                                    </div>
                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                    </div>
                                </div>
                            </div>
                            <div class="formInputFile">
                                <div class="form-group">
                                    <input id="file08" name="file08" class="form-control js-inputFile" value="" type="file">
                                    <input id="text08" name="text08" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                    <label class="control-label " for="">Document</label>
                                    <div class="form-icon form-icon_browse">
                                        <icon:upload/>
                                    </div>
                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="undertakingLetter">
                                <div class="undertakingLetter-text">
                                   <p>If you are not able to provide any of the aforedmentioned documents please download, sign and upload the <a href="">Undertaking Letter</a>. </p>
                                   <p>You can upload the required documents within the next 14 days.  </p> 
                                </div>
        
                                <button id="downloadUndertakingLetterButton" class="btn btn_outline btn_round">Download <icon:download/></button>
        
                            </div>
                        </div>
                    </div>
        
        
                    <div class="contentModule-separator contentModule-separator_noMarginBottom"></div>
        
                    <div class="formCheckBox">
                        <div class="form-group">
                        
                            <div class="form-item">
                                <input class="form-control" placeholder="." type="checkbox">
                                <label class="control-label " for="">
                                    <span><icon:check/></span> I agree to terms & conditions.
                                </label>
                            </div>  
                        </div>
                    </div>
           
        
                    
                </div>   
            </div>
        </div> 
        
        
      
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
            <button type="button" class="btn btn-secondary">
                Cancel
            </button>
            <button type="submit" class="btn btn-primary"  data-toggle="modal" data-target="#requestSubmitted3">
                Submit
            </button>
        </div>
    </div>
</div>





<div class="modal fade" id="requestSubmitted3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">Request submitted!</div>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:modal02/>
                </div>
                <div class="modal-description">
                    Thank you! We will check your details and approve your request soon.
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim" data-dismiss="modal">go to my dashboard</button>
            </div>
            <div class="modal-secondaryContent">
                <div class="modal-headline">How was your experience?</div>
                <div class="ratingModule">
                    <div class="ratingModule-star active"><icon:rating-star/></div>
                    <div class="ratingModule-star active"><icon:rating-star/></div>
                    <div class="ratingModule-star active"><icon:rating-star/></div>
                    <div class="ratingModule-star active"><icon:rating-star/></div>
                    <div class="ratingModule-star"><icon:rating-star-empty/></div>
                </div>
            </div>          
        </div>
    </div>
</div>

<script type="text/javascript">
$().ready(function() {
    function download(filePath) {
        var element = document.createElement('a');
        element.setAttribute('href', filePath);
        element.setAttribute('download', "undertakingLetterSample");
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
    }

	 $('#downloadUndertakingLetterButton').click(function() {
		 $.ajax({
             type: 'GET',
             url: ACC.config.encodedContextPath + "/undertakingLetter/",
             dataType: 'json',
             success: function(res) {
            	 var filePath =res.undertakingLetterUrl;
            	 download(filePath);
            }
	 });
})
});
</script>