/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.scpi.outbound.services;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.sap.sapcpiadapter.model.SAPCpiOutboundB2BContactModel;
import de.hybris.platform.sap.sapcpiadapter.model.SAPCpiOutboundB2BCustomerModel;
import de.hybris.platform.sap.sapcpiadapter.model.SAPCpiOutboundConfigModel;
import de.hybris.platform.sap.sapcpicustomerexchangeb2b.outbound.services.impl.SapCpiB2BCustomerDefaultConversionService;
import de.hybris.platform.sap.sapmodel.model.SAPLogicalSystemModel;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.sap.hybris.sapcustomerb2b.constants.Sapcustomerb2bConstants.*;

/**
 * Class to convert Hybris B2B Customer to SCPI B2B Customer.
 */
public class InvestsaudiCpiB2BCustomerConversionService extends SapCpiB2BCustomerDefaultConversionService {

  private static final String OBJECT_TYPE = "KNVK";
  public static final String BDUSER = "BDUserGroup";
  public static final String MARCOMM = "MarCommUserGroup";

  @Override
  public SAPCpiOutboundB2BCustomerModel convertB2BCustomerToSapCpiBb2BCustomer(B2BCustomerModel b2bCustomerModel, String sessionLanguage) {
    final SAPCpiOutboundB2BCustomerModel sapCpiOutboundB2BCustomer = new SAPCpiOutboundB2BCustomerModel();
    // Hybris B2B Unit Maps To SAP B2B Customer
    final B2BUnitModel b2bUnit = b2bCustomerModel.getDefaultB2BUnit();
    sapCpiOutboundB2BCustomer.setUid(b2bUnit.getUid());
    sapCpiOutboundB2BCustomer.setFirstName(b2bUnit.getName());
    final Set<SAPCpiOutboundB2BContactModel> sapCpiOutboundB2BContacts = new HashSet<>();
    // Hybris B2B Customer Maps To SAP B2B Contact
    sapCpiOutboundB2BContacts.add(convertB2BContactToSapCpiBb2BContact(b2bUnit, b2bCustomerModel, sessionLanguage));
    sapCpiOutboundB2BCustomer.setSapCpiOutboundB2BContacts(sapCpiOutboundB2BContacts);
    return sapCpiOutboundB2BCustomer;
  }

  @Override
  protected SAPCpiOutboundB2BContactModel convertB2BContactToSapCpiBb2BContact(B2BUnitModel b2bUnitModel, B2BCustomerModel b2bCustomerModel, String sessionLanguage) {

    final SAPCpiOutboundB2BContactModel sapCpiOutboundB2BContact = new SAPCpiOutboundB2BContactModel();
    sapCpiOutboundB2BContact.setUid(b2bCustomerModel.getUid());
    sapCpiOutboundB2BContact.setFirstName(b2bCustomerModel.getName());
    sapCpiOutboundB2BContact.setEmail(b2bCustomerModel.getEmail());
    sapCpiOutboundB2BContact.setEntityCode(b2bCustomerModel.getDefaultB2BUnit().getUid());
    sapCpiOutboundB2BContact.setOtherEntityName(b2bCustomerModel.getOtherUserEntity());
    sapCpiOutboundB2BContact.setDepartment(b2bCustomerModel.getDepartment());
    sapCpiOutboundB2BContact.setPosition("");
    sapCpiOutboundB2BContact.setPhoneCountryCode(b2bCustomerModel.getMobileCountryCode());
    sapCpiOutboundB2BContact.setPhone(b2bCustomerModel.getMobileNumber());
    sapCpiOutboundB2BContact.setGroups(mapB2BCustomerFunction(b2bCustomerModel.getGroups()));
    sapCpiOutboundB2BContact.setSessionLanguage(sessionLanguage);
    sapCpiOutboundB2BContact.setObjType(OBJECT_TYPE);

    return sapCpiOutboundB2BContact;

  }


  private String mapB2BCustomerFunction(final Set<PrincipalGroupModel> groups) {

    final List<String> groupUIDs = groups.stream().map(PrincipalGroupModel::getUid).collect(Collectors.toList());

    if (groupUIDs.containsAll(Arrays.asList(BDUSER))) {
      return BDUSER;
    } else if (groupUIDs.contains(MARCOMM)) {
      return MARCOMM;
    } else {
      return StringUtils.EMPTY;
    }

  }
}