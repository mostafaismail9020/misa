<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">Modals</h1>
	<p class="uiTest-description">Modals are available in three sizes. XSmall and Small for general information and Default for content.
	Small modals can contain differnet svgs. Some items can be omitted e.g. modal-close button. Buttons can be placed via modifier.</p>
</div>
<!-- End of Module description-->

<div class="row">
	<div class="col-md-6">
		<ul>
			<li><a href="#" data-toggle="modal" data-target="#XSmodalTemplate01">XSmall modal</a></li>
			<li><a href="#" data-toggle="modal" data-target="#XSmodalTemplate02">XSmall modal with space for rating module</a></li>			
			<li><a href="#" data-toggle="modal" data-target="#modalTemplate01">Small modal</a></li>
			<li><a href="#" data-toggle="modal" data-target="#modalTemplate02">Small modal with space for rating module</a></li>
			<li><a href="#" data-toggle="modal" data-target="#modalTemplate03">Large modal</a></li>						
		</ul>
		
		
		<ul>
			<li><a href="#" data-toggle="modal" data-target="#zindex">zindexTest</a></li>
			<li><a href="#" data-toggle="modal" data-target="#errorModal01">errorModal01</a></li>
			<li><a href="#" data-toggle="modal" data-target="#profileUpdated">Profile Updated modal XS</a></li>
			<li><a href="#" data-toggle="modal" data-target="#requestSubmitted">Request Submitted XS</a></li>
			<li><a href="#" data-toggle="modal" data-target="#requestSubmittedApply">Request Submitted without rating XS</a></li>
			<li><a href="#" data-toggle="modal" data-target="#requestSubmittedComment">Request Submitted XS with Comment-field</a></li>
			<li><a href="#" data-toggle="modal" data-target="#licenseApplicationPayment">licenseApplicationPayment</a></li>
			<li><a href="#" data-toggle="modal" data-target="#creditCardPayment">creditCardPayment</a></li>
			<li><a href="#" data-toggle="modal" data-target="#paymentConfirmation">paymentConfirmation</a></li>
			<li><a href="#" data-toggle="modal" data-target="#sadadPaymentConfirmation">sadadPaymentConfirmation</a></li>
			<li><a href="#" data-toggle="modal" data-target="#requestSubmitted02">requestSubmitted02</a></li>
			<li><a href="#" data-toggle="modal" data-target="#savedDraft">savedDraft</a></li>
			<li><a href="#" data-toggle="modal" data-target="#supportingDocuments">supportingDocuments</a></li>
			<li><a href="#" data-toggle="modal" data-target="#savedAppointment">savedAppointment</a></li>
			<li><a href="#" data-toggle="modal" data-target="#uploadFile">uploadFile</a></li>
			<li><a href="#" data-toggle="modal" data-target="#unsavedChanges">unsavedChanges</a></li>
			<li><a href="#" data-toggle="modal" data-target="#businessActivities">businessActivities</a></li>
			<li><a href="#" data-toggle="modal" data-target="#enquiryDetail">enquiryDetail</a></li>
			<li><a href="#" data-toggle="modal" data-target="#termsConditions">termsConditions</a></li>
			<li><a href="#" data-toggle="modal" data-target="#eServiceTour">eServiceTour</a></li>
		</ul>
	</div>
</div>


</div>


<!--Template for xsmall centered modals-->
<div class="modal fade" id="zindex"  tabindex="-1" role="dialog" aria-labelledby="zindex" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Zindex Test</div>
				<!--        Close Button is optional-->
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<!--differnt svgs possible		-->					
					<icon:modal01/>
				</div>
				<div class="modal-description">
	<div class="formSelectBox">
	    <div class="form-group">
	        <select id="" name="" class="js-select2 form-control">
	        	<option></option>
				<option value="1">hello</option>
				<option value="2">world</option>
				<option value="3">hello</option>
				<option value="4">world</option>
				<option value="5">hello</option>
				<option value="6">world</option>
				<option value="7">hello</option>
				<option value="8">world</option>
			</select>
	        <label class="control-label" for="">Select</label>
	    </div>
	</div>

				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim btn-secondary" data-dismiss="modal">Secondary</button>
				<button type="button" class="btn btn_slim" data-dismiss="modal">Primary</button>
			</div>
		</div>
	</div>
</div>


<!--Template for xsmall centered modals-->
<div class="modal fade" id="XSmodalTemplate01"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">XS Modal</div>
				<!--        Close Button is optional-->
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<!--differnt svgs possible		-->					
					<icon:modal01/>
				</div>
				<div class="modal-description">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit.
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim btn-secondary" data-dismiss="modal">Secondary</button>
				<button type="button" class="btn btn_slim" data-dismiss="modal">Primary</button>
			</div>
		</div>
	</div>
</div>


<!--Template for XSmall centered modals, including secondary content (e.g. for ratings)-->
<div class="modal fade" id="XSmodalTemplate02"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">XS Modal with secondary Content</div>
				<!--        Close Button is optional-->
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<!--differnt svgs possible		-->
					<icon:modal01/>
				</div>
				<div class="modal-description">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit.
					<br>Perferendis sunt adipisci repudiandae consectetur voluptate, qui recusandae natus inventore nulla voluptatum, perspiciatis quos at deserunt incidunt sint voluptas tempora beatae ut.
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim btn-secondary" data-dismiss="modal">Secondary</button>
				<button type="button" class="btn btn_slim" data-dismiss="modal">Primary</button>
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


