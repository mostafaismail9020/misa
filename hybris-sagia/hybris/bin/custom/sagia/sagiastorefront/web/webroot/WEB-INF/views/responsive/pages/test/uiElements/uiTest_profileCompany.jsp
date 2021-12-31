<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="survey" tagdir="/WEB-INF/tags/responsive/survey" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--<div id="globalMessages">
    <common:globalMessages/>
</div>--%>

<div class="mainSection mainSection_dark">
    <div class="container">
        <h1 class="mainSection-headline"><spring:theme code="general.account"/></h1>
    </div>
</div>

<div class="js-panelTabs panelTabs panelTabs_slim panelTabs_account">
    <div class="panelTabs-head" id="companyTab"><spring:theme code="general.company"/></div>
    <div class="panelTabs-body">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section">
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                        <div class="contentModule-headline">
                            <span class="iconElement iconElement_info"><icon:info/></span>
                            <spring:theme code="general.basicinformation"/>
                        </div>
                        <div>
                            <button class="js-myAccount-edit btn btn_link btn_edit">
                                <div class="js-myAccount-edit-text"><spring:theme code="profileCompany.button.edit.text"/>
                                    <span class="iconElement iconElement_edit02"><icon:edit/></span>
                                </div>
                                <div class="js-myAccount-edit-text hidden">
                                    <span class="iconElement iconElement_closeEdit" aria-hidden="true">&times;</span>
                                    <spring:theme code="profileCompany.button.close.edit.text"/>
                                </div>
                            </button>
                        </div>
                    </div>

                    <div class="js-myAccount-edit-toggle">
                        <div class="row">
                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.entityName"/></dt>
                                    <dd>${sagiaCompanyForm.entityName}</dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.entityNameArabic"/></dt>
                                    <dd>${sagiaCompanyForm.entityNameArabic}</dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.legalstatus"/></dt>
                                    <dd>${sagiaCompanyForm.legalStatusDisplay}</dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.capital"/></dt>
                                    <dd>${sagiaCompanyForm.capital}</dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.region"/></dt>
                                    <dd>${sagiaCompanyForm.regionDisplay}</dd>
                                </dl>
                            </div>


                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.city"/></dt>
                                    <dd>${sagiaCompanyForm.cityDisplay}</dd>
                                </dl>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="contentModule-section">
                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_documents"><icon:documents/></span><spring:theme code="profileCompany.supportingDocuments"/>
                    </div>

                    <div class="js-myAccount-edit-toggle">
                        <div class="documentSection">
                            <div class="documentSection-headline"><spring:theme code="general.documents"/></div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="documentSection-item">
                                        <div class="documentSection-img">
                                            <span class="iconElement iconElement_pdf02"><icon:pdf02/></span>
                                        </div>
                                        <div class="documentSection-name"><spring:theme
                                                code="company.applicationsigned"/></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="js-myAccount-edit-toggle" style="display: none;">
                        <!--form needs to be replaced-->
                        <form action="">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="formInputFile">
                                        <div class="form-group">
                                            <input id="file06" name="file06" class="form-control js-inputFile"
                                                   type="file" value=""></input>
                                            <input id="text06" name="text06" class="form-control" type="text" value=""
                                                   placeholder="" readonly tabindex="-1"></input>
                                            <label class="control-label " for=""><spring:theme
                                                    code="general.labelforfile"/></label>
                                            <div class="form-icon form-icon_browse"><icon:upload/></div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputFile">
                                        <div class="form-group">
                                            <input id="file07" name="file07" class="form-control js-inputFile"
                                                   type="file" value=""></input>
                                            <input id="text07" name="text07" class="form-control" type="text" value=""
                                                   placeholder="" readonly tabindex="-1"></input>
                                            <label class="control-label " for=""><spring:theme
                                                    code="general.labelforfile"/></label>
                                            <div class="form-icon form-icon_browse"><icon:upload/></div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="acceptTerms">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="formCheckBox">
                                            <div class="form-group">
                                                <div class="form-item">
                                                    <input id="checkbox03" name="checkbox03name" class="form-control"
                                                           placeholder="." type="checkbox" value="entity name"></input>
                                                    <label class="control-label " for="checkbox03">
                                                        <span><icon:check/></span><spring:theme code="profileCompany.acceptTerms.text"/>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <a href="#" class="link" target="_blank"><spring:theme
                                                code="company.coclettertemplate"/></a>
                                    </div>
                                </div>
                            </div>

                            <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                <button type="button" class="btn btn-secondary"><spring:theme
                                        code="general.cancel"/></button>
                                <button type="submit" class="btn"><spring:theme code="general.update"/></button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="contentModule-section">
                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_governmentDocuments"><icon:governmentDocuments/></span>
                        <spring:theme code="general.governmentdocuments"/>
                    </div>

                    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                        <a href="${encodedContextPath}/governmentDocuments" class="btn btn_outline"><spring:theme
                                code="general.governmentdocuments"/></a>
                    </div>
                </div>

                <div class="contentModule-section">

                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_branches"><icon:branches/></span>Branches (3)
                    </div>

                    <!-- Slim dummy table added-->
                    <!-- TODO remove lorem stuff -->
                    <div class="tableModule tableModule_slim">
                        <table class="tableModule-table">
                            <thead class="tableModule-head">
                            <tr>
                                <th>Lorem ipsum</th>
                                <th>dolor sit</th>
                                <th>amet</th>
                            </tr>
                            </thead>
                            <tbody class="tableModule-body">
                            <tr>
                                <td>consectetur</td>
                                <td>adipisicing</td>
                                <td>elit</td>
                            </tr>
                            <tr>
                                <td>Sit</td>
                                <td>aliquid</td>
                                <td>maiores</td>
                            </tr>
                            <tr>
                                <td>voluptas</td>
                                <td>libero</td>
                                <td>harum</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="contentModule-actions contentModule-actions_right">
                    <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                        <div class="hidden"><spring:theme code="text.account.followup.hideServiceHistory"/><span>&#x27f6;</span></div>
                        <div><spring:theme code="text.account.followup.showServiceHistory"/><span class="iconElement iconElement_closeBack"><icon:close/></span></div>
                    </button>
            </div>
        </div>
        <div class="expandableContent" id="expand01">
            <div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                            <span class="iconElement iconElement_history"><icon:history/></span>
                                <spring:theme code="text.account.followup.history"/></div>
                            <div class="searchInputBox searchInputBox_slim">
                                <input id = "convertSearchBox" class="searchInputBox-input" type="text" placeholder="Search"/>
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
                        </div>                        
                    </div>
                </div> 
            </div>        
            <div class="expandableContent-main">           
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
<!--                general manager-->
                        <div class="contentModule-section">
                           
                           
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_generalManager"><icon:generalManager/></span><spring:theme code="profileCompany.generalManager.title"/>
                                </div>
                                <div>
                                    <button class="js-myAccount-edit btn btn_link btn_edit">
                                        <div class="js-myAccount-edit-text"><spring:theme code="profileCompany.button.edit.text"/>
                                            <span class="iconElement iconElement_edit02"><icon:edit/></span>
                                        </div>
                                        <div class="js-myAccount-edit-text hidden">
                                            <span class="iconElement iconElement_closeEdit" aria-hidden="true">&times;</span>
                                            <spring:theme code="profileCompany.button.close.edit.text"/>
                                        </div>
                                    </button>
                                </div>
                            </div>                           

                            <div class="js-myAccount-edit-toggle">

                                <div class="row">
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.firstname"/></dt>
                                            <dd>${sagiaProfileGeneralManagerForm.firstName}</dd>
                                        </dl>
                                    </div>

                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.middlename"/></dt>
                                            <dd>${sagiaProfileGeneralManagerForm.middleName}</dd>
                                        </dl>
                                    </div>

                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.lastname"/></dt>
                                            <dd>${sagiaProfileGeneralManagerForm.lastName}</dd>
                                        </dl>
                                    </div>

                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.mobilenumber"/></dt>
                                            <dd>${sagiaProfileGeneralManagerForm.mobileNumber}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.email"/></dt>
                                            <dd>${sagiaProfileGeneralManagerForm.email}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.nationalid"/></dt>
                                            <dd>${sagiaProfileGeneralManagerForm.nationality}</dd>
                                        </dl>
                                    </div>
                                </div>
                                <div class="documentSection">
                                    <div class="documentSection-headline"><spring:theme code="general.documents"/></div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="documentSection-item">
                                                <div class="documentSection-img">
                                                    <span class="iconElement iconElement_pdf02"><icon:pdf02/></span>
                                                </div>
                                                <div class="documentSection-name js-attach-file-download"
                                                     data-object-id="${sagiaProfileGeneralManagerForm.srId}"
                                                     data-document-id="${sagiaProfileGeneralManagerForm.docId}"
                                                     style="cursor: pointer"><spring:theme code="general.idoriqama"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!--form needs to be replaced. dummy data-->
                            <div class="js-myAccount-edit-toggle" style="display: none;">
                                <form:form action="${encodedContextPath}/contacts/update-general-manager"
                                           enctype="multipart/form-data"
                                           method="POST"
                                           modelAttribute="generalManagerFormData"
                                           class="js-general-manager-form">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <input id="firstName" name="firstName" class="form-control" placeholder="."
                                                           type="text"
                                                           value="${sagiaProfileGeneralManagerForm.firstName}"></input>
                                                    <label class="control-label" for=""><spring:theme
                                                            code="general.firstname"/></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <input name="middleName" class="form-control" placeholder="." type="text"
                                                           value="${sagiaProfileGeneralManagerForm.middleName}"></input>
                                                    <label class="control-label" for=""><spring:theme
                                                            code="general.middlename"/></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <input id="lastName" name="lastName" class="form-control" placeholder="."
                                                           type="text"
                                                           value="${sagiaProfileGeneralManagerForm.lastName}"></input>
                                                    <label class="control-label" for=""><spring:theme
                                                            code="general.lastname"/></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <input id="mobileNumber" name="mobileNumber" class=" form-control"
                                                           placeholder="." type="text"
                                                           value="${sagiaProfileGeneralManagerForm.mobileNumber}"></input>
                                                    <label class="control-label" for=""><spring:theme
                                                            code="general.mobilenumber"/></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <input id="email" name="email" class="form-control" placeholder="."
                                                           type="text" value="${sagiaProfileGeneralManagerForm.email}"></input>
                                                    <label class="control-label" for=""><spring:theme
                                                            code="general.email"/></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <input id="nationality" name="nationality" class="form-control"
                                                           placeholder="." type="text"
                                                           value="${sagiaProfileGeneralManagerForm.nationality}"></input>
                                                    <label class="control-label" for=""><spring:theme
                                                            code="general.nationalid"/></label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="formInputFile">
                                                <div class="form-group">
                                                    <input id="fileNationalId" name="fileNationalId"
                                                           class="form-control js-inputFile" type="file" value=""
                                                           accept="application/pdf"></input>
                                                    <input id="text08" name="text08" class="form-control" type="text" value=""
                                                           placeholder="" readonly tabindex="-1"></input>
                                                    <label class="control-label " for=""><spring:theme
                                                            code="general.labelforfile"/></label>
                                                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                        <icon:cross/></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="acceptTerms">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="formCheckBox">
                                                    <div class="form-group">
                                                        <div class="form-item">
                                                            <input id="checkbox01" name="checkbox01name"
                                                                   class="form-control js-accept-terms-manager" placeholder="."
                                                                   type="checkbox"
                                                                   value="entity name"></input>
                                                            <label class="control-label " for="checkbox01">
                                                                <span><icon:check/></span><spring:theme
                                                                    code="general.accepttermsandconditions"/>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <a href="#" class="link" target="_blank"><spring:theme
                                                        code="company.coclettertemplate"/></a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                        <button type="button" class="btn btn-secondary"><spring:theme
                                                code="general.cancel"/></button>
                                        <button type="submit" class="btn"><spring:theme code="general.update"/></button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
        <!--                company rep-->
                        <div class="contentModule-section">
                            <div class="contentModule-headline">
                                <span class="iconElement iconElement_generalManager"><icon:person/></span><spring:theme code="profileCompany.companyRepresentative.title"/>
                            </div>
                            <div class="">
                                <div class="row">
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.firstname"/></dt>
                                            <dd>${sagiaProfileRepresentativeForm.firstName}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.middlename"/></dt>
                                            <dd>${sagiaProfileRepresentativeForm.middleName}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.lastname"/></dt>
                                            <dd>${sagiaProfileRepresentativeForm.lastName}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.mobilenumber"/></dt>
                                            <dd>${sagiaProfileRepresentativeForm.mobileNumber}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.email"/></dt>
                                            <dd>${sagiaProfileRepresentativeForm.email}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="general.nationalid"/></dt>
                                            <dd>${sagiaProfileRepresentativeForm.nationality}</dd>
                                        </dl>
                                    </div>
                                </div>
                                <div class="documentSection">
                                    <div class="documentSection-headline"><spring:theme code="general.documents"/></div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="documentSection-item">
                                                <div class="documentSection-img">
                                                    <span class="iconElement iconElement_pdf02"><icon:pdf02/></span>
                                                </div>
                                                <div class="documentSection-name js-attach-file-download"
                                                     data-object-id="${sagiaProfileRepresentativeForm.srId}"
                                                     data-document-id="${sagiaProfileRepresentativeForm.docId}"
                                                     style="cursor: pointer">
                                                    <spring:theme code="company.representativenationalid"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="documentSection-item">
                                                <div class="documentSection-img">
                                                    <span class="iconElement iconElement_pdf02"><icon:pdf02/></span>
                                                </div>
                                                <div class="documentSection-name js-attach-file-download"
                                                     data-object-id="${sagiaProfileRepresentativeForm.srId}"
                                                     data-document-id="${sagiaProfileRepresentativeForm.docGovId}"
                                                     style="cursor: pointer">
                                                    <spring:theme code="company.gosicertificate"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div>
                                <div class="tableModule tableModule_slim">
                                  <table class="tableModule-table">
                                    <thead class="tableModule-head">
                                      <tr>
                                        <th>First name</th>
                                        <th>Last name</th>
                                        <th>Mobile number</th>
                                        <th>Email</th>
                                        <th class="tableModule-headItem_actionsCount_1">Details</th>
                                      </tr>
                                    </thead>
                                    <tbody class="tableModule-body">
                                      <tr>
                                        <td>Ilam</td>
                                        <td>Siva</td>
                                        <td>+966 (23324)</td>
                                        <td>v.j@sa.com</td>
                                        <td class="tableModule-bodyItem-action">
                                            <button type="button" class="btn btn_link"
                                                    data-toggle="modal"
                                                    data-target="#companyRepresentative">
                                                <icon:view/>
                                            </button>                                            
                                        </td>
                                      </tr>                                     
                                      <tr>
                                        <td>Rollo</td>
                                        <td>Ragnvaldsson</td>
                                        <td>+966 (23326)</td>
                                        <td>r.rag@sap.com</td>
                                        <td class="tableModule-bodyItem-action">
                                            <button type="button" class="btn btn_link"
                                                    data-toggle="modal"
                                                    data-target="#companyRepresentative">
                                                <icon:view/>
                                            </button>                                            
                                        </td>
                                      </tr>
                                    </tbody>
                                  </table>
                                </div> 
                                <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                    <button href="/sagiastorefront/sagia/en/governmentDocuments" class="btn">+ Add representative</button>
                                </div>                               
                            </div>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>        
        
    </div>

    <div class="panelTabs-head" id="myProfileTab"><spring:theme code="company.myprofile"/></div>
    <div class="panelTabs-body">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section">
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                        <div class="contentModule-headline">
                            <span class="iconElement iconElement_accountSettings02"><icon:accountSettings02/></span><spring:theme code="profile.settings"/>
                        </div>
                        <div>
                            <button class="js-myAccount-edit btn btn_link btn_edit">
                                <div class="js-myAccount-edit-text"><spring:theme code="general.edit"/>
                                    <span class="iconElement iconElement_edit02"><icon:edit/></span>
                                </div>
                                <div class="js-myAccount-edit-text hidden">
                                    <span class="iconElement iconElement_closeEdit" aria-hidden="true">&times;</span><spring:theme code="profile.exit.editMode"/>
                                </div>
                            </button>
                        </div>
                    </div>

                    <div class="myAccount-edit-toggle js-myAccount-edit-toggle">
                        <div class="myAccount-profilImage">
                            <div class="myAccount-profilImage-img"><img src="${profilePicture}" alt=""></img></div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt>Title</dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.title.name}'/></dd>
                                </dl>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="general.firstname"/></dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.firstName}'/></dd>
                                    <dt><spring:theme code="general.country"/></dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.country.name}'/></dd>
                                    <dt><spring:theme code="general.sector"/></dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.sector.name}'/></dd>
                                    <dt><spring:theme code="general.mobilenumber"/></dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.mobileNumber}'/></dd>
                                </dl>
                            </div>
                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="general.lastname"/></dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.lastName}'/></dd>
                                    <!--
                                    <dt><spring:theme code="general.email"/></dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.email}'/></dd>
