package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.license.amendment.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.*;
import de.hybris.platform.converters.Populator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LicenseAmendmentPopulator implements Populator<LicenseAmendmentData, LicenseAmendment> {

    private static final String DEFAULT_LABOUR_VALUE = "0";
    private static final String DEFAULT_CAPITAL_VALUE = "0";

    private ShareholderPopulator shareholderPopulator;
    private BranchPopulator branchPopulator;
    private ProductPopulator productPopulator;

    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(LicenseAmendmentData licenseAmendmentData, LicenseAmendment licenseAmendment) {
        licenseAmendment.setId(licenseAmendmentData.getSrID());
        licenseAmendment.setStatus(licenseAmendmentData.getSrStDesc());
        licenseAmendment.setLicenseType(licenseAmendmentData.getLicType());
        licenseAmendment.setLicenseTypeText(licenseAmendmentData.getLicTypeDesc());
        licenseAmendment.setBpId(licenseAmendmentData.getBpID());

        if (licenseAmendmentData.getSrCrDate() != null) {
            licenseAmendment.setCreatedDate(sagiaFormatProvider.getLocalizedDateData(licenseAmendmentData.getSrCrDate()));
        }

        setEntity(licenseAmendmentData, licenseAmendment);

        int id = 1;

        List<ShareholderData> shareholdersData = licenseAmendmentData.getAmendHeaderToAmendShareHoldNav();
        if (shareholdersData != null && !shareholdersData.isEmpty()) {
            ArrayList<Shareholder> shareholders = new ArrayList<>();
            for (ShareholderData shareholderData : shareholdersData) {
                Shareholder shareholder = new Shareholder();
                shareholder.setSrId(String.valueOf(id++));
                shareholderPopulator.populate(shareholderData, shareholder);
                shareholders.add(shareholder);
            }
            licenseAmendment.setShareholders(shareholders);
        }

        List<BranchData> branchesData = licenseAmendmentData.getAmendHeaderToAmendBranchNav();
        if (branchesData != null && !branchesData.isEmpty()) {
            ArrayList<Branch> branches = new ArrayList<>();
            for (BranchData branchData : branchesData) {
                Branch branch = new Branch();
                branch.setSrId(String.valueOf(id++));
                branchPopulator.populate(branchData, branch);
                branches.add(branch);
            }
            licenseAmendment.setBranches(branches);
        }

        setProductData(licenseAmendmentData, licenseAmendment, id);
        ArrayList<Text> comments = new ArrayList<>();
        if (licenseAmendmentData.getAmendHeaderToTextNav() != null) {
            for (TextData textData : licenseAmendmentData.getAmendHeaderToTextNav()) {
                Text text = new Text();
                text.setComment(textData.getComments());
                comments.add(text);
            }
            licenseAmendment.setTexts(comments);
        }
    }

    private void setEntity(LicenseAmendmentData licenseAmendmentData, LicenseAmendment licenseAmendment) {
        EntityData entityData = licenseAmendmentData.getAmendHeaderToAmendEntityNav();
        if (entityData != null) {
            Entity entity = new Entity();
            entity.setBpId(entityData.getBpID());
            entity.setName(entityData.getBrandName());
            entity.setNameOld(entityData.getBrandNameOld());

            String labour = entityData.getLabour();
            entity.setLabour(labour == null || labour.isEmpty() ? DEFAULT_LABOUR_VALUE : labour);

            String labourOld = entityData.getLabourOld();
            entity.setLabourOld(labourOld == null || labourOld.isEmpty() ? DEFAULT_LABOUR_VALUE : labourOld);

            String capital = entityData.getCapital();
            entity.setCapital(capital == null || capital.isEmpty() ? DEFAULT_CAPITAL_VALUE : capital);

            String capitalOld = entityData.getCapitalOld();
            entity.setCapitalOld(capitalOld == null || capitalOld.isEmpty() ? DEFAULT_CAPITAL_VALUE : capitalOld);

            entity.setLegalStatus(entityData.getLegalStatus());
            entity.setLegalStatusOld(entityData.getLegalStausOld());
            entity.setActivity(entityData.getActivity());
            entity.setActivityOld(entityData.getActivityOld());
            licenseAmendment.setEntity(entity);
        }
    }

    private void setProductData(LicenseAmendmentData licenseAmendmentData, LicenseAmendment licenseAmendment, int id) {
        List<ProductData> productsData = licenseAmendmentData.getAmendHeaderToAmendProductNav();
        if (productsData != null && !productsData.isEmpty()) {
            ArrayList<Product> products = new ArrayList<>();
            for (ProductData productData : productsData) {
                Product product = new Product();
                product.setSrId(String.valueOf(id++));
                productPopulator.populate(productData, product);
                products.add(product);
            }
            licenseAmendment.setProducts(products);
        }
    }

    /**
     * @return ShareholderPopulator
     */
    public ShareholderPopulator getShareholderPopulator() {
        return shareholderPopulator;
    }

    /**
     * @param shareholderPopulator shareholderPopulator
     */
    public void setShareholderPopulator(ShareholderPopulator shareholderPopulator) {
        this.shareholderPopulator = shareholderPopulator;
    }

    /**
     * @return BranchPopulator
     */
    public BranchPopulator getBranchPopulator() {
        return branchPopulator;
    }

    /**
     * @param branchPopulator branchPopulator
     */
    public void setBranchPopulator(BranchPopulator branchPopulator) {
        this.branchPopulator = branchPopulator;
    }

    /**
     * @return ProductPopulator
     */
    public ProductPopulator getProductPopulator() {
        return productPopulator;
    }

    /**
     * @param productPopulator
     */
    public void setProductPopulator(ProductPopulator productPopulator) {
        this.productPopulator = productPopulator;
    }

    /**
     * @return SagiaFormatProvider
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
