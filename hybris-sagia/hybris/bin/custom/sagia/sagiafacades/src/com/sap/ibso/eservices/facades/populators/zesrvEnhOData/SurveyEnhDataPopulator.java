package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurvQuestDefEnhData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurveyHDREnhData;
import de.hybris.platform.converters.Populator;
import org.fest.util.Collections;
import org.fest.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class SurveyEnhDataPopulator implements Populator<SurveyHDREnhData, SurveyData> {
    private SagiaFormatProvider sagiaFormatProvider;
    private static final String CHECKBOX_TYPE = "3";

    @Override
    public void populate(final SurveyHDREnhData source, final SurveyData target)  {

        setTargetProperties(source, target);
        setDates(source, target);
        final List<SurveyQuestionsBlock> questionsBlocks = new ArrayList<>();
        target.setSections(questionsBlocks);
        SurveyQuestionsBlock currentBlock = new SurveyQuestionsBlock();
        currentBlock.setSections(new ArrayList<>());
        questionsBlocks.add(currentBlock);
        SurveyQuestionSection currentQuestionSection = new SurveyQuestionSection();
        currentQuestionSection.setQuestions(new ArrayList<>());
        if (source.getSurvQuestDefDataSet() == null) {
            return;
        }

        for (int i = 0; i < source.getSurvQuestDefDataSet().size(); i++) {
            final SurvQuestDefEnhData element = source.getSurvQuestDefDataSet().get(i);
            if (Strings.isEmpty(element.getAnswType())) {
                /* Prepare question sections */
                int index = element.getQuestTXT().indexOf(")");

                final String blockName = index > 0 ? element.getQuestTXT().substring(0, index - 2) : element.getQuestTXT();
                final String sectionName = index > 0 ? element.getQuestTXT().substring(index - 1).replaceAll("\\)", ". ") : "";

                if (Strings.isEmpty(currentBlock.getHeader())) {
                    currentBlock.setHeader(blockName);
                } else if (!currentBlock.getHeader().equals(blockName)) {
                    currentBlock = new SurveyQuestionsBlock();
                    currentBlock.setHeader(blockName);
                    currentBlock.setSections(new ArrayList<>());
                    questionsBlocks.add(currentBlock);
                }

                addCurrentQuestionSection(currentBlock, currentQuestionSection, sectionName);
            } else {
                addQuestionsToSections(currentQuestionSection, element);
            }
        }


    }

    private void addCurrentQuestionSection(SurveyQuestionsBlock currentBlock, SurveyQuestionSection currentQuestionSection, String sectionName) {
        if (Strings.isEmpty(currentQuestionSection.getHeader())) {
            currentQuestionSection.setHeader(sectionName);
            currentQuestionSection.setQuestions(new ArrayList<>());
            currentBlock.getSections().add(currentQuestionSection);
        } else if (!currentQuestionSection.getHeader().equals(sectionName)) {
            currentQuestionSection = new SurveyQuestionSection();
            currentQuestionSection.setHeader(sectionName);
            currentQuestionSection.setQuestions(new ArrayList<>());
            currentBlock.getSections().add(currentQuestionSection);
        }
    }

    private void setTargetProperties(SurveyHDREnhData source, SurveyData target) {
        target.setSurveyid(source.getSurveyid());

        if (Strings.isEmpty(source.getSurveytitle())) {
            target.setSurveytitle(source.getSurveyid());
        } else {
            target.setSurveytitle(source.getSurveytitle());
        }

        target.setSurveyversion(source.getSurveyVersion());
        target.setApplicationId(source.getApplicationId());
        target.setCreatedBy(source.getCreatedBy());
        target.setModifiedBy(source.getModifiedBy());
    }

    private void setDates(SurveyHDREnhData source, SurveyData target) {
        if (source.getMandatfrom() != null) {
            target.setMandatfrom(sagiaFormatProvider.getLocalizedDateData(source.getMandatfrom()));
        }
        if (source.getModifiedAt() != null) {
            target.setModifiedAt(sagiaFormatProvider.getLocalizedDateData(source.getModifiedAt()));
        }
        if (source.getCreatedAt() != null) {
            target.setCreatedAt(sagiaFormatProvider.getLocalizedDateData(source.getCreatedAt()));
        }
        if (source.getValidfrom() != null) {
            target.setValidfrom(sagiaFormatProvider.getLocalizedDateData(source.getValidfrom()));
        }
        if (source.getValidto() != null) {
            target.setValidto(sagiaFormatProvider.getLocalizedDateData(source.getValidto()));
        }
    }

    private void addQuestionsToSections(SurveyQuestionSection currentQuestionSection, SurvQuestDefEnhData element) {

        final SurveyQuestion surveyQuestion = new SurveyQuestion();

        surveyQuestion.setSurveyid(element.getSurveyId());
        surveyQuestion.setQuestID(element.getQuestID());
        surveyQuestion.setApplicationid(element.getApplicationId());
        surveyQuestion.setConsnr(element.getConsnr());
        surveyQuestion.setQuest(element.getQuest());
        surveyQuestion.setMandquest(element.getMandquest());
        surveyQuestion.setAnsTypeStd(element.getAnsTypeSt());
        surveyQuestion.setAnswType(element.getAnswType());

        if (!Strings.isEmpty(element.getQuestTXT()) && ':' == element.getQuestTXT().charAt(element.getQuestTXT().length() - 1)) {
            surveyQuestion.setQuestTXT(element.getQuestTXT().substring(0, element.getQuestTXT().length() - 1));
        } else {
            surveyQuestion.setQuestTXT(element.getQuestTXT());
        }


        final List<SurveyAnswer> answers = new ArrayList<>();
        surveyQuestion.setAnswers(answers);
        if (!Collections.isEmpty(element.getSurvQustDDLBDataSet())) {
            element.getSurvQustDDLBDataSet().forEach(answerElement -> {
                final SurveyAnswer answer = new SurveyAnswer();
                answer.setSurveyid(answerElement.getSurveyId());
                answer.setQuestID(answerElement.getQuestID());
                answer.setAnsID(answerElement.getAnsID());
                answer.setAnsText(answerElement.getAnsText());
                answer.setControlID(answerElement.getControlID());

                answers.add(answer);
            });
        }

        if (CHECKBOX_TYPE.equals(surveyQuestion.getAnswType()) && answers.size() == 1) {
            surveyQuestion.setAnswType("101");
        }

        currentQuestionSection.getQuestions().add(surveyQuestion);
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
