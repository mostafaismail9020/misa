package com.sap.ibso.eservices.storefront.controllers.pages.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.SagiaInvalidCRMResponseException;
import com.sap.ibso.eservices.sagiaservices.utils.JsonUtils;
import com.sap.ibso.eservices.storefront.exceptions.SagiaUserWithoutLicenseException;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler extends AbstractPageController {
    private static final String EXCEPTION_ATTRIBUTE = "exception";
    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);
    private static final String ERROR_MESSAGE = "errorMessage";

    /**
     * global handler for exceptions of type SagiaODataException
     * read "Accept" header field. If set to "application/json", return in json format
     * If the "Accept" header is missing/different than "application/json", return .html format
     * parse the error response received from CRM into a valid json
     * @return
     */
    @ExceptionHandler(SagiaODataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Object handleError(HttpServletRequest request, HttpServletResponse response, Model model, SagiaODataException exception) {
        LOG.error(exception.getErrorMessage(), exception);
        try {
            SagiaExceptionResponse exceptionResponse = new SagiaExceptionResponse()
                    .setDescription(exception.getDescription())
                    .setMessage(exception.getErrorMessage());
            if (isAcceptJson(request)) {
                return viewAsJson(exceptionResponse);
            }
            if(request.getAttribute(EXCEPTION_ATTRIBUTE) == null) {
                request.setAttribute(EXCEPTION_ATTRIBUTE, exception);
                return "forward:/sagia-error/odata";
            } else { //it is an error that is thrown for any requests so if not treated as such it will reach stackoverflow
                request.setAttribute(ERROR_MESSAGE, exception.getMessage());
                return "pages/error/sagiaODataException";
            }
        } catch (Exception error) {
            LOG.error(error.getMessage(), error);
            return handleGenericError(request, response, model, error);
        }
    }
    
    /**
     * Catch the CRM Exceptions and parse the message
     * @param request
     * @param model
     * @param exception
     * @return
     */
	@ExceptionHandler(SagiaCRMException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Object handleCRMError(HttpServletRequest request, HttpServletResponse response, Model model, SagiaCRMException exception) {
		LOG.error(exception.getErrorMessage(), exception);
		try {
			String parsedCrmResponse = parseCRMExceptionResponse(exception.getMessage());
			if (isAcceptJson(request)) {
				SagiaExceptionResponse exceptionResponse = new SagiaExceptionResponse()
						.setDescription(exception.getDescription())
						.setMessage(parsedCrmResponse);
				return viewAsJson(exceptionResponse);
			}
			if (request.getAttribute(EXCEPTION_ATTRIBUTE) == null) {
				request.setAttribute(EXCEPTION_ATTRIBUTE, new SagiaCRMException(parsedCrmResponse));
				return "forward:/sagia-error/odata";
			} else {
				request.setAttribute(ERROR_MESSAGE, parsedCrmResponse);
				return "pages/error/sagiaODataException";
			}
		} catch (Exception error) {
			LOG.error(error.getMessage(), error);
			return handleGenericError(request, response, model, error);
		}
	}

    /**
     * a generic error handler
     * for exceptions that were not treated by the other error handlers
     * if "application/json" is requested, return json, otherwise return html
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleGenericError(HttpServletRequest request, HttpServletResponse response, Model model, Exception exception) {
        LOG.error(exception.getMessage(), exception);
        try {
            SagiaExceptionResponse exceptionResponse = new SagiaExceptionResponse()
                    .setDescription(exception.getMessage())
                    .setMessage(getMessageSource().getMessage(exception.getMessage(), null, getI18nService().getCurrentLocale()));
            if (isAcceptJson(request)) {
                return viewAsJson(exceptionResponse);
            }
            if (request.getAttribute(EXCEPTION_ATTRIBUTE) == null) {
                request.setAttribute(EXCEPTION_ATTRIBUTE, exception);
                return "forward:/sagia-error/generic";
            } else { //it is an error that is thrown for any requests so if not treated as such it will reach stackoverflow
                request.setAttribute(ERROR_MESSAGE, exceptionResponse.getMessage());
                return "pages/error/sagiaGenericException";
            }
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                response.sendRedirect(request.getContextPath() + "/login");
            } catch(Exception e1) {
                LOG.error(e1.getMessage(), e1);
            }
            return null;
        }
    }
    
    /**
     * handle user without license exception
     */
    @ExceptionHandler(SagiaUserWithoutLicenseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleGenericError(HttpServletRequest request, Model model, SagiaUserWithoutLicenseException exception) {
        LOG.error(exception.getMessage(), exception);
        SagiaExceptionResponse exceptionResponse = new SagiaExceptionResponse()
                  .setDescription(exception.getMessage())
                  .setMessage(getMessageSource().getMessage("sagia.globalError", null, getI18nService().getCurrentLocale()));
        if (isAcceptJson(request)) {
            return viewAsJson(exceptionResponse);
        }
        if(request.getAttribute(EXCEPTION_ATTRIBUTE) == null) {
            request.setAttribute(EXCEPTION_ATTRIBUTE, exception);
            return "forward:/sagia-error/generic";
        } else { //it is an error that is thrown for any requests so if not treated as such it will reach stackoverflow
            request.setAttribute(ERROR_MESSAGE, exceptionResponse.getMessage());
            return "pages/error/sagiaGenericException";
        }
    }

    /**
     * if the CRM error response is not similar with the generic template,
     * that begins with an HTTP status code and then with the actual json part,
     * try to extract the error message from the actual response
     */
    private String parseCRMExceptionResponse(String crmResponse) {
        if(crmResponse == null || crmResponse.isEmpty()) {
            return "";
        }
        try {
            if (crmResponse.startsWith("HTTP") || !crmResponse.startsWith("{")) {
                String response = extractTextStartingFrom(crmResponse, "{");
                return readMessageFromJsonResponse(response);
            }
            return readMessageFromJsonResponse(crmResponse);
        } catch(Exception e) {
            LOG.error(e.getMessage(),e);
            return crmResponse;
        }
    }

    /**
     * each CRM error response begins with a status code, then with the json value
     * extract only the json part
     */
    private String extractTextStartingFrom(String text, String delimiter) {
        return text.substring(text.indexOf(delimiter)).trim();
    }

    /**
     * if the CRM error response is not a valid json, throw exception
     */
    private String readMessageFromJsonResponse(String response) {
        if (JsonUtils.isJSONValid(response)) {
            return readErrorMessageValueFrom(response);
        } else {
            throw new SagiaInvalidCRMResponseException("could not parse response to json");
        }
    }

    /**
     * each CRM error response is in json format
     * read the short error message
     * throw exception if the error message could not be read from the CRM response
     */
    private String readErrorMessageValueFrom(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode statusNode = root.path("error").path("message").path("value");
            return statusNode.textValue();
        } catch (IOException e) {
            LOG.debug(e.getMessage(), e);
            return response; //leave unparsed
        }
    }

    /**
     * @return error response in JSON format
     */
	private ModelAndView viewAsJson(SagiaExceptionResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setContentType("application/json");
		modelAndView.addObject(response);
		modelAndView.setView(jsonView);
		return modelAndView;
	}

    /**
     * check if the response is requested as json format
     */
    private boolean isAcceptJson(HttpServletRequest request) {
        try {
            return request.getHeader("Accept").contains("application/json");
        } catch(Exception e) {
            LOG.debug(e.getMessage(), e);
            return false;
        }
    }
}
