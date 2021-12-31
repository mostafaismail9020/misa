<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>


<%-- TODO: SAH-1289 Overview Page --%>


<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-headline">Questionnaires</div>
    </div>
</div>


<div class="mainSection mainSection_grey mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="panelModule panelModule_halfRadius">

            <div class="contentModule">

                <div class="contentModule-section">

                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_group"><icon:group/></span> Please participate in our latest questionnaire
                    </div>

                    <div class="myAccount-questionnaire">
                        <div class="myAccount-questionnaire-action">
                            <button class="btn btn_outline btn_slim">
                                Participate
                            </button>
                        </div>
                        <div class="myAccount-questionnaire-title">
                            <span class="myAccount-questionnaire-new">New!</span> Redesign of the Sagia E-Services page
                        </div>
                    </div>

                </div>


                <div class="contentModule-section">

                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_questionaires"><icon:questionaires/></span> All Questionnaires
                    </div>

                    <div class="myAccount-questionnaire">
                        <div class="myAccount-questionnaire-action">
                            <button class="btn btn_outline btn_slim">
                                Participate
                            </button>
                        </div>
                        <div class="myAccount-questionnaire-title">
                            Customer Satisfaction Survey
                        </div>
                    </div>

                    <div class="myAccount-questionnaire">
                        <div class="myAccount-questionnaire-action">
                            <button class="btn btn-secondary btn_link myAccount-questionnaire-completed">
                                <icon:status-complete/> Completed
                            </button>
                            <button class="btn btn_link myAccount-questionnaire-view btn_slim">
                                <icon:view/> View
                            </button>
                        </div>
                        <div class="myAccount-questionnaire-title">
                            General Event Feedback Survey
                        </div>
                    </div>

                    <div class="myAccount-questionnaire">
                        <div class="myAccount-questionnaire-action">
                            <button class="btn btn_outline btn_slim">
                                In Progress
                            </button>
                        </div>
                        <div class="myAccount-questionnaire-title">
                            Software and App Customer Feedback with Sentation.io
                        </div>
                    </div>

                </div>


                <div class="contentModule-section">

                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_feedback"><icon:feedback/></span> Send us your personal feedback
                    </div>

                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="profile.company.entityName2" class="text form-control" placeholder="." type="text">
                            <label class="control-label " for="profile.company.entityName2">* Subject</label>
                        </div>
                    </div>


                    <div class="formTextArea">
                        <div class="form-group">
                            <textarea id="td" class="form-control form-control" placeholder="."></textarea>
                            <label class="control-label" for="td">* Message</label>
                        </div>
                    </div>

                    <div class="contentModule-actions contentModule-actions_insideSection contentModule-actions_right">
                        <button class="btn" disabled>
                            Send Feedback
                        </button>

                    </div>

                </div>

            </div>

        </div>
    </div>
</div>














<%-- TODO: SAH-1289  Customer Satisfaction Survey --%>


<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-headline">Questionnaires</div>
    </div>
</div>


