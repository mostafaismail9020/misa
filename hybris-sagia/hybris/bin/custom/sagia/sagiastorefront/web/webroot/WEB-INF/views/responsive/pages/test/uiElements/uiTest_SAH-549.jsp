<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>







<%-- TODO: SAH-549 --%>










<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Financial Statement</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Service Details</a>
            <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand02">
                <div>Show Service History<span>&#x27f6;</span></div>
                <div class="hidden">Hide Service History<span>&times;</span></div>
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
              

                <div class="js-panelTabs panelTabs panelTabs_transparent panelTabs_iconsAndLabel panelTabs_separated panelTabs_tip_right  panelTabs_noPaddingTop financialStatement">
                    <div class="panelTabs-head">
                        <icon:financialStatement-income/>
                        <span class="panelTabs-label">Income Statement</span>
                    </div>
                    <div class="panelTabs-body panelModule panelModule_halfRadius">


                        
                        

                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                    <div class="contentModule-headline">
                                       <icon:generalManager/>
                                       Financial Data
                                    </div>
                                </div>


                                <div class="row">
                                  <div class="col-md-6">
                                    <dl class="dlList dlList_separated dlList_marginBottom">
                                      <dt>Revenue</dt>
                                      <dd>-</dd>
                                      <dt>GROSS INCOME</dt>
                                      <dd>-</dd>
                                      <dt>INCOME FROM MAIN OPERATIONS</dt>
                                      <dd>-</dd>
                                      <dt>Net Income Before TAX & Zakat</dt>
                                      <dd>-</dd>
                                      <dt>tax</dt>
                                      <dd>-</dd>
                                    </dl>
                                  </div>
                                  <div class="col-md-6">
                                    <dl class="dlList dlList_separated dlList_lineThrough">
                                      <dt>COST OF SALES</dt>
                                      <dd>-</dd>
                                      <dt>TOTAL OPERATING EXPENSES</dt>
                                      <dd>-</dd>
                                      <dt>TOTAL OTHER INCOMES (EXPENSES)</dt>
                                      <dd>-</dd>
                                      <dt>Zakat</dt>
                                      <dd>-</dd>
                                      <dt>NET INCOME</dt>
                                      <dd>-</dd>
                                    </dl>
                                  </div>
                                </div>

                            </div>
                        </div>

                    

                    </div>
                    <div class="panelTabs-head">
                        <icon:financialStatement-balance/>
                        <span class="panelTabs-label">Balance Sheet</span>
                    </div>
                    <div class="panelTabs-body panelModule panelModule_halfRadius">
                       

                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                    <div class="contentModule-headline">
                                       <icon:generalManager/>
                                       Financial Data
                                    </div>
                                </div>


                                <div class="row">
                                  <div class="col-md-6">

                                    <div class="contentModule-subheadline">
                                        Assets
                                    </div>
                                    <dl class="dlList dlList_separated dlList_marginBottom">
                                        <dt>total current assets</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt>total non-current assets</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt>other assets</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt><strong>Total assets:</strong></dt>
                                        <dd><strong>0,00 SAR</strong></dd>
                                        <dt>tax</dt>
                                        <dd>-</dd>
                                    </dl>
                                  </div>
                                  <div class="col-md-6">
                                     <div class="contentModule-subheadline">
                                        Liabilities
                                    </div>
                                    <dl class="dlList dlList_separated dlList_lineThrough">
                                        <dt>total current liabilities</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt>total non-current liabilities</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt>other liabilities</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt><strong>Total liabilities:</strong></dt>
                                        <dd><strong>0,00 SAR</strong></dd>
                                        <dt>NET INCOME</dt>
                                        <dd>-</dd>
                                    </dl>
                                  </div>
                                </div>

                                 <div class="contentModule-subheadline">
                                    Additional Data
                                </div>
                                <div class="row">
                                  <div class="col-md-6">
                                    <dl class="dlList dlList_separated dlList_marginBottom">
                                        <dt>Capital</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt>retained earnings (accumulated losses)</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt>total shareholder´s equity</dt>
                                        <dd>0,00 SAR</dd>
                                    </dl>
                                  </div>
                                  <div class="col-md-6">
                                    <dl class="dlList dlList_separated dlList_lineThrough">
                                        <dt>partners drawing accounts</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt>others</dt>
                                        <dd>0,00 SAR</dd>
                                        <dt class="dlList-empty">&nbsp;</dt>
                                        <dd class="dlList-empty">&nbsp;</dd>
                                    </dl>
                                  </div>
                                </div>

                            </div>
                        </div>
                    </div>



                    <div class="panelTabs-head">
                        <icon:financialStatement-equity/>
                        <span class="panelTabs-label">Equity Change</span>
                    </div>
                    <div class="panelTabs-body panelModule panelModule_halfRadius">
                       

                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                    <div class="contentModule-headline">
                                       <icon:documents/>
                                       Financial Data
                                    </div>
                                </div>


                                <div class="tableModule">
                                  <table class="tableModule-table">
                                    <thead class="tableModule-head">
                                      <tr>
                                        <th>items</th>
                                        <th>Retained earnings (accumulated losses)</th>
                                        <th>total</th>
                                      </tr>
                                    </thead>
                                    <tbody class="tableModule-body">
                                      <tr>
                                        <td>Balance at begining of period (after adjustments)</td>
                                        <td>-</td>
                                        <td>-</td>
                                      </tr>
                                      <tr>
                                        <td>Balance at end of period</td>
                                        <td>-</td>
                                        <td>-</td>
                                      </tr>
                                    </tbody>
                                  </table>
                                </div>



                            </div>
                        </div>
                    </div>






                </div>   



                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                               <icon:documents/>
                               Support Documents
                            </div>
                            <ul class="downloadList downloadList_maxHeight">
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Fnancial Statement
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
            <h1 class="mainSection-headline">Financial Statement</h1>
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
        
        
        
        <div class="js-panelTabs panelTabs panelTabs_transparent panelTabs_iconsAndLabel panelTabs_separated panelTabs_tip_right  panelTabs_noPaddingTop financialStatement">
            <div class="panelTabs-head">
                <icon:financialStatement-income/>
                <span class="panelTabs-label">Income Statement</span>
            </div>
            <div class="panelTabs-body panelModule panelModule_halfRadius">
                
                
                
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                            <div class="contentModule-headline">
                               <icon:generalManager/>
                               Financial Data
                            </div>
                        </div>
                
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * Revenue
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * COST OF SALES
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * GROSS INCOME
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * TOTAL OPERATING EXPENSES
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * INCOME FROM MAIN OPERATIONS
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * TOTAL OTHER INCOMES (EXPENSES)
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * Net Income Before TAX & Zakat
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * Zakat
                                        </label>  
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>               
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * tax
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            * NET INCOME
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>                 
                                    </div>
                                </div>
                            </div>
                        </div>
                
                
                
                
                    </div>
                </div>
            </div>
            <div class="panelTabs-head">
                <icon:financialStatement-balance/>
                <span class="panelTabs-label">Balance Sheet</span>
            </div>
            <div class="panelTabs-body panelModule panelModule_halfRadius">
               
                
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider ">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                            <div class="contentModule-headline">
                               <icon:generalManager/>
                               Financial Data
                            </div>
                        </div>
                
                
                
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="contentModule-subheadline">
                                    Assets
                                </div>
                                
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            Total current assets
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            Total non-current assents
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            other assets
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>

                                <div class="financialStatement-total">
                                    1502525 SAR
                                    <div class="financialStatement-total-label">
                                        Total assets:
                                    </div>  
                                </div>
                
                
                
                            </div>
                            <div class="col-sm-6">
                                <div class="contentModule-subheadline">
                                    Liabilities
                                </div>
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            Total currents Liabilities
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            Total non-current Liabilities
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            other Liabilities
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>

                                <div class="financialStatement-total">
                                    1502525 SAR
                                    <div class="financialStatement-total-label">
                                        Total Liabilities:
                                    </div>  
                                </div>
                
                               
                            </div>
                        </div>
                
                
                
                        <div class="row">
                            <div class="col-12">
                                <div class="contentModule-subheadline">
                                    Additional Data
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            capital
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            partners drawing accounts
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            retained earnings (accumulated losses)
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            others
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="formInputBox formInputBox_group">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            total shareholder's equity
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text">SAR</span>
                                        </div>              
                                    </div>
                                </div>
                            </div>


                           
                        </div>
                
                
                
                    </div>
                </div>
            </div>
                
                
                
            <div class="panelTabs-head">
                <icon:financialStatement-equity/>
                <span class="panelTabs-label">Equity Change</span>
            </div>
            <div class="panelTabs-body panelModule panelModule_halfRadius">
               
                
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                        
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                            <div class="contentModule-headline">
                               <icon:documents/>
                               Financial Data
                            </div>
                        </div>
                
                
                        <div class="tableModule">
                          <table class="tableModule-table">
                            <thead class="tableModule-head">
                              <tr>
                                <th>items</th>
                                <th>Retained earnings (accumulated losses)</th>
                                <th>total</th>
                              </tr>
                            </thead>
                            <tbody class="tableModule-body">
                              <tr>
                                <td>Balance at begining of period (after adjustments)</td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input class="form-control" type="text">             
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input class="form-control" type="text">             
                                        </div>
                                    </div>
                                </td>
                              </tr>
                              <tr>
                                <td>Balance at end of period</td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input class="form-control" type="text">             
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input class="form-control" type="text">             
                                        </div>
                                    </div>
                                </td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                
                
                        
                
                        
                
                    </div>
                </div>
            </div>                
                
        </div>




        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                    <div class="contentModule-headline">
                       <icon:documents/>
                       Support Documents
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="formInputFile">
                                <div class="form-group">
                                    <input id="file08" name="file08" class="form-control js-inputFile" value="" type="file">
                                    <input id="text08" name="text08" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                    <label class="control-label " for="">Financial Statement</label>
                                    <div class="form-icon form-icon_browse"><icon:upload/></div>
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
            <button type="submit" class="btn btn-primary" >
                Submit
            </button>
        </div>


    </div>
</div>
