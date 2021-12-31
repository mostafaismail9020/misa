package com.sap.ibso.eservices.facades.populators;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaB2BCustomerPopulator implements Populator<B2BCustomerModel, CustomerData> {
    private static final Logger LOG = Logger.getLogger(SagiaB2BCustomerPopulator.class);
    public static final String WO_ADMIN_GROUP = "WOAdminGroup";
    public static final String WOBD_USER_GROUP = "WOBDUserGroup";
    public static final String WOAG_USER_GROUP = "WOAGUserGroup";
    public static final String APPROVER = "Approver";
    public static final String BUSINESS_DEVELOPMENT_USER = "Business Development User";
    public static final String ADMIN = "Admin";

    @Override
    public void populate(B2BCustomerModel userModel, CustomerData customerData) throws ConversionException {
        Set<PrincipalGroupModel> userGroups =  userModel.getGroups();
        if(CollectionUtils.isEmpty(userGroups)) {
            LOG.error(String.format("Given user %s does not have any user group assigned.", userModel.getUid()));
        } else {
            for (PrincipalGroupModel curGroup : userGroups) {
                switch (curGroup.getUid()) {
                    case WO_ADMIN_GROUP:
                        customerData.setRole(ADMIN);
                        break;
                    case WOBD_USER_GROUP:
                        customerData.setRole(BUSINESS_DEVELOPMENT_USER);
                        break;
                    case WOAG_USER_GROUP:
                        customerData.setRole(APPROVER);
                        break;
                    default:
                        LOG.info(String.format("Given user %s does not have a valid Workflow user group assigned.", userModel.getUid()));
                }
            }
        }
    }
}
