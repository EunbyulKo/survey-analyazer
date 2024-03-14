package org.silverstar.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileColumnType {
    SURVEY_NUMBER("설문번호", FileDataType.NUMBER),
    SURVEY_NAME("설문이름", FileDataType.STRING),
    GROUP_NUMBER("그룹번호", FileDataType.NUMBER),
    QUESTION_NUMBER("설문문항번호", FileDataType.NUMBER),
    QUESTION_DESC("설문문항", FileDataType.STRING),
    ANSWER_NUMBER("설문문항보기번호", FileDataType.NUMBER),
    ANSWER_DESC("설문문항보기", FileDataType.STRING),

    RESULT_NUMBER("설문응시번호", FileDataType.NUMBER),
    TARGET_NUMBER("설문대상일련번호", FileDataType.NUMBER),
    USER_NUMBER("작성자번호", FileDataType.NUMBER),
    RESULT_RESPONSE("답변내용", FileDataType.NUMBER_ARRAY),

    RESULT_DATE("답변년월", FileDataType.DATE),
    RESULT_REG_DATE("작성년월일", FileDataType.DATE),

    SCORE("답변점수", FileDataType.NUMBER_OR_EMPTY),
    MAX_SCORE("최대점수", FileDataType.NUMBER_OR_EMPTY);

    private final String name;
    private final FileDataType fileDataType;

}
