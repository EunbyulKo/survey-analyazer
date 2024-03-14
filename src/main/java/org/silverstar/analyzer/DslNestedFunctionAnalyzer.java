package org.silverstar.analyzer;

import org.silverstar.model.*;
import org.silverstar.service.FileReaderService;
import org.silverstar.service.ResourceInspectService;

import java.util.List;

public class DslNestedFunctionAnalyzer {

    private final static FileReaderService fileReaderService = new FileReaderService();

    private final static ResourceInspectService resourceInspectService = new ResourceInspectService();


    public static List<SurveyBase> readSurveyFile() {
        return fileReaderService.getSurveyBase();
    }

    public static List<SurveyQuestion> readQuestionFile() {
        return fileReaderService.getSurveyQuestion();
    }

    public static List<SurveyQuestionAnswer> readAnswerFile() {
        return fileReaderService.getSurveyQuestionAnswer();
    }

    public static List<SurveyResult> readResultFile() {
        return fileReaderService.getSurveyResult();
    }

    public static ResourceFromFiles readFiles(List<SurveyBase> surveys, List<SurveyQuestion> questions,
                                               List<SurveyQuestionAnswer> answers, List<SurveyResult> results) {
        return ResourceFromFiles.builder().surveys(surveys).questions(questions).answers(answers).results(results).build();
    }

    public static void inspect(ResourceFromFiles files) {
        resourceInspectService.inspect(files);
    }
    
}
