package com.sap.ibso.eservices.facade.customerticket.impl;

import com.sap.ibso.eservices.core.sagia.services.SagiaTicketService;
import com.sap.ibso.eservices.facade.customerticket.SagiaCustomerTicketingFacade;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerticketingfacades.data.TicketData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketException;
import de.hybris.platform.ticket.service.TicketService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Set;

public class SagiaCustomerTicketingFacadeImpl implements SagiaCustomerTicketingFacade {
    protected static final Logger LOG = Logger.getLogger(DefaultSagiaCustomerTicketingFacade.class);
    private SagiaTicketService sagiaTicketService;
    private Converter<CsTicketModel, TicketData> ticketListConverter;

    @Resource
    private TicketService ticketService;

    @Resource
    private UserService userService;

    @Resource
    private Converter<CsTicketModel, TicketData> ticketConverter;

    private static final String NIPC_USER_GROUP = "NIPCUserGroup";

    private static final String WOAG_USER_GROUP = "WOAGUserGroup";

    @Override
    public void setTicketState(final String ticketId, final CsTicketState state, final String message)
            throws TicketException, IllegalAccessException {
        getSagiaTicketService().saveOpportunityStateAndTriggerEvent(ticketId, state, message);
    }

    @Override
    public SearchPageData<TicketData> getUnApprovedTicketsByB2BUnit(PageableData pageableData, String b2bUnit) {
        SearchPageData<CsTicketModel> ticketsByUnit = getSagiaTicketService().getUnApprovedTicketsByB2BUnit(pageableData, b2bUnit);
        return convertPageData(ticketsByUnit, getTicketListConverter());
    }

    protected <S, T> SearchPageData<T> convertPageData(final SearchPageData<S> source, final Converter<S, T> converter) {
        final SearchPageData<T> result = new SearchPageData<T>();
        result.setPagination(source.getPagination());
        result.setSorts(source.getSorts());
        result.setResults(Converters.convertAll(source.getResults(), converter));
        return result;
    }

    public SagiaTicketService getSagiaTicketService() {
        return sagiaTicketService;
    }

    public void setSagiaTicketService(SagiaTicketService sagiaTicketService) {
        this.sagiaTicketService = sagiaTicketService;
    }

    public Converter<CsTicketModel, TicketData> getTicketListConverter() {
        return ticketListConverter;
    }

    public void setTicketListConverter(Converter<CsTicketModel, TicketData> ticketListConverter) {
        this.ticketListConverter = ticketListConverter;
    }

    @Override
    public TicketData getTicket(final String ticketId) throws IllegalAccessException {
        final CsTicketModel ticketModel = ticketService.getTicketForTicketId(ticketId);
        if (ticketModel == null) {
            throw new RuntimeException("Ticket not found for the given ID " + ticketId); //NOSONAR
        }

        if (isMemberOfGivenGroup(NIPC_USER_GROUP)) {
            return ticketModel.getTicketID().equals(ticketId) ? ticketConverter.convert(ticketModel, new TicketData()) : null;
        } else if (isMemberOfGivenGroup(WOAG_USER_GROUP)) {
            return getSagiaTicketService().isCurrentUserValidApprover(ticketId,
                    ((B2BCustomerModel)ticketModel.getCustomer()).getDefaultB2BUnit())
                    ? ticketConverter.convert(ticketModel, new TicketData()) : null;
        } else {
            return ticketModel.getCustomer().getUid().equals(userService.getCurrentUser().getUid())
                    ? ticketConverter.convert(ticketModel, new TicketData()) : null;
        }
    }

    private boolean isMemberOfGivenGroup(String userGroupUid) {
        boolean nipcMember = false;
        UserModel currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            Set<PrincipalGroupModel> curGroups = currentUser.getGroups();
            for (PrincipalGroupModel curGroup : curGroups) {
                if (userGroupUid.equalsIgnoreCase(curGroup.getUid())) {
                    nipcMember = true;
                }
            }
        }
        return nipcMember;
    }
}
