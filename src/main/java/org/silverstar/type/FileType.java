package org.silverstar.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.silverstar.exception.FileFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
public enum FileType {
    SURVEY("survey.csv",
            List.of(FileColumnType.SURVEY_NUMBER, FileColumnType.SURVEY_NAME)),
    SURVEY_QUESTION("surveyQuestion.csv",
            List.of(FileColumnType.SURVEY_NUMBER, FileColumnType.GROUP_NUMBER,
                    FileColumnType.QUESTION_NUMBER, FileColumnType.QUESTION_DESC)),
    SURVEY_QUESTION_ANSWER("surveyQuestionAnswer.csv",
            List.of(FileColumnType.SURVEY_NUMBER, FileColumnType.GROUP_NUMBER, FileColumnType.QUESTION_NUMBER,
                    FileColumnType.ANSWER_NUMBER, FileColumnType.ANSWER_DESC)),
    SURVEY_RESULT("surveyResult.csv",
            List.of(FileColumnType.SURVEY_NUMBER, FileColumnType.RESULT_NUMBER, FileColumnType.TARGET_NUMBER,
                    FileColumnType.GROUP_NUMBER, FileColumnType.QUESTION_NUMBER, FileColumnType.RESULT_RESPONSE,
                    FileColumnType.RESULT_DATE, FileColumnType.SCORE, FileColumnType.MAX_SCORE,
                    FileColumnType.USER_NUMBER, FileColumnType.RESULT_REG_DATE));

    private final String fileName;
    private final List<FileColumnType> columns;

    public static void validateColumns(FileType fileType, String [] cols) throws FileFormatException {
        List<FileColumnType> columns = fileType.getColumns();

        if (columns.size() != cols.length) {
            throw new FileFormatException(fileType.name() + " columnsSize " + Arrays.toString(cols));
        }

        for (int i = 0; i < cols.length; i++) {
            if (!columns.get(i).getFileDataType().getPattern().matcher(cols[i]).matches()) {
                throw new FileFormatException(fileType.name() + " columnsType " + cols[i] + " " + Arrays.toString(cols));
            }

            if (columns.get(i).getFileDataType() == FileDataType.DATE) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false);
                    sdf.parse(cols[i]);
                } catch(ParseException e) {
                    throw new FileFormatException(fileType.name() + " columnsDate " + Arrays.toString(cols));
                }
            }
        }
    }

}
