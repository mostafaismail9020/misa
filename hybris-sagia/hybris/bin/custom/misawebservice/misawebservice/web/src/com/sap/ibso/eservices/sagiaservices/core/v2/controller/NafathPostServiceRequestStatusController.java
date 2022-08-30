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
package com.sap.ibso.eservices.sagiaservices.core.v2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ibso.eservices.core.sagia.services.NafathService;
import com.sap.ibso.eservices.sagiaservices.core.nafath.data.NafathPostServiceRequestStatus;
import com.sap.ibso.eservices.sagiaservices.core.v2.helper.NafathHelper;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@Controller
@RequestMapping(value = "/nafathPostServiceRequestStatus")
@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 360)
@Api(tags = "Nafath Post Service Request Status")
public class NafathPostServiceRequestStatusController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(NafathPostServiceRequestStatusController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Resource
    private NafathHelper nafathHelper;

    @Resource
    private NafathService nafathService;

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @ApiOperation(nickname = "NafathPostServiceRequestStatus", value = "Nafath Post Service Request Status", notes = "Nafath Post Service Request Status")
    public ResponseEntity nafathPostServiceRequest(@RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
                                                   @ApiParam(value = "Request body parameter that contains the Nafath Post Service Request Status details\n\nThe DTO is in .json format.", required = true) @RequestBody final NafathPostServiceRequestStatus nafathPostServiceRequestStatus) {

        log.debug("Received new request: [{}]", nafathPostServiceRequestStatus != null ? nafathPostServiceRequestStatus.getResponse() : "null");
        String nafathApiKey = configurationService.getConfiguration().getString("nic.nafath.postservice.nafathapikey");
        if (!StringUtils.equals(apiKey, nafathApiKey)) {
            log.warn("Wrong ApiKey received: [{}]", apiKey);
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        try {
            Jwt jwt = nafathHelper.decodeToken(nafathPostServiceRequestStatus.getResponse());

            Map<String, String> payload = mapper.readValue(jwt.getClaims(), Map.class);
            String transId = payload.get("transId");
            String status = payload.get("status");

            log.info("Received transId: [{}], status: [{}]", transId, status);
            nafathService.updateNafathLoginStatus(transId, status);
        } catch (Exception ex) {
            log.error("Error processing JWT token", ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