<div class="mainSection mainSection_grey mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="panelModule panelModule_halfRadius">

            <div class="contentModule">

                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_questionaires"><icon:questionaires/></span> Customer Satisfaction Survey
                    </div>
                    <div>
                        <a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to All Questionaires</a>
                    </div>
                </div>

                <div class="contentModule-section contentModule-section_slimDivider contentModule-section_noDivider">

                    <div class="contentModule-headline contentModule-headline_survey">A. Research & Development</div>

                    <div class="contentModule-subheadline">a. Please provide ependiture on R&D activities during the year 2015.</div>

                    <div class="row">
                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Current Expenditure
                                    </label>
                                </div>
                            </div>

                        </div>
                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Capital Expenditure
                                    </label>
                                </div>
                            </div>

                        </div>
                    </div>


                    <div class="contentModule-subheadline">b. What is your objective and reasons of R&D?</div>

                    <div class="row">
                        <div class="col">

                            <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                <div class="form-group">
                                    <div class="formCheckBox-label">Your can choose mulltiple options</div>
                                    <div class="form-item">
                                        <input id="SAH503-1" name="SAH503" class="form-control" placeholder="." type="checkbox" value="">
                                        <label class="control-label " for="SAH503-1">
                                            <span><icon:check/></span> Replace products being phased out
                                        </label>
                                    </div>

                                    <div class="form-item">
                                        <input id="SAH503-1" name="SAH503" class="form-control" placeholder="." type="checkbox" value="">
                                        <label class="control-label " for="SAH503-1">
                                            <span><icon:check/></span> Improvement of product quality
                                        </label>
                                    </div>

                                    <div class="form-item">
                                        <input id="SAH503-1" name="SAH503" class="form-control" placeholder="." type="checkbox" value="">
                                        <label class="control-label " for="SAH503-1">
                                            <span><icon:check/></span> Extension of the range of product applications
                                        </label>
                                    </div>

                                    <div class="form-item">
                                        <input id="SAH503-1" name="SAH503" class="form-control" placeholder="." type="checkbox" value="">
                                        <label class="control-label " for="SAH503-1">
                                            <span><icon:check/></span> Compliance with regulations and standards
                                        </label>
                                    </div>

                                    <div class="form-item">
                                        <input id="SAH503-1" name="SAH503" class="form-control" placeholder="." type="checkbox" value="">
                                        <label class="control-label " for="SAH503-1">
                                            <span><icon:check/></span> Reduction of labour costs
                                        </label>
                                    </div>

                                    <div class="form-item">
                                        <input id="SAH503-1" name="SAH503" class="form-control" placeholder="." type="checkbox" value="">
                                        <label class="control-label " for="SAH503-1">
                                            <span><icon:check/></span> Reduction of materials consumption in production
                                        </label>
                                    </div>

                                    <div class="form-item">
                                        <input id="SAH503-2" name="SAH503" class="form-control" placeholder="." type="checkbox" value="">
                                        <label class="control-label " for="SAH503-2">
                                            <span><icon:check/></span> Others (specify)
                                        </label>
                                        <div class="formCheckBox-detail formCheckBox-detail_textarea">
                                            <div class="formTextArea">
                                                <div class="form-group">
                                                    <textarea id="" class="form-control form-control_slim" placeholder="."></textarea>
                                                    <label class="control-label" for="">
                                                        Comments
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>


                    <div class="contentModule-subheadline">c. Please give the number of outputs of R&D in your organization.</div>

                    <div class="row">
                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Patents registered
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Inventions applied
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Trade Marks acquired
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Designs implemented
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Innovative processes developed but not patented
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Innovative products developed but not patented
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Others (please specify)
                                    </label>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>

                <div class="contentModule-section contentModule-section_slimDivider contentModule-section_noDivider">

                    <div class="contentModule-headline contentModule-headline_survey">B. Plans for future expansion</div>

                    <div class="contentModule-subheadline">a. What is your satisfaction level on the velow in last 6 months?</div>


                    <div class="row">
                        <div class="col-md-6">
                            <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                <div class="form-group">
                                    <div class="formRadioButton-label">In Saudi Economy</div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Extremely Dissatisfied</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Dissatisfied</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Satisfied</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Extremely Satisfied</label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-md-6">
                            <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                <div class="form-group">
                                    <div class="formRadioButton-label">In your company sector</div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Extremely Dissatisfied</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Dissatisfied</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Satisfied</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Extremely Satisfied</label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>



                    <div class="contentModule-subheadline">b. What is your confidence level on the below in next 6 months?</div>


                    <div class="row">
                        <div class="col-md-6">
                            <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                <div class="form-group">
                                    <div class="formRadioButton-label">In Saudi Economy</div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Extremely Non-confident</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Non-confident</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Somewhat Confident</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Confident</label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                <div class="form-group">
                                    <div class="formRadioButton-label">In your company sector</div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Extremely Non-confident</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Non-confident</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Somewhat Confident</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Confident</label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="contentModule-section contentModule-section_slimDivider contentModule-section_noDivider">

                    <div class="contentModule-headline contentModule-headline_survey">C. Investment Factor</div>

                    <div class="contentModule-subheadline">a. Importance of each factor in your location decision</div>


                    <div class="row">
                        <div class="col-md-6">
                            <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                <div class="form-group">
                                    <div class="formRadioButton-label">Regional presence in Middle East</div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Not important</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Important</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Crucial</label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                <div class="form-group">
                                    <div class="formRadioButton-label">Presence in Saudi Arabia</div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Not important</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Important</label>
                                    </div>

                                    <div class="form-item">
                                        <input id="" name="" class="form-control" type="radio" value="true">
                                        <label for="" class="control-label">
                                            <span></span> Crucial</label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>


                <div class="contentModule-section contentModule-section_noMargin contentModule-section_noDivider">

                    <div class="contentModule-headline contentModule-headline_survey">D. Acknowledgment</div>

                    <div class="contentModule-subheadline">Please provide your details:</div>


                    <div class="row">
                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Name *
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Mobile *
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="" class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label" for="">
                                        Phone *
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="formInputBox formInputBox_group ">
                                <div class="form-group">
                                    <input id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_date" placeholder="." value="" type="text">
                                    <label class="control-label" for="profile.enquiry.type">
                                        Date
                                    </label>
                                    <div class="formInputBox-append">
                                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>


                    <div class="row">
                        <div class="col-12">
                            <div class="formCheckBox">
                                <div class="form-group">
                                    <div class="form-item">
                                        <input id="terms" name="terms" class="form-control" placeholder="." type="checkbox">
                                        <label class="control-label " for="terms">
                                            <span>
                                                          <icon:check/>
                                                      </span>I agree that the above provided information in SAGIA eSurvey questionaire is accurate and complete *
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>


                <div class="contentModule-actions contentModule-actions_centered">
                    <button class="btn">Send Feedback</button>
                </div>

            </div>       
       
        </div>
    </div>
</div>






