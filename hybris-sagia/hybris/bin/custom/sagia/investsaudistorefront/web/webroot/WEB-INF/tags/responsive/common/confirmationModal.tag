<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <form action="" class="js-formInputFileBox">
                <div class="modal-header modal-header_smallPDB">
                    <div class="modal-title"></div>
                    <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                         <icon:close/>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="modal-description modal-description_largeMargin modal-description_smallText">
                    </div>
                </div>
                <div class="modal-footer modal-footer_spaceBetween">

                    <button type="button" class="btn btn-sagia btn-sagia-red noButton" data-dismiss="modal">No</button>
                    <button type="button" class="btn btn-sagia btn-sagia-green yesButton">Yes</button>
                </div>
            </form>
        </div>
    </div>
</div>
