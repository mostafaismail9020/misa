package com.sap.ibso.eservices.sagiaservices.jobs;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.IsicTextsModel;
import com.sap.ibso.eservices.sagiaservices.data.IsicMasterSetData;
import com.sap.ibso.eservices.sagiaservices.data.IsicTextsSetData;
import com.sap.ibso.eservices.sagiaservices.services.isic.IsicMasterDataService;
import com.sap.ibso.eservices.sagiaservices.services.isic.IsicTextsService;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.IsicMasterDataCronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

public class IsicMasterDataSyncJob extends AbstractJobPerformable<IsicMasterDataCronJobModel> {

    private static final Logger LOG = LoggerFactory.getLogger(IsicMasterDataSyncJob.class);

    private IsicMasterDataService isicMasterDataService;
    private IsicTextsService isicTextsService;
    private ModelService modelService;
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private Populator<IsicMasterSetData, IsicMasterModel> isicMasterDataReversePopulator;
    
    @Resource 
    private Populator<IsicTextsSetData, IsicTextsModel> isicTextsDataReversePopulator;

    @Override
    public PerformResult perform(IsicMasterDataCronJobModel isicMasterDataCronJobModel) {


        if(isicMasterDataCronJobModel.getDelta() != null && isicMasterDataCronJobModel.getDelta().equals(Boolean.TRUE)){
            processDeltaMasterInformation();
            processDeltaTextsInformation();
        }
        else
        {
        	processAllMasterInformation();
        	processAllTextsInformation();
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private void processAllMasterInformation() {

        LOG.info("Starting to get ISIC Master information's from CRM...");

        Collection<IsicMasterSetData> masterData = isicMasterDataService.getCollection();
        for (IsicMasterSetData isicMasterSetData : masterData) {
            try {
                if (isicMasterSetData != null) {
                    IsicMasterModel isicMasterModel = findIsicMasterModel(isicMasterSetData);
                    getIsicMasterDataReversePopulator().populate(isicMasterSetData, isicMasterModel);
                    modelService.save(isicMasterModel);
                }
            } catch (Exception exception) {
                LOG.error("IsicMaster information wih text: " + isicMasterSetData.getDescr_en() + " could not be saved in Hybris database.", exception);
            }
        }

        LOG.info("Successfully saved ISIC Master information in Hybris Database.");
    }

    private void processAllTextsInformation() {

        LOG.info("Starting to get ISIC Texts information's from CRM...");

        Collection<IsicTextsSetData> isicTextsData = isicTextsService.getCollection();

        for (IsicTextsSetData data : isicTextsData) {
            try {
                if (data != null) {
                    IsicTextsModel isicTextsModel = findIsicTextsModel(data);
                    getIsicTextsDataReversePopulator().populate(data, isicTextsModel);
                    modelService.save(isicTextsModel);
                }
            } catch (Exception exception) {
                LOG.error("IsicTexts information with text: " + data.getText_en() + " could not be saved in Hybris database.", exception);
            }
        }

        LOG.info("Successfully saved ISIC Texts information in Hybris Database.");
    }

    private void processDeltaMasterInformation() {

        LOG.info("Starting to get ISIC Master delta information's from CRM...");

        Collection<IsicMasterSetData> masterData = isicMasterDataService.getDeltaCollection();
        
        for (IsicMasterSetData isicMasterSetData : masterData) {
            try {
                if (isicMasterSetData != null) {
                    IsicMasterModel isicMasterModel = findIsicMasterModel(isicMasterSetData);
                    getIsicMasterDataReversePopulator().populate(isicMasterSetData, isicMasterModel);
                    modelService.save(isicMasterModel);
                }
            } catch (Exception exception) {
                LOG.error("Master information with text: " + isicMasterSetData.getDescr_en() + " could not be saved in Hybris database.", exception);
            }
        }

        LOG.info("Successfully saved ISIC Master information in Hybris Database.");
    }

    private void processDeltaTextsInformation() {

        LOG.info("Starting to get ISIC Texts delta information's from CRM...");

        Collection<IsicTextsSetData> isicTextsData = isicTextsService.getDeltaCollection();

        for (IsicTextsSetData data : isicTextsData) {
            try {
                if (data != null) {
                    IsicTextsModel isicTextsModel = findIsicTextsModel(data);
                    getIsicTextsDataReversePopulator().populate(data, isicTextsModel);
                    modelService.save(isicTextsModel);
                }
            } catch (Exception exception) {
                LOG.error("ISICTexts information with text: " + data.getText_en() + " could not be saved in Hybris database.", exception);
            }
        }

        LOG.info("Successfully saved Isic master information in Hybris Database.");

    }

    public IsicMasterModel findIsicMasterModel(IsicMasterSetData isicMasterSetData)
    {
        IsicMasterModel modelToSearch = new IsicMasterModel();
        modelToSearch.setIsicActivity(isicMasterSetData.getIsicactivity());
        List<IsicMasterModel> list =  getFlexibleSearchService().getModelsByExample(modelToSearch);

        if(list != null && list.size() > 0){
            return list.get(0);
        } else {
            return modelService.create(IsicMasterModel.class);
        }
    }

    public IsicTextsModel findIsicTextsModel(IsicTextsSetData isicTextsSetData)
    {
        IsicTextsModel modelToSearch = new IsicTextsModel();
        modelToSearch.setCode(isicTextsSetData.getIndsector());
        List<IsicTextsModel> list =  getFlexibleSearchService().getModelsByExample(modelToSearch);

        if(list != null && list.size() > 0){
            return list.get(0);
        } else {
            return modelService.create(IsicTextsModel.class);
        }
    }

    public IsicMasterDataService getIsicMasterDataService() {
        return isicMasterDataService;
    }

    public IsicTextsService getIsicTextsService() {
        return isicTextsService;
    }

    public Populator<IsicMasterSetData, IsicMasterModel> getIsicMasterDataReversePopulator() {
        return isicMasterDataReversePopulator;
    }

    public Populator<IsicTextsSetData, IsicTextsModel> getIsicTextsDataReversePopulator() {
        return isicTextsDataReversePopulator;
    }

    public void setIsicTextsService(IsicTextsService isicTextsService) {
        this.isicTextsService = isicTextsService;
    }

    public void setIsicMasterDataService(IsicMasterDataService isicMasterDataService) {
        this.isicMasterDataService = isicMasterDataService;
    }

    public void setIsicMasterDataReversePopulator(Populator<IsicMasterSetData, IsicMasterModel> isicMasterDataReversePopulator) {
        this.isicMasterDataReversePopulator = isicMasterDataReversePopulator;
    }

    public void setIsicTextsDataReversePopulator(Populator<IsicTextsSetData, IsicTextsModel> isicTextsDataReversePopulator) {
        this.isicTextsDataReversePopulator = isicTextsDataReversePopulator;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    @Override
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}