package com.sap.ibso.eservices.sagiasecchat.services.Impl;

import com.sap.ibso.eservices.core.model.ChatQueueTypeModel;
import com.sap.ibso.eservices.sagiasecchat.dao.ChatQueueDAO;
import com.sap.ibso.eservices.sagiasecchat.services.ChatQueueService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;

/**
 * DefaultChatQueueService
 */
public class DefaultChatQueueService extends AbstractBusinessService implements ChatQueueService {

    private transient ChatQueueDAO chatQueueDAO;

    public com.sap.ibso.eservices.sagiasecchat.dao.ChatQueueDAO getChatQueueDAO() {
        return chatQueueDAO;
    }

    public void setChatQueueDAO(com.sap.ibso.eservices.sagiasecchat.dao.ChatQueueDAO chatQueueDAO) {
        this.chatQueueDAO = chatQueueDAO;
    }

    @Override
    public List<ChatQueueTypeModel> getQueues() {
        return getChatQueueDAO().getQueues();
    }

    @Override
    public ChatQueueTypeModel getQueueForIsoCode(String isoCode) {
        return getChatQueueDAO().getQueueForIsoCode(isoCode);
    }
}
