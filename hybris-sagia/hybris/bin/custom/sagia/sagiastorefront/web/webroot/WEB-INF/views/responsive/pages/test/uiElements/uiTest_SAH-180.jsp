<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>



<%-- TODO: SAH-180 --%>


<!--uiTest_SAH-180.jsp Part 1 (Entity Tab)-->
                    <div>
                        <div class="formRadioBox-wrapper">
                            <div class="row">
                                <div class="col-md-6"><span>Do you have an Advance License Number?</span></div>
                                <div class="col-md-6">

                                    <div class="formRadioBox">
                                        <div class="form-group">

                                            <div class="form-item">
                                                <input type="radio" name="hasAdvanceLicenseNr" id="hasAdvanceLicenseNrYES" value="yes" class="form-control">
                                                <label for="hasAdvanceLicenseNrYES" unselectable class="control-label">Yes</label>
                                            </div>

                                            <div class="form-item">
                                                <input type="radio" name="hasAdvanceLicenseNr" id="hasAdvanceLicenseNrNO" value="no" class="form-control">
                                                <label for="hasAdvanceLicenseNrNO" unselectable class="control-label">No</label>
                                            </div>

                                        </div>
                                    </div>                                                                            

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="" class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label" for="">
                                            Advanced license number
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>                        
                    </div>


<!--uiTest_SAH-180.jsp Part 2 (Review Tab)-->
                <div class="row">
                    <div class="col-md-6">
                        <ul class="ynList">
                            <li class="ynList-item">
                                <span class="iconElement iconElement_ynIcon iconElement_ynIcon_yes"><icon:ynIcon_yes/></span>
                                Do you have an Advance License No.?
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-6">
                        <dl class="dlList">
                            <dt>Advance license number</dt>
                            <dd>12345678900</dd>
                        </dl>                                               
                    </div>
                </div> 
