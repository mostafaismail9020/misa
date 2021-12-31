<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<!--dont include this stile on page !!!-->
<style type="text/css">
    .investsaudiHeader,
    .sagiaNavigation,
    .investsaudiFooter {
        display: none;
    }
</style>

<%-- TODO: SAH-1290 Part 1--%>

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
                    <h1 class="accountLogin-title headline login-page__headline text-center">Forgotten Password</h1>

                    <form id="" action="" method="post">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="" class="form-control" placeholder="." value="" type="text">
                                <label class="control-label" for="">
                                    User / E-Mail
                                </label>				
                            </div>
                        </div>
                        <div class="accountLogin-copyWrapper">
                            <p>Please enter your account email address.  Instructions on how to Reset your Password will be sent to this address.</p>
                        </div>


                        <div class="accountLogin-buttonWrapper">
                            <button type="submit" class="btn btn_wide">
                                Reset Password</button>
                        </div>
                        <div class="accountLogin-linkWrapper">
                            <a href="#" class="btn btn_link btn_link_text">
                            Contact us</a>
                        </div>                
                    </form>
                </div>
            </div>   
        </div>    
    </div>    
</div>




<%-- TODO: SAH-1290 Part 2--%>



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
                    <h1 class="accountLogin-title headline login-page__headline text-center">Forgotten Password</h1>

                    <div class="accountLogin-copyWrapper">
                        <p>Password reset instructions have been sent to your <nobr>e-mail</nobr> address. Please contact customer support if you require additional assistance.</p>
                    </div>


                    <div class="accountLogin-buttonWrapper">
                        <a href="#" class="btn btn_wide">Back to ...</a>
                    </div>
                    <div class="accountLogin-linkWrapper">
                        <a href="#" class="btn btn_link btn_link_text">
                        Contact us</a>
                    </div>                

                </div>
            </div>   
        </div>    
    </div>    
</div>