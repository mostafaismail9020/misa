<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>







<%-- TODO: SAH-539 --%>






<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Warning Letter Extension</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
	<div class="container">
		<div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
			<a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Dashboard</a>
            <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div>Show Service History<span>&#x27f6;</span></div>
                <div class="hidden">Hide Service History<span>&#x27f6;</span></div>
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
                            <%--
                            <div class="emptyIndicator">
                                No History List
                            </div>
                            --%>
                        </div>
                    </div>
                </div>
            </div>
            
            
            
            <div class="expandableContent-main">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline">
                                    <icon:info/>
                                    Info: 35000000180 
                                </div>
                                <button type="submit" class="btn btn_slim">
                                    Create
                                </button>
                                <div class="statusIndicator statusIndicator_accepted">
                                    Status: <span>Completed</span>
                                </div>
                            </div>


                            <div class="row">
                              <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                  <dt>Requested No of Extensions days</dt>
                                  <dd>46</dd>
                                  <dt>Extended Warning Letter Date</dt>
                                  <dd>28 Aug 2017</dd>
                                </dl>
                              </div>
                              <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                  <dt>Approved NO of Extended days</dt>
                                  <dd>30</dd>
                                  <dt>Extended Warning Letter End Date</dt>
                                  <dd>22 Oct 2017</dd>
                                </dl>
                              </div>
                            </div>
                        </div>
                        
						<div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
							<div class="contentModule-headline contentModule-headline_small">Comments</div>
							<div class="commentModule">
								<div class="commentModule-window">
									<ul class="messageList">
										<li class="messageList-item">
											<div class="messageList-img">
												<!-- img src="https://dummyimage.com/80x80/DDDDDD/fff" alt="" -->
												<span class="iconElement iconElement_expertProfile_white">
													<icon:expertProfile/>
												</span>
											</div>
											<div class="messageList-content">
												<h2 class="messageList-name">Omar Khan (SAGIA advisor)</h2>
												<h3 class="messageList-date">05 Jan 2018</h3>
												<div class="messageList-message">
													<p>Esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
													<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident.</p>
												</div>
											</div>
										</li>
									</ul>                
								</div>
							</div>
						</div>                           
                                 
                    </div>
                </div>  

                 <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">

                             <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-headline contentModule-headline_bordered">
                                    <icon:documents/>
                                    Support Documents
                                </div>
                            </div>




                            <ul class="downloadList downloadList_maxHeight">
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Document
                                    </div>
                                    <div class="downloadList-actions">
                                        <a href="#" class="link link_nowrap">
                                            <span class="iconElement iconElement_cloud"><icon:download/></span>
                                            Download
                                        </a>
                                    </div>
                                </li>        
                            </ul>

                            
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
            <h1 class="mainSection-headline">Warning Letter Extension</h1>
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
                <div class="contentModule-section">
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                        <div class="contentModule-headline">
                            <icon:info/>
                            Info
                        </div>
                    </div>


                    <div class="row">
                        <div class="col">


                            <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                <div class="form-group">
                                    <div class="formRadioButton-label">Warning Letter No:</div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="" checked="checked">
                                        <label for="" class="control-label">
                                            <span></span> 52464646</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="">
                                        <label for="" class="control-label">
                                            <span></span> 52464648</label>
                                    </div>


                                </div>
                            </div>                                   

                        </div>
                    </div>                            

                </div>
                <div class="contentModule-section">   

                    <div class="row">
                        <div class="col-sm-3">
                            <dl class="dlList dlList_separated">
                                <dt>Warning Letter Ending Date</dt>
                                <dd>4.4.2018</dd>
                            </dl>
                        </div>
                        <div class="col-sm-6">
                            <div class="formRangeSlider js-formRangeSlider">
                                <div class="form-group">
                                    <input type="range" min="0" max="100" value="0" step="1">
                                    <div class="formRangeSlider-label">Warning Period Extension</div>
                                    <div class="formRangeSlider-value"><span>0</span> days</div>
                                </div>	
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <dl class="dlList dlList_separated">
                                <dt>Warning Letter Extended Date</dt>
                                <dd>9.4.2018</dd>
                            </dl>
                        </div>
                    </div>



                </div>





            </div>
        </div>  


        <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">


                    <div class="contentModule-headline">
                        <icon:enquiry2/>
                        Reason for extentions
                    </div>


                    <div class="formTextArea">
                        <div class="form-group">
                            <textarea id="inputNewMessage" class="form-control" placeholder="."></textarea>
                            <label class="control-label" for="">
                                Please specify your reason for the warning letter extension
                            </label>
                        </div>
                    </div>
                </div>   
            </div>
        </div> 

         <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">

                     <div class="contentModule-headline">
                         <icon:documents/>
                         Support Documents
                     </div>


                    <div class="row">
                        <div class="col-sm-6">
                            <div class="formInputFile">
                               <div class="form-group">
                                   <input id="" name="" class="form-control js-inputFile" value="" type="file">
                                   <input id="" name="" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
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
                    </div>







                </div>   
            </div>
        </div> 


        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
            <button type="button" class="btn btn-secondary">
                Cancel
            </button>
            <div class="formCheckBox formCheckBox_belowPanel">
                <div class="form-group">
                    <div class="form-item">
                        <input id="SAH539checkbox01" name="SAH539checkbox01name" class="form-control" placeholder="." type="checkbox" value="entity name">
                        <label class="control-label " for="SAH539checkbox01">
                            <span>
                                <icon:check/>
                            </span>I agree to <a href="#" class="link">terms &amp; conditions.</a>
                        </label>
                    </div>
                </div>
            </div>            
            <button type="submit" class="btn" >
                Submit
            </button>
        </div>


    </div>
</div>










