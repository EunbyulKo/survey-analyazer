package org.silverstar.service;

import lombok.extern.slf4j.Slf4j;
import org.silverstar.model.ResourceFromFiles;

@Slf4j
public class ResourceInspectService {

    private final ResourceInspectSurveyService surveyService = new ResourceInspectSurveyService();
    private final ResourceInspectQuestionService questionService = new ResourceInspectQuestionService();
    private final ResourceInspectAnswerService answerService = new ResourceInspectAnswerService();
    private final ResourceInspectResultService resultService = new ResourceInspectResultService();

    public void inspect(ResourceFromFiles resource) {
        surveyService.checkDuplicateSurvey(resource.getSurveys());

        questionService.checkDuplicateQuestion(resource.getQuestions());
        questionService.checkSurveyAndQuestion(resource.getSurveys(), resource.getQuestions());

        answerService.checkDuplicateAnswer(resource.getAnswers());
        answerService.checkQuestionAndAnswer(resource.getQuestions(), resource.getAnswers());

        resultService.checkNoResponseResult(resource.getResults());
        resultService.checkAnswerAndResult(resource.getAnswers(), resource.getResults());
        resultService.checkNullScoreAndMaxScore(resource.getResults());
        resultService.checkMoreScoreThanMaxScore(resource.getResults());
        resultService.checkTargetIdAndUserId(resource.getResults());
    }

}
