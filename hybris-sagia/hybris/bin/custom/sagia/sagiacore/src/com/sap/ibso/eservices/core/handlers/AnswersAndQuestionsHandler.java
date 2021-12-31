package com.sap.ibso.eservices.core.handlers;

import com.sap.ibso.eservices.core.model.TicketAnswerModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import de.hybris.platform.ticket.model.CsTicketModel;

import java.util.Set;

public class AnswersAndQuestionsHandler extends AbstractDynamicAttributeHandler<String, CsTicketModel> {

    @Override
    public String get(CsTicketModel model) {
        if (model == null) {
            throw new IllegalArgumentException("Item model is required");
        }

        StringBuilder answerAndQuestionsList = new StringBuilder();

        Set<TicketAnswerModel> ticketAnswers = model.getAnswers();
        for (TicketAnswerModel ticketAnswerModel : ticketAnswers){

            if(ticketAnswerModel.getTicketQuestion() != null){
                answerAndQuestionsList.append("Question: " + ticketAnswerModel.getTicketQuestion().getQuestion());
                answerAndQuestionsList.append(":");

                String answerFormated = ticketAnswerModel.getAnswer().replace(",", " ").replace(";", ":").replace('"', ' ');

                answerAndQuestionsList.append("Answer: " + answerFormated);
                answerAndQuestionsList.append(",");
            }
        }

        if (model.getAnswers() != null) {
            return answerAndQuestionsList.toString();
        }

        return null;
    }

    @Override
    public void set(CsTicketModel model, String value) {
        // do nothing
    }

}