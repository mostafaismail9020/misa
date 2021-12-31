package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaTutorialModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaTutorialDAO;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultSagiaTutorialDAO extends DefaultGenericDao<SagiaTutorialModel> implements SagiaTutorialDAO {


    public DefaultSagiaTutorialDAO(String typecode) {
        super(typecode);
    }

    @Override
    public SagiaTutorialModel getTutorial(String url) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(SagiaTutorialModel.PAGEURL, url);
        List<SagiaTutorialModel> tutorials = find(parameters);
        if (tutorials.size() == 1){
            return tutorials.get(0);
        }
        return null;
    }
}
