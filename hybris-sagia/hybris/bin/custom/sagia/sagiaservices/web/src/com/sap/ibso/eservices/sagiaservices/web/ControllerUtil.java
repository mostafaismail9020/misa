package com.sap.ibso.eservices.sagiaservices.web;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ControllerUtil {
    public static ResponseEntity createResponsePayload(HttpServletRequest request) {
        try {
            return new ResponseEntity(IOUtils.toString(request.getReader()), HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    public static ResponseEntity createResponsePayload(String requestBody) {
        return new ResponseEntity(requestBody, HttpStatus.CREATED);
    }
}
