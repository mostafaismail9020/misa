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

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.Signer;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import static org.mockito.Mockito.when;

@UnitTest
public class NafathHelperTest {

    private static final Logger log = LoggerFactory.getLogger(NafathHelperTest.class);
    @Mock
    private NafathHelper nafathHelper;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private Configuration configuration;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(configuration.getString("nic.nafath.postservice.certificate")).thenReturn("/nafath/devcert.cer");
        nafathHelper = new NafathHelper();
        nafathHelper.setConfigurationService(configurationService);
        nafathHelper.init();
    }

    private Signer setupSigner() {
        try {
            File pemkey = new File(this.getClass().getResource("/nafath/devkey.key").toURI());
            String key = Files.readString(pemkey.toPath(), Charset.defaultCharset());

            String privateKeyPEM = key
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replaceAll(System.lineSeparator(), "")
                    .replace("-----END PRIVATE KEY-----", "");

            byte[] encoded = Base64.decodeBase64(privateKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            return new RsaSigner(rsaPrivateKey);


        } catch (Exception e) {
            log.error("Error initializing private key", e);
        }

        return null;
    }

    @Test
    public void testValidateToken() {
        when(configuration.getBoolean("nic.nafath.postservice.validatetoken", false)).thenReturn(Boolean.TRUE);
        Signer signer = setupSigner();
        String claim = "{\"test\":\"success\"}";
        Jwt t = JwtHelper.encode(claim, signer);

        Jwt decodeToken = nafathHelper.decodeToken(t.getEncoded());
        String decodedClaim = decodeToken.getClaims();
        Assert.assertEquals(decodedClaim, claim);
    }

    @Test
    public void generateToken() {
        Signer signer = setupSigner();
        String claim =
                "{\n" +
                        "  \"status\":\"COMPLETE\",\n" +
                        "  \"transId\":\"123456\",\n" +
                        "  \"aud\":\"Investsaudi\",\n" +
                        "  \"iss\":\"Nafath App\",\n" +
                        "  \"exp\":1656352085,\n" +
                        "  \"iat\":1656348485,\n" +
                        "  \"nbf\":1656348485\n" +
                        "}\n";

        Jwt t = JwtHelper.encode(claim, signer);

        log.info("Claim: \n{}\n\n Encoded:\n{}", claim, t.getEncoded());
    }
}
