package com.investsaudi.portal.core.service;

import com.investsaudi.portal.core.model.InvestSaudiWebinarModel;
import com.investsaudi.portal.core.model.InvestSaudiWebinarRegistrationModel;

import java.io.Serializable;

public interface InvestSaudiWebinarRegistrationService extends Serializable {

    InvestSaudiWebinarRegistrationModel createNewWebinarRegistration(String userEmail, String webinarCode);

    void sendWebinarRegistrationEmail(InvestSaudiWebinarModel investSaudiWebinarModel, String userEmail);
}
