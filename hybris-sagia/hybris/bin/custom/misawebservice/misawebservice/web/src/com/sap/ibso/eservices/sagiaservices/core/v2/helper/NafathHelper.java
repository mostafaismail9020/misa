/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiaservices.core.v2.helper;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;


@Component
public class NafathHelper {

    private static final Logger log = LoggerFactory.getLogger(NafathHelper.class);

    private SignatureVerifier verifier;

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @PostConstruct
    public void init() {
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) factory.generateCertificate(this.getClass().getResourceAsStream(configurationService.getConfiguration().getString("nic.nafath.postservice.certificate")));
            RSAPublicKey publicKey = (RSAPublicKey) certificate.getPublicKey();

            verifier = new RsaVerifier(publicKey);
        } catch (Exception e) {
            log.error("Error initializing RsaVerifier", e);
            verifier = null;
        }
    }

    public Jwt decodeToken(String token) {
        boolean validateToken = configurationService.getConfiguration().getBoolean("nic.nafath.postservice.validatetoken", false);
        if (validateToken) {
            if (verifier != null) {
                return JwtHelper.decodeAndVerify(token, verifier);
            } else {
                throw new RuntimeException("No Rsa Verifier available and nic.nafath.postservice.validatetoken is true");
            }
        } else {
            return JwtHelper.decode(token);
        }
    }

    public SignatureVerifier getVerifier() {
        return verifier;
    }

    public void setVerifier(SignatureVerifier verifier) {
        this.verifier = verifier;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
