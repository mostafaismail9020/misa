/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 * 
 * package com.sap.ibso.eservices.storefront.controllers.misc;
 * 
 * import
 * com.investsaudi.portal.facades.webinar.InvestSaudiWebinarRegistrationFacade;
 * import com.sap.ibso.eservices.storefront.forms.WebinarRegistrationForm;
 * import de.hybris.platform.acceleratorstorefrontcommons.controllers.
 * AbstractController; import
 * de.hybris.platform.servicelayer.exceptions.ModelSavingException; import
 * org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.ResponseBody;
 * 
 * import javax.annotation.Resource; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import java.sql.SQLException;
 * 
 * @Controller public class WebinarRegistrationController extends
 * AbstractController {
 * 
 * private static final Logger log =
 * LoggerFactory.getLogger(WebinarRegistrationController.class);
 * 
 * @Resource private InvestSaudiWebinarRegistrationFacade
 * investSaudiWebinarRegistrationFacade;
 * 
 * @RequestMapping(value = "/registration", method = RequestMethod.POST,
 * consumes = "application/json", produces = "application/json")
 * 
 * @ResponseBody public String registration(@RequestBody WebinarRegistrationForm
 * webinarRegistrationForm, Model model, HttpServletRequest request,
 * HttpServletResponse response) { try {
 * investSaudiWebinarRegistrationFacade.sendEmailRegistration(
 * webinarRegistrationForm.getUserEmail(),
 * webinarRegistrationForm.getWebinarCode());
 * 
 * } catch (Exception e) { if (e instanceof ModelSavingException) {
 * log.error("User Already Registered", e); return "duplicated"; } else{
 * log.error("Error generating email registration", e); return "error"; } }
 * 
 * return "success";
 * 
 * }
 * 
 * }
 */