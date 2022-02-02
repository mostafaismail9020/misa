/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.core.sagia.services.impl;

import com.investsaudi.data.SagiaB2BUnitData;
import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.investsaudi.portal.core.service.ContactTicketBusinessService;
import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.sagia.dao.SagiaUserDao;
import com.sap.ibso.eservices.core.sagia.enums.ValidationError;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.user.exceptions.PasswordEncoderNotFoundException;
import de.hybris.platform.servicelayer.user.impl.DefaultUserService;

import de.hybris.platform.comments.model.CommentModel;
import de.hybris.platform.ticket.strategies.TicketEventStrategy;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.fest.util.Strings;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of UserService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaUserService extends DefaultUserService implements SagiaUserService {
    private static final Logger LOG = Logger.getLogger(DefaultSagiaUserService.class);
    private SagiaUserDao sagiaUserDao;

    @Resource(name = "b2bUnitService")
    private B2BUnitService<B2BUnitModel, B2BCustomerModel> b2bUnitService;

    @Resource
    UserService userService;
    
    @Resource(name = "contactTicketEventStrategy")
	private TicketEventStrategy ticketEventStrategy;
	
	@Resource(name="contactTicketBusinessService")
	private ContactTicketBusinessService contactTicketBusinessService;

    @Override
    public List<ValidationError> validateUniqueUserAttributes(final String uid, final String mobileNumber, 
    		final String mobileCountryCode, final String email) {
        final List<ValidationError> result = new ArrayList<>();
        final List<CustomerModel> customers = sagiaUserDao.getCustomers(uid, mobileNumber, mobileCountryCode, email);
        if (!CollectionUtils.isEmpty(customers)) {
            customers.forEach(customer -> {
                if (mobileNumber.equals(customer.getMobileNumber())) {
                    result.add(ValidationError.DUPLICATE_MOBILE_NUMBER);
                } else if (email.equals(customer.getUserNameEmail())) {
                    result.add(ValidationError.DUPLICATE_EMAIL);
                } else if (uid.equals(customer.getUid())) {
                    result.add(ValidationError.DUPLICATE_UID);
                }
            });
        }

        return result;
    }

    @Override
    public boolean validateUniqueness(final String userName, final String email, final String mobileNumber,
                                      final String mobileCountryCode) {
        List<CustomerModel> customers = new ArrayList<>();
        if (!Strings.isEmpty(userName)) {
            customers = sagiaUserDao.getCustomerByUid(userName);
        } else if (!Strings.isEmpty(email)) {
            customers = sagiaUserDao.getCustomerByEmail(email);
        } else if (!Strings.isEmpty(mobileCountryCode) && !Strings.isEmpty(mobileNumber)) {
            customers = sagiaUserDao.getCustomerByMobileNumber(mobileNumber, mobileCountryCode);
        }

        return customers.isEmpty();
    }

    @Override
    public CustomerModel getCustomerByEmail(final String email) {
        List<CustomerModel> customers = sagiaUserDao.getCustomerByEmail(email);
        if (customers.iterator().hasNext()) {
            return customers.iterator().next();
        }

        return null;
    }

    @Override
    public CustomerModel getCustomerByMobileNumber(final String mobileNumber, final String mobileCountryCode) {
        List<CustomerModel> customers = sagiaUserDao.getCustomerByMobileNumber(mobileNumber, mobileCountryCode);
        if (customers.iterator().hasNext()) {
            return customers.iterator().next();
        }

        return null;
    }

    @Override
    public void setPassword(String userId, String password, String encoding) throws PasswordEncoderNotFoundException {
        // Note: this method is (finally) invoked for resetting a user password during forgotten password process
        super.setPassword(userId, password, encoding);

        // In any case there is no password migration (cf. SagiaAuthenticationProvider) required anymore, as the user's password was just set
        CustomerModel customer = getUserForUID(userId, CustomerModel.class);
        customer.setPasswordMigration(false);
        getModelService().save(customer);
    }

    @Override
    public List<String> getApproversEmail(B2BUnitModel b2BUnitModel) {
        B2BUnitModel parentUnit = null != b2bUnitService.getParent(b2BUnitModel) ?  b2bUnitService.getParent(b2BUnitModel) :  b2BUnitModel;
        List<String> emailList = new ArrayList<String>();
        Set<PrincipalModel> members = parentUnit.getMembers();
        UserGroupModel approverUg = userService.getUserGroupForUID(SagiaCoreConstants.WORKFLOW_APPROVER_ID);
        for (PrincipalModel member : members) {
            if (member instanceof B2BCustomerModel && member.getGroups().contains(approverUg)) {
                emailList.add(((B2BCustomerModel) member).getUserNameEmail());
            }
        }
        return emailList;
    }

    @Override
    public Set<B2BCustomerModel> getAllChildB2BCustomers() {
        Set<B2BCustomerModel> childB2BCustomers = Collections.emptySet();
        UserModel userModel = getCurrentUser();
        if (userModel instanceof B2BCustomerModel) {
            B2BUnitModel b2BUnitModel = b2bUnitService.getParent((B2BCustomerModel) userModel);
            if (b2BUnitModel == null) {
                LOG.error(String.format("The given user does not have any parent B2B Unit assigned - %s", userModel.getUid()));
            } else {
                Set<B2BUnitModel> childB2BUnits = b2bUnitService.getB2BUnits(
                        b2bUnitService.getUnitForUid(b2BUnitModel.getUid()));
                childB2BCustomers = b2bUnitService.getB2BCustomers(b2BUnitModel);
                if(CollectionUtils.isEmpty(childB2BUnits)) {
                    LOG.error(String.format("No child B2B unit found - %s", b2BUnitModel.getUid()));
                } else {
                    for (B2BUnitModel b2BUnitModel1 : childB2BUnits) {
                        childB2BCustomers.addAll(b2bUnitService.getB2BCustomers(b2BUnitModel1));
                    }
                }
            }
        }
        if(CollectionUtils.isNotEmpty(childB2BCustomers)) {
            childB2BCustomers.remove((B2BCustomerModel) userModel);
        }
        return childB2BCustomers;
    }

    @Override
    public List<ContactTicketModel> getUserRaisedOpportunities(String contactEmail) {
        return sagiaUserDao.getUserRaisedOpportunities(contactEmail);
    }

    @Override
    public ContactTicketModel getContactTicketForTicketId(String ticketId) {
        return sagiaUserDao.getContactTicketForTicketId(ticketId);
    }
    
    
    public ContactTicketModel addContactTicketComments(String ticketId, String comments) {    	
    	ContactTicketModel contactTicket = sagiaUserDao.getContactTicketForTicketId(ticketId);
    	
    	if (null != contactTicket && null != comments) {    		    		
	    	CsTicketModel ticket = (CsTicketModel) contactTicket;
			CsCustomerEventModel ticketComment = ticketEventStrategy.createCreationEventForTicket(ticket,
					CsEventReason.FIRSTCONTACT, CsInterventionType.TICKETMESSAGE, comments);
            contactTicketBusinessService.customerEvent2SCPI(ticketComment);
			/*List<CommentModel> commentsList = new ArrayList<CommentModel>();
			commentsList.add(ticketComment);
			contactTicket.setComments(commentsList);
			getModelService().refresh(contactTicket);*/

            List<CommentModel> commentsList = new ArrayList<>(contactTicket.getComments());
            contactTicket.setComments(commentsList);
    	}
    	
        return contactTicket;
    }

    public SagiaUserDao getSagiaUserDao() {
        return sagiaUserDao;
    }

    public void setSagiaUserDao(final SagiaUserDao sagiaUserDao) {
        this.sagiaUserDao = sagiaUserDao;
    }

    @Override
    public boolean attachServiceRequestToContactTicket(final ServiceRequestModel serviceRequest, final String ticketId) {
    	boolean attachRequest = false;
    	try {
    		ContactTicketModel contactTicket = sagiaUserDao.getContactTicketForTicketId(ticketId);
    		if(null != contactTicket) {
    			serviceRequest.setContactTicket(contactTicket);
    			getModelService().save(serviceRequest);
    			getModelService().save(contactTicket);
				contactTicketBusinessService.servicerequest2SCPI(serviceRequest);
            	attachRequest = true;
    		}
        }catch(Exception e) {
    		LOG.error(e.getMessage(), e);
    		return attachRequest;
    	}
    	return attachRequest;
     }
}
