
package com.investsaudi.scpi.outbound.services;

import com.investsaudi.scpi.outbound.actions.SendB2BCustomerToScpiAction;
import de.hybris.platform.comments.model.CommentAttachmentModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.sap.sapcpiadapter.model.SAPCpiOutboundB2BCustomerModel;
import de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService;
import de.hybris.platform.sap.sapcpiadapter.service.impl.SapCpiOutboundServiceImpl;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.ticket.events.model.CsTicketEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import rx.Observable;

import javax.annotation.Resource;
import java.util.*;

public class MISAScpiOutboundServiceImpl extends SapCpiOutboundServiceImpl {

  private static final Logger LOG = LoggerFactory.getLogger(MISAScpiOutboundServiceImpl.class);

  // Opportunity Outbounds
  private static final String OUTBOUND_OPPORTUNITY_OBJECT = "OutboundOpportunity";
  private static final String OUTBOUND_OPPORTUNITY_DESTINATION = "scpiOpportunityDestination";
    public static final String DEFAULT_CREATION_NOTE = "DEFAULT_CREATION_NOTE";

    @Resource(name = "mediaService")
    private MediaService mediaService;

  public void sendOpportunity(CsTicketModel opportunity) {

      if(opportunity.getEvents() != null) {
          List<MediaModel> mediaList = new ArrayList<>();
          Iterator<CsTicketEventModel> iterator = opportunity.getEvents().iterator();
          for (Iterator i = iterator; i.hasNext(); ) {
              CsTicketEventModel csTicketEventModel = (CsTicketEventModel) i.next();
              if(csTicketEventModel.getText() != null && csTicketEventModel.getText().equalsIgnoreCase(DEFAULT_CREATION_NOTE)) {
                  Collection<CommentAttachmentModel> attachments = csTicketEventModel.getAttachments();
                  if (CollectionUtils.isNotEmpty(attachments)) {
                      CommentAttachmentModel comment = attachments.iterator().next();
                      MediaModel mediaModel = (MediaModel) comment.getItem();
                      mediaModel.setLocation(org.apache.ws.security.util.Base64.encode(mediaService.getDataFromMedia(mediaModel)));
                      mediaList.add(mediaModel);
                  }
              }
          }
          opportunity.setAttachments(mediaList);
      }

    getOutboundServiceFacade().send(opportunity, OUTBOUND_OPPORTUNITY_OBJECT, OUTBOUND_OPPORTUNITY_DESTINATION).subscribe(

            // onNext
            responseEntityMap -> {
                opportunity.setAttachments(null);
              if (SapCpiOutboundService.isSentSuccessfully(responseEntityMap)) {

                LOG.info("Opportunity [{}] has been sent to the SAP backend through SCPI! {}",
                        opportunity.getTicketID(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));

              } else {

                LOG.error("Opportunity [{}] has not been sent to the SAP backend! {}",
                        opportunity.getTicketID(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));

              }

            }
            // onError
            , error -> {
                opportunity.setAttachments(null);
                LOG.error("Opportunity [{}] has not been sent to the SAP backend through SCPI! {}",
                    opportunity.getTicketID(), error.getMessage(), error);
            }

    );
  }
}
