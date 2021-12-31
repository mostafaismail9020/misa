<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>

<div class="js-hidden-element" style="display: none;">
    <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
        <div class="contentModule">
            <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                    <div class="contentModule-headline">
                        <icon:info/>
                        <spring:theme code="text.account.followup.info"/>:&nbsp;${selectedItem.srId}
                    </div>
                    <a href="${encodedContextPath}/support-visits/create" class="btn btn_slim">
                        <spring:theme code="text.account.followup.create"/>
                    </a>
                </div>

                <div class="tableModule">
                    <table class="tableModule-table">
                        <thead class="tableModule-head">
                        <tr>
                            <th><spring:theme code="text.account.followup.supportVisitsDate"/></th>
                            <th><spring:theme code="text.account.followup.description"/></th>
                        </tr>
                        </thead>
                        <tbody class="tableModule-body">
                        <tr>
                            <td>${selectedItem.visitDate.dateFormatted}</td>
                            <td class="tableModule-bodyItem-left">${selectedItem.textMsg}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                <div class="commentModule commentModule-no-border">
                </div>
            </div>
        </div>
    </div>
</div>