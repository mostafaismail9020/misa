
  package com.sap.ibso.eservices.facades.economic.populators;
  
  import com.sap.ibso.eservices.core.model.GovernmentDebtModel;
  
  import de.hybris.platform.converters.Populator; import
  de.hybris.platform.servicelayer.dto.converter.ConversionException; import
  com.sap.ibso.eservices.facades.data.GovernmentDebtData;
  
  public class GovernmentDebtPopulator implements
  Populator<GovernmentDebtModel, GovernmentDebtData> {
  
  @Override public void populate(final GovernmentDebtModel source, final
  GovernmentDebtData target) throws ConversionException {
	  target.setValue(source.getValue());
	  target.setValueLabel(source.getValueLabel());
	  target.setPercentage(source.getPercentage());
	  target.setPercentageLabel(source.getPercentageLabel());
	  target.setName(source.getName());
	  target.setDisplayName(source.getDisplayName());
	  target.setCalenderValue(source.getCalenderValue());
	  //target.setCurrenyValue(source.getCurrenyValue());
	  
  
  }
  
  }
 