<!--Template for small centered modals-->
<div class="modal fade" id="modalTemplate01"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Place Modal title here</div>
				<!--        Close Button is optional-->
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<!--differnt svgs possible		-->					
					<icon:modal01/>
				</div>
				<div class="modal-description">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit.
					<br>Perferendis sunt adipisci repudiandae consectetur voluptate, qui recusandae natus inventore nulla voluptatum, perspiciatis quos at deserunt incidunt sint voluptas tempora beatae ut.
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim btn-secondary" data-dismiss="modal">Secondary</button>
				<button type="button" class="btn btn_slim" data-dismiss="modal">Primary</button>
			</div>
		</div>
	</div>
</div>


<!--Template for small centered modals, including secondary content (e.g. for ratings)-->
<div class="modal fade" id="modalTemplate02"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Place Modal title here</div>
				<!--        Close Button is optional-->
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<!--differnt svgs possible		-->
					<icon:modal01/>
				</div>
				<div class="modal-description">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit.
					<br>Perferendis sunt adipisci repudiandae consectetur voluptate, qui recusandae natus inventore nulla voluptatum, perspiciatis quos at deserunt incidunt sint voluptas tempora beatae ut.
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim btn-secondary" data-dismiss="modal">Secondary</button>
				<button type="button" class="btn btn_slim" data-dismiss="modal">Primary</button>
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


<!--Template for large modals-->
<div class="modal fade" id="modalTemplate03"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Place Modal title here</div>
				<!--        Close Button is optional-->
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				Place modules/content here
			</div>
			<div class="modal-footer modal-footer_right">
				<button type="button" class="btn btn_slim btn-secondary" data-dismiss="modal">Secondary</button>
				<button type="button" class="btn btn_slim" data-dismiss="modal">Primary</button>
			</div>
		</div>
	</div>
</div>


<!--Modal for terms and conditions. Use (data-toggle="modal" data-target="#termsConditions") on link or button to call it-->
<div class="modal fade" id="termsConditions"  tabindex="-1" role="dialog" aria-labelledby="termsConditions" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Terms &amp; Conditions</div>
				<!--        Close Button is optional-->
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="scrollWrapper">
					<div class="scrollWrapper-inner">
						<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. </p>

						<p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>

						<p>Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. </p>

						<p>Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. </p>

						<p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis. </p>

						<p>At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, At accusam aliquyam diam diam dolore dolores duo eirmod eos erat, et nonumy sed tempor et et invidunt justo labore Stet clita ea et gubergren, kasd magna no rebum. sanctus sea sed takimata ut vero voluptua. est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat. </p>
					</div>

				</div>
				<div class="row">
					<div class="col-md-6">
						<dl class="dlList dlList_separated">
							<dt>Name</dt>
							<dd>Rollo Ragnvaldsson</dd>
							<dt>Role</dt>
							<dd>Senior Project Manager</dd>
							<dt>Date</dt>
							<dd>12. March 2018</dd>															
						</dl>						
					</div>
					
					<div class="col-md-6">
						<div class="formCheckBox formCheckBox_block formCheckBox_slim">
							<div class="form-group">
								<div class="form-item">
									<input id="TermsCheckbox01" name="TermsCheckbox01name" class="form-control" placeholder="." type="checkbox" value="entity name">
									<label class="control-label " for="TermsCheckbox01">
										<span>
											<icon:check/>
										</span>I've read and agree all Terms and Conditions.
									</label>
								</div>			
								<div class="form-item">
									<input id="TermsCheckbox01" name="TermsCheckbox02name" class="form-control" placeholder="." type="checkbox" value="entity name">
									<label class="control-label " for="TermsCheckbox01">
										<span>
											<icon:check/>
										</span>I understand and declare that all the documents and data submitted and enclosed with the investment license application are valid; if proved otherwise to the Investment Authority (SAGIA), then I declare that I shall bear the consequence prescribed by law as stated in the Foreign Investment Law and its executive regulation.
									</label>
								</div>	
							</div>
						</div>						
					</div>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal">Submit</button>
			</div>
		</div>
	</div>
</div>


<!--Modal for profile update info. Use (data-toggle="modal" data-target="#profileUpdated") on link or button to call it-->
<div class="modal fade" id="profileUpdated"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Your profile information has been updated!</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:modal01/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim" data-dismiss="modal">Go to My Profile</button>
			</div>
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#errorModal01") on link or button to call it-->
<div class="modal fade" id="errorModal01"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title"></div>
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:attention-error/>
				</div>
				<div class="modal-description">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit.
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim" data-dismiss="modal">Close</button>
			</div>		
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#requestSubmittedApply") on link or button to call it-->
<div class="modal fade" id="requestSubmittedApply"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
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
				<button type="button" class="btn btn_slim" data-dismiss="modal">Apply now</button>
			</div>		
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#requestSubmitted") on link or button to call it-->
<div class="modal fade" id="requestSubmitted"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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

