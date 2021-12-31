package com.sap.ibso.eservices.sagiaservices.services.b2b.impl;

import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.sagia.dao.SagiaUserDao;
import com.sap.ibso.eservices.sagiaservices.services.b2b.SagiaB2BCustomer;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

/**
 * The type Sagia b 2 b customer.
 */
public class SagiaB2BCustomerImpl implements SagiaB2BCustomer {
    private static final Logger LOG = Logger.getLogger(SagiaB2BCustomerImpl.class);
    private SagiaUserDao sagiaUserDao;

    @Override
    public Set<B2BCustomerModel> getB2BCustomersToDisable() {
        Set<B2BCustomerModel> b2BCustomerModelsToDisable = Collections.EMPTY_SET;
        List<CsTicketModel> csTicketModelList = getSagiaUserDao()
                .getAllTicketsByCustomerUnderGiveUserGroup(SagiaCoreConstants.WORKFLOW_BD_ID);
        List<B2BCustomerModel> b2BCustomerModelsWithNoTickets = getSagiaUserDao()
                .getAllWOBDCustomerWithNoTickets(SagiaCoreConstants.WORKFLOW_BD_ID);
        b2BCustomerModelsToDisable = filterCustomersOlderThanThreshold(csTicketModelList, b2BCustomerModelsWithNoTickets);
        return b2BCustomerModelsToDisable;
    }

    private Set<B2BCustomerModel> filterCustomersOlderThanThreshold(final List<CsTicketModel> allCsTicketsByWoBDCustomers,
                                                                    final List<B2BCustomerModel> customersWithNoTickets) {
        Set<B2BCustomerModel> customersHavingOlderCsTickets = new HashSet<>();
        Set<B2BCustomerModel> customersNotHavingOlderCsTickets = new HashSet<>();
        if(CollectionUtils.isNotEmpty(allCsTicketsByWoBDCustomers)) {
            for (CsTicketModel csTicketModel : allCsTicketsByWoBDCustomers) {
                if (isTicketOlderThanThreshold(csTicketModel)) {
                    customersHavingOlderCsTickets.add((B2BCustomerModel) csTicketModel.getCustomer());
                } else {
                    customersNotHavingOlderCsTickets.add((B2BCustomerModel) csTicketModel.getCustomer());
                }
            }
            if (CollectionUtils.isNotEmpty(customersNotHavingOlderCsTickets)) {
                if (CollectionUtils.isNotEmpty(customersHavingOlderCsTickets)) {
                    customersHavingOlderCsTickets.removeIf(customersNotHavingOlderCsTickets::contains);
                }
            }
        }

        if(CollectionUtils.isNotEmpty(customersWithNoTickets)) {
            for (B2BCustomerModel customerModel: customersWithNoTickets) {
                if(isCustomerWithNoTicketOlderThanThreshold(customerModel)) {
                    customersHavingOlderCsTickets.add(customerModel);
                }
            }
        }

        return customersHavingOlderCsTickets;
    }

    private boolean isTicketOlderThanThreshold(CsTicketModel csTicketMode) {
        boolean isAfter = isGivenDateOlderThanThreshold(csTicketMode.getCreationtime());

        if(LOG.isDebugEnabled()) {
            LOG.debug(String.format("CSTicket %s is created by %s on %s.",
                    csTicketMode.getTicketID(), csTicketMode.getCustomer().getUid(),
                    csTicketMode.getCreationtime()));
            LOG.debug(String.format("Is CSTicket %s older than %s? - %s days.",csTicketMode.getTicketID(),
                    Config.getInt("wobduser.disable.time.in.days", 30), isAfter));
        }
        return isAfter;
    }

    private boolean isCustomerWithNoTicketOlderThanThreshold(B2BCustomerModel b2BCustomerModel) {
        boolean isAfter = isGivenDateOlderThanThreshold(b2BCustomerModel.getCreationtime());
        if(LOG.isDebugEnabled()) {
            LOG.debug(String.format("Is WOBD User (With no opportunity) %s older than %s? - %s days.",b2BCustomerModel.getUid(),
                    Config.getInt("wobduser.disable.time.in.days", 30), isAfter));
        }
        return isAfter;
    }

    private boolean isGivenDateOlderThanThreshold(final Date date) {
        boolean isBefore = false;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        LocalDate csCreationDate = LocalDate.of(calendar.get(Calendar.YEAR),
                (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate currentDateMinusThresholdDays = LocalDate.now().minusDays(
                Config.getInt("wobduser.disable.time.in.days", 30));
        if(LOG.isDebugEnabled()) {
            LOG.debug(String.format("Date before %s is - %s days",
                    Config.getInt("wobduser.disable.time.in.days", 30), currentDateMinusThresholdDays));
        }
        if (csCreationDate.isBefore(currentDateMinusThresholdDays)) {
            isBefore = true;
        }
        return isBefore;
    }

    /**
     * Gets sagia user dao.
     *
     * @return the sagia user dao
     */
    public SagiaUserDao getSagiaUserDao() {
        return sagiaUserDao;
    }

    /**
     * Sets sagia user dao.
     *
     * @param sagiaUserDao the sagia user dao
     */
    public void setSagiaUserDao(SagiaUserDao sagiaUserDao) {
        this.sagiaUserDao = sagiaUserDao;
    }
}
