package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.sagiaservices.data.moj.MOJInheritSetData;
import com.sap.ibso.eservices.facades.data.MOJInheritSet;
import de.hybris.platform.converters.Populator;

public class MOJInheritPopulator implements Populator<MOJInheritSetData, MOJInheritSet>{

	@Override
    public void populate(MOJInheritSetData mojInheritSetData, MOJInheritSet mojInheritSet) {
		mojInheritSet.setDeceasedId(mojInheritSetData.getDeceasedId());
		mojInheritSet.setDeceasedName(mojInheritSetData.getDeceasedName());
		mojInheritSet.setDeedNumber(mojInheritSetData.getDeedNumber());
		mojInheritSet.setIsMojVerified(mojInheritSetData.getIsMojVerified());
    }
}