-->
                                    <dt><spring:theme code="general.company"/></dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.company}'/></dd>
                                    <dt><spring:theme code="general.countrycode"/></dt>
                                    <dd><c:out value='${sagiaProfilePersonalForm.mobileCountryCode}'/></dd>
                                </dl>
                            </div>
                        </div>
                    </div>

                    <div class="myAccount-edit-toggle js-myAccount-edit-toggle" style="display: none;">
                        <div class="myAccount-profilImage">
                            <div class="myAccount-profilImage-img"><img src="${profilePicture}" alt=""></div>
                            <a href="" class="myAccount-profilImage-change" data-toggle="modal"
                               data-target="#uploadFilePicture"><spring:theme code="company.changeprofilepicture"/></a>
                        </div>

                        <!--Modal: Use (data-toggle="modal" data-target="#uploadFilePicture") on link or button to call it-->
                        <div class="modal fade" id="uploadFilePicture" tabindex="-1" role="dialog"
                             aria-labelledby="uploadFilePicture" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent"
                                 role="document">
                                <div class="modal-content">
                                    <form:form action="update-profilePic" method="post" enctype="multipart/form-data"
                                               class="js-formInputFileBox">
                                        <div class="modal-header">
                                            <div class="modal-title"><spring:theme
                                                    code="company.uploadyourdocument"/></div>
                                        </div>
                                        <div class="modal-body">
                                            <div class="formInputFileBox">
                                                <div class="form-group">
                                                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                    <input id="fileBoxModalPicture" class="form-control js-inputFile"
                                                           type="file" name="file" accept="image/*" value=""></input>
                                                    <label class="control-label" for="fileBoxModalPicture">
                                                        Choose a picture<span class="formInputFileBox-dragndrop"> or drag it here</span>.
                                                    </label>
                                                </div>
                                                <div class="formInputFileBox-uploading"><spring:theme
                                                        code="company.uploading"/></div>
                                                <div class="formInputFileBox-success"><spring:theme
                                                        code="company.done"/></div>
                                                <div class="formInputFileBox-error"><spring:theme code="company.error"/><span></span>.
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn"><spring:theme
                                                    code="text.account.profile.saveUpdates"
                                                    text="Save Updates"/></button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>

                        <form:form action="update-my-profile" method="post" modelAttribute="sagiaProfilePersonalForm">
                            <div class="row">
                                <div class="col-md-6">
                                    <formElement:formSelectBox idKey="profile.personal.title"
                                                               labelKey="profile.personal.title"
                                                               selectCSSClass="form-control" path="title.code"
                                                               items="${titles}" itemLabel="name" itemValue="code"
                                                               mandatory="true"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <formElement:formInputBox idKey="profile.personal.firstName"
                                                              labelKey="profile.personal.firstName" path="firstName"
                                                              inputCSS="" mandatory="true"/>
                                </div>
                                <div class="col-md-6">
                                    <formElement:formInputBox idKey="profile.personal.lastName"
                                                              labelKey="profile.personal.lastName" path="lastName"
                                                              inputCSS="" mandatory="true"/>
                                </div>
                                <!--
                                <div class="col-md-6">
                                    <formElement:formInputBox idKey="profile.personal.email"
                                                              labelKey="profile.personal.email" path="email" inputCSS=""
                                                              mandatory="true" disabled="true"/>
                                </div>
