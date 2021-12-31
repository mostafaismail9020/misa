<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="breadcrumb"
	tagdir="/WEB-INF/tags/responsive/nav/breadcrumb"%>

<section id="page-breadcrums" class="page-breadcrums">
                    <div class="container">
                        <div class="row">
                            <nav aria-label="breadcrumb">
                            <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                            </nav>
                        </div>
                    </div>
                </section>