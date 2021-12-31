package com.sap.ibso.eservices.sagiaservices.converters.license.amendment;

import com.sap.ibso.eservices.sagiaservices.converters.ODataReversePopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.*;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.*;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LicenseAmendmentReversePopulator extends ODataReversePopulator<LicenseAmendmentData> {

    private EntityReversePopulator entityReversePopulator;
    private ShareholderReversePopulator shareholderReversePopulator;
    private BranchReversePopulator branchReversePopulator;
    private ProductReversePopulator productReversePopulator;
    private AmendTypeReversePopulator amendTypeReversePopulator;
    private AttachmentReversePopulator attachmentReversePopulator;
    private BusinessActivityReversePopulator businessActivityReversePopulator;

    @Override
    public void populate(LicenseAmendmentData licenseAmendmentData, ODataModel model) throws ConversionException {
        super.populateWithDefaultFieldName(licenseAmendmentData, model);

        EntityData entityData = licenseAmendmentData.getAmendHeaderToAmendEntityNav();
        ODataModel oDataModel = new ODataModel();
        entityReversePopulator.populateWithDefaultFieldName(entityData, oDataModel);
        model.put("AmendHeaderToAmendEntityNav", oDataModel.get());

        setShareholderData(licenseAmendmentData, model);

        setBranchData(licenseAmendmentData, model);

        setProductData(licenseAmendmentData, model);

        setAmendments(licenseAmendmentData, model);

        setAttachments(licenseAmendmentData, model);

        setBusinessActivityData(licenseAmendmentData, model);
    }

    private void setShareholderData(LicenseAmendmentData licenseAmendmentData, ODataModel model) {
        ODataModel oDataModel;
        List<ShareholderData> shareholdersData = licenseAmendmentData.getAmendHeaderToAmendShareHoldNav();
        if (shareholdersData != null && !shareholdersData.isEmpty()) {
            Set<Map> shareholdersSet = new HashSet<>();
            for (ShareholderData shareholder : shareholdersData) {
                oDataModel = new ODataModel();
                shareholderReversePopulator.populateWithDefaultFieldName(shareholder, oDataModel);
                shareholdersSet.add(oDataModel.get());
            }
            model.put("AmendHeaderToAmendShareHoldNav", shareholdersSet);
        }
    }

    private void setBranchData(LicenseAmendmentData licenseAmendmentData, ODataModel model) {
        ODataModel oDataModel;
        List<BranchData> branchesData = licenseAmendmentData.getAmendHeaderToAmendBranchNav();
        if (branchesData != null && !branchesData.isEmpty()) {
            Set<Map> branchesSet = new HashSet<>();
            for (BranchData branchData : branchesData) {
                oDataModel = new ODataModel();
                branchReversePopulator.populateWithDefaultFieldName(branchData, oDataModel);
                branchesSet.add(oDataModel.get());
            }
            model.put("AmendHeaderToAmendBranchNav", branchesSet);
        }
    }

    private void setProductData(LicenseAmendmentData licenseAmendmentData, ODataModel model) {
        ODataModel oDataModel;
        List<ProductData> productsData = licenseAmendmentData.getAmendHeaderToAmendProductNav();
        if (productsData != null && !productsData.isEmpty()) {
            Set<Map> productsSet = new HashSet<>();
            for (ProductData productData : productsData) {
                oDataModel = new ODataModel();
                productReversePopulator.populateWithDefaultFieldName(productData, oDataModel);
                productsSet.add(oDataModel.get());
            }
            model.put("AmendHeaderToAmendProductNav", productsSet);
        }
    }

    private void setAmendments(LicenseAmendmentData licenseAmendmentData, ODataModel model) {
        ODataModel oDataModel;
        AmendTypeData amendmentTypeData = licenseAmendmentData.getAmendHeaderToAmendmentTypeNav();
        if (amendmentTypeData != null) {
            oDataModel = new ODataModel();
            amendTypeReversePopulator.populate(amendmentTypeData, oDataModel);
            model.put("AmendHeaderToAmendmentTypeNav", oDataModel.get());
        }
    }

    private void setAttachments(LicenseAmendmentData licenseAmendmentData, ODataModel model) {
        ODataModel oDataModel;
        List<AmendAttachmentData> attachmentsData = licenseAmendmentData.getAmendHeaderToUploadNav();
        if (attachmentsData != null && !attachmentsData.isEmpty()) {
            Set<Map> attachmentsSet = new HashSet<>();
            for (AmendAttachmentData attachmentData : attachmentsData) {
                oDataModel = new ODataModel();
                attachmentReversePopulator.populateWithDefaultFieldName(attachmentData, oDataModel);
                attachmentsSet.add(oDataModel.get());
            }
            model.put("AmendHeaderToUploadNav", attachmentsSet);
        }
    }

    private void setBusinessActivityData(LicenseAmendmentData licenseAmendmentData, ODataModel model) {
        ODataModel oDataModel;
        List<BusinessActivityData> isicData = licenseAmendmentData.getAmendHeaderToAmendISICActivityNav();
        if (isicData != null && !isicData.isEmpty()) {
            Set<Map> businessActivitiesSet = new HashSet<>();
            for (BusinessActivityData businessActivityData : isicData) {
                oDataModel = new ODataModel();
                businessActivityReversePopulator.populateWithDefaultFieldName(businessActivityData, oDataModel);
                businessActivitiesSet.add(oDataModel.get());
            }
            model.put("AmendHeaderToAmendISICActivityNav", businessActivitiesSet);
        }
    }

    /**
     * @return
     */
    public EntityReversePopulator getEntityReversePopulator() {
        return entityReversePopulator;
    }

    /**
     * @param entityReversePopulator
     */
    public void setEntityReversePopulator(EntityReversePopulator entityReversePopulator) {
        this.entityReversePopulator = entityReversePopulator;
    }

    /**
     * @return
     */
    public ShareholderReversePopulator getShareholderReversePopulator() {
        return shareholderReversePopulator;
    }

    /**
     * @param shareholderReversePopulator
     */
    public void setShareholderReversePopulator(ShareholderReversePopulator shareholderReversePopulator) {
        this.shareholderReversePopulator = shareholderReversePopulator;
    }

    /**
     * @return
     */
    public BranchReversePopulator getBranchReversePopulator() {
        return branchReversePopulator;
    }

    /**
     * @param branchReversePopulator
     */
    public void setBranchReversePopulator(BranchReversePopulator branchReversePopulator) {
        this.branchReversePopulator = branchReversePopulator;
    }

    /**
     * @return
     */
    public ProductReversePopulator getProductReversePopulator() {
        return productReversePopulator;
    }

    /**
     * @param productReversePopulator
     */
    public void setProductReversePopulator(ProductReversePopulator productReversePopulator) {
        this.productReversePopulator = productReversePopulator;
    }

    /**
     * @return
     */
    public AmendTypeReversePopulator getAmendTypeReversePopulator() {
        return amendTypeReversePopulator;
    }

    /**
     * @param amendTypeReversePopulator
     */
    public void setAmendTypeReversePopulator(AmendTypeReversePopulator amendTypeReversePopulator) {
        this.amendTypeReversePopulator = amendTypeReversePopulator;
    }

    /**
     * @return
     */
    public AttachmentReversePopulator getAttachmentReversePopulator() {
        return attachmentReversePopulator;
    }

    /**
     * @param attachmentReversePopulator
     */
    public void setAttachmentReversePopulator(AttachmentReversePopulator attachmentReversePopulator) {
        this.attachmentReversePopulator = attachmentReversePopulator;
    }

    /**
     * @param businessActivityReversePopulator
     */
    public void setBusinessActivityReversePopulator(BusinessActivityReversePopulator businessActivityReversePopulator) {
        this.businessActivityReversePopulator = businessActivityReversePopulator;
    }
}