-->
                                <div class="col-md-6">
                                    <formElement:formSelectBox idKey="profile.country"
                                                               labelKey="profile.personal.country"
                                                               selectCSSClass="form-control js-select2-searchBegining js-select2-sortAlphabetically"
                                                               path="country.code" mandatory="true" skipBlank="false"
                                                               skipBlankMessageKey="form.select.empty"
                                                               items="${countries}" itemValue="code"/>
                                </div>
                                <div class="col-md-6">
                                    <formElement:formInputBox idKey="profile.personal.company"
                                                              labelKey="profile.personal.company" path="company"
                                                              inputCSS="" mandatory="true"/>
                                </div>
                                <div class="col-md-6">
                                    <formElement:formSelectBox idKey="profile.personal.sector"
                                                               labelKey="profile.personal.sector"
                                                               selectCSSClass="form-control" path="sector.code"
                                                               items="${sectors}" itemLabel="name" itemValue="code"
                                                               mandatory="true"/>
                                </div>
                                <div class="col-md-6">
                                    <formElement:formInputBox idKey="profile.personal.mobileCountryCode"
                                                              labelKey="profile.personal.mobileCountryCode"
                                                              path="mobileCountryCode" inputCSS=""
                                                              mandatory="true"/>
                                </div>
                                <div class="col-md-6">
                                    <formElement:formInputBox idKey="profile.personal.mobileNumber"
                                                              labelKey="profile.personal.mobileNumber"
                                                              path="mobileNumber" inputCSS=""/>
                                </div>
                            </div>

                            <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                <ycommerce:testId code="personalDetails_cancelPersonalDetails_button">
                                    <button type="button" class="btn btn-secondary"><spring:theme
                                            code="text.account.profile.cancel" text="Cancel"/></button>
                                </ycommerce:testId>
                                <ycommerce:testId code="personalDetails_savePersonalDetails_button">
                                    <button type="submit" class="btn"><spring:theme
                                            code="text.account.profile.saveUpdates" text="Save Updates"/></button>
                                </ycommerce:testId>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="panelTabs-head" id="passwordTab"><spring:theme code="password.password"/>&<spring:theme
            code="general.email"/></div>
    <div class="panelTabs-body">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_password"><icon:password/></span>My Password
                    </div>
                    <account:updatePwd/>
                </div>
                <div class="contentModule-section">
                    <div class="contentModule-headline"><span
                            class="iconElement iconElement_password"><icon:accountSettings02/></span>My Username
                    </div>
                    <account:updateUsername/>
                </div>
                <div class="contentModule-section">
                    <div class="contentModule-headline"><span
                            class="iconElement iconElement_password"><icon:my-email/></span>My Email
                    </div>
                    <account:updateEmail/>
                </div>
            </div>
        </div>
    </div>

    <div class="panelTabs-head" id="enquiriesTab"><spring:theme code="company.enquirescomplaints"/><span
            class="notifyCount">${fn:length(complaintList)}</span></div>
    <div class="panelTabs-body">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_enquiry3"><icon:enquiry3/></span>New enquiry
                    </div>
                    <form:form action="${encodedContextPath}/complaints/create"
                               enctype="multipart/form-data"
                               method="post"
                               modelAttribute="complaintFormData">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <form:select class="js-select2-oneColumn" id="enquiryList"
                                                     path="details.EnquiryType">
                                            <form:options items="${complaintFormData.enquiryTypes}" itemValue="catID"
                                                          itemLabel="catDesc" htmlEscape="true"/>
                                        </form:select>
                                        <label class="control-label " for=""><spring:theme
                                                code="profile.enquiry.type"/></label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <form:select class="js-select2-oneColumn" id="categoriesOneList"
                                                     path="details.Category1">
                                            <form:options itemValue="catID" itemLabel="catDesc" htmlEscape="true"/>
                                        </form:select>
                                        <label class="control-label " for=""><spring:theme
                                                code="general.category1"/></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <form:select class="js-select2-oneColumn" id="categoriesTwoList"
                                                     path="details.Category2">
                                            <form:options itemValue="catID" itemLabel="catDesc" htmlEscape="true"/>
                                        </form:select>
                                        <label class="control-label " for=""><spring:theme
                                                code="general.category2"/></label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <form:select class="js-select2-oneColumn" path="details.Branch">
                                            <form:options items="${complaintFormData.branches}"
                                                          itemValue="description" itemLabel="description"
                                                          htmlEscape="true"/>
                                        </form:select>
                                        <label class="control-label " for=""><spring:theme
                                                code="general.branch"/></label>
                                    </div>
                                </div>
                            </div>

                            <c:forEach items="${complaintFormData.attachments}" var="file" varStatus="loop">
                                <input type="hidden" name="dockeys[${loop.index}]" value="${file.dockey_ID}"></input>
                            </c:forEach>
                        </div>

                        <formElement:formInputBox idKey="details.subject"
                                                  labelKey="Subject" path="details.TextMsg" inputCSS="text"
                                                  mandatory="true"/>
                        <formElement:formTextArea areaCSS="form-control"
                                                  idKey="details.TextMsg" labelKey="Message" path="details.TextMsg"
                                                  mandatory="true"/>
                        <div class="row">
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file0" name="files[0]" class="form-control js-inputFile"
                                               accept="application/pdf" type="file" value=""></input>
                                        <input id="text05" name="text05" class="form-control" type="text" value=""
                                               placeholder="" readonly tabindex="-1"></input>
                                        <label class="control-label " for=""><spring:theme
                                                code="general.labelforfile"/></label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file1" name="files[1]" class="form-control js-inputFile"
                                               accept="application/pdf" type="file" value=""></input>
                                        <input id="text02" name="text02" class="form-control" type="text" value=""
                                               placeholder="" readonly tabindex="-1"></input>
                                        <label class="control-label " for=""><spring:theme
                                                code="general.labelforfile"/></label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file2" name="files[2]" class="form-control js-inputFile"
                                               accept="application/pdf" type="file" value=""></input>
                                        <input id="text03" name="text03" class="form-control" type="text" value=""
                                               placeholder="" readonly tabindex="-1"></input>
                                        <label class="control-label " for=""><spring:theme
                                                code="general.labelforfile"/></label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file3" name="files[3]" class="form-control js-inputFile"
                                               accept="application/pdf" type="file" value=""></input>
                                        <input id="text04" name="text04" class="form-control" type="text" value=""
                                               placeholder="" readonly tabindex="-1"></input>
                                        <label class="control-label " for=""><spring:theme
                                                code="general.labelforfile"/></label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                            <button class="btn" type="submit" value="SUBMIT"><spring:theme
                                    code="profile.enquiry.create"/></button>
                        </div>
                    </form:form>
                </div>

                <div class="contentModule-section">

                    <div class="contentModule-headline contentModule-headline_flex">
                        <div class="contentModule-headline-title">
                            <span class="iconElement iconElement_your-tickets"><icon:your-tickets/></span><spring:theme code="profile.yourTickets"/>
                        </div>

                        <c:if test="${not empty processingTime}">
                            <div class="serviceTime">
                                <div class="serviceTime-label"><spring:theme code="average.service.time"/></div>
                                <div class="serviceTime-detail">
                                    <span class="serviceTime-highlight">${processingTime.days}</span> <spring:theme
                                        code="average.processingTime.days"/> <span
                                        class="serviceTime-highlight">${processingTime.hours}</span> <spring:theme
                                        code="average.processingTime.hours"/>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="contentModule-filter">
                        <select id="profileTicketSort" class="js-select2-oneColumn form-control"
                                onchange="sortProfileTickets()">
                            <option value="status_asc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme
                                    code="sagia.sort.asc"/></option>
                            <option value="status_desc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme
                                    code="sagia.sort.desc"/></option>
                            <option value="number_asc" data-sort="asc"><spring:theme code="sagia.sort.ticketNumber"/>&nbsp;<spring:theme
                                    code="sagia.sort.asc"/></option>
                            <option value="number_desc" data-sort="desc"><spring:theme code="sagia.sort.ticketNumber"/>&nbsp;<spring:theme
                                    code="sagia.sort.desc"/></option>
                            <option value="date_asc" data-sort="asc"><spring:theme
                                    code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                            <option value="date_desc" data-sort="desc"><spring:theme
                                    code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                        </select>
                    </div>
                    <div class="tableModule">
                        <table class="tableModule-table" id="profileTicketsTable">
                            <thead class="tableModule-head">
                            <tr>
                                <th>Last update</th>
                                <th>Ticket Number</th>
                                <th>Enquiry Type</th>
                                <th>Status</th>
                                <th>Options</th>
                            </tr>
                            </thead>
                            <tbody class="tableModule-body">
                            <c:forEach items="${complaintList}" var="complaint">
                                <tr>
                                    <td>${complaint.lastUpdateData.dateFormatted}</td>
                                    <td>${complaint.ticketNumber}</td>
                                    <td>${complaint.enquiryType}</td>
                                    <td>${complaint.status}</td>
                                    <td>
                                        <a href="#" class="link" data-toggle="modal"
                                           data-complaint-id="${complaint.ticketNumber}" data-target="#enquiryDetail"><spring:theme code="profile.details.label"/></a>
                                        <span class="notifyCount"></span>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="paginationModule">
                        <div class="paginationModule-wrapper">
                            <button class="paginationModule-control paginationModule-control_left" disabled>
                                <icon:arrow_green_right/></button>

                            <div class="paginationModule-items">
                                <div class="paginationModule-item"><a href="javascript:void(0);"
                                                                      class="paginationModule-link profile-ticket active">1</a>
                                </div>
                                <c:forEach begin="2" end="${TicketsPagesNumber}" varStatus="loop">
                                    <div class="paginationModule-item"><a href="javascript:void(0);"
                                                                          class="paginationModule-link profile-ticket">${loop.index}</a>
                                    </div>
                                </c:forEach>
                            </div>
                            <button class="paginationModule-control paginationModule-control_right">
                                <icon:arrow_green_right/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="panelTabs-head" id="questionnairesTab"><spring:theme code="general.questionnaires"/><c:if
            test="${surveyTabCount > 0}"><span class="notifyCount">${surveyTabCount}</span></c:if></div>
    <div class="panelTabs-body">
        <div class="panelModule panelModule_halfRadius">
            <survey:surveyList/> 
            <%--<survey:survey/>--%>
        </div>
    </div>
