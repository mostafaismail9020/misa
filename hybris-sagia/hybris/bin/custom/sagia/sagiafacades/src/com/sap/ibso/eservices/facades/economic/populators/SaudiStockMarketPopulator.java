
  package com.sap.ibso.eservices.facades.economic.populators;
  
  import com.sap.ibso.eservices.core.model.SaudiStockMarketModel;
  
  import de.hybris.platform.converters.Populator; import
  de.hybris.platform.servicelayer.dto.converter.ConversionException; import
  com.sap.ibso.eservices.facades.data.SaudiStockMarketData;
  
  public class SaudiStockMarketPopulator implements
  Populator<SaudiStockMarketModel, SaudiStockMarketData> {
  
  @Override public void populate(final SaudiStockMarketModel source, final
  SaudiStockMarketData target) throws ConversionException {
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
 