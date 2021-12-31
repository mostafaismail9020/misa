<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>







<%-- TODO: SAH-552 --%>






<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Classification Upgrade</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Dashboard</a>
            <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand02">
                <div>Show Service History<span>&#x27f6;</span></div>
                <div class="hidden">Hide Service History<span class="iconElement iconElement_closeBack"><icon:close/></span></div>
            </button>
        </div>
    </div>
</div>



<div class="mainSection mainSection_dark mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="expandableContent" id="expand02">

          <div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                            <span class="iconElement iconElement_history"><icon:history/></span>
                            History</div>
                            <div class="searchInputBox searchInputBox_slim searchInputBox_spaceTop">
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
                        <div class="contentModule-section contentModule-section_noDivider">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline">
                                    <icon:info/>
                                    Info
                                </div>
                                <button type="submit" class="btn btn_slim">
                                    Upgrade
                                </button>
                            </div>
                            <div class="classificationUpgrade-status classificationUpgrade-status_margin">
                                Current Classification Status: <span class="classificationUpgrade-status-value">C</span>
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
            <h1 class="mainSection-headline">Classification Upgrade</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Dashboard</a>
        </div>
    </div>
</div>




<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">

        <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider">
                    <div class="contentModule-actions contentModule-actions_spaceBetween">
                        <div class="contentModule-headline">
                            <icon:info/>
                            Info
                        </div>
                    </div>
                    <div class="classificationUpgrade-status">
                        Current Classification Status: <span class="classificationUpgrade-status-value">C</span>
                    </div>

                    <div class="row">
                      <div class="col-sm-6">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select name="" class="js-select2-oneColumn form-control">
                                    <option></option>
                                    <option value="1">A</option>
                                    <option value="2">A+</option>
                                    <option value="3">B</option>
                                    <option value="4">C</option>
                                    <option value="5">I</option>
                                </select>
                                <label class="control-label" for="">UPgrade</label>
                            </div>
                        </div>
                      </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider">
                    <div class="contentModule-actions contentModule-actions_spaceBetween">
                        <div class="contentModule-headline">
                            <icon:documents/>
                           Support Documents
                       </div>
                    </div>


                   <div class="classificationUpgrade-documents">
                        <div class="row">

                           <div class="col-md-6">
                               <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                   <div class="form-group">
                                       <input id="" name="" class="form-control js-inputFile" value="" type="file">
                                       <input id="" name="" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                       <label class="control-label " for="">*Application letter authentificated by COC</label>
                                       <div class="form-icon form-icon_browse">
                                           <icon:upload/>
                                       </div>
                                      <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                      </div>
                                      <div class="formInputFile-append">
                                        <span class="formInputFile-text formInputFile-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
                                        </span>
                                      </div>
                                   </div>
                               </div>
                           </div>


                           <div class="col-md-6">
                               <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                   <div class="form-group">
                                       <input id="" name="" class="form-control js-inputFile" value="" type="file">
                                       <input id="" name="" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                       <label class="control-label " for="">* Printout from Saudi Customs for Exported</label>
                                       <div class="form-icon form-icon_browse">
                                           <icon:upload/>
                                       </div>
                                      <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                      </div>
                                      <div class="formInputFile-append">
                                        <span class="formInputFile-text formInputFile-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
                                        </span>
                                      </div>
                                   </div>
                               </div>
                           </div>


                           <div class="col-md-6">
                               <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                   <div class="form-group">
                                       <input id="" name="" class="form-control js-inputFile" value="" type="file">
                                       <input id="" name="" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                       <label class="control-label " for="">*GOCI Certificate</label>
                                       <div class="form-icon form-icon_browse">
                                           <icon:upload/>
                                       </div>
                                      <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                      </div>
                                      <div class="formInputFile-append">
                                        <span class="formInputFile-text formInputFile-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
                                        </span>
                                      </div>
                                   </div>
                               </div>
                           </div>


                           <div class="col-md-6">
                               <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                   <div class="form-group">
                                       <input id="" name="" class="form-control js-inputFile" value="" type="file">
                                       <input id="" name="" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                       <label class="control-label " for="">* Current Classification Letter from MOMRA</label>
                                       <div class="form-icon form-icon_browse">
                                           <icon:upload/>
                                       </div>
                                      <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                      </div>
                                      <div class="formInputFile-append">
                                        <span class="formInputFile-text formInputFile-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
                                        </span>
                                      </div>
                                   </div>
                               </div>
                           </div>


                           <div class="col-md-6">
                               <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                   <div class="form-group">
                                       <input id="" name="" class="form-control js-inputFile" value="" type="file">
                                       <input id="" name="" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                       <label class="control-label " for="">*Nitaqat Certificate</label>
                                       <div class="form-icon form-icon_browse">
                                           <icon:upload/>
                                       </div>
                                      <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                      </div>
                                      <div class="formInputFile-append">
                                        <span class="formInputFile-text formInputFile-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
                                        </span>
                                      </div>
                                   </div>
                               </div>
                           </div>


                           <div class="col-md-6">
                               <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                   <div class="form-group">
                                       <input id="" name="" class="form-control js-inputFile" value="" type="file">
                                       <input id="" name="" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                       <label class="control-label " for="">* Patent Registered in KSA</label>
                                       <div class="form-icon form-icon_browse">
                                           <icon:upload/>
                                       </div>
                                      <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                      </div>
                                      <div class="formInputFile-append">
                                        <span class="formInputFile-text formInputFile-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
                                        </span>
                                      </div>
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
            <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#requestSubmitted">
                Submit
            </button>
        </div>

        <div class="modal fade" id="requestSubmitted" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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


    </div>
</div>
