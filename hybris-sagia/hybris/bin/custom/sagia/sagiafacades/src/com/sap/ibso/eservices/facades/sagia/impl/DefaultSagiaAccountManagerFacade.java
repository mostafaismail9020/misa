/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.facades.sagia.impl;

import org.fest.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ibso.eservices.facades.data.AccountManagerData;
import com.sap.ibso.eservices.facades.sagia.SagiaAccountManagerFacade;
import com.sap.ibso.eservices.sagiaservices.account.AccountManagerService;
import com.sap.ibso.eservices.sagiaservices.data.account.DedicatedAccountManagerData;

/**
 * DefaultSagiaAccountManagerFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.sagia.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaAccountManagerFacade implements SagiaAccountManagerFacade
{

	private AccountManagerService accountManagerService;

	private static final Logger LOG = LoggerFactory.getLogger(DefaultSagiaAccountManagerFacade.class);

	@Override
	public AccountManagerData getAccountManagerData()
	{
		try {
			final DedicatedAccountManagerData dedicatedAccountManagerData = accountManagerService.getDedicatedAccountManagerData();

			final AccountManagerData accountManagerData = new AccountManagerData();
			accountManagerData.setTitle(dedicatedAccountManagerData.getTitle());
			accountManagerData.setEmail(dedicatedAccountManagerData.getEmail());
			accountManagerData.setPhoneNumber(dedicatedAccountManagerData.getPhoneNumber());
			accountManagerData.setMobileNumber(dedicatedAccountManagerData.getMobileNumber());
			accountManagerData.setLastName(dedicatedAccountManagerData.getLastName());
			accountManagerData.setFirstName(dedicatedAccountManagerData.getFirstName());

			if (!Strings.isEmpty(dedicatedAccountManagerData.getFirstName()) ||
							!Strings.isEmpty(dedicatedAccountManagerData.getLastName()))
			{
				accountManagerData.setGeneral(true);
			}
			else
			{
				accountManagerData.setGeneral(false);
			}

			return  accountManagerData;
		}
		catch (Exception ex)
		{
			LOG.error(ex.getMessage(), ex);
			final AccountManagerData data =  new AccountManagerData();
			data.setGeneral(true);
			return data;
		}


	}

	/**
	 * @return
	 */
	public AccountManagerService getAccountManagerService()
	{
		return accountManagerService;
	}

	/**
	 * @param accountManagerService
	 */
	public void setAccountManagerService(final AccountManagerService accountManagerService)
	{
		this.accountManagerService = accountManagerService;
	}
}
