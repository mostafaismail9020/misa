package com.sap.ibso.eservices.facades.sagia;



import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.facades.data.odata.ServiceRequestCreation;
import com.sap.ibso.eservices.sagiaservices.data.odata.BasicContactInformationData;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntityInformationData;
import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderData;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * Provides access to SagiaODataFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaODataFacade {
   
    /**
     * create a new service request
     * 
     * @param serviceRequestCreation
     * @param customer
     * @return
     */
    public ServiceRequestCreation saveODataServiceRequestCreation(CustomerModel customer);
    
    
    public BasicContactInformationData saveContactPersonOData(ContactPersonModel contactPersonModel);
    
    public EntityInformationData saveEntityOData(EntityInformationModel entityInformationModel);
    
    public ShareholderData saveShareHolderOData(ShareHolderModel shareHolderModel);
    
    public String uploadAttachmentOData(MediaModel mediaModel,String delegate,String refid,String guid,String fileType,String objectid,String shCode);

    public void savesIsicInfoOData(SagiaLicenseModel license);
}
