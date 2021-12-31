package com.sap.ibso.eservices.sagiasecchat.dao.Impl;

import com.sap.ibso.eservices.core.model.ChatQueueTypeModel;
import com.sap.ibso.eservices.sagiasecchat.dao.ChatQueueDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultChatQueueDAO extends DefaultGenericDao<ChatQueueTypeModel> implements ChatQueueDAO {
    private static final String FALLBACK_LANGUAGE = "en";

    public DefaultChatQueueDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<ChatQueueTypeModel> getQueues() {
        return find();
    }

    @Override
    public ChatQueueTypeModel getQueueForIsoCode(String isoCode) {
        validateParameterNotNull(isoCode, "isoCode must not be null!");

        final Map parameters = new HashMap();
        parameters.put(ChatQueueTypeModel.ISOCODE, isoCode);
        List parameterList = find(parameters);
        if (CollectionUtils.isNotEmpty(parameterList)) {
            return (ChatQueueTypeModel) parameterList.get(0);
        } else {
            parameters.put(ChatQueueTypeModel.ISOCODE, FALLBACK_LANGUAGE);
            parameterList = find(parameters);
            if (CollectionUtils.isNotEmpty(parameterList)) {
                return (ChatQueueTypeModel) parameterList.get(0);
            } else {
                return null;
            }
        }
    }
}
