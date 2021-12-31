package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaSectorModel;

import java.util.List;

public interface SagiaSectorDAO {

    List<SagiaSectorModel> getAllSectors();

    SagiaSectorModel getSectorForCode(String code);

    SagiaSectorModel getSectorForSectorCode(String sectorCode);
}
