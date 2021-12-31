package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.SagiaAppointmentFacade;
import com.sap.ibso.eservices.sagiaservices.services.attachments.AttachmentFile;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.classification.ClassificationUpgradeContentService;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/attachment")
public class AttachmentController extends AbstractPageController {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentController.class);
    public static final String PDF = "application/pdf";
    private static final String STACKTRACE_LOG = "stacktrace:";

    @Autowired
    private ContentDetailsService contentDetailsService;
    @Autowired
    private ClassificationUpgradeContentService classificationUpgradeContentService;
    @Resource(name = "sagiaAppointmentFacade")
    private SagiaAppointmentFacade sagiaAppointmentFacade;

    /**
     * Opens in browser a tab with an attachment of PDF type from CRM
     *
     * @param objectId - Object id of the attachment
     * @param documentId - Document id of the attachment
     * @return - A new page with the specific PDF from CRM
     */
    @RequireHardLogIn
    @RequestMapping(method = RequestMethod.GET, path = "/pdf/{objectId}/{documentID}")
    public ResponseEntity<InputStreamResource> getPdfAttachment(@PathVariable("objectId") String objectId,
                                                                @PathVariable("documentID") String documentId) {
        try {
            InputStream inputStream = contentDetailsService.readAttachmentBy(objectId, documentId);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(PDF));
            return new ResponseEntity<InputStreamResource>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(STACKTRACE_LOG, e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Downloads an attachment from CRM
     *
     * @param objectId - Object id of the attachment
     * @param documentId - Document id of the attachment
     * @return - A new page with the specific PDF from CRM
     */
    @RequireHardLogIn
    @RequestMapping(method = RequestMethod.GET, path = "/download/{objectId}/{documentID}/{fileName:.+}")
    public ResponseEntity<InputStreamResource> downloadAttachment(@PathVariable("objectId") String objectId,
                                                                  @PathVariable("documentID") String documentId,
                                                                  @PathVariable("fileName") String fileName,
                                                                  HttpServletResponse response) {
        try {
            AttachmentFile inputStream = contentDetailsService.downloadAttachmentBy(objectId, documentId);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(inputStream.getContent());


            response.setHeader("Content-Disposition", "inline;filename=" + fileName);
            response.setHeader("Content-Type", inputStream.getMimeType());
            return new ResponseEntity<InputStreamResource>(attachmentInputStreamResource, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(STACKTRACE_LOG, e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * this reads attachment from ZClassification_UP_SRV
     */
    @RequireHardLogIn
    @RequestMapping(method = RequestMethod.GET, path = "/zclass/{objectID}/{docGUID}")
    public ResponseEntity<InputStreamResource> getZClassAttachment(@PathVariable("objectID") String objectID,
                                                                   @PathVariable("docGUID") String docGUID, HttpServletRequest request, Model model) {
        try {
            InputStream inputStream = classificationUpgradeContentService.readContentAttachmentBy(objectID, docGUID);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(PDF));
            return new ResponseEntity<InputStreamResource>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(STACKTRACE_LOG, e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * this reads attachment from ZClassification_UP_SRV
     */
    @RequireHardLogIn
    @RequestMapping(method = RequestMethod.GET, path = "/content-details/{objectID}/{docGUID}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("objectID") String objectID,
                                                       @PathVariable("docGUID") String docGUID, HttpServletRequest request, Model model) {
        try {
            InputStream inputStream = classificationUpgradeContentService.readContentAttachmentBy(objectID, docGUID);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(PDF));
            return new ResponseEntity<InputStreamResource>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(STACKTRACE_LOG, e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequireHardLogIn
    @RequestMapping(method = RequestMethod.GET, path = "/appointment-file/{id}")
    public ResponseEntity<InputStreamResource> getAppointmentFile(@PathVariable("id") String objectID){
            InputStream inputStream = sagiaAppointmentFacade.printAppointmentDetails(objectID);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(PDF));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
    }
}
