package com.sap.ibso.eservices.storefront.interceptors.beforecontroller;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;   
import java.lang.annotation.ElementType;   

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LicenseRequired {

}
