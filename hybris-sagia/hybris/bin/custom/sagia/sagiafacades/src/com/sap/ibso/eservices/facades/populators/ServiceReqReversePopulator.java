package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.AddressHDR;
import com.sap.ibso.eservices.facades.data.ContentHDRDocument;
import com.sap.ibso.eservices.facades.data.ContentHDRImage;
import com.sap.ibso.eservices.facades.data.ServiceRequestHDR;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.fest.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Used by the license renewal process
 */
/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
 * Marker exception needed even if is a RuntimeException
 * The structure of this method is not difficult to understand in the given context.
 */
@SuppressWarnings({"squid:RedundantThrowsDeclarationCheck","squid:MethodCyclomaticComplexity"})
public class ServiceReqReversePopulator implements Populator<ServiceRequestHDR, ServiceReqData> {

    private final static String RE_APPLY_PERIOD = "B";
    @Override
    public void populate(ServiceRequestHDR serviceRequestHDR, ServiceReqData serviceReqData) throws ConversionException {
        serviceReqData.setReApply(serviceRequestHDR.getReApply());
        serviceReqData.setSrGuid(serviceRequestHDR.getSrGuid());
        serviceReqData.setLicenseDuration(serviceRequestHDR.getLicenseDuration());

        SrvReqToAddressNav srvReqToAddressNav = new SrvReqToAddressNav();
        if(serviceRequestHDR.getAddress() != null){
            AddressHDR addressHDR = serviceRequestHDR.getAddress();
            srvReqToAddressNav.setSrID(addressHDR.getSrID());
            srvReqToAddressNav.setStreet(addressHDR.getStreet());
            srvReqToAddressNav.setBuilding(addressHDR.getBuilding());
            srvReqToAddressNav.setHouseNo(addressHDR.getHouseNo());
            srvReqToAddressNav.setCountry(addressHDR.getCountry());
            srvReqToAddressNav.setZipCode(addressHDR.getZipCode());
            srvReqToAddressNav.setAdditNo(addressHDR.getAdditionalNotes());
            srvReqToAddressNav.setCity(addressHDR.getCity());
            if(serviceRequestHDR.getReApply()){
                srvReqToAddressNav.setPeriod(RE_APPLY_PERIOD);
            }
            else{
                srvReqToAddressNav.setPeriod(addressHDR.getPeriod());
            }
        }
        serviceReqData.setSrvReqToAddressNav(srvReqToAddressNav);

        List<SrvReqToContentDetailNav> srvReqToContentDetailNavs = new ArrayList<>();
        if(serviceRequestHDR.getAttachedImages() != null && !serviceRequestHDR.getAttachedImages().isEmpty()) {
            for (ContentHDRImage image : serviceRequestHDR.getAttachedImages()) {
                SrvReqToContentDetailNav srvReqToContentDetailNav = new SrvReqToContentDetailNav();
                if(!Strings.isEmpty(image.getDocumentID())){
                    srvReqToContentDetailNav.setDocumentID(image.getDocumentID());
                }
                if(!Strings.isEmpty(image.getObjectID())){
                    srvReqToContentDetailNav.setObjectID(image.getObjectID());
                }
                srvReqToContentDetailNav.setMimeType(image.getMimetype());
                srvReqToContentDetailNav.setFilename(image.getFileName());
                srvReqToContentDetailNav.setFileContString(image.getFileContentString());
                srvReqToContentDetailNavs.add(srvReqToContentDetailNav);
            }
        }

        if(serviceRequestHDR.getAttachedDocuments() != null && !serviceRequestHDR.getAttachedDocuments().isEmpty()){
            for(ContentHDRDocument document : serviceRequestHDR.getAttachedDocuments()){
                SrvReqToContentDetailNav srvReqToContentDetailNav = new SrvReqToContentDetailNav();
                if(!Strings.isEmpty(document.getDocumentID())){
                    srvReqToContentDetailNav.setDocumentID(document.getDocumentID());
                }
                srvReqToContentDetailNav.setObjectID(document.getObjectId());
                srvReqToContentDetailNav.setMimeType(document.getMimetype());
                srvReqToContentDetailNav.setFilename(document.getFileName());
                srvReqToContentDetailNav.setFileContString(document.getFileContentString());
                srvReqToContentDetailNav.setDockey_ID(document.getKeyID());
                srvReqToContentDetailNavs.add(srvReqToContentDetailNav);
            }
        }

        serviceReqData.setSrvReqToContentDetailNav(srvReqToContentDetailNavs);
    }
}
