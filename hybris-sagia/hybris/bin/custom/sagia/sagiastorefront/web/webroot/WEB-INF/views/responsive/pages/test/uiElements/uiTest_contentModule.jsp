<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
    <h1 class="uiTest-headline">contentModule</h1>
    <p class="uiTest-description">Basic contentModule can contain multiple contentModule-section and different Button postions. E.g. centered</p>
</div>
<!-- End of Module description-->
    
    <!--contentModule with centered buttons-->
    <div class="contentModule">

        <div class="contentModule-section">

            <div class="contentModule-headline"><!--svg goes here-->Headline</div>
            
            <div class="contentModule-headline contentModule-headline_small"><!--svg goes here-->Small Headline</div>
            
            <div class="contentModule-headline contentModule-headline_small02"><!--svg goes here-->Small02 Headline</div>
            <!-- modules, row etc. here-->

             <div class="contentModule-subheadline">subHeadline</div>
        </div>       

        <div class="contentModule-actions">
            <button type="reset" class="btn btn_outline">
                Cancel
            </button>           
            <button type="submit" class="btn">
                Next
            </button>
        </div>
        
        
        <div class="contentModule-actions contentModule-actions_centered">
            <button type="reset" class="btn btn_outline">
                Cancel
            </button>           
            <button type="submit" class="btn">
                Next
            </button>
        </div>
        

        <div class="contentModule-actions contentModule-actions_spaceBetween">
            <button type="reset" class="btn btn_outline">
                Cancel
            </button>           
            <button type="submit" class="btn">
                Next
            </button>
        </div>

                
        <div class="contentModule-actions contentModule-actions_right">
            <button type="reset" class="btn btn_outline">
                Cancel
            </button>           
            <button type="submit" class="btn">
                Next
            </button>
        </div>          
        
    </div>
          
    
</div>
