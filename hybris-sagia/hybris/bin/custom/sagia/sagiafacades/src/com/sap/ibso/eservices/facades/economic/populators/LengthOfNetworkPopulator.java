
  package com.sap.ibso.eservices.facades.economic.populators;
  
  import com.sap.ibso.eservices.core.model.LengthOfNetworkModel;
  
  import de.hybris.platform.converters.Populator; import
  de.hybris.platform.servicelayer.dto.converter.ConversionException; import
  com.sap.ibso.eservices.facades.data.LengthOfNetworkData;
  
  public class LengthOfNetworkPopulator implements Populator<LengthOfNetworkModel, LengthOfNetworkData>
  {
  
  @Override public void populate(final LengthOfNetworkModel source, final LengthOfNetworkData
  target) throws ConversionException {
	  target.setNetworkName(source.getNetworkName());
	  target.setNetworkValue(source.getNetworkValue());
  }
  }
 