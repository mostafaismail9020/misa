<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
	
	<!-- Module description-->
	<div class="uiTest">
		<h1 class="uiTest-headline">enumList</h1>
		<p class="uiTest-description">styling on enumList. this one can be validatable and collapsible. Entries can also be disabled. <br>js is currently set to autocomplete (valid) each list-item when clicking forward (next-button, js-stepsList-next)</p>
	</div>
	<!-- End of Module description-->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-8">
		<ol class="enumList enumList_validatable enumList_collapsible js-stepsList" data-keyClass="enumList">
            <li class="enumList-item enumList-item_valid">
                <div class="enumList-item-head js-stepsList-toggle">
                    <div class="contentModule-headline">Headline 1 (initial completed Entry)</div>
                </div>
                <div class="enumList-item-body">
                    <div class="row">
                    	<div class="col">
                    	Content 1
                    	</div>
                    </div>
                    <div class="contentModule-actions contentModule-actions_noPadding contentModule-actions_right accountLogin-content-formSubmitSubSection">
                        <div>
                            <button class="btn js-stepsList-next">Continue</button>
                        </div>
                    </div>
                </div>
            </li>

            <li class="enumList-item enumList-item_active">
                <div class="enumList-item-head js-stepsList-toggle">
                    <div class="contentModule-headline">Headline 2 (initial active Entry)</div>
                </div>
                <div class="enumList-item-body">
                    <div class="row">
                    	<div class="col">
                    		Content 2
                    	</div>
                    </div>
                    <div class="contentModule-actions contentModule-actions_noMargin contentModule-actions_spaceBetween accountLogin-content-formSubmitSubSection">
                        <div>
                            <button class="btn btn-secondary js-stepsList-prev">Back</button>
                        </div>
                        <div>
                            <button class="btn js-stepsList-next">Continue</button>
                        </div>
                    </div>
                </div>
            </li>

            <li class="enumList-item enumList-item_disabled">
                <div class="enumList-item-head js-stepsList-toggle">
                    <div class="contentModule-headline">Headline 3 (disabled Entry)</div>
                </div>
                <div class="enumList-item-body">
                    <div class="row">
                    	<div class="col">
                    		Content 3
                    	</div>
                    </div>
                    <div class="contentModule-actions contentModule-actions_noPadding contentModule-actions_spaceBetween accountLogin-content-formSubmitSubSection">
                        <div>
                            <button class="btn btn-secondary js-stepsList-prev">Back</button>
                        </div>
                        <div>
                            <button class="btn js-stepsList-next">Continue</button>
                        </div>
                    </div>
                </div>
            </li>

            <li class="enumList-item">
                <div class="enumList-item-head js-stepsList-toggle">
                    <div class="contentModule-headline">Headline 4</div>
                </div>
                <div class="enumList-item-body">
                    <div class="row">
                    	<div class="col">
                    		Content 4
                    	</div>
                    </div>
                    <div class="contentModule-actions contentModule-actions_noPadding contentModule-actions_spaceBetween accountLogin-content-formSubmitSubSection">
                        <div>
                            <button class="btn btn-secondary js-stepsList-prev">Back</button>
                        </div>
                    </div>
                </div>
            </li>
        </ol>
    	</div>
        <div class="col-md-3"></div>
	</div>
</div>