<!--Modal: Use (data-toggle="modal" data-target="#requestSubmittedComment") on link or button to call it-->
<div class="modal fade" id="requestSubmittedComment"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
			<div class="modal-secondaryContent modal-secondaryContent_wide">
				<div class="modal-headline">How was your experience?</div>
				<div class="ratingModule">
					<div class="ratingModule-star active"><icon:rating-star/></div>
					<div class="ratingModule-star active"><icon:rating-star/></div>
					<div class="ratingModule-star"><icon:rating-star-empty/></div>
					<div class="ratingModule-star"><icon:rating-star-empty/></div>
					<div class="ratingModule-star"><icon:rating-star-empty/></div>
				</div>
				<div class="modal-body">
					<div class="formTextArea formTextArea_slim">
                        <div class="form-group">
                            <textarea id="extensionReason" name="extensionReason" class="form-control"
                                      placeholder="."></textarea>
                            <label class="control-label" for="">
                                Why was your Experience so bad?
                                <%--
                                <spring:theme code=""/>
                                --%>
                            </label>
                        </div>
                    </div>
					<%--
					<formElement:formTextArea areaCSS="form-control" containerCSS="formTextArea_slim"
							idKey="govDocs.comments"
                            labelKey="govDocs.comments"
                    		path="comments" mandatory="false"/> 
					--%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn_slim" data-dismiss="modal">send feedback</button>
				</div>
			</div>			
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#licenseApplicationPayment") on link or button to call it-->
<div class="modal fade" id="licenseApplicationPayment"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">License Application Payment</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:payment01/>
				</div>
				<div class="modal-description">
					<div class="tableModule tableModule_footer">
						<table class="tableModule-table">
							<tbody class="tableModule-body">
								<tr>
									<td>License Fee</td>
									<td>1234567,00 SAR</td>
								</tr>
								<tr>
									<td>Tax</td>
									<td>123,45 SAR</td>
								</tr>
							</tbody>
							<tfoot class="tableModule-footer">
								<tr>
									<td>Total</td>
									<td>3333333,45 SAR</td>
								</tr>
							</tfoot>
						</table>
					</div> 
				</div>
			</div>
			<div class="modal-secondaryContent">
				<div class="modal-footer modal-footer_centered modal-footer_iconButton">
					<div class="text-center">
						<span class="iconElement iconElement_iconButton"><icon:creditCard/></span>
						<button type="button" class="btn btn_slim" data-dismiss="modal">Pay with credit card</button>
					</div>
					<div class="text-center">
						<span class="iconElement iconElement_iconButton"><icon:sadad/></span>
						<button type="button" class="btn btn_slim" data-dismiss="modal">Pay with SADAD</button>
					</div>
				</div>
			</div>			
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#creditCardPayment") on link or button to call it-->
<div class="modal fade" id="creditCardPayment"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Credit Card Payment</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:payment02/>
				</div>
				<div class="modal-description">
					<p>Choose your credit card type</p>
					<form action="">
						<div class="row">
							<div class="col">
								<div class="formRadioPayment">

									<div class="form-group">

										<div class="form-item">
											<input id="pay01" name="radioPay01" class="form-control" type="radio" value="">
											<label for="pay01" class="control-label"><icon:creditCard_visa/></label>
										</div>

										<div class="form-item">
											<input id="pay02" name="radioPay01" class="form-control" type="radio" value="">
											<label for="pay02" class="control-label"><icon:creditCard_master/></label>
										</div>

										<div class="form-item">
											<input id="pay03" name="radioPay01" class="form-control" type="radio" value="">
											<label for="pay03" class="control-label"><icon:creditCard_dc/></label>
										</div>

										<div class="form-item">
											<input id="pay04" name="radioPay01" class="form-control" type="radio" value="">
											<label for="pay04" class="control-label"><icon:creditCard_ae/></label>
										</div>

										<div class="form-item">
											<input id="pay05" name="radioPay01" class="form-control" type="radio" value="">
											<label for="pay05" class="control-label"><icon:creditCard_jcb/></label>
										</div>			

									</div>
								</div>  							
							</div>
						</div>

						<div class="row">
							<div class="col">
								<div class="formInputBox">
									<div class="form-group">
										<input id="" class="form-control" placeholder="." value="" type="text">
										<label class="control-label" for="">
											Name on Card
										</label>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<div class="formInputBox">
									<div class="form-group">
										<input id="" class="form-control" placeholder="." value="" type="text">
										<label class="control-label" for="">
											Card Number
										</label>
									</div>
								</div>		
							</div>
						</div>
						<div class="row">						
							<div class="col-md-6">
								<div class="formInputBox formInputBox_group ">
									<div class="form-group">
										<input id="" class="form-control js-form-control_date" placeholder="." value="" type="text">
										<label class="control-label" for="">
											Expiry Date
										</label>
										<div class="formInputBox-append">
											<span class="formInputBox-text"><icon:calendar-gray/></span>
										</div>				
									</div>
								</div>		
							</div>
							<div class="col-md-6">
								<div class="formInputBox formInputBox_group">
									<div class="form-group">
										<input id="" class="form-control" placeholder="." value="" type="text">
										<label class="control-label" for="">
											CVV
										</label>
										<div class="formInputBox-append">
											<span class="formInputBox-text formInputBox-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
											</span>
										</div>		
									</div>
								</div>								
							</div>
						</div>
					</form>					
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal">Agree and proceed</button>
			</div>					
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#paymentConfirmation") on link or button to call it-->
<div class="modal fade" id="paymentConfirmation"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Payment Confirmation</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:paymentConfirm02/>
				</div>
				<div class="modal-description modal-description_largeMargin">
					<div class="row">
						<div class="col-md-6">
							<div class="modal-subheadline">Card Information</div>
							<dl class="dlList dlList_separated">
								<dt>Credit card type</dt>
								<dd>VISA</dd>
								<dt>Name on card</dt>
								<dd>Rollo Ragnvaldsson</dd>
								<dt>Card Number</dt>
								<dd>1234-5678-9012-3456</dd>
								<dt>Expires on</dt>
								<dd>July, 2024</dd>
								<dt>Security code</dt>
								<dd>234</dd>
							</dl>          
						</div>
						<div class="col-md-6">
							<div class="modal-subheadline">Payment Details</div>
							<dl class="dlList dlList_separated">
								<dt>License Application Payment</dt>
								<dd>1234567 SAR</dd>
								<dt>DISCOUNT</dt>
								<dd>0.00 SAR</dd>
								<dt>SUBTOTAL</dt>
								<dd>1234567 SAR</dd>
								<dt>TAX</dt>
								<dd>123,45 SAR</dd>
								<dt class="dlList-item dlList-item_highlighted">TOTAL</dt>
								<dd class="dlList-item dlList-item_highlighted">3333333 SAR</dd>
							</dl> 							
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal">Confirm Payment</button>
			</div>					
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#sadadPaymentConfirmation") on link or button to call it-->
<div class="modal fade" id="sadadPaymentConfirmation"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">SADAD Payment Confirmation</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:paymentConfirm01/>
				</div>
				<div class="modal-description modal-description_largeMargin">
					<div class="row">
						<div class="col-md-6">
							<div class="modal-subheadline">SADAD Account Information</div>
							<dl class="dlList dlList_separated">
								<dt>sadad id account</dt>
								<dd>1234567</dd>
								<dt>Biller Details</dt>
								<dd>Biller Name</dd>
								<dt>Service</dt>
								<dd>License Application Service</dd>
								<dt>Border Number</dt>
								<dd>200000650653</dd>
							</dl>          
						</div>
						<div class="col-md-6">
							<div class="modal-subheadline">Payment Details</div>
							<dl class="dlList dlList_separated">
								<dt>License Application Payment</dt>
								<dd>1234567 SAR</dd>
								<dt>DISCOUNT</dt>
								<dd>0.00 SAR</dd>
								<dt>SUBTOTAL</dt>
								<dd>1234567 SAR</dd>
								<dt>TAX</dt>
								<dd>123,45 SAR</dd>
								<dt class="dlList-item dlList-item_highlighted">TOTAL</dt>
								<dd class="dlList-item dlList-item_highlighted">3333333 SAR</dd>
							</dl> 							
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal">Confirm Payment</button>
			</div>					
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#requestSubmitted02") on link or button to call it-->
<div class="modal fade" id="requestSubmitted02"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
			<div class="modal-footer modal-footer_column">
				<button type="button" class="btn btn_slim" data-dismiss="modal">go to my dashboard</button>
				<a href="#" class="link link_nowrap">
					<span class="iconElement iconElement_cloud"><icon:download/></span>Download Invoice
				</a>
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

