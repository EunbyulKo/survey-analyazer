package org.silverstar.service;

import org.silverstar.model.SurveyQuestionId;
import org.silverstar.model.SurveyResult;
import org.silverstar.type.FileDataType;
import org.silverstar.type.FileType;
import org.silverstar.util.FileReaderUtil;

import java.util.regex.Pattern;

public class FileReaderResultService {

    private final Pattern fileLineSplitPattern = Pattern.compile("\\s*,\\s*");

    public SurveyResult convertSurveyResult(String line) {

        String[] cols = fileLineSplitPattern.split(line);

        if (!FileReaderUtil.validateLine(FileType.SURVEY_RESULT, cols)) {
            return null;
        }

        SurveyQuestionId id = SurveyQuestionId.builder()
                .surveyId(FileDataType.convertNumber(cols[0]))
                .groupId(FileDataType.convertNumber(cols[3]))
                .questionId(FileDataType.convertNumber(cols[4]))
                .build();

        return SurveyResult.builder()
                .id(id)
                .responses(FileDataType.convertNumberArray(cols[5]))
                .resultId(FileDataType.convertNumber(cols[1]))
                .targetId(FileDataType.convertNumber(cols[2]))
                .resultDate(FileDataType.convertDate(cols[6]))
                .score(FileDataType.convertNumberOrEmpty(cols[7]))
                .maxScore(FileDataType.convertNumberOrEmpty(cols[8]))
                .uesrId(cols[9])
                .regDate(FileDataType.convertDate(cols[10]))
                .build();
    }

}
