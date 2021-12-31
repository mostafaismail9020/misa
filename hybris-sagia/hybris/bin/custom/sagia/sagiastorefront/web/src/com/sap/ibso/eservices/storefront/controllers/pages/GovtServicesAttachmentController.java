package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.impl.GovtServicesAttachmentService;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/govtServices")
public class GovtServicesAttachmentController extends AbstractPageController {
    public static final String PDF = "application/pdf";
    private static final Logger LOG = Logger.getLogger(GovtServicesAttachmentController.class);

    @Resource
    private GovtServicesAttachmentService govtServicesAttachmentService;

    @RequireHardLogIn
    @RequestMapping(method = RequestMethod.GET, path = "/pdf/{objectId}/{documentID}")
    public ResponseEntity<InputStreamResource> getPdfAttachment(@PathVariable("objectId") String objectId,
                                                                @PathVariable("documentID") String documentId) {
        try {
            InputStream inputStream = govtServicesAttachmentService.readGovtServicesPdf(objectId, documentId);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(PDF));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("stacktrace",e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
