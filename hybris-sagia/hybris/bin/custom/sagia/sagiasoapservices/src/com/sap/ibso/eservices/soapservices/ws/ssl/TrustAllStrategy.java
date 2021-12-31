/*
 * Copyright (c) 2015 Tetrapak
 */

package com.sap.ibso.eservices.soapservices.ws.ssl;

import org.apache.http.conn.ssl.TrustStrategy;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * Created by Iulian Satala on 16/10/2018.
 */
public class TrustAllStrategy implements TrustStrategy
{

	@Override
	public boolean isTrusted(final X509Certificate[] x509Certificates, final String s) throws CertificateException
	{
		return true;
	}
}
