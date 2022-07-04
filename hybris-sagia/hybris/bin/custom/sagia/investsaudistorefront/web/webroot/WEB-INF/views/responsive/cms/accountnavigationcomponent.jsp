<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="investsaudi--my-account-menu-wrapper">
    <cms:component component="${component.navigationNode.links[0]}" />

    <div class="hidden investsaudi--my-account-menu display-md">
        <ul>
            <c:forEach items="${component.navigationNode.children[0].links}" var="link">
                <li>
                    <cms:component component="${link}"/>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="hidden investsaudi--my-account-menu-mobile display-xs display-sm">
        <ul class="dropdown-menu dropdown-large">
            <c:forEach items="${component.navigationNode.children[0].links}" var="link">
                <div class="row g-3">
                        <li class="third-menu">
                            <cms:component component="${link}"/>
                          </li>
                </div>
            </c:forEach>
        </ul>
    </div>
</div>
