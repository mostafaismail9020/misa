package com.sap.ibso.eservices.sagiasecchat.dao;

import com.sap.ibso.eservices.core.model.ChatQueueTypeModel;

import java.io.Serializable;
import java.util.List;

public interface ChatQueueDAO {

    List<ChatQueueTypeModel> getQueues();

    ChatQueueTypeModel getQueueForIsoCode(String isoCode);
}
