package org.silverstar.service;

import org.silverstar.model.SurveyQuestion;
import org.silverstar.model.SurveyQuestionId;
import org.silverstar.type.FileDataType;
import org.silverstar.type.FileType;
import org.silverstar.util.FileReaderUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReaderQuestionService {

    private final Pattern fileLineSplitPattern = Pattern.compile(
            "^(?<surveyId>[0-9]+)," +
                    "(\\s*)(?<groupId>[0-9]+)," +
                    "(\\s*)(?<questionId>[0-9]+)," +
                    "(\\s*)(?<question>.*)$");

    private String[] splitSurveyQuestionLine(String line) {
        Matcher m = fileLineSplitPattern.matcher(line);

        if (m.find()) {
            return new String [] {
                    m.group("surveyId"),
                    m.group("groupId"),
                    m.group("questionId"),
                    m.group("question")
            };
        }

        return new String [] {line};
    }

    public SurveyQuestion convertSurveyQuestion(String line) {

        String[] cols = splitSurveyQuestionLine(line);

        if (!FileReaderUtil.validateLine(FileType.SURVEY_QUESTION, cols)) {
            return null;
        }

        SurveyQuestionId id = SurveyQuestionId.builder()
                .surveyId(FileDataType.convertNumber(cols[0]))
                .groupId(FileDataType.convertNumber(cols[1]))
                .questionId(FileDataType.convertNumber(cols[2]))
                .build();

        return SurveyQuestion.builder()
                .id(id)
                .description(cols[3])
                .build();
    }

}
