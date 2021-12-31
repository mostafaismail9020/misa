package com.sap.ibso.eservices.sagiaservices.converters.license.amendment;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.*;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LicenseAmendmentPopulator extends ODataPopulator<LicenseAmendmentData> {

    private EntityPopulator entityPopulator;
    private ShareholderPopulator shareholderPopulator;
    private BranchPopulator branchPopulator;
    private ProductPopulator productPopulator;
    private TextPopulator textPopulator;

    @Override
    public void populate(ODataModel model, LicenseAmendmentData licenseAmendmentData) throws ConversionException {
        super.populate(model, licenseAmendmentData);

        Map<String, Object> map = setAmendHeaderToAmendEntityNav(model, licenseAmendmentData);

        setAmendHeaderToAmendShareHoldNav(licenseAmendmentData, map);

        setAmendHeaderToAmendBranchNav(licenseAmendmentData, map);

        setAmendHeaderToAmendProductNav(licenseAmendmentData, map);

        setAmendHeaderToAmendTextNav(licenseAmendmentData, map);
    }

    private Map<String, Object> setAmendHeaderToAmendEntityNav(ODataModel model, LicenseAmendmentData licenseAmendmentData) {
        Map<String, Object> map = model.get();
        ODataEntry amendEntityNav = (ODataEntry) map.get("AmendHeaderToAmendEntityNav");
        if (amendEntityNav != null) {
            EntityData entityData = new EntityData();
            entityPopulator.populate(new ODataModel(amendEntityNav), entityData);
            licenseAmendmentData.setAmendHeaderToAmendEntityNav(entityData);
        }
        return map;
    }

    private void setAmendHeaderToAmendTextNav(LicenseAmendmentData licenseAmendmentData, Map<String, Object> map) {
        ODataFeed textFeed = (ODataFeed) map.get("AmendHeaderToTextNav");
        if(textFeed != null && textFeed.getEntries() != null && !textFeed.getEntries().isEmpty()) {
            List<TextData> comments = new ArrayList<>();
            for(ODataEntry commentEntry : textFeed.getEntries()) {
                TextData textData = new TextData();
                textPopulator.populate(new ODataModel(commentEntry), textData);
                comments.add(textData);
            }
            licenseAmendmentData.setAmendHeaderToTextNav(comments);
        }
    }

    private void setAmendHeaderToAmendShareHoldNav(LicenseAmendmentData licenseAmendmentData, Map<String, Object> map) {
        ODataFeed shareholdersFeed = (ODataFeed) map.get("AmendHeaderToAmendShareHoldNav");
        if(shareholdersFeed != null && shareholdersFeed.getEntries() != null && !shareholdersFeed.getEntries().isEmpty()) {
            List<ShareholderData> shareholders = new ArrayList<>();
            for(ODataEntry shareholderEntry : shareholdersFeed.getEntries()) {
                ShareholderData shareholderData = new ShareholderData();
                shareholderPopulator.populate(new ODataModel(shareholderEntry), shareholderData);
                shareholders.add(shareholderData);
            }
            licenseAmendmentData.setAmendHeaderToAmendShareHoldNav(shareholders);
        }
    }

    private void setAmendHeaderToAmendBranchNav(LicenseAmendmentData licenseAmendmentData, Map<String, Object> map) {
        ODataFeed branchesFeed = (ODataFeed) map.get("AmendHeaderToAmendBranchNav");
        if(branchesFeed != null && branchesFeed.getEntries() != null && !branchesFeed.getEntries().isEmpty()) {
            List<BranchData> branches = new ArrayList<>();
            for(ODataEntry branchEntry : branchesFeed.getEntries()) {
                BranchData branchData = new BranchData();
                branchPopulator.populate(new ODataModel(branchEntry), branchData);
                branches.add(branchData);
            }
            licenseAmendmentData.setAmendHeaderToAmendBranchNav(branches);
        }
    }

    private void setAmendHeaderToAmendProductNav(LicenseAmendmentData licenseAmendmentData, Map<String, Object> map) {
        ODataFeed productsFeed = (ODataFeed) map.get("AmendHeaderToAmendProductNav");
        if(productsFeed != null && productsFeed.getEntries() != null && !productsFeed.getEntries().isEmpty()) {
            List<ProductData> products = new ArrayList<>();
            for(ODataEntry productEntry : productsFeed.getEntries()) {
                ProductData productData = new ProductData();
                productPopulator.populate(new ODataModel(productEntry), productData);
                products.add(productData);
            }
            licenseAmendmentData.setAmendHeaderToAmendProductNav(products);
        }
    }

    /**
     * @return
     */
    public EntityPopulator getEntityPopulator() {
        return entityPopulator;
    }

    /**
     * @param entityPopulator
     */
    public void setEntityPopulator(EntityPopulator entityPopulator) {
        this.entityPopulator = entityPopulator;
    }

    /**
     * @return
     */
    public ShareholderPopulator getShareholderPopulator() {
        return shareholderPopulator;
    }

    /**
     * @param shareholderPopulator
     */
    public void setShareholderPopulator(ShareholderPopulator shareholderPopulator) {
        this.shareholderPopulator = shareholderPopulator;
    }

    /**
     * @return
     */
    public BranchPopulator getBranchPopulator() {
        return branchPopulator;
    }

    /**
     * @param branchPopulator
     */
    public void setBranchPopulator(BranchPopulator branchPopulator) {
        this.branchPopulator = branchPopulator;
    }

    /**
     * @return
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

    public TextPopulator getTextPopulator() {
        return textPopulator;
    }

    public void setTextPopulator(TextPopulator textPopulator) {
        this.textPopulator = textPopulator;
    }
}