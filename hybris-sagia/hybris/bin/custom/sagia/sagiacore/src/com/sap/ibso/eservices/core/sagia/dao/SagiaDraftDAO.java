/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaDraftModel;

import java.util.Date;
import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.dao
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaDraftDAO {

    List<SagiaDraftModel> getDraft(String userPk, String formId);

    List<SagiaDraftModel> getDrafts(String userPk);

    List<SagiaDraftModel> getDrafts(Date date);

    Long getMaxTemporaryCode();
}