<!--Modal: Use (data-toggle="modal" data-target="#savedDraft") on link or button to call it-->
<div class="modal fade" id="savedDraft"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Your Draft has been saved</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">			
					<icon:modal01/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim btn-secondary" data-dismiss="modal">Continue editing</button>
				<button type="button" class="btn btn_slim" data-dismiss="modal">go to my dashboard</button>
			</div>
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#supportingDocuments") on link or button to call it-->
<div class="modal fade" id="supportingDocuments"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header modal-header_smallPDB">
				<div class="modal-title">Supporting Documents</div>
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>				
			</div>
			<div class="modal-body">
				<div class="modal-description modal-description_largeMargin">
					<p class="text-bold">Service Request: Selling Service of Real Estate</p>
				</div>
				<div class="modal-description modal-description_largeMargin">
					<ul class="downloadList downloadList_secondary">
						<li class="downloadList-item">
							<div class="downloadList-description">
								<span class="iconElement iconElement_pdf"><icon:pdf/></span>
								Copy of the Investments License
							</div>
							<div class="downloadList-actions">
								<a href="#" class="link link_nowrap">
									<span class="iconElement iconElement_cloud02"><icon:download/></span>
									Download
								</a>
							</div>
						</li>
						<li class="downloadList-item">
							<div class="downloadList-description">
								<span class="iconElement iconElement_pdf"><icon:pdf/></span>
								Processing of the Standard Forms
							</div>
							<div class="downloadList-actions">
								<a href="#" class="link link_nowrap">
									<span class="iconElement iconElement_cloud02"><icon:download/></span>
									Download
								</a>
							</div>
						</li>
						<li class="downloadList-item">
							<div class="downloadList-description">
								<span class="iconElement iconElement_pdf"><icon:pdf/></span>
								Trade Name Reseration
							</div>
							<div class="downloadList-actions">
								<a href="#" class="link link_nowrap">
									<span class="iconElement iconElement_cloud02"><icon:download/></span>
									Download
								</a>
							</div>
						</li>
						<li class="downloadList-item">
							<div class="downloadList-description">
								<span class="iconElement iconElement_pdf"><icon:pdf/></span>
								Sagia Licensing
							</div>
							<div class="downloadList-actions">
								<a href="#" class="link link_nowrap">
									<span class="iconElement iconElement_cloud02"><icon:download/></span>
									Download
								</a>
							</div>
						</li>
						<li class="downloadList-item">
							<div class="downloadList-description">
								<span class="iconElement iconElement_pdf"><icon:pdf/></span>
								Passport and IQAMA
							</div>
							<div class="downloadList-actions">
								<a href="#" class="link link_nowrap">
									<span class="iconElement iconElement_cloud02"><icon:download/></span>
									Download
								</a>
							</div>
						</li>			               
					</ul>					
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim btn_link" data-dismiss="modal">
				<span class="iconElement iconElement_cloud"><icon:download/></span>
				Download All</button>
			</div>					
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#savedAppointment") on link or button to call it-->
<div class="modal fade" id="savedAppointment"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Your appointment has been saved!</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">			
					<icon:modal01/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim btn_outline" data-dismiss="modal">Go to my appointments</button>
				<button type="button" class="btn btn_slim" data-dismiss="modal">View appointment details</button>
			</div>
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#uploadFile") on link or button to call it-->
<div class="modal fade" id="uploadFile"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<form action="" class="js-formInputFileBox">
				<div class="modal-header">
					<div class="modal-title">Upload your document</div>
				</div>
				<div class="modal-body">

					<div class="formInputFileBox">
						<div class="form-group">
							<div class="form-icon form-icon_browse">
								<icon:upload/>
							</div>		
							<input class="form-control js-inputFile" type="file" name="files[]" id="fileBoxModal"/>
							<label class="control-label" for="fileBoxModal">Choose a file<span class="formInputFileBox-dragndrop"> or drag it here</span>.</label>			
						</div>
						<div class="formInputFileBox-uploading">Uploading&hellip;</div>
						<div class="formInputFileBox-success">Done!</div>
						<div class="formInputFileBox-error">Error! <span></span>.</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" type="submit" class="btn btn_slim btn_round">Upload file</button>
				</div>
			</form>	
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#unsavedChanges") on link or button to call it-->
<div class="modal fade" id="unsavedChanges"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<form action="" class="js-formInputFileBox">
				<div class="modal-header modal-header_smallPDB">
					<div class="modal-title">You have unsaved changes</div>
					<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
						<icon:close/>
					</button>	
				</div>
				<div class="modal-body">
					<div class="modal-description modal-description_largeMargin modal-description_smallText">
						There are still changes in the form that were not yet submited. In order to amend those informations you need to either submit those changes or dismiss them. 
					</div>
				</div>
				<div class="modal-footer modal-footer_spaceBetween">
					<button type="button" type="submit" class="btn btn-warning btn_round btn_slim" data-dismiss="modal">Dismiss Changes</button>
					<button type="button" type="submit" class="btn btn_round btn_slim">Submit Changes</button>
				</div>
			</form>	
		</div>
	</div>
