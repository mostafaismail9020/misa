package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class HomeHDRPopulator extends ODataPopulator<HomeHDRData> {
    private SocialUserInfoPopulator socialUserInfoPopulator;
    private LicenseInfoPopulator licenseInfoPopulator;
    private HomeContactDetailPopulator homeContactDetailPopulator;
    private ShareHolderInfoPopulator shareHolderInfoPopulator;
    private SalAndEmpPopulator salAndEmpPopulator;
    private ClassificPopulator classificPopulator;

    @Override
    public void populate(ODataModel model, HomeHDRData homeHDRData) throws ConversionException
    {
          super.populate(model,homeHDRData);

        Map<String, Object> map = setSocialUserInfoData(model, homeHDRData);

        setLicenseInfoData(homeHDRData, map);

        setHomeContactDetailDataSet(homeHDRData, map);

        setShareHolderInfoDataSet(homeHDRData, map);

        setSalAndEmpData(homeHDRData, map);

        setClassificData(homeHDRData, map);
    }

    private void setClassificData(HomeHDRData homeHDRData, Map<String, Object> map) {
        ODataEntry homeHDRToClassificNav = (ODataEntry) map.get("HomeHDRToClassificNav");
        if(homeHDRToClassificNav != null) {
            ClassificData classificData = new ClassificData();
            classificPopulator.populate(new ODataModel(homeHDRToClassificNav), classificData);
            classificData.setHomeHDRData(homeHDRData);
            homeHDRData.setClassificData(classificData);
        }
    }

    private void setSalAndEmpData(HomeHDRData homeHDRData, Map<String, Object> map) {
        ODataEntry homeHDRToSalAndEmpNav = (ODataEntry) map.get("HomeHDRToSalAndEmpNav");
        if(homeHDRToSalAndEmpNav != null) {
            SalAndEmpData salAndEmpData = new SalAndEmpData();
            salAndEmpPopulator.populate(new ODataModel(homeHDRToSalAndEmpNav), salAndEmpData);
            salAndEmpData.setHomeHDRData(homeHDRData);
            homeHDRData.setSalAndEmpData(salAndEmpData);
        }
    }

    private void setShareHolderInfoDataSet(HomeHDRData homeHDRData, Map<String, Object> map) {
        ODataFeed homeToShareHolderNav = (ODataFeed) map.get("HomeToShareHolderNav");
        if(homeToShareHolderNav != null && homeToShareHolderNav.getEntries() != null && !homeToShareHolderNav.getEntries().isEmpty()) {
            Set<ShareHolderInfoData> shareHolderInfoDataSet = new HashSet<>();
            for(ODataEntry oDataEntry : homeToShareHolderNav.getEntries()) {
                ShareHolderInfoData shareHolderInfoData = new ShareHolderInfoData();
                shareHolderInfoPopulator.populate(new ODataModel(oDataEntry), shareHolderInfoData);
                shareHolderInfoData.setHomeHDRData(homeHDRData);
                shareHolderInfoDataSet.add(shareHolderInfoData);
            }
            homeHDRData.setShareHolderInfoDataSet(shareHolderInfoDataSet);
        }
    }

    private void setHomeContactDetailDataSet(HomeHDRData homeHDRData, Map<String, Object> map) {
        ODataFeed homeToContactNav = (ODataFeed) map.get("HomeToContactNav");
        if(homeToContactNav != null && homeToContactNav.getEntries() != null && !homeToContactNav.getEntries().isEmpty()) {
            Set<HomeContactDetailData> homeContactDetailDataSet = new HashSet<>();
            for(ODataEntry oDataEntry : homeToContactNav.getEntries()) {
                HomeContactDetailData homeContactDetailData = new HomeContactDetailData();
                homeContactDetailPopulator.populate(new ODataModel(oDataEntry), homeContactDetailData);
                homeContactDetailData.setHomeHDRData(homeHDRData);
                homeContactDetailDataSet.add(homeContactDetailData);
            }
            homeHDRData.setHomeContactDetailDataSet(homeContactDetailDataSet);
        }
    }

    private void setLicenseInfoData(HomeHDRData homeHDRData, Map<String, Object> map) {
        ODataEntry licenseInfo = (ODataEntry) map.get("HomeToLicInfoNav");
        if(licenseInfo != null) {
            LicenseInfoData licenseInfoData = new LicenseInfoData();
            licenseInfoPopulator.populate(new ODataModel(licenseInfo), licenseInfoData);
            licenseInfoData.setHomeHDRData(homeHDRData);
            homeHDRData.setLicenseInfoData(licenseInfoData);
        }
    }

    private Map<String, Object> setSocialUserInfoData(ODataModel model, HomeHDRData homeHDRData) {
        Map<String, Object> map = model.get();
        ODataEntry socialUserInfo = (ODataEntry) map.get("SocialUserInfos");
        if (socialUserInfo != null) {
            SocialUserInfoData socialUserInfoData = new SocialUserInfoData();
            Set<HomeHDRData> homeHDRDataSet = new HashSet<>();
            homeHDRDataSet.add(homeHDRData);
            socialUserInfoPopulator.populate(new ODataModel(socialUserInfo), socialUserInfoData);
            socialUserInfoData.setHomeHDRDataSet(homeHDRDataSet);
            homeHDRData.setSocialUserInfoData(socialUserInfoData);
        }
        return map;
    }

    /**
     * @return
     */
    public SocialUserInfoPopulator getSocialUserInfoPopulator() {
        return socialUserInfoPopulator;
    }

    /**
     * @param socialUserInfoPopulator
     */
    public void setSocialUserInfoPopulator(SocialUserInfoPopulator socialUserInfoPopulator) {
        this.socialUserInfoPopulator = socialUserInfoPopulator;
    }

    /**
     * @return
     */
    public LicenseInfoPopulator getLicenseInfoPopulator() {
        return licenseInfoPopulator;
    }

    /**
     * @param licenseInfoPopulator
     */
    public void setLicenseInfoPopulator(LicenseInfoPopulator licenseInfoPopulator) {
        this.licenseInfoPopulator = licenseInfoPopulator;
    }

    /**
     * @return
     */
    public HomeContactDetailPopulator getHomeContactDetailPopulator() {
        return homeContactDetailPopulator;
    }

    /**
     * @param homeContactDetailPopulator
     */
    public void setHomeContactDetailPopulator(HomeContactDetailPopulator homeContactDetailPopulator) {
        this.homeContactDetailPopulator = homeContactDetailPopulator;
    }

    /**
     * @return
     */
    public ShareHolderInfoPopulator getShareHolderInfoPopulator() {
        return shareHolderInfoPopulator;
    }

    /**
     * @param shareHolderInfoPopulator
     */
    public void setShareHolderInfoPopulator(ShareHolderInfoPopulator shareHolderInfoPopulator) {
        this.shareHolderInfoPopulator = shareHolderInfoPopulator;
    }

    /**
     * @return
     */
    public SalAndEmpPopulator getSalAndEmpPopulator() {
        return salAndEmpPopulator;
    }

    /**
     * @param salAndEmpPopulator
     */
    public void setSalAndEmpPopulator(SalAndEmpPopulator salAndEmpPopulator) {
        this.salAndEmpPopulator = salAndEmpPopulator;
    }

    /**
     * @return
     */
    public ClassificPopulator getClassificPopulator() {
        return classificPopulator;
    }

    /**
     * @param classificPopulator
     */
    public void setClassificPopulator(ClassificPopulator classificPopulator) {
        this.classificPopulator = classificPopulator;
    }
}
