package org.silverstar.service;

import org.silverstar.model.SurveyQuestionAnswer;
import org.silverstar.model.SurveyQuestionAnswerId;
import org.silverstar.type.FileDataType;
import org.silverstar.type.FileType;
import org.silverstar.util.FileReaderUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReaderAnswerService {

    private final Pattern fileLineSplitPattern = Pattern.compile(
            "^(?<surveyId>[0-9]+)," +
                    "(\\s*)(?<groupId>[0-9]+)," +
                    "(\\s*)(?<questionId>[0-9]+)," +
                    "(\\s*)(?<answerId>[0-9]+)," +
                    "(\\s*)(?<answer>.*)$");;


    private String[] splitSurveyQuestionAnswerLine(String line) {
        Matcher m = fileLineSplitPattern.matcher(line);

        while(m.find()) {
            return new String [] {
                    m.group("surveyId"),
                    m.group("groupId"),
                    m.group("questionId"),
                    m.group("answerId"),
                    m.group("answer")
            };
        }

        return new String [] {line};
    }

    public SurveyQuestionAnswer convertSurveyQuestionAnswer(String line) {

        String[] cols = splitSurveyQuestionAnswerLine(line);

        if (!FileReaderUtil.validateLine(FileType.SURVEY_QUESTION_ANSWER, cols)) {
            return null;
        }

        SurveyQuestionAnswerId id = SurveyQuestionAnswerId.builder()
                .surveyId(FileDataType.convertNumber(cols[0]))
                .groupId(FileDataType.convertNumber(cols[1]))
                .questionId(FileDataType.convertNumber(cols[2]))
                .answerId(FileDataType.convertNumber(cols[3]))
                .build();

        return SurveyQuestionAnswer.builder()
                .id(id)
                .description(cols[4])
                .build();
    }

}
