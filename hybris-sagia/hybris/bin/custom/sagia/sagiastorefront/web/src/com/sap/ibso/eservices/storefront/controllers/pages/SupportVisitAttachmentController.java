package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaSupportVisitsAttachmentService;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/support-visits")
public class SupportVisitAttachmentController extends SagiaAbstractPageController
{
    @Resource
    private SagiaSupportVisitsAttachmentService sagiaSupportVisitsAttachmentService;

    private static final Logger LOG = Logger.getLogger(SupportVisitAttachmentController.class.getName());

    @RequireHardLogIn
    @RequestMapping(method = RequestMethod.GET, path = "/download/{objectId}/{fileGuid}")
    public ResponseEntity<InputStreamResource> getPdfAttachment(@RequestParam(value = "mimeType", required = true) String mimeType,
                                                                @PathVariable("objectId") String objectId,
                                                                @PathVariable("fileGuid") String fileGuid) {
        try {
            InputStream inputStream = sagiaSupportVisitsAttachmentService.readSupportVisitPdf(objectId, fileGuid);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(mimeType));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
