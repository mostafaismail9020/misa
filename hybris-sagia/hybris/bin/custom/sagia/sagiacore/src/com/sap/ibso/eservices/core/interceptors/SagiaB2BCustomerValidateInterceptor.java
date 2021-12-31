
package com.sap.ibso.eservices.core.interceptors;


import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.event.CustomerUserGroupUpdateEvent;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.site.BaseSiteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class SagiaB2BCustomerValidateInterceptor implements PrepareInterceptor<B2BCustomerModel>
{
    public static final String INVESTSAUDI_SITE_ID = "investsaudi";
    public static final String ATTRIBUTE_GROUPS = "groups";

    @Autowired
    private BaseSiteService baseSiteService;

    @Autowired
    private EventService eventService;

    @Override
    public void onPrepare(final B2BCustomerModel b2BCustomerModel, final InterceptorContext ctx) throws InterceptorException
    {
        if(!ctx.isNew(b2BCustomerModel) && ctx.isModified(b2BCustomerModel, ATTRIBUTE_GROUPS) && adminGroupAssigned(b2BCustomerModel, ctx)){
            BaseSiteModel investSaudibaseSite = baseSiteService.getBaseSiteForUID(INVESTSAUDI_SITE_ID);
            CustomerUserGroupUpdateEvent customerUserGroupUpdateEvent = new CustomerUserGroupUpdateEvent();
            customerUserGroupUpdateEvent.setSite(investSaudibaseSite);
            customerUserGroupUpdateEvent.setBaseStore(investSaudibaseSite.getStores().get(0));
            customerUserGroupUpdateEvent.setCustomer(b2BCustomerModel);
            customerUserGroupUpdateEvent.setLanguage(b2BCustomerModel.getSessionLanguage());
            customerUserGroupUpdateEvent.setCurrency(investSaudibaseSite.getStores().get(0).getCurrencies().iterator().next());
            customerUserGroupUpdateEvent.setUserGroup(SagiaCoreConstants.WORKFLOW_ADMIN_ID);
            eventService.publishEvent(customerUserGroupUpdateEvent);
        }
    }

    private boolean adminGroupAssigned(B2BCustomerModel b2BCustomerModel, InterceptorContext ctx) {
        boolean isAdminGroupAssigned = false;
        Set<PrincipalGroupModel> updatedGroups = new HashSet<>((b2BCustomerModel.getGroups()));
        Set<PrincipalGroupModel> existingGroups = ((B2BCustomerModel)ctx.getModelService().get(b2BCustomerModel.getPk())).getGroups();
        updatedGroups.removeAll(existingGroups);
        for(PrincipalGroupModel userGroup:updatedGroups){
            if(StringUtils.equals(SagiaCoreConstants.WORKFLOW_ADMIN_ID, userGroup.getUid())){
                isAdminGroupAssigned = true;
            }
        }
        return isAdminGroupAssigned;
    }
}
