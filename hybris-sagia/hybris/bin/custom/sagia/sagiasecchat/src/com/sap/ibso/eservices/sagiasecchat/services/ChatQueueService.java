package com.sap.ibso.eservices.sagiasecchat.services;

import com.sap.ibso.eservices.core.model.ChatQueueTypeModel;

import java.util.List;

/**
 * ChatQueueService
 */
public interface ChatQueueService {
    /**
     * retrieves Queues
     * @return List of ChatQueueTypeModel
     */
    List<ChatQueueTypeModel> getQueues();

    /**
     * retrieves QueueForIsoCode
     * @param isoCode isoCode
     * @return ChatQueueTypeModel
     */
    ChatQueueTypeModel getQueueForIsoCode(String isoCode);
}
