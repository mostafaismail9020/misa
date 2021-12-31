package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaInvestorNotificationModel;

import java.util.List;
import java.util.Optional;

public interface SagiaInvestorNotificationDAO {

    List<SagiaInvestorNotificationModel> getNotificationsFor(String investorId);

    Optional<SagiaInvestorNotificationModel> getNotificationBy(String investorId, String transactionId);
}