</div>

<div class="modal fade" id="enquiryDetail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body"></div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:theme code="profile.close.modal"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="companyRepresentative"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		    <div class="contentModule">
                <div class="modal-header">
                    <div class="modal-title">
                        <span class="iconElement iconElement_generalManager"><icon:person/></span><spring:theme code="profileCompany.companyRepresentative.title"/>				    
                    </div>
                    <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                        <icon:close/>
                    </button>
                </div>
                <div class="modal-body">

                   <div class="contentModule-section">
                        
                        <div class="contentModule-actions contentModule-actions_right contentModule-actions_wrap">
                            <div>
                                <button class="js-myAccount-edit btn btn_link btn_edit">
                                    <div class="js-myAccount-edit-text"><spring:theme code="profileCompany.button.edit.text"/>
                                        <span class="iconElement iconElement_edit02"><icon:edit/></span>
                                    </div>
                                    <div class="js-myAccount-edit-text hidden">
                                        <span class="iconElement iconElement_closeEdit" aria-hidden="true">&times;</span>
                                        <spring:theme code="profileCompany.button.close.edit.text"/>
                                    </div>
                                </button>
                            </div>
                        </div>                          

                        <div class="js-myAccount-edit-toggle">
                            <div class="row">
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.firstname"/></dt>
                                        <dd>${sagiaProfileRepresentativeForm.firstName}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.middlename"/></dt>
                                        <dd>${sagiaProfileRepresentativeForm.middleName}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.lastname"/></dt>
                                        <dd>${sagiaProfileRepresentativeForm.lastName}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.mobilenumber"/></dt>
                                        <dd>${sagiaProfileRepresentativeForm.mobileNumber}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.email"/></dt>
                                        <dd>${sagiaProfileRepresentativeForm.email}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.nationalid"/></dt>
                                        <dd>${sagiaProfileRepresentativeForm.nationality}</dd>
                                    </dl>
                                </div>
                            </div>
                            <div class="documentSection">
                                <div class="documentSection-headline"><spring:theme code="general.documents"/></div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="documentSection-item">
                                            <div class="documentSection-img">
                                                <span class="iconElement iconElement_pdf02"><icon:pdf02/></span>
                                            </div>
                                            <div class="documentSection-name js-attach-file-download"
                                                 data-object-id="${sagiaProfileRepresentativeForm.srId}"
                                                 data-document-id="${sagiaProfileRepresentativeForm.docId}"
                                                 style="cursor: pointer">
                                                <spring:theme code="company.representativenationalid"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="documentSection-item">
                                            <div class="documentSection-img">
                                                <span class="iconElement iconElement_pdf02"><icon:pdf02/></span>
                                            </div>
                                            <div class="documentSection-name js-attach-file-download"
                                                 data-object-id="${sagiaProfileRepresentativeForm.srId}"
                                                 data-document-id="${sagiaProfileRepresentativeForm.docGovId}"
                                                 style="cursor: pointer">
                                                <spring:theme code="company.gosicertificate"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                       
                        <div class="js-myAccount-edit-toggle" style="display: none;">
                            <form:form action="${encodedContextPath}/contacts/update-company-representative"
                                       method="post"
                                       enctype="multipart/form-data"
                                       modelAttribute="companyRepresentativeFormData"
                                       class="js-company-representative-form">
                                <input name="bpId" class=" form-control" placeholder="." type="hidden"
                                       value="${sagiaProfileRepresentativeForm.bpId}"></input>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="formInputBox">
                                            <div class="form-group">
                                                <input id="companyRepresentativeFirstName" name="firstName"
                                                       class=" form-control" placeholder="." type="text"
                                                       value="${sagiaProfileRepresentativeForm.firstName}"></input>
                                                <label class="control-label " for=""><spring:theme
                                                        code="general.firstname"/></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="formInputBox">
                                            <div class="form-group">
                                                <input id="companyRepresentativeMiddleName" name="middleName"
                                                       class=" form-control" placeholder="." type="text"
                                                       value="${sagiaProfileRepresentativeForm.middleName}"></input>
                                                <label class="control-label " for=""><spring:theme
                                                        code="general.middlename"/></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="formInputBox">
                                            <div class="form-group">
                                                <input id="companyRepresentativeLastName" name="lastName"
                                                       class=" form-control" placeholder="." type="text"
                                                       value="${sagiaProfileRepresentativeForm.lastName}"></input>
                                                <label class="control-label " for=""><spring:theme
                                                        code="general.lastname"/></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="formInputBox">
                                            <div class="form-group">
                                                <input id="companyRepresentativeMobileNumber" name="mobileNumber"
                                                       class=" form-control" placeholder="." type="text"
                                                       value="${sagiaProfileRepresentativeForm.mobileNumber}"></input>
                                                <label class="control-label " for=""><spring:theme
                                                        code="general.mobilenumber"/></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="formInputBox">
                                            <div class="form-group">
                                                <input id="companyRepresentativeEmail" name="email" class=" form-control"
                                                       placeholder="." type="text"
                                                       value="${sagiaProfileRepresentativeForm.email}"></input>
                                                <label class="control-label " for=""><spring:theme
                                                        code="general.email"/></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="formInputBox">
                                            <div class="form-group">
                                                <input id="companyRepresentativeNationality" name="nationality"
                                                       class=" form-control" placeholder="." type="text"
                                                       value="${sagiaProfileRepresentativeForm.nationality}"></input>
                                                <label class="control-label " for=""><spring:theme
                                                        code="general.nationalid"/></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="formInputFile">
                                            <div class="form-group">
                                                <input id="companyRepresentativeFileNationalId" name="fileNationalId"
                                                       class="form-control js-inputFile" type="file" value=""></input>
                                                <input name="text09" class="form-control" type="text" value=""
                                                       placeholder="" readonly tabindex="-1"></input>
                                                <label class="control-label " for=""><spring:theme
                                                        code="general.labelforfile"/></label>
                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                <div class="form-icon form-icon_reset js-inputFile-reset">
                                                    <icon:cross/></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="formInputFile">
                                            <div class="form-group">
                                                <input id="companyRepresentativeFileGovDoc" name="fileGovDoc"
                                                       class="form-control js-inputFile" type="file" value=""></input>
                                                <input id="companyRepresentativeText10" name="text10" class="form-control"
                                                       type="text" value="" placeholder="" readonly tabindex="-1"></input>
                                                <label class="control-label " for=""><spring:theme
                                                        code="general.labelforfile"/></label>
                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                <div class="form-icon form-icon_reset js-inputFile-reset">
                                                    <icon:cross/></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="acceptTerms">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="formCheckBox">
                                                <div class="form-group">
                                                    <div class="form-item">
                                                        <input id="checkbox02" name="checkbox02name"
                                                               class="form-control js-accept-terms-representative"
                                                               placeholder="." type="checkbox" value="entity name"></input>
                                                        <label class="control-label " for="checkbox02">
                                                            <span><icon:check/></span> <spring:theme
                                                                code="general.accepttermsandconditions"/>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <a href="#" class="link" target="_blank"><spring:theme
                                                    code="company.coclettertemplate"/></a>
                                        </div>
                                    </div>
                                </div>

                                <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                    <button type="button" class="btn btn-secondary"><spring:theme
                                            code="general.cancel"/></button>
                                    <button type="submit" class="btn"><spring:theme code="general.update"/></button>
                                </div>
                            </form:form>
                        </div>
                  
                  
                   </div>

                </div>
                <div class="modal-footer modal-footer_right">
                </div>
            </div>
		</div>
	</div>
</div>

<table class="profileTicketsTemplateWrapper" style="display: none;">
    <tr>
        <td class="lastUpdate"></td>
        <td class="ticketNumber"></td>
        <td class="enquiryType"></td>
        <td class="status"></td>
        <td class="details">
            <a href="#" class="link" data-toggle="modal" data-complaint-id="" data-target="#enquiryDetail"><spring:theme code="profile.details.label"/></a>
            <span class="notifyCount"></span>
        </td>
    </tr>
</table>

<script>
    var categoryOne = [];
    <c:forEach var="category" items="${complaintFormData.categoryOne}">
        categoryOne.push({
            "description": "${category.catDesc}",
            "id": "${category.catID}",
            "parentId": "${category.parentID}"
        });
    </c:forEach>

    var categoryTwo = [];
    <c:forEach var="category" items="${complaintFormData.categoryTwo}">
        categoryTwo.push({
            "description": "${category.catDesc}",
            "id": "${category.catID}",
            "parentId": "${category.parentID}"
        });
    </c:forEach>
</script>
