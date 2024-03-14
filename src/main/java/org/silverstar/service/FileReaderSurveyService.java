package org.silverstar.service;

import org.silverstar.model.*;
import org.silverstar.type.FileDataType;
import org.silverstar.type.FileType;
import org.silverstar.util.FileReaderUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReaderSurveyService {
    private final Pattern fileLineSplitPattern = Pattern.compile("\\s*,\\s*");

    public SurveyBase convertSurveyBase(String line) {

        String[] cols = fileLineSplitPattern.split(line);

        if (!FileReaderUtil.validateLine(FileType.SURVEY, cols)) {
            return null;
        }

        return SurveyBase.builder()
                .id(FileDataType.convertNumber(cols[0]))
                .name(cols[1])
                .build();
    }

}