</div>

<!--Modal: Use (data-toggle="modal" data-target="#businessActivities") on link or button to call it-->
<div class="modal fade" id="businessActivities"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog_businessActivities" role="document">
		<div class="modal-content">
			<form action="" class="">
				<div class="modal-header">
					<div class="modal-title">Business Activities</div>
					<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
						<icon:close/>
					</button>
					<ul class="baBreadcrumb">
						<li class="baBreadcrumb-item">
							<a href="#" class="baBreadcrumb-link">Section</a>
							<div class="baBreadcrumb-subMenu">
								<ul class="undottedList">
									<li class="undottedList-item">C - Manufacturing</li>
								</ul>
								<a href="#" class="btn btn_outline btn_round btn_slim">Edit <span class="iconElement iconElement_editButton"><icon:edit/></span></a>		
							</div>
						</li>
						<li class="baBreadcrumb-item">
							<a href="#" class="baBreadcrumb-link">Division</a>
							<div class="baBreadcrumb-subMenu">
								<ul class="undottedList">
									<li class="undottedList-item">264 - Manufacture of consumer electronics</li>
									<li class="undottedList-item">265 - Manufacture of measuring, testing, navigating and control equipment; watches and clocks</li>
									<li class="undottedList-item">274 - Manufacture of electric lighting equipment</li>
									<li class="undottedList-item">275 - Manufacture of domestic appliances</li>
								</ul>
								<a href="#" class="btn btn_outline btn_round btn_slim">Edit <span class="iconElement iconElement_editButton"><icon:edit/></span></a>		
							</div>
						</li>
						<li class="baBreadcrumb-item">
							<a href="#" class="baBreadcrumb-link">Group</a>
							<div class="baBreadcrumb-subMenu">
								<ul class="undottedList">
									<li class="undottedList-item">group test</li>
								</ul>
								<a href="#" class="btn btn_outline btn_round btn_slim">Edit <span class="iconElement iconElement_editButton"><icon:edit/></span></a>								
							</div>
						</li>
						<li class="baBreadcrumb-item">
							<a href="#" class="baBreadcrumb-link">Class</a>
							<div class="baBreadcrumb-subMenu">
								<ul class="undottedList">
									<li class="undottedList-item">class test</li>
								</ul>
								<a href="#" class="btn btn_outline btn_round btn_slim">Edit <span class="iconElement iconElement_editButton"><icon:edit/></span></a>								
							</div>
						</li>
						<li class="baBreadcrumb-item">
							<a href="#" class="baBreadcrumb-link">Branch</a>
							<div class="baBreadcrumb-subMenu">
								<ul class="undottedList">
									<li class="undottedList-item">branch test</li>
								</ul>
								<a href="#" class="btn btn_outline btn_round btn_slim">Edit <span class="iconElement iconElement_editButton"><icon:edit/></span></a>								
							</div>
						</li>
						<li class="baBreadcrumb-item">
							<a href="#" class="baBreadcrumb-link active">Activity</a>
						</li>		
					</ul>
					<div class="baModule-headline">License type: <span>Industrial</span></div>
				</div>
				<div class="modal-body">
