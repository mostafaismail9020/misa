package com.sap.ibso.eservices.facades.sagia.economic;

import java.util.List;
import com.sap.ibso.eservices.facades.data.SAInternationalIndicesData;



public interface SAInternationalIndicesFacade {
	List<SAInternationalIndicesData> getSAInternationalIndicesListData(final String indicator, final Integer startYear, final Integer endYear);
}
