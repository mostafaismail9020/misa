<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<div class="mainSection mainSection_dark">
	<div class="container">
		<div class="mainSection-header">
	        <h1 class="mainSection-headline">Legal Consultation Services</h1>
		</div>
	</div>
</div>
<div class="mainSection mainSection_dark mainSection_noPadding">
  <div class="container">
    <a href="${encodedContextPath}/my-sagia/sagia-profile" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Account Overview</a>
  </div>
</div>
<div class="mainSection mainSection_dark mainSection_xsmallPaddingTop">
	<div class="container">
		<form:form action="${encodedContextPath}"
			enctype="multipart/form-data"
			method="post"
			modelAttribute="">
			<div class="panelModule panelModule_halfRadius">
		        <div class="contentModule">
		            <div class="contentModule-section">
						<div class="contentModule-headline">
		                    <span class="iconElement iconElement_enquiry3"><icon:info/></span>Request Informations
		                </div>

		                 

	                        <%-- <formElement:formInputBox idKey="details.subject"
	                                                  labelKey="Subject" path="details.TextMsg" inputCSS="text"
	                                                  mandatory="true"/> --%>
                        <div class="formInputBox">
							<div class="form-group">
								<input id="" class="form-control" placeholder="." value="" type="text">
								<label class="control-label" for="">
									Subject
								</label>				
							</div>
						</div>
                        <%-- <formElement:formTextArea areaCSS="form-control"
                                                  idKey="details.TextMsg" labelKey="Message" path="details.TextMsg"
                                                  mandatory="true"/> --%>
                        <div class="formTextArea">
							<div class="form-group">
								<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
								<label class="control-label" for="">
									Message
								</label>
							</div>
						</div>

					</div>
					<div class="contentModule-section">
                        <div class="contentModule-headline">
                            <icon:documents/>
                            Attachments
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file0" name="files[0]" class="form-control js-inputFile" accept="application/pdf" type="file" value=""></input>
                                        <input id="text05" name="text05" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"></input>
                                        <label class="control-label " for="">* Label for file </label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file1" name="files[1]" class="form-control js-inputFile" accept="application/pdf" type="file" value=""></input>
                                        <input id="text02" name="text02" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"></input>
                                        <label class="control-label " for="">Label for file</label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file2" name="files[2]" class="form-control js-inputFile" accept="application/pdf" type="file" value=""></input>
                                        <input id="text03" name="text03" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"></input>
                                        <label class="control-label " for="">Label for file</label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file3" name="files[3]" class="form-control js-inputFile" accept="application/pdf" type="file" value=""></input>
                                        <input id="text04" name="text04" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"></input>
                                        <label class="control-label " for="">Label for file</label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                        </div>
		            </div>
		        </div>
		    </div>
		    <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
	        	<button type="reset" class="btn btn-secondary">Cancel</button>
	        	<button type="submit" class="btn">Submit</button>
	        </div>
		</form:form>
	</div>
</div>