<!--				markup for section selection. Place content inside baModule-->
					<div class="baModule">
						<div class="baModule-header">
							<div class="baModule-title">Please choose your section:</div>
							<div class="baModule-search">
								<div class="searchInputBox searchInputBox_dark">
									<input class="searchInputBox-input" type="text" placeholder="Search"/>
								</div>								
							</div>
						</div>
						<ul class="baList baList_columns">
							<li class="baList-item"><a href="#">A - Agriculture, forestry and fishing</a></li>
							<li class="baList-item"><a href="#">B - Mining and quarrying</a></li>
							<li class="baList-item"><a href="#">C - Manufacturing</a></li>
							<li class="baList-item"><a href="#">D - Electricity, gas, steam and air conditioning supply</a></li>
							<li class="baList-item"><a href="#">E - Water supply; sewerage, waste management and remediation activities</a></li>
							<li class="baList-item"><a href="#">F - Construction</a></li>
							<li class="baList-item"><a href="#">G - Wholesale and retail trade; repair of motor vehicles and motorcycles</a></li>
							<li class="baList-item"><a href="#">H - Transportation and storage</a></li>
							<li class="baList-item"><a href="#">I - Accommodation and food service activities</a></li>
							<li class="baList-item"><a href="#">J - Information and communication</a></li>
							<li class="baList-item"><a href="#">K - Financial and insurance activities</a></li>
							<li class="baList-item"><a href="#">L - Real estate activities</a></li>
							<li class="baList-item"><a href="#">M - Professional, scientific and technical activities</a></li>
							<li class="baList-item"><a href="#">N - Administrative and support service activities</a></li>
							<li class="baList-item"><a href="#">O - Public administration and defence; compulsory social security</a></li>
							<li class="baList-item"><a href="#">P - Education</a></li>
							<li class="baList-item"><a href="#">Q - Human health and social work activities</a></li>
							<li class="baList-item"><a href="#">R - Arts, entertainment and recreation</a></li>
							<li class="baList-item"><a href="#">S - Other service activities</a></li>
							<li class="baList-item"><a href="#">T - Activities of households as employers; undifferentiated goods- and services-producing activities of households for own use</a></li>
							<li class="baList-item"><a href="#">U - Activities of extraterritorial organizations and bodies</a></li>
						</ul>						
					</div>
					
<!--				markup for activities selection. Place content inside baModule-->
					<div class="baModule">
						<div class="baModule-header">
							<div class="baModule-title">Please choose your division:</div>
							<div class="baModule-search">
								<div class="searchInputBox searchInputBox_dark">
									<input class="searchInputBox-input" type="text" placeholder="Search"/>
								</div>								
							</div>
						</div>
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">C - Manufacturing</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
10 - Manufacture of food products
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
11 - Manufacture of beverages
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
12 - Manufacture of tobacco products
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
13 - Manufacture of textiles
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
14 - Manufacture of wearing apparel
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
15 - Manufacture of leather and related products
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
16 - Manufacture of wood and of products of wood and cork, except furniture; manufacture of articles of straw and plaiting materials
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
17 - Manufacture of paper and paper products
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
18 - Printing and reproduction of recorded media
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
19 - Manufacture of coke and refined petroleum products
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
20 - Manufacture of chemicals and chemical products
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
21 - Manufacture of basic pharmaceutical products and pharmaceutical preparations
											</label>
										</div>											
									</div>
								</div>								
							</div>
							
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
22 - Manufacture of rubber and plastics products
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
23 - Manufacture of other non-metallic mineral products
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
24 - Manufacture of basic metals
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
25 - Manufacture of fabricated metal products, except machinery and equipment
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
26 - Manufacture of computer, electronic and optical products
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
27 - Manufacture of electrical equipment
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
28 - Manufacture of machinery and equipment n.e.c.
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
29 - Manufacture of motor vehicles, trailers and semi-trailers
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
30 - Manufacture of other transport equipment
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
31 - Manufacture of furniture
											</label>
										</div>	
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
32 - Other manufacturing
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
33 - Repair and installation of machinery and equipment
											</label>
										</div>								
									</div>
								</div>
							
							</div>
						</div>
					</div>
					
