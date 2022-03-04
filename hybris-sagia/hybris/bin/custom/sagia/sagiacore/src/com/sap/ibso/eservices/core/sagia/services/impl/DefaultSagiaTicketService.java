package com.sap.ibso.eservices.core.sagia.services.impl;


import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.ticket.dao.TicketDao;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.service.TicketService;
import de.hybris.platform.ticket.service.impl.DefaultTicketService;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import de.hybris.platform.ticket.strategies.TicketUpdateStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.sap.ibso.eservices.core.sagia.services.SagiaTicketService;
import com.sap.ibso.eservices.core.sagia.dao.SagiaTicketDao;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class DefaultSagiaTicketService extends DefaultTicketService implements SagiaTicketService {
	private static final Logger LOG = Logger.getLogger(DefaultSagiaTicketService.class);
	public static final String SAGIA_OPPORTUNITY_APPROVAL_EVENT = "SagiaOpportunityApproval";
	public static final String SCPI_PROCESS = "scpiOutInvestmentOpportunityProcess";
	public static final String WOAG_USER_GROUP = "WOAGUserGroup";
	private SagiaTicketDao sagiaTicketDao;
	@Resource
	private UserService userService;
	@Resource
	private ModelService modelService;
	@Resource
	private BusinessProcessService businessProcessService;
	@Resource
	private TicketDao ticketDao;
	@Resource
	private TicketService ticketService;

	@Resource(name = "ticketUpdateStrategy")
	private TicketUpdateStrategy ticketUpdateStrategy;

	@Resource(name = "b2bUnitService")
	private B2BUnitService<B2BUnitModel, B2BCustomerModel> b2bUnitService;

	public SagiaTicketDao getSagiaTicketDao() {
		return sagiaTicketDao;
	}

	public void setSagiaTicketDao(SagiaTicketDao sagiaTicketDao) {
		this.sagiaTicketDao = sagiaTicketDao;
	}

	@Override
	public SearchPageData<CsTicketModel> getTicketsByTicketCategory(PageableData pageableData, String ticketCategory, String sector) {
		SearchPageData<CsTicketModel> ticketsByCategory = getSagiaTicketDao().findTicketsByTicketCategory(pageableData, ticketCategory, sector);

		return ticketsByCategory;
	}
	
	@Override
	public SearchPageData<CsTicketModel> getTicketsByB2BUnit(PageableData pageableData, String b2bUnit) {
		SearchPageData<CsTicketModel> ticketsByUnit = getSagiaTicketDao().findTicketsByB2BUnit(pageableData, b2bUnit);
		return ticketsByUnit;
	}

	@Override
	public void saveOpportunityStateAndTriggerEvent(final String ticketId, final CsTicketState state, final String message)
			throws IllegalAccessException {
		CsTicketModel ticketModel = ticketService.getTicketForTicketId(ticketId);
		if(ticketModel.getState().equals(CsTicketState.WOAGAPPROVED)
				|| ticketModel.getState().equals(CsTicketState.WOAGREJECTED)) {
			LOG.error(String.format("The ticket - %s is already - %s. ", ticketId, ticketModel.getState().getCode()));
		} else {
			B2BUnitModel ticketUserParentB2BUnitModel = getParentB2BUnit(((B2BCustomerModel) ticketModel.getCustomer()).getDefaultB2BUnit());
			if (isCurrentUserValidApprover(ticketId, ticketUserParentB2BUnitModel)) {
				ticketModel.setState(state);
				ticketModel.setOpportunityMessage(message);
				modelService.save(ticketModel);
				//Trigerring the SagiaOpportunityApproval Event
				if (ticketModel.getState().equals(CsTicketState.WOAGAPPROVED)) {
					businessProcessService.triggerEvent(businessProcessService.getProcess(SCPI_PROCESS + "-" + ticketId + "%"),
							SAGIA_OPPORTUNITY_APPROVAL_EVENT);
				}
			}
		}
	}

	@Override
	public SearchPageData<CsTicketModel> getUnApprovedTicketsByB2BUnit(PageableData pageableData, String b2bUnit) {
		Set<B2BUnitModel> childB2BUnits = b2bUnitService.getB2BUnits(b2bUnitService.getUnitForUid(b2bUnit));
		SearchPageData<CsTicketModel> ticketsByUnit = getSagiaTicketDao().findUnApprovedTicketsByB2BUnit(pageableData, childB2BUnits);
		return ticketsByUnit;
	}

	@Override
	public boolean isCurrentUserValidApprover(final String ticketId, final B2BUnitModel ticketUserB2BUnitModel)
			throws IllegalAccessException {
		UserModel currentUser = userService.getCurrentUser();
		boolean isValidApprover = false;
		if(currentUser instanceof B2BCustomerModel) {
			if(((B2BCustomerModel) currentUser).getDefaultB2BUnit().equals(getParentB2BUnit(ticketUserB2BUnitModel))) {
				Set<PrincipalGroupModel> curGroups = currentUser.getGroups();
				if(CollectionUtils.isNotEmpty(curGroups)) {
					for (PrincipalGroupModel curGroup : curGroups) {
						if (WOAG_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
							isValidApprover = true;
							break;
						}
					}
				}
			}  else {
				throw new IllegalAccessException(
						String.format("User %s dont have permission to approve tickets %s from other B2BUnit %s",
								currentUser.getUid(), ticketId, ticketUserB2BUnitModel.getName()));
			}
		}
		return isValidApprover;
	}

	private B2BUnitModel getParentB2BUnit(B2BUnitModel b2BUnitModel) {
		B2BUnitModel parentB2BUnit = null;
		Set<PrincipalGroupModel> parentB2BUnitModels = b2BUnitModel.getAllGroups();
		if(CollectionUtils.isEmpty(parentB2BUnitModels)) {
			parentB2BUnit = b2BUnitModel;
		} else {
			for (PrincipalGroupModel principalGroupModel: parentB2BUnitModels) {
				if(principalGroupModel instanceof B2BUnitModel) {
					parentB2BUnit = (B2BUnitModel)principalGroupModel;
					break;
				}
			}
		}
		return parentB2BUnit;
	}
	
	@Override
	public List<ContactTicketModel> getScpiTickets() {
		return sagiaTicketDao.getScpiTickets(getConvertedDate());
	}
	@Override
	public List<ServiceRequestModel> getScpiServiceRequest() {
		return sagiaTicketDao.getScpiServiceRequest(getConvertedDate());
	}
	
	@Override
	public List<CsCustomerEventModel> getScpiCustomerEvents(){
		return sagiaTicketDao.getScpiCustomerEvents(getConvertedDate());
	}
	
	
	public String getConvertedDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat destFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
		// Substract 30 days from the calendar
		cal.add(Calendar.DATE, -30);
		return destFormat.format(cal.getTime());
	}
}
