package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.facades.data.license.amendment.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.*;
import de.hybris.platform.converters.Populator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LicenseAmendmentReversePopulator implements Populator<LicenseAmendment, LicenseAmendmentData> {

    private static final String APPLICATION_PDF = "application/pdf";
    @Autowired
    private ShareholderReversePopulator shareholderReversePopulator;

    @Autowired
    private BranchReversePopulator branchReversePopulator;

    @Autowired
    private ProductReversePopulator productReversePopulator;

    @Override
    public void populate(LicenseAmendment licenseAmendment, LicenseAmendmentData licenseAmendmentData) {

        licenseAmendmentData.setAction(licenseAmendment.getAction());
        licenseAmendmentData.setTransType(licenseAmendment.getTransactionType());
        licenseAmendmentData.setIsInstant(licenseAmendment.getInstantAmendment());
        licenseAmendmentData.setLicType(licenseAmendment.getLicenseType());
        licenseAmendmentData.setLicTypeDesc(licenseAmendment.getLicenseTypeText());

        setEntityData(licenseAmendment, licenseAmendmentData);

        setShareholders(licenseAmendment, licenseAmendmentData);

        setBranches(licenseAmendment, licenseAmendmentData);

        setProducts(licenseAmendment, licenseAmendmentData);

        setAmendmentTypes(licenseAmendment, licenseAmendmentData);

        setAttachments(licenseAmendment, licenseAmendmentData);

        setBusinessActivities(licenseAmendment, licenseAmendmentData);
    }

    private void setEntityData(LicenseAmendment licenseAmendment, LicenseAmendmentData licenseAmendmentData) {
        EntityData entityData = new EntityData();
        Entity entity = licenseAmendment.getEntity();
        if (entity != null) {
            entityData.setAction(entity.getAction());
            entityData.setBpID(entity.getBpId());
            entityData.setBrandName(entity.getName());
            entityData.setBrandNameOld(entity.getNameOld());
            entityData.setCapital(entity.getCapital());
            entityData.setCapitalOld(entity.getCapitalOld());
            entityData.setLabour(entity.getLabour());
            entityData.setLabourOld(entity.getLabourOld());
            entityData.setLegalStatus(entity.getLegalStatus());
            entityData.setLegalStausOld(entity.getLegalStatusOld());
            entityData.setActivity(entity.getActivity());
            entityData.setActivityOld(entity.getActivityOld());
        }
        licenseAmendmentData.setAmendHeaderToAmendEntityNav(entityData);
    }

    private void setShareholders(LicenseAmendment licenseAmendment, LicenseAmendmentData licenseAmendmentData) {
        List<Shareholder> shareholders = licenseAmendment.getShareholders();
        if (shareholders != null && !shareholders.isEmpty()) {
            ArrayList<ShareholderData> shareholdersData = new ArrayList<>();
            for (Shareholder shareholder : shareholders) {
                ShareholderData shareholderData = new ShareholderData();
                shareholderReversePopulator.populate(shareholder, shareholderData);
                shareholdersData.add(shareholderData);
            }
            licenseAmendmentData.setAmendHeaderToAmendShareHoldNav(shareholdersData);
        }
    }

    private void setBranches(LicenseAmendment licenseAmendment, LicenseAmendmentData licenseAmendmentData) {
        List<Branch> branches = licenseAmendment.getBranches();
        if (branches != null && !branches.isEmpty()) {
            ArrayList<BranchData> branchesData = new ArrayList<>();
            for (Branch branch : branches) {
                BranchData branchData = new BranchData();
                branchReversePopulator.populate(branch, branchData);
                branchesData.add(branchData);
            }
            licenseAmendmentData.setAmendHeaderToAmendBranchNav(branchesData);
        }
    }

    private void setProducts(LicenseAmendment licenseAmendment, LicenseAmendmentData licenseAmendmentData) {
        List<Product> products = licenseAmendment.getProducts();
        if (products != null && !products.isEmpty()) {
            ArrayList<ProductData> productsData = new ArrayList<>();
            for (Product product : products) {
                ProductData productData = new ProductData();
                productReversePopulator.populate(product, productData);
                productsData.add(productData);
            }
            licenseAmendmentData.setAmendHeaderToAmendProductNav(productsData);
        }
    }

    private void setAmendmentTypes(LicenseAmendment licenseAmendment, LicenseAmendmentData licenseAmendmentData) {
        LicenseAmendmentTypeData amendmentTypesData = licenseAmendment.getAmendmentTypesData();
        if (amendmentTypesData != null) {
            AmendTypeData amendTypeData = new AmendTypeData();
            amendTypeData.setShReQu(amendmentTypesData.getShReQu());
            amendTypeData.setShRem(amendmentTypesData.getShRem());
            amendTypeData.setShInProp(amendmentTypesData.getShInProp());
            amendTypeData.setShAddExNew(amendmentTypesData.getShAddExNew());
            amendTypeData.setBrOpen(amendmentTypesData.getBrOpen());
            amendTypeData.setBrClose(amendmentTypesData.getBrClose());
            amendTypeData.setEnLocCh(amendmentTypesData.getEnLocCh());
            amendTypeData.setPrAddReCh(amendmentTypesData.getPrAddReCh());
            amendTypeData.setPrDescChange(amendmentTypesData.getPrDescChange());
            amendTypeData.setEnActCh(amendmentTypesData.getEnActCh());
            amendTypeData.setEnCapIncr(amendmentTypesData.getEnCapIncr());
            amendTypeData.setEnCapRed(amendmentTypesData.getEnCapRed());
            amendTypeData.setEnIncrWf(amendmentTypesData.getEnIncrWf());
            amendTypeData.setEnNameCh(amendmentTypesData.getEnNameCh());
            amendTypeData.setEnEstToLlc(amendmentTypesData.getEnEstToLlc());
            amendTypeData.setEnLegalstatToIllc(amendmentTypesData.getEnLegalstatToIllc());
            licenseAmendmentData.setAmendHeaderToAmendmentTypeNav(amendTypeData);
        }
    }

    private void setAttachments(LicenseAmendment licenseAmendment, LicenseAmendmentData licenseAmendmentData) {
        List<LicenseAmendmentAttachment> attachments = licenseAmendment.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            List<AmendAttachmentData> attachmentsData = new ArrayList<>();
            for (LicenseAmendmentAttachment attachment : attachments) {
                AmendAttachmentData attachmentData = new AmendAttachmentData();
                attachmentData.setFilename(attachment.getName());
                attachmentData.setFileContString(attachment.getContent());
                attachmentData.setDockey_ID(attachment.getDockeyId());
                attachmentData.setMimeType(APPLICATION_PDF);
                attachmentsData.add(attachmentData);
            }
            licenseAmendmentData.setAmendHeaderToUploadNav(attachmentsData);
        }
    }

    private void setBusinessActivities(LicenseAmendment licenseAmendment, LicenseAmendmentData licenseAmendmentData) {
        List<BusinessActivity> businessActivities = licenseAmendment.getBusinessActivities();
        if (businessActivities != null && !businessActivities.isEmpty()) {
            ArrayList<BusinessActivityData> businessActivitiesData = new ArrayList<>();
            for (BusinessActivity businessActivity : businessActivities) {
                BusinessActivityData businessActivityData = new BusinessActivityData();
                businessActivityData.setIsicID(businessActivity.getId());
                businessActivityData.setDescriptionAR(businessActivity.getDescription());
                businessActivityData.setDescriptionEN(businessActivity.getDescription());
                businessActivitiesData.add(businessActivityData);
            }
            licenseAmendmentData.setAmendHeaderToAmendISICActivityNav(businessActivitiesData);
        }
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
}
