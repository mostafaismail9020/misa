<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>



<%-- TODO: SAH-1344 --%>

<style type="text/css">
    .investsaudiHeader,
    .sagiaNavigation,
    .investsaudiFooter,
    .sagiaHead-paddingHelper,
    .sagiaHead {
        display: none;
    }
</style>



<div class="accountLogin">
    <div class="accountLogin-background">
        <div class="container">
            <div class="accountLogin-headline">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.svg">
                </a>
            </div>

            <div class="panelModule panelModule_halfRadius accountLogin-content accountLogin-content_small">
                <div class="login-section">
                    <h1 class="accountLogin-title headline login-page__headline text-center"> 
                        <spring:theme code="text.account.profile.resetPassword"/>
                    </h1>
                    
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="" class="form-control" placeholder="." value="" type="text">
                            <label class="control-label" for="">
                                New Password
                            </label>				
                        </div>
                    </div>
                    
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="" class="form-control" placeholder="." value="" type="text">
                            <label class="control-label" for="">
                                Confirm Password
                            </label>				
                        </div>
                    </div>                     
                    
                    <div class="accountLogin-buttonWrapper accountLogin-buttonWrapper_spaceBetween">
                        <button class="btn btn-secondary">Cancel</button>
                        <button class="btn" type="submit">Update</button>
                    </div>

                    
                </div>
            </div>   
        </div>    
    </div>    
</div>