<!--				markup for activities selection. Place content inside baModule-->
					<div class="baModule">
						<div class="baModule-header">
							<div class="baModule-title">Please choose your group:</div>
							<div class="baModule-search">
								<div class="searchInputBox searchInputBox_dark">
									<input class="searchInputBox-input" type="text" placeholder="Search"/>
								</div>								
							</div>
						</div>
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">26 - Manufacture of computer, electronic and optical products</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												261 - Manufacture of electronic components and boards
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												262 - Manufacture of computers and peripheral equipment
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												263 - Manufacture of communication equipment
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												264 - Manufacture of consumer electronics
											</label>
										</div>																			<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265 - Manufacture of measuring, testing, navigating and control equipment; watches and clocks
											</label>
										</div>									
									</div>
								</div>								
							</div>
							
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												266 - Manufacture of irradiation, electromedical and electrotherapeutic equipment
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												267 - Manufacture of optical instruments and photographic equipment
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												268 - Manufacture of magnetic and optical media
											</label>
										</div>								
									</div>
								</div>
							
							</div>
						</div>
						
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">27 - Manufacture of electrical equipment</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
271 - Manufacture of electric motors, generators, transformers and electricity distribution and control apparatus
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
272 - Manufacture of batteries and accumulators
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
273 - Manufacture of wiring and wiring devices
											</label>
										</div>									
									</div>
								</div>								
							</div>
							
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
274 - Manufacture of electric lighting equipment
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
275 - Manufacture of domestic appliances
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
279 - Manufacture of other electrical equipment
											</label>
										</div>								
									</div>
								</div>
							
							</div>
						</div>						
					</div>
					
<!--				markup for activities selection. Place content inside baModule-->
					<div class="baModule">
						<div class="baModule-header">
							<div class="baModule-title">Please choose your class:</div>
							<div class="baModule-search">
								<div class="searchInputBox searchInputBox_dark">
									<input class="searchInputBox-input" type="text" placeholder="Search"/>
								</div>								
							</div>
						</div>
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">264 - Manufacture of consumer electronics</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												2640 - Manufacture of consumer electronics
											</label>
										</div>								
									</div>
								</div>								
							</div>
						</div>
						
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">265 - Manufacture of measuring, testing, navigating and control equipment; watches and clocks</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												2651 - Manufacture of measuring, testing, navigating and control equipment
											</label>
										</div>								
									</div>
								</div>								
							</div>
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												2652 - Manufacture of watches and clocks
											</label>
										</div>								
									</div>
								</div>								
							</div>							
						</div>
						
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">274 - Manufacture of electric lighting equipment</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												2740 - Manufacture of electric lighting equipment
											</label>
										</div>								
									</div>
								</div>								
							</div>							
						</div>
						
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">275 - Manufacture of domestic appliances</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												2750 - Manufacture of domestic appliances
											</label>
										</div>								
									</div>
								</div>								
							</div>							
						</div>												
					</div>
					
<!--				markup for activities selection. Place content inside baModule-->
					<div class="baModule">
						<div class="baModule-header">
							<div class="baModule-title">Please choose your branch:</div>
							<div class="baModule-search">
								<div class="searchInputBox searchInputBox_dark">
									<input class="searchInputBox-input" type="text" placeholder="Search"/>
								</div>								
							</div>
						</div>
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">Headline</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												Lorem ipsum dolor sit amet, consectetur adipisicing elit.
											</label>
										</div>								
									</div>
								</div>								
							</div>
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												Lorem ipsum dolor sit amet, consectetur adipisicing elit.
											</label>
										</div>								
									</div>
								</div>								
							</div>							
						</div>
					</div>					
					
<!--				markup for activities selection. Place content inside baModule-->
					<div class="baModule">
						<div class="baModule-header">
							<div class="baModule-title">Please choose your activities:</div>
							<div class="baModule-search">
								<div class="searchInputBox searchInputBox_dark">
									<input class="searchInputBox-input" type="text" placeholder="Search"/>
								</div>								
							</div>
						</div>
						<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">2640 - Manufacture of consumer electronics</div>
						<div class="row">
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265101 - Manufacture of pulse (signal) generators
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265102 - Manufacture of gps devices
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265103 - Manufacture of sensitive balances and non-optical microscopes
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265104 - Manufacture of devices for measuring electrical and non-electrical quantities such as: detectors vibrations, radiation detection and monitoring instruments, phone jamming gauges etc.
											</label>
										</div>																			<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265105 - Manufacture of meteorological instruments, navigation equipment, surveying instruments etc., radar equipment
											</label>
										</div>									
									</div>
								</div>								
							</div>
							
							<div class="col-md-6">
								<div class="formCheckBox formCheckBox_block formCheckBox_slim">
									<div class="form-group">
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265106 - Manufacture of setting or automatic controls instruments such as: thermostats, pressure controller etc.
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265107 - Manufacture of consumption meters (e.g. water, gas)
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265108 - Manufacture of physical properties testing and inspection equipment, pneumatic gauges, pressure or fluid density measurement devices etc.
											</label>
										</div>
										<div class="form-item">
											<input id="" name="" class="form-control" placeholder="." type="checkbox" value="">
											<label class="control-label " for="">
												<span><icon:check/></span>
												265109 - Manufacture of devices used in continuous automated measurement and control variables: hermometers liquid-in-glass and bimetal types (except medical), humidistats, pressure or viscosity measuring devices etc.
											</label>
										</div>								
									</div>
								</div>
							
							</div>
						</div>
					</div>	
				</div>
				<div class="modal-footer modal-footer_centered">
					<button type="button" type="submit" class="btn btn_outline btn_slim" data-dismiss="modal">Cancel</button>
					<button type="button" type="submit" class="btn btn_slim">Next</button>
				</div>
			</form>	
		</div>
	</div>
