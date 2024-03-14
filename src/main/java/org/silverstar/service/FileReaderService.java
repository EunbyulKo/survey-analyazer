package org.silverstar.service;

import lombok.extern.slf4j.Slf4j;
import org.silverstar.model.*;
import org.silverstar.type.FileType;
import org.silverstar.util.FileReaderUtil;

import java.util.List;

@Slf4j
public class FileReaderService {

    private final FileReaderSurveyService fileReaderSurveyService = new FileReaderSurveyService();
    private final FileReaderQuestionService fileReaderQuestionService = new FileReaderQuestionService();
    private final FileReaderAnswerService fileReaderAnswerService = new FileReaderAnswerService();
    private final FileReaderResultService fileReaderResultService = new FileReaderResultService();

    private List<SurveyBase> getSurveyBase() {
        return FileReaderUtil.readSurveyFile(FileType.SURVEY.getFileName(),
                fileReaderSurveyService::convertSurveyBase);
    }

    private List<SurveyQuestion> getSurveyQuestion() {
        return FileReaderUtil.readSurveyFile(FileType.SURVEY_QUESTION.getFileName(),
                fileReaderQuestionService::convertSurveyQuestion);
    }

    private List<SurveyQuestionAnswer> getSurveyQuestionAnswer() {
        return FileReaderUtil.readSurveyFile(FileType.SURVEY_QUESTION_ANSWER.getFileName(),
                fileReaderAnswerService::convertSurveyQuestionAnswer);
    }

    private List<SurveyResult> getSurveyResult() {
        return FileReaderUtil.readSurveyFile(FileType.SURVEY_RESULT.getFileName(),
                fileReaderResultService::convertSurveyResult);
    }

    public ResourceFromFiles getResourceFromFiles() {
        List<SurveyBase> surveys = getSurveyBase();
        log.info("surveys : " + surveys.size());

        List<SurveyQuestion> questions = getSurveyQuestion();
        log.info("questions : " + questions.size());

        List<SurveyQuestionAnswer> answers = getSurveyQuestionAnswer();
        log.info("answers : " + answers.size());

        List<SurveyResult> results = getSurveyResult();
        log.info("results : " + results.size());

        return ResourceFromFiles.builder()
                .surveys(surveys)
                .questions(questions)
                .answers(answers)
                .results(results)
                .build();
    }

}
