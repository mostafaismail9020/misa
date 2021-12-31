package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaTutorialModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaTutorialService;
import com.sap.ibso.eservices.facades.data.tutorial.SagiaTutorialData;
import com.sap.ibso.eservices.facades.populators.SagiaTutorialDataPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaTutorialFacade;

/**
 * DefaultSagiaTutorialFacade
 * @package com.sap.ibso.eservices.facades.sagia.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaTutorialFacade implements SagiaTutorialFacade {
    private SagiaTutorialService sagiaTutorialService;
    private SagiaTutorialDataPopulator sagiaTutorialDataPopulator;

    @Override
    public SagiaTutorialData getTutorial(String pageUrl) {
        SagiaTutorialModel tutorialModel = sagiaTutorialService.getSagiaTutorial(pageUrl);
        SagiaTutorialData tutorialData = null;
        if(tutorialModel != null){
            tutorialData = new SagiaTutorialData();
            sagiaTutorialDataPopulator.populate(tutorialModel, tutorialData);
        }
        return tutorialData;
    }

    public void setSagiaTutorialService(SagiaTutorialService sagiaTutorialService) {
        this.sagiaTutorialService = sagiaTutorialService;
    }

    public void setSagiaTutorialDataPopulator(SagiaTutorialDataPopulator sagiaTutorialDataPopulator) {
        this.sagiaTutorialDataPopulator = sagiaTutorialDataPopulator;
    }
}