</div>









<div class="modal fade" id="enquiryDetail"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title" id="exampleModalLabel">Ticket Number: 10045448</div>
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="ticketModule">
					<div class="ticketModule-top">
						<div class="row">
							<div class="col-md-4">

								<div class="ticketModule-details">
									<p><span class="ticketModule-label">Topic:</span> Amendment Of Licenses</p>
									<p><span class="ticketModule-label">Subject:</span> Quis nostrum exercitationem ullam corporis</p>
									<p><span class="ticketModule-label">Support documents:</span></p>
									
									<ul class="downloadList downloadList_secondary">
										<li class="downloadList-item downloadList-item_noPadding">
											<div class="downloadList-description">
												<span class="iconElement iconElement_pdf"><icon:pdf/></span>
												Article of Association
											</div>
											<div class="downloadList-actions">
												<a href="#" class="link link_nowrap">
													<span class="iconElement iconElement_cloud02"><icon:download/></span>
													Download
												</a>
											</div>
										</li>
										<li class="downloadList-item downloadList-item_noPadding">
											<div class="downloadList-description">
												<span class="iconElement iconElement_pdf"><icon:pdf/></span>
												Contract of Sale
											</div>
											<div class="downloadList-actions">
												<a href="#" class="link link_nowrap">
													<span class="iconElement iconElement_cloud02"><icon:download/></span>
													Download
												</a>
											</div>
										</li>               
									</ul>
									
									<a href="#" class="link js-showTarget" data-show-target="addDocuments" data-hide-self="true">Add documents</a>
									
									<div id="addDocuments" class="hidden">
										<form action="">
											<div class="formInputFile">
												<div class="form-group">
													<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
													<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly >
													<label class="control-label " for="">Label for file</label>
													<div class="form-icon form-icon_browse">
														<icon:upload/>
													</div>
													<div class="form-icon form-icon_reset js-inputFile-reset">
														<icon:cross/>
													</div>
												</div>
											</div>										
											<div class="ticketModule-actions">
												<button class="btn">Upload</button>
											</div>
										
										</form>
									</div>


								</div>

							</div>

							<div class="col-md-8">

								<ul class="messageList">
									<li class="messageList-item">

										<div class="messageList-content">
											<h2 class="messageList-name">You</h2>
											<h3 class="messageList-date">04 Jan 2018</h3>
											<div class="messageList-message">
												<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Enim optio, voluptatum possimus officia quas.</p>
											</div>
										</div>
									</li>
									<li class="messageList-item">

										<div class="messageList-content">
											<h2 class="messageList-name">Omar Khan (SAGIA advisor)</h2>
											<h3 class="messageList-date">05 Jan 2018</h3>
											<div class="messageList-message">
												<p>Enim optio, voluptatum possimus officia quas.</p>
												<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quis et sit reiciendis temporibus! Quo doloremque quam officia hic molestiae consectetur itaque quod quidem nisi sapiente iste blanditiis, ad nesciunt provident?</p>
											</div>
										</div>
									</li>
								</ul>


								<div class="formTextArea">
									<div class="form-group">
										<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
										<label class="control-label" for="">
											Comments
										</label>
									</div>
								</div>
								<div class="ticketModule-actions">
									<button class="btn">Send message</button>
								</div>

							</div>
						</div>
					</div>
					<div class="ticketModule-bottom">

						<ul class="timestampList">
							<li class="timestampList-item">
								<div class="timestampList-info">
									<span class="timestampList-timestampLabel">Timestamp:</span>
									<span class="timestampList-timestamp">02.02.2018 12:57pm</span>
								</div>
								<div class="timestampList-message">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
									<p>Explicabo dolorem obcaecati magnam vel adipisci vero aut ab ipsum repellat, consequatur cupiditate neque consequuntur consectetur, ratione, eaque, harum sunt quae beatae!</p>
								</div>
							</li>
							<li class="timestampList-item">
								<div class="timestampList-info">
									<span class="timestampList-timestampLabel">Timestamp:</span>
									<span class="timestampList-timestamp">02.02.2018 12:57pm</span>
								</div>
								<div class="timestampList-message">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
									<p>Explicabo dolorem obcaecati magnam vel adipisci vero aut ab ipsum repellat, consequatur cupiditate neque consequuntur consectetur, ratione, eaque, harum sunt quae beatae!</p>
								</div>
							</li>
						</ul>

					</div>

				</div>
			</div>

		</div>
	</div>
</div>



<!--Modal: Use (data-toggle="modal" data-target="#eServiceTour") on link or button to call it-->
<div class="modal fade" id="eServiceTour"  tabindex="-1" role="dialog" aria-labelledby="eServiceTour" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>				
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">			
					<icon:tutorial/>
				</div>
				<div class="modal-title modal-title_uppercase">Welcome to your Sagia Dashboard</div>
				<div class="modal-description modal-description_eService">
					With the new designed dashboard, it's easier to access your most important information and services.
				</div>
			</div>
			<div class="modal-footer modal-footer_wrap">
				<button type="button" class="btn btn_slim js-eServiceTour-start" data-dismiss="modal">Show me arround</button>
				<a class="btn btn_slim btn_link btn_inFooterModal js-skipTutorial" data-dismiss="modal">Don't show this message again<a>
			</div>
		</div>
	</div>
</div>

