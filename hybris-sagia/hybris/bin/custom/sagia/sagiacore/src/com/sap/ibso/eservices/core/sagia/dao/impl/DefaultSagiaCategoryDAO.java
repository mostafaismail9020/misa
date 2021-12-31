package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCategoryDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.List;

public class DefaultSagiaCategoryDAO  extends DefaultGenericDao<SagiaCategoryModel> implements SagiaCategoryDAO {

    public DefaultSagiaCategoryDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaCategoryModel> getAllCategories() {
        return find();
    }
}
